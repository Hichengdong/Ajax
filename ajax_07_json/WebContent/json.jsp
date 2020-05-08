<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 点击按钮后，把服务器响应的数据显示到h3 -->
	<button id="btn">点击这里</button>
	<h1>Json之Hello World</h1>
	<h3 id="h3"></h3>
</body>

<script>
	window.onload = function() {
		// 获取btn元素
		var btn = document.getElementById("btn");
		btn.onclick = function() {
			// 得到request
			var xmlHttp = createXMLHttpRequest();
			// 连接
			xmlHttp.open("get", "/ajax_01_hello/getJson", true)
			// 发送
			xmlHttp.send(null);
			// 添加监听
			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					var text = xmlHttp.responseText;
					var person = eval("(" + text + ")");
					var s = person.name + "," + person.age + "," + person.sex;
					document.getElementById("h3").innerHTML = s;
				}
			}
		};

		// var str = "{\"name\" : \"zhangsan\",\"age\" : 18,\"sex\" : \"male\"}";
		// var person = eval("(" + str + ")")
		// alert(person.name + "," + person.age + "," + person.sex);
	}

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

	function ajax(/*请求方式*/method,/*请求url*/url,/*是否异步*/asyn,/*请求体*/params,/*回调方法*/
			callback,/*服务响应数据转换成什么类型*/type) {
		var xmlHttp = createXMLHttpRequest();
		xmlHttp.open(method, url, asyn);
		if ("POST".equlas(method.toUpperCase())) {
			xmlHttp.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
		}
		xmlHttp.send(params);

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var data;
				if ("XML".equals(type.toUpperCase())) {
					data = xmlHttp.responseXML;
				} else if ("TEXT".equals(type.toUpperCase())) {
					data = xmlHttp.responseText;
				} else if ("JSON".equals(type.toUpperCase())) {
					var text = xmlHttp.responseText;
					data = eval("(" + text + ")");
				}
				callback(data);
			}
		}
	}
</script>
</html>