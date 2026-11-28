package net.augustus.modules.render.esp;

import net.augustus.modules.render.EspImageEntrySyntheticMarker;
import java.awt.image.BufferedImage;
import net.minecraft.util.ResourceLocation;

public class EspOverlayImage {
   private final BufferedImage image;
   private final ResourceLocation resourceLocation;

   private EspOverlayImage(BufferedImage image2, ResourceLocation location) {
      this.image = image2;
      this.resourceLocation = location;
   }

   public int getWidth() {
      return this.image.getWidth();
   }

   public int getHeight() {
      return this.image.getHeight();
   }

   private ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public EspOverlayImage(BufferedImage image2, ResourceLocation location, EspImageEntrySyntheticMarker marker) {
      this(image2, location);
   }

   public static ResourceLocation getResourceLocation(EspOverlayImage espOverlayImage) {
      return espOverlayImage.getResourceLocation();
   }
}
