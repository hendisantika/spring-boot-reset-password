package com.hendisantika.controller;

import com.hendisantika.dto.Message;
import com.hendisantika.dto.ResetPasswordRequest;
import com.hendisantika.entity.Person;
import com.hendisantika.service.PersonService;
import com.hendisantika.util.ResetPasswordDemoUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-reset-password2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/04/22
 * Time: 20.10
 */
@Slf4j
@RestController
@RequestMapping("/reset-pass-demo")
@RequiredArgsConstructor
public class PersonController {

    private static final String USER = "user";
    private final PersonService personService;

    @PostMapping("/signup")
    public ResponseEntity<Message> signup(@RequestBody Person person) {
        try {
            if (ResetPasswordDemoUtil.emailValidator(person.getEmail())) {
                Person existingPerson = personService.findByEmail(person.getEmail());
                if (existingPerson != null) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new Message("Email already exists"));
                }
                Person savedPerson = personService.signup(person);
                return ResponseEntity.ok(new Message("User registered successfully"));
            } else {
                return ResponseEntity.badRequest()
                        .body(new Message("Invalid email format"));
            }
        } catch (Exception e) {
            log.error("Error during signup", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Message("Error during registration"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody Person person, HttpServletRequest request) {
        try {
            Person loggedInPerson = personService.login(person);
            if (loggedInPerson != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute(USER, loggedInPerson);
                return ResponseEntity.ok(new Message("Login successful"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new Message("Invalid email or password"));
            }
        } catch (Exception e) {
            log.error("Error during login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Message("Error during login"));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Message> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            Person person = personService.findByEmail(request.getEmail());
            if (person != null) {
                // Generate reset token and send email
                String token = ResetPasswordDemoUtil.getSaltString();
                // TODO: Store token and send email
                // For now, just return success
                return ResponseEntity.ok(new Message("Password reset link sent to your email"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Message("Email not found"));
            }
        } catch (Exception e) {
            log.error("Error during password reset", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Message("Error during password reset"));
        }
    }

    @PostMapping("/update-password")
    public ResponseEntity<Message> updatePassword(@RequestBody ResetPasswordRequest request) {
        try {
            boolean updated = personService.updatePassword(request.getEmail(), request.getNewPassword());
            if (updated) {
                return ResponseEntity.ok(new Message("Password updated successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Message("Failed to update password"));
            }
        } catch (Exception e) {
            log.error("Error updating password", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Message("Error updating password"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Message> logout(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            return ResponseEntity.ok(new Message("Logout successful"));
        } catch (Exception e) {
            log.error("Error during logout", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Message("Error during logout"));
        }
    }
}
