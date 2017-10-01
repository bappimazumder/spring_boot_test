package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class Board extends BaseEntity<Long>{

@Id	
@GeneratedValue(strategy=GenerationType.AUTO)	
private Long Id;

@Column(name = "name",length = 100,nullable = false,unique = true)
private String name;

@Column(name="password",length = 255)
private String password;

@Column(name="contents")
@Type(type="text")
private String contents;


public Board() {
	
}


public Board(Long id, String name, String password, String contents) {
	
	Id = id;
	this.name = name;
	this.password = password;
	this.contents = contents;
}


public Long getId() {
	return Id;
}


public void setId(Long id) {
	Id = id;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public String getContents() {
	return contents;
}


public void setContents(String contents) {
	this.contents = contents;
}


@Override
public String toString() {
	return "Board [Id=" + Id + ", name=" + name + ", contents=" + contents + "]";
}




}
