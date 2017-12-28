package com.iftac.projekt.kulturbotten.com.iftac.projekt.kulturbotten;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import com.iftac.projekt.kulturbotten.dbconnection.MuseumConnectionFactory;

public class TweetDAO {

	private static TweetDAO instance;

	private ArrayList<Integer> completeList = new ArrayList<Integer>();
	private ArrayList<Integer> currentList = new ArrayList<Integer>();

	static MuseumConnectionFactory mcf = null;

	public TweetDAO(MuseumConnectionFactory mcf) throws FileNotFoundException {
		TweetDAO.mcf = mcf;

	}

	public static synchronized TweetDAO getInstance() throws FileNotFoundException {
		if (instance == null) {
			instance = new TweetDAO(mcf);
		}
		return instance;
	}

	@SuppressWarnings("resource")
	public Tweet createRandomTweet() throws FileNotFoundException {

		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection connection = null;

		ArrayList<Integer> IDs = new ArrayList<>();

		try {

			connection = mcf.getConnection();

			stmt = connection.prepareStatement("Select ID FROM art;");

			rs = stmt.executeQuery();

			while (rs.next()) {
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

		} catch (

		SQLException ex) {
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

	public void scanCompleteFile() throws FileNotFoundException {

		File completeFile = new File("CompleteList.txt");
		Scanner sc = new Scanner(completeFile);
		while (sc.hasNext()) {
			completeList.add(sc.nextInt());
		}
		sc.close();
	}

	public void scanCurrentFile() throws FileNotFoundException {
		File tempFile = new File("CurrentList.txt");
		Scanner sc = new Scanner(tempFile);
		while (sc.hasNext()) {
			currentList.add(sc.nextInt());
		}
		sc.close();
	}

	public boolean checkIfTweeted(int id) throws IOException {

		if (currentList.contains(id)) {
			System.out.println("Nu blev det sant");
			return true;
		}
		currentList.add(id);
		Collections.sort(currentList);
		writeToFile(currentList);

		return false;

	}

	public void writeToFile(ArrayList<Integer> currentList) throws IOException {
		FileWriter writer = new FileWriter("CurrentList.txt");
		for (int i = 0; i < currentList.size(); i++) {
			writer.write(currentList.get(i).toString());
			writer.write(System.lineSeparator());
		}
		writer.close();
	}

	public boolean compareLists() {
		if (Arrays.asList(completeList).equals(Arrays.asList(currentList))) {
			return true;
		}
		return false;
	}

}
