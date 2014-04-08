package com.namoo.club.domain.entity;


public class IdValue   {

	private String name;
	private int value;

	public IdValue(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void increase(){
		this.value++;
	}

}
