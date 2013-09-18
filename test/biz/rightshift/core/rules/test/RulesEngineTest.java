package biz.rightshift.core.rules.test;

import biz.rightshift.core.rules.*;
import biz.rightshift.core.rules.examples.SimpleDayOfWeekRule;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static biz.rightshift.core.rules.ParameterMap.build;
import static biz.rightshift.core.rules.RuleParameter.create;
import static java.util.Calendar.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mzimpel on 17.09.13 for rightshift.biz
 */
public class RulesEngineTest {

    @Test
    public void simpleMinLevel(){

        try {
            String name = "biz.rightshift.core.rules.examples.SimpleMinLevelRule";
            Root r = RuleFactory.create(name, create("minLevel",1000));

            assertTrue(r.evaluate(build(create("level", 1001))));

        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void sundayNightThreeTimes(){
        Rule sundayNight = new SimpleDayOfWeekRule().build(build(create("dayOfWeek", getInstance().get(SUNDAY))));
        Rule loginThreeTimes = new AbstractRule() {

            private Integer minLoginNumber;


            @Override
            public boolean eval(ParameterMap parameters) {
                return minLoginNumber <= (Integer) parameters.get("loginCount");
            }

            @Override
            public Rule build(ParameterMap parameters) {
                minLoginNumber = (Integer) parameters.get("minLoginNumber");
                return this;  //To change body of implemented methods use File | Settings | File Templates.
            }


        };

        loginThreeTimes.build(build(create("minLoginNumber",1)));

        assertTrue(sundayNight
                .and(loginThreeTimes)
                .evaluate(build(
                    create("dayOfWeek", getInstance().get(SUNDAY)),
                    create("loginCount", 1)
                )));
    }



    @Test
    public void complexRule(){

        try {
            String name = "biz.rightshift.core.rules.examples.ComplexMinMaxDayOfWeekRule";
            Root r = RuleFactory.create(name,
                    create("minLevel",1000),
                    create("maxLevel",1500),
                    create("dayOfWeek",getInstance().get(MONDAY))
            );
            assertFalse(r.evaluate(build(
                    create("level", 1001),
                    create("dayOfWeek", getInstance().get(TUESDAY))
            )));
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
