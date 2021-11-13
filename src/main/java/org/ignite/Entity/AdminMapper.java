package org.ignite.Entity;

public class AdminMapper {
    public static AdminDto toAdminDto(Admin admin) {
        AdminDto tmp = new AdminDto();
        tmp.setUsername(admin.getUsername());
        tmp.setRegistered_date(admin.getRegistered_date());
        return tmp;
    }
}
