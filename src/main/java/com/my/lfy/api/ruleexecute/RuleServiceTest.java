package com.my.lfy.api.ruleexecute;

import java.util.Arrays;

/**
 * RuleServiceTest
 *
 * @author lfy
 * @date 2021/4/21
 **/
public class RuleServiceTest {

    public static void main(String[] args) {
        //1. 定义规则  init rule

        NationRule nationRule = new NationRule();
        AddressRule addressRule = new AddressRule();

        //2. 构造需要的数据 create dto
        RuleDto dto = new RuleDto();
        dto.setAge(5);
        dto.setAddress("北京");


        boolean ruleResult = RuleService.create()
                .and(Arrays.asList(nationRule, addressRule))
                .or(Arrays.asList(nationRule))
                .execute(dto);

        System.out.println(ruleResult);
    }
}
