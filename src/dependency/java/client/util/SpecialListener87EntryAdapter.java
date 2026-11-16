package client.util;

import client.setting.CombatNumberProperty;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.command.ICommandSender;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class SpecialListener87EntryAdapter extends Listener87Entry {
   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 4) {
         throw new AimDispatcher("commands.setblock.usage", new Object[0]);
      } else {
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_BLOCKS, 0);
         BlockPos blockPos = resolveBlockPos(iCommandSender, textArray, 0, false);
         Block block = Listener87Entry.resolveBlock(iCommandSender, textArray[3]);
         int count = 0;
         if (textArray.length >= 5) {
            count = computeCount(textArray[4], 0, 15);
         }

         World worldObj = iCommandSender.getWorldObj();
         if (!worldObj.checkBlockPos12(blockPos)) {
            throw new InternalExceptionAdapter("commands.setblock.outOfWorld", new Object[0]);
         } else {
            NBTTagCompound tagCompound = new NBTTagCompound();
            boolean flag = false;
            if (textArray.length >= 7 && block.isState8()) {
               String string = resolveIChatComponent(iCommandSender, textArray, 6).getString();

               try {
                  tagCompound = DampenerEstimator.resolveNBTTagCompound(string);
                  flag = true;
               } catch (SecondaryExceptionAdapter exception) {
                  throw new InternalExceptionAdapter("commands.setblock.tagError", new Object[]{exception.getMessage()});
               }
            }

            if (textArray.length >= 6) {
               if (textArray[5].equals("destroy")) {
                  worldObj.matchesBlockPos3(blockPos, true);
                  if (block == Blocks.air) {
                     applyICommandSender(iCommandSender, this, "commands.setblock.success", new Object[0]);
                     return;
                  }
               } else if (textArray[5].equals("keep") && !worldObj.isAirBlock(blockPos)) {
                  throw new InternalExceptionAdapter("commands.setblock.noChange", new Object[0]);
               }
            }

            TileEntity tileEntity = worldObj.resolveTileEntity(blockPos);
            if (tileEntity != null) {
               if (tileEntity instanceof IInventory) {
                  ((IInventory)tileEntity).updateState2();
               }

               worldObj.matchesBlockPos(blockPos, Blocks.air.getDefaultState(), block == Blocks.air ? 2 : 4);
            }

            IBlockState blockState = block.processAction(count);
            if (!worldObj.matchesBlockPos(blockPos, blockState, 2)) {
               throw new InternalExceptionAdapter("commands.setblock.noChange", new Object[0]);
            } else {
               if (flag) {
                  TileEntity tileEntity2 = worldObj.resolveTileEntity(blockPos);
                  if (tileEntity2 != null) {
                     tagCompound.put("x", blockPos.getX());
                     tagCompound.put("y", blockPos.getY());
                     tagCompound.put("z", blockPos.getZ());
                     tileEntity2.applyNBTTagCompoundByNBTTagCompound(tagCompound);
                  }
               }

               worldObj.applyBlockPos3(blockPos, blockState.getBlock());
               iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_BLOCKS, 1);
               applyICommandSender(iCommandSender, this, "commands.setblock.success", new Object[0]);
            }
         }
      }
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.setblock.usage";
   }

   @Override
   public String getText() {
      return "setblock";
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length > 0 && textArray.length <= 3
         ? resolveListByTextArray(textArray, 0, blockPos)
         : (
            textArray.length == 4
               ? resolveList(textArray, Block.iterableExtensionBridge.getSet())
               : (textArray.length == 6 ? resolveList(textArray, new String[]{"replace", "destroy", "keep"}) : null)
         );
   }
}
