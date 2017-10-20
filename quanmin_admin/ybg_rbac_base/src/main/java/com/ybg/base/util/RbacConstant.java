package com.ybg.base.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ybg.rbac.user.domain.UserVO;

/** 系统常量类 **/
public class RbacConstant {
	
	/** 超管角色 **/
	public static final String	ROLE_ADMIN					= "1";
	/** 非超管角色 **/
	public static final String	ROLE_OTHER					= "10";
	/** 最小密码长度 **/
	public static final int		MIN_PASSWORD_LENTH			= 7;
	/** 目录类型 **/
	public static final String	RESOURCE_MENU				= "0";
	/** 系统默认 授权资源ID **/
	public static final String	RESOURCE_DEFAULT_PARENTID	= "0";
	
	/** 判断当前角色是超管 **/
	public static boolean IsAdmin(UserVO user) {
		if (user != null && user.getRoleid().equals(ROLE_ADMIN)) {
			return true;
		}
		return false;
	}
	
	/** 判断当前角色是非超管 **/
	public static boolean IsOther(UserVO user) {
		if (user != null && user.getRoleid().equals(ROLE_OTHER)) {
			return true;
		}
		return false;
	}
	
	/** 判断当前角色是企业管理员 **/
	public boolean IsCompanyAdmin(UserVO user) {
		if (user != null && user.getRoleid().equals("")) {
			return true;
		}
		return false;
	}
	
	/** 判断当前角色是企业员工 **/
	public boolean IsCompanyEmployee(UserVO user) {
		if (user != null && user.getRoleid().equals("")) {
			return true;
		}
		return false;
	}
	
	private RbacConstant() {
		// 禁止实例化
	}
	
	/** 获取加密后的密码 **/
	public static String getpwd(String pwd) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(pwd);
	}
}
