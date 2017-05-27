package cn.edu.bztc.happyidion.activity;

import cn.edu.bztc.happyidiom.db.MyDatabaseHelper;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	private TabHost tabHost;// 导航栏控件

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 取消标题栏
		setContentView(R.layout.activity_main);

		createCollectDatabase();
		tabHost = getTabHost(); // 获取TabHost实例
		/* 一个Tab对应一个name名称，一个标题，一个图标，yigeActivity页面 */
		// addTab("study",R.string.title_study,R.drawable.study,StudyActivity.class);
		// addTab("search",R.string.title_search,R.drawable.search,SearchActivity.class);
		// addTab("game",R.string.title_game,R.drawable.game,GameActivity.class);
		// addTab("save",R.string.title_save,R.drawable.save,CollectActivity.class);
		// addTab("help",R.string.title_help,R.drawable.help,StudyActivity.class);

		tabHost.addTab(tabHost.newTabSpec("study")
				.setIndicator("学习",getResources().getDrawable(R.drawable.study))
				.setContent(new Intent(this, StudyActivity.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
		tabHost.addTab(tabHost.newTabSpec("search")
				.setIndicator("搜索",getResources().getDrawable(R.drawable.search))
				.setContent(new Intent(this, SearchActivity.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
		tabHost.addTab(tabHost.newTabSpec("game")
				.setIndicator("游戏",getResources().getDrawable(R.drawable.game))
				.setContent(new Intent(this, GameActivity.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
		tabHost.addTab(tabHost.newTabSpec("save")
				.setIndicator("收藏",getResources().getDrawable(R.drawable.save))
				.setContent(new Intent(this, CollectActivity.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
		tabHost.addTab(tabHost.newTabSpec("help")
				.setIndicator("设置",getResources().getDrawable(R.drawable.help))
				.setContent(new Intent(this, SetActivity.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

	}

	/* 创建收藏数据库 */
	private void createCollectDatabase() {
		MyDatabaseHelper mydatabaseHelper = new MyDatabaseHelper(
				MainActivity.this);
		mydatabaseHelper.getWritableDatabase();
	}

	/* 定义每个Tab的显示内容 */
	private void addTab(String tag, int title_introduction, int title_icon,
			Class ActivityClass) {
		tabHost.addTab(tabHost
				.newTabSpec(tag)
				.setIndicator(getString(title_introduction),
						getResources().getDrawable(title_icon))
				.setContent(new Intent(this, ActivityClass)));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
