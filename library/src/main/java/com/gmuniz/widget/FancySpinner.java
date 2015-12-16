package com.gmuniz.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;

public class FancySpinner extends AppCompatSpinner
	implements AdapterView.OnItemSelectedListener, PopupWindow.OnDismissListener {

	private ListPopupWindow mPopupWindow;
	private OnItemSelectedListener mOnItemSelectedListener;

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

		final ArrayAdapter<String> mAdapter = new ArrayAdapter<>(context,
				android.R.layout.simple_list_item_multiple_choice, strings);

		mPopupWindow = new ListPopupWindow(context, attrs, defStyle);
		mPopupWindow.setAdapter(mAdapter);
		mPopupWindow.setModal(true);
		mPopupWindow.setAnchorView(this);
		mPopupWindow.setOnItemSelectedListener(this);
		mPopupWindow.setOnDismissListener(this);
		mPopupWindow.setPromptPosition(ListPopupWindow.POSITION_PROMPT_ABOVE);
	}

	@Override
	protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		final int margin = Math.round(TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));

		mPopupWindow.setWidth(getWidth() - margin);
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
	public void onItemSelected(final AdapterView<?> parent,
	                           final View view, final int position, final long id) {

		// TODO: Store selected items.

		if (mOnItemSelectedListener != null) {
			mOnItemSelectedListener.onItemSelected(parent, view, position, id);
		}
	}

	@Override
	public void onNothingSelected(final AdapterView<?> parent) {
		if (mOnItemSelectedListener != null) {
			mOnItemSelectedListener.onNothingSelected(parent);
		}
	}

	@Override
	public void setOnItemSelectedListener(final OnItemSelectedListener listener) {
		super.setOnItemSelectedListener(listener);

		mOnItemSelectedListener = listener;
	}

	@Override
	public void onDismiss() {

	}
}
