package com.cwn.wethink.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 邮件参数实体类
 *
 * @author : Rain
 * @date : 2019-12-10 09:26
 */
@Data
@ApiModel("邮件参数实体类")
public class MailParamsDTO implements Serializable {
    private static final long serialVersionUID = 1;
    @ApiModelProperty("发送邮件名")
    private String sendName;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("主机参数")
    private String host;
    @ApiModelProperty("测试发送邮件账号")
    private String toUserName;
}
