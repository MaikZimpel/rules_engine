package biz.rightshift.core.rules;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by mzimpel on 17.09.13 for rightshift.biz
 *
 * A Factory to create Rules
 */
public class RuleFactory {

    /**
     * Create a Rule using the fully qualified class name of the Rule. This could be an entry in the Database identifying
     * the concrete Rule class.
     *
     * @param className The fully qualified class name as a String
     *
     * @return A new instance of the specified Rule
     *
     * @throws ClassNotFoundException if the Rule can not be found by the class loader (i.e. The Rule doesn't exist)
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Rule create(String className, RuleParameter ... parameters) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<? extends Rule> tClass = (Class<? extends Rule>) Class.forName(className);
        return create(tClass, parameters);
    }

    public static Rule create(Class<? extends Rule> clazz,RuleParameter ... parameters) throws IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Rule rule = clazz.newInstance();
        for(RuleParameter parameter : parameters){
            Field f = rule.getClass().getDeclaredField(parameter.name);
            f.setAccessible(true);
            f.set(rule,parameter.value);
        }

        return rule;
    }

}
