package com.kslima.catalog.dto;

import com.kslima.catalog.entities.Role;

public class RoleDTO {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String authority;

    public RoleDTO() {

    }

    public RoleDTO(Role entity) {
        this.id = entity.getId();
        this.authority = entity.getAuthority();
    }

    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
