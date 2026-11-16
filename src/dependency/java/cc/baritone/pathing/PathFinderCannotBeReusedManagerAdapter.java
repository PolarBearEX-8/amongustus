package cc.baritone.pathing;

import cc.baritone.api.ItemStackEntry;
import cc.baritone.utils.AStarRecorder;
import cc.baritone.utils.AStarSampler;
import cc.baritone.utils.AuxiliaryCustom;
import cc.baritone.utils.BaritoneManager;
import cc.baritone.utils.BetterBlockPosXSYSZSBlockPos;
import cc.baritone.utils.DepthFirstScanner;
import cc.baritone.utils.DirectCustom;
import cc.baritone.utils.HeuristicCalculator;
import cc.baritone.utils.HeuristicPredictor;
import cc.baritone.utils.SecondaryListener;
import java.util.Optional;

public final class PathFinderCannotBeReusedManagerAdapter extends PathFinderCannotBeReusedManager {
   public final AuxiliaryCustom auxiliaryCustom;
   public final ItemStackEntry itemStackEntry2;

   @Override
   public Optional resolveOptional(long value, long value2) {
      this.aStarSampler = this.resolveAStarSampler(
         this.count, this.count2, this.count3, BetterBlockPosXSYSZSBlockPos.computeTimestampByIndex(this.count, this.count2, this.count3)
      );
      this.aStarSampler.value2 = 0.0;
      this.aStarSampler.value3 = this.aStarSampler.value;
      AStarRecorder aStarRecorder = new AStarRecorder();
      aStarRecorder.applyAStarSamplerByAStarSampler(this.aStarSampler);
      double[] offsetArray = new double[valueArray.length];

      for (int index = 0; index < offsetArray.length; index++) {
         offsetArray[index] = this.aStarSampler.value;
         this.aStarSamplerArray[index] = this.aStarSampler;
      }

      HeuristicCalculator heuristicCalculator = new HeuristicCalculator();
      DirectCustom directCustom = new DirectCustom(this.itemStackEntry2.world.getInternalListEntry());
      long timestamp = System.currentTimeMillis();
      boolean dataRef = (Boolean)BaritoneManager.getSettings().slowPath.dataRef;
      if (dataRef) {
         this.applyTextByText(
            "slowPath is on, path timeout will be " + BaritoneManager.getSettings().slowPathTimeoutMS.dataRef + "ms instead of " + value + "ms"
         );
      }

      long value3 = timestamp + (dataRef ? (Long)BaritoneManager.getSettings().slowPathTimeoutMS.dataRef : value);
      long value4 = timestamp + (dataRef ? (Long)BaritoneManager.getSettings().slowPathTimeoutMS.dataRef : value2);
      boolean flag = true;
      int count = 0;
      int count2 = 0;
      int count3 = 0;
      boolean flag2 = !this.auxiliaryCustom.isEmpty();
      int dataRef2 = (Integer)BaritoneManager.getSettings().pathingMaxChunkBorderFetch.dataRef;
      double offset = (Boolean)BaritoneManager.getSettings().minimumImprovementRepropagation.dataRef ? 0.01 : 0.0;
      HeuristicPredictor[] values = HeuristicPredictor.values();

      while (!aStarRecorder.isActive() && count3 < dataRef2 && !this.enabled2) {
         if ((count & 63) == 0) {
            long timestamp2 = System.currentTimeMillis();
            if (timestamp2 - value4 >= 0L || !flag && timestamp2 - value3 >= 0L) {
               break;
            }
         }

         if (dataRef) {
            try {
               Thread.sleep((Long)BaritoneManager.getSettings().slowPathTimeDelayMS.dataRef);
            } catch (InterruptedException interruptedException) {
            }
         }

         AStarSampler aStarSampler = aStarRecorder.getAStarSampler();
         this.aStarSampler2 = aStarSampler;
         count++;
         if (this.secondaryListener.checkIndex(aStarSampler.count, aStarSampler.count3, aStarSampler.count2)) {
            this.applyTextByText("Took " + (System.currentTimeMillis() - timestamp) + "ms, " + count2 + " movements considered");
            return Optional.of(new DepthFirstScanner(this.aStarSampler, aStarSampler, count, this.secondaryListener, this.itemStackEntry2));
         }

         for (HeuristicPredictor heuristicPredictor : values) {
            int count4 = aStarSampler.count + heuristicPredictor.xOffset;
            int count5 = aStarSampler.count2 + heuristicPredictor.zOffset;
            if ((count4 >> 4 != aStarSampler.count >> 4 || count5 >> 4 != aStarSampler.count2 >> 4) && !this.itemStackEntry2.checkIndex(count4, count5)) {
               if (!heuristicPredictor.dynamicXZ) {
                  count3++;
               }
            } else if ((heuristicPredictor.dynamicXZ || directCustom.checkIndexByIndex(count4, count5)) && aStarSampler.count3 + heuristicPredictor.yOffset <= 256 && aStarSampler.count3 + heuristicPredictor.yOffset >= 0) {
               heuristicCalculator.updateState();
               heuristicPredictor.applyItemStackEntry(this.itemStackEntry2, aStarSampler.count, aStarSampler.count3, aStarSampler.count2, heuristicCalculator);
               count2++;
               double value5 = heuristicCalculator.value;
               if (!(value5 >= 1000000.0)) {
                  if (value5 <= 0.0 || Double.isNaN(value5)) {
                     throw new IllegalStateException(heuristicPredictor + " calculated implausible cost " + value5);
                  }

                  if (!heuristicPredictor.dynamicXZ || directCustom.checkIndexByIndex(heuristicCalculator.count3, heuristicCalculator.count)) {
                     if (!heuristicPredictor.dynamicXZ && (heuristicCalculator.count3 != count4 || heuristicCalculator.count != count5)) {
                        throw new IllegalStateException(heuristicPredictor + " " + heuristicCalculator.count3 + " " + count4 + " " + heuristicCalculator.count + " " + count5);
                     }

                     if (!heuristicPredictor.dynamicY && heuristicCalculator.count2 != aStarSampler.count3 + heuristicPredictor.yOffset) {
                        throw new IllegalStateException(heuristicPredictor + " " + heuristicCalculator.count2 + " " + (aStarSampler.count3 + heuristicPredictor.yOffset));
                     }

                     long value6 = BetterBlockPosXSYSZSBlockPos.computeTimestampByIndex(heuristicCalculator.count3, heuristicCalculator.count2, heuristicCalculator.count);
                     if (flag2) {
                        value5 *= this.auxiliaryCustom.get(value6);
                     }

                     AStarSampler aStarSampler2 = this.resolveAStarSampler(heuristicCalculator.count3, heuristicCalculator.count2, heuristicCalculator.count, value6);
                     double offset2 = aStarSampler.value2 + value5;
                     if (aStarSampler2.value2 - offset2 > offset) {
                        aStarSampler2.aStarSampler = aStarSampler;
                        aStarSampler2.value2 = offset2;
                        aStarSampler2.value3 = offset2 + aStarSampler2.value;
                        if (aStarSampler2.isActive()) {
                           aStarRecorder.applyAStarSampler(aStarSampler2);
                        } else {
                           aStarRecorder.applyAStarSamplerByAStarSampler(aStarSampler2);
                        }

                        for (int index2 = 0; index2 < valueArray.length; index2++) {
                           double offset3 = aStarSampler2.value + aStarSampler2.value2 / valueArray[index2];
                           if (offsetArray[index2] - offset3 > offset) {
                              offsetArray[index2] = offset3;
                              this.aStarSamplerArray[index2] = aStarSampler2;
                              if (flag && this.computeValue(aStarSampler2) > 25.0) {
                                 flag = false;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      if (this.enabled2) {
         return Optional.empty();
      } else {
         System.out.println(count2 + " movements considered");
         System.out.println("Open set size: " + aStarRecorder.getCount2());
         System.out.println("PathNode map size: " + this.size());
         System.out.println((int)((double)count * 1.0 / (double)((float)(System.currentTimeMillis() - timestamp) / 1000.0F)) + " nodes per second");
         Optional optional = this.resolveOptional(true, count);
         if (optional.isPresent()) {
            this.applyTextByText("Took " + (System.currentTimeMillis() - timestamp) + "ms, " + count2 + " movements considered");
         }

         return optional;
      }
   }

   public PathFinderCannotBeReusedManagerAdapter(int count, int count2, int count3, SecondaryListener secondaryListener, AuxiliaryCustom auxiliaryCustom2, ItemStackEntry itemStackEntry) {
      super(count, count2, count3, secondaryListener, itemStackEntry);
      this.auxiliaryCustom = auxiliaryCustom2;
      this.itemStackEntry2 = itemStackEntry;
   }
}
