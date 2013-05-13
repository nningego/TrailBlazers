package com.thoughtworks.twu.dao.impl;

import com.thoughtworks.twu.model.Item;
import com.thoughtworks.twu.model.ItemType;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
public class ItemDaoImplTest extends DaoTest {
	
	@Autowired
    ItemDaoImpl itemDaoImpl;
	
	@Test
	public void shouldSaveAllFields() {
        Item item = makeItem();
		itemDaoImpl.save(item);
		List<Item> items = itemDaoImpl.findAll();
		Assert.assertEquals("Frame1", items.get(0).getName());
		Assert.assertEquals(BigDecimal.valueOf(13.99), items.get(0).getPrice());
		Assert.assertEquals("this frame is awesome", items.get(0).getDescription());
		Assert.assertEquals(ItemType.FRAME.name(), items.get(0).getType());
	}

    private Item makeItem() {
        Item item = new Item();
        item.setName("Frame1");
        item.setPrice(BigDecimal.valueOf(13.99));
        item.setDescription("this frame is awesome");
        item.setType(ItemType.FRAME.name());
        return item;
    }

}
