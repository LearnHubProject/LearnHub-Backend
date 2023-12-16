package org.learnhub.backend.service;

import jakarta.transaction.Transactional;
import org.learnhub.backend.api.factory.CreateSchoolResponseFactory;
import org.learnhub.backend.api.payload.response.CreateSchoolResponse;
import org.learnhub.backend.data.dto.SchoolDetailsDTO;
import org.learnhub.backend.data.mapper.SchoolMapper;
import org.learnhub.backend.database.entity.School;
import org.learnhub.backend.database.repostitory.SchoolsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

    @Autowired
    SchoolsRepository schoolsRepository;

    @Transactional
    public CreateSchoolResponse createSchool(String name){
        School newSchool = new School(name);
        School schoolEntity = schoolsRepository.save(newSchool);
        SchoolDetailsDTO schoolDetailsDTO = SchoolMapper.SchoolToSchoolDetails(schoolEntity);
        return CreateSchoolResponseFactory.createSchoolResponse(schoolDetailsDTO);
    }

}
