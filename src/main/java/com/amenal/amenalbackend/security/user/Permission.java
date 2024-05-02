package com.amenal.amenalbackend.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    SUPMANAGER_VALIDER("supmanagement:valider"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete")

    ;

    @Getter
    private final String permission;
}
