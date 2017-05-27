package cn.edu.bztc.happyidiom.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.edu.bztc.happyidiom.db.DBOpenHelper;
import cn.edu.bztc.happyidiom.entity.Animal;

public class AnimalQueryDao {
	
	private static AnimalQueryDao animalQueryDao;
	private SQLiteDatabase db;
	/*将构造方法私有化*/
	private AnimalQueryDao(Context context){
		DBOpenHelper dbHelper=new DBOpenHelper(context);
		db=dbHelper.openDatabase();
	}
	/*获取AnimalDao的实例*/
	public synchronized static AnimalQueryDao getInstance(Context context){
		if(animalQueryDao==null){
			animalQueryDao=new AnimalQueryDao(context);
		}
		return animalQueryDao;
	}
	public List<Animal> queryAnimal(String elem){
		List<Animal> animals=new ArrayList<Animal>();
		/*
		 * table为查询的表名
		 * columns为查询后返回的列，为null时则返回所有的列
		 * selection指定需要返回哪些行的where条件语句，为null表示返回所有行
		 * selectionArgs表示where语句中表达式的？占位参数列表，参数只能为String型
		 * groupBy表示对结果集进行分组的group by语句，为null将不对结果集进行分组
		 * having表示对分组结果集设置条件的having语句，必须配合groupBy参数使用，为null将不对分组结果集设置条件
		 * orderBy表示对结果集进行排序的orderBy语句，为null将对结果集使用默认的排序（不排序）
		 */
		Cursor cursor=db.query("animal",null,"name like ?",new String[]{"%"+elem+"%"},null,null,null);
		if(cursor.moveToFirst()){
			do{
				Animal animal=new Animal();
				animal.setId(cursor.getInt(cursor.getColumnIndex("_id")));
				animal.setName(cursor.getString(cursor.getColumnIndex("name")));
				animal.setPronounce(cursor.getString(cursor.getColumnIndex("pronounce")));
				animal.setAntonym(cursor.getString(cursor.getColumnIndex("antonym")));
				animal.setHomoionym(cursor.getString(cursor.getColumnIndex("homoionym")));
				animal.setDerivation(cursor.getString(cursor.getColumnIndex("derivation")));
				animal.setExamples(cursor.getString(cursor.getColumnIndex("examples")));
				animal.setExamples(cursor.getString(cursor.getColumnIndex("explain")));
				animals.add(animal);
			}while(cursor.moveToNext());
		}
		cursor.close();
		return animals;
	}
}
