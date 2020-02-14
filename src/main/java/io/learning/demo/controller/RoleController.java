package io.learning.demo.controller;


import io.learning.demo.model.role.Role;
import io.learning.demo.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/role")
@CrossOrigin("*")
public class RoleController {

    private RoleService roleService;

    public RoleController() {
    }

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping(value = "/all")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @PostMapping(value = "/save")
    public ResponseEntity save(@RequestBody Role role) {

        try {
            return ResponseEntity.ok(roleService.save(role));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity update(@RequestBody Role role) {
        try {
            return ResponseEntity.ok(roleService.update(role));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    //TODO : Since we don't want to delete a role we can create a column named 'deleted' in roles table and
    // set his value to true when registered and to false when deleted and
    // when retrieving roles we look for those where deleted = false
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
