package biz.rightshift.core.rules.test;

import biz.rightshift.core.rules.AbstractRule;
import biz.rightshift.core.rules.Rule;
import org.junit.Test;

import static com.sun.xml.internal.bind.v2.ClassFactory.create;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: mzimpel
 * Date: 16.09.13
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
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
                        .or(new TrueRule().and(new FalseRule()))
                        .validate()
        );
    }

    @Test
    public void ruleFactory(){
        assertTrue(create(TrueRule.class).validate());
    }

}
