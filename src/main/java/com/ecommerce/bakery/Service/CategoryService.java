package com.ecommerce.bakery.Service;

import com.ecommerce.bakery.Model.Category;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.bakery.Repository.CategoryRepository;
import org.springframework.ui.Model;

@Service
@Data
public class CategoryService {

    @Autowired
    private CategoryRepository cr;


    public Optional<Category> getCategory(final int id){
        return cr.findById(id);
    }

    public Iterable<Category> getAllCategory() {
        return cr.findAll();
    }

    public void deleteCategory(final int id) {
        cr.deleteById(id);
    }

    public void insertCategory(Category Category) {
        cr.save(Category);
    }


    public String getListCategory(Model model) {
        model.addAttribute("categories", getAllCategory());
        return "productGeneralPage";
    }
}
