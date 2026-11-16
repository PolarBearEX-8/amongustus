package client.util;

import net.minecraft.block.AdvancedBlockEntry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityEnderdragon;

public class AlreadyDecoratingManagerAdapter extends AlreadyDecoratingManager {
   public CurveParser curveParser21 = new AdvancedBlockEntry(Blocks.block28);

   @Override
   public void applyBiomeIDIsOutOfBoundsManage(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager) {
      this.updateState();
      if (this.random.nextInt(5) == 0) {
         int count = this.random.nextInt(16) + 8;
         int count2 = this.random.nextInt(16) + 8;
         this.curveParser21.matchesWorld(this.world, this.random, this.world.resolveBlockPos(this.blockPos.add(count, 0, count2)));
      }

      if (this.blockPos.getX() == 0 && this.blockPos.getZ() == 0) {
         EntityEnderdragon entityEnderdragon = new EntityEnderdragon(this.world);
         entityEnderdragon.applyDoubleByDouble(0.0, 128.0, 0.0, this.random.nextFloat() * 360.0F, 0.0F);
         this.world.matchesEntity(entityEnderdragon);
      }
   }
}
