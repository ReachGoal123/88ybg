/**
 * 
 */
package com.ybg.social.qq.connet;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import com.ybg.social.qq.api.QQ;
import com.ybg.social.qq.api.QQUserInfo;

/** @author zhailiang */
public class QQAdapter implements ApiAdapter<QQ> {
	
	// 测试API是否可用
	@Override
	public boolean test(QQ api) {
		return true;
	}
	
	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		values.setDisplayName(userInfo.getNickname());// 昵称
		values.setImageUrl(userInfo.getFigureurl_qq_1());// 用户头像
		values.setProfileUrl(null);// 个人主页，不一定有。
		values.setProviderUserId(userInfo.getOpenId());// 服务商的用户ID ,QQ 的是QQid // 这个异常需要捕获处理 处理成运行时异常
	}
	
	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub 绑定 解绑
		return null;
	}
	
	@Override
	public void updateStatus(QQ api, String message) {
		// do noting
	}
}
