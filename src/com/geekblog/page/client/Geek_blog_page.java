package com.geekblog.page.client;

import java.util.Date;
import java.util.List;

import com.geekblog.page.shared.models.Entry;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Geek_blog_page implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	@SuppressWarnings("unused")
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	
	private final LoginServiceAsync loginService = GWT.create(LoginService.class);
	
	private final EntryServiceAsync	entryService = GWT.create(EntryService.class);

	/**
	 * This is the entry point method.
	 */
	
	private void ClearDiv(String divName){
		while(RootPanel.get(divName).getWidgetCount() > 0){
			RootPanel.get(divName).remove(0);
		}	
	}
	
	private void FlexEntires(){
		entryService.getAllEntries(new AsyncCallback<List<Entry>>() {
			
			@Override
			public void onSuccess(List<Entry> result) {
				//remove old entires
				ClearDiv("entriesHolder");
				//generate new ones
				for(Entry e : result){
					VerticalPanel vp1 = new VerticalPanel();
					Label title = new Label(e.getTitle());
					title.addStyleName("titleHeader");
					Label dateLbl = new Label(e.getDateString());
					dateLbl.addStyleName("dateField");
					Label dscr = new Label(e.getDscr());
					Anchor link = new Anchor("Przejdz do artykułu >>", false, e.getLink(), "_blank");
					
					vp1.add(title);
					vp1.add(dateLbl);
					vp1.add(dscr);
					vp1.add(link);
					
					RootPanel.get("entriesHolder").add(vp1);
				}
			}
		
			@Override
			public void onFailure(Throwable caught) {
				Label err = new Label("rpc err with getting entries");
				RootPanel.get("entiresHolder").add(err);
				
			}
		});	
	}


	
	private void FlexCrud(Widget lb, Widget editEntryBtn, Widget rmEntryBtn, Widget titleTb, Widget dscrTa, 
			Widget linkTb, Widget addBtn, Widget editBtn, Widget cancelBtn, Widget rmEntryCancelBtn, 
			Widget rmEntryConfirmBtn, Widget rmEntryTitleLbl){
		RootPanel.get("listBoxHolder").add(lb);
		RootPanel.get("editEntryBtnHolder").add(editEntryBtn);
		RootPanel.get("rmEntryBtnHolder").add(rmEntryBtn);
		
		RootPanel.get("titleTbHolder").add(titleTb);
		RootPanel.get("dscrTaHolder").add(dscrTa);
		RootPanel.get("linkTbHolder").add(linkTb);
		RootPanel.get("addBtnHolder").add(addBtn);
		RootPanel.get("editBtnHolder").add(editBtn);
		RootPanel.get("cancelBtnHolder").add(cancelBtn);
		
		RootPanel.get("rmEntryCancelBtnHolder").add(rmEntryCancelBtn);
		RootPanel.get("rmEntryConfirmBtnHolder").add(rmEntryConfirmBtn);
		RootPanel.get("rmWarningTitleHolder").add(rmEntryTitleLbl);
		
		DOM.getElementById("editEntryLbl").getStyle().setDisplay(Display.NONE);
		DOM.getElementById("editBtnHolder").getStyle().setVisibility(Visibility.HIDDEN);
	}
	
	private void FlexCRUDEntiresList(final ListBox lb){
		entryService.getAllEntries(new AsyncCallback<List<Entry>>() {

			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(List<Entry> result) {		
				lb.clear();
				for(Entry e : result){
				
					lb.addItem("["+e.getDateString()+"] "+e.getTitle(), e.getId().toString());
				
				}
			}
		});
		
	}
	
	private void ClearCRUDEntires(){
		RootPanel.get("listBoxHolder").remove(0);
		RootPanel.get("editEntryBtnHolder").remove(0);
		RootPanel.get("rmEntryBtnHolder").remove(0);
		RootPanel.get("titleTbHolder").remove(0);
		RootPanel.get("dscrTaHolder").remove(0);
		RootPanel.get("linkTbHolder").remove(0);
		RootPanel.get("addBtnHolder").remove(0);
		RootPanel.get("editBtnHolder").remove(0);;
		RootPanel.get("cancelBtnHolder").remove(0);
		RootPanel.get("rmEntryCancelBtnHolder").remove(0);
		RootPanel.get("rmEntryConfirmBtnHolder").remove(0);
		RootPanel.get("rmWarningTitleHolder").remove(0);
	}
	
	public void onModuleLoad() {
		
		//signin Panel
		final TextBox loginTb = new TextBox();
		final PasswordTextBox passwdTb = new PasswordTextBox();
		
		Button signinBtn = new Button(" Zaloguj ");
		
		RootPanel.get("loginTbHolder").add(loginTb);
		RootPanel.get("passwdTbHolder").add(passwdTb);
		RootPanel.get("signinBtnHolder").add(signinBtn);
		
		//Loggedin panel
		
		final Label userNameLbl = new Label("user name");
		Button logoutBtn = new Button(" wyloguj ");
		
		RootPanel.get("userNameLblHolder").add(userNameLbl);
		RootPanel.get("logoutBtnHolder").add(logoutBtn);
		
		//---
		
		//admin UI
		
		final Button editEntryBtn = new Button("Edytuj");
		final Button rmEntryBtn = new Button("Usuń");
		
		final ListBox lb = new ListBox();
		
		final TextBox titleTb = new TextBox();
		final TextArea dscrTa = new TextArea();
		final TextBox linkTb = new TextBox();
		
		final Button addBtn = new Button("Dodaj");
		final Button editBtn = new Button("Zapisz");
		final Button cancelBtn = new Button("Anuluj");
		
		final Button rmEntryCancelBtn = new Button("Anuluj");
		final Button rmEntryConfirmBtn = new Button("Usuń");		
		final Label rmEntryTitleLbl = new Label();
		rmEntryTitleLbl.setStyleName("warn");

	    DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
	    final DateBox dateBox = new DateBox();
	    dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));	      
	    dateBox.setValue(new Date());

	    RootPanel.get("dateHolder").add(dateBox);
		
		//---
		
		//sign in click
		signinBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				final String login = loginTb.getText();
				String passwd = passwdTb.getText();
				
				loginService.checkUser(login, passwd, new AsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean result) {
						if(result){
							
							userNameLbl.setText(login);
							DOM.getElementById("siginErr").getStyle().setDisplay(Display.NONE);
							DOM.getElementById("loginPanel").getStyle().setDisplay(Display.NONE);
							DOM.getElementById("loggedinPanel").getStyle().setDisplay(Display.BLOCK);
							DOM.getElementById("crudHolder").getStyle().setDisplay(Display.BLOCK);
							ClearDiv("entriesHolder");
							FlexCrud(lb, editEntryBtn, rmEntryBtn, titleTb, dscrTa, linkTb, addBtn, editBtn, cancelBtn, 
									rmEntryCancelBtn, rmEntryConfirmBtn, rmEntryTitleLbl);
							FlexCRUDEntiresList(lb);
						}
						else {
							DOM.getElementById("siginErr").getStyle().setDisplay(Display.BLOCK);
						}
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						DOM.getElementById("siginErr").getStyle().setDisplay(Display.BLOCK);
						
					}
				});
				
			}
		});
		
		//sign out click
		logoutBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DOM.getElementById("loggedinPanel").getStyle().setDisplay(Display.NONE);
				DOM.getElementById("crudHolder").getStyle().setDisplay(Display.NONE);
				DOM.getElementById("loginPanel").getStyle().setDisplay(Display.BLOCK);	
				ClearCRUDEntires();
				FlexEntires();
			}
		});
		
		//---
		
		//Entries for users
		FlexEntires();
		
		//ADMIN UI LOGIC
		
		//add entry click
		addBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String title = titleTb.getValue();
				String dscr = dscrTa.getValue();
				String link = linkTb.getValue();
				Date date = dateBox.getValue();
				entryService.addEntry(new Entry(title, dscr, link, date), new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						//do sth with err
					}

					@Override
					public void onSuccess(Void result) {
						titleTb.setValue("");
						dscrTa.setValue("");
						linkTb.setValue("");
						FlexCRUDEntiresList(lb);
					}
				});
			}
		});
		//---
		
		//edit entry click
		editEntryBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DOM.getElementById("addEntryLbl").getStyle().setDisplay(Display.NONE);
				DOM.getElementById("editEntryLbl").getStyle().setDisplay(Display.BLOCK);
				DOM.getElementById("addBtnHolder").getStyle().setVisibility(Visibility.HIDDEN);
				DOM.getElementById("editBtnHolder").getStyle().setVisibility(Visibility.VISIBLE);
				DOM.getElementById("actionsHolder").getStyle().setDisplay(Display.NONE);
				Integer i = lb.getSelectedIndex();
				Long id = Long.parseLong(lb.getValue(i));

				entryService.getEntryById(id, new AsyncCallback<Entry>() {

					@Override
					public void onFailure(Throwable caught) {
						dscrTa.setText("err");
						
					}

					@Override
					public void onSuccess(Entry result) {
						if(result == null){
							dscrTa.setText("null");
						}
						titleTb.setText(result.getTitle());
						dscrTa.setText(result.getDscr());
						linkTb.setText(result.getLink());
						dateBox.setValue(result.getDate());
					}
				});
			}
		});
		//---
		
		//cancel click
		cancelBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DOM.getElementById("addEntryLbl").getStyle().setDisplay(Display.BLOCK);
				DOM.getElementById("editEntryLbl").getStyle().setDisplay(Display.NONE);
				DOM.getElementById("addBtnHolder").getStyle().setVisibility(Visibility.VISIBLE);
				DOM.getElementById("editBtnHolder").getStyle().setVisibility(Visibility.HIDDEN);
				DOM.getElementById("actionsHolder").getStyle().setDisplay(Display.BLOCK);
				titleTb.setText("");
				dateBox.setValue(new Date());
				dscrTa.setText("");
				linkTb.setText("");
			}
		});
		//---
		
		//save edit click
		editBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Integer i = lb.getSelectedIndex();
				Long id = Long.parseLong(lb.getValue(i));
				String title = titleTb.getValue();
				String dscr = dscrTa.getValue();
				String link = linkTb.getValue();
				Date date = dateBox.getValue();
				
				entryService.editEntry(new Entry(id, title, dscr, link, date), new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
						DOM.getElementById("addEntryLbl").getStyle().setDisplay(Display.BLOCK);
						DOM.getElementById("editEntryLbl").getStyle().setDisplay(Display.NONE);
						DOM.getElementById("addBtnHolder").getStyle().setVisibility(Visibility.VISIBLE);
						DOM.getElementById("editBtnHolder").getStyle().setVisibility(Visibility.HIDDEN);
						DOM.getElementById("actionsHolder").getStyle().setDisplay(Display.BLOCK);
						titleTb.setValue("");
						dateBox.setValue(new Date());
						dscrTa.setValue("");
						linkTb.setValue("");
						FlexCRUDEntiresList(lb);
					}
				});
				
			}
		});
		//---
		
		//rm entry click
		rmEntryBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Integer i = lb.getSelectedIndex();
				String title = lb.getItemText(i);
				
				rmEntryTitleLbl.setText(title);
				
				DOM.getElementById("rmWarningHolder").getStyle().setDisplay(Display.BLOCK);
				DOM.getElementById("textInputHolder").getStyle().setDisplay(Display.NONE);
				DOM.getElementById("actionsHolder").getStyle().setDisplay(Display.NONE);
			}
		});
		//--
		
		//rm entry cancel click
		rmEntryCancelBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DOM.getElementById("rmWarningHolder").getStyle().setDisplay(Display.NONE);
				DOM.getElementById("textInputHolder").getStyle().setDisplay(Display.BLOCK);
				DOM.getElementById("actionsHolder").getStyle().setDisplay(Display.BLOCK);
			}
		});
		//---
		
		//rm entry confirm click
		rmEntryConfirmBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Integer i = lb.getSelectedIndex();
				Long id = Long.parseLong(lb.getValue(i));
				entryService.rmEntry(id, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(Void result) {
						DOM.getElementById("rmWarningHolder").getStyle().setDisplay(Display.NONE);
						DOM.getElementById("textInputHolder").getStyle().setDisplay(Display.BLOCK);
						DOM.getElementById("actionsHolder").getStyle().setDisplay(Display.BLOCK);
						FlexCRUDEntiresList(lb);
					}
				});
				
			}
		});

	}
}
