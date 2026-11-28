package net.raphimc.minecraftauth.util;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.lenni0451.commons.httpclient.requests.impl.GetRequest;
import net.raphimc.minecraftauth.MinecraftAuth;

public class TimeUtil {
   private static Duration CLIENT_TIME_OFFSET = null;
   public static final int MAX_JWT_CLOCK_SKEW = 60;

   public static synchronized Duration getClientTimeOffset() {
      if (CLIENT_TIME_OFFSET == null) {
         HttpClient httpClient = MinecraftAuth.createHttpClient();
         httpClient.getRetryHandler().setMaxConnectRetries(3);

         try {
            HttpResponse response = httpClient.execute(new GetRequest(OAuthEnvironment.LIVE.getBaseUrl()));
            Instant clientTime = Instant.now();
            Instant serverTime = response.getFirstHeader("Date").map(s -> DateTimeFormatter.RFC_1123_DATE_TIME.parse(s, Instant::from)).get();
            CLIENT_TIME_OFFSET = Duration.between(clientTime, serverTime);
            if (CLIENT_TIME_OFFSET.abs().compareTo(Duration.ofMinutes(2L)) > 0) {
               MinecraftAuth.LOGGER.warn("Local clock is off by " + CLIENT_TIME_OFFSET.negated().toMinutes() + " minutes");
            }
         } catch (Throwable e) {
            MinecraftAuth.LOGGER.error("Failed to get client time offset. This may cause issues with authentication if the local clock is wrong.");
            e.printStackTrace();
            CLIENT_TIME_OFFSET = Duration.ZERO;
         }
      }

      return CLIENT_TIME_OFFSET;
   }
}
