package ajax_01_hello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getJson")
public class Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 * ��ͻ��˷���json�ַ���
		 */
		String str = "{\"name\" : \"zhangsan\",\"age\" : 18,\"sex\" : \"male\"}";
		resp.getWriter().print(str);
		System.out.println(str);
	}

}
