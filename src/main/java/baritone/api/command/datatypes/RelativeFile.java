package baritone.api.command.datatypes;

import baritone.api.command.exception.CommandException;
import baritone.api.utils.Helper;
import baritone.api.command.argument.IArgConsumer;
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

public enum RelativeFile implements IDatatypePost {
   INSTANCE;
   public File apply2(IDatatypeContext datatypeContext, File file) throws CommandException {
      if (file == null) {
         file = new File("./");
      }

      Path path;
      try {
         path = FileSystems.getDefault().getPath(datatypeContext.getConsumer().getString());
      } catch (InvalidPathException e) {
         throw new IllegalArgumentException("invalid path");
      }

      return getCanonicalFileUnchecked(file.toPath().resolve(path).toFile());
   }

   public static File gameDir(Minecraft mc) {
      File absoluteFile = mc.mcDataDir.getAbsoluteFile();
      return absoluteFile.getName().equals(".") ? absoluteFile.getParentFile() : absoluteFile;
   }

   @Override
   public Stream tabComplete(IDatatypeContext datatypeContext) {
      return Stream.empty();
   }

   private static File getCanonicalFileUnchecked(File file) {
      try {
         return file.getCanonicalFile();
      } catch (IOException e) {
         throw new UncheckedIOException(e);
      }
   }

   public static Stream tabComplete(IArgConsumer argConsumer, File file) throws CommandException {
      File canonicalFileUnchecked = getCanonicalFileUnchecked(file);
      String string = argConsumer.getString();
      Path path = FileSystems.getDefault().getPath(string);
      Path path2 = path.isAbsolute() ? path.getRoot() : canonicalFileUnchecked.toPath();
      boolean flag = !string.isEmpty() && !string.endsWith(File.separator);
      File file2 = path.isAbsolute() ? path.toFile() : new File(canonicalFileUnchecked, string);
      return Stream.of(Objects.requireNonNull(getCanonicalFileUnchecked(flag ? file2.getParentFile() : file2).listFiles()))
         .map(file3 -> (path.isAbsolute() ? file3 : path2.relativize(file3.toPath()).toString()) + (file3.isDirectory() ? File.separator : ""))
         .filter(str -> str.toLowerCase(Locale.US).startsWith(string.toLowerCase(Locale.US)))
         .filter(str2 -> !str2.contains(" "));
   }

   @Override
   public Object apply(IDatatypeContext datatypeContext, Object obj) throws CommandException {
      return this.apply2(datatypeContext, (File)obj);
   }

   @Deprecated
   public static File gameDir() {
      return gameDir(Helper.mc);
   }

}
