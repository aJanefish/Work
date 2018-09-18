package launcher.butler.com.drawabledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void levelListClick(View view) {
		if (flag) {
			view.getBackground().setLevel(3);
		} else {
			view.getBackground().setLevel(13);
		}
		this.flag = !this.flag;
	}
	
	boolean flag;

	

}
