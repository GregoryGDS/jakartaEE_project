package com.mycommerce.project.servlet;


import com.mycommerce.project.dao.DaoFactory;
import com.mycommerce.project.dao.base.UserDao;
import com.mycommerce.project.dao.exception.UnknownPersonException;
import com.mycommerce.project.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth/admin/update-user")
public class UpdateUserServlet extends HttpServlet {

    Long id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");

        try {
            id = Long.parseLong(idStr);
            UserDao userDao = DaoFactory.getUserDao();
            User user = userDao.findById(id);

            req.setAttribute("user", user);

        } catch (NumberFormatException e) {
            req.setAttribute("ERROR_TYPE_ID_USER", true);
        } catch (UnknownPersonException e) {
            req.setAttribute("ERROR_UNKNOWN_PERSON", true);
        } finally {
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/updateUser.jsp");
            rd.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pName = req.getParameter("pName");
        String pIsValid = req.getParameter("pIsValid");

        try {

            System.out.println("pIsValid => " + pIsValid);

            UserDao userDao = DaoFactory.getUserDao();
            User user = userDao.findById(id);
            boolean isValid = Boolean.valueOf(pIsValid);

            user.setName(pName);
            user.setIsValid(isValid);

            userDao.update(user);
            
            resp.sendRedirect(req.getContextPath()+ListPersonServlet.URL);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
