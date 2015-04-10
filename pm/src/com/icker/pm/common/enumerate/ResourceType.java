package com.icker.pm.common.enumerate;

/**
 * 文件资源类型
 * @author Icker
 *
 */
public enum ResourceType {
	DEMAND_DOCUMENT("1", "需求文档"),
	DESIGN_DOCUMENT("2", "设计文档"),
	PLAN_DOCUMENT("3", "策划文档"),
	TECHNOLOGY_SHARING("4", "技术分享"),
	CODE_SHARING("5", "代码分享"),
	REFERENCE("6", "参考文献"),
	UNCLASSIFIED("0", "其他");
	
	private String type;
	private String typeName;
	
	private ResourceType(String type, String typeName) {
		this.type = type;
		this.typeName = typeName;
	}

	/**
	 * 根据type获取typeName
	 * @param type
	 * @return typeName 返回键
	 */
	public static String getTypeName(String type) {
		ResourceType[] resourceTypes = ResourceType.values();
		for (ResourceType resourceType : resourceTypes) {
			if(resourceType.getType().equals(type))
				return resourceType.getTypeName();
		}
		return null;
	}
	
	/**
	 * 根据typeName获取type
	 * @param typeName
	 * @return type 返回值
	 */
	public static String getType(String typeName) {
		ResourceType[] resourceTypes = ResourceType.values();
		for (ResourceType resourceType : resourceTypes) {
			if(typeName.equals(resourceType.getTypeName()))
				return resourceType.getType();
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
