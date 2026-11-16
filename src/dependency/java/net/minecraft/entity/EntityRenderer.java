package net.minecraft.entity;

import augustus.modules.movement.FreeCam;
import augustus.modules.render.CustomZoom;
import augustus.modules.render.FovModifier;
import augustus.modules.render.XRay;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstTracker;
import cc.baritone.utils.SettingsTxtManager;
import client.Client;
import client.ClientGlobals;
import client.event.EventCancelListener;
import client.event.EventChunkLoad;
import client.event.EventChunkLoadCallback;
import client.event.EventFovUpdate;
import client.event.EventFovUpdateCallback;
import client.event.EventJump;
import client.event.EventJumpCallback;
import client.event.EventSilentMove;
import client.event.EventStrafe;
import client.event.EventTimerSpeed;
import client.event.EventTimerSpeedCallback;
import client.event.PreMotionConsumer;
import client.event.RenderListener;
import client.event.SilentMoveListener;
import client.event.StrafeListener;
import client.gui.BaseGuiScreenAdapter;
import client.module.Module;
import client.render.EntityEspRenderer;
import client.render.TexturesMapIconsManager;
import client.render.WorldRenderer;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.AuxiliaryOptionType;
import client.setting.BlockSolidType;
import client.setting.ClientSettingsOpenUrlType;
import client.setting.CombatRangeProperty;
import client.setting.GameSettings;
import client.setting.MovementAngleProperty;
import client.setting.WorldSettingsGameType;
import client.util.AndFSManager;
import client.util.AuxiliaryIntBufferEntry;
import client.util.BezierQueue;
import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.BuildManager;
import client.util.ClickEventActionManager;
import client.util.CompositeFloatEntry;
import client.util.CompositeStringEntry;
import client.util.CoreLongEntry;
import client.util.CustomFloatArrayEntry;
import client.util.DefaultFloatEntry;
import client.util.DefaultVertexFormats;
import client.util.EntitySelectPredicates;
import client.util.GeometryTransformer;
import client.util.HitBoxBuffer;
import client.util.IBlockState;
import client.util.IntegratedServer;
import client.util.InternalPngManager;
import client.util.KeybindAnimation;
import client.util.ListenerAdapter27;
import client.util.LivingEntityFilter;
import client.util.LoadingManager;
import client.util.LvlManager;
import client.util.MBSManager;
import client.util.MouseUtils;
import client.util.MoveUtil;
import client.util.MovementHandler;
import client.util.MovingObjectType;
import client.util.OptifineRandomManager;
import client.util.PassManager;
import client.util.PreEndHandManager;
import client.util.RayTraceFormatter;
import client.util.RotationBridge;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.StandardMapEntry;
import client.util.StyleHasParentManager;
import client.util.SupplementalMapEntry;
import client.util.SuppressedManager;
import client.util.VectorMatcher;
import com.google.common.base.Predicates;
import com.google.gson.JsonSyntaxException;
import de.florianmichael.dietrichevents2.StateTypes;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EffectRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.DiamondEssence;
import net.minecraft.item.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

public class EntityRenderer implements IResourceManagerReloadListener {
   public float factor5;
   public float factor25;
   public float factor16;
   public float factor20;
   public static boolean state = true;
   public static boolean state1 = true;
   public static final Logger logger = LogManager.getLogger();
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/environment/rain.png");
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/environment/snow.png");
   public static boolean enabled5;
   public static int count10;
   public Minecraft mc;
   public final IResourceManager iResourceManager;
   public Random random = new Random();
   public float factor21;
   public ItemRenderer itemRenderer;
   public final TexturesMapIconsManager texturesMapIconsManager;
   public int count2;
   public Entity entity;
   public CompositeFloatEntry compositeFloatEntry = new CompositeFloatEntry();
   public CompositeFloatEntry compositeFloatEntry2 = new CompositeFloatEntry();
   public float scaleFactor = 4.0F;
   public float factor12 = 4.0F;
   public float factor22;
   public float factor11;
   public float factor7;
   public float factor;
   public float factor3;
   public float factor13;
   public float factor10;
   public float factor14;
   public float factor19;
   public boolean enabled;
   public boolean enabled11 = true;
   public boolean enabled4 = true;
   public long timestamp2 = Minecraft.getTimestamp();
   public long timestamp;
   public final DynamicTexture dynamicTexture;
   public final int[] countArray;
   public final ResourceLocation resourceLocation3;
   public boolean enabled12;
   public float factor6;
   public float factor18;
   public int count9;
   public float[] factorArray = new float[1024];
   public float[] factorArray2 = new float[1024];
   public FloatBuffer floatBuffer = VectorMatcher.resolveFloatBuffer(16);
   public float factor9;
   public float factor15;
   public float factor2;
   public float factor23;
   public float factor17;
   public int count7 = 0;
   public boolean enabled9 = false;
   public double value3 = 1.0;
   public double value2;
   public double value;
   public PassManager passManager;
   public static final ResourceLocation[] resourceLocationArray = new ResourceLocation[]{
      new ResourceLocation("shaders/post/notch.json"),
      new ResourceLocation("shaders/post/fxaa.json"),
      new ResourceLocation("shaders/post/art.json"),
      new ResourceLocation("shaders/post/bumpy.json"),
      new ResourceLocation("shaders/post/blobs2.json"),
      new ResourceLocation("shaders/post/pencil.json"),
      new ResourceLocation("shaders/post/color_convolve.json"),
      new ResourceLocation("shaders/post/deconverge.json"),
      new ResourceLocation("shaders/post/flip.json"),
      new ResourceLocation("shaders/post/invert.json"),
      new ResourceLocation("shaders/post/ntsc.json"),
      new ResourceLocation("shaders/post/outline.json"),
      new ResourceLocation("shaders/post/phosphor.json"),
      new ResourceLocation("shaders/post/scan_pincushion.json"),
      new ResourceLocation("shaders/post/sobel.json"),
      new ResourceLocation("shaders/post/bits.json"),
      new ResourceLocation("shaders/post/desaturate.json"),
      new ResourceLocation("shaders/post/green.json"),
      new ResourceLocation("shaders/post/blur.json"),
      new ResourceLocation("shaders/post/wobble.json"),
      new ResourceLocation("shaders/post/blobs.json"),
      new ResourceLocation("shaders/post/antialias.json"),
      new ResourceLocation("shaders/post/creeper.json"),
      new ResourceLocation("shaders/post/spider.json")
   };
   public static final int count = resourceLocationArray.length;
   public int count8;
   public boolean enabled6;
   public int count5;
   public boolean enabled2 = false;
   public World world = null;
   public boolean enabled7 = false;
   public boolean enabled10 = false;
   public float factor4 = 128.0F;
   public long timestamp3 = 0L;
   public int count6 = 0;
   public int count3 = 0;
   public int count4 = 0;
   public float factor8 = 0.0F;
   public float factor24 = 0.0F;
   public PassManager[] passManagerArray = new PassManager[10];
   public boolean enabled8 = false;
   public boolean enabled3;

   private void applyResourceLocationByResourceLocation(ResourceLocation location) {
      if (OpenGlHelper.isActiveAsFlag()) {
         try {
            this.passManager = new PassManager(this.mc.getTextureManager(), this.iResourceManager, this.mc.getFramebuffer(), location);
            this.passManager.applyIndex(this.mc.displayWidth, this.mc.displayHeight);
            this.enabled6 = true;
         } catch (IOException ioException) {
            logger.warn("Failed to load shader: " + location, ioException);
            this.count8 = count;
            this.enabled6 = false;
         } catch (JsonSyntaxException jsonSyntaxException) {
            logger.warn("Failed to load shader: " + location, jsonSyntaxException);
            this.count8 = count;
            this.enabled6 = false;
         }
      }
   }

   public void applyScaledResolutionByScaledResolution(ScaledResolution scaledResolution) {
      GlStateManager.glClear(256);
      GlStateManager.matrixMode(5889);
      GlStateManager.loadIdentity();
      GlStateManager.glStateAction(0.0, scaledResolution.getCoordOffset(), scaledResolution.getCoordOffset3(), 0.0, 1000.0, 3000.0);
      GlStateManager.matrixMode(5888);
      GlStateManager.loadIdentity();
      GlStateManager.translate(0.0F, 0.0F, -2000.0F);
   }

   public float computeFactor(EntityLivingBase livingEntity, float factor26) {
      int indexOffset = livingEntity.getPotionEffect(Potion.potion31).getIndexOffset();
      return indexOffset > 200 ? 1.0F : 0.7F + MathHelper.abs(((float)indexOffset - factor26) * (float) Math.PI * 0.2F) * 0.3F;
   }

   private void applyRenderGlobal(RenderGlobal renderGlobal, float factor26, int count11) {
      if (this.mc.gameSettings.indexOffset16 >= 4 && !BuildManager.isActive18() && PreEndHandManager.matchesGameSettings(this.mc.gameSettings)) {
         this.mc.uNKNOWNManager.applyText("clouds");
         GlStateManager.matrixMode(5889);
         GlStateManager.loadIdentity();
         EventTimerSpeed event = EventTimerSpeedCallback.resolveEventTimerSpeed((float)this.mc.displayWidth / (float)this.mc.displayHeight, false);
         Project.gluPerspective(this.computeFactor(factor26, true), event.getScaleFactor(), 0.05F, this.factor4 * 4.0F);
         GlStateManager.matrixMode(5888);
         GlStateManager.pushMatrix();
         this.applyIndex(0, factor26);
         renderGlobal.applyFloat(factor26, count11);
         GlStateManager.applyState20();
         GlStateManager.popMatrix();
         GlStateManager.matrixMode(5889);
         GlStateManager.loadIdentity();
         Project.gluPerspective(this.computeFactor(factor26, true), event.getScaleFactor(), 0.05F, this.factor4);
         GlStateManager.matrixMode(5888);
      }
   }

   public void applyIndex(int count11, int count12) {
      if (OpenGlHelper.enabled4) {
         if (this.passManager != null) {
            this.passManager.applyIndex(count11, count12);
         }

         this.mc.renderGlobal.applyIndex(count11, count12);
      }
   }

   private void updateState9() {
      float floatItem = this.mc.theWorld.getFloat6(1.0F);
      if (!BuildManager.isActive12()) {
         floatItem /= 2.0F;
      }

      if (floatItem != 0.0F && BuildManager.isActive38()) {
         this.random.setSeed((long)this.count2 * 312987231L);
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         WorldClient world2 = this.mc.theWorld;
         BlockPos blockPos = new BlockPos(renderViewEntity);
         double offset = 0.0;
         double offset2 = 0.0;
         double offset3 = 0.0;
         int count11 = 0;
         int count12 = (int)(100.0F * floatItem * floatItem);
         if (this.mc.gameSettings.indexOffset134 == 1) {
            count12 >>= 1;
         } else if (this.mc.gameSettings.indexOffset134 == 2) {
            count12 = 0;
         }

         for (int index = 0; index < count12; index++) {
            BlockPos blockPos2 = world2.resolveBlockPosByBlockPos(
               blockPos.add(this.random.nextInt(10) - this.random.nextInt(10), 0, this.random.nextInt(10) - this.random.nextInt(10))
            );
            BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = world2.resolveBiomeIDIsOutOfBoundsManager(blockPos2);
            BlockPos blockPos3 = blockPos2.down();
            Block block = world2.getBlockState(blockPos3).getBlock();
            if (blockPos2.getY() <= blockPos.getY() + 10
               && blockPos2.getY() >= blockPos.getY() - 10
               && (
                  biomeIDIsOutOfBoundsManager.isActive2() && !((Module)ClientGlobals.moduleManager.ambiance).isToggled()
                     || ClientGlobals.moduleManager.ambiance.settingRain.getValue() > 0.0
               )
               && (
                  biomeIDIsOutOfBoundsManager.computeFactor(blockPos2) >= 0.15F && !((Module)ClientGlobals.moduleManager.ambiance).isToggled()
                     || !ClientGlobals.moduleManager.ambiance.settingSnow.isEnabled()
               )) {
               double offset4 = this.random.nextDouble();
               double offset5 = this.random.nextDouble();
               if (block.getMaterial() == Material.material29) {
                  this.mc
                     .theWorld
                     .applyAttackEffectsExplosionNorm(
                        AttackEffectsExplosionNormalType.SMOKE_NORMAL,
                        (double)blockPos2.getX() + offset4,
                        (double)((float)blockPos2.getY() + 0.1F) - block.getCoordOffset11(),
                        (double)blockPos2.getZ() + offset5,
                        0.0,
                        0.0,
                        0.0,
                        new int[0]
                     );
               } else if (block.getMaterial() != Material.coreItemEntry) {
                  block.processAction(world2, blockPos3.getX(), blockPos3.getY(), blockPos3.getZ());
                  if (this.random.nextInt(++count11) == 0) {
                     offset = (double)blockPos3.getX() + offset4;
                     offset2 = (double)((float)blockPos3.getY() + 0.1F) + block.getCoordOffset6() - 1.0;
                     offset3 = (double)blockPos3.getZ() + offset5;
                  }

                  this.mc
                     .theWorld
                     .applyAttackEffectsExplosionNorm(
                        AttackEffectsExplosionNormalType.WATER_DROP,
                        (double)blockPos3.getX() + offset4,
                        (double)((float)blockPos3.getY() + 0.1F) + block.getCoordOffset6(),
                        (double)blockPos3.getZ() + offset5,
                        0.0,
                        0.0,
                        0.0,
                        new int[0]
                     );
               }
            }
         }

         if (count11 > 0 && this.random.nextInt(3) < this.count9++) {
            this.count9 = 0;
            if (offset2 > (double)(blockPos.getY() + 1) && world2.computeCount(blockPos.getX(), blockPos.getZ()) > MathHelper.floor_float((float)blockPos.getY())) {
               this.mc.theWorld.applyText(offset, offset2, offset3, "ambient.weather.rain", 0.1F, 0.5F, false);
            } else {
               this.mc.theWorld.applyText(offset, offset2, offset3, "ambient.weather.rain", 0.2F, 1.0F, false);
            }
         }
      }
   }

