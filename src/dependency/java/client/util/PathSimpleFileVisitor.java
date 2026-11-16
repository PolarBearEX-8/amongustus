package client.util;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public final class PathSimpleFileVisitor extends SimpleFileVisitor {
   public final Path path;
   public final Path path2;

   public PathSimpleFileVisitor(Path path3, Path path4) {
      this.path2 = path3;
      this.path = path4;
   }

   public FileVisitResult resolveFileVisitResultByPath(Path path3, BasicFileAttributes basicFileAttributes) throws IOException {
      Files.createDirectories(this.path2.resolve(this.path.relativize(path3).toString()));
      return FileVisitResult.CONTINUE;
   }

   @Override
   public FileVisitResult preVisitDirectory(Object entry, BasicFileAttributes basicFileAttributes) throws IOException {
      Path path3 = (Path)entry;
      return this.resolveFileVisitResultByPath(path3, basicFileAttributes);
   }

   @Override
   public FileVisitResult visitFile(Object entry, BasicFileAttributes basicFileAttributes) throws IOException {
      Path path3 = (Path)entry;
      return this.resolveFileVisitResult(path3, basicFileAttributes);
   }

   public FileVisitResult resolveFileVisitResult(Path path3, BasicFileAttributes basicFileAttributes) throws IOException {
      Path path4 = this.path2.resolve(this.path.relativize(path3).toString());
      Files.copy(path3, path4, StandardCopyOption.REPLACE_EXISTING);
      return FileVisitResult.CONTINUE;
   }
}
