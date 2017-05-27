package cn.edu.bztc.happyidiom.test;

import cn.edu.bztc.happyidiom.db.DBOpenHelper;
import android.test.AndroidTestCase;

public class DBOpenHelperTest extends AndroidTestCase{
	public void testDBCopy(){
		DBOpenHelper dbOpenHelper=new DBOpenHelper(getContext());
		dbOpenHelper.openDatabase();
	}
}
