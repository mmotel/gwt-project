package com.geekblog.page.shared.models;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class Entry implements Serializable, Comparable<Entry> {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String dscr;
	private String link;
	private Date date;
	
	public Entry(){}
	
	public Entry(Long id, String title, String dscr, String link, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.dscr = dscr;
		this.link = link;
		this.date = date;
	}



	public Entry( String title, String dscr, String link, Date date) {
		super();
		this.id = new Date().getTime();
		this.title = title;
		this.dscr = dscr;
		this.link = link;
		this.date = date;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDscr() {
		return dscr;
	}

	public void setDscr(String dscr) {
		this.dscr = dscr;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getDate() {
		return date;
	}
	
	public String getDateString(){
		return DateTimeFormat.getFormat("dd/MM/yyyy").format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int compareTo(Entry o) {
		if(this.getDate().before(o.getDate())){
			return 1;
		}
		else if(this.getDate().after(o.getDate())){
			return -1;
		}else{
			return 0;
		}
	}
	

}
