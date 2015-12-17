package com.gmuniz.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by mundodescuento on 12/17/15.
 */
public abstract class MultiSpinnerAdapter<T> extends BaseAdapter {

	/**
	 * Gets a {@link android.view.View} that displays in the drop down popup
	 * the data at the specified position in the data set.
	 *
	 * @param selected the list of items those were checked.
	 * @param convertView the old view to reuse, if possible. Note: You should
	 *        check that this view is non-null and of an appropriate type before
	 *        using. If it is not possible to convert this view to display the
	 *        correct data, this method can create a new view.
	 * @param parent the parent that this view will eventually be attached to
	 * @return a {@link android.view.View} corresponding to the data at the
	 *         specified position.
	 */
	public abstract View getDropDownView(List<T> selected, View convertView, ViewGroup parent);
}
