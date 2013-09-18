package biz.rightshift.core.rules;

/**
 * Created with IntelliJ IDEA.
 * User: mzimpel
 * Date: 18.09.13
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class Root {

    private Rule root;

    public Root(Rule root){
        this.root = root;
    }

    public boolean evaluate(ParameterMap parameters){
        return root.evaluate(parameters);
    }

}
