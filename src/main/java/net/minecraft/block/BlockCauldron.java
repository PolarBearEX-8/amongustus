package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCauldron extends Block {
   public static final PropertyInteger LEVEL;

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{LEVEL});
   }

   public void setWaterLevel(World world, BlockPos pos, IBlockState state, int n) {
      world.setBlockState(pos, state.withProperty(LEVEL, MathHelper.clamp_int(n, 0, 3)), 2);
      world.updateComparatorOutputLevel(pos, this);
   }

   @Override
   public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB box, List list, Entity entity) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      float f = 0.125F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      this.setBlockBoundsForItemRender();
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (world.isRemote) {
         return true;
      } else {
         ItemStack currentItem = player.inventory.getCurrentItem();
         if (currentItem == null) {
            return true;
         } else {
            int value = (Integer)state.getValue(LEVEL);
            Item item = currentItem.getItem();
            if (item == Items.water_bucket) {
               if (value < 3) {
                  if (!player.capabilities.isCreativeMode) {
                     player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
                  }

                  player.triggerAchievement(StatList.cauldronFilled);
                  this.setWaterLevel(world, pos, state, 3);
               }

               return true;
            } else if (item == Items.glass_bottle) {
               if (value > 0) {
                  if (!player.capabilities.isCreativeMode) {
                     ItemStack stack = new ItemStack(Items.potionitem, 1, 0);
                     if (!player.inventory.addItemStackToInventory(stack)) {
                        world.spawnEntityInWorld(new EntityItem(world, (double)pos.getX() + 0.5, (double)pos.getY() + 1.5, (double)pos.getZ() + 0.5, stack));
                     } else if (player instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
                     }

                     player.triggerAchievement(StatList.cauldronUsed);
                     currentItem.stackSize--;
                     if (currentItem.stackSize <= 0) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                     }
                  }

                  this.setWaterLevel(world, pos, state, value - 1);
               }

               return true;
            } else {
               if (value > 0 && item instanceof ItemArmor) {
                  ItemArmor itemArmor = (ItemArmor)item;
                  if (itemArmor.getArmorMaterial() == ItemArmor.ArmorMaterial.LEATHER && itemArmor.hasColor(currentItem)) {
                     itemArmor.removeColor(currentItem);
                     this.setWaterLevel(world, pos, state, value - 1);
                     player.triggerAchievement(StatList.armorCleaned);
                     return true;
                  }
               }

               if (value > 0 && item instanceof ItemBanner && TileEntityBanner.getPatterns(currentItem) > 0) {
                  ItemStack stack2 = currentItem.copy();
                  stack2.stackSize = 1;
                  TileEntityBanner.removeBannerData(stack2);
                  if (currentItem.stackSize <= 1 && !player.capabilities.isCreativeMode) {
                     player.inventory.setInventorySlotContents(player.inventory.currentItem, stack2);
                  } else {
                     if (!player.inventory.addItemStackToInventory(stack2)) {
                        world.spawnEntityInWorld(new EntityItem(world, (double)pos.getX() + 0.5, (double)pos.getY() + 1.5, (double)pos.getZ() + 0.5, stack2));
                     } else if (player instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
                     }

                     player.triggerAchievement(StatList.bannerCleaned);
                     if (!player.capabilities.isCreativeMode) {
                        currentItem.stackSize--;
                     }
                  }

                  if (!player.capabilities.isCreativeMode) {
                     this.setWaterLevel(world, pos, state, value - 1);
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
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.cauldron;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(LEVEL);
   }

   static {
      LEVEL = PropertyInteger.create("level", 0, 3);
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Items.cauldron;
   }

   @Override
   public void setBlockBoundsForItemRender() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(LEVEL, n);
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
      int value = (Integer)state.getValue(LEVEL);
      float f = (float)pos.getY() + (6.0F + (float)(3 * value)) / 16.0F;
      if (!world.isRemote && entity.isBurning() && value > 0 && entity.getEntityBoundingBox().minY <= (double)f) {
         entity.extinguish();
         this.setWaterLevel(world, pos, state, value - 1);
      }
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (world.isRemote) {
         return true;
      } else {
         ItemStack currentItem = player.inventory.getCurrentItem();
         if (currentItem == null) {
            return true;
         } else {
            int value = (Integer)state.getValue(LEVEL);
            Item item = currentItem.getItem();
            if (item == Items.water_bucket) {
               return true;
            } else if (item == Items.glass_bottle) {
               return true;
            } else {
               if (value > 0 && item instanceof ItemArmor) {
                  ItemArmor itemArmor = (ItemArmor)item;
                  if (itemArmor.getArmorMaterial() == ItemArmor.ArmorMaterial.LEATHER && itemArmor.hasColor(currentItem)) {
                     return true;
                  }
               }

               return value > 0 && item instanceof ItemBanner && TileEntityBanner.getPatterns(currentItem) > 0;
            }
         }
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean hasComparatorInputOverride() {
      return true;
   }

   @Override
   public void fillWithRain(World world, BlockPos pos) {
      if (world.rand.nextInt(20) == 1) {
         IBlockState blockState2 = world.getBlockState(pos);
         if ((Integer)blockState2.getValue(LEVEL) < 3) {
            world.setBlockState(pos, blockState2.cycleProperty(LEVEL), 2);
         }
      }
   }

   public BlockCauldron() {
      super(Material.iron, MapColor.stoneColor);
      this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, 0));
   }

   @Override
   public int getComparatorInputOverride(World world, BlockPos pos) {
      return (Integer)world.getBlockState(pos).getValue(LEVEL);
   }
}
