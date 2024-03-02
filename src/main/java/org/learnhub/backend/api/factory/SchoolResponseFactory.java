package org.learnhub.backend.api.factory;

import org.learnhub.backend.api.payload.response.ClassResponse;
import org.learnhub.backend.api.payload.response.CreateSchoolResponse;
import org.learnhub.backend.api.payload.response.ListSchoolMembersResponse;
import org.learnhub.backend.data.dto.ClassDetailsDTO;
import org.learnhub.backend.data.dto.SchoolDetailsDTO;
import org.learnhub.backend.data.dto.SchoolMemberDTO;

import java.util.List;

public class SchoolResponseFactory {

    public static CreateSchoolResponse createSchoolResponse(SchoolDetailsDTO schoolDetails){
        return new CreateSchoolResponse(schoolDetails.getId(), schoolDetails.getName());
    }

    public static ListSchoolMembersResponse createListSchoolMembersResponse(List<SchoolMemberDTO> schoolMemberDTOS){
        return new ListSchoolMembersResponse(schoolMemberDTOS.stream().map(sm -> new ListSchoolMembersResponse.Member(sm.getId(), sm.getRole(), new ListSchoolMembersResponse.Member.AccountDetails(sm.getUserAccount().getId(), sm.getUserAccount().getEmail()))).toList());
    }

    public static ClassResponse createClassResponse(ClassDetailsDTO clazz){
        return new ClassResponse(clazz.getId(),
                clazz.getSchoolDetails().getId(),
                clazz.getTeacher().map(SchoolMemberDTO::getId),
                clazz.getClassMembers().stream().map(classMemberDTO -> classMemberDTO.getSchoolMember().getId()).toList());
    }

/*    public static ListJoinRequestsResponse createListJoinRequestsResponse(List<JoinRequestDTO> requests){
        return new ListJoinRequestsResponse(requests.stream().map(request ->
                new ListJoinRequestsResponse.Request(
                        request.getId(),
                        new ListJoinRequestsResponse.Request.User(
                                request.getRequestAuthor().getPersonalCode(),
                                request.getRequestAuthor().getEmail()
                        ),
                        new ListJoinRequestsResponse.Request.SchoolMember(
                                request.getMatch().get().getId(),
                                request.getMatch().get().getUserAccount().getEmail(),
                                request.getMatch().get().getUserAccount().getPersonalCode()
                        )
                )))
    }*/

}
