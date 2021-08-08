package com.gurkaran.Demo_Hibernate;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

//@Entity(name="alien_table")
@Entity
@Table(name="alien_table")       //table name derived from entity
public class Alien //POJO or Bean
{
	@Id
	private int aid;
	//@Transient              //not saved in Database
	private AlienName aname;
	//@Column(name="alien_color")
	private String color;
	
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}

	public AlienName getAname() {
		return aname;
	}
	public void setAname(AlienName aname) {
		this.aname = aname;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Alien [aid=" + aid + ", aname=" + aname + ", color=" + color + "]";
	}
	
	
}
