package net.minecraft.client;

import augustus.modules.world.NoPlaceDelay;
import cc.baritone.api.InternalItemEntry;
import cc.baritone.utils.AStarCalculator;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.HeuristicSelector;
import cc.baritone.utils.InternalOptionType;
import cc.baritone.utils.SecondaryCustom;
import cc.baritone.utils.SecondaryOptionType;
import cc.baritone.utils.SettingsTxtManager;
import cc.baritone.utils.SupplementalCustom;
import client.Client;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EnhancedListener;
import client.event.EventAutoSoupListener;
import client.event.EventClick;
import client.event.EventConnectionCallback;
import client.event.EventListener;
import client.event.EventMouseClick;
import client.event.EventSlowDown;
import client.event.MotionListener;
import client.event.MouseClickListener;
import client.event.PostMotionNotifier;
import client.event.SlowDownListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.font.FontJsonDeserializerExtensionAdapter;
import client.gui.AdvancedGuiScreenAdapter;
import client.gui.StandardGuiScreenAdapter;
import client.module.Module;
import client.network.LatencyPacket;
import client.render.RenderManager;
import client.render.StencilFramebuffer;
import client.render.TexturepackiconManager;
import client.render.TexturesColormapGrassPngManager;
import client.render.WorldRenderer;
import client.setting.BaseOptionType;
import client.setting.ClientSettingsChristmasType;
import client.setting.CombatKeybindProperty;
import client.setting.CombatThresholdProperty;
import client.setting.FactoryOptionType;
import client.setting.GameSettings;
import client.setting.GameSettingsFullType;
import client.setting.GameSettingsPeacefulType;
import client.setting.MovementIntervalProperty;
import client.setting.MovementSpeedProperty;
import client.setting.PhaseMode;
import client.setting.RenderProperty;
import client.setting.RenderSliderProperty;
import client.setting.SettingContainer;
import client.setting.StandardSettingArrayEntry;
import client.util.AchievementTheEnd2Manager;
import client.util.AdvancedCallableAdapter;
import client.util.AdvancedLongEntry;
import client.util.AltCallableAdapter;
import client.util.ArrayListData;
import client.util.AttackTargetHitCheck;
import client.util.AudioClipPlayer;
import client.util.AuxiliaryIChatComponentEntry;
import client.util.AuxiliaryListenerAdapter7;
import client.util.BaseCallableAdapter;
import client.util.BaseListEntry;
import client.util.BasePacketAdapter;
import client.util.BlurJsonDeserializerExtensionAdapter;
import client.util.CombatUtil;
import client.util.CommonCallableAdapter;
import client.util.ConfigManager;
import client.util.ConnectFailedManager;
import client.util.CustomCallableAdapter;
import client.util.DefaultFloatEntry;
import client.util.DefaultVertexFormats;
import client.util.DiscordRpcWorker;
import client.util.EndManager;
import client.util.EnhancedCallable;
import client.util.ErrorAdapter;
import client.util.ErrorManager;
import client.util.ExtendedCallableAdapter;
import client.util.ExtendedThreadAdapter;
import client.util.ExtraCallableAdapter;
import client.util.ExtraPacketHandler;
import client.util.FloatEntryAdapter;
import client.util.FoundMouseManager;
import client.util.GeometryScheduler;
import client.util.GeometryValidator;
import client.util.HandlerStringEntry;
import client.util.HolidayHelper;
import client.util.IntegratedServer;
import client.util.InternalCallableAdapter;
import client.util.InternalCustom;
import client.util.InvalidMetadataForManager;
import client.util.LanguageJsonDeserializerExtensionAdapter;
import client.util.LevelDatISaveFormatAdapter;
import client.util.Listener69Extension;
import client.util.ListenerAdapter11;
import client.util.ListenerAdapter24;
import client.util.ListenerAdapter27;
import client.util.ManagedStringEntryAdapter;
import client.util.MetadataSectionJsonDeserializerExtensionAdapter;
import client.util.MinecraftTimer;
import client.util.MouseHelper;
import client.util.MovingObjectType;
import client.util.NetherFloatArrayEntry;
import client.util.ObjectsManager;
import client.util.OnlineConfigsManager;
import client.util.OsNameManager;
import client.util.PackFormatJsonDeserializerExtensionAdapter;
import client.util.ProfileConfigManager;
import client.util.QueueEntry;
import client.util.RandomSeedManager;
import client.util.RayTraceUtil;
import client.util.ReloadingResourceManagerHolder;
import client.util.STDERRManager;
import client.util.SecondaryComparableAdapter;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.SkinsManager;
import client.util.SkippingEntityWithIdManager;
import client.util.SlotManager;
import client.util.SmoothChecker;
import client.util.StandardCallableAdapter;
import client.util.SupplementalCallable;
import client.util.SupplementalListEntry;
import client.util.SupplementalLoggerEntry;
import client.util.SupplementalLongEntry;
import client.util.UNKNOWNManager;
import client.util.VanillaManager;
import client.util.VanillaRunnable;
import client.via.ViaLoadingBase;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import de.florianmichael.dietrichevents2.StateTypes;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.function.BiFunction;
import javax.imageio.ImageIO;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiAchievement;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiConnecting;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EffectRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.resources.ResourceLanguageManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArmorstand;
import net.minecraft.entity.EntityBoat;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.entity.EntityLeashknot;
import net.minecraft.entity.EntityPainting;
import net.minecraft.entity.EntityRenderer;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.item.DiamondBoots;
import net.minecraft.item.GoldenCrystal;
import net.minecraft.item.IronDust;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.TertiaryItemCallback;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.profiler.Profiler;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.OpenGLException;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.glu.GLU;

public class Minecraft implements PostMotionNotifier, EnhancedListener {
   public final AudioClipPlayer audioClipPlayer = new AudioClipPlayer();
   public long epochMillis = 0L;
   public long epochMillis2 = 0L;
   public long timestamp = 0L;
   public long timestamp2 = 0L;
   public float factor = 0.0F;
   public float factor2 = 0.0F;
   public static final Logger logger = LogManager.getLogger();
   public static final boolean state3 = OsNameManager.getMovementIntervalProperty() == MovementIntervalProperty.OSX;
   public static byte[] byteFlagArray = new byte[10485760];
   public static final List list = Lists.newArrayList(new DisplayMode(2560, 1600), new DisplayMode(2880, 1800));
   public final File file2;
   public final PropertyMap propertyMap;
   public ServerData serverData;
   public TextureManager textureManager;
   public static Minecraft mcClient;
   public PlayerControllerMP playerController;
   public boolean state5;
   public boolean enabled6 = true;
   public boolean enabled8;
   public CrashReport crashReport;
   public int displayWidth;
   public int displayHeight;
   public boolean enabled5 = false;
   public MinecraftTimer timer = new MinecraftTimer(20.0F);
   public MinecraftTimer minecraftTimer;
   public PlayerUsageSnooper playerUsageSnooper;
   public WorldClient theWorld;
   public RenderGlobal renderGlobal;
   public RenderManager renderManager;
   public RenderItem renderItem;
   public ItemRenderer itemRenderer;
   public EntityPlayerSP thePlayer;
   public Entity entity;
   public Entity pointedEntity;
   public EffectRenderer effectRenderer;
   public Session session;
   public boolean state;
   public FontRenderer fontRendererObj;
   public FontRenderer fontRenderer;
   public GuiScreen currentScreen;
   public GuiScreen guiScreen;
   public GuiScreen guiScreen2;
   public LoadingScreenRenderer loadingScreenRenderer;
   public EntityRenderer entityRenderer;
   public int leftClickCounter;
   public int count7;
   public int count3;
   public IntegratedServer integratedServer;
   public GuiAchievement guiAchievement;
   public GuiIngame ingameGUI;
   public boolean enabled7;
   public MovingObjectPosition objectMouseOver;
   public MovingObjectPosition movingObjectPosition;
   public MovingObjectPosition movingObjectPosition2;
   public int indexOffset;
   public int rightClickDelayTimer;
   public GameSettings gameSettings;
   public MouseHelper mouseHelper;
   public final File mcDataDir;
   public final File file;
   public final String labelText3;
   public final Proxy proxy;
   public ISaveFormat iSaveFormat;
   public static int count8;
   public int count6;
   public String labelText;
   public int count;
   public boolean inGameHasFocus;
   public long timestamp7;
   public int count2;
   public final Profiler profiler;
   public long timestamp5;
   public final boolean enabled2;
   public final boolean enabled14;
   public NetworkManager networkManager;
   public boolean enabled10;
   public final UNKNOWNManager uNKNOWNManager;
   public long timestamp3;
   public Listener69Extension listener69Extension;
   public final InvalidMetadataForManager invalidMetadataForManager;
   public final List list2;
   public final IReloadableResourceManager iReloadableResourceManager;
   public ResourceLanguageManager resourceLanguageManager;
   public LanguageManager languageManager;
   public Framebuffer framebuffer;
   public ListenerAdapter27 listenerAdapter27;
   public SoundHandler mcSoundHandler;
   public MusicTicker musicTicker;
   public ResourceLocation resourceLocation;
   public MinecraftSessionService minecraftSessionService;
   public SkinsManager skinsManager;
   public final Queue queue2;
   public final Queue queue;
   public long timestamp9;
   public final Thread thread;
   public IronDust ironDust;
   public GoldenCrystal goldenCrystal;
   public volatile boolean enabled17;
   public String labelText4;
   public boolean enabled3;
   public boolean enabled15;
   public boolean enabled4;
   public boolean enabled;
   public boolean enabled16;
   public long timestamp6;
   public int count4;
   public long timestamp4;
   public String labelText2;
   public int count5;
   public boolean enabled11;
   public long timestamp8;
   public ArrayListData arrayListData;
   public ArrayListData arrayListData2;
   public boolean enabled9;
   public boolean enabled12;
   public boolean enabled13;
   public int indexOffset1;
   public final ArrayList arrayList;

   public static Minecraft getMinecraft() {
      return mcClient;
   }

   public void applyIndex(int count9, int count10, int count11, int count12, int count13, int count14, int count15, int count16, int count17, int count18) {
      float factor3 = 0.00390625F;
      float factor4 = 0.00390625F;
      WorldRenderer worldRenderer = Tessellator.getTessellator().getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      worldRenderer.getWorldRenderer((double)count9, (double)(count10 + count14), 0.0)
         .getWorldRenderer6((double)((float)count11 * factor3), (double)((float)(count12 + count14) * factor4))
         .resolveWorldRenderer(count15, count16, count17, count18)
         .updateState();
      worldRenderer.getWorldRenderer((double)(count9 + count13), (double)(count10 + count14), 0.0)
         .getWorldRenderer6((double)((float)(count11 + count13) * factor3), (double)((float)(count12 + count14) * factor4))
         .resolveWorldRenderer(count15, count16, count17, count18)
         .updateState();
      worldRenderer.getWorldRenderer((double)(count9 + count13), (double)count10, 0.0)
         .getWorldRenderer6((double)((float)(count11 + count13) * factor3), (double)((float)count12 * factor4))
         .resolveWorldRenderer(count15, count16, count17, count18)
         .updateState();
      worldRenderer.getWorldRenderer((double)count9, (double)count10, 0.0)
         .getWorldRenderer6((double)((float)count11 * factor3), (double)((float)count12 * factor4))
         .resolveWorldRenderer(count15, count16, count17, count18)
         .updateState();
      Tessellator.getTessellator().updateState();
   }

   public boolean isFullScreen() {
      return this.enabled10 && this.integratedServer != null;
   }

   public void applyIndexByIndex(int count9, int count10) throws IOException {
      long keySalt = 0x35b524bb2c31L ^ 0x32032429441dL;
      long keySalt2 = 0x35b524bb2c31L ^ 0x246fb0285ebaL;
      int count11 = 24997;
      int count13 = 6105;
      int count14 = 60385;
      if (ClientGlobals.moduleManager.packetHandler.settingEventToHandlePacket.getSelected().equals("Tick")) {
         this.updateState12();
      }

      this.indexOffset1 = count9;
      if (this.count6 > 0) {
         int count16 = this.count6 - 1;
         this.count6 = count16;
      }

      this.uNKNOWNManager.applyTextByText("gui");
      if (!this.state) {
         this.ingameGUI.renderElement();
      }

      this.uNKNOWNManager.updateState();
      MovingObjectPosition hitResult = new MovingObjectPosition(this.movingObjectPosition);
      this.movingObjectPosition2 = hitResult;
      SupplementalLongEntry.applyIndex(count9, count10);
      if (this.currentScreen == null && this.thePlayer != null) {
         if (this.thePlayer.getFloat2() <= 0.0F) {
            GuiScreen screen = null;
            this.displayGuiScreen(screen);
         } else if (this.thePlayer.isState5() && this.theWorld != null) {
            GeometryScheduler geometryScheduler = new GeometryScheduler();
            this.displayGuiScreen(geometryScheduler);
         }
      } else if (this.currentScreen != null && this.currentScreen instanceof GeometryScheduler && !this.thePlayer.isState5()) {
         GuiScreen screen2 = null;
         this.displayGuiScreen(screen2);
      }

      if (this.currentScreen != null
         && (!((Module)ClientGlobals.moduleManager.killAura).isToggled() || !ClientGlobals.moduleManager.killAura.settingInInv.isEnabled())) {
         this.leftClickCounter = 10000;
      }

      BiFunction biFunction = InternalItemEntry.getBiFunction();
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
         for (ActiveListener activeListener : (java.lang.Iterable<ActiveListener>) (SettingsTxtManager.getExtraListener().getList())) {
            InternalOptionType internalOptionType = activeListener.getStaticListener().getEntityPlayerSP() != null && activeListener.getStaticListener().getWorld() != null
               ? InternalOptionType.IN
               : InternalOptionType.OUT;
            activeListener.getCompositeListener().applyInternalItemEntry((InternalItemEntry)biFunction.apply(SecondaryOptionType.PRE, internalOptionType));
         }
      }

      if (this.currentScreen != null) {
         try {
            this.currentScreen.renderElement4();
         } catch (Throwable throwable) {
            CrashReport crashReport2 = CrashReport.resolveCrashReport(throwable, "Updating screen events");
            CrashReportCategory crashReportCategory = crashReport2.resolveCrashReportCategory("Affected screen");
            crashReportCategory.applyText("Screen name", new InternalCustom(this));
            throw new SecondaryRuntimeExceptionAdapter(crashReport2);
         }

         if (this.currentScreen != null) {
            try {
               this.currentScreen.updateState4();
            } catch (Throwable throwable2) {
               CrashReport crashReport3 = CrashReport.resolveCrashReport(throwable2, "Ticking screen");
               CrashReportCategory crashReportCategory2 = crashReport3.resolveCrashReportCategory("Affected screen");
               crashReportCategory2.applyText("Screen name", new GeometryValidator(this));
               throw new SecondaryRuntimeExceptionAdapter(crashReport3);
            }
         }
      }

