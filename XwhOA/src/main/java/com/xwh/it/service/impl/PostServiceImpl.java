package com.xwh.it.service.impl;

import com.xwh.it.model.entity.Post;
import com.xwh.it.mapper.PostMapper;
import com.xwh.it.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位表 服务实现类
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

}