   public void applyFloatByFloat(float factor26, long value4) {
      BuildManager.factor = factor26;
      this.updateState10();
      boolean active = Display.isActive();
      if (!active && this.mc.gameSettings.state72 && (!this.mc.gameSettings.state48 || !MouseUtils.checkCondition(1))) {
         if (Minecraft.getTimestamp() - this.timestamp2 > 500L) {
            this.mc.updateState2();
         }
      } else {
         this.timestamp2 = Minecraft.getTimestamp();
      }

      this.mc.uNKNOWNManager.applyTextByText("mouse");
      if (active && Minecraft.state3 && this.mc.inGameHasFocus && !Mouse.isInsideWindow()) {
         Mouse.setGrabbed(false);
         Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
         Mouse.setGrabbed(true);
      }

      if ((
            this.mc.inGameHasFocus
               || this.mc.currentScreen == null
               || ((Module)ClientGlobals.moduleManager.guiMove).isToggled()
                  && ClientGlobals.moduleManager.guiMove.canExecute7()
                  && (MouseUtils.checkCondition(1) && MouseUtils.checkCondition(0) || !ClientGlobals.moduleManager.guiMove.canExecute10())
         )
         && active
         && this.mc.thePlayer != null) {
         this.mc.mouseHelper.updateState2();
         if (!((Module)ClientGlobals.moduleManager.autoPlay).isToggled() || !ClientGlobals.moduleManager.autoPlay.settingRemoveEscape.isEnabled()) {
            if (!Mouse.isGrabbed()) {
               Mouse.setGrabbed(true);
            }

            if ((this.mc.currentScreen != null || !this.mc.inGameHasFocus)
               && ((Module)ClientGlobals.moduleManager.guiMove).isToggled()
               && ClientGlobals.moduleManager.guiMove.canExecute7()
               && (MouseUtils.checkCondition(1) && MouseUtils.checkCondition(0) || !ClientGlobals.moduleManager.guiMove.canExecute10())) {
               Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
               Mouse.setGrabbed(false);
            }
         }

         float factor27 = this.mc.gameSettings.scaleFactor * 0.6F + 0.2F;
         float factor28 = factor27 * factor27 * factor27 * 8.0F;
         EventSilentMove event = SilentMoveListener.resolveEventSilentMove(
            this.mc.mouseHelper.indexOffset, this.mc.mouseHelper.indexOffset1, factor26, StateTypes.PRE
         );
         float factor29 = (float)event.getIndex2() * factor28;
         float factor30 = (float)event.getIndex() * factor28;
         byte byteValue = 1;
         if (this.mc.gameSettings.state20 && !event.canExecute()) {
            byteValue = -1;
         }

         if (!this.mc.gameSettings.state32
            || event.isEnabled()
            || ((Module)ClientGlobals.moduleManager.customZoom).isToggled() && ClientGlobals.moduleManager.customZoom.settingFastZoom.isEnabled()) {
            this.factor22 = 0.0F;
            this.factor11 = 0.0F;
            this.mc.thePlayer.applyFloatByFloat(factor29, factor30 * (float)byteValue);
         } else {
            this.factor22 += factor29;
            this.factor11 += factor30;
            float factor31 = factor26 - this.factor3;
            this.factor3 = factor26;
            float factor32 = this.factor7 * factor31;
            float factor33 = this.factor * factor31;
            this.mc.thePlayer.applyFloatByFloat(factor32, factor33 * (float)byteValue);
         }

         SilentMoveListener.resolveEventSilentMove(this.mc.mouseHelper.indexOffset, this.mc.mouseHelper.indexOffset1, factor26, StateTypes.POST);
      }

      this.mc.uNKNOWNManager.updateState();
      if (!this.mc.enabled7) {
         enabled5 = this.mc.gameSettings.state105;
         ScaledResolution scaledResolution = new ScaledResolution(this.mc);
         int scaledWidth = scaledResolution.getScaledWidth();
         int scaledHeight = scaledResolution.getScaledHeight();
         int count11 = Mouse.getX() * scaledWidth / this.mc.displayWidth;
         int count12 = scaledHeight - Mouse.getY() * scaledHeight / this.mc.displayHeight - 1;
         int indexOffset = this.mc.gameSettings.indexOffset35;
         if (this.mc.theWorld != null) {
            this.mc.uNKNOWNManager.applyTextByText("level");
            int count13 = Math.max(Math.min(Minecraft.getLimitFramerate(), indexOffset), 60);
            long value5 = System.nanoTime() - value4;
            long value6 = Math.max((long)(1000000000 / count13 / 4) - value5, 0L);
            this.applyFloat(factor26, System.nanoTime() + value6);
            if (OpenGlHelper.enabled4) {
               this.mc.renderGlobal.updateState3();
               if (this.passManager != null && this.enabled6) {
                  GlStateManager.matrixMode(5890);
                  GlStateManager.pushMatrix();
                  GlStateManager.loadIdentity();
                  this.passManager.applyFloat(factor26);
                  GlStateManager.popMatrix();
               }

               this.mc.getFramebuffer().setState(true);
            }

            this.timestamp = System.nanoTime();
            this.mc.uNKNOWNManager.applyText("gui");
            if (!this.mc.gameSettings.state11 || this.mc.currentScreen != null) {
               GlStateManager.alphaFunc(516, 0.1F);
               this.mc.ingameGUI.applyFloat(factor26);
               if (this.mc.gameSettings.state130 && !this.mc.gameSettings.state14) {
                  BuildManager.updateState3();
               }

               if (this.mc.gameSettings.state14) {
                  MBSManager.applyScaledResolutionByScaledResolution(scaledResolution);
               }
            }

            this.mc.uNKNOWNManager.updateState();
         } else {
            GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
            GlStateManager.matrixMode(5889);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.loadIdentity();
            this.applyScaledResolutionByScaledResolution(scaledResolution);
            this.timestamp = System.nanoTime();
            StandardMapEntry.standardMapEntry.textureManager = this.mc.getTextureManager();
            StandardMapEntry.standardMapEntry.fontRenderer = this.mc.fontRendererObj;
         }

         if (this.mc.currentScreen != null) {
            GlStateManager.glClear(256);

            try {
               MovementHandler.processAction5();
               this.mc.currentScreen.applyIndex(count11, count12, factor26);
               if (((Module)Client.getClient().getModuleManager().notifications).isToggled()) {
                  Client.getClient().getModuleManager().notifications.processBoolean2(true);
               }

               MovementHandler.processAction12();
            } catch (Throwable throwable) {
               CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Rendering screen");
               CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Screen render details");
               crashReportCategory.applyText("Screen name", new BezierQueue(this));
               crashReportCategory.applyText("Mouse location", new GeometryTransformer(this, count11, count12));
               crashReportCategory.applyText("Screen size", new RayTraceFormatter(this, scaledResolution));
               throw new SecondaryRuntimeExceptionAdapter(crashReport);
            }
         } else {
            this.mc.audioClipPlayer.processChar();
         }

         if (this.mc.getRenderViewEntity() != null) {
            this.mc.getRenderViewEntity().prevRotationYaw = this.factor5;
            this.mc.getRenderViewEntity().prevRotationPitch = this.factor25;
            this.mc.getRenderViewEntity().rotationYaw = this.factor16;
            this.mc.getRenderViewEntity().rotationPitch = this.factor20;
         }
      }

      this.updateState6();
      this.updateState4();
      CoreLongEntry.updateState();
      MBSManager.updateState();
      if (this.mc.gameSettings.state71) {
         this.mc.gameSettings.state106 = true;
      }
   }

   private void applyFloat3(float factor26) {
      WorldClient world2 = this.mc.theWorld;
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      float factor27 = 0.25F + 0.75F * (float)this.mc.gameSettings.indexOffset16 / 32.0F;
      float factor28 = 1.0F - (float)Math.pow((double)factor27, 0.25);
      Vec3 vec = LoadingManager.resolveVec4(world2.resolveVec3(this.mc.getRenderViewEntity(), factor26), world2, this.mc.getRenderViewEntity(), factor26);
      float posX = (float)vec.xCoord;
      float posY = (float)vec.yCoord;
      float posZ = (float)vec.zCoord;
      Vec3 vec2 = LoadingManager.resolveVecByVec3(world2.resolveVec3(factor26), world2, this.mc.getRenderViewEntity(), factor26);
      EventJump event = EventJumpCallback.resolveEventJump((float)vec2.xCoord, (float)vec2.yCoord, (float)vec2.zCoord, CombatRangeProperty.FOG_COLOR);
      this.factor9 = event.getFactor();
      this.factor15 = event.getFactor2();
      this.factor2 = event.getFactor3();
      if (this.mc.gameSettings.indexOffset16 >= 4) {
         double offset = -1.0;
         Vec3 vec3 = MathHelper.abs(world2.computeFactor2(factor26)) > 0.0F ? new Vec3(offset, 0.0, 0.0) : new Vec3(1.0, 0.0, 0.0);
         float factor29 = (float)renderViewEntity.getVec36(factor26).distanceTo(vec3);
         if (factor29 < 0.0F) {
            factor29 = 0.0F;
         }

         if (factor29 > 0.0F) {
            float[] factorArray3 = world2.secondaryFloatArrayEntry.resolveFloatArray(world2.computeFactor(factor26), factor26);
            if (factorArray3 != null) {
               float factor30 = factor29 * factorArray3[3];
               this.factor9 = this.factor9 * (1.0F - factor30) + factorArray3[0] * factor30;
               this.factor15 = this.factor15 * (1.0F - factor30) + factorArray3[1] * factor30;
               this.factor2 = this.factor2 * (1.0F - factor30) + factorArray3[2] * factor30;
            }
         }
      }

      this.factor9 = this.factor9 + (posX - this.factor9) * factor28;
      this.factor15 = this.factor15 + (posY - this.factor15) * factor28;
      this.factor2 = this.factor2 + (posZ - this.factor2) * factor28;
      float floatItem = world2.getFloat6(factor26);
      if (floatItem > 0.0F) {
         float factor31 = 1.0F - floatItem * 0.5F;
         float factor32 = 1.0F - floatItem * 0.4F;
         this.factor9 *= factor31;
         this.factor15 *= factor31;
         this.factor2 *= factor32;
      }

      float floatItem2 = world2.getFloat7(factor26);
      if (floatItem2 > 0.0F) {
         float factor33 = 1.0F - floatItem2 * 0.5F;
         this.factor9 *= factor33;
         this.factor15 *= factor33;
         this.factor2 *= factor33;
      }

      Block block = AuxiliaryIntBufferEntry.resolveBlock(this.mc.theWorld, renderViewEntity, factor26);
      if (this.enabled) {
         Vec3 vec4 = world2.resolveVecByFloat(factor26);
         this.factor9 = (float)vec4.xCoord;
         this.factor15 = (float)vec4.yCoord;
         this.factor2 = (float)vec4.zCoord;
      } else if (block.getMaterial() == Material.coreItemEntry1) {
         float factor34 = BuildManager.computeFactor((float)LvlManager.computeCount(renderViewEntity) * 0.2F, 0.0F, 0.6F);
         if (renderViewEntity instanceof EntityLivingBase && ((EntityLivingBase)renderViewEntity).checkPotion(Potion.potion18)) {
            factor34 = factor34 * 0.3F + 0.6F;
         }

         this.factor9 = 0.02F + factor34;
         this.factor15 = 0.02F + factor34;
         this.factor2 = 0.2F + factor34;
         Vec3 vec5 = LoadingManager.resolveVecByIBlockAccess(
            this.mc.theWorld, this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().posY + 1.0, this.mc.getRenderViewEntity().posZ
         );
         if (vec5 != null) {
            this.factor9 = (float)vec5.xCoord;
            this.factor15 = (float)vec5.yCoord;
            this.factor2 = (float)vec5.zCoord;
         }
      } else if (block.getMaterial() == Material.material29) {
         this.factor9 = 0.6F;
         this.factor15 = 0.1F;
         this.factor2 = 0.0F;
         Vec3 vec6 = LoadingManager.resolveVec3(
            this.mc.theWorld, this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().posY + 1.0, this.mc.getRenderViewEntity().posZ
         );
         if (vec6 != null) {
            this.factor9 = (float)vec6.xCoord;
            this.factor15 = (float)vec6.yCoord;
            this.factor2 = (float)vec6.zCoord;
         }
      }

      float factor35 = this.factor23 + (this.factor17 - this.factor23) * factor26;
      this.factor9 *= factor35;
      this.factor15 *= factor35;
      this.factor2 *= factor35;
      double value4 = world2.secondaryFloatArrayEntry.getValue();
      double offset2 = (renderViewEntity.lastTickPosY + (renderViewEntity.posY - renderViewEntity.lastTickPosY) * (double)factor26) * value4;
      if (renderViewEntity instanceof EntityLivingBase
         && ((EntityLivingBase)renderViewEntity).checkPotion(Potion.potion16)
         && (!(renderViewEntity instanceof EntityPlayerSP) || !((Module)ClientGlobals.moduleManager.antiBlind).isToggled())) {
         int indexOffset = ((EntityLivingBase)renderViewEntity).getPotionEffect(Potion.potion16).getIndexOffset();
         if (indexOffset < 20) {
            offset2 *= (double)(1.0F - (float)indexOffset / 20.0F);
         } else {
            offset2 = 0.0;
         }
      }

      if (offset2 < 1.0) {
         if (offset2 < 0.0) {
            offset2 = 0.0;
         }

         double offset3 = offset2 * offset2;
         this.factor9 = (float)((double)this.factor9 * offset3);
         this.factor15 = (float)((double)this.factor15 * offset3);
         this.factor2 = (float)((double)this.factor2 * offset3);
      }

      if (this.factor14 > 0.0F) {
         float factor36 = this.factor19 + (this.factor14 - this.factor19) * factor26;
         this.factor9 = this.factor9 * (1.0F - factor36) + this.factor9 * 0.7F * factor36;
         this.factor15 = this.factor15 * (1.0F - factor36) + this.factor15 * 0.6F * factor36;
         this.factor2 = this.factor2 * (1.0F - factor36) + this.factor2 * 0.6F * factor36;
      }

      EventChunkLoad event2 = EventChunkLoadCallback.resolveEventChunkLoad(false, MovementAngleProperty.FOG_NIGHT_VISION);
      if (renderViewEntity instanceof EntityLivingBase && (((EntityLivingBase)renderViewEntity).checkPotion(Potion.potion31) || event2.isEnabled())) {
         float factor37 = event2.isEnabled() ? 1.0F : this.computeFactor((EntityLivingBase)renderViewEntity, factor26);
         float factor38 = 1.0F / this.factor9;
         if (factor38 > 1.0F / this.factor15) {
            factor38 = 1.0F / this.factor15;
         }

         if (factor38 > 1.0F / this.factor2) {
            factor38 = 1.0F / this.factor2;
         }

         if (Float.isInfinite(factor38)) {
            factor38 = Math.nextAfter(factor38, 0.0);
         }

         this.factor9 = this.factor9 * (1.0F - factor37) + this.factor9 * factor38 * factor37;
         this.factor15 = this.factor15 * (1.0F - factor37) + this.factor15 * factor38 * factor37;
         this.factor2 = this.factor2 * (1.0F - factor37) + this.factor2 * factor38 * factor37;
      }

      if (this.mc.gameSettings.state105) {
         float factor39 = (this.factor9 * 30.0F + this.factor15 * 59.0F + this.factor2 * 11.0F) / 100.0F;
         float factor40 = (this.factor9 * 30.0F + this.factor15 * 70.0F) / 100.0F;
         float factor41 = (this.factor9 * 30.0F + this.factor2 * 70.0F) / 100.0F;
         this.factor9 = factor39;
         this.factor15 = factor40;
         this.factor2 = factor41;
      }

      PreEndHandManager.applyFloat(this.factor9, this.factor15, this.factor2, 0.0F);
   }

