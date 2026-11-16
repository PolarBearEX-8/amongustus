package client.util;

import net.minecraft.entity.EntityElderGuardianAdapter;
import net.minecraft.world.World;

public class HitBoxRegistry extends EntityElderGuardianAdapter {
   public HitBoxRegistry(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3, posX4, posY, posZ, 2.5F);
   }
}
