package com.wongc.stm.controller;

import com.wongc.stm.security.JwtUtils;
import com.wongc.stm.security.UserDetailsImpl;
import com.wongc.stm.wrapper.AuthRequestWrapper;
import com.wongc.stm.wrapper.AuthWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/backend/authenticate")
    public ResponseEntity<?> login(@RequestBody AuthRequestWrapper auth) {
        // TODO: implement login logic
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(auth.getUsername(),auth.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new AuthWrapper(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));

    }

}
