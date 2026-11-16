package client.util;

import client.setting.CombatNumberProperty;
import java.util.List;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Blocks;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CoreListener87EntryAdapter extends Listener87Entry {
   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.compare.usage";
   }

   @Override
   public String getText() {
      return "testforblocks";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length > 0 && textArray.length <= 3
         ? resolveListByTextArray(textArray, 0, blockPos)
         : (
            textArray.length > 3 && textArray.length <= 6
               ? resolveListByTextArray(textArray, 3, blockPos)
               : (
                  textArray.length > 6 && textArray.length <= 9
                     ? resolveListByTextArray(textArray, 6, blockPos)
                     : (textArray.length == 10 ? resolveList(textArray, new String[]{"masked", "all"}) : null)
               )
         );
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 9) {
         throw new AimDispatcher("commands.compare.usage", new Object[0]);
      } else {
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_BLOCKS, 0);
         BlockPos blockPos = resolveBlockPos(iCommandSender, textArray, 0, false);
         BlockPos blockPos2 = resolveBlockPos(iCommandSender, textArray, 3, false);
         BlockPos blockPos3 = resolveBlockPos(iCommandSender, textArray, 6, false);
         DistanceAggregator distanceAggregator = new DistanceAggregator(blockPos, blockPos2);
         DistanceAggregator distanceAggregator2 = new DistanceAggregator(blockPos3, blockPos3.getBlockPos2(distanceAggregator.getVec3i()));
         int count = distanceAggregator.getCountAsIndex() * distanceAggregator.getCount2() * distanceAggregator.getCount();
         if (count > 524288) {
            throw new InternalExceptionAdapter("commands.compare.tooManyBlocks", new Object[]{count, 524288});
         } else if (distanceAggregator.count6 >= 0 && distanceAggregator.count2 < 256 && distanceAggregator2.count6 >= 0 && distanceAggregator2.count2 < 256) {
            World worldObj = iCommandSender.getWorldObj();
            if (worldObj.matchesDistanceAggregator(distanceAggregator) && worldObj.matchesDistanceAggregator(distanceAggregator2)) {
               boolean flag = false;
               if (textArray.length > 9 && textArray[9].equals("masked")) {
                  flag = true;
               }

               int count2 = 0;
               BlockPos blockPos4 = new BlockPos(distanceAggregator2.count3 - distanceAggregator.count3, distanceAggregator2.count6 - distanceAggregator.count6, distanceAggregator2.count4 - distanceAggregator.count4);
               AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();
               AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter2 = new AuxiliaryBlockPosAdapter();

               for (int index = distanceAggregator.count4; index <= distanceAggregator.count; index++) {
                  for (int index2 = distanceAggregator.count6; index2 <= distanceAggregator.count2; index2++) {
                     for (int index3 = distanceAggregator.count3; index3 <= distanceAggregator.count5; index3++) {
                        auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index3, index2, index);
                        auxiliaryBlockPosAdapter2.resolveAuxiliaryBlockPosAdapter(index3 + blockPos4.getX(), index2 + blockPos4.getY(), index + blockPos4.getZ());
                        boolean flag2 = false;
                        IBlockState blockState = worldObj.getBlockState(auxiliaryBlockPosAdapter);
                        if (!flag || blockState.getBlock() != Blocks.air) {
                           if (blockState == worldObj.getBlockState(auxiliaryBlockPosAdapter2)) {
                              TileEntity tileEntity = worldObj.resolveTileEntity(auxiliaryBlockPosAdapter);
                              TileEntity tileEntity2 = worldObj.resolveTileEntity(auxiliaryBlockPosAdapter2);
                              if (tileEntity != null && tileEntity2 != null) {
                                 NBTTagCompound tagCompound = new NBTTagCompound();
                                 tileEntity.applyNBTTagCompound(tagCompound);
                                 tagCompound.remove("x");
                                 tagCompound.remove("y");
                                 tagCompound.remove("z");
                                 NBTTagCompound tagCompound2 = new NBTTagCompound();
                                 tileEntity2.applyNBTTagCompound(tagCompound2);
                                 tagCompound2.remove("x");
                                 tagCompound2.remove("y");
                                 tagCompound2.remove("z");
                                 if (!tagCompound.equals(tagCompound2)) {
                                    flag2 = true;
                                 }
                              } else if (tileEntity != null) {
                                 flag2 = true;
                              }
                           } else {
                              flag2 = true;
                           }

                           count2++;
                           if (flag2) {
                              throw new InternalExceptionAdapter("commands.compare.failed", new Object[0]);
                           }
                        }
                     }
                  }
               }

               iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_BLOCKS, count2);
               applyICommandSender(iCommandSender, this, "commands.compare.success", new Object[]{count2});
            } else {
               throw new InternalExceptionAdapter("commands.compare.outOfWorld", new Object[0]);
            }
         } else {
            throw new InternalExceptionAdapter("commands.compare.outOfWorld", new Object[0]);
         }
      }
   }
}
