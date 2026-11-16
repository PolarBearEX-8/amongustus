package client.util;

import client.setting.CombatNumberProperty;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;

public class ManagedListener87EntryAdapter extends Listener87Entry {
   @Override
   public String getText() {
      return "enchant";
   }

   public String[] getTextArray() {
      return VanillaRunnable.getVanillaRunnable().getTextArray();
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.enchant.usage";
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 0;
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1 ? resolveList(textArray, this.getTextArray()) : (textArray.length == 2 ? resolveList(textArray, EnchantmentManager.keySet()) : null);
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 2) {
         throw new AimDispatcher("commands.enchant.usage", new Object[0]);
      } else {
         EntityPlayerAdapter entityPlayerAdapter = resolveEntityPlayerAdapter(iCommandSender, textArray[0]);
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ITEMS, 0);

         int count;
         try {
            count = computeCount(textArray[1], 0);
         } catch (AimDirector exception) {
            EnchantmentManager enchantmentManager = EnchantmentManager.get(textArray[1]);
            if (enchantmentManager == null) {
               throw exception;
            }

            count = enchantmentManager.indexOffset;
         }

         int count2 = 1;
         ItemStack stack = entityPlayerAdapter.getHeldItem();
         if (stack == null) {
            throw new InternalExceptionAdapter("commands.enchant.noItem", new Object[0]);
         } else {
            EnchantmentManager enchantmentManager2 = EnchantmentManager.resolveEnchantmentManager(count);
            if (enchantmentManager2 == null) {
               throw new AimDirector("commands.enchant.notFound", new Object[]{count});
            } else if (!enchantmentManager2.matchesItemStack(stack)) {
               throw new InternalExceptionAdapter("commands.enchant.cantEnchant", new Object[0]);
            } else {
               if (textArray.length >= 3) {
                  count2 = computeCount(textArray[2], enchantmentManager2.getCount2(), enchantmentManager2.getCount());
               }

               if (stack.canExecute()) {
                  NBTTagList extendedLoggerEntry = stack.getExtendedLoggerEntry();
                  if (extendedLoggerEntry != null) {
                     for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
                        short shortValue = extendedLoggerEntry.getNBTTagCompound(index).processValue("id");
                        if (EnchantmentManager.resolveEnchantmentManager(shortValue) != null) {
                           EnchantmentManager enchantmentManager3 = EnchantmentManager.resolveEnchantmentManager(shortValue);
                           if (!enchantmentManager3.matchesEnchantmentManager(enchantmentManager2)) {
                              throw new InternalExceptionAdapter(
                                 "commands.enchant.cantCombine",
                                 new Object[]{enchantmentManager2.resolveText(count2), enchantmentManager3.resolveText(extendedLoggerEntry.getNBTTagCompound(index).processValue("lvl"))}
                              );
                           }
                        }
                     }
                  }
               }

               stack.applyEnchantmentManager(enchantmentManager2, count2);
               applyICommandSender(iCommandSender, this, "commands.enchant.success", new Object[0]);
               iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ITEMS, 1);
            }
         }
      }
   }
}
