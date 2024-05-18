package com.huang.oa.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huang.oa.mapper.AnnouncementMapper;
import com.huang.oa.mapper.DepartmentWorkMapper;
import com.huang.oa.mapper.UserMapper;
import com.huang.oa.pojo.dto.DepartmentAnnouncementDTO;
import com.huang.oa.pojo.dto.DepartmentMemberDTO;
import com.huang.oa.pojo.entity.DepartmentAnnouncement;
import com.huang.oa.pojo.entity.User;
import com.huang.oa.pojo.vo.AnnouncementVO;
import com.huang.oa.pojo.vo.DepartmentWorkVO;
import com.huang.oa.pojo.vo.PageResultVO;
import com.huang.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AnnouncementMapper announcementMapper;
    @Autowired
    private DepartmentWorkMapper departmentWorkMapper;

    @Override
    public PageResultVO pageQuery(DepartmentMemberDTO departmentMemberDTO) {
        PageHelper.startPage(departmentMemberDTO.getPage(),departmentMemberDTO.getPageSize());
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

    @Override
    public List getWorkByDepartmentId(Integer departmentId) {
        List<DepartmentWorkVO> list = departmentWorkMapper.getByDepartmentId(departmentId);
        return list;
    }

    @Override
    public int insertAnnouncement(DepartmentAnnouncementDTO departmentAnnouncementDTO) {
        departmentAnnouncementDTO.setPublishDate(LocalDateTime.now());
        return announcementMapper.insertAll(departmentAnnouncementDTO);
    }
}
