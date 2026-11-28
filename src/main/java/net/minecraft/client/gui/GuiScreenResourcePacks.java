package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.client.resources.ResourcePackListEntryDefault;
import net.minecraft.client.resources.ResourcePackListEntryFound;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;

public class GuiScreenResourcePacks extends GuiScreen {
   private static final Logger LOGGER2 = LogManager.getLogger();
   private List availableResourcePacks;
   private List selectedResourcePacks;
   private GuiResourcePackAvailable availableResourcePacksList;
   private GuiResourcePackSelected selectedResourcePacksList;
   private boolean changed = false;

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.availableResourcePacksList.mouseClicked(mouseX, mouseY, mouseButton);
      this.selectedResourcePacksList.mouseClicked(mouseX, mouseY, mouseButton);
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
   }

   @Override
   public void initGui() {
      this.buttonList.add(new GuiOptionButton(2, this.width / 2 - 154, this.height - 48, I18n.format("resourcePack.openFolder", new Object[0])));
      this.buttonList.add(new GuiOptionButton(1, this.width / 2 + 4, this.height - 48, I18n.format("gui.done", new Object[0])));
      if (!this.changed) {
         this.availableResourcePacks = Lists.newArrayList();
         this.selectedResourcePacks = Lists.newArrayList();
         ResourcePackRepository resourcePackRepository = this.mc.getResourcePackRepository();
         resourcePackRepository.updateRepositoryEntriesAll();
         ArrayList list = Lists.newArrayList(resourcePackRepository.getRepositoryEntriesAll());
         list.removeAll(resourcePackRepository.getRepositoryEntries());

         for (ResourcePackRepository.Entry entry : (Iterable<ResourcePackRepository.Entry>)(list)) {
            this.availableResourcePacks.add(new ResourcePackListEntryFound(this, entry));
         }

         for (ResourcePackRepository.Entry entry2 : (Iterable<ResourcePackRepository.Entry>)(Lists.reverse(resourcePackRepository.getRepositoryEntries()))) {
            this.selectedResourcePacks.add(new ResourcePackListEntryFound(this, entry2));
         }

         this.selectedResourcePacks.add(new ResourcePackListEntryDefault(this));
      }

      this.availableResourcePacksList = new GuiResourcePackAvailable(this.mc, 200, this.height, this.availableResourcePacks);
      this.availableResourcePacksList.setSlotXBoundsFromLeft(this.width / 2 - 4 - 200);
      this.availableResourcePacksList.registerScrollButtons(7, 8);
      this.selectedResourcePacksList = new GuiResourcePackSelected(this.mc, 200, this.height, this.selectedResourcePacks);
      this.selectedResourcePacksList.setSlotXBoundsFromLeft(this.width / 2 + 4);
      this.selectedResourcePacksList.registerScrollButtons(7, 8);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawBackground(0);
      this.availableResourcePacksList.drawScreen(mouseX, mouseY, partialTicks);
      this.selectedResourcePacksList.drawScreen(mouseX, mouseY, partialTicks);
      drawCenteredStringRaw(this.fontRendererObj, I18n.format("resourcePack.title", new Object[0]), this.width / 2, 16, 16777215);
      drawCenteredStringRaw(this.fontRendererObj, I18n.format("resourcePack.folderInfo", new Object[0]), this.width / 2 - 77, this.height - 26, 8421504);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public List getSelectedResourcePacks() {
      return this.selectedResourcePacks;
   }

   public void markChanged() {
      this.changed = true;
   }

   public GuiScreenResourcePacks(GuiScreen screen) {
      super(screen);
      GuiScreen.NO_TOGGLE_SCREENS.add(this);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         if (button.id == 2) {
            File dirResourcepacks = this.mc.getResourcePackRepository().getDirResourcepacks();
            String absolutePath = dirResourcepacks.getAbsolutePath();
            if (Util.getOSType() == Util.EnumOS.OSX) {
               try {
                  LOGGER2.info(absolutePath);
                  Runtime.getRuntime().exec(new String[]{"/usr/bin/open", absolutePath});
                  return;
               } catch (IOException e) {
                  LOGGER2.error("Couldn't open file", e);
               }
            } else if (Util.getOSType() == Util.EnumOS.WINDOWS) {
               String str = String.format("cmd.exe /C start \"Open file\" \"%s\"", absolutePath);

               try {
                  Runtime.getRuntime().exec(str);
                  return;
               } catch (IOException e2) {
                  LOGGER2.error("Couldn't open file", e2);
               }
            }

            boolean flag = false;

            try {
               Class clazz = Class.forName("java.awt.Desktop");
               Object obj = clazz.getMethod("getDesktop").invoke(null);
               clazz.getMethod("browse", URI.class).invoke(obj, dirResourcepacks.toURI());
            } catch (Throwable e3) {
               LOGGER2.error("Couldn't open link", e3);
               flag = true;
            }

            if (flag) {
               LOGGER2.info("Opening via system class!");
               Sys.openURL("file://" + absolutePath);
            }
         } else if (button.id == 1) {
            if (this.changed) {
               ArrayList list = Lists.newArrayList();

               for (ResourcePackListEntry resourcePackListEntry : (Iterable<ResourcePackListEntry>)(this.selectedResourcePacks)) {
                  if (resourcePackListEntry instanceof ResourcePackListEntryFound) {
                     list.add(((ResourcePackListEntryFound)resourcePackListEntry).getResourcePackEntry());
                  }
               }

               Collections.reverse(list);
               this.mc.getResourcePackRepository().setRepositories(list);
               this.mc.gameSettings.resourcePacks.clear();
               this.mc.gameSettings.incompatibleResourcePacks.clear();

               for (ResourcePackRepository.Entry entry : (Iterable<ResourcePackRepository.Entry>)(list)) {
                  this.mc.gameSettings.resourcePacks.add(entry.getResourcePackName());
                  if (entry.getPackFormat() != 1) {
                     this.mc.gameSettings.incompatibleResourcePacks.add(entry.getResourcePackName());
                  }
               }

               this.mc.gameSettings.saveOptions();
               this.mc.refreshResources();
            }

            GuiScreen parentScreen2 = this.parentScreen;
            this.mc.displayGuiScreen(parentScreen2);
         }
      }
   }

   public boolean hasResourcePackEntry(ResourcePackListEntry resourcePackListEntry) {
      return this.selectedResourcePacks.contains(resourcePackListEntry);
   }

   public List getListContaining(ResourcePackListEntry resourcePackListEntry) {
      return this.hasResourcePackEntry(resourcePackListEntry) ? this.selectedResourcePacks : this.availableResourcePacks;
   }

   public List getAvailableResourcePacks() {
      return this.availableResourcePacks;
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.selectedResourcePacksList.handleMouseInput();
      this.availableResourcePacksList.handleMouseInput();
   }

}
