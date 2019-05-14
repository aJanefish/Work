package zy.walk.com.thewalkers.activity;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;

import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.viewinjection.ViewField;
import zy.walk.com.thewalkers.viewinjection.ViewLayout;
import zy.walk.com.thewalkers.viewinjection.ViewMethod;
import zy.walk.com.thewalkers.viewinjection.ViewUtils;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.View;
import android.widget.TextView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Android 集合使用
 * SparseArray
 * SparseLongArray
 * SparseBooleanArray
 * SparseIntArray
 * <p>
 * 双数组维护 key-values 相对于Java的HashMap 可以节省资源
 * <p>
 * Android中不使用枚举类（enum）替代为@IntDef @StringDef
 */
@ViewLayout(R.layout.activity_data)
public class AndroidDateActivity extends AppCompatActivity {

    private String TAG = "AndroidDateActivity";
    @ViewField(R.id.activity_data_des)
    TextView des;

    private static final int MAN = 0;
    private static final int WOMAN = 1;

    private static final String OK = "ok";
    private static final String ERROR = "error";

    /**
     * IntDef 使用Demo
     */
    @IntDef({MAN, WOMAN})
    @Target({
            ElementType.PARAMETER,
            ElementType.FIELD,
            ElementType.METHOD,
    }) //表示注解作用范围，参数注解，成员注解，方法注解
    @Retention(RetentionPolicy.SOURCE)
    @interface Sex {
    }


    /**
     * StringDef Demo
     */
    @StringDef({
            OK,
            ERROR
    }) //限定为 FlagContants.OK, FlagContants.ERROR
    @Target({
            ElementType.PARAMETER,
            ElementType.FIELD,
            ElementType.METHOD,
    }) //表示注解作用范围，参数注解，成员注解，方法注解
    @Retention(RetentionPolicy.SOURCE) //表示注解所存活的时间,在运行时,而不会存在 .class 文件中
    public @interface FlagDef { //接口，定义新的注解类型
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_data);
        ViewUtils.register(this);


    }


    public void setFlag(@FlagDef String flag) {
        String flags = flag;
    }

    /**
     * 只能使用 MAN,WOMAN
     */
    private void setMan(@Sex int sex) {

    }

    @ViewMethod(R.id.activity_data_IntDef)
    public void int_def(View view) {
        setMan(MAN);
        setMan(WOMAN);
        //setMan(0); //將會報錯

    }

    @ViewMethod(R.id.activity_data_StringDef)
    public void string_def(View view) {
        //setFlag("ss"); // 將會報錯
        setFlag(OK);
        setFlag(ERROR);
    }


    @ViewMethod(R.id.activity_data_sparse_long_array)
    public void sparse_long_array(View view) {
        SparseLongArray sparseLongArray = new SparseLongArray();
        sparseLongArray.append(100, 100L);
        sparseLongArray.append(10, 10L);
        sparseLongArray.append(1, 1L);

        Log.d(TAG, "sparseLongArray:" + sparseLongArray);

    }

    @ViewMethod(R.id.activity_data_sparse_boolean_array)
    public void sparse_boolean_array(View view) {
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        sparseBooleanArray.append(100, true);
        sparseBooleanArray.append(10, false);
        sparseBooleanArray.append(1, true);
        Log.d(TAG, "sparseBooleanArray:" + sparseBooleanArray);

    }

    @ViewMethod(R.id.activity_data_sparse_int_array)
    public void sparse_int_array(View view) {
        SparseIntArray sparseIntArray = new SparseIntArray();

        sparseIntArray.append(110, 110);
        sparseIntArray.append(10, 10);
        sparseIntArray.append(1, 1);
        sparseIntArray.append(5, 5);
        sparseIntArray.append(9, 9);
        sparseIntArray.append(4, 4);
        sparseIntArray.append(7, 7);

        Log.d(TAG, "sparseIntArray:" + sparseIntArray);

    }


    @ViewMethod(R.id.activity_data_sparse_array)
    public void sparse_array(View view) {
        SparseArray<String> sparseArray = new SparseArray<String>();

        sparseArray.append(110, "A" + 110);
        sparseArray.append(10, "A" + 10);
        sparseArray.append(1, "A" + 1);
        sparseArray.append(5, "A" + 5);
        sparseArray.append(9, "A" + 9);
        sparseArray.append(4, "A" + 4);
        sparseArray.append(7, "A" + 7);

        sparseArray.append(2, "A" + 2);
        sparseArray.size();

        sparseArray.put(2, "B");
        sparseArray.delete(2);
        String str1 = new String("A7");
        String str2 = new String("A7");
        boolean test = (str1 == str2);

        Log.d(TAG, "test:" + test + "," + sparseArray.indexOfValue("A7") + "," + sparseArray.indexOfValue(str1));
        Log.d(TAG, "" + sparseArray);

    }

    private void showViewDetails() {
        StringBuilder msg = new StringBuilder();

        des.setText(msg.toString());
    }
}
