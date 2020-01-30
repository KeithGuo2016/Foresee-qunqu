package com.foresee.utils;

/**
 * 查询条件枚举
 * @author xujunqing
 *
 */
public enum QueryCondition {
	
	/**等于*/				equal,
	/**不等于*/				not_equal,
	/**大于*/				large,
	/**大于等于*/			large_equal,
	/**小于*/				small,
	/**小于等于*/			small_equal,
	/**左右like(%xxx%)*/		like_anywhere,
	/**左like(xxx%)*/		like_start,
	/**右like(%xxx)*/		like_end,
	/**包含 需要“,”分割 */				in,
	/**不包含 需要“,”分割*/				not_in,
	/**是否为空(当参数值为1时为空,0时为非空)*/							if_null,
	/**为空*/							is_null,
	/**非空*/							not_null,
	/**在什么之间(参数值需要“,”分割)*/			between,
	/**不在什么之间(参数值需要“,”分割)*/	not_between
}
