package com.gmuniz.fancydropdownspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.gmuniz.widget.FancySpinner;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final String[] genres = new String[] {
				"Action", "Adventure", "Animation", "Children", "Comedy", "Documentary", "Drama",
				"Foreign", "History", "Independent", "Romance", "Sci-Fi", "Television", "Thriller"
		};

		final List<Book> books = new LinkedList<>();
		final Book.Builder bookBuilder = new Book.Builder();
		for (int i = 0; i < genres.length; i++) {
			bookBuilder
					.genre(genres[i])
					.title("Book " + i)
					.year(1900 + i);

			books.add(bookBuilder.build());
		}

		final DemoAdapter mAdapter = new DemoAdapter(this, books);
		final FancySpinner fancySpinner = (FancySpinner) findViewById(R.id.fancy_spinner);
		fancySpinner.setAdapter(mAdapter);

		final Spinner spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setAdapter(mAdapter);
	}
}
