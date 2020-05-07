package cqut.yook.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

public class Demo {

	// 返回javabean集合
	public List<Province> getProviceList() {
		Province p1 = new Province();
		p1.setName("北京");
		p1.addCity(new City("东城区", "DongChengQu"));
		p1.addCity(new City("西城区", "XiChengQu"));

		Province p2 = new Province();
		p2.setName("辽宁");
		p2.addCity(new City("沈阳市", "ShengYangShi"));
		p2.addCity(new City("葫芦岛", "HuluDao"));

		List<Province> provinceList = new ArrayList<>();
		provinceList.add(p1);
		provinceList.add(p2);
		return provinceList;
	}

	@Test
	public void fun1() {
		List<Province> proList = getProviceList();
		/*
		 * 创建XStream对象 调用toXML把集合转换成xml对象
		 */
		XStream xStream = new XStream();
		String xmlStr = xStream.toXML(proList);
		System.out.println(xmlStr);
	}

	/*
	 * 别名（alias） 希望： 默认List对应<List>元素，希望List类型对应<china>元素
	 * 默认Province类型对应<cqut.yook.demo.Province>，希望让它对应<province>
	 * 默认City类型对应<cqut.yook.demo.City>，希望它对应<city>
	 */
	@Test
	public void fun2() {
		List<Province> proList = getProviceList();
		XStream xStream = new XStream();
		// 指定的类型指定别名
		xStream.alias("china", List.class); // 给List类型指定别名为china
		xStream.alias("province", Province.class); // 给Province类型指定别名为province
		xStream.alias("city", City.class); // 给City类型指定别名为city

		String xmlStr = xStream.toXML(proList);
		System.out.println(xmlStr);
	}

	/*
	 * 默认javabean的属性都会生成子元素，而现在希望生成元素的属性
	 */
	@Test
	public void fun3() {
		List<Province> proList = getProviceList();
		XStream xStream = new XStream();
		// 指定的类型指定别名
		xStream.alias("china", List.class); // 给List类型指定别名为china
		xStream.alias("province", Province.class); // 给Province类型指定别名为province
		xStream.alias("city", City.class); // 给City类型指定别名为city

		/*
		 * 把Province类型的name属性，生成<province>元素的属性
		 */
		xStream.useAttributeFor(Province.class, "name");

		String xmlStr = xStream.toXML(proList);
		System.out.println(xmlStr);
	}

	/*
	 * 去除List类型的属性，只把List中的元素生成xml元素
	 */
	@Test
	public void fun4() {
		List<Province> proList = getProviceList();
		XStream xStream = new XStream();
		// 指定的类型指定别名
		xStream.alias("china", List.class); // 给List类型指定别名为china
		xStream.alias("province", Province.class); // 给Province类型指定别名为province
		xStream.alias("city", City.class); // 给City类型指定别名为city

		// 把Province类型的name属性，生成<province>元素的属性
		xStream.useAttributeFor(Province.class, "name");

		// 去除<cities>这样的collection类型的属性
		xStream.addImplicitCollection(Province.class, "cites");

		String xmlStr = xStream.toXML(proList);
		System.out.println(xmlStr);
	}

	/*
	 * 去除不需要的javabean属性 就是让某些javabean属性，不生成对应的xml元素
	 */
	@Test
	public void fun5() {
		List<Province> proList = getProviceList();
		XStream xStream = new XStream();
		// 指定的类型指定别名
		xStream.alias("china", List.class); // 给List类型指定别名为china
		xStream.alias("province", Province.class); // 给Province类型指定别名为province
		xStream.alias("city", City.class); // 给City类型指定别名为city

		// 把Province类型的name属性，生成<province>元素的属性
		xStream.useAttributeFor(Province.class, "name");

		// 去除<cities>这样的collection类型的属性
		xStream.addImplicitCollection(Province.class, "cites");

		// 让city类的description属性不生成对应的xml元素
		xStream.omitField(City.class, "description");

		String xmlStr = xStream.toXML(proList);
		System.out.println(xmlStr);
	}
}
