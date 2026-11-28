package wdl.api;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.SaveHandler;

public interface IPlayerInfoEditor extends IWDLMod {
   void editPlayerInfo(EntityPlayerSP player, SaveHandler saveHandler, NBTTagCompound tag);
}
