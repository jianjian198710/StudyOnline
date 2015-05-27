package w3cschool.xslt;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class java_xslt {

	private TransformerFactory tFactory = TransformerFactory.newInstance();
	private Transformer transformer;
	private XPath xpath;
	private Document doc;
	private static final String FILE_PATH="H:/Developer/Java work space/StudyOnline/src/w3cschool/xslt/";
	
	
	
	@Before
	public void init() throws TransformerConfigurationException, ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.parse("H:/Developer/Java work space/StudyOnline/src/w3cschool/xslt/tracker.xml");
		XPathFactory xFactory = XPathFactory.newInstance();
		xpath = xFactory.newXPath();
		transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource(FILE_PATH+"tracker.xsl"));	
	}
	
	private void show(NodeList nodelist){
		for(int i = 0; i < nodelist.getLength(); i++) {
			System.out.println(nodelist.item(i).getNodeName()+":"+nodelist.item(i).getTextContent());
		}
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("Finish!");
	}

	@Test
	public void test01() throws FileNotFoundException, TransformerException {
		transformer.setParameter("PAGE", "oab");
		transformer.transform(new javax.xml.transform.stream.StreamSource(FILE_PATH+"tracker.xml"),
				new javax.xml.transform.stream.StreamResult(new FileOutputStream(FILE_PATH+"tracker.html")));
	}
	
	@Test
	public void test02() throws XPathExpressionException, FileNotFoundException, TransformerException{
		ArrayList<String> blocklist = new ArrayList<String>();
		XPathExpression expression = xpath.compile("/blocks/block/@name");
		NodeList nodelist = (NodeList) expression.evaluate(doc,XPathConstants.NODESET);
		show(nodelist);
		for(int i=0;i<nodelist.getLength();i++){
			blocklist.add(nodelist.item(i).getTextContent());
		}
		for(String block:blocklist){
			transformer.setParameter("PAGE",block);
			transformer.transform(new javax.xml.transform.stream.StreamSource(FILE_PATH+"tracker.xml"),
					new javax.xml.transform.stream.StreamResult(new FileOutputStream(FILE_PATH+block+".html")));
		}
	}


}
