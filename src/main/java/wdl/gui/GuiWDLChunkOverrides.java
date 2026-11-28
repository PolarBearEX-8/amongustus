package wdl.gui;

import wdl.ChunkRange;
import wdl.WDL;
import wdl.WDLPluginChannels;
import com.google.common.collect.Multimap;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class GuiWDLChunkOverrides extends GuiScreen {
   private static final int TOP_MARGIN = 61;
   private static final int BOTTOM_MARGIN = 32;
   private static final ResourceLocation PERMISSION_WIDGETS_TEXTURE = new ResourceLocation("wdl:textures/permission_widgets.png");
   private final GuiScreen parent;
   private GuiButton startDownloadButton;
   private float scrollX;
   private float scrollZ;
   private static final int SCALE = 8;
   private ChunkOverrideMode mode = ChunkOverrideMode.PANNING;
   private boolean partiallyRequested;
   private int requestStartX;
   private int requestStartZ;
   private int requestEndX;
   private int requestEndZ;
   private boolean dragging;
   private int lastTickX;
   private int lastTickY;
   private static final int DEFAULT_RANGE_COLOR = 769532;

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
      if (state == 0) {
         this.dragging = false;
      }
   }

   private int displayYToChunkZ(int n) {
      return MathHelper.floor_float(((float)n - (float)(this.height / 2)) / 8.0F + this.scrollZ);
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      if (mouseY > 61 && mouseY < this.height - 32 && mouseButton == 0) {
         switch (ChunkOverrideModeSwitch.SWITCH_MAP[this.mode.ordinal()]) {
            case 1:
               this.dragging = true;
               this.lastTickX = mouseX;
               this.lastTickY = mouseY;
               break;
            case 2:
               if (this.partiallyRequested) {
                  this.requestEndX = this.displayXToChunkX(mouseX);
                  this.requestEndZ = this.displayYToChunkZ(mouseY);
                  WDLPluginChannels.addChunkOverrideRequest(new ChunkRange("", this.requestStartX, this.requestStartZ, this.requestEndX, this.requestEndZ));
                  this.partiallyRequested = false;
               } else {
                  this.requestStartX = this.displayXToChunkX(mouseX);
                  this.requestStartZ = this.displayYToChunkZ(mouseY);
                  this.partiallyRequested = true;
               }

               this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         }
      }
   }

   @Override
   public void initGui() {
      this.buttonList.add(new ChunkOverrideModeButton(this, 0, this.width / 2 - 155, 18, ChunkOverrideMode.PANNING));
      this.buttonList.add(new ChunkOverrideModeButton(this, 1, this.width / 2 - 130, 18, ChunkOverrideMode.REQUESTING));
      this.buttonList.add(new ChunkOverrideEraseButton(this, 2, this.width / 2 - 105, 18, ChunkOverrideMode.ERASING));
      this.buttonList.add(new ChunkOverrideMoveButton(this, 3, this.width / 2 - 80, 18, ChunkOverrideMode.MOVING));
      this.buttonList.add(new GuiButton(4, this.width / 2 - 80, 18, 80, 20, "Send request"));
      this.startDownloadButton = new GuiButton(6, this.width / 2 + 5, 18, 150, 20, "Start download in these ranges");
      this.startDownloadButton.enabled = WDLPluginChannels.canDownloadAtAll();
      this.buttonList.add(this.startDownloadButton);
      this.buttonList.add(new GuiButton(100, this.width / 2 - 100, this.height - 29, I18n.format("gui.done", new Object[0])));
      this.buttonList.add(new GuiButton(200, this.width / 2 - 155, 39, 100, 20, I18n.format("wdl.gui.permissions.current", new Object[0])));
      this.buttonList.add(new GuiButton(201, this.width / 2 - 50, 39, 100, 20, I18n.format("wdl.gui.permissions.request", new Object[0])));
      this.buttonList.add(new GuiButton(202, this.width / 2 + 55, 39, 100, 20, I18n.format("wdl.gui.permissions.overrides", new Object[0])));
   }

   public static ResourceLocation getPermissionWidgetsTexture() {
      return PERMISSION_WIDGETS_TEXTURE;
   }

   private int darken(int n) {
      int n2 = n >> 16 & 0xFF;
      int n3 = n >> 8 & 0xFF;
      int n4 = n & 0xFF;
      n2 /= 2;
      n3 /= 2;
      n4 /= 2;
      return (n2 << 16) + (n3 << 8) + n4;
   }

   private int displayXToChunkX(int n) {
      return MathHelper.floor_float(((float)n - (float)(this.width / 2)) / 8.0F + this.scrollX);
   }

   public static ChunkOverrideMode getMode(GuiWDLChunkOverrides guiWDLChunkOverrides) {
      return guiWDLChunkOverrides.mode;
   }

   private int chunkZToDisplayY(int n) {
      return (int)(((float)n - this.scrollZ) * 8.0F + (float)(this.height / 2));
   }

   private int chunkXToDisplayX(int n) {
      return (int)(((float)n - this.scrollX) * 8.0F + (float)(this.width / 2));
   }

   @Override
   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      if (this.dragging) {
         int n = this.lastTickX - mouseX;
         int n2 = this.lastTickY - mouseY;
         this.lastTickX = mouseX;
         this.lastTickY = mouseY;
         this.scrollX += (float)n / 8.0F;
         this.scrollZ += (float)n2 / 8.0F;
      }
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 0) {
         this.mode = ChunkOverrideMode.PANNING;
      }

      if (button.id == 1) {
         this.mode = ChunkOverrideMode.REQUESTING;
         this.partiallyRequested = false;
      }

      if (button.id == 4) {
         WDLPluginChannels.sendRequests();
      }

      if (button.id == 6) {
         if (!WDLPluginChannels.canDownloadAtAll()) {
            button.enabled = false;
            return;
         }

         WDL.startDownload();
      }

      if (button.id == 100) {
         GuiScreen parent2 = this.parent;
         this.mc.displayGuiScreen(parent2);
      }

      if (button.id == 200) {
         this.mc.displayGuiScreen(new GuiWDLPermissions(this.parent));
      }

      if (button.id == 201) {
         this.mc.displayGuiScreen(new GuiWDLPermissionRequest(this.parent));
      }

      if (button.id == 202) {
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      Utils.drawDarkBackground(0, 0, this.height, this.width);
      if (this.mode == ChunkOverrideMode.REQUESTING) {
         int n = this.partiallyRequested ? this.requestStartX : this.displayXToChunkX(mouseX);
         int n2 = this.partiallyRequested ? this.requestStartZ : this.displayYToChunkZ(mouseY);
         int n3 = this.displayXToChunkX(mouseX);
         int n4 = this.displayYToChunkZ(mouseY);
         this.displayRange(new ChunkRange("", n, n2, n3, n4), 16777215, 127 + (int)(Math.sin((double)Minecraft.getSystemTime() * 3.141592653589793 / 5000.0) * 64.0));
      }

      for (Multimap multimap : (Iterable<Multimap>)(WDLPluginChannels.getChunkOverrides().values())) {
         for (ChunkRange chunkRange2 : (Iterable<ChunkRange>)(multimap.values())) {
            this.displayRange(chunkRange2, 769532, 255);
         }
      }

      for (ChunkRange chunkRange3 : (Iterable<ChunkRange>)(WDLPluginChannels.getChunkOverrideRequests())) {
         this.displayRange(chunkRange3, 8421504, 127 + (int)(Math.sin((double)Minecraft.getSystemTime() * 3.141592653589793 / 5000.0) * 64.0));
      }

      int n7 = (int)((WDL.thePlayer.posX / 16.0 - (double)this.scrollX) * 8.0 + (double)(this.width / 2));
      int n8 = (int)((WDL.thePlayer.posZ / 16.0 - (double)this.scrollZ) * 8.0 + (double)(this.height / 2));
      this.drawHorizontalLine(n7 - 3, n7 + 3, n8, -1);
      this.drawVerticalLine(n7, n8 - 4, n8 + 4, -1);
      Utils.drawBorder(61, 32, 0, 0, this.height, this.width);
      drawCenteredString(this.fontRendererObj, "Chunk overrides", this.width / 2, 8, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
      drawCenteredString(this.fontRendererObj, "§c§lThis is a work in progress.", this.width / 2, this.height / 2, 16777215);
   }

   private void displayRange(ChunkRange chunkRange, int n, int n2) {
      int n3 = (chunkRange.tag.hashCode() ^ n) & 16777215;
      int n4 = this.chunkXToDisplayX(chunkRange.x1);
      int n5 = this.chunkZToDisplayY(chunkRange.z1);
      int n6 = this.chunkXToDisplayX(chunkRange.x2) + 8 - 1;
      int n7 = this.chunkZToDisplayY(chunkRange.z2) + 8 - 1;
      drawRect(n4, n5, n6, n7, n3 + (n2 << 24));
      int n8 = this.darken(n3);
      this.drawVerticalLine(n4, n5, n7, n8 + (n2 << 24));
      this.drawVerticalLine(n6, n5, n7, n8 + (n2 << 24));
      this.drawHorizontalLine(n4, n6, n5, n8 + (n2 << 24));
      this.drawHorizontalLine(n4, n6, n7, n8 + (n2 << 24));
   }

   public GuiWDLChunkOverrides(GuiScreen screen) {
      this.parent = screen;
      if (WDL.thePlayer != null) {
         this.scrollX = (float)WDL.thePlayer.chunkCoordX;
         this.scrollZ = (float)WDL.thePlayer.chunkCoordZ;
      }
   }

}
