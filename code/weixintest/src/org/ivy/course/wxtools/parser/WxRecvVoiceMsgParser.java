package org.ivy.course.wxtools.parser;

import org.jdom.Element;
import org.jdom.JDOMException;

import org.ivy.course.wxtools.vo.recv.WxRecvMsg;
import org.ivy.course.wxtools.vo.recv.WxRecvVoiceMsg;

public class WxRecvVoiceMsgParser extends WxRecvMsgBaseParser {

	@Override
	protected WxRecvVoiceMsg parser(Element root, WxRecvMsg msg) throws JDOMException {
		String event = getElementText(root, "Event");
		String eventKey = getElementText(root, "EventKey");
		
		return new WxRecvVoiceMsg(msg, event,eventKey);
	}

}
