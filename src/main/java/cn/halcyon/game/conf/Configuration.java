package cn.halcyon.game.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Configuration {
	private static Map<String, Object> cfg = new HashMap<String, Object>();
	
	static {
		init();
	}
	
	private Configuration(){}
	
	private static void init() {
		parseDocument("/configurations.xml");
	}
	
	@SuppressWarnings("unchecked")
	private static void parseConfiguration(Element element) {
		String id = element.attributeValue("id");
		Iterator<Element> it = (Iterator<Element>)element.elementIterator();
		while (it.hasNext()) {
			Element e = (Element)it.next(); 
			String eName = e.getName();
			if ("property".equals(eName)) {
				cfg.put(id+"."+e.attributeValue("name"), e.getTextTrim());
			}
			else {
				
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void parseDocument(String cfgPath) {
		SAXReader reader = new SAXReader(); 
		InputStream is = Configuration.class.getResourceAsStream(cfgPath);
		try {
			Document document = reader.read(is);
			Element root = document.getRootElement(); 
			Iterator<Element> it = (Iterator<Element>)root.elementIterator();
			while (it.hasNext()) {
				Element e = (Element) it.next(); 
				parseConfiguration(e);
			}
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	public static String getSqlDialect() {
		return (String)cfg.get("global.sql.dialect");
	}
	
	public static String getConnectionXml() {
		return (String)cfg.get("global.connection.xml");
	}
}