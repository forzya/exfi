package com.exfi.manageruser.api;

import com.exfi.manageruser.ActivityStatus;
import com.exfi.manageruser.DirectoryUser;
import com.exfi.manageruser.repository.IDirectoryUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manager_user")
public class ManagerUserApi {

    private IDirectoryUserRepository repository;

    public ManagerUserApi(IDirectoryUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getGroups")
    ResponseEntity<List<DirectoryUser>> getGroups() {
        System.out.println("getGroups");
        List<DirectoryUser> directoryGroups = repository.findAll();

        return new ResponseEntity<>(directoryGroups, HttpStatus.OK);
    }


    @GetMapping("/create")
    ResponseEntity<Void> createGroup() {
        System.out.println("create");

        DirectoryUser directoryGroup = new DirectoryUser("el1", "1l@mail.ru", ActivityStatus.ACTIVE);
        repository.save(directoryGroup);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test1")
    ResponseEntity<Void> test1() {
        System.out.println("test1");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test2")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Void> test2() {
        System.out.println("test2");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test3")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Void> test3() {
        System.out.println("test3");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
