package baritone.api.utils;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTrapDoor;
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
import net.minecraft.block.BlockBanner;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockStandingSign;
import net.minecraft.block.BlockStem;
import net.minecraft.block.BlockTripWire;
import net.minecraft.block.BlockVine;
import net.minecraft.block.BlockWall;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public final class BlockOptionalMeta {
   private final Block block;
   private final int meta;
   private final boolean noMeta;
   private final Set blockstates;
   private final ImmutableSet stateHashes;
   private final ImmutableSet stackHashes;
   private static final Pattern pattern = Pattern.compile("^(.+?)(?::(\\d+))?$");
   private static final Map normalizations;

   public BlockOptionalMeta(Block block2) {
      this(block2, null);
   }

   static {
      HashMap map = new HashMap();
      Consumer consumer = obj -> map.put(obj.getClass(), obj);
      consumer.accept(EnumFacing.NORTH);
      consumer.accept(EnumFacing.Axis.Y);
      consumer.accept(BlockLog.EnumAxis.Y);
      consumer.accept(BlockStairs.EnumHalf.BOTTOM);
      consumer.accept(BlockStairs.EnumShape.STRAIGHT);
      consumer.accept(BlockLever.EnumOrientation.DOWN_X);
      consumer.accept(BlockDoublePlant.EnumBlockHalf.LOWER);
      consumer.accept(net.minecraft.block.BlockSlab.EnumBlockHalf.BOTTOM);
      consumer.accept(BlockDoor.EnumDoorHalf.LOWER);
      consumer.accept(BlockDoor.EnumHingePosition.LEFT);
      consumer.accept(BlockBed.EnumPartType.HEAD);
      consumer.accept(BlockRailBase.EnumRailDirection.NORTH_SOUTH);
      consumer.accept(BlockTrapDoor.DoorHalf.BOTTOM);
      map.put(BlockBanner.ROTATION, 0);
      map.put(BlockBed.OCCUPIED, false);
      map.put(BlockBrewingStand.HAS_BOTTLE[0], false);
      map.put(BlockBrewingStand.HAS_BOTTLE[1], false);
      map.put(BlockBrewingStand.HAS_BOTTLE[2], false);
      map.put(BlockButton.POWERED, false);
      map.put(BlockDirt.SNOWY, false);
      map.put(BlockDoor.OPEN, false);
      map.put(BlockDoor.POWERED, false);
      map.put(BlockFence.NORTH, false);
      map.put(BlockFence.EAST, false);
      map.put(BlockFence.WEST, false);
      map.put(BlockFence.SOUTH, false);
      map.put(BlockFire.AGE, 0);
      map.put(BlockFire.NORTH, false);
      map.put(BlockFire.EAST, false);
      map.put(BlockFire.SOUTH, false);
      map.put(BlockFire.WEST, false);
      map.put(BlockFire.UPPER, false);
      map.put(BlockGrass.SNOWY, false);
      map.put(BlockLeaves.CHECK_DECAY, false);
      map.put(BlockPane.NORTH, false);
      map.put(BlockPane.EAST, false);
      map.put(BlockPane.WEST, false);
      map.put(BlockPane.SOUTH, false);
      map.put(BlockQuartz.EnumType.LINES_X, BlockQuartz.EnumType.LINES_Y);
      map.put(BlockQuartz.EnumType.LINES_Z, BlockQuartz.EnumType.LINES_Y);
      map.put(BlockRedstoneWire.NORTH, false);
      map.put(BlockRedstoneWire.EAST, false);
      map.put(BlockRedstoneWire.SOUTH, false);
      map.put(BlockRedstoneWire.WEST, false);
      map.put(BlockSapling.STAGE, 0);
      map.put(BlockSkull.NODROP, false);
      map.put(BlockStandingSign.ROTATION, 0);
      map.put(BlockStem.AGE, 0);
      map.put(BlockTripWire.NORTH, false);
      map.put(BlockTripWire.EAST, false);
      map.put(BlockTripWire.WEST, false);
      map.put(BlockTripWire.SOUTH, false);
      map.put(BlockVine.NORTH, false);
      map.put(BlockVine.EAST, false);
      map.put(BlockVine.SOUTH, false);
      map.put(BlockVine.WEST, false);
      map.put(BlockVine.UP, false);
      map.put(BlockWall.UP, false);
      map.put(BlockWall.NORTH, false);
      map.put(BlockWall.EAST, false);
      map.put(BlockWall.WEST, false);
      map.put(BlockWall.SOUTH, false);
      normalizations = Collections.unmodifiableMap(map);
   }

   public Set getStackHashes() {
      return this.stackHashes;
   }

   public static Comparable castToValue(IProperty property, Object obj) {
      return (Comparable)obj;
   }

   private static ImmutableSet getStateHashes(Set set) {
      return ImmutableSet.copyOf(set.stream().map(Object::hashCode).toArray(Integer[]::new));
   }

   private static ImmutableSet getStackHashes(Set set) {
      return ImmutableSet.copyOf(
         set.stream()
            .map(obj -> new ItemStack(((IBlockState)obj).getBlock().getItemDropped((IBlockState)obj, new Random(), 0), ((IBlockState)obj).getBlock().damageDropped((IBlockState)obj)))
            .map(obj2 -> ((ItemStack)obj2).getCachedItemHash())
            .toArray(Integer[]::new)
      );
   }

   public Block getBlock() {
      return this.block;
   }

   public Integer getMeta() {
      return this.noMeta ? null : this.meta;
   }

   public BlockOptionalMeta(String str) {
      Matcher matcher = pattern.matcher(str);
      if (!matcher.find()) {
         throw new IllegalArgumentException("invalid block selector");
      } else {
         MatchResult matchResult = matcher.toMatchResult();
         this.noMeta = matchResult.group(2) == null;
         ResourceLocation location = new ResourceLocation(matchResult.group(1));
         if (!Block.blockRegistry.containsKey(location)) {
            throw new IllegalArgumentException("Invalid block ID");
         } else {
            this.block = (Block)Block.blockRegistry.getObject(location);
            this.meta = this.noMeta ? 0 : Integer.parseInt(matchResult.group(2));
            this.blockstates = getStates(this.block, this.getMeta());
            this.stateHashes = getStateHashes(this.blockstates);
            this.stackHashes = getStackHashes(this.blockstates);
         }
      }
   }

   public static IProperty castToIProperty(Object obj) {
      return (IProperty)obj;
   }

   public static IBlockState normalize(IBlockState state) {
      IBlockState state2 = state;
      UnmodifiableIterator it = state.getProperties().keySet().iterator();

      while (it.hasNext()) {
         IProperty property = (IProperty)it.next();
         Class valueClass = property.getValueClass();
         if (normalizations.containsKey(property)) {
            try {
               state2 = state2.withProperty(castToIProperty(property), castToValue(property, normalizations.get(property)));
            } catch (IllegalArgumentException e) {
            }
         } else if (normalizations.containsKey(state.getValue(property))) {
            try {
               state2 = state2.withProperty(castToIProperty(property), castToValue(property, normalizations.get(state.getValue(property))));
            } catch (IllegalArgumentException e2) {
            }
         } else if (normalizations.containsKey(valueClass)) {
            try {
               state2 = state2.withProperty(castToIProperty(property), castToValue(property, normalizations.get(valueClass)));
            } catch (IllegalArgumentException e3) {
            }
         }
      }

      return state2;
   }

   public IBlockState getAnyBlockState() {
      return this.blockstates.size() > 0 ? (IBlockState)this.blockstates.iterator().next() : null;
   }

   private static Set getStates(Block block2, Integer num) {
      return (Set)(block2.getBlockState().getValidStates().stream().filter(obj -> num == null || stateMeta((IBlockState)obj) == num).collect(Collectors.toSet()));
   }

   public boolean matches3(IBlockState state) {
      return state.getBlock() == this.block && this.stateHashes.contains(state.hashCode());
   }

   public boolean matches(Block block2) {
      return block2 == this.block;
   }

   public static IBlockState blockStateFromStack(ItemStack stack) {
      return Block.getBlockFromItem(stack.getItem()).getStateFromMeta(stack.getMetadata());
   }

   @Override
   public String toString() {
      return String.format("BlockOptionalMeta{block=%s,meta=%s}", this.block, this.getMeta());
   }

   public boolean matches2(ItemStack stack) {
      int cachedItemHash = stack.getCachedItemHash();
      if (this.noMeta) {
         cachedItemHash -= stack.getItemDamage();
      }

      return this.stackHashes.contains(cachedItemHash);
   }

   public BlockOptionalMeta(Block block2, Integer num) {
      this.block = block2;
      this.noMeta = num == null;
      this.meta = this.noMeta ? 0 : num;
      this.blockstates = getStates(block2, num);
      this.stateHashes = getStateHashes(this.blockstates);
      this.stackHashes = getStackHashes(this.blockstates);
   }

   public static int stateMeta(IBlockState state) {
      return state.getBlock().getMetaFromState(normalize(state));
   }

}
