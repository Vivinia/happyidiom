package cn.edu.bztc.happyidion.activity;

import java.util.ArrayList;
import java.util.List;

import cn.edu.bztc.happyidiom.adapter.CollectAdapter;
import cn.edu.bztc.happyidiom.dao.CollectDao;
import cn.edu.bztc.happyidiom.entity.Animal;
import cn.edu.bztc.happyidiom.minterface.ChangeListData;
import cn.edu.bztc.happyidiom.util.DialogUtil;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CollectActivity extends Activity {

	private List<Animal> collectList = new ArrayList<Animal>();
	private CollectDao collectDao;
	private ListView lvCollect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collect);

		initAnimals();
		lvCollect = (ListView) findViewById(R.id.lvCollect);
		final CollectAdapter collectAdapter = new CollectAdapter(this,
				R.layout.collect_item, collectList);
		lvCollect.setAdapter(collectAdapter);

		lvCollect.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				Animal animal = collectList.get(position);
				/* 定义对话框中提示语句 */
				String result = animal.getName() + "\n" + animal.getPronounce()
						+ "\n【解释】：" + animal.getExplain() 
						+ "\n【近义词】："+ animal.getHomoionym() 
						+ "\n【反义词】："+ animal.getAntonym()
						+ "\n【来源】："+ animal.getDerivation() 
						+ "\n【示例】："+ animal.getExamples();
				DialogUtil.showDialog(result, CollectActivity.this);
			}
		});
		collectAdapter.setChangeListData(new ChangeListData() {

			/*刷新ListView*/
			@Override
			public void changedata() {
				collectList.clear();
				collectDao.getAllAnimals(collectList);
				collectAdapter.notifyDataSetChanged();
			}
		});
	}

	/* 获取数据库中的收藏集合 */
	private void initAnimals() {
		collectDao = CollectDao.getInstance(this);
		collectDao.getAllAnimals(collectList);
		
	}

}
