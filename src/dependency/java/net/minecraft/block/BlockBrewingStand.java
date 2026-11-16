package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.BlockSolidType;
import client.util.DampenerFilter;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import client.util.StatCraftItemManager;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCauldron;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockBrewingStand extends AdvancedBlockAdapter {
   public static final InternalImmutableSetEntry[] internalImmutableSetEntryArray = new InternalImmutableSetEntry[]{
      InternalImmutableSetEntry.resolveInternalImmutableSetEntry("has_bottle_0"),
      InternalImmutableSetEntry.resolveInternalImmutableSetEntry("has_bottle_1"),
      InternalImmutableSetEntry.resolveInternalImmutableSetEntry("has_bottle_2")
   };

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public void processAction2() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
      if (stack.isActive()) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityCauldron) {
            ((TileEntityCauldron)tileEntity).setLabelText(stack.getString());
         }
      }
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Items.brewingStand;
   }

   @Override
   public IBlockState processAction(int count) {
      IBlockState defaultState = this.getDefaultState();

      for (int index = 0; index < 3; index++) {
         defaultState = defaultState.resolveIBlockState(internalImmutableSetEntryArray[index], (count & 1 << index) > 0);
      }

      return defaultState;
   }

   @Override
   public int processAction5(World world, BlockPos blockPos) {
      return Container.computeCount(world.resolveTileEntity(blockPos));
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      if (tileEntity instanceof TileEntityCauldron) {
         DampenerFilter.applyWorldByWorld(world, blockPos, (TileEntityCauldron)tileEntity);
      }

      super.onBlockAdded(world, blockPos, blockState);
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.brewingStand;
   }

   @Override
   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState, AxisAlignedBB boundingBox, List list, Entity entity) {
      this.setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 0.875F, 0.5625F);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      this.processAction2();
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityCauldron();
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!world.state) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityCauldron) {
            player.applyIInventory((TileEntityCauldron)tileEntity);
            player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager12);
         }
      }

      return true;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{internalImmutableSetEntryArray[0], internalImmutableSetEntryArray[1], internalImmutableSetEntryArray[2]});
   }

   @Override
   public int processAction13() {
      return 3;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0;

      for (int index = 0; index < 3; index++) {
         if ((Boolean)blockState.getComparable(internalImmutableSetEntryArray[index])) {
            count |= 1 << index;
         }
      }

      return count;
   }

   @Override
   public String getText() {
      return I18n.getString2("item.brewingStand.name");
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      double offset = (double)((float)blockPos.getX() + 0.4F + random.nextFloat() * 0.2F);
      double offset2 = (double)((float)blockPos.getY() + 0.7F + random.nextFloat() * 0.3F);
      double offset3 = (double)((float)blockPos.getZ() + 0.4F + random.nextFloat() * 0.2F);
      world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_NORMAL, offset, offset2, offset3, 0.0, 0.0, 0.0, new int[0]);
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

   public BlockBrewingStand() {
      super(Material.material5);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(internalImmutableSetEntryArray[0], Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntryArray[1], Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntryArray[2], Boolean.FALSE)
      );
   }
}
