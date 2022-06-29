package com.xwh.it.mapper;

import com.xwh.it.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户基础表 Mapper 接口
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
public interface UserMapper extends BaseMapper<User> {

    //获得当前用户的加密盐值
    @Select("select salt from system_user where username = #{username}")
    Integer GetUserSalt(String username);


}
