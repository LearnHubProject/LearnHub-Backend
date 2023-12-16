package org.learnhub.backend.controller.admin;

import org.learnhub.backend.database.entity.School;
import org.learnhub.backend.api.payload.request.CreateSchoolRequest;
import org.learnhub.backend.api.payload.response.CreateSchoolResponse;
import org.learnhub.backend.misc.payloads.ResponsePayload;
import org.learnhub.backend.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/root")
public class AdminController {

    @Autowired
    SchoolService schoolService;

    @PostMapping("/school/create")
    public ResponseEntity<ResponsePayload> createSchool(Authentication authentication, @RequestBody CreateSchoolRequest request) {

        CreateSchoolResponse response = schoolService.createSchool(request.getName());

        return ResponseEntity.ok().body(response);
    }

}
