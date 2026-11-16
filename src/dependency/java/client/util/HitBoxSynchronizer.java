package client.util;

import client.setting.DefaultOptionType;
import java.util.Random;
import net.minecraft.block.BlockMonsterEgg;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntitySilverfish;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class HitBoxSynchronizer extends AdvancedClientManagerAdapter {
   public final EntitySilverfish entitySilverfish;
   public EnumFacing enumFacing;
   public boolean enabled2;

   @Override
   public void updateState3() {
      if (!this.enabled2) {
         super.updateState3();
      } else {
         World world = this.entitySilverfish.worldObj;
         BlockPos blockPos = new BlockPos(this.entitySilverfish.posX, this.entitySilverfish.posY + 0.5, this.entitySilverfish.posZ)
            .offset(this.enumFacing);
         IBlockState blockState = world.getBlockState(blockPos);
         if (BlockMonsterEgg.matchesIBlockState(blockState)) {
            world.matchesBlockPos(
               blockPos,
               Blocks.block22.getDefaultState().resolveIBlockState(BlockMonsterEgg.immutableSetEntry, DefaultOptionType.resolveDefaultOptionType(blockState)),
               3
            );
            this.entitySilverfish.updateState44();
            this.entitySilverfish.updateState9();
         }
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.enabled2 ? false : super.isActiveAsFlag();
   }

   public HitBoxSynchronizer(EntitySilverfish entitySilverfish2) {
      super(entitySilverfish2, 1.0, 10);
      this.entitySilverfish = entitySilverfish2;
      this.setCount(1);
   }

   @Override
   public boolean isActive() {
      if (this.entitySilverfish.getEntityLivingBase4() != null) {
         return false;
      } else if (!this.entitySilverfish.getPathfindManager().isActive2()) {
         return false;
      } else {
         Random random = this.entitySilverfish.getRandom();
         if (random.nextInt(10) == 0) {
            this.enumFacing = EnumFacing.resolveEnumFacing(random);
            BlockPos blockPos = new BlockPos(this.entitySilverfish.posX, this.entitySilverfish.posY + 0.5, this.entitySilverfish.posZ)
               .offset(this.enumFacing);
            if (BlockMonsterEgg.matchesIBlockState(this.entitySilverfish.worldObj.getBlockState(blockPos))) {
               this.enabled2 = true;
               return true;
            }
         }

         this.enabled2 = false;
         return super.isActive();
      }
   }
}
