package net.minecraft.block;

import client.setting.BlockSolidType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSlime extends ExtendedBlockAdapter {
   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.TRANSLUCENT;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, Entity entity) {
      if (Math.abs(entity.motionY) < 0.1 && !entity.isSprinting()) {
         double offset = 0.4 + Math.abs(entity.motionY) * 0.2;
         entity.motionX *= offset;
         entity.motionZ *= offset;
      }

      super.processAction(world, blockPos, entity);
   }

   @Override
   public void processAction(World world, Entity entity) {
      if (entity.isSprinting()) {
         super.processAction(world, entity);
      } else if (entity.motionY < 0.0) {
         entity.motionY = -entity.motionY;
      }
   }

   @Override
   public void processAction(World world, BlockPos blockPos, Entity entity, float factor) {
      if (entity.isSprinting()) {
         super.processAction(world, blockPos, entity, factor);
      } else {
         entity.processFloat2(factor, 0.0F);
      }
   }

   public BlockSlime() {
      super(Material.material27, false, MapColor.mapColor17);
      this.processAction(ItemGroupManager.itemGroupManager8);
      this.scaleFactor = 0.8F;
   }
}
