package com.example.singou.myapplication.activity;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.singou.myapplication.R;
import com.example.singou.myapplication.util.Book;
import com.example.singou.myapplication.util.Util;
import com.google.android.flexbox.FlexboxLayout;

public class FlexboxActivity extends AppCompatActivity {
	
	private FlexboxLayout flexboxLayout;
	private String TAG = "FlexboxActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flexbox);
		
		String[] tags = {"婚姻育儿", "散文", "设计", "上班这点事儿", "影视天堂", "大学生活", "美人说", "运动和健身", "工具癖", "生活家", "程序员", "想法", "短篇小说", "美食", "教育", "心理", "奇思妙想", "美食", "摄影"};
		flexboxLayout = (FlexboxLayout) findViewById(R.id.flexbox_layout);
		for (int i = 0; i < tags.length; i++) {
			Book model = new Book();
			model.setId(i);
			model.setName(tags[i]);
			flexboxLayout.addView(createNewFlexItemTextView(model));
		}
		

	}
	
	/**
	 * 动态创建TextView
	 * @param book
	 * @return
	 */
	private TextView createNewFlexItemTextView(final Book book) {
		TextView textView = new TextView(this);
		textView.setGravity(Gravity.CENTER);
		textView.setText(book.getName());
		textView.setTextSize(12);
		textView.setTextColor(getResources().getColor(R.color.colorAccent));
		textView.setBackgroundResource(R.drawable.ic_launcher_background);
		textView.setTag(book.getId());
		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.e(TAG, book.getName());
			}
		});
		int padding = Util.dpToPixel(this, 4);
		int paddingLeftAndRight = Util.dpToPixel(this, 8);
		ViewCompat.setPaddingRelative(textView, paddingLeftAndRight, padding, paddingLeftAndRight, padding);
		FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		int margin = Util.dpToPixel(this, 6);
		int marginTop = Util.dpToPixel(this, 16);
		layoutParams.setMargins(margin, marginTop, margin, 0);
		textView.setLayoutParams(layoutParams);
		return textView;
	}
	

}
