<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<select id="p" name="province">
		<option>===请选择省份===</option>
	</select>

	<select id="c" name="city">
		<option>===请选择城市===</option>
	</select>
</body>

<script>
	/*
	 * 在文档加载完毕时，发送请求，得到所有省份名称，现在在<select name="province"/>中
	 * 在选择了新的省份时，发送请求（参数为省的名称），得到xml文档，即<province>元素
	 * 解析xml文档，得到其中所有的<city>，再得到每个<city>元素的内容，即城市名，使用城市名生成<option>，插入到<select name="city">元素中
	 */
	window.onload = function() {
		// 1.得到异步对象
		var xmlHttp = createXMLHttpRequest();
		// 2.打开服务器连接
		xmlHttp.open("GET", "/ajax_01_hello//ProvinceServlet", true);
		// 3.发送请求
		xmlHttp.send(null);
		// 给异步对象的onreadystatechange事件注册监听器
		xmlHttp.onreadystatechange = function() { // 当xmlHttp的状态发生变化时执行
			// 双重判断：xmlHttp的状态为4（服务器响应结束），以及服务器响应的状态码为200（响应成功）
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				// 获取服务器的响应结果(xml)
				var text = xmlHttp.responseText;
				var arr = text.split(",");
				for (var i = 0; i < arr.length; i++) {
					var op = document.createElement("option");
					op.value = arr[i];
					var textNode = document.createTextNode(arr[i]);
					op.appendChild(textNode);
					document.getElementById("p").appendChild(op);
				}
			}
		}

		// 给<select name="province">添加改变监听器
		// 使用选择的省份名称取请求CityServer，得到<province>元素
		// 获取<province>元素中的所有<city>元素，遍历，获取每个<city>的文本内容
		// 使用每个城市的名称创建<option>元素添加到<select name="city">

		var proSelect = document.getElementById("p");
		proSelect.onchange = function() {
			var citySelect = document.getElementById("c");
			// citySelect.innerHTML = "<option>===请选择城市===</option>";
			// 获取所有子元素
			var optionEleList = citySelect.getElementsByTagName("option");
			// 循环遍历每个option元素，然后再citySelect中移除
			while (optionEleList.length > 1) {
				citySelect.removeChild(optionEleList[1]); // 总是删除1下标，因为1删除了，2就变成了1下标
			}

			// 1.得到异步对象
			var xmlHttp = createXMLHttpRequest();
			// 2.打开服务器连接
			xmlHttp.open("POST", "/ajax_01_hello//CityServlet", true);
			xmlHttp.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			// 3.发送请求
			xmlHttp.send("pname=" + proSelect.value); // 把下拉列表中选择的值发送给服务器！
			// 给异步对象的onreadystatechange事件注册监听器
			xmlHttp.onreadystatechange = function() { // 当xmlHttp的状态发生变化时执行
				// 双重判断：xmlHttp的状态为4（服务器响应结束），以及服务器响应的状态码为200（响应成功）
				if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					// 获取服务器的响应结果(xml)
					var doc = xmlHttp.responseXML;
					// 得到所有名为City的元素
					var cityEleList = doc.getElementsByTagName("city");
					// 循环遍历每个city元素
					for (var i = 0; i < cityEleList.length; i++) {
						// 得到每个city元素
						var cityEle = cityEleList[i];
						var cityName;
						// 处理浏览器差异
						if (window.addEventListener) {
							cityName = cityEle.textContent; // 其他浏览器
						} else {
							cityName = cityEle.text; // IE浏览器
						}

						// 使用城市名称创建option元素，添加到<select name="city">中
						var op = document.createElement("option");
						op.value = cityName;
						var textNode = document.createTextNode(cityName);
						op.appendChild(textNode);
						document.getElementById("c").appendChild(op);
					}
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