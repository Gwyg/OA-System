package com.huang.oa.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.huang.oa.common.*;
import com.huang.oa.exception.AlreadyExistException;
import com.huang.oa.exception.LackOfAuthorityException;
import com.huang.oa.exception.NotModifiedException;
import com.huang.oa.exception.PhotoUploadFailedException;
import com.huang.oa.mapper.*;
import com.huang.oa.pojo.dto.AlterRequestDTO;
import com.huang.oa.pojo.dto.CheckDTO;
import com.huang.oa.pojo.dto.DepartmentRequestDTO;
import com.huang.oa.pojo.dto.LeaveRequestDTO;
import com.huang.oa.pojo.entity.DepartmentRequest;
import com.huang.oa.pojo.entity.LeaveRequest;
import com.huang.oa.pojo.entity.User;
import com.huang.oa.pojo.entity.WorkContent;
import com.huang.oa.pojo.vo.UserQueryVO;
import com.huang.oa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentWorkMapper departmentWorkMapper;
    @Autowired
    private WorkMemberMapper workMemberMapper;
    @Autowired
    private WorkContentMapper workContentMapper;
    @Autowired
    private DepartmentRequestMapper departmentRequestMapper;
    @Autowired
    private DepartmentsMapper departmentsMapper;
    @Autowired
    private LeaveRequestMapper leaveRequestMapper;
    @Autowired
    private LeaveRecordMapper leaveRecordMapper;

    @Override
    public UserQueryVO query(int userId) {
        UserQueryVO userVO = new UserQueryVO();
        User user = userMapper.getByUserId(userId);
        String departmentName = departmentWorkMapper.getNameByDepartmentId(user.getDepartmentId());
        userVO.setRole(user.getRole());
        userVO.setStatus(user.getStatus());
        userVO.setDepartmentName(departmentName);
        return userVO;
    }

    @Override
    public List<WorkContent> getWorkContent(int userId) {
        int groupId = workMemberMapper.getGroupIdByMemberId(userId);
        List<WorkContent> workContents = workContentMapper.getByGroupId(groupId);
        return workContents;
    }

    @Override
    @Transactional
    public void request(DepartmentRequestDTO requestDTO) {
        DepartmentRequest request = departmentRequestMapper.getByUserId(StpUtil.getLoginIdAsInt());
        if(request != null) {
            throw new AlreadyExistException(ErrorMessage.APPLICATION_ALREADY_EXISTS);
        }
        requestDTO.setRequestTime(LocalDateTime.now());
        requestDTO.setUserId(StpUtil.getLoginIdAsInt());
        requestDTO.setStatus(Status.PENDING);
        departmentRequestMapper.insertAll(requestDTO);
    }

    @Override
    @Transactional
    public void check(CheckDTO checkDTO) {
        DepartmentRequest request = departmentRequestMapper.getById(checkDTO.getRequestId());

        if(RequestType.JOIN.equals(request.getRequestType())) {
            int managerId = departmentsMapper.getManagerIdById(request.getDepartmentId());
            String role = userMapper.getRoleById(StpUtil.getLoginIdAsInt());
            if(managerId != StpUtil.getLoginIdAsInt() && !Role.SUPER_AdMIN.equals(role)){
                throw new LackOfAuthorityException(ErrorMessage.LACK_OF_AUTHORITY);
            }

            if (Decision.AGREE.equals(checkDTO.getDecision())) {
                userMapper.updateDepartmentIdById(request.getUserId(),request.getDepartmentId());
                departmentRequestMapper.delete(checkDTO.getRequestId());
            }else if (Decision.REJECT.equals(checkDTO.getDecision())) {
                departmentRequestMapper.updateStatusById(checkDTO.getRequestId(),Status.REJECTED);
            }
        }

        if(RequestType.CHANGE.equals(request.getRequestType())) {
            if(Status.PENDING.equals(request.getStatus())) {
                int departmentId = userMapper.getDepartmentIdById(StpUtil.getLoginIdAsInt());
                int managertId = departmentsMapper.getManagerIdById(departmentId);
                String role = userMapper.getRoleById(StpUtil.getLoginIdAsInt());
                if(managertId != StpUtil.getLoginIdAsInt() && !Role.SUPER_AdMIN.equals(role)) {
                    throw new LackOfAuthorityException(ErrorMessage.LACK_OF_AUTHORITY);
                }
                if(Decision.AGREE.equals(checkDTO.getDecision())) {
                    departmentRequestMapper.updateStatusById(checkDTO.getRequestId(),Status.SECOND);
                }else if (Decision.REJECT.equals(checkDTO.getDecision())) {
                    departmentRequestMapper.updateStatusById(checkDTO.getRequestId(),Status.REJECTED);
                }
            }else if(Status.SECOND.equals(request.getStatus())) {
                int managerId = departmentsMapper.getManagerIdById(request.getDepartmentId());
                String role = userMapper.getRoleById(StpUtil.getLoginIdAsInt());
                if(managerId != StpUtil.getLoginIdAsInt() && !Role.SUPER_AdMIN.equals(role)) {
                    throw new LackOfAuthorityException(ErrorMessage.LACK_OF_AUTHORITY);
                }
                if(Decision.AGREE.equals(checkDTO.getDecision())) {
                    userMapper.updateDepartmentIdById(request.getUserId(),request.getDepartmentId());
                    departmentRequestMapper.delete(checkDTO.getRequestId());
                }else if (Decision.REJECT.equals(checkDTO.getDecision())) {
                    departmentRequestMapper.updateStatusById(checkDTO.getRequestId(),Status.REJECTED);
                }

            }

        }
    }

    @Override
    public void leaveRequest(LeaveRequestDTO requestDTO) {
        LeaveRequest request = leaveRequestMapper.getByUserId(StpUtil.getLoginIdAsInt());
        if(request != null){
            throw new AlreadyExistException(ErrorMessage.APPLICATION_ALREADY_EXISTS);
        }
        requestDTO.setStatus(Status.PENDING);
        requestDTO.setUserId(StpUtil.getLoginIdAsInt());
        leaveRequestMapper.insertAll(requestDTO);
    }

    @Override
    @Transactional
    public void leaveCheck(CheckDTO checkDTO) {
        LeaveRequest request = leaveRequestMapper.getById(checkDTO.getRequestId());
        int departmentId = userMapper.getDepartmentIdById(request.getUserId());
        int managerId = departmentsMapper.getManagerIdById(departmentId);
        String role = userMapper.getRoleById(StpUtil.getLoginIdAsInt());
        if(managerId != StpUtil.getLoginIdAsInt() && Role.SUPER_AdMIN.equals(role)) {
            throw new LackOfAuthorityException(ErrorMessage.LACK_OF_AUTHORITY);
        }
        if(Decision.AGREE.equals(checkDTO.getDecision())) {
            request.setStatus(Status.APPROVED);
            leaveRecordMapper.insertAll(request);
            leaveRequestMapper.delete(checkDTO.getRequestId());
        }else if(Decision.REJECT.equals(checkDTO.getDecision())) {
            request.setStatus(Status.REJECTED);
            leaveRecordMapper.insertAll(request);
            leaveRequestMapper.delete(checkDTO.getRequestId());
        }else if(Decision.SENDBACK.equals(checkDTO.getDecision())) {
            leaveRequestMapper.updateStatusById(request.getRequestId(),Status.DISMISSED);
        }
    }

    @Override
    public void alterLeave(AlterRequestDTO alterDTO) {
        LeaveRequest request = leaveRequestMapper.getById(alterDTO.getRequestId());
        if(request.getUserId() != StpUtil.getLoginIdAsInt()){
            throw new LackOfAuthorityException(ErrorMessage.LACK_OF_AUTHORITY);
        }
        if(request.getReason().equals(alterDTO.getReason())){
            throw new NotModifiedException(ErrorMessage.NOT_MODIFIED);
        }
        request.setStatus(Status.PENDING);
        leaveRecordMapper.updateReasonById(request.getRequestId(),request.getReason(),request.getStatus());
    }

    @Override
    public String upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString().replace("-","") + fileExtension;
        try {
            file.transferTo(new File("E:\\workspace\\OA\\src\\main\\resources\\static\\"+newFileName));
        } catch (IOException e) {
            throw new PhotoUploadFailedException("头像上传失败");
        }
        String path = "http://localhost:8080/static/"+newFileName;
        return path;
    }

}
