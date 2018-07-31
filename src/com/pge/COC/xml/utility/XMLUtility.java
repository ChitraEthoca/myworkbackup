package com.pge.COC.xml.utility;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This method is used used to read xml Validation
 * 
 * @author Lakshmikandan, Markandayaiyer L5MQ
 *
 */
public class XMLUtility {

	private String intervalEndTime, meterValue;
	
	public HashMap<String, String> readXML(String filename){
		HashMap<String, String> map = new HashMap<>();
		
		try{			
			File fXmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("MeasurementValue");
			System.out.println("----------------------------" + nList.getLength());
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					intervalEndTime = eElement.getElementsByTagName("intervalEndTime").item(0).getTextContent();
					meterValue = eElement.getElementsByTagName("meterValue").item(0).getTextContent();
					map.put(intervalEndTime + temp, meterValue);
					System.out.println(intervalEndTime);
					System.out.println(meterValue);
				}
			}

		}catch(Exception e){
			
		}		
		return map;		
	} 
}
