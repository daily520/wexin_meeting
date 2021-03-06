package com.qfjy.project.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.qfjy.po.User;
import com.qfjy.po.Weiuser;
import com.qfjy.project.weixin.api.hitokoto.HitokotoUtil;
import com.qfjy.project.weixin.api.tuling.TulingUtil;
import com.qfjy.service.UserService;
import com.qfjy.service.WeiuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfjy.project.weixin.util.MessageUtil;


import com.qfjy.project.weixin.bean.resp.Article;
import com.qfjy.project.weixin.bean.resp.NewsMessage;
import com.qfjy.project.weixin.bean.resp.TextMessage;
import com.qfjy.project.weixin.main.MenuManager;
@Service
public class CoreService {
	private static final String MSG="请求次数超限制!";

	@Autowired
	private TulingUtil tulingUtil;
	@Autowired
	private HitokotoUtil hitokotoUtil;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private WeiuserService weiuserService;
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id） 下面三行代码是： 从HashMap中取出消息中的字段；
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息 组装要返回的文本消息对象；
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
			// textMessage.setContent("欢迎访问<a
			// href=\"http://www.baidu.com/index.php?tn=site888_pg\">百度</a>!");
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);
			/**
			 * 演示了如何接收微信发送的各类型的消息，根据MsgType判断属于哪种类型的消息；
			 */

			// 接收用户发送的文本消息内容
			String content = requestMap.get("Content");

			// 创建图文消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//respContent=tulingUtil.sendMessage(content);
				respContent="请求次数超限制";

				List<String> list=TulingUtil.getKeys();
				for(String k:list) {
					respContent=tulingUtil.sendMessage(content, k);
					if (MSG.equals(respContent)){
						continue;
					}else {
						break;
					}
				}
				//respContent=tulingUtil.sendMessage(content);
				//respContent = "您发送的是文本消息！";
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					userInfoService.userInfo(fromUserName);
					respContent = "欢迎关注微信公众号";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					//TODO
					if (eventKey.equals("11")) {
						//respContent = "菜单项被点击！";

						List<Article> articleList=new ArrayList<>();
						Article article=new Article();
						Weiuser weiuser=weiuserService.selectByOpenid(fromUserName);
						User user=userService.getUserByWid(weiuser.getId());
						if (user==null){
							article.setTitle("您还未登录");
							article.setDescription("该功能需要完成登录后才可访问,点此登录");
							article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575035866863&di=776e2c26f552f344067f0ec9964ae13e&imgtype=0&src=http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Farchive%2F56b3c6cec8a65133c39f07cc353ee42d37d16818.png");
							article.setUrl(MenuManager.REAL_URL+"user/toLogin?wid="+weiuser.getId());
						}else {
							if (user.getRid()==1){
								article.setTitle("您不是抢单组无法使用此功能");
								article.setDescription("访问需要权限");
								article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575036370587&di=884f594af98dd26f72638033990877d6&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201804%2F05%2F20180405163029_MevJt.jpeg");
								article.setUrl(MenuManager.REAL_URL+"user/toUnauth");
							}else {
								article.setTitle(user.getName()+"欢迎您来抢单");
								article.setDescription("抢单功能教程");
								article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575036541237&di=4bd84b4e182f0e96481d063600d50905&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201804%2F04%2F20180404223315_3tAGi.jpeg");
								article.setUrl(MenuManager.REAL_URL+"meeting/grab?uid="+user.getId());
							}
						}
						articleList.add(article);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respMessage=MessageUtil.newsMessageToXml(newsMessage);
						return respMessage;
					}
					else if (eventKey.equals("70")) {

						List<Article> articleList = new ArrayList<Article>();
						
						// 单图文消息
						Article article = new Article();
						article.setTitle("标题");
						article.setDescription("描述内容");
						article.setPicUrl(
								"图片");
						article.setUrl("跳转连接");

						
						articleList.add(article);						
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换为XML字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						return respMessage;
					}
					//一言api
					else if(eventKey.equals("31")){
						respContent=hitokotoUtil.sendRequest();

					}
				}

			}

			// 组装要返回的文本消息对象；
			textMessage.setContent(respContent);
			// 调用消息工具类MessageUtil将要返回的文本消息对象TextMessage转化成xml格式的字符串；
			respMessage = MessageUtil.textMessageToXml(textMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

}
