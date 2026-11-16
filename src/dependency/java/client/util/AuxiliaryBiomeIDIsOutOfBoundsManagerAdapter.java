package client.util;

import net.minecraft.entity.EntityGhast;
import net.minecraft.entity.EntityLavaslime;
import net.minecraft.entity.EntityPigzombie;

public class AuxiliaryBiomeIDIsOutOfBoundsManagerAdapter extends BiomeIDIsOutOfBoundsManager {
   public AuxiliaryBiomeIDIsOutOfBoundsManagerAdapter(int count) {
      super(count);
      this.list3.clear();
      this.list4.clear();
      this.list2.clear();
      this.list.clear();
      this.list3.add(new ClassHolder(EntityGhast.class, 50, 4, 4));
      this.list3.add(new ClassHolder(EntityPigzombie.class, 100, 4, 4));
      this.list3.add(new ClassHolder(EntityLavaslime.class, 1, 4, 4));
   }
}
