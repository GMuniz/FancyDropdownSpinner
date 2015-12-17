package com.gmuniz.fancydropdownspinner;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by mundodescuento on 12/17/15.
 */
public class Book {

	private static final AtomicLong idGenerator = new AtomicLong(1);

	private final long id;
	private final String title;
	private final int year;
	private final String genre;
	private final String logoUrl;

	private Book(final Builder builder) {
		this.id = idGenerator.getAndAdd(1);
		this.title = builder.title;
		this.year = builder.year;
		this.genre = builder.genre;
		this.logoUrl = builder.logoUrl;
	}

	public static class Builder {
		private String title;
		private int year;
		private String genre;
		private String logoUrl;

		public Builder title(final String title) {
		    this.title = title;

		    return this;
		}

		public Builder year(final int year) {
		    this.year = year;

		    return this;
		}

		public Builder genre(final String genre) {
		    this.genre = genre;

		    return this;
		}

		public Builder logoUrl(final String logoUrl) {
		    this.logoUrl = logoUrl;

		    return this;
		}

		public Book build() {
			return new Book(this);
		}
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	public String getGenre() {
		return genre;
	}

	public String getLogoUrl() {
		return logoUrl;
	}
}
