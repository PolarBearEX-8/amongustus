package com.thealtening.api.retriever;

import com.thealtening.api.TheAlteningException;
import com.thealtening.api.response.Account;
import com.thealtening.api.response.License;
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

public interface DataRetriever {
   Logger LOGGER = Logger.getLogger("The Altening");
   Gson GSON = new GsonBuilder().create();
   String BASE_URL = "";
   String LICENCE_URL = "";
   String GENERATE_URL = "";
   String PRIVATE_ACC_URL = "";
   String FAVORITE_ACC_URL = "";
   String PRIVATES_URL = "";
   String FAVORITES_URL = "";

   default String connect(String str) throws IOException {
      StringBuilder sb = new StringBuilder();
      URLConnection conn = new URL(str).openConnection();
      conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
      InputStream inputStream = conn.getInputStream();
      String contentEncoding = conn.getContentEncoding();

      Charset charset;
      try {
         charset = contentEncoding == null ? StandardCharsets.UTF_8 : Charset.forName(contentEncoding);
      } catch (UnsupportedCharsetException e) {
         charset = StandardCharsets.UTF_8;
      }

      String str2;
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
         while ((str2 = reader.readLine()) != null) {
            sb.append(str2).append("\n");
         }
      }

      return sb.toString();
   }

   boolean isPrivate(String str) throws TheAlteningException;

   boolean isFavorite(String str) throws TheAlteningException;

   License getLicense();

   Account getAccount();

   void updateKey(String str);

   List getPrivatedAccountInfos();

   default boolean isSuccess(JsonObject json) {
      return json.has("success") && json.get("success").getAsBoolean();
   }

   default JsonElement retrieveData(String str) throws TheAlteningException {

      String str2;
      JsonElement elem;
      try {
         str2 = this.connect(str);
         elem = (JsonElement)GSON.fromJson(str2, JsonElement.class);
      } catch (IOException e) {
         LOGGER.log(Level.SEVERE, "Error while reading retrieved data from the website");
         throw new TheAlteningException("IO", e.getCause());
      }

      if (elem == null) {
         LOGGER.log(Level.SEVERE, "Error while parsing website's response");
         throw new TheAlteningException("JSON", "Parsing error: \n" + str2);
      } else if (elem.isJsonObject() && elem.getAsJsonObject().has("error") && elem.getAsJsonObject().has("errorMessage")) {
         LOGGER.log(
            Level.SEVERE,
            "The website returned, type: "
               + elem.getAsJsonObject().get("error").getAsString()
               + ". Details:"
               + elem.getAsJsonObject().get("errorMessage").getAsString()
         );
         throw new TheAlteningException("Connection", "Bad response");
      } else {
         return elem;
      }
   }

   List getFavouriteAccountInfos();
}
