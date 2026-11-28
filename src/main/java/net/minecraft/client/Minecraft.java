package net.minecraft.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.IStatStringFormat;
import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.event.events.BlockInteractEvent;
import baritone.api.event.events.PlayerUpdateEvent;
import baritone.api.event.events.TickEvent;
import baritone.api.event.events.WorldEvent;
import baritone.api.event.events.type.EventState;
import baritone.api.utils.IPlayerContext;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.function.BiFunction;
import javax.imageio.ImageIO;
import net.augustus.modules.render.MotionBlur;
import net.augustus.modules.world.NoPlaceDelay;
import net.augustus.events.listeners.LeftClickListener;
import net.augustus.events.EventGameLoop;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.WindowResizeListener;
import net.augustus.render.GpuVendorDetector;
import net.augustus.commands.impl.ClickerCommand;
import net.augustus.utils.HolidayDetector;
import net.augustus.utils.network.PacketFlushMarker;
import net.augustus.events.EventKey;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.BlockPlaceListener;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.file.ClientPaths;
import net.augustus.events.EventClick;
import net.augustus.settings.Setting;
import net.augustus.render.AbstractSkeletonRenderer;
import net.augustus.utils.combat.ProtectedPlayerCheck;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.RawMouseHelper;
import net.augustus.events.ClickButtonType;
import net.augustus.events.listeners.KeyListener;
import net.augustus.discord.DiscordRpcManager;
import net.augustus.utils.render.ResizableFramebuffer;
import net.augustus.events.TimerEventType;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.listeners.TimerListener;
import net.augustus.events.KeyInputSource;
import net.augustus.events.EventBlockPlace;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.events.listeners.GameLoopListener;
import net.augustus.utils.input.RawMouseInputThread;
import net.augustus.utils.rotation.RotationController;
import net.augustus.viaversion.ViaAugustus;
import net.augustus.events.EventTimer;
import net.augustus.events.TickPhase;
import net.augustus.utils.MouseWheelTracker;
import net.augustus.events.BlockPlaceStage;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.events.listeners.DropItemListener;
import net.augustus.Augustus;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.ui.GuiIngameHook;
import net.augustus.utils.WorldLoadWarmup;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.audio.JavaFxAudioPlayer;
import net.augustus.utils.input.ToggleUtil;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.events.listeners.InventoryCloseListener;
import net.augustus.events.listeners.RayCastListener;
import net.augustus.utils.ClientHoliday;
import net.augustus.utils.rotation.Rotation;
import net.augustus.utils.OSUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMemoryErrorScreen;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSleepMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.FoliageColorReloadListener;
import net.minecraft.client.resources.GrassColorReloadListener;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.resources.ResourceIndex;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.client.resources.data.FontMetadataSection;
import net.minecraft.client.resources.data.FontMetadataSectionSerializer;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.client.resources.data.LanguageMetadataSectionSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.client.resources.data.PackMetadataSectionSerializer;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSectionSerializer;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Bootstrap;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.FrameTimer;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MinecraftError;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ScreenShotHelper;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import net.minecraft.util.Util;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.storage.AnvilSaveConverter;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.OpenGLException;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.glu.GLU;

public class Minecraft implements IThreadListener {
   public final JavaFxAudioPlayer audioPlayer;
   public long realElapsedTicks;
   public long gameElapsedTicks;
   public long projectedRealTicks;
   public long projectedGameTicks;
   public float realTotalTicks;
   public float gameTotalTicks;
   private static final Logger LOGGER;
   public static final boolean isRunningOnMac;
   public static byte[] memoryReserve;
   private static final List macDisplayModes;
   private final File fileResourcepacks;
   private final PropertyMap twitchDetails;
   private ServerData currentServerData;
   private TextureManager renderEngine;
   private static Minecraft theMinecraft;
   public PlayerControllerMP playerController;
   private boolean fullscreen;
   private boolean enableGLErrorChecking;
   private boolean hasCrashed;
   private CrashReport crashReporter;
   public int displayWidth;
   public int displayHeight;
   private boolean connectedToRealms;
   public Timer timer;
   private Timer realTimer;
   public WorldClient theWorld;
   public RenderGlobal renderGlobal;
   private RenderManager renderManager;
   private RenderItem renderItem;
   private ItemRenderer itemRenderer;
   public EntityPlayerSP thePlayer;
   private Entity renderViewEntity;
   public Entity pointedEntity;
   public EffectRenderer effectRenderer;
   public Session session;
   public boolean isGamePaused;
   public FontRenderer fontRendererObj;
   public FontRenderer standardGalacticFontRenderer;
   public GuiScreen currentScreen;
   public GuiScreen previousScreen;
   public GuiScreen lastDisplayedScreen;
   public LoadingScreenRenderer loadingScreen;
   public EntityRenderer entityRenderer;
   public int leftClickCounter;
   private int tempDisplayWidth;
   private int tempDisplayHeight;
   private IntegratedServer theIntegratedServer;
   public GuiAchievement guiAchievement;
   public GuiIngameHook ingameGUI;
   public boolean skipRenderWorld;
   public MovingObjectPosition objectMouseOver;
   public MovingObjectPosition lastObjectMouseOver;
   public MovingObjectPosition previousObjectMouseOver;
   public int ticksLookingAtEntity;
   public int ticksNotLookingAtEntity;
   public GameSettings gameSettings;
   public RawMouseHelper mouseHelper;
   public final File mcDataDir;
   private final File fileAssets;
   private final String version;
   private final Proxy proxy;
   private ISaveFormat saveLoader;
   private static int debugFPS;
   public int rightClickDelayTimer;
   private String serverName;
   private int serverPort;
   public boolean inGameHasFocus;
   public long systemTime;
   private int joinPlayerCounter;
   public final FrameTimer frameTimer;
   public long startNanoTime;
   private final boolean java64bit;
   private final boolean isDemo;
   private NetworkManager myNetworkManager;
   private boolean integratedServerIsRunning;
   public final Profiler mcProfiler;
   private long debugCrashKeyPressTime;
   private IReloadableResourceManager mcResourceManager;
   private final IMetadataSerializer metadataSerializer_;
   private final List defaultResourcePacks;
   public final DefaultResourcePack mcDefaultResourcePack;
   private ResourcePackRepository mcResourcePackRepository;
   private LanguageManager mcLanguageManager;
   private Framebuffer framebufferMc;
   public Framebuffer blurFramebuffer;
   private TextureMap textureMapBlocks;
   private SoundHandler mcSoundHandler;
   private MusicTicker mcMusicTicker;
   private ResourceLocation mojangLogo;
   private MinecraftSessionService sessionService;
   private SkinManager skinManager;
   private final Queue scheduledTasks;
   private final Queue priorityScheduledTasks;
   private final Thread mcThread;
   private ModelManager modelManager;
   private BlockRendererDispatcher blockRenderDispatcher;
   public volatile boolean running;
   public String debug;
   public boolean renderChunksMany;
   private boolean wasLeftClicking;
   public long debugUpdateTime;
   public int fpsCounter;
   public long prevFrameTime;
   private String debugProfilerName;
   private int ticksWithoutScreen;
   private boolean skipGameTick;
   private long lastRenderTime;
   private DoubleSampleWindow tickTimeWindow;
   private DoubleSampleWindow frameTimeWindow;
   public boolean ranMultipleTicks;
   private boolean hittingBlockCustom;
   private boolean prevHittingBlockCustom;
   public int remainingTicksInFrame;
   private final ArrayList tickHistory;

private void displayDebugInfo(long l) {
   }

   public void setIngameFocus() {
      Minecraft mc;
      
         boolean isActive = Display.isActive();
         if (!isActive) {
               return;
            }

            mc = this;
isActive = this.inGameHasFocus;

         if (isActive) {
            return;
         }

         this.inGameHasFocus = true;
         this.mouseHelper.grabMouseCursor();
         this.displayGuiScreen((GuiScreen)null);
         mc = this;

      mc.leftClickCounter = (short)10000;
      Augustus.getInstance().getModuleManager().saveMoveKeys.resetInGameTicks();
   }

   public void middleClickMouse() {
      MovingObjectPosition hit = null;
      Block block = null;
      
      Minecraft mc = this;
      if (this.objectMouseOver == null) {
            return;
         }

         ClickListener.onClick(ClickButtonType.MID);
         mc = this;

      byte b;
      int n;
      boolean flag;
      TileEntity tileEntity;
      Object obj;
      b = (byte)((mc.thePlayer.capabilities.isCreativeMode) ? 1 : 0);
            n = 0;
            flag = false;
            tileEntity = null;
            MovingObjectPosition.MovingObjectType typeOfHit = this.objectMouseOver.typeOfHit;
            MovingObjectPosition.MovingObjectType block2 = MovingObjectPosition.MovingObjectType.BLOCK;
if (this.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
BlockPos blockPos = this.objectMouseOver.getBlockPos();
                  Block block3 = this.theWorld.getBlockState(blockPos).getBlock();
                  Block block4 = block3;
                  if (block3.getMaterial() == Material.air) {
                        return;
                     }

                     block4 = block3;

                  obj = block4.getItem(this.theWorld, blockPos);
                  if (obj == null) {
                     return;
                  }

                  byte b2 = b;
if (b != 0) {
b2 = (byte)((GuiScreen.isCtrlKeyDown()) ? 1 : 0);

                     if (b2 != 0) {
                        tileEntity = this.theWorld.getTileEntity(blockPos);
                     }
}

                  boolean flag2 = obj instanceof ItemBlock;
if (flag2) {
block = block3;

flag2 = block3.isFlowerPot();
if (!flag2) {
block = Block.getBlockFromItem((Item)obj);
} else {
block = block3;
}
} else {
block = block3;
}

                  n = block.getDamageValue(this.theWorld, blockPos);
                  flag = ((Item)obj).getHasSubtypes();
} else {
hit = this.objectMouseOver;
typeOfHit = this.objectMouseOver.typeOfHit;
               block2 = MovingObjectPosition.MovingObjectType.ENTITY;

            if (typeOfHit != block2) {
               return;
            }

            hit = this.objectMouseOver;

         if (hit.entityHit == null) {
            return;
         }

         byte b3 = b;
         if (b == 0) {
               return;
            }

            b3 = (byte)((this.objectMouseOver.entityHit instanceof EntityPainting) ? 1 : 0);
if (b3 != 0) {
obj = Items.painting;
} else {
b3 = (byte)((this.objectMouseOver.entityHit instanceof EntityLeashKnot) ? 1 : 0);
if (b3 != 0) {
obj = Items.lead;
} else {
b3 = (byte)((this.objectMouseOver.entityHit instanceof EntityItemFrame) ? 1 : 0);
if (b3 != 0) {
ItemStack displayedItem = ((EntityItemFrame)this.objectMouseOver.entityHit).getDisplayedItem();
                  ItemStack stack = displayedItem;
if (displayedItem == null) {
obj = Items.item_frame;
} else {
stack = displayedItem;

                  obj = stack.getItem();
                  n = displayedItem.getMetadata();
                  flag = true;
}
} else {
b3 = (byte)((this.objectMouseOver.entityHit instanceof EntityMinecart) ? 1 : 0);
if (b3 != 0) {
EntityMinecart entityHit2 = (EntityMinecart)this.objectMouseOver.entityHit;
               switch (entityHit2.getMinecartType()) {
                  case FURNACE:
                     obj = Items.furnace_minecart;
                     break;
                  case CHEST:
                     obj = Items.chest_minecart;
                     break;
                  case TNT:
                     obj = Items.tnt_minecart;
                     break;
                  case HOPPER:
                     obj = Items.hopper_minecart;
                     break;
                  case COMMAND_BLOCK:
                     obj = Items.command_block_minecart;
                     break;
                  default:
                     obj = Items.minecart;
               }
} else {
b3 = (byte)((this.objectMouseOver.entityHit instanceof EntityBoat) ? 1 : 0);
if (b3 != 0) {
obj = Items.boat;
} else {
b3 = (byte)((this.objectMouseOver.entityHit instanceof EntityArmorStand) ? 1 : 0);
if (b3 != 0) {
obj = Items.armor_stand;
} else {
obj = Items.spawn_egg;
         n = EntityList.getEntityID(this.objectMouseOver.entityHit);
         flag = true;
         if (!EntityList.entityEggs.containsKey(n)) {
            return;
         }
}
}
}
}
}
}
}

      InventoryPlayer inventoryPlayer;
      inventoryPlayer = this.thePlayer.inventory;
if (tileEntity == null) {
inventoryPlayer.setCurrentItem((Item)obj, n, flag, (b != 0));
} else {
ItemStack stack2 = this.storeTEInStack((Item)obj, n, tileEntity);
         inventoryPlayer.setInventorySlotContents(inventoryPlayer.currentItem, stack2);
}

      int n2 = b;
      if (b == 0) {
            return;
         }

         n2 = this.thePlayer.inventoryContainer.inventorySlots.size() - 9 + inventoryPlayer.currentItem;

      int n3 = n2;
      this.playerController.sendSlotPacket(inventoryPlayer.getStackInSlot(inventoryPlayer.currentItem), n3);
   }

   public void setConnectedToRealms(boolean flag) {
      this.connectedToRealms = flag;
   }

   public int getLimitFramerate() {
      boolean flag;
      byte b;
      
      byte b2 = (byte)((Display.isActive()) ? 1 : 0);
      b = (byte)((Augustus.getInstance().getModuleManager().clientSettings.removeFramerateLimitInScreens.getValue()) ? 1 : 0);
      if (b != 0) {
            b = b2;

if (b2 != 0) {
               return this.gameSettings.limitFramerate;
            }
         }

         b = b2;

      if (b == 0) {
b = (byte)((Augustus.getInstance().getModuleManager().clientSettings.limitFrameRateIfScreenNotFocused.getValue()) ? 1 : 0);

if (b != 0) {
            return 30;
         }
}

      Minecraft mc = this;
      if (this.theWorld == null) {

if (this.currentScreen != null) {
               return 30;
            }
         }

         mc = this;

      return mc.gameSettings.limitFramerate;
   }

   public void updateDisplay() {
      this.mcProfiler.startSection("display_update");
      Display.update();
      this.mcProfiler.endSection();
      this.checkWindowResize();
   }

   public static long getSystemTime() {
      return Sys.getTime() * 1000L / Sys.getTimerResolution();
   }

   public synchronized void setSession(Session session2) {
      this.session = session2;
   }

   public int getRightClickDelayTimer() {
      return this.rightClickDelayTimer;
   }

   public boolean isIntegratedServerRunning() {
      return this.integratedServerIsRunning;
   }

   public IResourceManager getResourceManager() {
      return this.mcResourceManager;
   }

   public ISaveFormat getSaveLoader() {
      return this.saveLoader;
   }

   public void refreshResources() {
      RuntimeException e = null;
      ArrayList list = Lists.newArrayList(this.defaultResourcePacks);
      
      Iterator it = this.mcResourcePackRepository.getRepositoryEntries().iterator();

while (true) {
         if (it.hasNext()) {
            list.add(((ResourcePackRepository.Entry)it.next()).getResourcePack());

continue;
         }

         if (this.mcResourcePackRepository.getResourcePackInstance() != null) {
            list.add(this.mcResourcePackRepository.getResourcePackInstance());
         }

         try {
            this.mcResourceManager.reloadResources(list);
            break;
         } catch (RuntimeException e3) {
            LOGGER.info("Caught error stitching, removing all assigned resourcepacks", e3);
         }
         list.clear();
         list.addAll(this.defaultResourcePacks);
         this.mcResourcePackRepository.setRepositories(Collections.emptyList());
         this.mcResourceManager.reloadResources(list);
         this.gameSettings.resourcePacks.clear();
         this.gameSettings.incompatibleResourcePacks.clear();
         this.gameSettings.saveOptions();
         break;
      }

      this.mcLanguageManager.parseLanguageMetadata(list);
      RenderGlobal renderGlobal2 = this.renderGlobal;
      if (this.renderGlobal == null) {
            return;
         }

         renderGlobal2 = this.renderGlobal;

      renderGlobal2.loadRenderers();
   }

   private ItemStack storeTEInStack(Item item, int n, TileEntity tileEntity) {
      ItemStack stack;
      NBTTagCompound tag;
      stack = new ItemStack(item, 1, n);
         
         NBTTagCompound tag2 = new NBTTagCompound();
         
         tileEntity.writeToNBT(tag2);
         if (item == Items.skull) {
               tag = tag2;

if (tag2.hasKey("Owner")) {
                  NBTTagCompound compoundTag = tag2.getCompoundTag("Owner");
                  NBTTagCompound tag3 = new NBTTagCompound();
                  tag3.setTag("SkullOwner", compoundTag);
                  stack.setTagCompound(tag3);
                  return stack;
               }
            }

            stack.setTagInfo("BlockEntityTag", tag2);

         tag = new NBTTagCompound();

      NBTTagCompound tag4 = tag;
      NBTTagList tagList = new NBTTagList();
      tagList.appendTag(new NBTTagString("(+NBT)"));
      tag4.setTag("Lore", tagList);
      stack.setTagInfo("display", tag4);
      return stack;
   }

   public boolean isFullScreen() {
      return this.fullscreen;
   }

