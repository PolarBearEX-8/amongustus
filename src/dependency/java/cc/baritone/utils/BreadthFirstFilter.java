package cc.baritone.utils;

import client.util.ListenerAdapter17;
import client.util.RandomSeedManager;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.nbt.NBTTagCompound;

public interface BreadthFirstFilter extends SpecialListener {
   void applyWorldClient(WorldClient world, RandomSeedManager randomSeedManager, ListenerAdapter17 listenerAdapter, NBTTagCompound tagCompound);
}
