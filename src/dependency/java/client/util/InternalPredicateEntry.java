package client.util;

import client.setting.CannotSupportPropertyPredicate;
import client.setting.MovementColorProperty;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallgrass;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityMob;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class InternalPredicateEntry extends RotationPool {
   public static final Predicate predicate = CannotSupportPropertyPredicate.resolveCannotSupportPropertyPredica(Blocks.blockTallgrass)
      .resolveCannotSupportPropertyPredica(BlockTallgrass.immutableSetEntry, Predicates.equalTo(MovementColorProperty.GRASS));
   public EntityMob entityMob;
   public World world;
   public int count2;

   public InternalPredicateEntry(EntityMob entityMob2) {
      this.entityMob = entityMob2;
      this.world = entityMob2.worldObj;
      this.setCount(7);
   }

   @Override
   public void updateState2() {
      this.count2 = Math.max(0, this.count2 - 1);
      if (this.count2 == 4) {
         BlockPos blockPos = new BlockPos(this.entityMob.posX, this.entityMob.posY, this.entityMob.posZ);
         if (predicate.apply(this.world.getBlockState(blockPos))) {
            if (this.world.getDoFireTickManager().matchesText("mobGriefing")) {
               this.world.matchesBlockPos3(blockPos, false);
            }

            this.entityMob.updateState6();
         } else {
            BlockPos blockPos2 = blockPos.down();
            if (this.world.getBlockState(blockPos2).getBlock() == Blocks.blockGrass) {
               if (this.world.getDoFireTickManager().matchesText("mobGriefing")) {
                  this.world.applyBlockPosByBlockPos(2001, blockPos2, Block.getIdFromBlock(Blocks.blockGrass));
                  this.world.matchesBlockPos(blockPos2, Blocks.block115.getDefaultState(), 2);
               }

               this.entityMob.updateState6();
            }
         }
      }
   }

   @Override
   public boolean isActive() {
      if (this.entityMob.getRandom().nextInt(this.entityMob.isActive16() ? 50 : 1000) != 0) {
         return false;
      } else {
         BlockPos blockPos = new BlockPos(this.entityMob.posX, this.entityMob.posY, this.entityMob.posZ);
         return predicate.apply(this.world.getBlockState(blockPos)) ? true : this.world.getBlockState(blockPos.down()).getBlock() == Blocks.blockGrass;
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.count2 > 0;
   }

   @Override
   public void updateState3() {
      this.count2 = 40;
      this.world.applyEntity(this.entityMob, (byte)10);
      this.entityMob.getPathfindManager().updateState4();
   }

   @Override
   public void updateState() {
      this.count2 = 0;
   }

   public int getCount2() {
      return this.count2;
   }
}
