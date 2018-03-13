package com.yiguo.permission;

import com.yiguo.bean.Permission;
import com.yiguo.bean.Role;

import java.util.List;

/**
 * Credentials主要凭证, 如用户名密码等, 这里的id,roles,permissions属性是为了给前端返回, 这个对象会被打包到jwt的body中
 */
class AccountCredentials {

    private Integer id;
    private String username;
    private String password;
    private String type;
    private List<Role> roles;
    private List<Permission> permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}