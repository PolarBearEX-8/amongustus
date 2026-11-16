package client.util;

import client.event.JsonListener;
import client.network.MysteryBoxPacketListener;
import client.setting.ModeOptionConfigurable;
import client.setting.ModuleModeOption;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class BezierBridge extends JsonListener {
   public final List list;

   private static ModuleModeOption resolveModuleModeOption(ModuleModeOption moduleModeOption) {
      return moduleModeOption;
   }

   @Override
   public JsonElement getJsonElement() {
      return this.getJsonObject();
   }

   private static Boolean resolveBooleanByModeOptionConfig(ModeOptionConfigurable modeOptionConfigurable) {
      return true;
   }

   public abstract Supplier getSupplier();

   public Function getFunction() {
      return (item -> BezierBridge.resolveBooleanByModeOptionConfig((ModeOptionConfigurable)item));
   }

   public JsonObject getJsonObject() {
      JsonObject jsonObject = new JsonObject();

      for (int index = 0; index < this.list.size(); index++) {
         ModuleModeOption moduleModeOption = (ModuleModeOption)this.list.get(index);
         MysteryBoxPacketListener mysteryBoxPacketListener = MysteryBoxService.mysteryBoxPacketListener;
         Function function = this.getFunction();
         Function mapper = function;
         Object entry = null;
         MysteryBoxPacketListener packet = mysteryBoxPacketListener;
         JsonObject jsonObject2 = moduleModeOption.getJsonObject(packet, (JsonObject)entry, mapper);
         jsonObject.add("E_" + index, jsonObject2);
      }

      return jsonObject;
   }

   public void applyJsonObject(JsonObject jsonObject) {
      Iterator iterator = jsonObject.entrySet().iterator();

      while (iterator.hasNext()) {
         JsonElement value2 = (JsonElement)((Entry)iterator.next()).getValue();
         if (value2.isJsonObject()) {
            ModuleModeOption moduleModeOption = (ModuleModeOption)this.getSupplier().get();
            JsonObject asJsonObject = value2.getAsJsonObject();
            MysteryBoxPacketListener mysteryBoxPacketListener = MysteryBoxService.mysteryBoxPacketListener;
            Function functionAsFunction = this.getFunctionAsFunction();
            boolean flag = false;
            MysteryBoxPacketListener packet = mysteryBoxPacketListener;
            moduleModeOption.updateListener(packet, asJsonObject, flag, functionAsFunction);
            Supplier supplier = () -> BezierBridge.resolveModuleModeOption(moduleModeOption);
            Supplier supplier2 = supplier;
            this.applySupplier(supplier2);
         }
      }
   }

   public abstract void applySupplier(Supplier supplier);

   @Override
   public Class getClassAsClass() {
      return JsonObject.class;
   }

   public Function getFunctionAsFunction() {
      return (item -> BezierBridge.resolveBoolean((ModeOptionConfigurable)item));
   }

   @Override
   public void applyJsonElement(JsonElement jsonElement) {
      JsonObject jsonObject = (JsonObject)jsonElement;
      this.applyJsonObject(jsonObject);
   }

   private static Boolean resolveBoolean(ModeOptionConfigurable modeOptionConfigurable) {
      return true;
   }

   public BezierBridge(List list2) {
      this.list = list2;
   }
}
