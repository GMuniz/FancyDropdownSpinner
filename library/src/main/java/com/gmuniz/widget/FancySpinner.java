package com.gmuniz.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

public class FancySpinner extends Button
	implements View.OnClickListener, AdapterView.OnItemSelectedListener {

	private PopupWindow mPopupWindow;
	private ListAdapter mAdapter;

	public FancySpinner(Context context) {
		super(context);
		init(null, 0);
	}

	public FancySpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
	}

	public FancySpinner(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}

	private void init(AttributeSet attrs, int defStyle) {
		// Load attributes

		final Context context = getContext();

		// TODO: Style as close as spinner

		final String[] strings = new String[] {
				"Action", "Adventure", "Animation", "Children", "Comedy", "Documentary", "Drama",
				"Foreign", "History", "Independent", "Romance", "Sci-Fi", "Television", "Thriller"
		};

		mAdapter = new ArrayAdapter<>(context,
				android.R.layout.simple_list_item_multiple_choice, strings);

		final LinearLayout.LayoutParams layoutParams =
				new LinearLayout.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.MATCH_PARENT);

		final ListView listView = new ListView(context);
		listView.setLayoutParams(layoutParams);
		listView.setAdapter(mAdapter);
		listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

		// TODO: Set transition / margins
		mPopupWindow = new PopupWindow(context);
		mPopupWindow.setContentView(listView);
		mPopupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
		mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setTouchable(true);
		mPopupWindow.setFocusable(true);

		mPopupWindow.setBackgroundDrawable(getResources().getDrawable(0, context.getTheme()));



		setOnClickListener(this);
		setText("One");
	}

	@Override
	public void onClick(final View v) {
		mPopupWindow.showAsDropDown(this);
	}


	public void setAdapter(final ListAdapter adapter) {
		mAdapter = adapter;
	}

	@Override
	public void onItemSelected(final AdapterView<?> parent,
	                           final View view, final int position, final long id) {

		// TODO: Store selected items.
	}

	@Override
	public void onNothingSelected(final AdapterView<?> parent) {
		// Nothing to do.
	}
}
