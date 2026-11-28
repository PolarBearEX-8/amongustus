package wdl.api;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.WorldInfo;

public interface IWorldInfoEditor extends IWDLMod {
   void editWorldInfo(WorldClient world, WorldInfo worldInfo, SaveHandler saveHandler, NBTTagCompound tag);
}
