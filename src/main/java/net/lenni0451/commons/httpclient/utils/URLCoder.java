package net.lenni0451.commons.httpclient.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import lombok.Generated;

public final class URLCoder {
   public static String encode(String s) {
      try {
         return encode(s, StandardCharsets.UTF_8);
      } catch (Throwable e) {
         throw URLCoder.<RuntimeException>sneakyThrow(e);
      }
   }

   public static String encode(String s, Charset charset) {
      try {
         return URLEncoder.encode(s, charset.name());
      } catch (Throwable e) {
         throw URLCoder.<RuntimeException>sneakyThrow(e);
      }
   }

   public static String decode(String s) {
      try {
         return decode(s, StandardCharsets.UTF_8);
      } catch (Throwable e) {
         throw URLCoder.<RuntimeException>sneakyThrow(e);
      }
   }

   public static String decode(String s, Charset charset) {
      try {
         return URLDecoder.decode(s, charset.name());
      } catch (Throwable e) {
         throw URLCoder.<RuntimeException>sneakyThrow(e);
      }
   }

   @Generated
   private URLCoder() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   @SuppressWarnings("unchecked")
   private static <E extends Throwable> RuntimeException sneakyThrow(Throwable t) throws E {
      throw (E)t;
   }
}
