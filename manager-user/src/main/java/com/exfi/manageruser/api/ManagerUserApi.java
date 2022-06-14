package com.exfi.manageruser.api;

import com.exfi.manageruser.DirectoryUser;
import com.exfi.manageruser.repository.IDirectoryUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manager_user")
@Slf4j
public class ManagerUserApi {

    private IDirectoryUserRepository repository;

    public ManagerUserApi(IDirectoryUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getAllUser")
    ResponseEntity<List<DirectoryUser>> getAllUser() {
        log.info("getAllUser");
        List<DirectoryUser> directoryUsers = repository.findAll();

        return new ResponseEntity<>(directoryUsers, HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    ResponseEntity<List<DirectoryUser>> getUsers(@RequestParam List<Long> ids) {
        log.info("getUsers");
        List<DirectoryUser> directoryUsers = repository.findAllById(ids);

        return new ResponseEntity<>(directoryUsers, HttpStatus.OK);
    }

    @GetMapping("/getUsersByEmail")
    ResponseEntity<List<DirectoryUser>> getUsersByEmail(@RequestParam String email) {
        log.info("getUsersByEmail");
        List<DirectoryUser> directoryUsers = repository.findByEmail(email);

        return new ResponseEntity<>(directoryUsers, HttpStatus.OK);
    }

    @GetMapping("/getUser")
    ResponseEntity<DirectoryUser> getUser(@RequestParam Long id) {
        log.info("getUser");
        Optional<DirectoryUser> optionalDirectoryUser = repository.findById(id);
        if (optionalDirectoryUser.isPresent()) {
            DirectoryUser directoryUser = optionalDirectoryUser.get();
            return new ResponseEntity<>(directoryUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
