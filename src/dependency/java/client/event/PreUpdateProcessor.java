package client.event;

import client.util.InvalidMetadataForManager;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.ResourceLocation;

public interface PreUpdateProcessor {
   boolean matchesResourceLocation(ResourceLocation location);

   IMetadataSection resolveIMetadataSection(InvalidMetadataForManager invalidMetadataForManager, String text) throws IOException;

   BufferedImage getBufferedImage() throws IOException;

   Set getSet();

   InputStream resolveInputStream(ResourceLocation location) throws IOException;

   String getName();
}
