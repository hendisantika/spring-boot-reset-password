package com.hendisantika.service;

import com.hendisantika.entity.Person;
import com.hendisantika.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-reset-password2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/04/22
 * Time: 19.35
 */
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    Map<Person, String> tokenRegister = new HashMap<>();

    private final PersonRepository personRepository;

    @Override
    public Person  signup(Person person) {
        String newPassword = ResetPasswordDemoUtil.encryptSHY2(person.getPassword());
        person.setPassword(newPassword);
        return personRepository.save(person);
    }

    @Override
    public Person  login(Person person) {
        String newPass = ResetPasswordDemoUtil.encryptSHY2(person.getPassword());
        return personRepository.login(person.getEmail(), newPass);
    }
}
