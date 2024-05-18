package com.huang.oa.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.huang.oa.common.ErrorMessage;
import com.huang.oa.common.ProgressStatus;
import com.huang.oa.exception.InsertFailureException;
import com.huang.oa.exception.LackOfAuthorityException;
import com.huang.oa.exception.NotOneDepartmentException;
import com.huang.oa.exception.AlreadyExistException;
import com.huang.oa.mapper.*;
import com.huang.oa.pojo.dto.UpdateProgressDTO;
import com.huang.oa.pojo.dto.WorkContentDTO;
import com.huang.oa.pojo.dto.WorkGroupDTO;
import com.huang.oa.pojo.entity.WorkContent;
import com.huang.oa.pojo.entity.WorkGroup;
import com.huang.oa.pojo.dto.WorkProgressesDTO;
import com.huang.oa.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WorkGroupMapper workGroupMapper;
    @Autowired
    private WorkMemberMapper workMemberMapper;
    @Autowired
    private DepartmentsMapper departmentsMapper;
    @Autowired
    private WorkContentMapper workContentMapper;
    @Autowired
    private WorkProgressesMapper workProgressesMapper;

    @Override
    @Transactional
    public void workGroup(WorkGroupDTO workGroupDTO) {
        List<Integer> userIds = workGroupDTO.getUserIds();
        List<Integer> departmentIds = userMapper.getDepartmenIdByUserIds(userIds);
        Integer temp = departmentIds.get(0);
        for (Integer departmentId : departmentIds) {
            if((int)temp != departmentId){
                throw new NotOneDepartmentException(ErrorMessage.NOT_SAME_DEPARTMENT);
            }
        }
        WorkGroup workGroup = workGroupMapper.getByGroupName(workGroupDTO.getGroupName());
        if(workGroup != null){
            throw new AlreadyExistException(ErrorMessage.WORKING_GROUP_ALREADY_EXISTS);
        }
        int i = workGroupMapper.insertAll(workGroupDTO);
        if(i == 0){
            throw new InsertFailureException(ErrorMessage.GROUP_FAILURE);
        }
        int j = workMemberMapper.insert(userIds,workGroupDTO.getGroupId());
        if(j == 0){
            throw new InsertFailureException(ErrorMessage.FAILED_TO_ADD_STAFF);
        }
    }

    @Override
    public void workcontent(WorkContentDTO workContentDTO) {
        WorkContent workContent = workContentMapper.getByTitle(workContentDTO.getTitle());
        if(workContent != null){
            throw new AlreadyExistException(ErrorMessage.WORK_ALREADY_EXISTS);
        }
        workContentMapper.insertAll(workContentDTO);
        WorkProgressesDTO workProgresses = new WorkProgressesDTO();
        workProgresses.setConteentId(workContentDTO.getContentId());
        workProgresses.setProgressStatus(ProgressStatus.NOT_STARTED);
        workProgresses.setUpdateTime(LocalDateTime.now());
        workProgresses.setIsEditable(1);//1 可以编辑
        workProgressesMapper.insert(workProgresses);
    }

    @Override
    @Transactional
    public void updateprogress(UpdateProgressDTO updateProgressDTO) {
        List<Integer> memberList = workMemberMapper.getMemberListByProgeressId(updateProgressDTO.getProgressId());
        if (!memberList.contains(StpUtil.getLoginIdAsInt())){
            throw new LackOfAuthorityException(ErrorMessage.LACK_OF_AUTHORITY);
        }
        int isEditable = workProgressesMapper.getIsEditable(updateProgressDTO.getProgressId());
        if(isEditable == 0){
            throw new LackOfAuthorityException(ErrorMessage.ALREADY_OVER);
        }
        workProgressesMapper.updateStatusById(updateProgressDTO.getProgressId(),ProgressStatus.UNDERWAY);
        workProgressesMapper.updateDescription(updateProgressDTO);
    }

    @Override
    public void endWork(Integer progressId) {
        workProgressesMapper.updateStatusById(progressId,ProgressStatus.FINISHED);
        workProgressesMapper.updateIsEditable(progressId);
    }
}
