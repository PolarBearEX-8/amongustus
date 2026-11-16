package client.util;

import client.setting.CombatNumberProperty;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.command.ICommandSender;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RayTraceCounter extends Listener87Entry {
   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 7) {
         throw new AimDispatcher("commands.fill.usage", new Object[0]);
      } else {
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_BLOCKS, 0);
         BlockPos blockPos = resolveBlockPos(iCommandSender, textArray, 0, false);
         BlockPos blockPos2 = resolveBlockPos(iCommandSender, textArray, 3, false);
         Block block = Listener87Entry.resolveBlock(iCommandSender, textArray[6]);
         int count = 0;
         if (textArray.length >= 8) {
            count = computeCount(textArray[7], 0, 15);
         }

         BlockPos blockPos3 = new BlockPos(Math.min(blockPos.getX(), blockPos2.getX()), Math.min(blockPos.getY(), blockPos2.getY()), Math.min(blockPos.getZ(), blockPos2.getZ()));
         BlockPos blockPos4 = new BlockPos(Math.max(blockPos.getX(), blockPos2.getX()), Math.max(blockPos.getY(), blockPos2.getY()), Math.max(blockPos.getZ(), blockPos2.getZ()));
         int count2 = (blockPos4.getX() - blockPos3.getX() + 1) * (blockPos4.getY() - blockPos3.getY() + 1) * (blockPos4.getZ() - blockPos3.getZ() + 1);
         if (count2 > 32768) {
            throw new InternalExceptionAdapter("commands.fill.tooManyBlocks", new Object[]{count2, 32768});
         } else if (blockPos3.getY() >= 0 && blockPos4.getY() < 256) {
            World worldObj = iCommandSender.getWorldObj();

            for (int index = blockPos3.getZ(); index < blockPos4.getZ() + 16; index += 16) {
               for (int index2 = blockPos3.getX(); index2 < blockPos4.getX() + 16; index2 += 16) {
                  if (!worldObj.checkBlockPos12(new BlockPos(index2, blockPos4.getY() - blockPos3.getY(), index))) {
                     throw new InternalExceptionAdapter("commands.fill.outOfWorld", new Object[0]);
                  }
               }
            }

            NBTTagCompound tagCompound = new NBTTagCompound();
            boolean flag = false;
            if (textArray.length >= 10 && block.isState8()) {
               String string = resolveIChatComponent(iCommandSender, textArray, 9).getString();

               try {
                  tagCompound = DampenerEstimator.resolveNBTTagCompound(string);
                  flag = true;
               } catch (SecondaryExceptionAdapter exception) {
                  throw new InternalExceptionAdapter("commands.fill.tagError", new Object[]{exception.getMessage()});
               }
            }

            ArrayList list = Lists.newArrayList();
            int count3 = 0;

            for (int index3 = blockPos3.getZ(); index3 <= blockPos4.getZ(); index3++) {
               for (int index4 = blockPos3.getY(); index4 <= blockPos4.getY(); index4++) {
                  for (int index5 = blockPos3.getX(); index5 <= blockPos4.getX(); index5++) {
                     BlockPos blockPos5 = new BlockPos(index5, index4, index3);
                     if (textArray.length >= 9) {
                        if (!textArray[8].equals("outline") && !textArray[8].equals("hollow")) {
                           if (textArray[8].equals("destroy")) {
                              worldObj.matchesBlockPos3(blockPos5, true);
                           } else if (textArray[8].equals("keep")) {
                              if (!worldObj.isAirBlock(blockPos5)) {
                                 continue;
                              }
                           } else if (textArray[8].equals("replace") && !block.isState8()) {
                              if (textArray.length > 9) {
                                 Block block2 = Listener87Entry.resolveBlock(iCommandSender, textArray[9]);
                                 if (worldObj.getBlockState(blockPos5).getBlock() != block2) {
                                    continue;
                                 }
                              }

                              if (textArray.length > 10) {
                                 int count4 = Listener87Entry.parseInt(textArray[10]);
                                 IBlockState blockState = worldObj.getBlockState(blockPos5);
                                 if (blockState.getBlock().getIndex(blockState) != count4) {
                                    continue;
                                 }
                              }
                           }
                        } else if (index5 != blockPos3.getX()
                           && index5 != blockPos4.getX()
                           && index4 != blockPos3.getY()
                           && index4 != blockPos4.getY()
                           && index3 != blockPos3.getZ()
                           && index3 != blockPos4.getZ()) {
                           if (textArray[8].equals("hollow")) {
                              worldObj.matchesBlockPos(blockPos5, Blocks.air.getDefaultState(), 2);
                              list.add(blockPos5);
                           }
                           continue;
                        }
                     }

                     TileEntity tileEntity = worldObj.resolveTileEntity(blockPos5);
                     if (tileEntity != null) {
                        if (tileEntity instanceof IInventory) {
                           ((IInventory)tileEntity).updateState2();
                        }

                        worldObj.matchesBlockPos(blockPos5, Blocks.block145.getDefaultState(), block == Blocks.block145 ? 2 : 4);
                     }

                     IBlockState blockState2 = block.processAction(count);
                     if (worldObj.matchesBlockPos(blockPos5, blockState2, 2)) {
                        list.add(blockPos5);
                        count3++;
                        if (flag) {
                           TileEntity tileEntity2 = worldObj.resolveTileEntity(blockPos5);
                           if (tileEntity2 != null) {
                              tagCompound.put("x", blockPos5.getX());
                              tagCompound.put("y", blockPos5.getY());
                              tagCompound.put("z", blockPos5.getZ());
                              tileEntity2.applyNBTTagCompoundByNBTTagCompound(tagCompound);
                           }
                        }
                     }
                  }
               }
            }

            for (BlockPos blockPos6 : (Iterable<BlockPos>)(Iterable)(list)) {
               Block block3 = worldObj.getBlockState(blockPos6).getBlock();
               worldObj.applyBlockPos3(blockPos6, block3);
            }

            if (count3 <= 0) {
               throw new InternalExceptionAdapter("commands.fill.failed", new Object[0]);
            } else {
               iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_BLOCKS, count3);
               applyICommandSender(iCommandSender, this, "commands.fill.success", new Object[]{count3});
            }
         } else {
            throw new InternalExceptionAdapter("commands.fill.outOfWorld", new Object[0]);
         }
      }
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.fill.usage";
   }

   @Override
   public String getText() {
      return "fill";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length > 0 && textArray.length <= 3
         ? resolveListByTextArray(textArray, 0, blockPos)
         : (
            textArray.length > 3 && textArray.length <= 6
               ? resolveListByTextArray(textArray, 3, blockPos)
               : (
                  textArray.length == 7
                     ? resolveList(textArray, Block.iterableExtensionBridge.getSet())
                     : (
                        textArray.length == 9
                           ? resolveList(textArray, new String[]{"replace", "destroy", "keep", "hollow", "outline"})
                           : (textArray.length == 10 && "replace".equals(textArray[8]) ? resolveList(textArray, Block.iterableExtensionBridge.getSet()) : null)
                     )
               )
         );
   }
}
