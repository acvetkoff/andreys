package com.softuni.andreys.web;

import com.softuni.andreys.models.binding.ItemAddBindingModel;
import com.softuni.andreys.models.service.ItemServiceModel;
import com.softuni.andreys.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemController extends BaseController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/add")
    public String addItem(Model model) {
        if (!model.containsAttribute("itemAddBindingModel")) {
            model.addAttribute("itemAddBindingModel", new ItemAddBindingModel());
        }

        return "add-item";
    }

    @PostMapping("/add")
    public String addItemConfirm(@Valid @ModelAttribute("itemAddBindingModel") ItemAddBindingModel itemAddBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.itemAddBindingModel", bindingResult);

            return this.redirect("add");
        }

        ItemServiceModel item = this.itemService
                                        .create(itemAddBindingModel);

        httpSession.setAttribute("item", item);

        return this.redirect("/");
    }

    @GetMapping("/details")
    public ModelAndView itemDetails(@RequestParam("id") String id, ModelAndView modelAndView) {
        modelAndView.addObject("item", this.itemService.findById(id));
        modelAndView.setViewName("details-item");

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") String id) {

        this.itemService.delete(id);

        return this.redirect("/");
    }
}
