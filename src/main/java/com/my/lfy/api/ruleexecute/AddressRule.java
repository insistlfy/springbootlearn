package com.my.lfy.api.ruleexecute;

/**
 * AddressRule
 * 例子1
 *
 * @author lfy
 * @date 2021/4/21
 **/
public class AddressRule extends AbstractRule {

    @Override
    public boolean execute(RuleDto dto) {
        System.out.println("AddressRule invoke！");
        if (dto.getAddress().startsWith(RuleConstant.MATCH_ADDRESS_START)) {
            return true;
        }
        return false;
    }
}
