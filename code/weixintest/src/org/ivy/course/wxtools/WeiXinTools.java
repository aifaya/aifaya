package org.ivy.course.wxtools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.output.XMLOutputter;

import org.ivy.course.wxtools.parser.WxMsgKit;
import org.ivy.course.wxtools.vo.recv.WxRecvMsg;
import org.ivy.course.wxtools.vo.send.WxSendMsg;


public final class WeiXinTools {
	
	public static boolean access(String token,String signature,String timestamp,String nonce) {
		List<String> ss = new ArrayList<String>();
		ss.add(timestamp);
		ss.add(nonce);
		ss.add(token);
		
		Collections.sort(ss);
		
		StringBuilder builder = new StringBuilder();
		for(String s : ss) {
			builder.append(s);
		}
		return signature.equalsIgnoreCase(HashKit.sha1(builder.toString()));
	}
	
	public static WxRecvMsg recv(InputStream in) throws JDOMException, IOException {
		return WxMsgKit.parse(in);
	}
	
	public static void send(WxSendMsg msg,OutputStream out) throws JDOMException,IOException {
		Document doc = WxMsgKit.parse(msg);
		if(null != doc) {
			new XMLOutputter().output(doc, out);
		} else {
			Logger.getAnonymousLogger().warning("鍙戦�娑堟伅鏃�瑙ｆ瀽鍑篸om涓虹┖ msg :"+msg);
		}
	}
	
	public static WxSendMsg builderSendByRecv(WxRecvMsg msg) {
		WxRecvMsg m = new WxRecvMsg(msg);
		String from = m.getFromUser();
		m.setFromUser(m.getToUser());
		m.setToUser(from);
		m.setCreateDt((System.currentTimeMillis() / 1000) + "");
		return new WxSendMsg(m);
	}
	
	
	
}
