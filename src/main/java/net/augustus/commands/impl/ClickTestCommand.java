package net.augustus.commands.impl;

import net.augustus.Augustus;
import net.augustus.commands.Command;
import net.augustus.config.ClipboardConfigCodec;
import net.augustus.modules.modes.clicking.AbstractClickPatternMode;
import net.augustus.modules.modes.clicking.DensityClickPatternMode;
import net.augustus.modules.modes.clicking.RecordedClickPatternMode;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.TuplePair;
import net.augustus.utils.math.DataPoint2D;
import net.augustus.utils.math.MathStatisticsUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import net.augustus.modules.legit.AutoClicker;
import net.augustus.settings.SettingGroup;

public class ClickTestCommand extends Command {

   private static double getPointX(DataPoint2D dataPoint2D) {
      return dataPoint2D.x;
   }

   public ClickTestCommand(long l) {
      super(".clicktest");
   }

   private double calculateCurveError(DataPoint2D[] dataPoint2Ds, ArrayList list) {
      double d = 0.0;

      for (int i = 0; i < list.size(); i++) {
         DataPoint2D dataPoint2D = (DataPoint2D)list.get(i);
         int n = Arrays.binarySearch(dataPoint2Ds, dataPoint2D, Comparator.comparingDouble(ClickTestCommand::getPointX));
         if (n < 0) {
            int n2 = -(n + 1);
            if (n2 > 0 && n2 < list.size()) {
               DataPoint2D dataPoint2D2 = dataPoint2Ds[n2 - 1];
               DataPoint2D dataPoint2D3 = dataPoint2Ds[n2];
               double d2 = (dataPoint2D3.y - dataPoint2D2.y) / (dataPoint2D3.x - dataPoint2D2.x);
               double d3 = dataPoint2D2.y - dataPoint2D2.x * d2;
               d += Math.abs(d2 * dataPoint2D.x + d3 - dataPoint2D.y);
            } else {
               d += Math.abs(dataPoint2D.y);
            }
         } else {
            d += Math.abs(dataPoint2D.y - dataPoint2Ds[n].y);
         }
      }

      return d;
   }

   private void randomizeDensityPattern(DensityClickPatternMode densityClickPatternMode) {
      densityClickPatternMode.recalculateSpikes();
   }

   public void commandAction(String[] args, String rawMessage) {
      AbstractClickPatternMode abstractSubMode = (AbstractClickPatternMode)Augustus.getInstance().getModuleManager().autoClicker.mode.getAbstractSubMode();
      if (abstractSubMode instanceof RecordedClickPatternMode) {
         Integer[] allDelays = ((RecordedClickPatternMode)abstractSubMode).getActivePattern().allDelays;
         ArrayList list = new ArrayList();
         ArrayList list2 = new ArrayList();

         for (int i = 0; i < allDelays.length - allDelays.length % 2; i += 2) {
            list.add(allDelays[i].doubleValue());
            list2.add(allDelays[i + 1].doubleValue());
         }

         short s = 400;
         DataPoint2D[] dataPoint2Ds = (DataPoint2D[])MathStatisticsUtil.computeKernelDensityEstimate(list, s).toArray(new DataPoint2D[0]);
         DataPoint2D[] dataPoint2Ds2 = (DataPoint2D[])MathStatisticsUtil.computeKernelDensityEstimate(list2, s).toArray(new DataPoint2D[0]);
         AutoClicker autoClicker = new AutoClicker(false);
         String str2 = "Density";
         DensityClickPatternMode subMode = (DensityClickPatternMode)autoClicker.mode.getSubMode(str2);
         TuplePair tuplePair = null;
         TuplePair tuplePair2 = null;

         for (int j = 0; j < 20000; j++) {
            this.randomizeDensityPattern(subMode);
            ArrayList list3 = new ArrayList();
            ArrayList list4 = new ArrayList();
            int n = Math.max(dataPoint2Ds.length, dataPoint2Ds2.length);

            for (int k = 0; k < n; k++) {
               TuplePair tuplePair3 = subMode.sampleUnpressedAndPressedDelay();
               list4.add((double)((Integer)tuplePair3.getFirst()).intValue());
               list3.add((double)((Integer)tuplePair3.getSecond()).intValue());
            }

            ArrayList list5 = MathStatisticsUtil.computeKernelDensityEstimate(list3, s);
            ArrayList list6 = MathStatisticsUtil.computeKernelDensityEstimate(list4, s);
            double d = this.calculateCurveError(dataPoint2Ds, list5);
            double d2 = this.calculateCurveError(dataPoint2Ds2, list6);
            if (Double.isFinite(d) && (tuplePair == null || (Double)tuplePair.getSecond() > d)) {
               SettingGroup pressedTimings = subMode.pressedTimings;
               tuplePair = new TuplePair(ClipboardConfigCodec.encodeSettingGroup(autoClicker, pressedTimings), d);
               System.out.println("Pressed: " + d);
            }

            if (Double.isFinite(d2) && (tuplePair2 == null || (Double)tuplePair2.getSecond() > d2)) {
               SettingGroup unpressedTimings = subMode.unpressedTimings;
               tuplePair2 = new TuplePair(ClipboardConfigCodec.encodeSettingGroup(autoClicker, unpressedTimings), d2);
               System.out.println("Unpressed: " + d2);
            }
         }

         ChatUtil.sendChat((Double)tuplePair.getSecond() / (double)s * 100.0 + " " + (Double)tuplePair2.getSecond() / (double)s * 100.0);
         AutoClicker autoClicker2 = Augustus.getInstance().getModuleManager().autoClicker;
         String str3 = "Density";
         DensityClickPatternMode subMode2 = (DensityClickPatternMode)autoClicker2.mode.getSubMode(str3);
         SettingGroup pressedTimings2 = subMode2.pressedTimings;
         ClipboardConfigCodec.importSettingGroup(autoClicker2, pressedTimings2, (String)tuplePair.getFirst());
         pressedTimings2 = subMode2.unpressedTimings;
         ClipboardConfigCodec.importSettingGroup(autoClicker2, pressedTimings2, (String)tuplePair2.getFirst());
         System.out.println("Original:");
         System.out.println("unten,oben");

         for (int m = 0; m < dataPoint2Ds.length; m++) {
            TuplePair tuplePair4 = new TuplePair(((Double)list.get(m)).intValue(), ((Double)list2.get(m)).intValue());
            System.out.println(tuplePair4.getFirst() + "," + tuplePair4.getSecond());
         }

         System.out.println("unten,oben");

         for (int idx = 0; idx < dataPoint2Ds.length; idx++) {
            TuplePair tuplePair5 = subMode2.sampleTotalAndPressedDelay();
            System.out.println((Integer)tuplePair5.getFirst() - (Integer)tuplePair5.getSecond() + "," + tuplePair5.getSecond());
         }
      } else {
         ChatUtil.sendChat("Recordedmode not selected in autoclicker");
      }
   }

   public void helpMessage() {
   }

}
