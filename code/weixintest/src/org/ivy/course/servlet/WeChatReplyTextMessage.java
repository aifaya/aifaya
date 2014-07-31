package org.ivy.course.servlet;

public class WeChatReplyTextMessage {
	 	private String toUserName; 
	    private String fromUserName; 
	    private String createTime; 
	    private String messageType; 
	    private String content; 
	    
	 /*   public static WeChatTextMessage getWeChatLocationMessage(String xml){ 
	        XStream xstream = new XStream(new DomDriver()); 
	        WeChatLocationMessage  message = null; 
	        xstream.alias("xml", WeChatLocationMessage.class); 
	        xstream.aliasField("ToUserName", WeChatLocationMessage.class, "toUserName"); 
	        xstream.aliasField("FromUserName", WeChatLocationMessage.class, "fromUserName"); 
	        xstream.aliasField("CreateTime", WeChatLocationMessage.class, "createTime"); 
	        xstream.aliasField("MsgType", WeChatLocationMessage.class, "msgType"); 
	        xstream.aliasField("Location_X", WeChatLocationMessage.class, "locationx"); 
	        xstream.aliasField("Location_Y", WeChatLocationMessage.class, "localtiony"); 
	        xstream.aliasField("Scale", WeChatLocationMessage.class, "scale"); 
	        xstream.aliasField("Label", WeChatLocationMessage.class, "label"); 
	        xstream.aliasField("MsgId", WeChatLocationMessage.class, "msgId"); 
	        message = (WeChatLocationMessage)xstream.fromXML(xml); 
	        return message; 
	    } */
	    public void setMessageType(String xml)
	    {
	    	messageType =  xml;
	    }

	    public void setCreateTime(String xml)
	    {
	    	createTime =  xml;
	    }
	    public void setContent(String xml)
	    {
	    	content =  xml;
	    }
	    public void setToUserName(String xml)
	    {
	    	toUserName =  xml;
	    }

	    public void setFromUserName(String xml)
	    {
	    	fromUserName =  xml;
	    }
	    
}
