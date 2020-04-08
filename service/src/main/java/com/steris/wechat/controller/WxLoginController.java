package com.steris.wechat.controller;
import com.alibaba.fastjson.JSON;
import com.steris.wechat.util.SignUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/wxAuth")
public class WxLoginController {
    private  static  String APPID="wx4eb53c2821e00f96";
    private  static  String APPSECRET="ac3517c7a061e00591cdafd8225f492c";


    /**
     * 用于给微信验证token
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/checkToken")
    public String checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.println("校验token成功");
            return echostr;
        }else{
            System.out.println("校验token不成功");
            return  null;
        }
    }

    /**
     * 用于获取出回调地址  （引导用户调用此接口，成功后自动调取回调地址然后取出用户信息）
     * @param response
     * @throws IOException
     */
    @RequestMapping("/login")
    public void wxLogin(HttpServletResponse response) throws IOException {
        //请求获取code的回调地址
        //用线上环境的域名或者用内网穿透，不能用ip
        String callBack = "http://yuchen.free.idcfengye.com/wxAuth/callBack";

        //请求地址
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize" +
                "?appid=" + APPID +
                "&redirect_uri=" + URLEncoder.encode(callBack, "UTF-8") +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";

        System.out.println(url);
        //重定向
        response.sendRedirect(url);
    }


    /**
     * 回调方法
     * @param request
     * @param response
     * @throws IOException
     */
    //	回调方法
    @RequestMapping("/callBack")
    public String wxCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/jscode2session" +
                "?appid=" + APPID +
                "&secret=" + APPSECRET +
                "&js_code=" + code +
                "&grant_type=authorization_code";
        CloseableHttpResponse resp = HttpClients.createDefault().execute(new HttpGet(url));
        // 获取响应实体
        String html = EntityUtils.toString(resp.getEntity());
        return  html;
    }
}
