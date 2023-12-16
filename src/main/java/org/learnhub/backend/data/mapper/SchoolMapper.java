package org.learnhub.backend.data.mapper;

import org.learnhub.backend.data.dto.SchoolDetailsDTO;
import org.learnhub.backend.database.entity.School;

public class SchoolMapper {

    public static SchoolDetailsDTO SchoolToSchoolDetails(School school){
        return new SchoolDetailsDTO(school.getId(), school.getName());
    }

}
