package com.iftac.projekt.kulturbotten.com.iftac.projekt.kulturbotten;

import java.awt.image.BufferedImage;
import com.google.common.base.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Tweet {

	private int id;
	private String title;
	private String description;
	private BufferedImage image;
	private String imageURL;

	private static Tweet instance;

	private Tweet() {
	}

	public static synchronized Tweet getInstance() {
		if (instance == null) {
			instance = new Tweet();
		}
		return instance;
	}

	@Override
	public String toString() {

		boolean hasImage = false;

		if (!(image == null)) {
			hasImage = true;
		}

		return "Tweet [id=" + id + ", title=" + title + ", description=" + description + ", image=" + hasImage
				+ ", imageURL=" + imageURL + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = Ascii.truncate(description, 200, "...");
	}

	public BufferedImage getPhoto() {

		BufferedImage imageInPng = null;
		try {
			imageInPng = ImageIO.read(new File("cachedImage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return imageInPng;
	}

	public void setPhoto(byte[] b) {

		BufferedImage bImage = null;
		byte[] imageInByte = b;

		try {

			// convert byte to BufferedImage
			InputStream in = new ByteArrayInputStream(imageInByte);
			bImage = ImageIO.read(in);

			ImageIO.write(bImage, "png", new File("cachedImage.png"));

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		this.image = bImage;
	}

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
