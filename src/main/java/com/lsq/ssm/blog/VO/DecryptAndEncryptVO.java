package com.lsq.ssm.blog.VO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
public class DecryptAndEncryptVO implements Serializable {
    private String account;
    private String password;
}
