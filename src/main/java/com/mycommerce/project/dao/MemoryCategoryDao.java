package com.mycommerce.project.dao;


import com.mycommerce.project.dao.base.CategoryDao;
import com.mycommerce.project.dao.base.ProductDao;
import com.mycommerce.project.dao.exception.UnknownCategoryException;
import com.mycommerce.project.dao.exception.UnknownProductException;
import com.mycommerce.project.model.Category;
import com.mycommerce.project.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MemoryCategoryDao implements CategoryDao {

    private static List<Category> categories = new ArrayList();
    private static Long idSequence = 1L;

    MemoryCategoryDao() {
    }

    @Override
    public void add(Category category) {
        Long var1 = idSequence;
        idSequence = idSequence + 1L;
        category.setId(var1);
        categories.add(category);
    }

    @Override
    public void update(Category category) {
        int index = this.getIndexById(category.getId());
        if (index > -1) {
            categories.set(index, category);
        } else {
            throw new UnknownCategoryException(category.getId());
        }
    }

    @Override
    public Category findById(Long id) {
        int index = this.getIndexById(id);
        if (index > -1) {
            Category category = (Category) categories.get(index);
            return category;
        } else {
            throw new UnknownCategoryException(id);
        }
    }

    @Override
    public List<Category> getAll() {
        return Collections.unmodifiableList(categories);
    }

    @Override
    public void remove(Category category) {
        this.remove(category.getId());
    }

    @Override
    public void remove(Long id) {
        int index = this.getIndexById(id);
        if (index > -1) {
            categories.remove(index);
        } else {
            throw new UnknownCategoryException(id);
        }
    }

    private int getIndexById(Long id) {
        for (int i = 0; i < categories.size(); ++i) {
            Category category = categories.get(i);
            if (category.getId().equals(id)) {
                return i;
            }
        }

        return -1;
    }
}
