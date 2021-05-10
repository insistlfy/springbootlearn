package com.my.lfy.api.ruleexecute;

/**
 * BaseRule
 * 规则抽象
 *
 * @author lfy
 * @date 2021/4/21
 **/
public interface BaseRule {

    /**
     * execute
     *
     * @param dto RuleDto
     * @return boolean
     */
    boolean execute(RuleDto dto);
}
