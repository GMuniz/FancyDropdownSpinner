package com.gmuniz.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SpinnerAdapter;

import java.util.LinkedList;
import java.util.List;

public class FancySpinner extends AppCompatSpinner
		implements AdapterView.OnItemClickListener, PopupWindow.OnDismissListener {

	private ListPopupWindow mPopupWindow;
	private MultiSpinnerAdapter mAdapter;
	private View mSelectedView;

	private final List mSelectedList = new LinkedList();

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
		final Context context = getContext();
		mPopupWindow = new ListPopupWindow(context, attrs, defStyle);
		mPopupWindow.setModal(true);
		mPopupWindow.setAnchorView(this);
		mPopupWindow.setOnItemClickListener(this);
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
		if (!mPopupWindow.isShowing()) {
			mPopupWindow.show();

			post(new Runnable() {
				@Override
				public void run() {
					mPopupWindow.getListView().setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
				}
			});
		}

		return true;
	}

	@Override
	public View getSelectedView() {
		return mSelectedView;
	}

	@Override
	public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
		final Object object = mAdapter.getItem(position);

		final ListView listView = mPopupWindow.getListView();
		final boolean isSelected = listView.isItemChecked(position);
		if (isSelected && !mSelectedList.contains(object)) {
			mSelectedList.add(object);
		} else if (mSelectedList.contains(object)) {
			mSelectedList.remove(object);
		}
	}

	@Override
	public void onDismiss() {
		mSelectedView = mAdapter.getDropDownView(mSelectedList, getSelectedView(), this);


		requestLayout();
		Log.d(getContext().getPackageName(), "Yaaaay! Size: " + mSelectedList.size());
	}

	@Override
	public void setAdapter(final SpinnerAdapter adapter) {
		throw new UnsupportedOperationException("You should call setAdapter(MultiSpinnerAdapter adapter) instead.");
	}

	public void setAdapter(final MultiSpinnerAdapter adapter) {
		mAdapter = new SpinnerAdapterWrapper(adapter);
		mPopupWindow.setAdapter(mAdapter);
	}

	private static class SpinnerAdapterWrapper extends MultiSpinnerAdapter {

		private final MultiSpinnerAdapter mWrappedAdapter;

		public SpinnerAdapterWrapper(final MultiSpinnerAdapter wrappedAdapter) {
			mWrappedAdapter = wrappedAdapter;
		}

		@Override
		public void registerDataSetObserver(final DataSetObserver observer) {
			mWrappedAdapter.registerDataSetObserver(observer);
		}

		@Override
		public void unregisterDataSetObserver(final DataSetObserver observer) {
			mWrappedAdapter.unregisterDataSetObserver(observer);
		}

		@Override
		public int getCount() {
			return mWrappedAdapter.getCount();
		}

		@Override
		public Object getItem(final int position) {
			return mWrappedAdapter.getItem(position);
		}

		@Override
		public long getItemId(final int position) {
			return mWrappedAdapter.getItemId(position);
		}

		@Override
		public boolean hasStableIds() {
			return mWrappedAdapter.hasStableIds();
		}

		@Override
		public View getView(final int position, final View convertView, final ViewGroup parent) {
			return mWrappedAdapter.getView(position, convertView, parent);
		}

		@Override
		public View getDropDownView(final int position, final View convertView, final ViewGroup parent) {
			throw new UnsupportedOperationException("You should override getDropdownView(selected, convertView, ViewGroup) instead.");
		}

		@Override
		public View getDropDownView(final List selected, final View convertView, final ViewGroup parent) {
			return mWrappedAdapter.getDropDownView(selected, convertView, parent);
		}

		@Override
		public int getItemViewType(final int position) {
			return mWrappedAdapter.getItemViewType(position);
		}

		@Override
		public int getViewTypeCount() {
			return mWrappedAdapter.getViewTypeCount();
		}

		@Override
		public boolean isEmpty() {
			return mWrappedAdapter.isEmpty();
		}
	}
}
