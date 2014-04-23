package edu.mum.ea.bookstore.controller;

import edu.mum.ea.bookstore.domain.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class MyBookDao implements IBookDao {
	private static int idCount = 1;
	private Map<Integer, Book> books = new HashMap<Integer, Book>();
	
	public MyBookDao() {
		//add(new Book("Harry Potter and the Sorcerer's Stone", "978-0590353427", "J.K. Rowling", 8.79));
		//add(new Book("The Color of Magic: A Discworld Novel", "978-0060855925", "Terry Pratchett", 11.19));
	}
	
	@Override
	public List<Book> getAll() {
		return new ArrayList<Book>(books.values());
	}
	
	@Override
	public void add(Book book) {
		//book.setId(idCount);
		books.put(idCount, book);
		idCount++;
	}
	
	@Override
	public Book get(int id) {
		Book result = books.get(id);
		
		return result;
	}
	
	@Override
	public void update(int bookId, Book book) {
		books.put(bookId, book);
	}
	
	@Override
	public void delete(int bookId) {
		Book removed = books.remove(bookId);
	}
}
