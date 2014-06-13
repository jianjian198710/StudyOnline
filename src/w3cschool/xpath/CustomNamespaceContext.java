package w3cschool.xpath;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

/*
 * 要使用命名空间，我们需要设置XPath的命名空间上下文：NamespaceContext
 * 这是一个接口类型，我们需要自定义去实现它
 */
public class CustomNamespaceContext implements NamespaceContext{

	public String getNamespaceURI(String prefix){
		if(prefix.equals("sml")){
			return "http://www.opengis.net/sensorML/1.0.1";
		}else if(prefix.equals("swe")){
			return "http://www.opengis.net/swe/1.0.1";
		}else if(prefix.equals("gml")){
			return "http://www.opengis.net/gml";
		}else{
			return XMLConstants.NULL_NS_URI;
		}
	}

	public String getPrefix(String namespaceURI){
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator<Object> getPrefixes(String namespaceURI){
		// TODO Auto-generated method stub
		return null;
	}

}
