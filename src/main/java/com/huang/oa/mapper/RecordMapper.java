package com.huang.oa.mapper;

import com.huang.oa.pojo.entity.Record;
import com.huang.oa.pojo.vo.RecordVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecordMapper {
    @Insert("insert into record (type, user_id, status, time) " +
            "VALUES (#{type},#{userId},#{status},#{time})")
    void insetAll(Record record);

    @Select("select * from record order by time desc")
    List<RecordVO> getAll();
}
