package ohm.softa.a13.tweets.generators;

import com.google.gson.Gson;
import ohm.softa.a13.model.Tweet;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class OfflineTweetStreamGenerator implements TweetStreamGenerator {
	@Override
	public Stream<Tweet> getTweetStream() {
		var offlineTweetsLocation = getClass().getResourceAsStream("trump_tweets.json");
		var sb = new StringBuilder();
		byte[] bytes = null;
		try {
			bytes = offlineTweetsLocation.readAllBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		for (byte b : bytes) {
			sb.append((char) b);
		}

		var gson = new Gson();
		Tweet[] tweets = gson.fromJson(sb.toString(), Tweet[].class);
		// return Stream.of(tweets);
		return Arrays.stream(tweets);
	}
}
