package com.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 正则表达式学习
 */
public class PatternMain {
    public static void main(String args[]) {
        test1();
    }

    private static void test1() {
        List<PatternBean> list = new ArrayList<>();


        list.add(new PatternBean(new String[]{"food", "zood", "zfood", "fzood"}, "(z|f)ood", "x|y 匹配 x 或 y。例如，'z|food' 匹配\"z\"或\"food\"。'(z|f)ood' 匹配\"zood\"或\"food\"。"));
        list.add(new PatternBean(new String[]{"b", "o", " ", "", "doood"}, ".", ". 匹配除\"\\r\\n\"之外的任何单个字符。若要匹配包括\"\\r\\n\"在内的任意字符，请使用诸如\"[\\s\\S]\"之类的模式。"));
        list.add(new PatternBean(new String[]{"do", "does", "doeses", "adoes", "ddo"}, "do(es)?", " ? 零次或一次匹配前面的字符或子表达式"));
        list.add(new PatternBean(new String[]{"z", "zo", "zoo", "zoooo", "zooob"}, "zo+", " + 一次或多次匹配前面的字符或子表达式"));
        list.add(new PatternBean(new String[]{"z", "zo", "zoo", "zoooo", "zooob"}, "zo*", " * 零次或多次匹配前面的字符或子表达式"));
        list.add(new PatternBean(new String[]{"a", "b", "c", "ab", "d"}, "[abc]", "[xyz] 字符集。匹配包含的任一字符。例如，\"[abc]\"匹配\"plain\"中的\"a\""));
        list.add(new PatternBean(new String[]{"a", "b", "c", "ab", "d", "64"}, "[^abc]", "[^xyz] 反向字符集。匹配未包含的任何字符"));
        list.add(new PatternBean(new String[]{"a", "b", "c", "d", "6", "9"}, "[a-z]", "[a-z] 字符范围。匹配指定范围内的任何字符。例如，\"[a-z]\"匹配\"a\"到\"z\"范围内的任何小写字母。"));
        list.add(new PatternBean(new String[]{"a", "b", "c", "d", "6", "9"}, "[^a-z]", "[^a-z] 反向范围字符。匹配不在指定的范围内的任何字符"));
        list.add(new PatternBean(new String[]{"1", "2", "32", "B"}, "\\d", "\\d 数字字符匹配。等效于 [0-9]。"));
        list.add(new PatternBean(new String[]{"1", "2", "32", "B"}, "\\D", "\\D 非数字字符匹配。等效于 [^0-9]。"));
        list.add(new PatternBean(new String[]{"er", "never", "verb", "54"}, "[a-z]*er\\b", "\\b 匹配一个字边界，即字与空格间的位置。例如，\"er\\b\"匹配\"never\"中的\"er\"，但不匹配\"verb\"中的\"er\""));
        list.add(new PatternBean(new String[]{"er", "never", "verb", "54"}, ".*er\\B.*", "\\B 非字边界匹配。\"er\\B\"匹配\"verb\"中的\"er\"，但不匹配\"never\"中的\"er\"。"));


        for (PatternBean patternBean : list) {
            patternBean.pattern();
        }
    }


}
