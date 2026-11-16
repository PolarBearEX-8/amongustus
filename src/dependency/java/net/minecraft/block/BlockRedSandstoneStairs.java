package net.minecraft.block;

import client.setting.BlockSolidType;
import client.setting.ModularOptionType;
import client.setting.MovementChoiceProperty;
import client.setting.MovementFactorProperty;
import client.util.AutoWalkPath;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedSandstoneStairs extends Block {
   public static final AutoWalkPath autoWalkPath;
   public static final ImmutableSetEntry immutableSetEntry2;
   public static final ImmutableSetEntry immutableSetEntry;
   public static final int[][] countArray = new int[][]{{4, 5}, {5, 7}, {6, 7}, {4, 6}, {0, 1}, {1, 3}, {2, 3}, {0, 2}};
   public final Block block;
   public final IBlockState iBlockState2;
   public boolean enabled;
   public int count;

   @Override
   public void breakBlock(World world, BlockPos blockPos, IBlockState blockState) {
      this.block.breakBlock(world, blockPos, blockState);
   }

   @Override
   public BlockSolidType processAction3() {
      return this.block.processAction3();
   }

   @Override
   public float processAction(Entity entity) {
      return this.block.processAction(entity);
   }

   public void applyIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      if (iBlockAccess.getBlockState(blockPos).getComparable(immutableSetEntry2) == MovementChoiceProperty.TOP) {
         this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }
   }

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      this.block.updateTick(world, blockPos, blockState, random);
   }

   @Override
   public void onBlockClicked(World world, BlockPos blockPos, EntityPlayer player) {
      this.block.onBlockClicked(world, blockPos, player);
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count2, int count3, int count4) {
      IBlockState blockState2 = blockState;
      if (this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count2, count3, count4)) {
         switch (this.computeCount(iBlockAccess, count2, count3, count4)) {
            case 0:
               blockState2 = blockState.resolveIBlockState(immutableSetEntry, ModularOptionType.STRAIGHT);
               break;
            case 1:
               blockState2 = blockState.resolveIBlockState(immutableSetEntry, ModularOptionType.INNER_RIGHT);
               break;
            case 2:
               blockState2 = blockState.resolveIBlockState(immutableSetEntry, ModularOptionType.INNER_LEFT);
         }
      } else {
         switch (this.computeCountByIBlockAccess(iBlockAccess, count2, count3, count4)) {
            case 0:
               blockState2 = blockState.resolveIBlockState(immutableSetEntry, ModularOptionType.STRAIGHT);
               break;
            case 1:
               blockState2 = blockState.resolveIBlockState(immutableSetEntry, ModularOptionType.OUTER_RIGHT);
               break;
            case 2:
               blockState2 = blockState.resolveIBlockState(immutableSetEntry, ModularOptionType.OUTER_LEFT);
         }
      }

      return blockState2;
   }

   public int computeCount(IBlockAccess iBlockAccess, int count2, int count3, int count4) {
      IBlockState iBlockState = iBlockAccess.getIBlockState(count2, count3, count4);
      EnumFacing comparable = (EnumFacing)iBlockState.getComparable(autoWalkPath);
      MovementChoiceProperty comparable2 = (MovementChoiceProperty)iBlockState.getComparable(immutableSetEntry2);
      boolean flag = comparable2 == MovementChoiceProperty.TOP;
      if (comparable == EnumFacing.EAST) {
         IBlockState iBlockState3 = iBlockAccess.getIBlockState(count2 - 1, count3, count4);
         if (matchesBlockByBlock(iBlockState3.getBlock()) && comparable2 == iBlockState3.getComparable(immutableSetEntry2)) {
            EnumFacing comparable3 = (EnumFacing)iBlockState3.getComparable(autoWalkPath);
            if (comparable3 == EnumFacing.NORTH && !matchesIBlockAccess(iBlockAccess, count2, count3, count4 - 1, iBlockState)) {
               return flag ? 1 : 2;
            }

            if (comparable3 == EnumFacing.SOUTH && !matchesIBlockAccess(iBlockAccess, count2, count3, count4 + 1, iBlockState)) {
               return flag ? 2 : 1;
            }
         }
      } else if (comparable == EnumFacing.WEST) {
         IBlockState iBlockState4 = iBlockAccess.getIBlockState(count2 + 1, count3, count4);
         if (matchesBlockByBlock(iBlockState4.getBlock()) && comparable2 == iBlockState4.getComparable(immutableSetEntry2)) {
            EnumFacing comparable4 = (EnumFacing)iBlockState4.getComparable(autoWalkPath);
            if (comparable4 == EnumFacing.NORTH && !matchesIBlockAccess(iBlockAccess, count2, count3, count4 - 1, iBlockState)) {
               return flag ? 2 : 1;
            }

            if (comparable4 == EnumFacing.SOUTH && !matchesIBlockAccess(iBlockAccess, count2, count3, count4 + 1, iBlockState)) {
               return flag ? 1 : 2;
            }
         }
      } else if (comparable == EnumFacing.SOUTH) {
         IBlockState iBlockState5 = iBlockAccess.getIBlockState(count2, count3, count4 - 1);
         if (matchesBlockByBlock(iBlockState5.getBlock()) && comparable2 == iBlockState5.getComparable(immutableSetEntry2)) {
            EnumFacing comparable5 = (EnumFacing)iBlockState5.getComparable(autoWalkPath);
            if (comparable5 == EnumFacing.WEST && !matchesIBlockAccess(iBlockAccess, count2 - 1, count3, count4, iBlockState)) {
               return flag ? 2 : 1;
            }

            if (comparable5 == EnumFacing.EAST && !matchesIBlockAccess(iBlockAccess, count2 + 1, count3, count4, iBlockState)) {
               return flag ? 1 : 2;
            }
         }
      } else if (comparable == EnumFacing.NORTH) {
         IBlockState iBlockState6 = iBlockAccess.getIBlockState(count2, count3, count4 + 1);
         if (matchesBlockByBlock(iBlockState6.getBlock()) && comparable2 == iBlockState6.getComparable(immutableSetEntry2)) {
            EnumFacing comparable6 = (EnumFacing)iBlockState6.getComparable(autoWalkPath);
            if (comparable6 == EnumFacing.WEST && !matchesIBlockAccess(iBlockAccess, count2 - 1, count3, count4, iBlockState)) {
               return flag ? 1 : 2;
            }

            if (comparable6 == EnumFacing.EAST && !matchesIBlockAccess(iBlockAccess, count2 + 1, count3, count4, iBlockState)) {
               return flag ? 2 : 1;
            }
         }
      }

      return 0;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, Entity entity) {
      this.block.processAction(world, blockPos, entity);
   }

   @Override
   public boolean processAction(IBlockState blockState, boolean flag, boolean flag2) {
      return this.block.processAction(blockState, flag);
   }

   @Override
   public AxisAlignedBB processAction(World world, BlockPos blockPos) {
      return this.block.processAction(world, blockPos);
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count2, int count3, int count4) {
      if (this.enabled) {
         this.setBlockBounds(
            0.5F * (float)(this.count % 2),
            0.5F * (float)(this.count / 4 % 2),
            0.5F * (float)(this.count / 2 % 2),
            0.5F + 0.5F * (float)(this.count % 2),
            0.5F + 0.5F * (float)(this.count / 4 % 2),
            0.5F + 0.5F * (float)(this.count / 2 % 2)
         );
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   @Override
   public IBlockState processAction(int count2) {
      return this.getDefaultState()
         .resolveIBlockState(immutableSetEntry2, (count2 & 4) > 0 ? MovementChoiceProperty.TOP : MovementChoiceProperty.BOTTOM)
         .resolveIBlockState(autoWalkPath, EnumFacing.getEnumFacing6(5 - (count2 & 3)));
   }

   @Override
   public int processAction(World world) {
      return this.block.processAction(world);
   }

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return this.block.processAction3(this.iBlockState2);
   }

   @Override
   public int getIndex(IBlockAccess iBlockAccess, int count2, int count3, int count4) {
      return this.block.getIndex(iBlockAccess, count2, count3, count4);
   }

   @Override
   public Vec3 processAction(World world, BlockPos blockPos, Entity entity, Vec3 vec) {
      return this.block.processAction(world, blockPos, entity, vec);
   }

   public static boolean matchesBlockByBlock(Block block2) {
      return block2 instanceof BlockRedSandstoneStairs;
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      this.block.onBlockAdded(world, blockPos, this.iBlockState2);
   }

   public boolean matchesIBlockAccessByIBlockAccess(IBlockAccess iBlockAccess, int count2, int count3, int count4) {
      IBlockState iBlockState = iBlockAccess.getIBlockState(count2, count3, count4);
      EnumFacing comparable = (EnumFacing)iBlockState.getComparable(autoWalkPath);
      MovementChoiceProperty comparable2 = (MovementChoiceProperty)iBlockState.getComparable(immutableSetEntry2);
      boolean flag = comparable2 == MovementChoiceProperty.TOP;
      float factor = 0.5F;
      float factor2 = 1.0F;
      if (flag) {
         factor = 0.0F;
         factor2 = 0.5F;
      }

      float factor3 = 0.0F;
      float factor4 = 1.0F;
      float factor5 = 0.0F;
      float factor6 = 0.5F;
      boolean flag2 = true;
      if (comparable == EnumFacing.EAST) {
         factor3 = 0.5F;
         factor6 = 1.0F;
         IBlockState iBlockState3 = iBlockAccess.getIBlockState(count2 + 1, count3, count4);
         if (matchesBlockByBlock(iBlockState3.getBlock()) && comparable2 == iBlockState3.getComparable(immutableSetEntry2)) {
            EnumFacing comparable3 = (EnumFacing)iBlockState3.getComparable(autoWalkPath);
            if (comparable3 == EnumFacing.NORTH && !matchesIBlockAccess(iBlockAccess, count2, count3, count4 + 1, iBlockState)) {
               factor6 = 0.5F;
               flag2 = false;
            } else if (comparable3 == EnumFacing.SOUTH && !matchesIBlockAccess(iBlockAccess, count2, count3, count4 - 1, iBlockState)) {
               factor5 = 0.5F;
               flag2 = false;
            }
         }
      } else if (comparable == EnumFacing.WEST) {
         factor4 = 0.5F;
         factor6 = 1.0F;
         IBlockState iBlockState4 = iBlockAccess.getIBlockState(count2 - 1, count3, count4);
         if (matchesBlockByBlock(iBlockState4.getBlock()) && comparable2 == iBlockState4.getComparable(immutableSetEntry2)) {
            EnumFacing comparable4 = (EnumFacing)iBlockState4.getComparable(autoWalkPath);
            if (comparable4 == EnumFacing.NORTH && !matchesIBlockAccess(iBlockAccess, count2, count3, count4 + 1, iBlockState)) {
               factor6 = 0.5F;
               flag2 = false;
            } else if (comparable4 == EnumFacing.SOUTH && !matchesIBlockAccess(iBlockAccess, count2, count3, count4 - 1, iBlockState)) {
               factor5 = 0.5F;
               flag2 = false;
            }
         }
      } else if (comparable == EnumFacing.SOUTH) {
         factor5 = 0.5F;
         factor6 = 1.0F;
         IBlockState iBlockState5 = iBlockAccess.getIBlockState(count2, count3, count4 + 1);
         if (matchesBlockByBlock(iBlockState5.getBlock()) && comparable2 == iBlockState5.getComparable(immutableSetEntry2)) {
            EnumFacing comparable5 = (EnumFacing)iBlockState5.getComparable(autoWalkPath);
            if (comparable5 == EnumFacing.WEST && !matchesIBlockAccess(iBlockAccess, count2 + 1, count3, count4, iBlockState)) {
               factor4 = 0.5F;
               flag2 = false;
            } else if (comparable5 == EnumFacing.EAST && !matchesIBlockAccess(iBlockAccess, count2 - 1, count3, count4, iBlockState)) {
               factor3 = 0.5F;
               flag2 = false;
            }
         }
      } else if (comparable == EnumFacing.NORTH) {
         IBlockState iBlockState6 = iBlockAccess.getIBlockState(count2, count3, count4 - 1);
         if (matchesBlockByBlock(iBlockState6.getBlock()) && comparable2 == iBlockState6.getComparable(immutableSetEntry2)) {
            EnumFacing comparable6 = (EnumFacing)iBlockState6.getComparable(autoWalkPath);
            if (comparable6 == EnumFacing.WEST && !matchesIBlockAccess(iBlockAccess, count2 + 1, count3, count4, iBlockState)) {
               factor4 = 0.5F;
               flag2 = false;
            } else if (comparable6 == EnumFacing.EAST && !matchesIBlockAccess(iBlockAccess, count2 - 1, count3, count4, iBlockState)) {
               factor3 = 0.5F;
               flag2 = false;
            }
         }
      }

      this.setBlockBounds(factor3, factor, factor5, factor4, factor2, factor6);
      return flag2;
   }

   @Override
   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState, AxisAlignedBB boundingBox, List list, Entity entity) {
      this.applyIBlockAccess(world, blockPos);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      boolean flag = this.matchesIBlockAccessByIBlockAccess(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      if (flag && this.matchesIBlockAccessByIBlockAccess(world, blockPos)) {
         super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      }

      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public boolean processAction(IBlockState blockState, boolean flag) {
      return this.block.processAction(blockState, flag);
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos blockPos, BlockBreakState blockBreakState) {
      this.block.onNeighborBlockChange(world, blockPos, blockBreakState);
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count2, EntityLivingBase livingEntity) {
      IBlockState blockState = super.onBlockPlaced(world, blockPos, enumFacing, posX, posY, posZ, count2, livingEntity)
         .resolveIBlockState(autoWalkPath, livingEntity.getEnumFacing2())
         .resolveIBlockState(immutableSetEntry, ModularOptionType.STRAIGHT);
      return enumFacing == EnumFacing.DOWN || enumFacing != EnumFacing.UP && !((double)posY <= 0.5)
         ? blockState.resolveIBlockState(immutableSetEntry2, MovementChoiceProperty.TOP)
         : blockState.resolveIBlockState(immutableSetEntry2, MovementChoiceProperty.BOTTOM);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return this.block.onBlockActivated(world, blockPos, this.iBlockState2, player, EnumFacing.DOWN, 0.0F, 0.0F, 0.0F);
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return this.block.processAction7(world, blockPos);
   }

   @Override
   public boolean processAction10() {
      return this.block.processAction10();
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      this.processAction(world, blockPos, this.iBlockState2, Blocks.air);
      this.block.processAction3(world, blockPos, this.iBlockState2);
   }

   public int computeCountByIBlockAccess(IBlockAccess iBlockAccess, int count2, int count3, int count4) {
      IBlockState iBlockState = iBlockAccess.getIBlockState(count2, count3, count4);
      EnumFacing comparable = (EnumFacing)iBlockState.getComparable(autoWalkPath);
      MovementChoiceProperty comparable2 = (MovementChoiceProperty)iBlockState.getComparable(immutableSetEntry2);
      boolean flag = comparable2 == MovementChoiceProperty.TOP;
      if (comparable == EnumFacing.EAST) {
         IBlockState iBlockState3 = iBlockAccess.getIBlockState(count2 + 1, count3, count4);
         if (matchesBlockByBlock(iBlockState3.getBlock()) && comparable2 == iBlockState3.getComparable(immutableSetEntry2)) {
            EnumFacing comparable3 = (EnumFacing)iBlockState3.getComparable(autoWalkPath);
            if (comparable3 == EnumFacing.NORTH && !matchesIBlockAccess(iBlockAccess, count2, count3, count4 + 1, iBlockState)) {
               return flag ? 1 : 2;
            }

            if (comparable3 == EnumFacing.SOUTH && !matchesIBlockAccess(iBlockAccess, count2, count3, count4 - 1, iBlockState)) {
               return flag ? 2 : 1;
            }
         }
      } else if (comparable == EnumFacing.WEST) {
         IBlockState iBlockState4 = iBlockAccess.getIBlockState(count2 - 1, count3, count4);
         if (matchesBlockByBlock(iBlockState4.getBlock()) && comparable2 == iBlockState4.getComparable(immutableSetEntry2)) {
            EnumFacing comparable4 = (EnumFacing)iBlockState4.getComparable(autoWalkPath);
            if (comparable4 == EnumFacing.NORTH && !matchesIBlockAccess(iBlockAccess, count2, count3, count4 + 1, iBlockState)) {
               return flag ? 2 : 1;
            }

            if (comparable4 == EnumFacing.SOUTH && !matchesIBlockAccess(iBlockAccess, count2, count3, count4 - 1, iBlockState)) {
               return flag ? 1 : 2;
            }
         }
      } else if (comparable == EnumFacing.SOUTH) {
         IBlockState iBlockState5 = iBlockAccess.getIBlockState(count2, count3, count4 + 1);
         if (matchesBlockByBlock(iBlockState5.getBlock()) && comparable2 == iBlockState5.getComparable(immutableSetEntry2)) {
            EnumFacing comparable5 = (EnumFacing)iBlockState5.getComparable(autoWalkPath);
            if (comparable5 == EnumFacing.WEST && !matchesIBlockAccess(iBlockAccess, count2 + 1, count3, count4, iBlockState)) {
               return flag ? 2 : 1;
            }

            if (comparable5 == EnumFacing.EAST && !matchesIBlockAccess(iBlockAccess, count2 - 1, count3, count4, iBlockState)) {
               return flag ? 1 : 2;
            }
         }
      } else if (comparable == EnumFacing.NORTH) {
         IBlockState iBlockState6 = iBlockAccess.getIBlockState(count2, count3, count4 - 1);
         if (matchesBlockByBlock(iBlockState6.getBlock()) && comparable2 == iBlockState6.getComparable(immutableSetEntry2)) {
            EnumFacing comparable6 = (EnumFacing)iBlockState6.getComparable(autoWalkPath);
            if (comparable6 == EnumFacing.WEST && !matchesIBlockAccess(iBlockAccess, count2 + 1, count3, count4, iBlockState)) {
               return flag ? 1 : 2;
            }

            if (comparable6 == EnumFacing.EAST && !matchesIBlockAccess(iBlockAccess, count2 - 1, count3, count4, iBlockState)) {
               return flag ? 2 : 1;
            }
         }
      }

      return 0;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, immutableSetEntry2, immutableSetEntry});
   }

   public boolean matchesWorld3(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return this.block.processAction(world, blockPos, this.iBlockState2, player, EnumFacing.DOWN, 0.0F, 0.0F, 0.0F);
   }

   public static boolean matchesIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState) {
      IBlockState blockState2 = iBlockAccess.getBlockState(blockPos);
      return matchesBlockByBlock(blockState2.getBlock())
         && blockState2.getComparable(immutableSetEntry2) == blockState.getComparable(immutableSetEntry2)
         && blockState2.getComparable(autoWalkPath) == blockState.getComparable(autoWalkPath);
   }

   public static boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count2, int count3, int count4, IBlockState blockState) {
      IBlockState iBlockState = iBlockAccess.getIBlockState(count2, count3, count4);
      return matchesBlockByBlock(iBlockState.getBlock())
         && iBlockState.getComparable(immutableSetEntry2) == blockState.getComparable(immutableSetEntry2)
         && iBlockState.getComparable(autoWalkPath) == blockState.getComparable(autoWalkPath);
   }

   @Override
   public int getIndex(IBlockState blockState) {
      byte byteValue = 0;
      if (blockState.getComparable(immutableSetEntry2) == MovementChoiceProperty.TOP) {
         byteValue |= 4;
      }

      return byteValue | 5 - ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset1();
   }

   public boolean matchesIBlockAccessByIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      IBlockState blockState = iBlockAccess.getBlockState(blockPos);
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      MovementChoiceProperty comparable2 = (MovementChoiceProperty)blockState.getComparable(immutableSetEntry2);
      boolean flag = comparable2 == MovementChoiceProperty.TOP;
      float factor = 0.5F;
      float factor2 = 1.0F;
      if (flag) {
         factor = 0.0F;
         factor2 = 0.5F;
      }

      float factor3 = 0.0F;
      float factor4 = 0.5F;
      float factor5 = 0.5F;
      float factor6 = 1.0F;
      boolean flag2 = false;
      if (comparable == EnumFacing.EAST) {
         IBlockState blockState2 = iBlockAccess.getBlockState(blockPos.getBlockPos());
         if (matchesBlockByBlock(blockState2.getBlock()) && comparable2 == blockState2.getComparable(immutableSetEntry2)) {
            EnumFacing comparable3 = (EnumFacing)blockState2.getComparable(autoWalkPath);
            if (comparable3 == EnumFacing.NORTH && !matchesIBlockAccess(iBlockAccess, blockPos.getBlockPosAsBlockPos(), blockState)) {
               factor5 = 0.0F;
               factor6 = 0.5F;
               flag2 = true;
            } else if (comparable3 == EnumFacing.SOUTH && !matchesIBlockAccess(iBlockAccess, blockPos.getBlockPos2(), blockState)) {
               factor5 = 0.5F;
               factor6 = 1.0F;
               flag2 = true;
            }
         }
      } else if (comparable == EnumFacing.WEST) {
         IBlockState blockState3 = iBlockAccess.getBlockState(blockPos.getBlockPos3());
         if (matchesBlockByBlock(blockState3.getBlock()) && comparable2 == blockState3.getComparable(immutableSetEntry2)) {
            factor3 = 0.5F;
            factor4 = 1.0F;
            EnumFacing comparable4 = (EnumFacing)blockState3.getComparable(autoWalkPath);
            if (comparable4 == EnumFacing.NORTH && !matchesIBlockAccess(iBlockAccess, blockPos.getBlockPosAsBlockPos(), blockState)) {
               factor5 = 0.0F;
               factor6 = 0.5F;
               flag2 = true;
            } else if (comparable4 == EnumFacing.SOUTH && !matchesIBlockAccess(iBlockAccess, blockPos.getBlockPos2(), blockState)) {
               factor5 = 0.5F;
               factor6 = 1.0F;
               flag2 = true;
            }
         }
      } else if (comparable == EnumFacing.SOUTH) {
         IBlockState blockState4 = iBlockAccess.getBlockState(blockPos.getBlockPosAsBlockPos());
         if (matchesBlockByBlock(blockState4.getBlock()) && comparable2 == blockState4.getComparable(immutableSetEntry2)) {
            factor5 = 0.0F;
            factor6 = 0.5F;
            EnumFacing comparable5 = (EnumFacing)blockState4.getComparable(autoWalkPath);
            if (comparable5 == EnumFacing.WEST && !matchesIBlockAccess(iBlockAccess, blockPos.getBlockPos(), blockState)) {
               flag2 = true;
            } else if (comparable5 == EnumFacing.EAST && !matchesIBlockAccess(iBlockAccess, blockPos.getBlockPos3(), blockState)) {
               factor3 = 0.5F;
               factor4 = 1.0F;
               flag2 = true;
            }
         }
      } else if (comparable == EnumFacing.NORTH) {
         IBlockState blockState5 = iBlockAccess.getBlockState(blockPos.getBlockPos2());
         if (matchesBlockByBlock(blockState5.getBlock()) && comparable2 == blockState5.getComparable(immutableSetEntry2)) {
            EnumFacing comparable6 = (EnumFacing)blockState5.getComparable(autoWalkPath);
            if (comparable6 == EnumFacing.WEST && !matchesIBlockAccess(iBlockAccess, blockPos.getBlockPos(), blockState)) {
               flag2 = true;
            } else if (comparable6 == EnumFacing.EAST && !matchesIBlockAccess(iBlockAccess, blockPos.getBlockPos3(), blockState)) {
               factor3 = 0.5F;
               factor4 = 1.0F;
               flag2 = true;
            }
         }
      }

      if (flag2) {
         this.setBlockBounds(factor3, factor, factor5, factor4, factor2, factor6);
      }

      return flag2;
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      this.block.processAction3(world, blockPos, blockState, random);
   }

   @Override
   public MovingObjectPosition resolveMovingObjectPositionByWorld(
      World world, int count2, int count3, int count4, double posX, double posX2, double posX3, double posX4, double posY, double posZ
   ) {
      MovingObjectPosition[] hitResultArray = new MovingObjectPosition[8];
      IBlockState iBlockState = world.getIBlockState(count2, count3, count4);
      int indexOffset = ((EnumFacing)iBlockState.getComparable(autoWalkPath)).getIndexOffset();
      boolean flag = iBlockState.getComparable(immutableSetEntry2) == MovementChoiceProperty.TOP;
      int[] countArray2 = countArray[indexOffset + (flag ? 4 : 0)];
      this.enabled = true;

      for (int index = 0; index < 8; index++) {
         this.count = index;
         if (Arrays.binarySearch(countArray2, index) < 0) {
            hitResultArray[index] = super.resolveMovingObjectPositionByWorld(world, count2, count3, count4, posX, posX2, posX3, posX4, posY, posZ);
         }
      }

      for (int count5 : countArray2) {
         hitResultArray[count5] = null;
      }

      MovingObjectPosition hitResult = null;
      double offset = 0.0;

      for (MovingObjectPosition hitResult2 : hitResultArray) {
         if (hitResult2 != null) {
            double doubleItem = hitResult2.hitVec.getDouble4(posZ, posX3, posX4);
            if (doubleItem > offset) {
               hitResult = hitResult2;
               offset = doubleItem;
            }
         }
      }

      return hitResult;
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockRedSandstoneStairs(IBlockState blockState) {
      super(blockState.getBlock().coreItemEntry);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(autoWalkPath, EnumFacing.NORTH)
            .resolveIBlockState(immutableSetEntry2, MovementChoiceProperty.BOTTOM)
            .resolveIBlockState(immutableSetEntry, ModularOptionType.STRAIGHT)
      );
      this.block = blockState.getBlock();
      this.iBlockState2 = blockState;
      this.getBlock5(this.block.scaleFactor22);
      this.setHardness(this.block.scaleFactor21 / 3.0F);
      this.processAction(this.block.stepManager20);
      this.getBlockById(255);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }

   static {
      String[] textArray = new String[]{"facing", "half", "shape"};
      autoWalkPath = AutoWalkPath.resolveAutoWalkPath(textArray[0], MovementFactorProperty.HORIZONTAL);
      immutableSetEntry2 = ImmutableSetEntry.resolveImmutableSetEntry(textArray[1], MovementChoiceProperty.class);
      immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry(textArray[2], ModularOptionType.class);
   }
}
