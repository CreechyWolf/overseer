package overseer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

public class DiscordApiClient
{
	private static final String DISCORD_ENDPOINT = "https://discord.com/api/v10";

	private CloseableHttpClient httpClient;

	public DiscordApiClient()
	{
		httpClient = HttpClientBuilder.create().build();
	}

	public String sendGetMessagesRequest()
	{
		HttpGet getRequest = new HttpGet(DISCORD_ENDPOINT + "/channels/540374208950173729/messages?limit=6");

		return sendRequest(getRequest);
	}
	
	public String sendCreateMessageRequest(String messageToSend)
	{
		HttpPost postRequest = new HttpPost(DISCORD_ENDPOINT + "/channels/540374208950173729/messages");
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("content", messageToSend));

		postRequest.setEntity(new UrlEncodedFormEntity(pairs ));
		
		return sendRequest(postRequest);
	}

	private String sendRequest(final HttpUriRequest request)
	{
		String responseString = "";

		request.addHeader(HttpHeaders.AUTHORIZATION,
				"Bot MTEzNzc2MjYxMjUwMzkyNDgzNg.G_7FLc.n5a8iryydUaho_WCH0z-ZYnFQ62ttR35nkkj40");

		try (final CloseableHttpResponse response = httpClient.execute(request))
		{
			final int statusCode = response.getCode();

			if (statusCode == HttpStatus.SC_OK)
			{
				responseString = EntityUtils.toString(response.getEntity());
			}
		} catch (final IOException | ParseException e)
		{
			// log.error("Error sending request");
		}
		// log.info("Response: " + responseString);
		return responseString;
	}
}
