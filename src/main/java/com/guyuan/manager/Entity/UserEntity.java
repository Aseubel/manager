package com.guyuan.manager.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author 杨之耀
 * @description 用户实体对象
 * @date 2024/12/3
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate entryTime;
    /**
     * 用户所获得赞数量
     */
    private Integer likeCount;
    /**
     * 用户点赞状态
     */
    private Boolean liked;
    /**
     * 用户所属职位/分组
     */
    private List<List<String>> positions;
    /**
     * 用户所属职位列表
     */
    private List<String> positionList;

    private Object teams;

    private List<String> roles;

    public void init() {
        this.userName = Optional.ofNullable(this.userName).orElse("");
        this.password = Optional.ofNullable(this.password).orElse("");
        this.phone = Optional.ofNullable(this.phone).orElse("");
        this.idCard = Optional.ofNullable(this.idCard).orElse("");
        this.email = Optional.ofNullable(this.email).orElse("");
        this.experience = Optional.ofNullable(this.experience).orElse("");
        this.currentStatus = Optional.ofNullable(this.currentStatus).orElse("");
        this.major = Optional.ofNullable(this.major).orElse("");
        this.studentId = Optional.ofNullable(this.studentId).orElse("");

    }

}
