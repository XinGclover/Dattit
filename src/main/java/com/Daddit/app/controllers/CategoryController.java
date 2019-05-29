
package com.Daddit.app.controllers;

import com.Daddit.app.models.Category;
import com.Daddit.app.services.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
    
    
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    

    @GetMapping("/getAll")
    public List<Category> getAllCategories() {
        return categoryService.findAllCategories();
    }
}
