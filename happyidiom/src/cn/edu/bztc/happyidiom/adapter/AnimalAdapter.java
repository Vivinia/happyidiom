package cn.edu.bztc.happyidiom.adapter;

import java.util.List;

import cn.edu.bztc.happyidiom.dao.CollectDao;
import cn.edu.bztc.happyidiom.entity.Animal;
import cn.edu.bztc.happyidion.activity.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AnimalAdapter extends ArrayAdapter<Animal>{

	private int resourceId;
	private Context context;
	public AnimalAdapter(Context context, int resource,List<Animal> objects) {
		super(context,resource, objects);
		this.context=context;
		resourceId=resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Animal animal =getItem(position);//获取当前项的Animal实例
		View view;
		ViewHolder viewHolder;
		if(convertView==null){//判断是否第一次运行，如果是则进入，并将上下文环境保存进convertView
			view=LayoutInflater.from(getContext()).inflate(resourceId,null);
			viewHolder=new ViewHolder();
			viewHolder.tvName=(TextView)view.findViewById(R.id.tvName);
			viewHolder.btnSave=(ImageButton)view.findViewById(R.id.btnSave);
			viewHolder.btnSave.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					int position=(Integer)view.getTag();
					Animal animal =getItem(position);//获取当前项的Animal实例
					CollectDao collectDao=new CollectDao(context);
					Log.i("MainActivity","Collectname:"+animal.getName()+",Collectid:"+animal.getId());
					collectDao.add(animal);
					Toast.makeText(context,"收藏成功",Toast.LENGTH_SHORT).show();
				}
			});
			view.setTag(viewHolder);
		}else{//如果不是第一次运行，convertView不为空，直接取出赋值给view
			view=convertView;
			viewHolder=(ViewHolder) view.getTag();
		}
		viewHolder.tvName.setText(animal.getName());//显示成语
		viewHolder.btnSave.setTag(position);
		return view;
	}
	class ViewHolder{
		TextView tvName;
		ImageButton btnSave;
	}
}
