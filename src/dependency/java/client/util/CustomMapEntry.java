package client.util;

import client.setting.CombatNumberProperty;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class CustomMapEntry extends Listener87Entry {
   public static final Map map = Maps.newHashMap();

   public String[] getTextArray() {
      return VanillaRunnable.getVanillaRunnable().getTextArray();
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public String getText() {
      return "replaceitem";
   }

   static {
      for (int index = 0; index < 54; index++) {
         map.put("slot.container." + index, index);
      }

      for (int index2 = 0; index2 < 9; index2++) {
         map.put("slot.hotbar." + index2, index2);
      }

      for (int index3 = 0; index3 < 27; index3++) {
         map.put("slot.inventory." + index3, 9 + index3);
      }

      for (int index4 = 0; index4 < 27; index4++) {
         map.put("slot.enderchest." + index4, 200 + index4);
      }

      for (int index5 = 0; index5 < 8; index5++) {
         map.put("slot.villager." + index5, 300 + index5);
      }

      for (int index6 = 0; index6 < 15; index6++) {
         map.put("slot.horse." + index6, 500 + index6);
      }

      map.put("slot.weapon", 99);
      map.put("slot.armor.head", 103);
      map.put("slot.armor.chest", 102);
      map.put("slot.armor.legs", 101);
      map.put("slot.armor.feet", 100);
      map.put("slot.horse.saddle", 400);
      map.put("slot.horse.armor", 401);
      map.put("slot.horse.chest", 499);
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.replaceitem.usage";
   }

   private int computeCount(String text) throws InternalExceptionAdapter {
      if (!map.containsKey(text)) {
         throw new InternalExceptionAdapter("commands.generic.parameter.invalid", new Object[]{text});
      } else {
         return (Integer)map.get(text);
      }
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 1) {
         throw new AimDispatcher("commands.replaceitem.usage", new Object[0]);
      } else {
         boolean flag;
         if (textArray[0].equals("entity")) {
            flag = false;
         } else {
            if (!textArray[0].equals("block")) {
               throw new AimDispatcher("commands.replaceitem.usage", new Object[0]);
            }

            flag = true;
         }

         int count;
         if (flag) {
            if (textArray.length < 6) {
               throw new AimDispatcher("commands.replaceitem.block.usage", new Object[0]);
            }

            count = 4;
         } else {
            if (textArray.length < 4) {
               throw new AimDispatcher("commands.replaceitem.entity.usage", new Object[0]);
            }

            count = 2;
         }

         int count2 = this.computeCount(textArray[count++]);

         Item item;
         try {
            item = resolveItem(iCommandSender, textArray[count]);
         } catch (AimDirector exception) {
            if (Block.getBlockFromName(textArray[count]) != Blocks.air) {
               throw exception;
            }

            item = null;
         }

         count++;
         int count3 = textArray.length > count ? computeCount(textArray[count++], 1, 64) : 1;
         int count4 = textArray.length > count ? parseInt(textArray[count++]) : 0;
         ItemStack stack = new ItemStack(item, count3, count4);
         if (textArray.length > count) {
            String string = resolveIChatComponent(iCommandSender, textArray, count).getString();

            try {
               stack.processAction(DampenerEstimator.resolveNBTTagCompound(string));
            } catch (SecondaryExceptionAdapter exception2) {
               throw new InternalExceptionAdapter("commands.replaceitem.tagError", new Object[]{exception2.getMessage()});
            }
         }

         if (stack.getItem() == null) {
            stack = null;
         }

         if (flag) {
            iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ITEMS, 0);
            BlockPos blockPos = resolveBlockPos(iCommandSender, textArray, 1, false);
            TileEntity tileEntity = iCommandSender.getWorldObj().resolveTileEntity(blockPos);
            if (tileEntity == null || !(tileEntity instanceof IInventory)) {
               throw new InternalExceptionAdapter("commands.replaceitem.noContainer", new Object[]{blockPos.getX(), blockPos.getY(), blockPos.getZ()});
            }

            IInventory iInventory = (IInventory)tileEntity;
            if (count2 >= 0 && count2 < iInventory.processAction4()) {
               iInventory.processAction(count2, stack);
            }
         } else {
            Entity entity = resolveEntity(iCommandSender, textArray[1]);
            iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ITEMS, 0);
            if (entity instanceof EntityPlayer) {
               ((EntityPlayer)entity).container.detectAndSendChanges();
            }

            if (!entity.matchesItemStack(count2, stack)) {
               throw new InternalExceptionAdapter("commands.replaceitem.failed", new Object[]{count2, count3, stack == null ? "Air" : stack.getIChatComponent()});
            }

            if (entity instanceof EntityPlayer) {
               ((EntityPlayer)entity).container.detectAndSendChanges();
            }
         }

         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ITEMS, count3);
         applyICommandSender(iCommandSender, this, "commands.replaceitem.success", new Object[]{count2, count3, stack == null ? "Air" : stack.getIChatComponent()});
      }
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1
         ? resolveList(textArray, new String[]{"entity", "block"})
         : (
            textArray.length == 2 && textArray[0].equals("entity")
               ? resolveList(textArray, this.getTextArray())
               : (
                  textArray.length >= 2 && textArray.length <= 4 && textArray[0].equals("block")
                     ? resolveListByTextArray(textArray, 1, blockPos)
                     : (
                        (textArray.length != 3 || !textArray[0].equals("entity")) && (textArray.length != 5 || !textArray[0].equals("block"))
                           ? (
                              textArray.length == 4 && textArray[0].equals("entity") || textArray.length == 6 && textArray[0].equals("block")
                                 ? resolveList(textArray, Item.iterableExtensionAdapter.getSet())
                                 : null
                           )
                           : resolveList(textArray, map.keySet())
                     )
               )
         );
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return textArray.length > 0 && textArray[0].equals("entity") && count == 1;
   }
}
