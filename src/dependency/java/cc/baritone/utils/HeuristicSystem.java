package cc.baritone.utils;

import client.util.IBlockState;
import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class HeuristicSystem {
   public final ImmutableSet immutableSet;
   public final ImmutableSet immutableSet2;
   public final ImmutableSet immutableSet3;
   public final PrimaryCustom[] primaryCustomArray;

   public HeuristicSystem(String[] textArray) {
      this(Stream.of(textArray).map(PrimaryCustom::new).toArray(HeuristicSystem::resolvePrimaryCustomArray));
   }

   @Override
   public String toString() {
      return String.format("BlockOptionalMetaLookup{%s}", Arrays.toString((Object[])this.primaryCustomArray));
   }

   public HeuristicSystem(Block[] blockArray) {
      this(Stream.of(blockArray).map(PrimaryCustom::new).toArray(HeuristicSystem::resolvePrimaryCustomArray2));
   }

   public boolean contains(IBlockState blockState) {
      return this.immutableSet2.contains(blockState);
   }

   private static PrimaryCustom[] resolvePrimaryCustomArrayByIndex(int count) {
      return new PrimaryCustom[count];
   }

   private static PrimaryCustom[] resolvePrimaryCustomArray2(int count) {
      return new PrimaryCustom[count];
   }

   public HeuristicSystem(List list) {
      this((PrimaryCustom[])list.stream().map(block -> new PrimaryCustom((Block)block)).toArray(HeuristicSystem::resolvePrimaryCustomArrayByIndex));
   }

   public boolean matchesItemStack(ItemStack stack) {
      int indexOffset = stack.getIndexOffset1();
      return this.immutableSet3.contains(indexOffset) || this.immutableSet3.contains(indexOffset - stack.getIndexOffset2());
   }

   public boolean contains(Block block) {
      return this.immutableSet.contains(block);
   }

   public List asList() {
      return Arrays.asList(this.primaryCustomArray);
   }

   public HeuristicSystem(PrimaryCustom[] primaryCustomArray2) {
      this.primaryCustomArray = primaryCustomArray2;
      HashSet set = new HashSet();
      HashSet set2 = new HashSet();
      HashSet set3 = new HashSet();

      for (PrimaryCustom primaryCustom : primaryCustomArray2) {
         set.add(primaryCustom.getBlock());
         set2.add(primaryCustom.getBlock().getDefaultState());
      }

      this.immutableSet = ImmutableSet.copyOf(set);
      this.immutableSet2 = ImmutableSet.copyOf(set2);
      this.immutableSet3 = ImmutableSet.copyOf(set3);
   }

   private static PrimaryCustom[] resolvePrimaryCustomArray(int count) {
      return new PrimaryCustom[count];
   }
}
