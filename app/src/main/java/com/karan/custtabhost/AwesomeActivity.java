package com.karan.custtabhost;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Adil Soomro
 *
 */
@SuppressWarnings("deprecation")
public class AwesomeActivity extends TabActivity implements TabHost.OnTabChangeListener{
	TabHost tabHost;
	ImageView icon;
	TextView title;
	/** Called when the activity is first created. */
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tabHost = getTabHost();
		setTabs();

		tabHost.setOnTabChangedListener(this);

		tabHost.getTabWidget().setDividerDrawable(null);
	}
	private void setTabs()
	{
		addTab("Home", R.drawable.ic_home_black_24dp, HomeActivity.class);
		addTab("History", R.drawable.ic_history_black_24dp, SearchActivity.class);
		addTab("Book", R.drawable.ic_add_circle_outline_black_24dp, SearchActivity.class);
		addTab("Profile", R.drawable.ic_person_black_24dp, HomeActivity.class);
		addTab("Help", R.drawable.ic_help_outline_black_24dp, SearchActivity.class);
	}
	private void addTab(String labelId, int drawableId, Class<?> c)
	{
		Intent intent = new Intent(this, c);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);	
		
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);
		icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);		
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}
	public void openCameraActivity(View b)
	{
		Intent intent = new Intent(this, CameraActivity.class);
		startActivity(intent);
	}


	@Override
	public void onTabChanged(String s) {
		//Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
		ImageView imageView;
		TextView tv;
		View v;
		for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
		{
			v = tabHost.getTabWidget().getChildAt(i);           //get TabWidget View
			//v.setBackgroundColor(Color.parseColor("#2B2B2B")); //unselected
			imageView = (ImageView) v.findViewById(R.id.icon);
			tv = (TextView) v.findViewById(R.id.title);
			imageView.setColorFilter(Color.parseColor("#2B2B2B"));
			tv.setTextColor(Color.parseColor("#2B2B2B"));
		}
		v = tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab());  //get Selected Tab view
		imageView = (ImageView) v.findViewById(R.id.icon);
		tv = (TextView) v.findViewById(R.id.title);
		//v.setBackgroundColor(Color.parseColor("#FFFFFF")); // selected
		imageView.setColorFilter(Color.WHITE);
		tv.setTextColor(Color.parseColor("#FFFFFF"));
	}
}