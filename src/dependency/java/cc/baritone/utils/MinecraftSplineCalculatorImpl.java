package cc.baritone.utils;

import client.event.PreUpdateDispatcher;
import client.util.SplineCalculator;
import java.util.List;
import net.minecraft.client.Minecraft;

public class MinecraftSplineCalculatorImpl extends SplineCalculator {
   public List list;
   public final MessagesEnableAllGuiScreen messagesEnableAllGuiScreen;

   public static Minecraft resolveMinecraft5(MinecraftSplineCalculatorImpl minecraftSplineCalculatorImpl) {
      return minecraftSplineCalculatorImpl.mc;
   }

   public static Minecraft resolveMinecraft4(MinecraftSplineCalculatorImpl minecraftSplineCalculatorImpl) {
      return minecraftSplineCalculatorImpl.mc;
   }

   @Override
   public int size() {
      return this.list.size();
   }

   @Override
   public PreUpdateDispatcher get(int count) {
      return (PreUpdateDispatcher)this.list.get(count);
   }

   public MinecraftSplineCalculatorImpl(MessagesEnableAllGuiScreen messagesEnableAllGuiScreen2) {
      super(MessagesEnableAllGuiScreen.resolveMinecraft(messagesEnableAllGuiScreen2), messagesEnableAllGuiScreen2.width, messagesEnableAllGuiScreen2.height, 39, messagesEnableAllGuiScreen2.height - 32, 20);
      this.messagesEnableAllGuiScreen = messagesEnableAllGuiScreen2;
      this.list = new ArrayListImpl(this);
   }

   public static Minecraft resolveMinecraft2(MinecraftSplineCalculatorImpl minecraftSplineCalculatorImpl) {
      return minecraftSplineCalculatorImpl.mc;
   }

   public static Minecraft resolveMinecraft6(MinecraftSplineCalculatorImpl minecraftSplineCalculatorImpl) {
      return minecraftSplineCalculatorImpl.mc;
   }

   public static Minecraft resolveMinecraft(MinecraftSplineCalculatorImpl minecraftSplineCalculatorImpl) {
      return minecraftSplineCalculatorImpl.mc;
   }

   public static Minecraft resolveMinecraftByMinecraftSplineC(MinecraftSplineCalculatorImpl minecraftSplineCalculatorImpl) {
      return minecraftSplineCalculatorImpl.mc;
   }

   public static Minecraft resolveMinecraft3(MinecraftSplineCalculatorImpl minecraftSplineCalculatorImpl) {
      return minecraftSplineCalculatorImpl.mc;
   }
}
