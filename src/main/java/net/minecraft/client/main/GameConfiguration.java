package net.minecraft.client.main;


import com.mojang.authlib.properties.PropertyMap;
import java.io.File;
import java.net.Proxy;
import net.minecraft.util.Session;
public class GameConfiguration {
   public final GameConfiguration.UserInformation userInfo;
   public final GameConfiguration.DisplayInformation displayInfo;
   public final GameConfiguration.FolderInformation folderInfo;
   public final GameConfiguration.GameInformation gameInfo;
   public final GameConfiguration.ServerInformation serverInfo;

   public GameConfiguration(
      GameConfiguration.UserInformation userInformation,
      GameConfiguration.DisplayInformation displayInformation,
      GameConfiguration.FolderInformation folderInformation,
      GameConfiguration.GameInformation gameInformation,
      GameConfiguration.ServerInformation serverInformation
   ) {
      this.userInfo = userInformation;
      this.displayInfo = displayInformation;
      this.folderInfo = folderInformation;
      this.gameInfo = gameInformation;
      this.serverInfo = serverInformation;
   }


   public static class GameInformation {
      public final boolean isDemo;
      public final String version;

      public GameInformation(boolean flag, String str) {
         this.isDemo = flag;
         this.version = str;
      }
   }

   public static class UserInformation {
      public final Session session;
      public final PropertyMap userProperties;
      public final PropertyMap profileProperties;
      public final Proxy proxy;

      public UserInformation(Session session2, PropertyMap propertyMap, PropertyMap propertyMap2, Proxy proxy2) {
         this.session = session2;
         this.userProperties = propertyMap;
         this.profileProperties = propertyMap2;
         this.proxy = proxy2;
      }
   }

   public static class FolderInformation {
      public final File mcDataDir;
      public final File resourcePacksDir;
      public final File assetsDir;
      public final String assetIndex;

      public FolderInformation(File file, File file2, File file3, String str) {
         this.mcDataDir = file;
         this.resourcePacksDir = file2;
         this.assetsDir = file3;
         this.assetIndex = str;
      }
   }

   public static class ServerInformation {
      public final String serverName;
      public final int serverPort;

      public ServerInformation(String str, int n) {
         this.serverName = str;
         this.serverPort = n;
      }
   }

   public static class DisplayInformation {
      public final int width;
      public final int height;
      public final boolean fullscreen;
      public final boolean checkGlErrors;

      public DisplayInformation(int n, int n2, boolean flag, boolean flag2) {
         this.width = n;
         this.height = n2;
         this.fullscreen = flag;
         this.checkGlErrors = flag2;
      }
   }
}
