package cn.edu.bztc.happyidion.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends Activity {

	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}

	public void gamePlay(View view) {
		switch (view.getId()) {
		case R.id.tvGameGuess:
			intent=new Intent(this,GameGuessActivity.class);
			startActivity(intent);
			break;
		case R.id.tvGameConn:

			break;

		default:
			break;
		}
	}

}
