package demo.okhttp.zy.com.okhttpdemo.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import demo.okhttp.zy.com.okhttpdemo.R;
import demo.okhttp.zy.com.okhttpdemo.manager.FaceManager;

public class FaceDemoActivity extends AppCompatActivity {

    private FaceManager faceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_demo);
        initView();
        initDate();
    }

    private void initDate() {
        faceManager = new FaceManager(getApplicationContext());
    }

    private void initView() {
    }

    public void login(View view) {
        faceManager.login();
    }

    public void getAllSubjects(View view) {
        faceManager.getAllSubjects();
    }

    public void uploadSubjectPhoto(View view) {
        faceManager.uploadSubjectPhoto(-1);
    }
    private int userId = 11313;
    public void updateSubjectPhoto(View view) {
        faceManager.uploadSubjectPhoto(userId);
    }

    public void getSubjectById(View view) {
        faceManager.getSubjectById(userId);
    }

    public void deleteUser(View view) {
        faceManager.deleteUser(userId);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createSubject(View view) {
        faceManager.createSubject();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void updateSubject(View view) {
        faceManager.upDateSubject(userId);

    }
}
