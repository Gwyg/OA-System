package com.huang.oa.mapper;

import com.huang.oa.pojo.dto.WorkGroupDTO;
import com.huang.oa.pojo.entity.WorkGroup;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WorkGroupMapper {


    int insertAll(WorkGroupDTO workGroupDTO);

    @Select("select * from work_groups where group_name = #{groupName}")
    WorkGroup getByGroupName(String groupName);
}
