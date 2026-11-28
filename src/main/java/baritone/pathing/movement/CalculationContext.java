package baritone.pathing.movement;

import baritone.Baritone;
import baritone.api.IBaritone;
import baritone.cache.WorldData;
import baritone.utils.ToolSet;
import baritone.pathing.precompute.PrecomputedData;
import baritone.utils.BlockStateInterface;
import baritone.utils.pathing.BetterWorldBorder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CalculationContext {
   private static final ItemStack STACK_BUCKET_WATER = new ItemStack(Items.water_bucket);
   public final boolean safeForThreadedUse;
   public final IBaritone baritone;
   public final World world;
   public final WorldData worldData;
   public final BlockStateInterface bsi;
   public final ToolSet toolSet;
   public final boolean hasWaterBucket;
   public final boolean hasThrowaway;
   public final boolean canSprint;
   public final double placeBlockCost;
   public final boolean allowBreak;
   public final List allowBreakAnyway;
   public final boolean allowParkour;
   public final boolean allowParkourPlace;
   public final boolean allowJumpAt256;
   public final boolean allowParkourAscend;
   public final boolean assumeWalkOnWater;
   public boolean allowFallIntoLava;
   public final boolean allowDiagonalDescend;
   public final boolean allowDiagonalAscend;
   public final boolean allowDownward;
   public int minFallHeight;
   public int maxFallHeightNoWater;
   public final int maxFallHeightBucket;
   public final double waterWalkSpeed;
   public final double breakBlockAdditionalCost;
   public double backtrackCostFavoringCoefficient;
   public double jumpPenalty;
   public final double walkOnWaterOnePenalty;
   public final BetterWorldBorder worldBorder;
   public final PrecomputedData precomputedData = new PrecomputedData();

   public IBlockState get(int n, int n2, int n3) {
      return this.bsi.get0(n, n2, n3);
   }

   public static boolean hasWaterBucket(EntityPlayer player) {
      boolean flag = false;

      for (int i = 0; i <= 8; i++) {
         ItemStack stackInSlot = player.inventory.getStackInSlot(i);
         if (stackInSlot != null && stackInSlot.isItemEqual(STACK_BUCKET_WATER)) {
            flag = true;
            break;
         }
      }

      return flag;
   }

   public double costOfPlacingAt(int n, int n2, int n3, IBlockState state) {
      if (!this.hasThrowaway) {
         return 1000000.0;
      } else if (this.isPossiblyProtected(n, n2, n3)) {
         return 1000000.0;
      } else {
         return !this.worldBorder.canPlaceAt(n, n3) ? 1000000.0 : this.placeBlockCost;
      }
   }

   public IBlockState get(BlockPos pos) {
      return this.get(pos.getX(), pos.getY(), pos.getZ());
   }

   public CalculationContext(IBaritone baritone2, boolean flag) {
      this.safeForThreadedUse = flag;
      this.baritone = baritone2;
      EntityPlayerSP player = baritone2.getPlayerContext().player();
      this.world = baritone2.getPlayerContext().world();
      this.worldData = (WorldData)baritone2.getPlayerContext().worldData();
      this.bsi = new BlockStateInterface(baritone2.getPlayerContext(), flag);
      this.toolSet = new ToolSet(player);
      this.hasThrowaway = (Boolean)Baritone.settings().allowPlace.value && ((Baritone)baritone2).getInventoryBehavior().hasGenericThrowaway();
      this.hasWaterBucket = (Boolean)Baritone.settings().allowWaterBucketFall.value && hasWaterBucket(player) && this.world.provider.getDimensionId() != -1;
      this.canSprint = (Boolean)Baritone.settings().allowSprint.value && player.getFoodStats().getFoodLevel() > 6;
      this.placeBlockCost = (Double)Baritone.settings().blockPlacementPenalty.value;
      this.allowBreak = (Boolean)Baritone.settings().allowBreak.value;
      this.allowBreakAnyway = new ArrayList((Collection)Baritone.settings().allowBreakAnyway.value);
      this.allowParkour = (Boolean)Baritone.settings().allowParkour.value;
      this.allowParkourPlace = (Boolean)Baritone.settings().allowParkourPlace.value;
      this.allowJumpAt256 = (Boolean)Baritone.settings().allowJumpAt256.value;
      this.allowParkourAscend = (Boolean)Baritone.settings().allowParkourAscend.value;
      this.assumeWalkOnWater = (Boolean)Baritone.settings().assumeWalkOnWater.value;
      this.allowFallIntoLava = false;
      this.allowDiagonalDescend = (Boolean)Baritone.settings().allowDiagonalDescend.value;
      this.allowDiagonalAscend = (Boolean)Baritone.settings().allowDiagonalAscend.value;
      this.allowDownward = (Boolean)Baritone.settings().allowDownward.value;
      this.minFallHeight = 3;
      this.maxFallHeightNoWater = (Integer)Baritone.settings().maxFallHeightNoWater.value;
      this.maxFallHeightBucket = (Integer)Baritone.settings().maxFallHeightBucket.value;
      int depthStriderModifier = EnchantmentHelper.getDepthStriderModifier(player);
      if (depthStriderModifier > 3) {
         depthStriderModifier = 3;
      }

      float f = (float)depthStriderModifier / 3.0F;
      this.waterWalkSpeed = 9.09090909090909 * (double)(1.0F - f) + 4.63284688441047 * (double)f;
      this.breakBlockAdditionalCost = (Double)Baritone.settings().blockBreakAdditionalPenalty.value;
      this.backtrackCostFavoringCoefficient = (Double)Baritone.settings().backtrackCostFavoringCoefficient.value;
      this.jumpPenalty = (Double)Baritone.settings().jumpPenalty.value;
      this.walkOnWaterOnePenalty = (Double)Baritone.settings().walkOnWaterOnePenalty.value;
      this.worldBorder = new BetterWorldBorder(this.world.getWorldBorder());
   }

   public CalculationContext(IBaritone baritone2) {
      this(baritone2, false);
   }

   public boolean isPossiblyProtected(int n, int n2, int n3) {
      return false;
   }

   public boolean isLoaded(int n, int n2) {
      return this.bsi.isLoaded(n, n2);
   }

   public double breakCostMultiplierAt(int n, int n2, int n3, IBlockState state) {
      if (!this.allowBreak && !this.allowBreakAnyway.contains(state.getBlock())) {
         return 1000000.0;
      } else {
         return this.isPossiblyProtected(n, n2, n3) ? 1000000.0 : 1.0;
      }
   }

   public final IBaritone getBaritone() {
      return this.baritone;
   }

   public Block getBlock(int n, int n2, int n3) {
      return this.get(n, n2, n3).getBlock();
   }

   public double getPlaceBlockCost() {
      return this.placeBlockCost;
   }
}
