package cc.baritone.utils;

import client.setting.AdapterOptionType;
import client.setting.CombatAngleProperty;
import client.setting.CombatDistanceProperty;
import client.setting.CombatStateProperty;
import client.setting.CommonOptionType;
import client.setting.ContextOptionType;
import client.setting.EnumFacingXType;
import client.setting.ModularOptionType;
import client.setting.MovementChoiceProperty;
import client.setting.MovementModeProperty;
import client.setting.MovementProfileProperty;
import client.setting.MovementScaleProperty;
import client.setting.VClipDirection;
import client.util.DecayableManager;
import client.util.IBlockState;
import client.util.VMIBlockAdapter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAcaciaFence;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.BlockCobblestoneWall;
import net.minecraft.block.BlockDarkOakDoor;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockGlassPane;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockMelonStem;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockStandingSign;
import net.minecraft.block.BlockTripwire;
import net.minecraft.block.BlockVine;
import net.minecraft.block.InternalBlockAdapter;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public final class PrimaryCustom {
   public final Block block;
   public final int count;
   public final boolean enabled;
   public final Set set;
   public final ImmutableSet immutableSet;
   public final ImmutableSet immutableSet2;
   public static final Pattern pattern = Pattern.compile("^(.+?)(?::(\\d+))?$");
   public static final Map map;

   public PrimaryCustom(Block block2) {
      this(block2, null);
   }

   public static IBlockState resolveIBlockState(ItemStack stack) {
      return Block.resolveBlock(stack.getItem()).processAction(stack.getIndexOffset());
   }

   public boolean matchesItemStack(ItemStack stack) {
      int indexOffset = stack.getIndexOffset1();
      if (this.enabled) {
         indexOffset -= stack.getIndexOffset2();
      }

      return this.immutableSet2.contains(indexOffset);
   }

   public boolean matchesIBlockState(IBlockState blockState) {
      return blockState.getBlock() == this.block && this.immutableSet.contains(blockState.hashCode());
   }

   private static void applyMap(Map map2, Enum enumItem) {
      map2.put(enumItem.getClass(), enumItem);
   }

   private static Set resolveSet(Block block2, Integer count2) {
      return (Set)block2.getBlockManager().getImmutableList().stream().filter(state -> PrimaryCustom.matchesInteger(count2, (IBlockState)state)).collect(Collectors.toSet());
   }

   public PrimaryCustom(String text) {
      Matcher matcher = pattern.matcher(text);
      if (!matcher.find()) {
         throw new IllegalArgumentException("invalid block selector");
      } else {
         MatchResult matchResult = matcher.toMatchResult();
         this.enabled = matchResult.group(2) == null;
         ResourceLocation location = new ResourceLocation(matchResult.group(1));
         if (!Block.iterableExtensionBridge.containsKey(location)) {
            throw new IllegalArgumentException("Invalid block ID");
         } else {
            this.block = (Block)Block.iterableExtensionBridge.getObject(location);
            this.count = this.enabled ? 0 : Integer.parseInt(matchResult.group(2));
            this.set = resolveSet(this.block, this.getInteger());
            this.immutableSet = resolveImmutableSetBySet(this.set);
            this.immutableSet2 = resolveImmutableSet(this.set);
         }
      }
   }

   private static ItemStack resolveItemStack(IBlockState blockState) {
      return new ItemStack(blockState.getBlock().processAction(blockState, new Random(), 0), blockState.getBlock().getMetaFromState(blockState));
   }

   private static boolean matchesInteger(Integer count2, IBlockState blockState) {
      return count2 == null || computeCount(blockState) == count2;
   }

   private static ImmutableSet resolveImmutableSet(Set set2) {
      return ImmutableSet.copyOf(
         set2.stream().map(item -> PrimaryCustom.resolveItemStack((IBlockState)item)).map(stack -> ((ItemStack)stack).getIndexOffset1()).toArray(PrimaryCustom::resolveIntegerArrayByIndex)
      );
   }

   static {
      HashMap map2 = new HashMap();
      Consumer<Enum> consumer = enumItem -> PrimaryCustom.applyMap(map2, enumItem);
      consumer.accept(EnumFacing.NORTH);
      consumer.accept(EnumFacingXType.Y);
      consumer.accept(MovementScaleProperty.Y);
      consumer.accept(MovementChoiceProperty.BOTTOM);
      consumer.accept(ModularOptionType.STRAIGHT);
      consumer.accept(AdapterOptionType.DOWN_X);
      consumer.accept(CombatDistanceProperty.LOWER);
      consumer.accept(MovementModeProperty.BOTTOM);
      consumer.accept(CombatAngleProperty.LOWER);
      consumer.accept(ContextOptionType.LEFT);
      consumer.accept(CombatStateProperty.HEAD);
      consumer.accept(MovementProfileProperty.NORTH_SOUTH);
      consumer.accept(VClipDirection.BOTTOM);
      map2.put(VMIBlockAdapter.auxiliaryImmutableSetEntry, 0);
      map2.put(BlockBed.internalImmutableSetEntry, false);
      map2.put(BlockBrewingStand.internalImmutableSetEntryArray[0], false);
      map2.put(BlockBrewingStand.internalImmutableSetEntryArray[1], false);
      map2.put(BlockBrewingStand.internalImmutableSetEntryArray[2], false);
      map2.put(InternalBlockAdapter.internalImmutableSetEntry, false);
      map2.put(BlockDirt.internalImmutableSetEntry, false);
      map2.put(BlockDarkOakDoor.internalImmutableSetEntry2, false);
      map2.put(BlockDarkOakDoor.internalImmutableSetEntry, false);
      map2.put(BlockAcaciaFence.internalImmutableSetEntry4, false);
      map2.put(BlockAcaciaFence.internalImmutableSetEntry2, false);
      map2.put(BlockAcaciaFence.internalImmutableSetEntry3, false);
      map2.put(BlockAcaciaFence.internalImmutableSetEntry, false);
      map2.put(BlockFire.auxiliaryImmutableSetEntry, 0);
      map2.put(BlockFire.internalImmutableSetEntry6, false);
      map2.put(BlockFire.internalImmutableSetEntry4, false);
      map2.put(BlockFire.internalImmutableSetEntry5, false);
      map2.put(BlockFire.internalImmutableSetEntry, false);
      map2.put(BlockFire.auxiliaryImmutableSetEntry2, false);
      map2.put(BlockGrass.internalImmutableSetEntry, false);
      map2.put(DecayableManager.internalImmutableSetEntry, false);
      map2.put(BlockGlassPane.internalImmutableSetEntry, false);
      map2.put(BlockGlassPane.internalImmutableSetEntry2, false);
      map2.put(BlockGlassPane.internalImmutableSetEntry3, false);
      map2.put(BlockGlassPane.internalImmutableSetEntry4, false);
      map2.put(CommonOptionType.LINES_X, CommonOptionType.LINES_Y);
      map2.put(CommonOptionType.LINES_Z, CommonOptionType.LINES_Y);
      map2.put(BlockRedstoneWire.immutableSetEntry3, false);
      map2.put(BlockRedstoneWire.immutableSetEntry2, false);
      map2.put(BlockRedstoneWire.immutableSetEntry4, false);
      map2.put(BlockRedstoneWire.immutableSetEntry, false);
      map2.put(BlockSapling.auxiliaryImmutableSetEntry, 0);
      map2.put(BlockSkull.internalImmutableSetEntry, false);
      map2.put(BlockStandingSign.auxiliaryImmutableSetEntry, 0);
      map2.put(BlockMelonStem.auxiliaryImmutableSetEntry, 0);
      map2.put(BlockTripwire.internalImmutableSetEntry3, false);
      map2.put(BlockTripwire.internalImmutableSetEntry2, false);
      map2.put(BlockTripwire.internalImmutableSetEntry8, false);
      map2.put(BlockTripwire.internalImmutableSetEntry6, false);
      map2.put(BlockVine.internalImmutableSetEntry, false);
      map2.put(BlockVine.internalImmutableSetEntry4, false);
      map2.put(BlockVine.internalImmutableSetEntry2, false);
      map2.put(BlockVine.internalImmutableSetEntry3, false);
      map2.put(BlockVine.internalImmutableSetEntry5, false);
      map2.put(BlockCobblestoneWall.internalImmutableSetEntry5, false);
      map2.put(BlockCobblestoneWall.internalImmutableSetEntry, false);
      map2.put(BlockCobblestoneWall.internalImmutableSetEntry4, false);
      map2.put(BlockCobblestoneWall.internalImmutableSetEntry2, false);
      map2.put(BlockCobblestoneWall.internalImmutableSetEntry3, false);
      map = Collections.unmodifiableMap(map2);
   }

   private static ImmutableSet resolveImmutableSetBySet(Set set2) {
      return ImmutableSet.copyOf(set2.stream().map(Object::hashCode).toArray(PrimaryCustom::resolveIntegerArray));
   }

   private static Integer[] resolveIntegerArrayByIndex(int count2) {
      return new Integer[count2];
   }

   public IBlockState getIBlockState() {
      return this.set.size() > 0 ? (IBlockState)this.set.iterator().next() : null;
   }

   public Integer getInteger() {
      return this.enabled ? null : this.count;
   }

   public Block getBlock() {
      return this.block;
   }

   public Set getSet() {
      return this.immutableSet2;
   }

   private static Integer[] resolveIntegerArray(int count2) {
      return new Integer[count2];
   }

   public static Comparable resolveComparable(IProperty iProperty, Object entry) {
      return (Comparable)entry;
   }

   public static IBlockState resolveIBlockState(IBlockState blockState) {
      IBlockState blockState2 = blockState;
      UnmodifiableIterator iterator = blockState.getImmutableMap().keySet().iterator();

      while (iterator.hasNext()) {
         IProperty iProperty = (IProperty)iterator.next();
         Class memberRef = iProperty.getMemberRef();
         if (map.containsKey(iProperty)) {
            try {
               blockState2 = blockState2.resolveIBlockState(resolveIProperty(iProperty), resolveComparable(iProperty, map.get(iProperty)));
            } catch (IllegalArgumentException illegalArgumentException) {
            }
         } else if (map.containsKey(blockState.getComparable(iProperty))) {
            try {
               blockState2 = blockState2.resolveIBlockState(resolveIProperty(iProperty), resolveComparable(iProperty, map.get(blockState.getComparable(iProperty))));
            } catch (IllegalArgumentException illegalArgumentException2) {
            }
         } else if (map.containsKey(memberRef)) {
            try {
               blockState2 = blockState2.resolveIBlockState(resolveIProperty(iProperty), resolveComparable(iProperty, map.get(memberRef)));
            } catch (IllegalArgumentException illegalArgumentException3) {
            }
         }
      }

      return blockState2;
   }

   public PrimaryCustom(Block block2, Integer count2) {
      this.block = block2;
      this.enabled = count2 == null;
      this.count = this.enabled ? 0 : count2;
      this.set = resolveSet(block2, count2);
      this.immutableSet = resolveImmutableSetBySet(this.set);
      this.immutableSet2 = resolveImmutableSet(this.set);
   }

   public boolean matchesBlock(Block block2) {
      return block2 == this.block;
   }

   public static IProperty resolveIProperty(Object entry) {
      return (IProperty)entry;
   }

   public static int computeCount(IBlockState blockState) {
      return blockState.getBlock().getIndex(resolveIBlockState(blockState));
   }

   @Override
   public String toString() {
      return String.format("BlockOptionalMeta{block=%s,meta=%s}", this.block, this.getInteger());
   }
}
