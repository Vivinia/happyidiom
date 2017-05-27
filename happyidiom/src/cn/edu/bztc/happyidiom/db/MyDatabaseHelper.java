package cn.edu.bztc.happyidiom.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	public static final String DB_COLLECT_NAME = "collect.db";// 数据库名
	public static final int COLLECT_VERSION = 1;// 数据库版本号
	public static final String TABLE_COLLECT = "collect";// 表名
	public static final String COLLECT_ID = "id";// id字段
	public static final String COLLECT_NAME = "name";// 成语名称
	public static final String COLLECT_PRO = "pronounce";// 成语发音
	public static final String COLLECT_EXP = "explain";// 成语解释
	public static final String COLLECT_ANT = "antonym";// 反义词
	public static final String COLLECT_HOM = "homoionym";// 同义词
	public static final String COLLECT_DER = "derivation";// 源自
	public static final String COLLECT_EXA = "examples";// 例子

	public static final String CREATE_COLLECT = "create table collect (id integer primary key autoincrement,name text,pronounce text,explain text,antonym text,homoionym text,derivation text,examples text)";

	public MyDatabaseHelper(Context context) {
		super(context, DB_COLLECT_NAME, null, COLLECT_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_COLLECT);// 执行创建数据库的SQL语句
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

}
