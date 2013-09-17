package biz.rightshift.core.rules.atomic;

/**
 * Created with IntelliJ IDEA.
 * User: mzimpel
 * Date: 17.09.13
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public class Eq extends AbstractRule {

    private Object o;
    private Object o1;

    public Eq(Object o, Object o1) {
        atomic = true;
        this.o = o;
        this.o1 = o1;
    }

    @Override
    protected boolean check() {
        return o.equals(o1);
    }


}
