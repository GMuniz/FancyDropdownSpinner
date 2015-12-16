package com.gmuniz.fancydropdownspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final String[] strings = new String[] {
				"Action", "Adventure", "Animation", "Children", "Comedy", "Documentary", "Drama",
				"Foreign", "History", "Independent", "Romance", "Sci-Fi", "Television", "Thriller"
		};

		final ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_multiple_choice, strings);
		final Spinner spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setAdapter(mAdapter);
	}
}
