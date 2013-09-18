package biz.rightshift.core.rules;

/**
 * Created by  maik on 2013/09/18 12:54 PM
 * for rightshift.biz
 */
public class RuleParameter {

    String key;
    Object value;

    private RuleParameter(String key, Object value){
        this.key = key;
        this.value = value;
    }

    public static RuleParameter create(String key,Object value){
        return new RuleParameter(key,value);
    }

}
