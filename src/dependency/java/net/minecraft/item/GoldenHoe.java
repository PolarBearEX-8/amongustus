package net.minecraft.item;

import client.setting.StandardOptionType;
import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.ClientManagerResolver;
import client.util.ShortArrayEntry;
import com.google.common.collect.Lists;
import java.util.Random;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class GoldenHoe extends BiomeIDIsOutOfBoundsManager {
   public BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager42;

   @Override
   public float getFactor() {
      return this.biomeIDIsOutOfBoundsManager42.getFactor();
   }

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos) {
      this.biomeIDIsOutOfBoundsManager42.alreadyDecoratingManager.applyWorldByWorld(world, random, this, blockPos);
   }

   @Override
   public int computeCount(BlockPos blockPos) {
      return this.biomeIDIsOutOfBoundsManager42.computeCount(blockPos);
   }

   @Override
   public StandardOptionType getOCEAN() {
      return this.biomeIDIsOutOfBoundsManager42.getOCEAN();
   }

   @Override
   public void applyWorld(World world, Random random, ShortArrayEntry shortArrayEntry, int count, int count2, double offset) {
      this.biomeIDIsOutOfBoundsManager42.applyWorld(world, random, shortArrayEntry, count, count2, offset);
   }

   @Override
   public Class getClassAsClass() {
      return this.biomeIDIsOutOfBoundsManager42.getClassAsClass();
   }

   @Override
   public ClientManagerResolver nextInt(Random random) {
      return this.biomeIDIsOutOfBoundsManager42.nextInt(random);
   }

   @Override
   public int computeCountByBlockPos(BlockPos blockPos) {
      return this.biomeIDIsOutOfBoundsManager42.computeCountByBlockPos(blockPos);
   }

   @Override
   public boolean matchesBiomeIDIsOutOfBoundsManage(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager) {
      return this.biomeIDIsOutOfBoundsManager42.matchesBiomeIDIsOutOfBoundsManage(biomeIDIsOutOfBoundsManager);
   }

   public GoldenHoe(int count, BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager) {
      super(count);
      this.biomeIDIsOutOfBoundsManager42 = biomeIDIsOutOfBoundsManager;
      this.resolveBiomeIDIsOutOfBoundsManager(biomeIDIsOutOfBoundsManager.count3, true);
      this.labelText = biomeIDIsOutOfBoundsManager.labelText + " M";
      this.iBlockState = biomeIDIsOutOfBoundsManager.iBlockState;
      this.iBlockState2 = biomeIDIsOutOfBoundsManager.iBlockState2;
      this.count2 = biomeIDIsOutOfBoundsManager.count2;
      this.factor2 = biomeIDIsOutOfBoundsManager.factor2;
      this.factor4 = biomeIDIsOutOfBoundsManager.factor4;
      this.factor = biomeIDIsOutOfBoundsManager.factor;
      this.factor3 = biomeIDIsOutOfBoundsManager.factor3;
      this.count5 = biomeIDIsOutOfBoundsManager.count5;
      this.enabled2 = biomeIDIsOutOfBoundsManager.enabled2;
      this.enabled = biomeIDIsOutOfBoundsManager.enabled;
      this.list4 = Lists.newArrayList(biomeIDIsOutOfBoundsManager.list4);
      this.list3 = Lists.newArrayList(biomeIDIsOutOfBoundsManager.list3);
      this.list = Lists.newArrayList(biomeIDIsOutOfBoundsManager.list);
      this.list2 = Lists.newArrayList(biomeIDIsOutOfBoundsManager.list2);
      this.factor = biomeIDIsOutOfBoundsManager.factor;
      this.factor3 = biomeIDIsOutOfBoundsManager.factor3;
      this.factor2 = biomeIDIsOutOfBoundsManager.factor2 + 0.1F;
      this.factor4 = biomeIDIsOutOfBoundsManager.factor4 + 0.2F;
   }

   static {
   }
}
