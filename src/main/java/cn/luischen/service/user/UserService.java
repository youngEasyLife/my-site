package cn.luischen.service.user;

import cn.luischen.model.UserDomain;

import java.util.List;

/**
 * Created by Donghua.Chen on 2018/4/20.
 */
public interface UserService {

    /**
     * @param user
     * @Author: Donghua.Chen
     * @Description: 更改用户信息
     * @Date: 2018/4/20
     */
    int updateUserInfo(UserDomain user);

    /**
     * @param uId 主键
     * @Author: Donghua.Chen
     * @Description: 根据主键编号获取用户信息
     * @Date: 2018/4/20
     */
    UserDomain getUserInfoById(Integer uId);


    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    UserDomain login(String username, String password);

    /**
     * 保存浏览人信息
     *
     * @param ip
     * @return
     */
    int insertTPv(String ip);

    /**
     * 根据
     *
     * @param userName
     * @return
     */
    UserDomain findByUserName(String userName);

    /**
     * 获取permission
     *
     * @param userId
     * @return
     */
    List<String> selectPermissionByUserId(Integer userId);

}
