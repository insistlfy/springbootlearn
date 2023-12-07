package com.my.lfy.api.spi.dubbo;

import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @FileName: DubboSPITest
 * @Description: TODO
 * @Author: lfy
 * @CreateDate: 2023/12/7 17:10
 * @Version: 1.0
 **/
public class DubboSPITest {
    public static void main(String[] args) {
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
    }
}
