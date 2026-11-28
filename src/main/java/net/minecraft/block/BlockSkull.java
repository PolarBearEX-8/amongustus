package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSkull extends BlockContainer {
   public static final PropertyDirection FACING = PropertyDirection.create("facing");
   public static final PropertyBool NODROP = PropertyBool.create("nodrop");
   private static final Predicate IS_WITHER_SKELETON = new BlockSkull.FilterPredicate();
   private BlockPattern witherBasePattern;
   private BlockPattern witherPattern;

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      this.setBlockBoundsBasedOnState(world, n, n2, n3);
      return super.getCollisionBoundingBox(world, state, n, n2, n3);
   }

   public boolean canDispenserPlace(World world, BlockPos pos, ItemStack stack) {
      return stack.getMetadata() == 1 && pos.getY() >= 2 && world.getDifficulty() != EnumDifficulty.PEACEFUL && !world.isRemote
         ? this.getWitherBasePattern().match(world, pos) != null
         : false;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      switch (((EnumFacing)world.getBlockState2(n, n2, n3).getValue(FACING))) {
         case UP:
         default:
            this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
            break;
         case NORTH:
            this.setBlockBounds(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F);
            break;
         case SOUTH:
            this.setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F);
            break;
         case WEST:
            this.setBlockBounds(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
            break;
         case EAST:
            this.setBlockBounds(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
      }
   }

   public BlockPattern getWitherBasePattern() {
      if (this.witherBasePattern == null) {
         this.witherBasePattern = FactoryBlockPattern.start()
            .aisle(new String[]{"   ", "###", "~#~"})
            .where('#', BlockWorldState.hasState(BlockStateHelper.forBlock(Blocks.soul_sand)))
            .where('~', BlockWorldState.hasState(BlockStateHelper.forBlock(Blocks.air)))
            .build();
      }

      return this.witherBasePattern;
   }

   @Override
   public int getDamageValue(World world, BlockPos pos) {
      TileEntity tileEntity = world.getTileEntity(pos);
      return tileEntity instanceof TileEntitySkull ? ((TileEntitySkull)tileEntity).getSkullType() : super.getDamageValue(world, pos);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, NODROP});
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return this.getDefaultState().withProperty(FACING, entity.getHorizontalFacing()).withProperty(NODROP, Boolean.FALSE);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((EnumFacing)state.getValue(FACING)).getIndex();
      if ((Boolean)state.getValue(NODROP)) {
         n |= 8;
      }

      return n;
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal("tile.skull.skeleton.name");
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntitySkull();
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Items.skull;
   }

   public BlockSkull() {
      super(Material.circuits);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(NODROP, Boolean.FALSE));
      this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
   }

   @Override
   public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
      if (player.capabilities.isCreativeMode) {
         state = state.withProperty(NODROP, Boolean.TRUE);
         world.setBlockState(pos, state, 4);
      }

      super.onBlockHarvested(world, pos, state, player);
   }

   public void checkWitherSpawn(World world, BlockPos pos, TileEntitySkull tileEntitySkull) {
      if (tileEntitySkull.getSkullType() == 1 && pos.getY() >= 2 && world.getDifficulty() != EnumDifficulty.PEACEFUL && !world.isRemote) {
         BlockPattern witherPattern2 = this.getWitherPattern();
         BlockPattern.PatternHelper patternHelper = witherPattern2.match(world, pos);
         if (patternHelper != null) {
            for (int i = 0; i < 3; i++) {
               BlockWorldState blockWorldState = patternHelper.translateOffset(i, 0, 0);
               world.setBlockState(blockWorldState.getPos(), blockWorldState.getBlockState().withProperty(NODROP, Boolean.TRUE), 2);
            }

            for (int j = 0; j < witherPattern2.getPalmLength(); j++) {
               for (int k = 0; k < witherPattern2.getThumbLength(); k++) {
                  world.setBlockState(patternHelper.translateOffset(j, k, 0).getPos(), Blocks.air.getDefaultState(), 2);
               }
            }

            BlockPos pos2 = patternHelper.translateOffset(1, 0, 0).getPos();
            EntityWither entityWither = new EntityWither(world);
            BlockPos pos3 = patternHelper.translateOffset(1, 2, 0).getPos();
            entityWither.setLocationAndAngles(
               (double)pos3.getX() + 0.5,
               (double)pos3.getY() + 0.55,
               (double)pos3.getZ() + 0.5,
               patternHelper.getFinger().getAxis() == EnumFacing.Axis.X ? 0.0F : 90.0F,
               0.0F
            );
            entityWither.renderYawOffset = patternHelper.getFinger().getAxis() == EnumFacing.Axis.X ? 0.0F : 90.0F;
            entityWither.ignite();

            for (EntityPlayer player : (Iterable<EntityPlayer>)(world.getEntitiesWithinAABB(EntityPlayer.class, entityWither.getEntityBoundingBox().expand(50.0, 50.0, 50.0)))) {
               player.triggerAchievement(AchievementList.spawnWither);
            }

            world.spawnEntityInWorld(entityWither);

            for (int m = 0; m < 120; m++) {
               world.spawnParticle(
                  EnumParticleTypes.SNOWBALL,
                  (double)pos2.getX() + world.rand.nextDouble(),
                  (double)(pos2.getY() - 2) + world.rand.nextDouble() * 3.9,
                  (double)pos2.getZ() + world.rand.nextDouble(),
                  0.0,
                  0.0,
                  0.0,
                  new int[0]
               );
            }

            for (int idx = 0; idx < witherPattern2.getPalmLength(); idx++) {
               for (int index = 0; index < witherPattern2.getThumbLength(); index++) {
                  world.notifyNeighborsRespectDebug(patternHelper.translateOffset(idx, index, 0).getPos(), Blocks.air);
               }
            }
         }
      }
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      if (!world.isRemote) {
         if (!(Boolean)state.getValue(NODROP)) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof TileEntitySkull) {
               TileEntitySkull tileEntitySkull = (TileEntitySkull)tileEntity;
               ItemStack stack = new ItemStack(Items.skull, 1, this.getDamageValue(world, pos));
               if (tileEntitySkull.getSkullType() == 3 && tileEntitySkull.getPlayerProfile() != null) {
                  stack.setTagCompound(new NBTTagCompound());
                  NBTTagCompound tag = new NBTTagCompound();
                  NBTUtil.writeGameProfile(tag, tileEntitySkull.getPlayerProfile());
                  stack.getTagCompound().setTag("SkullOwner", tag);
               }

               spawnAsEntity(world, pos, stack);
            }
         }

         super.breakBlock(world, pos, state);
      }
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(n & 7)).withProperty(NODROP, (n & 8) > 0);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.skull;
   }

   public BlockPattern getWitherPattern() {
      if (this.witherPattern == null) {
         this.witherPattern = FactoryBlockPattern.start()
            .aisle(new String[]{"^^^", "###", "~#~"})
            .where('#', BlockWorldState.hasState(BlockStateHelper.forBlock(Blocks.soul_sand)))
            .where('^', IS_WITHER_SKELETON)
            .where('~', BlockWorldState.hasState(BlockStateHelper.forBlock(Blocks.air)))
            .build();
      }

      return this.witherPattern;
   }

public static final class FilterPredicate implements Predicate {
      public boolean apply(BlockWorldState blockWorldState) {
         return blockWorldState.getBlockState() != null
            && blockWorldState.getBlockState().getBlock() == Blocks.skull
            && blockWorldState.getTileEntity() instanceof TileEntitySkull
            && ((TileEntitySkull)blockWorldState.getTileEntity()).getSkullType() == 1;
      }

      public boolean apply(Object obj) {
         return this.apply((BlockWorldState)obj);
      }
   }

}
