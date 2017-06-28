package com.po;
/**
 * 栏目类
 * 
 *
 */
public class NewsType {
	private int typeId;
	//栏目名称
	private String typeName;
	//创建者
	private String creator;
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
}
