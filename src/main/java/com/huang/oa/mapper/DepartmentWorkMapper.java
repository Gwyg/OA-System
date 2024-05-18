package com.huang.oa.mapper;

import com.huang.oa.pojo.vo.DepartmentWorkVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentWorkMapper {
    @Select("select * from department_work where department_id = #{departmentId}")
    List<DepartmentWorkVO> getByDepartmentId(Integer departmentId);

    @Select("select department_name from departments where department_id =#{departmentId}")
    String getNameByDepartmentId(Integer departmentId);
}
