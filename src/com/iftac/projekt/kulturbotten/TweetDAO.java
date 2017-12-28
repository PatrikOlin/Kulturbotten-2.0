package com.iftac.projekt.kulturbotten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.iftac.projekt.kulturbotten.dbconnection.MuseumConnectionFactory;


public class TweetDAO {
	
	private static TweetDAO instance;
	
	static MuseumConnectionFactory mcf = null;
	
	public TweetDAO(MuseumConnectionFactory mcf) {
		TweetDAO.mcf = mcf;
		
	}
	
    public static synchronized TweetDAO getInstance(){
        if(instance == null){
            instance = new TweetDAO(mcf);
        }
        return instance;
    }
	
	@SuppressWarnings("resource")
	public Tweet createRandomTweet() {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection connection = null;
		
		ArrayList<Integer> IDs = new ArrayList<>();
		

		try {

			connection = mcf.getConnection();
			
			stmt = connection.prepareStatement("Select ID FROM art;");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				IDs.add(rs.getInt("ID"));
				
			}
			
			
			Random randomGenerator = new Random();
			int index = randomGenerator.nextInt(IDs.size());
	        int randomID = IDs.get(index);

			stmt = connection.prepareStatement("Select * FROM art WHERE ID = ?;");

			stmt.setInt(1, randomID);

			rs = stmt.executeQuery();
			
			Tweet t = Tweet.getInstance();

			while (rs.next()) {
				
				t.setPhoto(rs.getBytes("photo"));
				t.setTitle(rs.getString("title"));
				t.setDescription(rs.getString("description"));
				t.setID(rs.getInt("id"));
				t.setImageURL(rs.getString("url"));
				
				
				
			}

			
			return t;

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally

		{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}

}
