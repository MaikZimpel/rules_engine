package biz.rightshift.core.rules.test;

import biz.rightshift.core.rules.AbstractRule;
import biz.rightshift.core.rules.Rule;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static biz.rightshift.core.rules.RuleFactory.create;

/**
 * Created by mzimpel on 17.09.13 for rightshift.biz
 */
public class RulesEngineTest {

    @Test
    public void newRuleTest(){

        Rule r1 = new AbstractRule("MyRule") {
            @Override
            public boolean check() {
                return true;
            }
        }
                .and(new AbstractRule("MyNewRule") {
                    @Override
                    public boolean check() {
                        return false;
                    }
                }, new AbstractRule("MySecondNewRule") {
                         @Override
                         public boolean check() {
                             return false;
                         }
                     }
                )
                .or(new AbstractRule("MyThirdRule") {
                    @Override
                    public boolean check() {
                        return true;
                    }
                }
                );


        assert r1.validate() : true;

    }

    class TrueRule extends AbstractRule {

        TrueRule(){
            super("Always True");
        }

        @Override
        protected boolean check() {
            return true;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    class FalseRule extends AbstractRule {
        FalseRule() {
            super("Always False");
        }

        @Override
        protected boolean check() {
            return false;
        }

    }

    @Test
    public void trueAndTrueAndTrue(){

        assertFalse(new TrueRule()
                .and(new TrueRule(),new TrueRule())
                .and(new FalseRule())
                .validate()
                );

    }

    @Test
    public void complex(){
        System.out.println(
                new FalseRule()
                        .and(new FalseRule())
                        .and(new FalseRule().or(new TrueRule()), new FalseRule())
                        .or(new TrueRule().and(new FalseRule().not()))
                        .validate()
        );
    }

    @Test
    public void ruleFactory(){

        try {
            assertTrue(create(TrueRule.class,new String[]{"minLevel"}).validate());
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void sundayNightThreeTimes(){
        Rule sundayNight = new AbstractRule("Sunday Night Rule") {
            @Override
            protected boolean check() {
                // Implement Sunday Night Rule
                return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
            }
        };

        Rule loginThreeTimes = new AbstractRule("Login Rule") {

            class User {
                int logins = 3;
            }

            @Override
            protected boolean check() {
                User u = new User();
                return u.logins >= 3;
            }
        };

        assertTrue(sundayNight.and(loginThreeTimes).validate());
    }

    @Test
    public void negate(){
        assertFalse(new TrueRule().not().validate());
    }

    @Test
    public void simpleRule(){

    }

}
