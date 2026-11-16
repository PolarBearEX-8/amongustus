package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.AStarSelector;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BetterBlockPosXSYSZSBlockPos;
import cc.baritone.utils.BreadthFirstManager;
import cc.baritone.utils.BreadthFirstMonitor;
import cc.baritone.utils.DepthFirstEstimator;
import cc.baritone.utils.DepthFirstHandler;
import cc.baritone.utils.DepthFirstInspector;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.DepthFirstSystem;
import cc.baritone.utils.DepthFirstVerifier;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.HeuristicCalibrator;
import cc.baritone.utils.HeuristicEstimator;
import cc.baritone.utils.HeuristicManager;
import cc.baritone.utils.HeuristicMonitor;
import cc.baritone.utils.HeuristicNavigator;
import cc.baritone.utils.HeuristicResolver;
import cc.baritone.utils.HeuristicScanner;
import cc.baritone.utils.HeuristicSystem;
import cc.baritone.utils.HeuristicVerifier;
import cc.baritone.utils.InternalClientManager;
import cc.baritone.utils.InternalListener;
import cc.baritone.utils.Listener;
import cc.baritone.utils.ManagedListener;
import cc.baritone.utils.NativeCustom;
import cc.baritone.utils.PassiveCustom;
import cc.baritone.utils.PrimaryCustom;
import cc.baritone.utils.StandardCustom;
import cc.baritone.utils.TertiaryClientManager;
import client.setting.EnumFacingXType;
import client.util.IBlockState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.block.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;

