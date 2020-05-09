package com.qingchen.demo.mydemo.mapper.slave;

import com.qingchen.demo.mydemo.model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName TestMapper
 * @description:
 * @author: WangChen
 * @create: 2020-04-26 16:37
 **/
public interface SlaveTestMapper {

     @Insert("insert into t_message(context, url, create_time) values (#{context}, #{url}, #{localDateTime})")
     int insert(Message message);

     @Select("select * from t_message where message_id = #{id}")
     Message query(@Param("id") long id);
}
