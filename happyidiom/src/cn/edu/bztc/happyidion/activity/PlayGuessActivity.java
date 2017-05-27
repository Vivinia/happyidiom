package cn.edu.bztc.happyidion.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import cn.edu.bztc.happyidiom.dao.AnimalDao;
import cn.edu.bztc.happyidiom.dao.GameDao;
import cn.edu.bztc.happyidiom.entity.Animal;

import android.R.color;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class PlayGuessActivity extends Activity {

	int score=0;// 总成绩
	int number=1;// 题目数目
	private TextView tvRightOrWrong;
	private ImageButton ibNext,ibSubmit;
	private Random random;// 随机数
	private AnimalDao animalDao;
	private GameDao gameDao;
	private List<Animal> animalList;
	private Animal animal, animalTwo, animalThree, animalFour;// 随机生成的成语对象
	private SharedPreferences pref;
	private Editor editor;
	private int[] array = { 5, 5, 5, 5 };// 整形数组，用于生成随机顺序显示选项
	private String answer;// 选择的答案

	private TextView tvExplain;
	private RadioButton rbPhraseOne, rbPhraseTwo, rbPhraseThree, rbPhraseFour;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_guess);
		tvExplain = (TextView) findViewById(R.id.tvExplain);
		rbPhraseOne = (RadioButton) findViewById(R.id.rbPhraseOne);
		rbPhraseTwo = (RadioButton) findViewById(R.id.rbPhraseTwo);
		rbPhraseThree = (RadioButton) findViewById(R.id.rbPhraseThree);
		rbPhraseFour = (RadioButton) findViewById(R.id.rbPhraseFour);
		tvRightOrWrong = (TextView) findViewById(R.id.tvRightOrWrong);
		ibSubmit=(ImageButton) findViewById(R.id.ibSubmit);
		ibNext=(ImageButton) findViewById(R.id.ibNext);

		pref = getSharedPreferences("FourPhrase", MODE_PRIVATE);// 将正确成语和随机生成的三个成语保存到文档
		editor = pref.edit();// 获取SharedPreferences.Editor对象

		/* 获取随机值对应的成语解释 */
		getNumber();

		/* 显示解释到TextView */
		showPhrase();

		/* 获取三个随机成语和一个正确成语的选项 */
		getFourPhrase();

		/* 显示到RadioButton */
		showRadiButton();

	}

	/* 获取随机值对应的成语 */
	private void getNumber() {
		animalList = new ArrayList<Animal>();
		animalDao = AnimalDao.getInstance(this);
		animalList = animalDao.getAllAnimals();
		random = new Random();

		String id = Integer.toString(random.nextInt(animalList.size()));
		gameDao = GameDao.getInstance(this);
		animal = gameDao.getPhrase(id);
		
		animalList = new ArrayList<Animal>();
		animalDao = AnimalDao.getInstance(this);
		animalList = animalDao.getAllAnimals();
	}

	/* 显示解释到页面 */
	private void showPhrase() {
		tvExplain.setText(animal.getExplain());
	}

	/* 获取四个成语选项 */
	private void getFourPhrase() {
		random = new Random();

		String two = null, three = null, four = null;
		two = Integer.toString(random.nextInt(animalList.size()));
		three = Integer.toString(random.nextInt(animalList.size()));
		four = Integer.toString(random.nextInt(animalList.size()));

		gameDao = GameDao.getInstance(this);
		animalTwo = gameDao.getPhrase(two); // 获取随机生成值对应的成语
		animalThree = gameDao.getPhrase(three);
		animalFour = gameDao.getPhrase(four);

		editor.putString("0", animal.getName());// 将四个成语存入文档
		editor.putString("1", animalTwo.getName());
		editor.putString("2", animalThree.getName());
		editor.putString("3", animalFour.getName());
		editor.commit();

		int no;
		for (int i = 0; i < array.length; i++) { // 随机生成四个不重复的值作为显示顺序
			no = random.nextInt(4);
			int j;
			for (j = 0; j <= i; j++) {
				if (no == array[j]) {
					i--;
					break;
				}
			}
			if (j == i + 1) {
				array[i] = no;
			}
		}
		Log.i("MainActivity", "0:" + array[0] + "1:" + array[1] + "2:"
				+ array[2] + "3:" + array[3]);
	}

	/* 显示到RadioButton */
	private void showRadiButton() {
		rbPhraseOne.setText(pref.getString(Integer.toString(array[0]), ""));
		rbPhraseTwo.setText(pref.getString(Integer.toString(array[1]), ""));
		rbPhraseThree.setText(pref.getString(Integer.toString(array[2]), ""));
		rbPhraseFour.setText(pref.getString(Integer.toString(array[3]), ""));
	}

	/* 提交答案 */
	public void AnswerSubmit(View view) {
		switch (view.getId()) {
		case R.id.ibSubmit:
			if (rbPhraseOne.isChecked())
				answer = rbPhraseOne.getText().toString();
			else if (rbPhraseTwo.isChecked())
				answer = rbPhraseTwo.getText().toString();
			else if (rbPhraseThree.isChecked())
				answer = rbPhraseThree.getText().toString();
			else
				answer = rbPhraseFour.getText().toString();

			if (answer == animal.getName()) {
				tvRightOrWrong.setText("真棒，回答正确");
				tvRightOrWrong.setTextColor(Color.rgb(7, 200, 12));
				ibSubmit.setClickable(false);      //回答正确或错误，提交按钮不能被点击，防止一个一个尝试获取答案加分
				score+=10;
			} else {
				tvRightOrWrong.setText("啊偶，回答错了");
				tvRightOrWrong.setTextColor(Color.rgb(255, 00, 00));
				ibSubmit.setClickable(false);
			}
			if(score==60&&number<=10){
				tvRightOrWrong.setText("恭喜，闯关成功");
				tvRightOrWrong.setTextColor(Color.rgb(7, 200, 12));
				ibNext.setClickable(false);      //闯关成功或失败，下一道题的按钮不能被点击
			}else if(score<=60&&number>=10){
				tvRightOrWrong.setText("抱歉，闯关失败");
				tvRightOrWrong.setTextColor(Color.rgb(255, 00, 00));
				ibNext.setClickable(false);
			}
			break;

		case R.id.ibNext:   //生成下一道题
			clearPhrase();//清空TextView和RadioButton属性
			getNumber();    
			showPhrase();
			getFourPhrase();
			showRadiButton();
			number+=1;  //题目数目加一
			break;
		}
	}
	public void clearPhrase(){     //跳到下一道题，删除之前控件的属性
		tvRightOrWrong.setText(" ");      //清空提示语
		ibSubmit.setClickable(true);    //提交按钮可以点击
		rbPhraseOne.setChecked(false);    //单选按钮都不被选中
		rbPhraseTwo.setChecked(false);
		rbPhraseThree.setChecked(false);
		rbPhraseFour.setChecked(false);
	}
}
