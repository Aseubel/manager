package com.guyuan.manager.dao.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author yangzhiyao
 * @description user持久类对象，时间类不加那几个注解存不了Redis
 * @date 2024/12/3
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户业务id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 用户性别
     */
    private Integer gender;
    /**
     * 用户身份证号
     */
    private String idCard;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户年级
     */
    private Integer grade;
    /**
     * 用户专业
     */
    private String major;
    /**
     * 用户学号
     */
    private String studentId;
    /**
     * 用户实习/就职经历
     */
    private String experience;
    /**
     * 用户现状
     */
    private String currentStatus;
    /**
     * 用户加入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate entryTime;
    /**
     * 用户所获得赞数量
     */
    private Integer likeCount;
    /**
     * 创建该用户的用户业务id
     */
    private String createBy;
    /**
     * 更新该用户的用户业务id
     */
    private String updateBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
