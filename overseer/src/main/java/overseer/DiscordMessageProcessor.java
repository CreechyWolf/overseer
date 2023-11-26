package overseer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

public class DiscordMessageProcessor
{
	private DiscordApiClient client;

	public DiscordMessageProcessor()
	{
		client = new DiscordApiClient();
	}

	public void processMessages(LocalDateTime lastRunTime) throws Exception
	{
		// get messages from discord
		String messages = client.sendGetMessagesRequest();
		// check for specific message

		JSONArray jsonArray = new JSONArray(messages);

		for (int i = 0; i < jsonArray.length(); i++)
		{
			JSONObject message = jsonArray.getJSONObject(i);
			String msgContent = message.getString("content");
			System.out.print(msgContent);
			LocalDateTime msgTime = LocalDateTime.parse(message.getString("timestamp").substring(0, 19),
					DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
			System.out.println(" at " + msgTime);
			if (msgContent.equals("/alert") && (lastRunTime == null || msgTime.isAfter(lastRunTime)))
			{
				client.sendCreateMessageRequest("ALERT HAS BEEN ISSUED");
			}
		}
	}
}
