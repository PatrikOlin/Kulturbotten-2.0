package com.iftac.projekt.kulturbotten.twitterbot;

import twitter4j.TwitterException;

public class TwitterBotDriver {

	public static void main(String[] args) throws TwitterException {
		
		TwitterClient twitterClient = new TwitterClient();
		
		twitterClient.readFile("keylist.txt");
		twitterClient.BotTweet("Då testar vi keylisten då");

	}

}
