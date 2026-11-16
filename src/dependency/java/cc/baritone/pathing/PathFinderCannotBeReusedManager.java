package cc.baritone.pathing;

import cc.baritone.api.ItemStackEntry;
import cc.baritone.utils.AStarSampler;
import cc.baritone.utils.BaritoneManager;
import cc.baritone.utils.BetterBlockPosXSYSZSBlockPos;
import cc.baritone.utils.Custom;
import cc.baritone.utils.DRNHandler;
import cc.baritone.utils.DepthFirstScanner;
import cc.baritone.utils.HeuristicValidator;
import cc.baritone.utils.SecondaryListener;
import cc.baritone.utils.TertiaryCustom;
import cc.baritone.utils.TertiaryListener;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.Optional;

public abstract class PathFinderCannotBeReusedManager implements DRNHandler, TertiaryListener {
   public final int count;
   public final int count2;
   public final int count3;
   public final SecondaryListener secondaryListener;
   public final ItemStackEntry itemStackEntry;
   public final Long2ObjectOpenHashMap long2ObjectOpenHashMap;
   public AStarSampler aStarSampler;
   public AStarSampler aStarSampler2;
   public final AStarSampler[] aStarSamplerArray;
   public volatile boolean enabled;
   public boolean enabled2;
   public static final double[] valueArray = new double[]{1.5, 2.0, 2.5, 3.0, 4.0, 5.0, 10.0};
   public static final double value = 5.0;
   public static final double value2 = 0.01;

   @Override
   public Optional getOptionalAsOptional() {
      return Optional.ofNullable(this.aStarSampler2).map(this::resolveCustom);
   }

   @Override
   public final SecondaryListener getSecondaryListener() {
      return this.secondaryListener;
   }

   public abstract Optional resolveOptional(long value3, long value4);

   @Override
   public synchronized TertiaryCustom resolveTertiaryCustom(long value3, long value4) {
      if (this.enabled) {
         throw new IllegalStateException("Path finder cannot be reused!");
      } else {
         this.enabled2 = false;

         TertiaryCustom tertiaryCustom;
         try {
            Custom custom = (Custom)this.resolveOptional(value3, value4).map(item -> ((Custom)item).getCustom()).orElse(null);
            if (this.enabled2) {
               return new TertiaryCustom(HeuristicValidator.CANCELLATION);
            }

            if (custom == null) {
               return new TertiaryCustom(HeuristicValidator.FAILURE);
            }

            int size = custom.size();
            Custom custom2 = custom.resolveCustom(this.itemStackEntry.heuristicManager);
            if (custom2.size() < size) {
               DRNHandler.dRNHandler.applyTextByText("Cutting off path at edge of loaded chunks");
               DRNHandler.dRNHandler.applyTextByText("Length decreased by " + (size - custom2.size()));
            } else {
               DRNHandler.dRNHandler.applyTextByText("Path ends within loaded chunks");
            }

            int size2 = custom2.size();
            Custom custom3 = custom2.resolveCustom(this.secondaryListener);
            if (custom3.size() < size2) {
               DRNHandler.dRNHandler.applyTextByText("Static cutoff " + size2 + " to " + custom3.size());
            }

            if (!this.secondaryListener.matchesBlockPos(custom3.getBetterBlockPosXSYSZSBlockPos2())) {
               return new TertiaryCustom(HeuristicValidator.SUCCESS_SEGMENT, custom3);
            }

            tertiaryCustom = new TertiaryCustom(HeuristicValidator.SUCCESS_TO_GOAL, custom3);
         } catch (Exception exception) {
            DRNHandler.dRNHandler.applyText("Pathing exception: " + exception);
            exception.printStackTrace();
            return new TertiaryCustom(HeuristicValidator.EXCEPTION);
         } finally {
            this.enabled = true;
         }

         return tertiaryCustom;
      }
   }

   public Optional resolveOptional(boolean flag, int count4) {
      if (this.aStarSampler == null) {
         return Optional.empty();
      } else {
         double offset = 0.0;

         for (int index = 0; index < valueArray.length; index++) {
            if (this.aStarSamplerArray[index] != null) {
               double offset2 = this.computeValue(this.aStarSamplerArray[index]);
               if (offset2 > offset) {
                  offset = offset2;
               }

               if (offset2 > 25.0) {
                  if (flag) {
                     if (valueArray[index] >= 3.0) {
                        System.out.println("Warning: cost coefficient is greater than three! Probably means that");
                        System.out.println("the path I found is pretty terrible (like sneak-bridging for dozens of blocks)");
                        System.out.println("But I'm going to do it anyway, because yolo");
                     }

                     System.out.println("Path goes for " + Math.sqrt(offset2) + " blocks");
                     this.applyTextByText("A* cost coefficient " + valueArray[index]);
                  }

                  return Optional.of(new DepthFirstScanner(this.aStarSampler, this.aStarSamplerArray[index], count4, this.secondaryListener, this.itemStackEntry));
               }
            }
         }

         if (flag) {
            this.applyTextByText(
               "Even with a cost coefficient of " + valueArray[valueArray.length - 1] + ", I couldn't get more than " + Math.sqrt(offset) + " blocks"
            );
            this.applyTextByText("No path found =(");
            this.applyText("No path found =(", true);
         }

         return Optional.empty();
      }
   }

   @Override
   public final boolean isEnabled() {
      return this.enabled;
   }

   public AStarSampler resolveAStarSampler(int count4, int count5, int count6, long value3) {
      AStarSampler aStarSampler3 = (AStarSampler)this.long2ObjectOpenHashMap.get(value3);
      if (aStarSampler3 == null) {
         aStarSampler3 = new AStarSampler(count4, count5, count6, this.secondaryListener);
         this.long2ObjectOpenHashMap.put(value3, aStarSampler3);
      }

      return aStarSampler3;
   }

   @Override
   public Optional getOptional() {
      return this.resolveOptional(false, 0);
   }

   public void updateState() {
      this.enabled2 = true;
   }

   private Custom resolveCustom(AStarSampler aStarSampler3) {
      return new DepthFirstScanner(this.aStarSampler, aStarSampler3, 0, this.secondaryListener, this.itemStackEntry);
   }

   public double computeValue(AStarSampler aStarSampler3) {
      int count4 = aStarSampler3.count - this.count;
      int count5 = aStarSampler3.count3 - this.count2;
      int count6 = aStarSampler3.count2 - this.count3;
      return (double)(count4 * count4 + count5 * count5 + count6 * count6);
   }

   public BetterBlockPosXSYSZSBlockPos getBetterBlockPosXSYSZSBlockPos() {
      return new BetterBlockPosXSYSZSBlockPos(this.count, this.count2, this.count3);
   }

   public int size() {
      return this.long2ObjectOpenHashMap.size();
   }

   public PathFinderCannotBeReusedManager(int count4, int count5, int count6, SecondaryListener secondaryListener2, ItemStackEntry itemStackEntry2) {
      this.aStarSamplerArray = new AStarSampler[valueArray.length];
      this.count = count4;
      this.count2 = count5;
      this.count3 = count6;
      this.secondaryListener = secondaryListener2;
      this.itemStackEntry = itemStackEntry2;
      this.long2ObjectOpenHashMap = new Long2ObjectOpenHashMap(
         (Integer)BaritoneManager.getSettings().pathingMapDefaultSize.dataRef, (Float)BaritoneManager.getSettings().pathingMapLoadFactor.dataRef
      );
   }
}
