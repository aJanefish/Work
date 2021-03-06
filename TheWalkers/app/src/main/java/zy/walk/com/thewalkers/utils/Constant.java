package zy.walk.com.thewalkers.utils;

import java.util.ArrayList;
import java.util.List;

import zy.walk.com.thewalkers.imagesandanimations.event.MainEvent;

public class Constant {

    public static final String LOG = "TheWalkers";

    public static final String CREATE_VIEW_LOG = "draw_view";


    public static final List<MainEvent> getMainEvent() {

        List<MainEvent> list = new ArrayList<>();
        list.add(createMainEvent("Other", "用来做一些小测试之类页面", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.OtherActivity"));
        list.add(createMainEvent("Test", "用来做一些小测试之类页面", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.TestActivity"));
        list.add(createMainEvent("Permission", "了解android M 后动态申请权限的方法", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.PermissionActivity"));
        list.add(createMainEvent("Toast", "吐司功能小集合", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.ToastActivity"));
        list.add(createMainEvent("Android Server", "android 服务总结", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.AndroidServerMainActivity"));

        list.add(createMainEvent("Animator", "Android 动画", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.AnimationsActivity"));


        list.add(createMainEvent("RxJava", "RxJava(RxJava)", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.RxJavaActivity"));
        list.add(createMainEvent("Activity生命周期", "The life cycle", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.AuxiliaryInfoShowActivityaa"));
        list.add(createMainEvent("dispatchTouchEvent", "View 事件传递机制", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.EventTransferActivity"));

        list.add(createMainEvent("PreferenceFragment", "设置常用", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.PreferenceActivity"));


        list.add(createMainEvent("辅助信息展示", "Singou(singou)", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.AuxiliaryInfoShowActivity"));
        list.add(createMainEvent("DIY VIEW", "android view", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.DiyViewActivity"));
        list.add(createMainEvent("Google translate", "谷歌翻译", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.GoogleTranslateActivity"));
        list.add(createMainEvent("Adapter", "adapter框架", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.AdapterMainActivity"));

        list.add(createMainEvent("Android Date 集合", "android集合", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.AndroidDateActivity"));

        //ContentResolver
        list.add(createMainEvent("ContentResolver", "ContentResolverDemo", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.ContentResolverDemoActivity"));
        list.add(createMainEvent("MediaPlayerD", "MediaPlayerDemo", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.MediaplayerDemoActivity"));

        list.add(createMainEvent("Dagger2", "Dagger2 依赖注入", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.DaggerActivity"));
        list.add(createMainEvent("JVM", "JVM", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.JVMActivity"));
        list.add(createMainEvent("Hot Fix", "热修复", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.HotFixActivity"));



        list.add(createMainEvent("废柴", "行走", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("废柴", "行走", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("废柴", "行走", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("废柴", "行走", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.MainActivity1"));

        return list;
    }

    private static MainEvent createMainEvent(String title, String content, String packName, String className) {
        return new MainEvent.Builder()
                .title(title)
                .content(content)
                .className(className)
                .packageName(packName)
                .bulde();
    }

    public static List<MainEvent> getDiyViewEvent() {
        List<MainEvent> list = new ArrayList<>();

        list.add(createMainEvent("DIY VIEW", "Canvas view", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.ViewActivity"));
        list.add(createMainEvent("Bitmap Demo", "Bitmap学习(构造和生成)", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.BitmapDemoActivity"));

        list.add(createMainEvent("Bitmap VIEW", "Android粒子篇之Bitmap像素级操作", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.BitmapActivity"));
        list.add(createMainEvent("Environment", "Environment详情", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.EnvironmentActivity"));
        list.add(createMainEvent("Diy Layout", "Diy Layout", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.DiyLayoutActivity"));

        list.add(createMainEvent("Diy Launcher", "Drag ViewGroup", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.DiyDragActivity"));


        list.add(createMainEvent("Face", "diy face", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.FaceActivity"));
        list.add(createMainEvent("GPU", "渲染优化", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.GPUActivity"));
        list.add(createMainEvent("View 绘制流程", "View 绘制流程", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.ViewCreateActivity"));
        list.add(createMainEvent("View", "View 基本用法", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.ViewBaseActivity"));



        list.add(createMainEvent("废柴", "行走", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.MainActivity1"));

        list.add(createMainEvent("废柴", "行走", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("废柴", "行走", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("废柴", "行走", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.MainActivity1"));

        return list;
    }


    public static List<MainEvent> getAdapterEvent() {
        List<MainEvent> list = new ArrayList<>();

        list.add(createMainEvent("Adapter", "Adapter框架", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.AdapterActivity"));
        list.add(createMainEvent("ItemTouchHelper", "ItemTouchHelper源码分析 实现拖动 ", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.ItemTouchHelperActivity"));
        list.add(createMainEvent("ListView", "ListView中图片错位怎么产生的", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.ListViewDemoActivity"));
        list.add(createMainEvent("ListView", "ListView缓存机制", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.activity.ListViewCacheActivity"));




        list.add(createMainEvent("废柴", "行走", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.MainActivity1"));

        list.add(createMainEvent("废柴", "行走", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("废柴", "行走", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("废柴", "行走", "zy.walk.com.thewalkers", "zy.walk.com.thewalkers.MainActivity1"));

        return list;
    }
}
