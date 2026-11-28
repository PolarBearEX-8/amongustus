package net.minecraft.client.gui;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.minecraft.world.gen.FlatLayerInfo;
import org.lwjgl.input.Keyboard;

public class GuiFlatPresets extends GuiScreen {
   private static final List FLAT_WORLD_PRESETS = Lists.newArrayList();
   private final GuiCreateFlatWorld parentScreen;
   private String presetsTitle;
   private String presetsShare;
   private String presetsListText;
   private GuiFlatPresets.ListSlot presetListSlot;
   private GuiButton usePresetButton;
   private GuiTextField exportTextField;

   @Override
   public void initGui() {
      this.buttonList.clear();
      Keyboard.enableRepeatEvents(true);
      this.presetsTitle = I18n.format("createWorld.customize.presets.title", new Object[0]);
      this.presetsShare = I18n.format("createWorld.customize.presets.share", new Object[0]);
      this.presetsListText = I18n.format("createWorld.customize.presets.list", new Object[0]);
      this.exportTextField = new GuiTextField(2, this.fontRendererObj, 50, 40, this.width - 100, 20);
      this.presetListSlot = new GuiFlatPresets.ListSlot(this);
      this.exportTextField.setMaxStringLength(1230);
      this.exportTextField.setText(this.parentScreen.getPreset());
      this.buttonList
         .add(this.usePresetButton = new GuiButton(0, this.width / 2 - 155, this.height - 28, 150, 20, I18n.format("createWorld.customize.presets.select", new Object[0])));
      this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height - 28, 150, 20, I18n.format("gui.cancel", new Object[0])));
      this.updateButtonValidity();
   }

   public void updateButtonValidity() {
      boolean flag = this.hasValidSelection();
      this.usePresetButton.enabled = flag;
   }

   public static GuiTextField accessExportTextField(GuiFlatPresets guiFlatPresets) {
      return guiFlatPresets.exportTextField;
   }

   private boolean hasValidSelection() {
      return this.presetListSlot.selectedIndex > -1 && this.presetListSlot.selectedIndex < FLAT_WORLD_PRESETS.size() || this.exportTextField.getText().length() > 1;
   }

   @Override
   public void updateScreen() {
      this.exportTextField.updateCursorCounter();
      super.updateScreen();
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.presetListSlot.drawScreen(mouseX, mouseY, partialTicks);
      drawCenteredString(this.fontRendererObj, this.presetsTitle, this.width / 2, 8, 16777215);
      this.drawString(this.fontRendererObj, this.presetsShare, 50, 30, 10526880);
      this.drawString(this.fontRendererObj, this.presetsListText, 50, 70, 10526880);
      this.exportTextField.drawTextBox();
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   private static void addPresetNoFeatures(String str, Item item, BiomeGenBase biomeGenBase, FlatLayerInfo[] flatLayerInfos) {
      addPreset(str, item, 0, biomeGenBase, (List)null, flatLayerInfos);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 0 && this.hasValidSelection()) {
         this.parentScreen.setPreset(this.exportTextField.getText());
         GuiCreateFlatWorld parentScreen2 = this.parentScreen;
         this.mc.displayGuiScreen(parentScreen2);
      } else if (button.id == 1) {
         GuiCreateFlatWorld parentScreen3 = this.parentScreen;
         this.mc.displayGuiScreen(parentScreen3);
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      this.exportTextField.mouseClicked(mouseX, mouseY, mouseButton);
      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   private static void addPreset(String str, Item item, int n, BiomeGenBase biomeGenBase, List list, FlatLayerInfo[] flatLayerInfos) {
      FlatGeneratorInfo flatGeneratorInfo = new FlatGeneratorInfo();

      for (int i = flatLayerInfos.length - 1; i >= 0; i--) {
         flatGeneratorInfo.getFlatLayers().add(flatLayerInfos[i]);
      }

      flatGeneratorInfo.setBiome(biomeGenBase.biomeID);
      flatGeneratorInfo.updateLayers();
      if (list != null) {
         for (String str2 : (Iterable<String>)(list)) {
            flatGeneratorInfo.getWorldFeatures().put(str2, Maps.newHashMap());
         }
      }

      FLAT_WORLD_PRESETS.add(new GuiFlatPresets.LayerItem(item, n, str, flatGeneratorInfo.toString()));
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.presetListSlot.handleMouseInput();
   }

   static {
      addPresetWithFeatures(
         "Classic Flat",
         Item.getItemFromBlock(Blocks.grass),
         BiomeGenBase.plains,
         Arrays.asList("village"),
         new FlatLayerInfo[]{new FlatLayerInfo(1, Blocks.grass), new FlatLayerInfo(2, Blocks.dirt), new FlatLayerInfo(1, Blocks.bedrock)}
      );
      addPresetWithFeatures(
         "Tunnelers' Dream",
         Item.getItemFromBlock(Blocks.stone),
         BiomeGenBase.extremeHills,
         Arrays.asList("biome_1", "dungeon", "decoration", "stronghold", "mineshaft"),
         new FlatLayerInfo[]{
            new FlatLayerInfo(1, Blocks.grass), new FlatLayerInfo(5, Blocks.dirt), new FlatLayerInfo(230, Blocks.stone), new FlatLayerInfo(1, Blocks.bedrock)
         }
      );
      addPresetWithFeatures(
         "Water World",
         Items.water_bucket,
         BiomeGenBase.deepOcean,
         Arrays.asList("biome_1", "oceanmonument"),
         new FlatLayerInfo[]{
            new FlatLayerInfo(90, Blocks.water),
            new FlatLayerInfo(5, Blocks.sand),
            new FlatLayerInfo(5, Blocks.dirt),
            new FlatLayerInfo(5, Blocks.stone),
            new FlatLayerInfo(1, Blocks.bedrock)
         }
      );
      addPreset(
         "Overworld",
         Item.getItemFromBlock(Blocks.tallgrass),
         BlockTallGrass.EnumType.GRASS.getMeta(),
         BiomeGenBase.plains,
         Arrays.asList("village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon", "lake", "lava_lake"),
         new FlatLayerInfo[]{
            new FlatLayerInfo(1, Blocks.grass), new FlatLayerInfo(3, Blocks.dirt), new FlatLayerInfo(59, Blocks.stone), new FlatLayerInfo(1, Blocks.bedrock)
         }
      );
      addPresetWithFeatures(
         "Snowy Kingdom",
         Item.getItemFromBlock(Blocks.snow_layer),
         BiomeGenBase.icePlains,
         Arrays.asList("village", "biome_1"),
         new FlatLayerInfo[]{
            new FlatLayerInfo(1, Blocks.snow_layer),
            new FlatLayerInfo(1, Blocks.grass),
            new FlatLayerInfo(3, Blocks.dirt),
            new FlatLayerInfo(59, Blocks.stone),
            new FlatLayerInfo(1, Blocks.bedrock)
         }
      );
      addPresetWithFeatures(
         "Bottomless Pit",
         Items.feather,
         BiomeGenBase.plains,
         Arrays.asList("village", "biome_1"),
         new FlatLayerInfo[]{new FlatLayerInfo(1, Blocks.grass), new FlatLayerInfo(3, Blocks.dirt), new FlatLayerInfo(2, Blocks.cobblestone)}
      );
      addPresetWithFeatures(
         "Desert",
         Item.getItemFromBlock(Blocks.sand),
         BiomeGenBase.desert,
         Arrays.asList("village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon"),
         new FlatLayerInfo[]{
            new FlatLayerInfo(8, Blocks.sand),
            new FlatLayerInfo(52, Blocks.sandstone),
            new FlatLayerInfo(3, Blocks.stone),
            new FlatLayerInfo(1, Blocks.bedrock)
         }
      );
      addPresetNoFeatures(
         "Redstone Ready",
         Items.redstone,
         BiomeGenBase.desert,
         new FlatLayerInfo[]{new FlatLayerInfo(52, Blocks.sandstone), new FlatLayerInfo(3, Blocks.stone), new FlatLayerInfo(1, Blocks.bedrock)}
      );
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if (!this.exportTextField.textboxKeyTyped(typedChar, keyCode)) {
         super.keyTyped(typedChar, keyCode);
      }
   }

   private static void addPresetWithFeatures(String str, Item item, BiomeGenBase biomeGenBase, List list, FlatLayerInfo[] flatLayerInfos) {
      addPreset(str, item, 0, biomeGenBase, list, flatLayerInfos);
   }

   public GuiFlatPresets(GuiCreateFlatWorld guiCreateFlatWorld) {
      this.parentScreen = guiCreateFlatWorld;
   }

   @Override
   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   public static GuiFlatPresets.ListSlot accessPresetListSlot(GuiFlatPresets guiFlatPresets) {
      return guiFlatPresets.presetListSlot;
   }

   public static List accessFLAT_WORLD_PRESETS() {
      return FLAT_WORLD_PRESETS;
   }



   public static class ListSlot extends GuiSlot {
      public int selectedIndex;
      public final GuiFlatPresets parentScreen;

      @Override
      public boolean isSelected(int n) {
         return n == this.selectedIndex;
      }

      public ListSlot(GuiFlatPresets guiFlatPresets) {
         super(guiFlatPresets.mc, guiFlatPresets.width, guiFlatPresets.height, 80, guiFlatPresets.height - 37, 24);
         this.parentScreen = guiFlatPresets;
         this.selectedIndex = -1;
      }

      private void drawSlotBackground(int n, int n2) {
         this.drawIconTexture(n, n2, 0, 0);
      }

      @Override
      public void elementClicked(int n, boolean flag, int n2, int n3) {
         this.selectedIndex = n;
         this.parentScreen.updateButtonValidity();
         GuiFlatPresets.accessExportTextField(this.parentScreen).setText(((GuiFlatPresets.LayerItem)GuiFlatPresets.accessFLAT_WORLD_PRESETS().get(GuiFlatPresets.accessPresetListSlot(this.parentScreen).selectedIndex)).generatorInfo);
      }

      @Override
      public void drawSlot(int n, int n2, int n3, int n4, int n5, int n6) {
         GuiFlatPresets.LayerItem layerItem = (GuiFlatPresets.LayerItem)GuiFlatPresets.accessFLAT_WORLD_PRESETS().get(n);
         this.drawPresetIcon(n2, n3, layerItem.icon, layerItem.iconMetadata);
         this.parentScreen.fontRendererObj.drawString(layerItem.name, n2 + 18 + 5, n3 + 6, 16777215);
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

      @Override
      public int getSize() {
         return GuiFlatPresets.accessFLAT_WORLD_PRESETS().size();
      }

      @Override
      public void drawBackground() {
      }

      private void drawPresetIcon(int n, int n2, Item item, int n3) {
         this.drawSlotBackground(n + 1, n2 + 1);
         GlStateManager.enableRescaleNormal();
         RenderHelper.enableGUIStandardItemLighting();
         this.parentScreen.itemRender.renderItemIntoGUI(new ItemStack(item, 1, n3), n + 2, n2 + 2);
         RenderHelper.disableStandardItemLighting();
         GlStateManager.disableRescaleNormal();
      }
   }

   public static class LayerItem {
      public Item icon;
      public int iconMetadata;
      public String name;
      public String generatorInfo;

      public LayerItem(Item item, int n, String str, String str2) {
         this.icon = item;
         this.iconMetadata = n;
         this.name = str;
         this.generatorInfo = str2;
      }
   }
}
