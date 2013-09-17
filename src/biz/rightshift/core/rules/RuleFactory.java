package biz.rightshift.core.rules;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by mzimpel on 17.09.13 for rightshift.biz
 *
 * A Factory to create Rules
 */
public class RuleFactory {

    /**
     * Create a Rule using the fully qualified class name of the Rule. This could be an entry in the Database identifying
     * the concrete Rule class. The Rule class must have a default (no parameter) constructor.
     *
     * @param className The fully qualified class name as a String
     *
     * @return A new instance of the specified Rule
     *
     * @throws ClassNotFoundException if the Rule can not be found by the class loader (i.e. The Rule doesn't exist)
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Rule create(String className, String[] params) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<? extends Rule> tClass = (Class<? extends Rule>) Class.forName(className);
        return create(tClass, params);
    }

    public static Rule create(Class<? extends Rule> clazz, String[] params) throws IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        // we know that AbstractRule has a constructor that expects a string as an argument
        Constructor<Rule> c = (Constructor<Rule>) clazz.getConstructor(String[].class);
        return c.newInstance(params);
    }

}
