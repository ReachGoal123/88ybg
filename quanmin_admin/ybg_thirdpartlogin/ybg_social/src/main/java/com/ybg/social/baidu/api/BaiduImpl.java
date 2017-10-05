package com.ybg.social.baidu.api;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ybg.social.qq.api.QQUserInfo;
import net.sf.json.JSONObject;

public class BaiduImpl extends AbstractOAuth2ApiBinding implements Baidu {
	
	private static final String	URL_GET_OPENID		= "https://openapi.baidu.com/rest/2.0/passport/users/getLoggedInUser";
	private static final String	URL_GET_USERINFO	= "https://openapi.baidu.com/rest/2.0/passport/users/getInfo";
	private String				appId;
	private String				uid;
	private ObjectMapper		objectMapper		= new ObjectMapper();
	
	public BaiduImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);
		System.out.println(result);
		this.uid = StringUtils.substringBetween(result, "\"uid\":\"", "\"}");
	}
	
	@Override
	public BaiduUserInfo getUserInfo(String uid) {
		// String url = String.format(URL_GET_USERINFO, appId, uid);
		// Map<String,Object> parms= new HashMap<>();
		// parms.put("", value)
		String result = getRestTemplate().postForObject(URL_GET_USERINFO, null, String.class);
		System.out.println(result);
		try {
			return (BaiduUserInfo) JSONObject.toBean(JSONObject.fromObject(result), BaiduUserInfo.class);
			// userInfo.setUserid(Long.parseLong(uid));
		} catch (Exception e) {
			throw new RuntimeException("获取用户信息失败", e);
		}
	}
}
