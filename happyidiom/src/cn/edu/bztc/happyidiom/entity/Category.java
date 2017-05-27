package cn.edu.bztc.happyidiom.entity;

public class Category {
	private String name;
	private int imageId;
	public Category(String name, int imageId) {
		super();
		this.name = name;
		this.imageId = imageId;
	}
	public String getName() {
		return name;
	}
	public int getImageId() {
		return imageId;
	}
}
