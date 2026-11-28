package net.minecraft.client.gui;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.FlatLayerInfo;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.gen.FlatGeneratorInfo;

public class GuiCreateFlatWorld extends GuiScreen {
   private final GuiCreateWorld createWorldGui;
   private FlatGeneratorInfo theFlatGeneratorInfo = FlatGeneratorInfo.getDefaultFlatGenerator();
   private String flatWorldTitle;
   private String materialText;
   private String heightText;
   private GuiCreateFlatWorld.Details createFlatWorldListSlotGui;
   private GuiButton addLayerButton;
   private GuiButton editLayerButton;
   private GuiButton removeLayerButton;

   @Override
   public void initGui() {
      this.buttonList.clear();
      this.flatWorldTitle = I18n.format("createWorld.customize.flat.title", new Object[0]);
      this.materialText = I18n.format("createWorld.customize.flat.tile", new Object[0]);
      this.heightText = I18n.format("createWorld.customize.flat.height", new Object[0]);
      this.createFlatWorldListSlotGui = new GuiCreateFlatWorld.Details(this);
      this.buttonList
         .add(
            this.addLayerButton = new GuiButton(
               2, this.width / 2 - 154, this.height - 52, 100, 20, I18n.format("createWorld.customize.flat.addLayer", new Object[0]) + " (NYI)"
            )
         );
      this.buttonList
         .add(
            this.editLayerButton = new GuiButton(
               3, this.width / 2 - 50, this.height - 52, 100, 20, I18n.format("createWorld.customize.flat.editLayer", new Object[0]) + " (NYI)"
            )
         );
      this.buttonList
         .add(
            this.removeLayerButton = new GuiButton(4, this.width / 2 - 155, this.height - 52, 150, 20, I18n.format("createWorld.customize.flat.removeLayer", new Object[0]))
         );
      this.buttonList.add(new GuiButton(0, this.width / 2 - 155, this.height - 28, 150, 20, I18n.format("gui.done", new Object[0])));
      this.buttonList.add(new GuiButton(5, this.width / 2 + 5, this.height - 52, 150, 20, I18n.format("createWorld.customize.presets", new Object[0])));
      this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height - 28, 150, 20, I18n.format("gui.cancel", new Object[0])));
      this.addLayerButton.visible = this.editLayerButton.visible = false;
      this.theFlatGeneratorInfo.updateLayers();
      this.onLayersChanged();
   }

   public String getPreset() {
      return this.theFlatGeneratorInfo.toString();
   }

   public void setPreset(String str) {
      this.theFlatGeneratorInfo = FlatGeneratorInfo.createFlatGeneratorFromString(str);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.createFlatWorldListSlotGui.drawScreen(mouseX, mouseY, partialTicks);
      drawCenteredString(this.fontRendererObj, this.flatWorldTitle, this.width / 2, 8, 16777215);
      int n = this.width / 2 - 92 - 16;
      this.drawString(this.fontRendererObj, this.materialText, n, 32, 16777215);
      this.drawString(this.fontRendererObj, this.heightText, n + 2 + 213 - this.fontRendererObj.getStringWidth(this.heightText), 32, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public static FlatGeneratorInfo accessTheFlatGeneratorInfo(GuiCreateFlatWorld guiCreateFlatWorld) {
      return guiCreateFlatWorld.theFlatGeneratorInfo;
   }

   private boolean hasSelectedLayer() {
      return this.createFlatWorldListSlotGui.selectedLayer > -1 && this.createFlatWorldListSlotGui.selectedLayer < this.theFlatGeneratorInfo.getFlatLayers().size();
   }

   public GuiCreateFlatWorld(GuiCreateWorld guiCreateWorld, String str) {
      this.createWorldGui = guiCreateWorld;
      this.setPreset(str);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      int n = this.theFlatGeneratorInfo.getFlatLayers().size() - this.createFlatWorldListSlotGui.selectedLayer - 1;
      if (button.id == 1) {
         GuiCreateWorld createWorldGui2 = this.createWorldGui;
         this.mc.displayGuiScreen(createWorldGui2);
      } else if (button.id == 0) {
         this.createWorldGui.chunkProviderSettingsJson = this.getPreset();
         GuiCreateWorld createWorldGui3 = this.createWorldGui;
         this.mc.displayGuiScreen(createWorldGui3);
      } else if (button.id == 5) {
         this.mc.displayGuiScreen(new GuiFlatPresets(this));
      } else if (button.id == 4 && this.hasSelectedLayer()) {
         this.theFlatGeneratorInfo.getFlatLayers().remove(n);
         this.createFlatWorldListSlotGui.selectedLayer = Math.min(this.createFlatWorldListSlotGui.selectedLayer, this.theFlatGeneratorInfo.getFlatLayers().size() - 1);
      }

      this.theFlatGeneratorInfo.updateLayers();
      this.onLayersChanged();
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.createFlatWorldListSlotGui.handleMouseInput();
   }

   public void onLayersChanged() {
      boolean flag = this.hasSelectedLayer();
      this.removeLayerButton.enabled = flag;
      this.editLayerButton.enabled = flag;
      this.editLayerButton.enabled = false;
      this.addLayerButton.enabled = false;
   }



   public static class Details extends GuiSlot {
      public int selectedLayer;
      public final GuiCreateFlatWorld parentScreen;

      private void drawLayerIcon(int n, int n2, ItemStack stack) {
         this.drawSlotBackground(n + 1, n2 + 1);
         GlStateManager.enableRescaleNormal();
         if (stack != null && stack.getItem() != null) {
            RenderHelper.enableGUIStandardItemLighting();
            this.parentScreen.itemRender.renderItemIntoGUI(stack, n + 2, n2 + 2);
            RenderHelper.disableStandardItemLighting();
         }

         GlStateManager.disableRescaleNormal();
      }

      @Override
      public int getSize() {
         return GuiCreateFlatWorld.accessTheFlatGeneratorInfo(this.parentScreen).getFlatLayers().size();
      }

      @Override
      public int getScrollBarX() {
         return this.width - 70;
      }

      @Override
      public boolean isSelected(int n) {
         return n == this.selectedLayer;
      }

      public Details(GuiCreateFlatWorld guiCreateFlatWorld) {
         super(guiCreateFlatWorld.mc, guiCreateFlatWorld.width, guiCreateFlatWorld.height, 43, guiCreateFlatWorld.height - 60, 24);
         this.parentScreen = guiCreateFlatWorld;
         this.selectedLayer = -1;
      }

      private void drawSlotBackground(int n, int n2) {
         this.drawIconTexture(n, n2, 0, 0);
      }

      @Override
      public void elementClicked(int n, boolean flag, int n2, int n3) {
         this.selectedLayer = n;
         this.parentScreen.onLayersChanged();
      }

      @Override
      public void drawSlot(int n, int n2, int n3, int n4, int n5, int n6) {
         FlatLayerInfo flatLayerInfo = (FlatLayerInfo)GuiCreateFlatWorld.accessTheFlatGeneratorInfo(this.parentScreen)
            .getFlatLayers()
            .get(GuiCreateFlatWorld.accessTheFlatGeneratorInfo(this.parentScreen).getFlatLayers().size() - n - 1);
         IBlockState layerMaterial = flatLayerInfo.getLayerMaterial();
         Block block = layerMaterial.getBlock();
         Item itemFromBlock = Item.getItemFromBlock(block);
         ItemStack stack = block != Blocks.air && itemFromBlock != null ? new ItemStack(itemFromBlock, 1, block.getMetaFromState(layerMaterial)) : null;
         String str = stack == null ? "Air" : itemFromBlock.getItemStackDisplayName(stack);
         if (itemFromBlock == null) {
            if (block == Blocks.water || block == Blocks.flowing_water) {
               itemFromBlock = Items.water_bucket;
            } else if (block == Blocks.lava || block == Blocks.flowing_lava) {
               itemFromBlock = Items.lava_bucket;
            }

            if (itemFromBlock != null) {
               stack = new ItemStack(itemFromBlock, 1, block.getMetaFromState(layerMaterial));
               str = block.getLocalizedName();
            }
         }

         this.drawLayerIcon(n2, n3, stack);
         this.parentScreen.fontRendererObj.drawString(str, n2 + 18 + 5, n3 + 3, 16777215);
         String str2;
         if (n == 0) {
            str2 = I18n.format("createWorld.customize.flat.layer.top", new Object[]{flatLayerInfo.getLayerCount()});
         } else if (n == GuiCreateFlatWorld.accessTheFlatGeneratorInfo(this.parentScreen).getFlatLayers().size() - 1) {
            str2 = I18n.format("createWorld.customize.flat.layer.bottom", new Object[]{flatLayerInfo.getLayerCount()});
         } else {
            str2 = I18n.format("createWorld.customize.flat.layer", new Object[]{flatLayerInfo.getLayerCount()});
         }

         this.parentScreen.fontRendererObj.drawString(str2, n2 + 2 + 213 - this.parentScreen.fontRendererObj.getStringWidth(str2), n3 + 3, 16777215);
      }

      @Override
      public void drawBackground() {
      }

      private void drawIconTexture(int n, int n2, int n3, int n4) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(Gui.statIcons);
         float f = 0.0078125F;
         float f2 = 0.0078125F;
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
         worldRenderer.pos((double)(n + 0), (double)(n2 + 18), (double)this.parentScreen.zLevel)
            .tex((double)((float)(n3 + 0) * 0.0078125F), (double)((float)(n4 + 18) * 0.0078125F))
            .endVertex();
         worldRenderer.pos((double)(n + 18), (double)(n2 + 18), (double)this.parentScreen.zLevel)
            .tex((double)((float)(n3 + 18) * 0.0078125F), (double)((float)(n4 + 18) * 0.0078125F))
            .endVertex();
         worldRenderer.pos((double)(n + 18), (double)(n2 + 0), (double)this.parentScreen.zLevel)
            .tex((double)((float)(n3 + 18) * 0.0078125F), (double)((float)(n4 + 0) * 0.0078125F))
            .endVertex();
         worldRenderer.pos((double)(n + 0), (double)(n2 + 0), (double)this.parentScreen.zLevel)
            .tex((double)((float)(n3 + 0) * 0.0078125F), (double)((float)(n4 + 0) * 0.0078125F))
            .endVertex();
         instance.draw();
      }
   }
}
