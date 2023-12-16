package org.learnhub.backend.controller;

import org.learnhub.backend.database.entity.SchoolMember;
import org.learnhub.backend.database.repostitory.SchoolsRepository;
import org.learnhub.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
class ApiController {

    @Autowired
    SchoolsRepository schoolsRepository;

    @Autowired
    UserService userService;

    @GetMapping("/status")
    public @ResponseBody String getStatus(Authentication authentication) {
        Set<SchoolMember> members= userService.getUserAccountByEmail(authentication.getName()).getMembers();
        return members.toString();
    }





}
