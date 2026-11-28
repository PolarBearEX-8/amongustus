package net.augustus.modules.modes.clicking;

import net.augustus.ClientManagers;
import net.augustus.clickgui.graph.GraphPointBuilder;
import net.augustus.events.EventClick;
import net.augustus.modules.combat.helper.ClickModuleContext;
import net.augustus.settings.ActionSetting;
import net.augustus.settings.GraphSetting;
import net.augustus.settings.SettingChangeListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.TextLabelSetting;
import net.augustus.settings.graph.GraphPoint;
import net.augustus.utils.MutableReference;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.utils.math.MathStatisticsUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;
import net.augustus.modules.Module;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class DistributionClickPatternMode extends AbstractClickPatternMode {
   private final StringValue distributionClickPattern;
   private final ClickPatternManager clickPatternManager;
   private final DoubleValue resolution;
   private final DoubleValue resatimport;
   private ClickPattern selectedImportPattern;
   private GraphSetting pressedDistributionGraph;
   private GraphSetting unpressedDistributionGraph;
   private BooleanValue preventTripleclicks;
   private GraphPointBuilder graphPointBuilder;
   private TextLabelSetting averageCps;
   private TextLabelSetting averageDcps;
   private DoubleValue buffersize;
   private TextLabelSetting averageFlawedKurtosis;
   private TextLabelSetting minFlawedKurtosis;
   private TextLabelSetting averageKurtosis;
   private TextLabelSetting minKurtosis;
   private TextLabelSetting worstCps;
   private TextLabelSetting probabilities;
   private long nextClickTimeMs;
   private long releaseTimeMs;
   private static final int MIN_GRAPH_MAX_Y = 10;
   private int lastClickTick;

   public DistributionClickPatternMode(Module module, SettingGroup doubleValue) {
       super(module, doubleValue, "Distribution");
      this.graphPointBuilder = new GraphPointBuilder();
      this.nextClickTimeMs = 0L;
      this.releaseTimeMs = 0L;
      this.graphPointBuilder.xEditable(false);
      this.graphPointBuilder.yEditable(true);
      this.graphPointBuilder.onChange(() -> this.resetAnalysisLabelsAndScale());
      this.graphPointBuilder.highlightZero(true);
      SettingGroup pressedGroup = new SettingGroup(module, "Pressed", doubleValue).visibleWhen(() -> this.isSelected());
      SettingGroup unpressedGroup = new SettingGroup(module, "Unpressed", doubleValue).visibleWhen(() -> this.isSelected());
      this.pressedDistributionGraph = new GraphSetting(module, "Pressedtime Distribution", new ArrayList(), 0.0, 200.0, 0.0, 10.0, pressedGroup, this.graphPointBuilder);
      this.unpressedDistributionGraph = new GraphSetting(module, "Unpressedtime Distribution", new ArrayList(), 0.0, 200.0, 0.0, 10.0, unpressedGroup, this.graphPointBuilder);
      this.addGraphShiftActions(module, pressedGroup, this.pressedDistributionGraph);
      this.addGraphShiftActions(module, unpressedGroup, this.unpressedDistributionGraph);
      this.preventTripleclicks = (BooleanValue)new BooleanValue(module, "Prevent tripleclicks", true, doubleValue).withVisibility(() -> this.isSelected());
      SettingGroup settingGroup = new SettingGroup(module, "Import", doubleValue).visibleWhen(() -> this.isSelected());
      SettingGroup analysisGroup = new SettingGroup(module, "Analysis", doubleValue).visibleWhen(() -> this.isSelected());
      new ActionSetting(module, "Analyze", () -> this.runDistributionAnalysis(), analysisGroup);
      this.averageCps = new TextLabelSetting(module, analysisGroup, "Average CPS: -");
      this.averageDcps = new TextLabelSetting(module, analysisGroup, "Average DCPS: -");
      this.buffersize = new DoubleValue(module, "Buffersize", 50.0, 4.0, 1000.0, 0, analysisGroup);
      this.averageFlawedKurtosis = new TextLabelSetting(module, analysisGroup, "Average flawed Kurtosis: -");
      this.minFlawedKurtosis = new TextLabelSetting(module, analysisGroup, "Min flawed Kurtosis: -");
      String str = "Frap moment";
      this.averageFlawedKurtosis.setDescription(str);
      String str2 = "Frap moment";
      this.minFlawedKurtosis.setDescription(str2);
      this.averageKurtosis = new TextLabelSetting(module, analysisGroup, "Average Kurtosis: -");
      this.minKurtosis = new TextLabelSetting(module, analysisGroup, "Min Kurtosis: -");
      this.worstCps = new TextLabelSetting(module, analysisGroup, "Worst CPS: -");
      this.probabilities = new TextLabelSetting(module, analysisGroup, "Probabilities: -");
      this.distributionClickPattern = (StringValue)new StringValue(module, "DistributionClickPattern", "", new String[0], settingGroup).withDisplayName("ClickPattern");
      this.clickPatternManager = ClickPatternManager.builder().withSelectorAndCallback(this.distributionClickPattern, obj -> this.selectedImportPattern = (ClickPattern)obj).build();
      this.resolution = new DoubleValue(module, "Resolution", 20.0, 1.0, 100.0, 0, settingGroup).visibleWhen(() -> this.isSelected());
      StringValue setting = this.distributionClickPattern;
      SettingChangeListener listener = () -> {
         this.selectedImportPattern = this.clickPatternManager.getPattern(this.distributionClickPattern.getSelectedValue());
      };
      setting.withChangeListener(listener);
      this.clickPatternManager.startWatchingAndLoad();
      this.resatimport = new DoubleValue(module, "resatimport", this.resolution.getValue(), this.resolution.getMinValue(), this.resolution.getMaxValue(), 0, doubleValue).visibleWhen(() -> false);
      new ActionSetting(module, "Import pattern", () -> this.importSelectedPattern(), settingGroup);
      this.initDefaultDistribution();
      this.resetAnalysisLabelsAndScale();
   }

   private void addGraphShiftActions(Module module, SettingGroup doubleValue, GraphSetting graphSetting) {
      new ActionSetting(module, "Shift left", () -> this.shiftGraphLeft(graphSetting), doubleValue);
      new ActionSetting(module, "Shift right", () -> this.shiftGraphRight(graphSetting), doubleValue);
   }

   private void shiftGraphPoints(GraphSetting graphSetting, int n) {
      if (n > 0) {
         for (int i = 0; i < graphSetting.getPoints().size(); i++) {
            GraphPoint graphPoint = (GraphPoint)graphSetting.getPoints().get(i);
            if (graphSetting.getPoints().size() > i + n) {
               graphPoint.setY(((GraphPoint)graphSetting.getPoints().get(i + n)).getY());
            } else {
               graphPoint.setY(0.0);
            }
         }
      } else {
         for (int j = graphSetting.getPoints().size() - 1; j >= 0; j--) {
            GraphPoint graphPoint2 = (GraphPoint)graphSetting.getPoints().get(j);
            if (j > 0) {
               graphPoint2.setY(((GraphPoint)graphSetting.getPoints().get(j + n)).getY());
            } else {
               graphPoint2.setY(0.0);
            }
         }
      }

      this.resetAnalysisLabelsAndScale();
   }

   @Override
   public int computeClicks(EventClick event) {
      int n = 0;
      long currentTimeMs = System.currentTimeMillis();
      if (this.lastClickTick != mc.thePlayer.ticksExisted - 1) {
         this.nextClickTimeMs = currentTimeMs - 50L;
         this.advanceClicksUntil(currentTimeMs - 50L);
      }

      n = this.advanceClicksUntil(currentTimeMs);
      if (n >= 3 && this.preventTripleclicks.getValue()) {
         n = 2;
         this.releaseTimeMs = currentTimeMs - 1L;
      }

      boolean flag = this.releaseTimeMs >= currentTimeMs;
      ((ClickModuleContext)this.parentModule).setHoldingAttack(flag);
      n = ((ClickModuleContext)this.parentModule).applyHitReduction(n);
      if (ClientManagers.moduleManager.smartClicking.isSuppressingClicks() || n != n) {
         n = 0;
         ((ClickModuleContext)this.parentModule).setHoldingAttack(false);
      }

      this.lastClickTick = mc.thePlayer.ticksExisted;
      return n;
   }

   private int advanceClicksUntil(long l) {
      int n = 0;

      while (l >= this.nextClickTimeMs) {
         n++;
         int n2 = Math.max(1, this.sampleDelayFromGraph(this.pressedDistributionGraph));
         int n3 = Math.max(1, this.sampleDelayFromGraph(this.unpressedDistributionGraph));
         this.releaseTimeMs = this.nextClickTimeMs + (long)n2;
         this.nextClickTimeMs += (long)(n2 + n3);
      }

      return n;
   }

   public int sampleDelayFromGraph(GraphSetting graphSetting) {
      List points = graphSetting.getPoints();
      double d = 0.0;

      for (int i = 0; i < points.size(); i++) {
         GraphPoint graphPoint = (GraphPoint)points.get(i);
         d += graphPoint.getY();
      }

      if (!points.isEmpty() && d != 0.0) {
         float f = 1.0F;
         float f2 = 0.0F;
         double d2 = (double)DefaultRandomGenerator.instance.nextFloat(f2, f) * d;
         int value = (int)this.resatimport.getValue();

         for (int j = 0; j < points.size(); j++) {
            GraphPoint graphPoint2 = (GraphPoint)points.get(j);
            d2 -= graphPoint2.getY();
            if (d2 <= 0.0) {
               float f3 = 1.0F;
               float f4 = 0.0F;
               return (int)((double)(DefaultRandomGenerator.instance.nextFloat(f4, f3) * 200.0F / (float)value) + graphPoint2.getX());
            }
         }

         float f5 = 1.0F;
         float f6 = 0.0F;
         return (int)((double)(DefaultRandomGenerator.instance.nextFloat(f6, f5) * (float)value) + ((GraphPoint)points.get(points.size() - 1)).getX());
      } else {
         return 50;
      }
   }

   @Override
   public void onTick() {
   }

   public void resetAnalysisLabelsAndScale() {
      String str = "Average CPS: -";
      this.averageCps.setText(str);
      String str2 = "Average DCPS: -";
      this.averageDcps.setText(str2);
      String str3 = "Average Kurtosis: -";
      this.averageKurtosis.setText(str3);
      String str4 = "Min Kurtosis: -";
      this.minKurtosis.setText(str4);
      String str5 = "Flawed average Kurtosis: -";
      this.averageFlawedKurtosis.setText(str5);
      String str6 = "Flawed min Kurtosis: -";
      this.minFlawedKurtosis.setText(str6);
      String str7 = "Worstcase CPS: -";
      this.worstCps.setText(str7);
      String str8 = "Probabilities: -";
      this.probabilities.setText(str8);
      int n = Math.max(10, Math.max(findMaxGraphY(this.pressedDistributionGraph), findMaxGraphY(this.unpressedDistributionGraph)) + 1);
      double d = (double)n;
      this.pressedDistributionGraph.setMaxY(d);
      double d2 = (double)n;
      this.unpressedDistributionGraph.setMaxY(d2);
   }

   private static int findMaxGraphY(GraphSetting graphSetting) {
      int minvalue = Integer.MIN_VALUE;

      for (int i = 0; i < graphSetting.getPoints().size(); i++) {
         minvalue = (int)Math.max((double)minvalue, ((GraphPoint)graphSetting.getPoints().get(i)).getY());
      }

      return minvalue;
   }

   public void runDistributionAnalysis() {
      long l = 0L;
      long l2 = 0L;
      int n = 0;
      int n2 = 0;
      int n3 = 0;
      int n4 = 0;
      ClickDelayMeanAccumulator accumulator = new ClickDelayMeanAccumulator();
      ClickDelayMeanAccumulator accumulator2 = new ClickDelayMeanAccumulator();
      MinKurtosisTracker minKurtosisTracker = new MinKurtosisTracker();
      MinKurtosisTracker minKurtosisTracker2 = new MinKurtosisTracker();
      DoubleSampleWindow doubleSampleWindow = new DoubleSampleWindow((int)this.buffersize.getValue());
      DoubleSampleWindow doubleSampleWindow2 = new DoubleSampleWindow(20);
      TreeMap treeMap = new TreeMap();
      boolean value = this.preventTripleclicks.getValue();

      for (int i = 0; i < 1000000; i++) {
         int n5 = 0;

         while (l >= l2) {
            n5++;
            int n6 = Math.max(1, this.sampleDelayFromGraph(this.pressedDistributionGraph));
            int n7 = Math.max(1, this.sampleDelayFromGraph(this.unpressedDistributionGraph));
            l2 += (long)(n6 + n7);
         }

         if (n5 >= 3 && value) {
            n5 = 2;
         }

         for (int j = 0; j < n5; j++) {
            doubleSampleWindow.add((double)(50 * n3));
            n3 = 0;
         }

         if (doubleSampleWindow.isFull()) {
            double d2 = MathStatisticsUtil.computeExcessKurtosis(doubleSampleWindow.getSamples());
            double d3 = MathStatisticsUtil.computeAlternateKurtosis(doubleSampleWindow.getSamples());
            accumulator.addSample(d2);
            minKurtosisTracker.recordSample(d2);
            accumulator2.addSample(d3);
            minKurtosisTracker2.recordSample(d3);
            doubleSampleWindow.clear();
         }

         l += 50L;
         if (n5 >= 2) {
            n++;
         }

         n2 += n5;
         n3++;
         doubleSampleWindow2.add((double)n5);
         if (doubleSampleWindow2.isFull()) {
            MutableReference mutableReference = (MutableReference)(treeMap.computeIfAbsent((int)doubleSampleWindow2.sum(), obj -> new MutableReference(0)));
            Integer num = (Integer)mutableReference.getValue() + 1;
            mutableReference.setValue(num);
            n4++;
         }
      }

      int n8 = 1;
      int n9 = 1;

      for (int k = 0; k < this.pressedDistributionGraph.getPoints().size(); k++) {
         GraphPoint graphPoint = (GraphPoint)this.pressedDistributionGraph.getPoints().get(k);
         if (graphPoint.getY() != 0.0) {
            n8 = Math.max((int)graphPoint.getX(), n8);
         }
      }

      for (int m = 0; m < this.unpressedDistributionGraph.getPoints().size(); m++) {
         GraphPoint graphPoint2 = (GraphPoint)this.unpressedDistributionGraph.getPoints().get(m);
         if (graphPoint2.getY() != 0.0) {
            n9 = Math.max((int)graphPoint2.getX(), n9);
         }
      }

      TextLabelSetting averageCps2 = this.averageCps;
      averageCps2.setText("Average CPS: " + MathStatisticsUtil.roundToDecimalPlaces((double)n2 / ((double)l2 / 1000.0), 2));
      averageCps2 = this.averageDcps;
      averageCps2.setText("Average DCPS: " + MathStatisticsUtil.roundToDecimalPlaces((double)n / ((double)l2 / 1000.0), 2));
      averageCps2 = this.averageKurtosis;
      averageCps2.setText("Average Kurtosis: " + MathStatisticsUtil.roundToDecimalPlaces(accumulator.getMean(), 2));
      averageCps2 = this.minKurtosis;
      averageCps2.setText("Min Kurtosis: " + MathStatisticsUtil.roundToDecimalPlaces(minKurtosisTracker.getMinValue(), 2));
      averageCps2 = this.averageFlawedKurtosis;
      averageCps2.setText("Flawed average Kurtosis: " + MathStatisticsUtil.roundToDecimalPlaces(accumulator2.getMean(), 2));
      averageCps2 = this.minFlawedKurtosis;
      averageCps2.setText("Flawed min Kurtosis: " + MathStatisticsUtil.roundToDecimalPlaces(minKurtosisTracker2.getMinValue(), 2));
      String str7 = "Worstcase CPS: " + 1000 / (n8 + n9);
      this.worstCps.setText(str7);
      StringBuilder sb = new StringBuilder("Probabilities (sampled):\n");

      for (int n10 : (Iterable<Integer>)(treeMap.navigableKeySet())) {
         sb.append(n10)
            .append(" CPS - ")
            .append(MathStatisticsUtil.roundToDecimalPlaces((double)((Integer)((MutableReference)treeMap.get(n10)).getValue()).intValue() / (double)n4 * 100.0, 5))
            .append("%\n");
      }

      averageCps2 = this.probabilities;
      averageCps2.setText(sb.toString());
   }

   public void importSelectedPattern() {
      if (this.selectedImportPattern != null) {
         List points = this.pressedDistributionGraph.getPoints();
         ArrayList pressedDelays = this.selectedImportPattern.pressedDelays;
         this.populateHistogramPoints(points, pressedDelays, (int)this.resolution.getValue());
         points = this.unpressedDistributionGraph.getPoints();
         pressedDelays = this.selectedImportPattern.unpressedDelays;
         this.populateHistogramPoints(points, pressedDelays, (int)this.resolution.getValue());
         this.resatimport.setValue(this.resolution.getValue());
         this.resetAnalysisLabelsAndScale();
      }
   }

   private void populateHistogramPoints(List list, List list2, int n) {
      list.clear();
      ArrayList list3 = new ArrayList();
      HashMap map = new HashMap();
      int n2 = 200 / n;

      for (int i = 0; i + n2 <= 200; i += n2) {
         ClickDelayHistogramBin clickDelayHistogramBin = new ClickDelayHistogramBin(i, i + n2 - 1);
         list3.add(clickDelayHistogramBin);

         for (int j = i; j < i + n2; j++) {
            map.put(j, clickDelayHistogramBin);
         }
      }

      int n3 = 0;

      for (int k = 0; k < list2.size(); k++) {
         int n4 = (Integer)list2.get(k);
         ClickDelayHistogramBin clickDelayHistogramBin2 = (ClickDelayHistogramBin)map.get(n4);
         if (clickDelayHistogramBin2 != null) {
            clickDelayHistogramBin2.setSampleCount(ClickDelayHistogramBin.accessSampleCount(clickDelayHistogramBin2) + 1);
            n3++;
         }
      }

      for (int m = 0; m < list3.size(); m++) {
         ClickDelayHistogramBin clickDelayHistogramBin3 = (ClickDelayHistogramBin)list3.get(m);
         list.add(this.graphPointBuilder.buildAt((double)(ClickDelayHistogramBin.accessMinDelay(clickDelayHistogramBin3) + ClickDelayHistogramBin.accessMaxDelay(clickDelayHistogramBin3)) / 2.0, (double)ClickDelayHistogramBin.accessSampleCount(clickDelayHistogramBin3) * 100.0 / (double)n3));
      }
   }

   private void initDefaultDistribution() {
      this.importSelectedPattern();
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public boolean simulatesMousePress() {
      return true;
   }

   private void shiftGraphRight(GraphSetting graphSetting) {
      this.shiftGraphPoints(graphSetting, (byte)-1);
   }

   private void shiftGraphLeft(GraphSetting graphSetting) {
      this.shiftGraphPoints(graphSetting, (byte)1);
   }

}
