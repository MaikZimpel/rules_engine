package biz.rightshift.core.rules;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzimpel on 17.09.13 for rightshift.biz
 *
 *
 * Default abstract implementation of the {@link biz.rightshift.core.rules.Rule} Interface
 */
public abstract class AbstractRule implements Rule{


    private final List<Rule> andArr = new ArrayList<Rule>();

    private final List<Rule> orArr = new ArrayList<Rule>();

    private boolean negate;

    /**
     *
     * @param r a Rule that is AND connected to this Rule
     * @return
     */
    @Override
    public Rule and(final Rule... r){
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
    public <T extends Comparable<T>> Rule gt(String property, T value) {
        // find the property in question and set the value
        try {
            this.getClass().getDeclaredField(property).set(value.getClass(), value);
        } catch (IllegalAccessException e) {
            //TODO implement sensible Exception handling
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return this;
    }

    @Override
    public <T extends Comparable<T>> Rule lt(String property, T value) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Rule eq(String property, Object value) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Validate the current Rule
     *
     * @return <code>true</code> if the Rule and all connected Rules evaluate to true, <code>false</code> otherwise
     */
    public boolean validate(){
        boolean ret = check();
        for(Rule r1 : andArr) ret &= r1.validate();
        for(Rule r2 : orArr) ret |= r2.validate();
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
