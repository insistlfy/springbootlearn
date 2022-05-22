package com.my.lfy.api.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.*;

/**
 * @FileName: TestSerializable
 * @Description: TODO
 * @Author: Lify
 * @CreateDate: 2022/5/22 18:23
 **/
@Data
@Builder
public class TestSerializable implements Serializable {

    private static final long serialVersionUID = 7282063998740508314L;

    private static Integer COUNT = 10;
    private Integer height;
    private String name;
    //transient 控制变量的需量化，修饰后，可以组织该变量序列化到文件中，反序列化后，为其默认值
    private transient String sex;

    // 解决@Build冲突
    @Tolerate
    public TestSerializable() {
    }

    public static void main(String[] args) throws Exception {

        TestSerializable build = TestSerializable.builder()
                .name("张三")
                .sex("男")
                .build();

        String file = "D:/tmp/data.d";
        writeData(build, file);
        System.out.println(JSON.toJSONString(readData(file), SerializerFeature.WriteMapNullValue));


    }


    /**
     * 将obj对象写入文件
     *
     * @param obj  Object
     * @param file Object
     * @throws Exception e
     */
    public static void writeData(Object obj, String file) throws Exception {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(obj);
        os.close();
        System.out.println("java 对象存储完成");
    }

    /**
     * 从文件中读取对象
     *
     * @return Object
     * @throws Exception e
     */
    public static Object readData(String file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream oi = new ObjectInputStream(fis);
        Object obj = oi.readObject();
        oi.close();
        System.out.println("java对象读取完成");
        return obj;
    }
}