      searchBlock: {
         if (this.currentScreen != null) {
            GuiScreen currentScreen2 = this.currentScreen;
            if (!this.matchesGuiScreenByGuiScreen(currentScreen2)) {
               break searchBlock;
            }
         }

         this.uNKNOWNManager.applyText("mouse");
         Client.getClient().getModuleManager().saveMoveKeys.updateState2();
         if (!ManagedStringEntryAdapter.clickPatternManager.isEnabled4()) {
            while (Mouse.next()) {
               int eventButton = Mouse.getEventButton();
               QueueEntry.updateState();
               KeyBinding.setKeyBindState(eventButton - 100, Mouse.getEventButtonState());
               if (Mouse.getEventButtonState()) {
                  if (this.thePlayer.isActive23() && eventButton == 2) {
                     this.ingameGUI.getExtendedTexturesGuiIconsPngManagerAdapter().renderElement();
                  } else {
                     KeyBinding.onTick(eventButton - 100);
                  }
               }

               if (this.gameSettings.keyBindTogglePerspective.isPressed()) {
                  this.gameSettings.indexOffset64++;
                  if (this.gameSettings.indexOffset64 > 2) {
                     this.gameSettings.indexOffset64 = 0;
                  }

                  if (this.gameSettings.indexOffset64 == 0) {
                     this.entityRenderer.applyEntityByEntity(this.getRenderViewEntity());
                  } else if (this.gameSettings.indexOffset64 == 1) {
                     this.entityRenderer.applyEntityByEntity((Entity)null);
                  }

                  this.renderGlobal.updateState14();
               }

               if (getTimestamp() - this.timestamp7 <= 200L) {
                  eventButton = Mouse.getEventDWheel();
                  if (eventButton != 0) {
                     if (this.thePlayer.isActive23()) {
                        eventButton = eventButton < 0 ? -1 : 1;
                        if (this.ingameGUI.getExtendedTexturesGuiIconsPngManagerAdapter().isActive()) {
                           this.ingameGUI.getExtendedTexturesGuiIconsPngManagerAdapter().applyIndexByIndex(-eventButton);
                        } else {
                           float factor3 = MathHelper.clamp_float(this.thePlayer.invulnerableManager.getFactor() + (float)eventButton * 0.005F, 0.0F, 0.2F);
                           this.thePlayer.invulnerableManager.setFactor(factor3);
                        }
                     } else {
                        this.thePlayer.inventoryPlayer.updateInt(eventButton);
                     }
                  }

                  if (this.currentScreen == null) {
                     if (!this.inGameHasFocus && Mouse.getEventButtonState()) {
                        this.updateState21();
                     }
                  } else {
                     this.currentScreen.updateState3();
                  }
               }
            }
         }

         if (this.leftClickCounter > 0) {
            int count17 = this.leftClickCounter - 1;
            this.leftClickCounter = count17;
         }

         this.uNKNOWNManager.applyText("keyboard");

         while (Keyboard.next()) {
            int count18 = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
            KeyBinding.setKeyBindState(count18, Keyboard.getEventKeyState());
            if (Keyboard.getEventKeyState()) {
               KeyBinding.onTick(count18);
            }

            if (this.timestamp3 > 0L) {
               if (getTimestamp() - this.timestamp3 >= 6000L) {
                  throw new SecondaryRuntimeExceptionAdapter(new CrashReport("Manually triggered debug crash", new Throwable()));
               }

               if (!Keyboard.isKeyDown(46) || !Keyboard.isKeyDown(61)) {
                  this.timestamp3 = -1L;
               }
            } else if (Keyboard.isKeyDown(46) && Keyboard.isKeyDown(61)) {
               keySalt2 = getTimestamp();
               this.timestamp3 = keySalt2;
            }

            this.updateState18();
            if (this.currentScreen == null) {
               QueueEntry.updateState3();
            }

            if (Keyboard.getEventKeyState()) {
               if (count18 == 62 && this.entityRenderer != null) {
                  this.entityRenderer.updateState11();
               }

               if (this.currentScreen != null) {
                  this.currentScreen.renderElement2();
               } else {
                  if (count18 == 1) {
                     this.updateState2();
                  }

                  if (count18 == 32 && Keyboard.isKeyDown(61) && this.ingameGUI != null) {
                     this.ingameGUI.getInternalLoggerEntry().renderElement();
                  }

                  if (count18 == 31 && Keyboard.isKeyDown(61)) {
                     this.updateState11();
                  }

                  if (count18 == 17) {
                     Keyboard.isKeyDown(61);
                  }

                  if (count18 == 18) {
                     Keyboard.isKeyDown(61);
                  }

                  if (count18 == 47) {
                     Keyboard.isKeyDown(61);
                  }

                  if (count18 == 38) {
                     Keyboard.isKeyDown(61);
                  }

                  if (count18 == 22) {
                     Keyboard.isKeyDown(61);
                  }

                  if (count18 == 20 && Keyboard.isKeyDown(61)) {
                     this.updateState11();
                  }

                  if (count18 == 33 && Keyboard.isKeyDown(61)) {
                     this.gameSettings.applyFactoryOptionTypeByFactoryOptionTyp(FactoryOptionType.RENDER_DISTANCE, GuiScreen.isActiveAsFlag() ? -1 : 1);
                  }

                  if (count18 == 30 && Keyboard.isKeyDown(61)) {
                     this.renderGlobal.updateState();
                  }

                  if (count18 == 35 && Keyboard.isKeyDown(61)) {
                     this.gameSettings.state136 = !this.gameSettings.state136;
                     this.gameSettings.updateState();
                  }

                  if (count18 == 48 && Keyboard.isKeyDown(61)) {
                     this.renderManager.setState5(!this.renderManager.isState5());
                  }

                  if (count18 == 25 && Keyboard.isKeyDown(61)) {
                     this.gameSettings.state72 = !this.gameSettings.state72;
                     this.gameSettings.updateState();
                  }

                  if (count18 == 59) {
                     this.gameSettings.state11 = !this.gameSettings.state11;
                  }

                  if (count18 == 61) {
                     this.gameSettings.state14 = !this.gameSettings.state14;
                     this.gameSettings.state106 = GuiScreen.isActiveAsFlag();
                     this.gameSettings.state47 = GuiScreen.isActive2();
                  }

                  if (this.gameSettings.keyBindTogglePerspective.isPressed()) {
                     this.gameSettings.indexOffset64++;
                     if (this.gameSettings.indexOffset64 > 2) {
                        this.gameSettings.indexOffset64 = 0;
                     }

                     if (this.gameSettings.indexOffset64 == 0) {
                        this.entityRenderer.applyEntityByEntity(this.getRenderViewEntity());
                     } else if (this.gameSettings.indexOffset64 == 1) {
                        this.entityRenderer.applyEntityByEntity((Entity)null);
                     }

                     this.renderGlobal.updateState14();
                  }

                  if (this.gameSettings.keyBindSmoothCamera.isPressed()) {
                     this.gameSettings.state32 = !this.gameSettings.state32;
                  }
               }

               if (this.gameSettings.state14 && this.gameSettings.state106) {
                  if (count18 == 11) {
                     this.applyIndexByIndex(0);
                  }

                  for (int index = 0; index < 9; index++) {
                     if (count18 == 2 + index) {
                        int count19 = index + 1;
                        this.applyIndexByIndex(count19);
                     }
                  }
               }
            }
         }

         if (this.thePlayer != null) {
            Client.getClient().getModuleManager().saveMoveKeys.updateState3();

            for (int index2 = 0; index2 < 9; index2++) {
               if (this.gameSettings.scorePlayerTeamArray[index2].isPressed()) {
                  if (this.thePlayer.isActive23()) {
                     this.ingameGUI.getExtendedTexturesGuiIconsPngManagerAdapter().applyIndex(index2);
                  } else {
                     SlotManager.indexOffset = index2;
                     this.thePlayer.inventoryPlayer.currentItem = index2;
                  }
               }
            }

            MotionListener.applyStateTypesByStateTypes(StateTypes.PRE);
            SlotManager.setCurrentSlot(this.thePlayer.inventoryPlayer.currentItem);
            MotionListener.applyStateTypesByStateTypes(StateTypes.POST);
            if (this.isIntegratedServerRunning()) {
               this.thePlayer.inventoryPlayer.currentItem = SlotManager.getCount();
            }

            int count20 = ((this.gameSettings.gameSettingsFullType != GameSettingsFullType.HIDDEN) ? 1 : 0);

            while (this.gameSettings.keyBindInventory.isPressed()) {
               if (this.playerController.isActiveAsFlag()) {
                  this.thePlayer.updateState46();
               } else {
                  ViaLoadingBase.processAction(count13, count14, (char)21074);
                  GuiInventory guiInventory = new GuiInventory(this.thePlayer);
                  this.displayGuiScreen(guiInventory);
               }
            }

            if (!this.thePlayer.isActive23()) {
               EventAutoSoupListener.updateState10();
            }

            while (this.gameSettings.keyBindDrop.isPressed()) {
               if (!this.thePlayer.isActive23()) {
                  this.thePlayer.getEntityItem(GuiScreen.isActive3());
               }
            }

            while (this.gameSettings.keyBindChat.isPressed() && count20 != 0) {
               GuiChat guiChat = new GuiChat();
               this.displayGuiScreen(guiChat);
            }

            if (this.currentScreen == null && this.gameSettings.keyBindCommand.isPressed() && count20 != 0) {
               GuiChat guiChat2 = new GuiChat("/");
               this.displayGuiScreen(guiChat2);
            }

            EventClick.animationTimer.processAction();
            EventClick.indexOffset10 = 0;
            MovementSpeedProperty movementSpeedProperty = MovementSpeedProperty.LEFT;
            boolean keyDown = this.gameSettings.keyBindUseItem.isKeyDown();
            boolean keyDown2 = this.gameSettings.keyBindAttack.isKeyDown();
            Runnable task = this::rightClickMouse;
            Runnable task2 = this::clickMouse;
            boolean flag = keyDown2;
            boolean flag2 = keyDown;
            EventClick event = ClickListener.resolveEventClick(movementSpeedProperty, flag2, flag, task2, task);
            count13 = event.getIndexOffset() + (event.isState3() ? 0 : this.gameSettings.keyBindAttack.getPressTime());
            count20 = event.getIndexOffset12() + (event.isState3() ? 0 : this.gameSettings.keyBindUseItem.getPressTime());
            count14 = event.isState3() ? 0 : this.gameSettings.keyBindPickBlock.getPressTime();
            flag = event.isState11();
            flag2 = event.isState1();
            if (event.isState2()) {
               count13 = 0;
               count20 = 0;
               count14 = 0;
               flag = false;
               flag2 = false;
            }

            this.gameSettings.keyBindAttack.setPressTime(0);
            this.gameSettings.keyBindUseItem.setPressTime(0);
            this.gameSettings.keyBindPickBlock.setPressTime(0);
            boolean flag3 = false;
            boolean flag4 = false;
            if (event.isState6()) {
               flag3 = true;
               flag4 = true;
            }

            if (!event.isCancelled() && this.thePlayer != null) {
               if (this.thePlayer.isUsingItem()) {
                  if (!flag || ClientGlobals.moduleManager.fixes.canExecute8()) {
                     this.playerController.processAction2(this.thePlayer);
                  }
               } else {
                  boolean flag5 = false;

                  for (int index3 = 0;
                     index3 < count13
                        && (
                           event.isState4()
                              || event.getIndexOffset() <= 0
                              || this.objectMouseOver == null
                              || this.objectMouseOver.typeOfHit != MovingObjectType.BLOCK
                              || !this.enabled16
                              || SettingContainer.settingProvider.getInt2(0, 100) < 20
                        );
                     index3++
                  ) {
                     event.getRunnable3().run();
                     EventClick.enabled = true;
                     EventClick.indexOffset10++;
                     flag5 = true;
                  }

                  if (event.isState5()) {
                     flag3 = this.leftClickCounter <= 100 && event.isState4() && this.currentScreen == null;
                  } else if (event.isState8()) {
                     flag3 = this.leftClickCounter <= 100 && EventClick.checkFlag(event.isState8());
                  } else {
                     flag3 = this.leftClickCounter <= 100 && this.currentScreen == null && flag2 && this.inGameHasFocus;
                  }

                  flag4 = true;
                  if (flag3 && !flag5 && !EventClick.enabled2 && this.objectMouseOver != null) {
                     event.getRunnable3().run();
                     EventClick.enabled = true;
                     EventClick.indexOffset10++;
                  }

                  short shortValue = 0;

                  for (int index4 = 0;
                     index4 < count20 && event.isState9() && !ClientGlobals.moduleManager.fixes.canExecute8() || shortValue != 0 && event.isState7() && index4 < 10;
                     index4++
                  ) {
                     if (((Module)ClientGlobals.moduleManager.noMiningTrace).isToggled()
                        && ClientGlobals.moduleManager.noMiningTrace.settingAlwaysAtRightClicking.isEnabled()) {
                        this.entityRenderer.processFloat5(1.0F, true);
                     }

                     event.getRunnable2().run();
                     if (((Module)ClientGlobals.moduleManager.noPlaceDelay).isToggled()
                        && this.objectMouseOver != null
                        && this.objectMouseOver.typeOfHit == MovingObjectType.BLOCK
                        && this.objectMouseOver.getBlockPos() != null) {
                        this.entityRenderer.processFloat5(1.0F, false);
                        if (((Module)Client.getClient().getModuleManager().smartRaytrace).isToggled()) {
                           shortValue = (short)-13970;
                           Client.getClient().getModuleManager().smartRaytrace.processAction();
                        }

                        ItemStack stack = this.thePlayer.getHeldItem();
                        BlockPos blockPos = this.objectMouseOver.getBlockPos();
                        Vec3 hitVec = this.objectMouseOver.hitVec;
                        EnumFacing sideHit = this.objectMouseOver.sideHit;
                        BlockPos blockPos2 = blockPos;
                        shortValue = ((short)((CombatUtil.checkItemStack(stack, blockPos2, sideHit, hitVec) && NoPlaceDelay.checkEnumFacing(this.objectMouseOver.sideHit)) ? 1 : 0));
                     } else {
                        shortValue = 0;
                     }
                  }

                  for (int index5 = 0; index5 < count14; index5++) {
                     this.updateState8();
                  }
               }

               if (flag && this.count6 == 0 && !this.thePlayer.isUsingItem() && event.isState9() && !ClientGlobals.moduleManager.fixes.canExecute8()) {
                  event.getRunnable2().run();
               }
            }

            double indexOffset2 = (double)EventClick.indexOffset10;
            EventClick.clickHelper.processDouble(indexOffset2);
            if (!flag4) {
               if (event.isState5()) {
                  flag3 = this.leftClickCounter <= 100 && event.isState4() && this.currentScreen == null;
               } else if (event.isState8()) {
                  flag3 = this.leftClickCounter <= 100 && EventClick.checkFlag(event.isState8());
               } else {
                  flag3 = this.leftClickCounter <= 100 && this.currentScreen == null && flag2 && this.inGameHasFocus;
               }
            }

            if (event.getRunnable() == null) {
               this.setEnabled(flag3);
               EventClick.enabled2 = flag3;
            } else {
               event.getRunnable().run();
            }

            this.enabled16 = flag3;
         }
      }

      if (this.isIntegratedServerRunning()) {
         count11 = this.count5 + 1;
         this.count5 = count11;
      } else {
         this.count5 = 0;
      }

