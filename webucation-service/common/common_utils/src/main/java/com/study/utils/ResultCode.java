package com.study.utils;

/**
 * @ProjectName: webucation-service
 * @ClassName: ResultCode
 * @author：yangyanbin
 * @Desc：统一返回状态码
 * @date 2021/4/17 17:45
 * @Version: 1.0
 * {
 *   "success": 布尔, //响应是否成功
 *   "code": 数字, //响应码
 *   "message": 字符串, //返回消息
 *   "data": HashMap //返回数据，放在键值对中
 * }
 */
public interface ResultCode {
    /**成功*/
    public static Integer SUCCESS = 20000;
    /**失败*/
    public static Integer ERROR = 20001;

}
