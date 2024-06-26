package org.learnhub.backend.controller;

import org.learnhub.backend.database.repostitory.SchoolsRepository;
import org.learnhub.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class ApiController {

    @Autowired
    SchoolsRepository schoolsRepository;

    @Autowired
    UserService userService;

    @GetMapping("/status")
    public @ResponseBody String getStatus(Authentication authentication) {
        return "I'm fine. I guess...";
    }





}
