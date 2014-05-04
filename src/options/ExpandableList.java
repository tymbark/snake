package options;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.snake.R;
import com.example.snake.R.id;
import com.example.snake.R.layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandableList extends BaseExpandableListAdapter{

	// private Context context;
	private Boolean o;
	public Activity activity;
	private ArrayList<String> headers;
	private HashMap<String, ArrayList<Option>> options;
	private LayoutInflater inflater;
	private static final String TAG = "ExpandableList";

	public ExpandableList(/* Context _context, */ArrayList<String> _headers,
			HashMap<String, ArrayList<Option>> _options) {
		// this.context = _context;
		this.headers = _headers;
		this.options = _options;
		// inflater = LayoutInflater.from(_context);
		Log.d(TAG, "constructor");
	}

	public void setInflater(LayoutInflater inflater, Activity activity) {
		this.inflater = inflater;
		this.activity = activity;
	}

	@Override
	public Option getChild(int groupPosition, int childPosititon) {
		return this.options.get(this.headers.get(groupPosition)).get(
				childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		Log.d(TAG, "getChildView");
		View v = null;

		if (convertView != null) {
			v = convertView;
		} else {
			v = inflater.inflate(R.layout.list_item, null);
		}
//		v.setOnClickListener(new OnClickListener() {
//			public void onClick(View v2) {
//				// TODO Auto-generated method stub
//				Toast.makeText(activity, "CHUJ",
//						Toast.LENGTH_SHORT).show();
//				Log.d("CHUJ", "CHUJ");
//			}
//		});
		Option o = getChild(groupPosition, childPosition);
		TextView cb = (TextView) v.findViewById(R.id.textView1);
		Log.d(TAG, "child:" + o.name);
		cb.setText(o.name);
		//cb.setChecked(o.state);
		return v;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.options.get(this.headers.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.headers.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.headers.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		Log.d(TAG, "GetGroupView");
		View v = null;

		if (convertView != null) {
			v = convertView;
		} else {
			v = inflater.inflate(R.layout.list_group, null);
		}

		String headerTitle = (String) getGroup(groupPosition);

		TextView optionsGroup = (TextView) v.findViewById(R.id.list_header);

		if (headerTitle != null) {
			optionsGroup.setText(headerTitle);
		}

		return v;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}


}