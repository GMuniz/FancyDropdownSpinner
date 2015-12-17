package com.gmuniz.fancydropdownspinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mundodescuento on 12/17/15.
 */
public class DemoAdapter extends BaseAdapter {

	private final Context mContext;
	private final List<Book> mBooks;
	private final LayoutInflater mInflater;

	public DemoAdapter(final Context context, final List<Book> books) {
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
		mBooks = books;
	}

	@Override
	public int getCount() {
		return mBooks.size();
	}

	@Override
	public Book getItem(final int position) {
		return mBooks.get(position);
	}

	@Override
	public long getItemId(final int position) {
		return mBooks.get(position).getId();
	}

	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		final Book book = getItem(position);

		final View ret;
		if (convertView == null) {
			ret = mInflater.inflate(R.layout.item_selected, parent, false);

			ret.setTag(R.id.text, ret.findViewById(R.id.text));
		} else {
			ret = convertView;
		}

		final TextView textView = (TextView) ret.getTag(R.id.text);
		textView.setText(String.format("%s (%d)", book.getTitle(), book.getYear()));

		return ret;
	}

	@Override
	public View getDropDownView(final int position, final View convertView, final ViewGroup parent) {
		final Book book = getItem(position);

		final View ret;
		if (convertView == null) {
			ret = mInflater.inflate(R.layout.item_dropdown, parent, false);

			ret.setTag(R.id.text, ret.findViewById(R.id.text));
		} else {
			ret = convertView;
		}

		final CheckedTextView textView = (CheckedTextView) ret.getTag(R.id.text);
		textView.setText(String.format("%s (%d)", book.getTitle(), book.getYear()));

		return ret;
	}
}
