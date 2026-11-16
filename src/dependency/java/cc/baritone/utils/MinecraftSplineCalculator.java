package cc.baritone.utils;

import client.event.PreUpdateDispatcher;
import client.util.SplineCalculator;
import java.util.List;
import net.minecraft.client.Minecraft;

public class MinecraftSplineCalculator extends SplineCalculator {
   public int count20;
   public int count21;
   public List list;
   public final UserEntityGuiScreen userEntityGuiScreen;

   public static int computeCountByMinecraftSplineC(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.count21;
   }

   public MinecraftSplineCalculator(UserEntityGuiScreen userEntityGuiScreen2) {
      super(UserEntityGuiScreen.resolveMinecraft(userEntityGuiScreen2), userEntityGuiScreen2.width, userEntityGuiScreen2.height, 39, userEntityGuiScreen2.height - 32, 20);
      this.userEntityGuiScreen = userEntityGuiScreen2;
      this.list = new PassiveHostileArrayList(this);
   }

   @Override
   public int getCount22() {
      return this.userEntityGuiScreen.width / 2 + this.count21 / 2 + 10;
   }

   public static Minecraft resolveMinecraft5(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.mc;
   }

   public static Minecraft resolveMinecraft6(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.mc;
   }

   public static Minecraft resolveMinecraft3(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.mc;
   }

   public static Minecraft resolveMinecraft4(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.mc;
   }

   public static int computeCountByMinecraftSplineC(MinecraftSplineCalculator minecraftSplineCalculator, int count) {
      return minecraftSplineCalculator.count20 = count;
   }

   public static Minecraft resolveMinecraft11(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.mc;
   }

   public static Minecraft resolveMinecraft9(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.mc;
   }

   @Override
   public PreUpdateDispatcher get(int count) {
      return (PreUpdateDispatcher)this.list.get(count);
   }

   public static Minecraft resolveMinecraft7(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.mc;
   }

   public static Minecraft resolveMinecraft10(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.mc;
   }

   @Override
   public int size() {
      return this.list.size();
   }

   public static Minecraft resolveMinecraftByMinecraftSplineC(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.mc;
   }

   public static Minecraft resolveMinecraft(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.mc;
   }

   public static int computeCount(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.count20;
   }

   public static Minecraft resolveMinecraft2(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.mc;
   }

   public static Minecraft resolveMinecraft8(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.mc;
   }

   public static Minecraft resolveMinecraft12(MinecraftSplineCalculator minecraftSplineCalculator) {
      return minecraftSplineCalculator.mc;
   }

   public static int computeCount(MinecraftSplineCalculator minecraftSplineCalculator, int count) {
      return minecraftSplineCalculator.count21 = count;
   }
}
