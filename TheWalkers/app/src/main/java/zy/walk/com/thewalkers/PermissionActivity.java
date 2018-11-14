package zy.walk.com.thewalkers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
/**
 * Android M对权限管理系统进行了改版，之前我们的App需要权限，只需在manifest中申明即可，用户安装后，一切申明的权限都可来去自如的使用。
 * 但是Android M把权限管理做了加强处理，在manifest申明了，在使用到相关功能时，还需重新授权方可使用。
 * 当然，不是所有权限都需重新授权，所以就把这些需要重新授权方可使用的权限称之为运行时权限
 *
 *
 *
 * */

public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }
}
