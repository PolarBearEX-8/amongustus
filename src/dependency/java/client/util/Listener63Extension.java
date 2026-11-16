package client.util;

import client.event.ComplexListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryPlayer;

public interface Listener63Extension extends ComplexListener {
   Container resolveContainer(InventoryPlayer inventoryPlayer, EntityPlayer player);

   String getLabelText2();
}