public class SelectionCommand extends ExtendedItemEntry {
   public Listener listener = this.activeListener.getListener();
   public BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = null;
   public InternalListener internalListener = null;
   public Vec3i vec3i = null;

   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      if (depthFirstManager.isActiveAsFlag()) {
         return new HeuristicEstimator()
            .resolveHeuristicEstimatorByTextArray(DepthFirstInspector.getTextArray())
            .resolveHeuristicEstimatorByText(depthFirstManager.getText())
            .getHeuristicEstimator2()
            .getStream();
      } else {
         DepthFirstInspector depthFirstInspector = DepthFirstInspector.resolveDepthFirstInspector(depthFirstManager.getText());
         if (depthFirstInspector != null) {
            if (depthFirstInspector != DepthFirstInspector.POS1 && depthFirstInspector != DepthFirstInspector.POS2) {
               if (depthFirstInspector.isActive()) {
                  if (depthFirstManager.isActiveAsFlag() || depthFirstInspector == DepthFirstInspector.REPLACE) {
                     while (depthFirstManager.size(2)) {
                        depthFirstManager.getBreadthFirstSchedulerAsBreadthFirstSche();
                     }

                     return depthFirstManager.resolveStream(AStarSelector.INSTANCE);
                  }

                  if (depthFirstManager.size2(2) && (depthFirstInspector == DepthFirstInspector.CYLINDER || depthFirstInspector == DepthFirstInspector.HCYLINDER)) {
                     depthFirstManager.getBreadthFirstSchedulerAsBreadthFirstSche();
                     return depthFirstManager.resolveStream(DepthFirstVerifier.INSTANCE);
                  }
               } else if (depthFirstInspector == DepthFirstInspector.EXPAND || depthFirstInspector == DepthFirstInspector.CONTRACT || depthFirstInspector == DepthFirstInspector.SHIFT) {
                  if (depthFirstManager.isActiveAsFlag()) {
                     return new HeuristicEstimator()
                        .resolveHeuristicEstimatorByTextArray(HeuristicMonitor.getTextArray())
                        .resolveHeuristicEstimatorByText(depthFirstManager.getText())
                        .getHeuristicEstimator2()
                        .getStream();
                  }

                  if (HeuristicMonitor.resolveHeuristicMonitor(depthFirstManager.getText()) != null && depthFirstManager.isActiveAsFlag()) {
                     return depthFirstManager.resolveStream(NativeCustom.INSTANCE);
                  }
               }
            } else if (depthFirstManager.sizeByIndex(3)) {
               return depthFirstManager.resolveStream(HeuristicNavigator.INSTANCE);
            }
         }

         return Stream.empty();
      }
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The sel command allows you to manipulate Baritone's selections, similarly to WorldEdit.",
         "",
         "Using these selections, you can clear areas, fill them with blocks, or something else.",
         "",
         "The expand/contract/shift commands use a kind of selector to choose which selections to target. Supported ones are a/all, n/newest, and o/oldest.",
         "",
         "Usage:",
         "> sel pos1/p1/1 - Set position 1 to your current position.",
         "> sel pos1/p1/1 <x> <y> <z> - Set position 1 to a relative position.",
         "> sel pos2/p2/2 - Set position 2 to your current position.",
         "> sel pos2/p2/2 <x> <y> <z> - Set position 2 to a relative position.",
         "",
         "> sel clear/c - Clear the selection.",
         "> sel undo/u - Undo the last action (setting positions, creating selections, etc.)",
         "> sel set/fill/s/f [block] - Completely fill all selections with a block.",
         "> sel walls/w [block] - Fill in the walls of the selection with a specified block.",
         "> sel shell/shl [block] - The same as walls, but fills in a ceiling and floor too.",
         "> sel sphere/sph [block] - Fills the selection with a sphere bounded by the sides.",
         "> sel hsphere/hsph [block] - The same as sphere, but hollow.",
         "> sel cylinder/cyl [block] <axis> - Fills the selection with a cylinder bounded by the sides, oriented about the given axis. (default=y)",
         "> sel hcylinder/hcyl [block] <axis> - The same as cylinder, but hollow.",
         "> sel cleararea/ca - Basically 'set air'.",
         "> sel replace/r <blocks...> <with> - Replaces blocks with another block.",
         "> sel copy/cp <x> <y> <z> - Copy the selected area relative to the specified or your position.",
         "> sel paste/p <x> <y> <z> - Build the copied area relative to the specified or your position.",
         "",
         "> sel expand <target> <direction> <blocks> - Expand the targets.",
         "> sel contract <target> <direction> <blocks> - Contract the targets.",
         "> sel shift <target> <direction> <blocks> - Shift the targets (does not resize)."
      );
   }

   @Override
   public String getLabelText2() {
      return "WorldEdit-like commands";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      DepthFirstInspector depthFirstInspector = DepthFirstInspector.resolveDepthFirstInspector(depthFirstManager.getText());
      if (depthFirstInspector == null) {
         throw new TertiaryClientManager(depthFirstManager.getBreadthFirstScheduler(), "an action");
      } else {
         if (depthFirstInspector != DepthFirstInspector.POS1 && depthFirstInspector != DepthFirstInspector.POS2) {
            if (depthFirstInspector == DepthFirstInspector.CLEAR) {
               depthFirstManager.applyIndexByIndex(0);
               this.betterBlockPosXSYSZSBlockPos = null;
               this.applyText(String.format("Removed %d selections", this.listener.getManagedListenerArray().length));
            } else if (depthFirstInspector == DepthFirstInspector.UNDO) {
               depthFirstManager.applyIndexByIndex(0);
               if (this.betterBlockPosXSYSZSBlockPos != null) {
                  this.betterBlockPosXSYSZSBlockPos = null;
                  this.applyText("Undid pos1");
               } else {
                  ManagedListener[] managedListenerArrayAsC44OArray = this.listener.getManagedListenerArrayAsC44OArray();
                  if (managedListenerArrayAsC44OArray.length < 1) {
                     throw new InternalClientManager("Nothing to undo!");
                  }

                  this.betterBlockPosXSYSZSBlockPos = this.listener
                     .resolveManagedListenerByManagedListener(managedListenerArrayAsC44OArray[managedListenerArrayAsC44OArray.length - 1])
                     .getBetterBlockPosXSYSZSBlockPos();
                  this.applyText("Undid pos2");
               }
            } else if (depthFirstInspector.isActive()) {
               PrimaryCustom primaryCustom = depthFirstInspector == DepthFirstInspector.CLEARAREA
                  ? new PrimaryCustom(Blocks.air)
                  : (PrimaryCustom)depthFirstManager.resolveTarget2(AStarSelector.INSTANCE);
               EnumFacingXType enumFacingXType;
               HeuristicSystem heuristicSystem;
               if (depthFirstInspector != DepthFirstInspector.REPLACE) {
                  if (depthFirstInspector != DepthFirstInspector.CYLINDER && depthFirstInspector != DepthFirstInspector.HCYLINDER) {
                     depthFirstManager.applyIndexByIndex(0);
                     heuristicSystem = null;
                     enumFacingXType = null;
                  } else {
                     depthFirstManager.applyIndexByIndex(1);
                     enumFacingXType = depthFirstManager.isActive() ? (EnumFacingXType)depthFirstManager.resolveTarget2(DepthFirstVerifier.INSTANCE) : EnumFacingXType.Y;
                     heuristicSystem = null;
                  }
               } else {
                  depthFirstManager.applyIndex2(1);
                  ArrayList list = new ArrayList();
                  list.add(primaryCustom);

                  while (depthFirstManager.size(2)) {
                     list.add(depthFirstManager.resolveTarget2(AStarSelector.INSTANCE));
                  }

                  primaryCustom = (PrimaryCustom)depthFirstManager.resolveTarget2(AStarSelector.INSTANCE);
                  heuristicSystem = new HeuristicSystem((PrimaryCustom[])list.toArray(new PrimaryCustom[0]));
                  enumFacingXType = null;
               }

               ManagedListener[] managedListenerArrayAsC44OArray2 = this.listener.getManagedListenerArrayAsC44OArray();
               if (managedListenerArrayAsC44OArray2.length == 0) {
                  throw new InternalClientManager("No selections");
               }

               BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = managedListenerArrayAsC44OArray2[0].getBetterBlockPosXSYSZSBlockPos3();
               HeuristicVerifier heuristicVerifier = new HeuristicVerifier(0, 0, 0);
               ManagedListener[] managedListenerArray = managedListenerArrayAsC44OArray2;
               int length = managedListenerArrayAsC44OArray2.length;

               for (int index = 0; index < length; index++) {
                  BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos3 = managedListenerArray[index].getBetterBlockPosXSYSZSBlockPos3();
                  betterBlockPosXSYSZSBlockPos2 = new BetterBlockPosXSYSZSBlockPos(
                     Math.min(betterBlockPosXSYSZSBlockPos2.count9, betterBlockPosXSYSZSBlockPos3.count9), Math.min(betterBlockPosXSYSZSBlockPos2.count12, betterBlockPosXSYSZSBlockPos3.count12), Math.min(betterBlockPosXSYSZSBlockPos2.count10, betterBlockPosXSYSZSBlockPos3.count10)
                  );
               }

               for (ManagedListener managedListener : managedListenerArrayAsC44OArray2) {
                  Vec3i vec3i2 = managedListener.getVec3i();
                  BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos4 = managedListener.getBetterBlockPosXSYSZSBlockPos3();
                  InternalListener internalListener2 = SelectionCommand.resolveInternalListener(
                     depthFirstInspector, heuristicSystem, enumFacingXType, new HeuristicScanner(vec3i2.getX(), vec3i2.getY(), vec3i2.getZ(), primaryCustom)
                  );
                  heuristicVerifier.applyInternalListener(internalListener2, betterBlockPosXSYSZSBlockPos4.count9 - betterBlockPosXSYSZSBlockPos2.count9, betterBlockPosXSYSZSBlockPos4.count12 - betterBlockPosXSYSZSBlockPos2.count12, betterBlockPosXSYSZSBlockPos4.count10 - betterBlockPosXSYSZSBlockPos2.count10);
               }

               this.activeListener.getSchematicsHandler().applyText("Fill", heuristicVerifier, betterBlockPosXSYSZSBlockPos2);
               this.applyText("Filling now");
            } else if (depthFirstInspector == DepthFirstInspector.COPY) {
               BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos5 = this.staticListener.getBetterBlockPosXSYSZSBlockPos();
               BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos6 = depthFirstManager.isActive()
                  ? (BetterBlockPosXSYSZSBlockPos)depthFirstManager.resolveTarget(HeuristicNavigator.INSTANCE, betterBlockPosXSYSZSBlockPos5)
                  : betterBlockPosXSYSZSBlockPos5;
               depthFirstManager.applyIndexByIndex(0);
               ManagedListener[] managedListenerArrayAsC44OArray3 = this.listener.getManagedListenerArrayAsC44OArray();
               if (managedListenerArrayAsC44OArray3.length < 1) {
                  throw new InternalClientManager("No selections");
               }

               HeuristicManager heuristicManager = new HeuristicManager(this.staticListener);
               BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos7 = managedListenerArrayAsC44OArray3[0].getBetterBlockPosXSYSZSBlockPos3();
               HeuristicVerifier heuristicVerifier2 = new HeuristicVerifier(0, 0, 0);
               ManagedListener[] managedListenerArray2 = managedListenerArrayAsC44OArray3;
               int length2 = managedListenerArrayAsC44OArray3.length;

               for (int index2 = 0; index2 < length2; index2++) {
                  BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos8 = managedListenerArray2[index2].getBetterBlockPosXSYSZSBlockPos3();
                  betterBlockPosXSYSZSBlockPos7 = new BetterBlockPosXSYSZSBlockPos(
                     Math.min(betterBlockPosXSYSZSBlockPos7.count9, betterBlockPosXSYSZSBlockPos8.count9), Math.min(betterBlockPosXSYSZSBlockPos7.count12, betterBlockPosXSYSZSBlockPos8.count12), Math.min(betterBlockPosXSYSZSBlockPos7.count10, betterBlockPosXSYSZSBlockPos8.count10)
                  );
               }

               for (ManagedListener managedListener2 : managedListenerArrayAsC44OArray3) {
                  Vec3i vec3i3 = managedListener2.getVec3i();
                  BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos9 = managedListener2.getBetterBlockPosXSYSZSBlockPos3();
                  IBlockState[][][] blockStateMatrix = new IBlockState[vec3i3.getX()][vec3i3.getZ()][vec3i3.getY()];

                  for (int index3 = 0; index3 < vec3i3.getX(); index3++) {
                     for (int index4 = 0; index4 < vec3i3.getY(); index4++) {
                        for (int index5 = 0; index5 < vec3i3.getZ(); index5++) {
                           blockStateMatrix[index3][index5][index4] = heuristicManager.resolveIBlockState(betterBlockPosXSYSZSBlockPos9.count9 + index3, betterBlockPosXSYSZSBlockPos9.count12 + index4, betterBlockPosXSYSZSBlockPos9.count10 + index5);
                        }
                     }
                  }

                  BreadthFirstMonitor breadthFirstMonitor = new BreadthFirstMonitor(this, blockStateMatrix, vec3i3);
                  heuristicVerifier2.applyInternalListener(breadthFirstMonitor, betterBlockPosXSYSZSBlockPos9.count9 - betterBlockPosXSYSZSBlockPos7.count9, betterBlockPosXSYSZSBlockPos9.count12 - betterBlockPosXSYSZSBlockPos7.count12, betterBlockPosXSYSZSBlockPos9.count10 - betterBlockPosXSYSZSBlockPos7.count10);
               }

               this.internalListener = heuristicVerifier2;
               this.vec3i = betterBlockPosXSYSZSBlockPos7.getBlockPos(betterBlockPosXSYSZSBlockPos6);
               this.applyText("Selection copied");
            } else if (depthFirstInspector == DepthFirstInspector.PASTE) {
               BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos10 = this.staticListener.getBetterBlockPosXSYSZSBlockPos();
               BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos11 = depthFirstManager.isActive()
                  ? (BetterBlockPosXSYSZSBlockPos)depthFirstManager.resolveTarget(HeuristicNavigator.INSTANCE, betterBlockPosXSYSZSBlockPos10)
                  : betterBlockPosXSYSZSBlockPos10;
               depthFirstManager.applyIndexByIndex(0);
               if (this.internalListener == null) {
                  throw new InternalClientManager("You need to copy a selection first");
               }

               this.activeListener.getSchematicsHandler().applyText("Fill", this.internalListener, betterBlockPosXSYSZSBlockPos11.getBlockPos2(this.vec3i));
               this.applyText("Building now");
            } else if (depthFirstInspector == DepthFirstInspector.EXPAND || depthFirstInspector == DepthFirstInspector.CONTRACT || depthFirstInspector == DepthFirstInspector.SHIFT) {
               depthFirstManager.applyIndex(3);
               HeuristicMonitor heuristicMonitor = HeuristicMonitor.resolveHeuristicMonitor(depthFirstManager.getText());
               if (heuristicMonitor == null) {
                  throw new InternalClientManager("Invalid transform type");
               }

               EnumFacing enumFacing = (EnumFacing)depthFirstManager.resolveTarget2(NativeCustom.INSTANCE);
               int count = (Integer)depthFirstManager.resolveTarget3(Integer.class);
               ManagedListener[] managedListenerArrayAsC44OArray4 = this.listener.getManagedListenerArrayAsC44OArray();
               if (managedListenerArrayAsC44OArray4.length < 1) {
                  throw new InternalClientManager("No selections found");
               }

               ManagedListener[] managedListenerArray3 = heuristicMonitor.apply(managedListenerArrayAsC44OArray4);

               for (ManagedListener managedListener3 : managedListenerArray3) {
                  if (depthFirstInspector == DepthFirstInspector.EXPAND) {
                     this.listener.resolveManagedListenerByManagedListener(managedListener3, enumFacing, count);
                  } else if (depthFirstInspector == DepthFirstInspector.CONTRACT) {
                     this.listener.resolveManagedListener(managedListener3, enumFacing, count);
                  } else {
                     this.listener.resolveManagedListener2(managedListener3, enumFacing, count);
                  }
               }

               this.applyText(String.format("Transformed %d selections", managedListenerArray3.length));
            }
         } else {
            if (depthFirstInspector == DepthFirstInspector.POS2 && this.betterBlockPosXSYSZSBlockPos == null) {
               throw new InternalClientManager("Set pos1 first before using pos2");
            }

            BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos12 = this.staticListener.getBetterBlockPosXSYSZSBlockPos();
            BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos13 = depthFirstManager.isActive() ? (BetterBlockPosXSYSZSBlockPos)depthFirstManager.resolveTarget(HeuristicNavigator.INSTANCE, betterBlockPosXSYSZSBlockPos12) : betterBlockPosXSYSZSBlockPos12;
            depthFirstManager.applyIndexByIndex(0);
            if (depthFirstInspector == DepthFirstInspector.POS1) {
               this.betterBlockPosXSYSZSBlockPos = betterBlockPosXSYSZSBlockPos13;
               this.applyText("Position 1 has been set");
            } else {
               this.listener.resolveManagedListener(this.betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos13);
               this.betterBlockPosXSYSZSBlockPos = null;
               this.applyText("Selection added");
            }
         }
      }
   }

   private static InternalListener resolveInternalListener(DepthFirstInspector depthFirstInspector, HeuristicSystem heuristicSystem, EnumFacingXType enumFacingXType, InternalListener internalListener2) {
      int count = internalListener2.getCount();
      int count2 = internalListener2.getCount2();
      int count3 = internalListener2.getCount3();
      switch (StandardCustom.countArray[depthFirstInspector.ordinal()]) {
         case 1:
            return new BreadthFirstManager(internalListener2);
         case 2:
            return new HeuristicCalibrator(internalListener2);
         case 3:
            return new PassiveCustom(internalListener2, heuristicSystem);
         case 4:
            return HeuristicResolver.resolveHeuristicResolver(internalListener2, new DepthFirstEstimator(count, count2, count3, true).getBreadthFirstEngine());
         case 5:
            return HeuristicResolver.resolveHeuristicResolver(internalListener2, new DepthFirstEstimator(count, count2, count3, false).getBreadthFirstEngine());
         case 6:
            return HeuristicResolver.resolveHeuristicResolver(internalListener2, new DepthFirstHandler(count, count2, count3, true, enumFacingXType).getBreadthFirstEngine());
         case 7:
            return HeuristicResolver.resolveHeuristicResolver(internalListener2, new DepthFirstHandler(count, count2, count3, false, enumFacingXType).getBreadthFirstEngine());
         default:
            return internalListener2;
      }
   }

   public static BetterBlockPosXSYSZSBlockPos resolveBetterBlockPosXSYSZSBlockPos(SelectionCommand selectionCommand) {
      return selectionCommand.betterBlockPosXSYSZSBlockPos;
   }

   public SelectionCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"sel", "selection", "s"});
      activeListener.getCompositeListener().add(new DepthFirstSystem(this));
   }
}
