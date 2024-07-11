package com.nagarro.LibMang.Service;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.nagarro.LibMang.service.LoginService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.StatusLine;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

	@Mock
	RestTemplate restTemplateMock;

	@InjectMocks
	LoginService loginService;

	@BeforeEach
	public void setup() {
	  MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testCheckLogin_SuccessfulLogin() throws Exception {
	  // Mock response body
	  String responseBody = "{\"uname\":\"adarshgupta\" , \"pass\": \"12345\"}";
	
	  // Mock ResponseEntity
	  ResponseEntity<String> mockResponseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
	  
	  // Create a mock HttpEntity
	  HttpHeaders headers = new HttpHeaders();
	  HttpEntity<String> entity = new HttpEntity(headers);
	  
	  when(restTemplateMock.exchange(
	  		"http://localhost:8090/users/adarshgupta",
	          HttpMethod.GET,
	          null,
	          String.class))
	      .thenReturn(mockResponseEntity);
	  
	
	  // Call the method
	  boolean result = loginService.checkLogin("adarshgupta", "12345");
	
	  // Verify the result
	  assertTrue(result, "Expected login to be successful");
	
	  // Verify restTemplate method call
	  verify(restTemplateMock, times(1)).exchange(
	  		"http://localhost:8090/users/adarshgupta",
	          HttpMethod.GET,
	          null,
	          String.class);
	}
//}
//public class LoginServiceTest {

//    @Mock
//    private CloseableHttpClient httpClient;
//
//    @InjectMocks
//    private LoginService loginService;
//
//
//    @Test
//    public void testCheckLogin_SuccessfulLogin() throws Exception {
//        String jsonResponse = "{\"uname\":\"adarshgupta\", \"pass\": \"12345\"}";
//
//        CloseableHttpResponse mockResponse = mock(CloseableHttpResponse.class);
//        HttpEntity mockEntity = mock(HttpEntity.class);
//        StatusLine mockStatusLine = mock(StatusLine.class);
//
//        when(mockResponse.getStatusLine()).thenReturn(mockStatusLine);
//        when(mockStatusLine.getStatusCode()).thenReturn(200);
////        when(mockResponse.getEntity()).thenReturn(mockEntity);
////        when(mockEntity.getContent()).thenReturn(IOUtils.toInputStream(responseBody, StandardCharsets.UTF_8));
//
//        // Mock HttpClient behavior
//        when(httpClient.execute(any(HttpGet.class))).thenReturn(mockResponse);
//
//        // Call the method under test
//        boolean result = loginService.checkLogin("adarshgupta", "12345");
//
//        // Verify the result
//        assertTrue(result, "Expected login to be successful");
//
//        // Verify HttpClient method call
//        verify(httpClient, times(1)).execute(any(HttpGet.class));
//
//        // Optionally, verify other interactions if needed
//        verify(mockResponse, times(1)).getStatusLine();
//        verify(mockResponse, times(1)).getEntity();
//    }

//    @Test
//    public void testCheckLogin_InvalidPassword() throws Exception {
//        String jsonResponse = "{\"uname\":\"adarshgupta\", \"pass\": \"wrongpass\"}";
//
//        HttpGet request = new HttpGet("http://localhost:8090/users/adarshgupta");
//        StatusLine statusLine = mock(StatusLine.class);
//        when(statusLine.getStatusCode()).thenReturn(200);
//        when(mockResponse.getStatusLine()).thenReturn(statusLine);
//        when(mockResponse.getEntity()).thenReturn(new org.apache.http.entity.StringEntity(jsonResponse));
//
//        when(httpClient.execute(request)).thenReturn(mockResponse);
//
//        boolean result = loginService.checkLogin("adarshgupta", "12345");
//
//        assertFalse(result, "Expected login to be unsuccessful");
//        verify(httpClient, times(1)).execute(request);
//    }
//
//    @Test
//    public void testCheckLogin_UserNotFound() throws Exception {
//        HttpGet request = new HttpGet("http://localhost:8090/users/nonexistent");
//        StatusLine statusLine = mock(StatusLine.class);
//        when(statusLine.getStatusCode()).thenReturn(404);
//        when(mockResponse.getStatusLine()).thenReturn(statusLine);
//
//        when(httpClient.execute(request)).thenReturn(mockResponse);
//
//        boolean result = loginService.checkLogin("nonexistent", "12345");
//
//        assertFalse(result, "Expected login to be unsuccessful due to user not found");
//        verify(httpClient, times(1)).execute(request);
//    }
//
//    @Test
//    public void testCheckLogin_ExceptionThrown() throws Exception {
//        HttpGet request = new HttpGet("http://localhost:8090/users/adarshgupta");
//        when(httpClient.execute(request)).thenThrow(new IOException("Connection failed"));
//
//        boolean result = loginService.checkLogin("adarshgupta", "12345");
//
//        assertFalse(result, "Expected login to be unsuccessful due to an exception");
//        verify(httpClient, times(1)).execute(request);
//    }
}


//public class LoginServiceTest {


	
//    RestTemplate restTemplateMock = new RestTemplate();
//
//    LoginService loginService = new LoginService(restTemplateMock);
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testCheckLogin_SuccessfulLogin() throws Exception {
//        // Mock response body
//        String responseBody = "{\"uname\":\"adarshgupta\" , \"pass\": \"12345\"}";
//
//        // Mock ResponseEntity
//        ResponseEntity<String> mockResponseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
//        
//        // Create a mock HttpEntity
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<String> entity = new HttpEntity(headers);
//        
//        when(restTemplateMock.exchange(
//        		"http://localhost:8090/users/adarshgupta",
//                HttpMethod.GET,
//                null,
//                String.class))
//            .thenReturn(mockResponseEntity);
//
//        // Call the method
//        boolean result = loginService.checkLogin("adarshgupta", "12345");
//
//        // Verify the result
//        assertFalse(result, "Expected login to be successful");
//
//        // Verify restTemplate method call
////        verify(restTemplateMock, times(1)).exchange(
////        		"http://localhost:8090/users/adarshgupta",
////                HttpMethod.GET,
////                null,
////                String.class);
//    }
//}




//package com.nagarro.LibMang.Service;
//
//import org.junit.jupiter.api.BeforeEach;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//
//import com.nagarro.LibMang.entities.Book;
//import com.nagarro.LibMang.service.LoginService;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//
//public class LoginServiceTest {
//
//    RestTemplate restTemplateMock = new RestTemplate() ;
//    
//    LoginService loginService = new LoginService(restTemplateMock);
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testCheckLogin_SuccessfulLogin() throws Exception {
//        // Mock response body
//        String responseBody = "[{\"uname\":\"adarshgupta\"}, {\"pass\": \"12345\"}]";
//
//        // Mock ResponseEntity
//        ResponseEntity<String> mockResponseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
//        when(restTemplateMock.exchange("http://localhost:8090/users/adarshgupta", HttpMethod.GET, HttpEntity.class, String.class))
//        .thenReturn(mockResponseEntity);
//
//        // Call the method
//        boolean result = loginService.checkLogin("adarshgupta", "12345");
//
//        // Verify the result
//        assertTrue(result, "Expected login to be successful");
//
//        // Verify restTemplate method call
//        verify(restTemplateMock, times(1)).exchange("http://localhost:8090/users/adarshgupta", HttpMethod.GET, HttpEntity.class, String.class);
//    }    }

    // Additional test
