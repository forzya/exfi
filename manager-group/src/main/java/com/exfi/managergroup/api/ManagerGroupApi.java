package com.exfi.managergroup.api;

import com.exfi.managergroup.ActivityStatus;
import com.exfi.managergroup.DirectoryGroup;
import com.exfi.managergroup.repository.IDirectoryGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manager")
public class ManagerGroupApi {

    private final Logger logger = LoggerFactory.getLogger(ManagerGroupApi.class);

    private final IDirectoryGroupRepository repository;

    @Autowired
    public ManagerGroupApi(IDirectoryGroupRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getGroup")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<DirectoryGroup> getGroup(@RequestParam Long id) {
        logger.info("Get group by id " + repository.findByActivityStatus(ActivityStatus.ACTIVE));
        Optional<DirectoryGroup> optionalDirectoryGroup = repository.findById(id);
        if (optionalDirectoryGroup.isPresent()) {
            DirectoryGroup directoryGroup = optionalDirectoryGroup.get();
            return new ResponseEntity<>(directoryGroup, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getGroups")
    ResponseEntity<List<DirectoryGroup>> getGroups(@RequestParam List<Long> ids) {
        List<DirectoryGroup> directoryGroups = repository.findAllById(ids);
        return new ResponseEntity<>(directoryGroups, HttpStatus.OK);
    }

    @GetMapping("/getAllGroups")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<DirectoryGroup>> getAllGroups() {
        logger.info("Get all groups");
        List<DirectoryGroup> directoryGroups = repository.findAll();
        return new ResponseEntity<>(directoryGroups, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteById(@RequestParam Long id) {
        logger.info("delete group by id " + id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectoryGroup> create(@RequestBody DirectoryGroup newGroup) {
        logger.info("save group ");
        DirectoryGroup user = repository.save(newGroup);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/update")
    ResponseEntity<Void> updateTest(@RequestParam Long id) {
        logger.info("update group with id " + id);
        Optional<DirectoryGroup> optionalDirectoryGroup = repository.findById(id);
        if (optionalDirectoryGroup.isPresent()) {
            DirectoryGroup directoryGroup = optionalDirectoryGroup.get();
            directoryGroup.setActivityStatus(ActivityStatus.INACTIVE);
            repository.save(directoryGroup);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/test")
    ResponseEntity<String> test() {
        return new ResponseEntity<>("hrlll", HttpStatus.OK);
    }

}
