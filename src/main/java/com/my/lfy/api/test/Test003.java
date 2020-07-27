package com.my.lfy.api.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Test003
 * describe : 已知两个对象的属性映射关系,按照映射关系进行赋值
 *
 * @author lfy
 * @date 2020/7/27
 **/
public class Test003 {

    public static void main(String[] args) {

        List<Mapping> mappingList = new ArrayList<>();
        Mapping mappingName = Mapping.builder()
                .key("test")
                .nameA("nameA")
                .nameB("nameB")
                .build();
        mappingList.add(mappingName);

        Mapping mappingWeight = Mapping.builder()
                .key("test")
                .weightA("weight_a")
                .weightB("weight_b")
                .build();
        mappingList.add(mappingWeight);


        ObjectA a = ObjectA.builder()
                .nameA("A")
                .build();

        String nameA1 = mappingList.stream()
                .filter(e -> "test".equals(e.getKey()) && "nameB".equals(e.getNameB()))
                .findFirst()
                .get()
                .getNameA();
        String fieldValueByFieldName = getFieldValueByFieldName(nameA1, a, String.class);
        ObjectB b = ObjectB.builder()
                .nameB(fieldValueByFieldName)
                .build();

        System.out.println(b);

    }

    public static <T> T getFieldValueByFieldName(String fieldName, Object object, Class<T> tClass) {

        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ObjectA implements Serializable {

    private static final long serialVersionUID = 273437938036314460L;

    private String nameA;

    private String weightA;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ObjectB implements Serializable {

    private static final long serialVersionUID = 2716985179607711327L;

    private String nameB;

    private String weightB;

}

@Data
@Builder
class Mapping implements Serializable {

    private static final long serialVersionUID = 2755235152418772939L;

    private String key;

    private String nameA;

    private String nameB;

    private String weightA;

    private String weightB;
}
