package net.minecraft.item;

import client.util.Packet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemMapBase extends Item {
   @Override
   public boolean processAction6() {
      return true;
   }

   public Packet resolvePacketByItemStack(ItemStack stack, World world, EntityPlayer player) {
      return null;
   }
}
