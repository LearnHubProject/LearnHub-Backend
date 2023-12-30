package org.learnhub.backend.controller;

import org.learnhub.backend.model.SchoolRole;
import org.learnhub.backend.model.authorization.SchoolPermission;
import org.learnhub.backend.service.AuthorizationService;
import org.learnhub.backend.service.SchoolService;
import org.learnhub.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//Warning: Entering ecological dead zone. Adding report to databank.

@Profile("debug")
@RestController()
@RequestMapping("/debug")
public class DebugController {


    @Autowired
    SchoolService schoolService;

    @Autowired
    UserService userService;

    @Autowired
    AuthorizationService authorizationService;

    @GetMapping("/state")
    public String debug(){
        return "true";
    }

    @GetMapping("/class/add-student")
    public String assignStudent(@RequestParam long schoolId, @RequestParam long userId, @RequestParam SchoolRole role){
        schoolService.addSchoolMember(schoolId, userId, role);
        return "good";
    }

    @GetMapping("/school/admin/authorization")
    public String checkAuthorization(Authentication authentication, @RequestParam(name = "id") long schoolId){
        boolean authorizationResult = authorizationService.checkSchoolPermission(authentication, 1L, SchoolPermission.MANAGE_SCHOOL);

        if(authorizationResult){
            return "good!";
        }
        else return "bad!";
    }
}
