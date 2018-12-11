package cn.luischen.service.user.impl;

import cn.luischen.constant.ErrorConstant;
import cn.luischen.dao.UserDao;
import cn.luischen.exception.BusinessException;
import cn.luischen.model.PvDomain;
import cn.luischen.model.UserDomain;
import cn.luischen.service.user.UserService;
import cn.luischen.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static cn.luischen.utils.GetAddressByIP.getAddressByIP;

/**
 * Created by Donghua.Chen on 2018/4/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;//这里会报错，但是并不会影响


    @Transactional
    @Override
    public int updateUserInfo(UserDomain user) {
        if (null == user.getUid())
            throw BusinessException.withErrorCode("用户编号不可能为空");
        return userDao.updateUserInfo(user);
    }

    @Override
    public UserDomain getUserInfoById(Integer uId) {
        return userDao.getUserInfoById(uId);
    }

    @Override
    public UserDomain login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);
        String pwd = TaleUtils.MD5encode(username + password);
        UserDomain user = userDao.getUserInfoByCond(username, pwd);
        if (null == user) {
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);
        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println("开始发送邮件");
////                    SendEmailUtils(user.getEmail(), "wangpeiyang@yansu.com", "365597937mM!");
//                    if (sendMailBySSL(user.getEmail(), "致 尊敬的用户：  \n \n" +
//                            "       欢迎您登陆本系统,若有任何意见和建议，您可以写在文章或回复此邮件,谢谢。", "【" + user.getUsername() + "】欢迎登陆"))
//                        System.out.println("邮件发送完成");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        return user;
    }

    @Override
    public int insertTPv(String ip) {
        PvDomain pvDomain = getAddressByIP(ip);
        pvDomain.setId(UUID.randomUUID().toString());
        pvDomain.setIp(ip);
        return userDao.insertTPv(pvDomain);
    }


    @Override
    public UserDomain findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public List<String> selectPermissionByUserId(Integer userId) {
        System.out.println("啊啊啊啊,什么时候调用啊");
        List<String> stringList = new ArrayList<>();
//        if (userId == 2) {
        stringList.add("systemUserAdd");
//        }
        return stringList;
    }
}
