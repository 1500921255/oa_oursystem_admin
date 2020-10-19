package com.example.demo.Shiro.Realms;

import com.example.demo.entity.EmployeeUser;
import com.example.demo.entity.Role;
import com.example.demo.service.EmployeeUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Component
public class EmployeeRealm extends AuthorizingRealm {

    public void serName(){
        super.setName("EmployeeRealm");
    }

    @Autowired
    EmployeeUserService employeeUserService;

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
        System.out.println(roles);
        Set<String> userroles = new HashSet<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Role role : roles){
            userroles.add(role.getRoleName());
        }
        info.setRoles(userroles);
        System.out.println("授权完成");
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
