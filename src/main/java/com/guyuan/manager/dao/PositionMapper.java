package com.guyuan.manager.dao;

import com.guyuan.manager.Entity.PositionEntity;
import com.guyuan.manager.dao.po.PositionPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;

/**
 * @author 杨之耀
 * @description 职位/分组dao
 * @date 2024/12/3
 */
@Mapper
public interface PositionMapper {

    /**
     * 给成员添加职位
     * @param userId 操作者ID
     * @param memberId 成员ID
     * @param positionIds 职位ID列表
     */
    void addPositionsToMember(@Param("userId")String userId,@Param("memberId") String memberId, @Param("positionIds")List<String> positionIds, String teamId);

    /**
     * 给用户添加职位
     * @param positions
     * @param userId
     */
    void addPositionToUser(@Param("positions")List<PositionEntity> positions, @Param("userId")  String userId, @Param("operatorId") String operatorId);

    /**
     * 删除用户的职位
     * @param positions
     * @param userId
     */
    void deletePositionWithUser(@Param("positions")List<PositionEntity> positions,@Param("userId") String userId);

    /**
     * 获取某个职位的所有下属职位
     * @param positionId
     * @return
     */
    List<PositionPO> listSubordinateByPositionId(@Param("positionId") String positionId);

    /**
     * 获取某个职位的信息
     * @param positionId
     * @return
     */
    PositionPO getPositionByPositionId(@Param("positionId") String positionId);

    /**
     * 获取某个职位的上级职位
     * 用以查询用户的个人职位
     * @param positionId
     * @return
     */
    PositionPO getParentPositionByPositionId(@Param("positionId") String positionId);

    /**
     * 添加职位/分组
     * @param positionPOList
     */
    void addPosition(@Param("positionPOList")List<PositionPO> positionPOList);

    /**
     * 在position表中删除职位/分组
     * @param positionsToDelete
     */
    void deletePositionInPosition(@Param("positionsToDelete")Collection<String> positionsToDelete);

    /**
     * 在user_position关联表中删除职位/分组
     * @param positionsToDelete
     */
    void deletePositionInUserPosition(@Param("positionsToDelete")Collection<String> positionsToDelete);

    /**
     * 用于在删除节点时查询要被删除的节点关联的所有用户
     * @param positionIds
     * @return 对应的所有用户id
     */
    List<String> listUserIdsByPositionIds(@Param("positionIds")Collection<String> positionIds);

    /**
     * 在删除职位时将用户关联到根（父）节点
     * @param positionId 根（父）节点
     * @param userIds 需要更改职位的用户
     */
    void addUsersToPosition(@Param("positionId")String positionId,@Param("userIds") List<String> userIds);
  
    /**
     * 获取某个职位的上级职位
     * 用以查询用户的个人职位
     * @param positions
     * @return
     */
//    @ResultType(PositionPO.class)
//    @Select("""
//            select id,position_id,position_name,level,subordinate,team_id,
//                            create_by,create_time,update_by,update_time
//                    from position
//                    where subordinate in
//                    <foreach collection="positions" item="position" separator="," open="(" close=")">
//                            #{position.positionId}
//                    </foreach>
//                            and is_deleted = 0""")
    List<PositionPO> listParentPositionByPositions(@Param("positions")Collection<PositionPO> positions);

    /**
     * 获取某个用户的所有最下级职位
     * @param userId
     * @return
     */
//    @ResultType(PositionPO.class)
//    @Select("""
//            select t1.id,t1.position_id,t1.position_name,t1.level,t1.subordinate,t1.team_id,
//                            t1.create_by,t1.create_time,t1.update_by,t1.update_time
//                    from position as t1, user_position as t2
//                    where t2.user_id = #{userId}
//                            and t1.position_id = t2.position_id
//                            and t1.subordinate = ''
//                            and t1.is_deleted = 0
//                            and t2.is_deleted = 0""")
    List<PositionPO> listPositionByUserId(@Param("userId") String userId);

    /**
     * 获取某个用户的团队Id和名称
     * @param userId
     * @return
     */
    List<PositionPO> listTeamByUserId(@Param("userId") String userId);

    /**
     * 根据职位名称和团队名称获取职位Id和团队Id
     * @param positions
     * @return
     */
    List<PositionEntity> listPositionIdAndTeamIdByNames(@Param("positions")List<PositionEntity> positions);

    /**
     * 根据团队名称获取团队Id
     * @param teamNames
     * @return
     */
    List<String> listTeamIdByNames(@Param("teamNames")List<String> teamNames);

    /**
     * 根据用户Id删除用户的职位
     * @param userId
     */
    void deletePositionByUserId(@Param("userId") String userId);
}