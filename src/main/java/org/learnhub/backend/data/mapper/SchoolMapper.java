package org.learnhub.backend.data.mapper;

import org.learnhub.backend.data.dto.SchoolDetailsDTO;
import org.learnhub.backend.data.dto.SchoolMemberDTO;
import org.learnhub.backend.database.entity.School;
import org.learnhub.backend.database.entity.SchoolMember;

public class SchoolMapper {

    public static SchoolDetailsDTO SchoolToSchoolDetails(School school){
        return new SchoolDetailsDTO(school.getId(), school.getName());
    }

    public static SchoolMemberDTO SchoolMemberToSchoolMemberDTO(SchoolMember schoolMember){
        return new SchoolMemberDTO(schoolMember.getSchool().getId(), schoolMember.getRole(), UserMapper.UserAccountToUserDetails(schoolMember.getAccount()));
    }

}
