package io.learning.demo.controller;

import io.learning.demo.model.user.User;
import io.learning.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/user")
@CrossOrigin("*")
public class UserController {

    private UserService userService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/all")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping(value = "/save")
    public ResponseEntity save(@RequestBody User user) {

        try {
            return ResponseEntity.ok(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity update(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.update(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    //TODO : Since we don't want to delete a user we can create a column named 'deleted' in users table and
    // set his value to true when registered and to false when deleted and
    // when retrieving users we look for those where deleted = false
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
