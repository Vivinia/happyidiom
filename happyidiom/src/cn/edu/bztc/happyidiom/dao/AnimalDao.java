package cn.edu.bztc.happyidiom.dao;

import java.util.ArrayList;
import java.util.List;

import cn.edu.bztc.happyidiom.db.DBOpenHelper;
import cn.edu.bztc.happyidiom.entity.Animal;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AnimalDao {
	private static AnimalDao animalDao;
	private SQLiteDatabase db;
	/*将构造方法私有化*/
	private AnimalDao(Context context){
		DBOpenHelper dbHelper=new DBOpenHelper(context);
		db=dbHelper.openDatabase();
	}
	/*获取AnimalDao的实例*/
	public synchronized static AnimalDao getInstance(Context context){
		if(animalDao==null){
			animalDao=new AnimalDao(context);
		}
		return animalDao;
	}
	/*从数据库读取所有的动物类成语*/
	public List<Animal> getAllAnimals(){
		List<Animal> list=new ArrayList<Animal>();
		Cursor cursor=db.query("animal",null,null,null,null,null,null);
		if(cursor.moveToNext()){
			do{
				Animal animal=new Animal();
				animal.setId(cursor.getInt(cursor.getColumnIndex("_id")));
				animal.setName(cursor.getString(cursor.getColumnIndex("name")));
				animal.setPronounce(cursor.getString(cursor.getColumnIndex("pronounce")));
				animal.setAntonym(cursor.getString(cursor.getColumnIndex("antonym")));
				animal.setHomoionym(cursor.getString(cursor.getColumnIndex("homoionym")));
				animal.setDerivation(cursor.getString(cursor.getColumnIndex("derivation")));
				animal.setExamples(cursor.getString(cursor.getColumnIndex("examples")));
				animal.setExplain(cursor.getString(cursor.getColumnIndex("explain")));
				list.add(animal);
			}while(cursor.moveToNext());
		}cursor.close();
		return list;
	}
}
