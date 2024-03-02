package org.learnhub.backend.model.authorization;

import org.learnhub.backend.model.SchoolRole;

import java.util.Arrays;
import java.util.List;

public enum SchoolPermission {

    MANAGE_SCHOOL(SchoolRole.ADMIN),
    MANAGE_MEMBERS(SchoolRole.ADMIN, SchoolRole.PRINCIPAL),
    MANAGE_CLASSES(SchoolRole.ADMIN),

    OWN_CLASSES(SchoolRole.TEACHER, SchoolRole.PRINCIPAL, SchoolRole.ADMIN);

    public final List<SchoolRole> roles;

    SchoolPermission(SchoolRole... roles){
        this.roles = Arrays.stream(roles).toList();
    }

}
