package net.minecraft.client.gui;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import client.setting.MovementColorProperty;
import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.ClassicFlatGuiScreenExtendedMinecraftEntry;
import client.util.NullManager;
import client.util.VillageManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.Items;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

public class ClassicFlatGuiScreen extends GuiScreen {
   public static final List list3 = Lists.newArrayList();
   public final CreateWorldCustomizeGuiScreen createWorldCustomizeGuiScreen;
   public String labelText;
   public String labelText2;
   public String labelText3;
   public ClassicFlatGuiScreenExtendedMinecraftEntry classicFlatGuiScreenExtendedMinecraftEntry;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;

   public static InternalTexturesGuiIconsPngManagerAdapter resolveInternalTexturesGuiIconsPngM(ClassicFlatGuiScreen classicFlatGuiScreen) {
      return classicFlatGuiScreen.internalTexturesGuiIconsPngManagerAdapter;
   }

   static {
      applyText(
         "Classic Flat",
         Item.processAction2(Blocks.blockGrass),
         BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35,
         Arrays.asList("village"),
         new NullManager[]{new NullManager(1, Blocks.blockGrass), new NullManager(2, Blocks.block115), new NullManager(1, Blocks.block95)}
      );
      applyText(
         "Tunnelers' Dream",
         Item.processAction2(Blocks.stone),
         BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager21,
         Arrays.asList("biome_1", "dungeon", "decoration", "stronghold", "mineshaft"),
         new NullManager[]{
            new NullManager(1, Blocks.blockGrass), new NullManager(5, Blocks.block115), new NullManager(230, Blocks.stone), new NullManager(1, Blocks.block95)
         }
      );
      applyText(
         "Water World",
         Items.waterBucket,
         BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager39,
         Arrays.asList("biome_1", "oceanmonument"),
         new NullManager[]{
            new NullManager(90, Blocks.blockLava2),
            new NullManager(5, Blocks.blockSand),
            new NullManager(5, Blocks.block115),
            new NullManager(5, Blocks.stone),
            new NullManager(1, Blocks.block95)
         }
      );
      applyText(
         "Overworld",
         Item.processAction2(Blocks.blockTallgrass),
         MovementColorProperty.GRASS.getCount(),
         BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35,
         Arrays.asList("village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon", "lake", "lava_lake"),
         new NullManager[]{
            new NullManager(1, Blocks.blockGrass), new NullManager(3, Blocks.block115), new NullManager(59, Blocks.stone), new NullManager(1, Blocks.block95)
         }
      );
      applyText(
         "Snowy Kingdom",
         Item.processAction2(Blocks.block74),
         BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager14,
         Arrays.asList("village", "biome_1"),
         new NullManager[]{
            new NullManager(1, Blocks.block74),
            new NullManager(1, Blocks.blockGrass),
            new NullManager(3, Blocks.block115),
            new NullManager(59, Blocks.stone),
            new NullManager(1, Blocks.block95)
         }
      );
      applyText(
         "Bottomless Pit",
         Items.feather,
         BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35,
         Arrays.asList("village", "biome_1"),
         new NullManager[]{new NullManager(1, Blocks.blockGrass), new NullManager(3, Blocks.block115), new NullManager(2, Blocks.block65)}
      );
      applyText(
         "Desert",
         Item.processAction2(Blocks.blockSand),
         BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17,
         Arrays.asList("village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon"),
         new NullManager[]{
            new NullManager(8, Blocks.blockSand), new NullManager(52, Blocks.block136), new NullManager(3, Blocks.stone), new NullManager(1, Blocks.block95)
         }
      );
      applyText(
         "Redstone Ready",
         Items.redstone,
         BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager17,
         new NullManager[]{new NullManager(52, Blocks.block136), new NullManager(3, Blocks.stone), new NullManager(1, Blocks.block95)}
      );
   }

   public ClassicFlatGuiScreen(CreateWorldCustomizeGuiScreen createWorldCustomizeGuiScreen2) {
      this.createWorldCustomizeGuiScreen = createWorldCustomizeGuiScreen2;
   }

