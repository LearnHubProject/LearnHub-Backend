package org.learnhub.backend.service;

import jakarta.transaction.Transactional;
import org.learnhub.backend.api.factory.SchoolResponseFactory;
import org.learnhub.backend.api.payload.response.CreateSchoolResponse;
import org.learnhub.backend.data.dto.ClassDetailsDTO;
import org.learnhub.backend.data.dto.SchoolDetailsDTO;
import org.learnhub.backend.data.dto.SchoolMemberDTO;
import org.learnhub.backend.data.mapper.SchoolMapper;
import org.learnhub.backend.database.entity.Class;
import org.learnhub.backend.database.entity.*;
import org.learnhub.backend.database.repostitory.*;
import org.learnhub.backend.misc.Result;
import org.learnhub.backend.model.SchoolRole;
import org.learnhub.backend.model.authorization.SchoolPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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

    @Autowired
    ClassesRepository classesRepository;
    @Autowired
    private JoinRequestRepository joinRequestRepository;

    @Transactional
    public CreateSchoolResponse createSchool(String name){
        School newSchool = new School(name);
        School schoolEntity = schoolsRepository.save(newSchool);
        SchoolDetailsDTO schoolDetailsDTO = SchoolMapper.SchoolToSchoolDetails(schoolEntity);
        return SchoolResponseFactory.createSchoolResponse(schoolDetailsDTO);
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

    public Optional<SchoolMemberDTO> getSchoolMemberById(Long id){
        return schoolMembersRepository.findById(id).map(SchoolMapper::SchoolMemberToSchoolMemberDTO);
    }

    public Optional<ClassDetailsDTO> getClassById(Long id){
        return classesRepository.findById(id).map(SchoolMapper::classToClassDetailsDTO);
    }

    public List<SchoolMemberDTO> listSchoolMembers(Long schoolId){
        List<SchoolMember> schoolMemberList = schoolMembersRepository.listSchoolMembersInSchool(schoolId);
        List<SchoolMemberDTO> schoolMemberDTOS = new ArrayList<>();
        for(SchoolMember schoolMember : schoolMemberList){
            schoolMemberDTOS.add(SchoolMapper.SchoolMemberToSchoolMemberDTO(schoolMember));
        }
        return schoolMemberDTOS;
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

    @Transactional
    public Result<ClassDetailsDTO> createClass(Long schoolId, String name, Optional<Long> teacherId) {

        Result<ClassDetailsDTO> result = new Result<>();

        Class clazz = new Class();
        clazz.setLabel(name);

        //In theory this check is currently redundant, however this method could be called from anywhere, so I'll leave it.
        Optional<School> targetSchool = schoolsRepository.findById(schoolId);
        if(targetSchool.isEmpty()) {
            result.setErrorMessage("School with the id " + schoolId.toString() + " was not found.");
            return result;
        }

        if(teacherId.isPresent()){
            Optional<SchoolMember> teacher = schoolMembersRepository.findById(teacherId.get());
            if(teacher.isEmpty()){
                result.setErrorMessage("Teacher with the id " + teacherId.get() + " was not found.");
                return result;
            } else if (!SchoolPermission.OWN_CLASSES.roles.contains(teacher.get().getRole())) {
                result.setErrorMessage("Member with the id " + teacherId.get() + " doesn't have a permission to own a class.");
                return result;
            }
            clazz.setTeacher(teacher.get());
        }
        clazz.setSchool(targetSchool.get());
        Class clazzSaved = classesRepository.save(clazz);
        result.setResult(SchoolMapper.classToClassDetailsDTO(clazzSaved));
        return result;
    }

    @Transactional
    public Result<ClassDetailsDTO> setClassTeacher(Long classId, Long teacherId){

        Result<ClassDetailsDTO> result = new Result<>();

        Optional<Class> targetClass = classesRepository.findById(classId);
        if (targetClass.isEmpty()) {
            result.setErrorMessage("Class with the id " + classId + " was not found.");
            return result;
        }

        Optional<SchoolMember> targetMember = schoolMembersRepository.findById(teacherId);
        if (targetMember.isEmpty()) {
            result.setErrorMessage("Teacher with the id " + classId + " was not found.");
            return result;
        }
        else if (!SchoolPermission.OWN_CLASSES.roles.contains(targetMember.get().getRole())){
            result.setErrorMessage("Member with the id " + teacherId + " doesn't have a permission to own a class.");
            return result;
        }
        targetClass.get().setTeacher(targetMember.get());
        Class savedClass = classesRepository.save(targetClass.get());
        result.setResult(SchoolMapper.classToClassDetailsDTO(savedClass));
        return result;
    }

    //TODO: use Result instead of boolean
    @Transactional
    public boolean createJoinRequest(Long schoolId, Long accountId){

        School targetSchool;

        if(schoolId == null){
            Iterator<School> itSchools = schoolsRepository.findAll().iterator();
            if(!itSchools.hasNext()) return false;
            targetSchool = itSchools.next();
        }
        else{
            Optional<School> optSchool = schoolsRepository.findById(schoolId);
            if(optSchool.isEmpty()) return false;
            targetSchool = optSchool.get();
        }

        Optional<UserAccount> userAccount = userAccountsRepository.findById(accountId);
        if(userAccount.isEmpty()) return false;

        JoinRequest newRequest = new JoinRequest();

        newRequest.setSchool(targetSchool);
        newRequest.setUserAccount(userAccount.get());

        joinRequestRepository.save(newRequest);
        return true;
    }

/*    @Transactional
    public List<JoinRequestDTO> listJoinRequests(Long schoolId, boolean buildLinkRecommendations){

        List<JoinRequest> joinRequests = joinRequestRepository.listJoinRequestsInSchool(schoolId);

        List<JoinRequestDTO> requestDTOS = new ArrayList<>();

        if(buildLinkRecommendations && !joinRequests.isEmpty()){
            List<SchoolMember> schoolMembers = schoolMembersRepository.listSchoolMembersInSchool(schoolId);

            for(JoinRequest joinRequest : joinRequests){
                Optional<SchoolMember> match = schoolMembers.stream().filter(schoolMember -> schoolMember.getPersonalCode().equals(joinRequest.getUserAccount().getPersonalCode())).findFirst();

                SchoolMemberDTO matchDTO;
                matchDTO = match.map(SchoolMapper::SchoolMemberToSchoolMemberDTO).orElse(null);

                requestDTOS.add(new JoinRequestDTO(
                        joinRequest.getId(),
                        UserMapper.UserAccountToUserDetails(joinRequest.getUserAccount()),
                        Optional.of(matchDTO),
                        SchoolMapper.SchoolToSchoolDetails(joinRequest.getSchool())
                ));

            }
        }
        else{
            requestDTOS = joinRequests.stream().map(SchoolMapper::joinRequestToJoinRequestDTO).toList();
        }

        return requestDTOS;

    }*/

 /*   @Transactional
    public String createJoinCode(SchoolDetailsDTO school, int maxUsages, Long expirationTime, SchoolRoleEnum roleEnum*//*, TODO Optional<ClassDTO> class*//*){

    }*/

}
