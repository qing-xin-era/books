package com.qing.config;

import com.qing.pojo.User;
import com.qing.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("shouquan");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();

        User currentUser = (User) subject.getPrincipal();
        subject.getSession().setAttribute("loginUser",currentUser.getName()   );
        info.addStringPermission(currentUser.getPerms());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = userService.queryUserByName(userToken.getUsername());
        System.out.println("renzheng");
        if(user==null){
            return  null;  //返回null时，抛出异常 UnknownAccountException
        }
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
/*

renzheng
shouquan
shouquan
shouquan
shouquan
shouquan
shouquan
shouquan
shouquan
shouquan
 */