   private void applyFloat(float factor26, int count11) {
      this.applyFloat(factor26, count11, true, true, false);
   }

   public TexturesMapIconsManager getTexturesMapIconsManager() {
      return this.texturesMapIconsManager;
   }

   private void updateState3() {
      this.factor18 = (float)((double)this.factor18 + (Math.random() - Math.random()) * Math.random() * Math.random());
      this.factor18 = (float)((double)this.factor18 * 0.9);
      this.factor6 = this.factor6 + (this.factor18 - this.factor6) * 1.0F;
      this.enabled12 = true;
   }

   private void applyGuiMainMenuByGuiMainMenu(GuiMainMenu guiMainMenu) {
      try {
         String text = null;
         Calendar instance = Calendar.getInstance();
         instance.setTime(new Date());
         int count11 = instance.get(5);
         int count12 = instance.get(2) + 1;
         if (count11 == 8 && count12 == 4) {
            text = "Happy birthday, OptiFine!";
         }

         if (count11 == 14 && count12 == 8) {
            text = "Happy birthday, sp614x!";
         }

         if (text == null) {
            return;
         }

         Field[] declaredFields = GuiMainMenu.class.getDeclaredFields();

         for (int index = 0; index < declaredFields.length; index++) {
            if (declaredFields[index].getType() == String.class) {
               declaredFields[index].setAccessible(true);
               declaredFields[index].set(guiMainMenu, text);
               break;
            }
         }
      } catch (Throwable throwable) {
      }
   }

   public static Minecraft resolveMinecraftByEntityRenderer(EntityRenderer entityRenderer) {
      return entityRenderer.mc;
   }

