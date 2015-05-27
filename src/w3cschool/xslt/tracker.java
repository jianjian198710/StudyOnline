package w3cschool.xslt;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class tracker {

	private XPath xpath;
	private Document doc;
	
	@Before
	public void init() throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.parse("H:/Developer/Java work space/StudyOnline/src/w3cschool/xslt/tracker.xml");
		XPathFactory xFactory = XPathFactory.newInstance();
		xpath = xFactory.newXPath();
	}
	
	private void show(NodeList nodelist){
		for(int i = 0; i < nodelist.getLength(); i++) {
			System.out.println(nodelist.item(i).getNodeName()+":"+nodelist.item(i).getTextContent());
		}
	}
	
	@Test
	public void test01() throws XPathExpressionException{
		XPathExpression expression = xpath.compile("//function/@name");
		NodeList nodelist = (NodeList) expression.evaluate(doc,XPathConstants.NODESET);
		show(nodelist);
	}

}
