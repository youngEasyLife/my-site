package cn.luischen.shrio;

import cn.luischen.model.UserDomain;
import cn.luischen.service.user.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName : UserRealm
 * @DesCription :TODO
 * @Author : young
 * @Date: 2018/12/11 15:37
 **/
public class UserRealm extends AuthorizingRealm {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService sysUserService;

    /**
     * 授权
     *
     * @param principals 在方法上加注解的时候注入
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserDomain sysUser = (UserDomain) principals.getPrimaryPrincipal();
        List<String> sysPermissions = sysUserService.selectPermissionByUserId(sysUser.getUid());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(sysPermissions);
        LOGGER.info("doGetAuthorizationInfo");
        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken 当调用Subject currentUser = SecurityUtils.getSubject();
     *                            <p>
     *                            currentUser.login(token);
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserDomain sysUser = sysUserService.findByUserName(token.getUsername());
        if (sysUser == null) {
            return null;
        }
//        自建仓库.并完成仓库基本方法的引用
//        String age = BirUtils.getBirAgeSex("610322199103160314");


        LOGGER.info("doGetAuthenticationInfo");
//        比对 密码 加盐 用户名
        return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword().toCharArray(), ByteSource.Util.bytes(sysUser.getUsername()), getName());
    }

}
