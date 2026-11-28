package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockBed extends BlockDirectional {
   public static final PropertyEnum PART = PropertyEnum.create("part", BlockBed.EnumPartType.class);
   public static final PropertyBool OCCUPIED = PropertyBool.create("occupied");

   @Override
   public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
      if (player.capabilities.isCreativeMode && state.getValue(PART) == BlockBed.EnumPartType.HEAD) {
         BlockPos pos2 = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());
         if (world.getBlockState(pos2).getBlock() == this) {
            world.setBlockToAir(pos2);
         }
      }
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      this.setBedBounds();
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      if (state.getValue(PART) == BlockBed.EnumPartType.HEAD) {
         n |= 8;
         if ((Boolean)state.getValue(OCCUPIED)) {
            n |= 4;
         }
      }

      return n;
   }

   public static BlockPos getSafeExitLocation(World world, BlockPos pos, int n) {
      EnumFacing value = (EnumFacing)world.getBlockState(pos).getValue(FACING);
      int x = pos.getX();
      int y = pos.getY();
      int z = pos.getZ();

      for (int i = 0; i <= 1; i++) {
         int n2 = x - value.getFrontOffsetX() * i - 1;
         int n3 = z - value.getFrontOffsetZ() * i - 1;
         int n4 = n2 + 2;
         int n5 = n3 + 2;

         for (int j = n2; j <= n4; j++) {
            for (int k = n3; k <= n5; k++) {
               BlockPos pos2 = new BlockPos(j, y, k);
               if (hasRoomForPlayer(world, pos2)) {
                  if (n <= 0) {
                     return pos2;
                  }

                  n--;
               }
            }
         }
      }

      return null;
   }

   public static boolean hasRoomForPlayer(World world, BlockPos pos) {
      return World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ())
         && !world.getBlockState(pos).getBlock().getMaterial().isSolid()
         && !world.getBlockState(pos.up()).getBlock().getMaterial().isSolid();
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, PART, OCCUPIED});
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      EnumFacing value = (EnumFacing)state.getValue(FACING);
      if (state.getValue(PART) == BlockBed.EnumPartType.HEAD) {
         if (world.getBlockState(pos.offset(value.getOpposite())).getBlock() != this) {
            world.setBlockToAir(pos);
         }
      } else if (world.getBlockState(pos.offset(value)).getBlock() != this) {
         world.setBlockToAir(pos);
         if (!world.isRemote) {
            this.dropBlockAsItem(world, pos, state, 0);
         }
      }
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      if (state.getValue(PART) == BlockBed.EnumPartType.FOOT) {
         EnumFacing value = (EnumFacing)state.getValue(FACING);
         IBlockState blockState2 = world.getBlockState2(n + value.getFrontOffsetX(), n2 + value.getFrontOffsetY(), n3 + value.getFrontOffsetZ());
         if (blockState2.getBlock() == this) {
            state = state.withProperty(OCCUPIED, blockState2.getValue(OCCUPIED));
         }
      }

      return state;
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Items.bed;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      EnumFacing horizontal = EnumFacing.getHorizontal(n);
      return (n & 8) > 0
         ? this.getDefaultState().withProperty(PART, BlockBed.EnumPartType.HEAD).withProperty(FACING, horizontal).withProperty(OCCUPIED, (n & 4) > 0)
         : this.getDefaultState().withProperty(PART, BlockBed.EnumPartType.FOOT).withProperty(FACING, horizontal);
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   private void setBedBounds() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (world.isRemote) {
         return true;
      } else {
         if (state.getValue(PART) != BlockBed.EnumPartType.HEAD) {
            pos = pos.offset((EnumFacing)state.getValue(FACING));
            state = world.getBlockState(pos);
            if (state.getBlock() != this) {
               return true;
            }
         }

         if (world.provider.canRespawnHere() && world.getBiomeGenForCoords(pos) != BiomeGenBase.hell) {
            if ((Boolean)state.getValue(OCCUPIED)) {
               if (this.getPlayerInBed(world, pos) != null) {
                  player.addChatComponentMessage(new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
                  return true;
               }

               state = state.withProperty(OCCUPIED, Boolean.FALSE);
               world.setBlockState(pos, state, 4);
            }

            EntityPlayer.EnumStatus enumStatus = player.trySleep(pos);
            if (enumStatus == EntityPlayer.EnumStatus.OK) {
               state = state.withProperty(OCCUPIED, Boolean.TRUE);
               world.setBlockState(pos, state, 4);
            } else if (enumStatus == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW) {
               player.addChatComponentMessage(new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
            } else if (enumStatus == EntityPlayer.EnumStatus.NOT_SAFE) {
               player.addChatComponentMessage(new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
            }

            return true;
         } else {
            world.setBlockToAir(pos);
            BlockPos pos2 = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());
            if (world.getBlockState(pos2).getBlock() == this) {
               world.setBlockToAir(pos2);
            }

            world.newExplosion((Entity)null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 5.0F, true, true);
            return true;
         }
      }
   }

   @Override
   public int getMobilityFlag() {
      return 1;
   }

   public BlockBed() {
      super(Material.cloth);
      this.setDefaultState(this.blockState.getBaseState().withProperty(PART, BlockBed.EnumPartType.FOOT).withProperty(OCCUPIED, Boolean.FALSE));
      this.setBedBounds();
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return state.getValue(PART) == BlockBed.EnumPartType.HEAD ? null : Items.bed;
   }

   private EntityPlayer getPlayerInBed(World world, BlockPos pos) {
      for (EntityPlayer player : (Iterable<EntityPlayer>)(world.playerEntities)) {
         if (player.isPlayerSleeping() && player.playerLocation.equals(pos)) {
            return player;
         }
      }

      return null;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
      if (state.getValue(PART) == BlockBed.EnumPartType.FOOT) {
         super.dropBlockAsItemWithChance(world, pos, state, f, 0);
      }
   }


   public enum EnumPartType implements IStringSerializable {
      HEAD("head"),
      FOOT("foot");

   private final String name;
   private EnumPartType(String str) {
         this.name = str;
      }

      @Override
      public String getName() {
         return this.name;
      }

      @Override
      public String toString() {
         return this.name;
      }
   }
}
