package com.icker.pm.common.enumerate;

/**
 * 写字板类型
 * @author Icker
 *
 */
public enum DiscussType {
	DEMAND_DOCUMENT("1", "需求文档"),
	DESIGN_DOCUMENT("2", "设计文档"),
	CUSTOMER_SERVICE("3", "客户服务"),
	TECHNOLOGY_SHARING("4", "技术分享"),
	UNCLASSIFIED("0", "未分类");
	
	private String type;
	private String typeName;
	
	private DiscussType(String type, String typeName) {
		this.type = type;
		this.typeName = typeName;
	}

	/**
	 * 根据type获取typeName
	 * @param type
	 * @return typeName 返回键
	 */
	public static String getTypeName(String type) {
		DiscussType[] discussTypes = DiscussType.values();
		for (DiscussType discussType : discussTypes) {
			if(discussType.getType().equals(type))
				return discussType.getTypeName();
		}
		return null;
	}
	
	/**
	 * 根据typeName获取type
	 * @param typeName
	 * @return type 返回值
	 */
	public static String getType(String typeName) {
		DiscussType[] discussTypes = DiscussType.values();
		for (DiscussType discussType : discussTypes) {
			if(typeName.equals(discussType.getTypeName()))
				return discussType.getType();
		}
		return null;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@Override
	public String toString() {
		return "KeyValue [typeName = "+ typeName +", type = " + type + "]";
	}
}
