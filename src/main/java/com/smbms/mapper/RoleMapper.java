package com.smbms.mapper;

import com.smbms.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleMapper {

    List<Role> list();
    Role getById(Integer id);
}
