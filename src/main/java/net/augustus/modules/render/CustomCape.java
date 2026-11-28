package net.augustus.modules.render;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.render.cape.WaveyCapeSimulationManager;
import net.augustus.utils.file.DirectoryWatchService;
import net.augustus.render.cape.CapeWindMode;
import net.augustus.render.cape.CapeMovementMode;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.DirectoryWatcher;
import net.augustus.utils.file.ClientPaths;
import net.augustus.settings.ActionSetting;
import net.augustus.render.cape.CapeStyle;
import net.augustus.render.BufferedImageTextureUtil;
import net.augustus.render.cape.WaveyCapeConfig;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ResourceLocation;
import net.optifine.player.CapeUtils;

public class CustomCape extends Module implements TickListener {
   private final String customCapesDir;
   public final StringValue type;
   public final StringValue labyModCapes;
   public final ActionSetting openFolder;
   public final StringValue mojangCapes;
   public final BooleanValue capePhysics;
   public final StringValue style;
   public final StringValue movement;
   public final StringValue wind;
   public final DoubleValue gravity;
   public final DoubleValue heightMultiplier;
   public final HashMap customCapeTextures;
   public final HashMap mojangCapeResources;

   public CustomCape() {
       super("CustomCape", "KundenspezifischeKap", new Color(235, 146, 52), Categorys.RENDER);
      this.customCapesDir = ClientPaths.CLIENT_DIR + File.separator + "customcapes";
      this.type = new StringValue(this, "Type", "LabyMod", new String[]{"Mojang", "LabyMod"});
      this.labyModCapes = (StringValue)new StringValue(this, "LabyModCapes", "Augustus", new String[0]).withVisibility(() -> this.isLabyModCapesVisible());
      this.openFolder = (ActionSetting)new ActionSetting(this, "OpenFolder", () -> this.openCustomCapesFolder()).withVisibility(() -> this.isOpenFolderVisible());
      StringValue setting = new StringValue(this, "MojangCapes", "Migrator", new String[0]);
      SettingVisibilityCondition condition3 = () -> {
         return this.type.getSelectedValue().equals("Mojang");
      };
      this.mojangCapes = (StringValue)setting.withVisibility(condition3);
      this.capePhysics = new BooleanValue(this, "CapePhysics", true);
      StringValue setting2 = new StringValue(this, "Style", "Smooth", new String[]{"Smooth", "Blocky"});
      BooleanValue capePhysics2 = this.capePhysics;
      BooleanValue booleanValue = capePhysics2;
      this.style = (StringValue)setting2.withVisibility(() -> booleanValue.getValue());
      StringValue setting3 = new StringValue(this, "Movement", "Simulation", new String[]{"Simulation", "Vanilla"});
      capePhysics2 = this.capePhysics;
      BooleanValue booleanValue2 = capePhysics2;
      this.movement = (StringValue)setting3.withVisibility(() -> booleanValue2.getValue());
      StringValue setting4 = new StringValue(this, "Wind", "None", new String[]{"None", "Waves"});
      capePhysics2 = this.capePhysics;
      BooleanValue booleanValue3 = capePhysics2;
      this.wind = (StringValue)setting4.withVisibility(() -> booleanValue3.getValue());
      DoubleValue gravitySetting = new DoubleValue(this, "Gravity", 25.0, 5.0, 32.0, 0);
      SettingVisibilityCondition condition7 = () -> {
         return this.capePhysics.getValue() && this.movement.getSelectedValue().equals("Simulation");
      };
      this.gravity = gravitySetting.visibleWhen(condition7);
      this.heightMultiplier = new DoubleValue(this, "HeightMultiplier", 6.0, 4.0, 16.0, 0).visibleWhen(() -> this.isHeightMultiplierVisible());
      this.customCapeTextures = new HashMap();
      this.mojangCapeResources = new HashMap();
      ClientFileUtil.ensureDirectory(this.customCapesDir);
      String str = Augustus.getInstance().getName().toLowerCase() + "/customcapes";
      ClientFileUtil.extractResource("assets/augustus/cape/Sky.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Augustus.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/AugustusAmethyst.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/AugustusCandy.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/AugustusClassic.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/AugustusMagic.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/AugustusMagma.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/AugustusMango.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/AugustusRose.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/AugustusTitanium.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Ejected.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/BlurSky.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/BlurSky2.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/HorizonSunset by Xolitey.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/NightCity.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Pepe.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Pineapples.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Rose.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Esound.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Dog.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/AugustusLightBlue.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/GigachadSound.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/NoobiiHome.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/GommeHD.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Seal.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Questionmark.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Creeper.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Karhu.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Moon.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Astolfo.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Astolfotrap.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/AstolfoBeta.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/AstolfoBeta2.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/E.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Grim.png", str, false);
      ClientFileUtil.extractResource("assets/augustus/cape/Otter.png", str, false);
      this.loadCustomCapes();
      this.loadMojangCapes();
      DirectoryWatcher directoryWatcher = DirectoryWatchService.watch(this.customCapesDir);
      directoryWatcher = directoryWatcher.onCreate(obj -> this.onCapeFileChanged((Path)obj));
      Consumer consumer2 = obj2 -> {
         this.loadCapeFile(((Path)obj2).toFile());
         this.updateCustomCapeOptions();
      };
      directoryWatcher = directoryWatcher.onModify(consumer2);
      Consumer consumer3 = obj3 -> {
         String name2 = ((Path)obj3).toFile().getName();
         if (name2.endsWith(".png")) {
            String str2 = name2.substring(0, name2.length() - 4);
            this.customCapeTextures.remove(str2);
            this.updateCustomCapeOptions();
         }
      };
      directoryWatcher.onDelete(consumer3).register();
   }

