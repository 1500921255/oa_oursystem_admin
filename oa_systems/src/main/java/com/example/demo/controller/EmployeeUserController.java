package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.common.UserResult;
import com.example.demo.entity.EmployeeUser;
import com.example.demo.entity.Role;
import com.example.demo.service.EmployeeUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxh
 * @since 2020-09-26
 */
@RestController
@RequestMapping("/employee-user")
public class EmployeeUserController {
    @Autowired
    private EmployeeUserService employeeUserService;

    @GetMapping("login")
    public Result login(@RequestParam("employee_user") String employee_user , @RequestParam("employee_pwd") String employee_pwd, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        employee_pwd =  new Md5Hash(employee_user,employee_pwd,3).toString();
       UsernamePasswordToken token = new UsernamePasswordToken(employee_user,employee_pwd);
       try{
        subject.login(token);
        EmployeeUser employeeUser = (EmployeeUser) subject.getPrincipal();
        int id = employeeUser.getEmployeeId();
        String SessionId = (String) subject.getSession().getId();
           //System.out.println( subject.hasRole("超级管理员"));
           System.out.println(subject.isPermitted("employee_user_add"));
        return Result.succ(SessionId,subject.getSession().getAttribute("roles"), subject.getSession().getAttribute("perms"), subject.getSession().getAttribute("menus"),id);
       }catch (IncorrectCredentialsException e){
        return Result.fail("密码错误");
       }catch (LockedAccountException e){
        return Result.fail("账户已被冻结");
       }catch (AuthenticationException e){
        return Result.fail("该用户不存在");
       }catch (Exception e){
           e.printStackTrace();
       }
       return Result.fail("登陆失败");
    }

    @PostMapping(value = "loginout")
    public Result loginout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    return Result.succ(null);
    }

    @GetMapping(value = "/Page/{current}")
    public Result SelectAll(@PathVariable("current") int current){
        Map map = employeeUserService.Pagelist(current,5);
        return Result.succ(map);
    }
    @RequiresRoles(value = "超级管理员")
    @DeleteMapping(value = "/DeleteUser/{employeeId}")
    public Result DeleteUser(@PathVariable("employeeId") int employeeId){
        int i = employeeUserService.DeleteUser(employeeId);
        if(i == 1){
            return Result.succ(200,"删除成功",true,null);
        }else {
            return Result.fail(401,"删除失败",false,null);
        }
    }

    @RequiresRoles(value = "超级管理员")
    @PostMapping(value = "InsertUser")
    public Result InsertUser(@RequestBody EmployeeUser employeeUser){
        int i = employeeUserService.InsertUser(employeeUser);
        if(i == 1){
            return Result.succ(200,"添加成功",true,null);
        }else {
            return Result.fail(401,"添加失败",false,null);
        }
    }
    @RequiresRoles(value = "超级管理员")
    @PutMapping(value = "UpdateUser")
    public Result UpdateUser(@RequestBody EmployeeUser employeeUser){
        int i = employeeUserService.UpdateUser(employeeUser);
        if(i == 1){
            return Result.succ(200,"添加成功",true,null);
        }else {
            return Result.fail(401,"添加失败",false,null);
        }
    }

    @GetMapping(value = "UserRoles/{id}")
    public Result UserRoles(@PathVariable("id")int id){
        Set<Role> set = employeeUserService.getemployeeRole(id);
        return Result.succ(set);
    }
}
