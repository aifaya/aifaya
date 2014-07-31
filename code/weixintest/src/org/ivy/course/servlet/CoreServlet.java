
package org.ivy.course.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.PrintWriter;
import org.apache.commons.logging.Log; 

import org.apache.commons.logging.LogFactory;  

import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.ivy.course.wxtools.WeiXinTools;
import org.ivy.course.wxtools.vo.recv.WxRecvMsg;
import org.ivy.course.wxtools.vo.recv.WxRecvTextMsg;
import org.ivy.course.wxtools.vo.send.WxSendMsg;
import org.ivy.course.wxtools.vo.send.WxSendTextMsg;
import org.jdom.JDOMException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;


/**
 * Servlet implementation class CoreServlet
 */
@WebServlet("/CoreServlet")
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CoreServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		// TODO Auto-generated method stub
		String signature = request.getParameter("signature");  
        // 时间戮  
        String timestamp = request.getParameter("timestamp");  
        // 随机数  
        String nonce = request.getParameter("nonce");  
        // 随机字符串  
        String echostr = request.getParameter("echostr");   
          
        PrintWriter out = response.getWriter();  
        // 通过检验 signature 对请求进行校验，若校验成功则原样返回 echostr，表示接入成功，否则接入失败  
       if(SignUtil.checkSignature(signature, timestamp, nonce)){  
           out.print(echostr);  
       }  
  
       out.close();  
       out = null; */
		  // TODO 为了简单起见,先不对消息来源进行校验
		PrintWriter out = response.getWriter();  
		out.print("aaa");  
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter pw = response.getWriter();
//        String echo = request.getParameter("echostr");
//        echo = new String(echo.getBytes("ISO-8859-1"),"UTF-8");
//        pw.println(echo);

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    Log log = LogFactory.getLog(this .getClass());   

	    log.info( "into doPost "+ request.getMethod() +" + "+ request.getInputStream() );   
	    
	    WxRecvMsg msg;
		try {
			msg = WeiXinTools.recv(request.getInputStream());
			WxSendMsg sendMsg = WeiXinTools.builderSendByRecv(msg);
			
			WxRecvTextMsg recvMsg = (WxRecvTextMsg) msg;
			// 鐢ㄦ埛杈撳叆鐨勫唴瀹�
			String text = recvMsg.getContent().trim();
			sendMsg = new WxSendTextMsg(sendMsg, "test");
			WeiXinTools.send(sendMsg, response.getOutputStream());
			return;
			
		} catch (JDOMException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

//	    response.setContentType("text/html;charset=UTF-8");
//        PrintWriter pw = response.getWriter();
//        String wxMsgXml = IOUtils.toString(request.getInputStream(),"utf-8");
//        WeChatTextMessage textMsg = null;
//        try {
//            textMsg = getWeChatTextMessage(wxMsgXml);
//            log.info("textMsg:" + textMsg);
//        } catch (Exception e) {
//        	log.info("textMsg exception" + e.getMessage());
//            e.printStackTrace();
//        }
//        log.info("91:");
//        StringBuffer replyMsg = new StringBuffer();
//        if(textMsg != null){
//            //增加你所需要的处理逻辑，这里只是简单重复消息
//            replyMsg.append("您给我的消息是：");
//            log.info("textMsg getContent." + textMsg.getContent());
//            replyMsg.append(textMsg.getContent());
//        }
//        else{
//        	log.info("textMsg is Null");
//            replyMsg.append(":)不是文本的消息，我暂时看不懂");
//        }
//        log.info("start to getReplyTextMessage");
//        String returnXml = getReplyTextMessage(replyMsg.toString(), textMsg.getFromUserName());
//        returnXml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content></xml>";
//        pw.println(returnXml);

	    log.info( "out doPost" );   
	}

	private WeChatTextMessage getWeChatTextMessage(String xml){
		Log log = LogFactory.getLog(this.getClass());
		log.info("Enter in getWeChatTextMessage, xml" + xml);
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("xml", WeChatTextMessage.class);
        xstream.aliasField("ToUserName", WeChatTextMessage.class, "toUserName");
        xstream.aliasField("FromUserName", WeChatTextMessage.class, "fromUserName");
        xstream.aliasField("CreateTime", WeChatTextMessage.class, "createTime");
        xstream.aliasField("MsgType", WeChatTextMessage.class, "messageType");
        xstream.aliasField("Content", WeChatTextMessage.class, "content");
        xstream.aliasField("MsgId", WeChatTextMessage.class, "msgId");
        WeChatTextMessage wechatTextMessage = (WeChatTextMessage)xstream.fromXML(xml); 
        return wechatTextMessage;
    }
    
    private String getReplyTextMessage(String content, String weChatUser){
    	Log log = LogFactory.getLog(this.getClass());   
    	log.info( "enter in getReplyTextMessage. content:"+ content +" + weChatUser:"+ weChatUser);   
        WeChatReplyTextMessage we = new WeChatReplyTextMessage();
        we.setMessageType("text");
        we.setCreateTime(new Long(new Date().getTime()).toString());
        we.setContent(content);
        we.setToUserName(weChatUser);
        we.setFromUserName("shanghaiweather");//TODO 你的公众帐号微信号
        XStream xstream = new XStream(new DomDriver()); 
        xstream.alias("xml", WeChatReplyTextMessage.class);
        xstream.aliasField("ToUserName", WeChatReplyTextMessage.class, "toUserName");
        xstream.aliasField("FromUserName", WeChatReplyTextMessage.class, "fromUserName");
        xstream.aliasField("CreateTime", WeChatReplyTextMessage.class, "createTime");
        xstream.aliasField("MsgType", WeChatReplyTextMessage.class, "messageType");
        xstream.aliasField("Content", WeChatReplyTextMessage.class, "content");
        xstream.aliasField("FuncFlag", WeChatReplyTextMessage.class, "funcFlag");
        String xml =xstream.toXML(we);
        log.info("exit getReplyTextMessage.xml=" +xml); 
        return xml;
    }
  

}
