package fr.simplex_software.arquillian_tests_with_junit_5.container_object;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet
{
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    PrintWriter writer = resp.getWriter();
    writer.println("Hello World");
  }
}
