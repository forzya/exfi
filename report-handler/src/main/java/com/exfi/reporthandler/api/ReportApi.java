package com.exfi.reporthandler.api;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/report")
public class ReportApi {


    @Autowired
    private KeycloakRestTemplate template;

    @GetMapping("/upload")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<String> upload() {
        System.out.println("getGroups");
        String endpoint = "http://localhost:8060/manager/getTest1";
        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
        System.out.println(response.getBody());

        return new ResponseEntity<>("hellom", HttpStatus.OK);
    }


    @GetMapping("/upload2")
    ResponseEntity<String> upload2() {
        System.out.println("getGroups");
        String endpoint = "http://localhost:8060/manager/getTest1";
//        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
//        System.out.println(response.getBody());

        return new ResponseEntity<>("hellom", HttpStatus.OK);
    }

}
