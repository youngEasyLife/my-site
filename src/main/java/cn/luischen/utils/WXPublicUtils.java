package cn.luischen.utils;

/**
 * @ClassName : WXPublicUtils
 * @DesCription :TODO
 * @Author : young
 * @Date: 2018/11/22 9:54
 **/
public class WXPublicUtils {
    /**
     * 验证Token
     *
     * @param msgSignature 签名串，对应URL参数的signature
     * @param timeStamp    时间戳，对应URL参数的timestamp
     * @param nonce        随机串，对应URL参数的nonce
     * @return 是否为安全签名
     * @throws AesException 执行失败，请查看该异常的错误码和具体的错误信息
     */
    public static boolean verifyUrl(String msgSignature, String timeStamp, String nonce)
            throws AesException {
        // 这里的 WXPublicConstants.TOKEN 填写你自己设置的Token就可以了
        String signature = SHA1.getSHA1("9d2d445d817e29b593cd8b432d5bbd50", timeStamp, nonce);
        if (!signature.equals(msgSignature)) {
            throw new AesException(AesException.ValidateSignatureError);
        }
        return true;
    }
}
