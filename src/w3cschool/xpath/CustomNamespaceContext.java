package w3cschool.xpath;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

/*
 * Ҫʹ�������ռ䣬������Ҫ����XPath�������ռ������ģ�NamespaceContext
 * ����һ���ӿ����ͣ�������Ҫ�Զ���ȥʵ����
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
