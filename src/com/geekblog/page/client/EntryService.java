package com.geekblog.page.client;

import java.util.List;

import com.geekblog.page.shared.models.Entry;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("entry")
public interface EntryService extends RemoteService {
	
	public void addEntry(Entry entryToAdd);
	
	public void editEntry(Entry editedEntry);
	
	void rmEntry(Long id);
	
	public List<Entry> getAllEntries();
	
	public Entry getEntryById(Long id);

}
