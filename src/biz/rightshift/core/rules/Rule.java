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
     * Evaluate the current Rule
     *
     * @return <code>true</code> if the Rule and all connected Rules evaluate to true, <code>false</code> otherwise
     */
    boolean evaluate(ParameterMap parameters);

    Rule build(ParameterMap parameters);
}
