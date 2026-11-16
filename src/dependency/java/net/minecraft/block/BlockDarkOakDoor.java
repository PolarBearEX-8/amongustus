package net.minecraft.block;

import client.setting.BlockSolidType;
import client.setting.CombatAngleProperty;
import client.setting.ContextOptionType;
import client.setting.MovementFactorProperty;
import client.util.AutoWalkPath;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.InternalImmutableSetEntry;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDarkOakDoor extends Block {
   public static final AutoWalkPath autoWalkPath = AutoWalkPath.resolveAutoWalkPath("facing", MovementFactorProperty.HORIZONTAL);
   public static final InternalImmutableSetEntry internalImmutableSetEntry2 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("open");
   public static final ImmutableSetEntry immutableSetEntry2 = ImmutableSetEntry.resolveImmutableSetEntry("hinge", ContextOptionType.class);
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("powered");
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("half", CombatAngleProperty.class);

   public static boolean checkIndex2(int count) {
      return (count & 16) != 0;
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player) {
      BlockPos blockPos2 = blockPos.down();
      if (player.invulnerableManager.enabled2
         && blockState.getComparable(immutableSetEntry) == CombatAngleProperty.UPPER
         && world.getBlockState(blockPos2).getBlock() == this) {
         world.matchesBlockPos8(blockPos2);
      }
   }

   public static EnumFacing resolveEnumFacing(int count) {
      return EnumFacing.resolveEnumFacing(count & 3).getEnumFacing2();
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (this.coreItemEntry == Material.material5) {
         return true;
      } else {
         BlockPos blockPos2 = blockState.getComparable(immutableSetEntry) == CombatAngleProperty.LOWER ? blockPos : blockPos.down();
         return (blockPos.equals(blockPos2) ? blockState : world.getBlockState(blockPos2)).getBlock() == this;
      }
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      IBlockState blockState2 = blockState;
      if (blockState.getComparable(immutableSetEntry) == CombatAngleProperty.LOWER) {
         IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2 + 1, count3);
         if (iBlockState.getBlock() == this) {
            blockState2 = blockState.resolveIBlockState(immutableSetEntry2, iBlockState.getComparable(immutableSetEntry2))
               .resolveIBlockState(internalImmutableSetEntry, iBlockState.getComparable(internalImmutableSetEntry));
         }
      } else {
         IBlockState iBlockState2 = iBlockAccess.getIBlockState(count, count2 - 1, count3);
         if (iBlockState2.getBlock() == this) {
            blockState2 = blockState.resolveIBlockState(autoWalkPath, iBlockState2.getComparable(autoWalkPath))
               .resolveIBlockState(internalImmutableSetEntry2, iBlockState2.getComparable(internalImmutableSetEntry2));
         }
      }

      return blockState2;
   }

   public static EnumFacing resolveEnumFacing(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return resolveEnumFacing(computeCount(iBlockAccess, blockPos));
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      this.applyIndex(computeCount(iBlockAccess, count, count2, count3));
   }

   @Override
   public AxisAlignedBB processAction(World world, BlockPos blockPos) {
      this.processAction(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
      return super.processAction(world, blockPos);
   }

   @Override
   public IBlockState processAction(int count) {
      return (count & 8) > 0
         ? this.getDefaultState()
            .resolveIBlockState(immutableSetEntry, CombatAngleProperty.UPPER)
            .resolveIBlockState(immutableSetEntry2, (count & 1) > 0 ? ContextOptionType.RIGHT : ContextOptionType.LEFT)
            .resolveIBlockState(internalImmutableSetEntry, (count & 2) > 0)
         : this.getDefaultState()
            .resolveIBlockState(immutableSetEntry, CombatAngleProperty.LOWER)
            .resolveIBlockState(autoWalkPath, EnumFacing.resolveEnumFacing(count & 3).getEnumFacing2())
            .resolveIBlockState(internalImmutableSetEntry2, (count & 4) > 0);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return this.getItem();
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return checkIndex(computeCount(iBlockAccess, count, count2, count3));
   }

   public static int computeCount(int count) {
      return count & 7;
   }

   public static boolean checkIndex(int count) {
      return (count & 4) != 0;
   }

   @Override
   public int processAction8() {
      return 1;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return blockState.getComparable(immutableSetEntry) == CombatAngleProperty.UPPER ? null : this.getItem();
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (blockState.getComparable(immutableSetEntry) == CombatAngleProperty.UPPER) {
         BlockPos blockPos2 = blockPos.down();
         IBlockState blockState2 = world.getBlockState(blockPos2);
         if (blockState2.getBlock() != this) {
            world.matchesBlockPos8(blockPos);
         } else if (block != this) {
            this.processAction(world, blockPos2, blockState2, block);
         }
      } else {
         boolean flag = false;
         BlockPos blockPos3 = blockPos.up();
         IBlockState blockState3 = world.getBlockState(blockPos3);
         if (blockState3.getBlock() != this) {
            world.matchesBlockPos8(blockPos);
            flag = true;
         }

         if (!World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())) {
            world.matchesBlockPos8(blockPos);
            flag = true;
            if (blockState3.getBlock() == this) {
               world.matchesBlockPos8(blockPos3);
            }
         }

         if (flag) {
            if (!world.state) {
               this.processAction(world, blockPos, blockState, 0);
            }
         } else {
            boolean flag2 = world.matchesBlockPos7(blockPos) || world.matchesBlockPos7(blockPos3);
            if ((flag2 || block.processAction7()) && block != this && flag2 != (Boolean)blockState3.getComparable(internalImmutableSetEntry)) {
               world.matchesBlockPos(blockPos3, blockState3.resolveIBlockState(internalImmutableSetEntry, flag2), 2);
               if (flag2 != (Boolean)blockState.getComparable(internalImmutableSetEntry2)) {
                  world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry2, flag2), 2);
                  world.applyBlockPos(blockPos, blockPos);
                  world.applyEntityPlayerByEntityPlayer((EntityPlayer)null, flag2 ? 1003 : 1006, blockPos, 0);
               }
            }
         }
      }
   }

   public static boolean checkIndexByIndex(int count) {
      return (count & 8) != 0;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (this.coreItemEntry == Material.material5) {
         return true;
      } else {
         BlockPos blockPos2 = blockState.getComparable(immutableSetEntry) == CombatAngleProperty.LOWER ? blockPos : blockPos.down();
         IBlockState blockState2 = blockPos.equals(blockPos2) ? blockState : world.getBlockState(blockPos2);
         if (blockState2.getBlock() != this) {
            return false;
         } else {
            IBlockState blockState3 = blockState2.resolveIBlockState(internalImmutableSetEntry2);
            world.matchesBlockPos(blockPos2, blockState3, 2);
            world.applyBlockPos(blockPos2, blockPos);
            world.applyEntityPlayerByEntityPlayer(player, ((Boolean)blockState3.getComparable(internalImmutableSetEntry2)) ? 1003 : 1006, blockPos, 0);
            return true;
         }
      }
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return blockPos.getY() >= 255
         ? false
         : World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())
            && super.processAction7(world, blockPos)
            && super.processAction7(world, blockPos.up());
   }

   private Item getItem() {
      return this == Blocks.ironDoor
         ? Items.ironDoor
         : (
            this == Blocks.block36
               ? Items.spruceDoor
               : (
                  this == Blocks.block124
                     ? Items.birchDoor
                     : (
                        this == Blocks.block112
                           ? Items.jungleDoor
                           : (this == Blocks.block84 ? Items.acaciaDoor : (this == Blocks.block99 ? Items.darkOakDoor : Items.woodenDoor))
                     )
               )
         );
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return checkIndex(computeCount(iBlockAccess, blockPos));
   }

   public static int computeCount(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return computeCount(iBlockAccess, blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   public void applyWorldByWorld(World world, BlockPos blockPos, boolean flag) {
      IBlockState blockState = world.getBlockState(blockPos);
      if (blockState.getBlock() == this) {
         BlockPos blockPos2 = blockState.getComparable(immutableSetEntry) == CombatAngleProperty.LOWER ? blockPos : blockPos.down();
         IBlockState blockState2 = blockPos == blockPos2 ? blockState : world.getBlockState(blockPos2);
         if (blockState2.getBlock() == this && (Boolean)blockState2.getComparable(internalImmutableSetEntry2) != flag) {
            world.matchesBlockPos(blockPos2, blockState2.resolveIBlockState(internalImmutableSetEntry2, flag), 2);
            world.applyBlockPos(blockPos2, blockPos);
            world.applyEntityPlayerByEntityPlayer((EntityPlayer)null, flag ? 1003 : 1006, blockPos, 0);
         }
      }
   }

   private void applyIndex(int count) {
      float factor = 0.1875F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
      EnumFacing enumFacing = resolveEnumFacing(count);
      boolean flag = checkIndex(count);
      boolean flag2 = checkIndex2(count);
      if (flag) {
         if (enumFacing == EnumFacing.EAST) {
            if (!flag2) {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, factor);
            } else {
               this.setBlockBounds(0.0F, 0.0F, 1.0F - factor, 1.0F, 1.0F, 1.0F);
            }
         } else if (enumFacing == EnumFacing.SOUTH) {
            if (!flag2) {
               this.setBlockBounds(1.0F - factor, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, factor, 1.0F, 1.0F);
            }
         } else if (enumFacing == EnumFacing.WEST) {
            if (!flag2) {
               this.setBlockBounds(0.0F, 0.0F, 1.0F - factor, 1.0F, 1.0F, 1.0F);
            } else {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, factor);
            }
         } else if (enumFacing == EnumFacing.NORTH) {
            if (!flag2) {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, factor, 1.0F, 1.0F);
            } else {
               this.setBlockBounds(1.0F - factor, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
         }
      } else if (enumFacing == EnumFacing.EAST) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, factor, 1.0F, 1.0F);
      } else if (enumFacing == EnumFacing.SOUTH) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, factor);
      } else if (enumFacing == EnumFacing.WEST) {
         this.setBlockBounds(1.0F - factor, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else if (enumFacing == EnumFacing.NORTH) {
         this.setBlockBounds(0.0F, 0.0F, 1.0F - factor, 1.0F, 1.0F, 1.0F);
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry, autoWalkPath, internalImmutableSetEntry2, immutableSetEntry2, internalImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      byte byteValue = 0;
      int count;
      if (blockState.getComparable(immutableSetEntry) == CombatAngleProperty.UPPER) {
         count = byteValue | 8;
         if (blockState.getComparable(immutableSetEntry2) == ContextOptionType.RIGHT) {
            count |= 1;
         }

         if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
            count |= 2;
         }
      } else {
         count = byteValue | ((EnumFacing)blockState.getComparable(autoWalkPath)).getEnumFacingAsEnumFacing().getIndexOffset();
         if ((Boolean)blockState.getComparable(internalImmutableSetEntry2)) {
            count |= 4;
         }
      }

      return count;
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      this.processAction(world, count, count2, count3);
      return super.processAction(world, blockState, count, count2, count3);
   }

   public static int computeCount(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2, count3);
      int index = iBlockState.getBlock().getIndex(iBlockState);
      boolean flag = checkIndexByIndex(index);
      IBlockState iBlockState2 = iBlockAccess.getIBlockState(count, count2 - 1, count3);
      int index2 = iBlockState2.getBlock().getIndex(iBlockState2);
      int count4 = flag ? index2 : index;
      IBlockState iBlockState3 = iBlockAccess.getIBlockState(count, count2 + 1, count3);
      int index3 = iBlockState3.getBlock().getIndex(iBlockState3);
      int count5 = flag ? index : index3;
      boolean flag2 = (count5 & 1) != 0;
      boolean flag3 = (count5 & 2) != 0;
      return computeCount(count4) | (flag ? 8 : 0) | (flag2 ? 16 : 0) | (flag3 ? 32 : 0);
   }

   @Override
   public String getText() {
      return I18n.getString2((this.getTextAsText() + ".name").replaceAll("tile", "item"));
   }

   @Override
   public MovingObjectPosition resolveMovingObjectPositionByWorld(
      World world, int count, int count2, int count3, double posX, double posX2, double posX3, double posX4, double posY, double posZ
   ) {
      this.processAction(world, count, count2, count3);
      return super.resolveMovingObjectPositionByWorld(world, count, count2, count3, posX, posX2, posX3, posX4, posY, posZ);
   }

   public static boolean matchesIBlockAccessByIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return checkIndex(computeCount(iBlockAccess, blockPos));
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockDarkOakDoor(Material material) {
      super(material);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(autoWalkPath, EnumFacing.NORTH)
            .resolveIBlockState(internalImmutableSetEntry2, Boolean.FALSE)
            .resolveIBlockState(immutableSetEntry2, ContextOptionType.LEFT)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
            .resolveIBlockState(immutableSetEntry, CombatAngleProperty.LOWER)
      );
   }
}
