package com.thoughtworks.twu.web;

import com.thoughtworks.twu.model.Item;

public class ItemCommand extends Item {

	private Boolean selected = false;

	public ItemCommand() {}

	public ItemCommand(Item item) {
		setId(item.getId());
		setName(item.getName());
		setPrice(item.getPrice());
		setDescription(item.getDescription());
		setType(item.getType());
	}

	public Item toItem() {
		Item item = new Item();
		item.setId(getId());
		item.setName(getName());
		item.setPrice(getPrice());
		item.setDescription(getDescription());
		item.setType(getType());
		return item;
	}

	public Boolean getSelected() {
		return selected;
	}
	
	public void setSelected(Boolean changed) {
		this.selected = changed;
	}
	
}