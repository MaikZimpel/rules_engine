package biz.rightshift.core.rules.atomic;

/**
 * Created with IntelliJ IDEA.
 * User: mzimpel
 * Date: 17.09.13
 * Time: 21:39
 * To change this template use File | Settings | File Templates.
 */
public class Lt extends AbstractRule {

    private Comparable max;
    private Comparable act;

    Lt(Comparable max, Comparable act){
        this.max = max;
        this.act = act;
        atomic = true;
    }

    @Override
    protected boolean check() {
        return max.compareTo(act) == 1;
    }

}
