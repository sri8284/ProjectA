package com.rnei.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class PickupRefernce {

	private List<Vendor> vendors;
	private List<Item> items;
	private List<Area> areas;
	private List<OnRoadResource> orrs;
	
	public List<Vendor> getVendors() {
		return vendors;
	}
	public void setVendors(List<Vendor> vendors) {
		this.vendors = vendors;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public List<Area> getAreas() {
		return areas;
	}
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	public List<OnRoadResource> getOrrs() {
		return orrs;
	}
	public void setOrrs(List<OnRoadResource> orrs) {
		this.orrs = orrs;
	}
	
	
	

}
