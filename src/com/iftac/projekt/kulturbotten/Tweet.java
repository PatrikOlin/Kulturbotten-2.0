package com.iftac.projekt.kulturbotten;

import java.awt.image.BufferedImage;

public class Tweet {
	
	
	@Override
	public String toString() {
		return "Tweet [id=" + id + ", title=" + title + ", description=" + description + ", image=" + image
				+ ", imageURL=" + imageURL + "]";
	}

	private int id;
	private String title;
	private String description;
	private BufferedImage image;
	private String imageURL;
	
	private static Tweet instance;
	



private Tweet(){}
    
    public static synchronized Tweet getInstance(){
        if(instance == null){
            instance = new Tweet();
        }
        return instance;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BufferedImage getImage() {
		return image;
	}

//	public void setPhoto(byte b) {
//		
//		
//		
//		this.image = b;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}
