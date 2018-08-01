package com.example.zy.emaildemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.zy.emaildemo.utils.MailService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "email";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onEmail(View view) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				super.run();
				try {
					MailService.send_email(MainActivity.this,"wo shi zhangyu!");
				} catch (IOException e) {
					Log.d(TAG, "IOException:" + e);
					e.printStackTrace();
				} catch (MessagingException e) {
					Log.d(TAG, "MessagingException:" + e);
					e.printStackTrace();
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
		
	}
	
	//在Android中，调用Email有三种类型的Intent：
	//Intent.ACTION_SENDTO 无附件的发送
	//Intent.ACTION_SEND 带附件的发送
	//Intent.ACTION_SEND_MULTIPLE 带有多附件的发送
	
	public void onEmail1(View view) {
		Intent data = new Intent(Intent.ACTION_SENDTO);
		data.setData(Uri.parse("mailto:输入邮箱"));
		data.putExtra(Intent.EXTRA_SUBJECT, "Singou_NLP_Date");
		data.putExtra(Intent.EXTRA_TEXT, "文件位置:Android/data/mo.singou.butler/cache/singou.xls");
		startActivity(data);
	}
	
	public void onEmail2(View view) {
		//很简单，发送邮件中，有收件者，抄送者，密送者。 也就是分别通过
		//Intent.EXTRA_EMAIL,
		//Intent.EXTRA_CC,
		//Intent.EXTRA_BCC
		//来进行putExtra来设定的，而单个附件的发送，则使用Intent.EXTRA_STREAM来设置附件的地址Uri。
		Intent intent = new Intent(Intent.ACTION_SEND);
		String[] tos = {"18328582499@163.com", "18328582499@163.com"};
		String[] ccs = {"18328582499@163.com", "18328582499@163.com"};
		String[] bccs = {"18328582499@163.com", "18328582499@163.com"};
		intent.putExtra(Intent.EXTRA_EMAIL, tos);
		intent.putExtra(Intent.EXTRA_CC, ccs);
		intent.putExtra(Intent.EXTRA_BCC, bccs);
		intent.putExtra(Intent.EXTRA_TEXT, "body");
		intent.putExtra(Intent.EXTRA_SUBJECT, "subject");

//发送图片
		File fileB = new File("sdcard/b.png");
		intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(this, "comxf.activity.provider.download", fileB));
		intent.setType("image/*");
		intent.setType("message/rfc882");
		Intent.createChooser(intent, "Choose Email Client");
		startActivity(intent);
	}
	
	public void onEmail3(View view) {
		Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
		String[] tos = {"way.ping.li@gmail.com"};
		String[] ccs = {"way.ping.li@gmail.com"};
		intent.putExtra(Intent.EXTRA_EMAIL, tos);
		intent.putExtra(Intent.EXTRA_CC, ccs);
		intent.putExtra(Intent.EXTRA_TEXT, "body");
		intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
		
		//File file = new File("/storage/emulated/0/test.txt");
		//Intent intent = new Intent(Intent.ACTION_VIEW);
		//intent.setDataAndType(Uri.fromFile(file), "text/*");
		File fileA = new File("sdcard/a.png");
		File fileB = new File("sdcard/b.png");
		ArrayList<Uri> imageUris = new ArrayList<Uri>();
		
		imageUris.add(FileProvider.getUriForFile(this, "comxf.activity.provider.download", fileA));
		imageUris.add(FileProvider.getUriForFile(this, "comxf.activity.provider.download", fileB));
		intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
		intent.setType("image/*");
		intent.setType("message/rfc882");
		//Intent.createChooser(intent, "Choose Email Client");
		startActivity(intent);
		
	}
}
