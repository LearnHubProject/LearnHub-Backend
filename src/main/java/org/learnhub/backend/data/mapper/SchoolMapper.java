package org.learnhub.backend.data.mapper;

import org.learnhub.backend.data.dto.ClassDetailsDTO;
import org.learnhub.backend.data.dto.ClassMemberDTO;
import org.learnhub.backend.data.dto.SchoolDetailsDTO;
import org.learnhub.backend.data.dto.SchoolMemberDTO;
import org.learnhub.backend.database.entity.Class;
import org.learnhub.backend.database.entity.ClassMember;
import org.learnhub.backend.database.entity.School;
import org.learnhub.backend.database.entity.SchoolMember;

import java.util.Collections;

public class SchoolMapper {

    public static SchoolDetailsDTO SchoolToSchoolDetails(School school){
        return new SchoolDetailsDTO(school.getId(), school.getName());
    }

    public static SchoolMemberDTO SchoolMemberToSchoolMemberDTO(SchoolMember schoolMember){
        return new SchoolMemberDTO(schoolMember.getId(), SchoolMapper.SchoolToSchoolDetails(schoolMember.getSchool()), schoolMember.getRole(), UserMapper.UserAccountToUserDetails(schoolMember.getAccount()));
    }

    public static ClassMemberDTO classMemberToClassMemberDTO(ClassMember member){
        return new ClassMemberDTO(member.getId(), member.getSchoolMember());
    }

    public static ClassDetailsDTO classToClassDetailsDTO(Class clazz){

        return new ClassDetailsDTO(
                clazz.getId(),
                SchoolToSchoolDetails(clazz.getSchool()), clazz.getTeacher() == null ? null : SchoolMemberToSchoolMemberDTO(clazz.getTeacher()),
                clazz.getStudents() == null ? Collections.emptyList() : clazz.getStudents().stream().map(SchoolMapper::classMemberToClassMemberDTO).toList());
    }

/*    public static JoinRequestDTO joinRequestToJoinRequestDTO(JoinRequest joinRequest){

        return new JoinRequestDTO(
                joinRequest.getId(),
                UserMapper.UserAccountToUserDetails(joinRequest.getUserAccount()),
                null, //TODO: IMPLEMENT THIS THING
                SchoolToSchoolDetails(joinRequest.getSchool())
        );

    }*/

}
