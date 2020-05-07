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
			xmlHttp.open("POST", "/ajax_01_hello/hello", true);
			// 3.发送请求
			xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlHttp.send("username=zhangsan");
			// 给异步对象的onreadystatechange事件注册监听器
			xmlHttp.onreadystatechange = function() { // 当xmlHttp的状态发生变化时执行
				// 双重判断：xmlHttp的状态为4（服务器响应结束），以及服务器响应的状态码为200（响应成功）
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					// 获取服务器的响应结果
					var text = xmlHttp.responseText;
					// 获取h1元素
					var h1 = document.getElementById("h1");
					h1.innerText = text;
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