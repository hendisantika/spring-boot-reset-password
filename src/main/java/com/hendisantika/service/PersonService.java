package com.hendisantika.service;

import com.hendisantika.entity.Person;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-reset-password2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/04/22
 * Time: 19.35
 */
public interface PersonService {

    Person login(Person person);

    Person  signup(Person person);

    boolean updatePassword(String email, String password);

    Person findByEmail(String email);
}
