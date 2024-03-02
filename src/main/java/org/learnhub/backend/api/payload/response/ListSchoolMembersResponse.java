package org.learnhub.backend.api.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.learnhub.backend.misc.payloads.ResponsePayload;
import org.learnhub.backend.model.SchoolRole;

import java.util.List;

public class ListSchoolMembersResponse implements ResponsePayload {

    @JsonProperty("members")
    List<Member> schoolMembers;

    public static class Member{

        @JsonProperty("schoolMemberID")
        Long id;

        @JsonProperty("schoolRole")
        SchoolRole role;

        @JsonProperty("userAccount")
        AccountDetails accountDetails;

        public Member(Long id, SchoolRole role, AccountDetails accountDetails) {
            this.id = id;
            this.role = role;
            this.accountDetails = accountDetails;
        }

        public Long getId() {
            return id;
        }

        public SchoolRole getRole() {
            return role;
        }

        public AccountDetails getAccountDetails() {
            return accountDetails;
        }

        public static class AccountDetails{

            @JsonProperty("userAccountID")
            Long id;

            @JsonProperty("userEmail")
            String email;

            public AccountDetails(Long id, String email) {
                this.id = id;
                this.email = email;
            }

            public Long getId() {
                return id;
            }

            public String getEmail() {
                return email;
            }
        }

    }

    public ListSchoolMembersResponse() {
    }

    public ListSchoolMembersResponse(List<Member> schoolMembers) {
        this.schoolMembers = schoolMembers;
    }
}
