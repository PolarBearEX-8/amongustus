package net.minecraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockTNT extends Block {
   public static final PropertyBool EXPLODE = PropertyBool.create("explode");

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (player.getCurrentEquippedItem() != null) {
         Item item = player.getCurrentEquippedItem().getItem();
         if (item == Items.flint_and_steel || item == Items.fire_charge) {
            this.explode(world, pos, state.withProperty(EXPLODE, Boolean.TRUE), player);
            world.setBlockToAir(pos);
            if (item == Items.flint_and_steel) {
               player.getCurrentEquippedItem().damageItem(1, player);
            } else if (!player.capabilities.isCreativeMode) {
               player.getCurrentEquippedItem().stackSize--;
            }

            return true;
         }
      }

      return super.onBlockActivated(world, pos, state, player, facing, f, f2, f3);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{EXPLODE});
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Boolean)state.getValue(EXPLODE) ? 1 : 0;
   }

   @Override
   public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
      this.explode(world, pos, state, (EntityLivingBase)null);
   }

   @Override
   public boolean canDropFromExplosion(Explosion explosion) {
      return false;
   }

   @Override
   public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
      if (!world.isRemote) {
         EntityTNTPrimed entityTNTPrimed = new EntityTNTPrimed(
            world, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), explosion.getExplosivePlacedBy()
         );
         entityTNTPrimed.fuse = world.rand.nextInt(entityTNTPrimed.fuse / 4) + entityTNTPrimed.fuse / 8;
         world.spawnEntityInWorld(entityTNTPrimed);
      }
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (world.isBlockPowered(pos)) {
         this.onBlockDestroyedByPlayer(world, pos, state.withProperty(EXPLODE, Boolean.TRUE));
         world.setBlockToAir(pos);
      }
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (player.getCurrentEquippedItem() != null) {
         Item item = player.getCurrentEquippedItem().getItem();
         if (item == Items.flint_and_steel || item == Items.fire_charge) {
            return true;
         }
      }

      return super.onBlockActivated(world, pos, state, player, facing, f, f2, f3);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(EXPLODE, (n & 1) > 0);
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      super.onBlockAdded(world, pos, state);
      if (world.isBlockPowered(pos)) {
         this.onBlockDestroyedByPlayer(world, pos, state.withProperty(EXPLODE, Boolean.TRUE));
         world.setBlockToAir(pos);
      }
   }

   public BlockTNT() {
      super(Material.tnt);
      this.setDefaultState(this.blockState.getBaseState().withProperty(EXPLODE, Boolean.FALSE));
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (!world.isRemote && entity instanceof EntityArrow) {
         EntityArrow entityArrow = (EntityArrow)entity;
         if (entityArrow.isBurning()) {
            this.explode(
               world,
               pos,
               world.getBlockState(pos).withProperty(EXPLODE, Boolean.TRUE),
               entityArrow.shootingEntity instanceof EntityLivingBase ? (EntityLivingBase)entityArrow.shootingEntity : null
            );
            world.setBlockToAir(pos);
         }
      }
   }

   public void explode(World world, BlockPos pos, IBlockState state, EntityLivingBase entity) {
      if (!world.isRemote && (Boolean)state.getValue(EXPLODE)) {
         EntityTNTPrimed entityTNTPrimed = new EntityTNTPrimed(world, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), entity);
         world.spawnEntityInWorld(entityTNTPrimed);
         world.playSoundAtEntity(entityTNTPrimed, "game.tnt.primed", 1.0F, 1.0F);
      }
   }
}
