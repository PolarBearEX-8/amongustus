package net.minecraft.client.main;

import java.net.PasswordAuthentication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.properties.PropertyMap.Serializer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class Main {
   private static final String[] NATIVE_FILES = new String[]{
      "lwjgl64.dll",
      "lwjgl.dll",
      "OpenAL64.dll",
      "OpenAL32.dll",
      "jinput-dx8_64.dll",
      "jinput-dx8.dll",
      "jinput-raw_64.dll",
      "jinput-raw.dll",
      "jinput-wintab.dll",
      "liblwjgl64.so",
      "liblwjgl.so",
      "libopenal64.so",
      "libopenal.so",
      "libjinput-linux64.so",
      "libjinput-linux.so",
      "liblwjgl.dylib",
      "openal.dylib",
      "libjinput-osx.jnilib"
   };

   private static boolean isNullOrEmpty(String str) {
      return str != null && !str.isEmpty();
   }

   private static boolean hasLwjglNative(File dir) {
      if (dir == null || !dir.isDirectory()) {
         return false;
      }
      return new File(dir, "lwjgl64.dll").isFile()
         || new File(dir, "lwjgl.dll").isFile()
         || new File(dir, "liblwjgl64.so").isFile()
         || new File(dir, "liblwjgl.so").isFile()
         || new File(dir, "liblwjgl.dylib").isFile();
   }

   private static void configureNatives() {
      String existing = System.getProperty("org.lwjgl.librarypath");
      if (existing != null && !existing.isEmpty() && new File(existing).isDirectory()) {
         if (System.getProperty("net.java.games.input.librarypath") == null) {
            System.setProperty("net.java.games.input.librarypath", existing);
         }
         return;
      }

      String javaLibPath = System.getProperty("java.library.path");
      if (javaLibPath != null && !javaLibPath.isEmpty()) {
         for (String entry : javaLibPath.split(java.util.regex.Pattern.quote(File.pathSeparator))) {
            if (!entry.isEmpty() && hasLwjglNative(new File(entry))) {
               return;
            }
         }
      }

      File nativesDir = findExistingNativesDir();
      if (nativesDir == null) {
         nativesDir = extractNativesFromClasspath();
      }
      if (nativesDir != null) {
         String path = nativesDir.getAbsolutePath();
         System.setProperty("org.lwjgl.librarypath", path);
         if (System.getProperty("net.java.games.input.librarypath") == null) {
            System.setProperty("net.java.games.input.librarypath", path);
         }
      }
   }

   private static File findExistingNativesDir() {
      File[] candidates = new File[]{
         new File("build/natives"),
         new File("../build/natives"),
         new File("natives")
      };
      for (File candidate : candidates) {
         if (hasLwjglNative(candidate)) {
            return candidate;
         }
      }
      try {
         URI location = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI();
         File cur = new File(location);
         for (int i = 0; i < 6 && cur != null; i++) {
            File buildNatives = new File(cur, "build/natives");
            if (hasLwjglNative(buildNatives)) {
               return buildNatives;
            }
            File directNatives = new File(cur, "natives");
            if (hasLwjglNative(directNatives)) {
               return directNatives;
            }
            cur = cur.getParentFile();
         }
      } catch (Exception ignored) {
      }
      return null;
   }

   private static File extractNativesFromClasspath() {
      File targetDir = new File(System.getProperty("java.io.tmpdir"), "openaugustus-natives");
      if (!targetDir.exists()) {
         targetDir.mkdirs();
      }
      ClassLoader cl = Main.class.getClassLoader();
      byte[] buffer = new byte[8192];
      for (String name : NATIVE_FILES) {
         File outFile = new File(targetDir, name);
         if (outFile.isFile() && outFile.length() > 0L) {
            continue;
         }
         try (InputStream in = cl.getResourceAsStream(name)) {
            if (in == null) {
               continue;
            }
            try (FileOutputStream out = new FileOutputStream(outFile)) {
               int read;
               while ((read = in.read(buffer)) != -1) {
                  out.write(buffer, 0, read);
               }
            }
         } catch (Exception ignored) {
         }
      }
      return hasLwjglNative(targetDir) ? targetDir : null;
   }

   public static void main(String[] args) {
      configureNatives();
      System.setProperty("java.net.preferIPv4Stack", "true");
      OptionParser optionParser = new OptionParser();
      optionParser.allowsUnrecognizedOptions();
      optionParser.accepts("demo");
      optionParser.accepts("fullscreen");
      optionParser.accepts("checkGlErrors");
      OptionSpec<String> optionSpec = optionParser.accepts("server").withRequiredArg();
      OptionSpec<Integer> optionSpec2 = optionParser.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo(25565);
      OptionSpec<File> optionSpec3 = optionParser.accepts("gameDir").withRequiredArg().ofType(File.class).defaultsTo(new File("."));
      OptionSpec<File> optionSpec4 = optionParser.accepts("assetsDir").withRequiredArg().ofType(File.class);
      OptionSpec<File> optionSpec5 = optionParser.accepts("resourcePackDir").withRequiredArg().ofType(File.class);
      OptionSpec<String> optionSpec6 = optionParser.accepts("proxyHost").withRequiredArg();
      OptionSpec<Integer> optionSpec7 = optionParser.accepts("proxyPort").withRequiredArg().defaultsTo("8080").ofType(Integer.class);
      OptionSpec<String> optionSpec8 = optionParser.accepts("proxyUser").withRequiredArg();
      OptionSpec<String> optionSpec9 = optionParser.accepts("proxyPass").withRequiredArg();
      OptionSpec<String> optionSpec10 = optionParser.accepts("username").withRequiredArg().defaultsTo("Player" + Minecraft.getSystemTime() % 1000L);
      OptionSpec<String> optionSpec11 = optionParser.accepts("uuid").withRequiredArg();
      OptionSpec<String> optionSpec12 = optionParser.accepts("accessToken").withRequiredArg().required();
      OptionSpec<String> optionSpec13 = optionParser.accepts("version").withRequiredArg().required();
      OptionSpec<Integer> optionSpec14 = optionParser.accepts("width").withRequiredArg().ofType(Integer.class).defaultsTo(854);
      OptionSpec<Integer> optionSpec15 = optionParser.accepts("height").withRequiredArg().ofType(Integer.class).defaultsTo(480);
      OptionSpec<String> optionSpec16 = optionParser.accepts("userProperties").withRequiredArg().defaultsTo("{}");
      OptionSpec<String> optionSpec17 = optionParser.accepts("profileProperties").withRequiredArg().defaultsTo("{}");
      OptionSpec<String> optionSpec18 = optionParser.accepts("assetIndex").withRequiredArg();
      OptionSpec<String> optionSpec19 = optionParser.accepts("userType").withRequiredArg().defaultsTo("legacy");
      OptionSpec<String> optionSpec20 = optionParser.nonOptions();
      OptionSet optionSet = optionParser.parse(args);
      List<String> list = optionSet.valuesOf(optionSpec20);
      if (!list.isEmpty()) {
         System.out.println("Completely ignored arguments: " + list);
      }

      String str = optionSet.valueOf(optionSpec6);
      Proxy noproxy = Proxy.NO_PROXY;
      if (str != null) {
         try {
            noproxy = new Proxy(Type.SOCKS, new InetSocketAddress(str, optionSet.valueOf(optionSpec7)));
         } catch (Exception e) {
         }
      }

      String str2 = optionSet.valueOf(optionSpec8);
      String str3 = optionSet.valueOf(optionSpec9);
      if (!noproxy.equals(Proxy.NO_PROXY) && isNullOrEmpty(str2) && isNullOrEmpty(str3)) {
         Authenticator.setDefault(new Main.DefaultAuthenticator(str2, str3));
      }

      int n = optionSet.valueOf(optionSpec14);
      int n2 = optionSet.valueOf(optionSpec15);
      boolean flag = optionSet.has("fullscreen");
      boolean flag2 = optionSet.has("checkGlErrors");
      boolean flag3 = optionSet.has("demo");
      String str4 = optionSet.valueOf(optionSpec13);
      Gson gson = new GsonBuilder().registerTypeAdapter(PropertyMap.class, new Serializer()).create();
      PropertyMap propertyMap = gson.fromJson(optionSet.valueOf(optionSpec16), PropertyMap.class);
      PropertyMap propertyMap2 = gson.fromJson(optionSet.valueOf(optionSpec17), PropertyMap.class);
      File file = optionSet.valueOf(optionSpec3);
      File file2 = optionSet.has(optionSpec4) ? optionSet.valueOf(optionSpec4) : new File(file, "assets/");
      File file3 = optionSet.has(optionSpec5) ? optionSet.valueOf(optionSpec5) : new File(file, "resourcepacks/");
      String str5 = optionSet.has(optionSpec11) ? optionSpec11.value(optionSet) : optionSpec10.value(optionSet);
      String str6 = optionSet.has(optionSpec18) ? optionSpec18.value(optionSet) : null;
      String str7 = optionSet.valueOf(optionSpec);
      Integer num = optionSet.valueOf(optionSpec2);
      Session session = new Session(optionSpec10.value(optionSet), str5, optionSpec12.value(optionSet), optionSpec19.value(optionSet), null, null);
      GameConfiguration gameConfiguration = new GameConfiguration(
         new GameConfiguration.UserInformation(session, propertyMap, propertyMap2, noproxy),
         new GameConfiguration.DisplayInformation(n, n2, flag, flag2),
         new GameConfiguration.FolderInformation(file, file3, file2, str6),
         new GameConfiguration.GameInformation(flag3, str4),
         new GameConfiguration.ServerInformation(str7, num)
      );
      Runtime.getRuntime().addShutdownHook(new Main.WorkerTask("Client Shutdown Thread"));
      Thread.currentThread().setName("Client thread");
      new Minecraft(gameConfiguration).run();
   }


   public static final class DefaultAuthenticator extends Authenticator {
      public final String proxyUser;
      public final String proxyPass;

      public DefaultAuthenticator(String str, String str2) {
         this.proxyUser = str;
         this.proxyPass = str2;
      }

      @Override
      public PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(this.proxyUser, this.proxyPass.toCharArray());
      }
   }

   public static final class WorkerTask extends Thread {
      public WorkerTask(String str) {
         super(str);
      }

      @Override
      public void run() {
         Minecraft.stopIntegratedServer();
      }
   }
}
