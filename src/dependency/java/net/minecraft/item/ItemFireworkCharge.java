package net.minecraft.item;

import client.setting.LocalOptionType;
import client.util.LISTManager;
import client.util.ProviderIntArrayEntry;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class ItemFireworkCharge extends Item {
   public static void applyNBTTagCompound(NBTTagCompound tagCompound, List list) {
      byte byteValue = tagCompound.computeByteCode("Type");
      if (byteValue >= 0 && byteValue <= 4) {
         list.add(I18n.getString2("item.fireworksCharge.type." + byteValue).trim());
      } else {
         list.add(I18n.getString2("item.fireworksCharge.type").trim());
      }

      int[] countArray = tagCompound.resolveIndexArray("Colors");
      if (countArray.length > 0) {
         boolean flag = true;
         String text = "";

         for (int count : countArray) {
            if (!flag) {
               text = text + ", ";
            }

            flag = false;
            boolean flag2 = false;

            for (int index = 0; index < ItemDye.countArray.length; index++) {
               if (count == ItemDye.countArray[index]) {
                  flag2 = true;
                  text = text + I18n.getString2("item.fireworksCharge." + LocalOptionType.resolveLocalOptionTypeByIndex(index).getLabelText());
                  break;
               }
            }

            if (!flag2) {
               text = text + I18n.getString2("item.fireworksCharge.customColor");
            }
         }

         list.add(text);
      }

      int[] countArray2 = tagCompound.resolveIndexArray("FadeColors");
      if (countArray2.length > 0) {
         boolean flag3 = true;
         String text2 = I18n.getString2("item.fireworksCharge.fadeTo") + " ";

         for (int count2 : countArray2) {
            if (!flag3) {
               text2 = text2 + ", ";
            }

            flag3 = false;
            boolean flag4 = false;

            for (int index2 = 0; index2 < 16; index2++) {
               if (count2 == ItemDye.countArray[index2]) {
                  flag4 = true;
                  text2 = text2 + I18n.getString2("item.fireworksCharge." + LocalOptionType.resolveLocalOptionTypeByIndex(index2).getLabelText());
                  break;
               }
            }

            if (!flag4) {
               text2 = text2 + I18n.getString2("item.fireworksCharge.customColor");
            }
         }

         list.add(text2);
      }

      if (tagCompound.matchesText("Trail")) {
         list.add(I18n.getString2("item.fireworksCharge.trail"));
      }

      if (tagCompound.matchesText("Flicker")) {
         list.add(I18n.getString2("item.fireworksCharge.flicker"));
      }
   }

   public static LISTManager resolveLISTManager(ItemStack stack, String text) {
      if (stack.canExecute()) {
         NBTTagCompound nBTTagCompound = stack.getStackTagCompound().getNBTTagCompound("Explosion");
         if (nBTTagCompound != null) {
            return nBTTagCompound.get(text);
         }
      }

      return null;
   }

   @Override
   public int processAction(ItemStack stack, int count) {
      if (count != 1) {
         return super.processAction(stack, count);
      } else {
         LISTManager lISTManager = resolveLISTManager(stack, "Colors");
         if (!(lISTManager instanceof ProviderIntArrayEntry)) {
            return 9079434;
         } else {
            int[] countArray = ((ProviderIntArrayEntry)lISTManager).getCountArray();
            if (countArray.length == 1) {
               return countArray[0];
            } else {
               int count2 = 0;
               int count3 = 0;
               int count4 = 0;

               for (int count5 : countArray) {
                  count2 += (count5 & 0xFF0000) >> 16;
                  count3 += (count5 & 0xFF00) >> 8;
                  count4 += (count5 & 0xFF) >> 0;
               }

               int count6 = count2 / countArray.length;
               int count7 = count3 / countArray.length;
               int count8 = count4 / countArray.length;
               return count6 << 16 | count7 << 8 | count8;
            }
         }
      }
   }

   @Override
   public void processAction(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      if (stack.canExecute()) {
         NBTTagCompound nBTTagCompound = stack.getStackTagCompound().getNBTTagCompound("Explosion");
         if (nBTTagCompound != null) {
            applyNBTTagCompound(nBTTagCompound, list);
         }
      }
   }
}
