package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.CannotSupportPropertyPredicate;
import client.util.AltPredicateAdapter;
import client.util.IBlockState;
import client.util.JoinerEntry;
import client.util.PredicateArrayEntry;
import client.util.SecondaryWorldEntry;
import com.google.common.base.Predicate;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySnowman;
import net.minecraft.entity.EntityVillagergolem;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockLitPumpkin extends BaseBlockAdapter {
   public PredicateArrayEntry predicateArrayEntry;
   public PredicateArrayEntry predicateArrayEntry3;
   public PredicateArrayEntry predicateArrayEntry4;
   public PredicateArrayEntry predicateArrayEntry2;
   public static final Predicate predicate = new AltPredicateAdapter();

   private void applyWorld2(World world, BlockPos blockPos) {
      InternalBlockPosEntry internalBlockPosEntry;
      if ((internalBlockPosEntry = this.getPredicateArrayEntry2().resolveInternalBlockPosEntry(world, blockPos)) != null) {
         for (int index = 0; index < this.getPredicateArrayEntry2().getCount2(); index++) {
            SecondaryWorldEntry secondaryWorldEntry = internalBlockPosEntry.resolveSecondaryWorldEntry(0, index, 0);
            world.matchesBlockPos(secondaryWorldEntry.getBlockPos(), Blocks.air.getDefaultState(), 2);
         }

         EntitySnowman entitySnowman = new EntitySnowman(world);
         BlockPos blockPos2 = internalBlockPosEntry.resolveSecondaryWorldEntry(0, 2, 0).getBlockPos();
         entitySnowman.applyDoubleByDouble((double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + 0.05, (double)blockPos2.getZ() + 0.5, 0.0F, 0.0F);
         world.matchesEntity(entitySnowman);

         for (int index2 = 0; index2 < 120; index2++) {
            world.applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.SNOW_SHOVEL,
               (double)blockPos2.getX() + world.random.nextDouble(),
               (double)blockPos2.getY() + world.random.nextDouble() * 2.5,
               (double)blockPos2.getZ() + world.random.nextDouble(),
               0.0,
               0.0,
               0.0,
               new int[0]
            );
         }

         for (int index3 = 0; index3 < this.getPredicateArrayEntry2().getCount2(); index3++) {
            SecondaryWorldEntry secondaryWorldEntry2 = internalBlockPosEntry.resolveSecondaryWorldEntry(0, index3, 0);
            world.applyBlockPos3(secondaryWorldEntry2.getBlockPos(), Blocks.air);
         }
      } else {
         InternalBlockPosEntry internalBlockPosEntry2;
         if ((internalBlockPosEntry2 = this.getPredicateArrayEntryAsPredicateArrayEn().resolveInternalBlockPosEntry(world, blockPos)) != null) {
            for (int index4 = 0; index4 < this.getPredicateArrayEntryAsPredicateArrayEn().getCount3(); index4++) {
               for (int index5 = 0; index5 < this.getPredicateArrayEntryAsPredicateArrayEn().getCount2(); index5++) {
                  world.matchesBlockPos(internalBlockPosEntry2.resolveSecondaryWorldEntry(index4, index5, 0).getBlockPos(), Blocks.air.getDefaultState(), 2);
               }
            }

            BlockPos blockPos3 = internalBlockPosEntry2.resolveSecondaryWorldEntry(1, 2, 0).getBlockPos();
            EntityVillagergolem entityVillagergolem = new EntityVillagergolem(world);
            entityVillagergolem.setEnabled6(true);
            entityVillagergolem.applyDoubleByDouble((double)blockPos3.getX() + 0.5, (double)blockPos3.getY() + 0.05, (double)blockPos3.getZ() + 0.5, 0.0F, 0.0F);
            world.matchesEntity(entityVillagergolem);

            for (int index6 = 0; index6 < 120; index6++) {
               world.applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.SNOWBALL,
                  (double)blockPos3.getX() + world.random.nextDouble(),
                  (double)blockPos3.getY() + world.random.nextDouble() * 3.9,
                  (double)blockPos3.getZ() + world.random.nextDouble(),
                  0.0,
                  0.0,
                  0.0,
                  new int[0]
               );
            }

            for (int index7 = 0; index7 < this.getPredicateArrayEntryAsPredicateArrayEn().getCount3(); index7++) {
               for (int index8 = 0; index8 < this.getPredicateArrayEntryAsPredicateArrayEn().getCount2(); index8++) {
                  SecondaryWorldEntry secondaryWorldEntry3 = internalBlockPosEntry2.resolveSecondaryWorldEntry(index7, index8, 0);
                  world.applyBlockPos3(secondaryWorldEntry3.getBlockPos(), Blocks.air);
               }
            }
         }
      }
   }

   public PredicateArrayEntry getPredicateArrayEntry() {
      if (this.predicateArrayEntry4 == null) {
         this.predicateArrayEntry4 = JoinerEntry.getJoinerEntry()
            .resolveJoinerEntry(new String[]{"~ ~", "###", "~#~"})
            .resolveJoinerEntry('#', SecondaryWorldEntry.resolvePredicate(CannotSupportPropertyPredicate.resolveCannotSupportPropertyPredica(Blocks.block16)))
            .resolveJoinerEntry('~', SecondaryWorldEntry.resolvePredicate(CannotSupportPropertyPredicate.resolveCannotSupportPropertyPredica(Blocks.air)))
            .getPredicateArrayEntry();
      }

      return this.predicateArrayEntry4;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(autoWalkPath, EnumFacing.resolveEnumFacing(count));
   }

   public PredicateArrayEntry getPredicateArrayEntryAsPredicateArrayEn() {
      if (this.predicateArrayEntry2 == null) {
         this.predicateArrayEntry2 = JoinerEntry.getJoinerEntry()
            .resolveJoinerEntry(new String[]{"~^~", "###", "~#~"})
            .resolveJoinerEntry('^', SecondaryWorldEntry.resolvePredicate(predicate))
            .resolveJoinerEntry('#', SecondaryWorldEntry.resolvePredicate(CannotSupportPropertyPredicate.resolveCannotSupportPropertyPredica(Blocks.block16)))
            .resolveJoinerEntry('~', SecondaryWorldEntry.resolvePredicate(CannotSupportPropertyPredicate.resolveCannotSupportPropertyPredica(Blocks.air)))
            .getPredicateArrayEntry();
      }

      return this.predicateArrayEntry2;
   }

   public boolean matchesWorld4(World world, BlockPos blockPos) {
      return this.getPredicateArrayEntry3().resolveInternalBlockPosEntry(world, blockPos) != null
         || this.getPredicateArrayEntry().resolveInternalBlockPosEntry(world, blockPos) != null;
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return world.getBlockState(blockPos).getBlock().coreItemEntry.canExecute() && World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ());
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return this.getDefaultState().resolveIBlockState(autoWalkPath, livingEntity.getEnumFacing2().getEnumFacing5());
   }

   public PredicateArrayEntry getPredicateArrayEntry2() {
      if (this.predicateArrayEntry3 == null) {
         this.predicateArrayEntry3 = JoinerEntry.getJoinerEntry()
            .resolveJoinerEntry(new String[]{"^", "#", "#"})
            .resolveJoinerEntry('^', SecondaryWorldEntry.resolvePredicate(predicate))
            .resolveJoinerEntry('#', SecondaryWorldEntry.resolvePredicate(CannotSupportPropertyPredicate.resolveCannotSupportPropertyPredica(Blocks.block61)))
            .getPredicateArrayEntry();
      }

      return this.predicateArrayEntry3;
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      super.processAction3(world, blockPos, blockState);
      this.applyWorld2(world, blockPos);
   }

   public PredicateArrayEntry getPredicateArrayEntry3() {
      if (this.predicateArrayEntry == null) {
         this.predicateArrayEntry = JoinerEntry.getJoinerEntry()
            .resolveJoinerEntry(new String[]{" ", "#", "#"})
            .resolveJoinerEntry('#', SecondaryWorldEntry.resolvePredicate(CannotSupportPropertyPredicate.resolveCannotSupportPropertyPredica(Blocks.block61)))
            .getPredicateArrayEntry();
      }

      return this.predicateArrayEntry;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset();
   }

   public BlockLitPumpkin() {
      super(Material.material13, MapColor.mapColor36);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.NORTH));
      this.processAction(true);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
