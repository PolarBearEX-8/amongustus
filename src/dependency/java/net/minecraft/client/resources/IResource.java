package net.minecraft.client.resources;

import java.io.InputStream;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.ResourceLocation;

public interface IResource {
   IMetadataSection resolveIMetadataSection(String text);

   ResourceLocation getResourceLocation();

   InputStream getInputStream();

   boolean isActive();

   String getLabelText();
}
