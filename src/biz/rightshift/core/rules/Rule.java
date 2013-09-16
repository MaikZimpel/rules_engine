package biz.rightshift.core.rules;

/**
 * Created with IntelliJ IDEA.
 * User: mzimpel
 * Date: 17.09.13
 * Time: 00:41
 * To change this template use File | Settings | File Templates.
 */
public interface Rule {

    Rule and (Rule ... r);
    Rule or (Rule ... r);
    boolean validate();

}
