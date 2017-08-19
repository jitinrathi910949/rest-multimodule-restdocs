package org.sample.multimodule.model;

import javax.persistence.Entity;
import javax.persistence.*;

/**
 * Hello world!
 *
 */
@Entity
public class User 
{

 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	
	private String name;
	
	private int age;
	
	public User() {}
	
	public User(Long Id, String name, int age)
	{
		this.Id=Id;
		this.name=name;
		this.age=age;
	}
	public User(String name,int age) {
		this.name=name;
		this.age=age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Id: ").append(this.Id).append(", Name: ").append(this.name).append(", Age: ")
				.append(this.age);
		return sb.toString();
	}
	
	
}
