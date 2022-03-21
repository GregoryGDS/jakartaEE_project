package com.mycommerce.project.servlet;


import com.mycommerce.project.dao.DaoFactory;
import com.mycommerce.project.dao.base.AdminDao;
import com.mycommerce.project.dao.base.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth/admin/remove-admin")
public class RemoveAdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("adminId");
        try {
            Long id = Long.parseLong(idStr);
            AdminDao dao = DaoFactory.getAdminDao();
            dao.remove(id);

            resp.sendRedirect(req.getContextPath()+ListPersonServlet.URL);

        } catch (NumberFormatException e) {
            //TODO
        }
    }
}
