package com.pdp.businesslogic;

import com.pdp.business.LibraryPersistentBeanRemote;
import com.pdp.model.Book;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;


@Stateless(name = "library")
@Local ( LibraryPersistentBean.class  )
public class LibraryPersistentBean implements LibraryPersistentBeanRemote {

    @PersistenceContext(unitName="EjbComponentPU")
    private EntityManager entityManager;

    public LibraryPersistentBean() {
    }
    
    public void addBook(Book book) {
        entityManager.persist(book);
    }

    public List<Book> getBooks() {
        return entityManager.createQuery("From Book").getResultList();
    }

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

    
    
}
