package org.learnhub.backend.controller.school;

import org.learnhub.backend.api.factory.SchoolResponseFactory;
import org.learnhub.backend.api.payload.request.CreateClassRequest;
import org.learnhub.backend.api.payload.request.SetClassTeacherRequest;
import org.learnhub.backend.api.payload.response.ListSchoolMembersResponse;
import org.learnhub.backend.data.dto.ClassDetailsDTO;
import org.learnhub.backend.data.dto.SchoolMemberDTO;
import org.learnhub.backend.misc.Result;
import org.learnhub.backend.misc.payloads.GenericFailureMessageResponse;
import org.learnhub.backend.misc.payloads.ResponsePayload;
import org.learnhub.backend.model.authorization.SchoolPermission;
import org.learnhub.backend.service.AuthorizationService;
import org.learnhub.backend.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controller used by the school admin to manage school
@RestController()
@RequestMapping("/api/admin")
public class SchoolAdminController {

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    SchoolService schoolService;

    @GetMapping("/schools/{schoolId}/members")
    public ResponseEntity<ResponsePayload> listSchoolMembers(Authentication authentication, @PathVariable("schoolId") long schoolId){
        if(!authorizationService.checkSchoolPermission(authentication, schoolId, SchoolPermission.MANAGE_MEMBERS)) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        List<SchoolMemberDTO> schoolMemberDTOS = schoolService.listSchoolMembers(schoolId);

        ListSchoolMembersResponse response = SchoolResponseFactory.createListSchoolMembersResponse(schoolMemberDTOS);

        return ResponseEntity.ok().body(response);
    }

/*    @GetMapping("/schools/{schoolId}/requests")
    public ResponseEntity<ResponsePayload> listRequests(Authentication authentication, @PathVariable("schoolId") long schoolId){
        if(!authorizationService.checkSchoolPermission(authentication, schoolId, SchoolPermission.MANAGE_MEMBERS)) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}


    }*/

    @PostMapping("/schools/{schoolId}/classes/create")
    public ResponseEntity<ResponsePayload> createClass(Authentication authentication, @PathVariable("schoolId") long schoolId, @RequestBody CreateClassRequest createClassRequest){
        if(!authorizationService.checkSchoolPermission(authentication, schoolId, SchoolPermission.MANAGE_CLASSES)) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        Result<ClassDetailsDTO> classCreationResult = schoolService.createClass(schoolId, createClassRequest.getClassName(), createClassRequest.getTeacherId());

        if(!classCreationResult.isSuccessful()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericFailureMessageResponse(classCreationResult.getErrorMessage()));
        }
        return ResponseEntity.ok().body(SchoolResponseFactory.createClassResponse(classCreationResult.getResult()));
    }

    @PostMapping("/classes/{classId}/set-teacher")
    public ResponseEntity<ResponsePayload> setClassTeacher(Authentication authentication, @PathVariable("classId") long classId, @RequestBody SetClassTeacherRequest setClassTeacherRequest){
        ClassDetailsDTO targetClass = schoolService.getClassById(classId).get();
        if (!authorizationService.checkSchoolPermission(authentication, targetClass.getSchoolDetails().getId(), SchoolPermission.MANAGE_CLASSES)) {return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        if (!authorizationService.checkSchoolMemberBelonging(setClassTeacherRequest.getTeacherId(), targetClass.getSchoolDetails().getId())) { return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); }
        Result<ClassDetailsDTO> classUpdateResult = schoolService.setClassTeacher(classId, setClassTeacherRequest.getTeacherId());

        if(!classUpdateResult.isSuccessful()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericFailureMessageResponse(classUpdateResult.getErrorMessage()));
        }
        return ResponseEntity.ok().body(SchoolResponseFactory.createClassResponse(classUpdateResult.getResult()));
    }

}
