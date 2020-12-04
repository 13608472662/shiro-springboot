package com.baizhi.realm;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Authority;
import com.baizhi.entity.Role;
import com.baizhi.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Myrealm extends AuthorizingRealm {
    @Autowired
    private AdminService adminService;
    @Override
    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String usrename = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = null;
        Admin admin = adminService.selctAllByName(usrename);
        List<String> role1 = new ArrayList<>();
        List<String> authority1 = new ArrayList<>();
        List<Role> role = admin.getRole();
        for (Role role2 : role) {
            role1.add(role2.getRolename());
            List<Authority> authority = role2.getAuthority();
            for (Authority authority2 : authority) {
                authority1.add(authority2.getAuthorityname());
            }

        }
        if (admin != null) {
            simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            //角色设置
            simpleAuthorizationInfo.addRoles(role1);
            //权限设置
            simpleAuthorizationInfo.addStringPermissions(authority1);
        }
        return simpleAuthorizationInfo;

    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //根据token获取用户名标识
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String principal = upToken.getUsername();
        Admin admin = adminService.selectByName(principal);
        System.out.println(admin);
        SimpleAuthenticationInfo info = null;
        //只有当输入的用户名与数据库一致时，在认证
        if (admin != null) {
            info = new SimpleAuthenticationInfo(admin.getUsername(), admin.getPassword(), ByteSource.Util.bytes(admin.getSalt()), this.getName());
        }

        return info;
    }
}
