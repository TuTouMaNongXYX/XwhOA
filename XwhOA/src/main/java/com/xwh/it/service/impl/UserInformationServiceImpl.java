package com.xwh.it.service.impl;

import com.xwh.it.model.entity.UserInformation;
import com.xwh.it.mapper.UserInformationMapper;
import com.xwh.it.service.IUserInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Service
public class UserInformationServiceImpl extends ServiceImpl<UserInformationMapper, UserInformation> implements IUserInformationService {

}
