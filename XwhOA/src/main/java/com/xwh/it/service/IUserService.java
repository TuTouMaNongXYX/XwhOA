package com.xwh.it.service;

import com.xwh.it.model.dto.UserVo;
import com.xwh.it.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户基础表 服务类
 * </p>
 *
 * @author 谢宇轩
 * @since 2022-06-28
 */
public interface IUserService extends IService<User> {


    /**
     * @return
     * @description 登录验证
     * @author 谢宇轩
     * @date 2022/6/28 15:03
     * @params
     */
    UserVo select_loginIs(String username, String password);


    /**
     * @return
     * @description 注册
     * @author 谢宇轩
     * @date 2022/6/28 17:41
     * @params
     */
    boolean addUser(String username, String password);


     /** @description 根据用户名查询是否有数据
     * @author 谢宇轩
     * @date 2022/6/28 18:05
     * @params
     * @return
     */
     boolean select_isUser(String username);


      /** @description 已登录用户信息
      * @author 谢宇轩
      * @date 2022/6/29 10:49
      * @params
      * @return
      */
    UserVo select_ThisUser(String oppnId);

}
