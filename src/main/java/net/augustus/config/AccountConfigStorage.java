package net.augustus.config;

import net.augustus.ui.account.AccountEntry;
import net.augustus.ui.account.AccountManagerScreen;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class AccountConfigStorage extends JsonConfigFile {

   public void deserializeAccounts(JsonArray array) {
      AccountManagerScreen.accounts.clear();
      array.forEach(elem -> loadAccountEntry((JsonElement)elem));
   }

   private static void loadAccountEntry(JsonElement elem) {

      try {
         JsonObject asJsonObject = elem.getAsJsonObject();
         AccountEntry accountEntry = new AccountEntry();
         accountEntry.fromJson(asJsonObject);
         AccountManagerScreen.accounts.add(accountEntry);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println("Failed to load alt-account");
      }
   }

   @Override
   public boolean alwaysReserializeOnSave() {
      return true;
   }

   public JsonArray serializeAccounts() {
      JsonArray array = new JsonArray();

      for (AccountEntry accountEntry : (Iterable<AccountEntry>)(AccountManagerScreen.accounts)) {
         try {
            array.add(accountEntry.toJson());
         } catch (Exception e) {
         }
      }

      return array;
   }

   @Override
   public Class getJsonType() {
      return JsonArray.class;
   }

   @Override
   public void deserialize(JsonElement elem) {
      this.deserializeAccounts((JsonArray)elem);
   }

   @Override
   public boolean markLoadedEvenIfEmpty() {
      return true;
   }

   @Override
   public JsonElement serialize() {
      return this.serializeAccounts();
   }

   @Override
   public String getSubDirectory() {
      return "settings";
   }

   @Override
   public String getName() {
      return "Accounts";
   }

}
