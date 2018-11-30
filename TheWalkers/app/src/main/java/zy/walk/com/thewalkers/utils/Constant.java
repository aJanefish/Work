package zy.walk.com.thewalkers.utils;

import java.util.ArrayList;
import java.util.List;

import zy.walk.com.thewalkers.event.MainEvent;

public class Constant {
    public static final List<MainEvent> getMainEvent(){

        List<MainEvent> list = new ArrayList<>();
        list.add(createMainEvent("Other","用来做一些小测试之类页面","zy.walk.com.thewalkers","zy.walk.com.thewalkers.OtherActivity"));
        list.add(createMainEvent("Test","用来做一些小测试之类页面","zy.walk.com.thewalkers","zy.walk.com.thewalkers.TestActivity"));
        list.add(createMainEvent("Permission","了解android M 后动态申请权限的方法","zy.walk.com.thewalkers","zy.walk.com.thewalkers.PermissionActivity"));
        list.add(createMainEvent("Toast","吐司功能小集合","zy.walk.com.thewalkers","zy.walk.com.thewalkers.ToastActivity"));
        list.add(createMainEvent("Animator","一直想学习动画,没有时间精力,可自从发现了:https://github.com/daimajia/AndroidViewAnimations","zy.walk.com.thewalkers","zy.walk.com.thewalkers.AnimationsActivity"));


        list.add(createMainEvent("RxJava","RxJava(RxJava)","zy.walk.com.thewalkers","zy.walk.com.thewalkers.RxJavaActivity"));
        list.add(createMainEvent("Activity生命周期","The life cycle","zy.walk.com.thewalkers","zy.walk.com.thewalkers.AuxiliaryInfoShowActivityaa"));
        list.add(createMainEvent("PreferenceFragment","设置常用","zy.walk.com.thewalkers","zy.walk.com.thewalkers.PreferenceActivity"));


        list.add(createMainEvent("辅助信息展示","Singou(singou)","zy.walk.com.thewalkers","zy.walk.com.thewalkers.AuxiliaryInfoShowActivity"));


        list.add(createMainEvent("废柴","行走","zy.walk.com.thewalkers","zy.walk.com.thewalkers.MainActivity1"));


        list.add(createMainEvent("废柴","行走","zy.walk.com.thewalkers","zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("废柴","行走","zy.walk.com.thewalkers","zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("废柴","行走","zy.walk.com.thewalkers","zy.walk.com.thewalkers.MainActivity1"));

        return list;
    }

    private static MainEvent createMainEvent(String title, String content, String packName, String className){
        return new MainEvent.Builder()
                .title(title)
                .content(content)
                .className(className)
                .packageName(packName)
                .bulde();
    }
}
