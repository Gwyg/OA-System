package com.huang.oa.controller;

import cn.dev33.satoken.util.SaResult;
import com.huang.oa.pojo.dto.DepartmentMemberDTO;
import com.huang.oa.pojo.entity.User;
import com.huang.oa.pojo.vo.AnnouncementVO;
import com.huang.oa.pojo.vo.PageResultVO;
import com.huang.oa.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dep")
@Slf4j
@Tag(name = "部门相关功能接口")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询部门成员
     * @param departmentMemberDTO 部门相关信息
     * @return 部门成员集合
     */
    @GetMapping("/page")
    @Operation(summary = "查询部门成员")
    public SaResult getDepartmentmember(DepartmentMemberDTO departmentMemberDTO){
        log.info("查询当前部门成员{}",departmentMemberDTO);
        PageResultVO pageResultVO = departmentService.pageQuery(departmentMemberDTO);
        return SaResult.data(pageResultVO);
    }

    /**
     * 查询部门公告
     * @param departmentId 部门ID
     * @return
     */
    @GetMapping("/announcement/{departmentId}")
    @Operation(summary = "查询部门公告")
    public SaResult getAnnouncement(@PathVariable Integer departmentId){
        log.info("查询部门公告：{}",departmentId);
        List<AnnouncementVO> list = departmentService.getAnnouncementByDepartmentId(departmentId);
        return SaResult.data(list);
    }

}
