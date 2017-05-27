package cn.edu.bztc.happyidiom.adapter;

import java.util.List;

import cn.edu.bztc.happyidiom.entity.Category;
import cn.edu.bztc.happyidion.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter<Category>{

	private int resourceId;
	public CategoryAdapter(Context context, int resource,
			List<Category> categoryList) {
		super(context,resource, categoryList);
		resourceId=resource;
	}
	public View getView(int position, android.view.View convertView, android.view.ViewGroup parent) {
		Category category=getItem(position);//获取当前项的Category实例
		View view;
		ViewHolder viewHolder;
		if(convertView==null){
			view=LayoutInflater.from(getContext()).inflate(resourceId,null);
			viewHolder=new ViewHolder();
			viewHolder.categoryImage=(ImageView)view.findViewById(R.id.category_image);
			viewHolder.categoryName=(TextView)view.findViewById(R.id.category_name);
			view.setTag(viewHolder);	
		}else{
			view=convertView;
			viewHolder=(ViewHolder) view.getTag();
		}
		viewHolder.categoryImage.setImageResource(category.getImageId());
		viewHolder.categoryName.setText(category.getName());
		return view;
	}
	class ViewHolder{
		ImageView categoryImage;
		TextView categoryName;
	}
}
