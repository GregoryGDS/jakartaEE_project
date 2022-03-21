package com.mycommerce.project.dao;


import com.mycommerce.project.dao.base.AdminDao;
import com.mycommerce.project.dao.base.UserDao;
import com.mycommerce.project.dao.exception.UnknownPersonException;
import com.mycommerce.project.model.Admin;
import com.mycommerce.project.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MemoryUserDao implements UserDao {

    private static List<User> users = new ArrayList();
    private static Long idSequence = 1L;

    MemoryUserDao() {
    }

    @Override
    public void add(User user) {
        Long var1 = idSequence;
        idSequence = idSequence + 1L;
        user.setId(var1);
        users.add(user);
    }

    @Override
    public void update(User user) {
        int index = this.getIndexById(user.getId());
        if (index > -1) {
            users.set(index, user);
        } else {
            throw new UnknownPersonException(user.getId());
        }
    }

    @Override
    public User findById(Long id) {
        int index = this.getIndexById(id);
        if (index > -1) {
            User user = (User) users.get(index);
            return user;
        } else {
            throw new UnknownPersonException(id);
        }
    }

    @Override
    public User findByName(String name) {
        List<User> userList = this.getAll();
        for (User u: userList) {
            if(u.getName().equals(name))
               return u;
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public void remove(User user) {
        this.remove(user.getId());
    }

    @Override
    public void remove(Long id) {
        int index = this.getIndexById(id);
        if (index > -1) {
            users.remove(index);
        } else {
            throw new UnknownPersonException(id);
        }
    }

    private int getIndexById(Long id) {
        for (int i = 0; i < users.size(); ++i) {
            User user = users.get(i);
            if (user.getId().equals(id)) {
                return i;
            }
        }

        return -1;
    }
}