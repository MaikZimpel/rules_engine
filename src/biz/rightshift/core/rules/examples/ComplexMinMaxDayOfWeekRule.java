package biz.rightshift.core.rules.examples;

import biz.rightshift.core.rules.AbstractRule;
import biz.rightshift.core.rules.ParameterMap;
import biz.rightshift.core.rules.Rule;

/**
 * Created by  maik on 2013/09/17 3:46 PM
 * for rightshift.biz
 */

/**
 * The Rule evaluates to true if the playerLevel is greater then minLevel and less then maxLevel or today is dayOfWeek
 */
public class ComplexMinMaxDayOfWeekRule extends AbstractRule{

    Rule root;

    @Override
    public Rule build(final ParameterMap parameters) {
        root = new SimpleDayOfWeekRule().build(parameters)
                .and(new SimpleMaxLevelRule().build(parameters))
                .or(new SimpleDayOfWeekRule().build(parameters));
        return root;
    }

    @Override
    public boolean eval(final ParameterMap parameters) {
        return root.evaluate(parameters);
    }

    


}
