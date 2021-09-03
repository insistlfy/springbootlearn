package com.my.lfy.api.mybatisplus.service;

import com.my.lfy.api.mybatisplus.entity.MybatisTest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * test 服务类
 * </p>
 *
 * @author Lify
 * @since 2021-09-02
 */
public interface IMybatisTestService extends IService<MybatisTest> {

    /**
     * getALl
     *
     * @return List
     */
    List<MybatisTest> getAll();

    /**
     * deleteById
     *
     * @param id int
     */
    void deleteById(int id);
}
