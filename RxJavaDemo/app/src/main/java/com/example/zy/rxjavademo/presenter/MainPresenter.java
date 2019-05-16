package com.example.zy.rxjavademo.presenter;


import android.util.Log;

import com.example.zy.rxjavademo.model.MainCallback;
import com.example.zy.rxjavademo.model.MainModel;
import com.example.zy.rxjavademo.view.IMainView;

import java.lang.ref.SoftReference;

public class MainPresenter {

    // View接口
    private SoftReference<IMainView> reference;

    public MainPresenter(IMainView view) {
        this.reference = new SoftReference<>(view);
        Log.d("zhangyu", "" + view);
    }

    /**
     * 获取网络数据
     *
     * @param params 参数
     */
    public void getData(String params) {
        //显示正在加载进度条
        IMainView iMainView = reference.get();
        if (iMainView != null) {
            iMainView.showLoading();
        }

        // 调用Model请求数据
        MainModel.getNetData(params, new MainCallback() {
            @Override
            public void onSuccess(String data) {
                //调用view接口显示数据
                IMainView iMainView = reference.get();
                if (iMainView != null) {
                    iMainView.showData(data);
                }
            }

            @Override
            public void onFailure(String msg) {
                //调用view接口提示失败信息
                IMainView iMainView = reference.get();
                if (iMainView != null) {
                    iMainView.showFailureMessage(msg);
                }
            }

            @Override
            public void onError() {
                //调用view接口提示请求异常
                IMainView iMainView = reference.get();
                if (iMainView != null) {
                    iMainView.showErrorMessage();
                }
            }

            @Override
            public void onComplete() {
                // 隐藏正在加载进度条
                IMainView iMainView = reference.get();
                if (iMainView != null) {
                    iMainView.hideLoading();
                }
            }
        });
    }
}
