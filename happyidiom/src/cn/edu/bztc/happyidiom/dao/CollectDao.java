package cn.edu.bztc.happyidiom.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import cn.edu.bztc.happyidiom.db.MyDatabaseHelper;
import cn.edu.bztc.happyidiom.entity.Animal;

public class CollectDao {
	private static CollectDao collectDao;
	SQLiteDatabase db;
	MyDatabaseHelper mydatabaseHelper;
	public CollectDao(Context context){
		mydatabaseHelper=new MyDatabaseHelper(context);
	}
	
	public synchronized static CollectDao getInstance(Context context){
		if(collectDao==null){
			collectDao=new CollectDao(context);
		}
		return collectDao;
	}
	
	/*添加收藏*/
	public void add(Animal animal) {
		db=mydatabaseHelper.getWritableDatabase();
		ContentValues value=new ContentValues();
		
		value.put(MyDatabaseHelper.COLLECT_NAME,animal.getName());
		value.put(MyDatabaseHelper.COLLECT_PRO,animal.getPronounce());
		value.put(MyDatabaseHelper.COLLECT_EXP,animal.getExplain());
		value.put(MyDatabaseHelper.COLLECT_ANT,animal.getAntonym());
		value.put(MyDatabaseHelper.COLLECT_HOM,animal.getHomoionym());
		value.put(MyDatabaseHelper.COLLECT_DER,animal.getDerivation());
		value.put(MyDatabaseHelper.COLLECT_EXA,animal.getExamples());
		
		db.insert(MyDatabaseHelper.TABLE_COLLECT,null,value);
		value.clear();
	}
	
	/*从数据库读取所有的动物类成语*/
	public List<Animal> getAllAnimals(List<Animal> list){
		db=mydatabaseHelper.getReadableDatabase();
		Cursor cursor=db.query("collect",null,null,null,null,null,null);
		
		if(cursor.moveToNext()){
			do{
				Animal animal=new Animal();
				animal.setId(cursor.getInt(cursor.getColumnIndex("id")));
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
	
	/*根据id删除收藏信息*/
	public void del(int id){
		db=mydatabaseHelper.getWritableDatabase();
		db.delete(mydatabaseHelper.TABLE_COLLECT,mydatabaseHelper.COLLECT_ID+"=?",new String[]{String.valueOf(id)});
	}

}
