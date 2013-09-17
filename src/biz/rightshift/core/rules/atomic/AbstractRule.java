package biz.rightshift.core.rules.atomic;

import biz.rightshift.core.rules.Rule;
import biz.rightshift.core.rules.atomic.Gt;
import biz.rightshift.core.rules.atomic.Lt;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzimpel on 17.09.13 for rightshift.biz
 *
 *
 * Default abstract implementation of the {@link biz.rightshift.core.rules.Rule} Interface
 */
public abstract class AbstractRule implements Rule {


    private final List<Rule> andArr = new ArrayList<Rule>();

    private final List<Rule> orArr = new ArrayList<Rule>();

    private boolean negate;

    protected boolean atomic;


    /**
     *
     * @param r a Rule that is AND connected to this Rule
     * @return
     */
    @Override
    public Rule and(final Rule... r){
        if(atomic) throw new UnsupportedOperationException("The Rule is atomic and can not evaluate any further");
        for(Rule rl :r) andArr.add(rl);
        return this;
    }

    /**
     *
     * @param r a Rule that is OR connected to this Rule
     * @return
     */
    @Override
    public Rule or(final Rule... r){
        if(atomic) throw new UnsupportedOperationException("The Rule is atomic and can not evaluate any further");
        for(Rule rl :r) orArr.add(rl);
        return this;
    }

    /**
     * Tell the validation method to negate this Rule
     * @return this Rule with the negate flag set to true
     */
    @Override
    public Rule not() {
        negate = true;
        return this;
    }

    @Override
    public Rule gt(Comparable min, Comparable act) {
        return new Gt(min,act);
    }

    @Override
    public Rule lt(Comparable max, Comparable act) {
        return new Lt(max,act);
    }

    @Override
    public Rule eq(Object o, Object o1) {
        return new Eq(o,o1);
    }

    /**
     * Validate the current Rule
     *
     * @return <code>true</code> if the Rule and all connected Rules evaluate to true, <code>false</code> otherwise
     */
    public boolean validate(){

        boolean ret = check();

        if(!atomic){
            for(Rule r1 : andArr) ret &= r1.validate();
            for(Rule r2 : orArr) ret |= r2.validate();
        }
        if(negate)
            return !ret;
        else
            return ret;
    }


    /**
     * The check method is called within {@link #validate()} to evaluate the current concrete Rule
     * @return <code>true</code> if the Rule logic evaluates to true
     */
    protected abstract boolean check();


}
