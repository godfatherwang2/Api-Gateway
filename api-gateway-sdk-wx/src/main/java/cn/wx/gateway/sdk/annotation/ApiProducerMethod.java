package cn.wx.gateway.sdk.annotation;
import java.lang.annotation.*;

/**
 * @author 小傅哥，微信：fustack
 * @description
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ApiProducerMethod {

    /** 方法名称 */
    String methodName() default "";

    /** 访问路径；/wg/activity/sayHi */
    String uri() default "";

    /** 接口类型；GET、POST、PUT、DELETE */
    String httpCommandType() default "GET";

    /** 是否认证；true = 1是、false = 0否 */
    int auth() default 0;

}
