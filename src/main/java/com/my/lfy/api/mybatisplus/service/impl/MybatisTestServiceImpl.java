package com.my.lfy.api.mybatisplus.service.impl;

import com.my.lfy.api.mybatisplus.entity.MybatisTest;
import com.my.lfy.api.mybatisplus.mapper.MybatisTestMapper;
import com.my.lfy.api.mybatisplus.service.IMybatisTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * test 服务实现类
 * </p>
 *
 * @author Lify
 * @since 2021-09-02
 */
@Service
public class MybatisTestServiceImpl extends ServiceImpl<MybatisTestMapper, MybatisTest> implements IMybatisTestService {

    @Autowired
    private MybatisTestMapper mybatisTestMapper;

    @Override
    public List<MybatisTest> getAll() {
        List<Long> idList = new ArrayList<>();
        idList.add(4L);
        idList.add(5L);
        idList.add(6L);
        return mybatisTestMapper.selectBatchIds(idList);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        mybatisTestMapper.deleteById(id);
    }
}
