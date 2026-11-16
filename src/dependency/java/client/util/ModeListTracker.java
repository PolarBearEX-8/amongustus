package client.util;

import client.event.AdapterListener;
import client.network.MysteryBoxPacketListener;
import client.setting.ModuleModeOption;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ModeListTracker implements AdapterListener {
   public List list;

   public JsonObject resolveJsonObject(Function mapper) {
      try {
         JsonObject jsonObject;
         if (this.list.size() == 1) {
            ModuleModeOption moduleModeOption = (ModuleModeOption)this.list.get(0);
            Object entry = null;
            MysteryBoxPacketListener mysteryBoxPacketListener = MysteryBoxService.mysteryBoxPacketListener;
            jsonObject = moduleModeOption.getJsonObject(mysteryBoxPacketListener, (JsonObject)entry, mapper);
         } else {
            jsonObject = new JsonObject();
            this.list.forEach(option -> ModeListTracker.applyFunction(mapper, jsonObject, (ModuleModeOption)option));
         }

         return jsonObject;
      } catch (Exception exception) {
         return null;
      }
   }

   public void processAction(ModuleModeOption[] moduleModeOptionArray) {
      this.list = Arrays.asList(moduleModeOptionArray);
   }

   public boolean checkJsonObject(JsonObject jsonObject, boolean flag, Function mapper) {
      try {
         if (this.list.size() == 1) {
            ModuleModeOption moduleModeOption = (ModuleModeOption)this.list.get(0);
            MysteryBoxPacketListener mysteryBoxPacketListener = MysteryBoxService.mysteryBoxPacketListener;
            moduleModeOption.updateListener(mysteryBoxPacketListener, jsonObject, false, mapper);
         } else {
            this.list.forEach(option -> ModeListTracker.applyJsonObject(jsonObject, flag, mapper, (ModuleModeOption)option));
         }

         return true;
      } catch (Exception exception) {
         return false;
      }
   }

   public void setList(List list2) {
      this.list = list2;
   }

   @Override
   public boolean matchesJsonElement(JsonElement jsonElement, boolean flag, Function mapper) {
      JsonObject jsonObject = (JsonObject)jsonElement;
      return this.checkJsonObject(jsonObject, flag, mapper);
   }

   @Override
   public JsonElement resolveJsonElement(Function mapper) {
      return this.resolveJsonObject(mapper);
   }

   private static void applyJsonObject(JsonObject jsonObject, boolean flag, Function mapper, ModuleModeOption moduleModeOption) {
      JsonElement jsonElement = jsonObject.get(moduleModeOption.getString());
      if (jsonElement != null && jsonElement.isJsonObject()) {
         JsonObject asJsonObject = jsonElement.getAsJsonObject();
         MysteryBoxPacketListener mysteryBoxPacketListener = MysteryBoxService.mysteryBoxPacketListener;
         moduleModeOption.updateListener(mysteryBoxPacketListener, asJsonObject, flag, mapper);
      }
   }

   private static void applyFunction(Function mapper, JsonObject jsonObject, ModuleModeOption moduleModeOption) {
      Object entry = null;
      MysteryBoxPacketListener mysteryBoxPacketListener = MysteryBoxService.mysteryBoxPacketListener;
      JsonObject jsonObject2 = moduleModeOption.getJsonObject(mysteryBoxPacketListener, (JsonObject)entry, mapper);
      jsonObject.add(moduleModeOption.getString(), jsonObject2);
   }

   public ModeListTracker(ModuleModeOption[] moduleModeOptionArray) {
      this.list = Arrays.asList(moduleModeOptionArray);
   }

   public ModeListTracker() {
   }

   public ModeListTracker(List list2) {
      this.list = list2;
   }
}
