package com.hendisantika.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-reset-password2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/04/22
 * Time: 19.40
 */
@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signup.html")
    public String signup() {
        return "signup";
    }

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/reset.html")
    public String reset() {
        return "reset";
    }

    @GetMapping("/index.html")
    public String indexHtml() {
        return "index";
    }
}
