package com.iftac.projekt.kulturbotten;



import java.io.IOException;

import com.iftac.projekt.kulturbotten.dbconnection.MuseumConnectionFactory;
import com.iftac.projekt.kulturbotten.twitterbot.TwitterBotDriver;
import com.iftac.projekt.kulturbotten.twitterbot.TwitterClient;

import twitter4j.TwitterException;

public class Main {

	public static void main(String[] args) throws TwitterException, IOException {



		
		MuseumConnectionFactory mcf = MuseumConnectionFactory.getInstance();
		
		TweetDAO tweetDAO = new TweetDAO(mcf);
		
		TwitterClient twitterClient = new TwitterClient();
		
		Tweet outputTweet = tweetDAO.createRandomTweet();
		
		twitterClient.readFile("keylist.txt");
		twitterClient.BotTweet(outputTweet);
    	
    	System.out.println(tweetDAO.createRandomTweet().toString());
    	
		

		
	
	}

}
