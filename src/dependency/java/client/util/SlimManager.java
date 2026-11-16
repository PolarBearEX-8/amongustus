package client.util;

import java.util.UUID;
import net.minecraft.util.ResourceLocation;

public class SlimManager {
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/entity/steve.png");
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/entity/alex.png");

   public static ResourceLocation getResourceLocation() {
      return resourceLocation;
   }

   public static String resolveText(UUID uuid) {
      return matchesUUID(uuid) ? "slim" : "default";
   }

   private static boolean matchesUUID(UUID uuid) {
      return (uuid.hashCode() & 1) == 1;
   }

   public static ResourceLocation resolveResourceLocationByUUID(UUID uuid) {
      return matchesUUID(uuid) ? resourceLocation2 : resourceLocation;
   }
}
