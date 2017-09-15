package com.example.spinnertest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.view.SpinerPopWindow;

/**
 * ��Activity  ����ʵ��popupwindow
 * @author ansen
 */
public class MainActivity extends Activity {
	private SpinerPopWindow<String> mSpinerPopWindow;
	private List<String> list;
	private TextView tvValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initData();

		tvValue = (TextView) findViewById(R.id.tv_value);
		tvValue.setOnClickListener(clickListener);
		mSpinerPopWindow = new SpinerPopWindow<String>(this, list,itemClickListener);
		mSpinerPopWindow.setOnDismissListener(dismissListener);
	}
	
	/**
	 * ����popupwindowȡ��
	 */
	private OnDismissListener  dismissListener=new OnDismissListener() {
		@Override
		public void onDismiss() {
			setTextImage(R.drawable.icon_down);
		}
	};

	/**
	 * popupwindow��ʾ��ListView��item����¼�
	 */
	private OnItemClickListener itemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			mSpinerPopWindow.dismiss();
			tvValue.setText(list.get(position));
			Toast.makeText(MainActivity.this, "�����:" + list.get(position),Toast.LENGTH_LONG).show();
		}
	};

	/**
	 * ��ʾPopupWindow
	 */
	private OnClickListener clickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_value:
				mSpinerPopWindow.setWidth(tvValue.getWidth());
				mSpinerPopWindow.showAsDropDown(tvValue);
				setTextImage(R.drawable.icon_up);
				break;
			}
		}
	};

	/**
	 * ��ʼ������
	 */
	private void initData() {
		list = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			list.add("test:" + i);
		}
	}

	/**
	 * ��TextView�ұ�����ͼƬ
	 * @param resId
	 */
	private void setTextImage(int resId) {
		Drawable drawable = getResources().getDrawable(resId);
		drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());// ��������ͼƬ��С��������ʾ
		tvValue.setCompoundDrawables(null, null, drawable, null);
	}
}
