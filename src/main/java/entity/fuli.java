package entity;

import org.apache.commons.collections.map.HashedMap;

public class fuli {

	private String href;
	private String src;
	private String title;
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public fuli(String href,String src,String title) {
		this.href=href;
		this.src=src;
		this.title=title;
	}
}
