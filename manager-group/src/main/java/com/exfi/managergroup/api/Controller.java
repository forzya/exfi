package com.exfi.managergroup.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Controller {

    //    @PreAuthorize("hasRole('USER')")
    @GetMapping("/ping")
    public String ping() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        String principal = authentication.getPrincipal().toString();
        System.out.println("principal_ " + principal);

        String details = authentication.getDetails().toString();
        System.out.println("details_ " + details);

        return "ok";
    }
}