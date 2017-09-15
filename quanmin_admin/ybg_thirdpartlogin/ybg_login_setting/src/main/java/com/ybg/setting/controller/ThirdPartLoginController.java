package com.ybg.setting.controller;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baidu.oauth.controllor.BaiduConfig;
import com.baidu.oauth.service.BaiduUserService;
import com.ybg.api.domain.WeixinOAuthConfig;
import com.ybg.api.service.WeixinApiService;
import com.ybg.base.util.Json;
import com.ybg.mayun.oauth.controller.MayunConfig;
import com.ybg.mayun.oauth.service.MayunUserService;
import cn.sina.service.WeiboUserService;
import io.swagger.annotations.Api;
import weibo4j.util.WeiboConfig;

@Api(tags = "第三方登陆设置项")
@Controller
@RequestMapping("/thirdoartlogin_do/")
public class ThirdPartLoginController {
	
	@Autowired
	WeiboUserService	weiboUserService;
	@Autowired
	BaiduUserService	baiduUserService;
	@Autowired
	WeixinApiService	weixinApiService;
	@Autowired
	MayunUserService	mayunUserService;
	
	@RequestMapping(value = "index.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "/thirdpartlogin/setting";
	}
	
	@ResponseBody
	@RequestMapping(value = "info.do", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> info() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("sina", weiboUserService.getSetting());
		map.put("mayun", mayunUserService.getSetting());
		map.put("weixin", weixinApiService.getSetting());
		map.put("baidu", baiduUserService.getSetting());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "update.do", method = { RequestMethod.GET, RequestMethod.POST })
	public Json update(String mayunid, String mayunSERCRET, String mayunurl, String baiduid, String baiduSERCRET, String baiduurl, String sinaid, String sinaSERCRET, String sinaurl, String weixinid, String weixinSERCRET) {
		Json j = new Json();
		j.setMsg("操作成功");
		weiboUserService.updateSetting(sinaid, sinaSERCRET, sinaurl);
		mayunUserService.updateSetting(mayunid, mayunSERCRET, mayunurl);
		weixinApiService.updateSetting(weixinid, weixinSERCRET);
		baiduUserService.updateSetting(baiduid, baiduSERCRET, baiduurl);
		WeiboConfig.reflushProperties();
		MayunConfig.reflushProperties();
		WeixinOAuthConfig.reflushProperties();
		BaiduConfig.reflushProperties();
		j.setSuccess(true);
		return j;
	}
}
