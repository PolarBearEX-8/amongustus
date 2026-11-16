package client.util;

import client.gui.DropdownMenuWidget;
import client.gui.TexturesGuiIconsPngManager;
import client.render.RenderUtils;
import client.setting.EasingFunction;
import client.setting.MovementValueProperty;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class AccountProfile implements MinecraftAccessor {
   public final KeybindAnimation keybindAnimation = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);
   public long timestamp3;
   public int count4;
   public boolean enabled;
   public boolean enabled3;
   public final TimeHelper timer = new TimeHelper();
   public int count2;
   public long timestamp;
   public JsonObject jsonObject = null;
   public int count;
   public String labelText;
   public boolean enabled4;
   public boolean enabled5;
   public MovementValueProperty movementValueProperty = MovementValueProperty.CRACKED;
   public final HashMap hashMap2;
   public final HashMap hashMap;
   public HashMap hashMap3;
   public HashMap hashMap4;
   public int count3;
   public long timestamp2;

   public int getCount2() {
      return this.count2;
   }

   public HashMap getHashMap() {
      return this.hashMap;
   }

   private void updateState4() {
      MsnManager.applyAccountProfile4(this);
   }

   public boolean get(String text) {
      return this.hashMap3.get(text) != null;
   }

   public void applyIndex(int count5, int count6, int count7) {
      if (count7 == 0 && RenderUtils.checkCondition((float)this.count, (float)this.count4, (float)this.count2, (float)this.count3, count5, count6)) {
         if (!KeyUtils.checkCondition(157) && !KeyUtils.checkCondition(29)) {
            if (!this.timer.hasReached(250L)
               && (
                  DropdownMenuWidget.dropdownMenuWidget.getCompletableFuture() == null || DropdownMenuWidget.dropdownMenuWidget.getCompletableFuture().isDone()
               )) {
               this.updateState6();
            }

            this.timer.reset();
         } else {
            this.enabled4 = !this.enabled4;
         }
      }
   }

   public boolean isEnabled4() {
      return this.enabled4;
   }

   public void applyList(int count5, int count6, int count7, int count8, int count9, int count10, boolean flag, List list) {
      this.count = count5;
      this.count4 = count6;
      this.count2 = count7;
      this.count3 = count8;
      GL11.glPushMatrix();
      FontRenderer fontRendererObj = MinecraftAccessor.minecraft.fontRendererObj;
      float factor = (float)count7 / 2.0F;
      float factor2 = (float)count8 / 2.0F;
      float factor3 = (float)count5 + factor;
      float factor4 = (float)count6 + factor2;
      byte byteValue = ((byte)((RenderUtils.checkCondition((float)count5, (float)count6, (float)count7, (float)count8, count9, count10)) ? 1 : 0));
      if (this.enabled5 != (byteValue != 0)) {
         this.keybindAnimation.updateEasingFunction(100, EasingFunction.easeInSine, byteValue, false);
      }

      float factor5 = (float)(1.0 - this.keybindAnimation.getDouble3() * 0.02F);
      GL11.glScaled((double)factor5, (double)factor5, 0.0);
      float factor6 = factor3 / factor5;
      float factor7 = factor4 / factor5;
      this.enabled5 = ((byteValue) != 0);
      TexturesGuiIconsPngManager.processAction(
         (int)(factor6 - factor), (int)(factor7 - factor2), (int)(factor6 + factor), (int)(factor7 + factor2), new Color(0, 0, 0, flag ? 200 : 100).getRGB()
      );
      GL11.glScaled((double)(1.0F / factor5), (double)(1.0F / factor5), 0.0);
      byte byteValue2 = 10;
      this.enabled = MinecraftAccessor.minecraft.getSession().getText1().equals(this.labelText)
         && MinecraftAccessor.minecraft.getSession().getKeybindAnimationEntry() != null
         && MinecraftAccessor.minecraft.getSession().getKeybindAnimationEntry().equals(this);
      int count11;
      if (this.isEnabled4()) {
         String text = MinecraftAccessor.minecraft.getSession() != null && MinecraftAccessor.minecraft.getSession().getText1().equals(this.labelText)
            ? "§7Name: §a" + this.labelText
            : "§7Name: §6" + this.labelText;
         count11 = byteValue2 + fontRendererObj.getIndex(text);
         fontRendererObj.computeCountByText(text, (float)count5 + 5.5F, (float)count6 + 3.5F, Color.white.getRGB());
         fontRendererObj.computeCountByText(
            "§7Login type: §6" + this.movementValueProperty.getLabelText(),
            (float)count5 + 5.5F,
            (float)count6 + 7.0F + (float)fontRendererObj.indexOffset,
            Color.white.getRGB()
         );
         fontRendererObj.computeCountByText(
            "§7Account state: §6" + (this.enabled3 ? "Functional" : "§cInvalid"),
            (float)count5 + 5.5F,
            (float)count6 + 10.5F + (float)(fontRendererObj.indexOffset * 2),
            Color.white.getRGB()
         );
      } else {
         String text2 = MinecraftAccessor.minecraft.getSession() != null && MinecraftAccessor.minecraft.getSession().getText1().equals(this.labelText)
            ? "§7Name: §a" + this.labelText
            : "§7Name: §f" + this.labelText;
         count11 = byteValue2 + fontRendererObj.getIndex(text2);
         fontRendererObj.computeCountByText(text2, (float)count5 + 5.5F, (float)count6 + 3.5F, Color.white.getRGB());
         fontRendererObj.computeCountByText(
            "§7Login type: §f" + this.movementValueProperty.getLabelText(),
            (float)count5 + 5.5F,
            (float)count6 + 7.0F + (float)fontRendererObj.indexOffset,
            Color.white.getRGB()
         );
         fontRendererObj.computeCountByText(
            "§7Account state: §f" + (this.enabled3 ? "Functional" : "§cInvalid"),
            (float)count5 + 5.5F,
            (float)count6 + 10.5F + (float)(fontRendererObj.indexOffset * 2),
            Color.white.getRGB()
         );
      }

      int byteValue3 = 0;

      for (Pair pair : (Iterable<Pair>)(Iterable<?>)this.hashMap2.values()) {
         if (pair.getFirst() != null) {
            GlStateManager.translate3f2(1.0F, 1.0F, 1.0F);
            int count12 = count5 + count11 + byteValue3;
            int count13 = count6 + 2;
            ResourceLocation second = (ResourceLocation)pair.getSecond();
            int count14 = count13;
            int count15 = count12;
            this.applyResourceLocation(count15, count14, second);
            byteValue3 += 12;
         }
      }

      GL11.glScaled((double)factor5, (double)factor5, 0.0);
      ResourceLocation location = (ResourceLocation)TertiaryMapEntry.map.get(this.labelText);
      if (location != null) {
         int count16 = (int)(factor6 - factor + (float)count7 - (float)count8);
         int count17 = (int)(factor7 - factor2);
         double offset = 1.0;
         int count18 = count17;
         int count19 = count16;
         PicturesConfigPngManager.applyResourceLocation(location, count19, count18, count8, count8, offset);
      }

      GL11.glPopMatrix();
   }

   public HashMap getHashMap4() {
      return this.hashMap4;
   }

   public JsonObject getJsonObject() {
      return this.jsonObject;
   }

   public int getCount3() {
      return this.count3;
   }

   public long getTimestamp2() {
      return this.timestamp2;
   }

   public void applyHashMap(HashMap map) {
      this.hashMap.clear();

      for (String text : (Iterable<String>)(Iterable<?>)this.hashMap3.keySet()) {
         Pair pair = (Pair)map.get(text);
         if (pair != null) {
            this.hashMap.put(text, pair);
         }
      }

      this.hashMap2.clear();

      for (String text2 : (Iterable<String>)(Iterable<?>)this.hashMap4.keySet()) {
         Pair pair2 = (Pair)map.get(text2);
         if (pair2 != null) {
            this.hashMap2.put(text2, pair2);
         }
      }
   }

   public void setTimestamp3(long timestamp4) {
      this.timestamp3 = timestamp4;
   }

   public void applyText(String text, Long value) {
      this.hashMap3.remove(text);
      this.hashMap.remove(text);
      Long value2 = (Long)this.hashMap4.get(text);
      if (value2 == null) {
         this.hashMap4.put(text, value);
         ResourceLocation location = new ResourceLocation("servers/" + text + "/icon");
         this.hashMap2.put(text, new Pair(location, (DynamicTexture)MinecraftAccessor.minecraft.getTextureManager().get(location)));
      } else {
         this.hashMap4.remove(text);
         this.hashMap4.put(text, value);
      }
   }

   private void updateState2() {
      MsnManager.applyAccountProfile(this);
   }

   public void setEnabled3(boolean enabled2) {
      this.enabled3 = enabled2;
   }

   private void updateState() {
      MsnManager.applyAccountProfile6(this);
   }

   public void applyTime(long value) {
      this.timestamp += value;
   }

   public HashMap getHashMap2() {
      return this.hashMap2;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public JsonArray resolveJsonArray(HashMap map) {
      short shortValue = 4526;
      char character = '\ud925';
      short shortValue2 = 25330;
      JsonArray jsonArray = new JsonArray();

      for (Entry entry : (Iterable<Entry>)(Iterable<?>)map.entrySet()) {
         JsonObject jsonObject2 = new JsonObject();
         JsonPropertyGetter baseListener = MysteryBoxService.mysteryBoxPacketListener.getBaseListener();
         Object key = entry.getKey();
         String text = "Key";
         baseListener.applyText(text, key, jsonObject2);
         baseListener = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter6();
         Object value = entry.getValue();
         String text2 = "Value";
         baseListener.applyText(text2, value, jsonObject2);
         jsonArray.add(jsonObject2);
      }

      return jsonArray;
   }

   private void updateState5() {
      MsnManager.applyAccountProfile3(this);
   }

   public String getLabelText() {
      return this.labelText;
   }

   public void applyJsonObject(JsonObject jsonObject2) {
      JsonPropertyGetter baseListener = MysteryBoxService.mysteryBoxPacketListener.getBaseListener();
      String labelText2 = this.labelText;
      String text = "Name";
      this.labelText = (String)baseListener.getEntry(text, labelText2, jsonObject2);
      baseListener = MysteryBoxService.mysteryBoxPacketListener.getBaseListener();
      String text2 = this.movementValueProperty.name();
      String text3 = "Type";
      this.movementValueProperty = MovementValueProperty.valueOf((String)baseListener.getEntry(text3, text2, jsonObject2));
      baseListener = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter6();
      Long timestamp4 = this.timestamp2;
      String text4 = "LastLoginTime";
      this.timestamp2 = (Long)baseListener.getEntry(text4, timestamp4, jsonObject2);
      baseListener = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter6();
      Long timestamp5 = this.timestamp;
      String text5 = "PlayTime";
      this.timestamp = (Long)baseListener.getEntry(text5, timestamp5, jsonObject2);
      baseListener = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter3();
      Boolean enabled2 = this.enabled4;
      String text6 = "Marked";
      this.enabled4 = (Boolean)baseListener.getEntry(text6, enabled2, jsonObject2);
      baseListener = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter3();
      Boolean enabled6 = this.enabled3;
      String text7 = "Working";
      this.enabled3 = (Boolean)baseListener.getEntry(text7, enabled6, jsonObject2);
      baseListener = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter3();
      Boolean enabled7 = this.enabled;
      String text8 = "LoggedIn";
      this.enabled = (Boolean)baseListener.getEntry(text8, enabled7, jsonObject2);
      baseListener = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter();
      JsonObject jsonObject3 = this.jsonObject;
      String text9 = "AccountData";
      this.jsonObject = (JsonObject)baseListener.getEntry(text9, jsonObject3, jsonObject2);
      JsonPropertyGetter jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter4();
      Object entry = null;
      String text10 = "Banned";
      JsonArray entry2 = (JsonArray)jsonPropertyGetter.getEntry(text10, entry, jsonObject2);
      this.hashMap4 = this.resolveHashMap(entry2);
      jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter4();
      Object entry3 = null;
      String text11 = "Unbanned";
      JsonArray entry4 = (JsonArray)jsonPropertyGetter.getEntry(text11, entry3, jsonObject2);
      this.hashMap3 = this.resolveHashMap(entry4);
      if (this.enabled) {
         this.updateState6();
      }
   }

   public boolean getByText(String text) {
      return this.hashMap4.get(text) != null;
   }

   private void applyResourceLocation(int count5, int count6, ResourceLocation location) {
      MinecraftAccessor.minecraft.getTextureManager().applyResourceLocation3(location);
      GlStateManager.disableBlend();
      TexturesGuiIconsPngManager.applyIndex(count5, count6, 0.0F, 0.0F, 10, 10, 10.0F, 10.0F);
      GlStateManager.enableBlend();
   }

   public HashMap getHashMap3() {
      return this.hashMap3;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   private void updateState6() {
      switch (AngleParser.countArray[this.movementValueProperty.ordinal()]) {
         case 1:
            DropdownMenuWidget dropdownMenuWidget = DropdownMenuWidget.dropdownMenuWidget;
            CompletableFuture completableFuture = CompletableFuture.runAsync(this::updateState);
            dropdownMenuWidget.setCompletableFuture(completableFuture);
            break;
         case 2:
            DropdownMenuWidget dropdownMenuWidget2 = DropdownMenuWidget.dropdownMenuWidget;
            CompletableFuture completableFuture2 = CompletableFuture.runAsync(this::updateState2);
            dropdownMenuWidget2.setCompletableFuture(completableFuture2);
            break;
         case 3:
            DropdownMenuWidget dropdownMenuWidget3 = DropdownMenuWidget.dropdownMenuWidget;
            CompletableFuture completableFuture3 = CompletableFuture.runAsync(this::updateState4);
            dropdownMenuWidget3.setCompletableFuture(completableFuture3);
            break;
         case 4:
            DropdownMenuWidget dropdownMenuWidget4 = DropdownMenuWidget.dropdownMenuWidget;
            CompletableFuture completableFuture4 = CompletableFuture.runAsync(this::updateState3);
            dropdownMenuWidget4.setCompletableFuture(completableFuture4);
            break;
         case 5:
            DropdownMenuWidget dropdownMenuWidget5 = DropdownMenuWidget.dropdownMenuWidget;
            CompletableFuture completableFuture5 = CompletableFuture.runAsync(this::updateState5);
            dropdownMenuWidget5.setCompletableFuture(completableFuture5);
      }
   }

   public void applyText(String text) {
      this.hashMap4.remove(text);
      this.hashMap2.remove(text);
      if ((Long)this.hashMap3.get(text) == null) {
         this.hashMap3.put(text, System.currentTimeMillis());
         ResourceLocation location = new ResourceLocation("servers/" + text + "/icon");
         this.hashMap.put(text, new Pair(location, (DynamicTexture)MinecraftAccessor.minecraft.getTextureManager().get(location)));
      } else {
         this.hashMap3.remove(text);
         this.hashMap3.put(text, System.currentTimeMillis());
      }
   }

   private void updateState3() {
      MsnManager.applyAccountProfile2(this);
   }

   public synchronized void currentTimeMillis(JsonObject jsonObject2, String text) {
      this.jsonObject = jsonObject2;
      this.labelText = text;
      this.timestamp2 = System.currentTimeMillis();
   }

   public long getTimestamp3() {
      return this.timestamp3;
   }

   public int getCount4() {
      return this.count4;
   }

   public HashMap resolveHashMap(JsonArray jsonArray) {
      if (jsonArray == null) {
         return new HashMap();
      } else {
         HashMap map = new HashMap();

         for (JsonElement jsonElement : jsonArray) {
            if (jsonElement instanceof JsonObject) {
               try {
                  JsonObject jsonObject2 = (JsonObject)jsonElement;
                  JsonPropertyGetter baseListener = MysteryBoxService.mysteryBoxPacketListener.getBaseListener();
                  String text = "";
                  String text2 = "Key";
                  String entry = (String)baseListener.getEntry(text2, text, jsonObject2);
                  baseListener = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter6();
                  Long value = Long.MIN_VALUE;
                  String text3 = "Value";
                  Long entry2 = (Long)baseListener.getEntry(text3, value, jsonObject2);
                  if (!entry.isEmpty() && entry2 != Long.MIN_VALUE) {
                     map.put(entry, entry2);
                  }
               } catch (Exception exception) {
               }
            }
         }

         return map;
      }
   }

   public JsonObject getJsonObjectAsJsonObject() {
      JsonObject jsonObject2 = new JsonObject();
      JsonPropertyGetter baseListener = MysteryBoxService.mysteryBoxPacketListener.getBaseListener();
      String labelText2 = this.labelText;
      String text = "Name";
      baseListener.applyText(text, labelText2, jsonObject2);
      JsonPropertyGetter baseListener2 = MysteryBoxService.mysteryBoxPacketListener.getBaseListener();
      String text2 = this.movementValueProperty.name();
      String text3 = "Type";
      baseListener2.applyText(text3, text2, jsonObject2);
      JsonPropertyGetter jsonPropertyGetter = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter6();
      Long timestamp4 = this.timestamp2;
      String text4 = "LastLoginTime";
      jsonPropertyGetter.applyText(text4, timestamp4, jsonObject2);
      JsonPropertyGetter jsonPropertyGetter2 = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter6();
      Long timestamp5 = this.timestamp;
      String text5 = "PlayTime";
      jsonPropertyGetter2.applyText(text5, timestamp5, jsonObject2);
      JsonPropertyGetter jsonPropertyGetter3 = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter3();
      Boolean enabled2 = this.enabled4;
      String text6 = "Marked";
      jsonPropertyGetter3.applyText(text6, enabled2, jsonObject2);
      JsonPropertyGetter jsonPropertyGetter4 = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter3();
      Boolean enabled6 = this.enabled3;
      String text7 = "Working";
      jsonPropertyGetter4.applyText(text7, enabled6, jsonObject2);
      JsonPropertyGetter jsonPropertyGetter5 = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter3();
      Boolean enabled7 = this.enabled;
      String text8 = "LoggedIn";
      jsonPropertyGetter5.applyText(text8, enabled7, jsonObject2);
      JsonPropertyGetter jsonPropertyGetter6 = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter();
      JsonObject jsonObject3 = this.jsonObject;
      String text9 = "AccountData";
      jsonPropertyGetter6.applyText(text9, jsonObject3, jsonObject2);
      JsonPropertyGetter jsonPropertyGetter7 = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter4();
      HashMap hashMap5 = this.hashMap4;
      JsonArray jsonArray = this.resolveJsonArray(hashMap5);
      String text10 = "Banned";
      jsonPropertyGetter7.applyText(text10, jsonArray, jsonObject2);
      JsonPropertyGetter jsonPropertyGetter8 = MysteryBoxService.mysteryBoxPacketListener.getJsonPropertyGetter4();
      HashMap hashMap6 = this.hashMap3;
      JsonArray jsonArray2 = this.resolveJsonArray(hashMap6);
      String text11 = "Unbanned";
      jsonPropertyGetter8.applyText(text11, jsonArray2, jsonObject2);
      return jsonObject2;
   }

   public int getCount() {
      return this.count;
   }

   public MovementValueProperty getMovementValueProperty() {
      return this.movementValueProperty;
   }

   public AccountProfile() {
      this.labelText = "";
      this.enabled4 = false;
      this.enabled3 = true;
      this.timestamp2 = 0L;
      this.timestamp = 0L;
      this.timestamp3 = 0L;
      this.hashMap4 = new HashMap();
      this.hashMap3 = new HashMap();
      this.hashMap2 = new HashMap();
      this.hashMap = new HashMap();
      this.enabled = false;
   }

   public AccountProfile(String text, JsonObject jsonObject2, MovementValueProperty movementValueProperty2) {
      this.labelText = "";
      this.enabled4 = false;
      this.enabled3 = true;
      this.timestamp2 = 0L;
      this.timestamp = 0L;
      this.timestamp3 = 0L;
      this.hashMap4 = new HashMap();
      this.hashMap3 = new HashMap();
      this.hashMap2 = new HashMap();
      this.hashMap = new HashMap();
      this.enabled = false;
      this.labelText = text;
      this.jsonObject = jsonObject2;
      this.movementValueProperty = movementValueProperty2;
      this.timestamp2 = System.currentTimeMillis();
   }
}
