package client.util;

import client.event.PreUpdateProcessor;
import client.setting.ClientSettingsBlackType;
import com.google.gson.JsonParseException;
import java.io.IOException;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TexturepackiconPackRayTraceValidator extends RayTraceValidator {
   public static final Logger logger = LogManager.getLogger();
   public final PreUpdateProcessor preUpdateProcessor = this.mc.getResourceLanguageManager().preUpdateProcessor2;
   public final ResourceLocation resourceLocation2;

   public TexturepackiconPackRayTraceValidator(GeometryPool geometryPool) {
      super(geometryPool);

      DynamicTexture dynamicTexture;
      try {
         dynamicTexture = new DynamicTexture(this.preUpdateProcessor.getBufferedImage());
      } catch (IOException ioException) {
         dynamicTexture = SpecialLoggerEntry.dynamicTexture;
      }

      this.resourceLocation2 = this.mc.getTextureManager().resolveResourceLocationByText("texturepackicon", dynamicTexture);
   }

   @Override
   public String getTextAsText() {
      try {
         AuxiliaryIChatComponentEntry auxiliaryIChatComponentEntry = (AuxiliaryIChatComponentEntry)this.preUpdateProcessor
            .resolveIMetadataSection(this.mc.getResourceLanguageManager().invalidMetadataForManager, "pack");
         if (auxiliaryIChatComponentEntry != null) {
            return auxiliaryIChatComponentEntry.getIChatComponent().getString2();
         }
      } catch (JsonParseException jsonParseException) {
         logger.error("Couldn't load metadata info", jsonParseException);
      } catch (IOException ioException) {
         logger.error("Couldn't load metadata info", ioException);
      }

      return ClientSettingsBlackType.RED + "Missing pack.mcmeta :(";
   }

   @Override
   public String getText() {
      return "Default";
   }

   @Override
   public boolean isActive2() {
      return false;
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public boolean isActive3() {
      return false;
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public boolean isActive4() {
      return false;
   }

   @Override
   public int getCount() {
      return 1;
   }

   @Override
   public void updateState() {
      this.mc.getTextureManager().applyResourceLocation3(this.resourceLocation2);
   }
}
