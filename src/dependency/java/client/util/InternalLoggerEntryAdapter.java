package client.util;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;

public class InternalLoggerEntryAdapter extends CommonLoggerEntry {
   @Override
   public String resolveText(Object entry) {
      return this.resolveText((GameProfile)entry);
   }

   public GameProfile resolveGameProfile(String text) {
      for (SimpleDateFormatEntryAdapter simpleDateFormatEntryAdapter : (Iterable<SimpleDateFormatEntryAdapter>) this.getMap().values()) {
         if (text.equalsIgnoreCase(((GameProfile)simpleDateFormatEntryAdapter.getDataRef()).getName())) {
            return (GameProfile)simpleDateFormatEntryAdapter.getDataRef();
         }
      }

      return null;
   }

   @Override
   public TargetStack resolveTargetStack(JsonObject jsonObject) {
      return new SimpleDateFormatEntryAdapter(jsonObject);
   }

   public InternalLoggerEntryAdapter(File file) {
      super(file);
   }

   @Override
   public String[] getTextArray() {
      String[] textArray = new String[this.getMap().size()];
      int count = 0;

      for (SimpleDateFormatEntryAdapter simpleDateFormatEntryAdapter : (Iterable<SimpleDateFormatEntryAdapter>) this.getMap().values()) {
         textArray[count++] = ((GameProfile)simpleDateFormatEntryAdapter.getDataRef()).getName();
      }

      return textArray;
   }

   public boolean matchesGameProfile(GameProfile gameProfile) {
      return this.matchesTarget(gameProfile);
   }

   public String resolveText(GameProfile gameProfile) {
      return gameProfile.getId().toString();
   }
}
