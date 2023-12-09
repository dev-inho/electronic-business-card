package com.inho.electronicbusinesscard.domain.common;

import org.springframework.security.core.GrantedAuthority;

/**
 * 권한
 */
public enum Authority implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