   public void runTick(int n, int n2) throws IOException {
      GuiScreen screen = null, screen2 = null;
      Minecraft mc = null, mc2 = null, mc3 = null, mc4 = null, mc5 = null, mc6 = null, mc7 = null, mc8 = null, mc9 = null, mc10 = null, mc11 = null, mc12 = null, mc13 = null, mc14 = null, mc15 = null;
      EntityPlayerSP player = null, player2 = null;
      TickEvent.Type type = null, type2 = null;
      boolean flag = false, flag2 = false;
      int n3 = 0, n4 = 0, n5 = 0, n6 = 0, n7 = 0, n8 = 0, n9 = 0;
      int n10 = (ClientManagers.moduleManager.packetHandler.eventToHandlePacket.getSelectedValue().equals("Tick")) ? 1 : 0;
      if (n10 != 0) {
            this.runScheduledTasks();
         }

         this.remainingTicksInFrame = n;
         n10 = this.rightClickDelayTimer;

      if (n10 > 0) {
            this.rightClickDelayTimer = this.rightClickDelayTimer - 1;
         }

         this.mcProfiler.startSection("gui");
         n10 = (this.isGamePaused) ? 1 : 0;

      if (n10 == 0) {
            this.ingameGUI.updateTick();
         }

         this.mcProfiler.endSection();
         this.previousObjectMouseOver = new MovingObjectPosition(this.lastObjectMouseOver);
         n10 = n;
         if (this.theWorld != null && this.thePlayer != null) {
            if (n2 == 0 || ClientManagers.moduleManager.rotationHandler.rotationMode.getSelectedValue().equals("Tick")) {
               RotationController rotHandler = Augustus.getInstance().getRotationController();
               rotHandler.beginRotationTick(
                  new Rotation(this.thePlayer.rotationYaw, this.thePlayer.rotationPitch),
                  this.getTimer().elapsedTicks - n
               );
               TickListener.postTick(TickPhase.EARLY);
               rotHandler.applyServerRotation();
               InventoryCloseListener.postInventoryClose();
            }
         }
         this.entityRenderer.getMouseOver(1.0F, false);
         if (Augustus.getInstance().getModuleManager().smartRaytrace.isToggled()) {
            Augustus.getInstance().getModuleManager().smartRaytrace.updateSmartRaytrace();
         }
         if (this.theWorld != null && this.thePlayer != null) {
            RayCastListener.postRayCast();
            this.lastObjectMouseOver = new MovingObjectPosition(this.objectMouseOver);
         }
         boolean hitLiving = this.objectMouseOver != null
            && this.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY
            && this.objectMouseOver.entityHit instanceof net.minecraft.entity.EntityLivingBase;
         this.ticksLookingAtEntity = hitLiving ? this.ticksLookingAtEntity + 1 : 0;
         this.ticksNotLookingAtEntity = hitLiving ? 0 : this.ticksNotLookingAtEntity + 1;
         this.mcProfiler.startSection("gameMode");
         if (!this.isGamePaused && this.theWorld != null) {
            this.playerController.updateController();
         }
         this.mcProfiler.endStartSection("textures");
         if (!this.isGamePaused) {
            this.getTextureManager().tick();
         }

      if (this.currentScreen == null && this.thePlayer != null) {
         if (this.thePlayer.getCurrentHealth() <= 0.0F) {
            this.displayGuiScreen(null);
         } else if (this.thePlayer.isPlayerSleeping() && this.theWorld != null) {
            this.displayGuiScreen(new GuiSleepMP());
         }
      } else if (this.currentScreen != null && this.currentScreen instanceof GuiSleepMP && !this.thePlayer.isPlayerSleeping()) {
         this.displayGuiScreen(null);
      }

            mc2 = this;

         screen = mc2.currentScreen;

      if (screen != null) {
boolean isToggled = ClientManagers.moduleManager.killAura.isToggled();
if (isToggled) {
isToggled = ClientManagers.moduleManager.killAura.inInv.getValue();
if (!isToggled) {
this.leftClickCounter = (short)10000;
}
} else {
this.leftClickCounter = (short)10000;
}
}

      BiFunction biFunction;
      biFunction = TickEvent.createNextProvider();
                                       if (Augustus.getInstance().getModuleManager().baritone.isToggled()) {
                                          for (IBaritone baritone : (Iterable<IBaritone>)(BaritoneAPI.getProvider().getAllBaritones())) {
                                             IPlayerContext playerContext = baritone.getPlayerContext();
                                                   player = playerContext.player();
if (player != null) {
playerContext = baritone.getPlayerContext();
if (playerContext.world() != null) {
type = TickEvent.Type.IN;
} else {
type = TickEvent.Type.OUT;
}
} else {
type = TickEvent.Type.OUT;
}

                                             TickEvent.Type type3 = type;
                                             baritone.getGameEventHandler().onTick((TickEvent)biFunction.apply(EventState.PRE, type3));
                                             
                                          }
                                       }

                                       screen2 = this.currentScreen;
                                          if (this.currentScreen != null) {
                                                try {
                                                   this.currentScreen.handleInput();
                                                } catch (Throwable e) {
                                                   CrashReport crashReport = CrashReport.makeCrashReport(e, "Updating screen events");
                                                   crashReport.makeCategory("Affected screen").addCrashSectionCallable("Screen name", new Minecraft.ValueCallable(this));
                                                   throw new ReportedException(crashReport);
                                                }

                                                screen2 = this.currentScreen;

if (this.currentScreen != null) {
                                                   try {
                                                      this.currentScreen.updateScreen();
                                                   } catch (Throwable e2) {
                                                      CrashReport crashReport2 = CrashReport.makeCrashReport(e2, "Ticking screen");
                                                      crashReport2.makeCategory("Affected screen").addCrashSectionCallable("Screen name", new Minecraft.ValueCallable2(this));
                                                      throw new ReportedException(crashReport2);
                                                   }
                                                }
                                             }

                                             mc3 = this;

screen2 = this.currentScreen;

                                          if (screen2 == null || this.shouldAllowUserInput(this.currentScreen)) {
                                       this.mcProfiler.endStartSection("mouse");
                                          Augustus.getInstance().getModuleManager().saveMoveKeys.restoreKeyStates();
                                          n10 = (ClickerCommand.CLICK_PATTERN_RECORDER.isRunning()) ? 1 : 0;
                                          if (n10 == 0) {
                                                while (Mouse.next()) {
                                                   int eventButton = Mouse.getEventButton();
                                                   ToggleUtil.onMouseInput(false);
                                                   n10 = (KeyListener.postKey(eventButton, true, Mouse.getEventButtonState(), KeyInputSource.MOUSE).isCancelled()) ? 1 : 0;

if (n10 != 0) {
                                                         continue;
                                                      }

                                                      KeyBinding.setKeyBindState(eventButton - 100, Mouse.getEventButtonState());
                                                      n10 = (Mouse.getEventButtonState()) ? 1 : 0;

                                                   if (n10 != 0) {
                                                      if (this.thePlayer.isSpectator() && eventButton == 2) {
                                                         this.ingameGUI.getSpectatorGui().onMiddleClick();
                                                      } else {
                                                         KeyBinding.onTick(eventButton - 100);
                                                      }
                                                   }

                                                      n10 = (this.gameSettings.keyBindTogglePerspective.isPressed()) ? 1 : 0;

                                                   if (n10 != 0) {
this.gameSettings.thirdPersonView++;
                                                         n10 = this.gameSettings.thirdPersonView;

                                                      if (n10 > 2) {
                                                            this.gameSettings.thirdPersonView = 0;
                                                         }

                                                         n10 = this.gameSettings.thirdPersonView;

                                                      if (n10 == 0) {
this.entityRenderer.loadEntityShader(this.getRenderViewEntity());
} else {
mc4 = this;

n10 = this.gameSettings.thirdPersonView;

                                                            if (n10 == 1) {
                                                               this.entityRenderer.loadEntityShader((Entity)null);
                                                            }
}

                                                         mc4 = this;

                                                      mc4.renderGlobal.setDisplayListEntitiesDirty();
}

                                                   long l = getSystemTime() - this.systemTime;
                                                      long l2;
                                                      n10 = (l2 = l - 200L) == 0L ? 0 : (l2 < 0L ? -1 : 1);
                                                      if (n10 > 0) {
                                                            continue;
                                                         }

                                                         n10 = Mouse.getEventDWheel();

                                                      int n14;
                                                         n14 = n10;
                                                            n10 = n14;
if (n14 != 0) {
player2 = this.thePlayer;
n10 = (this.thePlayer.isSpectator()) ? 1 : 0;
if (n10 != 0) {
n10 = n14;
                                                               n10 = n14 < 0 ? -1 : 1;

                                                               n14 = n10;
                                                               Minecraft mc16 = this;
if (this.ingameGUI.getSpectatorGui().isMenuActive()) {
this.ingameGUI.getSpectatorGui().onMouseScroll(-n14);
} else {
mc16 = this;

                                                               float f2 = MathHelper.clamp_float(
                                                                  mc16.thePlayer.capabilities.getFlySpeed() + (float)n14 * 0.005F, 0.0F, 0.2F
                                                               );
                                                               this.thePlayer.capabilities.setFlySpeed(f2);
}
} else {
player2 = this.thePlayer;

                                                         player2.inventory.changeCurrentItem(n14);
}
}

                                                      GuiScreen currentScreen3 = this.currentScreen;
                                                      if (this.currentScreen == null) {
                                                            boolean inGameHasFocus2 = this.inGameHasFocus;
                                                            if (this.inGameHasFocus) {
                                                                  continue;
                                                               }

                                                               inGameHasFocus2 = Mouse.getEventButtonState();

                                                            if (!inGameHasFocus2) {
                                                               continue;
                                                            }

                                                            this.setIngameFocus();
                                                            continue;
                                                         }

                                                         currentScreen3 = this.currentScreen;

                                                      currentScreen3.handleMouseInput();

}
                                             }

                                             mc5 = this;

n10 = this.leftClickCounter;

                                          if (n10 > 0) {
                                             this.leftClickCounter = this.leftClickCounter - 1;
                                          }

                                          mc5 = this;

                                       mc5.mcProfiler.endStartSection("keyboard");

                                       while (Keyboard.next()) {
                                          n10 = Keyboard.getEventKey();

n10 = n10 == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();

                                             int n16;
                                             n16 = n10;
                                             boolean eventKeyState = Keyboard.getEventKeyState();
                                             n10 = (KeyListener.postKey(n16, false, Keyboard.getEventKeyState(), KeyInputSource.KEYBOARD).isCancelled()) ? 1 : 0;
                                             if (n10 == 0) {
                                                   KeyBinding.setKeyBindState(n16, eventKeyState);
                                                   n10 = (Keyboard.getEventKeyState()) ? 1 : 0;

if (n10 != 0) {
                                                      KeyBinding.onTick(n16);
                                                   }
                                                }

                                                long l3;
                                                n10 = (l3 = this.debugCrashKeyPressTime - 0L) == 0L ? 0 : (l3 < 0L ? -1 : 1);

                                             if (this.debugCrashKeyPressTime > 0L) {
                                                if (getSystemTime() - this.debugCrashKeyPressTime >= 6000L) {
                                                   throw new ReportedException(new CrashReport("Manually triggered debug crash", new Throwable()));
                                                }
                                                if (!Keyboard.isKeyDown(46) || !Keyboard.isKeyDown(61)) {
                                                   this.debugCrashKeyPressTime = -1L;
                                                }
                                             } else if (Keyboard.isKeyDown(46) && Keyboard.isKeyDown(61)) {
                                                this.debugCrashKeyPressTime = getSystemTime();
                                             }

                                             this.dispatchKeypresses();
                                             if (this.currentScreen == null) {
                                                ToggleUtil.onKeyboardInput(false);
                                             }

                                             n10 = (Keyboard.getEventKeyState()) ? 1 : 0;
                                             if (n10 == 0) {
                                                   continue;
                                                }

                                                n10 = n16;

                                             if (n10 == 62) {
                                                   mc6 = this;

if (this.entityRenderer != null) {
                                                      this.entityRenderer.switchUseShader();
                                                   }
                                                }

                                                mc6 = this;

                                             GuiScreen currentScreen4 = mc6.currentScreen;
if (mc6.currentScreen != null) {
currentScreen4 = this.currentScreen;

                                                      currentScreen4.handleKeyboardInput();
} else {
n10 = n16;
                                                   byte b = 1;
                                                   if (n16 == 1) {
                                                         this.displayInGameMenu();
                                                      }

                                                      n10 = n16;
                                                      b = 32;

                                                   if (n10 == b) {
                                                            n10 = (Keyboard.isKeyDown(61)) ? 1 : 0;

if (n10 != 0) {
GuiIngameHook ingameGUI2 = this.ingameGUI;
if (this.ingameGUI != null) {
ingameGUI2 = this.ingameGUI;

                                                               ingameGUI2.getChatGUI().clearChatMessages();
}
}
                                                         }

                                                         n10 = n16;

                                                      b = 31;

                                                   if (n10 == b) {
                                                            n10 = (Keyboard.isKeyDown(61)) ? 1 : 0;

if (n10 != 0) {
                                                               this.refreshResources();
                                                            }
                                                         }

                                                         n10 = n16;

                                                      b = 17;

                                                   if (n10 == b && Keyboard.isKeyDown(61)) {
                                                      }

                                                      n10 = n16;
                                                      b = 18;

                                                   if (n10 == b && Keyboard.isKeyDown(61)) {
                                                      }

                                                      n10 = n16;
                                                      b = 47;

                                                   if (n10 == b && Keyboard.isKeyDown(61)) {
                                                      }

                                                      n10 = n16;
                                                      b = 38;

                                                   if (n10 == b && Keyboard.isKeyDown(61)) {
                                                      }

                                                      n10 = n16;
                                                      b = 22;

                                                   if (n10 == b && Keyboard.isKeyDown(61)) {
                                                      }

                                                      n10 = n16;
                                                      b = 20;

                                                   if (n10 == b) {
                                                            n10 = (Keyboard.isKeyDown(61)) ? 1 : 0;

if (n10 != 0) {
                                                               this.refreshResources();
                                                            }
                                                         }

                                                         n10 = n16;

                                                      b = 33;

                                                   if (n10 == b) {
                                                            n10 = (Keyboard.isKeyDown(61)) ? 1 : 0;

if (n10 != 0) {
                                                               GameSettings gameSettings2 = this.gameSettings;
                                                               GameSettings.Options renderdistance = GameSettings.Options.RENDER_DISTANCE;
                                                               int n17 = (GuiScreen.isShiftKeyDown()) ? 1 : 0;
                                                               n17 = n17 != 0 ? -1 : 1;

                                                               gameSettings2.setOptionValue(renderdistance, n17);
                                                            }
                                                         }

                                                         n10 = n16;

                                                      b = 30;

                                                   if (n10 == b) {
                                                            n10 = (Keyboard.isKeyDown(61)) ? 1 : 0;

if (n10 != 0) {
                                                               this.renderGlobal.loadRenderers();
                                                            }
                                                         }

                                                         n10 = n16;

                                                      b = 35;

                                                   if (n10 == b) {
                                                            n10 = (Keyboard.isKeyDown(61)) ? 1 : 0;

if (n10 != 0) {
                                                               boolean advancedItemTooltips = this.gameSettings.advancedItemTooltips;
                                                               advancedItemTooltips = !this.gameSettings.advancedItemTooltips;

                                                               this.gameSettings.advancedItemTooltips = advancedItemTooltips;
                                                               this.gameSettings.saveOptions();
                                                            }
                                                         }

                                                         n10 = n16;

                                                      b = 48;

                                                   if (n10 == b) {
                                                            n10 = (Keyboard.isKeyDown(61)) ? 1 : 0;

if (n10 != 0) {
                                                               RenderManager renderManager2 = this.renderManager;
                                                               boolean isDebugBoundingBox = this.renderManager.isDebugBoundingBox();
                                                               isDebugBoundingBox = !isDebugBoundingBox;

                                                               renderManager2.setDebugBoundingBox(isDebugBoundingBox);
                                                            }
                                                         }

                                                         n10 = n16;

                                                      b = 25;

                                                   if (n10 == b) {
                                                            n10 = (Keyboard.isKeyDown(61)) ? 1 : 0;

if (n10 != 0) {
                                                               boolean pauseOnLostFocus = this.gameSettings.pauseOnLostFocus;
                                                               pauseOnLostFocus = !this.gameSettings.pauseOnLostFocus;

                                                               this.gameSettings.pauseOnLostFocus = pauseOnLostFocus;
                                                               this.gameSettings.saveOptions();
                                                            }
                                                         }

                                                         n10 = n16;

                                                      b = 59;

                                                   if (n10 == b) {
                                                            boolean hideGUI = this.gameSettings.hideGUI;
                                                            hideGUI = !this.gameSettings.hideGUI;

                                                            this.gameSettings.hideGUI = hideGUI;
                                                         }

                                                         n10 = n16;
b = 61;

                                                      if (n10 == b) {
                                                         boolean showDebugInfo = this.gameSettings.showDebugInfo;
                                                         showDebugInfo = !this.gameSettings.showDebugInfo;

                                                         this.gameSettings.showDebugInfo = showDebugInfo;
                                                         this.gameSettings.showDebugProfilerChart = GuiScreen.isShiftKeyDown();
                                                         this.gameSettings.showLagometer = GuiScreen.isAltKeyDown();
                                                      }

                                                      n10 = (this.gameSettings.keyBindTogglePerspective.isPressed()) ? 1 : 0;

                                                   if (n10 != 0) {
                                                         this.gameSettings.thirdPersonView++;
                                                         n10 = this.gameSettings.thirdPersonView;
                                                         if (this.gameSettings.thirdPersonView > 2) {
                                                               this.gameSettings.thirdPersonView = 0;
                                                            }

                                                            n10 = this.gameSettings.thirdPersonView;

                                                         if (n10 == 0) {
this.entityRenderer.loadEntityShader(this.getRenderViewEntity());
} else {
mc7 = this;

n10 = this.gameSettings.thirdPersonView;

                                                               if (n10 == 1) {
                                                                  this.entityRenderer.loadEntityShader((Entity)null);
                                                               }
}

                                                            mc7 = this;

                                                         mc7.renderGlobal.setDisplayListEntitiesDirty();
                                                      }

                                                      n10 = (this.gameSettings.keyBindSmoothCamera.isPressed()) ? 1 : 0;

if (n10 != 0) {
                                                      boolean smoothCamera = this.gameSettings.smoothCamera;
                                                      smoothCamera = !this.gameSettings.smoothCamera;

                                                      this.gameSettings.smoothCamera = smoothCamera;
                                                   }
}

                                                n10 = (this.gameSettings.showDebugInfo) ? 1 : 0;

                                             if (n10 == 0) {
                                                   continue;
                                                }

                                                n10 = (this.gameSettings.showDebugProfilerChart) ? 1 : 0;

                                             if (n10 == 0) {
                                                   continue;
                                                }

                                                n10 = n16;

                                             if (n10 == 11) {
                                                   this.updateDebugProfilerName((byte)0);
                                                }

                                                n10 = 0;

                                             int n18 = n10;

                                             while (n18 < 9) {
                                                n10 = n16;
                                                n9 = 2 + n18;

if (n16 == n9) {
                                                   this.updateDebugProfilerName(n18 + 1);
                                                }

                                                n18++;
                                                
                                             }

}

                                       mc8 = this;

player = this.thePlayer;

                                    if (player != null) {
                                       Augustus.getInstance().getModuleManager().saveMoveKeys.incrementInGameTicks();
                                       int n20 = 0;

                                       while (true) {
                                             if (n20 < 9) {
                                                n10 = (this.gameSettings.keyBindsHotbar[n20].isPressed()) ? 1 : 0;

if (n10 != 0) {
n10 = (this.thePlayer.isSpectator()) ? 1 : 0;
if (n10 != 0) {
this.ingameGUI.getSpectatorGui().onHotbarSelected(n20);
} else {
n10 = n20;

                                                   SilentSlotManager.realHotbarSlot = n10;
                                                   this.thePlayer.inventory.currentItem = n20;
}
}

                                                n20++;
                                                continue;
                                             }

                                             SilentSlotListener.onSilentSlot(StateTypes.PRE);
                                             SilentSlotManager.resetSlot(this.thePlayer.inventory.currentItem);
                                             SilentSlotListener.onSilentSlot(StateTypes.POST);
                                             mc9 = this;

n10 = (this.isNotInGui()) ? 1 : 0;
                                             break;
                                          }

                                          if (n10 != 0) {
                                             this.thePlayer.inventory.currentItem = SilentSlotManager.tickAndResolveSlot();
                                          }

                                          mc9 = this;

                                       n20 = mc9.gameSettings.chatVisibility != EntityPlayer.EnumChatVisibility.HIDDEN ? 1 : 0;

                                       while (this.gameSettings.keyBindInventory.isPressed()) {
                                             Minecraft mc17 = this;
                                             flag = this.playerController.isRidingHorse();

if (flag) {
                                                   this.thePlayer.sendHorseInventory();
                                                   continue;
                                                }

                                                ViaAugustus.sendOpenInventoryPacketIfLegacy();
                                                mc17 = this;

                                             mc17.displayGuiScreen(new GuiInventory(this.thePlayer));
                                             
                                          }

                                          flag = this.thePlayer.isSpectator();

                                       if (!flag) {
                                                   DropItemListener.postDropItem();
                                                }
if (!this.gameSettings.keyBindDrop.isPressed()) {
flag2 = this.gameSettings.keyBindChat.isPressed();
} else {
while (true) {
                                                flag2 = this.thePlayer.isSpectator();

if (!flag2) {
                                                      this.thePlayer.dropOneItem(GuiScreen.isCtrlKeyDown());
                                                      
                                                   }

                                                if (!this.gameSettings.keyBindDrop.isPressed()) {
                                                   flag2 = this.gameSettings.keyBindChat.isPressed();
                                                   break;
                                                }
                                             }
}

                                          while (flag2) {
                                             n10 = n20;

if (n20 == 0) {
                                                break;
                                             }

                                             this.displayGuiScreen(new GuiChat());

flag2 = this.gameSettings.keyBindChat.isPressed();
                                          }

                                          Minecraft mc18 = this;
if (this.currentScreen == null) {
mc18 = this;

                                             n10 = (mc18.gameSettings.keyBindCommand.isPressed()) ? 1 : 0;

if (n10 != 0) {
                                                n10 = n20;

if (n20 != 0) {
                                                   this.displayGuiScreen(new GuiChat("/"));
                                                }
                                             }
}

                                          EventClick.tickCounter.increment();
                                          n10 = 0;

                                       EventClick.clicksThisTick = n10;
                                       ClickButtonType left = ClickButtonType.LEFT;
                                       boolean isKeyDown = this.gameSettings.keyBindUseItem.isKeyDown();
                                       boolean isKeyDown2 = this.gameSettings.keyBindAttack.isKeyDown();
                                       Runnable runnable = this::rightClickMouse;
                                       EventClick event3 = ClickListener.onClick(left, isKeyDown, isKeyDown2, this::clickMouse, runnable);
                                       n10 = event3.getLeftClicks();
                                       int n21 = (event3.isIgnoreVanillaClicks()) ? 1 : 0;
                                       n21 = n21 != 0 ? 0 : this.gameSettings.keyBindAttack.getPressTime();

                                       int n22 = n10 + n21;
                                       n10 = event3.getRightClicks();
                                       int n23 = (event3.isIgnoreVanillaClicks()) ? 1 : 0;
                                       n23 = n23 != 0 ? 0 : this.gameSettings.keyBindUseItem.getPressTime();

                                       int n24 = n10 + n23;
                                       n10 = (event3.isIgnoreVanillaClicks()) ? 1 : 0;
                                       n10 = n10 != 0 ? 0 : this.gameSettings.keyBindPickBlock.getPressTime();

                                       int n25 = n10;
                                       byte b3 = (byte)((event3.isRightMouseDown()) ? 1 : 0);
                                       byte b4 = (byte)((event3.isLeftMouseDown()) ? 1 : 0);
                                       int n26 = (event3.isBlockAllClicks()) ? 1 : 0;
                                       if (n26 != 0) {
                                             n22 = 0;
                                             n24 = 0;
                                             n25 = 0;
                                             b3 = 0;
                                             b4 = 0;
                                          }

                                          this.gameSettings.keyBindAttack.setPressTime(0);
                                          this.gameSettings.keyBindUseItem.setPressTime(0);
                                          this.gameSettings.keyBindPickBlock.setPressTime(0);
                                          n26 = 0;

                                       int n27 = n26;
                                       byte b5 = 0;
                                       n26 = (event3.isForceBreakBlock()) ? 1 : 0;
                                       if (n26 != 0) {
                                             n27 = 1;
                                             b5 = 1;
                                          }

                                          n26 = (event3.isCancelled()) ? 1 : 0;

                                       if (n26 == 0) {
EntityPlayerSP thePlayer2 = this.thePlayer;
if (this.thePlayer != null) {
thePlayer2 = this.thePlayer;

                                                if (thePlayer2.isUsingItem()) {
                                                   if (b3 == 0 || ClientManagers.moduleManager.fixes.shouldApplyIntaveBlockingFix()) {
                                                      this.playerController.onStoppedUsingItem(this.thePlayer);
                                                   }
                                                } else {
                                                      int n28;

                                                         n28 = 0;

                                                      int n29 = n28;
                                                      int n30 = 0;

                                                      while (n30 < n22) {
                                                            n28 = (event3.isBreakingBlock()) ? 1 : 0;

if (n28 == 0) {
                                                                  n28 = event3.getLeftClicks();

if (n28 > 0) {
MovingObjectPosition objectMouseOver2 = this.objectMouseOver;
if (this.objectMouseOver != null) {
mc10 = this;
objectMouseOver2 = this.objectMouseOver;
if (objectMouseOver2.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
mc10 = this;

                                                                     n28 = (mc10.wasLeftClicking) ? 1 : 0;

if (mc10.wasLeftClicking) {
                                                                        n28 = DefaultRandomGenerator.instance.nextInt((byte)0, (byte)100);

if (n28 >= 20) {
                                                                           break;
                                                                        }
                                                                     }
}
}
}
                                                               }

                                                               event3.getLeftClickAction().run();
                                                               EventClick.pendingBlockBreakDelay = true;
                                                               EventClick.clicksThisTick++;
                                                               n28 = 1;

                                                            n29 = n28;
                                                            n30++;
                                                            
                                                         }

                                                         n28 = (event3.isOverrideBlockBreak()) ? 1 : 0;

                                                      if (n28 != 0) {
n28 = this.leftClickCounter;
if (this.leftClickCounter <= 100) {
n28 = (event3.isBreakingBlock()) ? 1 : 0;
if (n28 != 0 && this.currentScreen == null) {
n3 = 1;
} else {
n3 = 0;
}
} else {
n3 = 0;
}

                                                               n27 = n3;
} else {
n28 = (event3.isDelayBlockBreak()) ? 1 : 0;
if (n28 != 0) {
n28 = this.leftClickCounter;
if (this.leftClickCounter <= 100) {
n28 = (EventClick.shouldBreakBlockAfterDelay(event3.isDelayBlockBreak())) ? 1 : 0;
if (n28 != 0) {
n28 = 1;
} else {
n28 = 0;
}
} else {
n28 = 0;
}

                                                                        n27 = n28;
} else {
mc11 = this;
n28 = this.leftClickCounter;
if (n28 <= 100) {
mc11 = this;
if (mc11.currentScreen == null) {
n4 = b4;
if (b4 != 0) {
n4 = (this.inGameHasFocus) ? 1 : 0;
if (n4 != 0) {
n4 = 1;
} else {
n4 = 0;
}
} else {
n4 = 0;
}
} else {
n4 = 0;
}
} else {
n4 = 0;
}

                                                         n27 = n4;
}
}

                                                      b5 = 1;
                                                      n28 = n27;
                                                      if (n27 != 0) {
                                                            n28 = n29;

if (n29 == 0) {
                                                               n28 = (EventClick.wasBreakingBlock) ? 1 : 0;

if (!EventClick.wasBreakingBlock && this.objectMouseOver != null) {
                                                                  event3.getLeftClickAction().run();
                                                                  EventClick.pendingBlockBreakDelay = true;
                                                                  EventClick.clicksThisTick++;
                                                               }
                                                            }
                                                         }

                                                         n30 = 0;
                                                         n28 = 0;

                                                      int n31 = n28;

                                                      do {
                                                            boolean canRightClick = n31 < n24 && event3.isAllowRightClick() && !ClientManagers.moduleManager.fixes.shouldApplyIntaveBlockingFix();
                                                            if (!canRightClick && (n30 == 0 || !event3.isAllowMultiRightClick() || n31 >= 10)) {
                                                               break;
                                                            }

                                                               n5 = (ClientManagers.moduleManager.noMiningTrace.isToggled()) ? 1 : 0;

                                                            if (n5 != 0) {
                                                                  n5 = (ClientManagers.moduleManager.noMiningTrace.alwaysAtRightClicking.getValue()) ? 1 : 0;

if (n5 != 0) {
                                                                     this.entityRenderer.getMouseOver(1.0F, true);
                                                                  }
                                                               }

                                                               event3.getRightClickAction().run();
                                                               n5 = (ClientManagers.moduleManager.noPlaceDelay.isToggled()) ? 1 : 0;

                                                            if (n5 != 0) {
MovingObjectPosition objectMouseOver3 = this.objectMouseOver;
if (this.objectMouseOver != null) {
objectMouseOver3 = this.objectMouseOver;
if (objectMouseOver3.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
mc12 = this;
objectMouseOver3 = this.objectMouseOver;
if (objectMouseOver3.getBlockPos() != null) {
mc12 = this;

                                                                     mc12.entityRenderer.getMouseOver(1.0F, false);
                                                                     byte b8 = (byte)((Augustus.getInstance().getModuleManager().smartRaytrace.isToggled()) ? 1 : 0);
                                                                     if (b8 != 0) {
                                                                           Augustus.getInstance().getModuleManager().smartRaytrace.updateSmartRaytrace();
                                                                        }

                                                                        ItemStack currentEquippedItem = this.thePlayer.getCurrentEquippedItem();
                                                                        BlockPos blockPos = this.objectMouseOver.getBlockPos();
                                                                        Vec3 hitVec = this.objectMouseOver.hitVec;
                                                                        b8 = (byte)((PlayerUtil.canPlaceBlockAt(currentEquippedItem, blockPos, this.objectMouseOver.sideHit, hitVec)) ? 1 : 0);

                                                                     if (b8 != 0) {
b8 = (byte)((NoPlaceDelay.isFacingEnabled(this.objectMouseOver.sideHit)) ? 1 : 0);
if (b8 != 0) {
b8 = 1;
} else {
b8 = 0;
}
} else {
b8 = 0;
}

                                                                     n30 = b8;
} else {
n5 = 0;

                                                               n30 = n5;
}
} else {
n5 = 0;

                                                               n30 = n5;
}
} else {
n5 = 0;

                                                               n30 = n5;
}
} else {
n5 = 0;

                                                               n30 = n5;
}

                                                            n31++;
                                                         } while (true);

                                                         n28 = 0;

                                                      n31 = n28;

                                                      while (n31 < n25) {
                                                         this.middleClickMouse();
                                                         n31++;

}
                                                   }

                                                   n26 = b3;

if (n26 != 0) {
                                                   n26 = this.rightClickDelayTimer;

if (this.rightClickDelayTimer == 0) {
                                                      n26 = (this.thePlayer.isUsingItem()) ? 1 : 0;

if (n26 == 0) {
                                                         n26 = (event3.isAllowRightClick()) ? 1 : 0;

if (n26 != 0) {
                                                            n26 = (ClientManagers.moduleManager.fixes.shouldApplyIntaveBlockingFix()) ? 1 : 0;

if (n26 == 0) {
                                                               event3.getRightClickAction().run();
                                                            }
                                                         }
                                                      }
                                                   }
                                                }
}
}

                                             double clicksThisTick = (double)EventClick.clicksThisTick;
                                             EventClick.recentClicksBuffer.add(clicksThisTick);

                                          n26 = b5;

                                       if (n26 == 0) {
n26 = (event3.isOverrideBlockBreak()) ? 1 : 0;
if (n26 != 0) {
n26 = this.leftClickCounter;
if (this.leftClickCounter <= 100) {
n26 = (event3.isBreakingBlock()) ? 1 : 0;
if (n26 != 0 && this.currentScreen == null) {
n6 = 1;
} else {
n6 = 0;
}
} else {
n6 = 0;
}

                                                n27 = n6;
} else {
n26 = (event3.isDelayBlockBreak()) ? 1 : 0;
if (n26 != 0) {
n26 = this.leftClickCounter;
if (this.leftClickCounter <= 100) {
n26 = (EventClick.shouldBreakBlockAfterDelay(event3.isDelayBlockBreak())) ? 1 : 0;
if (n26 != 0) {
n26 = 1;
} else {
n26 = 0;
}
} else {
n26 = 0;
}

                                                         n27 = n26;
} else {
mc13 = this;
n26 = this.leftClickCounter;
if (n26 <= 100) {
mc13 = this;
if (mc13.currentScreen == null) {
n7 = b4;
if (b4 != 0) {
n7 = (this.inGameHasFocus) ? 1 : 0;
if (n7 != 0) {
n7 = 1;
} else {
n7 = 0;
}
} else {
n7 = 0;
}
} else {
n7 = 0;
}
} else {
n7 = 0;
}

                                          n27 = n7;
}
}
}

                                       Runnable blockBreakAction = event3.getBlockBreakAction();
if (blockBreakAction == null) {
this.sendClickBlockToController((n27 != 0));
                                                EventClick.wasBreakingBlock = (n27 != 0);
} else {
blockBreakAction = event3.getBlockBreakAction();

                                          blockBreakAction.run();
}

                                       this.wasLeftClicking = (n27 != 0);
                                    }
                                 }

                                 if (this.isNotInGui()) {
                                    this.ticksWithoutScreen++;
                                 } else {
                                    this.ticksWithoutScreen = 0;
                                 }

                                 if (this.theWorld != null) {
                                    if (this.thePlayer != null) {
                                       TickListener.postTick(TickPhase.TICK);
                                       this.joinPlayerCounter++;
                                       if (this.joinPlayerCounter == 30) {
                                          this.joinPlayerCounter = 0;
                                          this.theWorld.joinEntityInSurroundings(this.thePlayer);
                                       }
                                    }

                                    this.mcProfiler.endStartSection("gameRenderer");
                                    if (!this.isGamePaused) {
                                       this.entityRenderer.updateRenderer();
                                    }

                                    this.mcProfiler.endStartSection("levelRenderer");
                                    if (!this.isGamePaused) {
                                       this.renderGlobal.updateClouds();
                                    }

                                    this.mcProfiler.endStartSection("level");
                                    if (!this.isGamePaused) {
                                       if (this.theWorld.getLastLightningBolt() > 0) {
                                          this.theWorld.setLastLightningBolt(this.theWorld.getLastLightningBolt() - 1);
                                       }

                                       this.theWorld.updateEntities();
                                       if (Augustus.getInstance().getModuleManager().baritone.isToggled()) {
                                          IBaritone baritoneForPlayer4 = BaritoneAPI.getProvider().getBaritoneForPlayer(this.thePlayer);
                                          if (baritoneForPlayer4 != null) {
                                             baritoneForPlayer4.getGameEventHandler().onPlayerUpdate(new PlayerUpdateEvent(EventState.POST));
                                          }
                                       }
                                    }
                                 } else if (this.entityRenderer.isShaderActive()) {
                                    this.entityRenderer.stopUseShader();
                                 }

                                 if (!this.isGamePaused) {
                                    this.mcMusicTicker.update();
                                    this.mcSoundHandler.update();
                                 }

         mc15 = this;

      if (this.theWorld != null) {
         if (!this.isGamePaused) {
            this.theWorld.setAllowedSpawnTypes(this.theWorld.getDifficulty() != EnumDifficulty.PEACEFUL, true);

            try {
               this.theWorld.tick();
            } catch (Throwable e3) {
               CrashReport crashReport3 = CrashReport.makeCrashReport(e3, "Exception in world tick");
               if (this.theWorld == null) {
                  crashReport3.makeCategory("Affected level").addCrashSection("Problem", "Level is null!");
               } else {
                  this.theWorld.addWorldInfoToCrashReport(crashReport3);
               }
               throw new ReportedException(crashReport3);
            }
         }

         this.mcProfiler.endStartSection("animateTick");
         if (!this.isGamePaused && this.theWorld != null) {
            this.theWorld.doVoidFogParticles(
               MathHelper.floor_double(this.thePlayer.posX),
               MathHelper.floor_double(this.thePlayer.posY),
               MathHelper.floor_double(this.thePlayer.posZ)
            );
         }

         this.mcProfiler.endStartSection("particles");
         if (!this.isGamePaused) {
            this.effectRenderer.updateEffects();
         }
      } else if (this.myNetworkManager != null) {
         this.mcProfiler.endStartSection("pendingConnection");
         this.myNetworkManager.processReceivedPackets();
      }

         mc15 = this;

      if (mc15.theWorld != null) {
            mc15 = this;

if (this.thePlayer != null) {
               TickListener.postTick(TickPhase.LATE);
            }
         }

         this.mcProfiler.endSection();
         mc15 = this;

      mc15.systemTime = getSystemTime();
      ToggleUtil.flushPendingToggles();
      if (Augustus.getInstance().getModuleManager().baritone.isToggled()) {
         for (IBaritone baritone6 : (Iterable<IBaritone>)(BaritoneAPI.getProvider().getAllBaritones())) {
            IPlayerContext playerContext2 = baritone6.getPlayerContext();
if (playerContext2.player() != null) {
playerContext2 = baritone6.getPlayerContext();
if (playerContext2.world() != null) {
type2 = TickEvent.Type.IN;
} else {
type2 = TickEvent.Type.OUT;
}
} else {
type2 = TickEvent.Type.OUT;
}

            TickEvent.Type type4 = type2;
            baritone6.getGameEventHandler().onPostTick((TickEvent)biFunction.apply(EventState.POST, type4));
            
         }
      }
   }

