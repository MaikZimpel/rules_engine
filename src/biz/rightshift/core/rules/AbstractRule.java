package biz.rightshift.core.rules;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mzimpel
 * Date: 16.09.13
 * Time: 23:24
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractRule implements Rule{

    private String name;

    private List<Rule> andArr = new ArrayList<Rule>();

    private List<Rule> orArr = new ArrayList<Rule>();

    public AbstractRule(String name){
        this.name = name;
    }

    public Rule and(Rule... r){
        for(Rule rl :r) andArr.add(rl);
        return this;
    }

    public Rule or(Rule... r){
        for(Rule rl :r) orArr.add(rl);
        return this;
    }

    public boolean validate(){
        boolean ret = check();
        for(Rule r1 : andArr) ret &= r1.validate();
        for(Rule r2 : orArr) ret |= r2.validate();
        return ret;
    }

    protected abstract boolean check();


}
