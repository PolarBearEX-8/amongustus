package net.minecraft.client.resources;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import net.minecraft.util.ResourceLocation;

public interface IResourceManager {
   List resolveListByResourceLocation(ResourceLocation location) throws IOException;

   Set getSet();

   IResource resolveIResourceByResourceLocation(ResourceLocation location) throws IOException;
}
