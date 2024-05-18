package com.huang.oa.mapper;

import com.huang.oa.pojo.dto.UpdateProgressDTO;
import com.huang.oa.pojo.dto.WorkProgressesDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface WorkProgressesMapper {
    @Insert("insert into work_progresses (content_id, progress_status, update_time, is_editable) " +
            "values (#{conteentId},#{progressStatus},#{updateTime},#{isEditable})")
    void insert(WorkProgressesDTO workProgresses);

    @Update("update work_progresses set description = #{description} where progresses_id = #{progressId}")
    void updateDescription(UpdateProgressDTO updateProgressDTO);

    @Select("select is_editable from work_progresses where progresses_id = #{progressId}")
    int getIsEditable(Integer progressId);

    @Update("update work_progresses set is_editable = 0 where progresses_id = #{progressId}")
    void updateIsEditable(Integer progressId);

    @Update("update work_progresses set progress_status = #{status} where progresses_id = #{progressId}")
    void updateStatusById(Integer progressId, String status);
}
