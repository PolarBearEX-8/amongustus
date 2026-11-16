package net.minecraft.item;

import client.event.JsonListener;
import client.setting.ModeOptionConfigurable;
import client.setting.ModuleModeOption;
import client.util.ModeListTracker;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public abstract class IronGem extends JsonListener {
   public final ModeListTracker modeListTracker;

   public Function getFunction() {
      return (item -> IronGem.resolveBooleanByModeOptionConfig((ModeOptionConfigurable)item));
   }

   public JsonObject getJsonObject() {
      ModeListTracker modeListTracker2 = this.modeListTracker;
      Function functionAsFunction = this.getFunctionAsFunction();
      Function mapper = functionAsFunction;
      return modeListTracker2.resolveJsonObject(mapper);
   }

   @Override
   public JsonElement getJsonElement() {
      return this.getJsonObject();
   }

   public Function getFunctionAsFunction() {
      return (item -> IronGem.resolveBoolean((ModeOptionConfigurable)item));
   }

   private static Boolean resolveBooleanByModeOptionConfig(ModeOptionConfigurable modeOptionConfigurable) {
      return true;
   }

   private static Boolean resolveBoolean(ModeOptionConfigurable modeOptionConfigurable) {
      return true;
   }

   @Override
   public Class getClassAsClass() {
      return JsonObject.class;
   }

   public void applyJsonObject(JsonObject jsonObject) {
      ModeListTracker modeListTracker2 = this.modeListTracker;
      Function function = this.getFunction();
      boolean flag = false;
      modeListTracker2.checkJsonObject(jsonObject, flag, function);
   }

   @Override
   public void applyJsonElement(JsonElement jsonElement) {
      JsonObject jsonObject = (JsonObject)jsonElement;
      this.applyJsonObject(jsonObject);
   }

   public IronGem(ModuleModeOption[] moduleModeOptionArray) {
      this.modeListTracker = new ModeListTracker(Arrays.asList(moduleModeOptionArray));
   }

   public IronGem(List list) {
      this.modeListTracker = new ModeListTracker(list);
   }
}
