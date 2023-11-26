package overseer;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner
{
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		String botMsg = args[0];
		String channelID = args[1];
		String chatCmd = args[2];
		DiscordMessageProcessor messageProcessor = new DiscordMessageProcessor();

		LocalDateTime lastRunTime = null;
		while(true) 
		{
			messageProcessor.processMessages(lastRunTime, botMsg, channelID, chatCmd);
			lastRunTime = LocalDateTime.now();
			Thread.sleep(10000);
		}
	}
}
