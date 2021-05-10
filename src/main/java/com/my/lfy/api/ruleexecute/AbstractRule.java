package com.my.lfy.api.ruleexecute;

/**
 * AbstractRule
 * 规则模板
 *
 * @author lfy
 * @date 2021/4/21
 **/
public class AbstractRule implements BaseRule {

    @Override
    public boolean execute(RuleDto dto) {
        return executeRule(convert(dto));
    }

    protected <T> T convert(RuleDto dto) {
        return (T) dto;
    }

    protected <T> boolean executeRule(T t) {
        return true;
    }

}