   public static ClassicFlatGuiScreenExtendedMinecraftEntry resolveClassicFlatGuiScreenExtended(ClassicFlatGuiScreen classicFlatGuiScreen) {
      return classicFlatGuiScreen.classicFlatGuiScreenExtendedMinecraftEntry;
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      this.classicFlatGuiScreenExtendedMinecraftEntry.applyIndex(count, count2, factor);
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 8, 16777215);
      this.applyFontRenderer(this.fontRenderer, this.labelText2, 50, 30, 10526880);
      this.applyFontRenderer(this.fontRenderer, this.labelText3, 50, 70, 10526880);
      this.internalTexturesGuiIconsPngManagerAdapter.updateState();
      super.applyIndex(count, count2, factor);
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

   public void renderElement() {
      boolean active = this.isActive4();
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = active;
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.classicFlatGuiScreenExtendedMinecraftEntry.updateState();
   }

   @Override
   public void enableRepeatEvents() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void updateState2() {
      this.list.clear();
      Keyboard.enableRepeatEvents(true);
      this.labelText = StatCollector.getText("createWorld.customize.presets.title", new Object[0]);
      this.labelText2 = StatCollector.getText("createWorld.customize.presets.share", new Object[0]);
      this.labelText3 = StatCollector.getText("createWorld.customize.presets.list", new Object[0]);
      this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(2, this.fontRenderer, 50, 40, this.width - 100, 20);
      this.classicFlatGuiScreenExtendedMinecraftEntry = new ClassicFlatGuiScreenExtendedMinecraftEntry(this);
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex3(1230);
      this.internalTexturesGuiIconsPngManagerAdapter.applyText(this.createWorldCustomizeGuiScreen.getProperty());
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 - 155, this.height - 28, 150, 20, StatCollector.getText("createWorld.customize.presets.select", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(1, this.width / 2 + 5, this.height - 28, 150, 20, StatCollector.getText("gui.cancel", new Object[0]))
         );
      this.renderElement();
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0 && this.isActive4()) {
         this.createWorldCustomizeGuiScreen.setProperty(this.internalTexturesGuiIconsPngManagerAdapter.getLabelText());
         CreateWorldCustomizeGuiScreen createWorldCustomizeGuiScreen2 = this.createWorldCustomizeGuiScreen;
         this.mc.displayGuiScreen(createWorldCustomizeGuiScreen2);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         CreateWorldCustomizeGuiScreen createWorldCustomizeGuiScreen3 = this.createWorldCustomizeGuiScreen;
         this.mc.displayGuiScreen(createWorldCustomizeGuiScreen3);
      }
   }

   private static void applyText(String text, Item item, BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager, NullManager[] nullManagerArray) {
      applyText(text, item, 0, biomeIDIsOutOfBoundsManager, (List)null, nullManagerArray);
   }

   private static void applyText(String text, Item item, int count, BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager, List list, NullManager[] nullManagerArray) {
      VillageManager villageManager = new VillageManager();

      for (int index = nullManagerArray.length - 1; index >= 0; index--) {
         villageManager.getList().add(nullManagerArray[index]);
      }

      villageManager.setCount(biomeIDIsOutOfBoundsManager.count);
      villageManager.updateState();
      if (list != null) {
         for (String text2 : (Iterable<String>)(Iterable<?>)list) {
            villageManager.getMap().put(text2, Maps.newHashMap());
         }
      }

      list3.add(new ItemRecord(item, count, text, villageManager.toString()));
   }

   public static List getList3() {
      return list3;
   }

   private boolean isActive4() {
      return this.classicFlatGuiScreenExtendedMinecraftEntry.count15 > -1 && this.classicFlatGuiScreenExtendedMinecraftEntry.count15 < list3.size()
         || this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().length() > 1;
   }

   private static void applyText(String text, Item item, BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager, List list, NullManager[] nullManagerArray) {
      applyText(text, item, 0, biomeIDIsOutOfBoundsManager, list, nullManagerArray);
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (!this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count)) {
         super.applyChar(character, count);
      }
   }
}
