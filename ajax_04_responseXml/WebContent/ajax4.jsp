<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<button id="btn">点击这里</button>
	<h1 id="h1"></h1>
</body>

<script>
	window.onload = function() {
		var btn = document.getElementById("btn");
		btn.onclick = function() { // 给按钮点击事件注册监听
			/*
				ajax四步操作，得到服务器的响应
				把响应结果显示到h1元素中
			 */

			// 1.得到异步对象
			var xmlHttp = createXMLHttpRequest();
			// 2.打开服务器连接
			xmlHttp.open("GET", "/ajax_01_hello/xmlHello", true);
			// 3.发送请求
			xmlHttp.send(null);
			// 给异步对象的onreadystatechange事件注册监听器
			xmlHttp.onreadystatechange = function() { // 当xmlHttp的状态发生变化时执行
				// 双重判断：xmlHttp的状态为4（服务器响应结束），以及服务器响应的状态码为200（响应成功）
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					// 获取服务器的响应结果(xml)
					var doc = xmlHttp.responseXML;
					console.log(doc);
					console.log(xmlHttp.responseText);
					// 查找文档下名为student的所有元素，得到数组，再取下标0元素
					var ele = doc.getElementsByTagName("student")[0];
					var number = ele.getAttribute("number"); // 获取属性名为number的属性值
					var name, age, sex;
					if (window.addEventListener) {
						// 其他浏览器
						name = ele.getElementsByTagName("name")[0].textContent;
						age = ele.getElementsByTagName("age")[0].textContent;
						sex = ele.getElementsByTagName("sex")[0].textContent;
					} else {
						// IE浏览器
						name = ele.getElementsByTagName("name")[0].text;
						age = ele.getElementsByTagName("age")[0].text;
						sex = ele.getElementsByTagName("sex")[0].text;
					}
					var text = number + "," + name + "," + age + "," + sex;
					document.getElementById("h1").innerText = text;
				}
			}
		}
	};

	// 创建异步对象
	function createXMLHttpRequest() {
		try {
			return new XMLHttpRequest(); // 大多数浏览器
		} catch (e) {
			try {
				return new ActiveXObject("Msxml12.XMLHTTP"); // IE6.0
			} catch (e) {
				try {
					return new ActiveXObject("Microsoft.XMLHttp"); // IE5.5及更早
				} catch (e) {
					throw e;
				}
			}
		}
	}
</script>
</html>