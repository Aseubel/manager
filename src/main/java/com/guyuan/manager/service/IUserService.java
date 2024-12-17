package com.guyuan.manager.service;

import com.guyuan.manager.Entity.UserEntity;

/**
 * @author 杨之耀
 * @description 用户服务统一接口
 * @date 2024/12/3
 */
public interface IUserService {

    /**
     * 根据用户id获取用户信息
     * @param userName 用户名
     * @return 用户信息
     * @author 杨之耀
     * @date 2024/12/5
     */
    UserEntity getUserByName(String userName);

    /**
     * 修改用户信息
     * @param userEntity
     * @param operatorId
     * @return
     */
    UserEntity modifyUserInfo(UserEntity userEntity, String operatorId);

    /**
     * 添加用户
     * @param userEntity
     * @param userId
     */
    void AddUser(UserEntity userEntity, String userId);
}
