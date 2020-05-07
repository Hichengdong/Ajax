package ajax_01_hello;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

@WebServlet("/ProvinceServlet")
public class ProvinceServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		/*
		 * 获取省份名称，加载该省对应的<province>元素! 把元素转换成字符串发送给客户端
		 */

		try {
			SAXReader reader = new SAXReader();
			InputStream input = this.getClass().getResourceAsStream("/china.xml");
			Document doc = reader.read(input);
			
			// 查询所有province的name属性，得到一个属性对象
			List<Node> arrList = doc.selectNodes("//province/@name");
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i<arrList.size();i++) {
				sb.append(arrList.get(i).getText());
				if(i<arrList.size()-1) {
					sb.append(",");
				}
			}
			resp.getWriter().print(sb);
		} catch (DocumentException e) {
			throw new RuntimeException();
		}

	}

}
