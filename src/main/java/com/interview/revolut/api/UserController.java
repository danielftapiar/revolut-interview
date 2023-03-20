package com.interview.revolut.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public BasicUserService service;

    @PutMapping("/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody User userDetails) {
        userDetails.setUsername(username);

        this.service.createUser(userDetails);

        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{username}")
    public ResponseEntity<String> getUser(@PathVariable String username) {
        User user = this.service.findUser(username);

        if (user == null ){
            return new ResponseEntity<>("{ “message“: “User Not Found“ }", HttpStatus.BAD_REQUEST);
        }

        if( this.service.checkIfBirthdateIsToday(user)){
            return new ResponseEntity<>("{ “message”: “Hello, "+user.getUsername()+"! Happy Birthday", HttpStatus.OK);
        }

        Long days = this.service.getNumberOfDaysTillNextBirthday(user);

        return new ResponseEntity<>("{ “message”: “Hello, "+user.getUsername()+"! Your birthday is in "+days+"day(s)}", HttpStatus.OK);
    }
}
