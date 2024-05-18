package com.huang.oa.service;

import com.huang.oa.pojo.dto.UpdateProgressDTO;
import com.huang.oa.pojo.dto.WorkContentDTO;
import com.huang.oa.pojo.dto.WorkGroupDTO;
import com.huang.oa.pojo.entity.WorkContent;

public interface WorkService {
    void workGroup(WorkGroupDTO workGroupDTO);

    void workcontent(WorkContentDTO workContentDTO);

    void updateprogress(UpdateProgressDTO updateProgressDTO);

    void endWork(Integer progressId);
}
