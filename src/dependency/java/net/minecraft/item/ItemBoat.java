package net.minecraft.item;

import client.util.MovingObjectType;
import client.util.StatCraftItemManager;
import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemBoat extends Item {
   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      float factor = 1.0F;
      float factor2 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * factor;
      float factor3 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * factor;
      double offset = player.prevPosX + (player.posX - player.prevPosX) * (double)factor;
      double offset2 = player.prevPosY + (player.posY - player.prevPosY) * (double)factor + (double)player.getHeight2();
      double offset3 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)factor;
      float factor4 = MathHelper.cos(-factor3 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float factor5 = MathHelper.abs(-factor3 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float factor6 = -MathHelper.cos(-factor2 * (float) (Math.PI / 180.0));
      float factor7 = MathHelper.abs(-factor2 * (float) (Math.PI / 180.0));
      float factor8 = factor5 * factor6;
      float factor9 = factor4 * factor6;
      double offset4 = 5.0;
      MovingObjectPosition hitResult = world.resolveMovingObjectPosition(
         offset, offset2, offset3, offset + (double)factor8 * offset4, offset2 + (double)factor7 * offset4, offset3 + (double)factor9 * offset4, true
      );
      if (hitResult == null) {
         return stack;
      } else {
         Vec3 vec3 = player.getVec36(factor);
         boolean flag = false;
         float factor10 = 1.0F;
         List list = world.resolveListByEntity(
            player,
            player.getBoundingBox2()
               .contract(vec3.xCoord * offset4, vec3.yCoord * offset4, vec3.zCoord * offset4)
               .offset((double)factor10, (double)factor10, (double)factor10)
         );

         for (int index = 0; index < list.size(); index++) {
            Entity entity = (Entity)list.get(index);
            if (entity.isActive5()) {
               float height = entity.getHeight();
               if (entity.getBoundingBox2().offset((double)height, (double)height, (double)height).checkDouble(offset, offset2, offset3)) {
                  flag = true;
               }
            }
         }

         if (flag) {
            return stack;
         } else {
            if (hitResult.typeOfHit == MovingObjectType.BLOCK) {
               BlockPos blockPos = hitResult.getBlockPos();
               if (world.getBlockState(blockPos).getBlock() == Blocks.block74) {
                  blockPos = blockPos.down();
               }

               EntityBoat entityBoat = new EntityBoat(
                  world, (double)((float)blockPos.getX() + 0.5F), (double)((float)blockPos.getY() + 1.0F), (double)((float)blockPos.getZ() + 0.5F)
               );
               entityBoat.rotationYaw = (float)(((MathHelper.ceiling_double_int((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5) & 3) - 1) * 90);
               if (!world.getList2(entityBoat, entityBoat.getBoundingBox2().offset(-0.1, -0.1, -0.1)).isEmpty()) {
                  return stack;
               }

               if (!world.state) {
                  world.matchesEntity(entityBoat);
               }

               if (!player.invulnerableManager.enabled2) {
                  stack.animationsToGo--;
               }

               player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
            }

            return stack;
         }
      }
   }

   public ItemBoat() {
      this.indexOffset1 = 1;
      this.processAction(ItemGroupManager.itemGroupManager12);
   }
}
