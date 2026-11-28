package net.minecraft.client.player.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IInteractionObject;

public class LocalBlockIntercommunication implements IInteractionObject {
   private String guiID;
   private IChatComponent displayName;

   @Override
   public IChatComponent getDisplayName() {
      return this.displayName;
   }

   @Override
   public String getName() {
      return this.displayName.getUnformattedText();
   }

   @Override
   public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean hasCustomName() {
      return true;
   }

   @Override
   public String getGuiID() {
      return this.guiID;
   }

   public LocalBlockIntercommunication(String str, IChatComponent component) {
      this.guiID = str;
      this.displayName = component;
   }
}
