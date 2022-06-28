package com.xwh.it.service.impl;

import com.xwh.it.model.entity.User;
import com.xwh.it.mapper.UserMapper;
import com.xwh.it.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基础表 服务实现类
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
