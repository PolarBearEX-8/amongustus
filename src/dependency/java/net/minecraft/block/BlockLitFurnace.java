package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.EnumFacingXType;
import client.setting.MovementFactorProperty;
import client.util.AutoWalkPath;
import client.util.DampenerFilter;
import client.util.FovTransformer;
import client.util.IBlockState;
import client.util.StatCraftItemManager;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockLitFurnace extends AdvancedBlockAdapter {
   public static final AutoWalkPath autoWalkPath = AutoWalkPath.resolveAutoWalkPath("facing", MovementFactorProperty.HORIZONTAL);
   public final boolean enabled3;
   public static boolean enabled2;

   @Override
   public IBlockState processAction(IBlockState blockState) {
      return this.getDefaultState().resolveIBlockState(autoWalkPath, EnumFacing.SOUTH);
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
      world.matchesBlockPos(blockPos, blockState.resolveIBlockState(autoWalkPath, livingEntity.getEnumFacing2().getEnumFacing5()), 2);
      if (stack.isActive()) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityFurnace) {
            ((TileEntityFurnace)tileEntity).setLabelText(stack.getString());
         }
      }
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Item.processAction2(Blocks.block39);
   }

   @Override
   public IBlockState processAction(int count) {
      EnumFacing enumFacing = EnumFacing.getEnumFacing6(count);
      if (enumFacing.getEnumFacingXType() == EnumFacingXType.Y) {
         enumFacing = EnumFacing.NORTH;
      }

      return this.getDefaultState().resolveIBlockState(autoWalkPath, enumFacing);
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      if (!enabled2) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityFurnace) {
            DampenerFilter.applyWorldByWorld(world, blockPos, (TileEntityFurnace)tileEntity);
            world.applyBlockPos(blockPos, this);
         }
      }

      super.onBlockAdded(world, blockPos, blockState);
   }

   @Override
   public int processAction5(World world, BlockPos blockPos) {
      return Container.computeCount(world.resolveTileEntity(blockPos));
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Item.processAction2(Blocks.block39);
   }

   private void applyWorld6(World world, BlockPos blockPos, IBlockState blockState) {
      if (!world.state) {
         Block block = world.getBlockState(blockPos.getBlockPosAsBlockPos()).getBlock();
         Block block2 = world.getBlockState(blockPos.getBlockPos2()).getBlock();
         Block block3 = world.getBlockState(blockPos.getBlockPos()).getBlock();
         Block block4 = world.getBlockState(blockPos.getBlockPos3()).getBlock();
         EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
         if (comparable == EnumFacing.NORTH && block.isState18() && !block2.isState18()) {
            comparable = EnumFacing.SOUTH;
         } else if (comparable == EnumFacing.SOUTH && block2.isState18() && !block.isState18()) {
            comparable = EnumFacing.NORTH;
         } else if (comparable == EnumFacing.WEST && block3.isState18() && !block4.isState18()) {
            comparable = EnumFacing.EAST;
         } else if (comparable == EnumFacing.EAST && block4.isState18() && !block3.isState18()) {
            comparable = EnumFacing.WEST;
         }

         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(autoWalkPath, comparable), 2);
      }
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityFurnace();
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return this.getDefaultState().resolveIBlockState(autoWalkPath, livingEntity.getEnumFacing2().getEnumFacing5());
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!world.state) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityFurnace) {
            player.applyIInventory((TileEntityFurnace)tileEntity);
            player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager2);
         }
      }

      return true;
   }

   public static void applyWorldByWorld(boolean flag, World world, BlockPos blockPos) {
      IBlockState blockState = world.getBlockState(blockPos);
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      enabled2 = true;
      if (flag) {
         world.matchesBlockPos(blockPos, Blocks.block88.getDefaultState().resolveIBlockState(autoWalkPath, blockState.getComparable(autoWalkPath)), 3);
         world.matchesBlockPos(blockPos, Blocks.block88.getDefaultState().resolveIBlockState(autoWalkPath, blockState.getComparable(autoWalkPath)), 3);
      } else {
         world.matchesBlockPos(blockPos, Blocks.block39.getDefaultState().resolveIBlockState(autoWalkPath, blockState.getComparable(autoWalkPath)), 3);
         world.matchesBlockPos(blockPos, Blocks.block39.getDefaultState().resolveIBlockState(autoWalkPath, blockState.getComparable(autoWalkPath)), 3);
      }

      enabled2 = false;
      if (tileEntity != null) {
         tileEntity.updateState5();
         world.applyBlockPos(blockPos, tileEntity);
      }
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      this.applyWorld6(world, blockPos, blockState);
   }

   @Override
   public int processAction13() {
      return 3;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset1();
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (this.enabled3) {
         EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
         double offset = (double)blockPos.getX() + 0.5;
         double offset2 = (double)blockPos.getY() + random.nextDouble() * 6.0 / 16.0;
         double offset3 = (double)blockPos.getZ() + 0.5;
         double offset4 = 0.52;
         double offset5 = random.nextDouble() * 0.6 - 0.3;
         switch (FovTransformer.countArray[comparable.ordinal()]) {
            case 1:
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_NORMAL, offset - offset4, offset2, offset3 + offset5, 0.0, 0.0, 0.0, new int[0]);
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.FLAME, offset - offset4, offset2, offset3 + offset5, 0.0, 0.0, 0.0, new int[0]);
               break;
            case 2:
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_NORMAL, offset + offset4, offset2, offset3 + offset5, 0.0, 0.0, 0.0, new int[0]);
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.FLAME, offset + offset4, offset2, offset3 + offset5, 0.0, 0.0, 0.0, new int[0]);
               break;
            case 3:
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_NORMAL, offset + offset5, offset2, offset3 - offset4, 0.0, 0.0, 0.0, new int[0]);
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.FLAME, offset + offset5, offset2, offset3 - offset4, 0.0, 0.0, 0.0, new int[0]);
               break;
            case 4:
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_NORMAL, offset + offset5, offset2, offset3 + offset4, 0.0, 0.0, 0.0, new int[0]);
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.FLAME, offset + offset5, offset2, offset3 + offset4, 0.0, 0.0, 0.0, new int[0]);
         }
      }
   }

   @Override
   public boolean processAction16() {
      return true;
   }

   public BlockLitFurnace(boolean flag) {
      super(Material.material4);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.NORTH));
      this.enabled3 = flag;
   }
}
