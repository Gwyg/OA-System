package com.huang.oa.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huang.oa.mapper.AnnouncementMapper;
import com.huang.oa.mapper.UserMapper;
import com.huang.oa.pojo.dto.DepartmentMemberDTO;
import com.huang.oa.pojo.entity.User;
import com.huang.oa.pojo.vo.AnnouncementVO;
import com.huang.oa.pojo.vo.PageResultVO;
import com.huang.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public PageResultVO pageQuery(DepartmentMemberDTO departmentMemberDTO) {
        PageHelper.startPage(departmentMemberDTO.getPage(),departmentMemberDTO.getPageSize());
        // TODO
        List<User> list= userMapper.getById(departmentMemberDTO.getDepartmentId());
        PageInfo<User> page = new PageInfo<>(list);
        Long total = page.getTotal();
        for (User user : list) {
            user.setPassword("******");
        }

        return new PageResultVO(total,list);
    }

    @Override
    public List<AnnouncementVO> getAnnouncementByDepartmentId(Integer departmentId) {
        List<AnnouncementVO> list = announcementMapper.getByDepartmentId(departmentId);
        return list;
    }
}
