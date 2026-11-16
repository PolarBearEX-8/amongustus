package client.util;

import client.setting.CombatNumberProperty;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class StaticListener87EntryAdapter extends Listener87Entry {
   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 4) {
         throw new AimDispatcher("commands.testforblock.usage", new Object[0]);
      } else {
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_BLOCKS, 0);
         BlockPos blockPos = resolveBlockPos(iCommandSender, textArray, 0, false);
         Block blockFromName = Block.getBlockFromName(textArray[3]);
         if (blockFromName == null) {
            throw new AimDirector("commands.setblock.notFound", new Object[]{textArray[3]});
         } else {
            int count = -1;
            if (textArray.length >= 5) {
               count = computeCount(textArray[4], -1, 15);
            }

            World worldObj = iCommandSender.getWorldObj();
            if (!worldObj.checkBlockPos12(blockPos)) {
               throw new InternalExceptionAdapter("commands.testforblock.outOfWorld", new Object[0]);
            } else {
               NBTTagCompound tagCompound = new NBTTagCompound();
               boolean flag = false;
               if (textArray.length >= 6 && blockFromName.isState8()) {
                  String string = resolveIChatComponent(iCommandSender, textArray, 5).getString();

                  try {
                     tagCompound = DampenerEstimator.resolveNBTTagCompound(string);
                     flag = true;
                  } catch (SecondaryExceptionAdapter exception) {
                     throw new InternalExceptionAdapter("commands.setblock.tagError", new Object[]{exception.getMessage()});
                  }
               }

               IBlockState blockState = worldObj.getBlockState(blockPos);
               Block block = blockState.getBlock();
               if (block != blockFromName) {
                  throw new InternalExceptionAdapter(
                     "commands.testforblock.failed.tile", new Object[]{blockPos.getX(), blockPos.getY(), blockPos.getZ(), block.getText(), blockFromName.getText()}
                  );
               } else {
                  if (count > -1) {
                     int index = blockState.getBlock().getIndex(blockState);
                     if (index != count) {
                        throw new InternalExceptionAdapter(
                           "commands.testforblock.failed.data", new Object[]{blockPos.getX(), blockPos.getY(), blockPos.getZ(), index, count}
                        );
                     }
                  }

                  if (flag) {
                     TileEntity tileEntity = worldObj.resolveTileEntity(blockPos);
                     if (tileEntity == null) {
                        throw new InternalExceptionAdapter("commands.testforblock.failed.tileEntity", new Object[]{blockPos.getX(), blockPos.getY(), blockPos.getZ()});
                     }

                     NBTTagCompound tagCompound2 = new NBTTagCompound();
                     tileEntity.applyNBTTagCompound(tagCompound2);
                     if (!AuxiliaryNameManager.matchesLISTManager(tagCompound, tagCompound2, true)) {
                        throw new InternalExceptionAdapter("commands.testforblock.failed.nbt", new Object[]{blockPos.getX(), blockPos.getY(), blockPos.getZ()});
                     }
                  }

                  iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_BLOCKS, 1);
                  applyICommandSender(iCommandSender, this, "commands.testforblock.success", new Object[]{blockPos.getX(), blockPos.getY(), blockPos.getZ()});
               }
            }
         }
      }
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.testforblock.usage";
   }

   @Override
   public String getText() {
      return "testforblock";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length > 0 && textArray.length <= 3
         ? resolveListByTextArray(textArray, 0, blockPos)
         : (textArray.length == 4 ? resolveList(textArray, Block.iterableExtensionBridge.getSet()) : null);
   }

   @Override
   public int getCount() {
      return 2;
   }
}
