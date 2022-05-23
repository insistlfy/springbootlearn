package com.my.lfy.api.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;

import java.io.*;

/**
 * @FileName: TestSerializable
 * @Description: ①：父类序列化，子类自动序列化，子类无需显示序列化
 * ②：父类序列化，子类自动序列化，且父类变量也会被序列化
 * ③：只有子类序列化，父类变量值不保存
 * ④：transient 控制变量的需量化，修饰后，可以组织该变量序列化到文件中，反序列化后，为其默认值
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

        // 测试需在本地路径下创建该文件
        File file = new File("D:/tmp/data.d");
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("文件创建成功...");
        }
        writeData(build, file.getPath());
        System.out.println(JSON.toJSONString(readData(file.getPath()), SerializerFeature.WriteMapNullValue));
        System.out.println("====================================================================");

        // 父类序列化，子类自动序列化，子类无需显示序列化
        Parent1 parent1 = Parent1.builder()
                .build();
        Children1 children1 = Children1.builder()
                .childrenNode1("childrenNode1")
                .parentNode1("parentNode1")
                .build();
        writeData(children1, file.getPath());
        System.out.println(JSON.toJSONString(readData(file.getPath()), SerializerFeature.WriteMapNullValue));
        if (parent1 instanceof Serializable) {
            System.out.println("parent1 is Serializable");
        }
        if (children1 instanceof Serializable) {
            System.out.println("children1 is Serializable");
        }
        System.out.println("====================================================================");
        Parent2 parent2 = Parent2.builder()
                .build();

        Children2 children2 = Children2.builder()
                .childrenNode2("childrenNode2")
                .parentNode2("parentNode2")
                .build();
        writeData(children2, file.getPath());
        System.out.println(JSON.toJSONString(readData(file.getPath()), SerializerFeature.WriteMapNullValue));
        if (parent2 instanceof Serializable) {
            System.out.println("parent2 is Serializable");
        }
        if (children2 instanceof Serializable) {
            System.out.println("children2 is Serializable");
        }
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
        System.out.println("java对象存储完成...");
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
        System.out.println("java对象读取完成...");
        return obj;
    }
}

@Data
@SuperBuilder
class Parent1 implements Serializable {

    private static final long serialVersionUID = 1246366139306183098L;

    private String parentNode1;

    @Tolerate
    public Parent1() {
    }
}

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
class Children1 extends Parent1 {

    private String childrenNode1;

    @Tolerate
    public Children1() {

    }
}

@Data
@SuperBuilder
class Parent2 {
    private String parentNode2;

    @Tolerate
    public Parent2() {

    }
}

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
class Children2 extends Parent2 implements Serializable {

    private static final long serialVersionUID = 4583713551836549039L;

    private String childrenNode2;

    @Tolerate
    public Children2() {

    }
}