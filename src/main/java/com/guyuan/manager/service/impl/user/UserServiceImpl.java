package com.guyuan.manager.service.impl.user;

import ch.qos.logback.core.util.StringUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.guyuan.manager.Entity.PositionEntity;
import com.guyuan.manager.Entity.UserEntity;
import com.guyuan.manager.dao.PositionMapper;
import com.guyuan.manager.dao.UserMapper;
import com.guyuan.manager.dao.po.PositionPO;
import com.guyuan.manager.dao.po.UserPO;
import com.guyuan.manager.exception.MyException;
import com.guyuan.manager.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PositionMapper positionMapper;

//    @Autowired
//    private RoleMapper roleMapper;
//
//    @Autowired
//    private LikeMapper likeMapper;

    @Override
    public UserEntity getUserByName(String userName) {
        UserPO userPO = userMapper.getUserByName(userName);
        if (userPO == null) {return null;}
        String userId = userPO.getUserId();
        // 封装position信息，先存根节点
        List<List<PositionPO>> positionPOList = new ArrayList<>();
        List<PositionPO> listPositions = positionMapper.listPositionByUserId(userId);
        for(PositionPO rootPosition : listPositions) {
            List<PositionPO> tempList = new ArrayList<>();
            tempList.add(rootPosition);
            positionPOList.add(tempList);
        }
        // 顺序获取父节点，并父子节点添加到根节点的children中
        if (!CollectionUtil.isEmpty(listPositions)) {
            listPositions = positionMapper.listParentPositionByPositions(listPositions);
        }
        while(!CollectionUtil.isEmpty(listPositions)) {
            for (PositionPO positionPO : listPositions) {
                for (List<PositionPO> positionList : positionPOList) {
                    if (positionList.get(positionList.size() - 1)
                            .getPositionId()
                            .equals(positionPO.getSubordinate())) {
                        positionList.add(positionPO);
                        break;
                    }
                }
            }
            listPositions = positionMapper.listParentPositionByPositions(listPositions);
        }
        // 单取职位名称，包括团队名称
        List<List<String>> positionNames = new ArrayList<>();
        for(List<PositionPO> positionList : positionPOList) {
            List<String> tempList = new ArrayList<>();
            // 第一个元素为positionId，便于在修改时定位到该职位发送请求
            tempList.add(positionList.get(0).getPositionId());
            for(int i = positionList.size() - 1; i >= 0; i--) {
                tempList.add(positionList.get(i).getPositionName());
            }
            positionNames.add(tempList);
        }

        UserEntity userEntity = UserEntity.builder()
                .userId(userPO.getUserId())
                .userName(userPO.getUserName())
                .password(userPO.getPassword())
                .phone(userPO.getPhone())
                .gender(userPO.getGender())
                .idCard(userPO.getIdCard())
                .email(userPO.getEmail())
                .grade(userPO.getGrade())
                .major(userPO.getMajor())
                .studentId(userPO.getStudentId())
                .experience(userPO.getExperience())
                .currentStatus(userPO.getCurrentStatus())
                .entryTime(userPO.getEntryTime())
                .likeCount(userPO.getLikeCount())
                .liked(false) // Optional.ofNullable(likeMapper.queryLikedById(userId, userPO.getUserId())).map(i -> i == 1).orElse(false)
                .positions(positionNames)
                .roles(null) // roleMapper.listRoleNamesByUserId(userId)
                .build();

        userEntity = convertToMemberListPositionNames(userEntity);

        return userEntity;
    }

    @Override
    public UserEntity modifyUserInfo(UserEntity userEntity, String operatorId) {
        String userId = userEntity.getUserId();

        if (!CollectionUtil.isEmpty(userEntity.getPositionList())) {
            // 转换格式
            List<List<String>> positions = userEntity.getPositionList().stream()
                    .map(s -> Arrays.asList(s.split("-")))
                    .toList();
            // 取到团队名称团队id
            List<String> teamNames = positions.stream()
                    .map(list -> list.get(0))
                    .collect(Collectors.toList());
            if (CollectionUtil.isEmpty(teamNames)) {
                teamNames.add("未选择团队");
            }
            List<String> teamIds = positionMapper.listTeamIdByNames(teamNames);
            // 修改用户所属团队，先全部删掉，再添加
            userMapper.deleteMemberTeam(userId, operatorId);
            userMapper.addMemberTeam(userId, teamIds, operatorId);
            // 删掉原有职位
            positionMapper.deletePositionByUserId(userId);
            // 只到团队的也要加
            List<List<String>> positionOnlyTeamNames = positions.stream()
                    .filter(list -> list.size() == 1)
                    .collect(Collectors.toList());
            List<List<String>> positionNames = positions.stream()
                    .filter(list -> list.size() >= 2)
                    .collect(Collectors.toList());
            // 如果在团队下有职位，就不用单独加团队的
            List<String> positionInTeamNames = positionNames.stream()
                    .map(list -> list.get(0))
                    .collect(Collectors.toList());
            for (List<String> position : positionOnlyTeamNames) {
                if (positionInTeamNames.contains(position.get(0))) {
                    continue;
                }
                List<String> temp = new ArrayList<>();
                temp.add(position.get(0));
                temp.add(position.get(0));
                positionNames.add(temp);
            }

            // 添加职位
            List<PositionEntity> addPositions = new ArrayList<>();
            if (!CollectionUtil.isEmpty(positionNames)) {
                // 拿到每个职位的团队名称
                List<String> addPositionTeamNames = positionNames.stream()
                        .map(list -> list.get(0))
                        .collect(Collectors.toList());
                List<String> addPositionNames = positionNames.stream()
                        .map(list -> list.get(list.size() - 1))
                        .collect(Collectors.toList());
                for (int i = 0; i < addPositionNames.size(); i++) {
                    addPositions.add(PositionEntity.builder()
                            .positionName(addPositionNames.get(i))
                            .teamName(addPositionTeamNames.get(i))
                            .build());
                }
                addPositions = positionMapper.listPositionIdAndTeamIdByNames(addPositions);
                if (!CollectionUtil.isEmpty(addPositions)){
                    positionMapper.addPositionToUser(addPositions, userId, operatorId);
                }
            }
        }

        // 修改用户角色，全删再添加
//        if (!Objects.equals(operatorId, userId)) {
//            roleMapper.deleteUserRoles(userId);
//            List<String> roleNames = userEntity.getRoles();
//            if (!CollectionUtil.isEmpty(roleNames)) {
//                List<String> roleIds = roleMapper.listRoleIdsByNames(roleNames);
//                roleMapper.addUserRoles(userId, roleIds);
//            }
//        }

        userMapper.updateMemberInfo(UserPO.builder()
                .userId(userId)
                .userName(userEntity.getUserName())
                .phone(userEntity.getPhone())
                .gender(userEntity.getGender())
                .idCard(userEntity.getIdCard())
                .email(userEntity.getEmail())
                .grade(userEntity.getGrade())
                .major(userEntity.getMajor())
                .studentId(userEntity.getStudentId())
                .experience(userEntity.getExperience())
                .currentStatus(userEntity.getCurrentStatus())
                .entryTime(userEntity.getEntryTime())
                .likeCount(userEntity.getLikeCount())
                .updateBy(operatorId)
                .build());

        return userEntity;
    }

    @Override
    public void AddUser(UserEntity userEntity, String operatorId) {
        if (StringUtil.isNullOrEmpty(userEntity.getUserName())) {
            throw new MyException("用户名不能为空！");
        }
        if (StringUtil.isNullOrEmpty(userEntity.getPhone())) {
            throw new MyException("手机号不能为空！");
        }
        // 去重
        userEntity.setPositionList(userEntity.getPositionList().stream()
                .distinct().collect(Collectors.toList()));
        userEntity.setUserId(UUID.randomUUID().toString());

        String userId = userEntity.getUserId();
        userMapper.addUser(UserPO.builder()
                .userId(userEntity.getUserId())
                .userName(userEntity.getUserName())
                .phone(userEntity.getPhone())
                .gender(userEntity.getGender())
                .idCard(userEntity.getIdCard())
                .email(userEntity.getEmail())
                .grade(userEntity.getGrade())
                .major(userEntity.getMajor())
                .studentId(userEntity.getStudentId())
                .experience(userEntity.getExperience())
                .currentStatus(userEntity.getCurrentStatus())
                .entryTime(userEntity.getEntryTime())
                .build());

        if (!CollectionUtil.isEmpty(userEntity.getPositionList())) {
            // 转换格式
            List<List<String>> positions = userEntity.getPositionList().stream()
                    .map(s -> Arrays.asList(s.split("-")))
                    .toList();

            // 取到团队名称团队id
            List<String> teamNames = positions.stream()
                    .map(list -> list.get(0))
                    .collect(Collectors.toList());
            if (CollectionUtil.isEmpty(teamNames)) {
                teamNames.add("未选择团队");
            }
            List<String> teamIds = positionMapper.listTeamIdByNames(teamNames);
            // 给用户添加团队
            userMapper.addMemberTeam(userId, teamIds, operatorId);

            // 添加职位
            // 只到团队的也要加
            List<List<String>> positionOnlyTeamNames = positions.stream()
                    .filter(list -> list.size() == 1)
                    .toList();
            List<List<String>> positionNames = positions.stream()
                    .filter(list -> list.size() >= 2)
                    .collect(Collectors.toList());
            // 如果在团队下有职位，就不用单独加团队的
            List<String> positionInTeamNames = positionNames.stream()
                    .map(list -> list.get(0))
                    .toList();
            for (List<String> position : positionOnlyTeamNames) {
                if (positionInTeamNames.contains(position.get(0))) {
                    continue;
                }
                List<String> temp = new ArrayList<>();
                temp.add(position.get(0));
                temp.add(position.get(0));
                positionNames.add(temp);
            }

            // 添加职位
            List<PositionEntity> addPositions = new ArrayList<>();
            if (!CollectionUtil.isEmpty(positionNames)) {
                // 拿到每个职位的团队名称
                List<String> addPositionTeamNames = positionNames.stream()
                        .map(list -> list.get(0))
                        .toList();
                List<String> addPositionNames = positionNames.stream()
                        .map(list -> list.get(list.size() - 1))
                        .toList();
                for (int i = 0; i < addPositionNames.size(); i++) {
                    addPositions.add(PositionEntity.builder()
                            .positionName(addPositionNames.get(i))
                            .teamName(addPositionTeamNames.get(i))
                            .build());
                }
                addPositions = positionMapper.listPositionIdAndTeamIdByNames(addPositions);
                if (!CollectionUtil.isEmpty(addPositions)){
                    positionMapper.addPositionToUser(addPositions, userId, operatorId);
                }
            }
        }

//        // 修改用户角色
//        List<String> roleNames = userEntity.getRoles();
//        if (!CollectionUtil.isEmpty(roleNames)) {
//            List<String> roleIds = roleMapper.listRoleIdsByNames(roleNames);
//            roleMapper.addUserRoles(userId, roleIds);
//        }
    }

    private static UserEntity convertToMemberListPositionNames(UserEntity userEntity) {
        List<List<String>> positionNames = userEntity.getPositions();
        List<String> resultNameList = new ArrayList<>();
        for (List<String> positionList : positionNames) {
            List<String> tempList = new ArrayList<>();
            // 前两个元素为positionId和团队名称，这里要转换成成员列表的职位显示格式，所以下标从2开始
            for (int i = 1; i < positionList.size(); i++) {
                tempList.add(positionList.get(i));
            }
            resultNameList.add(String.join("-", tempList));
        }
        userEntity.setPositionList(resultNameList);
        return userEntity;
    }
}
