package compiler_project.artifact_id;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class ChatGPTClient {
	// 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

	private static final String API_KEY = "API Key would go here";
	private static final String API_URL = "https://api.openai.com/v1/completions";

	private HttpClient httpClient;
	private HttpPost httpPost;

	public ChatGPTClient() {
		this.httpClient = HttpClients.createDefault();
		this.httpPost = new HttpPost(API_URL);
		this.httpPost.addHeader("Authorization", "Bearer " + API_KEY);
		this.httpPost.addHeader("Content-Type", "application/json");
	}

	public void executeRequestAndPrintResponse(String jsonRequest) {
		HttpResponse response = null; // Declare the HttpResponse variable

		try {
			// Add the "model" parameter to the JSON request
			jsonRequest += ", \"model\": \"text-davinci-003\"";

			httpPost.setEntity(new StringEntity(jsonRequest));
			response = httpClient.execute(httpPost); // Assign the response value

			// Get the response body as a String
			String responseBody = EntityUtils.toString(response.getEntity());

			// Print the response body to the console
			System.out.println(responseBody);
		} catch (Exception e) {
			// Log the exception instead of just printing the stack trace
			System.err.println("An error occurred while executing the request: " + e.getMessage());
		} finally {
			// Ensure that the response entity is properly closed
			if (response != null) {
				EntityUtils.consumeQuietly(response.getEntity());
			}
		}
	}

	public static void main(String[] args) {
		ChatGPTClient se = new ChatGPTClient();
		se.executeRequestAndPrintResponse("{\"prompt\": \"Once upon a time\", \"max_tokens\": 50}");
		
	}
}
