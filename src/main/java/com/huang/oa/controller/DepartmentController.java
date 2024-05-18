package com.huang.oa.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.util.SaResult;
import com.huang.oa.common.ErrorMessage;
import com.huang.oa.common.Role;
import com.huang.oa.pojo.dto.DepartmentAnnouncementDTO;
import com.huang.oa.pojo.dto.DepartmentMemberDTO;
import com.huang.oa.pojo.entity.DepartmentAnnouncement;
import com.huang.oa.pojo.entity.User;
import com.huang.oa.pojo.vo.AnnouncementVO;
import com.huang.oa.pojo.vo.DepartmentWorkVO;
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

    /**
     * 查询部门工作
     * @param departmentId 部门ID
     * @return
     */
    @GetMapping("/work/{departmentId}")
    @Operation(summary = "查询部门工作")
    public SaResult work(@PathVariable Integer departmentId){
        log.info("查询部门工作{}",departmentId);
        List<DepartmentWorkVO> list = departmentService.getWorkByDepartmentId(departmentId);
        return SaResult.data(list);
    }

    @PostMapping("/announcement")
    @Operation(summary = "发布部门公告")
    @SaCheckRole(value = {Role.SUPER_AdMIN,Role.DEPARTMENT_ADMIN},mode = SaMode.OR)
    public SaResult saveAnnouncement(@RequestBody DepartmentAnnouncementDTO departmentAnnouncementDTO){
        log.info("发布部门公告{}",departmentAnnouncementDTO);
        int i = departmentService.insertAnnouncement(departmentAnnouncementDTO);
        return i == 1?SaResult.ok():SaResult.error(ErrorMessage.FAILED_MAKE_AN_ANNOUNCEMENT);
    }
}
