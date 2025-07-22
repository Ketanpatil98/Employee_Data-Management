package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GatherInformation")
public class EmployeeDB extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String fathername = request.getParameter("fathername");
        String mothername = request.getParameter("mothername");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String role = request.getParameter("role");
        String department = request.getParameter("department");
        String DOB = request.getParameter("DOB");
        String Gender = request.getParameter("Gender");
        String hire_date = request.getParameter("Hire_date");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String mobNo = request.getParameter("phone");  // Keep as String
        String emp_Id = request.getParameter("e_Id");
        int e_Id = Integer.parseInt(emp_Id);
        String Salary = request.getParameter("Salary");
        int salary = Integer.parseInt(Salary);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Data Submitted Successfully!</h1>");
        out.println("<h3>Full Name: " + fullname + "</h3>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            PreparedStatement ps = c.prepareStatement("INSERT INTO Employee_info (fullname,fathername,mothername,city,state,role,department,DOB,Gender,hire_date,email,password,phoneNo,e_Id,Salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, fullname);
            ps.setString(2, fathername);
            ps.setString(3, mothername);
            ps.setString(4, city);
            ps.setString(5, state);
            ps.setString(6, role);
            ps.setString(7, department);
            ps.setString(8, DOB);
            ps.setString(9, Gender);
            ps.setString(10, hire_date);
            ps.setString(11, email);
            ps.setString(12, password);
            ps.setString(13, mobNo); // phone number as String
            ps.setInt(14, e_Id);
            ps.setInt(15, salary);
            ps.executeUpdate();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2 style='color:red;'>Error: " + e.getMessage() + "</h2>");
        }
    }
}
