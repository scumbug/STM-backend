package com.wongc.stm.scheduler;

import com.wongc.stm.model.Constants;
import com.wongc.stm.model.Lease;
import com.wongc.stm.model.Payment;
import com.wongc.stm.repository.AdminRepository;
import com.wongc.stm.repository.LeaseRepository;
import com.wongc.stm.repository.PaymentRepository;
import com.wongc.stm.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class PaymentScheduler {

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private AdminRepository adminRepository;

    private final LocalDate today = LocalDate.now();

    //@Scheduled(fixedRate = 5000)
    public void generatePayment() {
        // Grab and filter leases with start_date matching current date
        List<Lease> leases = leaseRepository.findByDayMonth(today.getDayOfMonth(),today.getMonthValue());
        if(!leases.isEmpty()) {
            // Generate payment record
            for(Lease lease : leases) {
                Payment payment = new Payment();
                payment.setPaymentStatus(0);
                payment.setLeaseId(lease.getLeaseId());
                payment.setTenantId(lease.getTenantId());
                payment.setPaymentStartPeriod(Date.valueOf(LocalDate.now()));
                payment.setPaymentEndPeriod(Date.valueOf(LocalDate.now().plusMonths(1)));
                paymentRepository.save(payment);
            }
        }
    }

    @Scheduled(fixedRate = 5000)
    public void runReminders() {
        //Grab unpaid rent, collate and send email to tenants
        List<Payment> unpaidRent = paymentRepository.findByPaymentStatus(0);

        //Define email token replacement
        Pattern pattern = Pattern.compile("\\{(.+?)\\}");
        String template = adminRepository.findByName("email_template").getContent();

        if(!unpaidRent.isEmpty()) {
            // aggregate total number of months unpaid per tenant
            Map<Long, Long> tenantOutstanding = unpaidRent.stream().collect(Collectors.groupingBy(Payment::getTenantId, Collectors.counting()));
            // get amount per tenant for the month and multiply to map
            for( Long tenantId : tenantOutstanding.keySet()) {
                Lease lease = leaseRepository.findByTenantId(tenantId);
                tenantOutstanding.put(tenantId, (long) (tenantOutstanding.get(tenantId) * lease.getRent()));
                // send email
                Map<String, String> replacements = new HashMap<>();
                replacements.put("totalDueAmount",tenantOutstanding.get(tenantId).toString());
                Matcher matcher = pattern.matcher(template);
                StringBuffer buffer = new StringBuffer();
                while(matcher.find()) {
                    String replacement = replacements.get(matcher.group(1));
                    if(replacement != null) {
                        matcher.appendReplacement(buffer,"");
                        buffer.append(replacement);
                    }
                }
                matcher.appendTail(buffer);
                System.out.println(buffer.toString());

            }
            // send email

            System.out.println(tenantOutstanding);
        }
    }
}
