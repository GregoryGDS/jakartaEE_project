package com.mycommerce.project.dao;


import com.mycommerce.project.dao.base.AdminDao;
import com.mycommerce.project.dao.base.ProductDao;
import com.mycommerce.project.dao.exception.UnknownPersonException;
import com.mycommerce.project.dao.exception.UnknownProductException;
import com.mycommerce.project.model.Admin;
import com.mycommerce.project.model.Product;
import com.mycommerce.project.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MemoryAdminDao implements AdminDao {

    private static List<Admin> admins = new ArrayList();
    private static Long idSequence = 1L;

    MemoryAdminDao() {
    }

    @Override
    public void add(Admin admin) {
        Long var1 = idSequence;
        idSequence = idSequence + 1L;
        admin.setId(var1);
        admins.add(admin);
    }

    @Override
    public void update(Admin admin) {
        int index = this.getIndexById(admin.getId());
        if (index > -1) {
            admins.set(index, admin);
        } else {
            throw new UnknownPersonException(admin.getId());
        }
    }

    @Override
    public Admin findById(Long id) {
        int index = this.getIndexById(id);
        if (index > -1) {
            Admin admin = (Admin) admins.get(index);
            return admin;
        } else {
            throw new UnknownPersonException(id);
        }
    }

    @Override
    public Admin findByName(String name) {
        List<Admin> adminList = this.getAll();
        for (Admin a: adminList) {
            if(a.getName().equals(name))
                return a;
        }
        return null;
    }

    @Override
    public List<Admin> getAll() {
        return Collections.unmodifiableList(admins);
    }

    @Override
    public void remove(Admin admin) {
        this.remove(admin.getId());
    }

    @Override
    public void remove(Long id) {
        int index = this.getIndexById(id);
        if (index > -1) {
            admins.remove(index);
        } else {
            throw new UnknownPersonException(id);
        }
    }

    private int getIndexById(Long id) {
        for (int i = 0; i < admins.size(); ++i) {
            Admin admin = admins.get(i);
            if (admin.getId().equals(id)) {
                return i;
            }
        }

        return -1;
    }
}