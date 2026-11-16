package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BaritoneManager;
import cc.baritone.utils.BetterBlockPosXSYSZSBlockPos;
import cc.baritone.utils.BreadthFirstOptimizer;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.HeuristicNavigator;
import cc.baritone.utils.InternalClientManager;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.io.FilenameUtils;

public class BuildCommand extends ExtendedItemEntry {
   public final File file;

   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      if (depthFirstManager.isActiveAsFlag()) {
         return BreadthFirstOptimizer.resolveStream(depthFirstManager, this.file);
      } else if (depthFirstManager.size(2)) {
         depthFirstManager.getBreadthFirstSchedulerAsBreadthFirstSche();
         return depthFirstManager.resolveStream(HeuristicNavigator.INSTANCE);
      } else {
         return Stream.empty();
      }
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "Build a schematic from a file.",
         "",
         "Usage:",
         "> build <filename> - Loads and builds '<filename>.schematic'",
         "> build <filename> <x> <y> <z> - Custom position"
      );
   }

   @Override
   public String getLabelText2() {
      return "Build a schematic";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      File absoluteFile = ((File)depthFirstManager.resolveTarget(BreadthFirstOptimizer.INSTANCE, this.file)).getAbsoluteFile();
      if (FilenameUtils.getExtension(absoluteFile.getAbsolutePath()).isEmpty()) {
         absoluteFile = new File(absoluteFile.getAbsolutePath() + "." + (String)BaritoneManager.getSettings().schematicFallbackExtension.dataRef);
      }

      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
      if (depthFirstManager.isActive()) {
         depthFirstManager.applyIndexByIndex(3);
         betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = (BetterBlockPosXSYSZSBlockPos)depthFirstManager.resolveTarget(HeuristicNavigator.INSTANCE, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS);
      } else {
         depthFirstManager.applyIndexByIndex(0);
      }

      if (!this.activeListener.getSchematicsHandler().matchesText(absoluteFile.getName(), absoluteFile, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS)) {
         throw new InternalClientManager("Couldn't load the schematic. Make sure to use the FULL file name, including the extension (e.g. blah.schematic).");
      } else {
         this.applyText(String.format("Successfully loaded schematic for building\nOrigin: %s", betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS));
      }
   }

   public BuildCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"build"});
      this.file = new File(activeListener.getStaticListener().getMc().mcDataDir, "schematics");
   }
}
