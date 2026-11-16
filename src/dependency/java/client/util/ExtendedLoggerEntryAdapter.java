package client.util;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;

public class ExtendedLoggerEntryAdapter extends CommonLoggerEntry {
   public boolean matchesGameProfile(GameProfile gameProfile) {
      TargetDirector targetDirector = (TargetDirector)this.resolveTargetStack(gameProfile);
      return targetDirector != null ? targetDirector.isEnabled() : false;
   }

   public String resolveText(GameProfile gameProfile) {
      return gameProfile.getId().toString();
   }

   @Override
   public String[] getTextArray() {
      String[] textArray = new String[this.getMap().size()];
      int count = 0;

      for (TargetDirector targetDirector : (Iterable<TargetDirector>)(Iterable)this.getMap().values()) {
         textArray[count++] = ((GameProfile)targetDirector.getDataRef()).getName();
      }

      return textArray;
   }

   public ExtendedLoggerEntryAdapter(File file) {
      super(file);
   }

   @Override
   public String resolveText(Object entry) {
      return this.resolveText((GameProfile)entry);
   }

   @Override
   public TargetStack resolveTargetStack(JsonObject jsonObject) {
      return new TargetDirector(jsonObject);
   }

   public GameProfile resolveGameProfile(String text) {
      for (TargetDirector targetDirector : (Iterable<TargetDirector>)(Iterable)this.getMap().values()) {
         if (text.equalsIgnoreCase(((GameProfile)targetDirector.getDataRef()).getName())) {
            return (GameProfile)targetDirector.getDataRef();
         }
      }

      return null;
   }
}
