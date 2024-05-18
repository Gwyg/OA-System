package com.huang.oa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WorkMemberMapper {
    int insert(List<Integer> userIds, Integer groupId);

    List<Integer> getMemberListByProgeressId(Integer progressId);

    @Select("select group_id from work_member where member_id = #{userId}")
    int getGroupIdByMemberId(int userId);
}
