package com.huang.oa.mapper;

import com.huang.oa.pojo.vo.AnnouncementVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    @Select("select * from department_announcements where department_id = #{departmentId}")
    List<AnnouncementVO> getByDepartmentId(Integer departmentId);
}
