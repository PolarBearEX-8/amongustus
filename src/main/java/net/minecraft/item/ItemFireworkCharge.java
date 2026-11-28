package net.minecraft.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.StatCollector;

public class ItemFireworkCharge extends Item {

   @Override
   public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      if (stack.hasTagCompound()) {
         NBTTagCompound compoundTag = stack.getTagCompound().getCompoundTag("Explosion");
         if (compoundTag != null) {
            addExplosionInfo(compoundTag, list);
         }
      }
   }

   public static NBTBase getExplosionTag(ItemStack stack, String str) {
      if (stack.hasTagCompound()) {
         NBTTagCompound compoundTag = stack.getTagCompound().getCompoundTag("Explosion");
         if (compoundTag != null) {
            return compoundTag.getTag(str);
         }
      }

      return null;
   }

   @Override
   public int getColorFromItemStack(ItemStack stack, int n) {
      if (n != 1) {
         return super.getColorFromItemStack(stack, n);
      } else {
         NBTBase explosionTag = getExplosionTag(stack, "Colors");
         if (!(explosionTag instanceof NBTTagIntArray)) {
            return 9079434;
         } else {
            int[] intArray = ((NBTTagIntArray)explosionTag).getIntArray();
            if (intArray.length == 1) {
               return intArray[0];
            } else {
               int n2 = 0;
               int n3 = 0;
               int n4 = 0;

               for (int n5 : intArray) {
                  n2 += (n5 & 0xFF0000) >> 16;
                  n3 += (n5 & 0xFF00) >> 8;
                  n4 += (n5 & 0xFF) >> 0;
               }

               n2 /= intArray.length;
               n3 /= intArray.length;
               n4 /= intArray.length;
               return n2 << 16 | n3 << 8 | n4;
            }
         }
      }
   }

   public static void addExplosionInfo(NBTTagCompound tag, List list) {
      byte b = tag.getByte("Type");
      if (b >= 0 && b <= 4) {
         list.add(StatCollector.translateToLocal("item.fireworksCharge.type." + b).trim());
      } else {
         list.add(StatCollector.translateToLocal("item.fireworksCharge.type").trim());
      }

      int[] intArray = tag.getIntArray("Colors");
      if (intArray.length > 0) {
         boolean flag = true;
         String str = "";

         for (int n : intArray) {
            if (!flag) {
               str = str + ", ";
            }

            flag = false;
            boolean flag2 = false;

            for (int i = 0; i < ItemDye.dyeColors.length; i++) {
               if (n == ItemDye.dyeColors[i]) {
                  flag2 = true;
                  str = str + StatCollector.translateToLocal("item.fireworksCharge." + EnumDyeColor.byDyeDamage(i).getUnlocalizedName());
                  break;
               }
            }

            if (!flag2) {
               str = str + StatCollector.translateToLocal("item.fireworksCharge.customColor");
            }
         }

         list.add(str);
      }

      int[] intArray2 = tag.getIntArray("FadeColors");
      if (intArray2.length > 0) {
         boolean flag3 = true;
         String str2 = StatCollector.translateToLocal("item.fireworksCharge.fadeTo") + " ";

         for (int n2 : intArray2) {
            if (!flag3) {
               str2 = str2 + ", ";
            }

            flag3 = false;
            boolean flag4 = false;

            for (int j = 0; j < 16; j++) {
               if (n2 == ItemDye.dyeColors[j]) {
                  flag4 = true;
                  str2 = str2 + StatCollector.translateToLocal("item.fireworksCharge." + EnumDyeColor.byDyeDamage(j).getUnlocalizedName());
                  break;
               }
            }

            if (!flag4) {
               str2 = str2 + StatCollector.translateToLocal("item.fireworksCharge.customColor");
            }
         }

         list.add(str2);
      }

      if (tag.getBoolean("Trail")) {
         list.add(StatCollector.translateToLocal("item.fireworksCharge.trail"));
      }

      if (tag.getBoolean("Flicker")) {
         list.add(StatCollector.translateToLocal("item.fireworksCharge.flicker"));
      }
   }
}
