package net.minecraft.client.gui;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.world.gen.ChunkProviderSettings;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

public class GuiScreenCustomizePresets extends GuiScreen {
   private static final List PRESETS = Lists.newArrayList();
   private GuiScreenCustomizePresets.ListPreset list;
   private GuiButton select;
   private GuiTextField export;
   private GuiCustomizeWorldScreen parent;
   public String title = "Customize World Presets";
   private String shareText;
   private String listText;

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }

   public GuiScreenCustomizePresets(GuiCustomizeWorldScreen guiCustomizeWorldScreen) {
      this.parent = guiCustomizeWorldScreen;
   }

   static {
      ChunkProviderSettings.Factory factory = ChunkProviderSettings.Factory.jsonToFactory(
         "{ \"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":5000.0, \"mainNoiseScaleY\":1000.0, \"mainNoiseScaleZ\":5000.0, \"baseSize\":8.5, \"stretchY\":8.0, \"biomeDepthWeight\":2.0, \"biomeDepthOffset\":0.5, \"biomeScaleWeight\":2.0, \"biomeScaleOffset\":0.375, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":255 }"
      );
      ResourceLocation location = new ResourceLocation("textures/gui/presets/water.png");
      PRESETS.add(new GuiScreenCustomizePresets.Info(I18n.format("createWorld.customize.custom.preset.waterWorld", new Object[0]), location, factory));
      factory = ChunkProviderSettings.Factory.jsonToFactory(
         "{\"coordinateScale\":3000.0, \"heightScale\":6000.0, \"upperLimitScale\":250.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":80.0, \"mainNoiseScaleY\":160.0, \"mainNoiseScaleZ\":80.0, \"baseSize\":8.5, \"stretchY\":10.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":63 }"
      );
      location = new ResourceLocation("textures/gui/presets/isles.png");
      PRESETS.add(new GuiScreenCustomizePresets.Info(I18n.format("createWorld.customize.custom.preset.isleLand", new Object[0]), location, factory));
      factory = ChunkProviderSettings.Factory.jsonToFactory(
         "{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":5000.0, \"mainNoiseScaleY\":1000.0, \"mainNoiseScaleZ\":5000.0, \"baseSize\":8.5, \"stretchY\":5.0, \"biomeDepthWeight\":2.0, \"biomeDepthOffset\":1.0, \"biomeScaleWeight\":4.0, \"biomeScaleOffset\":1.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":63 }"
      );
      location = new ResourceLocation("textures/gui/presets/delight.png");
      PRESETS.add(new GuiScreenCustomizePresets.Info(I18n.format("createWorld.customize.custom.preset.caveDelight", new Object[0]), location, factory));
      factory = ChunkProviderSettings.Factory.jsonToFactory(
         "{\"coordinateScale\":738.41864, \"heightScale\":157.69133, \"upperLimitScale\":801.4267, \"lowerLimitScale\":1254.1643, \"depthNoiseScaleX\":374.93652, \"depthNoiseScaleZ\":288.65228, \"depthNoiseScaleExponent\":1.2092624, \"mainNoiseScaleX\":1355.9908, \"mainNoiseScaleY\":745.5343, \"mainNoiseScaleZ\":1183.464, \"baseSize\":1.8758626, \"stretchY\":1.7137525, \"biomeDepthWeight\":1.7553768, \"biomeDepthOffset\":3.4701107, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":2.535211, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":63 }"
      );
      location = new ResourceLocation("textures/gui/presets/madness.png");
      PRESETS.add(new GuiScreenCustomizePresets.Info(I18n.format("createWorld.customize.custom.preset.mountains", new Object[0]), location, factory));
      factory = ChunkProviderSettings.Factory.jsonToFactory(
         "{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":1000.0, \"mainNoiseScaleY\":3000.0, \"mainNoiseScaleZ\":1000.0, \"baseSize\":8.5, \"stretchY\":10.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":20 }"
      );
      location = new ResourceLocation("textures/gui/presets/drought.png");
      PRESETS.add(new GuiScreenCustomizePresets.Info(I18n.format("createWorld.customize.custom.preset.drought", new Object[0]), location, factory));
      factory = ChunkProviderSettings.Factory.jsonToFactory(
         "{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":2.0, \"lowerLimitScale\":64.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":80.0, \"mainNoiseScaleY\":160.0, \"mainNoiseScaleZ\":80.0, \"baseSize\":8.5, \"stretchY\":12.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":6 }"
      );
      location = new ResourceLocation("textures/gui/presets/chaos.png");
      PRESETS.add(new GuiScreenCustomizePresets.Info(I18n.format("createWorld.customize.custom.preset.caveChaos", new Object[0]), location, factory));
      factory = ChunkProviderSettings.Factory.jsonToFactory(
         "{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":80.0, \"mainNoiseScaleY\":160.0, \"mainNoiseScaleZ\":80.0, \"baseSize\":8.5, \"stretchY\":12.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":true, \"seaLevel\":40 }"
      );
      location = new ResourceLocation("textures/gui/presets/luck.png");
      PRESETS.add(new GuiScreenCustomizePresets.Info(I18n.format("createWorld.customize.custom.preset.goodLuck", new Object[0]), location, factory));
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      switch (button.id) {
         case 0:
            this.parent.loadValues(this.export.getText());
            GuiCustomizeWorldScreen parent2 = this.parent;
            this.mc.displayGuiScreen(parent2);
            break;
         case 1:
            GuiCustomizeWorldScreen parent3 = this.parent;
            this.mc.displayGuiScreen(parent3);
      }
   }

   @Override
   public void initGui() {
      this.buttonList.clear();
      Keyboard.enableRepeatEvents(true);
      this.title = I18n.format("createWorld.customize.custom.presets.title", new Object[0]);
      this.shareText = I18n.format("createWorld.customize.presets.share", new Object[0]);
      this.listText = I18n.format("createWorld.customize.presets.list", new Object[0]);
      this.export = new GuiTextField(2, this.fontRendererObj, 50, 40, this.width - 100, 20);
      this.list = new GuiScreenCustomizePresets.ListPreset(this);
      this.export.setMaxStringLength(2000);
      this.export.setText(this.parent.saveValues());
      this.buttonList
         .add(this.select = new GuiButton(0, this.width / 2 - 102, this.height - 27, 100, 20, I18n.format("createWorld.customize.presets.select", new Object[0])));
      this.buttonList.add(new GuiButton(1, this.width / 2 + 3, this.height - 27, 100, 20, I18n.format("gui.cancel", new Object[0])));
      this.updateButtonValidity();
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.list.drawScreen(mouseX, mouseY, partialTicks);
      drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 8, 16777215);
      this.drawString(this.fontRendererObj, this.shareText, 50, 30, 10526880);
      this.drawString(this.fontRendererObj, this.listText, 50, 70, 10526880);
      this.export.drawTextBox();
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Override
   public void updateScreen() {
      this.export.updateCursorCounter();
      super.updateScreen();
   }

   @Override
   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   public static GuiTextField accessExport(GuiScreenCustomizePresets presets) {
      return presets.export;
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      this.export.mouseClicked(mouseX, mouseY, mouseButton);
      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   public static List accessPRESETS() {
      return PRESETS;
   }

   public void updateButtonValidity() {
      this.select.enabled = this.hasValidSelection();
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if (!this.export.textboxKeyTyped(typedChar, keyCode)) {
         super.keyTyped(typedChar, keyCode);
      }
   }

   private boolean hasValidSelection() {
      return this.list.selected > -1 && this.list.selected < PRESETS.size() || this.export.getText().length() > 1;
   }

   public static GuiScreenCustomizePresets.ListPreset accessList(GuiScreenCustomizePresets presets) {
      return presets.list;
   }



   public static class Info {
      public String name;
      public ResourceLocation texture;
      public ChunkProviderSettings.Factory settings;

      public Info(String str, ResourceLocation location, ChunkProviderSettings.Factory factory) {
         this.name = str;
         this.texture = location;
         this.settings = factory;
      }
   }

   public static class ListPreset extends GuiSlot {
      public int selected;
      public final GuiScreenCustomizePresets presetsScreen;

      @Override
      public void elementClicked(int n, boolean flag, int n2, int n3) {
         this.selected = n;
         this.presetsScreen.updateButtonValidity();
         GuiScreenCustomizePresets.accessExport(this.presetsScreen)
            .setText(
               ((GuiScreenCustomizePresets.Info)GuiScreenCustomizePresets.accessPRESETS().get(GuiScreenCustomizePresets.accessList(this.presetsScreen).selected)).settings.toString()
            );
      }

      @Override
      public int getSize() {
         return GuiScreenCustomizePresets.accessPRESETS().size();
      }

      @Override
      public void drawSlot(int n, int n2, int n3, int n4, int n5, int n6) {
         GuiScreenCustomizePresets.Info info = (GuiScreenCustomizePresets.Info)GuiScreenCustomizePresets.accessPRESETS().get(n);
         this.drawPresetIcon(n2, n3, info.texture);
         this.presetsScreen.fontRendererObj.drawString(info.name, n2 + 32 + 10, n3 + 14, 16777215);
      }

      private void drawPresetIcon(int n, int n2, ResourceLocation location) {
         int n3 = n + 5;
         this.presetsScreen.drawHorizontalLine(n3 - 1, n3 + 32, n2 - 1, -2039584);
         this.presetsScreen.drawHorizontalLine(n3 - 1, n3 + 32, n2 + 32, -6250336);
         this.presetsScreen.drawVerticalLine(n3 - 1, n2 - 1, n2 + 32, -2039584);
         this.presetsScreen.drawVerticalLine(n3 + 32, n2 - 1, n2 + 32, -6250336);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(location);
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
         worldRenderer.pos((double)(n3 + 0), (double)(n2 + 32), 0.0).tex(0.0, 1.0).endVertex();
         worldRenderer.pos((double)(n3 + 32), (double)(n2 + 32), 0.0).tex(1.0, 1.0).endVertex();
         worldRenderer.pos((double)(n3 + 32), (double)(n2 + 0), 0.0).tex(1.0, 0.0).endVertex();
         worldRenderer.pos((double)(n3 + 0), (double)(n2 + 0), 0.0).tex(0.0, 0.0).endVertex();
         instance.draw();
      }

      @Override
      public void drawBackground() {
      }

      public ListPreset(GuiScreenCustomizePresets presets) {
         super(presets.mc, presets.width, presets.height, 80, presets.height - 32, 38);
         this.presetsScreen = presets;
         this.selected = -1;
      }

      @Override
      public boolean isSelected(int n) {
         return n == this.selected;
      }
   }
}
