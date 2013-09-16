package biz.rightshift.core.rules;

/**
 * Created with IntelliJ IDEA.
 * User: mzimpel
 * Date: 17.09.13
 * Time: 00:45
 * To change this template use File | Settings | File Templates.
 */
public class RuleFactory {

    public static Rule create(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (Rule) Class.forName(className).newInstance();
    }

    public static Rule create(Class<Rule> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

}
