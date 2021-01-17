package com.lsq.ssm.blog.controller.util;

import com.lsq.ssm.blog.VO.DecryptAndEncryptVO;
import com.lsq.ssm.blog.util.DecryptPwd;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lsq
 */
@Controller
public class UtilController {

    @RequestMapping(value = "/view/util")
    public String utilJsp()  {
        return "util/util";
    }

    @RequestMapping(value = "/encryption/decryption",method = RequestMethod.GET)
    @ResponseBody
    public DecryptAndEncryptVO decryptAndEncrypt(
            @RequestParam("type") String type,
            @RequestParam("account") String account,
            @RequestParam("pwd") String pwd)  {
        DecryptAndEncryptVO decryptAndEncryptVO = new DecryptAndEncryptVO();
        String password = "";
        if (type == null || type.equals("1")) {
            //解密
            password = DecryptPwd.decryptPwd(account,pwd);
        } else if (type.equals("2")) {
            password = DecryptPwd.encryptPwd(account,pwd);
        }
        decryptAndEncryptVO.setAccount(account);
        decryptAndEncryptVO.setPassword(password);
        return decryptAndEncryptVO;
    }


}
