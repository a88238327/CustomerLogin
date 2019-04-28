package entity;

public class shouyeCarousel {

	private String href;
	private String src;
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
	public shouyeCarousel(String href,String src) {
		this.href=href;
		this.src=src;
	}
}
