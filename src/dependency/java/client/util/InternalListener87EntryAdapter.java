package client.util;

import client.setting.CombatNumberProperty;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class InternalListener87EntryAdapter extends Listener87Entry {
   @Override
   public String getText() {
      return "blockdata";
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.blockdata.usage";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 4) {
         throw new AimDispatcher("commands.blockdata.usage", new Object[0]);
      } else {
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_BLOCKS, 0);
         BlockPos blockPos = resolveBlockPos(iCommandSender, textArray, 0, false);
         World worldObj = iCommandSender.getWorldObj();
         if (!worldObj.checkBlockPos12(blockPos)) {
            throw new InternalExceptionAdapter("commands.blockdata.outOfWorld", new Object[0]);
         } else {
            TileEntity tileEntity = worldObj.resolveTileEntity(blockPos);
            if (tileEntity == null) {
               throw new InternalExceptionAdapter("commands.blockdata.notValid", new Object[0]);
            } else {
               NBTTagCompound tagCompound = new NBTTagCompound();
               tileEntity.applyNBTTagCompound(tagCompound);
               NBTTagCompound lISTManager = (NBTTagCompound)tagCompound.getLISTManager();

               NBTTagCompound tagCompound2;
               try {
                  tagCompound2 = DampenerEstimator.resolveNBTTagCompound(resolveIChatComponent(iCommandSender, textArray, 3).getString());
               } catch (SecondaryExceptionAdapter exception) {
                  throw new InternalExceptionAdapter("commands.blockdata.tagError", new Object[]{exception.getMessage()});
               }

               tagCompound.applyNBTTagCompound(tagCompound2);
               tagCompound.put("x", blockPos.getX());
               tagCompound.put("y", blockPos.getY());
               tagCompound.put("z", blockPos.getZ());
               if (tagCompound.equals(lISTManager)) {
                  throw new InternalExceptionAdapter("commands.blockdata.failed", new Object[]{tagCompound.toString()});
               } else {
                  tileEntity.applyNBTTagCompoundByNBTTagCompound(tagCompound);
                  tileEntity.processAction5();
                  worldObj.applyBlockPos2(blockPos);
                  iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_BLOCKS, 1);
                  applyICommandSender(iCommandSender, this, "commands.blockdata.success", new Object[]{tagCompound.toString()});
               }
            }
         }
      }
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length > 0 && textArray.length <= 3 ? resolveListByTextArray(textArray, 0, blockPos) : null;
   }

   @Override
   public int getCount() {
      return 2;
   }
}
