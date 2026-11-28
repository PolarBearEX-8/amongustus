package net.minecraft.item;

import java.util.List;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemMonsterPlacer extends Item {

   @Override
   public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
      for (EntityList.EntityEggInfo entityEggInfo : (Iterable<EntityList.EntityEggInfo>)(EntityList.entityEggs.values())) {
         list.add(new ItemStack(item, 1, entityEggInfo.spawnedID));
      }
   }

   public static Entity spawnCreature(World world, int n, double d, double d2, double d3) {
      if (!EntityList.entityEggs.containsKey(n)) {
         return null;
      } else {
         Entity entity = null;

         for (int i = 0; i < 1; i++) {
            entity = EntityList.createEntityByID(n, world);
            if (entity instanceof EntityLivingBase) {
               EntityLiving entityLiving = (EntityLiving)entity;
               entity.setLocationAndAngles(d, d2, d3, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
               entityLiving.rotationYawHead = entityLiving.rotationYaw;
               entityLiving.renderYawOffset = entityLiving.rotationYaw;
               entityLiving.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityLiving)), (IEntityLivingData)null);
               world.spawnEntityInWorld(entity);
               entityLiving.playLivingSound();
            }
         }

         return entity;
      }
   }

   public ItemMonsterPlacer() {
      this.setHasSubtypes(true);
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      if (world.isRemote) {
         return stack;
      } else {
         MovingObjectPosition hit = this.getMovingObjectPositionFromPlayer(world, player, true);
         if (hit == null) {
            return stack;
         } else {
            if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
               BlockPos blockPos = hit.getBlockPos();
               if (!world.isBlockModifiable(player, blockPos)) {
                  return stack;
               }

               if (!player.canPlayerEdit(blockPos, hit.sideHit, stack)) {
                  return stack;
               }

               if (world.getBlockState(blockPos).getBlock() instanceof BlockLiquid) {
                  Entity entity = spawnCreature(world, stack.getMetadata(), (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5);
                  if (entity != null) {
                     if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
                        ((EntityLiving)entity).setCustomNameTag(stack.getDisplayName());
                     }

                     if (!player.capabilities.isCreativeMode) {
                        stack.stackSize--;
                     }

                     player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
                  }
               }
            }

            return stack;
         }
      }
   }

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (world.isRemote) {
         return true;
      } else if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
         return false;
      } else {
         IBlockState blockState = world.getBlockState(pos);
         if (blockState.getBlock() == Blocks.mob_spawner) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof TileEntityMobSpawner) {
               ((TileEntityMobSpawner)tileEntity).getSpawnerBaseLogic().setEntityName(EntityList.getStringFromID(stack.getMetadata()));
               tileEntity.markDirty();
               world.markBlockForUpdate(pos);
               if (!player.capabilities.isCreativeMode) {
                  stack.stackSize--;
               }

               return true;
            }
         }

         pos = pos.offset(facing);
         double d = 0.0;
         if (facing == EnumFacing.UP && blockState instanceof BlockFence) {
            d = 0.5;
         }

         Entity entity = spawnCreature(world, stack.getMetadata(), (double)pos.getX() + 0.5, (double)pos.getY() + d, (double)pos.getZ() + 0.5);
         if (entity != null) {
            if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
               entity.setCustomNameTag(stack.getDisplayName());
            }

            if (!player.capabilities.isCreativeMode) {
               stack.stackSize--;
            }
         }

         return true;
      }
   }

   @Override
   public String getItemStackDisplayName(ItemStack stack) {
      String str = ("" + StatCollector.translateToLocal(this.getUnlocalizedName() + ".name")).trim();
      String stringFromID = EntityList.getStringFromID(stack.getMetadata());
      if (stringFromID != null) {
         str = str + " " + StatCollector.translateToLocal("entity." + stringFromID + ".name");
      }

      return str;
   }

   @Override
   public int getColorFromItemStack(ItemStack stack, int n) {
      EntityList.EntityEggInfo entityEggInfo = (EntityList.EntityEggInfo)EntityList.entityEggs.get(stack.getMetadata());
      return entityEggInfo != null ? (n == 0 ? entityEggInfo.primaryColor : entityEggInfo.secondaryColor) : 16777215;
   }
}
