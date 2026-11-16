package net.minecraft.item;

import client.setting.ProviderOptionType;
import client.setting.StepsGotHelper;
import client.util.TickManager;
import java.util.List;
import java.util.function.Function;

public class TickManagerHelper {
   public static double computeValue(double posX, double posY, double posZ) {
      return posY + posX * (posZ - posY);
   }

   public static float computeFactor(double posX, double posY, double posZ) {
      return (float)computeValue(posX, posY, posZ);
   }

   public static float computeFactor(TickManager tickManager, ProviderOptionType providerOptionType, Function mapper) {
      ProviderOptionType providerOptionType2 = providerOptionType;
      long value = 0x51D4AAF9EA3AL;
      if (tickManager.memberRef2 >= tickManager.memberRef) {
         return tickManager.memberRef3.floatValue();
      } else if (tickManager.memberRef2 == 0.0 && tickManager.memberRef == 0.0) {
         return tickManager.memberRef3.floatValue();
      } else if (providerOptionType == ProviderOptionType.CUSTOM && mapper != null) {
         return computeFactor((Double)mapper.apply(tickManager.memberRef2 / tickManager.memberRef), tickManager.memberRef4, tickManager.memberRef3);
      } else {
         if (providerOptionType == ProviderOptionType.NONE && tickManager.advancedListEntry != null) {
            providerOptionType2 = tickManager.advancedListEntry.providerOptionType;
         }

         double offset = tickManager.memberRef2 / tickManager.memberRef;
         List list = tickManager.advancedListEntry == null ? null : tickManager.advancedListEntry.list;
         return computeFactor(StepsGotHelper.computeValue(offset, providerOptionType2, list), tickManager.memberRef4, tickManager.memberRef3);
      }
   }
}
