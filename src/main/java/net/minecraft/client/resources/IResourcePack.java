package net.minecraft.client.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

public interface IResourcePack {
   boolean resourceExists(ResourceLocation location);

   BufferedImage getPackImage() throws IOException;

   InputStream getInputStream(ResourceLocation location) throws IOException;

   IMetadataSection getPackMetadata(IMetadataSerializer metadataSerializer, String str) throws IOException;

   Set getResourceDomains();

   String getPackName();
}
