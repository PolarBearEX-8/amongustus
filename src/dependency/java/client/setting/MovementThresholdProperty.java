package client.setting;

import client.util.AimPipeline;
import client.util.HitBoxBuffer;
import client.util.VectorPipeline;
import client.util.VectorTransformer;
import net.minecraft.block.material.Material;

public enum MovementThresholdProperty {
    MONSTER(VectorPipeline.class, 70, Material.coreItemEntry, false, false),
    CREATURE(HitBoxBuffer.class, 10, Material.coreItemEntry, true, true),
    AMBIENT(VectorTransformer.class, 15, Material.coreItemEntry, true, false),
    WATER_CREATURE(AimPipeline.class, 5, Material.coreItemEntry1, true, false);

   public final Class memberRef;
   public final int count;
   public final Material material;
   public final boolean enabled;
   public final boolean enabled2;

   private MovementThresholdProperty(Class clazz, int count2, Material material2, boolean flag, boolean flag2) {
      this.memberRef = clazz;
      this.count = count2;
      this.material = material2;
      this.enabled = flag;
      this.enabled2 = flag2;
   }



   public int getCount() {
      return this.count;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public Class getMemberRef() {
      return this.memberRef;
   }
}
