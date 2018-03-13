package com.yiguo.permission;

import com.yiguo.bean.Permission;
import org.springframework.security.core.GrantedAuthority;

class StringGrantedAuthority implements GrantedAuthority {
    private String authority;
    @Override
    public String getAuthority() {
        return authority;
    }

    public StringGrantedAuthority(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}