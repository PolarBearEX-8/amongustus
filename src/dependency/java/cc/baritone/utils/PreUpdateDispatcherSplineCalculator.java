package cc.baritone.utils;

import client.event.PreUpdateDispatcher;
import client.util.SplineCalculator;
import java.util.List;
import net.minecraft.client.Minecraft;

public class PreUpdateDispatcherSplineCalculator extends SplineCalculator {
   public final int count20;
   public final int count21;
   public List list;

   @Override
   public int getCount20() {
      return this.count9 - 18;
   }

   public void clear() {
      this.list.clear();
   }

   public PreUpdateDispatcherSplineCalculator(Minecraft mc, int count, int count2, int count3, int count4) {
      super(mc, count, count2, count3, count2 - count4, mc.fontRendererObj.indexOffset + 1);
      this.count20 = count3;
      this.count21 = count4;
      this.list = new java.util.ArrayList();
   }

   @Override
   public int getCount22() {
      return this.count9 - 10;
   }

   public void applyText(String text, String text2) {
      for (String text3 : (Iterable<String>)(Iterable<?>)CouldnOpenHelper.resolveList(text, this.getCount20())) {
         this.list.add(new MinecraftPreUpdateDisp(this.mc, text3, text2));
      }
   }

   @Override
   public PreUpdateDispatcher get(int count) {
      return (PreUpdateDispatcher)this.list.get(count);
   }

   @Override
   public int size() {
      return this.list.size();
   }

   public void applyText(String text) {
      for (String text2 : (Iterable<String>)(Iterable<?>)CouldnOpenHelper.resolveList(text, this.getCount20())) {
         this.list.add(new MinecraftPreUpdateDispatcher(this.mc, text2, 16777215));
      }
   }

   public void add() {
      this.list.add(new MinecraftPreUpdateDispatcher(this.mc, "", 16777215));
   }
}
