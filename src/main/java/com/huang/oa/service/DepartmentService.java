package com.huang.oa.service;

import com.huang.oa.pojo.dto.DepartmentMemberDTO;
import com.huang.oa.pojo.entity.User;
import com.huang.oa.pojo.vo.AnnouncementVO;
import com.huang.oa.pojo.vo.PageResultVO;

import java.util.List;

public interface DepartmentService {
    PageResultVO pageQuery(DepartmentMemberDTO departmentMemberDTO);

    List<AnnouncementVO> getAnnouncementByDepartmentId(Integer departmentId);
}
