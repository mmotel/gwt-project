package com.geekblog.page.client;

import java.util.List;

import com.geekblog.page.shared.models.Entry;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EntryServiceAsync {

	void addEntry(Entry entryToAdd, AsyncCallback<Void> callback);

	void editEntry(Entry editedEntry, AsyncCallback<Void> callback);

	void getAllEntries(AsyncCallback<List<Entry>> callback);

	void rmEntry(Long id, AsyncCallback<Void> callback);

	void getEntryById(Long id, AsyncCallback<Entry> callback);

}
