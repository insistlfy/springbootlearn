package com.my.lfy.exception;

/**
 * 异常信息定义
 * 分模块（微服务）定义错误信息
 * 公共模块　　：-1--10000000
 * 云网关     ：10010000-10020000
 * 云患者端服务：10020000-10030000
 * 云医生端服务：10030000-10040000
 * 云管理服务  ：10040000-10050000
 * 云药店服务  ：10050000-10060000
 * 云支付服务  ：10060000-10070000
 * 云咨询服务  ：10070000-10080000
 * 云问诊服务  ：10080000-10090000
 * 云内容服务  ：10090000-10100000
 * 云消息服务  ：10100000-10110000
 * 云患者管理  ：10110000-10120000
 * 云图片服务  ：10120000-10130000
 * 云数据同步服务：10130000-10140000
 * 云问诊服务：10140000-10150000
 * <p>
 * 本地网关          ：20010000--20020000
 * 本地HIS服务       ：20020000--20030000
 * 本地CIS服务       ：20030000--20040000
 * 本地管理服务       ：20040000--20050000
 * 本地支付服务       ：20050000--20060000
 * 本地号源服务       ：20060000--20070000
 * 本地检验打码叫号服务：20070000--20080000
 * 本地医疗衍生服务   ：20080000--20090000
 * 本地与云平台服务   ：20090000--20100000
 * 本地数据对接服务   ：20100000--20110000
 * 本地数据同步服务   ：20110000--20120000
 * 本地患者管理服务   ：20130000--20140000
 *
 * @author cyj
 * @date 19-03-13
 */
public interface IExceptionMsg {

    /**
     * 获取异常的状态码
     *
     * @return
     */
    Integer getCode();

    /**
     * 获取异常的提示信息
     *
     * @return
     */
    String getMessage();
}
