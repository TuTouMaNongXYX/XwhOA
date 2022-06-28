package com.xwh.it.service.impl;

import com.xwh.it.model.entity.UserIdentity;
import com.xwh.it.mapper.UserIdentityMapper;
import com.xwh.it.service.IUserIdentityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户身份表 服务实现类
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Service
public class UserIdentityServiceImpl extends ServiceImpl<UserIdentityMapper, UserIdentity> implements IUserIdentityService {

}