      if (this.theWorld != null) {
         if (this.thePlayer != null) {
            TickListener.applyRenderSliderProperty(RenderSliderProperty.TICK);
            count11 = this.count2 + 1;
            this.count2 = count11;
            if (this.count2 == 30) {
               this.count2 = 0;
               this.theWorld.applyEntity9(this.thePlayer);
            }
         }

         this.uNKNOWNManager.applyText("gameRenderer");
         if (!this.state) {
            this.entityRenderer.updateState();
         }

         this.uNKNOWNManager.applyText("levelRenderer");
         if (!this.state) {
            this.renderGlobal.updateState10();
         }

         this.uNKNOWNManager.applyText("level");
         if (!this.state) {
            if (this.theWorld.getIndexOffset2() > 0) {
               this.theWorld.setIndexOffset2(this.theWorld.getIndexOffset2() - 1);
            }

            this.theWorld.updateState4();
            if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
               ActiveListener activeListener2 = SettingsTxtManager.getExtraListener().resolveActiveListenerByEntityPlayerSP(this.thePlayer);
               if (activeListener2 != null) {
                  activeListener2.getCompositeListener().forEach(new AStarCalculator(SecondaryOptionType.POST));
               }
            }
         }
      } else if (this.entityRenderer.isActive()) {
         this.entityRenderer.updateState8();
      }

      if (!this.state) {
         this.musicTicker.updateState();
         this.mcSoundHandler.updateState();
      }

      if (this.theWorld != null) {
         if (!this.state) {
            this.theWorld.applyFlag(this.theWorld.getGameSettingsPeacefulType() != GameSettingsPeacefulType.PEACEFUL, true);

            try {
               this.theWorld.updateState2();
            } catch (Throwable throwable3) {
               CrashReport crashReport4 = CrashReport.resolveCrashReport(throwable3, "Exception in world tick");
               if (this.theWorld == null) {
                  CrashReportCategory crashReportCategory3 = crashReport4.resolveCrashReportCategory("Affected level");
                  crashReportCategory3.add("Problem", "Level is null!");
               } else {
                  this.theWorld.resolveCrashReportCategory(crashReport4);
               }

               throw new SecondaryRuntimeExceptionAdapter(crashReport4);
            }
         }

         this.uNKNOWNManager.applyText("animateTick");
         if (!this.state && this.theWorld != null) {
            this.theWorld
               .applyIndexByIndex(
                  MathHelper.ceiling_double_int(this.thePlayer.posX),
                  MathHelper.ceiling_double_int(this.thePlayer.posY),
                  MathHelper.ceiling_double_int(this.thePlayer.posZ)
               );
         }

         this.uNKNOWNManager.applyText("particles");
         if (!this.state) {
            this.effectRenderer.updateState();
         }
      } else if (this.networkManager != null) {
         this.uNKNOWNManager.applyText("pendingConnection");
         this.networkManager.updateState2();
      }

      if (this.theWorld != null && this.thePlayer != null) {
         TickListener.applyRenderSliderProperty(RenderSliderProperty.LATE);
      }

      this.uNKNOWNManager.updateState();
      keySalt = getTimestamp();
      this.timestamp7 = keySalt;
      QueueEntry.updateState2();
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
         for (ActiveListener activeListener3 : (java.lang.Iterable<ActiveListener>) (SettingsTxtManager.getExtraListener().getList())) {
            InternalOptionType internalOptionType2 = activeListener3.getStaticListener().getEntityPlayerSP() != null && activeListener3.getStaticListener().getWorld() != null
               ? InternalOptionType.IN
               : InternalOptionType.OUT;
            activeListener3.getCompositeListener().forEach((InternalItemEntry)biFunction.apply(SecondaryOptionType.POST, internalOptionType2));
         }
      }
   }

   public void setEnabled5(boolean enabled18) {
      this.enabled5 = enabled18;
   }

   public void applyWorldClientByWorldClient(WorldClient world, String text) {
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled() && (this.theWorld != null || world != null)) {
         SettingsTxtManager.getExtraListener().get().getCompositeListener().applySupplementalCustom(new SupplementalCustom(world, SecondaryOptionType.PRE));
      }

      if (world != this.theWorld) {
         this.entityRenderer.getTexturesMapIconsManager().renderElement();
      }

      WorldLoadListener.applyWorldByWorld(world);
      if (world == null) {
         NetHandlerPlayClient netHandlerPlayClient = this.getNetHandlerPlayClient();
         if (netHandlerPlayClient != null) {
            netHandlerPlayClient.updateState();
         }

         if (this.integratedServer != null && this.integratedServer.onUpdate()) {
            this.integratedServer.updateState();
            this.integratedServer.updateState13();
         }

         this.integratedServer = null;
         this.guiAchievement.renderElement3();
         this.entityRenderer.getTexturesMapIconsManager().renderElement();
      }

      this.entity = null;
      this.networkManager = null;
      if (this.loadingScreenRenderer != null) {
         this.loadingScreenRenderer.applyTextByText(text);
         this.loadingScreenRenderer.applyText("");
      }

      if (world == null && this.theWorld != null) {
         this.resourceLanguageManager.updateState2();
         this.ingameGUI.renderElement2();
         ServerData serverData2 = null;
         this.setServerData(serverData2);
         this.enabled10 = false;
      }

      this.mcSoundHandler.updateState4();
      this.theWorld = world;
      if (world != null) {
         if (this.renderGlobal != null) {
            this.renderGlobal.applyWorldClientByWorldClient(world);
         }

         if (this.effectRenderer != null) {
            this.effectRenderer.applyWorldByWorld(world);
         }

         if (this.thePlayer == null) {
            EntityPlayerSP player = this.playerController.processAction(world, new StatFileWriter());
            this.thePlayer = player;
            this.playerController.processAction(this.thePlayer);
         }

         this.thePlayer.updateState22();
         world.matchesEntity(this.thePlayer);
         this.thePlayer.secondaryFloatEntry = new FloatEntryAdapter(this.gameSettings);
         this.playerController.processAction3(this.thePlayer);
         EntityPlayerSP player2 = this.thePlayer;
         this.entity = player2;

         try {
            StandardSettingArrayEntry.updateState();
         } catch (Throwable throwable) {
            throwable.printStackTrace();
         }
      } else {
         this.iSaveFormat.updateState();
         this.thePlayer = null;
      }

      this.timestamp7 = 0L;
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
         SettingsTxtManager.getExtraListener().get().getCompositeListener().applySupplementalCustom(new SupplementalCustom(world, SecondaryOptionType.POST));
      }
   }

   public void applyText(String text, String text2, AdvancedLongEntry advancedLongEntry) {
      WorldClient world = null;
      this.loadWorld(world);
      System.gc();
      ISaveHandler iSaveHandler = this.iSaveFormat.resolveISaveHandler(text, false);
      RandomSeedManager randomSeedManager = iSaveHandler.getRandomSeedManager();
      if (randomSeedManager == null && advancedLongEntry != null) {
         randomSeedManager = new RandomSeedManager(advancedLongEntry, text);
         iSaveHandler.applyRandomSeedManager(randomSeedManager);
      }

      if (advancedLongEntry == null) {
         advancedLongEntry = new AdvancedLongEntry(randomSeedManager);
      }

      try {
         IntegratedServer integratedServer2 = new IntegratedServer(this, text, text2, advancedLongEntry);
         this.integratedServer = integratedServer2;
         this.integratedServer.start();
         this.enabled10 = true;
      } catch (Throwable throwable) {
         CrashReport crashReport2 = CrashReport.resolveCrashReport(throwable, "Starting integrated server");
         CrashReportCategory crashReportCategory = crashReport2.resolveCrashReportCategory("Starting integrated server");
         crashReportCategory.add("Level ID", text);
         crashReportCategory.add("Level Name", text2);
         throw new SecondaryRuntimeExceptionAdapter(crashReport2);
      }

      this.loadingScreenRenderer.applyText2(StatCollector.getText("menu.loadingLevel", new Object[0]));

      while (!this.integratedServer.isEnabled6()) {
         String labelText5 = this.integratedServer.getLabelText8();
         if (labelText5 != null) {
            this.loadingScreenRenderer.applyText(StatCollector.getText(labelText5, new Object[0]));
         } else {
            this.loadingScreenRenderer.applyText("");
         }

         try {
            Thread.sleep(200L);
         } catch (InterruptedException interruptedException) {
         }
      }

      GuiScreen screen = null;
      this.displayGuiScreen(screen);
      SocketAddress socketAddress = this.integratedServer.getCustomLoggerEntry().getSocketAddress();
      NetworkManager networkManager2 = NetworkManager.resolveNetworkManager(socketAddress);
      networkManager2.applyINetHandler(new ConnectFailedManager(networkManager2, this, (GuiScreen)null));
      networkManager2.handlePacket6(new BasePacketAdapter(47, socketAddress.toString(), 0, CombatKeybindProperty.LOGIN));
      networkManager2.handlePacket6(new ExtraPacketHandler(this.getSession().getGameProfile()));
      this.networkManager = networkManager2;
      DiscordRpcWorker threadEntry = Client.getClient().getThreadEntry();
      String text3 = "Ingame";
      String text4 = "Playing lonely in singleplayer";
      threadEntry.applyString(text4, text3);
   }

   public static String resolveTextByMinecraft(Minecraft mc) {
      return mc.labelText3;
   }

   public void updateState6() {
      this.uNKNOWNManager.applyTextByText("display_update");
      Display.update();
      this.uNKNOWNManager.updateState();
      this.updateState5();
   }

   public MinecraftTimer getTimer() {
      return this.timer;
   }

   public int getCountAsIndex() {
      if (Client.getClient().getModuleManager().clientSettings.settingRemoveFramerateLimitInScreens.isEnabled() && Display.isActive()) {
         return this.gameSettings.indexOffset35;
      } else {
         return this.theWorld == null && this.currentScreen != null ? 30 : this.gameSettings.indexOffset35;
      }
   }

   public void applyIndex(int count9) {
      this.theWorld.updateState10();
      this.theWorld.updateState12();
      int count10 = 0;
      String text = null;
      if (this.thePlayer != null) {
         count10 = this.thePlayer.getEntityId();
         this.theWorld.spawnEntityInWorld(this.thePlayer);
         text = this.thePlayer.getText4();
      }

      this.entity = null;
      EntityPlayerSP player = this.thePlayer;
      EntityPlayerSP player2 = this.playerController
         .processAction(this.theWorld, this.thePlayer == null ? new StatFileWriter() : this.thePlayer.getStatFileWriter());
      this.thePlayer = player2;
      this.thePlayer.getDataWatcher().applyList(player.getDataWatcher().getList());
      this.thePlayer.count4 = count9;
      player2 = this.thePlayer;
      this.entity = player2;
      this.thePlayer.updateState22();
      this.thePlayer.setText2(text);
      this.theWorld.matchesEntity(this.thePlayer);
      this.playerController.processAction(this.thePlayer);
      this.thePlayer.secondaryFloatEntry = new FloatEntryAdapter(this.gameSettings);
      this.thePlayer.setEntityId(count10);
      this.playerController.processAction3(this.thePlayer);
      this.thePlayer.setInWater2(player.onDisable());
      if (this.currentScreen instanceof GuiGameOver) {
         GuiScreen screen = null;
         this.displayGuiScreen(screen);
      }
   }

   public MusicTicker getMusicTicker() {
      return this.musicTicker;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public Profiler getProfiler() {
      return this.profiler;
   }

   @Override
   public void applyPlayerUsageSnooperByPlayerUsageSnoop(PlayerUsageSnooper playerUsageSnooper2) {
      playerUsageSnooper2.applyText("opengl_version", GL11.glGetString(7938));
      playerUsageSnooper2.applyText("opengl_vendor", GL11.glGetString(7936));
      playerUsageSnooper2.applyText("client_brand", VanillaManager.getText());
      playerUsageSnooper2.applyText("launched_version", this.labelText3);
      ContextCapabilities capabilities = GLContext.getCapabilities();
      playerUsageSnooper2.applyText("gl_caps[ARB_arrays_of_arrays]", capabilities.GL_ARB_arrays_of_arrays);
      playerUsageSnooper2.applyText("gl_caps[ARB_base_instance]", capabilities.GL_ARB_base_instance);
      playerUsageSnooper2.applyText("gl_caps[ARB_blend_func_extended]", capabilities.GL_ARB_blend_func_extended);
      playerUsageSnooper2.applyText("gl_caps[ARB_clear_buffer_object]", capabilities.GL_ARB_clear_buffer_object);
      playerUsageSnooper2.applyText("gl_caps[ARB_color_buffer_float]", capabilities.GL_ARB_color_buffer_float);
      playerUsageSnooper2.applyText("gl_caps[ARB_compatibility]", capabilities.GL_ARB_compatibility);
      playerUsageSnooper2.applyText("gl_caps[ARB_compressed_texture_pixel_storage]", capabilities.GL_ARB_compressed_texture_pixel_storage);
      playerUsageSnooper2.applyText("gl_caps[ARB_compute_shader]", capabilities.GL_ARB_compute_shader);
      playerUsageSnooper2.applyText("gl_caps[ARB_copy_buffer]", capabilities.GL_ARB_copy_buffer);
      playerUsageSnooper2.applyText("gl_caps[ARB_copy_image]", capabilities.GL_ARB_copy_image);
      playerUsageSnooper2.applyText("gl_caps[ARB_depth_buffer_float]", capabilities.GL_ARB_depth_buffer_float);
      playerUsageSnooper2.applyText("gl_caps[ARB_compute_shader]", capabilities.GL_ARB_compute_shader);
      playerUsageSnooper2.applyText("gl_caps[ARB_copy_buffer]", capabilities.GL_ARB_copy_buffer);
      playerUsageSnooper2.applyText("gl_caps[ARB_copy_image]", capabilities.GL_ARB_copy_image);
      playerUsageSnooper2.applyText("gl_caps[ARB_depth_buffer_float]", capabilities.GL_ARB_depth_buffer_float);
      playerUsageSnooper2.applyText("gl_caps[ARB_depth_clamp]", capabilities.GL_ARB_depth_clamp);
      playerUsageSnooper2.applyText("gl_caps[ARB_depth_texture]", capabilities.GL_ARB_depth_texture);
      playerUsageSnooper2.applyText("gl_caps[ARB_draw_buffers]", capabilities.GL_ARB_draw_buffers);
      playerUsageSnooper2.applyText("gl_caps[ARB_draw_buffers_blend]", capabilities.GL_ARB_draw_buffers_blend);
      playerUsageSnooper2.applyText("gl_caps[ARB_draw_elements_base_vertex]", capabilities.GL_ARB_draw_elements_base_vertex);
      playerUsageSnooper2.applyText("gl_caps[ARB_draw_indirect]", capabilities.GL_ARB_draw_indirect);
      playerUsageSnooper2.applyText("gl_caps[ARB_draw_instanced]", capabilities.GL_ARB_draw_instanced);
      playerUsageSnooper2.applyText("gl_caps[ARB_explicit_attrib_location]", capabilities.GL_ARB_explicit_attrib_location);
      playerUsageSnooper2.applyText("gl_caps[ARB_explicit_uniform_location]", capabilities.GL_ARB_explicit_uniform_location);
      playerUsageSnooper2.applyText("gl_caps[ARB_fragment_layer_viewport]", capabilities.GL_ARB_fragment_layer_viewport);
      playerUsageSnooper2.applyText("gl_caps[ARB_fragment_program]", capabilities.GL_ARB_fragment_program);
      playerUsageSnooper2.applyText("gl_caps[ARB_fragment_shader]", capabilities.GL_ARB_fragment_shader);
      playerUsageSnooper2.applyText("gl_caps[ARB_fragment_program_shadow]", capabilities.GL_ARB_fragment_program_shadow);
      playerUsageSnooper2.applyText("gl_caps[ARB_framebuffer_object]", capabilities.GL_ARB_framebuffer_object);
      playerUsageSnooper2.applyText("gl_caps[ARB_framebuffer_sRGB]", capabilities.GL_ARB_framebuffer_sRGB);
      playerUsageSnooper2.applyText("gl_caps[ARB_geometry_shader4]", capabilities.GL_ARB_geometry_shader4);
      playerUsageSnooper2.applyText("gl_caps[ARB_gpu_shader5]", capabilities.GL_ARB_gpu_shader5);
      playerUsageSnooper2.applyText("gl_caps[ARB_half_float_pixel]", capabilities.GL_ARB_half_float_pixel);
      playerUsageSnooper2.applyText("gl_caps[ARB_half_float_vertex]", capabilities.GL_ARB_half_float_vertex);
      playerUsageSnooper2.applyText("gl_caps[ARB_instanced_arrays]", capabilities.GL_ARB_instanced_arrays);
      playerUsageSnooper2.applyText("gl_caps[ARB_map_buffer_alignment]", capabilities.GL_ARB_map_buffer_alignment);
      playerUsageSnooper2.applyText("gl_caps[ARB_map_buffer_range]", capabilities.GL_ARB_map_buffer_range);
      playerUsageSnooper2.applyText("gl_caps[ARB_multisample]", capabilities.GL_ARB_multisample);
      playerUsageSnooper2.applyText("gl_caps[ARB_multitexture]", capabilities.GL_ARB_multitexture);
      playerUsageSnooper2.applyText("gl_caps[ARB_occlusion_query2]", capabilities.GL_ARB_occlusion_query2);
      playerUsageSnooper2.applyText("gl_caps[ARB_pixel_buffer_object]", capabilities.GL_ARB_pixel_buffer_object);
      playerUsageSnooper2.applyText("gl_caps[ARB_seamless_cube_map]", capabilities.GL_ARB_seamless_cube_map);
      playerUsageSnooper2.applyText("gl_caps[ARB_shader_objects]", capabilities.GL_ARB_shader_objects);
      playerUsageSnooper2.applyText("gl_caps[ARB_shader_stencil_export]", capabilities.GL_ARB_shader_stencil_export);
      playerUsageSnooper2.applyText("gl_caps[ARB_shader_texture_lod]", capabilities.GL_ARB_shader_texture_lod);
      playerUsageSnooper2.applyText("gl_caps[ARB_shadow]", capabilities.GL_ARB_shadow);
      playerUsageSnooper2.applyText("gl_caps[ARB_shadow_ambient]", capabilities.GL_ARB_shadow_ambient);
      playerUsageSnooper2.applyText("gl_caps[ARB_stencil_texturing]", capabilities.GL_ARB_stencil_texturing);
      playerUsageSnooper2.applyText("gl_caps[ARB_sync]", capabilities.GL_ARB_sync);
      playerUsageSnooper2.applyText("gl_caps[ARB_tessellation_shader]", capabilities.GL_ARB_tessellation_shader);
      playerUsageSnooper2.applyText("gl_caps[ARB_texture_border_clamp]", capabilities.GL_ARB_texture_border_clamp);
      playerUsageSnooper2.applyText("gl_caps[ARB_texture_buffer_object]", capabilities.GL_ARB_texture_buffer_object);
      playerUsageSnooper2.applyText("gl_caps[ARB_texture_cube_map]", capabilities.GL_ARB_texture_cube_map);
      playerUsageSnooper2.applyText("gl_caps[ARB_texture_cube_map_array]", capabilities.GL_ARB_texture_cube_map_array);
      playerUsageSnooper2.applyText("gl_caps[ARB_texture_non_power_of_two]", capabilities.GL_ARB_texture_non_power_of_two);
      playerUsageSnooper2.applyText("gl_caps[ARB_uniform_buffer_object]", capabilities.GL_ARB_uniform_buffer_object);
      playerUsageSnooper2.applyText("gl_caps[ARB_vertex_blend]", capabilities.GL_ARB_vertex_blend);
      playerUsageSnooper2.applyText("gl_caps[ARB_vertex_buffer_object]", capabilities.GL_ARB_vertex_buffer_object);
      playerUsageSnooper2.applyText("gl_caps[ARB_vertex_program]", capabilities.GL_ARB_vertex_program);
      playerUsageSnooper2.applyText("gl_caps[ARB_vertex_shader]", capabilities.GL_ARB_vertex_shader);
      playerUsageSnooper2.applyText("gl_caps[EXT_bindable_uniform]", capabilities.GL_EXT_bindable_uniform);
      playerUsageSnooper2.applyText("gl_caps[EXT_blend_equation_separate]", capabilities.GL_EXT_blend_equation_separate);
      playerUsageSnooper2.applyText("gl_caps[EXT_blend_func_separate]", capabilities.GL_EXT_blend_func_separate);
      playerUsageSnooper2.applyText("gl_caps[EXT_blend_minmax]", capabilities.GL_EXT_blend_minmax);
      playerUsageSnooper2.applyText("gl_caps[EXT_blend_subtract]", capabilities.GL_EXT_blend_subtract);
      playerUsageSnooper2.applyText("gl_caps[EXT_draw_instanced]", capabilities.GL_EXT_draw_instanced);
      playerUsageSnooper2.applyText("gl_caps[EXT_framebuffer_multisample]", capabilities.GL_EXT_framebuffer_multisample);
      playerUsageSnooper2.applyText("gl_caps[EXT_framebuffer_object]", capabilities.GL_EXT_framebuffer_object);
      playerUsageSnooper2.applyText("gl_caps[EXT_framebuffer_sRGB]", capabilities.GL_EXT_framebuffer_sRGB);
      playerUsageSnooper2.applyText("gl_caps[EXT_geometry_shader4]", capabilities.GL_EXT_geometry_shader4);
      playerUsageSnooper2.applyText("gl_caps[EXT_gpu_program_parameters]", capabilities.GL_EXT_gpu_program_parameters);
      playerUsageSnooper2.applyText("gl_caps[EXT_gpu_shader4]", capabilities.GL_EXT_gpu_shader4);
      playerUsageSnooper2.applyText("gl_caps[EXT_multi_draw_arrays]", capabilities.GL_EXT_multi_draw_arrays);
      playerUsageSnooper2.applyText("gl_caps[EXT_packed_depth_stencil]", capabilities.GL_EXT_packed_depth_stencil);
      playerUsageSnooper2.applyText("gl_caps[EXT_paletted_texture]", capabilities.GL_EXT_paletted_texture);
      playerUsageSnooper2.applyText("gl_caps[EXT_rescale_normal]", capabilities.GL_EXT_rescale_normal);
      playerUsageSnooper2.applyText("gl_caps[EXT_separate_shader_objects]", capabilities.GL_EXT_separate_shader_objects);
      playerUsageSnooper2.applyText("gl_caps[EXT_shader_image_load_store]", capabilities.GL_EXT_shader_image_load_store);
      playerUsageSnooper2.applyText("gl_caps[EXT_shadow_funcs]", capabilities.GL_EXT_shadow_funcs);
      playerUsageSnooper2.applyText("gl_caps[EXT_shared_texture_palette]", capabilities.GL_EXT_shared_texture_palette);
      playerUsageSnooper2.applyText("gl_caps[EXT_stencil_clear_tag]", capabilities.GL_EXT_stencil_clear_tag);
      playerUsageSnooper2.applyText("gl_caps[EXT_stencil_two_side]", capabilities.GL_EXT_stencil_two_side);
      playerUsageSnooper2.applyText("gl_caps[EXT_stencil_wrap]", capabilities.GL_EXT_stencil_wrap);
      playerUsageSnooper2.applyText("gl_caps[EXT_texture_3d]", capabilities.GL_EXT_texture_3d);
      playerUsageSnooper2.applyText("gl_caps[EXT_texture_array]", capabilities.GL_EXT_texture_array);
      playerUsageSnooper2.applyText("gl_caps[EXT_texture_buffer_object]", capabilities.GL_EXT_texture_buffer_object);
      playerUsageSnooper2.applyText("gl_caps[EXT_texture_integer]", capabilities.GL_EXT_texture_integer);
      playerUsageSnooper2.applyText("gl_caps[EXT_texture_lod_bias]", capabilities.GL_EXT_texture_lod_bias);
      playerUsageSnooper2.applyText("gl_caps[EXT_texture_sRGB]", capabilities.GL_EXT_texture_sRGB);
      playerUsageSnooper2.applyText("gl_caps[EXT_vertex_shader]", capabilities.GL_EXT_vertex_shader);
      playerUsageSnooper2.applyText("gl_caps[EXT_vertex_weighting]", capabilities.GL_EXT_vertex_weighting);
      playerUsageSnooper2.applyText("gl_caps[gl_max_vertex_uniforms]", GL11.glGetInteger(35658));
      GL11.glGetError();
      playerUsageSnooper2.applyText("gl_caps[gl_max_fragment_uniforms]", GL11.glGetInteger(35657));
      GL11.glGetError();
      playerUsageSnooper2.applyText("gl_caps[gl_max_vertex_attribs]", GL11.glGetInteger(34921));
      GL11.glGetError();
      playerUsageSnooper2.applyText("gl_caps[gl_max_vertex_texture_image_units]", GL11.glGetInteger(35660));
      GL11.glGetError();
      playerUsageSnooper2.applyText("gl_caps[gl_max_texture_image_units]", GL11.glGetInteger(34930));
      GL11.glGetError();
      playerUsageSnooper2.applyText("gl_caps[gl_max_texture_image_units]", GL11.glGetInteger(35071));
      GL11.glGetError();
      playerUsageSnooper2.applyText("gl_max_texture_size", getCount());
   }

   public void sendClickBlockToController(boolean flag, MovingObjectPosition hitResult, BlockPos blockPos, int count9) {
      this.enabled12 = false;
      int currentItem = this.thePlayer.inventoryPlayer.currentItem;
      this.thePlayer.inventoryPlayer.currentItem = count9;
      if (!flag) {
         this.leftClickCounter = 0;
      }

      if (this.leftClickCounter <= 0 && !this.thePlayer.isUsingItem()) {
         if (flag && hitResult != null && hitResult.typeOfHit == MovingObjectType.BLOCK) {
            if (this.theWorld.getBlockState(blockPos).getBlock().getMaterial() != Material.coreItemEntry
               && this.playerController.matchesBlockPos(blockPos, hitResult.sideHit, count9)) {
               this.effectRenderer.applyBlockPos(blockPos, hitResult.sideHit);
               this.thePlayer.respawnPlayer();
               this.enabled12 = true;
            }
         } else {
            this.playerController.setEnabled(!this.enabled12 && this.enabled13);
            this.thePlayer.updateState6();
         }
      }

      this.thePlayer.inventoryPlayer.currentItem = currentItem;
      boolean enabled18 = this.enabled13;
      this.enabled12 = enabled18;
   }

   public void applyEntityByEntity(Entity entity2) {
      this.entity = entity2;
      this.entityRenderer.applyEntityByEntity(entity2);
   }

   public ListenableFuture resolveListenableFuture(Callable callable) {
      Validate.notNull(callable);
      if (!this.isSingleplayer()) {
         ListenableFutureTask listenableFutureTask = ListenableFutureTask.create(callable);
         synchronized (this.queue) {
            this.queue.add(listenableFutureTask);
            return listenableFutureTask;
         }
      } else {
         try {
            return Futures.immediateFuture(callable.call());
         } catch (Exception exception) {
            return Futures.immediateFailedFuture(exception);
         }
      }
   }

   public void updateState2() {
      if (this.currentScreen == null) {
         GuiScreenServerList guiScreenServerList = new GuiScreenServerList();
         this.displayGuiScreen(guiScreenServerList);
         if (this.isFullScreen() && !this.integratedServer.isEnabled14()) {
            this.mcSoundHandler.updateState2();
         }
      }
   }

   private String getTextAsText() {
      return this.integratedServer != null
         ? (this.integratedServer.isEnabled14() ? "hosting_lan" : "singleplayer")
         : (this.serverData != null ? (this.serverData.isEnabled2() ? "playing_lan" : "multiplayer") : "out_of_game");
   }

   public SkinsManager getSkinsManager() {
      return this.skinsManager;
   }

   public boolean isGamePaused() {
      return this.enabled10;
   }

   public Entity getRenderViewEntity() {
      return this.entity;
   }

   public static boolean isActive() {
      return mcClient != null && mcClient.gameSettings.indexOffset36 != 0;
   }

   public Session getSession() {
      return this.session;
   }

   public void rightClickMouse() {
      if (!this.playerController.isState() || ClientGlobals.moduleManager.fixes.canExecute5()) {
         int count9 = this.count6;
         this.count6 = 4;
         boolean flag = true;
         ItemStack slot = this.thePlayer.inventoryPlayer.getCurrentItem();
         if (this.objectMouseOver == null) {
            logger.warn("Null returned as 'hitResult', this shouldn't happen!");
         } else {
            switch (SmoothChecker.countArray2[this.objectMouseOver.typeOfHit.ordinal()]) {
               case 1:
                  if (this.playerController.matchesEntityPlayer(this.thePlayer, this.objectMouseOver.entityHit, this.objectMouseOver)
                     || this.playerController.matchesEntityPlayer(this.thePlayer, this.objectMouseOver.entityHit)) {
                     return;
                  }
                  break;
               case 2:
                  BlockPos blockPos = this.objectMouseOver.getBlockPos();
                  if (this.theWorld.getBlockState(blockPos).getBlock().getMaterial() != Material.coreItemEntry) {
                     int count10 = slot != null ? slot.animationsToGo : 0;
                     if (EventConnectionCallback.resolveEventConnection(PhaseMode.PRE).isCancelled()) {
                        this.count6 = count9;
                        return;
                     }

                     if (this.playerController
                        .onPlayerRightClick(this.thePlayer, this.theWorld, slot, blockPos, this.objectMouseOver.sideHit, this.objectMouseOver.hitVec)) {
                        flag = false;
                        this.thePlayer.respawnPlayer();
                        if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
                           SettingsTxtManager.getExtraListener().get().getCompositeListener().forEach(new SecondaryCustom(blockPos, HeuristicSelector.USE));
                        }
                     }

                     if (slot == null) {
                        return;
                     }

                     if (slot.animationsToGo == 0) {
                        this.thePlayer.inventoryPlayer.mainInventory[this.thePlayer.inventoryPlayer.currentItem] = null;
                     } else if (slot.animationsToGo != count10 || this.playerController.processAction2()) {
                        this.entityRenderer.itemRenderer.updateState();
                     }
                  }
            }
         }

         if (flag) {
            ItemStack slot2 = this.thePlayer.inventoryPlayer.getCurrentItem();
            if (slot2 != null && this.playerController.sendUseItem(this.thePlayer, this.theWorld, slot2)) {
               this.entityRenderer.itemRenderer.updateState2();
            }
         }
      }
   }

   public ByteBuffer resolveByteBuffer(InputStream inputStream) throws IOException {
      BufferedImage bufferedImage = ImageIO.read(inputStream);
      int[] rgb = bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), (int[])null, 0, bufferedImage.getWidth());
      ByteBuffer buffer = ByteBuffer.allocate(4 * rgb.length);

      for (int count9 : rgb) {
         buffer.putInt(count9 << 8 | count9 >> 24 & 0xFF);
      }

      ((Buffer)buffer).flip();
      return buffer;
   }

   public NetHandlerPlayClient getNetHandlerPlayClient() {
      return this.thePlayer != null ? this.thePlayer.netHandlerPlayClient : null;
   }

   public void updateState() {
      try {
         Client.getClient().getThreadEntry().updateState();
         logger.info("Stopping!");

         try {
            WorldClient world = null;
            this.loadWorld(world);
         } catch (Throwable throwable) {
         }

         this.mcSoundHandler.updateState5();
      } finally {
         Display.destroy();
         if (!this.enabled8) {
            System.exit(0);
         }
      }

      System.gc();
   }

   public ListenableFuture getListenableFuture() {
      Runnable task = this::updateState11;
      return this.resolveListenableFuture(task);
   }

   @Override
   public void applyPlayerUsageSnooper(PlayerUsageSnooper playerUsageSnooper2) {
      playerUsageSnooper2.applyTextByText("fps", count8);
      playerUsageSnooper2.applyTextByText("vsync_enabled", this.gameSettings.state26);
      playerUsageSnooper2.applyTextByText("display_frequency", Display.getDisplayMode().getFrequency());
      playerUsageSnooper2.applyTextByText("display_type", this.state5 ? "fullscreen" : "windowed");
      playerUsageSnooper2.applyTextByText("run_time", (VanillaRunnable.currentTimeMillis() - playerUsageSnooper2.getTimestamp()) / 60L * 1000L);
      playerUsageSnooper2.applyTextByText("current_action", this.getTextAsText());
      String text = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN ? "little" : "big";
      playerUsageSnooper2.applyTextByText("endianness", text);
      playerUsageSnooper2.applyTextByText("resource_packs", this.resourceLanguageManager.copyOfAsList().size());
      int count9 = 0;

      for (TexturepackiconManager texturepackiconManager : (java.lang.Iterable<TexturepackiconManager>) (this.resourceLanguageManager.copyOfAsList())) {
         playerUsageSnooper2.applyTextByText("resource_pack[" + count9++ + "]", texturepackiconManager.getText());
      }

      if (this.integratedServer != null && this.integratedServer.getPlayerUsageSnooper() != null) {
         playerUsageSnooper2.applyTextByText("snooper_partner", this.integratedServer.getPlayerUsageSnooper().getLabelText());
      }
   }

   public boolean isEnabled5() {
      return this.enabled5;
   }

   private void applyTime(long value) {
   }

   public void loadWorld(WorldClient world) {
      String text = "";
      this.applyWorldClientByWorldClient(world, text);
   }

   public LanguageManager getLanguageManager() {
      return this.languageManager;
   }

   private void updateState4() {
      this.framebuffer.applyIndex4(this.displayWidth, this.displayHeight);
      if (this.entityRenderer != null) {
         this.entityRenderer.applyIndex(this.displayWidth, this.displayHeight);
      }
   }

   public static LanguageManager resolveLanguageManagerByMinecraft(Minecraft mc) {
      return mc.languageManager;
   }

   public boolean isIntegratedServerRunning() {
      return this.currentScreen == null && this.guiScreen == null;
   }

   public GoldenCrystal getGoldenCrystal() {
      return this.goldenCrystal;
   }

   @Override
   public ListenableFuture resolveListenableFuture(Runnable task) {
      Validate.notNull(task);
      Callable callable = Executors.callable(task);
      return this.resolveListenableFutureByCallable(callable);
   }

   public Proxy getProxy() {
      return this.proxy;
   }

   public static int getCount() {
      for (int shortValue = 16384; shortValue > 0; shortValue >>= 1) {
         GL11.glTexImage2D(32868, 0, 6408, shortValue, shortValue, 0, 6408, 5121, (ByteBuffer)null);
         if (GL11.glGetTexLevelParameteri(32868, 0, 4096) != 0) {
            return shortValue;
         }
      }

      return -1;
   }

   public BaseOptionType getBaseOptionType() {
      return this.thePlayer != null
         ? (
            this.thePlayer.worldObj.secondaryFloatArrayEntry instanceof NetherFloatArrayEntry
               ? BaseOptionType.NETHER
               : (
                  this.thePlayer.worldObj.secondaryFloatArrayEntry instanceof EndManager
                     ? (DefaultFloatEntry.labelText != null && DefaultFloatEntry.count > 0 ? BaseOptionType.END_BOSS : BaseOptionType.END)
                     : (
                        this.thePlayer.invulnerableManager.enabled2 && this.thePlayer.invulnerableManager.state2
                           ? BaseOptionType.CREATIVE
                           : BaseOptionType.GAME
                     )
               )
         )
         : BaseOptionType.MENU;
   }

   public ListenerAdapter27 getListenerAdapter27() {
      return this.listenerAdapter27;
   }

   public IResourceManager getIResourceManager() {
      return this.listener69Extension;
   }

   private void applyTextureManager(TextureManager textureManager2) throws LWJGLException {
      ScaledResolution scaledResolution = new ScaledResolution(this);
      int scaleFactor = scaledResolution.getScaleFactor();
      Framebuffer framebuffer2 = new Framebuffer(scaledResolution.getScaledWidth() * scaleFactor, scaledResolution.getScaledHeight() * scaleFactor, true);
      framebuffer2.setState(false);
      GlStateManager.matrixMode(5889);
      GlStateManager.loadIdentity();
      GlStateManager.glStateAction(0.0, (double)scaledResolution.getScaledWidth(), (double)scaledResolution.getScaledHeight(), 0.0, 1000.0, 3000.0);
      GlStateManager.matrixMode(5888);
      GlStateManager.loadIdentity();
      GlStateManager.translate(0.0F, 0.0F, -2000.0F);
      GlStateManager.applyState3();
      GlStateManager.applyState20();
      GlStateManager.enableDepth();
      GlStateManager.disableTexture2D();
      BufferedImage bufferedImage = null;
      InputStream resourceAsStream = Client.class.getClassLoader().getResourceAsStream("assets/augustus/pictures/startLogo.png");

      try {
         bufferedImage = ImageIO.read(resourceAsStream);
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }

      ResourceLocation location = textureManager2.resolveResourceLocationByText("logo", new DynamicTexture(bufferedImage));
      this.resourceLocation = location;
      textureManager2.applyResourceLocation3(this.resourceLocation);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      worldRenderer.getWorldRenderer(0.0, (double)this.displayHeight, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(255, 255, 255, 255).updateState();
      worldRenderer.getWorldRenderer((double)this.displayWidth, (double)this.displayHeight, 0.0)
         .getWorldRenderer6(0.0, 0.0)
         .resolveWorldRenderer(255, 255, 255, 255)
         .updateState();
      worldRenderer.getWorldRenderer((double)this.displayWidth, 0.0, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(255, 255, 255, 255).updateState();
      worldRenderer.getWorldRenderer(0.0, 0.0, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(255, 255, 255, 255).updateState();
      tessellator.updateState();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      int count9 = (scaledResolution.getScaledHeight() - 256) / 2;
      int count10 = (scaledResolution.getScaledWidth() - 256) / 2;
      this.applyIndex(count10, count9, 0, 0, 256, 256, 255, 255, 255, 255);
      GlStateManager.applyState3();
      GlStateManager.applyState20();
      framebuffer2.renderElement();
      framebuffer2.applyIndexByIndex(scaledResolution.getScaledWidth() * scaleFactor, scaledResolution.getScaledHeight() * scaleFactor);
      GlStateManager.applyState14();
      GlStateManager.alphaFunc(516, 0.1F);
      this.updateState6();
   }

   public void applyCrashReportByCrashReport(CrashReport crashReport2) {

      try {
         if (crashReport2 != null) {
            ProfileConfigManager profileConfigManager = new ProfileConfigManager();
            String text = Client.getClient().getString7() + " " + crashReport2.getText();
            profileConfigManager.applyText3(text, 0x30ccd67839adL);
         }
      } catch (Exception exception) {
      }

      File file3 = new File(new File(RenderProperty.getRenderProperty().getText()), "crash-reports");
      ConfigManager.applyString2(file3.getAbsolutePath());
      file3 = new File(file3, "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + "-client.txt");
      STDERRManager.println(crashReport2.getText());
      if (crashReport2.getFile() != null) {
         STDERRManager.println("#@!@# Game crashed! Crash report saved to: #@!@# " + crashReport2.getFile());
         System.exit(-1);
      } else if (crashReport2.matchesFile(file3)) {
         STDERRManager.println("#@!@# Game crashed! Crash report saved to: #@!@# " + file3.getAbsolutePath());
         System.exit(-1);
      } else {
         STDERRManager.println("#@?@# Game crashed! Crash report could not be saved. #@?@#");
         System.exit(-2);
      }
   }

   public RenderItem getRenderItem() {
      return this.renderItem;
   }

   private static boolean isActive6() {
      String[] textArray = new String[]{"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"};
      int length = textArray.length;

      for (int index = 0; index < length; index++) {
         String property = System.getProperty(textArray[index]);
         if (property != null && property.contains("64")) {
            return true;
         }
      }

      return false;
   }

   public int getInt3() {
      return this.count5;
   }

   private void updateState22() {
      ExtendedThreadAdapter extendedThreadAdapter = new ExtendedThreadAdapter(this, "Timer hack thread");
      extendedThreadAdapter.setDaemon(true);
      extendedThreadAdapter.start();
   }

   private void updateState23() throws LWJGLException {
      Display.setResizable(true);
      StringBuilder builder = new StringBuilder();
      Client clientItem = Client.getClient();
      Display.setTitle(builder.append(clientItem.getString4()).append(" ").append(Client.getClient().getString5()).toString());

      try {
         Display.create(new PixelFormat().withDepthBits(24));
      } catch (LWJGLException lWJGLException) {
         logger.error("Couldn't set pixel format", (Throwable)lWJGLException);

         try {
            Thread.sleep(1000L);
         } catch (InterruptedException interruptedException) {
         }

         if (this.state5) {
            this.updateState20();
         }

         Display.create();
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.gameSettings.state115;
   }

   public ListenableFuture resolveListenableFutureByCallable(Callable callable) {
      Validate.notNull(callable);
      if (!this.isSingleplayer()) {
         ListenableFutureTask listenableFutureTask = ListenableFutureTask.create(callable);
         synchronized (this.queue2) {
            this.queue2.add(listenableFutureTask);
            return listenableFutureTask;
         }
      } else {
         try {
            return Futures.immediateFuture(callable.call());
         } catch (Exception exception) {
            return Futures.immediateFailedFuture(exception);
         }
      }
   }

   public ResourceLanguageManager getResourceLanguageManager() {
      return this.resourceLanguageManager;
   }

   public ISaveFormat getISaveFormat() {
      return this.iSaveFormat;
   }

   public void setServerData(ServerData serverData2) {
      this.serverData = serverData2;
   }

   public CrashReport resolveCrashReport(CrashReport crashReport2) {
      crashReport2.getCrashReportCategory().applyText("Launched Version", new AdvancedCallableAdapter(this));
      crashReport2.getCrashReportCategory().applyText("LWJGL", new AltCallableAdapter(this));
      crashReport2.getCrashReportCategory().applyText("OpenGL", new ExtendedCallableAdapter(this));
      crashReport2.getCrashReportCategory().applyText("GL Caps", new CommonCallableAdapter(this));
      crashReport2.getCrashReportCategory().applyText("Using VBOs", new ExtraCallableAdapter(this));
      crashReport2.getCrashReportCategory().applyText("Is Modded", new BaseCallableAdapter(this));
      crashReport2.getCrashReportCategory().applyText("Type", new SupplementalCallable(this));
      crashReport2.getCrashReportCategory().applyText("Resource Packs", new InternalCallableAdapter(this));
      crashReport2.getCrashReportCategory().applyText("Current Language", new EnhancedCallable(this));
      crashReport2.getCrashReportCategory().applyText("Profiler Position", new StandardCallableAdapter(this));
      crashReport2.getCrashReportCategory().applyText("CPU", new CustomCallableAdapter(this));
      if (this.theWorld != null) {
         this.theWorld.resolveCrashReportCategory(crashReport2);
      }

      return crashReport2;
   }

   public int getCount6() {
      return this.count6;
   }

   @Override
   public ListenableFuture resolveListenableFutureByRunnable(Runnable task) {
      Validate.notNull(task);
      Callable callable = Executors.callable(task);
      return this.resolveListenableFuture(callable);
   }

   public void setIngameFocus(boolean ingameFocus) {
      if (OsNameManager.getMovementIntervalProperty() != MovementIntervalProperty.OSX) {
         InputStream inputStream = null;
         InputStream inputStream2 = null;

         try {
            if (ingameFocus) {
               inputStream = this.iReloadableResourceManager.resolveInputStream5(new ResourceLocation("icons/icon_16x16.png"));
               inputStream2 = this.iReloadableResourceManager.resolveInputStream5(new ResourceLocation("icons/icon_32x32.png"));
            } else {
               inputStream = Minecraft.class.getClassLoader().getResourceAsStream("assets/augustus/pictures/logo_16x16.png");
               inputStream2 = Minecraft.class.getClassLoader().getResourceAsStream("assets/augustus/pictures/logo_32x32.png");
            }

            if (inputStream != null && inputStream2 != null) {
               Display.setIcon(new ByteBuffer[]{this.resolveByteBuffer(inputStream), this.resolveByteBuffer(inputStream2)});
            }
         } catch (IOException ioException) {
            logger.error("Couldn't set icon", (Throwable)ioException);
         } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(inputStream2);
         }
      }
   }

   public MinecraftSessionService getMinecraftSessionService() {
      return this.minecraftSessionService;
   }

   public boolean isCallingFromMinecraftThread() {
      return this.state5;
   }

   public Framebuffer getFramebuffer() {
      return this.framebuffer;
   }

   public Profiler getProfilerAsProfiler() {
      return this.profiler;
   }

   private ItemStack resolveItemStack(Item item, int count9, TileEntity tileEntity) {
      ItemStack stack = new ItemStack(item, 1, count9);
      NBTTagCompound tagCompound = new NBTTagCompound();
      tileEntity.applyNBTTagCompound(tagCompound);
      if (item == Items.skull && tagCompound.containsKey("Owner")) {
         tagCompound = tagCompound.getNBTTagCompound("Owner");
         NBTTagCompound tagCompound2 = new NBTTagCompound();
         tagCompound2.applyText("SkullOwner", tagCompound);
         stack.processAction(tagCompound2);
         return stack;
      } else {
         stack.applyText("BlockEntityTag", tagCompound);
         tagCompound = new NBTTagCompound();
         NBTTagList nBTTagList = new NBTTagList();
         nBTTagList.applyLISTManager(new HandlerStringEntry("(+NBT)"));
         tagCompound.applyText("Lore", nBTTagList);
         stack.applyText("display", tagCompound);
         return stack;
      }
   }

   public RenderManager getRenderManager() {
      return this.renderManager;
   }

   public ServerData getServerData() {
      return this.serverData;
   }

   public void applyCrashReport(CrashReport crashReport2) {
      this.enabled8 = true;
      this.crashReport = crashReport2;
   }

   public static Map getMap() {
      HashMap map = Maps.newHashMap();
      map.put("X-Minecraft-Username", getMinecraft().getSession().getText1());
      map.put("X-Minecraft-UUID", getMinecraft().getSession().getText());
      map.put("X-Minecraft-Version", "1.8.9");
      return map;
   }

   public void updateState5() {
      if (!this.state5 && Display.wasResized()) {
         int displayWidth2 = this.displayWidth;
         int displayHeight2 = this.displayHeight;
         this.displayWidth = Display.getWidth();
         this.displayHeight = Display.getHeight();
         if (this.displayWidth != displayWidth2 || this.displayHeight != displayHeight2) {
            if (this.displayWidth <= 0) {
               this.displayWidth = 1;
            }

            if (this.displayHeight <= 0) {
               this.displayHeight = 1;
            }

            displayWidth2 = this.displayHeight;
            displayHeight2 = this.displayWidth;
            this.applyIndex(displayHeight2, displayWidth2);
         }
      }
   }

   public PlayerUsageSnooper getPlayerUsageSnooper() {
      return this.playerUsageSnooper;
   }

   public ItemRenderer getItemRenderer() {
      return this.itemRenderer;
   }

   public MinecraftTimer getMinecraftTimer() {
      return this.minecraftTimer;
   }

   public static long getTimestamp() {
      return Sys.getTime() * 1000L / Sys.getTimerResolution();
   }

   public synchronized void setSession(Session session2) {
      this.session = session2;
   }

   public SoundHandler getMcSoundHandler() {
      return this.mcSoundHandler;
   }

   public void updateState7() {
      try {
         byteFlagArray = new byte[0];
         this.renderGlobal.updateState15();
      } catch (Throwable throwable) {
      }

      try {
         System.gc();
         WorldClient world = null;
         this.loadWorld(world);
      } catch (Throwable throwable2) {
      }

      System.gc();
   }

   public PropertyMap getPropertyMap() {
      if (this.propertyMap.isEmpty()) {
         GameProfile gameProfile = this.getMinecraftSessionService().fillProfileProperties(this.session.getGameProfile(), false);
         this.propertyMap.putAll(gameProfile.getProperties());
      }

      return this.propertyMap;
   }

   public void setEnabled(boolean enabled18) {
      if (!enabled18) {
         this.leftClickCounter = 0;
      }

      if (this.leftClickCounter <= 0 && !this.thePlayer.isUsingItem()) {
         if (enabled18 && this.objectMouseOver != null && this.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
            BlockPos blockPos = this.objectMouseOver.getBlockPos();
            if (this.theWorld.getBlockState(blockPos).getBlock().getMaterial() != Material.coreItemEntry
               && this.playerController.matchesBlockPos(blockPos, this.objectMouseOver.sideHit)) {
               if (!((Module)ClientGlobals.moduleManager.noBlockParticles).isToggled()) {
                  this.effectRenderer.applyBlockPos(blockPos, this.objectMouseOver.sideHit);
               }

               this.thePlayer.respawnPlayer();
            }
         } else {
            this.playerController.updateState();
         }
      }

      if (((Module)ClientGlobals.moduleManager.fixes).isToggled()
         && ClientGlobals.moduleManager.fixes.settingHitDelayFixMode.getSelected().equals("WithoutDoubleClick")) {
         this.leftClickCounter = 0;
      }
   }

   private void applyIndexByIndex(int count9) {
      ArrayList list3 = new ArrayList();
      if (!list3.isEmpty()) {
         SecondaryComparableAdapter secondaryComparableAdapter = (SecondaryComparableAdapter)list3.remove(0);
         if (count9 == 0) {
            if (secondaryComparableAdapter.labelText.length() > 0) {
               int count10 = this.labelText2.lastIndexOf(".");
               if (count10 >= 0) {
                  String text = this.labelText2.substring(0, count10);
                  this.labelText2 = text;
               }
            }
         } else {
            count9--;
            if (count9 < list3.size() && !((SecondaryComparableAdapter)list3.get(count9)).labelText.equals("unspecified")) {
               if (this.labelText2.length() > 0) {
                  String text2 = this.labelText2 + ".";
                  this.labelText2 = text2;
               }

               String text3 = this.labelText2 + ((SecondaryComparableAdapter)list3.get(count9)).labelText;
               this.labelText2 = text3;
            }
         }
      }
   }

   public IntegratedServer getIntegratedServer() {
      return this.integratedServer;
   }

   private void applyText(String text) {
      if (this.enabled6) {
         int count9 = GL11.glGetError();
         if (count9 != 0) {
            String text2 = GLU.gluErrorString(count9);
            logger.error("########## GL ERROR ##########");
            logger.error("@ " + text);
            logger.error(count9 + ": " + text2);
         }
      }
   }

   @Override
   public boolean isSingleplayer() {
      return Thread.currentThread() == this.thread;
   }

   public TextureManager getTextureManager() {
      return this.textureManager;
   }

   public boolean isState() {
      return this.state;
   }

   private void applyIndex(int count9, int count10) {
      this.displayWidth = Math.max(1, count9);
      this.displayHeight = Math.max(1, count10);
      TertiaryItemCallback.applyIndex3(this.displayWidth, this.displayHeight);
      if (this.currentScreen != null) {
         ScaledResolution scaledResolution = new ScaledResolution(this);
         this.currentScreen.applyMinecraft(this, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight());
      }

      LoadingScreenRenderer loadingScreenRenderer2 = new LoadingScreenRenderer(this);
      this.loadingScreenRenderer = loadingScreenRenderer2;
      this.updateState4();
   }

   private void updateState3() throws IOException {
      long keySalt = 0x2e31d68be332L ^ 0x5d3e22c3ed6dL;
      long keySalt2 = 0x2e31d68be332L ^ 0x2987d6198b1eL;
      long keySalt3 = 0x2e31d68be332L ^ 0x6e7c981af48L;
      int count9 = 0;
      int count10 = 24184;
      long timestamp10 = System.nanoTime();
      this.uNKNOWNManager.applyTextByText("root");
      if (Display.isCreated() && Display.isCloseRequested()) {
         this.updateState14();
      }

      if (this.state && this.theWorld != null) {
         float scaleFactor = this.timer.scaleFactor6;
         this.timer.updateState();
         this.timer.scaleFactor6 = scaleFactor;
         scaleFactor = this.minecraftTimer.scaleFactor6;
         this.minecraftTimer.updateState();
         this.minecraftTimer.scaleFactor6 = scaleFactor;
      } else {
         this.timer.updateState();
         this.minecraftTimer.updateState();
      }

      float scaleFactor2 = this.timer.scaleFactor6;
      if (((Module)ClientGlobals.moduleManager.timeManipulation).isToggled()) {
         int indexOffset2 = this.timer.indexOffset;
         int indexOffset3 = this.minecraftTimer.indexOffset;
         if (this.minecraftTimer.indexOffset >= 1) {
            long value = this.epochMillis + (long)indexOffset3;
            this.timestamp = value;
            value = this.epochMillis2 + (long)indexOffset2;
            this.timestamp2 = value;
         }

         if (this.minecraftTimer.indexOffset >= 1) {
            int count11 = (int)(this.timestamp - this.timestamp2);
            count9 = count11;
            boolean flag = ClientGlobals.moduleManager.timeManipulation.checkCondition(count9);
            this.enabled11 = flag;
            if (this.enabled11) {
               long value2 = this.timestamp2 - (long)indexOffset2;
               this.timestamp2 = value2;
            }
         }

         if (this.enabled11) {
            this.timer.indexOffset = 0;
            this.timer.scaleFactor6 = 0.0F;
         }

         long value3 = this.epochMillis2 + (long)this.timer.indexOffset;
         long value4 = this.epochMillis + (long)this.minecraftTimer.indexOffset;
         count9 = (int)(value3 - value4);
         if (!this.enabled11) {
            if ((double)count9 < ClientGlobals.moduleManager.timeManipulation.settingMinTicksAvailable.getValue() - 1.0 && this.theWorld != null) {
               EventSlowDown event = SlowDownListener.resolveEventSlowDown(0, this.minecraftTimer.scaleFactor2, CombatThresholdProperty.TIMER);
               this.timer.scaleFactor2 = event.getFactor();
               if (this.timer.indexOffset >= 1) {
                  event = SlowDownListener.resolveEventSlowDown(0, this.minecraftTimer.scaleFactor2, CombatThresholdProperty.TICK);
                  this.timer.indexOffset = this.timer.indexOffset
                     + (int)Math.min(
                        (double)event.getCount2(), ClientGlobals.moduleManager.timeManipulation.settingMinTicksAvailable.getValue() - (double)count9
                     );
               }
            } else {
               this.timer.scaleFactor2 = this.minecraftTimer.scaleFactor2;
               if ((double)count9 < ClientGlobals.moduleManager.timeManipulation.settingMinTicksAvailable.getValue() && this.minecraftTimer.indexOffset >= 1) {
                  boolean flag2 = this.timer.indexOffset >= 1;
                  this.timer.processAction(this.minecraftTimer);
                  if (flag2) {
                     this.timer.indexOffset -= count9;
                  }
               }
            }
         }
      }

      this.epochMillis = this.epochMillis + (long)this.minecraftTimer.indexOffset;
      this.epochMillis2 = this.epochMillis2 + (long)this.timer.indexOffset;
      this.factor = (float)this.epochMillis + this.minecraftTimer.scaleFactor3;
      this.factor2 = (float)this.epochMillis2 + this.timer.scaleFactor3;
      this.uNKNOWNManager.applyTextByText("scheduledExecutables");
      EventMouseClick event2 = null;
      if (this.thePlayer != null && this.theWorld != null) {
         event2 = MouseClickListener.resolveEventMouseClick(this.timer.indexOffset, this.timer.scaleFactor6);
         this.timer.indexOffset = event2.getIndex();
         this.timer.scaleFactor6 = event2.getFactor();
      }

      if (this.timer.indexOffset >= 1
         && Client.getClient().getModuleManager() != null
         && this.theWorld != null
         && this.thePlayer != null
         && (
            ClientGlobals.moduleManager.packetHandler.settingMacroDelayModes.getSelected().equals("OtherMacroDelay")
               || ClientGlobals.moduleManager.packetHandler.settingMacroDelayModes.getSelected().equals("ExperimentalMacroDelay")
         )) {
         LatencyPacket packet = new LatencyPacket();
         packet.enabled2 = true;
         this.getNetHandlerPlayClient().getNetworkManager().handlePacket8(packet);
         ClientGlobals.moduleManager.packetHandler.setExpanded2(false);
      }

      synchronized (this.queue) {
         while (!this.queue.isEmpty()) {
            OsNameManager.resolveTarget((FutureTask)this.queue.poll(), logger);
         }
      }

      if ((
            !ClientGlobals.moduleManager.packetHandler.settingMacroDelayModes.getSelected().equals("ExperimentalMacroDelay")
               || !ClientGlobals.moduleManager.packetHandler.isExpanded2()
         )
         && ClientGlobals.moduleManager.packetHandler.settingEventToHandlePacket.getSelected().equals("Vanilla")
         && (
            !((Module)ClientGlobals.moduleManager.timeManipulation).isToggled()
               || !this.enabled11
               || ClientGlobals.moduleManager.timeManipulation.settingUpdateEntityPositions.isEnabled()
               || !ClientGlobals.moduleManager.timeManipulation.settingCorrect.isEnabled()
         )
         && (event2 == null || !event2.isEnabled2() || !event2.isEnabled())) {
         this.updateState12();
      }

      if (this.enabled11 && ClientGlobals.moduleManager.timeManipulation.settingUpdateEntityPositions.isEnabled()) {
         for (int index = 0; index < this.minecraftTimer.indexOffset; index++) {
            ClientGlobals.moduleManager.timeManipulation.updateState();
         }
      }

      this.uNKNOWNManager.updateState();
      keySalt3 = System.nanoTime();
      this.uNKNOWNManager.applyTextByText("tick");
      count9 = this.timer.indexOffset - 1;
      ClientGlobals.moduleManager.rotationHandler.tickDelay1++;
      if (this.timer.indexOffset > 0) {
         TickListener.applyRenderSliderProperty(RenderSliderProperty.PRE);

         for (int index2 = 0; index2 < this.timer.indexOffset; index2++) {
            count10 = count9 - index2;
            this.applyIndexByIndex(count10, index2);
         }

         boolean flag3 = this.timer.indexOffset > 1;
         this.enabled9 = flag3;
         TickListener.applyRenderSliderProperty(RenderSliderProperty.POST);
         ClientGlobals.moduleManager.rotationHandler.tickDelay = ClientGlobals.moduleManager.rotationHandler.tickDelay1;
         ClientGlobals.moduleManager.rotationHandler.tickDelay1 = 0;
         GuiScreen currentScreen2 = this.currentScreen;
         this.guiScreen = currentScreen2;
      }

      this.uNKNOWNManager.applyText("preRenderErrors");
      long value5 = System.nanoTime() - keySalt3;
      this.applyText("Pre render");
      this.uNKNOWNManager.applyText("sound");
      this.mcSoundHandler.applyEntityPlayer(this.thePlayer, this.timer.scaleFactor6);
      this.uNKNOWNManager.updateState();
      this.uNKNOWNManager.applyTextByText("render");
      GlStateManager.pushMatrix();
      GlStateManager.glClear(16640);
      this.framebuffer.setState(true);
      this.uNKNOWNManager.applyTextByText("display");
      GlStateManager.disableTexture2D();
      if (this.thePlayer != null && this.thePlayer.isActive15()) {
         this.gameSettings.indexOffset64 = 0;
      }

      this.uNKNOWNManager.updateState();
      if (!this.enabled7) {
         this.uNKNOWNManager.applyText("gameRenderer");
         this.entityRenderer.applyFloatByFloat(this.timer.scaleFactor6, timestamp10);
         this.uNKNOWNManager.updateState();
      }

      this.uNKNOWNManager.updateState();
      if (this.gameSettings.state14 && this.gameSettings.state106 && !this.gameSettings.state11) {
         if (!this.uNKNOWNManager.enabled) {
            this.uNKNOWNManager.updateState2();
         }

         this.uNKNOWNManager.enabled = true;
         this.applyTime(value5);
      } else {
         this.uNKNOWNManager.enabled = false;
         keySalt2 = System.nanoTime();
         this.timestamp4 = keySalt2;
      }

      if (!((Module)ClientGlobals.moduleManager.antiAchievement).isToggled()) {
         this.guiAchievement.renderElement();
      }

      this.framebuffer.renderElement();
      GlStateManager.popMatrix();
      GlStateManager.pushMatrix();
      this.framebuffer.applyIndexByIndex(this.displayWidth, this.displayHeight);
      GlStateManager.popMatrix();
      GlStateManager.pushMatrix();
      GlStateManager.popMatrix();
      this.uNKNOWNManager.applyTextByText("root");
      if (!ClientGlobals.moduleManager.motionBlur.amdUnsupported && ((Module)ClientGlobals.moduleManager.motionBlur).isToggled()) {
         ClientGlobals.moduleManager.motionBlur.updateState();
      }

      this.updateState6();
      Thread.yield();
      this.uNKNOWNManager.applyTextByText("stream");
      this.uNKNOWNManager.applyTextByText("update");
      this.uNKNOWNManager.applyText("submit");
      this.uNKNOWNManager.updateState();
      this.uNKNOWNManager.updateState();
      this.applyText("Post render");
      count9 = this.count4 + 1;
      this.count4 = count9;
      boolean flag4 = this.isFullScreen() && this.currentScreen != null && this.currentScreen.isActive() && !this.integratedServer.isEnabled14();
      this.state = flag4;
      keySalt = System.nanoTime();
      this.profiler.applyTime(keySalt - this.timestamp5);
      this.timestamp5 = keySalt;

      while (getTimestamp() >= this.timestamp6 + 1000L) {
         count8 = this.count4;
         String text = String.format(
            "%d fps (%d chunk update%s) T: %s%s%s%s%s",
            count8,
            RenderChunk.count3,
            RenderChunk.count3 != 1 ? "s" : "",
            (float)this.gameSettings.indexOffset35 == FactoryOptionType.FRAMERATE_LIMIT.getFactor2() ? "inf" : this.gameSettings.indexOffset35,
            this.gameSettings.state26 ? " vsync" : "",
            this.gameSettings.state5 ? "" : " fast",
            this.gameSettings.indexOffset33 == 0 ? "" : (this.gameSettings.indexOffset33 == 1 ? " fast-clouds" : " fancy-clouds"),
            OpenGlHelper.isActive() ? " vbo" : ""
         );
         this.labelText4 = text;
         RenderChunk.count3 = 0;
         keySalt = this.timestamp6 + 1000L;
         this.timestamp6 = keySalt;
         this.count4 = 0;
         this.playerUsageSnooper.updateState3();
         if (!this.playerUsageSnooper.isEnabled()) {
            this.playerUsageSnooper.updateState2();
         }
      }

      if (this.isActive4()) {
         this.uNKNOWNManager.applyTextByText("fpslimit_wait");
         Display.sync(this.getCountAsIndex());
         this.uNKNOWNManager.updateState();
      } else if (this.gameSettings.state26 && !Display.isVisible()) {
         Display.sync(60);
      }

      this.uNKNOWNManager.updateState();
      this.timer.scaleFactor6 = scaleFactor2;
   }

   private boolean matchesGuiScreenByGuiScreen(GuiScreen screen) {
      return !((Module)Client.getClient().getModuleManager().baritone).isToggled()
         ? screen.enabled
         : SettingsTxtManager.getExtraListener().get().getCoreListener().isActiveAsFlag() && this.thePlayer != null || screen.enabled;
   }

   public static int getLimitFramerate() {
      return count8;
   }

   public void displayGuiScreen(GuiScreen screen) {
      if (this.currentScreen != null) {
         this.currentScreen.enableRepeatEvents();
      }

      if (screen == null && this.theWorld == null) {
         screen = new GuiMainMenu();
      } else if (screen == null && this.thePlayer.getFloat2() <= 0.0F) {
         screen = new GuiGameOver();
      }

      if (screen instanceof GuiMainMenu) {
         this.gameSettings.state14 = false;
         this.ingameGUI.getInternalLoggerEntry().renderElement();
      }

      this.currentScreen = (GuiScreen)screen;
      this.guiScreen2 = (GuiScreen)screen;
      if (screen != null) {
         this.updateState10();
         ScaledResolution scaledResolution = new ScaledResolution(this);
         int scaledWidth = scaledResolution.getScaledWidth();
         int scaledHeight = scaledResolution.getScaledHeight();
         screen.applyMinecraft2(this, scaledWidth, scaledHeight);
         this.enabled7 = false;
      } else {
         this.mcSoundHandler.updateState3();
         this.updateState21();
      }

      if (this.thePlayer != null) {
         this.thePlayer.setGuiScreen((GuiScreen)screen);
      }
   }

   public boolean isActive4() {
      return (float)this.getCountAsIndex() < FactoryOptionType.FRAMERATE_LIMIT.getFactor2();
   }

   public void updateState8() {
      if (this.objectMouseOver != null) {
         ClickListener.resolveEventClick(MovementSpeedProperty.MID);
         boolean enabled18 = this.thePlayer.invulnerableManager.enabled2;
         int count9 = 0;
         boolean flag = false;
         TileEntity tileEntity = null;
         Object entry;
         if (this.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
            BlockPos blockPos = this.objectMouseOver.getBlockPos();
            Block block = this.theWorld.getBlockState(blockPos).getBlock();
            if (block.getMaterial() == Material.coreItemEntry) {
               return;
            }

            entry = block.processAction4(this.theWorld, blockPos);
            if (entry == null) {
               return;
            }

            if (enabled18 && GuiScreen.isActive3()) {
               tileEntity = this.theWorld.resolveTileEntity(blockPos);
            }

            count9 = (entry instanceof ItemBlock && !block.processAction4() ? Block.resolveBlock((Item)entry) : block).processAction2((World)this.theWorld, blockPos);
            flag = ((Item)entry).isState();
         } else {
            if (this.objectMouseOver.typeOfHit != MovingObjectType.ENTITY || this.objectMouseOver.entityHit == null || !enabled18) {
               return;
            }

            if (this.objectMouseOver.entityHit instanceof EntityPainting) {
               entry = Items.painting;
            } else if (this.objectMouseOver.entityHit instanceof EntityLeashknot) {
               entry = Items.lead;
            } else if (this.objectMouseOver.entityHit instanceof EntityItemframe) {
               EntityItemframe entityHit = (EntityItemframe)this.objectMouseOver.entityHit;
               ItemStack stack = entityHit.getItemStack();
               if (stack == null) {
                  entry = Items.itemFrame;
               } else {
                  entry = stack.getItem();
                  count9 = stack.getIndexOffset();
                  flag = true;
               }
            } else if (this.objectMouseOver.entityHit instanceof EntityMinecart) {
               EntityMinecart entityHit2 = (EntityMinecart)this.objectMouseOver.entityHit;
               switch (SmoothChecker.countArray[entityHit2.getFURNACE().ordinal()]) {
                  case 1:
                     entry = Items.furnaceMinecart;
                     break;
                  case 2:
                     entry = Items.chestMinecart;
                     break;
                  case 3:
                     entry = Items.tntMinecart;
                     break;
                  case 4:
                     entry = Items.hopperMinecart;
                     break;
                  case 5:
                     entry = Items.commandBlockMinecart;
                     break;
                  default:
                     entry = Items.minecart;
               }
            } else if (this.objectMouseOver.entityHit instanceof EntityBoat) {
               entry = Items.boat;
            } else if (this.objectMouseOver.entityHit instanceof EntityArmorstand) {
               entry = Items.armorStand;
            } else {
               entry = Items.spawnEgg;
               count9 = SkippingEntityWithIdManager.computeCount(this.objectMouseOver.entityHit);
               flag = true;
               if (!SkippingEntityWithIdManager.map6.containsKey(count9)) {
                  return;
               }
            }
         }

         InventoryPlayer inventoryPlayer = this.thePlayer.inventoryPlayer;
         if (tileEntity == null) {
            inventoryPlayer.applyItem((Item)entry, count9, flag, enabled18);
         } else {
            ItemStack stack2 = this.resolveItemStack((Item)entry, count9, tileEntity);
            inventoryPlayer.processAction(inventoryPlayer.currentItem, stack2);
         }

         if (enabled18) {
            count9 = this.thePlayer.container.itemList2.size() - 9 + inventoryPlayer.currentItem;
            this.playerController.applyItemStack(inventoryPlayer.processAction3(inventoryPlayer.currentItem), count9);
         }
      }
   }

   public void clickMouse() {
      if (this.objectMouseOver == null
         || this.objectMouseOver.typeOfHit != MovingObjectType.ENTITY
         || !AttackTargetHitCheck.checkEntity(this.objectMouseOver.entityHit)) {
         if (((Module)ClientGlobals.moduleManager.fixes).isToggled()
            && (
               ClientGlobals.moduleManager.fixes.settingHitDelayFixMode.getSelected().equals("OnlyAtTargetLite") && this.leftClickCounter < 9
                  || ClientGlobals.moduleManager.fixes.settingHitDelayFixMode.getSelected().equals("OnlyAtTarget")
            )
            && this.objectMouseOver != null
            && this.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
            this.leftClickCounter = 0;
         }

         if (this.leftClickCounter > 0
            && ClientGlobals.moduleManager.debug.settingLostHitDebug.isEnabled()
            && ((Module)ClientGlobals.moduleManager.debug).isToggled()
            && this.objectMouseOver != null
            && this.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
            RayTraceUtil.applyString("Lost Hit");
         }

         if (this.leftClickCounter <= 0) {
            if (ViaLoadingBase.protocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8)) {
               this.thePlayer.respawnPlayer();
            }

            if (this.objectMouseOver == null) {
               logger.error("Null returned as 'hitResult', this shouldn't happen!");
               if (this.playerController.canExecute()) {
                  this.leftClickCounter = 10;
               }
            } else {
               switch (SmoothChecker.countArray2[this.objectMouseOver.typeOfHit.ordinal()]) {
                  case 1:
                     this.playerController.attackEntity(this.thePlayer, this.objectMouseOver.entityHit);
                     break;
                  case 2:
                     BlockPos blockPos = this.objectMouseOver.getBlockPos();
                     if (this.theWorld.getBlockState(blockPos).getBlock().getMaterial() != Material.coreItemEntry) {
                        this.playerController.onPlayerDamageBlock(blockPos, this.objectMouseOver.sideHit);
                        if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
                           SettingsTxtManager.getExtraListener()
                              .get()
                              .getCompositeListener()
                              .forEach(new SecondaryCustom(blockPos, HeuristicSelector.START_BREAK));
                        }
                        break;
                     }
                  case 3:
                  default:
                     if (this.playerController.canExecute()) {
                        this.leftClickCounter = 10;
                        if (((Module)ClientGlobals.moduleManager.fixes).isToggled()
                           && ClientGlobals.moduleManager.fixes.settingHitDelayFixMode.getSelected().equals("Default")) {
                           this.leftClickCounter = 0;
                        }
                     }

                     this.thePlayer.updateState6();
               }

               if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
                  this.thePlayer.respawnPlayer();
               }
            }
         }

         EventListener.getAbstractEventAdapter();
      }
   }

   private void updateState12() {
      synchronized (this.queue2) {
         while (!this.queue2.isEmpty()) {
            OsNameManager.resolveTarget((FutureTask)this.queue2.poll(), logger);
         }
      }
   }

   public void updateState14() {
      this.enabled17 = false;
   }

   public void updateState21() {
      if (Display.isActive() && !this.inGameHasFocus) {
         this.inGameHasFocus = true;
         this.mouseHelper.setGrabbed();
         GuiScreen screen = null;
         this.displayGuiScreen(screen);
         this.leftClickCounter = 10000;
         Client.getClient().getModuleManager().saveMoveKeys.updateState();
      }
   }

   public void updateState18() {
      int count9 = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() : Keyboard.getEventKey();
      if (count9 != 0
         && !Keyboard.isRepeatEvent()
         && (!(this.currentScreen instanceof StandardGuiScreenAdapter) || ((StandardGuiScreenAdapter)this.currentScreen).timestamp2 <= getTimestamp() - 20L)
         && Keyboard.getEventKeyState()) {
         if (count9 == this.gameSettings.keyBindFullscreen.getKeyCode()) {
            this.updateState16();
         } else if (count9 == this.gameSettings.keyBindScreenshot.getKeyCode()) {
            SupplementalLoggerEntry.applyFile(this.mcDataDir, this.displayWidth, this.displayHeight, this.framebuffer);
         }
      }
   }

   private void updateState20() throws LWJGLException {
      HashSet set = Sets.newHashSet();
      Collections.addAll(set, Display.getAvailableDisplayModes());
      DisplayMode desktopDisplayMode = Display.getDesktopDisplayMode();
      if (!set.contains(desktopDisplayMode) && OsNameManager.getMovementIntervalProperty() == MovementIntervalProperty.OSX) {
         for (DisplayMode displayMode : (java.lang.Iterable<DisplayMode>) (list)) {
            boolean flag = true;

            for (DisplayMode displayMode2 : (java.lang.Iterable<DisplayMode>) (set)) {
               if (displayMode2.getBitsPerPixel() == 32 && displayMode2.getWidth() == displayMode.getWidth() && displayMode2.getHeight() == displayMode.getHeight()) {
                  flag = false;
                  break;
               }
            }

            if (!flag) {
               for (DisplayMode displayMode3 : (java.lang.Iterable<DisplayMode>) (set)) {
                  if (displayMode3.getBitsPerPixel() == 32 && displayMode3.getWidth() == displayMode.getWidth() / 2 && displayMode3.getHeight() == displayMode.getHeight() / 2) {
                     desktopDisplayMode = displayMode3;
                     break;
                  }
               }
            }
         }
      }

      Display.setDisplayMode(desktopDisplayMode);
      this.displayWidth = desktopDisplayMode.getWidth();
      this.displayHeight = desktopDisplayMode.getHeight();
   }

   private void updateState9() {
      this.invalidMetadataForManager.applyJsonDeserializerExtension(new BlurJsonDeserializerExtensionAdapter(), SupplementalListEntry.class);
      this.invalidMetadataForManager.applyJsonDeserializerExtension(new FontJsonDeserializerExtensionAdapter(), ListenerAdapter11.class);
      this.invalidMetadataForManager.applyJsonDeserializerExtension(new MetadataSectionJsonDeserializerExtensionAdapter(), BaseListEntry.class);
      this.invalidMetadataForManager.applyJsonDeserializerExtension(new PackFormatJsonDeserializerExtensionAdapter(), AuxiliaryIChatComponentEntry.class);
      this.invalidMetadataForManager.applyJsonDeserializerExtension(new LanguageJsonDeserializerExtensionAdapter(), AuxiliaryListenerAdapter7.class);
   }

   public void updateState10() {
      if (this.inGameHasFocus) {
         KeyBinding.resetKeyBindingArrayAndHash();
         this.inGameHasFocus = false;
         this.mouseHelper.updateState();
      }
   }

   private void updateState17() throws LWJGLException {
      if (this.state5) {
         Display.setFullscreen(true);
         DisplayMode displayMode = Display.getDisplayMode();
         this.displayWidth = Math.max(1, displayMode.getWidth());
         this.displayHeight = Math.max(1, displayMode.getHeight());
      } else {
         Display.setDisplayMode(new DisplayMode(this.displayWidth, this.displayHeight));
      }
   }

   public void updateState15() {
      this.enabled17 = true;

      try {
         this.updateState19();
      } catch (Throwable throwable) {
         CrashReport crashReport2 = CrashReport.resolveCrashReport(throwable, "Initializing game");
         crashReport2.resolveCrashReportCategory("Initialization");
         CrashReport crashReport3 = this.resolveCrashReport(crashReport2);
         this.applyCrashReportByCrashReport(crashReport3);
         return;
      }

      try {
         while (this.enabled17) {
            if (this.enabled8 && this.crashReport != null) {
               CrashReport crashReport4 = this.crashReport;
               this.applyCrashReportByCrashReport(crashReport4);
            } else {
               try {
                  this.updateState3();
               } catch (OutOfMemoryError outOfMemoryError) {
                  this.updateState7();
                  AdvancedGuiScreenAdapter advancedGuiScreenAdapter = new AdvancedGuiScreenAdapter();
                  this.displayGuiScreen(advancedGuiScreenAdapter);
                  System.gc();
               }
            }
         }

         return;
      } catch (ErrorAdapter exception) {
      } catch (SecondaryRuntimeExceptionAdapter exception2) {
         CrashReport crashReport5 = exception2.getCrashReport();
         this.resolveCrashReport(crashReport5);
         this.updateState7();
         logger.fatal("Reported exception thrown!", (Throwable)exception2);
         CrashReport crashReport6 = exception2.getCrashReport();
         this.applyCrashReportByCrashReport(crashReport6);
      } catch (Throwable throwable2) {
         CrashReport crashReport7 = new CrashReport("Unexpected error", throwable2);
         crashReport7 = this.resolveCrashReport(crashReport7);
         this.updateState7();
         logger.fatal("Unreported exception thrown!", throwable2);
         this.applyCrashReportByCrashReport(crashReport7);
      } finally {
         this.updateState();
      }
   }

   public void updateState16() {
      int count9 = 0;
      int count10 = 737217537;

      try {
         this.state5 = !this.state5;
         this.gameSettings.state81 = this.state5;
         if (this.state5) {
            this.updateState20();
            this.displayWidth = Display.getDisplayMode().getWidth();
            this.displayHeight = Display.getDisplayMode().getHeight();
            if (this.displayWidth <= 0) {
               this.displayWidth = 1;
            }

            if (this.displayHeight <= 0) {
               this.displayHeight = 1;
            }
         } else {
            Display.setDisplayMode(new DisplayMode(this.count7, this.count3));
            this.displayWidth = this.count7;
            this.displayHeight = this.count3;
            if (this.displayWidth <= 0) {
               this.displayWidth = 1;
            }

            if (this.displayHeight <= 0) {
               this.displayHeight = 1;
            }
         }

         int displayHeight2 = this.displayHeight;
         int displayWidth2 = this.displayWidth;
         TertiaryItemCallback.applyIndex3(displayWidth2, displayHeight2);
         if (this.currentScreen != null) {
            count9 = this.displayHeight;
            count10 = this.displayWidth;
            this.applyIndex(count10, count9);
         } else {
            this.updateState4();
         }

         Display.setFullscreen(this.state5);
         Display.setVSyncEnabled(this.gameSettings.state26);
         this.updateState6();
      } catch (Exception exception) {
         logger.error("Couldn't toggle fullscreen", (Throwable)exception);
      }
   }

   private void updateState19() throws LWJGLException, IOException {
      FoundMouseManager.foundMouseManager.updateState2();
      GameSettings gameSettings2 = new GameSettings(this, this.mcDataDir);
      this.gameSettings = gameSettings2;
      KeyBinding.updateMovementKeyStates();
      this.list2.add(this.iReloadableResourceManager);
      this.updateState22();
      if (this.gameSettings.indexOffset73 > 0 && this.gameSettings.indexOffset65 > 0) {
         this.displayWidth = this.gameSettings.indexOffset65;
         this.displayHeight = this.gameSettings.indexOffset73;
      }

      logger.info("LWJGL Version: " + Sys.getVersion());
      this.setIngameFocus(false);
      this.updateState17();
      this.updateState23();
      Client.getClient().getClass();

      try {
         if (HolidayHelper.getClientSettingsChristmasType() == ClientSettingsChristmasType.HALLOWEEN) {
            String text = "sounds/halloween.mp3";
            this.audioClipPlayer.applyTextByText(text);
            double offset = 0.6;
            this.audioClipPlayer.applyDouble(offset);
            this.audioClipPlayer.processAction(10);
         } else if (HolidayHelper.getClientSettingsChristmasType() == ClientSettingsChristmasType.FIRST_APRIL) {
            String text2 = "sounds/start.mp3";
            this.audioClipPlayer.applyTextByText(text2);
            double offset2 = 1.0;
            this.audioClipPlayer.applyDouble(offset2);
            this.audioClipPlayer.processAction(10);
         } else if (HolidayHelper.getClientSettingsChristmasType() == ClientSettingsChristmasType.CHRISTMAS_24) {
            String text3 = "sounds/christmas24.mp3";
            this.audioClipPlayer.applyTextByText(text3);
            double offset3 = 1.0;
            this.audioClipPlayer.applyDouble(offset3);
            this.audioClipPlayer.processAction(10);
         } else if (HolidayHelper.getClientSettingsChristmasType() == ClientSettingsChristmasType.CHRISTMAS_25) {
            String text4 = "sounds/christmas25.mp3";
            this.audioClipPlayer.applyTextByText(text4);
            double offset4 = 1.0;
            this.audioClipPlayer.applyDouble(offset4);
            this.audioClipPlayer.processAction(10);
         } else if (HolidayHelper.getClientSettingsChristmasType() == ClientSettingsChristmasType.CHRISTMAS_26) {
            String text5 = "sounds/christmas26.mp3";
            this.audioClipPlayer.applyTextByText(text5);
            double offset5 = 1.0;
            this.audioClipPlayer.applyDouble(offset5);
            this.audioClipPlayer.processAction(10);
         } else if (HolidayHelper.getClientSettingsChristmasType() == ClientSettingsChristmasType.MINIMI) {
            String text6 = "sounds/hackersong.mp3";
            this.audioClipPlayer.applyTextByText(text6);
            double offset6 = 1.0;
            this.audioClipPlayer.applyDouble(offset6);
            this.audioClipPlayer.processAction(10);
         } else {
            File file3 = new File(OnlineConfigsManager.text + "/sounds");
            ConfigManager.applyString2(OnlineConfigsManager.text + "/sounds");
            File[] fileArray = file3.listFiles();
            if (fileArray != null) {
               for (File file4 : fileArray) {
                  if (file4.getName().split("\\.")[0].equals("start")) {
                     AudioClipPlayer audioClipPlayer2 = this.audioClipPlayer;
                     String absolutePath = file4.getAbsolutePath();
                     audioClipPlayer2.applyText(absolutePath);
                     double offset7 = 0.6;
                     this.audioClipPlayer.applyDouble(offset7);
                     this.audioClipPlayer.processAction(10);
                     break;
                  }
               }
            }
         }
      } catch (Exception exception) {
      }

      OpenGlHelper.updateState();
      StencilFramebuffer stencilFramebuffer = new StencilFramebuffer(this.displayWidth, this.displayHeight, true);
      this.framebuffer = stencilFramebuffer;
      this.framebuffer.applyFloat(0.0F, 0.0F, 0.0F, 0.0F);
      this.updateState9();
      ResourceLanguageManager resourceLanguageManager2 = new ResourceLanguageManager(
         this.file2, new File(this.mcDataDir, "server-resource-packs"), this.iReloadableResourceManager, this.invalidMetadataForManager, this.gameSettings
      );
      this.resourceLanguageManager = resourceLanguageManager2;
      ReloadingResourceManagerHolder reloadingResourceManagerHolder = new ReloadingResourceManagerHolder(this.invalidMetadataForManager);
      this.listener69Extension = reloadingResourceManagerHolder;
      LanguageManager languageManager2 = new LanguageManager(this.invalidMetadataForManager, this.gameSettings.text93);
      this.languageManager = languageManager2;
      this.listener69Extension.applyIResourceManagerReloadList(this.languageManager);
      this.updateState11();
      TextureManager textureManager2 = new TextureManager(this.listener69Extension);
      this.textureManager = textureManager2;
      this.listener69Extension.applyIResourceManagerReloadList(this.textureManager);
      TextureManager textureManager3 = this.textureManager;
      this.applyTextureManager(textureManager3);
      SkinsManager skinsManager2 = new SkinsManager(this.textureManager, new File(this.file, "skins"), this.minecraftSessionService);
      this.skinsManager = skinsManager2;
      LevelDatISaveFormatAdapter levelDatISaveFormatAdapter = new LevelDatISaveFormatAdapter(new File(this.mcDataDir, "saves"));
      this.iSaveFormat = levelDatISaveFormatAdapter;
      this.mcSoundHandler = new SoundHandler(this.listener69Extension, this.gameSettings);
      this.listener69Extension.applyIResourceManagerReloadList(this.mcSoundHandler);
      MusicTicker musicTicker2 = new MusicTicker(this);
      this.musicTicker = musicTicker2;
      FontRenderer fontRenderer2 = new FontRenderer(this.gameSettings, new ResourceLocation("textures/font/ascii.png"), this.textureManager, false);
      this.fontRendererObj = fontRenderer2;
      if (this.gameSettings.text93 != null) {
         this.fontRendererObj.setState(this.isActive3());
         this.fontRendererObj.setState1(this.languageManager.isActive());
      }

      FontRenderer fontRenderer3 = new FontRenderer(this.gameSettings, new ResourceLocation("textures/font/ascii_sga.png"), this.textureManager, false);
      this.fontRenderer = fontRenderer3;
      this.listener69Extension.applyIResourceManagerReloadList(this.fontRendererObj);
      this.listener69Extension.applyIResourceManagerReloadList(this.fontRenderer);
      this.listener69Extension.applyIResourceManagerReloadList(new TexturesColormapGrassPngManager());
      this.listener69Extension.applyIResourceManagerReloadList(new ListenerAdapter24());
      AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter31.withPostUpdateSubscriber(new ErrorManager(this));
      MouseHelper mouseHelper2 = new MouseHelper();
      this.mouseHelper = mouseHelper2;
      this.applyText("Pre startup");
      GlStateManager.disableTexture2D();
      GlStateManager.glShadeModel(7425);
      GlStateManager.glClearDepth(1.0);
      GlStateManager.disableDepth();
      GlStateManager.glDepthFunc(515);
      GlStateManager.applyState14();
      GlStateManager.alphaFunc(516, 0.1F);
      GlStateManager.glCullFace(1029);
      GlStateManager.matrixMode(5889);
      GlStateManager.loadIdentity();
      GlStateManager.matrixMode(5888);
      this.applyText("Startup");
      ListenerAdapter27 listenerAdapter = new ListenerAdapter27("textures");
      this.listenerAdapter27 = listenerAdapter;
      this.listenerAdapter27.setCount6(this.gameSettings.indexOffset118);
      this.textureManager.matchesResourceLocationByResourceLocation(ListenerAdapter27.resourceLocation, this.listenerAdapter27);
      this.textureManager.applyResourceLocation3(ListenerAdapter27.resourceLocation);
      this.listenerAdapter27.applyFlagByFlag(false, this.gameSettings.indexOffset118 > 0);
      IronDust ironDust2 = new IronDust(this.listenerAdapter27);
      this.ironDust = ironDust2;
      this.listener69Extension.applyIResourceManagerReloadList(this.ironDust);
      RenderItem renderItem2 = new RenderItem(this.textureManager, this.ironDust);
      this.renderItem = renderItem2;
      RenderManager renderManager2 = new RenderManager(this.textureManager, this.renderItem);
      this.renderManager = renderManager2;
      ItemRenderer itemRenderer2 = new ItemRenderer(this);
      this.itemRenderer = itemRenderer2;
      this.listener69Extension.applyIResourceManagerReloadList(this.renderItem);
      EntityRenderer entityRenderer2 = new EntityRenderer(this, this.listener69Extension);
      this.entityRenderer = entityRenderer2;
      this.listener69Extension.applyIResourceManagerReloadList(this.entityRenderer);
      GoldenCrystal goldenCrystal2 = new GoldenCrystal(this.ironDust.getCommonMapEntry(), this.gameSettings);
      this.goldenCrystal = goldenCrystal2;
      this.listener69Extension.applyIResourceManagerReloadList(this.goldenCrystal);
      RenderGlobal renderGlobal2 = new RenderGlobal(this);
      this.renderGlobal = renderGlobal2;
      this.listener69Extension.applyIResourceManagerReloadList(this.renderGlobal);
      GuiAchievement guiAchievement2 = new GuiAchievement(this);
      this.guiAchievement = guiAchievement2;
      GlStateManager.viewport(0, 0, this.displayWidth, this.displayHeight);
      EffectRenderer effectRenderer2 = new EffectRenderer(this.theWorld, this.textureManager);
      this.effectRenderer = effectRenderer2;
      this.applyText("Post startup");
      GuiIngame guiIngame = new GuiIngame(this);
      this.ingameGUI = guiIngame;
      Client clientItem = Client.getClient();
      clientItem.applyChar((char)0, (char)26430, 1556202762);
      Runtime.getRuntime().addShutdownHook(new Thread(Client.getClient()::updateState));
      if (this.labelText != null) {
         GuiConnecting guiConnecting = new GuiConnecting(new GuiMainMenu(), this, this.labelText, this.count);
         this.displayGuiScreen(guiConnecting);
      } else {
         GuiMainMenu guiMainMenu = new GuiMainMenu();
         this.displayGuiScreen(guiMainMenu);
      }

      this.textureManager.applyResourceLocation2(this.resourceLocation);
      this.resourceLocation = null;
      LoadingScreenRenderer loadingScreenRenderer2 = new LoadingScreenRenderer(this);
      this.loadingScreenRenderer = loadingScreenRenderer2;
      if (this.gameSettings.state81 && !this.state5) {
         this.updateState16();
      }

      try {
         Display.setVSyncEnabled(this.gameSettings.state26);
      } catch (OpenGLException openGLException) {
         this.gameSettings.state26 = false;
         this.gameSettings.updateState();
      }

      this.renderGlobal.updateState6();
      SettingsTxtManager.getExtraListener().get();
   }

   public static void updateState13() {
      if (mcClient != null) {
         IntegratedServer integratedServer2 = mcClient.getIntegratedServer();
         if (integratedServer2 != null) {
            integratedServer2.updateState5();
         }
      }
   }

   public void updateState11() {
      ArrayList list3 = Lists.newArrayList(this.list2);

      for (TexturepackiconManager texturepackiconManager : (java.lang.Iterable<TexturepackiconManager>) (this.resourceLanguageManager.copyOfAsList())) {
         list3.add(texturepackiconManager.getPreUpdateProcessor());
      }

      if (this.resourceLanguageManager.getPreUpdateProcessor() != null) {
         list3.add(this.resourceLanguageManager.getPreUpdateProcessor());
      }

      try {
         this.listener69Extension.applyList(list3);
      } catch (RuntimeException exception) {
         logger.info("Caught error stitching, removing all assigned resourcepacks", (Throwable)exception);
         list3.clear();
         list3.addAll(this.list2);
         this.resourceLanguageManager.applyList(Collections.emptyList());
         this.listener69Extension.applyList(list3);
         this.gameSettings.itemList.clear();
         this.gameSettings.itemList2.clear();
         this.gameSettings.updateState();
      }

      this.languageManager.applyList(list3);
      if (this.renderGlobal != null) {
         this.renderGlobal.updateState();
      }
   }

   public final boolean isEnabled14() {
      return this.enabled14;
   }

   public static boolean isActive2() {
      return mcClient == null || !mcClient.gameSettings.state11;
   }

   public boolean isActive3() {
      return this.languageManager.isActiveAsFlag() || this.gameSettings.state87;
   }

   public static boolean isActive5() {
      return mcClient != null && mcClient.gameSettings.state5;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public Minecraft(DiamondBoots diamondBoots) {
      MinecraftTimer minecraftTimer2 = new MinecraftTimer(20.0F);
      this.minecraftTimer = minecraftTimer2;
      PlayerUsageSnooper playerUsageSnooper2 = new PlayerUsageSnooper("client", this, VanillaRunnable.currentTimeMillis());
      this.playerUsageSnooper = playerUsageSnooper2;
      this.indexOffset = 0;
      this.rightClickDelayTimer = 0;
      long timestamp10 = getTimestamp();
      this.timestamp7 = timestamp10;
      this.profiler = new Profiler();
      timestamp10 = System.nanoTime();
      this.timestamp5 = timestamp10;
      this.uNKNOWNManager = new UNKNOWNManager();
      this.timestamp3 = -1L;
      this.invalidMetadataForManager = new InvalidMetadataForManager();
      this.list2 = Lists.newArrayList();
      this.queue2 = Queues.newArrayDeque();
      this.queue = Queues.newArrayDeque();
      this.timestamp9 = 0L;
      this.thread = Thread.currentThread();
      this.enabled17 = true;
      String text = "";
      this.labelText4 = text;
      this.enabled3 = false;
      this.enabled15 = false;
      this.enabled4 = false;
      this.enabled = true;
      this.enabled16 = false;
      timestamp10 = getTimestamp();
      this.timestamp6 = timestamp10;
      this.timestamp4 = -1L;
      String text2 = "root";
      this.labelText2 = text2;
      this.count5 = 0;
      this.enabled11 = false;
      timestamp10 = System.currentTimeMillis();
      this.timestamp8 = timestamp10;
      ArrayListData arrayListData3 = new ArrayListData(40);
      this.arrayListData = arrayListData3;
      ArrayListData arrayListData4 = new ArrayListData(40);
      this.arrayListData2 = arrayListData4;
      this.enabled9 = false;
      this.indexOffset1 = 0;
      this.arrayList = new ArrayList();
      mcClient = this;
      this.mcDataDir = diamondBoots.fileHolder.file3;
      this.file = diamondBoots.fileHolder.file2;
      this.file2 = diamondBoots.fileHolder.file;
      this.labelText3 = diamondBoots.dampenerCollector.labelText;
      this.propertyMap = diamondBoots.sessionEntry.propertyMap2;
      this.iReloadableResourceManager = new IReloadableResourceManager(new ObjectsManager(diamondBoots.fileHolder.file2, diamondBoots.fileHolder.labelText).getMap());
      this.proxy = diamondBoots.sessionEntry.proxy == null ? Proxy.NO_PROXY : diamondBoots.sessionEntry.proxy;
      MinecraftSessionService minecraftSessionService2 = new YggdrasilAuthenticationService(diamondBoots.sessionEntry.proxy, UUID.randomUUID().toString()).createMinecraftSessionService();
      this.minecraftSessionService = minecraftSessionService2;
      Session session2 = diamondBoots.sessionEntry.session;
      this.session = session2;
      logger.info("Setting user: " + this.session.getText1());
      logger.info("(Session ID is " + this.session.getTextAsText() + ")");
      this.enabled14 = diamondBoots.dampenerCollector.enabled;
      this.displayWidth = diamondBoots.providerClientManager.count > 0 ? diamondBoots.providerClientManager.count : 1;
      this.displayHeight = diamondBoots.providerClientManager.count2 > 0 ? diamondBoots.providerClientManager.count2 : 1;
      int count9 = diamondBoots.providerClientManager.count;
      this.count7 = count9;
      count9 = diamondBoots.providerClientManager.count2;
      this.count3 = count9;
      this.state5 = diamondBoots.providerClientManager.enabled2;
      this.enabled2 = isActive6();
      IntegratedServer integratedServer2 = new IntegratedServer(this);
      this.integratedServer = integratedServer2;
      if (diamondBoots.smoothFactory.labelText != null) {
         String labelText5 = diamondBoots.smoothFactory.labelText;
         this.labelText = labelText5;
         count9 = diamondBoots.smoothFactory.count;
         this.count = count9;
      }

      ImageIO.setUseCache(false);
      STDERRManager.updateState3();
   }
}
