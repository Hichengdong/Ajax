package cqut.yook.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

public class Demo {

	// ����javabean����
	public List<Province> getProviceList() {
		Province p1 = new Province();
		p1.setName("����");
		p1.addCity(new City("������", "DongChengQu"));
		p1.addCity(new City("������", "XiChengQu"));

		Province p2 = new Province();
		p2.setName("����");
		p2.addCity(new City("������", "ShengYangShi"));
		p2.addCity(new City("��«��", "HuluDao"));

		List<Province> provinceList = new ArrayList<>();
		provinceList.add(p1);
		provinceList.add(p2);
		return provinceList;
	}

	@Test
	public void fun1() {
		List<Province> proList = getProviceList();
		/*
		 * ����XStream���� ����toXML�Ѽ���ת����xml����
		 */
		XStream xStream = new XStream();
		String xmlStr = xStream.toXML(proList);
		System.out.println(xmlStr);
	}

	/*
	 * ������alias�� ϣ���� Ĭ��List��Ӧ<List>Ԫ�أ�ϣ��List���Ͷ�Ӧ<china>Ԫ��
	 * Ĭ��Province���Ͷ�Ӧ<cqut.yook.demo.Province>��ϣ��������Ӧ<province>
	 * Ĭ��City���Ͷ�Ӧ<cqut.yook.demo.City>��ϣ������Ӧ<city>
	 */
	@Test
	public void fun2() {
		List<Province> proList = getProviceList();
		XStream xStream = new XStream();
		// ָ��������ָ������
		xStream.alias("china", List.class); // ��List����ָ������Ϊchina
		xStream.alias("province", Province.class); // ��Province����ָ������Ϊprovince
		xStream.alias("city", City.class); // ��City����ָ������Ϊcity

		String xmlStr = xStream.toXML(proList);
		System.out.println(xmlStr);
	}

	/*
	 * Ĭ��javabean�����Զ���������Ԫ�أ�������ϣ������Ԫ�ص�����
	 */
	@Test
	public void fun3() {
		List<Province> proList = getProviceList();
		XStream xStream = new XStream();
		// ָ��������ָ������
		xStream.alias("china", List.class); // ��List����ָ������Ϊchina
		xStream.alias("province", Province.class); // ��Province����ָ������Ϊprovince
		xStream.alias("city", City.class); // ��City����ָ������Ϊcity

		/*
		 * ��Province���͵�name���ԣ�����<province>Ԫ�ص�����
		 */
		xStream.useAttributeFor(Province.class, "name");

		String xmlStr = xStream.toXML(proList);
		System.out.println(xmlStr);
	}

	/*
	 * ȥ��List���͵����ԣ�ֻ��List�е�Ԫ������xmlԪ��
	 */
	@Test
	public void fun4() {
		List<Province> proList = getProviceList();
		XStream xStream = new XStream();
		// ָ��������ָ������
		xStream.alias("china", List.class); // ��List����ָ������Ϊchina
		xStream.alias("province", Province.class); // ��Province����ָ������Ϊprovince
		xStream.alias("city", City.class); // ��City����ָ������Ϊcity

		// ��Province���͵�name���ԣ�����<province>Ԫ�ص�����
		xStream.useAttributeFor(Province.class, "name");

		// ȥ��<cities>������collection���͵�����
		xStream.addImplicitCollection(Province.class, "cites");

		String xmlStr = xStream.toXML(proList);
		System.out.println(xmlStr);
	}

	/*
	 * ȥ������Ҫ��javabean���� ������ĳЩjavabean���ԣ������ɶ�Ӧ��xmlԪ��
	 */
	@Test
	public void fun5() {
		List<Province> proList = getProviceList();
		XStream xStream = new XStream();
		// ָ��������ָ������
		xStream.alias("china", List.class); // ��List����ָ������Ϊchina
		xStream.alias("province", Province.class); // ��Province����ָ������Ϊprovince
		xStream.alias("city", City.class); // ��City����ָ������Ϊcity

		// ��Province���͵�name���ԣ�����<province>Ԫ�ص�����
		xStream.useAttributeFor(Province.class, "name");

		// ȥ��<cities>������collection���͵�����
		xStream.addImplicitCollection(Province.class, "cites");

		// ��city���description���Բ����ɶ�Ӧ��xmlԪ��
		xStream.omitField(City.class, "description");

		String xmlStr = xStream.toXML(proList);
		System.out.println(xmlStr);
	}
}
