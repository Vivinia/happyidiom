package cn.edu.bztc.happyidion.activity;
import com.example.musicservice.AndioService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

public class SetActivity extends Activity {
	
	private Switch switchMusic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set);
		
		switchMusic=(Switch) findViewById(R.id.switchMusic);
		switchMusic.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
				if(checked){
					startService(new Intent(SetActivity.this,AndioService.class));
				}else{
					stopService(new Intent(SetActivity.this,AndioService.class));
				}
			}
		});
	}
}
