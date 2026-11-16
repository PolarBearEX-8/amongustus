package net.minecraft.block;

import client.setting.EnumFacingXType;
import client.setting.MovementFactorProperty;
import client.util.AutoWalkPath;
import client.util.DampenerFilter;
import client.util.DistanceBuilder;
import client.util.IBlockState;
import client.util.IInventoryExtension;
import client.util.StatCraftItemManager;
import java.util.Iterator;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityOzelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTrappedChest extends AdvancedBlockAdapter {
   public static final AutoWalkPath autoWalkPath = AutoWalkPath.resolveAutoWalkPath("facing", MovementFactorProperty.HORIZONTAL);
   public final int count;

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count2, int count3, int count4) {
      if (iBlockAccess.getIBlockState(count2, count3, count4 - 1).getBlock() == this) {
         this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
      } else if (iBlockAccess.getIBlockState(count2, count3, count4 + 1).getBlock() == this) {
         this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
      } else if (iBlockAccess.getIBlockState(count2 - 1, count3, count4).getBlock() == this) {
         this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
      } else if (iBlockAccess.getIBlockState(count2 + 1, count3, count4).getBlock() == this) {
         this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
      } else {
         this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
      }
   }

   private boolean matchesWorld6(World world, BlockPos blockPos) {
      Iterator iterator = world.resolveList(
            EntityOzelot.class,
            new AxisAlignedBB(
               (double)blockPos.getX(),
               (double)(blockPos.getY() + 1),
               (double)blockPos.getZ(),
               (double)(blockPos.getX() + 1),
               (double)(blockPos.getY() + 2),
               (double)(blockPos.getZ() + 1)
            )
         )
         .iterator();

      while (iterator.hasNext()) {
         if (((EntityOzelot)((Entity)iterator.next())).isActive33()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
      EnumFacing enumFacing = EnumFacing.resolveEnumFacing(MathHelper.ceiling_double_int((double)(livingEntity.rotationYaw * 4.0F / 360.0F) + 0.5) & 3).getEnumFacing5();
      IBlockState blockState2 = blockState.resolveIBlockState(autoWalkPath, enumFacing);
      BlockPos blockPosAsBlockPos = blockPos.getBlockPosAsBlockPos();
      BlockPos blockPos2 = blockPos.getBlockPos2();
      BlockPos blockPos3 = blockPos.getBlockPos();
      BlockPos blockPos4 = blockPos.getBlockPos3();
      boolean flag = this == world.getBlockState(blockPosAsBlockPos).getBlock();
      boolean flag2 = this == world.getBlockState(blockPos2).getBlock();
      boolean flag3 = this == world.getBlockState(blockPos3).getBlock();
      boolean flag4 = this == world.getBlockState(blockPos4).getBlock();
      if (!flag && !flag2 && !flag3 && !flag4) {
         world.matchesBlockPos(blockPos, blockState2, 3);
      } else if (enumFacing.getEnumFacingXType() != EnumFacingXType.X || !flag && !flag2) {
         if (enumFacing.getEnumFacingXType() == EnumFacingXType.Z && (flag3 || flag4)) {
            if (flag3) {
               world.matchesBlockPos(blockPos3, blockState2, 3);
            } else {
               world.matchesBlockPos(blockPos4, blockState2, 3);
            }

            world.matchesBlockPos(blockPos, blockState2, 3);
         }
      } else {
         if (flag) {
            world.matchesBlockPos(blockPosAsBlockPos, blockState2, 3);
         } else {
            world.matchesBlockPos(blockPos2, blockState2, 3);
         }

         world.matchesBlockPos(blockPos, blockState2, 3);
      }

      if (stack.isActive()) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityChest) {
            ((TileEntityChest)tileEntity).setLabelText(stack.getString());
         }
      }
   }

   @Override
   public boolean processAction7() {
      return this.count == 1;
   }

   @Override
   public IBlockState processAction(int count2) {
      EnumFacing enumFacing = EnumFacing.getEnumFacing6(count2);
      if (enumFacing.getEnumFacingXType() == EnumFacingXType.Y) {
         enumFacing = EnumFacing.NORTH;
      }

      return this.getDefaultState().resolveIBlockState(autoWalkPath, enumFacing);
   }

   public IBlockState resolveIBlockStateByWorld(World world, BlockPos blockPos, IBlockState blockState) {
      IBlockState blockState2 = blockState;
      if (!world.state) {
         IBlockState blockState3 = world.getBlockState(blockPos.getBlockPosAsBlockPos());
         IBlockState blockState4 = world.getBlockState(blockPos.getBlockPos2());
         IBlockState blockState5 = world.getBlockState(blockPos.getBlockPos());
         IBlockState blockState6 = world.getBlockState(blockPos.getBlockPos3());
         EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
         Block block = blockState3.getBlock();
         Block block2 = blockState4.getBlock();
         Block block3 = blockState5.getBlock();
         Block block4 = blockState6.getBlock();
         if (block != this && block2 != this) {
            boolean state = block.isState18();
            boolean state2 = block2.isState18();
            if (block3 == this || block4 == this) {
               BlockPos blockPos2 = block3 == this ? blockPos.getBlockPos() : blockPos.getBlockPos3();
               IBlockState blockState7 = world.getBlockState(blockPos2.getBlockPosAsBlockPos());
               IBlockState blockState8 = world.getBlockState(blockPos2.getBlockPos2());
               comparable = EnumFacing.SOUTH;
               EnumFacing enumFacing;
               if (block3 == this) {
                  enumFacing = (EnumFacing)blockState5.getComparable(autoWalkPath);
               } else {
                  enumFacing = (EnumFacing)blockState6.getComparable(autoWalkPath);
               }

               if (enumFacing == EnumFacing.NORTH) {
                  comparable = EnumFacing.NORTH;
               }

               Block block5 = blockState7.getBlock();
               Block block6 = blockState8.getBlock();
               if ((state || block5.isState18()) && !state2 && !block6.isState18()) {
                  comparable = EnumFacing.SOUTH;
               }

               if ((state2 || block6.isState18()) && !state && !block5.isState18()) {
                  comparable = EnumFacing.NORTH;
               }
            }
         } else {
            BlockPos blockPos3 = block == this ? blockPos.getBlockPosAsBlockPos() : blockPos.getBlockPos2();
            IBlockState blockState9 = world.getBlockState(blockPos3.getBlockPos());
            IBlockState blockState10 = world.getBlockState(blockPos3.getBlockPos3());
            comparable = EnumFacing.EAST;
            EnumFacing enumFacing2;
            if (block == this) {
               enumFacing2 = (EnumFacing)blockState3.getComparable(autoWalkPath);
            } else {
               enumFacing2 = (EnumFacing)blockState4.getComparable(autoWalkPath);
            }

            if (enumFacing2 == EnumFacing.WEST) {
               comparable = EnumFacing.WEST;
            }

            Block block7 = blockState9.getBlock();
            Block block8 = blockState10.getBlock();
            if ((block3.isState18() || block7.isState18()) && !block4.isState18() && !block8.isState18()) {
               comparable = EnumFacing.EAST;
            }

            if ((block4.isState18() || block8.isState18()) && !block3.isState18() && !block7.isState18()) {
               comparable = EnumFacing.WEST;
            }
         }

         blockState2 = blockState.resolveIBlockState(autoWalkPath, comparable);
         world.matchesBlockPos(blockPos, blockState2, 3);
      }

      return blockState2;
   }

   public IInventoryExtension resolveIInventoryExtension(World world, BlockPos blockPos) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      if (!(tileEntity instanceof TileEntityChest)) {
         return null;
      } else {
         Object entry = (TileEntityChest)tileEntity;
         if (this.matchesWorld10(world, blockPos)) {
            return null;
         } else {
            for (EnumFacing enumFacing : (Iterable<EnumFacing>)(Iterable)(MovementFactorProperty.HORIZONTAL)) {
               BlockPos blockPos2 = blockPos.offset(enumFacing);
               if (world.getBlockState(blockPos2).getBlock() == this) {
                  if (this.matchesWorld10(world, blockPos2)) {
                     return null;
                  }

                  TileEntity tileEntity2 = world.resolveTileEntity(blockPos2);
                  if (tileEntity2 instanceof TileEntityChest) {
                     if (enumFacing != EnumFacing.WEST && enumFacing != EnumFacing.NORTH) {
                        entry = new DistanceBuilder("container.chestDouble", (IInventoryExtension)entry, (TileEntityChest)tileEntity2);
                     } else {
                        entry = new DistanceBuilder("container.chestDouble", (TileEntityChest)tileEntity2, (IInventoryExtension)entry);
                     }
                  }
               }
            }

            return (IInventoryExtension)entry;
         }
      }
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      if (tileEntity instanceof IInventory) {
         DampenerFilter.applyWorldByWorld(world, blockPos, (IInventory)tileEntity);
         world.applyBlockPos(blockPos, this);
      }

      super.onBlockAdded(world, blockPos, blockState);
   }

   @Override
   public int processAction5(World world, BlockPos blockPos) {
      return Container.computeCount(this.resolveIInventoryExtension(world, blockPos));
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      if (!this.processAction7()) {
         return 0;
      } else {
         int count2 = 0;
         TileEntity tileEntity = iBlockAccess.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityChest) {
            count2 = ((TileEntityChest)tileEntity).count3;
         }

         return MathHelper.getInt12(count2, 0, 15);
      }
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      super.processAction(world, blockPos, blockState, block);
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      if (tileEntity instanceof TileEntityChest) {
         tileEntity.updateState4();
      }
   }

   private boolean matchesWorld8(World world, BlockPos blockPos) {
      if (world.getBlockState(blockPos).getBlock() != this) {
         return false;
      } else {
         for (EnumFacing enumFacing : (Iterable<EnumFacing>)(Iterable)(MovementFactorProperty.HORIZONTAL)) {
            if (world.getBlockState(blockPos.offset(enumFacing)).getBlock() == this) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count2) {
      return new TileEntityChest();
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      int count2 = 0;
      BlockPos blockPos2 = blockPos.getBlockPos();
      BlockPos blockPos3 = blockPos.getBlockPos3();
      BlockPos blockPosAsBlockPos = blockPos.getBlockPosAsBlockPos();
      BlockPos blockPos4 = blockPos.getBlockPos2();
      if (world.getBlockState(blockPos2).getBlock() == this) {
         if (this.matchesWorld8(world, blockPos2)) {
            return false;
         }

         count2++;
      }

      if (world.getBlockState(blockPos3).getBlock() == this) {
         if (this.matchesWorld8(world, blockPos3)) {
            return false;
         }

         count2++;
      }

      if (world.getBlockState(blockPosAsBlockPos).getBlock() == this) {
         if (this.matchesWorld8(world, blockPosAsBlockPos)) {
            return false;
         }

         count2++;
      }

      if (world.getBlockState(blockPos4).getBlock() == this) {
         if (this.matchesWorld8(world, blockPos4)) {
            return false;
         }

         count2++;
      }

      return count2 <= 1;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!world.state) {
         IInventoryExtension iInventoryExtension = this.resolveIInventoryExtension(world, blockPos);
         if (iInventoryExtension != null) {
            player.applyIInventory(iInventoryExtension);
            if (this.count == 0) {
               player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager6);
            } else if (this.count == 1) {
               player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager27);
            }
         }
      }

      return true;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count2, EntityLivingBase livingEntity) {
      return this.getDefaultState().resolveIBlockState(autoWalkPath, livingEntity.getEnumFacing2());
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      this.resolveIBlockStateByWorld(world, blockPos, blockState);

      for (EnumFacing enumFacing : (Iterable<EnumFacing>)(Iterable)(MovementFactorProperty.HORIZONTAL)) {
         BlockPos blockPos2 = blockPos.offset(enumFacing);
         IBlockState blockState2 = world.getBlockState(blockPos2);
         if (blockState2.getBlock() == this) {
            this.resolveIBlockStateByWorld(world, blockPos2, blockState2);
         }
      }
   }

   private boolean matchesWorld10(World world, BlockPos blockPos) {
      return this.matchesWorld12(world, blockPos) || this.matchesWorld6(world, blockPos);
   }

   @Override
   public int processAction2(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return enumFacing == EnumFacing.UP ? this.processAction(iBlockAccess, blockPos, blockState, enumFacing) : 0;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath});
   }

   @Override
   public int processAction13() {
      return 2;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset1();
   }

   private boolean matchesWorld12(World world, BlockPos blockPos) {
      return world.getBlockState(blockPos.up()).getBlock().isActive2();
   }

   public IBlockState resolveIBlockState3(World world, BlockPos blockPos, IBlockState blockState) {
      EnumFacing enumFacing = null;

      for (EnumFacing enumFacing2 : (Iterable<EnumFacing>)(Iterable)(MovementFactorProperty.HORIZONTAL)) {
         IBlockState blockState2 = world.getBlockState(blockPos.offset(enumFacing2));
         if (blockState2.getBlock() == this) {
            return blockState;
         }

         if (blockState2.getBlock().isState18()) {
            if (enumFacing != null) {
               enumFacing = null;
               break;
            }

            enumFacing = enumFacing2;
         }
      }

      if (enumFacing != null) {
         return blockState.resolveIBlockState(autoWalkPath, enumFacing.getEnumFacing5());
      } else {
         EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
         if (world.getBlockState(blockPos.offset(comparable)).getBlock().isState18()) {
            comparable = comparable.getEnumFacing5();
         }

         if (world.getBlockState(blockPos.offset(comparable)).getBlock().isState18()) {
            comparable = comparable.getEnumFacingAsEnumFacing();
         }

         if (world.getBlockState(blockPos.offset(comparable)).getBlock().isState18()) {
            comparable = comparable.getEnumFacing5();
         }

         return blockState.resolveIBlockState(autoWalkPath, comparable);
      }
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

   public BlockTrappedChest(int count2) {
      super(Material.material);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.NORTH));
      this.count = count2;
      this.processAction(ItemGroupManager.itemGroupManager8);
      this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
   }
}
