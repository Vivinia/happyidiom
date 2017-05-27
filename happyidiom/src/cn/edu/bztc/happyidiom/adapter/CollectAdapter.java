package cn.edu.bztc.happyidiom.adapter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.bztc.happyidiom.dao.CollectDao;
import cn.edu.bztc.happyidiom.entity.Animal;
import cn.edu.bztc.happyidiom.minterface.ChangeListData;
import cn.edu.bztc.happyidion.activity.CollectActivity;
import cn.edu.bztc.happyidion.activity.MainActivity;
import cn.edu.bztc.happyidion.activity.R;

public class CollectAdapter extends ArrayAdapter<Animal> {

	private int resourceId;
	private Context context;
	private ChangeListData mChangeListData;

	public CollectAdapter(Context context, int resource, List<Animal> objects) {
		super(context, resource, objects);
		this.context = context;
		resourceId = resource;
	}

	public ChangeListData getChangeListData() {
		return mChangeListData;
	}

	public void setChangeListData(ChangeListData mChangeListData) {
		this.mChangeListData = mChangeListData;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Animal animal = getItem(position);// 获取当前项的Animal实例
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {// 判断是否第一次运行，如果是则进入，并将上下文环境保存进convertView
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.tvName = (TextView) view.findViewById(R.id.tvName);
			viewHolder.btnDel = (ImageButton) view.findViewById(R.id.btnDel);
			viewHolder.btnDel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					/* 弹出提示框 */
					showDialog();
				}

				/* 弹出提示框 */
				private void showDialog() {
					AlertDialog.Builder builder = new AlertDialog.Builder(context);
					builder.setMessage("确定删除？");// 设置内容
					/* 添加对话框中确定按钮和点击事件 */
					builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							CollectDao collectDao = new CollectDao(context);
							collectDao.del(animal.getId());
							Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
							if (mChangeListData != null)
								mChangeListData.changedata();
						}
					});
					/* 添加对话框中取消按钮和点击事件 */
					builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
						}
					});
					AlertDialog dialog = builder.create();// 获取dialog
					dialog.show();// 显示对话框
				}
			});
			view.setTag(viewHolder);
		} else {// 如果不是第一次运行，convertView不为空，直接取出赋值给view
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.tvName.setText(animal.getName());// 显示成语
		return view;
	}

	class ViewHolder {
		TextView tvName;
		ImageButton btnDel;
	}
}
