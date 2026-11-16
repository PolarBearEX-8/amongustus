package net.minecraft.item;

import net.minecraft.block.BlockAcaciaFence;
import net.minecraft.entity.EntityLeashknot;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemLead extends Item {
   public static boolean matchesEntityPlayer3(EntityPlayer player, World world, BlockPos blockPos) {
      EntityLeashknot entityLeashknot = EntityLeashknot.resolveEntityLeashknot3(world, blockPos);
      boolean flag = false;
      double offset = 7.0;
      int coordX = blockPos.getX();
      int coordY = blockPos.getY();
      int coordZ = blockPos.getZ();

      for (EntityMob entityMob : (Iterable<EntityMob>)(Iterable)world.resolveList(
         EntityMob.class,
         new AxisAlignedBB((double)coordX - offset, (double)coordY - offset, (double)coordZ - offset, (double)coordX + offset, (double)coordY + offset, (double)coordZ + offset)
      )) {
         if (entityMob.isEnabled23() && entityMob.getEntity6() == player) {
            if (entityLeashknot == null) {
               entityLeashknot = EntityLeashknot.resolveEntityLeashknotByWorld(world, blockPos);
            }

            entityMob.applyEntity(entityLeashknot, true);
            flag = true;
         }
      }

      return flag;
   }

   public static boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player, World world, BlockPos blockPos) {
      double offset = 7.0;
      int coordX = blockPos.getX();
      int coordY = blockPos.getY();
      int coordZ = blockPos.getZ();

      for (EntityMob entityMob : (Iterable<EntityMob>)(Iterable)world.resolveList(
         EntityMob.class,
         new AxisAlignedBB((double)coordX - offset, (double)coordY - offset, (double)coordZ - offset, (double)coordX + offset, (double)coordY + offset, (double)coordZ + offset)
      )) {
         if (entityMob.isEnabled23() && entityMob.getEntity6() == player) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (world.getBlockState(blockPos).getBlock() instanceof BlockAcaciaFence) {
         if (world.state) {
            return true;
         } else {
            matchesEntityPlayer3(player, world, blockPos);
            return true;
         }
      } else {
         return false;
      }
   }

   public ItemLead() {
      this.processAction(ItemGroupManager.itemGroupManager2);
   }
}
