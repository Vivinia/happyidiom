package cn.edu.bztc.happyidion.activity;

import java.util.List;

import cn.edu.bztc.happyidiom.adapter.AnimalAdapter;
import cn.edu.bztc.happyidiom.dao.AnimalQueryDao;
import cn.edu.bztc.happyidiom.entity.Animal;
import cn.edu.bztc.happyidiom.util.DialogUtil;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SearchActivity extends Activity {

	private List<Animal> animalList;
	private AnimalQueryDao queryDao;
	private ListView lvQueryAfter;

	private EditText etSearch;
	private Button btnSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		etSearch = (EditText) findViewById(R.id.etSearch);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		lvQueryAfter = (ListView) findViewById(R.id.lvQueryAfter);
		btnSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				search();
			}
		});
	}

	private void initAnimals(String elem) {
		queryDao=AnimalQueryDao.getInstance(this);
		animalList = queryDao.queryAnimal(elem);
	}

	private void search() {
		String elem = etSearch.getText().toString();
		initAnimals(elem);
		AnimalAdapter animalAdapter = new AnimalAdapter(this,
				R.layout.animal_item, animalList);
		lvQueryAfter.setAdapter(animalAdapter);
		lvQueryAfter.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				Animal animal = animalList.get(position);
				/* 定义对话框中提示语句 */
				String result = animal.getName() + "\n" + animal.getPronounce()
						+ "\n【解释】：" + animal.getExplain() + "\n【近义词】："
						+ animal.getHomoionym() + "\n【反义词】："
						+ animal.getAntonym() + "\n【来源】："
						+ animal.getDerivation() + "\n【示例】："
						+ animal.getExamples();
				DialogUtil.showDialog(result, SearchActivity.this);
			}
		});
	}
}
