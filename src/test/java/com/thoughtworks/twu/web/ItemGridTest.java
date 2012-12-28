package com.thoughtworks.twu.web;

import com.thoughtworks.twu.model.*;
import junit.framework.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItemGridTest {
	
	@Test
	public void two_valid_items_should_not_have_violations() {

		List<Item> items = new ArrayList<Item>();

		items.add(new Item());
		items.get(0).setName("name1");
		items.get(0).setPrice(BigDecimal.valueOf(13.99));
		items.get(0).setDescription("description1");

		items.add(new Item());
		items.get(1).setName("name2");
		items.get(1).setPrice(BigDecimal.valueOf(14.99));
		items.get(1).setDescription("description2");

		ItemGrid itemGrid = new ItemGrid(items);
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<ItemGrid>> violations = validator.validate(itemGrid);
		Assert.assertTrue(violations.isEmpty());
	
	}

    @Test
    public void should_accept_valid_price() {

        List<Item> items = new ArrayList<Item>();

        items.add(new Item());
        items.get(0).setName("name1");
        items.get(0).setPrice(BigDecimal.valueOf(13.99));
        items.get(0).setDescription("description1");

        ItemGrid itemsCommand = new ItemGrid(items);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ItemGrid>> violations = validator.validate(itemsCommand);
        Assert.assertTrue(violations.isEmpty());
    }

	@Test
	public void invalid_price_should_have_violations() {

		List<Item> items = new ArrayList<Item>();

		items.add(new Item());
		items.get(0).setName("name1");
		items.get(0).setPrice(BigDecimal.valueOf(348324689));
		items.get(0).setDescription("description1");

		ItemGrid itemsCommand = new ItemGrid(items);
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<ItemGrid>> violations = validator.validate(itemsCommand);
		Assert.assertFalse(violations.isEmpty());
		for (ConstraintViolation<ItemGrid> violation : violations) {
			Assert.assertEquals("must be less than or equal to 99999", violation.getMessage());
		}
	
	}

}
