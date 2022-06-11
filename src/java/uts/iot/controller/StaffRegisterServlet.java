package uts.iot.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import java.util.logging.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.iot.model.Staff;
import uts.iot.model.dao.AccessDBManager;
import uts.iot.controller.AccessValidator;
import uts.iot.model.User_Account;

public class StaffRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = 0;                 //Default value due to id is auto-generated
        String history = "";        //Default value due to newly registered staff has not done any actions
        int accid = 0;              //Default value due to id is auto-generated

        HttpSession session = request.getSession();
        AccessValidator validator = new AccessValidator();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String number = request.getParameter("number");
        String address = request.getParameter("address");
        String type = request.getParameter("type");
        AccessDBManager manager = (AccessDBManager) session.getAttribute("accessManager");
        validator.clear(session);

        //Input validations
        if (validator.registerCheckEmpty(email, password, name, dob, number, gender, address)) {
            session.setAttribute("empErr", "Please fill in every textfield");
            request.getRequestDispatcher("staffRegister.jsp").include(request, response);
        } else if (!validator.validateEmail(email)) {
            session.setAttribute("emailErr", "Error: Email format is incorrect");
            request.getRequestDispatcher("staffRegister.jsp").include(request, response);
        } else if (!validator.validateName(name)) {
            session.setAttribute("nameErr", "Error: Name format is incorrect");
            request.getRequestDispatcher("staffRegister.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("passErr", "Error: Password format is incorrect");
            request.getRequestDispatcher("staffRegister.jsp").include(request, response);
        } else if (!validator.validateNumber(number)) {
            session.setAttribute("numErr", "Error: Number format is incorrect");
            request.getRequestDispatcher("staffRegister.jsp").include(request, response);
        } else {
            try {
                User_Account exist = manager.findUserEmail(email);

                    //Check if staff email already exists in the database
                if (exist != null) {
                    session.setAttribute("existErr", "User email already exists in the database");
                    request.getRequestDispatcher("staffRegister.jsp").include(request, response);
                } else {

                    //add the staff in the STAFF and USER_ACCOUNT table
                    manager.addStaff(name, email, number, address, type, history, password, dob, gender, Boolean.FALSE, id);
                    Staff staff = new Staff(id, name, email, number, address, type, history, accid);
                    User_Account user = new User_Account(accid, email, password, dob, gender, Boolean.FALSE, id);
                    session.setAttribute("staff", staff);
                    session.setAttribute("user", user);

                    int ids = user.getUserAccountID();                              //get userId of the user
                    manager.addLog(ids, "LOGIN");                           //Add a login log in the ACCESS_LOG Table
                    request.getRequestDispatcher("staffMain.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(StaffRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}