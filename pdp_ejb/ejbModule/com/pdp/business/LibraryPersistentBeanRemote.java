package com.pdp.business;

import javax.ejb.Remote;

import com.pdp.model.Book;

import java.util.List;

@Remote
public interface LibraryPersistentBeanRemote {

    void addBook(Book bookName);

    List<Book> getBooks();

}
