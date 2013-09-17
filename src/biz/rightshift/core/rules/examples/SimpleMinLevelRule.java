package biz.rightshift.core.rules.examples;

import biz.rightshift.core.rules.atomic.AbstractRule;

/**
 * Created with IntelliJ IDEA.
 * User: mzimpel
 * Date: 17.09.13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class SimpleMinLevelRule extends AbstractRule {

    private Integer minLevel;
    private Integer actualLevel;

    @Override
    protected boolean check() {
        return or(gt(minLevel, actualLevel),eq(minLevel, actualLevel)).validate();
    }
}
