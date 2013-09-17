package biz.rightshift.core.rules.examples;

import biz.rightshift.core.rules.atomic.AbstractRule;

import java.util.Calendar;

/**
 * Created by  maik on 2013/09/17 3:46 PM
 * for rightshift.biz
 */

/**
 * The Rule evaluates to true if the playerLevel is greater then minLevel and less then maxLevel or today is dayOfWeek
 */
public class ComplexMinMaxDayOfWeekRule extends AbstractRule{

    private Integer minLevel;
    private Integer maxLevel;
    private Integer playerLevel;
    private int dayOfWeek;

    @Override
    protected boolean check() {
        return this
                .and(gt(minLevel, playerLevel),lt(maxLevel, playerLevel))
                .or(eq(dayOfWeek, Calendar.getInstance().get(Calendar.DAY_OF_WEEK)))
                .validate();
    }

}
