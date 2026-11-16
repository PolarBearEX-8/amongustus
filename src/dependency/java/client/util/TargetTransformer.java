package client.util;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityMushroomcow;

public class TargetTransformer extends BiomeIDIsOutOfBoundsManager {
   public TargetTransformer(int count) {
      super(count);
      this.alreadyDecoratingManager.count9 = -100;
      this.alreadyDecoratingManager.count8 = -100;
      this.alreadyDecoratingManager.count5 = -100;
      this.alreadyDecoratingManager.count2 = 1;
      this.alreadyDecoratingManager.count10 = 1;
      this.iBlockState = Blocks.blockMycelium.getDefaultState();
      this.list3.clear();
      this.list4.clear();
      this.list2.clear();
      this.list4.add(new ClassHolder(EntityMushroomcow.class, 8, 4, 8));
   }
}
