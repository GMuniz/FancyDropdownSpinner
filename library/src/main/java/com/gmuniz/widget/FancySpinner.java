package com.gmuniz.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

public class FancySpinner extends Spinner
	implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

	private ListPopupWindow mPopupWindow;
	private ListAdapter mAdapter;
	private int mHeight;

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
		final String[] strings = new String[] {
				"Action", "Adventure", "Animation", "Children", "Comedy", "Documentary", "Drama",
				"Foreign", "History", "Independent", "Romance", "Sci-Fi", "Television", "Thriller"
		};

		mAdapter = new ArrayAdapter<>(context,
				android.R.layout.simple_list_item_multiple_choice, strings);

		// TODO: Set transition / margins
		mPopupWindow = new ListPopupWindow(context);
		mPopupWindow.setModal(true);
		mPopupWindow.setAdapter(mAdapter);
		mPopupWindow.setAnchorView(this);
		mPopupWindow.setOnItemSelectedListener(this);
		mPopupWindow.setOnItemClickListener(this);
		mPopupWindow.setPromptPosition(ListPopupWindow.POSITION_PROMPT_ABOVE);
		mPopupWindow.setWidth(ListPopupWindow.WRAP_CONTENT);
	}

	@Override
	protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		mHeight = getMeasuredHeight();
		mPopupWindow.setVerticalOffset(0 - mHeight);
	}

	public void setAdapter(final ListAdapter adapter) {
		mAdapter = adapter;
	}

	@Override
	public void onItemSelected(final AdapterView<?> parent,
	                           final View view, final int position, final long id) {

		view.setSelected(true);
		// TODO: Store selected items.
	}

	@Override
	public void onNothingSelected(final AdapterView<?> parent) {
		// Nothing to do.
	}

	@Override
	public boolean performClick() {

		mPopupWindow.show();
		post(new Runnable() {
			@Override
			public void run() {
				mPopupWindow.getListView().setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
			}
		});

		return true;
	}

	@Override
	public void onItemClick(final AdapterView<?> parent,
	                        final View view, final int position, final long id) {

		view.setSelected(!view.isSelected());
	}
}
