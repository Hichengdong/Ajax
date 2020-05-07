package ajax_01_hello;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/xml;charset=utf-8");
		/*
		 * ��ȡʡ�����ƣ����ظ�ʡ��Ӧ��<city>Ԫ��! ��Ԫ�ط��͸��ͻ���
		 */

		try {
			SAXReader reader = new SAXReader();
			InputStream input = this.getClass().getResourceAsStream("/china.xml");
			Document doc = reader.read(input);

			// ��ȡʡ������
			String pname = req.getParameter("pname");
			Element proEle = (Element) doc.selectSingleNode("//province[@name='" + pname + "']"); // province[@name='beijing']
			String xmlStr = proEle.asXML(); // ��Ԫ��ת��Ϊ�ַ���
			resp.getWriter().print(xmlStr);
		} catch (DocumentException e) {
			throw new RuntimeException();
		}
	}

}
