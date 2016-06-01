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
public class AwesomeActivity extends TabActivity{
	TabHost tabHost;
	ImageView icon;
	TextView title;
	/** Called when the activity is first created. */
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tabHost = getTabHost();
		setTabs();
		tabHost.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ImageView ic = (ImageView) view.findViewById(R.id.icon);
				TextView tv = (TextView) view.findViewById(R.id.title);
				ic.setColorFilter(Color.WHITE);
				tv.setTextColor(Color.WHITE);
			}
		});
		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String s) {
				Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
				for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
				{
					tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#7392B5")); //unselected
				}
				tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFFFF")); // selected
			}
		});
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


}