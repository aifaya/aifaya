package org.ivy.course.wxtools.parser;

import org.jdom.Element;
import org.jdom.JDOMException;

import org.ivy.course.wxtools.vo.recv.WxRecvMsg;
import org.ivy.course.wxtools.vo.recv.WxRecvPicMsg;

public class WxRecvPicMsgParser extends WxRecvMsgBaseParser {

	@Override
	protected WxRecvPicMsg parser(Element root, WxRecvMsg msg) throws JDOMException {
		return new WxRecvPicMsg(msg, getElementText(root, "PicUrl"));
	}

}
