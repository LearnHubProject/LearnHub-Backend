package org.learnhub.backend.service;

import jakarta.transaction.Transactional;
import org.learnhub.backend.api.factory.CreateSchoolResponseFactory;
import org.learnhub.backend.api.payload.response.CreateSchoolResponse;
import org.learnhub.backend.data.dto.SchoolDetailsDTO;
import org.learnhub.backend.data.dto.SchoolMemberDTO;
import org.learnhub.backend.data.mapper.SchoolMapper;
import org.learnhub.backend.database.entity.School;
import org.learnhub.backend.database.entity.SchoolMember;
import org.learnhub.backend.database.entity.UserAccount;
import org.learnhub.backend.database.repostitory.SchoolMembersRepository;
import org.learnhub.backend.database.repostitory.SchoolsRepository;
import org.learnhub.backend.database.repostitory.UserAccountsRepository;
import org.learnhub.backend.model.SchoolRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    @Autowired
    SchoolsRepository schoolsRepository;

    @Autowired
    UserAccountsRepository userAccountsRepository;

    @Autowired
    SchoolMembersRepository schoolMembersRepository;

    @Transactional
    public CreateSchoolResponse createSchool(String name){
        School newSchool = new School(name);
        School schoolEntity = schoolsRepository.save(newSchool);
        SchoolDetailsDTO schoolDetailsDTO = SchoolMapper.SchoolToSchoolDetails(schoolEntity);
        return CreateSchoolResponseFactory.createSchoolResponse(schoolDetailsDTO);
    }

    @Transactional
    public void addSchoolMember(Long schoolId, Long userId, SchoolRole role){
        School targetSchool = schoolsRepository.findById(schoolId).get();
        UserAccount targetUser = userAccountsRepository.findById(userId).get();

        SchoolMember schoolMember = new SchoolMember();
        schoolMember.setSchool(targetSchool);
        schoolMember.setAccount(targetUser);
        schoolMember.setRole(role);

        schoolMembersRepository.save(schoolMember);
    }

    public Optional<SchoolMemberDTO> getSchoolMember(String username, Long schoolId){
        Optional<SchoolMember> schoolMember = schoolMembersRepository.findSchoolMemberByUserEmailAndSchoolId(username, schoolId);
        return schoolMember.map(SchoolMapper::SchoolMemberToSchoolMemberDTO);
    }

    public SchoolDetailsDTO getSchoolById(Long id){
        return SchoolMapper.SchoolToSchoolDetails(schoolsRepository.findById(id).get());
    }

    public List<SchoolDetailsDTO> listSchools(){
        Iterable<School> schools = schoolsRepository.findAll();
        List<SchoolDetailsDTO> schoolDTOs = new ArrayList<SchoolDetailsDTO>();

        for(School school : schools){
            schoolDTOs.add(SchoolMapper.SchoolToSchoolDetails(school));
        }


        return schoolDTOs;
    }

 /*   @Transactional
    public String createJoinCode(SchoolDetailsDTO school, int maxUsages, Long expirationTime, SchoolRoleEnum roleEnum*//*, TODO Optional<ClassDTO> class*//*){

    }*/

}
