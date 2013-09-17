package biz.rightshift.core.rules.atomic;

/**
 * Created with IntelliJ IDEA.
 * User: mzimpel
 * Date: 17.09.13
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
public class Gt extends AbstractRule {


    private Comparable min;
    private Comparable act;

    Gt(Comparable min, Comparable act) {
        atomic = true;
        this.min = min;
        this.act = act;
    }

    @Override
    protected boolean check() {
        return min.compareTo(act) == 1;
    }


}
