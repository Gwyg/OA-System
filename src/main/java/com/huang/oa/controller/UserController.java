package com.huang.oa.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.huang.oa.common.Role;
import com.huang.oa.pojo.dto.AlterRequestDTO;
import com.huang.oa.pojo.dto.CheckDTO;
import com.huang.oa.pojo.dto.DepartmentRequestDTO;
import com.huang.oa.pojo.dto.LeaveRequestDTO;
import com.huang.oa.pojo.entity.Record;
import com.huang.oa.pojo.entity.WorkContent;
import com.huang.oa.pojo.vo.PageResultVO;
import com.huang.oa.pojo.vo.RecordVO;
import com.huang.oa.pojo.vo.UserQueryVO;
import com.huang.oa.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "用户功能")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所属部门。身份信息
     * @return
     */
    @GetMapping("/query")
    @Operation(summary = "查询部门，身份")
    public SaResult query(){
        log.info("查询自己所属部门，身份信息");
        UserQueryVO user = userService.query(StpUtil.getLoginIdAsInt());
        return SaResult.data(user);
    }

    /**
     * 查询工作内容
     * @return
     */
    @GetMapping("/work")
    @Operation(summary = "查询工作内容")
    public SaResult work(){
        log.info("查询自己的工作内容");
        List<WorkContent> workContent = userService.getWorkContent(StpUtil.getLoginIdAsInt());
        return SaResult.data(workContent);
    }


    /**
     * 部门申请请求
     * @param requestDTO
     * @return
     */
    @PostMapping("/request")
    @Operation(summary = "部门申请")
    public SaResult request(@RequestBody DepartmentRequestDTO requestDTO){
        log.info("部门申请请求：{}", requestDTO);
        userService.request(requestDTO);
        return SaResult.ok();
    }

    /**
     * 部门审核接口
     * @param checkDTO
     * @return
     */
    @PutMapping("/check")
    @Operation(summary = "部门审核")
    @SaCheckRole(value = {Role.DEPARTMENT_ADMIN,Role.SUPER_AdMIN},mode = SaMode.OR)
    public SaResult check(@RequestBody CheckDTO checkDTO){
        log.info("部门审核：{}",checkDTO);
        userService.check(checkDTO);
        return SaResult.ok();
    }

    /**
     * 请假申请接口
     * @param requestDTO
     * @return
     */
    @PostMapping("/leaveRequest")
    @Operation(summary = "请假申请接口")
    public SaResult leaveRequest(@RequestBody LeaveRequestDTO requestDTO){
        log.info("请假申请接口{}", requestDTO);
        userService.leaveRequest(requestDTO);
        return SaResult.ok();
    }

    /**
     * 请假审核接口
     * @param checkDTO
     * @return
     */
    @PutMapping("/leaveCheck")
    @Operation(summary = "请假审核接口")
    @SaCheckRole(value = {Role.DEPARTMENT_ADMIN,Role.SUPER_AdMIN},mode = SaMode.OR)
    public SaResult leaveCheck(@RequestBody CheckDTO checkDTO){
        log.info("请假审核接口：{}",checkDTO);
        userService.leaveCheck(checkDTO);
        return SaResult.ok();
    }
    @PutMapping("/alterLeave")
    @Operation(summary = "修改驳回的申请")
    public SaResult alterLeave(@RequestBody AlterRequestDTO alterDTO){
        log.info("修改驳回的请求：{}",alterDTO);
        userService.alterLeave(alterDTO);
        return SaResult.ok();
    }

    /**
     * 用户头像上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @Operation(summary = "用户头像上传")
    public SaResult upload(@RequestParam("file") MultipartFile file){
        log.info("用户头像上传");
        String path = userService.upload(file);
        return SaResult.data(path);
    }

    @GetMapping("/record")
    @Operation(summary = "查询操作记录")
    @SaCheckRole(Role.SUPER_AdMIN)
    public SaResult record(Integer page,Integer pageSize){
        log.info("查询操作记录");
        PageResultVO list = userService.record(page,pageSize);
        return SaResult.data(list);
    }

}
