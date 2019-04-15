package zy.walk.com.thewalkers.jvm;

import android.util.Log;

import zy.walk.com.thewalkers.App;

public class JvmBean {

    private String id;
    private byte[] bytes;


    public JvmBean(String id) {
        this.id = id;
        Log.d(App.TAG,"JvmBean constructor "+id);
        bytes = new byte[1024*1024*10];
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d(App.TAG,"JvmBean finalize "+this);
    }

    @Override
    public String toString() {
        return "JvmBean{" +
                "id=" + id +
                '}';
    }
}
