package com.example.demo.Shiro.Realms;

import com.example.demo.entity.EmployeeUser;
import com.example.demo.service.EmployeeUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
        EmployeeUser employeeUser = (EmployeeUser) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("21321");
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
