package cn.luischen.controller;

import cn.luischen.utils.AesException;
import cn.luischen.utils.WXPublicUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : verifyWXToken
 * @DesCription :公众号开发信息 服务器配置
 * @Author : young
 * @Date: 2018/11/22 9:59
 **/
@Controller
public class verifyWXToken extends BaseController {

    /**
     * 公众号开发信息 服务器配置
     *
     * @param request
     * @return
     * @throws AesException
     */
    @RequestMapping("/wxpublic/verify_wx_token")
    @ResponseBody
    public String verifyWXToken(HttpServletRequest request) throws AesException {
        String msgSignature = request.getParameter("signature");
        String msgTimestamp = request.getParameter("timestamp");
        String msgNonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (WXPublicUtils.verifyUrl(msgSignature, msgTimestamp, msgNonce)) {
            return echostr;
        }
        return null;
    }
}