   public void setServerData(ServerData serverData) {
      this.currentServerData = serverData;
      getMinecraft().fontRendererObj.clearFilterPatterns();
   }

   public void setIngameNotInFocus() {
      
      Minecraft mc = this;
      if (!this.inGameHasFocus) {
            return;
         }

         KeyBinding.unPressAllKeys();
         this.inGameHasFocus = false;
         mc = this;

      mc.mouseHelper.ungrabMouseCursor();
   }

   public PropertyMap getTwitchDetails() {
      
      PropertyMap twitchDetails2 = this.twitchDetails;
      if (this.twitchDetails.isEmpty()) {
            GameProfile profile = this.getSessionService().fillProfileProperties(this.session.getProfile(), false);
            this.twitchDetails.putAll(profile.getProperties());
         }

         twitchDetails2 = this.twitchDetails;

      return twitchDetails2;
   }

   public CrashReport addGraphicsAndWorldToCrashReport(CrashReport crashReport) {
      crashReport.getCategory().addCrashSectionCallable("Launched Version", new Minecraft.ValueCallable3(this));
      
      crashReport.getCategory().addCrashSectionCallable("LWJGL", new Minecraft.ValueCallable4(this));
      crashReport.getCategory().addCrashSectionCallable("OpenGL", new Minecraft.ValueCallable5(this));
      crashReport.getCategory().addCrashSectionCallable("GL Caps", new Minecraft.ValueCallable6(this));
      crashReport.getCategory().addCrashSectionCallable("Using VBOs", new Minecraft.ValueCallable7(this));
      crashReport.getCategory().addCrashSectionCallable("Is Modded", new Minecraft.ValueCallable8(this));
      crashReport.getCategory().addCrashSectionCallable("Type", new Minecraft.ValueCallable9(this));
      crashReport.getCategory().addCrashSectionCallable("Resource Packs", new Minecraft.ValueCallable10(this));
      crashReport.getCategory().addCrashSectionCallable("Current Language", new Minecraft.ValueCallable11(this));
      crashReport.getCategory().addCrashSectionCallable("Profiler Position", new Minecraft.ValueCallable12(this));
      CrashReport crashReport2 = crashReport;
      crashReport.getCategory().addCrashSectionCallable("CPU", new Minecraft.ValueCallable13(this));
         if (this.theWorld != null) {
            this.theWorld.addWorldInfoToCrashReport(crashReport);
         }

         crashReport2 = crashReport;

      return crashReport2;
   }

