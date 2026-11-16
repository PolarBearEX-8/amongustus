package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BreadthFirstOptimizer;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.InternalClientManager;
import cc.baritone.utils.TertiaryClientManager;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ExplorefilterCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      return depthFirstManager.isActiveAsFlag()
         ? BreadthFirstOptimizer.resolveStream(depthFirstManager, BreadthFirstOptimizer.resolveFileByMinecraft(this.staticListener.getMc()))
         : Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "Apply an explore filter before using explore, which tells the explore process which chunks have been explored/not explored.",
         "",
         "The JSON file will follow this format: [{\"x\":0,\"z\":0},...]",
         "",
         "If 'invert' is specified, the chunks listed will be considered NOT explored, rather than explored.",
         "",
         "Usage:",
         "> explorefilter <path> [invert] - Load the JSON file referenced by the specified path. If invert is specified, it must be the literal word 'invert'."
      );
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(2);
      File file = (File)depthFirstManager.resolveTarget(BreadthFirstOptimizer.INSTANCE, this.staticListener.getMc().mcDataDir.getAbsoluteFile().getParentFile());
      boolean flag = false;
      if (depthFirstManager.isActive()) {
         if (!depthFirstManager.getText().equalsIgnoreCase("invert")) {
            throw new TertiaryClientManager(depthFirstManager.getBreadthFirstScheduler(), "either \"invert\" or nothing");
         }

         flag = true;
      }

      try {
         this.activeListener.getInternalINACTIVEHandlerExten().applyPath(file.toPath().toAbsolutePath(), flag);
      } catch (NoSuchFileException noSuchFileException) {
         throw new InternalClientManager("File not found");
      } catch (JsonSyntaxException jsonSyntaxException) {
         throw new InternalClientManager("Invalid JSON syntax");
      } catch (Exception exception) {
         throw new IllegalStateException(exception);
      }

      this.applyText(String.format("Explore filter applied. Inverted: %s", Boolean.toString(flag)));
   }

   @Override
   public String getLabelText2() {
      return "Explore chunks from a json";
   }

   public ExplorefilterCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"explorefilter"});
   }
}
