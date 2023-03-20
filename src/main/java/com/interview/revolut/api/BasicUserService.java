package com.interview.revolut.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class BasicUserService implements UserService {
    @Autowired
    public UserRepository userRepository;
    @Override
    public User createUser(User userDetails) {
        if (userDetails == null ){
            return null;
        }
        User user = userRepository.findByUsername(userDetails.getUsername());

        if ( user == null ){
            user = new User();
            user.setDateOfBirth(userDetails.getDateOfBirth());
            user.setUsername(userDetails.getUsername());
        }else {
            user.setDateOfBirth(userDetails.getDateOfBirth());
        }
        
        userRepository.save(user);
        return user;
    }

    @Override
    public User findUser(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null ){
            return null;
        }

        return user;
    }

    @Override
    public User findUser(User user) {
        User userFound = userRepository.findByUsername(user.getUsername());

        if (userFound == null ){
            return null;
        }

        return userFound;
    }

    @Override
    public Long getNumberOfDaysTillNextBirthday(User user) {
        LocalDate today = LocalDate.now();

        LocalDate nextBirthday = user.getDateOfBirth().withYear(today.getYear());
        if (nextBirthday.isBefore(today) || nextBirthday.isEqual(today)) {
            nextBirthday = nextBirthday.plusYears(1).minusDays(1);
        }

        return ChronoUnit.DAYS.between(today,nextBirthday);
    }

    @Override
    public Boolean checkIfBirthdateIsToday(User user) {
        LocalDate today = LocalDate.now();

        if (today.getMonthValue() == user.getDateOfBirth().getMonthValue() && today.getDayOfYear() == user.getDateOfBirth().getDayOfYear()){
            return true;
        } else {
            return false;
        }
    }
}
