package net.minecraft.item;

import augustus.modules.misc.ClientSettings;
import client.Client;
import client.event.DynamicListener;
import client.render.RenderUtils;
import client.setting.EasingFunction;
import client.util.AltDoubleEntry;
import client.util.AuxiliaryListenerAdapter;
import client.util.KeybindAnimation;
import client.util.MinecraftAccessor;
import client.util.SecondaryListEntry;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

public final class ServiceItemEntry implements MinecraftAccessor {
   public static final int count4 = 2;
   public static final int count2 = 20;
   public AltDoubleEntry altDoubleEntry;
   public static final int count3 = -939524096;
   public final List list;
   public KeybindAnimation keybindAnimation;
   public boolean enabled2;
   public boolean enabled;
   public int count;

   public void applyIndex(int count5, int count6, float factor) {
      this.applyIndex(count5);
      int floatItem = (int)this.keybindAnimation.getFloat();
      ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
      if (!this.enabled) {
         MinecraftAccessor.minecraft
            .fontRendererObj
            .getIndex2(
               "§l->",
               (float)(floatItem - MinecraftAccessor.minecraft.fontRendererObj.getIndex("§l->")),
               (float)scaledResolution.getScaledHeight() / 2.0F - (float)MinecraftAccessor.minecraft.fontRendererObj.indexOffset / 2.0F,
               -1
            );
      } else {
         int count7 = scaledResolution.getScaledWidth() - floatItem;
         RenderUtils.processAction(floatItem, 0, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), -939524096);
         int value = (int)this.altDoubleEntry.getValue();

         for (String text : (Iterable<String>)(Iterable<?>)this.getList()) {
            DynamicListener dynamicListener = this.resolveDynamicListener(text);
            int count8 = count5 >= scaledResolution.getScaledWidth() - this.count - 2
                  && count6 >= value - 1
                  && count6 <= value + MinecraftAccessor.minecraft.fontRendererObj.indexOffset - 1
               ? -4144960
               : -1;
            if (dynamicListener != null && dynamicListener.isActive()) {
               int count9 = floatItem + this.count / 2 - MinecraftAccessor.minecraft.fontRendererObj.getIndex(text) / 2 - 2;
               MinecraftAccessor.minecraft
                  .fontRendererObj
                  .getIndex2(text, (float)count9, (float)value, dynamicListener instanceof AuxiliaryListenerAdapter ? Client.getClient().getColor().getRGB() : count8);
            } else {
               MinecraftAccessor.minecraft.fontRendererObj.getIndex2(text, (float)(floatItem + 2), (float)value, count8);
            }

            value += MinecraftAccessor.minecraft.fontRendererObj.indexOffset + 1;
         }

         if (count7 >= this.count - 2) {
            this.altDoubleEntry.updateState();
         }
      }
   }

   private DynamicListener resolveDynamicListener(String text) {
      for (DynamicListener dynamicListener : (Iterable<DynamicListener>)(Iterable<?>)this.list) {
         String labelText = dynamicListener.getLabelText();
         if (labelText != null && labelText.equals(text)) {
            return dynamicListener;
         }

         Set set = dynamicListener.getSet();
         if (set != null && set.contains(text)) {
            return dynamicListener;
         }
      }

      return null;
   }

   public void updateState() {
      ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
      this.altDoubleEntry = new AltDoubleEntry(MinecraftAccessor.minecraft.fontRendererObj.indexOffset + 1);
      AltDoubleEntry altDoubleEntry2 = this.altDoubleEntry;
      int count5 = scaledResolution.getScaledWidth() - 4;
      int count6 = scaledResolution.getScaledWidth() - 2;
      int scaledHeight = scaledResolution.getScaledHeight();
      int count7 = count6;
      byte byteValue = 0;
      int count8 = count5;
      altDoubleEntry2.applyIndex(count8, byteValue, count7, scaledHeight);
      List list2 = this.getList();
      altDoubleEntry2 = this.altDoubleEntry;
      int size = list2.size();
      altDoubleEntry2.setCount4(size);
      this.count = 0;

      for (String text : (Iterable<String>)(Iterable)(list2)) {
         this.count = 20 + Math.max(this.count - 20, MinecraftAccessor.minecraft.fontRendererObj.getIndex(text));
      }

      this.count += 4;
      this.keybindAnimation = new KeybindAnimation((float)scaledResolution.getScaledWidth(), 0.0F, (float)(scaledResolution.getScaledWidth() - this.count), 0.0F);
   }

   public boolean checkIndex(int count5, int count6, int count7) {
      if (!this.enabled) {
         return false;
      } else {
         ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
         int value = (int)this.altDoubleEntry.getValue();

         for (String text : (Iterable<String>)(Iterable)(this.getList())) {
            if (count5 >= scaledResolution.getScaledWidth() - this.count - 2
               && count6 >= value - 1
               && count6 <= value + MinecraftAccessor.minecraft.fontRendererObj.indexOffset - 1) {
               DynamicListener dynamicListener = this.resolveDynamicListener(text);
               if (dynamicListener != null) {
                  ClientSettings.processShort();
                  dynamicListener.run(text);
                  return true;
               }
            }

            value += MinecraftAccessor.minecraft.fontRendererObj.indexOffset + 1;
         }

         return false;
      }
   }

   private List getList() {
      LinkedList list2 = new LinkedList();

      for (DynamicListener dynamicListener : (Iterable<DynamicListener>)(Iterable<?>)this.list) {
         Set set = dynamicListener.getSet();
         if (set != null) {
            list2.addAll(set);
         } else {
            list2.add(dynamicListener.getLabelText());
         }
      }

      return list2;
   }

   public static SecondaryListEntry getSecondaryListEntry() {
      return new SecondaryListEntry();
   }

   public boolean isActive() {
      boolean flag = this.enabled && Mouse.getX() > new ScaledResolution(MinecraftAccessor.minecraft).getScaledWidth() - this.count;
      if (flag) {
         this.altDoubleEntry.updateState5();
      }

      return flag;
   }

   private void applyIndex(int count5) {
      ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
      int scaledWidth = scaledResolution.getScaledWidth();
      this.keybindAnimation.processFloat((float)scaledWidth, 0.0F, (float)(scaledWidth - this.count), 0.0F);
      if (!((float)count5 > Math.max(this.keybindAnimation.getFloat(), (float)(scaledWidth - this.count))) && count5 <= scaledWidth - 40) {
         if (this.enabled2) {
            this.keybindAnimation.updateEasingFunction(200, EasingFunction.easeOutQuad, 0, false);
         }

         this.enabled2 = false;
      } else {
         if (!this.enabled2) {
            this.keybindAnimation.updateEasingFunction(200, EasingFunction.easeInQuad, 1, false);
         }

         this.enabled2 = true;
      }

      this.enabled = this.keybindAnimation.getDouble3() > 0.0;
   }

   public ServiceItemEntry(List list2) {
      this.list = list2;
   }
}
