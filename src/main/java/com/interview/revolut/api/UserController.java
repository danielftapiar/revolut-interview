package com.interview.revolut.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/hello")
public class UserController {

    @Autowired
    public UserRepository userRepository;
    @PutMapping("/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody User userDetails) {
        User user = userRepository.findByUsername(username);

        if ( user == null ){
            user = new User();
            user.setDateOfBirth(userDetails.getDateOfBirth());
            user.setUsername(username);
        }else {
            user.setDateOfBirth(userDetails.getDateOfBirth());
        }
        userRepository.save(user);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{username}")
    public ResponseEntity<String> getUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);

        if (user == null ){
            return new ResponseEntity<>("{ “message“: “User Not Found“ }", HttpStatus.BAD_REQUEST);
        }

        LocalDate today = LocalDate.now();

        if (today.getMonthValue() == user.getDateOfBirth().getMonthValue() && today.getDayOfYear() == user.getDateOfBirth().getDayOfYear()){
            return new ResponseEntity<>("{ “message”: “Hello, "+user.getUsername()+"! Happy Birthday", HttpStatus.OK);
        }

        LocalDate nextBirthday = user.getDateOfBirth().withYear(today.getYear());
        if (nextBirthday.isBefore(today) || nextBirthday.isEqual(today)) {
            nextBirthday = nextBirthday.plusYears(1).minusDays(1);
        }

        long between = ChronoUnit.DAYS.between(today,nextBirthday);

        return new ResponseEntity<>("{ “message”: “Hello, "+user.getUsername()+"! Your birthday is in "+between+"day(s)}", HttpStatus.OK);
    }
}
