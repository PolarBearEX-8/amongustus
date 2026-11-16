package client.util;

import client.event.LocalListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;

public class ListenerCoordinator implements LocalListener {
   public SplineFormatter splineFormatter;
   public EntityPlayer entityPlayer;
   public AuxiliaryArrayListAdapter auxiliaryArrayListAdapter;
   public IChatComponent iChatComponent;

   public ListenerCoordinator(EntityPlayer player, IChatComponent iChatComponent2) {
      this.entityPlayer = player;
      this.iChatComponent = iChatComponent2;
      this.splineFormatter = new SplineFormatter(player, this);
   }

   @Override
   public void applyItemStack(ItemStack stack) {
   }

   @Override
   public void setEntityPlayer2(EntityPlayer entityPlayer2) {
   }

   @Override
   public IChatComponent getChatComponent() {
      return (IChatComponent)(this.iChatComponent != null
         ? this.iChatComponent
         : new BaseManagerStyleIChatComponentAdapter("entity.Villager.name", new Object[0]));
   }

   @Override
   public AuxiliaryArrayListAdapter resolveAuxiliaryArrayListAdapterByEntityPlayer(EntityPlayer player) {
      return this.auxiliaryArrayListAdapter;
   }

   @Override
   public EntityPlayer getEntityPlayer2() {
      return this.entityPlayer;
   }

   @Override
   public void applyDistanceBuffer(DistanceBuffer distanceBuffer) {
      distanceBuffer.updateState2();
   }

   @Override
   public void setAuxiliaryArrayListAdapter(AuxiliaryArrayListAdapter auxiliaryArrayListAdapter2) {
      this.auxiliaryArrayListAdapter = auxiliaryArrayListAdapter2;
   }
}
