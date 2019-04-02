package com.controller;

import com.dao.UserDao;
import com.model.User;
import com.util.DbUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller extends HttpServlet {

    private UserDao dao;

    private final static String INSERT_OR_EDIT = "/user.jsp";
    private final static String LIST_USER = "/listUser.jsp";

    public Controller() {
        super();
        dao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        int id;
        switch (action) {
            case "delete":
                forward = LIST_USER;
                id = Integer.parseInt(req.getParameter("userId"));
                dao.deleteUser(id);
                req.setAttribute("users", dao.getAllUsers());
                break;
            case "edit":
                forward = INSERT_OR_EDIT;
                id = Integer.parseInt(req.getParameter("userId"));
                User user = dao.getUserById(id);
                req.setAttribute("user", user);
                break;
            case "listUser":
                forward = LIST_USER;
                req.setAttribute("users", dao.getAllUsers());
                break;
            default:
                forward = INSERT_OR_EDIT;
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(forward);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;

        String id = req.getParameter("userId");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        Date birthdayDate = null;
        try {
            birthdayDate = new SimpleDateFormat("dd.MM.yyyy").parse(req.getParameter("birthdayDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (id == null || id.isEmpty()) {
            user = new User(firstName, lastName, birthdayDate, email);
            int newId = dao.addUser(user);

            user.setUserId(newId);
        } else {
            user = new User(Integer.parseInt(id), firstName, lastName, birthdayDate, email);
            dao.updateUser(user);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(LIST_USER);
        req.setAttribute("users", dao.getAllUsers());
        requestDispatcher.forward(req, resp);
    }
}