   public void applyFloatByFloat(float factor26) {
      float floatItem = this.mc.theWorld.getFloat6(factor26);
      if (floatItem > 0.0F) {
         if (BuildManager.isActive15()) {
            return;
         }

         this.updateState7();
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         WorldClient world2 = this.mc.theWorld;
         int count11 = MathHelper.ceiling_double_int(renderViewEntity.posX);
         int count12 = MathHelper.ceiling_double_int(renderViewEntity.posY);
         int count13 = MathHelper.ceiling_double_int(renderViewEntity.posZ);
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         GlStateManager.disableAlpha();
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.alphaFunc(516, 0.1F);
         double offset = renderViewEntity.lastTickPosX + (renderViewEntity.posX - renderViewEntity.lastTickPosX) * (double)factor26;
         double offset2 = renderViewEntity.lastTickPosY + (renderViewEntity.posY - renderViewEntity.lastTickPosY) * (double)factor26;
         double offset3 = renderViewEntity.lastTickPosZ + (renderViewEntity.posZ - renderViewEntity.lastTickPosZ) * (double)factor26;
         int count14 = MathHelper.ceiling_double_int(offset2);
         byte byteValue = 5;
         if (BuildManager.isActive12()) {
            byteValue = 10;
         }

         byte byteValue2 = -1;
         float factor27 = (float)this.count2 + factor26;
         worldRenderer.applyDouble(-offset, -offset2, -offset3);
         if (BuildManager.isActive12()) {
            byteValue = 10;
         }

         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

         for (int index = count13 - byteValue; index <= count13 + byteValue; index++) {
            for (int index2 = count11 - byteValue; index2 <= count11 + byteValue; index2++) {
               int count15 = (index - count13 + 16) * 32 + index2 - count11 + 16;
               double offset4 = (double)this.factorArray[count15] * 0.5;
               double offset5 = (double)this.factorArray2[count15] * 0.5;
               auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index2, 0, index);
               BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = world2.resolveBiomeIDIsOutOfBoundsManager(auxiliaryBlockPosAdapter);
               if (biomeIDIsOutOfBoundsManager.isActive2()
                  || biomeIDIsOutOfBoundsManager.isActive()
                  || ((Module)ClientGlobals.moduleManager.ambiance).isToggled() && ClientGlobals.moduleManager.ambiance.settingRain.getValue() > 0.0) {
                  int count16 = world2.computeCount(auxiliaryBlockPosAdapter.getX(), auxiliaryBlockPosAdapter.getZ());
                  int count17 = count12 - byteValue;
                  int count18 = count12 + byteValue;
                  if (count17 < count16) {
                     count17 = count16;
                  }

                  if (count18 < count16) {
                     count18 = count16;
                  }

                  int count19 = count16;
                  if (count16 < count14) {
                     count19 = count14;
                  }

                  if (count17 != count18) {
                     this.random.setSeed((long)(index2 * index2 * 3121 + index2 * 45238971 ^ index * index * 418711 + index * 13761));
                     auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index2, count17, index);
                     float factor28 = biomeIDIsOutOfBoundsManager.computeFactor(auxiliaryBlockPosAdapter);
                     float factor29 = world2.getInvalidBiomeIdManager().computeFactor(factor28, count16);
                     if (((Module)ClientGlobals.moduleManager.ambiance).isToggled() && ClientGlobals.moduleManager.ambiance.settingSnow.isEnabled()) {
                        factor29 = 0.1F;
                     }

                     if (factor29 >= 0.15F) {
                        if (byteValue2 != 0) {
                           if (byteValue2 >= 0) {
                              tessellator.updateState();
                           }

                           byteValue2 = 0;
                           this.mc.getTextureManager().applyResourceLocation3(resourceLocation);
                           worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry8);
                        }

                        double offset6 = (
                              (double)(this.count2 + index2 * index2 * 3121 + index2 * 45238971 + index * index * 418711 + index * 13761 & 31) + (double)factor26
                           )
                           / 32.0
                           * (3.0 + this.random.nextDouble());
                        double offset7 = (double)((float)index2 + 0.5F) - renderViewEntity.posX;
                        double offset8 = (double)((float)index + 0.5F) - renderViewEntity.posZ;
                        float factor30 = MathHelper.sqrt_double(offset7 * offset7 + offset8 * offset8) / (float)byteValue;
                        float factor31 = ((1.0F - factor30 * factor30) * 0.5F + 0.5F) * floatItem;
                        auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index2, count19, index);
                        int count20 = world2.computeCount(auxiliaryBlockPosAdapter.getX(), auxiliaryBlockPosAdapter.getY(), auxiliaryBlockPosAdapter.getZ(), 0);
                        int count21 = count20 >> 16 & 65535;
                        int count22 = count20 & 65535;
                        worldRenderer.getWorldRenderer((double)index2 - offset4 + 0.5, (double)count17, (double)index - offset5 + 0.5)
                           .getWorldRenderer6(0.0, (double)count17 * 0.25 + offset6)
                           .resolveWorldRenderer(1.0F, 1.0F, 1.0F, factor31)
                           .resolveWorldRenderer(count21, count22)
                           .updateState();
                        worldRenderer.getWorldRenderer((double)index2 + offset4 + 0.5, (double)count17, (double)index + offset5 + 0.5)
                           .getWorldRenderer6(1.0, (double)count17 * 0.25 + offset6)
                           .resolveWorldRenderer(1.0F, 1.0F, 1.0F, factor31)
                           .resolveWorldRenderer(count21, count22)
                           .updateState();
                        worldRenderer.getWorldRenderer((double)index2 + offset4 + 0.5, (double)count18, (double)index + offset5 + 0.5)
                           .getWorldRenderer6(1.0, (double)count18 * 0.25 + offset6)
                           .resolveWorldRenderer(1.0F, 1.0F, 1.0F, factor31)
                           .resolveWorldRenderer(count21, count22)
                           .updateState();
                        worldRenderer.getWorldRenderer((double)index2 - offset4 + 0.5, (double)count18, (double)index - offset5 + 0.5)
                           .getWorldRenderer6(0.0, (double)count18 * 0.25 + offset6)
                           .resolveWorldRenderer(1.0F, 1.0F, 1.0F, factor31)
                           .resolveWorldRenderer(count21, count22)
                           .updateState();
                     } else {
                        if (byteValue2 != 1) {
                           if (byteValue2 >= 0) {
                              tessellator.updateState();
                           }

                           byteValue2 = 1;
                           this.mc.getTextureManager().applyResourceLocation3(resourceLocation2);
                           worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry8);
                        }

                        double offset9 = (double)(((float)(this.count2 & 511) + factor26) / 512.0F);
                        double offset10 = this.random.nextDouble() + (double)factor27 * 0.01 * (double)((float)this.random.nextGaussian());
                        double offset11 = this.random.nextDouble() + (double)(factor27 * (float)this.random.nextGaussian()) * 0.001;
                        double offset12 = (double)((float)index2 + 0.5F) - renderViewEntity.posX;
                        double offset13 = (double)((float)index + 0.5F) - renderViewEntity.posZ;
                        float factor32 = MathHelper.sqrt_double(offset12 * offset12 + offset13 * offset13) / (float)byteValue;
                        float factor33 = ((1.0F - factor32 * factor32) * 0.3F + 0.5F) * floatItem;
                        auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index2, count19, index);
                        int count23 = (world2.computeCount(auxiliaryBlockPosAdapter.getX(), auxiliaryBlockPosAdapter.getY(), auxiliaryBlockPosAdapter.getZ(), 0) * 3 + 15728880) / 4;
                        int count24 = count23 >> 16 & 65535;
                        int count25 = count23 & 65535;
                        worldRenderer.getWorldRenderer((double)index2 - offset4 + 0.5, (double)count17, (double)index - offset5 + 0.5)
                           .getWorldRenderer6(0.0 + offset10, (double)count17 * 0.25 + offset9 + offset11)
                           .resolveWorldRenderer(1.0F, 1.0F, 1.0F, factor33)
                           .resolveWorldRenderer(count24, count25)
                           .updateState();
                        worldRenderer.getWorldRenderer((double)index2 + offset4 + 0.5, (double)count17, (double)index + offset5 + 0.5)
                           .getWorldRenderer6(1.0 + offset10, (double)count17 * 0.25 + offset9 + offset11)
                           .resolveWorldRenderer(1.0F, 1.0F, 1.0F, factor33)
                           .resolveWorldRenderer(count24, count25)
                           .updateState();
                        worldRenderer.getWorldRenderer((double)index2 + offset4 + 0.5, (double)count18, (double)index + offset5 + 0.5)
                           .getWorldRenderer6(1.0 + offset10, (double)count18 * 0.25 + offset9 + offset11)
                           .resolveWorldRenderer(1.0F, 1.0F, 1.0F, factor33)
                           .resolveWorldRenderer(count24, count25)
                           .updateState();
                        worldRenderer.getWorldRenderer((double)index2 - offset4 + 0.5, (double)count18, (double)index - offset5 + 0.5)
                           .getWorldRenderer6(0.0 + offset10, (double)count18 * 0.25 + offset9 + offset11)
                           .resolveWorldRenderer(1.0F, 1.0F, 1.0F, factor33)
                           .resolveWorldRenderer(count24, count25)
                           .updateState();
                     }
                  }
               }
            }
         }

         if (byteValue2 >= 0) {
            tessellator.updateState();
         }

         worldRenderer.applyDouble(0.0, 0.0, 0.0);
         GlStateManager.enableLighting();
         GlStateManager.enableBlend();
         GlStateManager.alphaFunc(516, 0.1F);
         this.updateState5();
      }
   }

   public void applyFloat(float factor26, long value4) {
      this.applyFloat4(factor26);
      if (this.mc.getRenderViewEntity() == null) {
         EntityPlayerSP player = this.mc.thePlayer;
         this.mc.applyEntityByEntity(player);
      }

      this.processFloat5(factor26, false);
      this.factor5 = this.mc.getRenderViewEntity().prevRotationYaw;
      this.factor25 = this.mc.getRenderViewEntity().prevRotationPitch;
      this.factor16 = this.mc.getRenderViewEntity().rotationYaw;
      this.factor20 = this.mc.getRenderViewEntity().rotationPitch;
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      renderViewEntity.rotationYaw = Client.getClient().getViaProtocolHelper().scaleFactor1;
      renderViewEntity.rotationPitch = Client.getClient().getViaProtocolHelper().scaleFactor2;
      renderViewEntity.prevRotationYaw = Client.getClient().getViaProtocolHelper().scaleFactor3;
      renderViewEntity.prevRotationPitch = Client.getClient().getViaProtocolHelper().scaleFactor;
      double posX = renderViewEntity.posX;
      double posY = renderViewEntity.posY;
      double posZ = renderViewEntity.posZ;
      double prevPosX = renderViewEntity.prevPosX;
      double prevPosY = renderViewEntity.prevPosY;
      double prevPosZ = renderViewEntity.prevPosZ;
      double lastTickPosX = renderViewEntity.lastTickPosX;
      double lastTickPosY = renderViewEntity.lastTickPosY;
      double lastTickPosZ = renderViewEntity.lastTickPosZ;
      FreeCam freeCam = Client.getClient().getModuleManager().freeCam;
      if (((Module)freeCam).isToggled()) {
         freeCam.reachDistance6 = posX;
         freeCam.reachDistance5 = posY;
         freeCam.reachDistance1 = posZ;
         freeCam.reachDistance4 = lastTickPosX;
         freeCam.reachDistance = lastTickPosY;
         freeCam.reachDistance3 = lastTickPosZ;
         renderViewEntity.posX = freeCam.positionVec.xCoord;
         renderViewEntity.posY = freeCam.positionVec.yCoord;
         renderViewEntity.posZ = freeCam.positionVec.zCoord;
         renderViewEntity.lastTickPosX = freeCam.positionVec2.xCoord;
         renderViewEntity.lastTickPosY = freeCam.positionVec2.yCoord;
         renderViewEntity.lastTickPosZ = freeCam.positionVec2.zCoord;
         renderViewEntity.prevPosX = freeCam.positionVec2.xCoord;
         renderViewEntity.prevPosY = freeCam.positionVec2.yCoord;
         renderViewEntity.prevPosZ = freeCam.positionVec2.zCoord;
      }

      if (BuildManager.canExecute()) {
         MovementHandler.state = true;
         PreEndHandManager.applyMinecraftByMinecraft(this.mc, factor26, value4);
      }

      GlStateManager.disableDepth();
      GlStateManager.applyState14();
      GlStateManager.alphaFunc(516, 0.1F);
      this.mc.uNKNOWNManager.applyTextByText("center");
      if (this.mc.gameSettings.state105) {
         count10 = 0;
         GlStateManager.applyFlag(false, true, true, false);
         this.applyIndex(0, factor26, value4);
         count10 = 1;
         GlStateManager.applyFlag(true, false, false, false);
         this.applyIndex(1, factor26, value4);
         GlStateManager.applyFlag(true, true, true, false);
      } else {
         this.applyIndex(2, factor26, value4);
      }

      if (((Module)freeCam).isToggled()) {
         renderViewEntity.posX = posX;
         renderViewEntity.posY = posY;
         renderViewEntity.posZ = posZ;
         renderViewEntity.lastTickPosX = lastTickPosX;
         renderViewEntity.lastTickPosY = lastTickPosY;
         renderViewEntity.lastTickPosZ = lastTickPosZ;
         renderViewEntity.prevPosX = prevPosX;
         renderViewEntity.prevPosY = prevPosY;
         renderViewEntity.prevPosZ = prevPosZ;
      }

      this.mc.uNKNOWNManager.updateState();
   }

   public void updateState2() {
      if (OpenGlHelper.enabled4 && this.mc.getRenderViewEntity() instanceof EntityPlayer) {
         if (this.passManager != null) {
            this.passManager.updateState();
         }

         this.count8 = (this.count8 + 1) % (resourceLocationArray.length + 1);
         if (this.count8 != count) {
            this.applyResourceLocationByResourceLocation(resourceLocationArray[this.count8]);
         } else {
            this.passManager = null;
         }
      }
   }

   private FloatBuffer resolveFloatBuffer(float posX, float posX2, float posY, float posZ) {
      if (BuildManager.canExecute()) {
         PreEndHandManager.applyFloat(posX, posX2, posY);
      }

      ((Buffer)this.floatBuffer).clear();
      this.floatBuffer.put(posX).put(posX2).put(posY).put(posZ);
      ((Buffer)this.floatBuffer).flip();
      return this.floatBuffer;
   }

   public void updateState() {
      if (OpenGlHelper.enabled4 && AndFSManager.getAndFSManager() == null) {
         AndFSManager.updateState();
      }

      this.updateState12();
      this.updateState3();
      this.factor23 = this.factor17;
      this.factor12 = this.scaleFactor;
      if (this.mc.gameSettings.state32) {
         float factor26 = this.mc.gameSettings.scaleFactor * 0.6F + 0.2F;
         float factor27 = factor26 * factor26 * factor26 * 8.0F;
         this.factor7 = this.compositeFloatEntry.computeFactor(this.factor22, 0.05F * factor27);
         this.factor = this.compositeFloatEntry2.computeFactor(this.factor11, 0.05F * factor27);
         this.factor3 = 0.0F;
         this.factor22 = 0.0F;
         this.factor11 = 0.0F;
      } else {
         this.factor7 = 0.0F;
         this.factor = 0.0F;
         this.compositeFloatEntry.updateState();
         this.compositeFloatEntry2.updateState();
      }

      if (this.mc.getRenderViewEntity() == null) {
         EntityPlayerSP player = this.mc.thePlayer;
         this.mc.applyEntityByEntity(player);
      }

      Entity renderViewEntity = this.mc.getRenderViewEntity();
      double posX = renderViewEntity.posX;
      double offset = renderViewEntity.posY + (double)renderViewEntity.getHeight2();
      double posZ = renderViewEntity.posZ;
      float factor28 = this.mc
         .theWorld
         .computeFactor(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(offset), MathHelper.ceiling_double_int(posZ));
      float factor29 = MathHelper.clamp_float((float)this.mc.gameSettings.indexOffset16 / 16.0F, 0.0F, 1.0F);
      float factor30 = factor28 * (1.0F - factor29) + factor29;
      this.factor17 = this.factor17 + (factor30 - this.factor17) * 0.1F;
      this.count2++;
      this.itemRenderer.updateState3();
      this.updateState9();
      this.factor19 = this.factor14;
      if (DefaultFloatEntry.enabled) {
         this.factor14 += 0.05F;
         if (this.factor14 > 1.0F) {
            this.factor14 = 1.0F;
         }

         DefaultFloatEntry.enabled = false;
      } else if (this.factor14 > 0.0F) {
         this.factor14 -= 0.0125F;
      }
   }

   public PassManager getPassManager() {
      return this.passManager;
   }

   private void applyFloat(float factor26) {
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      EventStrafe event = StrafeListener.resolveEventStrafe(renderViewEntity.getHeight2(), renderViewEntity.rotationYaw, renderViewEntity.rotationPitch);
      float scaleFactor2 = event.getScaleFactor();
      double offset = renderViewEntity.prevPosX + (renderViewEntity.posX - renderViewEntity.prevPosX) * (double)factor26;
      double offset2 = renderViewEntity.prevPosY + (renderViewEntity.posY - renderViewEntity.prevPosY) * (double)factor26 + (double)scaleFactor2;
      double offset3 = renderViewEntity.prevPosZ + (renderViewEntity.posZ - renderViewEntity.prevPosZ) * (double)factor26;
      if (renderViewEntity instanceof EntityLivingBase && ((EntityLivingBase)renderViewEntity).isState5()) {
         scaleFactor2 = (float)((double)scaleFactor2 + 1.0);
         GlStateManager.translate(0.0F, 0.3F, 0.0F);
         if (!this.mc.gameSettings.state54) {
            BlockPos blockPos = new BlockPos(renderViewEntity);
            IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
            if (blockState.getBlock() == Blocks.bed) {
               GlStateManager.rotate((float)(((EnumFacing)blockState.getComparable(BlockBed.autoWalkPath)).getIndexOffset() * 90), 0.0F, 1.0F, 0.0F);
            }

            GlStateManager.rotate(renderViewEntity.prevRotationYaw + (renderViewEntity.rotationYaw - renderViewEntity.prevRotationYaw) * factor26 + 180.0F, 0.0F, -1.0F, 0.0F);
            GlStateManager.rotate(renderViewEntity.prevRotationPitch + (renderViewEntity.rotationPitch - renderViewEntity.prevRotationPitch) * factor26, -1.0F, 0.0F, 0.0F);
         }
      } else if (this.mc.gameSettings.indexOffset64 > 0) {
         double offset4 = (double)(this.factor12 + (this.scaleFactor - this.factor12) * factor26);
         if (this.mc.gameSettings.state54) {
            GlStateManager.translate(0.0F, 0.0F, (float)(-offset4));
         } else {
            float factor27 = event.getFactor();
            float factor28 = event.getFactor2();
            if (this.mc.gameSettings.indexOffset64 == 2) {
               factor28 += 180.0F;
            }

            double offset5 = (double)(-MathHelper.abs(factor27 / 180.0F * (float) Math.PI) * MathHelper.cos(factor28 / 180.0F * (float) Math.PI)) * offset4;
            double offset6 = (double)(MathHelper.cos(factor27 / 180.0F * (float) Math.PI) * MathHelper.cos(factor28 / 180.0F * (float) Math.PI)) * offset4;
            double offset7 = (double)(-MathHelper.abs(factor28 / 180.0F * (float) Math.PI)) * offset4;
            if (!((Module)ClientGlobals.moduleManager.cameraNoClip).isToggled()) {
               for (int index = 0; index < 8; index++) {
                  float factor29 = (float)((index & 1) * 2 - 1);
                  float factor30 = (float)((index >> 1 & 1) * 2 - 1);
                  float factor31 = (float)((index >> 2 & 1) * 2 - 1);
                  float factor32 = factor29 * 0.1F;
                  float factor33 = factor30 * 0.1F;
                  float factor34 = factor31 * 0.1F;
                  MovingObjectPosition movingObjectPosition = this.mc
                     .theWorld
                     .getMovingObjectPosition2(
                        offset + (double)factor32,
                        offset2 + (double)factor33,
                        offset3 + (double)factor34,
                        offset - offset5 + (double)factor32 + (double)factor34,
                        offset2 - offset7 + (double)factor33,
                        offset3 - offset6 + (double)factor34
                     );
                  if (movingObjectPosition != null) {
                     double doubleItem = movingObjectPosition.hitVec.getDouble2(offset, offset2, offset3);
                     if (doubleItem < offset4) {
                        offset4 = doubleItem;
                     }
                  }
               }
            }

            if (this.mc.gameSettings.indexOffset64 == 2) {
               GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            }

            GlStateManager.rotate(event.getFactor2() - factor28, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(event.getFactor() - factor27, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(0.0F, 0.0F, (float)(-offset4));
            GlStateManager.rotate(factor27 - event.getFactor(), 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(factor28 - event.getFactor2(), 1.0F, 0.0F, 0.0F);
         }
      } else {
         GlStateManager.translate(0.0F, 0.0F, -0.1F);
      }

      if (!this.mc.gameSettings.state54) {
         if (event.isEnabled()) {
            GlStateManager.rotate(event.getFactor2(), 1.0F, 0.0F, 0.0F);
            if (renderViewEntity instanceof HitBoxBuffer) {
               GlStateManager.rotate(event.getFactor() + 180.0F, 0.0F, 1.0F, 0.0F);
            } else {
               GlStateManager.rotate(event.getFactor() + 180.0F, 0.0F, 1.0F, 0.0F);
            }
         } else {
            GlStateManager.rotate(renderViewEntity.prevRotationPitch + (renderViewEntity.rotationPitch - renderViewEntity.prevRotationPitch) * factor26, 1.0F, 0.0F, 0.0F);
            if (renderViewEntity instanceof HitBoxBuffer) {
               HitBoxBuffer hitBoxBuffer = (HitBoxBuffer)renderViewEntity;
               GlStateManager.rotate(hitBoxBuffer.scaleFactor22 + (hitBoxBuffer.scaleFactor12 - hitBoxBuffer.scaleFactor22) * factor26 + 180.0F, 0.0F, 1.0F, 0.0F);
            } else {
               GlStateManager.rotate(renderViewEntity.prevRotationYaw + (renderViewEntity.rotationYaw - renderViewEntity.prevRotationYaw) * factor26 + 180.0F, 0.0F, 1.0F, 0.0F);
            }
         }
      }

      GlStateManager.translate3d2(-event.getPositionVec().xCoord, -event.getPositionVec().yCoord - (double)scaleFactor2, -event.getPositionVec().zCoord);
      double offset8 = renderViewEntity.prevPosX + (renderViewEntity.posX - renderViewEntity.prevPosX) * (double)factor26;
      double offset9 = renderViewEntity.prevPosY + (renderViewEntity.posY - renderViewEntity.prevPosY) * (double)factor26 + (double)scaleFactor2;
      double offset10 = renderViewEntity.prevPosZ + (renderViewEntity.posZ - renderViewEntity.prevPosZ) * (double)factor26;
      this.enabled = this.mc.renderGlobal.checkDouble(offset8, offset9, offset10, factor26);
   }

   public void applyEntityByEntity(Entity entity2) {
      if (OpenGlHelper.enabled4) {
         if (this.passManager != null) {
            this.passManager.updateState();
         }

         this.passManager = null;
         if (entity2 instanceof EntityCreeper) {
            this.applyResourceLocationByResourceLocation(new ResourceLocation("shaders/post/creeper.json"));
         } else if (entity2 instanceof EntitySpider) {
            this.applyResourceLocationByResourceLocation(new ResourceLocation("shaders/post/spider.json"));
         } else if (entity2 instanceof EntityEnderman) {
            this.applyResourceLocationByResourceLocation(new ResourceLocation("shaders/post/invert.json"));
         }
      }
   }

   public boolean isActive() {
      return OpenGlHelper.enabled4 && this.passManager != null;
   }

   public void updateState7() {
      GlStateManager.enableState5(OpenGlHelper.defaultTexUnit);
      GlStateManager.matrixMode(5890);
      GlStateManager.loadIdentity();
      float factor26 = 0.00390625F;
      GlStateManager.translate3f3(factor26, factor26, factor26);
      GlStateManager.translate(8.0F, 8.0F, 8.0F);
      GlStateManager.matrixMode(5888);
      this.mc.getTextureManager().applyResourceLocation3(this.resourceLocation3);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexParameteri(3553, 10242, 33071);
      GL11.glTexParameteri(3553, 10243, 33071);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableTexture2D();
      GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit);
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState51();
      }
   }

   private void applyFloat2(float factor26) {
      if (this.mc.getRenderViewEntity() instanceof EntityPlayer) {
         EntityPlayer renderViewEntity = (EntityPlayer)this.mc.getRenderViewEntity();
         float factor27 = renderViewEntity.distanceWalkedModified - renderViewEntity.factor4;
         float factor28 = -(renderViewEntity.distanceWalkedModified + factor27 * factor26);
         float factor29 = renderViewEntity.factor30 + (renderViewEntity.factor47 - renderViewEntity.factor30) * factor26;
         float factor30 = renderViewEntity.factor18 + (renderViewEntity.factor8 - renderViewEntity.factor18) * factor26;
         GlStateManager.translate(MathHelper.abs(factor28 * (float) Math.PI) * factor29 * 0.5F, -Math.abs(MathHelper.cos(factor28 * (float) Math.PI) * factor29), 0.0F);
         GlStateManager.rotate(MathHelper.abs(factor28 * (float) Math.PI) * factor29 * 3.0F, 0.0F, 0.0F, 1.0F);
         GlStateManager.rotate(Math.abs(MathHelper.cos(factor28 * (float) Math.PI - 0.2F) * factor29) * 5.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(factor30, 1.0F, 0.0F, 0.0F);
      }
   }

   private void applyEntityByEntity(Entity entity2, double offset, PreMotionConsumer preMotionConsumer, boolean flag) {
      int indexOffset = this.mc.gameSettings.indexOffset40;
      boolean state2 = this.mc.gameSettings.state66;

      try {
         this.mc.gameSettings.indexOffset40 = 1000;
         this.mc.gameSettings.state66 = false;
         RenderGlobal renderGlobalAsRenderGlobal = BuildManager.getRenderGlobalAsRenderGlobal();
         int countAsIndex = renderGlobalAsRenderGlobal.getCountAsIndex();
         System.currentTimeMillis();
         BuildManager.applyText4("Loading visible chunks");
         long value4 = System.currentTimeMillis() + 5000L;
         int count11 = 0;

         boolean flag2;
         do {
            flag2 = false;

            for (int index = 0; index < 100; index++) {
               renderGlobalAsRenderGlobal.enabled6 = true;
               renderGlobalAsRenderGlobal.applyEntityByEntity(entity2, offset, preMotionConsumer, this.count5++, flag);
               if (!renderGlobalAsRenderGlobal.isActiveAsFlag()) {
                  flag2 = true;
               }

               int count12 = count11 + renderGlobalAsRenderGlobal.sizeAsIndex();

               while (!renderGlobalAsRenderGlobal.isActiveAsFlag()) {
                  renderGlobalAsRenderGlobal.applyTime(System.nanoTime() + 1000000000L);
               }

               count11 = count12 - renderGlobalAsRenderGlobal.sizeAsIndex();
               if (!flag2) {
                  break;
               }
            }

            if (renderGlobalAsRenderGlobal.getCountAsIndex() != countAsIndex) {
               flag2 = true;
               countAsIndex = renderGlobalAsRenderGlobal.getCountAsIndex();
            }

            if (System.currentTimeMillis() > value4) {
               BuildManager.applyText("Chunks loaded: " + count11);
               value4 = System.currentTimeMillis() + 5000L;
            }
         } while (flag2);

         BuildManager.applyText("Chunks loaded: " + count11);
         BuildManager.applyText("Finished loading visible chunks");
         RenderChunk.count3 = 0;
      } finally {
         this.mc.gameSettings.indexOffset40 = indexOffset;
         this.mc.gameSettings.state66 = state2;
      }
   }

   public boolean checkIndex(int count11) {
      if (!OpenGlHelper.isActiveAsFlag()) {
         return false;
      } else if (this.passManager != null && this.passManager != this.passManagerArray[2] && this.passManager != this.passManagerArray[4]) {
         return true;
      } else if (count11 != 2 && count11 != 4) {
         if (this.passManager == null) {
            return true;
         } else {
            this.passManager.updateState();
            this.passManager = null;
            return true;
         }
      } else if (this.passManager != null && this.passManager == this.passManagerArray[count11]) {
         return true;
      } else if (this.mc.theWorld == null) {
         return true;
      } else {
         this.applyResourceLocationByResourceLocation(new ResourceLocation("shaders/post/fxaa_of_" + count11 + "x.json"));
         this.passManagerArray[count11] = this.passManager;
         return this.enabled6;
      }
   }

   public void updateState11() {
      this.enabled6 = !this.enabled6;
   }

   private float computeFactor(float factor26, boolean flag) {
      if (this.enabled9) {
         return 90.0F;
      } else {
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         float factor27 = 70.0F;
         if (flag) {
            factor27 = this.mc.gameSettings.scaleFactor4;
            FovModifier fovModifier = ClientGlobals.moduleManager.fovModifier;
            if (((Module)fovModifier).isToggled()) {
               float value4 = (float)fovModifier.settingDefaultFov.getValue();
               boolean flag2 = this.mc.thePlayer.checkPotion(Potion.potion2) && MoveUtil.canExecute();
               boolean riding = this.mc.thePlayer.isRiding();
               float factor28 = value4;
               if (riding || flag2) {
                  factor28 = flag2 ? (float)fovModifier.settingSpeedFov.getValue() : (float)fovModifier.settingSprintFov.getValue();
               }

               if (riding != fovModifier.enabled2 || flag2 != fovModifier.expanded2) {
                  fovModifier.fovAnimation
                     .updateEasingFunction(
                        Math.max(1, (int)fovModifier.settingAnimationTime.getValue()),
                        KeybindAnimation.getEasingFunction(fovModifier.settingAnimationModes.getSelected()),
                        factor28 > fovModifier.motionMultiplier ? 1 : 0,
                        false
                     );
               }

               float factor29 = fovModifier.motionMultiplier - factor28;
               factor27 = (float)((double)factor28 + (double)factor29 * fovModifier.fovAnimation.getDouble3());
               if (riding != fovModifier.enabled2 || flag2 != fovModifier.expanded2) {
                  fovModifier.motionMultiplier = fovModifier.motionMultiplier1;
                  fovModifier.motionMultiplier1 = factor28;
               }

               fovModifier.enabled2 = riding;
               fovModifier.expanded2 = flag2;
            }

            if (BuildManager.isActive50()
               && (
                  !((Module)ClientGlobals.moduleManager.fovModifier).isToggled()
                     || !ClientGlobals.moduleManager.fovModifier.settingIgnoreDynamicFov.isEnabled()
               )) {
               factor27 *= this.factor10 + (this.factor13 - this.factor10) * factor26;
            }
         }

         boolean flag3 = false;
         if (this.mc.currentScreen == null) {
            flag3 = GameSettings.matchesKeyBinding(this.mc.gameSettings.ofKeyBindZoom);
         }

         boolean flag4 = flag3;
         if (((Module)ClientGlobals.moduleManager.customZoom).isToggled()) {
            CustomZoom customZoom = ClientGlobals.moduleManager.customZoom;
            if (flag3 != this.enabled3) {
               KeybindAnimation keybindAnimation = customZoom.getKeybindAnimation();
               int value5 = (int)customZoom.settingAnimationTime.getValue();
               customZoom.getKeybindAnimation();
               keybindAnimation.updateEasingFunction(value5, KeybindAnimation.getEasingFunction(customZoom.settingAnimationModes.getSelected()), flag3 ? 1 : 0, true);
            }

            if (!flag3 && customZoom.getKeybindAnimation().getDouble2() < 1.0 && BuildManager.enabled10 && !customZoom.settingAnimationModes.getSelected().equals("None")
               )
             {
               flag3 = true;
            }
         }

         this.enabled3 = flag4;
         if (flag3) {
            if (!BuildManager.enabled10) {
               BuildManager.enabled10 = true;
               BuildManager.enabled6 = this.mc.gameSettings.state32;
               this.mc.gameSettings.state32 = true;
               this.mc.renderGlobal.enabled6 = true;
            }

            if (BuildManager.enabled10) {
               EventFovUpdate event = EventFovUpdateCallback.resolveEventFovUpdate(4.0F);
               factor27 /= event.getFactor();
            }
         } else if (BuildManager.enabled10) {
            BuildManager.enabled10 = false;
            this.mc.gameSettings.state32 = false;
            this.compositeFloatEntry = new CompositeFloatEntry();
            this.compositeFloatEntry2 = new CompositeFloatEntry();
            this.mc.renderGlobal.enabled6 = true;
         }

         if (renderViewEntity instanceof EntityLivingBase && ((EntityLivingBase)renderViewEntity).getFloat2() <= 0.0F) {
            float factor30 = (float)((EntityLivingBase)renderViewEntity).indexOffset29 + factor26;
            factor27 /= (1.0F - 500.0F / (factor30 + 500.0F)) * 2.0F + 1.0F;
         }

         if (AuxiliaryIntBufferEntry.resolveBlock(this.mc.theWorld, renderViewEntity, factor26).getMaterial() == Material.coreItemEntry1) {
            factor27 = factor27 * 60.0F / 70.0F;
         }

         return factor27;
      }
   }

   private void applyFloat5(float factor26) {
      if (this.mc.gameSettings.state14 && !this.mc.gameSettings.state11 && !this.mc.thePlayer.onDisable() && !this.mc.gameSettings.state22) {
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GL11.glLineWidth(1.0F);
         GlStateManager.enableTexture2D();
         GlStateManager.depthMask(false);
         GlStateManager.pushMatrix();
         GlStateManager.matrixMode(5888);
         GlStateManager.loadIdentity();
         this.applyFloat(factor26);
         GlStateManager.translate(0.0F, renderViewEntity.getHeight2(), 0.0F);
         RenderGlobal.applyDouble(0.0, 0.0, 0.0, 0.005, 1.0E-4, 1.0E-4, 255, 0, 0, 255);
         RenderGlobal.applyDouble(0.0, 0.0, 0.0, 1.0E-4, 1.0E-4, 0.005, 0, 0, 255, 255);
         RenderGlobal.applyDouble(0.0, 0.0, 0.0, 1.0E-4, 0.0033, 1.0E-4, 0, 255, 0, 255);
         GlStateManager.popMatrix();
         GlStateManager.depthMask(true);
         GlStateManager.disableTexture2D();
         GlStateManager.enableBlend();
      }
   }

   public void applyFloat(float factor26, int count11, boolean flag, boolean flag2, boolean flag3) {
      if (!this.enabled9) {
         GlStateManager.matrixMode(5889);
         GlStateManager.loadIdentity();
         float factor27 = 0.07F;
         if (this.mc.gameSettings.state105) {
            GlStateManager.translate((float)(-(count11 * 2 - 1)) * factor27, 0.0F, 0.0F);
         }

         if (BuildManager.canExecute()) {
            PreEndHandManager.glScaled();
         }

         EventTimerSpeed event = EventTimerSpeedCallback.resolveEventTimerSpeed((float)this.mc.displayWidth / (float)this.mc.displayHeight, true);
         Project.gluPerspective(this.computeFactor(factor26, false), event.getScaleFactor(), 0.05F, this.factor21 * 2.0F);
         GlStateManager.matrixMode(5888);
         GlStateManager.loadIdentity();
         if (this.mc.gameSettings.state105) {
            GlStateManager.translate((float)(count11 * 2 - 1) * 0.1F, 0.0F, 0.0F);
         }

         boolean flag4 = false;
         if (flag) {
            GlStateManager.pushMatrix();
            this.applyFloat6(factor26);
            if (this.mc.gameSettings.state28) {
               this.applyFloat2(factor26);
            }

            flag4 = this.mc.getRenderViewEntity() instanceof EntityLivingBase && ((EntityLivingBase)this.mc.getRenderViewEntity()).isState5();
            if (this.mc.gameSettings.indexOffset64 == 0 && !flag4 && !this.mc.gameSettings.state11 && !this.mc.playerController.isActive()) {
               this.updateState7();
               if (BuildManager.canExecute()) {
                  RotationBridge.applyItemRenderer(this.itemRenderer, factor26, flag3);
               } else {
                  this.itemRenderer.applyFloatByFloat(factor26);
               }

               this.updateState5();
            }

            GlStateManager.popMatrix();
         }

         if (!flag2) {
            return;
         }

         this.updateState5();
         if (this.mc.gameSettings.indexOffset64 == 0 && !flag4) {
            this.itemRenderer.applyFloat2(factor26);
            this.applyFloat6(factor26);
         }

         if (this.mc.gameSettings.state28) {
            this.applyFloat2(factor26);
         }
      }
   }

   public void processFloat5(float factor26, boolean flag) {
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      if (renderViewEntity != null && this.mc.theWorld != null) {
         this.mc.uNKNOWNManager.applyTextByText("pick");
         this.mc.pointedEntity = null;
         double offset = ((Module)ClientGlobals.moduleManager.range).isToggled()
            ? Math.max(ClientGlobals.moduleManager.range.settingBuildRange.getValue(), 4.5)
            : (double)this.mc.playerController.getBlockReachDistance();
         if (((Module)ClientGlobals.moduleManager.teleport).isToggled()) {
            offset = 200.0;
         }

         boolean flag2 = false;
         double offset2 = 0.0;
         double offset3 = 0.0;
         double offset4 = 0.0;
         double offset5 = 0.0;
         double offset6 = 0.0;
         double offset7 = 0.0;
         if (((Module)ClientGlobals.moduleManager.freeCam).isToggled() && ClientGlobals.moduleManager.freeCam.settingUpdateRaytrace.isEnabled()) {
            FreeCam freeCam = ClientGlobals.moduleManager.freeCam;
            offset2 = renderViewEntity.posX;
            offset4 = renderViewEntity.posY;
            offset6 = renderViewEntity.posZ;
            renderViewEntity.posX = freeCam.positionVec.xCoord;
            renderViewEntity.posY = freeCam.positionVec.yCoord;
            renderViewEntity.posZ = freeCam.positionVec.zCoord;
            renderViewEntity.prevPosX = freeCam.positionVec2.xCoord;
            renderViewEntity.prevPosY = freeCam.positionVec2.yCoord;
            renderViewEntity.prevPosZ = freeCam.positionVec2.zCoord;
            flag2 = true;
         }

         Minecraft mc2 = this.mc;
         MovingObjectPosition movingObjectPosition = renderViewEntity.getMovingObjectPosition2(offset, factor26);
         mc2.objectMouseOver = movingObjectPosition;
         double offset8 = offset;
         Vec3 vec3 = renderViewEntity.getVec35(factor26);
         boolean flag3 = false;
         if (this.mc.playerController.processAction3()) {
            offset = 6.0;
            offset8 = 6.0;
         } else if (offset > 3.0) {
            flag3 = true;
         }

         if (this.mc.objectMouseOver == null
            || ClientGlobals.moduleManager.range.checkCondition() && ClientGlobals.moduleManager.range.settingThroughWalls.isEnabled()) {
            if (ClientGlobals.moduleManager.range.checkCondition()) {
               if (ClientGlobals.moduleManager.fixes.canExecute7()) {
                  offset8 = 6.0;
               }

               offset8 = Math.max(offset8, ClientGlobals.moduleManager.range.getCoordinate());
            }
         } else {
            offset8 = this.mc.objectMouseOver.hitVec.getCoordinate4(vec3);
         }

         if (ClientGlobals.moduleManager.fixes.canExecute7()) {
            offset = 6.0;
         }

         double offset9 = Math.max(offset, ClientGlobals.moduleManager.range.getCoordinate());
         Vec3 vec32 = renderViewEntity.getVec36(factor26);
         Vec3 vec33 = vec3.getVec312(vec32.xCoord * offset9, vec32.yCoord * offset9, vec32.zCoord * offset9);
         this.entity = null;
         Vec3 vec = null;
         float factor27 = 1.0F;
         List entitiesInAABBexcluding = this.mc
            .theWorld
            .getEntitiesInAABBexcluding(
               renderViewEntity,
               renderViewEntity.getBoundingBox2()
                  .contract(vec32.xCoord * offset9, vec32.yCoord * offset9, vec32.zCoord * offset9)
                  .offset((double)factor27, (double)factor27, (double)factor27),
               Predicates.and(LivingEntityFilter.predicate4, new EntitySelectPredicates(this))
            );
         double offset10 = offset8;

         for (int index = 0; index < entitiesInAABBexcluding.size(); index++) {
            Entity entity2 = (Entity)entitiesInAABBexcluding.get(index);
            float height = entity2.getHeight();
            AxisAlignedBB boundingBox = entity2.getBoundingBox2();
            AxisAlignedBB boundingBox2;
            if (((Module)ClientGlobals.moduleManager.hitBox).isToggled()) {
               boundingBox2 = ClientGlobals.moduleManager.hitBox.getAxisAlignedBB(entity2, height);
            } else {
               boundingBox2 = boundingBox.offset((double)height, (double)height, (double)height);
            }

            MovingObjectPosition hitResult = boundingBox2.calculateIntercept(vec3, vec33);
            if (boundingBox2.isVecInside(vec3)) {
               if (offset10 >= 0.0) {
                  this.entity = entity2;
                  vec = hitResult == null ? vec3 : hitResult.hitVec;
                  offset10 = 0.0;
               }
            } else if (hitResult != null) {
               double coordinate = vec3.getCoordinate4(hitResult.hitVec);
               if (coordinate < offset10 || offset10 == 0.0) {
                  if (entity2 == renderViewEntity.ridingEntity) {
                     if (offset10 == 0.0) {
                        this.entity = entity2;
                        vec = hitResult.hitVec;
                     }
                  } else {
                     this.entity = entity2;
                     vec = hitResult.hitVec;
                     offset10 = coordinate;
                  }
               }
            }
         }

         if (this.mc.playerController != null && this.mc.thePlayer != null && ClientGlobals.moduleManager.noMiningTrace.canExecute5() || flag) {
            this.entity = null;
         }

         if (this.entity != null
            && flag3
            && vec3.getCoordinate4(vec)
               > (
                  ClientGlobals.moduleManager.range.checkCondition() && this.mc.thePlayer.getDistanceSqToEntity(this.entity) < 6.0F
                     ? ClientGlobals.moduleManager.range.getCoordinate()
                     : 3.0
               )) {
            this.entity = null;
            mc2 = this.mc;
            MovingObjectPosition hitResult2 = new MovingObjectPosition(MovingObjectType.MISS, vec, (EnumFacing)null, new BlockPos(vec));
            mc2.objectMouseOver = hitResult2;
         }

         if (this.entity != null && (offset10 < offset8 || this.mc.objectMouseOver == null)) {
            mc2 = this.mc;
            MovingObjectPosition hitResult3 = new MovingObjectPosition(this.entity, vec);
            mc2.objectMouseOver = hitResult3;
            if (this.entity instanceof EntityLivingBase || this.entity instanceof EntityItemframe) {
               Entity entity3 = this.entity;
               this.mc.pointedEntity = entity3;
            }
         }

         if (flag2) {
            renderViewEntity.posX = offset2;
            renderViewEntity.posY = offset4;
            renderViewEntity.posZ = offset6;
            renderViewEntity.prevPosX = offset3;
            renderViewEntity.prevPosY = offset5;
            renderViewEntity.prevPosZ = offset7;
         }
      }

      if (factor26 == 1.0F) {
         Minecraft mc3 = this.mc;
         MovingObjectPosition hitResult4 = new MovingObjectPosition(this.mc.objectMouseOver);
         mc3.movingObjectPosition = hitResult4;
      }
   }

   private void updateState12() {
      float factor26 = 1.0F;
      if (this.mc.getRenderViewEntity() instanceof AbstractClientPlayer) {
         factor26 = ((AbstractClientPlayer)this.mc.getRenderViewEntity()).getFactor4();
      }

      this.factor10 = this.factor13;
      this.factor13 = this.factor13 + (factor26 - this.factor13) * 0.5F;
      if (this.factor13 > 1.5F) {
         this.factor13 = 1.5F;
      }

      if (this.factor13 < 0.1F) {
         this.factor13 = 0.1F;
      }
   }

   private void applyFloat6(float factor26) {
      if (this.mc.getRenderViewEntity() instanceof EntityLivingBase) {
         EntityLivingBase renderViewEntity = (EntityLivingBase)this.mc.getRenderViewEntity();
         float factor27 = (float)renderViewEntity.hurtTime - factor26;
         if (renderViewEntity.getFloat2() <= 0.0F && !ClientGlobals.moduleManager.customHurtCam.canExecute5()) {
            float factor28 = (float)renderViewEntity.indexOffset29 + factor26;
            GlStateManager.rotate(40.0F - 8000.0F / (factor28 + 200.0F), 0.0F, 0.0F, 1.0F);
         }

         if (factor27 < 0.0F) {
            return;
         }

         float factor29 = factor27 / (float)renderViewEntity.count12;
         float factor30 = MathHelper.abs(factor29 * factor29 * factor29 * factor29 * (float) Math.PI);
         float factor31 = renderViewEntity.factor17;
         GlStateManager.rotate(-factor31, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-factor30 * ClientGlobals.moduleManager.customHurtCam.getFloat(), 0.0F, 0.0F, 1.0F);
         GlStateManager.rotate(factor31, 0.0F, 1.0F, 0.0F);
      }
   }

   private void applyIndex(int count11, float factor26) {
      this.enabled10 = false;
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      GL11.glFog(2918, this.resolveFloatBuffer(this.factor9, this.factor15, this.factor2, 1.0F));
      GL11.glNormal3f(0.0F, -1.0F, 0.0F);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      Block block = AuxiliaryIntBufferEntry.resolveBlock(this.mc.theWorld, renderViewEntity, factor26);
      if (renderViewEntity instanceof EntityLivingBase
         && ((EntityLivingBase)renderViewEntity).checkPotion(Potion.potion16)
         && !((Module)ClientGlobals.moduleManager.antiBlind).isToggled()) {
         float factor27 = 5.0F;
         int indexOffset = ((EntityLivingBase)renderViewEntity).getPotionEffect(Potion.potion16).getIndexOffset();
         if (indexOffset < 20) {
            factor27 = 5.0F + (this.factor21 - 5.0F) * (1.0F - (float)indexOffset / 20.0F);
         }

         GlStateManager.applyIndex(9729);
         if (count11 == -1) {
            GlStateManager.glFogf(0.0F);
            GlStateManager.glFogfByFloat(factor27 * 0.8F);
         } else {
            GlStateManager.glFogf(factor27 * 0.25F);
            GlStateManager.glFogfByFloat(factor27);
         }

         if (GLContext.getCapabilities().GL_NV_fog_distance && BuildManager.isActive22()) {
            GL11.glFogi(34138, 34139);
         }
      } else if (this.enabled) {
         GlStateManager.applyIndex(2048);
         GlStateManager.applyFloat(0.1F);
      } else if (block.getMaterial() == Material.coreItemEntry1) {
         GlStateManager.applyIndex(2048);
         float factor28 = BuildManager.isActive34() ? 0.02F : 0.1F;
         if (renderViewEntity instanceof EntityLivingBase && ((EntityLivingBase)renderViewEntity).checkPotion(Potion.potion18)) {
            GlStateManager.applyFloat(0.01F);
         } else {
            GlStateManager.applyFloat(BuildManager.computeFactor(0.1F - (float)LvlManager.computeCount(renderViewEntity) * 0.03F, 0.0F, factor28));
         }
      } else if (block.getMaterial() == Material.material29) {
         GlStateManager.applyIndex(2048);
         GlStateManager.applyFloat(2.0F);
      } else {
         float factor29 = this.factor21;
         this.enabled10 = true;
         GlStateManager.applyIndex(9729);
         if (count11 == -1) {
            GlStateManager.glFogf(0.0F);
            GlStateManager.glFogfByFloat(factor29);
         } else {
            GlStateManager.glFogf(factor29 * BuildManager.getFactorAsFloat());
            GlStateManager.glFogfByFloat(factor29);
         }

         if (GLContext.getCapabilities().GL_NV_fog_distance) {
            if (BuildManager.isActive22()) {
               GL11.glFogi(34138, 34139);
            }

            if (BuildManager.isActive31()) {
               GL11.glFogi(34138, 34140);
            }
         }

         if (this.mc.theWorld.secondaryFloatArrayEntry.checkIndexByIndex((int)renderViewEntity.posX, (int)renderViewEntity.posZ)) {
            GlStateManager.glFogf(factor29 * 0.05F);
            GlStateManager.glFogfByFloat(factor29);
         }
      }

      GlStateManager.applyState6();
      GlStateManager.applyState19();
      GlStateManager.glColorMaterial(1028, 4608);
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager2) {
      if (this.passManager != null) {
         this.passManager.updateState();
      }

      this.passManager = null;
      if (this.count8 != count) {
         this.applyResourceLocationByResourceLocation(resourceLocationArray[this.count8]);
      } else {
         this.applyEntityByEntity(this.mc.getRenderViewEntity());
      }
   }

   private void applyEntityByEntity(Entity entity2, float factor26, PreMotionConsumer preMotionConsumer, boolean flag) {
      if (this.enabled8) {
         this.enabled8 = false;
         this.applyEntityByEntity(entity2, (double)factor26, preMotionConsumer, flag);
         this.mc.ingameGUI.getInternalLoggerEntry().applyIndexByIndex(201435902);
      }

      if (Keyboard.isKeyDown(61) && Keyboard.isKeyDown(38)) {
         if (this.mc.currentScreen != null) {
            return;
         }

         this.enabled8 = true;
         ChatComponentText chatComponentText = new ChatComponentText(StatCollector.getText("of.message.loadingVisibleChunks", new Object[0]));
         this.mc.ingameGUI.getInternalLoggerEntry().applyIChatComponent(chatComponentText, 201435902);
      }
   }

   public void updateState8() {
      if (this.passManager != null) {
         this.passManager.updateState();
      }

      this.passManager = null;
      this.count8 = count;
   }

   private boolean isActiveAsFlag() {
      if (!this.enabled4) {
         return false;
      } else {
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         boolean flag = renderViewEntity instanceof EntityPlayer && !this.mc.gameSettings.state11;
         if (flag
            && !((EntityPlayer)renderViewEntity).invulnerableManager.state1
            && (
               !((Module)ClientGlobals.moduleManager.blockOverlay).isToggled()
                  || ((Module)ClientGlobals.moduleManager.blockOverlay).isToggled()
                     && !ClientGlobals.moduleManager.blockOverlay.settingInAdventureMode.isEnabled()
            )) {
            ItemStack stack = ((EntityPlayer)renderViewEntity).getHeldItem();
            if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
               BlockPos blockPos = this.mc.objectMouseOver.getBlockPos();
               IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
               Block block = blockState.getBlock();
               if (this.mc.playerController.getWorldSettingsGameType() == WorldSettingsGameType.SPECTATOR) {
                  flag = blockState.getBlock().isState8() && this.mc.theWorld.resolveTileEntity(blockPos) instanceof IInventory;
               } else {
                  flag = stack != null && (stack.matchesBlockByBlock(block) || stack.matchesBlock(block));
               }
            }
         }

         return flag;
      }
   }

   private void applyFloat4(float factor26) {
      if (this.enabled12) {
         this.mc.uNKNOWNManager.applyTextByText("lightTex");
         WorldClient world2 = this.mc.theWorld;
         if (world2 != null) {
            if (BuildManager.isActive33()
               && !((Module)ClientGlobals.moduleManager.fullBright).isToggled()
               && !XRay.activeState3
               && (!ClientGlobals.moduleManager.ambiance.settingCustomWorldColor.isEnabled() || !((Module)ClientGlobals.moduleManager.ambiance).isToggled())
               && !((Module)ClientGlobals.moduleManager.mLG).isToggled()
               && LoadingManager.matchesWorldByWorld(world2, this.factor6, this.countArray, this.mc.thePlayer.checkPotion(Potion.potion31), factor26)) {
               this.dynamicTexture.updateState2();
               this.enabled12 = false;
               this.mc.uNKNOWNManager.updateState();
               return;
            }

            float factor27 = world2.computeFactor3(1.0F);
            float factor28 = factor27 * 0.95F + 0.05F;

            for (int index = 0; index < 256; index++) {
               float factor29 = world2.secondaryFloatArrayEntry.getFactorArray()[index / 16] * factor28;
               float factor30 = world2.secondaryFloatArrayEntry.getFactorArray()[index % 16] * (this.factor6 * 0.1F + 1.5F);
               if (world2.getIndexOffset2() > 0) {
                  factor29 = world2.secondaryFloatArrayEntry.getFactorArray()[index / 16];
               }

               float factor31 = factor29 * (factor27 * 0.65F + 0.35F);
               float factor32 = factor29 * (factor27 * 0.65F + 0.35F);
               float factor33 = factor30 * ((factor30 * 0.6F + 0.4F) * 0.6F + 0.4F);
               float factor34 = factor30 * (factor30 * factor30 * 0.6F + 0.4F);
               float factor35 = factor31 + factor30;
               float factor36 = factor32 + factor33;
               float factor37 = factor29 + factor34;
               float factor38 = factor35 * 0.96F + 0.03F;
               float factor39 = factor36 * 0.96F + 0.03F;
               float factor40 = factor37 * 0.96F + 0.03F;
               EventJump event = EventJumpCallback.resolveEventJump(factor38, factor39, factor40, CombatRangeProperty.WORLD_COLOR);
               float factor41 = event.getFactor();
               float factor42 = event.getFactor2();
               float factor43 = event.getFactor3();
               if (this.factor14 > 0.0F) {
                  float factor44 = this.factor19 + (this.factor14 - this.factor19) * factor26;
                  factor41 = factor41 * (1.0F - factor44) + factor41 * 0.7F * factor44;
                  factor42 = factor42 * (1.0F - factor44) + factor42 * 0.6F * factor44;
                  factor43 = factor43 * (1.0F - factor44) + factor43 * 0.6F * factor44;
               }

               if (world2.secondaryFloatArrayEntry.getCountAsIndex() == 1) {
                  factor41 = 0.22F + factor30 * 0.75F;
                  factor42 = 0.28F + factor33 * 0.75F;
                  factor43 = 0.25F + factor34 * 0.75F;
               }

               EventChunkLoad event2 = EventChunkLoadCallback.resolveEventChunkLoad(false, MovementAngleProperty.NIGHT_VISION);
               if (this.mc.thePlayer.checkPotion(Potion.potion31) || event2.isEnabled()) {
                  float factor45 = event2.isEnabled() ? 1.0F : this.computeFactor(this.mc.thePlayer, factor26);
                  float factor46 = 1.0F / factor41;
                  if (factor46 > 1.0F / factor42) {
                     factor46 = 1.0F / factor42;
                  }

                  if (factor46 > 1.0F / factor43) {
                     factor46 = 1.0F / factor43;
                  }

                  factor41 = factor41 * (1.0F - factor45) + factor41 * factor46 * factor45;
                  factor42 = factor42 * (1.0F - factor45) + factor42 * factor46 * factor45;
                  factor43 = factor43 * (1.0F - factor45) + factor43 * factor46 * factor45;
               }

               if (factor41 > 1.0F) {
                  factor41 = 1.0F;
               }

               if (factor42 > 1.0F) {
                  factor42 = 1.0F;
               }

               if (factor43 > 1.0F) {
                  factor43 = 1.0F;
               }

               float factor47 = XRay.activeState3 ? 16.0F : this.mc.gameSettings.scaleFactor100;
               float factor48 = 1.0F - factor41;
               float factor49 = 1.0F - factor42;
               float factor50 = 1.0F - factor43;
               float factor51 = 1.0F - factor48 * factor48 * factor48 * factor48;
               float factor52 = 1.0F - factor49 * factor49 * factor49 * factor49;
               float factor53 = 1.0F - factor50 * factor50 * factor50 * factor50;
               float factor54 = factor41 * (1.0F - factor47) + factor51 * factor47;
               float factor55 = factor42 * (1.0F - factor47) + factor52 * factor47;
               float factor56 = factor43 * (1.0F - factor47) + factor53 * factor47;
               float factor57 = factor54 * 0.96F + 0.03F;
               float factor58 = factor55 * 0.96F + 0.03F;
               float factor59 = factor56 * 0.96F + 0.03F;
               if (factor57 > 1.0F) {
                  factor57 = 1.0F;
               }

               if (factor58 > 1.0F) {
                  factor58 = 1.0F;
               }

               if (factor59 > 1.0F) {
                  factor59 = 1.0F;
               }

               if (factor57 < 0.0F) {
                  factor57 = 0.0F;
               }

               if (factor58 < 0.0F) {
                  factor58 = 0.0F;
               }

               if (factor59 < 0.0F) {
                  factor59 = 0.0F;
               }

               int count11 = (int)(factor57 * 255.0F);
               int count12 = (int)(factor58 * 255.0F);
               int count13 = (int)(factor59 * 255.0F);
               this.countArray[index] = 0xFF000000 | count11 << 16 | count12 << 8 | count13;
            }

            this.dynamicTexture.updateState2();
            this.enabled12 = false;
            this.mc.uNKNOWNManager.updateState();
         }
      }
   }

   private void updateState10() {
      SuppressedManager.updateState();
      if (!this.enabled2) {
         InternalPngManager.updateState();
         if (BuildManager.getenv() == 64 && BuildManager.getCount10() == 32) {
            BuildManager.setEnabled9(true);
         }

         this.enabled2 = true;
      }

      BuildManager.updateState2();
      WorldClient world2 = this.mc.theWorld;
      if (world2 != null) {
         if (BuildManager.getLabelText11() != null) {
            String text = "HD_U".replace("HD_U", "HD Ultra").replace("L", "Light");
            String text2 = text + " " + BuildManager.getLabelText11();
            ChatComponentText chatComponentText = new ChatComponentText(StatCollector.getText("of.message.newVersion", new Object[]{"§n" + text2 + "§r"}));
            chatComponentText.getIChat(
               new StyleHasParentManager()
                  .getStyleHasParentManager(new ClickEventActionManager(ClientSettingsOpenUrlType.OPEN_URL, "https://optifine.net/downloads"))
            );
            this.mc.ingameGUI.getInternalLoggerEntry().applyIChatComponent(chatComponentText);
            BuildManager.setLabelText11((String)null);
         }

         if (BuildManager.isEnabled9()) {
            BuildManager.setEnabled9(false);
            ChatComponentText chatComponentText2 = new ChatComponentText(StatCollector.getText("of.message.java64Bit", new Object[0]));
            this.mc.ingameGUI.getInternalLoggerEntry().applyIChatComponent(chatComponentText2);
         }
      }

      if (this.mc.currentScreen instanceof GuiMainMenu) {
         this.applyGuiMainMenuByGuiMainMenu((GuiMainMenu)this.mc.currentScreen);
      }

      if (this.world != world2) {
         OptifineRandomManager.applyWorldByWorld(this.world, world2);
         BuildManager.updateState9();
         this.timestamp3 = 0L;
         this.count6 = 0;
         this.world = world2;
      }

      if (!this.checkIndex(PreEndHandManager.count5)) {
         PreEndHandManager.count5 = 0;
      }

      if (this.mc.currentScreen != null && this.mc.currentScreen.getClass() == GuiChat.class) {
         Minecraft mc2 = this.mc;
         CompositeStringEntry compositeStringEntry = new CompositeStringEntry((GuiChat)this.mc.currentScreen);
         mc2.displayGuiScreen(compositeStringEntry);
      }
   }

   private void applyIndex(int count11, float factor26, long value4) {
      boolean flag = BuildManager.canExecute() && Display.isVisible();
      if (flag) {
         PreEndHandManager.applyIndex(count11, factor26, value4);
      }

      RenderGlobal renderGlobal = this.mc.renderGlobal;
      EffectRenderer effectRenderer = this.mc.effectRenderer;
      boolean activeAsFlag = this.isActiveAsFlag();
      GlStateManager.enableLighting();
      this.mc.uNKNOWNManager.applyText("clear");
      if (flag) {
         PreEndHandManager.applyIndex(0, 0, this.mc.displayWidth, this.mc.displayHeight);
      } else {
         GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
      }

      this.applyFloat3(factor26);
      GlStateManager.glClear(16640);
      if (flag) {
         PreEndHandManager.updateState23();
      }

      this.mc.uNKNOWNManager.applyText("camera");
      this.applyFloatByFloat(factor26, count11);
      if (flag) {
         PreEndHandManager.applyFloatByFloat(factor26);
      }

      AuxiliaryIntBufferEntry.applyEntityPlayer(this.mc.thePlayer, this.mc.gameSettings.indexOffset64 == 2);
      this.mc.uNKNOWNManager.applyText("frustum");
      CustomFloatArrayEntry customFloatArrayEntry = DiamondEssence.getCustomFloatArrayEntry();
      this.mc.uNKNOWNManager.applyText("culling");
      customFloatArrayEntry.enabled = BuildManager.canExecute() && !PreEndHandManager.isActive3();
      EntityEspRenderer entityEspRenderer = new EntityEspRenderer(customFloatArrayEntry);
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      double offset = renderViewEntity.lastTickPosX + (renderViewEntity.posX - renderViewEntity.lastTickPosX) * (double)factor26;
      double offset2 = renderViewEntity.lastTickPosY + (renderViewEntity.posY - renderViewEntity.lastTickPosY) * (double)factor26;
      double offset3 = renderViewEntity.lastTickPosZ + (renderViewEntity.posZ - renderViewEntity.lastTickPosZ) * (double)factor26;
      if (flag) {
         RotationBridge.applyPreMotionConsumer(entityEspRenderer, offset, offset2, offset3);
      } else {
         entityEspRenderer.processDouble(offset, offset2, offset3);
      }

      if ((BuildManager.isActive32() || BuildManager.isActive59() || BuildManager.isActive43()) && !PreEndHandManager.enabled12) {
         this.applyIndex(-1, factor26);
         this.mc.uNKNOWNManager.applyText("sky");
         GlStateManager.matrixMode(5889);
         GlStateManager.loadIdentity();
         EventTimerSpeed event = EventTimerSpeedCallback.resolveEventTimerSpeed((float)this.mc.displayWidth / (float)this.mc.displayHeight, false);
         Project.gluPerspective(this.computeFactor(factor26, true), event.getScaleFactor(), 0.05F, this.factor4);
         GlStateManager.matrixMode(5888);
         if (flag) {
            PreEndHandManager.updateState64();
         }

         renderGlobal.applyFloat2(factor26, count11);
         if (flag) {
            PreEndHandManager.updateState65();
         }

         GlStateManager.matrixMode(5889);
         GlStateManager.loadIdentity();
         Project.gluPerspective(this.computeFactor(factor26, true), event.getScaleFactor(), 0.05F, this.factor4);
         GlStateManager.matrixMode(5888);
      } else {
         GlStateManager.enableBlend();
      }

      this.applyIndex(0, factor26);
      GlStateManager.glShadeModel(7425);
      if (renderViewEntity.posY + (double)renderViewEntity.getHeight2() < 128.0 + (double)(this.mc.gameSettings.scaleFactor60 * 128.0F)) {
         this.applyRenderGlobal(renderGlobal, factor26, count11);
      }

      this.mc.uNKNOWNManager.applyText("prepareterrain");
      this.applyIndex(0, factor26);
      this.mc.getTextureManager().applyResourceLocation3(ListenerAdapter27.resourceLocation);
      RenderHelper.disableStandardItemLighting();
      this.mc.uNKNOWNManager.applyText("terrain_setup");
      this.applyEntityByEntity(renderViewEntity, factor26, entityEspRenderer, this.mc.thePlayer.isActive23());
      FreeCam freeCam = Client.getClient().getModuleManager().freeCam;
      if (flag) {
         RotationBridge.applyRenderGlobal(
            renderGlobal,
            renderViewEntity,
            (double)factor26,
            entityEspRenderer,
            this.count5++,
            this.mc.thePlayer.isActive23() || ((Module)freeCam).isToggled() && freeCam.settingSpectatorRender.isEnabled()
         );
      } else {
         renderGlobal.applyEntityByEntity(
            renderViewEntity,
            (double)factor26,
            entityEspRenderer,
            this.count5++,
            this.mc.thePlayer.isActive23() || ((Module)freeCam).isToggled() && freeCam.settingSpectatorRender.isEnabled()
         );
      }

      if (count11 == 0 || count11 == 2) {
         this.mc.uNKNOWNManager.applyText("updatechunks");
         MBSManager.nativeLongEntry6.nanoTime();
         this.mc.renderGlobal.applyTime(value4);
         MBSManager.nativeLongEntry6.updateState();
      }

      this.mc.uNKNOWNManager.applyText("terrain");
      MBSManager.nativeLongEntry5.nanoTime();
      if (this.mc.gameSettings.state34 && count11 > 0) {
         this.mc.uNKNOWNManager.applyText("finish");
         GL11.glFinish();
         this.mc.uNKNOWNManager.applyText("terrain");
      }

      GlStateManager.matrixMode(5888);
      GlStateManager.pushMatrix();
      GlStateManager.applyState10();
      if (flag) {
         RotationBridge.updateState16();
      }

      renderGlobal.computeCount(BlockSolidType.SOLID, (double)factor26, count11, renderViewEntity);
      GlStateManager.applyState14();
      if (flag) {
         RotationBridge.updateState13();
      }

      this.mc.getTextureManager().get(ListenerAdapter27.resourceLocation).applyFlag(false, this.mc.gameSettings.indexOffset118 > 0);
      renderGlobal.computeCount(BlockSolidType.CUTOUT_MIPPED, (double)factor26, count11, renderViewEntity);
      this.mc.getTextureManager().get(ListenerAdapter27.resourceLocation).updateState();
      this.mc.getTextureManager().get(ListenerAdapter27.resourceLocation).applyFlag(false, false);
      if (flag) {
         RotationBridge.updateState10();
      }

      renderGlobal.computeCount(BlockSolidType.CUTOUT, (double)factor26, count11, renderViewEntity);
      this.mc.getTextureManager().get(ListenerAdapter27.resourceLocation).updateState();
      if (flag) {
         RotationBridge.updateState8();
      }

      MBSManager.nativeLongEntry5.updateState();
      GlStateManager.glShadeModel(7424);
      GlStateManager.alphaFunc(516, 0.1F);
      if (!this.enabled9) {
         GlStateManager.matrixMode(5888);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         RenderHelper.enableStandardItemLighting();
         this.mc.uNKNOWNManager.applyText("entities");
         renderGlobal.applyEntity(renderViewEntity, entityEspRenderer, factor26);
         RenderHelper.disableStandardItemLighting();
         this.updateState5();
         GlStateManager.matrixMode(5888);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         if (this.mc.objectMouseOver != null && renderViewEntity.matchesMaterial(Material.coreItemEntry1) && activeAsFlag) {
            EntityPlayer player = (EntityPlayer)renderViewEntity;
            GlStateManager.applyState10();
            this.mc.uNKNOWNManager.applyText("outline");
            if (!this.mc.gameSettings.state11) {
               renderGlobal.applyEntityPlayerByEntityPlayer(player, this.mc.objectMouseOver, 0, factor26);
            }

            GlStateManager.applyState14();
         }
      }

      GlStateManager.matrixMode(5888);
      GlStateManager.popMatrix();
      if (activeAsFlag && this.mc.objectMouseOver != null && !renderViewEntity.matchesMaterial(Material.coreItemEntry1)) {
         EntityPlayer player2 = (EntityPlayer)renderViewEntity;
         GlStateManager.applyState10();
         this.mc.uNKNOWNManager.applyText("outline");
         if (!this.mc.gameSettings.state11 && !EventCancelListener.getEventCancelAction().isCancelled()) {
            renderGlobal.applyEntityPlayerByEntityPlayer(player2, this.mc.objectMouseOver, 0, factor26);
         }

         GlStateManager.applyState14();
      }

      if (!renderGlobal.map.isEmpty()) {
         this.mc.uNKNOWNManager.applyText("destroyProgress");
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
         this.mc.getTextureManager().get(ListenerAdapter27.resourceLocation).applyFlag(false, false);
         renderGlobal.applyTessellatorByTessellator(Tessellator.getTessellator(), Tessellator.getTessellator().getWorldRenderer(), renderViewEntity, factor26);
         this.mc.getTextureManager().get(ListenerAdapter27.resourceLocation).updateState();
         GlStateManager.enableBlend();
      }

      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.enableBlend();
      if (!this.enabled9) {
         this.updateState7();
         this.mc.uNKNOWNManager.applyText("litParticles");
         if (flag) {
            PreEndHandManager.updateState55();
         }

         effectRenderer.applyEntity3(renderViewEntity, factor26);
         RenderHelper.disableStandardItemLighting();
         this.applyIndex(0, factor26);
         this.mc.uNKNOWNManager.applyText("particles");
         if (flag) {
            PreEndHandManager.updateState74();
         }

         effectRenderer.applyEntityByEntity(renderViewEntity, factor26);
         if (flag) {
            PreEndHandManager.updateState18();
         }

         this.updateState5();
      }

      GlStateManager.depthMask(false);
      if (BuildManager.canExecute()) {
         GlStateManager.depthMask(PreEndHandManager.isActive10());
      }

      GlStateManager.enableLighting();
      this.mc.uNKNOWNManager.applyText("weather");
      if (flag) {
         PreEndHandManager.updateState56();
      }

      this.applyFloatByFloat(factor26);
      if (flag) {
         PreEndHandManager.updateState57();
      }

      GlStateManager.depthMask(true);
      renderGlobal.applyEntityByEntity(renderViewEntity, factor26);
      if (flag) {
         RotationBridge.applyEntityRenderer(this, factor26, count11);
         PreEndHandManager.updateState10();
      }

      GlStateManager.enableBlend();
      GlStateManager.enableLighting();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.alphaFunc(516, 0.1F);
      this.applyIndex(0, factor26);
      GlStateManager.disableBlend();
      GlStateManager.depthMask(false);
      this.mc.getTextureManager().applyResourceLocation3(ListenerAdapter27.resourceLocation);
      GlStateManager.glShadeModel(7425);
      this.mc.uNKNOWNManager.applyText("translucent");
      if (flag) {
         PreEndHandManager.updateState16();
      }

      renderGlobal.computeCount(BlockSolidType.TRANSLUCENT, (double)factor26, count11, renderViewEntity);
      if (flag) {
         PreEndHandManager.updateState35();
      }

      GlStateManager.applyState20();
      GlStateManager.depthMask(true);
      GlStateManager.enableBlend();
      GlStateManager.enableLighting();
      GlStateManager.glShadeModel(7424);
      if (renderViewEntity.posY + (double)renderViewEntity.getHeight2() >= 128.0 + (double)(this.mc.gameSettings.scaleFactor60 * 128.0F)) {
         this.mc.uNKNOWNManager.applyText("aboveClouds");
         this.applyRenderGlobal(renderGlobal, factor26, count11);
      }

      if (flag && state) {
         PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager6);
      }

      GlStateManager.disableBlend();
      AuxiliaryOptionType auxiliaryOptionType = AuxiliaryOptionType.RENDER3D;
      RenderListener.applyScaledResolution(factor26, (ScaledResolution)null, auxiliaryOptionType);
      GlStateManager.enableBlend();
      if (flag && state) {
         PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager7);
      }

      this.mc.uNKNOWNManager.applyText("hand");
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
         Iterator iterator = SettingsTxtManager.getExtraListener().getList().iterator();

         while (iterator.hasNext()) {
            ((ActiveListener)iterator.next()).getCompositeListener().forEach(new DepthFirstTracker(factor26));
         }
      }

      if (this.enabled11 && !PreEndHandManager.enabled12) {
         if (flag) {
            RotationBridge.applyEntityRendererByEntityRenderer(this, factor26, count11);
            PreEndHandManager.updateState32();
         }

         GlStateManager.glClear(256);
         if (flag) {
            RotationBridge.applyEntityRenderer2(this, factor26, count11);
         } else {
            this.applyFloat(factor26, count11);
         }

         this.applyFloat5(factor26);
      }

      if (this.enabled11 && !PreEndHandManager.enabled12) {
         GlStateManager.glClear(256);
      }

      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState76();
         MovementHandler.state = false;
      }
   }

   private void updateState6() {
      if (this.mc.theWorld != null && BuildManager.isActive20() && SupplementalMapEntry.matchesText("CheckGlErrorFrameFinish", 10000L)) {
         int error = GlStateManager.getError();
         if (error != 0 && SuppressedManager.checkIndex(error)) {
            String text = BuildManager.resolveText(error);
            ChatComponentText chatComponentText = new ChatComponentText(StatCollector.getText("of.message.openglError", new Object[]{error, text}));
            this.mc.ingameGUI.getInternalLoggerEntry().applyIChatComponent(chatComponentText);
         }
      }
   }

   public void applyFloatByFloat(float factor26, int count11) {
      this.factor21 = ((Module)ClientGlobals.moduleManager.ambiance).isToggled() && ClientGlobals.moduleManager.ambiance.settingCustomFogDistance.isEnabled()
         ? (float)ClientGlobals.moduleManager.ambiance.settingFogDistance.getValue()
         : (float)(this.mc.gameSettings.indexOffset16 * 16);
      if (BuildManager.isActive22()) {
         this.factor21 *= 0.95F;
      }

      if (BuildManager.isActive31()) {
         this.factor21 *= 0.83F;
      }

      GlStateManager.matrixMode(5889);
      GlStateManager.loadIdentity();
      float factor27 = 0.07F;
      if (this.mc.gameSettings.state105) {
         GlStateManager.translate((float)(-(count11 * 2 - 1)) * factor27, 0.0F, 0.0F);
      }

      this.factor4 = this.factor21 * 2.0F;
      if (this.factor4 < 173.0F) {
         this.factor4 = 173.0F;
      }

      if (this.value3 != 1.0) {
         GlStateManager.translate((float)this.value2, (float)(-this.value), 0.0F);
         GlStateManager.translate3d(this.value3, this.value3, 1.0);
      }

      EventTimerSpeed event = EventTimerSpeedCallback.resolveEventTimerSpeed((float)this.mc.displayWidth / (float)this.mc.displayHeight, false);
      Project.gluPerspective(this.computeFactor(factor26, true), event.getScaleFactor(), 0.05F, this.factor4);
      GlStateManager.matrixMode(5888);
      GlStateManager.loadIdentity();
      if (this.mc.gameSettings.state105) {
         GlStateManager.translate((float)(count11 * 2 - 1) * 0.1F, 0.0F, 0.0F);
      }

      this.applyFloat6(factor26);
      if (this.mc.gameSettings.state28
         && (!((Module)ClientGlobals.moduleManager.noBob).isToggled() || !ClientGlobals.moduleManager.noBob.settingMode.getSelected().equals("NoScreenBobbing"))
         )
       {
         this.applyFloat2(factor26);
      }

      float factor28 = this.mc.thePlayer.scaleFactor16 + (this.mc.thePlayer.scaleFactor7 - this.mc.thePlayer.scaleFactor16) * factor26;
      if (factor28 > 0.0F) {
         byte byteValue = 20;
         if (this.mc.thePlayer.checkPotion(Potion.potion15)) {
            byteValue = 7;
         }

         float factor29 = 5.0F / (factor28 * factor28 + 5.0F) - factor28 * 0.04F;
         float factor30 = factor29 * factor29;
         GlStateManager.rotate(((float)this.count2 + factor26) * (float)byteValue, 0.0F, 1.0F, 1.0F);
         GlStateManager.translate3f3(1.0F / factor30, 1.0F, 1.0F);
         GlStateManager.rotate(-((float)this.count2 + factor26) * (float)byteValue, 0.0F, 1.0F, 1.0F);
      }

      this.applyFloat(factor26);
      if (this.enabled9) {
         switch (this.count7) {
            case 0:
               GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
               break;
            case 1:
               GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
               break;
            case 2:
               GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
               break;
            case 3:
               GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
               break;
            case 4:
               GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
         }
      }
   }

   private void updateState4() {
      this.count4 = 0;
      if (!BuildManager.isActive52() || !BuildManager.isActive29()) {
         this.timestamp3 = 0L;
         this.count6 = 0;
      } else if (this.mc.isGamePaused()) {
         IntegratedServer integratedServer = this.mc.getIntegratedServer();
         if (integratedServer != null) {
            if (!this.mc.isState() && !(this.mc.currentScreen instanceof BaseGuiScreenAdapter)) {
               if (this.count3 > 0) {
                  MBSManager.nativeLongEntry4.nanoTime();
                  BuildManager.applyTime((long)this.count3);
                  MBSManager.nativeLongEntry4.updateState();
                  this.count4 = this.count3;
               }

               long value4 = System.nanoTime() / 1000000L;
               if (this.timestamp3 != 0L && this.count6 != 0) {
                  long value5 = value4 - this.timestamp3;
                  if (value5 < 0L) {
                     this.timestamp3 = value4;
                     value5 = 0L;
                  }

                  if (value5 >= 50L) {
                     this.timestamp3 = value4;
                     int countAsIndex = integratedServer.getCountAsIndex();
                     int count11 = countAsIndex - this.count6;
                     if (count11 < 0) {
                        this.count6 = countAsIndex;
                        count11 = 0;
                     }

                     if (count11 < 1 && this.count3 < 100) {
                        this.count3 += 2;
                     }

                     if (count11 > 1 && this.count3 > 0) {
                        this.count3--;
                     }

                     this.count6 = countAsIndex;
                  }
               } else {
                  this.timestamp3 = value4;
                  this.count6 = integratedServer.getCountAsIndex();
                  this.factor24 = 1.0F;
                  this.factor8 = 50.0F;
               }
            } else {
               if (this.mc.currentScreen instanceof BaseGuiScreenAdapter) {
                  BuildManager.applyTime(20L);
               }

               this.timestamp3 = 0L;
               this.count6 = 0;
            }
         }
      }
   }

   public void updateState5() {
      GlStateManager.enableState5(OpenGlHelper.defaultTexUnit);
      GlStateManager.enableTexture2D();
      GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit);
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState8();
      }
   }

   public EntityRenderer(Minecraft mc2, IResourceManager iResourceManager2) {
      this.count8 = count;
      this.enabled6 = false;
      this.count5 = 0;
      this.mc = mc2;
      this.iResourceManager = iResourceManager2;
      this.itemRenderer = mc2.getItemRenderer();
      this.texturesMapIconsManager = new TexturesMapIconsManager(mc2.getTextureManager());
      this.dynamicTexture = new DynamicTexture(16, 16);
      this.resourceLocation3 = mc2.getTextureManager().resolveResourceLocationByText("lightMap", this.dynamicTexture);
      this.countArray = this.dynamicTexture.getCountArray();
      this.passManager = null;

      for (int index = 0; index < 32; index++) {
         for (int index2 = 0; index2 < 32; index2++) {
            float factor26 = (float)(index2 - 16);
            float factor27 = (float)(index - 16);
            float factor28 = MathHelper.sin(factor26 * factor26 + factor27 * factor27);
            this.factorArray[index << 5 | index2] = -factor27 / factor28;
            this.factorArray2[index << 5 | index2] = factor26 / factor28;
         }
      }
   }
}
