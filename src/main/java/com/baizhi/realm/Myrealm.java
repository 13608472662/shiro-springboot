package com.baizhi.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class Myrealm extends AuthorizingRealm {

    @Override
    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String usrename = (String) principalCollection.getPrimaryPrincipal();

        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //根据token获取用户名标识
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String principal = upToken.getUsername();
        SimpleAuthenticationInfo info = null;
        //只有当输入的用户名与数据库一致时，在认证
        if (principal.equals("cc")) {
            info = new SimpleAuthenticationInfo("cc", "c3f2b09474f65a0bb8eda78e3682955f", ByteSource.Util.bytes("abcd"), this.getName());
        }

        return info;
    }
}
