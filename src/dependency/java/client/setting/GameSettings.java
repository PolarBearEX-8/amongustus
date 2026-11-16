package client.setting;

import client.gui.OptifineGuiContainerManager;
import client.util.AimFactory;
import client.util.BuildManager;
import client.util.CurveEstimator;
import client.util.CurveFilter;
import client.util.GeneratorDefaultManager;
import client.util.ListenerAdapter27;
import client.util.LoadingManager;
import client.util.McpatcherSkyWorldManager;
import client.util.NotFoundManager;
import client.util.OptifineRandomManager;
import client.util.ParameterizedTypeAdapter;
import client.util.PreEndHandManager;
import client.util.TargetEstimator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GameSettings {
   public static final int indexOffset108 = 0;
   public static final int indexOffset9 = 1;
   public static final int indexOffset128 = 2;
   public static final int indexOffset23 = 3;
   public static final int indexOffset19 = 4;
   public static final int indexOffset52 = 0;
   public static final int indexOffset90 = 1;
   public static final int indexOffset62 = 2;
   public static final String text55 = "Default";
   public static final Logger logger = LogManager.getLogger();
   public static final Gson gson = new Gson();
   public static final ParameterizedType parameterizedType = new ParameterizedTypeAdapter();
   public static final String[] stringArray = new String[]{
      "options.guiScale.auto", "options.guiScale.small", "options.guiScale.normal", "options.guiScale.large"
   };
   public static final String[] stringArray2 = new String[]{"options.particles.all", "options.particles.decreased", "options.particles.minimal"};
   public static final String[] stringArray3 = new String[]{"options.ao.off", "options.ao.min", "options.ao.max"};
   public static final String[] stringArray4 = new String[]{
      "options.stream.compression.low", "options.stream.compression.medium", "options.stream.compression.high"
   };
   public static final String[] stringArray5 = new String[]{
      "options.stream.chat.enabled.streaming", "options.stream.chat.enabled.always", "options.stream.chat.enabled.never"
   };
   public static final String[] stringArray6 = new String[]{
      "options.stream.chat.userFilter.all", "options.stream.chat.userFilter.subs", "options.stream.chat.userFilter.mods"
   };
   public static final String[] stringArray7 = new String[]{"options.stream.mic_toggle.mute", "options.stream.mic_toggle.talk"};
   public static final String[] stringArray8 = new String[]{"options.off", "options.graphics.fast", "options.graphics.fancy"};
   public static final int[] intValueArray = new int[]{0, 1, 4, 2};
   public static final int[] intValueArray2 = new int[]{3, 1, 2};
   public static final String[] stringArray9 = new String[]{"options.off", "options.graphics.fast", "options.graphics.fancy"};
   public final Set entrySet = Sets.newHashSet(TabListCapeType.values());
   public final Map entryMap = Maps.newEnumMap(AutoPlayTargetEntity.class);
   public float scaleFactor = 0.5F;
   public boolean state20;
   public int indexOffset16 = -1;
   public boolean state28 = true;
   public boolean state105;
   public boolean state96 = true;
   public int indexOffset35 = 120;
   public int indexOffset33 = 2;
   public boolean state5 = true;
   public int indexOffset36 = 2;
   public List itemList = Lists.newArrayList();
   public List itemList2 = Lists.newArrayList();
   public GameSettingsFullType gameSettingsFullType = GameSettingsFullType.FULL;
   public boolean state27 = true;
   public boolean state13 = true;
   public boolean state124 = true;
   public float scaleFactor102 = 1.0F;
   public boolean state115 = true;
   public boolean state81;
   public boolean state26 = true;
   public boolean state112 = false;
   public boolean state123 = true;
   public boolean state22 = false;
   public boolean state129;
   public boolean state136;
   public boolean state72 = true;
   public boolean state48;
   public int indexOffset65;
   public int indexOffset73;
   public boolean state82 = true;
   public float scaleFactor29 = 1.0F;
   public float scaleFactor137 = 1.0F;
   public float scaleFactor8 = 0.44366196F;
   public float scaleFactor41 = 1.0F;
   public boolean state107 = true;
   public int indexOffset118 = 4;
   public float scaleFactor97 = 0.5F;
   public float scaleFactor131 = 1.0F;
   public float scaleFactor12 = 1.0F;
   public float scaleFactor59 = 0.5412844F;
   public float scaleFactor10 = 0.31690142F;
   public int indexOffset88 = 1;
   public boolean state116 = true;
   public String text = "";
   public int indexOffset99 = 0;
   public int indexOffset63 = 0;
   public int indexOffset94 = 0;
   public boolean state98 = true;
   public boolean state91 = true;
   public KeyBinding keyBindForward = new KeyBinding("key.forward", 17, "key.categories.movement");
   public KeyBinding keyBindLeft = new KeyBinding("key.left", 30, "key.categories.movement");
   public KeyBinding keyBindBack = new KeyBinding("key.back", 31, "key.categories.movement");
   public KeyBinding keyBindRight = new KeyBinding("key.right", 32, "key.categories.movement");
   public KeyBinding keyBindJump = new KeyBinding("key.jump", 57, "key.categories.movement");
   public KeyBinding keyBindSneak = new KeyBinding("key.sneak", 42, "key.categories.movement");
   public KeyBinding keyBindSprint = new KeyBinding("key.sprint", 29, "key.categories.movement");
   public KeyBinding keyBindInventory = new KeyBinding("key.inventory", 18, "key.categories.inventory");
   public KeyBinding keyBindUseItem = new KeyBinding("key.use", -99, "key.categories.gameplay");
   public KeyBinding keyBindDrop = new KeyBinding("key.drop", 16, "key.categories.gameplay");
   public KeyBinding keyBindAttack = new KeyBinding("key.attack", -100, "key.categories.gameplay");
   public KeyBinding keyBindPickBlock = new KeyBinding("key.pickItem", -98, "key.categories.gameplay");
   public KeyBinding keyBindChat = new KeyBinding("key.chat", 20, "key.categories.multiplayer");
   public KeyBinding keyBindPlayerList = new KeyBinding("key.playerlist", 15, "key.categories.multiplayer");
   public KeyBinding keyBindCommand = new KeyBinding("key.command", 53, "key.categories.multiplayer");
   public KeyBinding keyBindScreenshot = new KeyBinding("key.screenshot", 60, "key.categories.misc");
   public KeyBinding keyBindTogglePerspective = new KeyBinding("key.togglePerspective", 63, "key.categories.misc");
   public KeyBinding keyBindSmoothCamera = new KeyBinding("key.smoothCamera", 0, "key.categories.misc");
   public KeyBinding keyBindFullscreen = new KeyBinding("key.fullscreen", 87, "key.categories.misc");
   public KeyBinding keyBindSpectatorOutlines = new KeyBinding("key.spectatorOutlines", 0, "key.categories.misc");
   public KeyBinding keyBindStreamStartStop = new KeyBinding("key.streamStartStop", 64, "key.categories.stream");
   public KeyBinding keyBindStreamPauseUnpause = new KeyBinding("key.streamPauseUnpause", 65, "key.categories.stream");
   public KeyBinding keyBindStreamCommercials = new KeyBinding("key.streamCommercial", 0, "key.categories.stream");
   public KeyBinding keyBindStreamToggleMic = new KeyBinding("key.streamToggleMic", 0, "key.categories.stream");
   public KeyBinding[] scorePlayerTeamArray = new KeyBinding[]{
      new KeyBinding("key.hotbar.1", 2, "key.categories.inventory"),
      new KeyBinding("key.hotbar.2", 3, "key.categories.inventory"),
      new KeyBinding("key.hotbar.3", 4, "key.categories.inventory"),
      new KeyBinding("key.hotbar.4", 5, "key.categories.inventory"),
      new KeyBinding("key.hotbar.5", 6, "key.categories.inventory"),
      new KeyBinding("key.hotbar.6", 7, "key.categories.inventory"),
      new KeyBinding("key.hotbar.7", 8, "key.categories.inventory"),
      new KeyBinding("key.hotbar.8", 9, "key.categories.inventory"),
      new KeyBinding("key.hotbar.9", 10, "key.categories.inventory")
   };
   public KeyBinding[] scorePlayerTeamArray2;
   public GameSettingsPeacefulType gameSettingsPeacefulType;
   public boolean state11;
   public int indexOffset64;
   public boolean state14;
   public boolean state106;
   public boolean state47;
   public String text68;
   public boolean state32;
   public boolean state54;
   public float scaleFactor4;
   public float scaleFactor100;
   public float scaleFactor117;
   public int indexOffset109;
   public int indexOffset134;
   public String text93;
   public boolean state87;
   public int indexOffset57 = 1;
   public float scaleFactor21 = 0.8F;
   public int indexOffset43 = 0;
   public boolean state103 = false;
   public boolean state34 = false;
   public boolean state83 = BuildManager.isActive29();
   public boolean state66 = BuildManager.isActive29();
   public boolean state75 = false;
   public boolean state85 = false;
   public float scaleFactor6 = 1.0F;
   public int indexOffset89 = 0;
   public int indexOffset18 = 1;
   public int indexOffset92 = 0;
   public float scaleFactor60 = 0.0F;
   public int indexOffset121 = 0;
   public int indexOffset = 0;
   public int indexOffset69 = 0;
   public int indexOffset46 = 3;
   public int indexOffset132 = 4000;
   public boolean state80 = false;
   public boolean state71 = false;
   public boolean state130 = false;
   public boolean state125 = true;
   public boolean state17 = true;
   public boolean state79 = true;
   public boolean state53 = true;
   public int indexOffset111 = 0;
   public int indexOffset40 = 1;
   public boolean state49 = false;
   public int indexOffset74 = 0;
   public boolean state45 = false;
   public boolean state76 = false;
   public String text42 = "Default";
   public boolean state31 = true;
   public boolean state114 = true;
   public boolean state120 = true;
   public boolean state86 = true;
   public boolean state127 = true;
   public boolean state37 = true;
   public boolean state126 = true;
   public int indexOffset95 = 2;
   public boolean state44 = true;
   public boolean state122 = false;
   public boolean state = true;
   public boolean state50 = false;
   public boolean state56 = false;
   public int indexOffset7 = 0;
   public boolean state2 = true;
   public boolean state110 = true;
   public int indexOffset61 = 3;
   public boolean state70 = true;
   public boolean state30 = true;
   public boolean state135 = true;
   public int indexOffset101 = 1;
   public int indexOffset25 = 0;
   public int indexOffset15 = 0;
   public boolean state104 = true;
   public boolean state67 = true;
   public boolean state38 = true;
   public boolean state3 = true;
   public boolean state77 = true;
   public boolean state24 = true;
   public boolean state84 = true;
   public boolean state58 = true;
   public boolean state39 = true;
   public boolean state51 = true;
   public boolean state1 = true;
   public boolean state78 = true;
   public boolean state133 = true;
   public boolean state119 = true;
   public boolean state113 = true;
   public KeyBinding ofKeyBindZoom;
   public Minecraft mcClient;
   public File file;
   public File file2;

   public void applyFactoryOptionType(FactoryOptionType factoryOptionType, float factor) {
      this.applyFactoryOptionTypeByFactoryOptionTyp(factoryOptionType, factor);
      if (factoryOptionType == FactoryOptionType.SENSITIVITY) {
         this.scaleFactor = factor;
      }

      if (factoryOptionType == FactoryOptionType.FOV) {
         this.scaleFactor4 = factor;
      }

      if (factoryOptionType == FactoryOptionType.GAMMA) {
         this.scaleFactor100 = factor;
      }

      if (factoryOptionType == FactoryOptionType.FRAMERATE_LIMIT) {
         this.indexOffset35 = (int)factor;
         this.state26 = false;
         if (this.indexOffset35 <= 0) {
            this.indexOffset35 = (int)FactoryOptionType.FRAMERATE_LIMIT.getFactor2();
            this.state26 = true;
         }

         this.setVSyncEnabled();
      }

      if (factoryOptionType == FactoryOptionType.CHAT_OPACITY) {
         this.scaleFactor102 = factor;
         this.mcClient.ingameGUI.getInternalLoggerEntry().renderElement2();
      }

      if (factoryOptionType == FactoryOptionType.CHAT_HEIGHT_FOCUSED) {
         this.scaleFactor41 = factor;
         this.mcClient.ingameGUI.getInternalLoggerEntry().renderElement2();
      }

      if (factoryOptionType == FactoryOptionType.CHAT_HEIGHT_UNFOCUSED) {
         this.scaleFactor8 = factor;
         this.mcClient.ingameGUI.getInternalLoggerEntry().renderElement2();
      }

      if (factoryOptionType == FactoryOptionType.CHAT_WIDTH) {
         this.scaleFactor137 = factor;
         this.mcClient.ingameGUI.getInternalLoggerEntry().renderElement2();
      }

      if (factoryOptionType == FactoryOptionType.CHAT_SCALE) {
         this.scaleFactor29 = factor;
         this.mcClient.ingameGUI.getInternalLoggerEntry().renderElement2();
      }

      if (factoryOptionType == FactoryOptionType.MIPMAP_LEVELS) {
         int indexOffset2 = this.indexOffset118;
         this.indexOffset118 = (int)factor;
         if ((float)indexOffset2 != factor) {
            this.mcClient.getListenerAdapter27().setCount6(this.indexOffset118);
            this.mcClient.getTextureManager().applyResourceLocation3(ListenerAdapter27.resourceLocation);
            this.mcClient.getListenerAdapter27().applyFlagByFlag(false, this.indexOffset118 > 0);
            this.mcClient.getListenableFuture();
         }
      }

      if (factoryOptionType == FactoryOptionType.BLOCK_ALTERNATIVES) {
         this.state123 = !this.state123;
         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.RENDER_DISTANCE) {
         this.indexOffset16 = (int)factor;
         this.mcClient.renderGlobal.updateState14();
      }
   }

   private static int computeCountByIndexArray(int count, int[] countArray) {
      return computeCount2(count, countArray) < 0 ? countArray[0] : count;
   }

   public void setEnabled(boolean enabled) {
      int count = enabled ? 0 : 2;
      this.indexOffset25 = count;
      this.indexOffset15 = count;
      this.state104 = enabled;
      this.state67 = enabled;
      this.state38 = enabled;
      this.state3 = enabled;
      this.state77 = enabled;
      this.state24 = enabled;
      this.state84 = enabled;
      this.state58 = enabled;
      this.state39 = enabled;
      this.state51 = enabled;
      this.state1 = enabled;
      this.state78 = enabled;
      this.indexOffset134 = enabled ? 0 : 2;
      this.state133 = enabled;
      this.state119 = enabled;
      this.state113 = enabled;
   }

   public void setVSyncEnabled() {
      Display.setVSyncEnabled(this.state26);
   }

   public void updateState4() {
      if (this.mcClient.thePlayer != null) {
         int count = 0;

         for (TabListCapeType tabListCapeType : (Iterable<TabListCapeType>)(Iterable<?>)this.entrySet) {
            count |= tabListCapeType.getCount();
         }

         this.mcClient
            .thePlayer
            .netHandlerPlayClient
            .handlePacket(new C15PacketClientSettings(this.text93, this.indexOffset16, this.gameSettingsFullType, this.state27, count));
      }
   }

   public float getScaleFactor(AutoPlayTargetEntity autoPlayTargetEntity) {
      return this.entryMap.containsKey(autoPlayTargetEntity) ? (Float)this.entryMap.get(autoPlayTargetEntity) : 1.0F;
   }

   public void updateState3() {
      try {
         PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.file2), StandardCharsets.UTF_8));
         printWriter.println("ofFogType:" + this.indexOffset57);
         printWriter.println("ofFogStart:" + this.scaleFactor21);
         printWriter.println("ofMipmapType:" + this.indexOffset43);
         printWriter.println("ofOcclusionFancy:" + this.state103);
         printWriter.println("ofSmoothFps:" + this.state34);
         printWriter.println("ofSmoothWorld:" + this.state83);
         printWriter.println("ofAoLevel:" + this.scaleFactor6);
         printWriter.println("ofClouds:" + this.indexOffset92);
         printWriter.println("ofCloudsHeight:" + this.scaleFactor60);
         printWriter.println("ofTrees:" + this.indexOffset121);
         printWriter.println("ofDroppedItems:" + this.indexOffset69);
         printWriter.println("ofRain:" + this.indexOffset);
         printWriter.println("ofAnimatedWater:" + this.indexOffset25);
         printWriter.println("ofAnimatedLava:" + this.indexOffset15);
         printWriter.println("ofAnimatedFire:" + this.state104);
         printWriter.println("ofAnimatedPortal:" + this.state67);
         printWriter.println("ofAnimatedRedstone:" + this.state38);
         printWriter.println("ofAnimatedExplosion:" + this.state3);
         printWriter.println("ofAnimatedFlame:" + this.state77);
         printWriter.println("ofAnimatedSmoke:" + this.state24);
         printWriter.println("ofVoidParticles:" + this.state84);
         printWriter.println("ofWaterParticles:" + this.state58);
         printWriter.println("ofPortalParticles:" + this.state51);
         printWriter.println("ofPotionParticles:" + this.state1);
         printWriter.println("ofFireworkParticles:" + this.state78);
         printWriter.println("ofDrippingWaterLava:" + this.state133);
         printWriter.println("ofAnimatedTerrain:" + this.state119);
         printWriter.println("ofAnimatedTextures:" + this.state113);
         printWriter.println("ofRainSplash:" + this.state39);
         printWriter.println("ofLagometer:" + this.state80);
         printWriter.println("ofShowFps:" + this.state130);
         printWriter.println("ofAutoSaveTicks:" + this.indexOffset132);
         printWriter.println("ofBetterGrass:" + this.indexOffset46);
         printWriter.println("ofConnectedTextures:" + this.indexOffset95);
         printWriter.println("ofWeather:" + this.state125);
         printWriter.println("ofSky:" + this.state17);
         printWriter.println("ofStars:" + this.state79);
         printWriter.println("ofSunMoon:" + this.state53);
         printWriter.println("ofVignette:" + this.indexOffset111);
         printWriter.println("ofChunkUpdates:" + this.indexOffset40);
         printWriter.println("ofChunkUpdatesDynamic:" + this.state49);
         printWriter.println("ofTime:" + this.indexOffset74);
         printWriter.println("ofClearWater:" + this.state45);
         printWriter.println("ofAaLevel:" + this.indexOffset89);
         printWriter.println("ofAfLevel:" + this.indexOffset18);
         printWriter.println("ofProfiler:" + this.state71);
         printWriter.println("ofBetterSnow:" + this.state76);
         printWriter.println("ofSwampColors:" + this.state31);
         printWriter.println("ofRandomEntities:" + this.state114);
         printWriter.println("ofSmoothBiomes:" + this.state120);
         printWriter.println("ofCustomFonts:" + this.state86);
         printWriter.println("ofCustomColors:" + this.state127);
         printWriter.println("ofCustomItems:" + this.state44);
         printWriter.println("ofCustomSky:" + this.state37);
         printWriter.println("ofShowCapes:" + this.state126);
         printWriter.println("ofNaturalTextures:" + this.state122);
         printWriter.println("ofEmissiveTextures:" + this.state);
         printWriter.println("ofLazyChunkLoading:" + this.state66);
         printWriter.println("ofRenderRegions:" + this.state75);
         printWriter.println("ofSmartAnimations:" + this.state85);
         printWriter.println("ofDynamicFov:" + this.state2);
         printWriter.println("ofAlternateBlocks:" + this.state110);
         printWriter.println("ofDynamicLights:" + this.indexOffset61);
         printWriter.println("ofScreenshotSize:" + this.indexOffset101);
         printWriter.println("ofCustomEntityModels:" + this.state70);
         printWriter.println("ofCustomGuis:" + this.state30);
         printWriter.println("ofShowGlErrors:" + this.state135);
         printWriter.println("ofFullscreenMode:" + this.text42);
         printWriter.println("ofFastMath:" + this.state50);
         printWriter.println("ofFastRender:" + this.state56);
         printWriter.println("ofTranslucentBlocks:" + this.indexOffset7);
         printWriter.println("key_" + this.ofKeyBindZoom.getKeyDescription() + ":" + this.ofKeyBindZoom.getKeyCode());
         printWriter.close();
      } catch (Exception exception) {
         BuildManager.applyText3("Failed to save options");
         exception.printStackTrace();
      }
   }

   public boolean isActive() {
      if (!this.state98) {
         throw new IllegalStateException("Native transport can't be disabled.");
      } else {
         return true;
      }
   }

   private void updateState2() {
      if (BuildManager.isActive74()) {
         BuildManager.enabled = true;
      }

      AimFactory.applyGameSettingsByGameSettings(this, this.mcClient.theWorld);
   }

   public boolean matchesFactoryOptionType(FactoryOptionType factoryOptionType) {
      switch (CurveEstimator.countArray[factoryOptionType.ordinal()]) {
         case 1:
            return this.state20;
         case 2:
            return this.state28;
         case 3:
            return this.state105;
         case 4:
            return this.state96;
         case 5:
            return this.state27;
         case 6:
            return this.state13;
         case 7:
            return this.state124;
         case 8:
            return this.state115;
         case 9:
            return this.state81;
         case 10:
            return this.state26;
         case 11:
            return this.state112;
         case 12:
            return this.state48;
         case 13:
            return this.state116;
         case 14:
            return this.state87;
         case 15:
            return this.state123;
         case 16:
            return this.state22;
         case 17:
            return this.state91;
         default:
            return false;
      }
   }

   private String resolveText(FactoryOptionType factoryOptionType) {
      String text2 = StatCollector.getText(factoryOptionType.getLabelText(), new Object[0]) + ": ";
      if (text2 == null) {
         text2 = factoryOptionType.getLabelText();
      }

      if (factoryOptionType == FactoryOptionType.RENDER_DISTANCE) {
         int count = (int)this.computeFactor(factoryOptionType);
         String text3 = StatCollector.getText("options.renderDistance.tiny", new Object[0]);
         byte byteValue = 2;
         if (count >= 4) {
            text3 = StatCollector.getText("options.renderDistance.short", new Object[0]);
            byteValue = 4;
         }

         if (count >= 8) {
            text3 = StatCollector.getText("options.renderDistance.normal", new Object[0]);
            byteValue = 8;
         }

         if (count >= 16) {
            text3 = StatCollector.getText("options.renderDistance.far", new Object[0]);
            byteValue = 16;
         }

         if (count >= 32) {
            text3 = GeneratorDefaultManager.resolveText("of.options.renderDistance.extreme");
            byteValue = 32;
         }

         if (count >= 48) {
            text3 = GeneratorDefaultManager.resolveText("of.options.renderDistance.insane");
            byteValue = 48;
         }

         if (count >= 64) {
            text3 = GeneratorDefaultManager.resolveText("of.options.renderDistance.ludicrous");
            byteValue = 64;
         }

         if (this.indexOffset16 - byteValue > 0) {
            text3 = text3 + "+";
         }

         return text2 + count + " " + text3;
      } else if (factoryOptionType == FactoryOptionType.FOG_FANCY) {
         switch (this.indexOffset57) {
            case 1:
               return text2 + GeneratorDefaultManager.getText();
            case 2:
               return text2 + GeneratorDefaultManager.getText2();
            case 3:
               return text2 + GeneratorDefaultManager.getText4();
            default:
               return text2 + GeneratorDefaultManager.getText4();
         }
      } else if (factoryOptionType == FactoryOptionType.FOG_START) {
         return text2 + this.scaleFactor21;
      } else if (factoryOptionType == FactoryOptionType.MIPMAP_TYPE) {
         switch (this.indexOffset43) {
            case 0:
               return text2 + GeneratorDefaultManager.resolveText("of.options.mipmap.nearest");
            case 1:
               return text2 + GeneratorDefaultManager.resolveText("of.options.mipmap.linear");
            case 2:
               return text2 + GeneratorDefaultManager.resolveText("of.options.mipmap.bilinear");
            case 3:
               return text2 + GeneratorDefaultManager.resolveText("of.options.mipmap.trilinear");
            default:
               return text2 + "of.options.mipmap.nearest";
         }
      } else if (factoryOptionType == FactoryOptionType.SMOOTH_FPS) {
         return this.state34 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.SMOOTH_WORLD) {
         return this.state83 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.CLOUDS) {
         switch (this.indexOffset92) {
            case 1:
               return text2 + GeneratorDefaultManager.getText();
            case 2:
               return text2 + GeneratorDefaultManager.getText2();
            case 3:
               return text2 + GeneratorDefaultManager.getText4();
            default:
               return text2 + GeneratorDefaultManager.getText3();
         }
      } else if (factoryOptionType == FactoryOptionType.TREES) {
         switch (this.indexOffset121) {
            case 1:
               return text2 + GeneratorDefaultManager.getText();
            case 2:
               return text2 + GeneratorDefaultManager.getText2();
            case 3:
            default:
               return text2 + GeneratorDefaultManager.getText3();
            case 4:
               return text2 + GeneratorDefaultManager.resolveText("of.general.smart");
         }
      } else if (factoryOptionType == FactoryOptionType.DROPPED_ITEMS) {
         switch (this.indexOffset69) {
            case 1:
               return text2 + GeneratorDefaultManager.getText();
            case 2:
               return text2 + GeneratorDefaultManager.getText2();
            default:
               return text2 + GeneratorDefaultManager.getText3();
         }
      } else if (factoryOptionType == FactoryOptionType.RAIN) {
         switch (this.indexOffset) {
            case 1:
               return text2 + GeneratorDefaultManager.getText();
            case 2:
               return text2 + GeneratorDefaultManager.getText2();
            case 3:
               return text2 + GeneratorDefaultManager.getText4();
            default:
               return text2 + GeneratorDefaultManager.getText3();
         }
      } else if (factoryOptionType == FactoryOptionType.ANIMATED_WATER) {
         switch (this.indexOffset25) {
            case 1:
               return text2 + GeneratorDefaultManager.resolveText("of.options.animation.dynamic");
            case 2:
               return text2 + GeneratorDefaultManager.getText4();
            default:
               return text2 + GeneratorDefaultManager.getTextAsText();
         }
      } else if (factoryOptionType == FactoryOptionType.ANIMATED_LAVA) {
         switch (this.indexOffset15) {
            case 1:
               return text2 + GeneratorDefaultManager.resolveText("of.options.animation.dynamic");
            case 2:
               return text2 + GeneratorDefaultManager.getText4();
            default:
               return text2 + GeneratorDefaultManager.getTextAsText();
         }
      } else if (factoryOptionType == FactoryOptionType.ANIMATED_FIRE) {
         return this.state104 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.ANIMATED_PORTAL) {
         return this.state67 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.ANIMATED_REDSTONE) {
         return this.state38 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.ANIMATED_EXPLOSION) {
         return this.state3 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.ANIMATED_FLAME) {
         return this.state77 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.ANIMATED_SMOKE) {
         return this.state24 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.VOID_PARTICLES) {
         return this.state84 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.WATER_PARTICLES) {
         return this.state58 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.PORTAL_PARTICLES) {
         return this.state51 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.POTION_PARTICLES) {
         return this.state1 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.FIREWORK_PARTICLES) {
         return this.state78 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.DRIPPING_WATER_LAVA) {
         return this.state133 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.ANIMATED_TERRAIN) {
         return this.state119 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.ANIMATED_TEXTURES) {
         return this.state113 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.RAIN_SPLASH) {
         return this.state39 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.LAGOMETER) {
         return this.state80 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.SHOW_FPS) {
         return this.state130 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.AUTOSAVE_TICKS) {
         short shortValue = 900;
         return this.indexOffset132 <= 900
            ? text2 + GeneratorDefaultManager.resolveText("of.options.save.45s")
            : (
               this.indexOffset132 <= 2 * shortValue
                  ? text2 + GeneratorDefaultManager.resolveText("of.options.save.90s")
                  : (
                     this.indexOffset132 <= 4 * shortValue
                        ? text2 + GeneratorDefaultManager.resolveText("of.options.save.3min")
                        : (
                           this.indexOffset132 <= 8 * shortValue
                              ? text2 + GeneratorDefaultManager.resolveText("of.options.save.6min")
                              : (
                                 this.indexOffset132 <= 16 * shortValue
                                    ? text2 + GeneratorDefaultManager.resolveText("of.options.save.12min")
                                    : text2 + GeneratorDefaultManager.resolveText("of.options.save.24min")
                              )
                        )
                  )
            );
      } else if (factoryOptionType == FactoryOptionType.BETTER_GRASS) {
         switch (this.indexOffset46) {
            case 1:
               return text2 + GeneratorDefaultManager.getText();
            case 2:
               return text2 + GeneratorDefaultManager.getText2();
            default:
               return text2 + GeneratorDefaultManager.getText4();
         }
      } else if (factoryOptionType == FactoryOptionType.CONNECTED_TEXTURES) {
         switch (this.indexOffset95) {
            case 1:
               return text2 + GeneratorDefaultManager.getText();
            case 2:
               return text2 + GeneratorDefaultManager.getText2();
            default:
               return text2 + GeneratorDefaultManager.getText4();
         }
      } else if (factoryOptionType == FactoryOptionType.WEATHER) {
         return this.state125 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.SKY) {
         return this.state17 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.STARS) {
         return this.state79 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.SUN_MOON) {
         return this.state53 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.VIGNETTE) {
         switch (this.indexOffset111) {
            case 1:
               return text2 + GeneratorDefaultManager.getText();
            case 2:
               return text2 + GeneratorDefaultManager.getText2();
            default:
               return text2 + GeneratorDefaultManager.getText3();
         }
      } else if (factoryOptionType == FactoryOptionType.CHUNK_UPDATES) {
         return text2 + this.indexOffset40;
      } else if (factoryOptionType == FactoryOptionType.CHUNK_UPDATES_DYNAMIC) {
         return this.state49 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.TIME) {
         return this.indexOffset74 == 1
            ? text2 + GeneratorDefaultManager.resolveText("of.options.time.dayOnly")
            : (this.indexOffset74 == 2 ? text2 + GeneratorDefaultManager.resolveText("of.options.time.nightOnly") : text2 + GeneratorDefaultManager.getText3());
      } else if (factoryOptionType == FactoryOptionType.CLEAR_WATER) {
         return this.state45 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.AA_LEVEL) {
         String text4 = "";
         if (this.indexOffset89 != BuildManager.getCount3()) {
            text4 = " (" + GeneratorDefaultManager.resolveText("of.general.restart") + ")";
         }

         return this.indexOffset89 == 0 ? text2 + GeneratorDefaultManager.getText4() + text4 : text2 + this.indexOffset89 + text4;
      } else if (factoryOptionType == FactoryOptionType.AF_LEVEL) {
         return this.indexOffset18 == 1 ? text2 + GeneratorDefaultManager.getText4() : text2 + this.indexOffset18;
      } else if (factoryOptionType == FactoryOptionType.PROFILER) {
         return this.state71 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.BETTER_SNOW) {
         return this.state76 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.SWAMP_COLORS) {
         return this.state31 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.RANDOM_ENTITIES) {
         return this.state114 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.SMOOTH_BIOMES) {
         return this.state120 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.CUSTOM_FONTS) {
         return this.state86 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.CUSTOM_COLORS) {
         return this.state127 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.CUSTOM_SKY) {
         return this.state37 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.SHOW_CAPES) {
         return this.state126 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.CUSTOM_ITEMS) {
         return this.state44 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.NATURAL_TEXTURES) {
         return this.state122 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.EMISSIVE_TEXTURES) {
         return this.state ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.FAST_MATH) {
         return this.state50 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.FAST_RENDER) {
         return this.state56 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.TRANSLUCENT_BLOCKS) {
         return this.indexOffset7 == 1
            ? text2 + GeneratorDefaultManager.getText()
            : (this.indexOffset7 == 2 ? text2 + GeneratorDefaultManager.getText2() : text2 + GeneratorDefaultManager.getText3());
      } else if (factoryOptionType == FactoryOptionType.LAZY_CHUNK_LOADING) {
         return this.state66 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.RENDER_REGIONS) {
         return this.state75 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.SMART_ANIMATIONS) {
         return this.state85 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.DYNAMIC_FOV) {
         return this.state2 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.ALTERNATE_BLOCKS) {
         return this.state110 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.DYNAMIC_LIGHTS) {
         int count2 = computeCount2(this.indexOffset61, intValueArray2);
         return text2 + resolveText(stringArray9, count2);
      } else if (factoryOptionType == FactoryOptionType.SCREENSHOT_SIZE) {
         return this.indexOffset101 <= 1 ? text2 + GeneratorDefaultManager.getText3() : text2 + this.indexOffset101 + "x";
      } else if (factoryOptionType == FactoryOptionType.CUSTOM_ENTITY_MODELS) {
         return this.state70 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.CUSTOM_GUIS) {
         return this.state30 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.SHOW_GL_ERRORS) {
         return this.state135 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.FULLSCREEN_MODE) {
         return this.text42.equals("Default") ? text2 + GeneratorDefaultManager.getText3() : text2 + this.text42;
      } else if (factoryOptionType == FactoryOptionType.HELD_ITEM_TOOLTIPS) {
         return this.state82 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.ADVANCED_TOOLTIPS) {
         return this.state136 ? text2 + GeneratorDefaultManager.getTextAsText() : text2 + GeneratorDefaultManager.getText4();
      } else if (factoryOptionType == FactoryOptionType.FRAMERATE_LIMIT) {
         float factor = this.computeFactor(factoryOptionType);
         return factor == 0.0F
            ? text2 + GeneratorDefaultManager.resolveText("of.options.framerateLimit.vsync")
            : (
               factor == FactoryOptionType.computeFactor(factoryOptionType)
                  ? text2 + StatCollector.getText("options.framerateLimit.max", new Object[0])
                  : text2 + (int)factor + " fps"
            );
      } else {
         return null;
      }
   }

   public Set processAction() {
      return ImmutableSet.copyOf(this.entrySet);
   }

   public void applyTabListCapeType(TabListCapeType tabListCapeType, boolean flag) {
      if (flag) {
         this.entrySet.add(tabListCapeType);
      } else {
         this.entrySet.remove(tabListCapeType);
      }

      this.updateState4();
   }

   public void updateAutoPlayTargetEntity(AutoPlayTargetEntity autoPlayTargetEntity, float factor) {
      this.mcClient.getMcSoundHandler().applyAutoPlayTargetEntity(autoPlayTargetEntity, factor);
      this.entryMap.put(autoPlayTargetEntity, factor);
   }

   public float computeFactor(FactoryOptionType factoryOptionType) {
      float factor = this.computeFactorByFactoryOptionTyp(factoryOptionType);
      return factor != Float.MAX_VALUE
         ? factor
         : (
            factoryOptionType == FactoryOptionType.FOV
               ? this.scaleFactor4
               : (
                  factoryOptionType == FactoryOptionType.GAMMA
                     ? this.scaleFactor100
                     : (
                        factoryOptionType == FactoryOptionType.SATURATION
                           ? this.scaleFactor117
                           : (
                              factoryOptionType == FactoryOptionType.SENSITIVITY
                                 ? this.scaleFactor
                                 : (
                                    factoryOptionType == FactoryOptionType.CHAT_OPACITY
                                       ? this.scaleFactor102
                                       : (
                                          factoryOptionType == FactoryOptionType.CHAT_HEIGHT_FOCUSED
                                             ? this.scaleFactor41
                                             : (
                                                factoryOptionType == FactoryOptionType.CHAT_HEIGHT_UNFOCUSED
                                                   ? this.scaleFactor8
                                                   : (
                                                      factoryOptionType == FactoryOptionType.CHAT_SCALE
                                                         ? this.scaleFactor29
                                                         : (
                                                            factoryOptionType == FactoryOptionType.CHAT_WIDTH
                                                               ? this.scaleFactor137
                                                               : (
                                                                  factoryOptionType == FactoryOptionType.FRAMERATE_LIMIT
                                                                     ? (float)this.indexOffset35
                                                                     : (
                                                                        factoryOptionType == FactoryOptionType.MIPMAP_LEVELS
                                                                           ? (float)this.indexOffset118
                                                                           : (
                                                                              factoryOptionType == FactoryOptionType.RENDER_DISTANCE
                                                                                 ? (float)this.indexOffset16
                                                                                 : (
                                                                                    factoryOptionType == FactoryOptionType.STREAM_BYTES_PER_PIXEL
                                                                                       ? this.scaleFactor97
                                                                                       : (
                                                                                          factoryOptionType == FactoryOptionType.STREAM_VOLUME_MIC
                                                                                             ? this.scaleFactor131
                                                                                             : (
                                                                                                factoryOptionType == FactoryOptionType.STREAM_VOLUME_SYSTEM
                                                                                                   ? this.scaleFactor12
                                                                                                   : (
                                                                                                      factoryOptionType == FactoryOptionType.STREAM_KBPS
                                                                                                         ? this.scaleFactor59
                                                                                                         : (
                                                                                                            factoryOptionType == FactoryOptionType.STREAM_FPS
                                                                                                               ? this.scaleFactor10
                                                                                                               : 0.0F
                                                                                                         )
                                                                                                   )
                                                                                             )
                                                                                       )
                                                                                 )
                                                                           )
                                                                     )
                                                               )
                                                         )
                                                   )
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
   }

   private void updateState6() {
      switch (this.indexOffset92) {
         case 1:
            this.indexOffset33 = 1;
            break;
         case 2:
            this.indexOffset33 = 2;
            break;
         case 3:
            this.indexOffset33 = 0;
            break;
         default:
            if (this.state5) {
               this.indexOffset33 = 2;
            } else {
               this.indexOffset33 = 1;
            }
      }
   }

   private void applyFactoryOptionType(FactoryOptionType factoryOptionType, int count) {
      if (factoryOptionType == FactoryOptionType.FOG_FANCY) {
         switch (this.indexOffset57) {
            case 1:
               this.indexOffset57 = 2;
               if (!BuildManager.isEnabled8()) {
                  this.indexOffset57 = 3;
               }
               break;
            case 2:
               this.indexOffset57 = 3;
               break;
            case 3:
               this.indexOffset57 = 1;
               break;
            default:
               this.indexOffset57 = 1;
         }
      }

      if (factoryOptionType == FactoryOptionType.FOG_START) {
         this.scaleFactor21 += 0.2F;
         if (this.scaleFactor21 > 0.81F) {
            this.scaleFactor21 = 0.2F;
         }
      }

      if (factoryOptionType == FactoryOptionType.SMOOTH_FPS) {
         this.state34 = !this.state34;
      }

      if (factoryOptionType == FactoryOptionType.SMOOTH_WORLD) {
         this.state83 = !this.state83;
         BuildManager.updateState9();
      }

      if (factoryOptionType == FactoryOptionType.CLOUDS) {
         this.indexOffset92++;
         if (this.indexOffset92 > 3) {
            this.indexOffset92 = 0;
         }

         this.updateState6();
         this.mcClient.renderGlobal.updateState11();
      }

      if (factoryOptionType == FactoryOptionType.TREES) {
         this.indexOffset121 = computeCount(this.indexOffset121, intValueArray);
         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.DROPPED_ITEMS) {
         this.indexOffset69++;
         if (this.indexOffset69 > 2) {
            this.indexOffset69 = 0;
         }
      }

      if (factoryOptionType == FactoryOptionType.RAIN) {
         this.indexOffset++;
         if (this.indexOffset > 3) {
            this.indexOffset = 0;
         }
      }

      if (factoryOptionType == FactoryOptionType.ANIMATED_WATER) {
         this.indexOffset25++;
         if (this.indexOffset25 == 1) {
            this.indexOffset25++;
         }

         if (this.indexOffset25 > 2) {
            this.indexOffset25 = 0;
         }
      }

      if (factoryOptionType == FactoryOptionType.ANIMATED_LAVA) {
         this.indexOffset15++;
         if (this.indexOffset15 == 1) {
            this.indexOffset15++;
         }

         if (this.indexOffset15 > 2) {
            this.indexOffset15 = 0;
         }
      }

      if (factoryOptionType == FactoryOptionType.ANIMATED_FIRE) {
         this.state104 = !this.state104;
      }

      if (factoryOptionType == FactoryOptionType.ANIMATED_PORTAL) {
         this.state67 = !this.state67;
      }

      if (factoryOptionType == FactoryOptionType.ANIMATED_REDSTONE) {
         this.state38 = !this.state38;
      }

      if (factoryOptionType == FactoryOptionType.ANIMATED_EXPLOSION) {
         this.state3 = !this.state3;
      }

      if (factoryOptionType == FactoryOptionType.ANIMATED_FLAME) {
         this.state77 = !this.state77;
      }

      if (factoryOptionType == FactoryOptionType.ANIMATED_SMOKE) {
         this.state24 = !this.state24;
      }

      if (factoryOptionType == FactoryOptionType.VOID_PARTICLES) {
         this.state84 = !this.state84;
      }

      if (factoryOptionType == FactoryOptionType.WATER_PARTICLES) {
         this.state58 = !this.state58;
      }

      if (factoryOptionType == FactoryOptionType.PORTAL_PARTICLES) {
         this.state51 = !this.state51;
      }

      if (factoryOptionType == FactoryOptionType.POTION_PARTICLES) {
         this.state1 = !this.state1;
      }

      if (factoryOptionType == FactoryOptionType.FIREWORK_PARTICLES) {
         this.state78 = !this.state78;
      }

      if (factoryOptionType == FactoryOptionType.DRIPPING_WATER_LAVA) {
         this.state133 = !this.state133;
      }

      if (factoryOptionType == FactoryOptionType.ANIMATED_TERRAIN) {
         this.state119 = !this.state119;
      }

      if (factoryOptionType == FactoryOptionType.ANIMATED_TEXTURES) {
         this.state113 = !this.state113;
      }

      if (factoryOptionType == FactoryOptionType.RAIN_SPLASH) {
         this.state39 = !this.state39;
      }

      if (factoryOptionType == FactoryOptionType.LAGOMETER) {
         this.state80 = !this.state80;
      }

      if (factoryOptionType == FactoryOptionType.SHOW_FPS) {
         this.state130 = !this.state130;
      }

      if (factoryOptionType == FactoryOptionType.AUTOSAVE_TICKS) {
         this.indexOffset132 = Math.max(this.indexOffset132 / 900 * 900, 900);
         this.indexOffset132 *= 2;
         if (this.indexOffset132 > 28800) {
            this.indexOffset132 = 900;
         }
      }

      if (factoryOptionType == FactoryOptionType.BETTER_GRASS) {
         this.indexOffset46++;
         if (this.indexOffset46 > 3) {
            this.indexOffset46 = 1;
         }

         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.CONNECTED_TEXTURES) {
         this.indexOffset95++;
         if (this.indexOffset95 > 3) {
            this.indexOffset95 = 1;
         }

         if (this.indexOffset95 == 2) {
            this.mcClient.renderGlobal.updateState();
         } else {
            this.mcClient.updateState11();
         }
      }

      if (factoryOptionType == FactoryOptionType.WEATHER) {
         this.state125 = !this.state125;
      }

      if (factoryOptionType == FactoryOptionType.SKY) {
         this.state17 = !this.state17;
      }

      if (factoryOptionType == FactoryOptionType.STARS) {
         this.state79 = !this.state79;
      }

      if (factoryOptionType == FactoryOptionType.SUN_MOON) {
         this.state53 = !this.state53;
      }

      if (factoryOptionType == FactoryOptionType.VIGNETTE) {
         this.indexOffset111++;
         if (this.indexOffset111 > 2) {
            this.indexOffset111 = 0;
         }
      }

      if (factoryOptionType == FactoryOptionType.CHUNK_UPDATES) {
         this.indexOffset40++;
         if (this.indexOffset40 > 5) {
            this.indexOffset40 = 1;
         }
      }

      if (factoryOptionType == FactoryOptionType.CHUNK_UPDATES_DYNAMIC) {
         this.state49 = !this.state49;
      }

      if (factoryOptionType == FactoryOptionType.TIME) {
         this.indexOffset74++;
         if (this.indexOffset74 > 2) {
            this.indexOffset74 = 0;
         }
      }

      if (factoryOptionType == FactoryOptionType.CLEAR_WATER) {
         this.state45 = !this.state45;
         this.updateState2();
      }

      if (factoryOptionType == FactoryOptionType.PROFILER) {
         this.state71 = !this.state71;
      }

      if (factoryOptionType == FactoryOptionType.BETTER_SNOW) {
         this.state76 = !this.state76;
         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.SWAMP_COLORS) {
         this.state31 = !this.state31;
         LoadingManager.updateState();
         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.RANDOM_ENTITIES) {
         this.state114 = !this.state114;
         OptifineRandomManager.updateState();
      }

      if (factoryOptionType == FactoryOptionType.SMOOTH_BIOMES) {
         this.state120 = !this.state120;
         LoadingManager.updateState();
         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.CUSTOM_FONTS) {
         this.state86 = !this.state86;
         this.mcClient.fontRendererObj.applyIResourceManager(BuildManager.getIResourceManager());
         this.mcClient.fontRenderer.applyIResourceManager(BuildManager.getIResourceManager());
      }

      if (factoryOptionType == FactoryOptionType.CUSTOM_COLORS) {
         this.state127 = !this.state127;
         LoadingManager.updateState2();
         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.CUSTOM_ITEMS) {
         this.state44 = !this.state44;
         this.mcClient.updateState11();
      }

      if (factoryOptionType == FactoryOptionType.CUSTOM_SKY) {
         this.state37 = !this.state37;
         McpatcherSkyWorldManager.updateState();
      }

      if (factoryOptionType == FactoryOptionType.SHOW_CAPES) {
         this.state126 = !this.state126;
      }

      if (factoryOptionType == FactoryOptionType.NATURAL_TEXTURES) {
         this.state122 = !this.state122;
         NotFoundManager.updateState();
         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.EMISSIVE_TEXTURES) {
         this.state = !this.state;
         this.mcClient.updateState11();
      }

      if (factoryOptionType == FactoryOptionType.FAST_MATH) {
         this.state50 = !this.state50;
         MathHelper.state = this.state50;
      }

      if (factoryOptionType == FactoryOptionType.FAST_RENDER) {
         if (!this.state56 && BuildManager.canExecute()) {
            BuildManager.applyText(GeneratorDefaultManager.resolveText("of.message.fr.shaders1"), GeneratorDefaultManager.resolveText("of.message.fr.shaders2"));
            return;
         }

         this.state56 = !this.state56;
         if (this.state56) {
            this.mcClient.entityRenderer.updateState8();
         }

         BuildManager.updateState10();
      }

      if (factoryOptionType == FactoryOptionType.TRANSLUCENT_BLOCKS) {
         if (this.indexOffset7 == 0) {
            this.indexOffset7 = 1;
         } else if (this.indexOffset7 == 1) {
            this.indexOffset7 = 2;
         } else if (this.indexOffset7 == 2) {
            this.indexOffset7 = 0;
         } else {
            this.indexOffset7 = 0;
         }

         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.LAZY_CHUNK_LOADING) {
         this.state66 = !this.state66;
      }

      if (factoryOptionType == FactoryOptionType.RENDER_REGIONS) {
         this.state75 = !this.state75;
         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.SMART_ANIMATIONS) {
         this.state85 = !this.state85;
         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.DYNAMIC_FOV) {
         this.state2 = !this.state2;
      }

      if (factoryOptionType == FactoryOptionType.ALTERNATE_BLOCKS) {
         this.state110 = !this.state110;
         this.mcClient.updateState11();
      }

      if (factoryOptionType == FactoryOptionType.DYNAMIC_LIGHTS) {
         this.indexOffset61 = computeCount(this.indexOffset61, intValueArray2);
         TargetEstimator.applyRenderGlobalByRenderGlobal(this.mcClient.renderGlobal);
      }

      if (factoryOptionType == FactoryOptionType.SCREENSHOT_SIZE) {
         this.indexOffset101++;
         if (this.indexOffset101 > 4) {
            this.indexOffset101 = 1;
         }

         if (!OpenGlHelper.isActiveAsFlag()) {
            this.indexOffset101 = 1;
         }
      }

      if (factoryOptionType == FactoryOptionType.CUSTOM_ENTITY_MODELS) {
         this.state70 = !this.state70;
         this.mcClient.updateState11();
      }

      if (factoryOptionType == FactoryOptionType.CUSTOM_GUIS) {
         this.state30 = !this.state30;
         OptifineGuiContainerManager.renderElement();
      }

      if (factoryOptionType == FactoryOptionType.SHOW_GL_ERRORS) {
         this.state135 = !this.state135;
      }

      if (factoryOptionType == FactoryOptionType.HELD_ITEM_TOOLTIPS) {
         this.state82 = !this.state82;
      }

      if (factoryOptionType == FactoryOptionType.ADVANCED_TOOLTIPS) {
         this.state136 = !this.state136;
      }
   }

   public void updateState8() {
      try {
         File file3 = this.file2;
         if (!file3.exists()) {
            file3 = this.file;
         }

         if (!file3.exists()) {
            return;
         }

         BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file3), StandardCharsets.UTF_8));

         String text2;
         while ((text2 = reader.readLine()) != null) {
            try {
               String[] textArray = text2.split(":");
               if (textArray[0].equals("ofRenderDistanceChunks") && textArray.length >= 2) {
                  this.indexOffset16 = Integer.parseInt(textArray[1]);
                  this.indexOffset16 = BuildManager.computeCount(this.indexOffset16, 2, 1024);
               }

               if (textArray[0].equals("ofFogType") && textArray.length >= 2) {
                  this.indexOffset57 = Integer.parseInt(textArray[1]);
                  this.indexOffset57 = BuildManager.computeCount(this.indexOffset57, 1, 3);
               }

               if (textArray[0].equals("ofFogStart") && textArray.length >= 2) {
                  this.scaleFactor21 = Float.parseFloat(textArray[1]);
                  if (this.scaleFactor21 < 0.2F) {
                     this.scaleFactor21 = 0.2F;
                  }

                  if (this.scaleFactor21 > 0.81F) {
                     this.scaleFactor21 = 0.8F;
                  }
               }

               if (textArray[0].equals("ofMipmapType") && textArray.length >= 2) {
                  this.indexOffset43 = Integer.parseInt(textArray[1]);
                  this.indexOffset43 = BuildManager.computeCount(this.indexOffset43, 0, 3);
               }

               if (textArray[0].equals("ofOcclusionFancy") && textArray.length >= 2) {
                  this.state103 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofSmoothFps") && textArray.length >= 2) {
                  this.state34 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofSmoothWorld") && textArray.length >= 2) {
                  this.state83 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofAoLevel") && textArray.length >= 2) {
                  this.scaleFactor6 = Float.parseFloat(textArray[1]);
                  this.scaleFactor6 = BuildManager.computeFactor(this.scaleFactor6, 0.0F, 1.0F);
               }

               if (textArray[0].equals("ofClouds") && textArray.length >= 2) {
                  this.indexOffset92 = Integer.parseInt(textArray[1]);
                  this.indexOffset92 = BuildManager.computeCount(this.indexOffset92, 0, 3);
                  this.updateState6();
               }

               if (textArray[0].equals("ofCloudsHeight") && textArray.length >= 2) {
                  this.scaleFactor60 = Float.parseFloat(textArray[1]);
                  this.scaleFactor60 = BuildManager.computeFactor(this.scaleFactor60, 0.0F, 1.0F);
               }

               if (textArray[0].equals("ofTrees") && textArray.length >= 2) {
                  this.indexOffset121 = Integer.parseInt(textArray[1]);
                  this.indexOffset121 = computeCountByIndexArray(this.indexOffset121, intValueArray);
               }

               if (textArray[0].equals("ofDroppedItems") && textArray.length >= 2) {
                  this.indexOffset69 = Integer.parseInt(textArray[1]);
                  this.indexOffset69 = BuildManager.computeCount(this.indexOffset69, 0, 2);
               }

               if (textArray[0].equals("ofRain") && textArray.length >= 2) {
                  this.indexOffset = Integer.parseInt(textArray[1]);
                  this.indexOffset = BuildManager.computeCount(this.indexOffset, 0, 3);
               }

               if (textArray[0].equals("ofAnimatedWater") && textArray.length >= 2) {
                  this.indexOffset25 = Integer.parseInt(textArray[1]);
                  this.indexOffset25 = BuildManager.computeCount(this.indexOffset25, 0, 2);
               }

               if (textArray[0].equals("ofAnimatedLava") && textArray.length >= 2) {
                  this.indexOffset15 = Integer.parseInt(textArray[1]);
                  this.indexOffset15 = BuildManager.computeCount(this.indexOffset15, 0, 2);
               }

               if (textArray[0].equals("ofAnimatedFire") && textArray.length >= 2) {
                  this.state104 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofAnimatedPortal") && textArray.length >= 2) {
                  this.state67 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofAnimatedRedstone") && textArray.length >= 2) {
                  this.state38 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofAnimatedExplosion") && textArray.length >= 2) {
                  this.state3 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofAnimatedFlame") && textArray.length >= 2) {
                  this.state77 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofAnimatedSmoke") && textArray.length >= 2) {
                  this.state24 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofVoidParticles") && textArray.length >= 2) {
                  this.state84 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofWaterParticles") && textArray.length >= 2) {
                  this.state58 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofPortalParticles") && textArray.length >= 2) {
                  this.state51 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofPotionParticles") && textArray.length >= 2) {
                  this.state1 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofFireworkParticles") && textArray.length >= 2) {
                  this.state78 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofDrippingWaterLava") && textArray.length >= 2) {
                  this.state133 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofAnimatedTerrain") && textArray.length >= 2) {
                  this.state119 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofAnimatedTextures") && textArray.length >= 2) {
                  this.state113 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofRainSplash") && textArray.length >= 2) {
                  this.state39 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofLagometer") && textArray.length >= 2) {
                  this.state80 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofShowFps") && textArray.length >= 2) {
                  this.state130 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofAutoSaveTicks") && textArray.length >= 2) {
                  this.indexOffset132 = Integer.parseInt(textArray[1]);
                  this.indexOffset132 = BuildManager.computeCount(this.indexOffset132, 40, 40000);
               }

               if (textArray[0].equals("ofBetterGrass") && textArray.length >= 2) {
                  this.indexOffset46 = Integer.parseInt(textArray[1]);
                  this.indexOffset46 = BuildManager.computeCount(this.indexOffset46, 1, 3);
               }

               if (textArray[0].equals("ofConnectedTextures") && textArray.length >= 2) {
                  this.indexOffset95 = Integer.parseInt(textArray[1]);
                  this.indexOffset95 = BuildManager.computeCount(this.indexOffset95, 1, 3);
               }

               if (textArray[0].equals("ofWeather") && textArray.length >= 2) {
                  this.state125 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofSky") && textArray.length >= 2) {
                  this.state17 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofStars") && textArray.length >= 2) {
                  this.state79 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofSunMoon") && textArray.length >= 2) {
                  this.state53 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofVignette") && textArray.length >= 2) {
                  this.indexOffset111 = Integer.parseInt(textArray[1]);
                  this.indexOffset111 = BuildManager.computeCount(this.indexOffset111, 0, 2);
               }

               if (textArray[0].equals("ofChunkUpdates") && textArray.length >= 2) {
                  this.indexOffset40 = Integer.parseInt(textArray[1]);
                  this.indexOffset40 = BuildManager.computeCount(this.indexOffset40, 1, 5);
               }

               if (textArray[0].equals("ofChunkUpdatesDynamic") && textArray.length >= 2) {
                  this.state49 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofTime") && textArray.length >= 2) {
                  this.indexOffset74 = Integer.parseInt(textArray[1]);
                  this.indexOffset74 = BuildManager.computeCount(this.indexOffset74, 0, 2);
               }

               if (textArray[0].equals("ofClearWater") && textArray.length >= 2) {
                  this.state45 = Boolean.parseBoolean(textArray[1]);
                  this.updateState2();
               }

               if (textArray[0].equals("ofAaLevel") && textArray.length >= 2) {
                  this.indexOffset89 = Integer.parseInt(textArray[1]);
                  this.indexOffset89 = BuildManager.computeCount(this.indexOffset89, 0, 16);
               }

               if (textArray[0].equals("ofAfLevel") && textArray.length >= 2) {
                  this.indexOffset18 = Integer.parseInt(textArray[1]);
                  this.indexOffset18 = BuildManager.computeCount(this.indexOffset18, 1, 16);
               }

               if (textArray[0].equals("ofProfiler") && textArray.length >= 2) {
                  this.state71 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofBetterSnow") && textArray.length >= 2) {
                  this.state76 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofSwampColors") && textArray.length >= 2) {
                  this.state31 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofRandomEntities") && textArray.length >= 2) {
                  this.state114 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofSmoothBiomes") && textArray.length >= 2) {
                  this.state120 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofCustomFonts") && textArray.length >= 2) {
                  this.state86 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofCustomColors") && textArray.length >= 2) {
                  this.state127 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofCustomItems") && textArray.length >= 2) {
                  this.state44 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofCustomSky") && textArray.length >= 2) {
                  this.state37 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofShowCapes") && textArray.length >= 2) {
                  this.state126 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofNaturalTextures") && textArray.length >= 2) {
                  this.state122 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofEmissiveTextures") && textArray.length >= 2) {
                  this.state = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofLazyChunkLoading") && textArray.length >= 2) {
                  this.state66 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofRenderRegions") && textArray.length >= 2) {
                  this.state75 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofSmartAnimations") && textArray.length >= 2) {
                  this.state85 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofDynamicFov") && textArray.length >= 2) {
                  this.state2 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofAlternateBlocks") && textArray.length >= 2) {
                  this.state110 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofDynamicLights") && textArray.length >= 2) {
                  this.indexOffset61 = Integer.parseInt(textArray[1]);
                  this.indexOffset61 = computeCountByIndexArray(this.indexOffset61, intValueArray2);
               }

               if (textArray[0].equals("ofScreenshotSize") && textArray.length >= 2) {
                  this.indexOffset101 = Integer.parseInt(textArray[1]);
                  this.indexOffset101 = BuildManager.computeCount(this.indexOffset101, 1, 4);
               }

               if (textArray[0].equals("ofCustomEntityModels") && textArray.length >= 2) {
                  this.state70 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofCustomGuis") && textArray.length >= 2) {
                  this.state30 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofShowGlErrors") && textArray.length >= 2) {
                  this.state135 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofFullscreenMode") && textArray.length >= 2) {
                  this.text42 = textArray[1];
               }

               if (textArray[0].equals("ofFastMath") && textArray.length >= 2) {
                  this.state50 = Boolean.parseBoolean(textArray[1]);
                  MathHelper.state = this.state50;
               }

               if (textArray[0].equals("ofFastRender") && textArray.length >= 2) {
                  this.state56 = Boolean.parseBoolean(textArray[1]);
               }

               if (textArray[0].equals("ofTranslucentBlocks") && textArray.length >= 2) {
                  this.indexOffset7 = Integer.parseInt(textArray[1]);
                  this.indexOffset7 = BuildManager.computeCount(this.indexOffset7, 0, 2);
               }

               if (textArray[0].equals("key_" + this.ofKeyBindZoom.getKeyDescription())) {
                  this.ofKeyBindZoom.setKeyCode(Integer.parseInt(textArray[1]));
               }
            } catch (Exception exception) {
               BuildManager.applyText4("Skipping bad option: " + text2);
               exception.printStackTrace();
            }
         }

         CurveFilter.applyKeyBindingArray(this.scorePlayerTeamArray2, new KeyBinding[]{this.ofKeyBindZoom});
         KeyBinding.unPressAllKeys();
         reader.close();
      } catch (Exception exception2) {
         BuildManager.applyText3("Failed to load options");
         exception2.printStackTrace();
      }
   }

   public String resolveTextByFactoryOptionTyp(FactoryOptionType factoryOptionType) {
      String text2 = this.resolveText(factoryOptionType);
      if (text2 != null) {
         return text2;
      } else {
         String text3 = StatCollector.getText(factoryOptionType.getLabelText(), new Object[0]) + ": ";
         if (!factoryOptionType.isEnabled()) {
            if (factoryOptionType.isEnabled2()) {
               return this.matchesFactoryOptionType(factoryOptionType)
                  ? text3 + StatCollector.getText("options.on", new Object[0])
                  : text3 + StatCollector.getText("options.off", new Object[0]);
            } else if (factoryOptionType == FactoryOptionType.GUI_SCALE) {
               return this.indexOffset109 >= stringArray.length ? text3 + this.indexOffset109 + "x" : text3 + resolveText(stringArray, this.indexOffset109);
            } else if (factoryOptionType == FactoryOptionType.CHAT_VISIBILITY) {
               return text3 + StatCollector.getText(this.gameSettingsFullType.getLabelText(), new Object[0]);
            } else if (factoryOptionType == FactoryOptionType.PARTICLES) {
               return text3 + resolveText(stringArray2, this.indexOffset134);
            } else if (factoryOptionType == FactoryOptionType.AMBIENT_OCCLUSION) {
               return text3 + resolveText(stringArray3, this.indexOffset36);
            } else if (factoryOptionType == FactoryOptionType.STREAM_COMPRESSION) {
               return text3 + resolveText(stringArray4, this.indexOffset88);
            } else if (factoryOptionType == FactoryOptionType.STREAM_CHAT_ENABLED) {
               return text3 + resolveText(stringArray5, this.indexOffset99);
            } else if (factoryOptionType == FactoryOptionType.STREAM_CHAT_USER_FILTER) {
               return text3 + resolveText(stringArray6, this.indexOffset63);
            } else if (factoryOptionType == FactoryOptionType.STREAM_MIC_TOGGLE_BEHAVIOR) {
               return text3 + resolveText(stringArray7, this.indexOffset94);
            } else if (factoryOptionType == FactoryOptionType.RENDER_CLOUDS) {
               return text3 + resolveText(stringArray8, this.indexOffset33);
            } else if (factoryOptionType == FactoryOptionType.GRAPHICS) {
               return this.state5
                  ? text3 + StatCollector.getText("options.graphics.fancy", new Object[0])
                  : text3 + StatCollector.getText("options.graphics.fast", new Object[0]);
            } else {
               return text3;
            }
         } else {
            float factor = this.computeFactor(factoryOptionType);
            float factor2 = factoryOptionType.computeFactor(factor);
            return factoryOptionType == FactoryOptionType.MIPMAP_LEVELS && (double)factor >= 4.0
               ? text3 + GeneratorDefaultManager.resolveText("of.general.max")
               : (
                  factoryOptionType == FactoryOptionType.SENSITIVITY
                     ? (
                        factor2 == 0.0F
                           ? text3 + StatCollector.getText("options.sensitivity.min", new Object[0])
                           : (factor2 == 1.0F ? text3 + StatCollector.getText("options.sensitivity.max", new Object[0]) : text3 + (int)(factor2 * 200.0F) + "%")
                     )
                     : (
                        factoryOptionType == FactoryOptionType.FOV
                           ? (
                              factor == 70.0F
                                 ? text3 + StatCollector.getText("options.fov.min", new Object[0])
                                 : (factor == 110.0F ? text3 + StatCollector.getText("options.fov.max", new Object[0]) : text3 + (int)factor)
                           )
                           : (
                              factoryOptionType == FactoryOptionType.FRAMERATE_LIMIT
                                 ? (
                                    factor == FactoryOptionType.computeFactor(factoryOptionType)
                                       ? text3 + StatCollector.getText("options.framerateLimit.max", new Object[0])
                                       : text3 + (int)factor + " fps"
                                 )
                                 : (
                                    factoryOptionType == FactoryOptionType.RENDER_CLOUDS
                                       ? (
                                          factor == FactoryOptionType.computeFactorByFactoryOptionTyp(factoryOptionType)
                                             ? text3 + StatCollector.getText("options.cloudHeight.min", new Object[0])
                                             : text3 + ((int)factor + 128)
                                       )
                                       : (
                                          factoryOptionType == FactoryOptionType.GAMMA
                                             ? (
                                                factor2 == 0.0F
                                                   ? text3 + StatCollector.getText("options.gamma.min", new Object[0])
                                                   : (
                                                      factor2 == 1.0F
                                                         ? text3 + StatCollector.getText("options.gamma.max", new Object[0])
                                                         : text3 + "+" + (int)(factor2 * 100.0F) + "%"
                                                   )
                                             )
                                             : (
                                                factoryOptionType == FactoryOptionType.SATURATION
                                                   ? text3 + (int)(factor2 * 400.0F) + "%"
                                                   : (
                                                      factoryOptionType == FactoryOptionType.CHAT_OPACITY
                                                         ? text3 + (int)(factor2 * 90.0F + 10.0F) + "%"
                                                         : (
                                                            factoryOptionType == FactoryOptionType.CHAT_HEIGHT_UNFOCUSED
                                                               ? text3 + GuiNewChat.computeCount(factor2) + "px"
                                                               : (
                                                                  factoryOptionType == FactoryOptionType.CHAT_HEIGHT_FOCUSED
                                                                     ? text3 + GuiNewChat.computeCount(factor2) + "px"
                                                                     : (
                                                                        factoryOptionType == FactoryOptionType.CHAT_WIDTH
                                                                           ? text3 + GuiNewChat.computeCountByFloat(factor2) + "px"
                                                                           : (
                                                                              factoryOptionType == FactoryOptionType.RENDER_DISTANCE
                                                                                 ? text3 + (int)factor + " chunks"
                                                                                 : (
                                                                                    factoryOptionType == FactoryOptionType.MIPMAP_LEVELS
                                                                                       ? (
                                                                                          factor == 0.0F
                                                                                             ? text3 + StatCollector.getText("options.off", new Object[0])
                                                                                             : text3 + (int)factor
                                                                                       )
                                                                                       : (
                                                                                          factoryOptionType == FactoryOptionType.STREAM_FPS
                                                                                             ? text3 + " fps"
                                                                                             : (
                                                                                                factoryOptionType == FactoryOptionType.STREAM_KBPS
                                                                                                   ? text3 + " Kbps"
                                                                                                   : (
                                                                                                      factoryOptionType == FactoryOptionType.STREAM_BYTES_PER_PIXEL
                                                                                                         ? text3 + "%.3f bpp"
                                                                                                         : (
                                                                                                            factor2 == 0.0F
                                                                                                               ? text3
                                                                                                                  + StatCollector.getText(
                                                                                                                     "options.off", new Object[0]
                                                                                                                  )
                                                                                                               : text3 + (int)(factor2 * 100.0F) + "%"
                                                                                                         )
                                                                                                   )
                                                                                             )
                                                                                       )
                                                                                 )
                                                                           )
                                                                     )
                                                               )
                                                         )
                                                   )
                                             )
                                       )
                                 )
                           )
                     )
               );
         }
      }
   }

   public static String resolveText(int count) {
      return count < 0
         ? StatCollector.getText("key.mouseButton", new Object[]{count + 101})
         : (count < 256 ? Keyboard.getKeyName(count) : String.format("%c", (char)(count - 256)).toUpperCase());
   }

   public static boolean matchesKeyBinding(KeyBinding keyBinding) {
      return keyBinding.getKeyCode() != 0 && (keyBinding.getKeyCode() < 0 ? Mouse.isButtonDown(keyBinding.getKeyCode() + 100) : Keyboard.isKeyDown(keyBinding.getKeyCode()));
   }

   public void processAction(KeyBinding keyBinding, int count) {
      keyBinding.setKeyCode(count);
      this.updateState();
   }

   public void updateState() {
      try {
         PrintWriter printWriter = new PrintWriter(new FileWriter(this.file));
         printWriter.println("invertYMouse:" + this.state20);
         printWriter.println("mouseSensitivity:" + this.scaleFactor);
         printWriter.println("fov:" + (this.scaleFactor4 - 70.0F) / 40.0F);
         printWriter.println("gamma:" + this.scaleFactor100);
         printWriter.println("saturation:" + this.scaleFactor117);
         printWriter.println("renderDistance:" + this.indexOffset16);
         printWriter.println("guiScale:" + this.indexOffset109);
         printWriter.println("particles:" + this.indexOffset134);
         printWriter.println("bobView:" + this.state28);
         printWriter.println("anaglyph3d:" + this.state105);
         printWriter.println("maxFps:" + this.indexOffset35);
         printWriter.println("fboEnable:" + this.state96);
         printWriter.println("difficulty:" + this.gameSettingsPeacefulType.getIndex());
         printWriter.println("fancyGraphics:" + this.state5);
         printWriter.println("ao:" + this.indexOffset36);
         switch (this.indexOffset33) {
            case 0:
               printWriter.println("renderClouds:false");
               break;
            case 1:
               printWriter.println("renderClouds:fast");
               break;
            case 2:
               printWriter.println("renderClouds:true");
         }

         printWriter.println("resourcePacks:" + gson.toJson(this.itemList));
         printWriter.println("incompatibleResourcePacks:" + gson.toJson(this.itemList2));
         printWriter.println("lastServer:" + this.text68);
         printWriter.println("lang:" + this.text93);
         printWriter.println("chatVisibility:" + this.gameSettingsFullType.getCount());
         printWriter.println("chatColors:" + this.state27);
         printWriter.println("chatLinks:" + this.state13);
         printWriter.println("chatLinksPrompt:" + this.state124);
         printWriter.println("chatOpacity:" + this.scaleFactor102);
         printWriter.println("snooperEnabled:" + this.state115);
         printWriter.println("fullscreen:" + this.state81);
         printWriter.println("enableVsync:" + this.state26);
         printWriter.println("useVbo:" + this.state112);
         printWriter.println("hideServerAddress:" + this.state129);
         printWriter.println("advancedItemTooltips:" + this.state136);
         printWriter.println("pauseOnLostFocus:" + this.state72);
         printWriter.println("touchscreen:" + this.state48);
         printWriter.println("overrideWidth:" + this.indexOffset65);
         printWriter.println("overrideHeight:" + this.indexOffset73);
         printWriter.println("heldItemTooltips:" + this.state82);
         printWriter.println("chatHeightFocused:" + this.scaleFactor41);
         printWriter.println("chatHeightUnfocused:" + this.scaleFactor8);
         printWriter.println("chatScale:" + this.scaleFactor29);
         printWriter.println("chatWidth:" + this.scaleFactor137);
         printWriter.println("showInventoryAchievementHint:" + this.state107);
         printWriter.println("mipmapLevels:" + this.indexOffset118);
         printWriter.println("streamBytesPerPixel:" + this.scaleFactor97);
         printWriter.println("streamMicVolume:" + this.scaleFactor131);
         printWriter.println("streamSystemVolume:" + this.scaleFactor12);
         printWriter.println("streamKbps:" + this.scaleFactor59);
         printWriter.println("streamFps:" + this.scaleFactor10);
         printWriter.println("streamCompression:" + this.indexOffset88);
         printWriter.println("streamSendMetadata:" + this.state116);
         printWriter.println("streamPreferredServer:" + this.text);
         printWriter.println("streamChatEnabled:" + this.indexOffset99);
         printWriter.println("streamChatUserFilter:" + this.indexOffset63);
         printWriter.println("streamMicToggleBehavior:" + this.indexOffset94);
         printWriter.println("forceUnicodeFont:" + this.state87);
         printWriter.println("allowBlockAlternatives:" + this.state123);
         printWriter.println("reducedDebugInfo:" + this.state22);
         printWriter.println("useNativeTransport:" + this.state98);
         printWriter.println("entityShadows:" + this.state91);

         for (KeyBinding keyBinding : this.scorePlayerTeamArray2) {
            printWriter.println("key_" + keyBinding.getKeyDescription() + ":" + keyBinding.getKeyCode());
         }

         for (AutoPlayTargetEntity autoPlayTargetEntity : AutoPlayTargetEntity.values()) {
            printWriter.println("soundCategory_" + autoPlayTargetEntity.getLabelText() + ":" + this.getScaleFactor(autoPlayTargetEntity));
         }

         for (TabListCapeType tabListCapeType : TabListCapeType.values()) {
            printWriter.println("modelPart_" + tabListCapeType.getLabelText() + ":" + this.entrySet.contains(tabListCapeType));
         }

         printWriter.close();
      } catch (Exception exception) {
         logger.error("Failed to save options", exception);
      }

      this.updateState3();
      this.updateState4();
   }

   private static int computeCount(int count, int[] countArray) {
      int count2 = computeCount2(count, countArray);
      if (count2 < 0) {
         return countArray[0];
      } else {
         if (++count2 >= countArray.length) {
            count2 = 0;
         }

         return countArray[count2];
      }
   }

   private void applyFactoryOptionTypeByFactoryOptionTyp(FactoryOptionType factoryOptionType, float factor) {
      if (factoryOptionType == FactoryOptionType.CLOUD_HEIGHT) {
         this.scaleFactor60 = factor;
         this.mcClient.renderGlobal.updateState11();
      }

      if (factoryOptionType == FactoryOptionType.AO_LEVEL) {
         this.scaleFactor6 = factor;
         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.AA_LEVEL) {
         int count = (int)factor;
         if (count > 0 && BuildManager.canExecute()) {
            BuildManager.applyText(GeneratorDefaultManager.resolveText("of.message.aa.shaders1"), GeneratorDefaultManager.resolveText("of.message.aa.shaders2"));
            return;
         }

         int[] countArray = new int[]{0, 2, 4, 6, 8, 12, 16};
         this.indexOffset89 = 0;

         for (int index = 0; index < countArray.length; index++) {
            if (count >= countArray[index]) {
               this.indexOffset89 = countArray[index];
            }
         }

         this.indexOffset89 = BuildManager.computeCount(this.indexOffset89, 0, 16);
      }

      if (factoryOptionType == FactoryOptionType.AF_LEVEL) {
         int count2 = (int)factor;
         if (count2 > 1 && BuildManager.canExecute()) {
            BuildManager.applyText(GeneratorDefaultManager.resolveText("of.message.af.shaders1"), GeneratorDefaultManager.resolveText("of.message.af.shaders2"));
            return;
         }

         this.indexOffset18 = 1;

         while (this.indexOffset18 * 2 <= count2) {
            this.indexOffset18 *= 2;
         }

         this.indexOffset18 = BuildManager.computeCount(this.indexOffset18, 1, 16);
         this.mcClient.updateState11();
      }

      if (factoryOptionType == FactoryOptionType.MIPMAP_TYPE) {
         int count3 = (int)factor;
         this.indexOffset43 = BuildManager.computeCount(count3, 0, 3);
         this.mcClient.updateState11();
      }

      if (factoryOptionType == FactoryOptionType.FULLSCREEN_MODE) {
         int count4 = (int)factor - 1;
         String[] textArray = BuildManager.getTextArray2();
         if (count4 < 0 || count4 >= textArray.length) {
            this.text42 = "Default";
            return;
         }

         this.text42 = textArray[count4];
      }
   }

   public void applyFactoryOptionTypeByFactoryOptionTyp(FactoryOptionType factoryOptionType, int count) {
      this.applyFactoryOptionType(factoryOptionType, count);
      if (factoryOptionType == FactoryOptionType.INVERT_MOUSE) {
         this.state20 = !this.state20;
      }

      if (factoryOptionType == FactoryOptionType.GUI_SCALE) {
         this.indexOffset109 += count;
         if (GuiScreen.isActiveAsFlag()) {
            this.indexOffset109 = 0;
         }

         DisplayMode displayMode = BuildManager.getDisplayMode();
         int count2 = displayMode.getWidth() / 320;
         int count3 = displayMode.getHeight() / 240;
         int count4 = Math.min(count2, count3);
         if (this.indexOffset109 < 0) {
            this.indexOffset109 = count4 - 1;
         }

         if (this.mcClient.isActive3() && this.indexOffset109 % 2 != 0) {
            this.indexOffset109 += count;
         }

         if (this.indexOffset109 < 0 || this.indexOffset109 >= count4) {
            this.indexOffset109 = 0;
         }
      }

      if (factoryOptionType == FactoryOptionType.PARTICLES) {
         this.indexOffset134 = (this.indexOffset134 + count) % 3;
      }

      if (factoryOptionType == FactoryOptionType.VIEW_BOBBING) {
         this.state28 = !this.state28;
      }

      if (factoryOptionType == FactoryOptionType.RENDER_CLOUDS) {
         this.indexOffset33 = (this.indexOffset33 + count) % 3;
      }

      if (factoryOptionType == FactoryOptionType.FORCE_UNICODE_FONT) {
         this.state87 = !this.state87;
         this.mcClient.fontRendererObj.setState(this.mcClient.getLanguageManager().isActiveAsFlag() || this.state87);
      }

      if (factoryOptionType == FactoryOptionType.FBO_ENABLE) {
         this.state96 = !this.state96;
      }

      if (factoryOptionType == FactoryOptionType.ANAGLYPH) {
         if (!this.state105 && BuildManager.canExecute()) {
            BuildManager.applyText(GeneratorDefaultManager.resolveText("of.message.an.shaders1"), GeneratorDefaultManager.resolveText("of.message.an.shaders2"));
            return;
         }

         this.state105 = !this.state105;
         this.mcClient.updateState11();
      }

      if (factoryOptionType == FactoryOptionType.GRAPHICS) {
         this.state5 = !this.state5;
         this.updateState6();
         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.AMBIENT_OCCLUSION) {
         this.indexOffset36 = (this.indexOffset36 + count) % 3;
         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.CHAT_VISIBILITY) {
         this.gameSettingsFullType = GameSettingsFullType.resolveGameSettingsFullType((this.gameSettingsFullType.getCount() + count) % 3);
      }

      if (factoryOptionType == FactoryOptionType.STREAM_COMPRESSION) {
         this.indexOffset88 = (this.indexOffset88 + count) % 3;
      }

      if (factoryOptionType == FactoryOptionType.STREAM_SEND_METADATA) {
         this.state116 = !this.state116;
      }

      if (factoryOptionType == FactoryOptionType.STREAM_CHAT_ENABLED) {
         this.indexOffset99 = (this.indexOffset99 + count) % 3;
      }

      if (factoryOptionType == FactoryOptionType.STREAM_CHAT_USER_FILTER) {
         this.indexOffset63 = (this.indexOffset63 + count) % 3;
      }

      if (factoryOptionType == FactoryOptionType.STREAM_MIC_TOGGLE_BEHAVIOR) {
         this.indexOffset94 = (this.indexOffset94 + count) % 2;
      }

      if (factoryOptionType == FactoryOptionType.CHAT_COLOR) {
         this.state27 = !this.state27;
      }

      if (factoryOptionType == FactoryOptionType.CHAT_LINKS) {
         this.state13 = !this.state13;
      }

      if (factoryOptionType == FactoryOptionType.CHAT_LINKS_PROMPT) {
         this.state124 = !this.state124;
      }

      if (factoryOptionType == FactoryOptionType.SNOOPER_ENABLED) {
         this.state115 = !this.state115;
      }

      if (factoryOptionType == FactoryOptionType.TOUCHSCREEN) {
         this.state48 = !this.state48;
      }

      if (factoryOptionType == FactoryOptionType.USE_FULLSCREEN) {
         this.state81 = !this.state81;
         if (this.mcClient.isCallingFromMinecraftThread() != this.state81) {
            this.mcClient.updateState16();
         }
      }

      if (factoryOptionType == FactoryOptionType.ENABLE_VSYNC) {
         this.state26 = !this.state26;
         Display.setVSyncEnabled(this.state26);
      }

      if (factoryOptionType == FactoryOptionType.USE_VBO) {
         this.state112 = !this.state112;
         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.BLOCK_ALTERNATIVES) {
         this.state123 = !this.state123;
         this.mcClient.renderGlobal.updateState();
      }

      if (factoryOptionType == FactoryOptionType.REDUCED_DEBUG_INFO) {
         this.state22 = !this.state22;
      }

      if (factoryOptionType == FactoryOptionType.ENTITY_SHADOWS) {
         this.state91 = !this.state91;
      }

      this.updateState();
   }

   public void updateState7() {
      this.indexOffset16 = 8;
      this.state28 = true;
      this.state105 = false;
      this.indexOffset35 = (int)FactoryOptionType.FRAMERATE_LIMIT.getFactor2();
      this.state26 = false;
      this.setVSyncEnabled();
      this.indexOffset118 = 4;
      this.state5 = true;
      this.indexOffset36 = 2;
      this.indexOffset33 = 2;
      this.scaleFactor4 = 70.0F;
      this.scaleFactor100 = 0.0F;
      this.indexOffset109 = 0;
      this.indexOffset134 = 0;
      this.state82 = true;
      this.state112 = false;
      this.state87 = false;
      this.indexOffset57 = 1;
      this.scaleFactor21 = 0.8F;
      this.indexOffset43 = 0;
      this.state103 = false;
      this.state85 = false;
      this.state34 = false;
      BuildManager.updateState8();
      this.state83 = BuildManager.isActive29();
      this.state66 = false;
      this.state75 = false;
      this.state50 = false;
      this.state56 = false;
      this.indexOffset7 = 0;
      this.state2 = true;
      this.state110 = true;
      this.indexOffset61 = 3;
      this.indexOffset101 = 1;
      this.state70 = true;
      this.state30 = true;
      this.state135 = true;
      this.scaleFactor6 = 1.0F;
      this.indexOffset89 = 0;
      this.indexOffset18 = 1;
      this.indexOffset92 = 0;
      this.scaleFactor60 = 0.0F;
      this.indexOffset121 = 0;
      this.indexOffset = 0;
      this.indexOffset46 = 3;
      this.indexOffset132 = 4000;
      this.state80 = false;
      this.state130 = false;
      this.state71 = false;
      this.state125 = true;
      this.state17 = true;
      this.state79 = true;
      this.state53 = true;
      this.indexOffset111 = 0;
      this.indexOffset40 = 1;
      this.state49 = false;
      this.indexOffset74 = 0;
      this.state45 = false;
      this.state76 = false;
      this.text42 = "Default";
      this.state31 = true;
      this.state114 = true;
      this.state120 = true;
      this.state86 = true;
      this.state127 = true;
      this.state44 = true;
      this.state37 = true;
      this.state126 = true;
      this.indexOffset95 = 2;
      this.state122 = false;
      this.state = true;
      this.indexOffset25 = 0;
      this.indexOffset15 = 0;
      this.state104 = true;
      this.state67 = true;
      this.state38 = true;
      this.state3 = true;
      this.state77 = true;
      this.state24 = true;
      this.state84 = true;
      this.state58 = true;
      this.state39 = true;
      this.state51 = true;
      this.state1 = true;
      this.state78 = true;
      this.state133 = true;
      this.state119 = true;
      this.state113 = true;
      PreEndHandManager.applyText2("OFF");
      PreEndHandManager.count5 = 0;
      PreEndHandManager.updateState77();
      PreEndHandManager.updateState25();
      this.updateState2();
      this.mcClient.updateState11();
      this.updateState();
   }

   public int getCount() {
      return this.indexOffset16 >= 4 ? this.indexOffset33 : 0;
   }

   private static int computeCount2(int count, int[] countArray) {
      for (int index = 0; index < countArray.length; index++) {
         if (countArray[index] == count) {
            return index;
         }
      }

      return -1;
   }

   private float computeFactorByFactoryOptionTyp(FactoryOptionType factoryOptionType) {
      if (factoryOptionType == FactoryOptionType.CLOUD_HEIGHT) {
         return this.scaleFactor60;
      } else if (factoryOptionType == FactoryOptionType.AO_LEVEL) {
         return this.scaleFactor6;
      } else if (factoryOptionType == FactoryOptionType.AA_LEVEL) {
         return (float)this.indexOffset89;
      } else if (factoryOptionType == FactoryOptionType.AF_LEVEL) {
         return (float)this.indexOffset18;
      } else if (factoryOptionType == FactoryOptionType.MIPMAP_TYPE) {
         return (float)this.indexOffset43;
      } else if (factoryOptionType != FactoryOptionType.FRAMERATE_LIMIT) {
         if (factoryOptionType == FactoryOptionType.FULLSCREEN_MODE) {
            if (this.text42.equals("Default")) {
               return 0.0F;
            } else {
               int count = Arrays.asList(BuildManager.getTextArray2()).indexOf(this.text42);
               return count < 0 ? 0.0F : (float)(count + 1);
            }
         } else {
            return Float.MAX_VALUE;
         }
      } else {
         return (float)this.indexOffset35 == FactoryOptionType.FRAMERATE_LIMIT.getFactor2() && this.state26 ? 0.0F : (float)this.indexOffset35;
      }
   }

   public void applyTabListCapeType(TabListCapeType tabListCapeType) {
      if (!this.processAction().contains(tabListCapeType)) {
         this.entrySet.add(tabListCapeType);
      } else {
         this.entrySet.remove(tabListCapeType);
      }

      this.updateState4();
   }

   private float computeFactor(String text2) {
      return text2.equals("true") ? 1.0F : (text2.equals("false") ? 0.0F : Float.parseFloat(text2));
   }

   public void updateState5() {
      FileInputStream inputStream = null;

      searchBlock: {
         try {
            if (this.file.exists()) {
               BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream = new FileInputStream(this.file)));
               this.entryMap.clear();

               String text2;
               while ((text2 = reader.readLine()) != null) {
                  try {
                     String[] textArray = text2.split(":");
                     if (textArray[0].equals("mouseSensitivity")) {
                        this.scaleFactor = this.computeFactor(textArray[1]);
                     }

                     if (textArray[0].equals("fov")) {
                        this.scaleFactor4 = this.computeFactor(textArray[1]) * 40.0F + 70.0F;
                     }

                     if (textArray[0].equals("gamma")) {
                        this.scaleFactor100 = this.computeFactor(textArray[1]);
                     }

                     if (textArray[0].equals("saturation")) {
                        this.scaleFactor117 = this.computeFactor(textArray[1]);
                     }

                     if (textArray[0].equals("invertYMouse")) {
                        this.state20 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("renderDistance")) {
                        this.indexOffset16 = Integer.parseInt(textArray[1]);
                     }

                     if (textArray[0].equals("guiScale")) {
                        this.indexOffset109 = Integer.parseInt(textArray[1]);
                     }

                     if (textArray[0].equals("particles")) {
                        this.indexOffset134 = Integer.parseInt(textArray[1]);
                     }

                     if (textArray[0].equals("bobView")) {
                        this.state28 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("anaglyph3d")) {
                        this.state105 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("maxFps")) {
                        this.indexOffset35 = Integer.parseInt(textArray[1]);
                        if (this.state26) {
                           this.indexOffset35 = (int)FactoryOptionType.FRAMERATE_LIMIT.getFactor2();
                        }

                        if (this.indexOffset35 <= 0) {
                           this.indexOffset35 = (int)FactoryOptionType.FRAMERATE_LIMIT.getFactor2();
                        }
                     }

                     if (textArray[0].equals("fboEnable")) {
                        this.state96 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("difficulty")) {
                        this.gameSettingsPeacefulType = GameSettingsPeacefulType.resolveGameSettingsPeacefulType(Integer.parseInt(textArray[1]));
                     }

                     if (textArray[0].equals("fancyGraphics")) {
                        this.state5 = textArray[1].equals("true");
                        this.updateState6();
                     }

                     if (textArray[0].equals("ao")) {
                        if (textArray[1].equals("true")) {
                           this.indexOffset36 = 2;
                        } else if (textArray[1].equals("false")) {
                           this.indexOffset36 = 0;
                        } else {
                           this.indexOffset36 = Integer.parseInt(textArray[1]);
                        }
                     }

                     if (textArray[0].equals("renderClouds")) {
                        if (textArray[1].equals("true")) {
                           this.indexOffset33 = 2;
                        } else if (textArray[1].equals("false")) {
                           this.indexOffset33 = 0;
                        } else if (textArray[1].equals("fast")) {
                           this.indexOffset33 = 1;
                        }
                     }

                     if (textArray[0].equals("resourcePacks")) {
                        this.itemList = (List)gson.fromJson(text2.substring(text2.indexOf(58) + 1), parameterizedType);
                        if (this.itemList == null) {
                           this.itemList = Lists.newArrayList();
                        }
                     }

                     if (textArray[0].equals("incompatibleResourcePacks")) {
                        this.itemList2 = (List)gson.fromJson(text2.substring(text2.indexOf(58) + 1), parameterizedType);
                        if (this.itemList2 == null) {
                           this.itemList2 = Lists.newArrayList();
                        }
                     }

                     if (textArray[0].equals("lastServer") && textArray.length >= 2) {
                        this.text68 = text2.substring(text2.indexOf(58) + 1);
                     }

                     if (textArray[0].equals("lang") && textArray.length >= 2) {
                        this.text93 = textArray[1];
                     }

                     if (textArray[0].equals("chatVisibility")) {
                        this.gameSettingsFullType = GameSettingsFullType.resolveGameSettingsFullType(Integer.parseInt(textArray[1]));
                     }

                     if (textArray[0].equals("chatColors")) {
                        this.state27 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("chatLinks")) {
                        this.state13 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("chatLinksPrompt")) {
                        this.state124 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("chatOpacity")) {
                        this.scaleFactor102 = this.computeFactor(textArray[1]);
                     }

                     if (textArray[0].equals("snooperEnabled")) {
                        this.state115 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("fullscreen")) {
                        this.state81 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("enableVsync")) {
                        this.state26 = textArray[1].equals("true");
                        if (this.state26) {
                           this.indexOffset35 = (int)FactoryOptionType.FRAMERATE_LIMIT.getFactor2();
                        }

                        this.setVSyncEnabled();
                     }

                     if (textArray[0].equals("useVbo")) {
                        this.state112 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("hideServerAddress")) {
                        this.state129 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("advancedItemTooltips")) {
                        this.state136 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("pauseOnLostFocus")) {
                        this.state72 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("touchscreen")) {
                        this.state48 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("overrideHeight")) {
                        this.indexOffset73 = Integer.parseInt(textArray[1]);
                     }

                     if (textArray[0].equals("overrideWidth")) {
                        this.indexOffset65 = Integer.parseInt(textArray[1]);
                     }

                     if (textArray[0].equals("heldItemTooltips")) {
                        this.state82 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("chatHeightFocused")) {
                        this.scaleFactor41 = this.computeFactor(textArray[1]);
                     }

                     if (textArray[0].equals("chatHeightUnfocused")) {
                        this.scaleFactor8 = this.computeFactor(textArray[1]);
                     }

                     if (textArray[0].equals("chatScale")) {
                        this.scaleFactor29 = this.computeFactor(textArray[1]);
                     }

                     if (textArray[0].equals("chatWidth")) {
                        this.scaleFactor137 = this.computeFactor(textArray[1]);
                     }

                     if (textArray[0].equals("showInventoryAchievementHint")) {
                        this.state107 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("mipmapLevels")) {
                        this.indexOffset118 = Integer.parseInt(textArray[1]);
                     }

                     if (textArray[0].equals("streamBytesPerPixel")) {
                        this.scaleFactor97 = this.computeFactor(textArray[1]);
                     }

                     if (textArray[0].equals("streamMicVolume")) {
                        this.scaleFactor131 = this.computeFactor(textArray[1]);
                     }

                     if (textArray[0].equals("streamSystemVolume")) {
                        this.scaleFactor12 = this.computeFactor(textArray[1]);
                     }

                     if (textArray[0].equals("streamKbps")) {
                        this.scaleFactor59 = this.computeFactor(textArray[1]);
                     }

                     if (textArray[0].equals("streamFps")) {
                        this.scaleFactor10 = this.computeFactor(textArray[1]);
                     }

                     if (textArray[0].equals("streamCompression")) {
                        this.indexOffset88 = Integer.parseInt(textArray[1]);
                     }

                     if (textArray[0].equals("streamSendMetadata")) {
                        this.state116 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("streamPreferredServer") && textArray.length >= 2) {
                        this.text = text2.substring(text2.indexOf(58) + 1);
                     }

                     if (textArray[0].equals("streamChatEnabled")) {
                        this.indexOffset99 = Integer.parseInt(textArray[1]);
                     }

                     if (textArray[0].equals("streamChatUserFilter")) {
                        this.indexOffset63 = Integer.parseInt(textArray[1]);
                     }

                     if (textArray[0].equals("streamMicToggleBehavior")) {
                        this.indexOffset94 = Integer.parseInt(textArray[1]);
                     }

                     if (textArray[0].equals("forceUnicodeFont")) {
                        this.state87 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("allowBlockAlternatives")) {
                        this.state123 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("reducedDebugInfo")) {
                        this.state22 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("useNativeTransport")) {
                        this.state98 = textArray[1].equals("true");
                     }

                     if (textArray[0].equals("entityShadows")) {
                        this.state91 = textArray[1].equals("true");
                     }

                     for (KeyBinding keyBinding : this.scorePlayerTeamArray2) {
                        if (textArray[0].equals("key_" + keyBinding.getKeyDescription())) {
                           keyBinding.setKeyCode(Integer.parseInt(textArray[1]));
                        }
                     }

                     for (AutoPlayTargetEntity autoPlayTargetEntity : AutoPlayTargetEntity.values()) {
                        if (textArray[0].equals("soundCategory_" + autoPlayTargetEntity.getLabelText())) {
                           this.entryMap.put(autoPlayTargetEntity, this.computeFactor(textArray[1]));
                        }
                     }

                     for (TabListCapeType tabListCapeType : TabListCapeType.values()) {
                        if (textArray[0].equals("modelPart_" + tabListCapeType.getLabelText())) {
                           this.applyTabListCapeType(tabListCapeType, textArray[1].equals("true"));
                        }
                     }
                  } catch (Exception exception) {
                     logger.warn("Skipping bad option: " + text2);
                     exception.printStackTrace();
                  }
               }

               KeyBinding.unPressAllKeys();
               reader.close();
               break searchBlock;
            }
         } catch (Exception exception2) {
            logger.error("Failed to load options", exception2);
            break searchBlock;
         } finally {
            IOUtils.closeQuietly(inputStream);
         }

         return;
      }

      this.updateState8();
   }

   private static String resolveText(String[] textArray, int count) {
      int count2 = count;
      if (count < 0 || count >= textArray.length) {
         count2 = 0;
      }

      return StatCollector.getText(textArray[count2], new Object[0]);
   }

   public GameSettings() {
      this.scorePlayerTeamArray2 = (KeyBinding[])ArrayUtils.addAll(
         new KeyBinding[]{
            this.keyBindAttack,
            this.keyBindUseItem,
            this.keyBindForward,
            this.keyBindLeft,
            this.keyBindBack,
            this.keyBindRight,
            this.keyBindJump,
            this.keyBindSneak,
            this.keyBindSprint,
            this.keyBindDrop,
            this.keyBindInventory,
            this.keyBindChat,
            this.keyBindPlayerList,
            this.keyBindPickBlock,
            this.keyBindCommand,
            this.keyBindScreenshot,
            this.keyBindTogglePerspective,
            this.keyBindSmoothCamera,
            this.keyBindStreamStartStop,
            this.keyBindStreamPauseUnpause,
            this.keyBindStreamCommercials,
            this.keyBindStreamToggleMic,
            this.keyBindFullscreen,
            this.keyBindSpectatorOutlines
         },
         this.scorePlayerTeamArray
      );
      this.gameSettingsPeacefulType = GameSettingsPeacefulType.NORMAL;
      this.text68 = "";
      this.scaleFactor4 = 70.0F;
      this.text93 = "en_US";
      this.state87 = false;
   }

   public GameSettings(Minecraft mc, File file3) {
      this.scorePlayerTeamArray2 = (KeyBinding[])ArrayUtils.addAll(
         new KeyBinding[]{
            this.keyBindAttack,
            this.keyBindUseItem,
            this.keyBindForward,
            this.keyBindLeft,
            this.keyBindBack,
            this.keyBindRight,
            this.keyBindJump,
            this.keyBindSneak,
            this.keyBindSprint,
            this.keyBindDrop,
            this.keyBindInventory,
            this.keyBindChat,
            this.keyBindPlayerList,
            this.keyBindPickBlock,
            this.keyBindCommand,
            this.keyBindScreenshot,
            this.keyBindTogglePerspective,
            this.keyBindSmoothCamera,
            this.keyBindStreamStartStop,
            this.keyBindStreamPauseUnpause,
            this.keyBindStreamCommercials,
            this.keyBindStreamToggleMic,
            this.keyBindFullscreen,
            this.keyBindSpectatorOutlines
         },
         this.scorePlayerTeamArray
      );
      this.gameSettingsPeacefulType = GameSettingsPeacefulType.NORMAL;
      this.text68 = "";
      this.scaleFactor4 = 70.0F;
      this.text93 = "en_US";
      this.state87 = false;
      this.mcClient = mc;
      this.file = new File(file3, "optionsEL.txt");
      if (mc.isEnabled2() && Runtime.getRuntime().maxMemory() >= 1000000000L) {
         FactoryOptionType.RENDER_DISTANCE.setFactor2(32.0F);
         if (Runtime.getRuntime().maxMemory() >= 1500000000L) {
            FactoryOptionType.RENDER_DISTANCE.setFactor2(48.0F);
         }

         if (Runtime.getRuntime().maxMemory() >= 2500000000L) {
            FactoryOptionType.RENDER_DISTANCE.setFactor2(64.0F);
         }
      } else {
         FactoryOptionType.RENDER_DISTANCE.setFactor2(16.0F);
      }

      this.indexOffset16 = mc.isEnabled2() ? 12 : 8;
      this.file2 = new File(file3, "optionsofEL.txt");
      this.indexOffset35 = (int)FactoryOptionType.FRAMERATE_LIMIT.getFactor2();
      this.ofKeyBindZoom = new KeyBinding("of.key.zoom", 46, "key.categories.misc");
      this.scorePlayerTeamArray2 = (KeyBinding[])ArrayUtils.add(this.scorePlayerTeamArray2, this.ofKeyBindZoom);
      CurveFilter.applyKeyBindingArray(this.scorePlayerTeamArray2, new KeyBinding[]{this.ofKeyBindZoom});
      this.indexOffset16 = 8;
      this.updateState5();
      BuildManager.applyGameSettings(this);
   }
}
