package net.minecraft.client.resources;

import com.google.gson.JsonParseException;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourcePackListEntryDefault extends ResourcePackListEntry {
   private static final Logger LOGGER = LogManager.getLogger();
   private final IResourcePack defaultResourcePack = this.mc.getResourcePackRepository().rprDefaultResourcePack;
   private final ResourceLocation resourcePackIcon;

   @Override
   public boolean showHoverOverlay() {
      return false;
   }

   public ResourcePackListEntryDefault(GuiScreenResourcePacks guiScreenResourcePacks) {
      super(guiScreenResourcePacks);

      DynamicTexture dynamicTexture;
      try {
         dynamicTexture = new DynamicTexture(this.defaultResourcePack.getPackImage());
      } catch (IOException e) {
         dynamicTexture = TextureUtil.missingTexture;
      }

      this.resourcePackIcon = this.mc.getTextureManager().getDynamicTextureLocation("texturepackicon", dynamicTexture);
   }

   @Override
   public boolean canMoveUp() {
      return false;
   }

   @Override
   public String getResourcePackName() {
      return "Default";
   }

   @Override
   public int getResourcePackFormat() {
      return 1;
   }

   @Override
   public boolean canMoveLeft() {
      return false;
   }

   @Override
   public boolean canMoveRight() {
      return false;
   }

   @Override
   public void bindResourcePackIcon() {
      this.mc.getTextureManager().bindTexture(this.resourcePackIcon);
   }

   @Override
   public boolean canMoveDown() {
      return false;
   }

   @Override
   public String getResourcePackDescription() {
      try {
         PackMetadataSection packMetadata = (PackMetadataSection)this.defaultResourcePack.getPackMetadata(this.mc.getResourcePackRepository().rprMetadataSerializer, "pack");
         if (packMetadata != null) {
            return packMetadata.getPackDescription().getFormattedText();
         }
      } catch (JsonParseException e) {
         LOGGER.error("Couldn't load metadata info", e);
      } catch (IOException e2) {
         LOGGER.error("Couldn't load metadata info", e2);
      }

      return EnumChatFormatting.RED + "Missing pack.mcmeta :(";
   }
}
