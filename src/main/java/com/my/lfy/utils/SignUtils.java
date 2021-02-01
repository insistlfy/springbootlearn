package com.my.lfy.utils;

import com.my.lfy.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SignUtils
 *
 * @author lfy
 * @date 2021/1/18
 **/
@Slf4j
public final class SignUtils {

    /**
     * 应用秘钥
     */
    private static final String SECRET_KEY = "cqkj";

    /**
     * 加签
     *
     * @param t 参数内容
     */
    public static <T> String sign(T t) {
        Optional.ofNullable(t).orElseThrow(() -> new ServiceException("签名参数为空"));

        Map<String, Object> map = convertBean(t);
        List<String> keyList = map.keySet().stream().sorted().collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();

        keyList.forEach(e -> {
            if (null != map.get(e)) {
                builder.append(e).append("=").append(map.get(e)).append("&");
            }
        });
        builder.append("secret=").append(SECRET_KEY);
        return DigestUtils.md5DigestAsHex(builder.toString().toUpperCase().getBytes());
    }

    /**
     * 验签
     *
     * @param t    T
     * @param sign 签名
     * @return Boolean
     */
    public static <T> Boolean verifySign(T t, String sign) {
        String verifySign = sign(t);
        if (!verifySign.equals(sign)) {
            log.error("验签失败,请传入正确的签名");
            return false;
        }
        return true;
    }

    /**
     * @param t 要转化的JavaBean 对象
     * @return 转化出来的 Map 对象
     */
    private static <T> Map<String, Object> convertBean(T t) {

        if (t instanceof Map) {
            return (Map<String, Object>) t;
        }

        Map<String, Object> returnMap = new HashMap<>(2);
        try {
            Class type = t.getClass();
            returnMap = new HashMap<>(1);
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo
                    .getPropertyDescriptors();
            for (PropertyDescriptor descriptor : propertyDescriptors) {
                String propertyName = descriptor.getName();
                if (!"class".equals(propertyName)) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(t);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    }
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
            log.error("convertBean failed...", e);
        }
        return returnMap;
    }

    public static void main(String[] args) {

        Map<String, Object> params = new HashMap<>();
        params.put("userName", "Rose");
        params.put("sex", "F");

        String sign = sign(params);
        System.out.println(sign);
        System.out.println(verifySign(params, sign));
        System.out.println("======================================");


        Map<String, Object> extend = new HashMap<>(4);
        extend.put("goodAt", "basketball");
        extend.put("nation", "America");
        User user = User.builder()
                .userName("Rose")
                .sex("女")
                .age(18)
                .extend(extend)
                .build();

        String newSign = sign(params);
        System.out.println(newSign);
        System.out.println(verifySign(params, newSign));

    }
}
