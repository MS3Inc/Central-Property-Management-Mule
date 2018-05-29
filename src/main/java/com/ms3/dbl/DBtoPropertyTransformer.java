package com.ms3.dbl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

import org.mule.api.MuleMessage;
import org.mule.util.CaseInsensitiveHashMap;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class DBtoPropertyTransformer extends AbstractMessageTransformer {

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		
		System.out.println("Class is: "+message.getPayload().getClass().getName());
		LinkedList payload = message.getPayload(java.util.LinkedList.class);
		
		ListIterator list = payload.listIterator();
	
		Map<String,String> props = new HashMap<String,String>();
		while(list.hasNext()){
			CaseInsensitiveHashMap record = (CaseInsensitiveHashMap)list.next();
			String name = record.get("property_name").toString();
			String value = record.get("Property_value").toString();
			props.put(name, value);
		}
		message.setPayload(props);
		return message;
	}

}

