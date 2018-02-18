package com.sid.onlonebanking.vo;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="CountrySelect")
public class CountrySelectVO {
	private String id;
	private Set<String> country;
	private Set<String> state;
	private Set<String> city;
	public Set<String> getCountry() {
		return country;
	}
	public void setCountry(Set<String> country) {
		this.country = country;
	}
	public Set<String> getState() {
		return state;
	}
	public void setState(Set<String> state) {
		this.state = state;
	}
	public Set<String> getCity() {
		return city;
	}
	public void setCity(Set<String> city) {
		this.city = city;
	}
	
	
}
