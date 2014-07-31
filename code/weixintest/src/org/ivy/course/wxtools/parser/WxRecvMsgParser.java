package org.ivy.course.wxtools.parser;

import org.jdom.Document;
import org.jdom.JDOMException;

import org.ivy.course.wxtools.vo.recv.WxRecvMsg;

public interface WxRecvMsgParser {
	WxRecvMsg parser(Document doc) throws JDOMException;
}
