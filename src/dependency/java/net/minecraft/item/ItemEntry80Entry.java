package net.minecraft.item;

import client.event.PostRenderConsumer;
import client.util.SplineObserver;
import net.minecraft.entity.EntityZombie;

public class ItemEntry80Entry implements PostRenderConsumer {
   public boolean enabled2;
   public boolean enabled;
   public final EntityZombie entityZombie;

   public ItemEntry80Entry(EntityZombie entityZombie2, boolean flag, boolean flag2, SplineObserver splineObserver) {
      this(entityZombie2, flag, flag2);
   }

   private ItemEntry80Entry(EntityZombie entityZombie2, boolean flag, boolean flag2) {
      this.entityZombie = entityZombie2;
      this.enabled2 = false;
      this.enabled = false;
      this.enabled2 = flag;
      this.enabled = flag2;
   }
}
