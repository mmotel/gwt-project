package com.geekblog.page.server;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.geekblog.page.client.EntryService;
import com.geekblog.page.shared.models.Entry;
import com.google.gwt.dev.util.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EntryServiceImpl extends RemoteServiceServlet implements EntryService {

	private static final long serialVersionUID = 1L;
	
	private List<Entry> EntryDBstub = new ArrayList<Entry>();
	
	

	public EntryServiceImpl() {
		EntryDBstub.add(new Entry(1L, 
				"Bootstrap: Prosty layout", 
				"Twitter Bootstrap - darmowa biblioteka programistyczna, rozwijana przez developerów Twittera,"
				+ " wydawana na licencji Apache License 2.0. Zawiera zestaw przydatnych narzędzi ułatwiających "
				+ "tworzenie interfejsu graficznego stron oraz aplikacji internetowych. Bazuje głównie na gotowych "
				+ "rozwiązaniach HTML oraz CSS i może być stosowana m.in. do stylizacji takich elementów jak teksty, "
				+ "formularze, przyciski, wykresy, nawigacje i innych komponentów wyświetlanych na stronie. Biblioteka "
				+ "posiada także rozszerzenie dla języka JavaScript.", 
				"https://github.com/GeekCodingBlog/bootstrap-prosty-layout/blob/master/README.md#bootstrap-prosty-layout", 
				new Date(113,7,25)));
		EntryDBstub.add(new Entry(2L, 
				"Bootstrap + jQuery: Single-page application", 
				"Coraz popularniejsze stają się strony/aplikacje internetowe z interjefsem napisanym według wzorca single-page "
				+ "application.  O co chodzi? \n"
				+ " Single-page application (SPA), znana również jako single-page interface (SPI), "
				+ "to aplikacja internetowa lub strona internetowa, która mieści się na jednej stronie, której celem jest "
				+ "zapewnienie bardziej płynnego interfejsu użytkownika podobnego do aplikacji desktopowych.W SPA, albo wszystkie "
				+ "niezbędne zasoby - HTML, JavaScript i CSS - są pobierane podczas jednego ładowania strony, lub odpowiednie zasoby "
				+ "są ładowane dynamicznie i dodawane do strony w razie potrzeby, zwykle w odpowiedzi na działania użytkownika. "
				+ "Strona nie przeładowuje się w żadnym momencie pracy, ani nie przekazuje sterowania do innej strony, [..]. "
				+ "Interakcje z single-page application często wymaga dynamicznej komunikacji podskórnej z serwerem WWW. ", 
				"https://github.com/GeekCodingBlog/bootstrap-jquery-spa-demo/blob/master/README.md#bootstrap--jquery-single-page-application", 
				new Date(113,8,9)));
		EntryDBstub.add(new Entry(3L, 
				"MeteorJS: Wprowadzenie", 
				"MeteorJS jest bardzo prostym środowiskiem do tworzenia nowoczesnych apliakcji webowych. To co kiedyś zajmowało tygodnie, "
				+ "nawet z najlepszymi narzędziami, z Meteorem zajmuje godziny! Meteor składa się z dwóch rzeczy: \n"
				+ "1) Biblioteki pakietów: gotowych, niezależnych modułów, których możesz potrzebować w swojej apliakcji. \n"
				+ "2) Dostępne z linii poleceń narzędzie meteor, które słyży do zarządzania pakietami oraz apliakcją.", 
				"https://github.com/GeekCodingBlog/meteorjs-wprowadzenie/blob/master/README.md#meteorjs-wprowadzenie", 
				new Date(114,3,18)));
	}

	@Override
	public void addEntry(Entry entryToAdd) {
		EntryDBstub.add(entryToAdd);
	}

	@Override
	public void editEntry(Entry editedEntry) {
		for(Entry e : EntryDBstub){
			if( ( e.getId().toString().compareTo( editedEntry.getId().toString() ) ) == 0 ){
				Integer i = EntryDBstub.indexOf(e);
				EntryDBstub.set(i, editedEntry);
			}
		}
	}

	@Override
	public void rmEntry(Long id) {
		System.out.println(id);
		for(Entry e : EntryDBstub){
			System.out.println(e.getId());
			if( ( e.getId().toString().compareTo( id.toString() ) ) == 0 ){
				//Integer i = EntryDBstub.indexOf(e);
				EntryDBstub.remove(e);
				System.out.println("Remove " + e.getId());// i);
			}
		}
	}

	@Override
	public List<Entry> getAllEntries() {
		System.out.println("DBstub size: " + EntryDBstub.size());
		Lists.sort(EntryDBstub);
		return EntryDBstub;
	}

	@Override
	public Entry getEntryById(Long id) {
		//System.out.println(id);
		for(Entry e : EntryDBstub){
			//System.out.println(e.getId());
			if( ( e.getId().toString().compareTo(  id.toString() ) ) == 0 ){
				return e;
			}
		}
		return null;
	}

}
