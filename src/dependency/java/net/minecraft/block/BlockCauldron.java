package net.minecraft.block;

import client.setting.CrafterLeatherType;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import client.util.StatCraftItemManager;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCauldron extends Block {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("level", 0, 3);

   @Override
   public void processAction2() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (world.state) {
         return true;
      } else {
         ItemStack slot = player.inventoryPlayer.getCurrentItem();
         if (slot == null) {
            return true;
         } else {
            int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
            Item item = slot.getItem();
            if (item == Items.waterBucket) {
               return true;
            } else if (item == Items.glassBottle) {
               return true;
            } else {
               if (comparable > 0 && item instanceof ItemGoldenBoots) {
                  ItemGoldenBoots itemGoldenBoots = (ItemGoldenBoots)item;
                  if (itemGoldenBoots.getCrafterLeatherType() == CrafterLeatherType.LEATHER && itemGoldenBoots.matchesItemStack2(slot)) {
                     return true;
                  }
               }

               return comparable > 0 && item instanceof ItemBanner && TileEntityBanner.computeCount(slot) > 0;
            }
         }
      }
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count);
   }

   @Override
   public void processAction3(World world, BlockPos blockPos) {
      if (world.random.nextInt(20) == 1) {
         IBlockState blockState = world.getBlockState(blockPos);
         if ((Integer)blockState.getComparable(auxiliaryImmutableSetEntry) < 3) {
            world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry), 2);
         }
      }
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Items.cauldron;
   }

   @Override
   public int processAction5(World world, BlockPos blockPos) {
      return (Integer)world.getBlockState(blockPos).getComparable(auxiliaryImmutableSetEntry);
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos blockPos, IBlockState blockState, Entity entity) {
      int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
      float factor = (float)blockPos.getY() + (6.0F + (float)(3 * comparable)) / 16.0F;
      if (!world.state && entity.canExecute2() && comparable > 0 && entity.getBoundingBox2().minY <= (double)factor) {
         entity.updateState12();
         this.applyWorldByWorld(world, blockPos, blockState, comparable - 1);
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.cauldron;
   }

   @Override
   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState, AxisAlignedBB boundingBox, List list, Entity entity) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      float factor = 0.125F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, factor, 1.0F, 1.0F);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, factor);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      this.setBlockBounds(1.0F - factor, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      this.setBlockBounds(0.0F, 0.0F, 1.0F - factor, 1.0F, 1.0F, 1.0F);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      this.processAction2();
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (world.state) {
         return true;
      } else {
         ItemStack slot = player.inventoryPlayer.getCurrentItem();
         if (slot == null) {
            return true;
         } else {
            int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
            Item item = slot.getItem();
            if (item == Items.waterBucket) {
               if (comparable < 3) {
                  if (!player.invulnerableManager.enabled2) {
                     player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, new ItemStack(Items.bucket));
                  }

                  player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager38);
                  this.applyWorldByWorld(world, blockPos, blockState, 3);
               }

               return true;
            } else if (item == Items.glassBottle) {
               if (comparable > 0) {
                  if (!player.invulnerableManager.enabled2) {
                     ItemStack stack = new ItemStack(Items.potion, 1, 0);
                     if (!player.inventoryPlayer.checkItemStack3(stack)) {
                        world.matchesEntity(new EntityItem(world, (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.5, (double)blockPos.getZ() + 0.5, stack));
                     } else if (player instanceof EntityPlayerAdapter) {
                        ((EntityPlayerAdapter)player).applyContainer(player.container);
                     }

                     player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager16);
                     slot.animationsToGo--;
                     if (slot.animationsToGo <= 0) {
                        player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, (ItemStack)null);
                     }
                  }

                  this.applyWorldByWorld(world, blockPos, blockState, comparable - 1);
               }

               return true;
            } else {
               if (comparable > 0 && item instanceof ItemGoldenBoots) {
                  ItemGoldenBoots itemGoldenBoots = (ItemGoldenBoots)item;
                  if (itemGoldenBoots.getCrafterLeatherType() == CrafterLeatherType.LEATHER && itemGoldenBoots.matchesItemStack2(slot)) {
                     itemGoldenBoots.applyItemStack(slot);
                     this.applyWorldByWorld(world, blockPos, blockState, comparable - 1);
                     player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager48);
                     return true;
                  }
               }

               if (comparable > 0 && item instanceof ItemBanner && TileEntityBanner.computeCount(slot) > 0) {
                  ItemStack stack2 = slot.getItemStack();
                  stack2.animationsToGo = 1;
                  TileEntityBanner.applyItemStackByItemStack(stack2);
                  if (slot.animationsToGo <= 1 && !player.invulnerableManager.enabled2) {
                     player.inventoryPlayer.processAction(player.inventoryPlayer.currentItem, stack2);
                  } else {
                     if (!player.inventoryPlayer.checkItemStack3(stack2)) {
                        world.matchesEntity(new EntityItem(world, (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.5, (double)blockPos.getZ() + 0.5, stack2));
                     } else if (player instanceof EntityPlayerAdapter) {
                        ((EntityPlayerAdapter)player).applyContainer(player.container);
                     }

                     player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager47);
                     if (!player.invulnerableManager.enabled2) {
                        slot.animationsToGo--;
                     }
                  }

                  if (!player.invulnerableManager.enabled2) {
                     this.applyWorldByWorld(world, blockPos, blockState, comparable - 1);
                  }

                  return true;
               } else {
                  return false;
               }
            }
         }
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{auxiliaryImmutableSetEntry});
   }

   public void applyWorldByWorld(World world, BlockPos blockPos, IBlockState blockState, int count) {
      world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry, MathHelper.getInt12(count, 0, 3)), 2);
      world.applyBlockPos(blockPos, this);
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean processAction16() {
      return true;
   }

   public BlockCauldron() {
      super(Material.material5, MapColor.mapColor10);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(auxiliaryImmutableSetEntry, 0));
   }
}
