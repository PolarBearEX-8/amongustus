package cc.baritone.utils;

import client.event.PreUpdateDispatcher;
import client.util.SplineCalculator;
import java.util.List;
import net.minecraft.client.Minecraft;

public class HeuristicRegistrySplineCalculator extends SplineCalculator {
   public List list;
   public final HeuristicRegistry heuristicRegistry;

   public static Minecraft resolveMinecraft(HeuristicRegistrySplineCalculator heuristicRegistrySplineCalculator) {
      return heuristicRegistrySplineCalculator.mc;
   }

   @Override
   public int size() {
      return this.list.size();
   }

   public static Minecraft resolveMinecraft2(HeuristicRegistrySplineCalculator heuristicRegistrySplineCalculator) {
      return heuristicRegistrySplineCalculator.mc;
   }

   @Override
   public PreUpdateDispatcher get(int count) {
      return (PreUpdateDispatcher)this.list.get(count);
   }

   public static Minecraft resolveMinecraftByHeuristicRegistr(HeuristicRegistrySplineCalculator heuristicRegistrySplineCalculator) {
      return heuristicRegistrySplineCalculator.mc;
   }

   public HeuristicRegistrySplineCalculator(HeuristicRegistry heuristicRegistry2) {
      super(HeuristicRegistry.resolveMinecraft7(heuristicRegistry2), heuristicRegistry2.width, heuristicRegistry2.height, 39, heuristicRegistry2.height - 32, 20);
      this.heuristicRegistry = heuristicRegistry2;
      this.list = new WorldOverridesGeneratorOverrArrayList(this);
   }

   public static Minecraft resolveMinecraft3(HeuristicRegistrySplineCalculator heuristicRegistrySplineCalculator) {
      return heuristicRegistrySplineCalculator.mc;
   }
}
