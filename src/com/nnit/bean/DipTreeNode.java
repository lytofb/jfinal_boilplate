package com.nnit.bean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DipTreeNode {

	private int id;
	
	private String name;
	
	private String url;
	
	private boolean open; //
	
	private boolean checked;

	private Integer pId;
	
	private Map<String,Object> attributes = new HashMap<String, Object>();

	private List<DipTreeNode>  childNodes;
	
	private String icon;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}


	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<DipTreeNode> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(List<DipTreeNode> childNodes) {
		this.childNodes = childNodes;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}



	
	
}
