package com.foresee.finals;


import java.util.HashMap;
/**
 * 静态参数设置类
 * @author xujunqing
 *
 */
public class BasicsFinal {
	
		
		// 请求处理成功
		public static final String SUCCESS = "success";
		// 请求处理失败
		public static final String FAILED = "failed";
		// 请求处理异常
		public static final String EXCEPTION = "error";
		// 登出处理
		public static final String LOGINOUT = "loginOut";
		// 登陆处理
		public static final String LOGINING = "logining";
		// session超时
		public static final String SESSIONOUTTIME = "sessionOutTime";
		
		/**=====权限控制CRUD=====**/
		public static final int C = 1;// 添加    0001
		public static final int R = 2;// 查询  0010 
		public static final int U = 4;// 修改    0100
		public static final int D = 8;// 删除  1000
		/**数据库查询需要的连接符*/
		//排序标示
		public static final String ORDER_BY="orderstr";
		//倒序
		public static final String DESC="desc";
		//默认排序
		public static final String ASC="asc";
		//等于
		public static final String EQ="=";
		//不等于
		public static final String NOT_EQ="!=";
		//大于
		public static final String GT=">";
		//大于等于
		public static final String GE=">=";
		//小于
		public static final String LT="<";
		//小于等于
		public static final String LE="<=";
		//等于空值
		public static final String IS_NULL="is null";
		//非空
		public static final String IS_NOT_NULL="is not null";
		//like
		public static final String LIKE="like";
		//like  开始于
		public static final String BEGIN_LIKE="begin like";
		//like 结束于
		public static final String END_LIKE="end like";
		//in
		public static final String IN="in";
		//not in
		public static final String NOT_IN="not in";
		//between x and y
		public static final String BETWEEN="between";
		//not between x and y
		public static final String NOT_BETWEEN="not between";
		
		public static final  String WX_URL =  "https://api.weixin.qq.com/sns/jscode2session";
		//public static final  String APPID =  "wxeb93e9ab4a099073";
		//public static final  String SECRET =  "5e364a2f17303db040f5106f9df531d8";
		public static final  String APPID =  "wx83e14beaccf10316";
		public static final  String SECRET =  "59aa0de8e41b2dcf638a364834e97f6f";
		
		//public static final  String WX_USER_FACE = "F:/xiaochengxu/wx-user-face";
		//public static final  String WX_CONTRIBUTE_ICON = "F:/xiaochengxu/wx-contribute-icon";
		public static final  String WX_USER_FACE = "/root/img";
		public static final  String WX_CONTRIBUTE_ICON = "/root/img";
		
		
		/**
		 * 数据字典数据保存对象
		 * 通过BaseFinal.dictionaryMap.get("字典类型编码")获取此类型下的字典值集合
		 */
		//public static HashMap<String, List<DictionaryContent>> dictionaryMap = new HashMap<String, List<DictionaryContent>>();
		/**
		 * 系统参数配置,保存的值为sysconfig.properties文件中的内容和表sen_params中的内容
		 * 通过BaseFinal.systemConfig.get("xxx.xx.x")的方式获取系统配置参数值
		 */
		public static HashMap<String, String> systemConfig=new HashMap<String, String>();
		//public static List<Module> modulelist = new ArrayList<Module>();
}
