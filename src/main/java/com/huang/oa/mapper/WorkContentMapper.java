package com.huang.oa.mapper;

import com.huang.oa.pojo.dto.WorkContentDTO;
import com.huang.oa.pojo.entity.WorkContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WorkContentMapper {
    @Select("select * from work_contents where title = #{title}")
    WorkContent getByTitle(String title);


    void insertAll(WorkContentDTO workContentDTO);

    @Select("select * from work_contents where group_id = #{groupId}")
    List<WorkContent> getByGroupId(int groupId);
}
