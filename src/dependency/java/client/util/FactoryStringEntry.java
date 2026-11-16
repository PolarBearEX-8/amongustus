package client.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.util.IChatComponent;

public class FactoryStringEntry implements Listener63Extension {
   public String labelText;
   public IChatComponent iChatComponent;

   @Override
   public String getLabelText2() {
      return this.labelText;
   }

   @Override
   public boolean processAction() {
      return true;
   }

   @Override
   public String getText() {
      return this.iChatComponent.getString();
   }

   @Override
   public Container resolveContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      throw new UnsupportedOperationException();
   }

   public FactoryStringEntry(String text, IChatComponent iChatComponent2) {
      this.labelText = text;
      this.iChatComponent = iChatComponent2;
   }

   @Override
   public IChatComponent getChatComponent() {
      return this.iChatComponent;
   }
}
