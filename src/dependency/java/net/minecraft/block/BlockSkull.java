package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.CannotSupportPropertyPredicate;
import client.setting.EnumFacingXType;
import client.setting.GameSettingsPeacefulType;
import client.util.AchievementTheEnd2Manager;
import client.util.AutoWalkPath;
import client.util.AuxiliaryNameManager;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import client.util.JoinerEntry;
import client.util.PredicateArrayEntry;
import client.util.SecondaryPredicateAdapter;
import client.util.SecondaryWorldEntry;
import client.util.SplineBuffer;
import com.google.common.base.Predicate;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityWitherboss;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSkull extends AdvancedBlockAdapter {
   public static final AutoWalkPath autoWalkPath = AutoWalkPath.resolveAutoWalkPath("facing");
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("nodrop");
   public static final Predicate predicate = new SecondaryPredicateAdapter();
   public PredicateArrayEntry predicateArrayEntry2;
   public PredicateArrayEntry predicateArrayEntry;

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player) {
      IBlockState blockState2 = blockState;
      if (player.invulnerableManager.enabled2) {
         blockState2 = blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE);
         world.matchesBlockPos(blockPos, blockState2, 4);
      }

      super.processAction(world, blockPos, blockState2, player);
   }

   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      switch (SplineBuffer.countArray[((EnumFacing)iBlockAccess.getIBlockState(count, count2, count3).getComparable(autoWalkPath)).ordinal()]) {
         case 1:
         default:
            this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
            break;
         case 2:
            this.setBlockBounds(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F);
            break;
         case 3:
            this.setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F);
            break;
         case 4:
            this.setBlockBounds(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
            break;
         case 5:
            this.setBlockBounds(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
      }
   }

   @Override
   public int processAction2(World world, BlockPos blockPos) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      return tileEntity instanceof TileEntitySkull ? ((TileEntitySkull)tileEntity).getCount2() : super.processAction2(world, blockPos);
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, EnumFacing.getEnumFacing6(count & 7))
         .resolveIBlockState(internalImmutableSetEntry, (count & 8) > 0);
   }

   public PredicateArrayEntry getPredicateArrayEntry() {
      if (this.predicateArrayEntry == null) {
         this.predicateArrayEntry = JoinerEntry.getJoinerEntry()
            .resolveJoinerEntry(new String[]{"^^^", "###", "~#~"})
            .resolveJoinerEntry('#', SecondaryWorldEntry.resolvePredicate(CannotSupportPropertyPredicate.resolveCannotSupportPropertyPredica(Blocks.block126)))
            .resolveJoinerEntry('^', predicate)
            .resolveJoinerEntry('~', SecondaryWorldEntry.resolvePredicate(CannotSupportPropertyPredicate.resolveCannotSupportPropertyPredica(Blocks.air)))
            .getPredicateArrayEntry();
      }

      return this.predicateArrayEntry;
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Items.skull;
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      if (!world.state) {
         if (!(Boolean)blockState.getComparable(internalImmutableSetEntry)) {
            TileEntity tileEntity = world.resolveTileEntity(blockPos);
            if (tileEntity instanceof TileEntitySkull) {
               TileEntitySkull tileEntitySkull = (TileEntitySkull)tileEntity;
               ItemStack stack = new ItemStack(Items.skull, 1, this.processAction2(world, blockPos));
               if (tileEntitySkull.getCount2() == 3 && tileEntitySkull.getGameProfile() != null) {
                  stack.processAction(new NBTTagCompound());
                  NBTTagCompound tagCompound = new NBTTagCompound();
                  AuxiliaryNameManager.resolveNBTTagCompound(tagCompound, tileEntitySkull.getGameProfile());
                  stack.getStackTagCompound().applyText("SkullOwner", tagCompound);
               }

               applyWorld(world, blockPos, stack);
            }
         }

         super.onBlockAdded(world, blockPos, blockState);
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.skull;
   }

   public void applyWorld(World world, BlockPos blockPos, TileEntitySkull tileEntitySkull) {
      if (tileEntitySkull.getCount2() == 1 && blockPos.getY() >= 2 && world.getGameSettingsPeacefulType() != GameSettingsPeacefulType.PEACEFUL && !world.state) {
         PredicateArrayEntry predicateArrayEntry3 = this.getPredicateArrayEntry();
         InternalBlockPosEntry internalBlockPosEntry = predicateArrayEntry3.resolveInternalBlockPosEntry(world, blockPos);
         if (internalBlockPosEntry != null) {
            for (int index = 0; index < 3; index++) {
               SecondaryWorldEntry secondaryWorldEntry = internalBlockPosEntry.resolveSecondaryWorldEntry(index, 0, 0);
               world.matchesBlockPos(secondaryWorldEntry.getBlockPos(), secondaryWorldEntry.getIBlockState().resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE), 2);
            }

            for (int index2 = 0; index2 < predicateArrayEntry3.getCount3(); index2++) {
               for (int index3 = 0; index3 < predicateArrayEntry3.getCount2(); index3++) {
                  SecondaryWorldEntry secondaryWorldEntry2 = internalBlockPosEntry.resolveSecondaryWorldEntry(index2, index3, 0);
                  world.matchesBlockPos(secondaryWorldEntry2.getBlockPos(), Blocks.air.getDefaultState(), 2);
               }
            }

            BlockPos blockPos2 = internalBlockPosEntry.resolveSecondaryWorldEntry(1, 0, 0).getBlockPos();
            EntityWitherboss entityWitherboss = new EntityWitherboss(world);
            BlockPos blockPos3 = internalBlockPosEntry.resolveSecondaryWorldEntry(1, 2, 0).getBlockPos();
            entityWitherboss.applyDoubleByDouble(
               (double)blockPos3.getX() + 0.5,
               (double)blockPos3.getY() + 0.55,
               (double)blockPos3.getZ() + 0.5,
               internalBlockPosEntry.getEnumFacing().getEnumFacingXType() == EnumFacingXType.X ? 0.0F : 90.0F,
               0.0F
            );
            entityWitherboss.scaleFactor8 = internalBlockPosEntry.getEnumFacing().getEnumFacingXType() == EnumFacingXType.X ? 0.0F : 90.0F;
            entityWitherboss.updateState48();
            Iterator iterator = world.resolveList(EntityPlayer.class, entityWitherboss.getBoundingBox2().offset(50.0, 50.0, 50.0)).iterator();

            while (iterator.hasNext()) {
               ((EntityPlayer)iterator.next()).applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter13);
            }

            world.matchesEntity(entityWitherboss);

            for (int index4 = 0; index4 < 120; index4++) {
               world.applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.SNOWBALL,
                  (double)blockPos2.getX() + world.random.nextDouble(),
                  (double)(blockPos2.getY() - 2) + world.random.nextDouble() * 3.9,
                  (double)blockPos2.getZ() + world.random.nextDouble(),
                  0.0,
                  0.0,
                  0.0,
                  new int[0]
               );
            }

            for (int index5 = 0; index5 < predicateArrayEntry3.getCount3(); index5++) {
               for (int index6 = 0; index6 < predicateArrayEntry3.getCount2(); index6++) {
                  SecondaryWorldEntry secondaryWorldEntry3 = internalBlockPosEntry.resolveSecondaryWorldEntry(index5, index6, 0);
                  world.applyBlockPos3(secondaryWorldEntry3.getBlockPos(), Blocks.air);
               }
            }
         }
      }
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return this.getDefaultState().resolveIBlockState(autoWalkPath, livingEntity.getEnumFacing2()).resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE);
   }

   public PredicateArrayEntry getPredicateArrayEntryAsPredicateArrayEn() {
      if (this.predicateArrayEntry2 == null) {
         this.predicateArrayEntry2 = JoinerEntry.getJoinerEntry()
            .resolveJoinerEntry(new String[]{"   ", "###", "~#~"})
            .resolveJoinerEntry('#', SecondaryWorldEntry.resolvePredicate(CannotSupportPropertyPredicate.resolveCannotSupportPropertyPredica(Blocks.block126)))
            .resolveJoinerEntry('~', SecondaryWorldEntry.resolvePredicate(CannotSupportPropertyPredicate.resolveCannotSupportPropertyPredica(Blocks.air)))
            .getPredicateArrayEntry();
      }

      return this.predicateArrayEntry2;
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntitySkull();
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, internalImmutableSetEntry});
   }

   public boolean matchesWorldByWorld(World world, BlockPos blockPos, ItemStack stack) {
      return stack.getIndexOffset() == 1 && blockPos.getY() >= 2 && world.getGameSettingsPeacefulType() != GameSettingsPeacefulType.PEACEFUL && !world.state
         ? this.getPredicateArrayEntryAsPredicateArrayEn().resolveInternalBlockPosEntry(world, blockPos) != null
         : false;
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      this.processAction(world, count, count2, count3);
      return super.processAction(world, blockState, count, count2, count3);
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset1();
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         count |= 8;
      }

      return count;
   }

   @Override
   public String getText() {
      return I18n.getString2("tile.skull.skeleton.name");
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockSkull() {
      super(Material.material22);
      this.setIBlockState(
         this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.NORTH).resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
      );
      this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
   }
}
