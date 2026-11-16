package net.minecraft.block;

import client.event.PreMotionFilter;
import client.setting.BlockNoneType;
import client.setting.CombatDistanceProperty;
import client.setting.ExternalOptionType;
import client.setting.MovementColorProperty;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.StatCraftItemManager;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.GoldenShovel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDoublePlant extends SecondaryBlockAdapter implements PreMotionFilter {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("variant", ExternalOptionType.class);
   public static final ImmutableSetEntry immutableSetEntry3 = ImmutableSetEntry.resolveImmutableSetEntry("half", CombatDistanceProperty.class);
   public static final ImmutableSetEntry immutableSetEntry2 = BaseBlockAdapter.autoWalkPath;

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player) {
      if (blockState.getComparable(immutableSetEntry3) == CombatDistanceProperty.UPPER) {
         if (world.getBlockState(blockPos.down()).getBlock() == this) {
            if (!player.invulnerableManager.enabled2) {
               IBlockState blockState2 = world.getBlockState(blockPos.down());
               ExternalOptionType comparable = (ExternalOptionType)blockState2.getComparable(immutableSetEntry);
               if (comparable != ExternalOptionType.FERN && comparable != ExternalOptionType.GRASS) {
                  world.matchesBlockPos3(blockPos.down(), true);
               } else if (!world.state) {
                  if (player.getHeldItem() != null && player.getHeldItem().getItem() == Items.shears) {
                     this.matchesWorldByWorld(world, blockPos, blockState2, player);
                     world.matchesBlockPos8(blockPos.down());
                  } else {
                     world.matchesBlockPos3(blockPos.down(), true);
                  }
               } else {
                  world.matchesBlockPos8(blockPos.down());
               }
            } else {
               world.matchesBlockPos8(blockPos.down());
            }
         }
      } else if (player.invulnerableManager.enabled2 && world.getBlockState(blockPos.up()).getBlock() == this) {
         world.matchesBlockPos(blockPos.up(), Blocks.air.getDefaultState(), 2);
      }

      super.processAction(world, blockPos, blockState, player);
   }

   @Override
   public int processAction2(World world, BlockPos blockPos) {
      return this.resolveExternalOptionType(world, blockPos).getCount();
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
      world.matchesBlockPos(blockPos.up(), this.getDefaultState().resolveIBlockState(immutableSetEntry3, CombatDistanceProperty.UPPER), 2);
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      IBlockState blockState2 = blockState;
      if (blockState.getComparable(immutableSetEntry3) == CombatDistanceProperty.UPPER) {
         IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2 - 1, count3);
         if (iBlockState.getBlock() == this) {
            blockState2 = blockState.resolveIBlockState(immutableSetEntry, iBlockState.getComparable(immutableSetEntry));
         }
      }

      return blockState2;
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   private boolean matchesWorldByWorld(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player) {
      ExternalOptionType comparable = (ExternalOptionType)blockState.getComparable(immutableSetEntry);
      if (comparable != ExternalOptionType.FERN && comparable != ExternalOptionType.GRASS) {
         return false;
      } else {
         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray4[Block.getIdFromBlock(this)]);
         int count = (comparable == ExternalOptionType.GRASS ? MovementColorProperty.GRASS : MovementColorProperty.FERN).getCount();
         applyWorld(world, blockPos, new ItemStack(Blocks.blockTallgrass, 2, count));
         return true;
      }
   }

   @Override
   public IBlockState processAction(int count) {
      return (count & 8) > 0
         ? this.getDefaultState().resolveIBlockState(immutableSetEntry3, CombatDistanceProperty.UPPER)
         : this.getDefaultState()
            .resolveIBlockState(immutableSetEntry3, CombatDistanceProperty.LOWER)
            .resolveIBlockState(immutableSetEntry, ExternalOptionType.resolveExternalOptionType(count & 7));
   }

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      applyWorld(world, blockPos, new ItemStack(this, 1, this.resolveExternalOptionType(world, blockPos).getCount()));
   }

   @Override
   public boolean processAction6(World world, BlockPos blockPos) {
      IBlockState blockState = world.getBlockState(blockPos);
      if (blockState.getBlock() != this) {
         return true;
      } else {
         ExternalOptionType comparable = (ExternalOptionType)this.processAction(blockState, world, blockPos.getX(), blockPos.getY(), blockPos.getZ()).getComparable(immutableSetEntry);
         return comparable == ExternalOptionType.FERN || comparable == ExternalOptionType.GRASS;
      }
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState, boolean flag) {
      ExternalOptionType externalOptionType = this.resolveExternalOptionType(world, blockPos);
      return externalOptionType != ExternalOptionType.GRASS && externalOptionType != ExternalOptionType.FERN;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      if (blockState.getComparable(immutableSetEntry3) == CombatDistanceProperty.UPPER) {
         return null;
      } else {
         ExternalOptionType comparable = (ExternalOptionType)blockState.getComparable(immutableSetEntry);
         return comparable == ExternalOptionType.FERN
            ? null
            : (comparable == ExternalOptionType.GRASS ? (random.nextInt(8) == 0 ? Items.wheatSeeds : null) : Item.processAction2(this));
      }
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (ExternalOptionType externalOptionType : ExternalOptionType.values()) {
         list.add(new ItemStack(item, 1, externalOptionType.getCount()));
      }
   }

   @Override
   public boolean nextFloat(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      return true;
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, int count) {
      ExternalOptionType externalOptionType = this.resolveExternalOptionType(iBlockAccess, blockPos);
      return externalOptionType != ExternalOptionType.GRASS && externalOptionType != ExternalOptionType.FERN ? 16777215 : GoldenShovel.computeCount(iBlockAccess, blockPos);
   }

   @Override
   public BlockNoneType processAction9() {
      return BlockNoneType.XZ;
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return super.processAction7(world, blockPos) && world.isAirBlock(blockPos.up());
   }

   public void applyWorldByWorld(World world, BlockPos blockPos, ExternalOptionType externalOptionType, int count) {
      world.matchesBlockPos(
         blockPos, this.getDefaultState().resolveIBlockState(immutableSetEntry3, CombatDistanceProperty.LOWER).resolveIBlockState(immutableSetEntry, externalOptionType), count
      );
      world.matchesBlockPos(blockPos.up(), this.getDefaultState().resolveIBlockState(immutableSetEntry3, CombatDistanceProperty.UPPER), count);
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      if (blockState.getComparable(immutableSetEntry3) == CombatDistanceProperty.UPPER) {
         return world.getBlockState(blockPos.down()).getBlock() == this;
      } else {
         IBlockState blockState2 = world.getBlockState(blockPos.up());
         return blockState2.getBlock() == this && super.matchesWorld(world, blockPos, blockState2);
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry3, immutableSetEntry, immutableSetEntry2});
   }

   @Override
   public void applyWorld(World world, EntityPlayer player, BlockPos blockPos, IBlockState blockState, TileEntity tileEntity) {
      if (world.state
         || player.getHeldItem() == null
         || player.getHeldItem().getItem() != Items.shears
         || blockState.getComparable(immutableSetEntry3) != CombatDistanceProperty.LOWER
         || !this.matchesWorldByWorld(world, blockPos, blockState, player)) {
         super.applyWorld(world, player, blockPos, blockState, tileEntity);
      }
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return blockState.getComparable(immutableSetEntry3) != CombatDistanceProperty.UPPER && blockState.getComparable(immutableSetEntry) != ExternalOptionType.GRASS
         ? ((ExternalOptionType)blockState.getComparable(immutableSetEntry)).getCount()
         : 0;
   }

   public ExternalOptionType resolveExternalOptionType(IBlockAccess iBlockAccess, BlockPos blockPos) {
      IBlockState blockState = iBlockAccess.getBlockState(blockPos);
      return blockState.getBlock() == this
         ? (ExternalOptionType)this.processAction(blockState, iBlockAccess, blockPos.getX(), blockPos.getY(), blockPos.getZ()).getComparable(immutableSetEntry)
         : ExternalOptionType.FERN;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return blockState.getComparable(immutableSetEntry3) == CombatDistanceProperty.UPPER
         ? 8 | ((EnumFacing)blockState.getComparable(immutableSetEntry2)).getIndexOffset()
         : ((ExternalOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState) {
      if (!this.matchesWorld(world, blockPos, blockState)) {
         boolean flag = blockState.getComparable(immutableSetEntry3) == CombatDistanceProperty.UPPER;
         BlockPos blockPos2 = flag ? blockPos : blockPos.up();
         BlockPos blockPos3 = flag ? blockPos.down() : blockPos;
         Object entry = flag ? this : world.getBlockState(blockPos2).getBlock();
         Object entry2 = flag ? world.getBlockState(blockPos3).getBlock() : this;
         if (entry == this) {
            world.matchesBlockPos(blockPos2, Blocks.air.getDefaultState(), 2);
         }

         if (entry2 == this) {
            world.matchesBlockPos(blockPos3, Blocks.air.getDefaultState(), 3);
            if (!flag) {
               this.processAction(world, blockPos3, blockState, 0);
            }
         }
      }
   }

   public BlockDoublePlant() {
      super(Material.material7);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(immutableSetEntry, ExternalOptionType.SUNFLOWER)
            .resolveIBlockState(immutableSetEntry3, CombatDistanceProperty.LOWER)
            .resolveIBlockState(immutableSetEntry2, EnumFacing.NORTH)
      );
      this.getBlock5(0.0F);
      this.processAction(stepManager26);
      this.setUnlocalizedName("doublePlant");
   }
}
