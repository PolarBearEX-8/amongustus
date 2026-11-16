package client.util;

import net.minecraft.entity.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class AuxiliaryContainerAdapter extends Container {
   public final EntitySheep entitySheep;

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player) {
      return false;
   }

   public AuxiliaryContainerAdapter(EntitySheep entitySheep2) {
      this.entitySheep = entitySheep2;
   }
}
