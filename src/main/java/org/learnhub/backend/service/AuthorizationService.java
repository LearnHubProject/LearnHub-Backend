package org.learnhub.backend.service;

import org.learnhub.backend.data.dto.SchoolMemberDTO;
import org.learnhub.backend.model.authorization.SchoolPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Since I haven't figured out how to use @PreAuthorize and other authorization annotations I will be just calling authorization method from this service whenever I need to authorize a request.
@Service
public class AuthorizationService {

    @Autowired
    SchoolService schoolService;

    public boolean checkSchoolPermission(Authentication authentication, Long schoolId, SchoolPermission targetPermission){
        Optional<SchoolMemberDTO> schoolMember = schoolService.getSchoolMember(authentication.getName(), schoolId);

        if(schoolMember.isEmpty()) return false;

        SchoolMemberDTO schoolMemberDTO = schoolMember.get();

        //Check if user's role has target permission
        if(!targetPermission.roles.contains(schoolMemberDTO.getRole())) return false;

        //Perform additional check based on the target permission

        return true;
    }



}
