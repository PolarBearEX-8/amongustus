package net.labymod;

import com.thealtening.api.TheAlteningException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class InternalAccountStorage {
   public static String getSecretKey() {
      byte[] bytes = "jT++uNQSC4k8l92xnPqLaUGnFzD/ZTyuBVfsgRF9cWvP7L2p/w1VelEvTwPAqTuKzYXwVPu6TmeiZmMEHiF1Ofh+TS5/G1RvLYaoleMirNlGgpOgo4jsidDGzkTijYkrIrYNuQpUpvje4qoZHA5JJe0I1r114V/ZxDUhzJKFJYS28oIk6pATJJEhf/MWmky1eCmpZKcE5kawHY70euiO6PXY"
         .getBytes(StandardCharsets.UTF_8);
      byte[] bytes2 = "s/XG3jz5XBPxxT5DwrftqWW30SSiE4VXkFCHzjSB1SaitA4AT7jxhSEptvO5iLW4s5EiKIAItaiOxFflkLHe5/3bmlzwyY4f38/47/g1Lyq+TufXXGWtPHP61poRLfPUfphw98Dn3B9Qrkwjo2IxPjIWdG/+7Kp0W1J+goPmG8j5y88sMxcN1kn+9ixwJyDFFohT8af3AGYvcmF51+psMs32Vew+0MlrHUpvJPe8"
         .getBytes(StandardCharsets.UTF_8);
      byte[] bytes3 = "WDt7YRgZjz0CXPoNcZDgxh5zD7rK7Bpl219eEOzodLeXZ2rVxIwxu5NrDk1hxvwMrktH8C/diPGPgz4MP9nVGdBFE3gBkiHc0Km2KbmqmKhglPjKe8kM+E2RFA8X2NiViIMco0jKOOdpQGpQ955vpndh3GXgVI3qIDUvrikRM/bzi6gZnkUt1Dyyh5mqcsIIwNtws9GFYn8OgK+t1nsL+Wo1SNLrei/0TovLyUomNul5KeqxxSpGyEUwtswBVh1+w3lHcQcFTXBYOC6EitIX0gqsBQKtQVG+MeD/JSMbniTZc7y0xyWlxf1Ml4ID8be2Aze51CuiUt8gOGQjit2omcUSnZ+RnE909+sfERY7x8wY4wOUpbQdF6/XEs4cjGW7"
         .getBytes(StandardCharsets.UTF_8);
      byte[] bytes4 = "+6DTE23IgU8EUA6M/FJmeDaW6c2sKb8YUiX85F97X+xnhTTjSGLpmEn3nDqDzKf+8db+BvjyE4sNq0OyCJseJz/HmTkvqqRtk05JOq4erFl+ZZ2eNvFvCm3Zin4aaDP4rgD9wpCO5OCJOU0pbu+Km8/WxRwzroJz+uaRekG0YBZbTBB4/IFxP8Wnx/6il8+QHc3UJwEf5xhXAuma3vJ7NL932TExcFMrPIeGRQ=="
         .getBytes(StandardCharsets.UTF_8);
      byte[] bytes5 = new byte[bytes.length];
      byte[] bytes6 = new byte[bytes3.length];
      int n = 0;

      try {
         while (n < bytes.length) {
            bytes5[n] = (byte)(bytes[n] + bytes2[n % bytes2.length]);
            n++;
         }
      } catch (TheAlteningException e) {
         throw e;
      }

      n = 0;

      try {
         while (n < bytes3.length) {
            bytes6[n] = (byte)(bytes3[n] ^ bytes4[n % bytes4.length]);
            n++;
         }
      } catch (TheAlteningException e2) {
         throw e2;
      }

      byte[] bytes7 = new byte[bytes5.length + bytes6.length];
      System.arraycopy(bytes5, 0, bytes7, 0, bytes5.length);
      System.arraycopy(bytes6, 0, bytes7, bytes5.length, bytes6.length);
      return "Ym70*4" + Base64.getEncoder().encodeToString(bytes7) + "sA772!";
   }

}
