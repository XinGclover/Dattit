/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Daddit.app;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author xingao
 */
@Service("categoryService")
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;
    
    List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }
    
    Optional<Category> findCategoryById(Long id) {
        return categoryRepo.findById(id);
    }
    
}
