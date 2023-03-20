package com.interview.revolut.api;

public interface UserService {
    public User createUser(User user);
    public User findUser(String username);
    public User findUser(User user);

    public Long getNumberOfDaysTillNextBirthday(User user);
    public Boolean checkIfBirthdateIsToday(User user);

}
