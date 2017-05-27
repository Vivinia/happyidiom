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

	int score=0;// �ܳɼ�
	int number=1;// ��Ŀ��Ŀ
	private TextView tvRightOrWrong;
	private ImageButton ibNext,ibSubmit;
	private Random random;// �����
	private AnimalDao animalDao;
	private GameDao gameDao;
	private List<Animal> animalList;
	private Animal animal, animalTwo, animalThree, animalFour;// ������ɵĳ������
	private SharedPreferences pref;
	private Editor editor;
	private int[] array = { 5, 5, 5, 5 };// �������飬�����������˳����ʾѡ��
	private String answer;// ѡ��Ĵ�

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

		pref = getSharedPreferences("FourPhrase", MODE_PRIVATE);// ����ȷ�����������ɵ��������ﱣ�浽�ĵ�
		editor = pref.edit();// ��ȡSharedPreferences.Editor����

		/* ��ȡ���ֵ��Ӧ�ĳ������ */
		getNumber();

		/* ��ʾ���͵�TextView */
		showPhrase();

		/* ��ȡ������������һ����ȷ�����ѡ�� */
		getFourPhrase();

		/* ��ʾ��RadioButton */
		showRadiButton();

	}

	/* ��ȡ���ֵ��Ӧ�ĳ��� */
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

	/* ��ʾ���͵�ҳ�� */
	private void showPhrase() {
		tvExplain.setText(animal.getExplain());
	}

	/* ��ȡ�ĸ�����ѡ�� */
	private void getFourPhrase() {
		random = new Random();

		String two = null, three = null, four = null;
		two = Integer.toString(random.nextInt(animalList.size()));
		three = Integer.toString(random.nextInt(animalList.size()));
		four = Integer.toString(random.nextInt(animalList.size()));

		gameDao = GameDao.getInstance(this);
		animalTwo = gameDao.getPhrase(two); // ��ȡ�������ֵ��Ӧ�ĳ���
		animalThree = gameDao.getPhrase(three);
		animalFour = gameDao.getPhrase(four);

		editor.putString("0", animal.getName());// ���ĸ���������ĵ�
		editor.putString("1", animalTwo.getName());
		editor.putString("2", animalThree.getName());
		editor.putString("3", animalFour.getName());
		editor.commit();

		int no;
		for (int i = 0; i < array.length; i++) { // ��������ĸ����ظ���ֵ��Ϊ��ʾ˳��
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

	/* ��ʾ��RadioButton */
	private void showRadiButton() {
		rbPhraseOne.setText(pref.getString(Integer.toString(array[0]), ""));
		rbPhraseTwo.setText(pref.getString(Integer.toString(array[1]), ""));
		rbPhraseThree.setText(pref.getString(Integer.toString(array[2]), ""));
		rbPhraseFour.setText(pref.getString(Integer.toString(array[3]), ""));
	}

	/* �ύ�� */
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
				tvRightOrWrong.setText("������ش���ȷ");
				tvRightOrWrong.setTextColor(Color.rgb(7, 200, 12));
				ibSubmit.setClickable(false);      //�ش���ȷ������ύ��ť���ܱ��������ֹһ��һ�����Ի�ȡ�𰸼ӷ�
				score+=10;
			} else {
				tvRightOrWrong.setText("��ż���ش����");
				tvRightOrWrong.setTextColor(Color.rgb(255, 00, 00));
				ibSubmit.setClickable(false);
			}
			if(score==60&&number<=10){
				tvRightOrWrong.setText("��ϲ�����سɹ�");
				tvRightOrWrong.setTextColor(Color.rgb(7, 200, 12));
				ibNext.setClickable(false);      //���سɹ���ʧ�ܣ���һ����İ�ť���ܱ����
			}else if(score<=60&&number>=10){
				tvRightOrWrong.setText("��Ǹ������ʧ��");
				tvRightOrWrong.setTextColor(Color.rgb(255, 00, 00));
				ibNext.setClickable(false);
			}
			break;

		case R.id.ibNext:   //������һ����
			clearPhrase();//���TextView��RadioButton����
			getNumber();    
			showPhrase();
			getFourPhrase();
			showRadiButton();
			number+=1;  //��Ŀ��Ŀ��һ
			break;
		}
	}
	public void clearPhrase(){     //������һ���⣬ɾ��֮ǰ�ؼ�������
		tvRightOrWrong.setText(" ");      //�����ʾ��
		ibSubmit.setClickable(true);    //�ύ��ť���Ե��
		rbPhraseOne.setChecked(false);    //��ѡ��ť������ѡ��
		rbPhraseTwo.setChecked(false);
		rbPhraseThree.setChecked(false);
		rbPhraseFour.setChecked(false);
	}
}
