package com.exfi.managergroup.api;

import com.exfi.managergroup.ActivityStatus;
import com.exfi.managergroup.DirectoryGroup;
import com.exfi.managergroup.repository.IDirectoryGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manager")
@Slf4j
public class ManagerGroupApi {


    private final IDirectoryGroupRepository repository;

    @Autowired
    public ManagerGroupApi(IDirectoryGroupRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getGroup")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<DirectoryGroup> getGroup(@RequestParam Long id) {
        log.info("Get group by id " + repository.findByActivityStatus(ActivityStatus.ACTIVE));
        Optional<DirectoryGroup> optionalDirectoryGroup = repository.findById(id);
        if (optionalDirectoryGroup.isPresent()) {
            DirectoryGroup directoryGroup = optionalDirectoryGroup.get();
            return new ResponseEntity<>(directoryGroup, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getGroups")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<List<DirectoryGroup>> getGroups(@RequestParam List<Long> ids) {
        List<DirectoryGroup> directoryGroups = repository.findAllById(ids);
        return new ResponseEntity<>(directoryGroups, HttpStatus.OK);
    }

    @GetMapping("/getAllGroups")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<List<DirectoryGroup>> getAllGroups() {
        log.info("Get all groups");
        List<DirectoryGroup> directoryGroups = repository.findAll();
        return new ResponseEntity<>(directoryGroups, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Void> deleteById(@RequestParam Long id) {
        log.info("delete group by id " + id);
        Optional<DirectoryGroup> optionalDirectoryGroup = repository.findById(id);
        if (optionalDirectoryGroup.isPresent()) {
            DirectoryGroup directoryGroup = optionalDirectoryGroup.get();
            directoryGroup.setActivityStatus(ActivityStatus.INACTIVE);
            directoryGroup.setDeletionDateTime(LocalDateTime.now());
            repository.save(directoryGroup);
//            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DirectoryGroup> create(@RequestBody @Valid DirectoryGroup newGroup) {
        log.info("save group ");
        DirectoryGroup user = repository.save(newGroup);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Void> updateTest(@RequestParam Long id) {
        log.info("update group with id " + id);
        Optional<DirectoryGroup> optionalDirectoryGroup = repository.findById(id);
        if (optionalDirectoryGroup.isPresent()) {
            DirectoryGroup directoryGroup = optionalDirectoryGroup.get();
            directoryGroup.setActivityStatus(ActivityStatus.INACTIVE);
            repository.save(directoryGroup);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(path = "/createtest")
    public ResponseEntity<DirectoryGroup> create() {
        log.info("save group ");
        DirectoryGroup directoryGroup = new DirectoryGroup("1234567890", ActivityStatus.ACTIVE, List.of("em1"));
        DirectoryGroup user = repository.save(directoryGroup);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
