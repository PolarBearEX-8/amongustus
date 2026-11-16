package client.util;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;

public class AuxiliaryLoggerEntryAdapter extends CommonLoggerEntry {
   @Override
   public String[] getTextArray() {
      String[] textArray = new String[this.getMap().size()];
      int count = 0;

      for (Object targetSystemObj : this.getMap().values()) {
         TargetSystem targetSystem = (TargetSystem)targetSystemObj;
         textArray[count++] = ((GameProfile)targetSystem.getDataRef()).getName();
      }

      return textArray;
   }

   @Override
   public String resolveText(Object entry) {
      return this.resolveText((GameProfile)entry);
   }

   @Override
   public TargetStack resolveTargetStack(JsonObject jsonObject) {
      return new TargetSystem(jsonObject);
   }

   public GameProfile resolveGameProfile(String text) {
      for (Object targetSystemObj : this.getMap().values()) {
         TargetSystem targetSystem = (TargetSystem)targetSystemObj;
         if (text.equalsIgnoreCase(((GameProfile)targetSystem.getDataRef()).getName())) {
            return (GameProfile)targetSystem.getDataRef();
         }
      }

      return null;
   }

   public String resolveText(GameProfile gameProfile) {
      return gameProfile.getId().toString();
   }

   public AuxiliaryLoggerEntryAdapter(File file) {
      super(file);
   }
}
