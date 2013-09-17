package biz.rightshift.core.rules;

/**
 * Created by mzimpel on 17.09.13 for rightshift.biz
 *
 *
 * Rule interface provides access to Rule DSL.
 *
 */
public interface Rule {

    /**
     * Connect a Rule with another Rule using an AND connective.
     *
     * @param r a Rule that is AND connected to this Rule
     * @return the current Rule
     */
    Rule and(Rule... r);

    /**
     * Connect a Rule with another Rule using an OR connective.
     *
     * @param r a Rule that is OR connected to this Rule
     * @return the current Rule
     */
    Rule or(Rule... r);

    /**
     * Negate the current Rule
     *
     * @return the current rule and tells the validation method to negate it
     */
    Rule not();

    /**
     * Greater then
     * @param property The name property of the property to be evaluated
     * @param value The value that needs to be compared
     * @param <T> Must be Comparable
     * @return
     */
    <T extends Comparable<T>> Rule gt(String property,T value);

    /**
     *
     * @param property
     * @param value
     * @param <T>
     * @return
     */
    <T extends Comparable<T>> Rule lt(String property, T value);

    /**
     * Equal to. Evaluates equality between a property of this Rule and the value by using the equals method of
     * value. <b>that method has to be overwritten in order to make this work for arbitrary objects</b>
     *
     * @param property
     * @param value
     * @return
     */
    Rule eq(String property, Object value);

    /**
     * Validate the current Rule
     *
     * @return <code>true</code> if the Rule and all connected Rules evaluate to true, <code>false</code> otherwise
     */
    boolean validate();

}
