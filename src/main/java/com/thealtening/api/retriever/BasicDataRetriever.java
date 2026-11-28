package com.thealtening.api.retriever;

import com.thealtening.api.TheAlteningException;
import com.thealtening.api.response.Account;
import com.thealtening.api.response.License;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class BasicDataRetriever implements DataRetriever {
   private String apiKey;

   @Override
   public boolean isFavorite(String str) throws TheAlteningException {
      return this.isSuccess(this.retrieveData("http://api.thealtening.com/v2/favorite?token=" + str + "&key=" + this.apiKey).getAsJsonObject());
   }

   @Override
   public Account getAccount() throws TheAlteningException {
      JsonObject asJsonObject = this.retrieveData("http://api.thealtening.com/v2/generate?info=true&key=" + this.apiKey).getAsJsonObject();
      return (Account)GSON.fromJson(asJsonObject, Account.class);
   }

   @Override
   public boolean isPrivate(String str) throws TheAlteningException {
      return this.isSuccess(this.retrieveData("http://api.thealtening.com/v2/private?token=" + str + "&key=" + this.apiKey).getAsJsonObject());
   }

   public AsynchronousDataRetriever toAsync() {
      return new AsynchronousDataRetriever(this.apiKey);
   }

   @Override
   public License getLicense() throws TheAlteningException {
      JsonObject asJsonObject = this.retrieveData("http://api.thealtening.com/v2/license?key=" + this.apiKey).getAsJsonObject();
      return (License)GSON.fromJson(asJsonObject, License.class);
   }

   @Override
   public List getPrivatedAccountInfos() {
      ArrayList list = new ArrayList();

      for (JsonElement elem : this.retrieveData("http://api.thealtening.com/v2/privates?key=" + this.apiKey).getAsJsonArray()) {
         if (elem.isJsonObject()) {
            list.add(GSON.fromJson(elem, Account.class));
         }
      }

      return list;
   }

   @Override
   public List getFavouriteAccountInfos() {
      ArrayList list = new ArrayList();

      for (JsonElement elem : this.retrieveData("http://api.thealtening.com/v2/favorites?key=" + this.apiKey).getAsJsonArray()) {
         if (elem.isJsonObject()) {
            list.add(GSON.fromJson(elem, Account.class));
         }
      }

      return list;
   }

   @Override
   public void updateKey(String str) {
      this.apiKey = str;
   }

   public BasicDataRetriever(String str) {
      this.apiKey = str;
   }
}
