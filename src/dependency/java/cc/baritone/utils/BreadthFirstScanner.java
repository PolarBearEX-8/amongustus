package cc.baritone.utils;

import client.util.ListenerAdapter17;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.nbt.NBTTagCompound;

public interface BreadthFirstScanner extends SpecialListener {
   void applyEntityPlayerSPByEntityPlayerSP(EntityPlayerSP player, ListenerAdapter17 listenerAdapter, NBTTagCompound tagCompound);
}
