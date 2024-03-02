/*
package org.learnhub.backend.api.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListJoinRequestsResponse {

    @JsonProperty("requests")
    List<Request> requests;



    public static class Request {
        @JsonProperty("requestID")
        Long id;

        @JsonProperty("user")
        User user;

        @JsonProperty("match")
        SchoolMember schoolMember;

        public Request(Long id, User user, SchoolMember schoolMember) {
            this.id = id;
            this.user = user;
            this.schoolMember = schoolMember;
        }

        public static class User{

            @JsonProperty("personalCode")
            Long personalCode;

            @JsonProperty("email")
            String email;

            public User(Long personalCode, String email) {
                this.personalCode = personalCode;
                this.email = email;
            }
        }
        public static class SchoolMember{

            @JsonProperty("id")
            Long id;

            @JsonProperty("email")
            String email;

            @JsonProperty("personalCode")
            Long personalCode;

            public SchoolMember(Long id, String email, Long personalCode) {
                this.id = id;
                this.email = email;
                this.personalCode = personalCode;
            }
        }

    }


}
*/
