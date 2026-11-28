package baritone.command.defaults;

import baritone.Baritone;
import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.datatypes.RelativeBlockPos;
import baritone.api.command.datatypes.RelativeFile;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidStateException;
import baritone.api.utils.BetterBlockPos;
import baritone.api.command.argument.IArgConsumer;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.io.FilenameUtils;

public class BuildCommand extends Command {
   private final File schematicsDir;

   public BuildCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"build"});
      this.schematicsDir = new File(baritone2.getPlayerContext().getMc().mcDataDir, "schematics");
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      File absoluteFile = ((File)argConsumer.getDatatypePost(RelativeFile.INSTANCE, this.schematicsDir)).getAbsoluteFile();
      if (FilenameUtils.getExtension(absoluteFile.getAbsolutePath()).isEmpty()) {
         absoluteFile = new File(absoluteFile.getAbsolutePath() + "." + (String)Baritone.settings().schematicFallbackExtension.value);
      }

      BetterBlockPos betterBlockPos = this.ctx.playerFeet();
      BetterBlockPos betterBlockPos2;
      if (argConsumer.hasAny()) {
         argConsumer.requireMax(3);
         betterBlockPos2 = (BetterBlockPos)argConsumer.getDatatypePost(RelativeBlockPos.INSTANCE, betterBlockPos);
      } else {
         argConsumer.requireMax(0);
         betterBlockPos2 = betterBlockPos;
      }

      if (!this.baritone.getBuilderProcess().build(absoluteFile.getName(), absoluteFile, betterBlockPos2)) {
         throw new CommandInvalidStateException(
            "Couldn't load the schematic. Make sure to use the FULL file name, including the extension (e.g. blah.schematic)."
         );
      } else {
         this.logDirect2(String.format("Successfully loaded schematic for building\nOrigin: %s", betterBlockPos2));
      }
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) throws CommandException {
      if (argConsumer.hasExactlyOne()) {
         return RelativeFile.tabComplete(argConsumer, this.schematicsDir);
      } else if (argConsumer.has(2)) {
         argConsumer.get();
         return argConsumer.tabCompleteDatatype(RelativeBlockPos.INSTANCE);
      } else {
         return Stream.empty();
      }
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "Build a schematic from a file.",
         "",
         "Usage:",
         "> build <filename> - Loads and builds '<filename>.schematic'",
         "> build <filename> <x> <y> <z> - Custom position"
      );
   }

   @Override
   public String getShortDesc() {
      return "Build a schematic";
   }
}