   public Proxy getProxy() {
      return this.proxy;
   }

   private void runScheduledTasks() {
      
      synchronized (this.scheduledTasks) {
         while (!this.scheduledTasks.isEmpty()) {
            Util.runTask((FutureTask)this.scheduledTasks.poll(), LOGGER);
            continue;
            
         }
      }
   }

   private void updateDebugProfilerName(int n) {
      
      ArrayList list = new ArrayList();
      Object obj = list;
      if (list == null) {
            return;
         }

         obj = list;

      if (((ArrayList)obj).isEmpty()) {
            return;
         }

         obj = list.remove(0);

      Profiler.Result result = (Profiler.Result)obj;
      int n2 = n;
      if (n == 0) {
            int length = result.profilerName.length();
            if (length <= 0) {
                  return;
               }

               length = this.debugProfilerName.lastIndexOf(".");

            int n3 = length;
            if (n3 >= 0) {
               this.debugProfilerName = this.debugProfilerName.substring(0, n3);
            }

            return;
         }

         n2 = --n;

      if (n2 >= list.size()) {
            return;
         }

         n2 = (((Profiler.Result)list.get(n)).profilerName.equals("unspecified")) ? 1 : 0;

      if (n2 != 0) {
               return;
            }

            obj = this;
n2 = this.debugProfilerName.length();

         if (n2 > 0) {
            this.debugProfilerName = this.debugProfilerName + ".";
         }

         obj = this;

      String str3 = this.debugProfilerName + ((Profiler.Result)list.get(n)).profilerName;
      ((Minecraft)obj).debugProfilerName = str3;
   }

   public boolean isSingleplayer() {
      
      Minecraft mc = this;
      if (!this.integratedServerIsRunning) {
            return false;
         }

         mc = this;

      return mc.theIntegratedServer != null;
   }

   public boolean isUnicode() {
      return this.mcLanguageManager.isCurrentLocaleUnicode() || this.gameSettings.forceUnicodeFont;
   }

   public RenderManager getRenderManager() {
      return this.renderManager;
   }

   public String getVersion() {
      return this.version;
   }

   public boolean isConnectedToRealms() {
      return this.connectedToRealms;
   }

   public BlockRendererDispatcher getBlockRendererDispatcher() {
      return this.blockRenderDispatcher;
   }

   public Timer getTimer() {
      return this.timer;
   }

   public static Map getSessionInfo() {
      HashMap map = Maps.newHashMap();
      map.put("X-Minecraft-Username", getMinecraft().getSession().getUsername());
      map.put("X-Minecraft-UUID", getMinecraft().getSession().getPlayerID());
      map.put("X-Minecraft-Version", "1.8.9");
      return map;
   }

