package org.learnhub.backend.api.factory;

import org.learnhub.backend.api.payload.response.CreateSchoolResponse;
import org.learnhub.backend.data.dto.SchoolDetailsDTO;

public class CreateSchoolResponseFactory {

    public static CreateSchoolResponse createSchoolResponse(SchoolDetailsDTO schoolDetails){
        return new CreateSchoolResponse(schoolDetails.getId(), schoolDetails.getName());
    }

}
