package com.thoughtworks.twu.web;

import com.thoughtworks.twu.model.ItemType;
import com.thoughtworks.twu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(ItemController.URL)
public class ItemController{

	static final String URL = "/item";

	@Autowired
    ItemService itemService;

	@RequestMapping(method = RequestMethod.GET)
	public void get(Model model, @ModelAttribute ItemCommand itemCommand) {
		model.addAttribute("itemGrid", itemService.findAll());
        model.addAttribute("itemTypes", ItemType.values());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(Model model, @Valid ItemCommand itemCommand, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("itemGrid", itemService.findAll());
            model.addAttribute("itemTypes", ItemType.values());
			return URL;
		}
		itemService.save(itemCommand);
		return "redirect:" + URL;
	}
	
	@RequestMapping(method = RequestMethod.POST, params="update=Update all enabled items")
	public String updateItem(Model model, @Valid ItemGrid itemGrid, BindingResult result) {
		if (result.hasErrors()) {
			itemService.updateWithAll(itemGrid);
			return URL;
		}
		itemService.saveAll(itemGrid);
		return "redirect:" + URL;
	}

    @RequestMapping(method = RequestMethod.POST, params="delete=Delete all enabled items")
    public String deleteItem(Model model, @Valid ItemGrid itemGrid, BindingResult result) {
        if (result.hasErrors()) {
            itemService.updateWithAll(itemGrid);
            return URL;
        }
        itemService.deleteAll(itemGrid);
        return "redirect:" + URL;
    }
	
}
