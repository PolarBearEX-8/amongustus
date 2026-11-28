package net.minecraft.block;

import com.google.common.base.Predicate;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockPumpkin extends BlockDirectional {
   private BlockPattern snowmanBasePattern;
   private BlockPattern snowmanPattern;
   private BlockPattern golemBasePattern;
   private BlockPattern golemPattern;
   private static final Predicate IS_PUMPKIN = new BlockPumpkin.FilterPredicate();

   public BlockPattern getSnowmanPattern() {
      if (this.snowmanPattern == null) {
         this.snowmanPattern = FactoryBlockPattern.start()
            .aisle(new String[]{"^", "#", "#"})
            .where('^', BlockWorldState.hasState(IS_PUMPKIN))
            .where('#', BlockWorldState.hasState(BlockStateHelper.forBlock(Blocks.snow)))
            .build();
      }

      return this.snowmanPattern;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return this.getDefaultState().withProperty(FACING, entity.getHorizontalFacing().getOpposite());
   }

   public boolean canDispenserPlace(World world, BlockPos pos) {
      return this.getSnowmanBasePattern().match(world, pos) != null || this.getGolemBasePattern().match(world, pos) != null;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return world.getBlockState(pos).getBlock().blockMaterial.isReplaceable() && World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ());
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(n));
   }

   public BlockPumpkin() {
      super(Material.gourd, MapColor.adobeColor);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING});
   }

   public BlockPattern getSnowmanBasePattern() {
      if (this.snowmanBasePattern == null) {
         this.snowmanBasePattern = FactoryBlockPattern.start()
            .aisle(new String[]{" ", "#", "#"})
            .where('#', BlockWorldState.hasState(BlockStateHelper.forBlock(Blocks.snow)))
            .build();
      }

      return this.snowmanBasePattern;
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      super.onBlockAdded(world, pos, state);
      this.trySpawnGolem(world, pos);
   }

   public BlockPattern getGolemPattern() {
      if (this.golemPattern == null) {
         this.golemPattern = FactoryBlockPattern.start()
            .aisle(new String[]{"~^~", "###", "~#~"})
            .where('^', BlockWorldState.hasState(IS_PUMPKIN))
            .where('#', BlockWorldState.hasState(BlockStateHelper.forBlock(Blocks.iron_block)))
            .where('~', BlockWorldState.hasState(BlockStateHelper.forBlock(Blocks.air)))
            .build();
      }

      return this.golemPattern;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
   }

   public BlockPattern getGolemBasePattern() {
      if (this.golemBasePattern == null) {
         this.golemBasePattern = FactoryBlockPattern.start()
            .aisle(new String[]{"~ ~", "###", "~#~"})
            .where('#', BlockWorldState.hasState(BlockStateHelper.forBlock(Blocks.iron_block)))
            .where('~', BlockWorldState.hasState(BlockStateHelper.forBlock(Blocks.air)))
            .build();
      }

      return this.golemBasePattern;
   }

   private void trySpawnGolem(World world, BlockPos pos) {
      BlockPattern.PatternHelper patternHelper;
      if ((patternHelper = this.getSnowmanPattern().match(world, pos)) != null) {
         for (int i = 0; i < this.getSnowmanPattern().getThumbLength(); i++) {
            world.setBlockState(patternHelper.translateOffset(0, i, 0).getPos(), Blocks.air.getDefaultState(), 2);
         }

         EntitySnowman entitySnowman = new EntitySnowman(world);
         BlockPos pos2 = patternHelper.translateOffset(0, 2, 0).getPos();
         entitySnowman.setLocationAndAngles((double)pos2.getX() + 0.5, (double)pos2.getY() + 0.05, (double)pos2.getZ() + 0.5, 0.0F, 0.0F);
         world.spawnEntityInWorld(entitySnowman);

         for (int j = 0; j < 120; j++) {
            world.spawnParticle(
               EnumParticleTypes.SNOW_SHOVEL,
               (double)pos2.getX() + world.rand.nextDouble(),
               (double)pos2.getY() + world.rand.nextDouble() * 2.5,
               (double)pos2.getZ() + world.rand.nextDouble(),
               0.0,
               0.0,
               0.0,
               new int[0]
            );
         }

         for (int k = 0; k < this.getSnowmanPattern().getThumbLength(); k++) {
            world.notifyNeighborsRespectDebug(patternHelper.translateOffset(0, k, 0).getPos(), Blocks.air);
         }
      } else if ((patternHelper = this.getGolemPattern().match(world, pos)) != null) {
         for (int m = 0; m < this.getGolemPattern().getPalmLength(); m++) {
            for (int idx = 0; idx < this.getGolemPattern().getThumbLength(); idx++) {
               world.setBlockState(patternHelper.translateOffset(m, idx, 0).getPos(), Blocks.air.getDefaultState(), 2);
            }
         }

         BlockPos pos3 = patternHelper.translateOffset(1, 2, 0).getPos();
         EntityIronGolem entityIronGolem = new EntityIronGolem(world);
         entityIronGolem.setPlayerCreated(true);
         entityIronGolem.setLocationAndAngles((double)pos3.getX() + 0.5, (double)pos3.getY() + 0.05, (double)pos3.getZ() + 0.5, 0.0F, 0.0F);
         world.spawnEntityInWorld(entityIronGolem);

         for (int index = 0; index < 120; index++) {
            world.spawnParticle(
               EnumParticleTypes.SNOWBALL,
               (double)pos3.getX() + world.rand.nextDouble(),
               (double)pos3.getY() + world.rand.nextDouble() * 3.9,
               (double)pos3.getZ() + world.rand.nextDouble(),
               0.0,
               0.0,
               0.0,
               new int[0]
            );
         }

         for (int i2 = 0; i2 < this.getGolemPattern().getPalmLength(); i2++) {
            for (int i3 = 0; i3 < this.getGolemPattern().getThumbLength(); i3++) {
               world.notifyNeighborsRespectDebug(patternHelper.translateOffset(i2, i3, 0).getPos(), Blocks.air);
            }
         }
      }
   }


   public static final class FilterPredicate implements Predicate {
      public boolean apply(IBlockState state) {
         return state != null && (state.getBlock() == Blocks.pumpkin || state.getBlock() == Blocks.lit_pumpkin);
      }

      public boolean apply(Object obj) {
         return this.apply((IBlockState)obj);
      }
   }
}
