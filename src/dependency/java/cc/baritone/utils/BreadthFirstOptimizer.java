package cc.baritone.utils;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.client.Minecraft;

public enum BreadthFirstOptimizer implements Listener10Extension {
   INSTANCE;

   public static final BreadthFirstOptimizer[] ENUM_VALUES = values();

   public static File resolveFileByMinecraft(Minecraft minecraft) {
      File dataDir = minecraft.mcDataDir.getAbsoluteFile();
      return dataDir.getName().equals(".") ? dataDir.getParentFile() : dataDir;
   }

   @Deprecated
   public static File getFile() {
      return resolveFileByMinecraft(DRNHandler.mc);
   }

   private static File getCanonicalFile(File file) {
      try {
         return file.getCanonicalFile();
      } catch (IOException ioException) {
         throw new UncheckedIOException(ioException);
      }
   }

   public File resolveFile(HeuristicSampler sampler, File baseDir) throws ExceptionAdapter {
      if (baseDir == null) {
         baseDir = new File("./");
      }

      Path relativePath;
      try {
         relativePath = FileSystems.getDefault().getPath(sampler.getDepthFirstManager().getText());
      } catch (InvalidPathException pathException) {
         throw new IllegalArgumentException("invalid path");
      }

      return getCanonicalFile(baseDir.toPath().resolve(relativePath).toFile());
   }

   private static String resolveText(Path parsedPath, Path rootPath, File candidate) {
      return (parsedPath.isAbsolute() ? candidate : rootPath.relativize(candidate.toPath()).toString()) + (candidate.isDirectory() ? File.separator : "");
   }

   @Override
   public Object resolveTarget(HeuristicSampler sampler, Object baseDir) throws ExceptionAdapter {
      return this.resolveFile(sampler, (File)baseDir);
   }

   public static Stream resolveStream(DepthFirstManager manager, File baseDir) throws ExceptionAdapter {
      File canonicalDir = getCanonicalFile(baseDir);
      String inputText = manager.getText();
      Path parsedPath = FileSystems.getDefault().getPath(inputText);
      Path rootPath = parsedPath.isAbsolute() ? parsedPath.getRoot() : canonicalDir.toPath();
      boolean useParent = !inputText.isEmpty() && !inputText.endsWith(File.separator);
      canonicalDir = parsedPath.isAbsolute() ? parsedPath.toFile() : new File(canonicalDir, inputText);
      return Stream.of(Objects.requireNonNull(getCanonicalFile(useParent ? canonicalDir.getParentFile() : canonicalDir).listFiles()))
         .map(entry -> BreadthFirstOptimizer.resolveText(parsedPath, rootPath, entry))
         .filter(name -> BreadthFirstOptimizer.matchesText(inputText, name))
         .filter(BreadthFirstOptimizer::contains);
   }

   private static boolean matchesText(String prefix, String candidate) {
      return candidate.toLowerCase(Locale.US).startsWith(prefix.toLowerCase(Locale.US));
   }

   private static boolean contains(String name) {
      return !name.contains(" ");
   }

   @Override
   public Stream empty(HeuristicSampler sampler) {
      return Stream.empty();
   }
}