   @Override
   public void onEnable() {
      if (mc.thePlayer != null && mc.thePlayer.getPlayerInfo() != null) {
         mc.thePlayer.getPlayerInfo().playerTexturesLoaded = false;
         mc.thePlayer.getPlayerInfo().loadPlayerTextures();
      }
   }

   @Override
   public void onDisable() {
      if (mc.thePlayer != null && mc.thePlayer.getPlayerInfo() != null) {
         mc.thePlayer.getPlayerInfo().playerTexturesLoaded = false;
         mc.thePlayer.getPlayerInfo().loadPlayerTextures();
      }

      WaveyCapeSimulationManager.getWaveyCapeSimulationManager().clearAll();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   @Override
   public void onPreTick() {
      this.syncWaveyCapeConfig();
      if (mc.thePlayer != null && this.movement.getSelectedValue().equals("Simulation")) {
         WaveyCapeSimulationManager.getWaveyCapeSimulationManager().tickPlayer(mc.thePlayer);
      }
   }

   private void syncWaveyCapeConfig() {
      WaveyCapeConfig config = WaveyCapeSimulationManager.getWaveyCapeSimulationManager().getConfig();
      config.capeStyle = this.style.getSelectedValue().equals("Smooth") ? CapeStyle.SMOOTH : CapeStyle.BLOCKY;
      config.capeMovement = this.movement.getSelectedValue().equals("Simulation") ? CapeMovementMode.BASIC_SIMULATION : CapeMovementMode.VANILLA;
      config.windMode = this.wind.getSelectedValue().equals("Waves") ? CapeWindMode.WAVES : CapeWindMode.NONE;
      config.gravity = (int)this.gravity.getValue();
      config.heightMultiplier = (int)this.heightMultiplier.getValue();
   }

   public WaveyCapeConfig getWaveyCapeConfig() {
      this.syncWaveyCapeConfig();
      return WaveyCapeSimulationManager.getWaveyCapeSimulationManager().getConfig();
   }

   public void loadMojangCapes() {
      java.util.ArrayList list = new java.util.ArrayList();
      String str = "cape/mojang/";
      List list2 = Arrays.asList(
         "Birthday.png",
         "Cobalt.png",
         "dB.png",
         "Migrator.png",
         "MillionthCustomer.png",
         "MineCon2011.png",
         "MineCon2012.png",
         "MineCon2013.png",
         "MineCon2015.png",
         "MineCon2016.png",
         "Mojang.png",
         "MojangClassic.png",
         "MojangStudios.png",
         "MojiraModerator.png",
         "Prismarine.png",
         "RealmsMapmaker.png",
         "Scrolls.png",
         "Snowman.png",
         "Spade.png",
         "Translator.png",
         "Translator(Chinese).png",
         "Translator(Japanese).png",
         "Turtle.png",
         "Valentine.png",
         "Vanilla.png",
         "Founders.png",
         "MCExperience.png",
         "15Year.png",
         "Bacon.png",
         "CherryBlossom.png",
         "Eyeblossom.png",
         "Frog.png",
         "Home.png",
         "MCChampionship.png",
         "Menace.png",
         "NewYears.png",
         "Oxeye.png",
         "Snail.png",
         "Sniffer.png",
         "TikTok.png",
         "Twitch.png",
         "Xmas.png",
         "Yearn.png"
      );
      this.mojangCapeResources.clear();

      for (String str2 : (Iterable<String>)(list2)) {
         String[] parts = str2.split("\\.");
         String str3 = str2.replace("." + parts[parts.length - 1], "");
         this.mojangCapeResources.put(str3, new ResourceLocation("augustus", str + str2));
         list.add(str3);
      }

      boolean flag = false;
      String[] strings2 = new String[list.size()];

      for (int i = 0; i < strings2.length; i++) {
         String str4 = (String)list.get(i);
         strings2[i] = str4;
         if (this.mojangCapes.getSelectedValue().equals(str4)) {
            flag = true;
         }
      }

      if (!flag) {
         String str5 = strings2[0];
         this.mojangCapes.setSelectedValue(str5);
      }

      this.mojangCapes.setOptions(strings2);
   }

   public void loadCustomCapes() {
      File[] files = new File(this.customCapesDir).listFiles();
      if (files != null) {
         for (File file2 : files) {
            this.loadCapeFile(file2);
         }
      }

      this.updateCustomCapeOptions();
   }

   private void loadCapeFile(File file) {

      try {
         String name2 = file.getName();
         if (name2.endsWith(".png")) {
            String str = name2.substring(0, name2.length() - 4);
            if (!str.isEmpty()) {
               this.customCapeTextures.put(str, BufferedImageTextureUtil.uploadTexture(CapeUtils.parseCape(ImageIO.read(file.getAbsoluteFile()))));
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void updateCustomCapeOptions() {
      if (!this.customCapeTextures.keySet().stream().anyMatch(obj -> this.isSelectedCustomCape((String)obj)) && !this.customCapeTextures.isEmpty()) {
         this.labyModCapes.setSelectedValue((String)this.customCapeTextures.keySet().iterator().next());
      }

      StringValue labyModCapes3 = this.labyModCapes;
      String[] strings = ((Map<String, ?>)this.customCapeTextures).keySet().stream().sorted(Comparator.comparing(String::toLowerCase)).toArray(String[]::new);
      labyModCapes3.setOptions(strings);
   }

   private boolean isSelectedCustomCape(String str) {
      return this.labyModCapes.getSelectedValue().equals(str);
   }

   private void onCapeFileChanged(Path path) {
      this.loadCapeFile(path.toFile());
      this.updateCustomCapeOptions();
   }

   private boolean isHeightMultiplierVisible() {
      return this.capePhysics.getValue() && this.movement.getSelectedValue().equals("Simulation");
   }

   private boolean isOpenFolderVisible() {
      return this.type.getSelectedValue().equals("LabyMod");
   }

   private void openCustomCapesFolder() {
      ClientFileUtil.openFolder(this.customCapesDir);
   }

   private boolean isLabyModCapesVisible() {
      return this.type.getSelectedValue().equals("LabyMod");
   }

}
