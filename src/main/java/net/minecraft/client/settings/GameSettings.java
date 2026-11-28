package net.minecraft.client.settings;

import java.lang.reflect.Type;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.optifine.ClearWater;
import net.optifine.CustomColors;
import net.optifine.CustomGuis;
import net.optifine.CustomSky;
import net.optifine.DynamicLights;
import net.optifine.Lang;
import net.optifine.NaturalTextures;
import net.optifine.RandomEntities;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import net.optifine.util.KeyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GameSettings {
   public static final int DEFAULT = 0;
   public static final int FAST = 1;
   public static final int FANCY = 2;
   public static final int OFF = 3;
   public static final int SMART = 4;
   public static final int ANIM_ON = 0;
   public static final int ANIM_GENERATED = 1;
   public static final int ANIM_OFF = 2;
   public static final String DEFAULT_STR = "Default";
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Gson gson = new Gson();
   private static final ParameterizedType typeListString = new GameSettings.DefaultParameterizedType();
   private static final String[] GUISCALES = new String[]{
      "options.guiScale.auto", "options.guiScale.small", "options.guiScale.normal", "options.guiScale.large"
   };
   private static final String[] PARTICLES = new String[]{"options.particles.all", "options.particles.decreased", "options.particles.minimal"};
   private static final String[] AMBIENT_OCCLUSIONS = new String[]{"options.ao.off", "options.ao.min", "options.ao.max"};
   private static final String[] STREAM_COMPRESSIONS = new String[]{
      "options.stream.compression.low", "options.stream.compression.medium", "options.stream.compression.high"
   };
   private static final String[] STREAM_CHAT_MODES = new String[]{
      "options.stream.chat.enabled.streaming", "options.stream.chat.enabled.always", "options.stream.chat.enabled.never"
   };
   private static final String[] STREAM_CHAT_FILTER_MODES = new String[]{
      "options.stream.chat.userFilter.all", "options.stream.chat.userFilter.subs", "options.stream.chat.userFilter.mods"
   };
   private static final String[] STREAM_MIC_MODES = new String[]{"options.stream.mic_toggle.mute", "options.stream.mic_toggle.talk"};
   private static final String[] CLOUDS_TYPES = new String[]{"options.off", "options.graphics.fast", "options.graphics.fancy"};
   private static final int[] OF_TREES_VALUES = new int[]{0, 1, 4, 2};
   private static final int[] OF_DYNAMIC_LIGHTS = new int[]{3, 1, 2};
   private static final String[] KEYS_DYNAMIC_LIGHTS = new String[]{"options.off", "options.graphics.fast", "options.graphics.fancy"};
   private final Set setModelParts = Sets.newHashSet(EnumPlayerModelParts.values());
   private final Map mapSoundLevels = Maps.newEnumMap(SoundCategory.class);
   public float mouseSensitivity = 0.5F;
   public boolean invertMouse;
   public int renderDistanceChunks = -1;
   public boolean viewBobbing = true;
   public boolean anaglyph;
   public boolean fboEnable = true;
   public int limitFramerate = 120;
   public int clouds = 2;
   public boolean fancyGraphics = true;
   public int ambientOcclusion = 2;
   public List resourcePacks = Lists.newArrayList();
   public List incompatibleResourcePacks = Lists.newArrayList();
   public EntityPlayer.EnumChatVisibility chatVisibility = EntityPlayer.EnumChatVisibility.FULL;
   public boolean chatColours = true;
   public boolean chatLinks = true;
   public boolean chatLinksPrompt = true;
   public float chatOpacity = 1.0F;
   public boolean snooperEnabled = true;
   public boolean fullScreen;
   public boolean enableVsync = true;
   public boolean useVbo = false;
   public boolean allowBlockAlternatives = true;
   public boolean reducedDebugInfo = false;
   public boolean hideServerAddress;
   public boolean advancedItemTooltips;
   public boolean pauseOnLostFocus = true;
   public boolean touchscreen;
   public int overrideWidth;
   public int overrideHeight;
   public boolean heldItemTooltips = true;
   public float chatScale = 1.0F;
   public float chatWidth = 1.0F;
   public float chatHeightUnfocused = 0.44366196F;
   public float chatHeightFocused = 1.0F;
   public boolean showInventoryAchievementHint = true;
   public int mipmapLevels = 4;
   public float streamBytesPerPixel = 0.5F;
   public float streamMicVolume = 1.0F;
   public float streamSystemVolume = 1.0F;
   public float streamKbps = 0.5412844F;
   public float streamFps = 0.31690142F;
   public int streamCompression = 1;
   public boolean streamSendMetadata = true;
   public String streamPreferredServer = "";
   public int streamChatEnabled = 0;
   public int streamChatUserFilter = 0;
   public int streamMicToggleBehavior = 0;
   public boolean useNativeTransport = true;
   public boolean entityShadows = true;
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
   public KeyBinding[] keyBindsHotbar = new KeyBinding[]{
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
   public KeyBinding[] keyBindings;
   public EnumDifficulty difficulty;
   public boolean hideGUI;
   public int thirdPersonView;
   public boolean showDebugInfo;
   public boolean showDebugProfilerChart;
   public boolean showLagometer;
   public String lastServer;
   public boolean smoothCamera;
   public boolean debugCamEnable;
   public float fovSetting;
   public float gammaSetting;
   public float saturation;
   public int guiScale;
   public int particleSetting;
   public String language;
   public boolean forceUnicodeFont;
   public int ofFogType = 1;
   public float ofFogStart = 0.8F;
   public int ofMipmapType = 0;
   public boolean ofOcclusionFancy = false;
   public boolean ofSmoothFps = false;
   public boolean ofSmoothWorld = Config.isSingleProcessor();
   public boolean ofLazyChunkLoading = Config.isSingleProcessor();
   public boolean ofRenderRegions = false;
   public boolean ofSmartAnimations = false;
   public float ofAoLevel = 1.0F;
   public int ofAaLevel = 0;
   public int ofAfLevel = 1;
   public int ofClouds = 0;
   public float ofCloudsHeight = 0.0F;
   public int ofTrees = 0;
   public int ofRain = 0;
   public int ofDroppedItems = 0;
   public int ofBetterGrass = 3;
   public int ofAutoSaveTicks = 4000;
   public boolean ofLagometer = false;
   public boolean ofProfiler = false;
   public boolean ofShowFps = false;
   public boolean ofWeather = true;
   public boolean ofSky = true;
   public boolean ofStars = true;
   public boolean ofSunMoon = true;
   public int ofVignette = 0;
   public int ofChunkUpdates = 1;
   public boolean ofChunkUpdatesDynamic = false;
   public int ofTime = 0;
   public boolean ofClearWater = false;
   public boolean ofBetterSnow = false;
   public String ofFullscreenMode = "Default";
   public boolean ofSwampColors = true;
   public boolean ofRandomEntities = true;
   public boolean ofSmoothBiomes = true;
   public boolean ofCustomFonts = true;
   public boolean ofCustomColors = true;
   public boolean ofCustomSky = true;
   public boolean ofShowCapes = true;
   public int ofConnectedTextures = 2;
   public boolean ofCustomItems = true;
   public boolean ofNaturalTextures = false;
   public boolean ofEmissiveTextures = true;
   public boolean ofFastMath = false;
   public boolean ofFastRender = false;
   public int ofTranslucentBlocks = 0;
   public boolean ofDynamicFov = true;
   public boolean ofAlternateBlocks = true;
   public int ofDynamicLights = 3;
   public boolean ofCustomEntityModels = true;
   public boolean ofCustomGuis = true;
   public boolean ofShowGlErrors = true;
   public int ofScreenshotSize = 1;
   public int ofAnimatedWater = 0;
   public int ofAnimatedLava = 0;
   public boolean ofAnimatedFire = true;
   public boolean ofAnimatedPortal = true;
   public boolean ofAnimatedRedstone = true;
   public boolean ofAnimatedExplosion = true;
   public boolean ofAnimatedFlame = true;
   public boolean ofAnimatedSmoke = true;
   public boolean ofVoidParticles = true;
   public boolean ofWaterParticles = true;
   public boolean ofRainSplash = true;
   public boolean ofPortalParticles = true;
   public boolean ofPotionParticles = true;
   public boolean ofFireworkParticles = true;
   public boolean ofDrippingWaterLava = true;
   public boolean ofAnimatedTerrain = true;
   public boolean ofAnimatedTextures = true;
   public KeyBinding ofKeyBindZoom;
   public Minecraft mc;
   private File optionsFile;
   private File optionsFileOF;

   private static int indexOf(int n, int[] ints) {
      for (int i = 0; i < ints.length; i++) {
         if (ints[i] == n) {
            return i;
         }
      }

      return -1;
   }

   public void setOptionValue(GameSettings.Options options, int n) {
      this.setOptionValueOF(options, n);
      if (options == GameSettings.Options.INVERT_MOUSE) {
         this.invertMouse = !this.invertMouse;
      }

      if (options == GameSettings.Options.GUI_SCALE) {
         this.guiScale += n;
         if (GuiScreen.isShiftKeyDown()) {
            this.guiScale = 0;
         }

         DisplayMode largestDisplayMode = Config.getLargestDisplayMode();
         int n4 = Math.min(largestDisplayMode.getWidth() / 320, largestDisplayMode.getHeight() / 240);
         if (this.guiScale < 0) {
            this.guiScale = n4 - 1;
         }

         if (this.mc.isUnicode() && this.guiScale % 2 != 0) {
            this.guiScale += n;
         }

         if (this.guiScale < 0 || this.guiScale >= n4) {
            this.guiScale = 0;
         }
      }

      if (options == GameSettings.Options.PARTICLES) {
         this.particleSetting = (this.particleSetting + n) % 3;
      }

      if (options == GameSettings.Options.VIEW_BOBBING) {
         this.viewBobbing = !this.viewBobbing;
      }

      if (options == GameSettings.Options.RENDER_CLOUDS) {
         this.clouds = (this.clouds + n) % 3;
      }

      if (options == GameSettings.Options.FORCE_UNICODE_FONT) {
         this.forceUnicodeFont = !this.forceUnicodeFont;
         this.mc.fontRendererObj.setUnicodeFlag(this.mc.getLanguageManager().isCurrentLocaleUnicode() || this.forceUnicodeFont);
      }

      if (options == GameSettings.Options.FBO_ENABLE) {
         this.fboEnable = !this.fboEnable;
      }

      if (options == GameSettings.Options.ANAGLYPH) {
         if (!this.anaglyph && Config.isShaderPackLoaded()) {
            Config.showGuiMessage(Lang.get("of.message.an.shaders1"), Lang.get("of.message.an.shaders2"));
            return;
         }

         this.anaglyph = !this.anaglyph;
         this.mc.refreshResources();
      }

      if (options == GameSettings.Options.GRAPHICS) {
         this.fancyGraphics = !this.fancyGraphics;
         this.updateRenderClouds();
         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.AMBIENT_OCCLUSION) {
         this.ambientOcclusion = (this.ambientOcclusion + n) % 3;
         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.CHAT_VISIBILITY) {
         this.chatVisibility = EntityPlayer.EnumChatVisibility.getEnumChatVisibility((this.chatVisibility.getChatVisibility() + n) % 3);
      }

      if (options == GameSettings.Options.STREAM_COMPRESSION) {
         this.streamCompression = (this.streamCompression + n) % 3;
      }

      if (options == GameSettings.Options.STREAM_SEND_METADATA) {
         this.streamSendMetadata = !this.streamSendMetadata;
      }

      if (options == GameSettings.Options.STREAM_CHAT_ENABLED) {
         this.streamChatEnabled = (this.streamChatEnabled + n) % 3;
      }

      if (options == GameSettings.Options.STREAM_CHAT_USER_FILTER) {
         this.streamChatUserFilter = (this.streamChatUserFilter + n) % 3;
      }

      if (options == GameSettings.Options.STREAM_MIC_TOGGLE_BEHAVIOR) {
         this.streamMicToggleBehavior = (this.streamMicToggleBehavior + n) % 2;
      }

      if (options == GameSettings.Options.CHAT_COLOR) {
         this.chatColours = !this.chatColours;
      }

      if (options == GameSettings.Options.CHAT_LINKS) {
         this.chatLinks = !this.chatLinks;
      }

      if (options == GameSettings.Options.CHAT_LINKS_PROMPT) {
         this.chatLinksPrompt = !this.chatLinksPrompt;
      }

      if (options == GameSettings.Options.SNOOPER_ENABLED) {
         this.snooperEnabled = !this.snooperEnabled;
      }

      if (options == GameSettings.Options.TOUCHSCREEN) {
         this.touchscreen = !this.touchscreen;
      }

      if (options == GameSettings.Options.USE_FULLSCREEN) {
         this.fullScreen = !this.fullScreen;
         if (this.mc.isFullScreen() != this.fullScreen) {
            this.mc.toggleFullscreen();
         }
      }

      if (options == GameSettings.Options.ENABLE_VSYNC) {
         this.enableVsync = !this.enableVsync;
         Display.setVSyncEnabled(this.enableVsync);
      }

      if (options == GameSettings.Options.USE_VBO) {
         this.useVbo = !this.useVbo;
         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.BLOCK_ALTERNATIVES) {
         this.allowBlockAlternatives = !this.allowBlockAlternatives;
         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.REDUCED_DEBUG_INFO) {
         this.reducedDebugInfo = !this.reducedDebugInfo;
      }

      if (options == GameSettings.Options.ENTITY_SHADOWS) {
         this.entityShadows = !this.entityShadows;
      }

      this.saveOptions();
   }

   public float getSoundLevel(SoundCategory soundCategory) {
      return this.mapSoundLevels.containsKey(soundCategory) ? (Float)this.mapSoundLevels.get(soundCategory) : 1.0F;
   }

   public GameSettings(Minecraft mc2, File file) {
      this.keyBindings = (KeyBinding[])ArrayUtils.addAll(
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
         this.keyBindsHotbar
      );
      this.difficulty = EnumDifficulty.NORMAL;
      this.lastServer = "";
      this.fovSetting = 70.0F;
      this.language = "en_US";
      this.forceUnicodeFont = false;
      this.mc = mc2;
      this.optionsFile = new File(file, "optionsEL.txt");
      if (mc2.isJava64bit() && Runtime.getRuntime().maxMemory() >= 1000000000L) {
         GameSettings.Options.RENDER_DISTANCE.setValueMax(32.0F);
         long l = 1000000L;
         if (Runtime.getRuntime().maxMemory() >= 1500L * l) {
            GameSettings.Options.RENDER_DISTANCE.setValueMax(48.0F);
         }

         if (Runtime.getRuntime().maxMemory() >= 2500L * l) {
            GameSettings.Options.RENDER_DISTANCE.setValueMax(64.0F);
         }
      } else {
         GameSettings.Options.RENDER_DISTANCE.setValueMax(16.0F);
      }

      this.renderDistanceChunks = mc2.isJava64bit() ? 12 : 8;
      this.optionsFileOF = new File(file, "optionsofEL.txt");
      this.limitFramerate = (int)GameSettings.Options.FRAMERATE_LIMIT.getValueMax();
      this.ofKeyBindZoom = new KeyBinding("of.key.zoom", 46, "key.categories.misc");
      this.keyBindings = (KeyBinding[])ArrayUtils.add(this.keyBindings, this.ofKeyBindZoom);
      KeyUtils.fixKeyConflicts(this.keyBindings, new KeyBinding[]{this.ofKeyBindZoom});
      this.renderDistanceChunks = 8;
      this.loadOptions();
      Config.initGameSettings(this);
   }

   public static boolean isKeyDown(KeyBinding keyBinding) {
      return keyBinding.getKeyCode() != 0 && (keyBinding.getKeyCode() < 0 ? Mouse.isButtonDown(keyBinding.getKeyCode() + 100) : Keyboard.isKeyDown(keyBinding.getKeyCode()));
   }

   public boolean isUsingNativeTransport() {
      if (!this.useNativeTransport) {
         throw new IllegalStateException("Native transport can't be disabled.");
      } else {
         return true;
      }
   }

   public void setOptionKeyBinding(KeyBinding keyBinding, int n) {
      keyBinding.setKeyCode(n);
      this.saveOptions();
   }

   public void setAllAnimations(boolean flag) {
      int n = flag ? 0 : 2;
      this.ofAnimatedWater = n;
      this.ofAnimatedLava = n;
      this.ofAnimatedFire = flag;
      this.ofAnimatedPortal = flag;
      this.ofAnimatedRedstone = flag;
      this.ofAnimatedExplosion = flag;
      this.ofAnimatedFlame = flag;
      this.ofAnimatedSmoke = flag;
      this.ofVoidParticles = flag;
      this.ofWaterParticles = flag;
      this.ofRainSplash = flag;
      this.ofPortalParticles = flag;
      this.ofPotionParticles = flag;
      this.ofFireworkParticles = flag;
      this.particleSetting = flag ? 0 : 2;
      this.ofDrippingWaterLava = flag;
      this.ofAnimatedTerrain = flag;
      this.ofAnimatedTextures = flag;
   }

   public void loadOptions() {
      FileInputStream fileInputStream = null;

      try {
         if (!this.optionsFile.exists()) {
            return;
         }
               BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream = new FileInputStream(this.optionsFile)));
               String str = "";
               this.mapSoundLevels.clear();

               while ((str = reader.readLine()) != null) {
                  try {
                     String[] strings = str.split(":");
                     if (strings[0].equals("mouseSensitivity")) {
                        this.mouseSensitivity = this.parseFloat(strings[1]);
                     }

                     if (strings[0].equals("fov")) {
                        this.fovSetting = this.parseFloat(strings[1]) * 40.0F + 70.0F;
                     }

                     if (strings[0].equals("gamma")) {
                        this.gammaSetting = this.parseFloat(strings[1]);
                     }

                     if (strings[0].equals("saturation")) {
                        this.saturation = this.parseFloat(strings[1]);
                     }

                     if (strings[0].equals("invertYMouse")) {
                        this.invertMouse = strings[1].equals("true");
                     }

                     if (strings[0].equals("renderDistance")) {
                        this.renderDistanceChunks = Integer.parseInt(strings[1]);
                     }

                     if (strings[0].equals("guiScale")) {
                        this.guiScale = Integer.parseInt(strings[1]);
                     }

                     if (strings[0].equals("particles")) {
                        this.particleSetting = Integer.parseInt(strings[1]);
                     }

                     if (strings[0].equals("bobView")) {
                        this.viewBobbing = strings[1].equals("true");
                     }

                     if (strings[0].equals("anaglyph3d")) {
                        this.anaglyph = strings[1].equals("true");
                     }

                     if (strings[0].equals("maxFps")) {
                        this.limitFramerate = Integer.parseInt(strings[1]);
                        if (this.limitFramerate <= 0) {
                           this.limitFramerate = (int)GameSettings.Options.FRAMERATE_LIMIT.getValueMax();
                        }
                     }

                     if (strings[0].equals("fboEnable")) {
                        this.fboEnable = strings[1].equals("true");
                     }

                     if (strings[0].equals("difficulty")) {
                        this.difficulty = EnumDifficulty.getDifficultyEnum(Integer.parseInt(strings[1]));
                     }

                     if (strings[0].equals("fancyGraphics")) {
                        this.fancyGraphics = strings[1].equals("true");
                        this.updateRenderClouds();
                     }

                     if (strings[0].equals("ao")) {
                        if (strings[1].equals("true")) {
                           this.ambientOcclusion = 2;
                        } else if (strings[1].equals("false")) {
                           this.ambientOcclusion = 0;
                        } else {
                           this.ambientOcclusion = Integer.parseInt(strings[1]);
                        }
                     }

                     if (strings[0].equals("renderClouds")) {
                        if (strings[1].equals("true")) {
                           this.clouds = 2;
                        } else if (strings[1].equals("false")) {
                           this.clouds = 0;
                        } else if (strings[1].equals("fast")) {
                           this.clouds = 1;
                        }
                     }

                     if (strings[0].equals("resourcePacks")) {
                        this.resourcePacks = (List)gson.fromJson(str.substring(str.indexOf(58) + 1), typeListString);
                        if (this.resourcePacks == null) {
                           this.resourcePacks = Lists.newArrayList();
                        }
                     }

                     if (strings[0].equals("incompatibleResourcePacks")) {
                        this.incompatibleResourcePacks = (List)gson.fromJson(str.substring(str.indexOf(58) + 1), typeListString);
                        if (this.incompatibleResourcePacks == null) {
                           this.incompatibleResourcePacks = Lists.newArrayList();
                        }
                     }

                     if (strings[0].equals("lastServer") && strings.length >= 2) {
                        this.lastServer = str.substring(str.indexOf(58) + 1);
                     }

                     if (strings[0].equals("lang") && strings.length >= 2) {
                        this.language = strings[1];
                     }

                     if (strings[0].equals("chatVisibility")) {
                        this.chatVisibility = EntityPlayer.EnumChatVisibility.getEnumChatVisibility(Integer.parseInt(strings[1]));
                     }

                     if (strings[0].equals("chatColors")) {
                        this.chatColours = strings[1].equals("true");
                     }

                     if (strings[0].equals("chatLinks")) {
                        this.chatLinks = strings[1].equals("true");
                     }

                     if (strings[0].equals("chatLinksPrompt")) {
                        this.chatLinksPrompt = strings[1].equals("true");
                     }

                     if (strings[0].equals("chatOpacity")) {
                        this.chatOpacity = this.parseFloat(strings[1]);
                     }

                     if (strings[0].equals("snooperEnabled")) {
                        this.snooperEnabled = strings[1].equals("true");
                     }

                     if (strings[0].equals("fullscreen")) {
                        this.fullScreen = strings[1].equals("true");
                     }

                     if (strings[0].equals("enableVsync")) {
                        this.enableVsync = strings[1].equals("true");
                        if (this.enableVsync) {
                           this.limitFramerate = (int)GameSettings.Options.FRAMERATE_LIMIT.getValueMax();
                        }

                        this.updateVSync();
                     }

                     if (strings[0].equals("useVbo")) {
                        this.useVbo = strings[1].equals("true");
                     }

                     if (strings[0].equals("hideServerAddress")) {
                        this.hideServerAddress = strings[1].equals("true");
                     }

                     if (strings[0].equals("advancedItemTooltips")) {
                        this.advancedItemTooltips = strings[1].equals("true");
                     }

                     if (strings[0].equals("pauseOnLostFocus")) {
                        this.pauseOnLostFocus = strings[1].equals("true");
                     }

                     if (strings[0].equals("touchscreen")) {
                        this.touchscreen = strings[1].equals("true");
                     }

                     if (strings[0].equals("overrideHeight")) {
                        this.overrideHeight = Integer.parseInt(strings[1]);
                     }

                     if (strings[0].equals("overrideWidth")) {
                        this.overrideWidth = Integer.parseInt(strings[1]);
                     }

                     if (strings[0].equals("heldItemTooltips")) {
                        this.heldItemTooltips = strings[1].equals("true");
                     }

                     if (strings[0].equals("chatHeightFocused")) {
                        this.chatHeightFocused = this.parseFloat(strings[1]);
                     }

                     if (strings[0].equals("chatHeightUnfocused")) {
                        this.chatHeightUnfocused = this.parseFloat(strings[1]);
                     }

                     if (strings[0].equals("chatScale")) {
                        this.chatScale = this.parseFloat(strings[1]);
                     }

                     if (strings[0].equals("chatWidth")) {
                        this.chatWidth = this.parseFloat(strings[1]);
                     }

                     if (strings[0].equals("showInventoryAchievementHint")) {
                        this.showInventoryAchievementHint = strings[1].equals("true");
                     }

                     if (strings[0].equals("mipmapLevels")) {
                        this.mipmapLevels = Integer.parseInt(strings[1]);
                     }

                     if (strings[0].equals("streamBytesPerPixel")) {
                        this.streamBytesPerPixel = this.parseFloat(strings[1]);
                     }

                     if (strings[0].equals("streamMicVolume")) {
                        this.streamMicVolume = this.parseFloat(strings[1]);
                     }

                     if (strings[0].equals("streamSystemVolume")) {
                        this.streamSystemVolume = this.parseFloat(strings[1]);
                     }

                     if (strings[0].equals("streamKbps")) {
                        this.streamKbps = this.parseFloat(strings[1]);
                     }

                     if (strings[0].equals("streamFps")) {
                        this.streamFps = this.parseFloat(strings[1]);
                     }

                     if (strings[0].equals("streamCompression")) {
                        this.streamCompression = Integer.parseInt(strings[1]);
                     }

                     if (strings[0].equals("streamSendMetadata")) {
                        this.streamSendMetadata = strings[1].equals("true");
                     }

                     if (strings[0].equals("streamPreferredServer") && strings.length >= 2) {
                        this.streamPreferredServer = str.substring(str.indexOf(58) + 1);
                     }

                     if (strings[0].equals("streamChatEnabled")) {
                        this.streamChatEnabled = Integer.parseInt(strings[1]);
                     }

                     if (strings[0].equals("streamChatUserFilter")) {
                        this.streamChatUserFilter = Integer.parseInt(strings[1]);
                     }

                     if (strings[0].equals("streamMicToggleBehavior")) {
                        this.streamMicToggleBehavior = Integer.parseInt(strings[1]);
                     }

                     if (strings[0].equals("forceUnicodeFont")) {
                        this.forceUnicodeFont = strings[1].equals("true");
                     }

                     if (strings[0].equals("allowBlockAlternatives")) {
                        this.allowBlockAlternatives = strings[1].equals("true");
                     }

                     if (strings[0].equals("reducedDebugInfo")) {
                        this.reducedDebugInfo = strings[1].equals("true");
                     }

                     if (strings[0].equals("useNativeTransport")) {
                        this.useNativeTransport = strings[1].equals("true");
                     }

                     if (strings[0].equals("entityShadows")) {
                        this.entityShadows = strings[1].equals("true");
                     }

                     for (KeyBinding keyBinding : this.keyBindings) {
                        if (strings[0].equals("key_" + keyBinding.getKeyDescription())) {
                           keyBinding.setKeyCode(Integer.parseInt(strings[1]));
                        }
                     }

                     for (SoundCategory soundCategory : SoundCategory.values()) {
                        if (strings[0].equals("soundCategory_" + soundCategory.getCategoryName())) {
                           this.mapSoundLevels.put(soundCategory, this.parseFloat(strings[1]));
                        }
                     }

                     for (EnumPlayerModelParts enumPlayerModelParts : EnumPlayerModelParts.values()) {
                        if (strings[0].equals("modelPart_" + enumPlayerModelParts.getPartName())) {
                           this.setModelPartEnabled(enumPlayerModelParts, strings[1].equals("true"));
                        }
                     }
                  } catch (Exception e) {
                     LOGGER.warn("Skipping bad option: " + str);
                     e.printStackTrace();
                  }
               }

         KeyBinding.resetKeyBindingArrayAndHash();
         reader.close();
      } catch (Exception e2) {
         LOGGER.error("Failed to load options", e2);
      } finally {
         IOUtils.closeQuietly(fileInputStream);
      }

      this.loadOfOptions();
   }

   public void resetSettings() {
      this.renderDistanceChunks = 8;
      this.viewBobbing = true;
      this.anaglyph = false;
      this.limitFramerate = (int)GameSettings.Options.FRAMERATE_LIMIT.getValueMax();
      this.enableVsync = false;
      this.updateVSync();
      this.mipmapLevels = 4;
      this.fancyGraphics = true;
      this.ambientOcclusion = 2;
      this.clouds = 2;
      this.fovSetting = 70.0F;
      this.gammaSetting = 0.0F;
      this.guiScale = 0;
      this.particleSetting = 0;
      this.heldItemTooltips = true;
      this.useVbo = false;
      this.forceUnicodeFont = false;
      this.ofFogType = 1;
      this.ofFogStart = 0.8F;
      this.ofMipmapType = 0;
      this.ofOcclusionFancy = false;
      this.ofSmartAnimations = false;
      this.ofSmoothFps = false;
      Config.updateAvailableProcessors();
      this.ofSmoothWorld = Config.isSingleProcessor();
      this.ofLazyChunkLoading = false;
      this.ofRenderRegions = false;
      this.ofFastMath = false;
      this.ofFastRender = false;
      this.ofTranslucentBlocks = 0;
      this.ofDynamicFov = true;
      this.ofAlternateBlocks = true;
      this.ofDynamicLights = 3;
      this.ofScreenshotSize = 1;
      this.ofCustomEntityModels = true;
      this.ofCustomGuis = true;
      this.ofShowGlErrors = true;
      this.ofAoLevel = 1.0F;
      this.ofAaLevel = 0;
      this.ofAfLevel = 1;
      this.ofClouds = 0;
      this.ofCloudsHeight = 0.0F;
      this.ofTrees = 0;
      this.ofRain = 0;
      this.ofBetterGrass = 3;
      this.ofAutoSaveTicks = 4000;
      this.ofLagometer = false;
      this.ofShowFps = false;
      this.ofProfiler = false;
      this.ofWeather = true;
      this.ofSky = true;
      this.ofStars = true;
      this.ofSunMoon = true;
      this.ofVignette = 0;
      this.ofChunkUpdates = 1;
      this.ofChunkUpdatesDynamic = false;
      this.ofTime = 0;
      this.ofClearWater = false;
      this.ofBetterSnow = false;
      this.ofFullscreenMode = "Default";
      this.ofSwampColors = true;
      this.ofRandomEntities = true;
      this.ofSmoothBiomes = true;
      this.ofCustomFonts = true;
      this.ofCustomColors = true;
      this.ofCustomItems = true;
      this.ofCustomSky = true;
      this.ofShowCapes = true;
      this.ofConnectedTextures = 2;
      this.ofNaturalTextures = false;
      this.ofEmissiveTextures = true;
      this.ofAnimatedWater = 0;
      this.ofAnimatedLava = 0;
      this.ofAnimatedFire = true;
      this.ofAnimatedPortal = true;
      this.ofAnimatedRedstone = true;
      this.ofAnimatedExplosion = true;
      this.ofAnimatedFlame = true;
      this.ofAnimatedSmoke = true;
      this.ofVoidParticles = true;
      this.ofWaterParticles = true;
      this.ofRainSplash = true;
      this.ofPortalParticles = true;
      this.ofPotionParticles = true;
      this.ofFireworkParticles = true;
      this.ofDrippingWaterLava = true;
      this.ofAnimatedTerrain = true;
      this.ofAnimatedTextures = true;
      Shaders.setShaderPack("OFF");
      Shaders.configAntialiasingLevel = 0;
      Shaders.uninit();
      Shaders.storeConfig();
      this.updateWaterOpacity();
      this.mc.refreshResources();
      this.saveOptions();
   }

   public boolean getOptionOrdinalValue(GameSettings.Options options) {
      switch (options) {
         case INVERT_MOUSE:
            return this.invertMouse;
         case VIEW_BOBBING:
            return this.viewBobbing;
         case ANAGLYPH:
            return this.anaglyph;
         case FBO_ENABLE:
            return this.fboEnable;
         case CHAT_COLOR:
            return this.chatColours;
         case CHAT_LINKS:
            return this.chatLinks;
         case CHAT_LINKS_PROMPT:
            return this.chatLinksPrompt;
         case SNOOPER_ENABLED:
            return this.snooperEnabled;
         case USE_FULLSCREEN:
            return this.fullScreen;
         case ENABLE_VSYNC:
            return this.enableVsync;
         case USE_VBO:
            return this.useVbo;
         case TOUCHSCREEN:
            return this.touchscreen;
         case STREAM_SEND_METADATA:
            return this.streamSendMetadata;
         case FORCE_UNICODE_FONT:
            return this.forceUnicodeFont;
         case BLOCK_ALTERNATIVES:
            return this.allowBlockAlternatives;
         case REDUCED_DEBUG_INFO:
            return this.reducedDebugInfo;
         case ENTITY_SHADOWS:
            return this.entityShadows;
         default:
            return false;
      }
   }

   public String getKeyBinding(GameSettings.Options options) {
      String keyBindingOF = this.getKeyBindingOF(options);
      if (keyBindingOF != null) {
         return keyBindingOF;
      } else {
         String str = I18n.format(options.getEnumString(), new Object[0]) + ": ";
         if (!options.getEnumFloat()) {
            if (options.getEnumBoolean()) {
               return this.getOptionOrdinalValue(options) ? str + I18n.format("options.on", new Object[0]) : str + I18n.format("options.off", new Object[0]);
            } else if (options == GameSettings.Options.GUI_SCALE) {
               return this.guiScale >= GUISCALES.length ? str + this.guiScale + "x" : str + getTranslation(GUISCALES, this.guiScale);
            } else if (options == GameSettings.Options.CHAT_VISIBILITY) {
               return str + I18n.format(this.chatVisibility.getResourceKey(), new Object[0]);
            } else if (options == GameSettings.Options.PARTICLES) {
               return str + getTranslation(PARTICLES, this.particleSetting);
            } else if (options == GameSettings.Options.AMBIENT_OCCLUSION) {
               return str + getTranslation(AMBIENT_OCCLUSIONS, this.ambientOcclusion);
            } else if (options == GameSettings.Options.STREAM_COMPRESSION) {
               return str + getTranslation(STREAM_COMPRESSIONS, this.streamCompression);
            } else if (options == GameSettings.Options.STREAM_CHAT_ENABLED) {
               return str + getTranslation(STREAM_CHAT_MODES, this.streamChatEnabled);
            } else if (options == GameSettings.Options.STREAM_CHAT_USER_FILTER) {
               return str + getTranslation(STREAM_CHAT_FILTER_MODES, this.streamChatUserFilter);
            } else if (options == GameSettings.Options.STREAM_MIC_TOGGLE_BEHAVIOR) {
               return str + getTranslation(STREAM_MIC_MODES, this.streamMicToggleBehavior);
            } else if (options == GameSettings.Options.RENDER_CLOUDS) {
               return str + getTranslation(CLOUDS_TYPES, this.clouds);
            } else if (options == GameSettings.Options.GRAPHICS) {
               if (this.fancyGraphics) {
                  return str + I18n.format("options.graphics.fancy", new Object[0]);
               } else {
                  String str2 = "options.graphics.fast";
                  return str + I18n.format("options.graphics.fast", new Object[0]);
               }
            } else {
               return str;
            }
         } else {
            float optionFloatValue = this.getOptionFloatValue(options);
            float f = options.normalizeValue(optionFloatValue);
            return options == GameSettings.Options.MIPMAP_LEVELS && (double)optionFloatValue >= 4.0
               ? str + Lang.get("of.general.max")
               : (
                  options == GameSettings.Options.SENSITIVITY
                     ? (
                        f == 0.0F
                           ? str + I18n.format("options.sensitivity.min", new Object[0])
                           : (f == 1.0F ? str + I18n.format("options.sensitivity.max", new Object[0]) : str + (int)(f * 200.0F) + "%")
                     )
                     : (
                        options == GameSettings.Options.FOV
                           ? (
                              optionFloatValue == 70.0F
                                 ? str + I18n.format("options.fov.min", new Object[0])
                                 : (optionFloatValue == 110.0F ? str + I18n.format("options.fov.max", new Object[0]) : str + (int)optionFloatValue)
                           )
                           : (
                              options == GameSettings.Options.FRAMERATE_LIMIT
                                 ? (
                                    optionFloatValue == GameSettings.Options.getOptionValueMax(options)
                                       ? str + I18n.format("options.framerateLimit.max", new Object[0])
                                       : str + (int)optionFloatValue + " fps"
                                 )
                                 : (
                                    options == GameSettings.Options.RENDER_CLOUDS
                                       ? (
                                          optionFloatValue == GameSettings.Options.getOptionValueMin(options)
                                             ? str + I18n.format("options.cloudHeight.min", new Object[0])
                                             : str + ((int)optionFloatValue + 128)
                                       )
                                       : (
                                          options == GameSettings.Options.GAMMA
                                             ? (
                                                f == 0.0F
                                                   ? str + I18n.format("options.gamma.min", new Object[0])
                                                   : (
                                                      f == 1.0F
                                                         ? str + I18n.format("options.gamma.max", new Object[0])
                                                         : str + "+" + (int)(f * 100.0F) + "%"
                                                   )
                                             )
                                             : (
                                                options == GameSettings.Options.SATURATION
                                                   ? str + (int)(f * 400.0F) + "%"
                                                   : (
                                                      options == GameSettings.Options.CHAT_OPACITY
                                                         ? str + (int)(f * 90.0F + 10.0F) + "%"
                                                         : (
                                                            options == GameSettings.Options.CHAT_HEIGHT_UNFOCUSED
                                                               ? str + GuiNewChat.calculateChatboxHeight(f) + "px"
                                                               : (
                                                                  options == GameSettings.Options.CHAT_HEIGHT_FOCUSED
                                                                     ? str + GuiNewChat.calculateChatboxHeight(f) + "px"
                                                                     : (
                                                                        options == GameSettings.Options.CHAT_WIDTH
                                                                           ? str + GuiNewChat.calculateChatboxWidth(f) + "px"
                                                                           : (
                                                                              options == GameSettings.Options.RENDER_DISTANCE
                                                                                 ? str + (int)optionFloatValue + " chunks"
                                                                                 : (
                                                                                    options == GameSettings.Options.MIPMAP_LEVELS
                                                                                       ? (
                                                                                          optionFloatValue == 0.0F
                                                                                             ? str + I18n.format("options.off", new Object[0])
                                                                                             : str + (int)optionFloatValue
                                                                                       )
                                                                                       : (
                                                                                          options == GameSettings.Options.STREAM_FPS
                                                                                             ? str + " fps"
                                                                                             : (
                                                                                                options == GameSettings.Options.STREAM_KBPS
                                                                                                   ? str + " Kbps"
                                                                                                   : (
                                                                                                      options == GameSettings.Options.STREAM_BYTES_PER_PIXEL
                                                                                                         ? str + "%.3f bpp"
                                                                                                         : (
                                                                                                            f == 0.0F
                                                                                                               ? str
                                                                                                                  + I18n.format("options.off", new Object[0])
                                                                                                               : str + (int)(f * 100.0F) + "%"
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

   public void setSoundLevel(SoundCategory soundCategory, float f) {
      this.mc.getSoundHandler().setSoundLevel(soundCategory, f);
      this.mapSoundLevels.put(soundCategory, f);
   }

   private String getKeyBindingOF(GameSettings.Options options) {
      String str = I18n.format(options.getEnumString(), new Object[0]) + ": ";
      if (str == null) {
         str = options.getEnumString();
      }

      if (options == GameSettings.Options.RENDER_DISTANCE) {
         int optionFloatValue = (int)this.getOptionFloatValue(options);
         String str2 = I18n.format("options.renderDistance.tiny", new Object[0]);
         byte b = 2;
         if (optionFloatValue >= 4) {
            str2 = I18n.format("options.renderDistance.short", new Object[0]);
            b = 4;
         }

         if (optionFloatValue >= 8) {
            str2 = I18n.format("options.renderDistance.normal", new Object[0]);
            b = 8;
         }

         if (optionFloatValue >= 16) {
            str2 = I18n.format("options.renderDistance.far", new Object[0]);
            b = 16;
         }

         if (optionFloatValue >= 32) {
            str2 = Lang.get("of.options.renderDistance.extreme");
            b = 32;
         }

         if (optionFloatValue >= 48) {
            str2 = Lang.get("of.options.renderDistance.insane");
            b = 48;
         }

         if (optionFloatValue >= 64) {
            str2 = Lang.get("of.options.renderDistance.ludicrous");
            b = 64;
         }

         int n = this.renderDistanceChunks - b;
         String str3 = str2;
         if (n > 0) {
            str3 = str2 + "+";
         }

         return str + optionFloatValue + " " + str3;
      } else if (options == GameSettings.Options.FOG_FANCY) {
         switch (this.ofFogType) {
            case 1:
               return str + Lang.getFast();
            case 2:
               return str + Lang.getFancy();
            case 3:
               return str + Lang.getOff();
            default:
               return str + Lang.getOff();
         }
      } else if (options == GameSettings.Options.FOG_START) {
         return str + this.ofFogStart;
      } else if (options == GameSettings.Options.MIPMAP_TYPE) {
         switch (this.ofMipmapType) {
            case 0:
               return str + Lang.get("of.options.mipmap.nearest");
            case 1:
               return str + Lang.get("of.options.mipmap.linear");
            case 2:
               return str + Lang.get("of.options.mipmap.bilinear");
            case 3:
               return str + Lang.get("of.options.mipmap.trilinear");
            default:
               return str + "of.options.mipmap.nearest";
         }
      } else if (options == GameSettings.Options.SMOOTH_FPS) {
         return this.ofSmoothFps ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.SMOOTH_WORLD) {
         return this.ofSmoothWorld ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.CLOUDS) {
         switch (this.ofClouds) {
            case 1:
               return str + Lang.getFast();
            case 2:
               return str + Lang.getFancy();
            case 3:
               return str + Lang.getOff();
            default:
               return str + Lang.getDefault();
         }
      } else if (options == GameSettings.Options.TREES) {
         switch (this.ofTrees) {
            case 1:
               return str + Lang.getFast();
            case 2:
               return str + Lang.getFancy();
            case 3:
            default:
               return str + Lang.getDefault();
            case 4:
               return str + Lang.get("of.general.smart");
         }
      } else if (options == GameSettings.Options.DROPPED_ITEMS) {
         switch (this.ofDroppedItems) {
            case 1:
               return str + Lang.getFast();
            case 2:
               return str + Lang.getFancy();
            default:
               return str + Lang.getDefault();
         }
      } else if (options == GameSettings.Options.RAIN) {
         switch (this.ofRain) {
            case 1:
               return str + Lang.getFast();
            case 2:
               return str + Lang.getFancy();
            case 3:
               return str + Lang.getOff();
            default:
               return str + Lang.getDefault();
         }
      } else if (options == GameSettings.Options.ANIMATED_WATER) {
         switch (this.ofAnimatedWater) {
            case 1:
               return str + Lang.get("of.options.animation.dynamic");
            case 2:
               return str + Lang.getOff();
            default:
               return str + Lang.getOn();
         }
      } else if (options == GameSettings.Options.ANIMATED_LAVA) {
         switch (this.ofAnimatedLava) {
            case 1:
               return str + Lang.get("of.options.animation.dynamic");
            case 2:
               return str + Lang.getOff();
            default:
               return str + Lang.getOn();
         }
      } else if (options == GameSettings.Options.ANIMATED_FIRE) {
         return this.ofAnimatedFire ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.ANIMATED_PORTAL) {
         return this.ofAnimatedPortal ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.ANIMATED_REDSTONE) {
         return this.ofAnimatedRedstone ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.ANIMATED_EXPLOSION) {
         return this.ofAnimatedExplosion ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.ANIMATED_FLAME) {
         return this.ofAnimatedFlame ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.ANIMATED_SMOKE) {
         return this.ofAnimatedSmoke ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.VOID_PARTICLES) {
         return this.ofVoidParticles ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.WATER_PARTICLES) {
         return this.ofWaterParticles ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.PORTAL_PARTICLES) {
         return this.ofPortalParticles ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.POTION_PARTICLES) {
         return this.ofPotionParticles ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.FIREWORK_PARTICLES) {
         return this.ofFireworkParticles ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.DRIPPING_WATER_LAVA) {
         return this.ofDrippingWaterLava ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.ANIMATED_TERRAIN) {
         return this.ofAnimatedTerrain ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.ANIMATED_TEXTURES) {
         return this.ofAnimatedTextures ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.RAIN_SPLASH) {
         return this.ofRainSplash ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.LAGOMETER) {
         return this.ofLagometer ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.SHOW_FPS) {
         return this.ofShowFps ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.AUTOSAVE_TICKS) {
         short s = 900;
         return this.ofAutoSaveTicks <= s
            ? str + Lang.get("of.options.save.45s")
            : (
               this.ofAutoSaveTicks <= 2 * s
                  ? str + Lang.get("of.options.save.90s")
                  : (
                     this.ofAutoSaveTicks <= 4 * s
                        ? str + Lang.get("of.options.save.3min")
                        : (
                           this.ofAutoSaveTicks <= 8 * s
                              ? str + Lang.get("of.options.save.6min")
                              : (this.ofAutoSaveTicks <= 16 * s ? str + Lang.get("of.options.save.12min") : str + Lang.get("of.options.save.24min"))
                        )
                  )
            );
      } else if (options == GameSettings.Options.BETTER_GRASS) {
         switch (this.ofBetterGrass) {
            case 1:
               return str + Lang.getFast();
            case 2:
               return str + Lang.getFancy();
            default:
               return str + Lang.getOff();
         }
      } else if (options == GameSettings.Options.CONNECTED_TEXTURES) {
         switch (this.ofConnectedTextures) {
            case 1:
               return str + Lang.getFast();
            case 2:
               return str + Lang.getFancy();
            default:
               return str + Lang.getOff();
         }
      } else if (options == GameSettings.Options.WEATHER) {
         return this.ofWeather ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.SKY) {
         return this.ofSky ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.STARS) {
         return this.ofStars ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.SUN_MOON) {
         return this.ofSunMoon ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.VIGNETTE) {
         switch (this.ofVignette) {
            case 1:
               return str + Lang.getFast();
            case 2:
               return str + Lang.getFancy();
            default:
               return str + Lang.getDefault();
         }
      } else if (options == GameSettings.Options.CHUNK_UPDATES) {
         return str + this.ofChunkUpdates;
      } else if (options == GameSettings.Options.CHUNK_UPDATES_DYNAMIC) {
         return this.ofChunkUpdatesDynamic ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.TIME) {
         return this.ofTime == 1
            ? str + Lang.get("of.options.time.dayOnly")
            : (this.ofTime == 2 ? str + Lang.get("of.options.time.nightOnly") : str + Lang.getDefault());
      } else if (options == GameSettings.Options.CLEAR_WATER) {
         return this.ofClearWater ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.AA_LEVEL) {
         String str4 = "";
         if (this.ofAaLevel != Config.getAntialiasingLevel()) {
            str4 = " (" + Lang.get("of.general.restart") + ")";
         }

         return this.ofAaLevel == 0 ? str + Lang.getOff() + str4 : str + this.ofAaLevel + str4;
      } else if (options == GameSettings.Options.AF_LEVEL) {
         return this.ofAfLevel == 1 ? str + Lang.getOff() : str + this.ofAfLevel;
      } else if (options == GameSettings.Options.PROFILER) {
         return this.ofProfiler ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.BETTER_SNOW) {
         return this.ofBetterSnow ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.SWAMP_COLORS) {
         return this.ofSwampColors ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.RANDOM_ENTITIES) {
         return this.ofRandomEntities ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.SMOOTH_BIOMES) {
         return this.ofSmoothBiomes ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.CUSTOM_FONTS) {
         return this.ofCustomFonts ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.CUSTOM_COLORS) {
         return this.ofCustomColors ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.CUSTOM_SKY) {
         return this.ofCustomSky ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.SHOW_CAPES) {
         return this.ofShowCapes ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.CUSTOM_ITEMS) {
         return this.ofCustomItems ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.NATURAL_TEXTURES) {
         return this.ofNaturalTextures ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.EMISSIVE_TEXTURES) {
         return this.ofEmissiveTextures ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.FAST_MATH) {
         return this.ofFastMath ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.FAST_RENDER) {
         return this.ofFastRender ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.TRANSLUCENT_BLOCKS) {
         return this.ofTranslucentBlocks == 1 ? str + Lang.getFast() : (this.ofTranslucentBlocks == 2 ? str + Lang.getFancy() : str + Lang.getDefault());
      } else if (options == GameSettings.Options.LAZY_CHUNK_LOADING) {
         return this.ofLazyChunkLoading ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.RENDER_REGIONS) {
         return this.ofRenderRegions ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.SMART_ANIMATIONS) {
         return this.ofSmartAnimations ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.DYNAMIC_FOV) {
         return this.ofDynamicFov ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.ALTERNATE_BLOCKS) {
         return this.ofAlternateBlocks ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.DYNAMIC_LIGHTS) {
         int n2 = indexOf(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
         return str + getTranslation(KEYS_DYNAMIC_LIGHTS, n2);
      } else if (options == GameSettings.Options.SCREENSHOT_SIZE) {
         return this.ofScreenshotSize <= 1 ? str + Lang.getDefault() : str + this.ofScreenshotSize + "x";
      } else if (options == GameSettings.Options.CUSTOM_ENTITY_MODELS) {
         return this.ofCustomEntityModels ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.CUSTOM_GUIS) {
         return this.ofCustomGuis ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.SHOW_GL_ERRORS) {
         return this.ofShowGlErrors ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.FULLSCREEN_MODE) {
         return this.ofFullscreenMode.equals("Default") ? str + Lang.getDefault() : str + this.ofFullscreenMode;
      } else if (options == GameSettings.Options.HELD_ITEM_TOOLTIPS) {
         return this.heldItemTooltips ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.ADVANCED_TOOLTIPS) {
         return this.advancedItemTooltips ? str + Lang.getOn() : str + Lang.getOff();
      } else if (options == GameSettings.Options.FRAMERATE_LIMIT) {
         float optionFloatValue2 = this.getOptionFloatValue(options);
         return optionFloatValue2 == 0.0F
            ? str + Lang.get("of.options.framerateLimit.vsync")
            : (optionFloatValue2 == GameSettings.Options.getOptionValueMax(options) ? str + I18n.format("options.framerateLimit.max", new Object[0]) : str + (int)optionFloatValue2 + " fps");
      } else {
         return null;
      }
   }

   public void setModelPartEnabled(EnumPlayerModelParts enumPlayerModelParts, boolean flag) {
      if (flag) {
         this.setModelParts.add(enumPlayerModelParts);
      } else {
         this.setModelParts.remove(enumPlayerModelParts);
      }

      this.sendSettingsToServer();
   }

   public float getOptionFloatValue(GameSettings.Options options) {
      float optionFloatValueOF = this.getOptionFloatValueOF(options);
      return optionFloatValueOF != Float.MAX_VALUE
         ? optionFloatValueOF
         : (
            options == GameSettings.Options.FOV
               ? this.fovSetting
               : (
                  options == GameSettings.Options.GAMMA
                     ? this.gammaSetting
                     : (
                        options == GameSettings.Options.SATURATION
                           ? this.saturation
                           : (
                              options == GameSettings.Options.SENSITIVITY
                                 ? this.mouseSensitivity
                                 : (
                                    options == GameSettings.Options.CHAT_OPACITY
                                       ? this.chatOpacity
                                       : (
                                          options == GameSettings.Options.CHAT_HEIGHT_FOCUSED
                                             ? this.chatHeightFocused
                                             : (
                                                options == GameSettings.Options.CHAT_HEIGHT_UNFOCUSED
                                                   ? this.chatHeightUnfocused
                                                   : (
                                                      options == GameSettings.Options.CHAT_SCALE
                                                         ? this.chatScale
                                                         : (
                                                            options == GameSettings.Options.CHAT_WIDTH
                                                               ? this.chatWidth
                                                               : (
                                                                  options == GameSettings.Options.FRAMERATE_LIMIT
                                                                     ? (float)this.limitFramerate
                                                                     : (
                                                                        options == GameSettings.Options.MIPMAP_LEVELS
                                                                           ? (float)this.mipmapLevels
                                                                           : (
                                                                              options == GameSettings.Options.RENDER_DISTANCE
                                                                                 ? (float)this.renderDistanceChunks
                                                                                 : (
                                                                                    options == GameSettings.Options.STREAM_BYTES_PER_PIXEL
                                                                                       ? this.streamBytesPerPixel
                                                                                       : (
                                                                                          options == GameSettings.Options.STREAM_VOLUME_MIC
                                                                                             ? this.streamMicVolume
                                                                                             : (
                                                                                                options == GameSettings.Options.STREAM_VOLUME_SYSTEM
                                                                                                   ? this.streamSystemVolume
                                                                                                   : (
                                                                                                      options == GameSettings.Options.STREAM_KBPS
                                                                                                         ? this.streamKbps
                                                                                                         : (
                                                                                                            options == GameSettings.Options.STREAM_FPS
                                                                                                               ? this.streamFps
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

   public static String getKeyDisplayString(int n) {
      return n < 0
         ? I18n.format("key.mouseButton", new Object[]{n + 101})
         : (n < 256 ? Keyboard.getKeyName(n) : String.format("%c", (char)(n - 256)).toUpperCase());
   }

   public void sendSettingsToServer() {
      if (this.mc.thePlayer != null) {
         int n = 0;

         for (EnumPlayerModelParts enumPlayerModelParts : (Iterable<EnumPlayerModelParts>)(this.setModelParts)) {
            n |= enumPlayerModelParts.getPartMask();
         }

         this.mc.thePlayer.sendQueue.addToSendQueue(new C15PacketClientSettings(this.language, this.renderDistanceChunks, this.chatVisibility, this.chatColours, n));
      }
   }

   public int shouldRenderClouds() {
      return this.renderDistanceChunks >= 4 ? this.clouds : 0;
   }

   private void setOptionFloatValueOF(GameSettings.Options options, float f) {
      if (options == GameSettings.Options.CLOUD_HEIGHT) {
         this.ofCloudsHeight = f;
         this.mc.renderGlobal.resetClouds();
      }

      if (options == GameSettings.Options.AO_LEVEL) {
         this.ofAoLevel = f;
         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.AA_LEVEL) {
         int n = (int)f;
         if (n > 0 && Config.isShaderPackLoaded()) {
            Config.showGuiMessage(Lang.get("of.message.aa.shaders1"), Lang.get("of.message.aa.shaders2"));
            return;
         }

         int[] ints = new int[]{0, 2, 4, 6, 8, 12, 16};
         this.ofAaLevel = 0;

         for (int i = 0; i < ints.length; i++) {
            if (n >= ints[i]) {
               this.ofAaLevel = ints[i];
            }
         }

         this.ofAaLevel = Config.limit(this.ofAaLevel, 0, 16);
      }

      if (options == GameSettings.Options.AF_LEVEL) {
         int n2 = (int)f;
         if (n2 > 1 && Config.isShaderPackLoaded()) {
            Config.showGuiMessage(Lang.get("of.message.af.shaders1"), Lang.get("of.message.af.shaders2"));
            return;
         }

         this.ofAfLevel = 1;

         while (this.ofAfLevel * 2 <= n2) {
            this.ofAfLevel *= 2;
         }

         this.ofAfLevel = Config.limit(this.ofAfLevel, 1, 16);
         this.mc.refreshResources();
      }

      if (options == GameSettings.Options.MIPMAP_TYPE) {
         this.ofMipmapType = Config.limit((int)f, 0, 3);
         this.mc.refreshResources();
      }

      if (options == GameSettings.Options.FULLSCREEN_MODE) {
         int n4 = (int)f - 1;
         String[] fullscreenModes = Config.getFullscreenModes();
         if (n4 < 0 || n4 >= fullscreenModes.length) {
            this.ofFullscreenMode = "Default";
            return;
         }

         this.ofFullscreenMode = fullscreenModes[n4];
      }
   }

   private float parseFloat(String str) {
      return str.equals("true") ? 1.0F : (str.equals("false") ? 0.0F : Float.parseFloat(str));
   }

   public void updateVSync() {
      Display.setVSyncEnabled(this.enableVsync);
   }

   private void updateWaterOpacity() {
      if (Config.isIntegratedServerRunning()) {
         Config.waterOpacityChanged = true;
      }

      ClearWater.updateWaterOpacity(this, this.mc.theWorld);
   }

   public void setOptionFloatValue(GameSettings.Options options, float f) {
      this.setOptionFloatValueOF(options, f);
      if (options == GameSettings.Options.SENSITIVITY) {
         this.mouseSensitivity = f;
      }

      if (options == GameSettings.Options.FOV) {
         this.fovSetting = f;
      }

      if (options == GameSettings.Options.GAMMA) {
         this.gammaSetting = f;
      }

      if (options == GameSettings.Options.FRAMERATE_LIMIT) {
         this.limitFramerate = (int)f;
         this.enableVsync = false;
         if (this.limitFramerate <= 0) {
            this.limitFramerate = (int)GameSettings.Options.FRAMERATE_LIMIT.getValueMax();
            this.enableVsync = true;
         }

         this.updateVSync();
      }

      if (options == GameSettings.Options.CHAT_OPACITY) {
         this.chatOpacity = f;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      if (options == GameSettings.Options.CHAT_HEIGHT_FOCUSED) {
         this.chatHeightFocused = f;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      if (options == GameSettings.Options.CHAT_HEIGHT_UNFOCUSED) {
         this.chatHeightUnfocused = f;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      if (options == GameSettings.Options.CHAT_WIDTH) {
         this.chatWidth = f;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      if (options == GameSettings.Options.CHAT_SCALE) {
         this.chatScale = f;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      if (options == GameSettings.Options.MIPMAP_LEVELS) {
         int mipmapLevels2 = this.mipmapLevels;
         this.mipmapLevels = (int)f;
         if ((float)mipmapLevels2 != f) {
            this.mc.getTextureMapBlocks().setMipmapLevels(this.mipmapLevels);
            this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            this.mc.getTextureMapBlocks().setBlurMipmapDirect(false, this.mipmapLevels > 0);
            this.mc.scheduleResourcesRefresh();
         }
      }

      if (options == GameSettings.Options.BLOCK_ALTERNATIVES) {
         this.allowBlockAlternatives = !this.allowBlockAlternatives;
         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.RENDER_DISTANCE) {
         this.renderDistanceChunks = (int)f;
         this.mc.renderGlobal.setDisplayListEntitiesDirty();
      }
   }

   public Set getModelParts() {
      return ImmutableSet.copyOf(this.setModelParts);
   }

   private static int limit(int n, int[] ints) {
      return indexOf(n, ints) < 0 ? ints[0] : n;
   }

   private float getOptionFloatValueOF(GameSettings.Options options) {
      if (options == GameSettings.Options.CLOUD_HEIGHT) {
         return this.ofCloudsHeight;
      } else if (options == GameSettings.Options.AO_LEVEL) {
         return this.ofAoLevel;
      } else if (options == GameSettings.Options.AA_LEVEL) {
         return (float)this.ofAaLevel;
      } else if (options == GameSettings.Options.AF_LEVEL) {
         return (float)this.ofAfLevel;
      } else if (options == GameSettings.Options.MIPMAP_TYPE) {
         return (float)this.ofMipmapType;
      } else if (options != GameSettings.Options.FRAMERATE_LIMIT) {
         if (options == GameSettings.Options.FULLSCREEN_MODE) {
            if (this.ofFullscreenMode.equals("Default")) {
               return 0.0F;
            } else {
               List list = Arrays.asList(Config.getFullscreenModes());
               int n = list.indexOf(this.ofFullscreenMode);
               return n < 0 ? 0.0F : (float)(n + 1);
            }
         } else {
            return Float.MAX_VALUE;
         }
      } else {
         return (float)this.limitFramerate == GameSettings.Options.FRAMERATE_LIMIT.getValueMax() && this.enableVsync ? 0.0F : (float)this.limitFramerate;
      }
   }

   private void updateRenderClouds() {
      switch (this.ofClouds) {
         case 1:
            this.clouds = 1;
            break;
         case 2:
            this.clouds = 2;
            break;
         case 3:
            this.clouds = 0;
            break;
         default:
            if (this.fancyGraphics) {
               this.clouds = 2;
            } else {
               this.clouds = 1;
            }
      }
   }

   public void saveOfOptions() {
      try {
         PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.optionsFileOF), StandardCharsets.UTF_8));
         writer.println("ofFogType:" + this.ofFogType);
         writer.println("ofFogStart:" + this.ofFogStart);
         writer.println("ofMipmapType:" + this.ofMipmapType);
         writer.println("ofOcclusionFancy:" + this.ofOcclusionFancy);
         writer.println("ofSmoothFps:" + this.ofSmoothFps);
         writer.println("ofSmoothWorld:" + this.ofSmoothWorld);
         writer.println("ofAoLevel:" + this.ofAoLevel);
         writer.println("ofClouds:" + this.ofClouds);
         writer.println("ofCloudsHeight:" + this.ofCloudsHeight);
         writer.println("ofTrees:" + this.ofTrees);
         writer.println("ofDroppedItems:" + this.ofDroppedItems);
         writer.println("ofRain:" + this.ofRain);
         writer.println("ofAnimatedWater:" + this.ofAnimatedWater);
         writer.println("ofAnimatedLava:" + this.ofAnimatedLava);
         writer.println("ofAnimatedFire:" + this.ofAnimatedFire);
         writer.println("ofAnimatedPortal:" + this.ofAnimatedPortal);
         writer.println("ofAnimatedRedstone:" + this.ofAnimatedRedstone);
         writer.println("ofAnimatedExplosion:" + this.ofAnimatedExplosion);
         writer.println("ofAnimatedFlame:" + this.ofAnimatedFlame);
         writer.println("ofAnimatedSmoke:" + this.ofAnimatedSmoke);
         writer.println("ofVoidParticles:" + this.ofVoidParticles);
         writer.println("ofWaterParticles:" + this.ofWaterParticles);
         writer.println("ofPortalParticles:" + this.ofPortalParticles);
         writer.println("ofPotionParticles:" + this.ofPotionParticles);
         writer.println("ofFireworkParticles:" + this.ofFireworkParticles);
         writer.println("ofDrippingWaterLava:" + this.ofDrippingWaterLava);
         writer.println("ofAnimatedTerrain:" + this.ofAnimatedTerrain);
         writer.println("ofAnimatedTextures:" + this.ofAnimatedTextures);
         writer.println("ofRainSplash:" + this.ofRainSplash);
         writer.println("ofLagometer:" + this.ofLagometer);
         writer.println("ofShowFps:" + this.ofShowFps);
         writer.println("ofAutoSaveTicks:" + this.ofAutoSaveTicks);
         writer.println("ofBetterGrass:" + this.ofBetterGrass);
         writer.println("ofConnectedTextures:" + this.ofConnectedTextures);
         writer.println("ofWeather:" + this.ofWeather);
         writer.println("ofSky:" + this.ofSky);
         writer.println("ofStars:" + this.ofStars);
         writer.println("ofSunMoon:" + this.ofSunMoon);
         writer.println("ofVignette:" + this.ofVignette);
         writer.println("ofChunkUpdates:" + this.ofChunkUpdates);
         writer.println("ofChunkUpdatesDynamic:" + this.ofChunkUpdatesDynamic);
         writer.println("ofTime:" + this.ofTime);
         writer.println("ofClearWater:" + this.ofClearWater);
         writer.println("ofAaLevel:" + this.ofAaLevel);
         writer.println("ofAfLevel:" + this.ofAfLevel);
         writer.println("ofProfiler:" + this.ofProfiler);
         writer.println("ofBetterSnow:" + this.ofBetterSnow);
         writer.println("ofSwampColors:" + this.ofSwampColors);
         writer.println("ofRandomEntities:" + this.ofRandomEntities);
         writer.println("ofSmoothBiomes:" + this.ofSmoothBiomes);
         writer.println("ofCustomFonts:" + this.ofCustomFonts);
         writer.println("ofCustomColors:" + this.ofCustomColors);
         writer.println("ofCustomItems:" + this.ofCustomItems);
         writer.println("ofCustomSky:" + this.ofCustomSky);
         writer.println("ofShowCapes:" + this.ofShowCapes);
         writer.println("ofNaturalTextures:" + this.ofNaturalTextures);
         writer.println("ofEmissiveTextures:" + this.ofEmissiveTextures);
         writer.println("ofLazyChunkLoading:" + this.ofLazyChunkLoading);
         writer.println("ofRenderRegions:" + this.ofRenderRegions);
         writer.println("ofSmartAnimations:" + this.ofSmartAnimations);
         writer.println("ofDynamicFov:" + this.ofDynamicFov);
         writer.println("ofAlternateBlocks:" + this.ofAlternateBlocks);
         writer.println("ofDynamicLights:" + this.ofDynamicLights);
         writer.println("ofScreenshotSize:" + this.ofScreenshotSize);
         writer.println("ofCustomEntityModels:" + this.ofCustomEntityModels);
         writer.println("ofCustomGuis:" + this.ofCustomGuis);
         writer.println("ofShowGlErrors:" + this.ofShowGlErrors);
         writer.println("ofFullscreenMode:" + this.ofFullscreenMode);
         writer.println("ofFastMath:" + this.ofFastMath);
         writer.println("ofFastRender:" + this.ofFastRender);
         writer.println("ofTranslucentBlocks:" + this.ofTranslucentBlocks);
         writer.println("key_" + this.ofKeyBindZoom.getKeyDescription() + ":" + this.ofKeyBindZoom.getKeyCode());
         writer.close();
      } catch (Exception e) {
         Config.warn("Failed to save options");
         e.printStackTrace();
      }
   }

   public void loadOfOptions() {
      try {
         File optionsFileOF2 = this.optionsFileOF;
         if (!optionsFileOF2.exists()) {
            optionsFileOF2 = this.optionsFile;
         }

         if (!optionsFileOF2.exists()) {
            return;
         }

         BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(optionsFileOF2), StandardCharsets.UTF_8));
         String str = "";

         while ((str = reader.readLine()) != null) {
            try {
               String[] strings = str.split(":");
               if (strings[0].equals("ofRenderDistanceChunks") && strings.length >= 2) {
                  this.renderDistanceChunks = Integer.parseInt(strings[1]);
                  this.renderDistanceChunks = Config.limit(this.renderDistanceChunks, 2, 1024);
               }

               if (strings[0].equals("ofFogType") && strings.length >= 2) {
                  this.ofFogType = Integer.parseInt(strings[1]);
                  this.ofFogType = Config.limit(this.ofFogType, 1, 3);
               }

               if (strings[0].equals("ofFogStart") && strings.length >= 2) {
                  this.ofFogStart = Float.parseFloat(strings[1]);
                  if (this.ofFogStart < 0.2F) {
                     this.ofFogStart = 0.2F;
                  }

                  if (this.ofFogStart > 0.81F) {
                     this.ofFogStart = 0.8F;
                  }
               }

               if (strings[0].equals("ofMipmapType") && strings.length >= 2) {
                  this.ofMipmapType = Integer.parseInt(strings[1]);
                  this.ofMipmapType = Config.limit(this.ofMipmapType, 0, 3);
               }

               if (strings[0].equals("ofOcclusionFancy") && strings.length >= 2) {
                  this.ofOcclusionFancy = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofSmoothFps") && strings.length >= 2) {
                  this.ofSmoothFps = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofSmoothWorld") && strings.length >= 2) {
                  this.ofSmoothWorld = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofAoLevel") && strings.length >= 2) {
                  this.ofAoLevel = Float.parseFloat(strings[1]);
                  this.ofAoLevel = Config.limit3(this.ofAoLevel, 0.0F, 1.0F);
               }

               if (strings[0].equals("ofClouds") && strings.length >= 2) {
                  this.ofClouds = Integer.parseInt(strings[1]);
                  this.ofClouds = Config.limit(this.ofClouds, 0, 3);
                  this.updateRenderClouds();
               }

               if (strings[0].equals("ofCloudsHeight") && strings.length >= 2) {
                  this.ofCloudsHeight = Float.parseFloat(strings[1]);
                  this.ofCloudsHeight = Config.limit3(this.ofCloudsHeight, 0.0F, 1.0F);
               }

               if (strings[0].equals("ofTrees") && strings.length >= 2) {
                  this.ofTrees = Integer.parseInt(strings[1]);
                  this.ofTrees = limit(this.ofTrees, OF_TREES_VALUES);
               }

               if (strings[0].equals("ofDroppedItems") && strings.length >= 2) {
                  this.ofDroppedItems = Integer.parseInt(strings[1]);
                  this.ofDroppedItems = Config.limit(this.ofDroppedItems, 0, 2);
               }

               if (strings[0].equals("ofRain") && strings.length >= 2) {
                  this.ofRain = Integer.parseInt(strings[1]);
                  this.ofRain = Config.limit(this.ofRain, 0, 3);
               }

               if (strings[0].equals("ofAnimatedWater") && strings.length >= 2) {
                  this.ofAnimatedWater = Integer.parseInt(strings[1]);
                  this.ofAnimatedWater = Config.limit(this.ofAnimatedWater, 0, 2);
               }

               if (strings[0].equals("ofAnimatedLava") && strings.length >= 2) {
                  this.ofAnimatedLava = Integer.parseInt(strings[1]);
                  this.ofAnimatedLava = Config.limit(this.ofAnimatedLava, 0, 2);
               }

               if (strings[0].equals("ofAnimatedFire") && strings.length >= 2) {
                  this.ofAnimatedFire = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofAnimatedPortal") && strings.length >= 2) {
                  this.ofAnimatedPortal = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofAnimatedRedstone") && strings.length >= 2) {
                  this.ofAnimatedRedstone = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofAnimatedExplosion") && strings.length >= 2) {
                  this.ofAnimatedExplosion = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofAnimatedFlame") && strings.length >= 2) {
                  this.ofAnimatedFlame = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofAnimatedSmoke") && strings.length >= 2) {
                  this.ofAnimatedSmoke = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofVoidParticles") && strings.length >= 2) {
                  this.ofVoidParticles = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofWaterParticles") && strings.length >= 2) {
                  this.ofWaterParticles = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofPortalParticles") && strings.length >= 2) {
                  this.ofPortalParticles = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofPotionParticles") && strings.length >= 2) {
                  this.ofPotionParticles = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofFireworkParticles") && strings.length >= 2) {
                  this.ofFireworkParticles = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofDrippingWaterLava") && strings.length >= 2) {
                  this.ofDrippingWaterLava = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofAnimatedTerrain") && strings.length >= 2) {
                  this.ofAnimatedTerrain = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofAnimatedTextures") && strings.length >= 2) {
                  this.ofAnimatedTextures = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofRainSplash") && strings.length >= 2) {
                  this.ofRainSplash = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofLagometer") && strings.length >= 2) {
                  this.ofLagometer = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofShowFps") && strings.length >= 2) {
                  this.ofShowFps = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofAutoSaveTicks") && strings.length >= 2) {
                  this.ofAutoSaveTicks = Integer.parseInt(strings[1]);
                  this.ofAutoSaveTicks = Config.limit(this.ofAutoSaveTicks, 40, 40000);
               }

               if (strings[0].equals("ofBetterGrass") && strings.length >= 2) {
                  this.ofBetterGrass = Integer.parseInt(strings[1]);
                  this.ofBetterGrass = Config.limit(this.ofBetterGrass, 1, 3);
               }

               if (strings[0].equals("ofConnectedTextures") && strings.length >= 2) {
                  this.ofConnectedTextures = Integer.parseInt(strings[1]);
                  this.ofConnectedTextures = Config.limit(this.ofConnectedTextures, 1, 3);
               }

               if (strings[0].equals("ofWeather") && strings.length >= 2) {
                  this.ofWeather = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofSky") && strings.length >= 2) {
                  this.ofSky = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofStars") && strings.length >= 2) {
                  this.ofStars = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofSunMoon") && strings.length >= 2) {
                  this.ofSunMoon = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofVignette") && strings.length >= 2) {
                  this.ofVignette = Integer.parseInt(strings[1]);
                  this.ofVignette = Config.limit(this.ofVignette, 0, 2);
               }

               if (strings[0].equals("ofChunkUpdates") && strings.length >= 2) {
                  this.ofChunkUpdates = Integer.parseInt(strings[1]);
                  this.ofChunkUpdates = Config.limit(this.ofChunkUpdates, 1, 5);
               }

               if (strings[0].equals("ofChunkUpdatesDynamic") && strings.length >= 2) {
                  this.ofChunkUpdatesDynamic = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofTime") && strings.length >= 2) {
                  this.ofTime = Integer.parseInt(strings[1]);
                  this.ofTime = Config.limit(this.ofTime, 0, 2);
               }

               if (strings[0].equals("ofClearWater") && strings.length >= 2) {
                  this.ofClearWater = Boolean.parseBoolean(strings[1]);
                  this.updateWaterOpacity();
               }

               if (strings[0].equals("ofAaLevel") && strings.length >= 2) {
                  this.ofAaLevel = Integer.parseInt(strings[1]);
                  this.ofAaLevel = Config.limit(this.ofAaLevel, 0, 16);
               }

               if (strings[0].equals("ofAfLevel") && strings.length >= 2) {
                  this.ofAfLevel = Integer.parseInt(strings[1]);
                  this.ofAfLevel = Config.limit(this.ofAfLevel, 1, 16);
               }

               if (strings[0].equals("ofProfiler") && strings.length >= 2) {
                  this.ofProfiler = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofBetterSnow") && strings.length >= 2) {
                  this.ofBetterSnow = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofSwampColors") && strings.length >= 2) {
                  this.ofSwampColors = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofRandomEntities") && strings.length >= 2) {
                  this.ofRandomEntities = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofSmoothBiomes") && strings.length >= 2) {
                  this.ofSmoothBiomes = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofCustomFonts") && strings.length >= 2) {
                  this.ofCustomFonts = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofCustomColors") && strings.length >= 2) {
                  this.ofCustomColors = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofCustomItems") && strings.length >= 2) {
                  this.ofCustomItems = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofCustomSky") && strings.length >= 2) {
                  this.ofCustomSky = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofShowCapes") && strings.length >= 2) {
                  this.ofShowCapes = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofNaturalTextures") && strings.length >= 2) {
                  this.ofNaturalTextures = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofEmissiveTextures") && strings.length >= 2) {
                  this.ofEmissiveTextures = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofLazyChunkLoading") && strings.length >= 2) {
                  this.ofLazyChunkLoading = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofRenderRegions") && strings.length >= 2) {
                  this.ofRenderRegions = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofSmartAnimations") && strings.length >= 2) {
                  this.ofSmartAnimations = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofDynamicFov") && strings.length >= 2) {
                  this.ofDynamicFov = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofAlternateBlocks") && strings.length >= 2) {
                  this.ofAlternateBlocks = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofDynamicLights") && strings.length >= 2) {
                  this.ofDynamicLights = Integer.parseInt(strings[1]);
                  this.ofDynamicLights = limit(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
               }

               if (strings[0].equals("ofScreenshotSize") && strings.length >= 2) {
                  this.ofScreenshotSize = Integer.parseInt(strings[1]);
                  this.ofScreenshotSize = Config.limit(this.ofScreenshotSize, 1, 4);
               }

               if (strings[0].equals("ofCustomEntityModels") && strings.length >= 2) {
                  this.ofCustomEntityModels = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofCustomGuis") && strings.length >= 2) {
                  this.ofCustomGuis = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofShowGlErrors") && strings.length >= 2) {
                  this.ofShowGlErrors = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofFullscreenMode") && strings.length >= 2) {
                  this.ofFullscreenMode = strings[1];
               }

               if (strings[0].equals("ofFastMath") && strings.length >= 2) {
                  this.ofFastMath = Boolean.parseBoolean(strings[1]);
                  MathHelper.fastMath = this.ofFastMath;
               }

               if (strings[0].equals("ofFastRender") && strings.length >= 2) {
                  this.ofFastRender = Boolean.parseBoolean(strings[1]);
               }

               if (strings[0].equals("ofTranslucentBlocks") && strings.length >= 2) {
                  this.ofTranslucentBlocks = Integer.parseInt(strings[1]);
                  this.ofTranslucentBlocks = Config.limit(this.ofTranslucentBlocks, 0, 2);
               }

               if (strings[0].equals("key_" + this.ofKeyBindZoom.getKeyDescription())) {
                  this.ofKeyBindZoom.setKeyCode(Integer.parseInt(strings[1]));
               }
            } catch (Exception e) {
               Config.dbg("Skipping bad option: " + str);
               e.printStackTrace();
            }
         }

         KeyUtils.fixKeyConflicts(this.keyBindings, new KeyBinding[]{this.ofKeyBindZoom});
         KeyBinding.resetKeyBindingArrayAndHash();
         reader.close();
      } catch (Exception e2) {
         Config.warn("Failed to load options");
         e2.printStackTrace();
      }
   }

   public void saveOptions() {
      try {
         PrintWriter writer = new PrintWriter(new FileWriter(this.optionsFile));
         writer.println("invertYMouse:" + this.invertMouse);
         writer.println("mouseSensitivity:" + this.mouseSensitivity);
         writer.println("fov:" + (this.fovSetting - 70.0F) / 40.0F);
         writer.println("gamma:" + this.gammaSetting);
         writer.println("saturation:" + this.saturation);
         writer.println("renderDistance:" + this.renderDistanceChunks);
         writer.println("guiScale:" + this.guiScale);
         writer.println("particles:" + this.particleSetting);
         writer.println("bobView:" + this.viewBobbing);
         writer.println("anaglyph3d:" + this.anaglyph);
         writer.println("maxFps:" + this.limitFramerate);
         writer.println("fboEnable:" + this.fboEnable);
         writer.println("difficulty:" + this.difficulty.getDifficultyId());
         writer.println("fancyGraphics:" + this.fancyGraphics);
         writer.println("ao:" + this.ambientOcclusion);
         switch (this.clouds) {
            case 0:
               writer.println("renderClouds:false");
               break;
            case 1:
               writer.println("renderClouds:fast");
               break;
            case 2:
               writer.println("renderClouds:true");
         }

         writer.println("resourcePacks:" + gson.toJson(this.resourcePacks));
         writer.println("incompatibleResourcePacks:" + gson.toJson(this.incompatibleResourcePacks));
         writer.println("lastServer:" + this.lastServer);
         writer.println("lang:" + this.language);
         writer.println("chatVisibility:" + this.chatVisibility.getChatVisibility());
         writer.println("chatColors:" + this.chatColours);
         writer.println("chatLinks:" + this.chatLinks);
         writer.println("chatLinksPrompt:" + this.chatLinksPrompt);
         writer.println("chatOpacity:" + this.chatOpacity);
         writer.println("snooperEnabled:" + this.snooperEnabled);
         writer.println("fullscreen:" + this.fullScreen);
         writer.println("enableVsync:" + this.enableVsync);
         writer.println("useVbo:" + this.useVbo);
         writer.println("hideServerAddress:" + this.hideServerAddress);
         writer.println("advancedItemTooltips:" + this.advancedItemTooltips);
         writer.println("pauseOnLostFocus:" + this.pauseOnLostFocus);
         writer.println("touchscreen:" + this.touchscreen);
         writer.println("overrideWidth:" + this.overrideWidth);
         writer.println("overrideHeight:" + this.overrideHeight);
         writer.println("heldItemTooltips:" + this.heldItemTooltips);
         writer.println("chatHeightFocused:" + this.chatHeightFocused);
         writer.println("chatHeightUnfocused:" + this.chatHeightUnfocused);
         writer.println("chatScale:" + this.chatScale);
         writer.println("chatWidth:" + this.chatWidth);
         writer.println("showInventoryAchievementHint:" + this.showInventoryAchievementHint);
         writer.println("mipmapLevels:" + this.mipmapLevels);
         writer.println("streamBytesPerPixel:" + this.streamBytesPerPixel);
         writer.println("streamMicVolume:" + this.streamMicVolume);
         writer.println("streamSystemVolume:" + this.streamSystemVolume);
         writer.println("streamKbps:" + this.streamKbps);
         writer.println("streamFps:" + this.streamFps);
         writer.println("streamCompression:" + this.streamCompression);
         writer.println("streamSendMetadata:" + this.streamSendMetadata);
         writer.println("streamPreferredServer:" + this.streamPreferredServer);
         writer.println("streamChatEnabled:" + this.streamChatEnabled);
         writer.println("streamChatUserFilter:" + this.streamChatUserFilter);
         writer.println("streamMicToggleBehavior:" + this.streamMicToggleBehavior);
         writer.println("forceUnicodeFont:" + this.forceUnicodeFont);
         writer.println("allowBlockAlternatives:" + this.allowBlockAlternatives);
         writer.println("reducedDebugInfo:" + this.reducedDebugInfo);
         writer.println("useNativeTransport:" + this.useNativeTransport);
         writer.println("entityShadows:" + this.entityShadows);

         for (KeyBinding keyBinding : this.keyBindings) {
            writer.println("key_" + keyBinding.getKeyDescription() + ":" + keyBinding.getKeyCode());
         }

         for (SoundCategory soundCategory : SoundCategory.values()) {
            writer.println("soundCategory_" + soundCategory.getCategoryName() + ":" + this.getSoundLevel(soundCategory));
         }

         for (EnumPlayerModelParts enumPlayerModelParts : EnumPlayerModelParts.values()) {
            writer.println("modelPart_" + enumPlayerModelParts.getPartName() + ":" + this.setModelParts.contains(enumPlayerModelParts));
         }

         writer.close();
      } catch (Exception e) {
         LOGGER.error("Failed to save options", e);
      }

      this.saveOfOptions();
      this.sendSettingsToServer();
   }

   public GameSettings() {
      this.keyBindings = (KeyBinding[])ArrayUtils.addAll(
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
         this.keyBindsHotbar
      );
      this.difficulty = EnumDifficulty.NORMAL;
      this.lastServer = "";
      this.fovSetting = 70.0F;
      this.language = "en_US";
      this.forceUnicodeFont = false;
   }

   private void setOptionValueOF(GameSettings.Options options, int n) {
      if (options == GameSettings.Options.FOG_FANCY) {
         switch (this.ofFogType) {
            case 1:
               this.ofFogType = 2;
               if (!Config.isFancyFogAvailable()) {
                  this.ofFogType = 3;
               }
               break;
            case 2:
               this.ofFogType = 3;
               break;
            case 3:
               this.ofFogType = 1;
               break;
            default:
               this.ofFogType = 1;
         }
      }

      if (options == GameSettings.Options.FOG_START) {
         this.ofFogStart += 0.2F;
         if (this.ofFogStart > 0.81F) {
            this.ofFogStart = 0.2F;
         }
      }

      if (options == GameSettings.Options.SMOOTH_FPS) {
         this.ofSmoothFps = !this.ofSmoothFps;
      }

      if (options == GameSettings.Options.SMOOTH_WORLD) {
         this.ofSmoothWorld = !this.ofSmoothWorld;
         Config.updateThreadPriorities();
      }

      if (options == GameSettings.Options.CLOUDS) {
         this.ofClouds++;
         if (this.ofClouds > 3) {
            this.ofClouds = 0;
         }

         this.updateRenderClouds();
         this.mc.renderGlobal.resetClouds();
      }

      if (options == GameSettings.Options.TREES) {
         this.ofTrees = nextValue(this.ofTrees, OF_TREES_VALUES);
         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.DROPPED_ITEMS) {
         this.ofDroppedItems++;
         if (this.ofDroppedItems > 2) {
            this.ofDroppedItems = 0;
         }
      }

      if (options == GameSettings.Options.RAIN) {
         this.ofRain++;
         if (this.ofRain > 3) {
            this.ofRain = 0;
         }
      }

      if (options == GameSettings.Options.ANIMATED_WATER) {
         this.ofAnimatedWater++;
         if (this.ofAnimatedWater == 1) {
            this.ofAnimatedWater++;
         }

         if (this.ofAnimatedWater > 2) {
            this.ofAnimatedWater = 0;
         }
      }

      if (options == GameSettings.Options.ANIMATED_LAVA) {
         this.ofAnimatedLava++;
         if (this.ofAnimatedLava == 1) {
            this.ofAnimatedLava++;
         }

         if (this.ofAnimatedLava > 2) {
            this.ofAnimatedLava = 0;
         }
      }

      if (options == GameSettings.Options.ANIMATED_FIRE) {
         this.ofAnimatedFire = !this.ofAnimatedFire;
      }

      if (options == GameSettings.Options.ANIMATED_PORTAL) {
         this.ofAnimatedPortal = !this.ofAnimatedPortal;
      }

      if (options == GameSettings.Options.ANIMATED_REDSTONE) {
         this.ofAnimatedRedstone = !this.ofAnimatedRedstone;
      }

      if (options == GameSettings.Options.ANIMATED_EXPLOSION) {
         this.ofAnimatedExplosion = !this.ofAnimatedExplosion;
      }

      if (options == GameSettings.Options.ANIMATED_FLAME) {
         this.ofAnimatedFlame = !this.ofAnimatedFlame;
      }

      if (options == GameSettings.Options.ANIMATED_SMOKE) {
         this.ofAnimatedSmoke = !this.ofAnimatedSmoke;
      }

      if (options == GameSettings.Options.VOID_PARTICLES) {
         this.ofVoidParticles = !this.ofVoidParticles;
      }

      if (options == GameSettings.Options.WATER_PARTICLES) {
         this.ofWaterParticles = !this.ofWaterParticles;
      }

      if (options == GameSettings.Options.PORTAL_PARTICLES) {
         this.ofPortalParticles = !this.ofPortalParticles;
      }

      if (options == GameSettings.Options.POTION_PARTICLES) {
         this.ofPotionParticles = !this.ofPotionParticles;
      }

      if (options == GameSettings.Options.FIREWORK_PARTICLES) {
         this.ofFireworkParticles = !this.ofFireworkParticles;
      }

      if (options == GameSettings.Options.DRIPPING_WATER_LAVA) {
         this.ofDrippingWaterLava = !this.ofDrippingWaterLava;
      }

      if (options == GameSettings.Options.ANIMATED_TERRAIN) {
         this.ofAnimatedTerrain = !this.ofAnimatedTerrain;
      }

      if (options == GameSettings.Options.ANIMATED_TEXTURES) {
         this.ofAnimatedTextures = !this.ofAnimatedTextures;
      }

      if (options == GameSettings.Options.RAIN_SPLASH) {
         this.ofRainSplash = !this.ofRainSplash;
      }

      if (options == GameSettings.Options.LAGOMETER) {
         this.ofLagometer = !this.ofLagometer;
      }

      if (options == GameSettings.Options.SHOW_FPS) {
         this.ofShowFps = !this.ofShowFps;
      }

      if (options == GameSettings.Options.AUTOSAVE_TICKS) {
         short s = 900;
         this.ofAutoSaveTicks = Math.max(this.ofAutoSaveTicks / s * s, s);
         this.ofAutoSaveTicks *= 2;
         if (this.ofAutoSaveTicks > 32 * s) {
            this.ofAutoSaveTicks = s;
         }
      }

      if (options == GameSettings.Options.BETTER_GRASS) {
         this.ofBetterGrass++;
         if (this.ofBetterGrass > 3) {
            this.ofBetterGrass = 1;
         }

         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.CONNECTED_TEXTURES) {
         this.ofConnectedTextures++;
         if (this.ofConnectedTextures > 3) {
            this.ofConnectedTextures = 1;
         }

         if (this.ofConnectedTextures == 2) {
            this.mc.renderGlobal.loadRenderers();
         } else {
            this.mc.refreshResources();
         }
      }

      if (options == GameSettings.Options.WEATHER) {
         this.ofWeather = !this.ofWeather;
      }

      if (options == GameSettings.Options.SKY) {
         this.ofSky = !this.ofSky;
      }

      if (options == GameSettings.Options.STARS) {
         this.ofStars = !this.ofStars;
      }

      if (options == GameSettings.Options.SUN_MOON) {
         this.ofSunMoon = !this.ofSunMoon;
      }

      if (options == GameSettings.Options.VIGNETTE) {
         this.ofVignette++;
         if (this.ofVignette > 2) {
            this.ofVignette = 0;
         }
      }

      if (options == GameSettings.Options.CHUNK_UPDATES) {
         this.ofChunkUpdates++;
         if (this.ofChunkUpdates > 5) {
            this.ofChunkUpdates = 1;
         }
      }

      if (options == GameSettings.Options.CHUNK_UPDATES_DYNAMIC) {
         this.ofChunkUpdatesDynamic = !this.ofChunkUpdatesDynamic;
      }

      if (options == GameSettings.Options.TIME) {
         this.ofTime++;
         if (this.ofTime > 2) {
            this.ofTime = 0;
         }
      }

      if (options == GameSettings.Options.CLEAR_WATER) {
         this.ofClearWater = !this.ofClearWater;
         this.updateWaterOpacity();
      }

      if (options == GameSettings.Options.PROFILER) {
         this.ofProfiler = !this.ofProfiler;
      }

      if (options == GameSettings.Options.BETTER_SNOW) {
         this.ofBetterSnow = !this.ofBetterSnow;
         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.SWAMP_COLORS) {
         this.ofSwampColors = !this.ofSwampColors;
         CustomColors.updateUseDefaultGrassFoliageColors();
         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.RANDOM_ENTITIES) {
         this.ofRandomEntities = !this.ofRandomEntities;
         RandomEntities.update();
      }

      if (options == GameSettings.Options.SMOOTH_BIOMES) {
         this.ofSmoothBiomes = !this.ofSmoothBiomes;
         CustomColors.updateUseDefaultGrassFoliageColors();
         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.CUSTOM_FONTS) {
         this.ofCustomFonts = !this.ofCustomFonts;
         this.mc.fontRendererObj.onResourceManagerReload(Config.getResourceManager());
         this.mc.standardGalacticFontRenderer.onResourceManagerReload(Config.getResourceManager());
      }

      if (options == GameSettings.Options.CUSTOM_COLORS) {
         this.ofCustomColors = !this.ofCustomColors;
         CustomColors.update();
         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.CUSTOM_ITEMS) {
         this.ofCustomItems = !this.ofCustomItems;
         this.mc.refreshResources();
      }

      if (options == GameSettings.Options.CUSTOM_SKY) {
         this.ofCustomSky = !this.ofCustomSky;
         CustomSky.update();
      }

      if (options == GameSettings.Options.SHOW_CAPES) {
         this.ofShowCapes = !this.ofShowCapes;
      }

      if (options == GameSettings.Options.NATURAL_TEXTURES) {
         this.ofNaturalTextures = !this.ofNaturalTextures;
         NaturalTextures.update();
         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.EMISSIVE_TEXTURES) {
         this.ofEmissiveTextures = !this.ofEmissiveTextures;
         this.mc.refreshResources();
      }

      if (options == GameSettings.Options.FAST_MATH) {
         this.ofFastMath = !this.ofFastMath;
         MathHelper.fastMath = this.ofFastMath;
      }

      if (options == GameSettings.Options.FAST_RENDER) {
         if (!this.ofFastRender && Config.isShaderPackLoaded()) {
            Config.showGuiMessage(Lang.get("of.message.fr.shaders1"), Lang.get("of.message.fr.shaders2"));
            return;
         }

         this.ofFastRender = !this.ofFastRender;
         if (this.ofFastRender) {
            this.mc.entityRenderer.stopUseShader();
         }

         Config.updateFramebufferSize();
      }

      if (options == GameSettings.Options.TRANSLUCENT_BLOCKS) {
         if (this.ofTranslucentBlocks == 0) {
            this.ofTranslucentBlocks = 1;
         } else if (this.ofTranslucentBlocks == 1) {
            this.ofTranslucentBlocks = 2;
         } else if (this.ofTranslucentBlocks == 2) {
            this.ofTranslucentBlocks = 0;
         } else {
            this.ofTranslucentBlocks = 0;
         }

         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.LAZY_CHUNK_LOADING) {
         this.ofLazyChunkLoading = !this.ofLazyChunkLoading;
      }

      if (options == GameSettings.Options.RENDER_REGIONS) {
         this.ofRenderRegions = !this.ofRenderRegions;
         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.SMART_ANIMATIONS) {
         this.ofSmartAnimations = !this.ofSmartAnimations;
         this.mc.renderGlobal.loadRenderers();
      }

      if (options == GameSettings.Options.DYNAMIC_FOV) {
         this.ofDynamicFov = !this.ofDynamicFov;
      }

      if (options == GameSettings.Options.ALTERNATE_BLOCKS) {
         this.ofAlternateBlocks = !this.ofAlternateBlocks;
         this.mc.refreshResources();
      }

      if (options == GameSettings.Options.DYNAMIC_LIGHTS) {
         this.ofDynamicLights = nextValue(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
         DynamicLights.removeLights(this.mc.renderGlobal);
      }

      if (options == GameSettings.Options.SCREENSHOT_SIZE) {
         this.ofScreenshotSize++;
         if (this.ofScreenshotSize > 4) {
            this.ofScreenshotSize = 1;
         }

         if (!OpenGlHelper.isFramebufferEnabled()) {
            this.ofScreenshotSize = 1;
         }
      }

      if (options == GameSettings.Options.CUSTOM_ENTITY_MODELS) {
         this.ofCustomEntityModels = !this.ofCustomEntityModels;
         this.mc.refreshResources();
      }

      if (options == GameSettings.Options.CUSTOM_GUIS) {
         this.ofCustomGuis = !this.ofCustomGuis;
         CustomGuis.update();
      }

      if (options == GameSettings.Options.SHOW_GL_ERRORS) {
         this.ofShowGlErrors = !this.ofShowGlErrors;
      }

      if (options == GameSettings.Options.HELD_ITEM_TOOLTIPS) {
         this.heldItemTooltips = !this.heldItemTooltips;
      }

      if (options == GameSettings.Options.ADVANCED_TOOLTIPS) {
         this.advancedItemTooltips = !this.advancedItemTooltips;
      }
   }

   public void switchModelPartEnabled(EnumPlayerModelParts enumPlayerModelParts) {
      if (!this.getModelParts().contains(enumPlayerModelParts)) {
         this.setModelParts.add(enumPlayerModelParts);
      } else {
         this.setModelParts.remove(enumPlayerModelParts);
      }

      this.sendSettingsToServer();
   }

   private static int nextValue(int n, int[] ints) {
      int n2 = indexOf(n, ints);
      if (n2 < 0) {
         return ints[0];
      } else {
         if (++n2 >= ints.length) {
            n2 = 0;
         }

         return ints[n2];
      }
   }

   private static String getTranslation(String[] strings, int n) {
      if (n < 0 || n >= strings.length) {
         n = 0;
      }

      return I18n.format(strings[n], new Object[0]);
   }

public static final class DefaultParameterizedType implements ParameterizedType {
      @Override
      public Type getOwnerType() {
         return null;
      }

      @Override
      public Type getRawType() {
         return List.class;
      }

      @Override
      public Type[] getActualTypeArguments() {
         return new Type[]{String.class};
      }
   }

public enum Options {
      INVERT_MOUSE("options.invertMouse", false, true),
      SENSITIVITY("options.sensitivity", true, false),
      FOV("options.fov", true, false, 30.0F, 110.0F, 1.0F),
      GAMMA("options.gamma", true, false),
      SATURATION("options.saturation", true, false),
      RENDER_DISTANCE("options.renderDistance", true, false, 2.0F, 16.0F, 1.0F),
      VIEW_BOBBING("options.viewBobbing", false, true),
      ANAGLYPH("options.anaglyph", false, true),
      FRAMERATE_LIMIT("options.framerateLimit", true, false, 0.0F, 260.0F, 5.0F),
      FBO_ENABLE("options.fboEnable", false, true),
      RENDER_CLOUDS("options.renderClouds", false, false),
      GRAPHICS("options.graphics", false, false),
      AMBIENT_OCCLUSION("options.ao", false, false),
      GUI_SCALE("options.guiScale", false, false),
      PARTICLES("options.particles", false, false),
      CHAT_VISIBILITY("options.chat.visibility", false, false),
      CHAT_COLOR("options.chat.color", false, true),
      CHAT_LINKS("options.chat.links", false, true),
      CHAT_OPACITY("options.chat.opacity", true, false),
      CHAT_LINKS_PROMPT("options.chat.links.prompt", false, true),
      SNOOPER_ENABLED("options.snooper", false, true),
      USE_FULLSCREEN("options.fullscreen", false, true),
      ENABLE_VSYNC("options.vsync", false, true),
      USE_VBO("options.vbo", false, true),
      TOUCHSCREEN("options.touchscreen", false, true),
      CHAT_SCALE("options.chat.scale", true, false),
      CHAT_WIDTH("options.chat.width", true, false),
      CHAT_HEIGHT_FOCUSED("options.chat.height.focused", true, false),
      CHAT_HEIGHT_UNFOCUSED("options.chat.height.unfocused", true, false),
      MIPMAP_LEVELS("options.mipmapLevels", true, false, 0.0F, 4.0F, 1.0F),
      FORCE_UNICODE_FONT("options.forceUnicodeFont", false, true),
      STREAM_BYTES_PER_PIXEL("options.stream.bytesPerPixel", true, false),
      STREAM_VOLUME_MIC("options.stream.micVolumne", true, false),
      STREAM_VOLUME_SYSTEM("options.stream.systemVolume", true, false),
      STREAM_KBPS("options.stream.kbps", true, false),
      STREAM_FPS("options.stream.fps", true, false),
      STREAM_COMPRESSION("options.stream.compression", false, false),
      STREAM_SEND_METADATA("options.stream.sendMetadata", false, true),
      STREAM_CHAT_ENABLED("options.stream.chat.enabled", false, false),
      STREAM_CHAT_USER_FILTER("options.stream.chat.userFilter", false, false),
      STREAM_MIC_TOGGLE_BEHAVIOR("options.stream.micToggleBehavior", false, false),
      BLOCK_ALTERNATIVES("options.blockAlternatives", false, true),
      REDUCED_DEBUG_INFO("options.reducedDebugInfo", false, true),
      ENTITY_SHADOWS("options.entityShadows", false, true),
      FOG_FANCY("of.options.FOG_FANCY", false, false),
      FOG_START("of.options.FOG_START", false, false),
      MIPMAP_TYPE("of.options.MIPMAP_TYPE", true, false, 0.0F, 3.0F, 1.0F),
      SMOOTH_FPS("of.options.SMOOTH_FPS", false, false),
      CLOUDS("of.options.CLOUDS", false, false),
      CLOUD_HEIGHT("of.options.CLOUD_HEIGHT", true, false),
      TREES("of.options.TREES", false, false),
      RAIN("of.options.RAIN", false, false),
      ANIMATED_WATER("of.options.ANIMATED_WATER", false, false),
      ANIMATED_LAVA("of.options.ANIMATED_LAVA", false, false),
      ANIMATED_FIRE("of.options.ANIMATED_FIRE", false, false),
      ANIMATED_PORTAL("of.options.ANIMATED_PORTAL", false, false),
      AO_LEVEL("of.options.AO_LEVEL", true, false),
      LAGOMETER("of.options.LAGOMETER", false, false),
      SHOW_FPS("of.options.SHOW_FPS", false, false),
      AUTOSAVE_TICKS("of.options.AUTOSAVE_TICKS", false, false),
      BETTER_GRASS("of.options.BETTER_GRASS", false, false),
      ANIMATED_REDSTONE("of.options.ANIMATED_REDSTONE", false, false),
      ANIMATED_EXPLOSION("of.options.ANIMATED_EXPLOSION", false, false),
      ANIMATED_FLAME("of.options.ANIMATED_FLAME", false, false),
      ANIMATED_SMOKE("of.options.ANIMATED_SMOKE", false, false),
      WEATHER("of.options.WEATHER", false, false),
      SKY("of.options.SKY", false, false),
      STARS("of.options.STARS", false, false),
      SUN_MOON("of.options.SUN_MOON", false, false),
      VIGNETTE("of.options.VIGNETTE", false, false),
      CHUNK_UPDATES("of.options.CHUNK_UPDATES", false, false),
      CHUNK_UPDATES_DYNAMIC("of.options.CHUNK_UPDATES_DYNAMIC", false, false),
      TIME("of.options.TIME", false, false),
      CLEAR_WATER("of.options.CLEAR_WATER", false, false),
      SMOOTH_WORLD("of.options.SMOOTH_WORLD", false, false),
      VOID_PARTICLES("of.options.VOID_PARTICLES", false, false),
      WATER_PARTICLES("of.options.WATER_PARTICLES", false, false),
      RAIN_SPLASH("of.options.RAIN_SPLASH", false, false),
      PORTAL_PARTICLES("of.options.PORTAL_PARTICLES", false, false),
      POTION_PARTICLES("of.options.POTION_PARTICLES", false, false),
      FIREWORK_PARTICLES("of.options.FIREWORK_PARTICLES", false, false),
      PROFILER("of.options.PROFILER", false, false),
      DRIPPING_WATER_LAVA("of.options.DRIPPING_WATER_LAVA", false, false),
      BETTER_SNOW("of.options.BETTER_SNOW", false, false),
      FULLSCREEN_MODE("of.options.FULLSCREEN_MODE", true, false, 0.0F, (float)Config.getDisplayModes().length, 1.0F),
      ANIMATED_TERRAIN("of.options.ANIMATED_TERRAIN", false, false),
      SWAMP_COLORS("of.options.SWAMP_COLORS", false, false),
      RANDOM_ENTITIES("of.options.RANDOM_ENTITIES", false, false),
      SMOOTH_BIOMES("of.options.SMOOTH_BIOMES", false, false),
      CUSTOM_FONTS("of.options.CUSTOM_FONTS", false, false),
      CUSTOM_COLORS("of.options.CUSTOM_COLORS", false, false),
      SHOW_CAPES("of.options.SHOW_CAPES", false, false),
      CONNECTED_TEXTURES("of.options.CONNECTED_TEXTURES", false, false),
      CUSTOM_ITEMS("of.options.CUSTOM_ITEMS", false, false),
      AA_LEVEL("of.options.AA_LEVEL", true, false, 0.0F, 16.0F, 1.0F),
      AF_LEVEL("of.options.AF_LEVEL", true, false, 1.0F, 16.0F, 1.0F),
      ANIMATED_TEXTURES("of.options.ANIMATED_TEXTURES", false, false),
      NATURAL_TEXTURES("of.options.NATURAL_TEXTURES", false, false),
      EMISSIVE_TEXTURES("of.options.EMISSIVE_TEXTURES", false, false),
      HELD_ITEM_TOOLTIPS("of.options.HELD_ITEM_TOOLTIPS", false, false),
      DROPPED_ITEMS("of.options.DROPPED_ITEMS", false, false),
      LAZY_CHUNK_LOADING("of.options.LAZY_CHUNK_LOADING", false, false),
      CUSTOM_SKY("of.options.CUSTOM_SKY", false, false),
      FAST_MATH("of.options.FAST_MATH", false, false),
      FAST_RENDER("of.options.FAST_RENDER", false, false),
      TRANSLUCENT_BLOCKS("of.options.TRANSLUCENT_BLOCKS", false, false),
      DYNAMIC_FOV("of.options.DYNAMIC_FOV", false, false),
      DYNAMIC_LIGHTS("of.options.DYNAMIC_LIGHTS", false, false),
      ALTERNATE_BLOCKS("of.options.ALTERNATE_BLOCKS", false, false),
      CUSTOM_ENTITY_MODELS("of.options.CUSTOM_ENTITY_MODELS", false, false),
      ADVANCED_TOOLTIPS("of.options.ADVANCED_TOOLTIPS", false, false),
      SCREENSHOT_SIZE("of.options.SCREENSHOT_SIZE", false, false),
      CUSTOM_GUIS("of.options.CUSTOM_GUIS", false, false),
      RENDER_REGIONS("of.options.RENDER_REGIONS", false, false),
      SHOW_GL_ERRORS("of.options.SHOW_GL_ERRORS", false, false),
      SMART_ANIMATIONS("of.options.SMART_ANIMATIONS", false, false);

   private final boolean enumFloat;
      private final boolean enumBoolean;
      private final String enumString;
      private final float valueStep;
      private final float valueMin;
      private float valueMax;
   public void setValueMax(float f) {
         this.valueMax = f;
      }

      public static Options getEnumOptions(int n) {
         for (Options options : values()) {
            if (options.returnEnumOrdinal() == n) {
               return options;
            }
         }

         return null;
      }

      public float normalizeValue(float f) {
         return MathHelper.clamp_float((this.snapToStepClamp(f) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
      }

      public float snapToStepClamp(float f) {
         f = this.snapToStep(f);
         return MathHelper.clamp_float(f, this.valueMin, this.valueMax);
      }

      public int returnEnumOrdinal() {
         return this.ordinal();
      }

      public boolean getEnumFloat() {
         return this.enumFloat;
      }

      public static float getOptionValueMax(Options options) {
         return options.valueMax;
      }

      private float snapToStep(float f) {
         if (this.valueStep > 0.0F) {
            f = this.valueStep * (float)Math.round(f / this.valueStep);
         }

         return f;
      }

      public float getValueMax() {
         return this.valueMax;
      }

      public float denormalizeValue(float f) {
         return this.snapToStepClamp(this.valueMin + (this.valueMax - this.valueMin) * MathHelper.clamp_float(f, 0.0F, 1.0F));
      }

      public static float getOptionValueMin(Options options) {
         return options.valueMin;
      }

      public boolean getEnumBoolean() {
         return this.enumBoolean;
      }

      private Options(String str, boolean flag, boolean flag2, float f, float f2, float f3) {
         this.enumString = str;
         this.enumFloat = flag;
         this.enumBoolean = flag2;
         this.valueMin = f;
         this.valueMax = f2;
         this.valueStep = f3;
      }

      public String getEnumString() {
         return this.enumString;
      }

      private Options(String str, boolean flag, boolean flag2) {
         this(str, flag, flag2, 0.0F, 1.0F, 0.0F);
      }
   }
}
