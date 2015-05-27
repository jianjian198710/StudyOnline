package w3cschool.xpath;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XpathStudy{
	
	private XPath xpath;
	private Document doc;
	
	@Before
	public void init() throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//Ҫʹ�������ռ� ��namespaceAware���true Ĭ��Ϊfalse
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.parse("H:/Developer/Java work space/StudyOnline/src/w3cschool/xpath/sensorml.xml");
		XPathFactory xFactory = XPathFactory.newInstance();
		xpath = xFactory.newXPath();
		//���ö���������ռ�������
	}
	
	@Test
	public void test() throws XPathException{
		//��Xpath�Դ��������ռ䴦�����Ӹ�Ŀ¼��ȡgml:description ��ǩ��ֵ
		XPathExpression expression = xpath.compile("/*[name()='sml:SensorML']/*[name()='sml:member']/*[name()='sml:System']/*[name()='gml:description']");
		NodeList nodelist = (NodeList) expression.evaluate(doc,XPathConstants.NODESET);
		for(int i = 0; i < nodelist.getLength(); i++) {
			//ʹ��getTextContent()����ȡreturns the text content of this node and its descendants
			System.out.println(nodelist.item(i).getNodeName()+":"+nodelist.item(i).getTextContent());
		}
	}

	@Test
	public void test2() throws XPathException{
		//ֱ�ӻ�ȡȫ��gml:description ��ǩ��ֵ
		XPathExpression expression = xpath.compile("//*[name()='gml:description']");
		NodeList nodelist = (NodeList) expression.evaluate(doc,XPathConstants.NODESET);
		for(int i = 0; i < nodelist.getLength(); i++) {
			System.out.println(nodelist.item(i).getNodeName()+":"+nodelist.item(i).getTextContent());
		}
	}
	
	@Test
	public void test3() throws XPathException{
		//ʹ���Զ��崦��������ռ���CustomNamespaceContext ��ȡsml:identification�µ�����sml:value ��ǩ��ֵ
		xpath.setNamespaceContext(new CustomNamespaceContext());
		XPathExpression expression = xpath.compile("/sml:SensorML/sml:member/sml:System/sml:identification//sml:value");
		NodeList nodelist = (NodeList) expression.evaluate(doc,XPathConstants.NODESET);
		for(int i = 0; i < nodelist.getLength(); i++) {
			System.out.println(nodelist.item(i).getNodeName()+":"+nodelist.item(i).getTextContent());
		}
	}
	
	@Test
	public void test4() throws XPathException{
		//ʹ���Զ��崦��������ռ䷽�� ��ȡsml:identification�µ�sml:Term��definition=urn:ogc:def:identifier:OGC:uniqueID ��ǩ��ֵ
		xpath.setNamespaceContext(new CustomNamespaceContext());
		XPathExpression expression = xpath.compile("/sml:SensorML/sml:member/sml:System/sml:identification//sml:Term[@definition='urn:ogc:def:identifier:OGC:uniqueID']/sml:value");
		NodeList nodelist = (NodeList) expression.evaluate(doc,XPathConstants.NODESET);
		for(int i = 0; i < nodelist.getLength(); i++) {
			System.out.println(nodelist.item(i).getNodeName()+":"+nodelist.item(i).getTextContent());
		}
	}
	
	@Test
	public void test5() throws XPathException{
		//ͬtest4 ������ֵ��ΪString ֱ�ӻ�ȡֵ �����ǻ�ȡ�����ڵ�
		xpath.setNamespaceContext(new CustomNamespaceContext());
		XPathExpression expression = xpath.compile("/sml:SensorML/sml:member/sml:System/sml:identification//sml:Term[@definition='urn:ogc:def:identifier:OGC:uniqueID']/sml:value");
		String value = (String) expression.evaluate(doc,XPathConstants.STRING);
		System.out.println(value);
	}
}
