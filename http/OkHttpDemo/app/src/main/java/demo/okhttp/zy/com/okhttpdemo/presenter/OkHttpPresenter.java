package demo.okhttp.zy.com.okhttpdemo.presenter;

import java.lang.ref.SoftReference;

import demo.okhttp.zy.com.okhttpdemo.view.IOkhttpView;

public class OkHttpPresenter {

    private SoftReference<IOkhttpView> softReference;

    public OkHttpPresenter(IOkhttpView iOkhttpView) {
        this.softReference = new SoftReference<>(iOkhttpView);
    }
}
