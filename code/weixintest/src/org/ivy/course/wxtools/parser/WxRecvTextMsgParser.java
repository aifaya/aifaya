package org.ivy.course.wxtools.parser;

import org.jdom.Element;
import org.jdom.JDOMException;

import org.ivy.course.wxtools.vo.recv.WxRecvMsg;
import org.ivy.course.wxtools.vo.recv.WxRecvTextMsg;

public class WxRecvTextMsgParser extends WxRecvMsgBaseParser{

	@Override
	protected WxRecvTextMsg parser(Element root, WxRecvMsg msg) throws JDOMException {
		return new WxRecvTextMsg(msg, getElementText(root, "Content"));
	}

	
}
