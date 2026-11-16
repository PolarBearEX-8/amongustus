package client.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public interface PostMotionFilter {
   String[] getTextArray();

   NBTTagCompound resolveNBTTagCompoundByEntityPlayer(EntityPlayer player);

   void applyEntityPlayer(EntityPlayer player);
}
