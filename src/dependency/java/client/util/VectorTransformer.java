package client.util;

import client.event.NativeListener;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class VectorTransformer extends EntityMob implements NativeListener {
   @Override
   public boolean isActive26() {
      return false;
   }

   @Override
   public boolean matchesEntityPlayer6(EntityPlayer player) {
      return false;
   }

   public VectorTransformer(World world) {
      super(world);
   }
}
