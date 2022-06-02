package deringo.oneloginapisample;

import java.io.IOException;
import java.util.Base64;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import deringo.configuration.Config;

public class TestMain {
	private static final String ONELOGIN_SUBDOMAIN = Config.get("onelogin.ONELOGIN_SUBDOMAIN");
	
	// OneLogin Administration -> Applications -> select Application, get ID from URL
	private static final String ONELOGIN_APP_ID = Config.get("onelogin.ONELOGIN_APP_ID");
	
	// OneLogin Administration -> Developers -> API Credentials
	private static final String ONELOGIN_CLIENT_ID = Config.get("onelogin.ONELOGIN_API_CLIENT_ID");
	private static final String ONELOGIN_CLIENT_SECRET = Config.get("onelogin.ONELOGIN_API_CLIENT_SECRET");

	
	
	private String accessToken = null;

	public static void main(String[] args) throws Exception {
		TestMain main = new TestMain();

		String accessToken = main.getAccessToken();
		System.out.println("Access Token: " + accessToken);
		
		Integer userId = null;
		Integer roleId = Integer.parseInt( Config.get("onelogin.role_id") );
		userId = main.createUser();
		main.updateUser(userId);
		main.addUserToRole(roleId, userId);
		main.removeUserFromRole(roleId, userId);
		main.deleteUser(userId);
	}
	
	private void removeUserFromRole(Integer roleId, Integer userId) throws Exception {
		String resourceURL = String.format("https://%s.onelogin.com/api/2/roles/%d/users", ONELOGIN_SUBDOMAIN, roleId);
		
		CloseableHttpClient client = HttpClientBuilder.create().build();

		HttpPost request = new HttpPost(resourceURL) {
			@Override
	        public String getMethod() {
				return "DELETE";
			}
		};
		request.setHeader("Authorization", "bearer " + getAccessToken());
		request.addHeader("Content-Type", "application/json");
		
	    StringEntity entity = new StringEntity("[" + userId + "]");
	    request.setEntity(entity);

		try {
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addUserToRole(Integer roleId, Integer userId) throws Exception {
		String resourceURL = String.format("https://%s.onelogin.com/api/2/roles/%d/users", ONELOGIN_SUBDOMAIN, roleId);
		
		CloseableHttpClient client = HttpClientBuilder.create().build();

		HttpPost request = new HttpPost(resourceURL);
		request.setHeader("Authorization", "bearer " + getAccessToken());
		request.addHeader("Content-Type", "application/json");
		
	    StringEntity entity = new StringEntity("[" + userId + "]");
	    request.setEntity(entity);

		try {
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine());
			String content = EntityUtils.toString(response.getEntity());
			System.out.println(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void updateUser(Integer id) throws Exception {
		String resourceURL = String.format("https://%s.onelogin.com/api/2/users/%d", ONELOGIN_SUBDOMAIN, id);
		
		CloseableHttpClient client = HttpClientBuilder.create().build();

		HttpPut request = new HttpPut(resourceURL);
		request.setHeader("Authorization", "bearer " + getAccessToken());
		request.addHeader("Content-Type", "application/json");
		
		JSONObject json = new JSONObject()
				.put("firstname", "Very Happy");
	    StringEntity entity = new StringEntity(json.toString());
	    request.setEntity(entity);

		try {
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine());
			String content = EntityUtils.toString(response.getEntity());
			System.out.println(content);
			JSONObject o = new JSONObject(content);
			System.out.println("User ID: " + o.get("id"));
			System.out.println("Firstname: " + o.get("firstname"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Integer createUser() throws Exception {
		String resourceURL = String.format("https://%s.onelogin.com/api/2/users", ONELOGIN_SUBDOMAIN);
		
		CloseableHttpClient client = HttpClientBuilder.create().build();

		HttpPost request = new HttpPost(resourceURL);
		request.setHeader("Authorization", "bearer " + getAccessToken());
		request.addHeader("Content-Type", "application/json");
		
		JSONObject json = new JSONObject()
				.put("firstname", "Happy")
				.put("lastname", "Gilmore")
				.put("username", "happy.gilmore")
				.put("password", "helloworld123")
				.put("password_confirmation", "helloworld123");
	    StringEntity entity = new StringEntity(json.toString());
	    request.setEntity(entity);

		try {
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine());
			String content = EntityUtils.toString(response.getEntity());
			System.out.println(content);
			JSONObject o = new JSONObject(content);
			System.out.println("new User ID: " + o.get("id"));
			return (Integer) o.get("id"); 
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void deleteUser(Integer userId) throws Exception {
		String resourceURL = String.format("https://%s.onelogin.com/api/2/users/%d", ONELOGIN_SUBDOMAIN, userId);
		
		CloseableHttpClient client = HttpClientBuilder.create().build();

		HttpDelete request = new HttpDelete(resourceURL);
		request.setHeader("Authorization", "bearer " + getAccessToken());
		request.addHeader("Content-Type", "application/json");

		try {
			CloseableHttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void listRoles() {
		String resourceURL = String.format("https://%s.onelogin.com/api/2/roles", ONELOGIN_SUBDOMAIN);

		CloseableHttpClient client = HttpClientBuilder.create().build();

		HttpGet request = new HttpGet(resourceURL);
		request.setHeader("Authorization", "bearer " + getAccessToken());
		request.addHeader("Content-Type", "application/json");

		try {
			CloseableHttpResponse reponse = client.execute(request);

			String content = EntityUtils.toString(reponse.getEntity());

			JSONArray jsonArray = new JSONArray(content);
			jsonArray.forEach(o -> System.out.println(o));
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void listAppUsers() {
		String resourceURL = String.format("https://%s.onelogin.com/api/2/apps/%s/users", ONELOGIN_SUBDOMAIN, ONELOGIN_APP_ID);

		CloseableHttpClient client = HttpClientBuilder.create().build();

		HttpGet request = new HttpGet(resourceURL);
		request.setHeader("Authorization", "bearer " + getAccessToken());
		request.addHeader("Content-Type", "application/json");

		try {
			CloseableHttpResponse reponse = client.execute(request);

			String content = EntityUtils.toString(reponse.getEntity());

			JSONArray jsonArray = new JSONArray(content);
			jsonArray.forEach(o -> System.out.println(o));
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getAccessToken() {
		// simple cache to store the access token.
		// do not use this this way in an productive environment, because the access token can expire
		if (accessToken != null) {
			return accessToken;
		}
		//
		
		CloseableHttpClient client = HttpClientBuilder.create().build();

		HttpPost request = new HttpPost(String.format("https://%s.onelogin.com/auth/oauth2/v2/token", ONELOGIN_SUBDOMAIN));

		String credentials = String.format("%s:%s", ONELOGIN_CLIENT_ID, ONELOGIN_CLIENT_SECRET);
		byte[] encodedAuth = Base64.getEncoder().encode(credentials.getBytes());
		String authHeader = "Basic " + new String(encodedAuth);

		request.setHeader("Authorization", authHeader);
		request.addHeader("Content-Type", "application/json");
		request.setEntity(new StringEntity("{ \"grant_type\": \"client_credentials\" }", "UTF-8"));

		try {
			CloseableHttpResponse reponse = client.execute(request);

			String content = EntityUtils.toString(reponse.getEntity());

			JSONObject json = new JSONObject(content);

			accessToken = json.getString("access_token");
			return accessToken;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
