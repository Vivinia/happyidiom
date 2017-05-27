package cn.edu.bztc.happyidion.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class GameGuessActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_guess);
		
		
	}
	public void startGame(View view){
		switch (view.getId()) {
		case R.id.ibStartGame:
			Intent intent=new Intent(this,PlayGuessActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}
}
