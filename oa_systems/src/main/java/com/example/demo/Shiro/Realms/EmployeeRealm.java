package com.example.demo.Shiro.Realms;

import com.example.demo.entity.EmployeeUser;
import com.example.demo.entity.Menuinfo;
import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.service.EmployeeUserService;
import com.example.demo.service.MenuinfoService;
import com.example.demo.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class EmployeeRealm extends AuthorizingRealm {

    public void serName(){
        super.setName("EmployeeRealm");
    }

    @Autowired
    EmployeeUserService employeeUserService;
    @Autowired
    RoleService roleService;
    @Autowired
    MenuinfoService menuinfoService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //得到认证之后返回的安全数据
        EmployeeUser employeeUser = (EmployeeUser) principalCollection.getPrimaryPrincipal();
        Set<Role> roles = employeeUserService.getemployeeRole(employeeUser.getEmployeeId());
        Set<Permission>perms = new HashSet<>();
        Set<String> menus = new HashSet<>();
        System.out.println(roles);
        Set<String> userroles = new HashSet<>();
        Set<String> userperms = new HashSet<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Menuinfo> list = new ArrayList<>();
        for (Role role : roles){
            userroles.add(role.getRoleName());
            perms = roleService.getRolePermissions(role.getId());
        }
        for(Permission permission : perms){
            if(permission.getActionType() == 1){
                Menuinfo m = menuinfoService.MensByRouter(permission.getPermission());
                list.add(m);
            }else if(permission.getActionType() == 3){
                userperms.add(permission.getPermission());

            }
        }
        info.setRoles(userroles);
        info.setStringPermissions(userperms);
        System.out.println("-----角色------");
        System.out.println(info.getRoles());
        System.out.println("-----权限------");
        System.out.println(info.getStringPermissions());
        System.out.println("-----菜单------");
        System.out.println(list);
        System.out.println("授权完成");
        //存入会话管理器
        SecurityUtils.getSubject().getSession().setAttribute("roles",info.getRoles());
        SecurityUtils.getSubject().getSession().setAttribute("perms",info.getStringPermissions());
        SecurityUtils.getSubject().getSession().setAttribute("menus",list);
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("456");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        if (StringUtils.isEmpty(username)) {
            throw new AuthenticationException("token错误!");
        }
        EmployeeUser employeeUser =  employeeUserService.LOGIN(username);
        if(employeeUser ==null){
            return null;
        }
        if(employeeUser.getEmployeeStatus() == 0){
            throw new LockedAccountException();   //用户处于禁用状态
        }
        return new SimpleAuthenticationInfo(employeeUser,employeeUser.getEmployeePwd(),this.getName());
    }
}
