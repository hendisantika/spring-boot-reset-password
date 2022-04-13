package com.hendisantika.repository;

import com.hendisantika.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-reset-password2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/04/22
 * Time: 19.34
 */
@RepositoryRestResource(path = "/test", collectionResourceRel = "test")
public interface PersonRepository extends JpaRepository<Person, Long> {

    @RestResource(exported = false)
    @Query("SELECT p FROM Person AS p WHERE p.email =:email AND p.password =:password")
    Person login(@Param("email") String email , @Param("password")String password);

    @RestResource(exported = false)
    @Query("SELECT p FROM Person AS p WHERE p.email =:email")
    Person getPersonByEmail(@Param("email") String email);
}
