package cn.luischen.dao;

import cn.luischen.dto.cond.UserCond;
import cn.luischen.model.PvDomain;
import cn.luischen.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Donghua.Chen on 2018/4/20.
 */
@Mapper
public interface UserDao {

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
    UserDomain getUserInfoById(@Param("uid") Integer uId);


    UserDomain findByUserName(@Param("userName") String userName);


    /**
     * 根据用户名和密码获取用户信息
     *
     * @param username
     * @param password
     * @return
     */
    UserDomain getUserInfoByCond(@Param("username") String username, @Param("password") String password);


    int insertTPv(PvDomain pvDomain);


    Long getPvCount();

    Long getUvCount();
}
