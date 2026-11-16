package cc.baritone.utils;

import client.event.PreUpdateDispatcher;
import client.util.SplineCalculator;
import java.util.List;
import net.minecraft.client.Minecraft;

public class MinecraftSplineCalculatorAdapter extends SplineCalculator {
   public List list;
   public final GuiDoneGuiScreen guiDoneGuiScreen;

   public static Minecraft resolveMinecraft6(MinecraftSplineCalculatorAdapter minecraftSplineCalculatorAdapter) {
      return minecraftSplineCalculatorAdapter.mc;
   }

   @Override
   public int size() {
      return this.list.size();
   }

   public static Minecraft resolveMinecraft3(MinecraftSplineCalculatorAdapter minecraftSplineCalculatorAdapter) {
      return minecraftSplineCalculatorAdapter.mc;
   }

   @Override
   public int getCount20() {
      return this.guiDoneGuiScreen.width - 20;
   }

   public static Minecraft resolveMinecraft4(MinecraftSplineCalculatorAdapter minecraftSplineCalculatorAdapter) {
      return minecraftSplineCalculatorAdapter.mc;
   }

   @Override
   public PreUpdateDispatcher get(int count) {
      return (PreUpdateDispatcher)this.list.get(count);
   }

   public static Minecraft resolveMinecraft2(MinecraftSplineCalculatorAdapter minecraftSplineCalculatorAdapter) {
      return minecraftSplineCalculatorAdapter.mc;
   }

   public static Minecraft resolveMinecraft(MinecraftSplineCalculatorAdapter minecraftSplineCalculatorAdapter) {
      return minecraftSplineCalculatorAdapter.mc;
   }

   public MinecraftSplineCalculatorAdapter(GuiDoneGuiScreen guiDoneGuiScreen2) {
      super(
         GuiDoneGuiScreen.resolveMinecraftByGuiDoneGuiScreen(guiDoneGuiScreen2),
         guiDoneGuiScreen2.width,
         GuiDoneGuiScreen.computeCount(guiDoneGuiScreen2),
         23,
         GuiDoneGuiScreen.computeCount(guiDoneGuiScreen2),
         22
      );
      this.guiDoneGuiScreen = guiDoneGuiScreen2;
      this.list = new ArrayList(this);
      this.enabled2 = true;
   }

   @Override
   public boolean checkIndex(int count) {
      return count == GuiDoneGuiScreen.computeCountByGuiDoneGuiScreen(this.guiDoneGuiScreen);
   }

   public static Minecraft resolveMinecraft5(MinecraftSplineCalculatorAdapter minecraftSplineCalculatorAdapter) {
      return minecraftSplineCalculatorAdapter.mc;
   }

   @Override
   public int getCount22() {
      return this.guiDoneGuiScreen.width - 10;
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.count3 = this.count7 = GuiDoneGuiScreen.computeCount(this.guiDoneGuiScreen);
      super.applyIndex(count, count2, factor);
   }

   public static Minecraft resolveMinecraftByMinecraftSplineC(MinecraftSplineCalculatorAdapter minecraftSplineCalculatorAdapter) {
      return minecraftSplineCalculatorAdapter.mc;
   }

   @Override
   public void updateState() {
      if (this.count2 < GuiDoneGuiScreen.computeCount(this.guiDoneGuiScreen)) {
         super.updateState();
      }
   }
}
