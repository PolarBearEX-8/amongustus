package baritone.api.utils;

import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class BlockOptionalMetaLookup {
   private final ImmutableSet blockSet;
   private final ImmutableSet blockStateSet;
   private final ImmutableSet stackHashes;
   private final BlockOptionalMeta[] boms;

   public boolean has3(IBlockState state) {
      return this.blockStateSet.contains(state);
   }

   @Override
   public String toString() {
      return String.format("BlockOptionalMetaLookup{%s}", Arrays.toString((Object[])this.boms));
   }

   public BlockOptionalMetaLookup(List list) {
      this((BlockOptionalMeta[])list.stream().map(obj -> new BlockOptionalMeta((Block)obj)).toArray(BlockOptionalMeta[]::new));
   }

   public BlockOptionalMetaLookup(Block[] blocks) {
      this(Stream.of(blocks).map(BlockOptionalMeta::new).toArray(BlockOptionalMeta[]::new));
   }

   public BlockOptionalMetaLookup(BlockOptionalMeta[] blockOptionalMetas) {
      this.boms = blockOptionalMetas;
      HashSet set = new HashSet();
      HashSet set2 = new HashSet();
      HashSet set3 = new HashSet();

      for (BlockOptionalMeta blockOptionalMeta : blockOptionalMetas) {
         set.add(blockOptionalMeta.getBlock());
         set2.add(blockOptionalMeta.getBlock().getDefaultState());
      }

      this.blockSet = ImmutableSet.copyOf(set);
      this.blockStateSet = ImmutableSet.copyOf(set2);
      this.stackHashes = ImmutableSet.copyOf(set3);
   }

   public BlockOptionalMetaLookup(String[] strings) {
      this(Stream.of(strings).map(BlockOptionalMeta::new).toArray(BlockOptionalMeta[]::new));
   }

   public boolean has(ItemStack stack) {
      int cachedItemHash = stack.getCachedItemHash();
      return this.stackHashes.contains(cachedItemHash) || this.stackHashes.contains(cachedItemHash - stack.getItemDamage());
   }

   public boolean has2(Block block) {
      return this.blockSet.contains(block);
   }

   public List blocks() {
      return Arrays.asList(this.boms);
   }

}
