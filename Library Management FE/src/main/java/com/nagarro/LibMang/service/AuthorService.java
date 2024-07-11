package com.nagarro.LibMang.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.LibMang.entities.Author;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.LinkedList;
import java.util.List;

@Component
public class AuthorService {

	@Autowired
    private RestTemplate restTemplate;

    public List<Author> retrieveAuthors() {
        String url = "http://localhost:8090/author";
        String inline = restTemplate.getForObject(url, String.class);

        System.out.println("\nJSON Response in String format");
        System.out.println(inline);
        
        // Check if the response is null
        if (inline == null) {
            return null;
        }

        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonarr = (JSONArray) parser.parse(inline);

            List<Author> allAuthors = new LinkedList();
            for (int i = 0; i < jsonarr.size(); i++) {
                JSONObject jsonobj = (JSONObject) jsonarr.get(i);
                String name = (String) jsonobj.get("name");

                Author author = new Author();
                author.setName(name);

                allAuthors.add(author);
            }

            return allAuthors;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}





























//@Component
//public class AuthorService {
//
//	public List<Author> retrieveAuthors() {
//
//		// inline will store the JSON data streamed in string format
//		String inline = "";
//
//		try {
//			URL url = new URL("http://localhost:8090/author");
//			// Parse URL into HttpURLConnection in order to open the connection in order to
//			// get the JSON data
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			// Set the request to GET or POST as per the requirements
//			conn.setRequestMethod("GET");
//			// Use the connect method to create the connection bridge
//			conn.connect();
//			// Get the response status of the Rest API
//			int responsecode = conn.getResponseCode();
//			System.out.println("Response code is: " + responsecode);
//
//			// Iterating condition to if response code is not 200 then throw a runtime
//			// exception
//			// else continue the actual process of getting the JSON data
//			if (responsecode != 200)
//				throw new RuntimeException("HttpResponseCode: " + responsecode);
//			else {
//				// Scanner functionality will read the JSON data from the stream
//				Scanner sc = new Scanner(url.openStream());
//				while (sc.hasNext()) {
//					inline += sc.nextLine();
//				}
//				System.out.println("\nJSON Response in String format");
//				System.out.println(inline);
//				// Close the stream when reading the data has been finished
//				sc.close();
//			}
//
//			// JSONParser reads the data from string object and break each data into key
//			// value pairs
//			JSONParser parse = new JSONParser();
//			// Type caste the parsed json data in json object
//			JSONArray jsonarr = (JSONArray) parse.parse(inline);
//
//			// Get data for Results array
//			List<Author> allAuthor = new LinkedList<Author>();
//			for (int i = 0; i < jsonarr.size(); i++) {
//				// Store the JSON objects in an array
//				// Get the index of the JSON object and print the values as per the index
//				JSONObject jsonobj = (JSONObject) jsonarr.get(i);
//
//				String name = (String) jsonobj.get("name");
//
//				Author author = new Author();
//				
//				author.setName(name);
//
//				allAuthor.add(author);
//
//			}
//
//			return allAuthor;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//}
