package org.learnhub.backend.controller.admin;

import org.learnhub.backend.api.payload.request.CreateSchoolRequest;
import org.learnhub.backend.api.payload.response.CreateSchoolResponse;
import org.learnhub.backend.api.payload.response.ListSchoolsResponse;
import org.learnhub.backend.misc.payloads.GenericFailureMessageResponse;
import org.learnhub.backend.misc.payloads.ResponsePayload;
import org.learnhub.backend.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/root")
public class AdminController {

    @Autowired
    SchoolService schoolService;

    @PostMapping("/school/create")
    public ResponseEntity<ResponsePayload> createSchool(Authentication authentication, @RequestBody CreateSchoolRequest request) {
        try{
            CreateSchoolResponse response = schoolService.createSchool(request.getName());
            return ResponseEntity.ok().body(response);
        }
        catch (Exception e){
            GenericFailureMessageResponse response = new GenericFailureMessageResponse("Something went wrong during school creation.");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/school/list")
    public ResponseEntity<ResponsePayload> listSchools(Authentication authentication){
        ListSchoolsResponse response = new ListSchoolsResponse(schoolService.listSchools().stream().map(schoolDetailsDTO -> new ListSchoolsResponse.School(schoolDetailsDTO.getId(), schoolDetailsDTO.getName())).toList());
        return ResponseEntity.ok().body(response);
    }

}
