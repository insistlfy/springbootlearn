package com.my.lfy.api.spi.dubbo;

import org.apache.dubbo.common.extension.SPI;

/**
 * @FileName: Robot
 * @Description: TODO
 * @Author: lfy
 * @CreateDate: 2023/12/7 17:04
 * @Version: 1.0
 **/
@SPI
public interface Robot {

    void sayHello();
}
