package net.minecraft.client.resources;

import net.minecraft.client.gui.GuiScreenResourcePacks;

public class ResourcePackListEntryFound extends ResourcePackListEntry {
   private final ResourcePackRepository.Entry resourcePackEntry;

   @Override
   public String getResourcePackDescription() {
      return this.resourcePackEntry.getTexturePackDescription();
   }

   @Override
   public void bindResourcePackIcon() {
      this.resourcePackEntry.bindTexturePackIcon(this.mc.getTextureManager());
   }

   @Override
   public String getResourcePackName() {
      return this.resourcePackEntry.getResourcePackName();
   }

   @Override
   public int getResourcePackFormat() {
      return this.resourcePackEntry.getPackFormat();
   }

   public ResourcePackRepository.Entry getResourcePackEntry() {
      return this.resourcePackEntry;
   }

   public ResourcePackListEntryFound(GuiScreenResourcePacks guiScreenResourcePacks, ResourcePackRepository.Entry entry) {
      super(guiScreenResourcePacks);
      this.resourcePackEntry = entry;
   }
}
