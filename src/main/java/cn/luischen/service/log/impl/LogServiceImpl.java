package cn.luischen.service.log.impl;

import cn.luischen.constant.ErrorConstant;
import cn.luischen.dao.LogDao;
import cn.luischen.exception.BusinessException;
import cn.luischen.model.LogDomain;
import cn.luischen.service.log.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.luischen.api.SendEmailUtil.SendEmailUtils;

/**
 * 请求日志
 * Created by Donghua.Chen on 2018/4/29.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void addLog(String action, String data, String ip, Integer authorId) {
        LogDomain logDomain = new LogDomain();
        logDomain.setAuthorId(authorId);
        logDomain.setIp(ip);
        logDomain.setData(data);
        logDomain.setAction(action);
        logDao.addLog(logDomain);
    }

    @Override
    public void deleteLogById(Integer id) {
        if (null == id)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        deleteLogById(id);
    }

    @Override
    public PageInfo<LogDomain> getLogs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LogDomain> logs = logDao.getLogs();
        PageInfo<LogDomain> pageInfo = new PageInfo<>(logs);
      /*  new Thread(new Runnable() {
            @Override
            public void run() {
                SendEmailUtils("365597937@qq.com", "service@yansu.com", "0*2m$hsl!yN9");
            }
        });*/
        return pageInfo;
    }
}
