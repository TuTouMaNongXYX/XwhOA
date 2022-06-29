package com.xwh.it.configure.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 谢宇轩  全局事务配置文件
 * @Description: TODO
 * @DateTime: 2022/6/29 9:02
 **/
@Configuration
public class GlobalTransactionHandler {

    @Resource
    private PlatformTransactionManager transactionManager; //事务管理


    /**
     * 事务管理配置
     */
    @Bean
    public TransactionInterceptor getTxAdvice(){

        //设置第一个事务管理的模式（适用于“增删改”）
        RuleBasedTransactionAttribute txAttr_required = new RuleBasedTransactionAttribute();
        //设置事务传播行为（当前存在事务则加入其中，如果没有则新创建一个事务）
        txAttr_required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //指定事务回滚异常类型（设置为“Exception”级别）
        txAttr_required.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        //设置事务隔离级别（读以提交的数据,此处可不做设置，数据库默认的隔离级别就行）
        txAttr_required.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);


        //设置第二个事务管理的模式（适用于“查”）
        RuleBasedTransactionAttribute txAttr_readonly = new RuleBasedTransactionAttribute();
        //设置事务传播行为（当前存在事务则挂起，继续执行当前逻辑，执行结束后恢复上下文事务）
        txAttr_readonly.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        //指定事务回滚异常类型（设置为“Exception”级别）
        txAttr_readonly.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        //设置事务隔离级别（读以提交的数据,此处可不做设置，数据库默认的隔离级别就行）
        txAttr_readonly.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        //设置事务是否为“只读”（非必须，只是声明该事务中不会进行修改数据库的操作，可减轻由事务机制造成的数据库，属压力于性能优化推荐配置）
        txAttr_readonly.setReadOnly(true);


        //事务管理规则，承载需要进行事务管理的方法名（模糊匹配），及事务的传播行为和隔离级别等属性
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        //source.addTransactionalMethod("insert*",txAttr_required);  可以直接设置，推荐使用map集合或者properties文件操作存储

        /**
         * 创建一个map用于存储需要进行事务管理的方法名（模糊匹配）
         */
        Map<String, TransactionAttribute> map = new HashMap<>();
        //增删改的操作需要设置为REQUIRED的传播行为
        map.put("insert*",txAttr_required);
        map.put("add*",txAttr_required);
        map.put("increase*",txAttr_required);
        map.put("delete*",txAttr_required);
        map.put("remove*",txAttr_required);
        map.put("update*",txAttr_required);
        map.put("alter*",txAttr_required);
        map.put("modify*",txAttr_required);
        //查询的操作设置为REQUIRED_NOT_SUPPORT非事务传播行为，并设置为只读，减轻数据库压力
        map.put("select*",txAttr_readonly);
        map.put("get*",txAttr_readonly);

        //注入上述匹配好的map集合
        source.setNameMap(map);

        //实例化事务拦截器(整合事务管理和事务操作数据源-要操作的事务方法)
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager,source);
        //并将事务通知返回
        return txAdvice;
    }


    /**
     * 利用AspectJExpressionPointcut设置切面
     * @return
     */
    @Bean
    public Advisor txAdviceAdvisor(){

        //声明切入面（也就是所有切入点的逻辑集合，所有切入点形成的切面）
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //设置切入点的路径
        pointcut.setExpression("execution(* com.xwh.it.service..*.*(..))");

        //返回aop配置：整合切面（切入点集合） 和  配置好的事务通知（也就是事务拦截操作）
        Advisor advisor = new DefaultPointcutAdvisor(pointcut,getTxAdvice());

        return advisor;
    }


}
