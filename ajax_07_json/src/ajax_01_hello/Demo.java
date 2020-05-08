package ajax_01_hello;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Demo {
	/*
	 * 当map来用
	 */
	@Test
	public void fun1() {
		JSONObject map = new JSONObject();
		map.put("name", "zhangsan");
		map.put("age", 23);
		map.put("sex", "male");
		
		String s = map.toString();
		System.out.println(s);
	}
	/*
	 * 当你已经有了一个person对象时，可以把person转换成person对象
	 */
	@Test
	public void fun2() {
		Person p = new Person("zhangsan",23,"male");
		JSONObject map = JSONObject.fromObject(p);
		System.out.println(map.toString());
	}
	
	/*
	 * JSONArray
	 */
	@Test
	public void fun3() {
		Person p1 = new Person("zhangsan",23,"male");
		Person p2 = new Person("lisi",24,"male");
		
		JSONArray list = new JSONArray();
		list.add(p1);
		list.add(p2);
		
		System.out.println(list.toString());
	}
	
	/*
	 * 原来就有一个List，我们需要把List转换成JSONArray
	 */
	@Test
	public void fun4() {
		Person p1 = new Person("zhangsan",23,"male");
		Person p2 = new Person("lisi",24,"male");
		
		List<Person> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
	
		System.out.println(JSONArray.fromObject(list));
	}
}
