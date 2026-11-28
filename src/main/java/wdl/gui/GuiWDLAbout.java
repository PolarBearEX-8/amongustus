package wdl.gui;

import wdl.WDL;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWDLAbout extends GuiScreen {
   private final GuiScreen parent;
   private static final String FORUMS_THREAD_USAGE_LINK = null;
   private static final String COREMOD_GITHUB = null;
   private static final String LITEMOD_GITHUB = null;
   private TextList list;

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      if (!this.list.mouseReleased(mouseX, mouseY, state)) {
         super.mouseReleased(mouseX, mouseY, state);
      }
   }

   @Override
   public void initGui() {

      try {
         this.buttonList.add(new GuiButton(0, this.width / 2 - 155, 18, 150, 20, I18n.format("wdl.gui.about.extensions", new Object[0])));
         this.buttonList.add(new GuiButton(1, this.width / 2 + 5, 18, 150, 20, I18n.format("wdl.gui.about.debugInfo", new Object[0])));
         this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height - 29, I18n.format("gui.done", new Object[0])));
         String str = "1.8.9a-beta2";
         String fullMinecraftVersion = WDL.getFullMinecraftVersion();
         this.list = new TextList(this.mc, this.width, this.height, 39, 32);
         this.list.addLine(I18n.format("wdl.gui.about.blurb", new Object[0]));
         this.list.addBlankLine();
         this.list.addLine(I18n.format("wdl.gui.about.version", new Object[]{str, fullMinecraftVersion}));
         this.list.addBlankLine();
         String str2 = WDL.mc.getLanguageManager().getCurrentLanguage().toString();
         String str3 = I18n.format("wdl.translatorCredit", new Object[]{str2});
         if (str3 != null && !str3.isEmpty()) {
            this.list.addLine(str3);
            this.list.addBlankLine();
         }

         this.list
            .addLinkLine(
               I18n.format("wdl.gui.about.forumThread", new Object[0]), "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2520465"
            );
         this.list.addBlankLine();
         this.list.addLinkLine(I18n.format("wdl.gui.about.coremodSrc", new Object[0]), "https://github.com/Pokechu22/WorldDownloader");
         this.list.addBlankLine();
         this.list.addLinkLine(I18n.format("wdl.gui.about.litemodSrc", new Object[0]), "https://github.com/uyjulian/LiteModWDL/");
      } catch (NullPointerException e) {
         e.printStackTrace();
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      this.list.mouseClicked(mouseX, mouseY, mouseButton);
      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }

   public GuiWDLAbout(GuiScreen screen) {
      this.parent = screen;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.list != null) {
         this.list.drawScreen(mouseX, mouseY, partialTicks);
         super.drawScreen(mouseX, mouseY, partialTicks);
         drawCenteredString(this.fontRendererObj, I18n.format("wdl.gui.about.title", new Object[0]), this.width / 2, 2, 16777215);
      }
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 0) {
         this.mc.displayGuiScreen(new GuiWDLExtensions(this));
      } else if (button.id == 1) {
         setClipboardString(WDL.getDebugInfo());
         button.displayString = I18n.format("wdl.gui.about.debugInfo.copied", new Object[0]);
      } else if (button.id == 2) {
         GuiScreen parent2 = this.parent;
         this.mc.displayGuiScreen(parent2);
      }
   }

}
