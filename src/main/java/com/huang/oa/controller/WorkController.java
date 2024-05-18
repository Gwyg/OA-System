package com.huang.oa.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.util.SaResult;
import com.huang.oa.common.Role;
import com.huang.oa.pojo.dto.UpdateProgressDTO;
import com.huang.oa.pojo.dto.WorkContentDTO;
import com.huang.oa.pojo.dto.WorkGroupDTO;
import com.huang.oa.pojo.entity.WorkContent;
import com.huang.oa.service.WorkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/work")
@Tag(name = "工作相关功能接口")
@Slf4j
public class WorkController {

    @Autowired
    private WorkService workService;


    /**
     * 新建一个工作小组
     * @param workGroupDTO
     * @return
     */
    @PostMapping("/group")
    @Operation(summary = "工作分组")
    @SaCheckRole(value = {Role.DEPARTMENT_ADMIN,Role.SUPER_AdMIN},mode = SaMode.OR)
    public SaResult workGroup(@RequestBody WorkGroupDTO workGroupDTO){
        log.info("工作分组：{}", workGroupDTO);
        workService.workGroup(workGroupDTO);
        return SaResult.ok();
    }

    /**
     * 部门负责人指定工作内容
     * @param workContentDTO
     * @return
     */
    @PostMapping("/content")
    @Operation(summary = "工作内容")
    @SaCheckRole(value = {Role.DEPARTMENT_ADMIN,Role.SUPER_AdMIN},mode = SaMode.OR)
    public SaResult workContent(@RequestBody WorkContentDTO workContentDTO){
        log.info("工作内容：{}", workContentDTO);
        workService.workcontent(workContentDTO);
        return SaResult.ok();
    }

    /**
     * 修改工作进度
     * @return
     */
    @PutMapping("/progress")
    @Operation(summary = "工作进度")
    public SaResult updateprogress(@RequestBody UpdateProgressDTO updateProgressDTO){
        log.info("修改工作进度：{}", updateProgressDTO);
        workService.updateprogress(updateProgressDTO);
        return SaResult.ok();
    }
    @PutMapping("/end")
    @Operation(summary = "工作结束")
    public SaResult endWork(@RequestBody Integer progressId){
        log.info("工作结束：{}", progressId);
        workService.endWork(progressId);
        return SaResult.ok();
    }

}
