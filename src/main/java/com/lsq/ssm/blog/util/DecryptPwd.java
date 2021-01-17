package com.lsq.ssm.blog.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;

public class DecryptPwd {
    private static final String ProviderSunJCE = "com.sun.crypto.provider.SunJCE";
    private static final String ProviderSUN = "sun.security.provider.Sun";

    public DecryptPwd() {
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("账号或密码加密字符串为空！");
        } else {
            String account;
            String pwdStr;
            String pwd;
            if (args.length == 2) {
                account = args[0];
                pwdStr = args[1];
                pwd = decryptPwd(account, pwdStr);
                int index = pwd.indexOf(32);
                System.out.println("账号：" + account + " 密码：" + pwd);
                if (index != -1) {
                    System.out.println("请注意，密码含有空格，空格位置为" + index);
                }
            } else {
                account = args[0];
                pwdStr = args[1];
                pwd = encryptPwd(account, pwdStr);
                System.out.println("账号：" + account + " 加密字符串：" + pwd);
            }

        }
    }

    public static String decryptPwd(String account, String passwordStr) {
        try {
            String acc = (new StringBuffer(account)).reverse().toString();
            Key key = null;
            Cipher cipher = null;
            Security.addProvider((Provider)Class.forName("com.sun.crypto.provider.SunJCE").newInstance());
            Security.addProvider((Provider)Class.forName("sun.security.provider.Sun").newInstance());
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            SecureRandom srn = SecureRandom.getInstance("SHA1PRNG", "SUN");
            srn.setSeed(acc.getBytes());
            generator.init(srn);
            key = generator.generateKey();
            byte[] iv = new byte[8];
            srn.nextBytes(iv);
            IvParameterSpec params = new IvParameterSpec(iv, 0, 8);
            cipher = Cipher.getInstance("DES/OFB16/NoPadding");
            cipher.init(2, key, params);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] raw = cipher.doFinal(decoder.decodeBuffer(passwordStr));
            String result = new String(raw, "ISO-8859-1");
            return result;
        } catch (Exception var12) {
            return null;
        }
    }

    public static String encryptPwd(String account, String password) {
        String acc = (new StringBuffer(account)).reverse().toString();
        Key key = null;
        Cipher cipher = null;

        try {
            Security.addProvider((Provider)Class.forName("com.sun.crypto.provider.SunJCE").newInstance());
            Security.addProvider((Provider)Class.forName("sun.security.provider.Sun").newInstance());
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            SecureRandom srn = SecureRandom.getInstance("SHA1PRNG", "SUN");
            srn.setSeed(acc.getBytes());
            generator.init(srn);
            key = generator.generateKey();
            byte[] iv = new byte[8];
            srn.nextBytes(iv);
            IvParameterSpec params = new IvParameterSpec(iv, 0, 8);
            cipher = Cipher.getInstance("DES/OFB16/NoPadding");
            cipher.init(1, key, params);
        } catch (InstantiationException var11) {
            var11.printStackTrace();
        } catch (IllegalAccessException var12) {
            var12.printStackTrace();
        } catch (ClassNotFoundException var13) {
            var13.printStackTrace();
        } catch (NoSuchAlgorithmException var14) {
            var14.printStackTrace();
        } catch (NoSuchProviderException var15) {
            var15.printStackTrace();
        } catch (NoSuchPaddingException var16) {
            var16.printStackTrace();
        } catch (InvalidKeyException var17) {
            var17.printStackTrace();
        } catch (InvalidAlgorithmParameterException var18) {
            var18.printStackTrace();
        }

        byte[] stringBytes = password.getBytes();
        byte[] raw = new byte[0];

        try {
            raw = cipher.doFinal(stringBytes);
        } catch (IllegalBlockSizeException var9) {
            var9.printStackTrace();
        } catch (BadPaddingException var10) {
            var10.printStackTrace();
        }

        BASE64Encoder encoder = new BASE64Encoder();
        return new String(encoder.encode(raw));
    }
}
