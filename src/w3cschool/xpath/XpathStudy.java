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
		//要使用命名空间 把namespaceAware设成true 默认为false
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.parse("H:/Developer/Java work space/StudyOnline/src/w3cschool/xpath/sensorml.xml");
		XPathFactory xFactory = XPathFactory.newInstance();
		xpath = xFactory.newXPath();
		//配置定义的命名空间上下文
	}
	
	@Test
	public void test() throws XPathException{
		//用Xpath自带的命名空间处理函数从根目录获取gml:description 标签的值
		XPathExpression expression = xpath.compile("/*[name()='sml:SensorML']/*[name()='sml:member']/*[name()='sml:System']/*[name()='gml:description']");
		NodeList nodelist = (NodeList) expression.evaluate(doc,XPathConstants.NODESET);
		for(int i = 0; i < nodelist.getLength(); i++) {
			//使用getTextContent()来获取returns the text content of this node and its descendants
			System.out.println(nodelist.item(i).getNodeName()+":"+nodelist.item(i).getTextContent());
		}
	}

	@Test
	public void test2() throws XPathException{
		//直接获取全部gml:description 标签的值
		XPathExpression expression = xpath.compile("//*[name()='gml:description']");
		NodeList nodelist = (NodeList) expression.evaluate(doc,XPathConstants.NODESET);
		for(int i = 0; i < nodelist.getLength(); i++) {
			System.out.println(nodelist.item(i).getNodeName()+":"+nodelist.item(i).getTextContent());
		}
	}
	
	@Test
	public void test3() throws XPathException{
		//使用自定义处理的命名空间类CustomNamespaceContext 获取sml:identification下的所有sml:value 标签的值
		xpath.setNamespaceContext(new CustomNamespaceContext());
		XPathExpression expression = xpath.compile("/sml:SensorML/sml:member/sml:System/sml:identification//sml:value");
		NodeList nodelist = (NodeList) expression.evaluate(doc,XPathConstants.NODESET);
		for(int i = 0; i < nodelist.getLength(); i++) {
			System.out.println(nodelist.item(i).getNodeName()+":"+nodelist.item(i).getTextContent());
		}
	}
	
	@Test
	public void test4() throws XPathException{
		//使用自定义处理的命名空间方法 获取sml:identification下的sml:Term的definition=urn:ogc:def:identifier:OGC:uniqueID 标签的值
		xpath.setNamespaceContext(new CustomNamespaceContext());
		XPathExpression expression = xpath.compile("/sml:SensorML/sml:member/sml:System/sml:identification//sml:Term[@definition='urn:ogc:def:identifier:OGC:uniqueID']/sml:value");
		NodeList nodelist = (NodeList) expression.evaluate(doc,XPathConstants.NODESET);
		for(int i = 0; i < nodelist.getLength(); i++) {
			System.out.println(nodelist.item(i).getNodeName()+":"+nodelist.item(i).getTextContent());
		}
	}
	
	@Test
	public void test5() throws XPathException{
		//同test4 将返回值设为String 直接获取值 而不是获取整个节点
		xpath.setNamespaceContext(new CustomNamespaceContext());
		XPathExpression expression = xpath.compile("/sml:SensorML/sml:member/sml:System/sml:identification//sml:Term[@definition='urn:ogc:def:identifier:OGC:uniqueID']/sml:value");
		String value = (String) expression.evaluate(doc,XPathConstants.STRING);
		System.out.println(value);
	}
}
