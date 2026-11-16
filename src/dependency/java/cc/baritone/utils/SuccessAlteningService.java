package cc.baritone.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface SuccessAlteningService {
   Logger logger = Logger.getLogger("The Altening");
   Gson gson = new GsonBuilder().create();
   String labelText = "http://api.thealtening.com/v2/";
   String labelText2 = "http://api.thealtening.com/v2/license?key=";
   String labelText3 = "http://api.thealtening.com/v2/generate?info=true&key=";
   String labelText4 = "http://api.thealtening.com/v2/private?token=";
   String labelText5 = "http://api.thealtening.com/v2/favorite?token=";
   String labelText6 = "http://api.thealtening.com/v2/privates?key=";
   String labelText7 = "http://api.thealtening.com/v2/favorites?key=";

   default boolean matchesJsonObject(JsonObject jsonObject, byte byteValue, int count, int count2) {
      return jsonObject.has("success") && jsonObject.get("success").getAsBoolean();
   }

   ControllerContext getControllerContext();

   boolean matchesTextByText(String text) throws RuntimeExceptionAdapter;

   List getListAsList();

   boolean matchesText(String text) throws RuntimeExceptionAdapter;

   default String resolveText(String text, short shortValue) throws IOException {
      StringBuilder builder = new StringBuilder();
      URLConnection uRLConnection = new URL(text).openConnection();
      uRLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
      InputStream inputStream = uRLConnection.getInputStream();
      String contentEncoding = uRLConnection.getContentEncoding();

      Charset charset;
      try {
         charset = contentEncoding == null ? StandardCharsets.UTF_8 : Charset.forName(contentEncoding);
      } catch (UnsupportedCharsetException unsupportedCharsetException) {
         charset = StandardCharsets.UTF_8;
      }

      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));
      Throwable throwable = null;

      while (true) {
         boolean flag = false ;

         try {
            flag = true;
            String text2;
            if ((text2 = reader.readLine()) == null) {
               flag = false;
               break;
            }

            builder.append(text2).append("\n");
         } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
         } finally {
            if (flag) {
               if (throwable != null) {
                  try {
                     reader.close();
                  } catch (Throwable throwable3) {
                     throwable.addSuppressed(throwable3);
                  }
               } else {
                  reader.close();
               }
            }
         }
      }

      reader.close();
      return builder.toString();
   }

   default JsonElement resolveJsonElement(String text) throws RuntimeExceptionAdapter {

      String text2;
      JsonElement jsonElement;
      try {
         text2 = this.resolveText(text, (short)8731);
         jsonElement = (JsonElement)gson.fromJson(text2, JsonElement.class);
      } catch (IOException ioException) {
         logger.log(Level.SEVERE, "Error while reading retrieved data from the website");
         throw new RuntimeExceptionAdapter("IO", ioException.getCause());
      }

      if (jsonElement == null) {
         logger.log(Level.SEVERE, "Error while parsing website's response");
         throw new RuntimeExceptionAdapter("JSON", 0x576c17e61cf4L, "Parsing error: \n" + text2);
      } else if (jsonElement.isJsonObject() && jsonElement.getAsJsonObject().has("error") && jsonElement.getAsJsonObject().has("errorMessage")) {
         logger.log(
            Level.SEVERE,
            "The website returned, type: "
               + jsonElement.getAsJsonObject().get("error").getAsString()
               + ". Details:"
               + jsonElement.getAsJsonObject().get("errorMessage").getAsString()
         );
         throw new RuntimeExceptionAdapter("Connection", 0x576c17e61cf4L, "Bad response");
      } else {
         return jsonElement;
      }
   }

   HeuristicOptimizer getHeuristicOptimizer();

   void setLabelText8(String labelText8);

   List getList();
}
