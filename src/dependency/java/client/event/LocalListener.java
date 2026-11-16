package client.event;

import client.util.AuxiliaryArrayListAdapter;
import client.util.DistanceBuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;

public interface LocalListener {
   IChatComponent getChatComponent();

   void setEntityPlayer2(EntityPlayer entityPlayer);

   void setAuxiliaryArrayListAdapter(AuxiliaryArrayListAdapter auxiliaryArrayListAdapter);

   AuxiliaryArrayListAdapter resolveAuxiliaryArrayListAdapterByEntityPlayer(EntityPlayer player);

   void applyDistanceBuffer(DistanceBuffer distanceBuffer);

   void applyItemStack(ItemStack stack);

   EntityPlayer getEntityPlayer2();
}
