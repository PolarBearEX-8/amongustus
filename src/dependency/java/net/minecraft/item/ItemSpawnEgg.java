package net.minecraft.item;

import client.event.PostRenderConsumer;
import client.util.DuplicateStatIdManagerEntry;
import client.util.IBlockState;
import client.util.MovingObjectType;
import client.util.SkippingEntityWithIdManager;
import client.util.StatCraftItemManager;
import java.util.List;
import net.minecraft.block.BlockAcaciaFence;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobspawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemSpawnEgg extends Item {
   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (DuplicateStatIdManagerEntry duplicateStatIdManagerEntry : (Iterable<DuplicateStatIdManagerEntry>)(Iterable<?>)SkippingEntityWithIdManager.map6.values()) {
         list.add(new ItemStack(item, 1, duplicateStatIdManagerEntry.count2));
      }
   }

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (world.state) {
         return true;
      } else if (!player.matchesBlockPos(blockPos.offset(enumFacing), enumFacing, stack)) {
         return false;
      } else {
         IBlockState blockState = world.getBlockState(blockPos);
         if (blockState.getBlock() == Blocks.block29) {
            TileEntity tileEntity = world.resolveTileEntity(blockPos);
            if (tileEntity instanceof TileEntityMobspawner) {
               ((TileEntityMobspawner)tileEntity).getPassiveStringEntry().setLabelText(SkippingEntityWithIdManager.resolveText(stack.getIndexOffset()));
               tileEntity.processAction5();
               world.applyBlockPos2(blockPos);
               if (!player.invulnerableManager.enabled2) {
                  stack.animationsToGo--;
               }

               return true;
            }
         }

         BlockPos blockPos2 = blockPos.offset(enumFacing);
         double offset = 0.0;
         if (enumFacing == EnumFacing.UP && blockState instanceof BlockAcaciaFence) {
            offset = 0.5;
         }

         Entity entity = resolveEntityByWorld(world, stack.getIndexOffset(), (double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + offset, (double)blockPos2.getZ() + 0.5);
         if (entity != null) {
            if (entity instanceof EntityLivingBase && stack.isActive()) {
               entity.setLabelText(stack.getString());
            }

            if (!player.invulnerableManager.enabled2) {
               stack.animationsToGo--;
            }
         }

         return true;
      }
   }

   @Override
   public int processAction(ItemStack stack, int count) {
      DuplicateStatIdManagerEntry duplicateStatIdManagerEntry = (DuplicateStatIdManagerEntry)SkippingEntityWithIdManager.map6.get(stack.getIndexOffset());
      return duplicateStatIdManagerEntry != null ? (count == 0 ? duplicateStatIdManagerEntry.count3 : duplicateStatIdManagerEntry.count) : 16777215;
   }

   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      if (world.state) {
         return stack;
      } else {
         MovingObjectPosition hitResult = this.resolveMovingObjectPositionByWorld(world, player, true);
         if (hitResult == null) {
            return stack;
         } else {
            if (hitResult.typeOfHit == MovingObjectType.BLOCK) {
               BlockPos blockPos = hitResult.getBlockPos();
               if (!world.matchesEntityPlayer(player, blockPos)) {
                  return stack;
               }

               if (!player.matchesBlockPos(blockPos, hitResult.sideHit, stack)) {
                  return stack;
               }

               if (world.getBlockState(blockPos).getBlock() instanceof BlockLiquid) {
                  Entity entity = resolveEntityByWorld(
                     world, stack.getIndexOffset(), (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5
                  );
                  if (entity != null) {
                     if (entity instanceof EntityLivingBase && stack.isActive()) {
                        ((EntityMob)entity).setLabelText(stack.getString());
                     }

                     if (!player.invulnerableManager.enabled2) {
                        stack.animationsToGo--;
                     }

                     player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
                  }
               }
            }

            return stack;
         }
      }
   }

   public static Entity resolveEntityByWorld(World world, int count, double posX, double posY, double posZ) {
      if (!SkippingEntityWithIdManager.map6.containsKey(count)) {
         return null;
      } else {
         Entity entity = null;

         for (int index = 0; index < 1; index++) {
            entity = SkippingEntityWithIdManager.resolveEntity(count, world);
            if (entity instanceof EntityLivingBase) {
               EntityMob entityMob = (EntityMob)entity;
               entity.applyDoubleByDouble(posX, posY, posZ, MathHelper.sqrt_float(world.random.nextFloat() * 360.0F), 0.0F);
               entityMob.scaleFactor12 = entityMob.rotationYaw;
               entityMob.scaleFactor8 = entityMob.rotationYaw;
               entityMob.resolvePostRenderConsumer(world.resolveGoldenOre(new BlockPos(entityMob)), (PostRenderConsumer)null);
               world.matchesEntity(entity);
               entityMob.updateState43();
            }
         }

         return entity;
      }
   }

   @Override
   public String resolveText(ItemStack stack) {
      String text = ("" + I18n.getString2(this.getString() + ".name")).trim();
      String text2 = SkippingEntityWithIdManager.resolveText(stack.getIndexOffset());
      if (text2 != null) {
         text = text + " " + I18n.getString2("entity." + text2 + ".name");
      }

      return text;
   }

   public ItemSpawnEgg() {
      this.processAction(true);
      this.processAction(ItemGroupManager.itemGroupManager5);
   }
}
