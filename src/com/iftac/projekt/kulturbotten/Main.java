package com.iftac.projekt.kulturbotten;



import com.iftac.projekt.kulturbotten.dbconnection.MuseumConnectionFactory;

public class Main {

	public static void main(String[] args) {



		
		MuseumConnectionFactory mcf = MuseumConnectionFactory.getInstance();
		
		TweetDAO tweetDAO = new TweetDAO(mcf);
    	
    	System.out.println(tweetDAO.createRandomTweet().toString());
    	
		

		
	
	}

}
