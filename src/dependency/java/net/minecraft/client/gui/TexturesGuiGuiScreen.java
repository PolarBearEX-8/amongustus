package net.minecraft.client.gui;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import client.util.AuxiliaryGsonEntry;
import client.util.ResourceLocationRecord;
import client.util.TexturesGuiGuiScreenExtendedMinecraftEntry;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

public class TexturesGuiGuiScreen extends GuiScreen {
   public static final List list3 = Lists.newArrayList();
   public TexturesGuiGuiScreenExtendedMinecraftEntry texturesGuiGuiScreenExtendedMinecraftEntry;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
   public OptionsCustomizeTitleGuiScreen optionsCustomizeTitleGuiScreen;
   public String labelText = "Customize World Presets";
   public String labelText2;
   public String labelText3;

   static {
      AuxiliaryGsonEntry auxiliaryGsonEntry = AuxiliaryGsonEntry.resolveAuxiliaryGsonEntry(
         "{ \"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":5000.0, \"mainNoiseScaleY\":1000.0, \"mainNoiseScaleZ\":5000.0, \"baseSize\":8.5, \"stretchY\":8.0, \"biomeDepthWeight\":2.0, \"biomeDepthOffset\":0.5, \"biomeScaleWeight\":2.0, \"biomeScaleOffset\":0.375, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":255 }"
      );
      ResourceLocation location = new ResourceLocation("textures/gui/presets/water.png");
      list3.add(new ResourceLocationRecord(StatCollector.getText("createWorld.customize.custom.preset.waterWorld", new Object[0]), location, auxiliaryGsonEntry));
      AuxiliaryGsonEntry auxiliaryGsonEntry2 = AuxiliaryGsonEntry.resolveAuxiliaryGsonEntry(
         "{\"coordinateScale\":3000.0, \"heightScale\":6000.0, \"upperLimitScale\":250.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":80.0, \"mainNoiseScaleY\":160.0, \"mainNoiseScaleZ\":80.0, \"baseSize\":8.5, \"stretchY\":10.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":63 }"
      );
      ResourceLocation location2 = new ResourceLocation("textures/gui/presets/isles.png");
      list3.add(new ResourceLocationRecord(StatCollector.getText("createWorld.customize.custom.preset.isleLand", new Object[0]), location2, auxiliaryGsonEntry2));
      AuxiliaryGsonEntry auxiliaryGsonEntry3 = AuxiliaryGsonEntry.resolveAuxiliaryGsonEntry(
         "{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":5000.0, \"mainNoiseScaleY\":1000.0, \"mainNoiseScaleZ\":5000.0, \"baseSize\":8.5, \"stretchY\":5.0, \"biomeDepthWeight\":2.0, \"biomeDepthOffset\":1.0, \"biomeScaleWeight\":4.0, \"biomeScaleOffset\":1.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":63 }"
      );
      ResourceLocation location3 = new ResourceLocation("textures/gui/presets/delight.png");
      list3.add(new ResourceLocationRecord(StatCollector.getText("createWorld.customize.custom.preset.caveDelight", new Object[0]), location3, auxiliaryGsonEntry3));
      AuxiliaryGsonEntry auxiliaryGsonEntry4 = AuxiliaryGsonEntry.resolveAuxiliaryGsonEntry(
         "{\"coordinateScale\":738.41864, \"heightScale\":157.69133, \"upperLimitScale\":801.4267, \"lowerLimitScale\":1254.1643, \"depthNoiseScaleX\":374.93652, \"depthNoiseScaleZ\":288.65228, \"depthNoiseScaleExponent\":1.2092624, \"mainNoiseScaleX\":1355.9908, \"mainNoiseScaleY\":745.5343, \"mainNoiseScaleZ\":1183.464, \"baseSize\":1.8758626, \"stretchY\":1.7137525, \"biomeDepthWeight\":1.7553768, \"biomeDepthOffset\":3.4701107, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":2.535211, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":63 }"
      );
      ResourceLocation location4 = new ResourceLocation("textures/gui/presets/madness.png");
      list3.add(new ResourceLocationRecord(StatCollector.getText("createWorld.customize.custom.preset.mountains", new Object[0]), location4, auxiliaryGsonEntry4));
      AuxiliaryGsonEntry auxiliaryGsonEntry5 = AuxiliaryGsonEntry.resolveAuxiliaryGsonEntry(
         "{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":1000.0, \"mainNoiseScaleY\":3000.0, \"mainNoiseScaleZ\":1000.0, \"baseSize\":8.5, \"stretchY\":10.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":20 }"
      );
      ResourceLocation location5 = new ResourceLocation("textures/gui/presets/drought.png");
      list3.add(new ResourceLocationRecord(StatCollector.getText("createWorld.customize.custom.preset.drought", new Object[0]), location5, auxiliaryGsonEntry5));
      AuxiliaryGsonEntry auxiliaryGsonEntry6 = AuxiliaryGsonEntry.resolveAuxiliaryGsonEntry(
         "{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":2.0, \"lowerLimitScale\":64.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":80.0, \"mainNoiseScaleY\":160.0, \"mainNoiseScaleZ\":80.0, \"baseSize\":8.5, \"stretchY\":12.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":6 }"
      );
      ResourceLocation location6 = new ResourceLocation("textures/gui/presets/chaos.png");
      list3.add(new ResourceLocationRecord(StatCollector.getText("createWorld.customize.custom.preset.caveChaos", new Object[0]), location6, auxiliaryGsonEntry6));
      AuxiliaryGsonEntry auxiliaryGsonEntry7 = AuxiliaryGsonEntry.resolveAuxiliaryGsonEntry(
         "{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":80.0, \"mainNoiseScaleY\":160.0, \"mainNoiseScaleZ\":80.0, \"baseSize\":8.5, \"stretchY\":12.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":true, \"seaLevel\":40 }"
      );
      ResourceLocation location7 = new ResourceLocation("textures/gui/presets/luck.png");
      list3.add(new ResourceLocationRecord(StatCollector.getText("createWorld.customize.custom.preset.goodLuck", new Object[0]), location7, auxiliaryGsonEntry7));
   }

   @Override
   public void enableRepeatEvents() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      this.texturesGuiGuiScreenExtendedMinecraftEntry.applyIndex(count, count2, factor);
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 8, 16777215);
      this.applyFontRenderer(this.fontRenderer, this.labelText2, 50, 30, 10526880);
      this.applyFontRenderer(this.fontRenderer, this.labelText3, 50, 70, 10526880);
      this.internalTexturesGuiIconsPngManagerAdapter.updateState();
      super.applyIndex(count, count2, factor);
   }

   public void renderElement() {
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = this.isActive4();
   }

   @Override
   public void updateState2() {
      this.list.clear();
      Keyboard.enableRepeatEvents(true);
      this.labelText = StatCollector.getText("createWorld.customize.custom.presets.title", new Object[0]);
      this.labelText2 = StatCollector.getText("createWorld.customize.presets.share", new Object[0]);
      this.labelText3 = StatCollector.getText("createWorld.customize.presets.list", new Object[0]);
      this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(2, this.fontRenderer, 50, 40, this.width - 100, 20);
      this.texturesGuiGuiScreenExtendedMinecraftEntry = new TexturesGuiGuiScreenExtendedMinecraftEntry(this);
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex3(2000);
      this.internalTexturesGuiIconsPngManagerAdapter.applyText(this.optionsCustomizeTitleGuiScreen.getTextAsText());
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 - 102, this.height - 27, 100, 20, StatCollector.getText("createWorld.customize.presets.select", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(1, this.width / 2 + 3, this.height - 27, 100, 20, StatCollector.getText("gui.cancel", new Object[0]))
         );
      this.renderElement();
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (!this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count)) {
         super.applyChar(character, count);
      }
   }

   public static InternalTexturesGuiIconsPngManagerAdapter resolveInternalTexturesGuiIconsPngM(TexturesGuiGuiScreen texturesGuiGuiScreen) {
      return texturesGuiGuiScreen.internalTexturesGuiIconsPngManagerAdapter;
   }

   public static TexturesGuiGuiScreenExtendedMinecraftEntry resolveTexturesGuiGuiScreenExtended(TexturesGuiGuiScreen texturesGuiGuiScreen) {
      return texturesGuiGuiScreen.texturesGuiGuiScreenExtendedMinecraftEntry;
   }

   private boolean isActive4() {
      return this.texturesGuiGuiScreenExtendedMinecraftEntry.count15 > -1 && this.texturesGuiGuiScreenExtendedMinecraftEntry.count15 < list3.size()
         || this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().length() > 1;
   }

   public TexturesGuiGuiScreen(OptionsCustomizeTitleGuiScreen optionsCustomizeTitleGuiScreen2) {
      this.optionsCustomizeTitleGuiScreen = optionsCustomizeTitleGuiScreen2;
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      switch (auxiliaryTexturesGuiIconsPngManagerAdapter.count2) {
         case 0:
            this.optionsCustomizeTitleGuiScreen.applyText(this.internalTexturesGuiIconsPngManagerAdapter.getLabelText());
            OptionsCustomizeTitleGuiScreen optionsCustomizeTitleGuiScreen2 = this.optionsCustomizeTitleGuiScreen;
            this.mc.displayGuiScreen(optionsCustomizeTitleGuiScreen2);
            break;
         case 1:
            OptionsCustomizeTitleGuiScreen optionsCustomizeTitleGuiScreen3 = this.optionsCustomizeTitleGuiScreen;
            this.mc.displayGuiScreen(optionsCustomizeTitleGuiScreen3);
      }
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.texturesGuiGuiScreenExtendedMinecraftEntry.updateState();
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex(count, count2, count3);
      super.processAction(count, count2, count3);
   }

   @Override
   public void updateState4() {
      this.internalTexturesGuiIconsPngManagerAdapter.renderElement();
      super.updateState4();
   }

   public static List getList3() {
      return list3;
   }
}
