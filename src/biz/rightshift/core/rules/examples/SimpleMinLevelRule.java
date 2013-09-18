package biz.rightshift.core.rules.examples;

import biz.rightshift.core.rules.AbstractRule;
import biz.rightshift.core.rules.ParameterMap;
import biz.rightshift.core.rules.Rule;

/**
 * Created with IntelliJ IDEA.
 * User: mzimpel
 * Date: 17.09.13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class SimpleMinLevelRule extends AbstractRule {

    private Integer minLevel;

    @Override
    public Rule build(ParameterMap parameters) {
        minLevel = (Integer)parameters.get("minLevel");
        return this;
    }

    @Override
    public boolean eval(ParameterMap parameters) {
        return minLevel <= (Integer)parameters.get("level");
    }
}
