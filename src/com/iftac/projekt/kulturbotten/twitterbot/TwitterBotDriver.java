package com.iftac.projekt.kulturbotten.twitterbot;

import com.iftac.projekt.kulturbotten.Tweet;

import twitter4j.TwitterException;

public class TwitterBotDriver {

	public static void main(String[] args) throws TwitterException {

		TwitterClient twitterClient = new TwitterClient();
		
		
		twitterClient.readFile("keylist.txt");

	}

}
