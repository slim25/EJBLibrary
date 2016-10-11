package com.pdp.model;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="books")
public class Book implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
    private String name;
    
    public Book() {
	}
    
	public Book(String name) {
		this.name = name;
	}

    public int getId() {
        return id;
    }


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setId(int id) {
		this.id = id;
	}
    
}
