package ajax_01_hello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/xmlHello")
public class Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String xml = "<students><student number='001'><name>zhangSan</name><age>18</age><sex>male</sex></student></students>";
		resp.setContentType("text/xml;charset=utf-8");
		resp.getWriter().print(xml);
	}
}
