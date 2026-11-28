package net.augustus.utils.file;

import net.augustus.Augustus;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

public class JarResourceListingUtil {

   public static ArrayList listResourceEntries(String str) {
      ArrayList list = new ArrayList();

      try {
         String prefix = "assets/augustus/" + str.replace('\\', '/');
         while (prefix.endsWith("/")) {
            prefix = prefix.substring(0, prefix.length() - 1);
         }
         URL loc = JarResourceListingUtil.class.getProtectionDomain().getCodeSource().getLocation();
         if (loc == null) {
            return list;
         }
         File codeFile = new File(loc.toURI());
         if (codeFile.isDirectory()) {
            Path dir = codeFile.toPath().resolve(prefix);
            if (Files.isDirectory(dir)) {
               try (Stream<Path> stream = Files.list(dir)) {
                  stream.forEach(p -> list.add(p.getFileName().toString()));
               }
            }
            return list;
         }
         if (codeFile.isFile()) {
            try (JarFile jarFile = new JarFile(codeFile)) {
               String dirPrefix = prefix + "/";
               Set<String> seen = new LinkedHashSet<>();
               Enumeration<JarEntry> entries = jarFile.entries();
               while (entries.hasMoreElements()) {
                  String name = entries.nextElement().getName();
                  if (name.startsWith(dirPrefix) && name.length() > dirPrefix.length()) {
                     String rest = name.substring(dirPrefix.length());
                     int slash = rest.indexOf('/');
                     String child = slash >= 0 ? rest.substring(0, slash) : rest;
                     if (!child.isEmpty() && seen.add(child)) {
                        list.add(child);
                     }
                  }
               }
            }
         }
         return list;
      } catch (Exception e) {
         e.printStackTrace();
         return list;
      }
   }

}
