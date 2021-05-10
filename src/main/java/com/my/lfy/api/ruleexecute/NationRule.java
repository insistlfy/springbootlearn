package com.my.lfy.api.ruleexecute;

/**
 * NationRule
 * 例子2
 *
 * @author lfy
 * @date 2021/4/21
 **/
public class NationRule extends AbstractRule {

    @Override
    protected <T> T convert(RuleDto dto) {

        NationRuleDto nationRuleDto = new NationRuleDto();
        if (dto.getAddress().startsWith(RuleConstant.MATCH_ADDRESS_START)) {
            nationRuleDto.setNation(RuleConstant.MATCH_NATIONALITY_START);
        }
        return (T) dto;
    }

    @Override
    protected <T> boolean executeRule(T t) {
        System.out.println("NationRule invoke!");
        NationRuleDto nationRuleDto = (NationRuleDto) t;
        if (nationRuleDto.getNation().startsWith(RuleConstant.MATCH_NATIONALITY_START)) {
            return true;
        }
        return false;
    }
}
