package zy.walk.com.thewalkers.utils;

import com.github.houbb.opencc4j.util.ZhConverterUtil;

/**
 * 繁体字 简体字 切换
 *
 *     繁字体 简体字转换
 *     https://github.com/houbb/opencc4j
 *     implementation 'com.github.houbb:opencc4j:1.0.2'
 * */
public class FontUtils {

    //简体字 -> 繁体字
    public static String  convertToTraditional(String original){
        return ZhConverterUtil.convertToTraditional(original);
    }

    //繁体字 -> 简体字
    public static String  convertToSimple(String original){
        return ZhConverterUtil.convertToSimple(original);
    }
}
