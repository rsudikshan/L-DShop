package com.sr.L.DShop.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permissions {

   /* USER_CREATE("user::create"),
    USER_READ("user::read"),
    USER_UPDATE("user::update"),
    USER_DELETE("user::delete"),*/

    ADMIN_CREATE("admin::create"),
    ADMIN_READ("admin::read"),
    ADMIN_UPDATE("admin::update"),
    ADMIN_DELETE("admin::delete");

    private final String permission;

}
