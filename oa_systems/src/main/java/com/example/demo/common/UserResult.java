package com.example.demo.common;

import com.example.demo.entity.Menuinfo;
import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class UserResult implements Serializable {

    protected String SessionID;
    protected Object roles;
    protected Object perms;
    protected Object menus;

    public static UserResult succ(String SessionID,Object roles,Object perms,Object menus){
         UserResult u = new UserResult();
         u.setSessionID(SessionID);
         u.setRoles(roles);
         u.setPerms(perms);
         u.setMenus(menus);
        return u;
    }
}
