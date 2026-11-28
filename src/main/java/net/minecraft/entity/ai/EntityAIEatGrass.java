package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityAIEatGrass extends EntityAIBase {
   private static final Predicate IS_TALL_GRASS = BlockStateHelper.forBlock(Blocks.tallgrass)
      .where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
   private EntityLiving grassEaterEntity;
   private World entityWorld;
   public int eatingGrassTimer;

   public EntityAIEatGrass(EntityLiving entityLiving) {
      this.grassEaterEntity = entityLiving;
      this.entityWorld = entityLiving.entityWorld;
      this.setMutexBits(7);
   }

   @Override
   public boolean shouldExecute() {
      if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0) {
         return false;
      } else {
         BlockPos pos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
         return IS_TALL_GRASS.apply(this.entityWorld.getBlockState(pos)) ? true : this.entityWorld.getBlockState(pos.down()).getBlock() == Blocks.grass;
      }
   }

   public int getEatingGrassTimer() {
      return this.eatingGrassTimer;
   }

   @Override
   public boolean continueExecuting() {
      return this.eatingGrassTimer > 0;
   }

   @Override
   public void updateTask() {
      this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);
      if (this.eatingGrassTimer == 4) {
         BlockPos pos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
         if (IS_TALL_GRASS.apply(this.entityWorld.getBlockState(pos))) {
            if (this.entityWorld.getGameRules().getBoolean("mobGriefing")) {
               this.entityWorld.destroyBlock(pos, false);
            }

            this.grassEaterEntity.eatGrassBonus();
         } else {
            BlockPos pos2 = pos.down();
            if (this.entityWorld.getBlockState(pos2).getBlock() == Blocks.grass) {
               if (this.entityWorld.getGameRules().getBoolean("mobGriefing")) {
                  this.entityWorld.playAuxSFX(2001, pos2, Block.getIdFromBlock(Blocks.grass));
                  this.entityWorld.setBlockState(pos2, Blocks.dirt.getDefaultState(), 2);
               }

               this.grassEaterEntity.eatGrassBonus();
            }
         }
      }
   }

   @Override
   public void startExecuting() {
      this.eatingGrassTimer = 40;
      this.entityWorld.setEntityState(this.grassEaterEntity, (byte)10);
      this.grassEaterEntity.getNavigator().clearPathEntity();
   }

   @Override
   public void resetTask() {
      this.eatingGrassTimer = 0;
   }
}