   public void setWindowIcon(boolean flag) {
      
      if (Util.getOSType() != Util.EnumOS.OSX) {
         InputStream in = null;
         InputStream in2 = null;
         try {
            if (flag) {
               in = this.mcDefaultResourcePack.getInputStreamAssets(new ResourceLocation("icons/icon_16x16.png"));
               in2 = this.mcDefaultResourcePack.getInputStreamAssets(new ResourceLocation("icons/icon_32x32.png"));
            } else {
               in = Minecraft.class.getClassLoader().getResourceAsStream("assets/augustus/pictures/logo_16x16.png");
               in2 = Minecraft.class.getClassLoader().getResourceAsStream("assets/augustus/pictures/logo_32x32.png");
            }

            if (in != null && in2 != null) {
               Display.setIcon(new ByteBuffer[]{this.readImageToBuffer(in), this.readImageToBuffer(in2)});
            }
         } catch (IOException e) {
            LOGGER.error("Couldn't set icon", e);
         } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(in2);
         }
      }
   }

   public void rightClickMouse() {
      boolean flag;
      int n;
      
      n = (this.playerController.isHittingBlock()) ? 1 : 0;
      if (n != 0) {
            n = (ClientManagers.moduleManager.fixes.isPlaceWhileBreakEnabled()) ? 1 : 0;

if (n == 0) {
               return;
            }
         }

         n = this.rightClickDelayTimer;

      byte b;
         int n2;
            ItemStack stack;
            n2 = n;
               this.rightClickDelayTimer = (byte)4;
               b = 1;
               stack = this.thePlayer.inventory.getCurrentItem();
if (this.objectMouseOver == null) {
LOGGER.warn("Null returned as 'hitResult', this shouldn't happen!");
} else {
switch (this.objectMouseOver.typeOfHit) {
               case ENTITY:
                  n = (this.playerController.isPlayerRightClickingOnEntity(this.thePlayer, this.objectMouseOver.entityHit, this.objectMouseOver)) ? 1 : 0;
                  if (n != 0) {
                        b = 0;
                        break;
                     }

                     n = (this.playerController.interactWithEntitySendPacket(this.thePlayer, this.objectMouseOver.entityHit)) ? 1 : 0;

if (n == 0) {
                     break;
                  }

                  b = 0;
                  break;
               case BLOCK:
                  BlockPos blockPos = this.objectMouseOver.getBlockPos();
                  if (this.theWorld.getBlockState(blockPos).getBlock().getMaterial() != Material.air) {
ItemStack stack2 = stack;
if (stack == null) {
n = 0;
} else {
stack2 = stack;

                        n = stack2.stackSize;
}

                     int n3 = n;
                     boolean isCancelled = BlockPlaceListener.postBlockPlace(BlockPlaceStage.PRE).isCancelled();
                     if (isCancelled) {
                           this.rightClickDelayTimer = n2;
                           return;
                        }

                        isCancelled = this.playerController.onPlayerRightClick(this.thePlayer, this.theWorld, stack, blockPos, this.objectMouseOver.sideHit, this.objectMouseOver.hitVec);

                     if (isCancelled) {
b = 0;
                           this.thePlayer.swingItem();
                           isCancelled = Augustus.getInstance().getModuleManager().baritone.isToggled();

                        if (isCancelled) {
                           BaritoneAPI.getProvider().getPrimaryBaritone().getGameEventHandler().onBlockInteract(new BlockInteractEvent(blockPos, BlockInteractEvent.Type.USE));
                        }
}

                     ItemStack stack3 = stack;
                     if (stack == null) {
                           return;
                        }

                        stack3 = stack;

                     n = stack3.stackSize;
if (stack3.stackSize == 0) {
this.thePlayer.inventory.mainInventory[this.thePlayer.inventory.currentItem] = null;
} else {
n = stack.stackSize;
if (n == n3) {
n = (this.playerController.isInCreativeMode()) ? 1 : 0;
if (n != 0) {
this.entityRenderer.itemRenderer.resetEquippedProgress();
}
} else {
this.entityRenderer.itemRenderer.resetEquippedProgress();
}
}
}
            }
}

         n = b;

      if (n != 0) {
         ItemStack currentItem = this.thePlayer.inventory.getCurrentItem();
         if (currentItem != null) {
            Minecraft mc = this;
            if (!this.playerController.sendUseItem(this.thePlayer, this.theWorld, currentItem)) {
                  return;
               }

               mc = this;

            mc.entityRenderer.itemRenderer.resetEquippedProgress2();
         }
      }
   }

   public ResourcePackRepository getResourcePackRepository() {
      return this.mcResourcePackRepository;
   }

   public TextureManager getTextureManager() {
      return this.renderEngine;
   }

   public void shutdownMinecraftApplet() {
      
      boolean flag = false /* VF: Semaphore variable */;

      try {
         flag = true;
         Augustus.getInstance().getDiscordRpcManager().shutdown();
         LOGGER.info("Stopping!");

         try {
            this.loadWorld((WorldClient)null);
         } catch (Throwable e) {
         }

         this.mcSoundHandler.unloadSounds();
         flag = false;
      } finally {
         if (flag) {
            Display.destroy();
               byte b = (byte)((this.hasCrashed) ? 1 : 0);
if (!this.hasCrashed) {
b = 0;

               System.exit(b);
}
         }
      }

      Display.destroy();
      if (!this.hasCrashed) {
            System.exit(0);
         }

         System.gc();
   }

   public Session getSession() {
      return this.session;
   }

   public LanguageManager getLanguageManager() {
      return this.mcLanguageManager;
   }

   public void loadWorld(WorldClient world) {
      this.loadWorld(world, "");
   }

   public void toggleFullscreen() {

      try {
         this.fullscreen = !this.fullscreen;
         this.gameSettings.fullScreen = this.fullscreen;
         if (this.fullscreen) {
            this.updateDisplayMode();
            this.displayWidth = Display.getDisplayMode().getWidth();
            this.displayHeight = Display.getDisplayMode().getHeight();
            if (this.displayWidth <= 0) {
               this.displayWidth = 1;
            }

            if (this.displayHeight <= 0) {
               this.displayHeight = 1;
            }
         } else {
            Display.setDisplayMode(new DisplayMode(this.tempDisplayWidth, this.tempDisplayHeight));
            this.displayWidth = this.tempDisplayWidth;
            this.displayHeight = this.tempDisplayHeight;
            if (this.displayWidth <= 0) {
               this.displayWidth = 1;
            }

            if (this.displayHeight <= 0) {
               this.displayHeight = 1;
            }
         }

         WindowResizeListener.postWindowResize(this.displayWidth, this.displayHeight);
         if (this.currentScreen != null) {
            this.resize(this.displayWidth, this.displayHeight);
         } else {
            this.updateFramebufferSize();
         }

         Display.setFullscreen(this.fullscreen);
         Display.setVSyncEnabled(this.gameSettings.enableVsync);
         this.updateDisplay();
      } catch (Exception e) {
         LOGGER.error("Couldn't toggle fullscreen", e);
      }
   }

   public void clickMouse() {
      Minecraft mc = null, mc2 = null, mc3 = null;
      boolean flag = false;
      boolean flag2;
      int n;
      MovingObjectPosition objectMouseOver2 = this.objectMouseOver;
if (this.objectMouseOver != null) {
objectMouseOver2 = this.objectMouseOver;
if (objectMouseOver2.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
objectMouseOver2 = this.objectMouseOver;

            n = (ProtectedPlayerCheck.isProtectedPlayer(objectMouseOver2.entityHit)) ? 1 : 0;

if (n != 0) {
               return;
            }
}
}

         n = (ClientManagers.moduleManager.fixes.isToggled()) ? 1 : 0;

      if (n != 0) {
         String fixMode = ClientManagers.moduleManager.fixes.hitDelayFixMode.getSelectedValue();
         boolean applyFix = (fixMode.equals("OnlyAtTargetLite") && this.leftClickCounter < 9)
            || (fixMode.equals("OnlyAtTarget") && ClientManagers.moduleManager.fixes.shouldApplyHitDelayFix(this.leftClickCounter < 9));
         if (applyFix && this.objectMouseOver != null && this.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            this.leftClickCounter = 0;
         }
      }

            mc = this;

         n = mc.leftClickCounter;

      if (n > 0) {
               n = (ClientManagers.moduleManager.debug.lostHitDebug.getValue()) ? 1 : 0;

if (n != 0) {
                  n = (ClientManagers.moduleManager.debug.isToggled()) ? 1 : 0;

if (n != 0) {
                     mc2 = this;

if (this.objectMouseOver != null) {
                        mc2 = this;

if (this.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                           ChatUtil.sendChat("Lost Hit");
                        }
                     }
                  }
               }
            }

            mc2 = this;

         n = mc2.leftClickCounter;

      if (n <= 0) {
         if (ViaAugustus.targetProtocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8)) {
            this.thePlayer.swingItem();
         }

         if (this.objectMouseOver == null) {
            LOGGER.error("Null returned as 'hitResult', this shouldn't happen!");
            if (this.playerController.isNotCreative()) {
               this.leftClickCounter = 10;
            }
         } else {
            boolean attackedOrClicked = false;
            switch (this.objectMouseOver.typeOfHit) {
               case ENTITY:
                  this.playerController.attackEntity(this.thePlayer, this.objectMouseOver.entityHit);
                  attackedOrClicked = true;
                  break;
               case BLOCK:
                  BlockPos blockPos = this.objectMouseOver.getBlockPos();
                  if (this.theWorld.getBlockState(blockPos).getBlock().getMaterial() != Material.air) {
                     this.playerController.clickBlock(blockPos, this.objectMouseOver.sideHit);
                     if (Augustus.getInstance().getModuleManager().baritone.isToggled()) {
                        BaritoneAPI.getProvider().getPrimaryBaritone().getGameEventHandler().onBlockInteract(new BlockInteractEvent(blockPos, BlockInteractEvent.Type.START_BREAK));
                     }
                     attackedOrClicked = true;
                  }
                  break;
               case MISS:
               default:
                  break;
            }
            if (!attackedOrClicked) {
               if (this.playerController.isNotCreative()) {
                  this.leftClickCounter = 10;
                  if (ClientManagers.moduleManager.fixes.isToggled() && ClientManagers.moduleManager.fixes.hitDelayFixMode.getSelectedValue().equals("Default")) {
                     this.leftClickCounter = 0;
                  }
               }
               this.thePlayer.resetTicksSinceLastSwing();
            }

            if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
               this.thePlayer.swingItem();
            }
         }
      }

      LeftClickListener.onLeftClick();
   }

   public final boolean isDemo() {
      return this.isDemo;
   }

   public void shutdown() {
      this.running = false;
   }

   public TextureMap getTextureMapBlocks() {
      return this.textureMapBlocks;
   }

   public IntegratedServer getIntegratedServer() {
      return this.theIntegratedServer;
   }

   private boolean shouldAllowUserInput(GuiScreen screen) {
      
      boolean isToggled = Augustus.getInstance().getModuleManager().baritone.isToggled();
      if (!isToggled) {
            return screen.allowUserInput;
         }

         isToggled = BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing();

      if (isToggled && this.thePlayer != null) {
            return true;
         }

         isToggled = screen.allowUserInput;

      return isToggled;
   }

   public FrameTimer getFrameTimer2() {
      return this.frameTimer;
   }

   private void startTimerHackThread() {
      Minecraft.WorkerTask obj = new Minecraft.WorkerTask(this, "Timer hack thread");
      obj.setDaemon(true);
      obj.start();
   }

   public static int getGLMaximumTextureSize() {
      
      int n = 16384;

      int n2;
      while (true) {
         if (n > 0) {
            GL11.glTexImage2D(32868, 0, 6408, n, n, 0, 6408, 5121, (ByteBuffer)null);
            int n3 = GL11.glGetTexLevelParameteri(32868, 0, 4096);
            n2 = n3;

if (n3 != 0) {
                  return n;
               }

               n2 = n >> 1;

            n = n2;
            continue;
         }

         n2 = -1;
         break;
      }

      return n2;
   }

   public void dispatchKeypresses() {
      
      int eventKey = Keyboard.getEventKey();
      eventKey = eventKey == 0 ? Keyboard.getEventCharacter() : Keyboard.getEventKey();

      int n = eventKey;
      eventKey = n;
      if (n == 0) {
            return;
         }

         eventKey = (Keyboard.isRepeatEvent()) ? 1 : 0;

      if (eventKey != 0) {
            return;
         }

         eventKey = (this.currentScreen instanceof GuiControls) ? 1 : 0;

      if (eventKey != 0) {
            long l;
            eventKey = (l = ((GuiControls)this.currentScreen).time - (getSystemTime() - 20L)) == 0L ? 0 : (l < 0L ? -1 : 1);

if (eventKey > 0) {
               return;
            }
         }

         eventKey = (Keyboard.getEventKeyState()) ? 1 : 0;

      if (eventKey == 0) {
            return;
         }

         eventKey = n;

      int keyCode = this.gameSettings.keyBindFullscreen.getKeyCode();
      if (eventKey == keyCode) {
            this.toggleFullscreen();
            return;
         }

         eventKey = n;
         keyCode = this.gameSettings.keyBindScreenshot.getKeyCode();

      if (eventKey == keyCode) {
         ScreenShotHelper.saveScreenshotAsync(this.mcDataDir, this.displayWidth, this.displayHeight, this.framebufferMc);
      }
   }

   private void updateDisplayMode() throws LWJGLException {
      HashSet set = Sets.newHashSet();
      Collections.addAll(set, Display.getAvailableDisplayModes());
      DisplayMode desktopDisplayMode = Display.getDesktopDisplayMode();
      if (!set.contains(desktopDisplayMode) && Util.getOSType() == Util.EnumOS.OSX) {
         for (DisplayMode displayMode : (Iterable<DisplayMode>)macDisplayModes) {
            boolean foundFull = true;
            for (DisplayMode available : (Iterable<DisplayMode>)set) {
               if (available.getBitsPerPixel() == 32 && available.getWidth() == displayMode.getWidth() && available.getHeight() == displayMode.getHeight()) {
                  foundFull = false;
                  break;
               }
            }
            if (!foundFull) {
               for (DisplayMode available : (Iterable<DisplayMode>)set) {
                  if (available.getBitsPerPixel() == 32 && available.getWidth() == displayMode.getWidth() / 2 && available.getHeight() == displayMode.getHeight() / 2) {
                     desktopDisplayMode = available;
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

   public static String accessVersion(Minecraft mc) {
      return mc.version;
   }

   public void crashed(CrashReport crashReport) {
      this.hasCrashed = true;
      this.crashReporter = crashReport;
   }

   public static Minecraft getMinecraft() {
      return theMinecraft;
   }

   private void startGame() throws LWJGLException, IOException {
      ClientHoliday clientHoliday = null, clientHoliday2 = null;
      File[] files = null;
      boolean flag = false, flag2 = false;
      boolean flag3;
      Minecraft mc;
      RawMouseInputThread.INSTANCE.start();
      
      this.gameSettings = new GameSettings(this, this.mcDataDir);
      KeyBinding.rebuildMovementKeyMap();
      this.defaultResourcePacks.add(this.mcDefaultResourcePack);
      
      this.startTimerHackThread();
      mc = this;
      if (this.gameSettings.overrideHeight > 0) {
            mc = this;

if (this.gameSettings.overrideWidth > 0) {
               this.displayWidth = this.gameSettings.overrideWidth;
               this.displayHeight = this.gameSettings.overrideHeight;
            }
         }

         LOGGER.info("LWJGL Version: " + Sys.getVersion());
         this.setWindowIcon(false);
         this.setInitialDisplayMode();
         mc = this;

      mc.createDisplay();
      AbstractSkeletonRenderer.captureModelViewMatrix();

      try {
         ClientHoliday currentHoliday = HolidayDetector.getCurrentHoliday();
         if (currentHoliday == ClientHoliday.HALLOWEEN) {
            this.audioPlayer.playResource("sounds/halloween.mp3");
            this.audioPlayer.setVolume(0.6);
            this.audioPlayer.setCycleCount(10);
         } else if (currentHoliday == ClientHoliday.FIRST_APRIL) {
            this.audioPlayer.playResource("sounds/start.mp3");
            this.audioPlayer.setVolume(1.0);
            this.audioPlayer.setCycleCount(10);
         } else if (currentHoliday == ClientHoliday.CHRISTMAS_24) {
            this.audioPlayer.playResource("sounds/christmas24.mp3");
            this.audioPlayer.setVolume(1.0);
            this.audioPlayer.setCycleCount(10);
         } else if (currentHoliday == ClientHoliday.CHRISTMAS_25) {
            this.audioPlayer.playResource("sounds/christmas25.mp3");
            this.audioPlayer.setVolume(1.0);
            this.audioPlayer.setCycleCount(10);
         } else if (currentHoliday == ClientHoliday.CHRISTMAS_26) {
            this.audioPlayer.playResource("sounds/christmas26.mp3");
            this.audioPlayer.setVolume(1.0);
            this.audioPlayer.setCycleCount(10);
         } else if (currentHoliday == ClientHoliday.MINIMI) {
            this.audioPlayer.playResource("sounds/hackersong.mp3");
            this.audioPlayer.setVolume(1.0);
            this.audioPlayer.setCycleCount(10);
         } else {
            File soundsDir = new File(ClientPaths.CLIENT_DIR + "/sounds");
            ClientFileUtil.ensureDirectory(ClientPaths.CLIENT_DIR + "/sounds");
            File[] soundFiles = soundsDir.listFiles();
            if (soundFiles != null) {
               for (File soundFile : soundFiles) {
                  if (soundFile.getName().split("\\.")[0].equals("start")) {
                     this.audioPlayer.playFile(soundFile.getAbsolutePath());
                     this.audioPlayer.setVolume(0.6);
                     this.audioPlayer.setCycleCount(10);
                     break;
                  }
               }
            }
         }
      } catch (Exception ignored) {
      }

      OpenGlHelper.initializeTextures();
            this.framebufferMc = new ResizableFramebuffer(this.displayWidth, this.displayHeight, true);
            this.blurFramebuffer = new ResizableFramebuffer(this.displayWidth, this.displayHeight, true);
            this.framebufferMc.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
            this.blurFramebuffer.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
            this.registerMetadataSerializers();
            this.mcResourcePackRepository = new ResourcePackRepository(
               this.fileResourcepacks, new File(this.mcDataDir, "server-resource-packs"), this.mcDefaultResourcePack, this.metadataSerializer_, this.gameSettings
            );
            this.mcResourceManager = new SimpleReloadableResourceManager(this.metadataSerializer_);
            this.mcLanguageManager = new LanguageManager(this.metadataSerializer_, this.gameSettings.language);
            this.mcResourceManager.registerReloadListener(this.mcLanguageManager);
            this.refreshResources();
            this.renderEngine = new TextureManager(this.mcResourceManager);
            this.mcResourceManager.registerReloadListener(this.renderEngine);
            this.drawSplashScreen(this.renderEngine);
            this.skinManager = new SkinManager(this.renderEngine, new File(this.fileAssets, "skins"), this.sessionService);
            this.saveLoader = new AnvilSaveConverter(new File(this.mcDataDir, "saves"));
            this.mcSoundHandler = new SoundHandler(this.mcResourceManager, this.gameSettings);
            this.mcResourceManager.registerReloadListener(this.mcSoundHandler);
            this.mcMusicTicker = new MusicTicker(this);
            this.fontRendererObj = new FontRenderer(this.gameSettings, new ResourceLocation("textures/font/ascii.png"), this.renderEngine, false);
            mc = this;

if (this.gameSettings.language != null) {
               this.fontRendererObj.setUnicodeFlag(this.isUnicode());
               this.fontRendererObj.setBidiFlag(this.mcLanguageManager.isCurrentLanguageBidirectional());
            }

            this.standardGalacticFontRenderer = new FontRenderer(this.gameSettings, new ResourceLocation("textures/font/ascii_sga.png"), this.renderEngine, false);
            this.mcResourceManager.registerReloadListener(this.fontRendererObj);
            this.mcResourceManager.registerReloadListener(this.standardGalacticFontRenderer);
            this.mcResourceManager.registerReloadListener(new GrassColorReloadListener());
            this.mcResourceManager.registerReloadListener(new FoliageColorReloadListener());
            AchievementList.openInventory.setStatStringFormatter(new Minecraft.DefaultStatStringFormat(this));
            this.mouseHelper = new RawMouseHelper();
            this.checkGLError("Pre startup");
            GlStateManager.enableTexture2D();
            GlStateManager.shadeModel(7425);
            GlStateManager.clearDepth(1.0);
            GlStateManager.enableDepth();
            GlStateManager.depthFunc(515);
            GlStateManager.enableAlpha();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.cullFace(1029);
            GlStateManager.matrixMode(5889);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            this.checkGLError("Startup");
            this.textureMapBlocks = new TextureMap("textures");
            this.textureMapBlocks.setMipmapLevels(this.gameSettings.mipmapLevels);
            this.renderEngine.loadTickableTexture(TextureMap.locationBlocksTexture, this.textureMapBlocks);

         this.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
         mc = this;

      int mipmapLevels = this.gameSettings.mipmapLevels;
      mipmapLevels = this.gameSettings.mipmapLevels > 0 ? 1 : 0;

      mc.textureMapBlocks.setBlurMipmapDirect(false, (mipmapLevels != 0));
         this.modelManager = new ModelManager(this.textureMapBlocks);
         this.mcResourceManager.registerReloadListener(this.modelManager);
         this.renderItem = new RenderItem(this.renderEngine, this.modelManager);
         this.renderManager = new RenderManager(this.renderEngine, this.renderItem);
         this.itemRenderer = new ItemRenderer(this);
         this.mcResourceManager.registerReloadListener(this.renderItem);
         this.entityRenderer = new EntityRenderer(this, this.mcResourceManager);
         this.mcResourceManager.registerReloadListener(this.entityRenderer);
         this.blockRenderDispatcher = new BlockRendererDispatcher(this.modelManager.getBlockModelShapes(), this.gameSettings);
         this.mcResourceManager.registerReloadListener(this.blockRenderDispatcher);
         this.renderGlobal = new RenderGlobal(this);
         this.mcResourceManager.registerReloadListener(this.renderGlobal);
         this.guiAchievement = new GuiAchievement(this);
         GlStateManager.viewport(0, 0, this.displayWidth, this.displayHeight);
         this.effectRenderer = new EffectRenderer(this.theWorld, this.renderEngine);
         this.checkGLError("Post startup");
         this.ingameGUI = new GuiIngameHook(this);
         Augustus.getInstance().preStart();
         Augustus.getInstance().start();
         Runtime.getRuntime().addShutdownHook(new Thread(Augustus.getInstance()::getMouseWheelTracker));
         mc = this;
if (this.serverName != null) {
this.displayGuiScreen(new GuiConnecting(new GuiMainMenu(), this, this.serverName, this.serverPort));
} else {
mc = this;

         mc.displayGuiScreen(new GuiMainMenu());
}

      this.renderEngine.deleteTexture(this.mojangLogo);
      this.mojangLogo = null;
      this.loadingScreen = new LoadingScreenRenderer(this);
      if (this.gameSettings.fullScreen && !this.fullscreen) {
         this.toggleFullscreen();
      }

      try {
         Display.setVSyncEnabled(this.gameSettings.enableVsync);
      } catch (OpenGLException e) {
         this.gameSettings.enableVsync = false;
         this.gameSettings.saveOptions();
      }

      this.renderGlobal.makeEntityOutlineShader();
      BaritoneAPI.getProvider().getPrimaryBaritone();
   }

   public static boolean isAmbientOcclusionEnabled() {
      
      Minecraft mc = theMinecraft;
      if (theMinecraft == null) {
            return false;
         }

         mc = theMinecraft;

      return ((mc.gameSettings.ambientOcclusion != 0 ? 1 : 0) != 0);
   }

   private void runGameLoop() throws IOException {
      boolean flag = false, flag2 = false, flag3 = false;
      int n = 0;
      boolean flag4;
      long l;
      l = System.nanoTime();
      this.mcProfiler.startSection("root");
      if (Display.isCreated() && Display.isCloseRequested()) {
         this.shutdown();
      }

      if (this.isGamePaused && this.theWorld != null) {
         float renderPartialTicks = this.timer.renderPartialTicks;
         this.timer.updateTimer();
         this.timer.renderPartialTicks = renderPartialTicks;
         float renderPartialTicks2 = this.realTimer.renderPartialTicks;
         this.realTimer.updateTimer();
         this.realTimer.renderPartialTicks = renderPartialTicks2;
      } else {
         this.timer.updateTimer();
         this.realTimer.updateTimer();
      }

      float f;
      f = this.timer.renderPartialTicks;
         MouseWheelTracker mouseWheelTracker = Augustus.getInstance().getMouseWheelTracker();
if (mouseWheelTracker != null) {
mouseWheelTracker = Augustus.getInstance().getMouseWheelTracker();

         mouseWheelTracker.pollMouseWheel();
}

      Minecraft mc2;
         int n2 = (ClientManagers.moduleManager.timeManipulation.isToggled()) ? 1 : 0;
if (n2 != 0) {
n2 = this.timer.elapsedTicks;

               int n3 = n2;
                     int elapsedTicks = this.realTimer.elapsedTicks;
                     n = this.realTimer.elapsedTicks;
                     byte b = 1;
                     if (this.realTimer.elapsedTicks >= 1) {
                           this.projectedRealTicks = this.realElapsedTicks + (long)elapsedTicks;
                           this.projectedGameTicks = this.gameElapsedTicks + (long)n3;
                        }

                        n = this.realTimer.elapsedTicks;
b = 1;

                     if (n >= b) {
                        int n4 = (int)(this.projectedRealTicks - this.projectedGameTicks);
                        this.skipGameTick = ClientManagers.moduleManager.timeManipulation.shouldChargeTick(n4);
                        n = (this.skipGameTick) ? 1 : 0;

if (this.skipGameTick) {
                           this.projectedGameTicks = this.projectedGameTicks - (long)n3;
                        }
                     }

                     mc2 = this;

n = (this.skipGameTick) ? 1 : 0;

                  if (n != 0) {
                     this.timer.elapsedTicks = 0;
                     this.timer.renderPartialTicks = 0.0F;
                  }

                  mc2 = this;

               int n5 = (int)((mc2.gameElapsedTicks + (long)this.timer.elapsedTicks) - (this.realElapsedTicks + (long)this.realTimer.elapsedTicks));
               mc2 = this;

            if (!this.skipGameTick) {
               if ((double)n5 < ClientManagers.moduleManager.timeManipulation.minTicksAvailable.getValue() - 1.0 && this.theWorld != null) {
                  EventTimer event = TimerListener.onTimer(0, this.realTimer.timerSpeed, TimerEventType.TIMER);
                  this.timer.timerSpeed = event.getTimerSpeed();
                  if (this.timer.elapsedTicks >= 1) {
                     event = TimerListener.onTimer(0, this.realTimer.timerSpeed, TimerEventType.TICK);
                     this.timer.elapsedTicks += (int)Math.min(
                        (double)event.getExtraTicks(),
                        ClientManagers.moduleManager.timeManipulation.minTicksAvailable.getValue() - (double)n5
                     );
                  }
               } else {
                  this.timer.timerSpeed = this.realTimer.timerSpeed;
                  if ((double)n5 < ClientManagers.moduleManager.timeManipulation.minTicksAvailable.getValue() && this.realTimer.elapsedTicks >= 1) {
                     boolean hadElapsedTicks = this.timer.elapsedTicks >= 1;
                     this.timer.copyFrom(this.realTimer);
                     if (hadElapsedTicks) {
                        this.timer.elapsedTicks -= n5;
                     }
                  }
               }
            }
}

            this.realElapsedTicks = this.realElapsedTicks + (long)this.realTimer.elapsedTicks;
            this.gameElapsedTicks = this.gameElapsedTicks + (long)this.timer.elapsedTicks;
            this.realTotalTicks = (float)this.realElapsedTicks + this.realTimer.elapsedPartialTicks;
            this.gameTotalTicks = (float)this.gameElapsedTicks + this.timer.elapsedPartialTicks;
            mc2 = this;

         mc2.mcProfiler.startSection("scheduledExecutables");

      EventGameLoop event2;
      event2 = null;
         Minecraft mc3 = this;
if (this.thePlayer != null) {
mc3 = this;
if (mc3.theWorld != null) {
mc3 = this;

         event2 = GameLoopListener.onGameLoop(mc3.timer.elapsedTicks, this.timer.renderPartialTicks);
         this.timer.elapsedTicks = event2.getElapsedTicks();
         this.timer.renderPartialTicks = event2.getRenderPartialTicks();
}
}

      Minecraft mc4;
      mc4 = this;
      if (this.timer.elapsedTicks >= 1 && Augustus.getInstance().getModuleManager() != null) {
            mc4 = this;

if (this.theWorld != null) {
               mc4 = this;

               if (this.thePlayer != null) {
                  String macroMode = ClientManagers.moduleManager.packetHandler.macroDelayModes.getSelectedValue();
                  if (macroMode.equals("OtherMacroDelay") || macroMode.equals("ExperimentalMacroDelay")) {
                     PacketFlushMarker packetFlushMarker = new PacketFlushMarker();
                     packetFlushMarker.tickBoundary = true;
                     this.getNetHandler().getNetworkManager().receivePacketAsync(packetFlushMarker);
                     ClientManagers.moduleManager.packetHandler.setFlushingPackets(false);
                  }
               }
            }
         }

         mc4 = this;

      synchronized (mc4.priorityScheduledTasks) {
         while (!this.priorityScheduledTasks.isEmpty()) {
            Util.runTask((FutureTask)this.priorityScheduledTasks.poll(), LOGGER);
            continue;
            
         }
      }

      boolean experimentalFlushing = ClientManagers.moduleManager.packetHandler.macroDelayModes.getSelectedValue().equals("ExperimentalMacroDelay")
         && ClientManagers.moduleManager.packetHandler.isFlushingPackets();
      if (!experimentalFlushing && ClientManagers.moduleManager.packetHandler.eventToHandlePacket.getSelectedValue().equals("Vanilla")) {
         boolean skipForTimeManip = ClientManagers.moduleManager.timeManipulation.isToggled()
            && this.skipGameTick
            && !ClientManagers.moduleManager.timeManipulation.updateEntityPositions.getValue()
            && ClientManagers.moduleManager.timeManipulation.correct.getValue();
         boolean skipForRender = event2 != null && event2.isSkipRender() && event2.isAllowSkipRender();
         if (!skipForTimeManip && !skipForRender) {
            this.runScheduledTasks();
         }
      }

               mc4 = this;

flag2 = this.skipGameTick;

            if (flag2) {
byte b2 = (byte)((ClientManagers.moduleManager.timeManipulation.updateEntityPositions.getValue()) ? 1 : 0);
if (b2 != 0) {
b2 = 0;

               int n9 = b2;

               while (n9 < this.realTimer.elapsedTicks) {
                  ClientManagers.moduleManager.timeManipulation.updateOtherEntities();
                  n9++;

}
}
}

            mc4 = this;

         mc4.mcProfiler.endSection();

      long l7 = System.nanoTime();
      this.mcProfiler.startSection("tick");
      int n10 = this.timer.elapsedTicks - 1;
      ClientManagers.moduleManager.rotationHandler.framesSinceLastTick++;
      mc4 = this;
      if (this.timer.elapsedTicks > 0) {
            TickListener.postTick(TickPhase.PRE);
            int n11 = 0;

            while (true) {
               if (n11 < this.timer.elapsedTicks) {
                  this.runTick(n10 - n11, n11);
                  n11++;

continue;
               }

               int elapsedTicks4 = this.timer.elapsedTicks;
               elapsedTicks4 = this.timer.elapsedTicks > 1 ? 1 : 0;

               this.ranMultipleTicks = (elapsedTicks4 != 0);
               TickListener.postTick(TickPhase.POST);
               ClientManagers.moduleManager.rotationHandler.framesPerTick = ClientManagers.moduleManager.rotationHandler.framesSinceLastTick;
               ClientManagers.moduleManager.rotationHandler.framesSinceLastTick = 0;
               break;
            }

            this.previousScreen = this.currentScreen;
         }

         mc4 = this;

      long l8;
      mc4.mcProfiler.endStartSection("preRenderErrors");
         l8 = System.nanoTime() - l7;
         this.checkGLError("Pre render");
         this.mcProfiler.endStartSection("sound");
         this.mcSoundHandler.setListener(this.thePlayer, this.timer.renderPartialTicks);
         this.mcProfiler.endSection();
         this.mcProfiler.startSection("render");
         GlStateManager.pushMatrix();
         GlStateManager.clear(16640);
         this.framebufferMc.bindFramebuffer(true);
         this.mcProfiler.startSection("display");
         GlStateManager.enableTexture2D();
         mc4 = this;
         if (this.thePlayer != null) {
               flag3 = this.thePlayer.isEntityInsideOpaqueBlock();

if (flag3) {
                  this.gameSettings.thirdPersonView = 0;
               }
            }

            this.mcProfiler.endSection();
            mc4 = this;

         flag3 = mc4.skipRenderWorld;

      if (!flag3) {
         this.mcProfiler.endStartSection("gameRenderer");
         this.entityRenderer.updateCameraAndRender(this.timer.renderPartialTicks, l, this.lastRenderTime);
         this.mcProfiler.endSection();
      }

      this.mcProfiler.endSection();
      if (this.gameSettings.showDebugInfo && this.gameSettings.showDebugProfilerChart && !this.gameSettings.hideGUI) {
         if (!this.mcProfiler.profilingEnabled) {
            this.mcProfiler.clearProfiling();
         }

         this.mcProfiler.profilingEnabled = true;
         this.displayDebugInfo(l8);
      } else {
         this.mcProfiler.profilingEnabled = false;
         this.prevFrameTime = System.nanoTime();
      }

      boolean isToggled = ClientManagers.moduleManager.antiAchievement.isToggled();
         if (!isToggled) {
               this.guiAchievement.updateAchievementWindow();
            }

            this.framebufferMc.unbindFramebuffer();
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            this.framebufferMc.framebufferRender(this.displayWidth, this.displayHeight);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.popMatrix();
            this.mcProfiler.startSection("root");
isToggled = GpuVendorDetector.isNvidia();

         if (isToggled) {
MotionBlur motionBlur = ClientManagers.moduleManager.motionBlur;
if (ClientManagers.moduleManager.motionBlur.isToggled()) {
motionBlur = ClientManagers.moduleManager.motionBlur;

            motionBlur.applyMotionBlur();
}
}

         this.updateDisplay();
         Thread.yield();
         this.mcProfiler.startSection("stream");
         this.mcProfiler.startSection("update");
         this.mcProfiler.endStartSection("submit");
         this.mcProfiler.endSection();
         this.mcProfiler.endSection();
         this.checkGLError("Post render");
         this.fpsCounter = this.fpsCounter + 1;

      boolean flag8;
      Minecraft mc5 = this;
if (this.isSingleplayer()) {
mc5 = this;

            GuiScreen currentScreen3 = mc5.currentScreen;
if (mc5.currentScreen != null) {
currentScreen3 = this.currentScreen;

            flag8 = currentScreen3.doesGuiPauseGame();
if (flag8) {
flag8 = this.theIntegratedServer.getPublic();
if (!flag8) {
flag8 = true;
} else {
flag8 = false;
}
} else {
flag8 = false;
}
} else {
flag8 = false;
}
} else {
flag8 = false;
}

      this.isGamePaused = flag8;
      long l10 = System.nanoTime();
      this.frameTimer.addFrame(l10 - this.startNanoTime);
      this.startNanoTime = l10;

      while (true) {
         if (getSystemTime() >= this.debugUpdateTime + 1000L) {
            debugFPS = this.fpsCounter;
            mc4 = this;

Object[] array;
            Object obj;
            array = new Object[]{debugFPS, RenderChunk.renderChunksUpdated, RenderChunk.renderChunksUpdated != 1 ? "s" : "", null, null, null, null, null};
               float f2;
               int n15 = (f2 = (float)this.gameSettings.limitFramerate - GameSettings.Options.FRAMERATE_LIMIT.getValueMax()) == 0.0F
                  ? 0
                  : (f2 < 0.0F ? -1 : 1);
if (n15 == 0) {
obj = "inf";
} else {
n15 = this.gameSettings.limitFramerate;

               obj = n15;
}

            array[3] = obj;
               array[4] = this.gameSettings.enableVsync ? " vsync" : "";
               array[5] = this.gameSettings.fancyGraphics ? "" : " fast";
               int clouds = this.gameSettings.clouds;
if (this.gameSettings.clouds == 0) {
obj = "";
} else {
clouds = this.gameSettings.clouds;

               obj = clouds == 1 ? " fast-clouds" : " fancy-clouds";
}

            array[6] = obj;
            array[7] = OpenGlHelper.useVbo() ? " vbo" : "";
            this.debug = String.format("%d fps (%d chunk update%s) T: %s%s%s%s%s", array);
            RenderChunk.renderChunksUpdated = 0;
            this.debugUpdateTime = this.debugUpdateTime + 1000L;
            this.fpsCounter = (byte)0;
            continue;
         }

         mc4 = this;
         break;
      }

      boolean flag10 = mc4.isFramerateLimitBelowMax();
if (flag10) {
this.mcProfiler.startSection("fpslimit_wait");
                  Display.sync(this.getLimitFramerate());
                  this.mcProfiler.endSection();
} else {
mc4 = this;

flag10 = this.gameSettings.enableVsync;

            if (flag10) {
byte b4 = (byte)((Display.isVisible()) ? 1 : 0);
if (b4 == 0) {
b4 = 60;

               Display.sync(b4);
}
}
}

         this.mcProfiler.endSection();
         mc4 = this;

      mc4.timer.renderPartialTicks = f;
   }

   public ListenableFuture addScheduledTask(Callable callable) {
      Validate.notNull(callable);
      if (!this.isCallingFromMinecraftThread()) {
         ListenableFutureTask listenableFutureTask = ListenableFutureTask.create(callable);
         synchronized (this.scheduledTasks) {
            this.scheduledTasks.add(listenableFutureTask);
            return listenableFutureTask;
         }
      }

      try {
         return Futures.immediateFuture(callable.call());
      } catch (Exception e) {
         return Futures.immediateFailedFuture(e);
      }
   }

   private void createDisplay() throws LWJGLException {
      Display.setResizable(true);
      
      Display.setTitle(Augustus.getInstance().getName() + " " + Augustus.getInstance().getVersion());

      try {
         Display.create(new PixelFormat().withDepthBits(24));
      } catch (LWJGLException e) {
         LOGGER.error("Couldn't set pixel format", e);

         try {
            Thread.sleep(1000L);
         } catch (InterruptedException e2) {
         }

         Minecraft mc = this;
if (this.fullscreen) {
mc = this;

            mc.updateDisplayMode();
}

         Display.create();
      }
   }

   public ByteBuffer readImageToBuffer(InputStream in) throws IOException {
      BufferedImage image = ImageIO.read(in);
      int[] rgb = image.getRGB(0, 0, image.getWidth(), image.getHeight(), (int[])null, 0, image.getWidth());
      
      ByteBuffer buf = ByteBuffer.allocate(4 * rgb.length);
      
      int length = rgb.length;
      int n = 0;

      while (true) {
         if (n < length) {
            int n2 = rgb[n];
            buf.putInt(n2 << 8 | n2 >> 24 & 0xFF);
            n++;

continue;
         }

         ((Buffer)buf).flip();
         break;
      }

      return buf;
   }

   public void freeMemory() {
      try {
         memoryReserve = new byte[0];
         this.renderGlobal.deleteAllDisplayLists();
      } catch (Throwable e) {
      }

      try {
         System.gc();
         this.loadWorld((WorldClient)null);
      } catch (Throwable e2) {
      }

      System.gc();
   }

   public ItemRenderer getItemRenderer() {
      return this.itemRenderer;
   }

   public Minecraft(GameConfiguration gameConfiguration) {
      super();
      boolean flag;
      Proxy proxy2;
      this.audioPlayer = new JavaFxAudioPlayer();
         this.realElapsedTicks = 0L;
         this.gameElapsedTicks = 0L;
         this.projectedRealTicks = 0L;
         this.projectedGameTicks = 0L;
         this.realTotalTicks = 0.0F;
         this.gameTotalTicks = 0.0F;
         this.enableGLErrorChecking = true;
         
         this.connectedToRealms = false;
         this.timer = new Timer(20.0F);
         this.realTimer = new Timer(20.0F);
         this.ticksLookingAtEntity = (byte)0;
         this.ticksNotLookingAtEntity = (byte)0;
         this.systemTime = getSystemTime();
         this.frameTimer = new FrameTimer();
         this.startNanoTime = System.nanoTime();
         this.mcProfiler = new Profiler();
         this.debugCrashKeyPressTime = -1L;
         this.metadataSerializer_ = new IMetadataSerializer();
         this.defaultResourcePacks = Lists.newArrayList();
         this.scheduledTasks = Queues.newArrayDeque();
         this.priorityScheduledTasks = Queues.newArrayDeque();
         this.mcThread = Thread.currentThread();
         this.running = true;
         this.debug = "";
         this.renderChunksMany = true;
         this.wasLeftClicking = false;
         this.debugUpdateTime = getSystemTime();
         this.prevFrameTime = -1L;
         this.debugProfilerName = "root";
         this.ticksWithoutScreen = (byte)0;
         this.skipGameTick = false;
         this.lastRenderTime = getSystemTime();
         this.tickTimeWindow = new DoubleSampleWindow(40);
         this.frameTimeWindow = new DoubleSampleWindow(40);
         this.ranMultipleTicks = false;
         
         this.remainingTicksInFrame = (byte)0;
         this.tickHistory = new ArrayList();
         theMinecraft = this;
         this.mcDataDir = gameConfiguration.folderInfo.mcDataDir;
         this.fileAssets = gameConfiguration.folderInfo.assetsDir;
         this.fileResourcepacks = gameConfiguration.folderInfo.resourcePacksDir;
         this.version = gameConfiguration.gameInfo.version;
         this.twitchDetails = gameConfiguration.userInfo.profileProperties;
         this.mcDefaultResourcePack = new DefaultResourcePack(new ResourceIndex(gameConfiguration.folderInfo.assetsDir, gameConfiguration.folderInfo.assetIndex).getResourceMap());
         GameConfiguration.UserInformation userInfo = gameConfiguration.userInfo;
if (gameConfiguration.userInfo.proxy == null) {
proxy2 = Proxy.NO_PROXY;
} else {
userInfo = gameConfiguration.userInfo;

         proxy2 = userInfo.proxy;
}

      this.proxy = proxy2;
      this.sessionService = new YggdrasilAuthenticationService(gameConfiguration.userInfo.proxy, UUID.randomUUID().toString()).createMinecraftSessionService();
      this.session = gameConfiguration.userInfo.session;
      LOGGER.info("Setting user: " + this.session.getUsername());
      LOGGER.info("(Session ID is " + this.session.getSessionID() + ")");
      this.isDemo = gameConfiguration.gameInfo.isDemo;
      int width = gameConfiguration.displayInfo.width;
      width = gameConfiguration.displayInfo.width > 0 ? gameConfiguration.displayInfo.width : 1;

      this.displayWidth = width;
      int height = gameConfiguration.displayInfo.height;
      height = gameConfiguration.displayInfo.height > 0 ? gameConfiguration.displayInfo.height : 1;

      this.displayHeight = height;
      this.tempDisplayWidth = gameConfiguration.displayInfo.width;
      this.tempDisplayHeight = gameConfiguration.displayInfo.height;
      this.fullscreen = gameConfiguration.displayInfo.fullscreen;
      this.java64bit = isJvm64bit();
      this.theIntegratedServer = new IntegratedServer(this);
      if (gameConfiguration.serverInfo.serverName != null) {
            this.serverName = gameConfiguration.serverInfo.serverName;
            this.serverPort = gameConfiguration.serverInfo.serverPort;
         }

         ImageIO.setUseCache(false);
         Bootstrap.register();

}

   public ServerData getCurrentServerData() {
      return this.currentServerData;
   }

   private void resize(int n, int n2) {
      this.displayWidth = Math.max(1, n);
      
      this.displayHeight = Math.max(1, n2);
      WindowResizeListener.postWindowResize(this.displayWidth, this.displayHeight);
      Minecraft mc = this;
      if (this.currentScreen != null) {
            ScaledResolution sr = new ScaledResolution(this);
            this.currentScreen.onResize(this, sr.getScaledWidth(), sr.getScaledHeight());
         }

         this.loadingScreen = new LoadingScreenRenderer(this);
         mc = this;

      mc.updateFramebufferSize();
   }

   private void checkGLError(String str) {
      
      int n = (this.enableGLErrorChecking) ? 1 : 0;
      if (!this.enableGLErrorChecking) {
            return;
         }

         n = GL11.glGetError();

      int n2 = n;
      n = n2;
      if (n2 == 0) {
            return;
         }

         n = n2;

      String str2 = GLU.gluErrorString(n);
      LOGGER.error("########## GL ERROR ##########");
      LOGGER.error("@ " + str);
      LOGGER.error(n2 + ": " + str2);
   }

   public void run() {
      this.running = true;

      try {
         this.startGame();
      } catch (Throwable e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Initializing game");
         crashReport.makeCategory("Initialization");
         this.displayCrashReport(this.addGraphicsAndWorldToCrashReport(crashReport));
         return;
      }

      try {
         while (this.running) {
            if (!this.hasCrashed || this.crashReporter == null) {
               try {
                  this.runGameLoop();
               } catch (OutOfMemoryError e2) {
                  this.freeMemory();
                  this.displayGuiScreen(new GuiMemoryErrorScreen());
                  System.gc();
               }
            } else {
               this.displayCrashReport(this.crashReporter);
            }
         }
      } catch (MinecraftError e3) {
      } catch (ReportedException e4) {
         this.addGraphicsAndWorldToCrashReport(e4.getCrashReport());
         this.freeMemory();
         LOGGER.fatal("Reported exception thrown!", e4);
         this.displayCrashReport(e4.getCrashReport());
      } catch (Throwable e5) {
         CrashReport crashReport2 = this.addGraphicsAndWorldToCrashReport(new CrashReport("Unexpected error", e5));
         this.freeMemory();
         LOGGER.fatal("Unreported exception thrown!", e5);
         this.displayCrashReport(crashReport2);
      } finally {
         this.shutdownMinecraftApplet();
      }
   }

   @Override
   public ListenableFuture addScheduledTaskDirect(Runnable runnable) {
      Validate.notNull(runnable);
      return this.addPriorityScheduledTask(Executors.callable(runnable));
   }

   public boolean isJava64bit() {
      return this.java64bit;
   }

   public MusicTicker getMcMusicTicker() {
      return this.mcMusicTicker;
   }

   public void setRenderViewEntity(Entity entity) {
      this.renderViewEntity = entity;
      this.entityRenderer.loadEntityShader(entity);
   }

   public boolean isSnooperEnabled() {
      return this.gameSettings.snooperEnabled;
   }

   private String getConnectionState() {
      
      Minecraft mc = this;
      if (this.theIntegratedServer != null) {
            return this.theIntegratedServer.getPublic() ? "hosting_lan" : "singleplayer";
         }

         mc = this;

      ServerData currentServerData2 = mc.currentServerData;
      if (mc.currentServerData == null) {
            return "out_of_game";
         }

         currentServerData2 = this.currentServerData;

      return currentServerData2.isLanServer() ? "playing_lan" : "multiplayer";
   }

   private void setInitialDisplayMode() throws LWJGLException {
      boolean fullscreen2 = this.fullscreen;
if (this.fullscreen) {
fullscreen2 = true;

         Display.setFullscreen(fullscreen2);
         DisplayMode displayMode = Display.getDisplayMode();
         this.displayWidth = Math.max(1, displayMode.getWidth());
         this.displayHeight = Math.max(1, displayMode.getHeight());
         return;
}

      Display.setDisplayMode(new DisplayMode(this.displayWidth, this.displayHeight));
   }

   public static LanguageManager accessMcLanguageManager(Minecraft mc) {
      return mc.mcLanguageManager;
   }

   public RenderItem getRenderItem() {
      return this.renderItem;
   }

   public Timer getRealTimer() {
      return this.realTimer;
   }

   public static void stopIntegratedServer() {
      
      Minecraft mc = theMinecraft;
      if (theMinecraft == null) {
            return;
         }

         mc = theMinecraft;

      IntegratedServer integratedServer = mc.getIntegratedServer();
      IntegratedServer integratedServer2 = integratedServer;
      if (integratedServer == null) {
            return;
         }

         integratedServer2 = integratedServer;

      integratedServer2.stopServer();
   }

   public FrameTimer getFrameTimer() {
      return this.frameTimer;
   }

   public void checkWindowResize() {
      Minecraft mc = null;
      
      int n = (this.fullscreen) ? 1 : 0;
      if (this.fullscreen) {
            return;
         }

         n = (Display.wasResized()) ? 1 : 0;

      if (n == 0) {
            return;
         }

         n = this.displayWidth;

      int n2 = n;
      int displayHeight2 = this.displayHeight;
      this.displayWidth = Display.getWidth();
      this.displayHeight = Display.getHeight();
      n = this.displayWidth;
      if (this.displayWidth == n2) {
            n = this.displayHeight;

if (this.displayHeight == displayHeight2) {
               return;
            }
         }

         n = this.displayWidth;

      if (n <= 0) {
               this.displayWidth = 1;
            }

            mc = this;
n = this.displayHeight;

         if (n <= 0) {
            this.displayHeight = 1;
         }

         mc = this;

      int displayHeight3 = this.displayHeight;
      mc.resize(this.displayWidth, displayHeight3);
   }

   public static int getDebugFPS() {
      return debugFPS;
   }

   public Entity getRenderViewEntity() {
      return this.renderViewEntity;
   }

   public void setDimensionAndSpawnPlayer(int n) {
      boolean flag;
      int n2;
      String str;
      this.theWorld.setInitialSpawnLocation();
         this.theWorld.removeAllEntities();
         n2 = 0;
         
         str = null;
         
         EntityPlayerSP thePlayer2 = this.thePlayer;
if (this.thePlayer != null) {
n2 = this.thePlayer.getEntityId();
            this.theWorld.removeEntity(this.thePlayer);
            thePlayer2 = this.thePlayer;

         str = thePlayer2.getClientBrand();
}

      EntityPlayerSP player;
      StatFileWriter statFileWriter;
      PlayerControllerMP playerControllerMP;
      WorldClient world;
      this.renderViewEntity = (Entity)null;
         player = this.thePlayer;
         playerControllerMP = this.playerController;
         world = this.theWorld;
         EntityPlayerSP thePlayer3 = this.thePlayer;
if (this.thePlayer == null) {
statFileWriter = new StatFileWriter();
} else {
thePlayer3 = this.thePlayer;

         statFileWriter = thePlayer3.getStatFileWriter();
}

      this.thePlayer = playerControllerMP.createClientPlayer(world, statFileWriter);
      this.thePlayer.getDataWatcher().updateWatchedObjectsFromList(player.getDataWatcher().getAllWatched());
      this.thePlayer.dimension = n;
      this.renderViewEntity = this.thePlayer;
      this.thePlayer.preparePlayerToSpawn();
      this.thePlayer.setClientBrand(str);
      this.theWorld.spawnEntityInWorld(this.thePlayer);
      this.playerController.flipPlayer(this.thePlayer);
      this.thePlayer.movementInput = new MovementInputFromOptions(this.gameSettings);
      this.thePlayer.setEntityId(n2);
      this.playerController.setPlayerCapabilities(this.thePlayer);
      this.thePlayer.setReducedDebug(player.hasReducedDebug());
      Minecraft mc = this;
      if (!(this.currentScreen instanceof GuiGameOver)) {
            return;
         }

         mc = this;

      mc.displayGuiScreen((GuiScreen)null);
   }

   public boolean isNotInGui() {
      boolean flag;
      Minecraft mc;
      
         GuiScreen currentScreen2 = this.currentScreen;
         if (this.currentScreen != null) {
               return false;
            }

            mc = this;
currentScreen2 = this.previousScreen;

         if (currentScreen2 != null) {
            return false;
         }

         mc = this;

      EntityPlayerSP thePlayer2 = mc.thePlayer;
      if (mc.thePlayer == null) {
            return false;
         }

         thePlayer2 = this.thePlayer;

      return thePlayer2.getSpoofedScreen() == null;
   }

   public ListenableFuture scheduleResourcesRefresh() {
      Runnable runnable = this::refreshResources;
      return this.addScheduledTask(runnable);
   }

   public void loadWorld(WorldClient world, String str) {
      Minecraft mc = null;
      boolean flag;
      WorldClient world2;
      
         if (Augustus.getInstance().getModuleManager().baritone.isToggled()) {
world2 = this.theWorld;
if (this.theWorld == null) {
world2 = world;
if (world2 != null) {
BaritoneAPI.getProvider().getPrimaryBaritone().getGameEventHandler().onWorldEvent(new WorldEvent(world, EventState.PRE));
}
} else {
BaritoneAPI.getProvider().getPrimaryBaritone().getGameEventHandler().onWorldEvent(new WorldEvent(world, EventState.PRE));
}
}

         world2 = world;

      if (world2 != this.theWorld) {
               this.entityRenderer.getMapItemRenderer().clearLoadedMaps();
            }

            LoadWorldListener.postLoadWorld(world);
world2 = world;

         if (world2 == null) {
            NetHandlerPlayClient netHandler = this.getNetHandler();
               NetHandlerPlayClient netHandlerPlayClient = netHandler;
if (netHandler != null) {
netHandlerPlayClient = netHandler;

               netHandlerPlayClient.cleanup();
}

            mc = this;
            if (this.theIntegratedServer != null) {
                  mc = this;

if (this.theIntegratedServer.isAnvilFileSet()) {
                     this.theIntegratedServer.initiateShutdown();
                     this.theIntegratedServer.setStaticInstance();
                  }
               }

               this.theIntegratedServer = (IntegratedServer)null;
               this.guiAchievement.clearAchievements();
               mc = this;

            mc.entityRenderer.getMapItemRenderer().clearLoadedMaps();
         }

         this.renderViewEntity = (Entity)null;
         this.myNetworkManager = (NetworkManager)null;

      LoadingScreenRenderer loadingScreen2 = this.loadingScreen;
if (this.loadingScreen != null) {
this.loadingScreen.resetProgressAndMessage(str);
            loadingScreen2 = this.loadingScreen;

         loadingScreen2.displayLoadingString("");
}

      world2 = world;
            if (world == null) {
                  world2 = this.theWorld;

if (this.theWorld != null) {
                     this.mcResourcePackRepository.clearResourcePack();
                     this.ingameGUI.resetPlayersOverlayFooterHeader();
                     this.setServerData((ServerData)null);
                     this.integratedServerIsRunning = false;
                  }
               }

               this.mcSoundHandler.stopSounds();
               this.theWorld = world;

world2 = world;
      if (world2 != null) {
         if (this.renderGlobal != null) {
            this.renderGlobal.setWorldAndLoadRenderers(world);
         }

         if (this.effectRenderer != null) {
            this.effectRenderer.clearEffects(world);
         }

         if (this.thePlayer == null) {
            this.thePlayer = this.playerController.createClientPlayer(world, new StatFileWriter());
            this.playerController.flipPlayer(this.thePlayer);
         }

         this.thePlayer.preparePlayerToSpawn();
         world.spawnEntityInWorld(this.thePlayer);
         this.thePlayer.movementInput = new MovementInputFromOptions(this.gameSettings);
         this.playerController.setPlayerCapabilities(this.thePlayer);
         this.renderViewEntity = this.thePlayer;

         try {
            WorldLoadWarmup.warmup();
         } catch (Throwable e) {
            e.printStackTrace();
         }
      } else {
         this.saveLoader.flushCache();
         this.thePlayer = null;
      }

      this.systemTime = 0L;
      if (Augustus.getInstance().getModuleManager().baritone.isToggled()) {
         BaritoneAPI.getProvider().getPrimaryBaritone().getGameEventHandler().onWorldEvent(new WorldEvent(world, EventState.POST));
      }
   }

   @Override
   public boolean isCallingFromMinecraftThread() {
      return Thread.currentThread() == this.mcThread;
   }

   private void registerMetadataSerializers() {
      this.metadataSerializer_.registerMetadataSectionType(new TextureMetadataSectionSerializer(), TextureMetadataSection.class);
      this.metadataSerializer_.registerMetadataSectionType(new FontMetadataSectionSerializer(), FontMetadataSection.class);
      this.metadataSerializer_.registerMetadataSectionType(new AnimationMetadataSectionSerializer(), AnimationMetadataSection.class);
      this.metadataSerializer_.registerMetadataSectionType(new PackMetadataSectionSerializer(), PackMetadataSection.class);
      this.metadataSerializer_.registerMetadataSectionType(new LanguageMetadataSectionSerializer(), LanguageMetadataSection.class);
   }

   private static boolean isJvm64bit() {
      String[] props = new String[]{"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"};
      for (String prop : props) {
         String value = System.getProperty(prop);
         if (value != null && value.contains("64")) {
            return true;
         }
      }
      return false;
   }

   public boolean isFramerateLimitBelowMax() {
      
      float f;
      int n = (f = (float)this.getLimitFramerate() - GameSettings.Options.FRAMERATE_LIMIT.getValueMax()) == 0.0F ? 0 : (f < 0.0F ? -1 : 1);
      n = n < 0 ? 1 : 0;

      return (n != 0);
   }

   public MusicTicker.MusicType getAmbientMusicType() {
      
      EntityPlayerSP thePlayer2 = this.thePlayer;
      if (this.thePlayer == null) {
            return MusicTicker.MusicType.MENU;
         }

         thePlayer2 = this.thePlayer;

      boolean flag = thePlayer2.entityWorld.provider instanceof WorldProviderHell;
      if (flag) {
            return MusicTicker.MusicType.NETHER;
         }

         flag = this.thePlayer.entityWorld.provider instanceof WorldProviderEnd;

      if (flag) {
            return BossStatus.bossName != null && BossStatus.statusBarTime > 0 ? MusicTicker.MusicType.END_BOSS : MusicTicker.MusicType.END;
         }

         flag = this.thePlayer.capabilities.isCreativeMode;

      if (!flag) {
            return MusicTicker.MusicType.GAME;
         }

         flag = this.thePlayer.capabilities.allowFlying;

      return flag ? MusicTicker.MusicType.CREATIVE : MusicTicker.MusicType.GAME;
   }

   public SkinManager getSkinManager() {
      return this.skinManager;
   }

   public SoundHandler getSoundHandler() {
      return this.mcSoundHandler;
   }

   public static boolean isGuiEnabled() {
      
      Minecraft mc = theMinecraft;
      if (theMinecraft == null) {
            return true;
         }

         mc = theMinecraft;

      return !mc.gameSettings.hideGUI;
   }

   public int getTicksWithoutScreen() {
      return this.ticksWithoutScreen;
   }

   public void sendClickBlockToController(boolean flag) {
      Minecraft mc = null;
      
      int n = (flag) ? 1 : 0;
      if (!flag) {
            this.leftClickCounter = (byte)0;
         }

         n = this.leftClickCounter;

      if (n <= 0) {
            n = (this.thePlayer.isUsingItem()) ? 1 : 0;

if (n == 0) {
if (flag) {
mc = this;
if (this.objectMouseOver != null) {
mc = this;
if (this.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
BlockPos blockPos = this.objectMouseOver.getBlockPos();
                              Minecraft mc2 = this;
if (this.theWorld.getBlockState(blockPos).getBlock().getMaterial() != Material.air) {
mc2 = this;

                              boolean flag2 = mc2.playerController.onPlayerDamageBlock(blockPos, this.objectMouseOver.sideHit);
if (flag2) {
flag2 = ClientManagers.moduleManager.noBlockParticles.isToggled();

                              if (!flag2) {
                                 this.effectRenderer.addBlockHitEffects(blockPos, this.objectMouseOver.sideHit);
                              }

                              this.thePlayer.swingItem();
}
}
} else {
mc = this;

               mc.playerController.resetBlockRemoving();
}
} else {
mc = this;

               mc.playerController.resetBlockRemoving();
}
} else {
mc = this;

               mc.playerController.resetBlockRemoving();
}
}
         }

         n = (ClientManagers.moduleManager.fixes.isToggled()) ? 1 : 0;

      if (n == 0) {
            return;
         }

         n = (ClientManagers.moduleManager.fixes.hitDelayFixMode.getSelectedValue().equals("WithoutDoubleClick")) ? 1 : 0;

      if (n != 0) {
         this.leftClickCounter = (byte)0;
      }
   }

   public void displayGuiScreen(GuiScreen screen) {
      int n = 0;
      
      Object currentScreen2 = this.currentScreen;
      if (this.currentScreen != null) {
            this.currentScreen.onGuiClosed();
         }

         currentScreen2 = screen;

      if (currentScreen2 == null && this.theWorld == null) {
screen = new GuiMainMenu();
} else {
currentScreen2 = screen;

if (currentScreen2 == null) {
                           float f;
                           n = (f = this.thePlayer.getCurrentHealth() - 0.0F) == 0.0F ? 0 : (f < 0.0F ? -1 : 1);

if (n <= 0) {
                              screen = new GuiGameOver();
                           }
                        }
}

                     currentScreen2 = screen;

n = (currentScreen2 instanceof GuiMainMenu) ? 1 : 0;

               if (n != 0) {
                  this.gameSettings.showDebugInfo = false;
                  this.ingameGUI.getChatGUI().clearChatMessages();
               }

               this.currentScreen = (GuiScreen)screen;
               this.lastDisplayedScreen = (GuiScreen)screen;

currentScreen2 = screen;
if (currentScreen2 != null) {
this.setIngameNotInFocus();
               ScaledResolution sr = new ScaledResolution(this);
               int scaledWidth = sr.getScaledWidth();
               int scaledHeight = sr.getScaledHeight();
               ((GuiScreen)screen).setWorldAndResolution(this, scaledWidth, scaledHeight);
               this.skipRenderWorld = false;
} else {
this.mcSoundHandler.resumeSounds();

         this.setIngameFocus();
}

      currentScreen2 = this.thePlayer;
      if (this.thePlayer == null) {
            return;
         }

         currentScreen2 = this.thePlayer;

      ((EntityPlayerSP)currentScreen2).setSpoofedScreen((GuiScreen)screen);
   }

   @Override
   public ListenableFuture addScheduledTask(Runnable runnable) {
      Validate.notNull(runnable);
      return this.addScheduledTask(Executors.callable(runnable));
   }

   public Framebuffer getFramebuffer() {
      return BlurShadowManager.isHudCompositionActive() ? this.blurFramebuffer : this.framebufferMc;
   }

   public NetHandlerPlayClient getNetHandler() {
      return this.thePlayer != null ? this.thePlayer.sendQueue : null;
   }

   public void sendClickBlockToControllerWithSlot(boolean flag, MovingObjectPosition hit, BlockPos pos, int n) {
      EntityPlayerSP player = null;
      Minecraft mc;
      this.hittingBlockCustom = false;
         
         int currentItem = this.thePlayer.inventory.currentItem;
         
         this.thePlayer.inventory.currentItem = n;
         int n2 = (flag) ? 1 : 0;
         if (!flag) {
               this.leftClickCounter = (byte)0;
            }

            mc = this;
n2 = this.leftClickCounter;

         if (n2 <= 0) {
               player = this.thePlayer;

            if (!this.thePlayer.isUsingItem()) {
               if (flag && hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                  if (this.theWorld.getBlockState(pos).getBlock().getMaterial() != Material.air
                     && this.playerController.onPlayerDamageBlockWithSlot(pos, hit.sideHit, n)) {
                     this.effectRenderer.addBlockHitEffects(pos, hit.sideHit);
                     this.thePlayer.swingItem();
                     this.hittingBlockCustom = true;
                  }
               } else {
                  boolean flag5 = !this.hittingBlockCustom && this.prevHittingBlockCustom;
                  this.playerController.abortBlockBreaking(flag5);
                  this.thePlayer.resetTicksSinceLastSwing();
               }
            }
            }

            player = this.thePlayer;

         player.inventory.currentItem = currentItem;
         mc = this;

      mc.hittingBlockCustom = this.prevHittingBlockCustom;
   }

   public void displayInGameMenu() {
      
      Minecraft mc = this;
      if (this.currentScreen != null) {
            return;
         }

         this.displayGuiScreen(new GuiIngameMenu());
         mc = this;

      boolean isSingleplayer = mc.isSingleplayer();
         if (!isSingleplayer) {
               return;
            }

            mc = this;
isSingleplayer = this.theIntegratedServer.getPublic();

         if (isSingleplayer) {
            return;
         }

         mc = this;

      mc.mcSoundHandler.pauseSounds();
   }

   public static boolean isFancyGraphicsEnabled() {
      
      Minecraft mc = theMinecraft;
      if (theMinecraft == null) {
            return false;
         }

         mc = theMinecraft;

      return mc.gameSettings.fancyGraphics;
   }

   public void launchIntegratedServer(String str, String str2, WorldSettings worldSettings) {
      boolean flag;
      WorldInfo worldInfo;
      WorldSettings worldSettings2;
      this.loadWorld((WorldClient)null);
         System.gc();
         ISaveHandler saveLoader2 = this.saveLoader.getSaveLoader(str, false);
         
         worldInfo = saveLoader2.loadWorldInfo();
         
         if (worldInfo == null) {
            worldSettings2 = worldSettings;

if (worldSettings != null) {
               worldInfo = new WorldInfo(worldSettings, str);
               saveLoader2.saveWorldInfo(worldInfo);
            }
         }

         worldSettings2 = worldSettings;

      if (worldSettings2 == null) {
worldSettings2 = new WorldSettings(worldInfo);

         worldSettings = worldSettings2;
}

      try {
         this.theIntegratedServer = new IntegratedServer(this, str, str2, worldSettings);
         this.theIntegratedServer.startServerThread();
         this.integratedServerIsRunning = true;
      } catch (Throwable e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Starting integrated server");
         CrashReportCategory crashReportCategory = crashReport.makeCategory("Starting integrated server");
         crashReportCategory.addCrashSection("Level ID", str);
         crashReportCategory.addCrashSection("Level Name", str2);
         throw new ReportedException(crashReport);
      }

      this.loadingScreen.displaySavingString(I18n.format("menu.loadingLevel", new Object[0]));

      while (true) {
         if (!this.theIntegratedServer.serverIsInRunLoop()) {
            String userMessage = this.theIntegratedServer.getUserMessage();

if (userMessage != null) {
this.loadingScreen.displayLoadingString(I18n.format(userMessage, new Object[0]));
} else {
this.loadingScreen.displayLoadingString("");
}

            try {
               Thread.sleep(200L);
            } catch (InterruptedException e2) {
            }

            continue;
         }

         this.displayGuiScreen((GuiScreen)null);
         break;
      }

      SocketAddress socketAddress = this.theIntegratedServer.getNetworkSystem().addLocalEndpoint();
      NetworkManager networkManager = NetworkManager.provideLocalClient(socketAddress);
      networkManager.setNetHandler(new NetHandlerLoginClient(networkManager, this, (GuiScreen)null));
      networkManager.sendPacket(new C00Handshake(47, socketAddress.toString(), 0, EnumConnectionState.LOGIN));
      networkManager.sendPacket(new C00PacketLoginStart(this.getSession().getProfile()));
      this.myNetworkManager = networkManager;
      DiscordRpcManager discordRpcManager = Augustus.getInstance().getDiscordRpcManager();
      String str3 = "Ingame";
      discordRpcManager.updatePresence("Playing lonely in singleplayer", str3);
   }

   public ListenableFuture addPriorityScheduledTask(Callable callable) {
      Validate.notNull(callable);
      if (!this.isCallingFromMinecraftThread()) {
         ListenableFutureTask listenableFutureTask = ListenableFutureTask.create(callable);
         synchronized (this.priorityScheduledTasks) {
            this.priorityScheduledTasks.add(listenableFutureTask);
            return listenableFutureTask;
         }
      }

      try {
         return Futures.immediateFuture(callable.call());
      } catch (Exception e) {
         return Futures.immediateFailedFuture(e);
      }
   }

   public boolean isGamePaused() {
      return this.isGamePaused;
   }

   public void draw(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10) {
      float f = 0.00390625F;
      float f2 = 0.00390625F;
      WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      worldRenderer.pos((double)n, (double)(n2 + n6), 0.0)
         .tex((double)((float)n3 * f), (double)((float)(n4 + n6) * f2))
         .color(n7, n8, n9, n10)
         .endVertex();
      worldRenderer.pos((double)(n + n5), (double)(n2 + n6), 0.0)
         .tex((double)((float)(n3 + n5) * f), (double)((float)(n4 + n6) * f2))
         .color(n7, n8, n9, n10)
         .endVertex();
      worldRenderer.pos((double)(n + n5), (double)n2, 0.0)
         .tex((double)((float)(n3 + n5) * f), (double)((float)n4 * f2))
         .color(n7, n8, n9, n10)
         .endVertex();
      worldRenderer.pos((double)n, (double)n2, 0.0).tex((double)((float)n3 * f), (double)((float)n4 * f2)).color(n7, n8, n9, n10).endVertex();
      Tessellator.getInstance().draw();
   }

   public MinecraftSessionService getSessionService() {
      return this.sessionService;
   }

   public void displayCrashReport(CrashReport crashReport) {

File file = new File(new File(OSUtil.detectOS().getMinecraftDirectory()), "crash-reports");
      ClientFileUtil.ensureDirectory(file.getAbsolutePath());
      File file2 = new File(file, "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + "-client.txt");
      Bootstrap.printToSYSOUT(crashReport.getCompleteReport());
      CrashReport crashReport2 = crashReport;
      if (crashReport.getFile() != null) {
            Bootstrap.printToSYSOUT("#@!@# Game crashed! Crash report saved to: #@!@# " + crashReport.getFile());
            System.exit(-1);
            return;
         }

         crashReport2 = crashReport;

      byte b = (byte)((crashReport2.saveToFile(file2)) ? 1 : 0);
      if (b != 0) {
            Bootstrap.printToSYSOUT("#@!@# Game crashed! Crash report saved to: #@!@# " + file2.getAbsolutePath());
            System.exit(-1);
            return;
         }

         Bootstrap.printToSYSOUT("#@?@# Game crashed! Crash report could not be saved. #@?@#");
         b = -2;

      System.exit(b);
   }

   private void drawSplashScreen(TextureManager textureManager) throws LWJGLException {
      ScaledResolution sr = new ScaledResolution(this);
      int scaleFactor = sr.getScaleFactor();
      Framebuffer framebuffer = new Framebuffer(sr.getScaledWidth() * scaleFactor, sr.getScaledHeight() * scaleFactor, true);
      framebuffer.bindFramebuffer(false);
      GlStateManager.matrixMode(5889);
      GlStateManager.loadIdentity();
      GlStateManager.ortho(0.0, (double)sr.getScaledWidth(), (double)sr.getScaledHeight(), 0.0, 1000.0, 3000.0);
      GlStateManager.matrixMode(5888);
      GlStateManager.loadIdentity();
      GlStateManager.translate(0.0F, 0.0F, -2000.0F);
      GlStateManager.disableLighting();
      GlStateManager.disableFog();
      GlStateManager.disableDepth();
      GlStateManager.enableTexture2D();
      BufferedImage image = null;
      InputStream resourceAsStream = Augustus.class.getClassLoader().getResourceAsStream("assets/augustus/pictures/startLogo.png");

      try {
         image = ImageIO.read(resourceAsStream);
      } catch (IOException e) {
         e.printStackTrace();
      }

      this.mojangLogo = textureManager.getDynamicTextureLocation("logo", new DynamicTexture(image));
      textureManager.bindTexture(this.mojangLogo);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      worldRenderer.pos(0.0, (double)this.displayHeight, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
      worldRenderer.pos((double)this.displayWidth, (double)this.displayHeight, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
      worldRenderer.pos((double)this.displayWidth, 0.0, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
      worldRenderer.pos(0.0, 0.0, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
      instance.draw();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      short s = 256;
      short s2 = 256;
      this.draw((sr.getScaledWidth() - s) / 2, (sr.getScaledHeight() - s2) / 2, (byte)0, (byte)0, s, s2, (short)255, (short)255, (short)255, (short)255);
      GlStateManager.disableLighting();
      GlStateManager.disableFog();
      framebuffer.unbindFramebuffer();
      framebuffer.framebufferRender(sr.getScaledWidth() * scaleFactor, sr.getScaledHeight() * scaleFactor);
      GlStateManager.enableAlpha();
      GlStateManager.alphaFunc(516, 0.1F);
      this.updateDisplay();
   }

   private void updateFramebufferSize() {
      
      this.framebufferMc.createBindFramebuffer(this.displayWidth, this.displayHeight);
      EntityRenderer entityRenderer2 = this.entityRenderer;
      if (this.entityRenderer == null) {
            return;
         }

         entityRenderer2 = this.entityRenderer;

      entityRenderer2.updateShaderGroupSize(this.displayWidth, this.displayHeight);
   }

static {
      
      LOGGER = LogManager.getLogger();
      isRunningOnMac = Util.getOSType() == Util.EnumOS.OSX;
      memoryReserve = new byte[10485760];
      macDisplayModes = Lists.newArrayList(new DisplayMode[]{new DisplayMode(2560, 1600), new DisplayMode(2880, 1800)});
   }

public static class DefaultStatStringFormat implements IStatStringFormat {
      public final Minecraft mc;

      public DefaultStatStringFormat(Minecraft mc2) {
         this.mc = mc2;
      }

      @Override
      public String formatString(String str) {
         try {
            return String.format(str, GameSettings.getKeyDisplayString(this.mc.gameSettings.keyBindInventory.getKeyCode()));
         } catch (Exception e) {
            return "Error: " + e.getLocalizedMessage();
         }
      }
   }

   public static class WorkerTask extends Thread {
      public final Minecraft mc;

      @Override
      public void run() {
         while (this.mc.running) {
            try {
               Thread.sleep(2147483647L);
            } catch (InterruptedException e) {
            }
         }
      }

      public WorkerTask(Minecraft mc2, String str) {
         super(str);
         this.mc = mc2;
      }
   }

   public static class ValueCallable implements Callable {
      public final Minecraft mc;

      public ValueCallable(Minecraft mc2) {
         this.mc = mc2;
      }

      public String getCurrentScreenName() throws Exception {
         return this.mc.currentScreen.getClass().getCanonicalName();
      }

      @Override
      public Object call() throws Exception {
         return this.getCurrentScreenName();
      }
   }

   public static class ValueCallable2 implements Callable {
      public final Minecraft mc;

      public ValueCallable2(Minecraft mc2) {
         this.mc = mc2;
      }

      @Override
      public Object call() throws Exception {
         return this.getScreenName();
      }

      public String getScreenName() throws Exception {
         return this.mc.currentScreen.getClass().getCanonicalName();
      }
   }

   public static class ValueCallable3 implements Callable {
      public final Minecraft mc;

      public String getLaunchedVersion() throws Exception {
         return Minecraft.accessVersion(this.mc);
      }

      @Override
      public Object call() throws Exception {
         return this.getLaunchedVersion();
      }

      public ValueCallable3(Minecraft mc2) {
         this.mc = mc2;
      }
   }

   public static class ValueCallable4 implements Callable {
      public final Minecraft mc;

      public String getLwjglVersion() {
         return Sys.getVersion();
      }

      @Override
      public Object call() throws Exception {
         return this.getLwjglVersion();
      }

      public ValueCallable4(Minecraft mc2) {
         this.mc = mc2;
      }
   }

   public static class ValueCallable5 implements Callable {
      public final Minecraft mc;

      public ValueCallable5(Minecraft mc2) {
         this.mc = mc2;
      }

      public String getOpenGlInfo() {
         return GL11.glGetString(7937) + " GL version " + GL11.glGetString(7938) + ", " + GL11.glGetString(7936);
      }

      @Override
      public Object call() throws Exception {
         return this.getOpenGlInfo();
      }
   }

   public static class ValueCallable6 implements Callable {
      public final Minecraft mc;

      public ValueCallable6(Minecraft mc2) {
         this.mc = mc2;
      }

      @Override
      public Object call() throws Exception {
         return this.getGlCapsInfo();
      }

      public String getGlCapsInfo() {
         return OpenGlHelper.getLogText();
      }
   }

   public static class ValueCallable7 implements Callable {
      public final Minecraft mc;

      public String getUsingVbosStatus() {
         return this.mc.gameSettings.useVbo ? "Yes" : "No";
      }

      public ValueCallable7(Minecraft mc2) {
         this.mc = mc2;
      }

      @Override
      public Object call() throws Exception {
         return this.getUsingVbosStatus();
      }
   }

   public static class ValueCallable8 implements Callable {
      public final Minecraft mc;

      public String getModdedStatus() throws Exception {
         String clientModName = ClientBrandRetriever.getClientModName();
         return !clientModName.equals("vanilla")
            ? "Definitely; Client brand changed to '" + clientModName + "'"
            : (
               Minecraft.class.getSigners() == null
                  ? "Very likely; Jar signature invalidated"
                  : "Probably not. Jar signature remains and client brand is untouched."
            );
      }

      public ValueCallable8(Minecraft mc2) {
         this.mc = mc2;
      }

      @Override
      public Object call() throws Exception {
         return this.getModdedStatus();
      }
   }

   public static class ValueCallable9 implements Callable {
      public final Minecraft mc;

      public ValueCallable9(Minecraft mc2) {
         this.mc = mc2;
      }

      public String getClientType() throws Exception {
         return "Client (map_client.txt)";
      }

      @Override
      public Object call() throws Exception {
         return this.getClientType();
      }
   }

   public static class ValueCallable10 implements Callable {
      public final Minecraft mc;

      @Override
      public Object call() throws Exception {
         return this.getResourcePacksList();
      }

      public ValueCallable10(Minecraft mc2) {
         this.mc = mc2;
      }

      public String getResourcePacksList() throws Exception {
         StringBuilder sb = new StringBuilder();

         for (Object obj : this.mc.gameSettings.resourcePacks) {
            if (sb.length() > 0) {
               sb.append(", ");
            }

            sb.append(obj);
            if (this.mc.gameSettings.incompatibleResourcePacks.contains(obj)) {
               sb.append(" (incompatible)");
            }
         }

         return sb.toString();
      }
   }

   public static class ValueCallable11 implements Callable {
      public final Minecraft mc;

      public String getCurrentLanguage() throws Exception {
         return Minecraft.accessMcLanguageManager(this.mc).getCurrentLanguage().toString();
      }

      public ValueCallable11(Minecraft mc2) {
         this.mc = mc2;
      }

      @Override
      public Object call() throws Exception {
         return this.getCurrentLanguage();
      }
   }

   public static class ValueCallable12 implements Callable {
      public final Minecraft mc;

      public ValueCallable12(Minecraft mc2) {
         this.mc = mc2;
      }

      @Override
      public Object call() throws Exception {
         return this.getProfilerPosition();
      }

      public String getProfilerPosition() throws Exception {
         return this.mc.mcProfiler.profilingEnabled ? this.mc.mcProfiler.getNameOfLastSection() : "N/A (disabled)";
      }
   }

   public static class ValueCallable13 implements Callable {
      public final Minecraft mc;

      @Override
      public Object call() throws Exception {
         return this.getCpuInfo();
      }

      public String getCpuInfo() {
         return OpenGlHelper.getCpu();
      }

      public ValueCallable13(Minecraft mc2) {
         this.mc = mc2;
      }
   }

}
