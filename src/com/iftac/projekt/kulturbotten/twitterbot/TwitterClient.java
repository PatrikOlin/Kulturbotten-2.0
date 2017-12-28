package com.iftac.projekt.kulturbotten.twitterbot;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.iftac.projekt.kulturbotten.com.iftac.projekt.kulturbotten.Tweet;

public class TwitterClient {

	private String consumerKey = null;
	private String consumerSecret = null;
	private String accessToken = null;
	private String accessTokenSecret = null;

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	public void readFile(String filename) {
		try {
			Scanner reader = new Scanner(new File(filename));

			while (reader.hasNextLine()) {
				setConsumerKey(reader.next());
				setConsumerSecret(reader.next());
				setAccessToken(reader.next());
				setAccessTokenSecret(reader.next());
			}

			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void botTweet(Tweet tweet) throws TwitterException, IOException {

		File outputFile = new File("cachedImage.png");
		ImageIO.write(tweet.getPhoto(), "png", outputFile);
		TwitterFactory twitterFactory = new TwitterFactory();

		Twitter twitter = twitterFactory.getInstance();

		twitter.setOAuthConsumer(getConsumerKey(), getConsumerSecret());
		twitter.setOAuthAccessToken(new AccessToken(getAccessToken(), getAccessTokenSecret()));

		StatusUpdate statusUpdate = new StatusUpdate(tweet.getTitle().toUpperCase() + "\n\n" + tweet.getDescription() + "\n\n" + tweet.getImageURL());

		statusUpdate.setMedia(outputFile);

		Status status = twitter.updateStatus(statusUpdate);

		System.out.println("status.toString() = " + status.toString());
		System.out.println("status.getInReplyToScreenName() = " + status.getInReplyToScreenName());
		System.out.println("status.getSource() = " + status.getSource());
		System.out.println("status.getText() = " + status.getText());

		System.out.println("status.getURLEntities() = " + Arrays.toString(status.getURLEntities()));
		System.out.println("status.getUserMentionEntities() = " + Arrays.toString(status.getUserMentionEntities()));
	}
}
