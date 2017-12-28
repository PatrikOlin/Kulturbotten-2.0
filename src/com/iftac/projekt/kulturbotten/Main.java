package com.iftac.projekt.kulturbotten;

import java.io.FileWriter;
import java.io.IOException;

import com.iftac.projekt.kulturbotten.com.iftac.projekt.kulturbotten.Tweet;
import com.iftac.projekt.kulturbotten.com.iftac.projekt.kulturbotten.TweetDAO;
import com.iftac.projekt.kulturbotten.dbconnection.MuseumConnectionFactory;
import com.iftac.projekt.kulturbotten.twitterbot.TwitterClient;

import twitter4j.TwitterException;

public class Main {

	public static void main(String[] args) throws TwitterException, IOException {

		MuseumConnectionFactory mcf = MuseumConnectionFactory.getInstance();

		TweetDAO tweetDAO = new TweetDAO(mcf);
		tweetDAO.scanCompleteFile();
		tweetDAO.scanCurrentFile();

		TwitterClient twitterClient = new TwitterClient();

		Tweet tweet = null;
		if (tweetDAO.compareLists()) {
			FileWriter writer = new FileWriter("CurrentList.txt");
			writer.write("");
			tweetDAO.scanCurrentFile();
			writer.close();
		}
		do {
			tweet = tweetDAO.createRandomTweet();
		} while (tweetDAO.checkIfTweeted(tweet.getID()));

		twitterClient.readFile("keylist.txt");
		twitterClient.botTweet(tweet);
		
	}

}
