package com.ecommerce.bakery.Controller;

import com.ecommerce.bakery.Service.SelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SelectionController {
    @Autowired
    private SelectionService ss;

    @GetMapping("/cart/{id}")
    public String DeleteProduct(@PathVariable(name = "id") int id) {
        ss.deleteSelection(id);
        return "redirect:/cart";
    }

}
