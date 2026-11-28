package net.minecraft.client.renderer;

import java.util.concurrent.Callable;
import net.minecraft.world.WorldSettings;
import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.event.events.RenderEvent;
import com.google.common.base.Predicates;
import com.google.gson.JsonSyntaxException;
import de.florianmichael.dietrichevents2.StateTypes;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import net.augustus.modules.Module;
import net.augustus.modules.legit.Range;
import net.augustus.modules.movement.FreeCam;
import net.augustus.modules.render.CustomZoom;
import net.augustus.modules.render.FovModifier;
import net.augustus.modules.render.XRay;
import net.augustus.utils.EntityCollidablePredicate;
import net.augustus.events.listeners.RenderListener;
import net.augustus.events.EventBlockOverlay;
import net.augustus.events.EventOrientCamera;
import net.augustus.events.EventPerspective;
import net.augustus.events.listeners.MouseMoveListener;
import net.augustus.events.EventWorldColor;
import net.augustus.ClientManagers;
import net.augustus.events.EventZoom;
import net.augustus.events.listeners.WorldColorListener;
import net.augustus.utils.input.MouseInputUtil;
import net.augustus.utils.MoveUtil;
import net.augustus.events.listeners.ZoomListener;
import net.augustus.events.listeners.PerspectiveListener;
import net.optifine.gui.GuiChatOF;
import net.augustus.events.listeners.BlockOverlayListener;
import net.augustus.events.listeners.NightVisionListener;
import net.augustus.events.WorldColorType;
import net.augustus.events.EventMouseMove;
import net.optifine.GlErrors;
import net.augustus.events.EventNightVision;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.events.listeners.OrientCameraListener;
import net.augustus.Augustus;
import net.augustus.events.RenderPassType;
import net.augustus.events.NightVisionPhase;
import net.augustus.utils.animation.AnimationUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.MapItemRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MouseFilter;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.CustomColors;
import net.optifine.Lagometer;
import net.optifine.RandomEntities;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;
import net.optifine.util.MemoryMonitor;
import net.optifine.util.TextureUtils;
import net.optifine.util.TimedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

public class EntityRenderer implements IResourceManagerReloadListener {
   private float prevRenderYaw;
   private float prevRenderPitch;
   private float renderYaw;
   private float renderPitch;
   public static boolean shaderProgramSwitchEnabled = true;
   public static boolean shaderNameTagFix = true;
   private static final Logger logger = LogManager.getLogger();
   private static final ResourceLocation locationRainPng = new ResourceLocation("textures/environment/rain.png");
   private static final ResourceLocation locationSnowPng = new ResourceLocation("textures/environment/snow.png");
   public static boolean anaglyphEnable;
   public static int anaglyphField;
   private Minecraft mc;
   private final IResourceManager resourceManager;
   private Random random = new Random();
   private float farPlaneDistance;
   public ItemRenderer itemRenderer;
   private final MapItemRenderer theMapItemRenderer;
   private int rendererUpdateCount;
   public Entity pointedEntity;
   private MouseFilter mouseFilterXAxis = new MouseFilter();
   private MouseFilter mouseFilterYAxis = new MouseFilter();
   public float thirdPersonDistance = 4.0F;
   private float thirdPersonDistanceTemp = 4.0F;
   private float smoothCamYaw;
   private float smoothCamPitch;
   private float smoothCamFilterX;
   private float smoothCamFilterY;
   private float smoothCamPartialTicks;
   private float fovModifierHand;
   private float fovModifierHandPrev;
   private float bossColorModifier;
   private float bossColorModifierPrev;
   private boolean cloudFog;
   private boolean renderHand = true;
   private boolean drawBlockOutline = true;
   private long prevFrameTime = Minecraft.getSystemTime();
   private long renderEndNanoTime;
   private final DynamicTexture lightmapTexture;
   private final int[] lightmapColors;
   private final ResourceLocation locationLightMap;
   private boolean lightmapUpdateNeeded;
   private float torchFlickerX;
   private float torchFlickerDX;
   private int rainSoundCounter;
   private float[] rainXCoords = new float[1024];
   private float[] rainYCoords = new float[1024];
   private FloatBuffer fogColorBuffer = GLAllocation.createDirectFloatBuffer(16);
   public float fogColorRed;
   public float fogColorGreen;
   public float fogColorBlue;
   private float fogColor2;
   private float fogColor1;
   private int debugViewDirection = 0;
   private boolean debugView = false;
   private double cameraZoom = 1.0;
   private double cameraYaw;
   private double cameraPitch;
   private ShaderGroup theShaderGroup;
   private static final ResourceLocation[] shaderResourceLocations = new ResourceLocation[]{
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
   public static final int shaderCount = shaderResourceLocations.length;
   private int shaderIndex;
   private boolean useShader;
   public int frameCount;
   private boolean initialized = false;
   private World updatedWorld = null;
   private boolean showDebugInfo = false;
   public boolean fogStandard = false;
   private float clipDistance = 128.0F;
   private long lastServerTime = 0L;
   private int lastServerTicks = 0;
   private int serverWaitTime = 0;
   private int serverWaitTimeCurrent = 0;
   private float avgServerTimeDiff = 0.0F;
   private float avgServerTickDiff = 0.0F;
   private ShaderGroup[] fxaaShaders = new ShaderGroup[10];
   private boolean loadVisibleChunks = false;
   private boolean wasZooming;

   public EntityRenderer(Minecraft mc2, IResourceManager resourceManager2) {
      this.shaderIndex = shaderCount;
      this.useShader = false;
      this.frameCount = 0;
      this.mc = mc2;
      this.resourceManager = resourceManager2;
      this.itemRenderer = mc2.getItemRenderer();
      this.theMapItemRenderer = new MapItemRenderer(mc2.getTextureManager());
      this.lightmapTexture = new DynamicTexture(16, 16);
      this.locationLightMap = mc2.getTextureManager().getDynamicTextureLocation("lightMap", this.lightmapTexture);
      this.lightmapColors = this.lightmapTexture.getTextureData();
      this.theShaderGroup = null;

      for (int i = 0; i < 32; i++) {
         for (int j = 0; j < 32; j++) {
            float f = (float)(j - 16);
            float f2 = (float)(i - 16);
            float f3 = MathHelper.sqrt_float(f * f + f2 * f2);
            this.rainXCoords[i << 5 | j] = -f2 / f3;
            this.rainYCoords[i << 5 | j] = f / f3;
         }
      }
   }

   private void updateFovModifierHand() {
      float f = 1.0F;
      if (this.mc.getRenderViewEntity() instanceof AbstractClientPlayer) {
         f = ((AbstractClientPlayer)this.mc.getRenderViewEntity()).getFovModifier();
      }

      this.fovModifierHandPrev = this.fovModifierHand;
      this.fovModifierHand = this.fovModifierHand + (f - this.fovModifierHand) * 0.5F;
      if (this.fovModifierHand > 1.5F) {
         this.fovModifierHand = 1.5F;
      }

      if (this.fovModifierHand < 0.1F) {
         this.fovModifierHand = 0.1F;
      }
   }

   public void setupCameraTransform(float f, int n) {
      this.farPlaneDistance = ClientManagers.moduleManager.ambiance.isToggled() && ClientManagers.moduleManager.ambiance.customFogDistance.getValue()
         ? (float)ClientManagers.moduleManager.ambiance.fogDistance.getValue()
         : (float)(this.mc.gameSettings.renderDistanceChunks * 16);
      if (Config.isFogFancy()) {
         this.farPlaneDistance *= 0.95F;
      }

      if (Config.isFogFast()) {
         this.farPlaneDistance *= 0.83F;
      }

      GlStateManager.matrixMode(5889);
      GlStateManager.loadIdentity();
      float f2 = 0.07F;
      if (this.mc.gameSettings.anaglyph) {
         GlStateManager.translate((float)(-(n * 2 - 1)) * f2, 0.0F, 0.0F);
      }

      this.clipDistance = this.farPlaneDistance * 2.0F;
      if (this.clipDistance < 173.0F) {
         this.clipDistance = 173.0F;
      }

      if (this.cameraZoom != 1.0) {
         GlStateManager.translate((float)this.cameraYaw, (float)(-this.cameraPitch), 0.0F);
         GlStateManager.scale(this.cameraZoom, this.cameraZoom, 1.0);
      }

      EventPerspective event = PerspectiveListener.onPerspective((float)this.mc.displayWidth / (float)this.mc.displayHeight, false);
      Project.gluPerspective(this.getFOVModifier(f, true), event.getAspectRatio(), 0.05F, this.clipDistance);
      GlStateManager.matrixMode(5888);
      GlStateManager.loadIdentity();
      if (this.mc.gameSettings.anaglyph) {
         GlStateManager.translate((float)(n * 2 - 1) * 0.1F, 0.0F, 0.0F);
      }

      this.hurtCameraEffect(f);
      if (this.mc.gameSettings.viewBobbing && (!ClientManagers.moduleManager.noBob.isToggled() || !ClientManagers.moduleManager.noBob.mode.getSelectedValue().equals("NoScreenBobbing"))) {
         this.setupViewBobbing(f);
      }

      float f3 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * f;
      if (f3 > 0.0F) {
         byte b = 20;
         if (this.mc.thePlayer.isPotionActive(Potion.confusion)) {
            b = 7;
         }

         float f4 = 5.0F / (f3 * f3 + 5.0F) - f3 * 0.04F;
         f4 *= f4;
         GlStateManager.rotate(((float)this.rendererUpdateCount + f) * (float)b, 0.0F, 1.0F, 1.0F);
         GlStateManager.scale(1.0F / f4, 1.0F, 1.0F);
         GlStateManager.rotate(-((float)this.rendererUpdateCount + f) * (float)b, 0.0F, 1.0F, 1.0F);
      }

      this.orientCamera(f);
      if (this.debugView) {
         switch (this.debugViewDirection) {
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

   private void renderWorldPass(int n, float f, long l, long l2) {
      boolean flag = Config.isShaderPackLoaded() && Display.isVisible();
      if (flag) {
         Shaders.beginRenderPass(n, f, l);
      }

      RenderGlobal renderGlobal = this.mc.renderGlobal;
      EffectRenderer effectRenderer = this.mc.effectRenderer;
      boolean isDrawBlockOutline = this.isDrawBlockOutline();
      GlStateManager.enableCull();
      this.mc.mcProfiler.endStartSection("clear");
      if (flag) {
         Shaders.setViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
      } else {
         GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
      }

      this.updateFogColor(f);
      GlStateManager.clear(16640);
      if (flag) {
         Shaders.clearRenderBuffer();
      }

      this.mc.mcProfiler.endStartSection("camera");
      this.setupCameraTransform(f, n);
      if (flag) {
         Shaders.setCamera(f);
      }

      ActiveRenderInfo.updateRenderInfo(this.mc.thePlayer, this.mc.gameSettings.thirdPersonView == 2);
      this.mc.mcProfiler.endStartSection("frustum");
      ClippingHelper instance = ClippingHelperImpl.getInstance();
      this.mc.mcProfiler.endStartSection("culling");
      instance.disabled = Config.isShaderPackLoaded() && !Shaders.isFrustumCulling();
      Frustum frustum = new Frustum(instance);
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      double d = renderViewEntity.lastTickPosX + (renderViewEntity.posX - renderViewEntity.lastTickPosX) * (double)f;
      double d2 = renderViewEntity.lastTickPosY + (renderViewEntity.posY - renderViewEntity.lastTickPosY) * (double)f;
      double d3 = renderViewEntity.lastTickPosZ + (renderViewEntity.posZ - renderViewEntity.lastTickPosZ) * (double)f;
      if (flag) {
         ShadersRender.setFrustrumPosition(frustum, d, d2, d3);
      } else {
         frustum.setPosition(d, d2, d3);
      }

      if ((Config.isSkyEnabled() || Config.isSunMoonEnabled() || Config.isStarsEnabled()) && !Shaders.isShadowPass) {
         this.setupFog(-1, f);
         this.mc.mcProfiler.endStartSection("sky");
         GlStateManager.matrixMode(5889);
         GlStateManager.loadIdentity();
         EventPerspective event = PerspectiveListener.onPerspective((float)this.mc.displayWidth / (float)this.mc.displayHeight, false);
         Project.gluPerspective(this.getFOVModifier(f, true), event.getAspectRatio(), 0.05F, this.clipDistance);
         GlStateManager.matrixMode(5888);
         if (flag) {
            Shaders.beginSky();
         }

         renderGlobal.renderSky(f, n);
         if (flag) {
            Shaders.endSky();
         }

         GlStateManager.matrixMode(5889);
         GlStateManager.loadIdentity();
         Project.gluPerspective(this.getFOVModifier(f, true), event.getAspectRatio(), 0.05F, this.clipDistance);
         GlStateManager.matrixMode(5888);
      } else {
         GlStateManager.disableBlend();
      }

      this.setupFog(0, f);
      GlStateManager.shadeModel(7425);
      if (renderViewEntity.posY + (double)renderViewEntity.getEyeHeight() < 128.0 + (double)(this.mc.gameSettings.ofCloudsHeight * 128.0F)) {
         this.renderCloudsCheck(renderGlobal, f, n);
      }

      this.mc.mcProfiler.endStartSection("prepareterrain");
      this.setupFog(0, f);
      this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
      RenderHelper.disableStandardItemLighting();
      this.mc.mcProfiler.endStartSection("terrain_setup");
      this.checkLoadVisibleChunks(renderViewEntity, f, frustum, this.mc.thePlayer.isSpectator());
      FreeCam freeCam = Augustus.getInstance().getModuleManager().freeCam;
      if (flag) {
         ShadersRender.setupTerrain(renderGlobal, renderViewEntity, (double)f, frustum, this.frameCount++, this.mc.thePlayer.isSpectator() || freeCam.isToggled() && freeCam.spectatorRender.getValue());
      } else {
         renderGlobal.setupTerrain(renderViewEntity, (double)f, frustum, this.frameCount++, this.mc.thePlayer.isSpectator() || freeCam.isToggled() && freeCam.spectatorRender.getValue());
      }

      if (n == 0 || n == 2) {
         this.mc.mcProfiler.endStartSection("updatechunks");
         Lagometer.timerChunkUpload.start();
         this.mc.renderGlobal.updateChunks(l);
         Lagometer.timerChunkUpload.end();
      }

      this.mc.mcProfiler.endStartSection("terrain");
      Lagometer.timerTerrain.start();
      if (this.mc.gameSettings.ofSmoothFps && n > 0) {
         this.mc.mcProfiler.endStartSection("finish");
         GL11.glFinish();
         this.mc.mcProfiler.endStartSection("terrain");
      }

      GlStateManager.matrixMode(5888);
      GlStateManager.pushMatrix();
      GlStateManager.disableAlpha();
      if (flag) {
         ShadersRender.beginTerrainSolid();
      }

      renderGlobal.renderBlockLayer(EnumWorldBlockLayer.SOLID, (double)f, n, renderViewEntity);
      GlStateManager.enableAlpha();
      if (flag) {
         ShadersRender.beginTerrainCutoutMipped();
      }

      this.mc.getTextureManager().getTexture(TextureMap.locationBlocksTexture).setBlurMipmap(false, this.mc.gameSettings.mipmapLevels > 0);
      renderGlobal.renderBlockLayer(EnumWorldBlockLayer.CUTOUT_MIPPED, (double)f, n, renderViewEntity);
      this.mc.getTextureManager().getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
      this.mc.getTextureManager().getTexture(TextureMap.locationBlocksTexture).setBlurMipmap(false, false);
      if (flag) {
         ShadersRender.beginTerrainCutout();
      }

      renderGlobal.renderBlockLayer(EnumWorldBlockLayer.CUTOUT, (double)f, n, renderViewEntity);
      this.mc.getTextureManager().getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
      if (flag) {
         ShadersRender.endTerrain();
      }

      Lagometer.timerTerrain.end();
      GlStateManager.shadeModel(7424);
      GlStateManager.alphaFunc(516, 0.1F);
      if (!this.debugView) {
         GlStateManager.matrixMode(5888);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         RenderHelper.enableStandardItemLighting();
         this.mc.mcProfiler.endStartSection("entities");
         renderGlobal.renderEntities(renderViewEntity, frustum, f);
         RenderHelper.disableStandardItemLighting();
         this.disableLightmap();
         GlStateManager.matrixMode(5888);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         if (this.mc.objectMouseOver != null && renderViewEntity.isInsideOfMaterial(Material.water) && isDrawBlockOutline) {
            EntityPlayer player = (EntityPlayer)renderViewEntity;
            GlStateManager.disableAlpha();
            this.mc.mcProfiler.endStartSection("outline");
            if (!this.mc.gameSettings.hideGUI) {
               renderGlobal.drawSelectionBox(player, this.mc.objectMouseOver, 0, f);
            }

            GlStateManager.enableAlpha();
         }
      }

      GlStateManager.matrixMode(5888);
      GlStateManager.popMatrix();
      if (isDrawBlockOutline && this.mc.objectMouseOver != null && !renderViewEntity.isInsideOfMaterial(Material.water)) {
         EntityPlayer player2 = (EntityPlayer)renderViewEntity;
         GlStateManager.disableAlpha();
         this.mc.mcProfiler.endStartSection("outline");
         if (!this.mc.gameSettings.hideGUI) {
            if (!BlockOverlayListener.onBlockOverlay().isCancelled()) {
               renderGlobal.drawSelectionBox(player2, this.mc.objectMouseOver, 0, f);
            }
         }

         GlStateManager.enableAlpha();
      }

      if (!renderGlobal.damagedBlocks.isEmpty()) {
         this.mc.mcProfiler.endStartSection("destroyProgress");
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
         this.mc.getTextureManager().getTexture(TextureMap.locationBlocksTexture).setBlurMipmap(false, false);
         renderGlobal.drawBlockDamageTexture(Tessellator.getInstance(), Tessellator.getInstance().getWorldRenderer(), renderViewEntity, f);
         this.mc.getTextureManager().getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
         GlStateManager.disableBlend();
      }

      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.disableBlend();
      if (!this.debugView) {
         this.enableLightmap();
         this.mc.mcProfiler.endStartSection("litParticles");
         if (flag) {
            Shaders.beginLitParticles();
         }

         effectRenderer.renderLitParticles(renderViewEntity, f);
         RenderHelper.disableStandardItemLighting();
         this.setupFog(0, f);
         this.mc.mcProfiler.endStartSection("particles");
         if (flag) {
            Shaders.beginParticles();
         }

         effectRenderer.renderParticles(renderViewEntity, f);
         if (flag) {
            Shaders.endParticles();
         }

         this.disableLightmap();
      }

      GlStateManager.depthMask(false);
      if (Config.isShaderPackLoaded()) {
         GlStateManager.depthMask(Shaders.isRainDepth());
      }

      GlStateManager.enableCull();
      this.mc.mcProfiler.endStartSection("weather");
      if (flag) {
         Shaders.beginWeather();
      }

      this.renderRainSnow(f);
      if (flag) {
         Shaders.endWeather();
      }

      GlStateManager.depthMask(true);
      renderGlobal.renderWorldBorder(renderViewEntity, f);
      if (flag) {
         Shaders.preWater();
      }

      GlStateManager.disableBlend();
      GlStateManager.enableCull();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.alphaFunc(516, 0.1F);
      this.setupFog(0, f);
      GlStateManager.enableBlend();
      GlStateManager.depthMask(false);
      this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
      GlStateManager.shadeModel(7425);
      this.mc.mcProfiler.endStartSection("translucent");
      if (flag) {
         Shaders.beginWater();
      }

      renderGlobal.renderBlockLayer(EnumWorldBlockLayer.TRANSLUCENT, (double)f, n, renderViewEntity);
      if (flag) {
         Shaders.endWater();
      }

      if (Minecraft.getSystemTime() - 219538800L < l2) {
         GlStateManager.disableFog();
         GlStateManager.depthMask(true);
         GlStateManager.disableBlend();
         GlStateManager.enableCull();
         GlStateManager.shadeModel(7424);
      }

      if (renderViewEntity.posY + (double)renderViewEntity.getEyeHeight() >= 128.0 + (double)(this.mc.gameSettings.ofCloudsHeight * 128.0F)) {
         this.mc.mcProfiler.endStartSection("aboveClouds");
         this.renderCloudsCheck(renderGlobal, f, n);
      }

      if (flag && shaderProgramSwitchEnabled) {
         Shaders.useProgram(Shaders.ProgramNone);
      }

      GlStateManager.enableBlend();
      if (!Shaders.isShadowPass) {
         RenderPassType rendeR3D = RenderPassType.RENDER3D;
         RenderListener.postRender(f, (ScaledResolution)null, rendeR3D);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      }

      GlStateManager.disableBlend();
      if (flag && shaderProgramSwitchEnabled) {
         Shaders.useProgram(Shaders.activeProgram);
      }

      this.mc.mcProfiler.endStartSection("hand");
      if (((Module)Augustus.getInstance().getModuleManager().baritone).isToggled()) {
         for (IBaritone baritone : (Iterable<IBaritone>)(BaritoneAPI.getProvider().getAllBaritones())) {
            baritone.getGameEventHandler().onRenderPass(new RenderEvent(f));
         }
      }

      if (this.renderHand && !Shaders.isShadowPass) {
         if (flag) {
            ShadersRender.renderHand1(this, f, n);
            Shaders.renderCompositeFinal();
         }

         GlStateManager.clear(256);
         if (flag) {
            ShadersRender.renderFPOverlay(this, f, n);
         } else {
            this.renderHand(f, n);
         }

         this.renderWorldDirections(f);
      }

      if (this.renderHand && !Shaders.isShadowPass) {
         GlStateManager.clear(256);
      }

      if (Config.isShaderPackLoaded()) {
         Shaders.endRender();
      }
   }

   public ShaderGroup getShaderGroup() {
      return this.theShaderGroup;
   }

   private void updateFogColor(float f) {
      WorldClient theWorld = this.mc.theWorld;
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      float f2 = 0.25F + 0.75F * (float)this.mc.gameSettings.renderDistanceChunks / 32.0F;
      f2 = 1.0F - (float)Math.pow((double)f2, 0.25);
      Vec3 skyColor = theWorld.getSkyColor(this.mc.getRenderViewEntity(), f);
      skyColor = CustomColors.getWorldSkyColor(skyColor, theWorld, this.mc.getRenderViewEntity(), f);
      float xCoord = (float)skyColor.xCoord;
      float yCoord = (float)skyColor.yCoord;
      float zCoord = (float)skyColor.zCoord;
      Vec3 fogColor = theWorld.getFogColor(f);
      fogColor = CustomColors.getWorldFogColor(fogColor, theWorld, this.mc.getRenderViewEntity(), f);
      EventWorldColor event = WorldColorListener.onWorldColor((float)fogColor.xCoord, (float)fogColor.yCoord, (float)fogColor.zCoord, WorldColorType.FOG_COLOR);
      this.fogColorRed = event.getRed();
      this.fogColorGreen = event.getGreen();
      this.fogColorBlue = event.getBlue();
      if (this.mc.gameSettings.renderDistanceChunks >= 4) {
         double d = -1.0;
         Vec3 vec = MathHelper.sin(theWorld.getCelestialAngleRadians(f)) > 0.0F ? new Vec3(d, 0.0, 0.0) : new Vec3(1.0, 0.0, 0.0);
         float f3 = (float)renderViewEntity.getLook(f).dotProduct(vec);
         if (f3 < 0.0F) {
            f3 = 0.0F;
         }

         if (f3 > 0.0F) {
            float[] floats = theWorld.provider.calcSunriseSunsetColors(theWorld.getCelestialAngle(f), f);
            if (floats != null) {
               f3 *= floats[3];
               this.fogColorRed = this.fogColorRed * (1.0F - f3) + floats[0] * f3;
               this.fogColorGreen = this.fogColorGreen * (1.0F - f3) + floats[1] * f3;
               this.fogColorBlue = this.fogColorBlue * (1.0F - f3) + floats[2] * f3;
            }
         }
      }

      this.fogColorRed = this.fogColorRed + (xCoord - this.fogColorRed) * f2;
      this.fogColorGreen = this.fogColorGreen + (yCoord - this.fogColorGreen) * f2;
      this.fogColorBlue = this.fogColorBlue + (zCoord - this.fogColorBlue) * f2;
      float rainStrength = theWorld.getRainStrength(f);
      if (rainStrength > 0.0F) {
         float f4 = 1.0F - rainStrength * 0.5F;
         float f5 = 1.0F - rainStrength * 0.4F;
         this.fogColorRed *= f4;
         this.fogColorGreen *= f4;
         this.fogColorBlue *= f5;
      }

      float thunderStrength = theWorld.getThunderStrength(f);
      if (thunderStrength > 0.0F) {
         float f6 = 1.0F - thunderStrength * 0.5F;
         this.fogColorRed *= f6;
         this.fogColorGreen *= f6;
         this.fogColorBlue *= f6;
      }

      Block blockAtEntityViewpoint = ActiveRenderInfo.getBlockAtEntityViewpoint(this.mc.theWorld, renderViewEntity, f);
      if (this.cloudFog) {
         Vec3 cloudColour = theWorld.getCloudColour(f);
         this.fogColorRed = (float)cloudColour.xCoord;
         this.fogColorGreen = (float)cloudColour.yCoord;
         this.fogColorBlue = (float)cloudColour.zCoord;
      } else if (blockAtEntityViewpoint.getMaterial() == Material.water) {
         float f7 = (float)EnchantmentHelper.getRespiration(renderViewEntity) * 0.2F;
         f7 = Config.limit3(f7, 0.0F, 0.6F);
         if (renderViewEntity instanceof EntityLivingBase && ((EntityLivingBase)renderViewEntity).isPotionActive(Potion.waterBreathing)) {
            f7 = f7 * 0.3F + 0.6F;
         }

         this.fogColorRed = 0.02F + f7;
         this.fogColorGreen = 0.02F + f7;
         this.fogColorBlue = 0.2F + f7;
         Vec3 underwaterColor = CustomColors.getUnderwaterColor(
            this.mc.theWorld, this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().posY + 1.0, this.mc.getRenderViewEntity().posZ
         );
         if (underwaterColor != null) {
            this.fogColorRed = (float)underwaterColor.xCoord;
            this.fogColorGreen = (float)underwaterColor.yCoord;
            this.fogColorBlue = (float)underwaterColor.zCoord;
         }
      } else if (blockAtEntityViewpoint.getMaterial() == Material.lava) {
         this.fogColorRed = 0.6F;
         this.fogColorGreen = 0.1F;
         this.fogColorBlue = 0.0F;
         Vec3 underlavaColor = CustomColors.getUnderlavaColor(
            this.mc.theWorld, this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().posY + 1.0, this.mc.getRenderViewEntity().posZ
         );
         if (underlavaColor != null) {
            this.fogColorRed = (float)underlavaColor.xCoord;
            this.fogColorGreen = (float)underlavaColor.yCoord;
            this.fogColorBlue = (float)underlavaColor.zCoord;
         }
      }

      float f8 = this.fogColor2 + (this.fogColor1 - this.fogColor2) * f;
      this.fogColorRed *= f8;
      this.fogColorGreen *= f8;
      this.fogColorBlue *= f8;
      double voidFogYFactor = theWorld.provider.getVoidFogYFactor();
      double d2 = (renderViewEntity.lastTickPosY + (renderViewEntity.posY - renderViewEntity.lastTickPosY) * (double)f) * voidFogYFactor;
      if (renderViewEntity instanceof EntityLivingBase
         && ((EntityLivingBase)renderViewEntity).isPotionActive(Potion.blindness)
         && (!(renderViewEntity instanceof EntityPlayerSP) || !((Module)ClientManagers.moduleManager.antiBlind).isToggled())) {
         int duration = ((EntityLivingBase)renderViewEntity).getActivePotionEffect(Potion.blindness).getDuration();
         if (duration < 20) {
            d2 *= (double)(1.0F - (float)duration / 20.0F);
         } else {
            d2 = 0.0;
         }
      }

      if (d2 < 1.0) {
         if (d2 < 0.0) {
            d2 = 0.0;
         }

         d2 *= d2;
         this.fogColorRed = (float)((double)this.fogColorRed * d2);
         this.fogColorGreen = (float)((double)this.fogColorGreen * d2);
         this.fogColorBlue = (float)((double)this.fogColorBlue * d2);
      }

      if (this.bossColorModifier > 0.0F) {
         float f9 = this.bossColorModifierPrev + (this.bossColorModifier - this.bossColorModifierPrev) * f;
         this.fogColorRed = this.fogColorRed * (1.0F - f9) + this.fogColorRed * 0.7F * f9;
         this.fogColorGreen = this.fogColorGreen * (1.0F - f9) + this.fogColorGreen * 0.6F * f9;
         this.fogColorBlue = this.fogColorBlue * (1.0F - f9) + this.fogColorBlue * 0.6F * f9;
      }

      EventNightVision event2 = NightVisionListener.onNightVision(false, NightVisionPhase.FOG_NIGHT_VISION);
      if (renderViewEntity instanceof EntityLivingBase && (((EntityLivingBase)renderViewEntity).isPotionActive(Potion.nightVision) || event2.isEnabled())) {
         float f10 = event2.isEnabled() ? 1.0F : this.getNightVisionBrightness((EntityLivingBase)renderViewEntity, f);
         float f11 = 1.0F / this.fogColorRed;
         if (f11 > 1.0F / this.fogColorGreen) {
            f11 = 1.0F / this.fogColorGreen;
         }

         if (f11 > 1.0F / this.fogColorBlue) {
            f11 = 1.0F / this.fogColorBlue;
         }

         if (Float.isInfinite(f11)) {
            f11 = Math.nextAfter(f11, 0.0);
         }

         this.fogColorRed = this.fogColorRed * (1.0F - f10) + this.fogColorRed * f11 * f10;
         this.fogColorGreen = this.fogColorGreen * (1.0F - f10) + this.fogColorGreen * f11 * f10;
         this.fogColorBlue = this.fogColorBlue * (1.0F - f10) + this.fogColorBlue * f11 * f10;
      }

      if (this.mc.gameSettings.anaglyph) {
         float f12 = (this.fogColorRed * 30.0F + this.fogColorGreen * 59.0F + this.fogColorBlue * 11.0F) / 100.0F;
         float f13 = (this.fogColorRed * 30.0F + this.fogColorGreen * 70.0F) / 100.0F;
         float f14 = (this.fogColorRed * 30.0F + this.fogColorBlue * 70.0F) / 100.0F;
         this.fogColorRed = f12;
         this.fogColorGreen = f13;
         this.fogColorBlue = f14;
      }

      Shaders.setClearColor(this.fogColorRed, this.fogColorGreen, this.fogColorBlue, 0.0F);
   }

   private void setupFog(int n, float f) {
      this.fogStandard = false;
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      GL11.glFog(2918, this.setFogColorBuffer(this.fogColorRed, this.fogColorGreen, this.fogColorBlue, 1.0F));
      GL11.glNormal3f(0.0F, -1.0F, 0.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      Block blockAtEntityViewpoint = ActiveRenderInfo.getBlockAtEntityViewpoint(this.mc.theWorld, renderViewEntity, f);
      if (renderViewEntity instanceof EntityLivingBase && ((EntityLivingBase)renderViewEntity).isPotionActive(Potion.blindness) && !((Module)ClientManagers.moduleManager.antiBlind).isToggled()) {
         float f2 = 5.0F;
         int duration = ((EntityLivingBase)renderViewEntity).getActivePotionEffect(Potion.blindness).getDuration();
         if (duration < 20) {
            f2 = 5.0F + (this.farPlaneDistance - 5.0F) * (1.0F - (float)duration / 20.0F);
         }

         GlStateManager.setFog(9729);
         if (n == -1) {
            GlStateManager.setFogStart(0.0F);
            GlStateManager.setFogEnd(f2 * 0.8F);
         } else {
            GlStateManager.setFogStart(f2 * 0.25F);
            GlStateManager.setFogEnd(f2);
         }

         if (GLContext.getCapabilities().GL_NV_fog_distance && Config.isFogFancy()) {
            GL11.glFogi(34138, 34139);
         }
      } else if (this.cloudFog) {
         GlStateManager.setFog(2048);
         GlStateManager.setFogDensity(0.1F);
      } else if (blockAtEntityViewpoint.getMaterial() == Material.water) {
         GlStateManager.setFog(2048);
         float f3 = Config.isClearWater() ? 0.02F : 0.1F;
         if (renderViewEntity instanceof EntityLivingBase && ((EntityLivingBase)renderViewEntity).isPotionActive(Potion.waterBreathing)) {
            GlStateManager.setFogDensity(0.01F);
         } else {
            GlStateManager.setFogDensity(Config.limit3(0.1F - (float)EnchantmentHelper.getRespiration(renderViewEntity) * 0.03F, 0.0F, f3));
         }
      } else if (blockAtEntityViewpoint.getMaterial() == Material.lava) {
         GlStateManager.setFog(2048);
         GlStateManager.setFogDensity(2.0F);
      } else {
         float farPlaneDistance2 = this.farPlaneDistance;
         this.fogStandard = true;
         GlStateManager.setFog(9729);
         if (n == -1) {
            GlStateManager.setFogStart(0.0F);
            GlStateManager.setFogEnd(farPlaneDistance2);
         } else {
            GlStateManager.setFogStart(farPlaneDistance2 * Config.getFogStart());
            GlStateManager.setFogEnd(farPlaneDistance2);
         }

         if (GLContext.getCapabilities().GL_NV_fog_distance) {
            if (Config.isFogFancy()) {
               GL11.glFogi(34138, 34139);
            }

            if (Config.isFogFast()) {
               GL11.glFogi(34138, 34140);
            }
         }

         if (this.mc.theWorld.provider.doesXZShowFog((int)renderViewEntity.posX, (int)renderViewEntity.posZ)) {
            GlStateManager.setFogStart(farPlaneDistance2 * 0.05F);
            GlStateManager.setFogEnd(farPlaneDistance2);
         }
      }

      GlStateManager.enableColorMaterial();
      GlStateManager.enableFog();
      GlStateManager.colorMaterial(1028, 4608);
   }

   public float getNightVisionBrightness(EntityLivingBase entity, float f) {
      int duration = entity.getActivePotionEffect(Potion.nightVision).getDuration();
      return duration > 200 ? 1.0F : 0.7F + MathHelper.sin(((float)duration - f) * 3.1415927F * 0.2F) * 0.3F;
   }

   public static Minecraft accessMc(EntityRenderer entityRenderer) {
      return entityRenderer.mc;
   }

   public MapItemRenderer getMapItemRenderer() {
      return this.theMapItemRenderer;
   }

   private void updateLightmap(float f) {
      if (this.lightmapUpdateNeeded) {
         this.mc.mcProfiler.startSection("lightTex");
         WorldClient theWorld = this.mc.theWorld;
         if (theWorld != null) {
            if (Config.isCustomColors()
               && !ClientManagers.moduleManager.fullBright.isToggled()
               && !XRay.enabled
               && (!ClientManagers.moduleManager.ambiance.customWorldColor.getValue() || !ClientManagers.moduleManager.ambiance.isToggled())
               && !ClientManagers.moduleManager.mlg.isToggled()
               && CustomColors.updateLightmap(theWorld, this.torchFlickerX, this.lightmapColors, this.mc.thePlayer.isPotionActive(Potion.nightVision), f)) {
               this.lightmapTexture.updateDynamicTexture();
               this.lightmapUpdateNeeded = false;
               this.mc.mcProfiler.endSection();
               return;
            }

            float sunBrightness = theWorld.getSunBrightness(1.0F);
            float f2 = sunBrightness * 0.95F + 0.05F;

            for (int i = 0; i < 256; i++) {
               float f3 = theWorld.provider.getLightBrightnessTable()[i / 16] * f2;
               float f4 = theWorld.provider.getLightBrightnessTable()[i % 16] * (this.torchFlickerX * 0.1F + 1.5F);
               if (theWorld.getLastLightningBolt() > 0) {
                  f3 = theWorld.provider.getLightBrightnessTable()[i / 16];
               }

               float f5 = f3 * (sunBrightness * 0.65F + 0.35F);
               float f6 = f3 * (sunBrightness * 0.65F + 0.35F);
               float f7 = f4 * ((f4 * 0.6F + 0.4F) * 0.6F + 0.4F);
               float f8 = f4 * (f4 * f4 * 0.6F + 0.4F);
               float f9 = f5 + f4;
               float f10 = f6 + f7;
               float f11 = f3 + f8;
               f9 = f9 * 0.96F + 0.03F;
               f10 = f10 * 0.96F + 0.03F;
               f11 = f11 * 0.96F + 0.03F;
               EventWorldColor event = WorldColorListener.onWorldColor(f9, f10, f11, WorldColorType.WORLD_COLOR);
               f9 = event.getRed();
               f10 = event.getGreen();
               f11 = event.getBlue();
               if (this.bossColorModifier > 0.0F) {
                  float f12 = this.bossColorModifierPrev + (this.bossColorModifier - this.bossColorModifierPrev) * f;
                  f9 = f9 * (1.0F - f12) + f9 * 0.7F * f12;
                  f10 = f10 * (1.0F - f12) + f10 * 0.6F * f12;
                  f11 = f11 * (1.0F - f12) + f11 * 0.6F * f12;
               }

               if (theWorld.provider.getDimensionId() == 1) {
                  f9 = 0.22F + f4 * 0.75F;
                  f10 = 0.28F + f7 * 0.75F;
                  f11 = 0.25F + f8 * 0.75F;
               }

               EventNightVision event2 = NightVisionListener.onNightVision(false, NightVisionPhase.NIGHT_VISION);
               if (this.mc.thePlayer.isPotionActive(Potion.nightVision) || event2.isEnabled()) {
                  float f13 = event2.isEnabled() ? 1.0F : this.getNightVisionBrightness(this.mc.thePlayer, f);
                  float f14 = 1.0F / f9;
                  if (f14 > 1.0F / f10) {
                     f14 = 1.0F / f10;
                  }

                  if (f14 > 1.0F / f11) {
                     f14 = 1.0F / f11;
                  }

                  f9 = f9 * (1.0F - f13) + f9 * f14 * f13;
                  f10 = f10 * (1.0F - f13) + f10 * f14 * f13;
                  f11 = f11 * (1.0F - f13) + f11 * f14 * f13;
               }

               if (f9 > 1.0F) {
                  f9 = 1.0F;
               }

               if (f10 > 1.0F) {
                  f10 = 1.0F;
               }

               if (f11 > 1.0F) {
                  f11 = 1.0F;
               }

               float f15 = XRay.enabled ? 16.0F : this.mc.gameSettings.gammaSetting;
               float f16 = 1.0F - f9;
               float f17 = 1.0F - f10;
               float f18 = 1.0F - f11;
               f16 = 1.0F - f16 * f16 * f16 * f16;
               f17 = 1.0F - f17 * f17 * f17 * f17;
               f18 = 1.0F - f18 * f18 * f18 * f18;
               f9 = f9 * (1.0F - f15) + f16 * f15;
               f10 = f10 * (1.0F - f15) + f17 * f15;
               f11 = f11 * (1.0F - f15) + f18 * f15;
               f9 = f9 * 0.96F + 0.03F;
               f10 = f10 * 0.96F + 0.03F;
               f11 = f11 * 0.96F + 0.03F;
               if (f9 > 1.0F) {
                  f9 = 1.0F;
               }

               if (f10 > 1.0F) {
                  f10 = 1.0F;
               }

               if (f11 > 1.0F) {
                  f11 = 1.0F;
               }

               if (f9 < 0.0F) {
                  f9 = 0.0F;
               }

               if (f10 < 0.0F) {
                  f10 = 0.0F;
               }

               if (f11 < 0.0F) {
                  f11 = 0.0F;
               }

               int n = (int)(f9 * 255.0F);
               int n2 = (int)(f10 * 255.0F);
               int n3 = (int)(f11 * 255.0F);
               this.lightmapColors[i] = ((short)255) << 24 | n << 16 | n2 << 8 | n3;
            }

            this.lightmapTexture.updateDynamicTexture();
            this.lightmapUpdateNeeded = false;
            this.mc.mcProfiler.endSection();
         }
      }
   }

   public void getMouseOver(float f, boolean flag) {
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      if (renderViewEntity != null && this.mc.theWorld != null) {
         this.mc.mcProfiler.startSection("pick");
         this.mc.pointedEntity = (Entity)null;
         double buildRange = ClientManagers.moduleManager.range.getBuildRange((double)this.mc.playerController.getBlockReachDistance());
         if (ClientManagers.moduleManager.teleport.isToggled()) {
            buildRange = 200.0;
         }

         boolean flag2 = false;
         double d = 0.0;
         double d2 = 0.0;
         double d3 = 0.0;
         double d4 = 0.0;
         double d5 = 0.0;
         double d6 = 0.0;
         if (ClientManagers.moduleManager.freeCam.isToggled() && ClientManagers.moduleManager.freeCam.updateRaytrace.getValue()) {
            FreeCam freeCam = ClientManagers.moduleManager.freeCam;
            d = renderViewEntity.posX;
            d3 = renderViewEntity.posY;
            d5 = renderViewEntity.posZ;
            renderViewEntity.posX = freeCam.cameraPos.xCoord;
            renderViewEntity.posY = freeCam.cameraPos.yCoord;
            renderViewEntity.posZ = freeCam.cameraPos.zCoord;
            renderViewEntity.prevPosX = freeCam.prevCameraPos.xCoord;
            renderViewEntity.prevPosY = freeCam.prevCameraPos.yCoord;
            renderViewEntity.prevPosZ = freeCam.prevCameraPos.zCoord;
            flag2 = true;
         }

         this.mc.objectMouseOver = renderViewEntity.rayTrace(buildRange, f);
         double d7 = buildRange;
         Vec3 positionEyes = renderViewEntity.getPositionEyes(f);
         boolean flag3 = false;
         if (this.mc.playerController.extendedReach()) {
            buildRange = 6.0;
            d7 = 6.0;
         } else if (buildRange > 3.0) {
            flag3 = true;
         }

         if (this.mc.objectMouseOver == null || ClientManagers.moduleManager.range.isCombatRangeActive() && ClientManagers.moduleManager.range.throughWalls.getValue()) {
            if (ClientManagers.moduleManager.range.isCombatRangeActive()) {
               if (ClientManagers.moduleManager.fixes.isIntaveFixEnabled()) {
                  d7 = 6.0;
               }

               d7 = Math.max(d7, ClientManagers.moduleManager.range.getCurrentRange());
            }
         } else {
            d7 = this.mc.objectMouseOver.hitVec.distanceTo(positionEyes);
         }

         if (ClientManagers.moduleManager.fixes.isIntaveFixEnabled()) {
            buildRange = 6.0;
         }

         buildRange = Math.max(buildRange, ClientManagers.moduleManager.range.getCurrentRange());
         Vec3 look = renderViewEntity.getLook(f);
         Vec3 vec = positionEyes.addVector(look.xCoord * buildRange, look.yCoord * buildRange, look.zCoord * buildRange);
         this.pointedEntity = null;
         Vec3 vec2 = null;
         float f2 = 1.0F;
         List list = this.mc
            .theWorld
            .getEntitiesInAABBexcluding(
               renderViewEntity,
               renderViewEntity.getEntityBoundingBox()
                  .addCoord(look.xCoord * buildRange, look.yCoord * buildRange, look.zCoord * buildRange)
                  .expand((double)f2, (double)f2, (double)f2),
               Predicates.and(EntitySelectors.NOT_SPECTATING, new EntityCollidablePredicate(this))
            );
         double d8 = d7;

         for (int i = 0; i < list.size(); i++) {
            Entity entity = (Entity)list.get(i);
            float collisionBorderSize = entity.getCollisionBorderSize();
            AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();
            if (ClientManagers.moduleManager.hitBox.isToggled()) {
               entityBoundingBox = ClientManagers.moduleManager.hitBox.getExpandedBoundingBox(entity, collisionBorderSize);
            } else {
               entityBoundingBox = entityBoundingBox.expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
            }

            MovingObjectPosition hit2 = entityBoundingBox.calculateIntercept(positionEyes, vec);
            if (entityBoundingBox.isVecInside(positionEyes)) {
               if (d8 >= 0.0) {
                  this.pointedEntity = entity;
                  vec2 = hit2 == null ? positionEyes : hit2.hitVec;
                  d8 = 0.0;
               }
            } else if (hit2 != null) {
               double d9 = positionEyes.distanceTo(hit2.hitVec);
               if (d9 < d8 || d8 == 0.0) {
                  if (entity != renderViewEntity.ridingEntity || false) {
                     this.pointedEntity = entity;
                     vec2 = hit2.hitVec;
                     d8 = d9;
                  } else if (d8 == 0.0) {
                     this.pointedEntity = entity;
                     vec2 = hit2.hitVec;
                  }
               }
            }
         }

         if (this.mc.playerController != null && this.mc.thePlayer != null && ClientManagers.moduleManager.noMiningTrace.shouldIgnoreEntities() || flag) {
            this.pointedEntity = null;
         }

         if (this.pointedEntity != null
            && flag3
            && positionEyes.distanceTo(vec2)
               > (ClientManagers.moduleManager.range.isCombatRangeActive() && this.mc.thePlayer.getDistanceToEntity(this.pointedEntity) < 6.0F ? ClientManagers.moduleManager.range.getCurrentRange() : 3.0)) {
            this.pointedEntity = null;
            this.mc.objectMouseOver = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, vec2, (EnumFacing)null, new BlockPos(vec2));
         }

         if (this.pointedEntity != null && (d8 < d7 || this.mc.objectMouseOver == null)) {
            this.mc.objectMouseOver = new MovingObjectPosition(this.pointedEntity, vec2);
            if (this.pointedEntity instanceof EntityLivingBase || this.pointedEntity instanceof EntityItemFrame) {
               Entity pointedEntity2 = this.pointedEntity;
               this.mc.pointedEntity = pointedEntity2;
            }
         }

         if (flag2) {
            renderViewEntity.posX = d;
            renderViewEntity.posY = d3;
            renderViewEntity.posZ = d5;
            renderViewEntity.prevPosX = d2;
            renderViewEntity.prevPosY = d4;
            renderViewEntity.prevPosZ = d6;
         }
      }

      if (f == 1.0F) {
         this.mc.lastObjectMouseOver = new MovingObjectPosition(this.mc.objectMouseOver);
      }
   }

   public void activateNextShader() {
      if (OpenGlHelper.shadersSupported && this.mc.getRenderViewEntity() instanceof EntityPlayer) {
         if (this.theShaderGroup != null) {
            this.theShaderGroup.deleteShaderGroup();
         }

         this.shaderIndex = (this.shaderIndex + 1) % (shaderResourceLocations.length + 1);
         if (this.shaderIndex != shaderCount) {
            this.loadShader(shaderResourceLocations[this.shaderIndex]);
         } else {
            this.theShaderGroup = null;
         }
      }
   }

   public void updateShaderGroupSize(int n, int n2) {
      if (OpenGlHelper.shadersSupported) {
         if (this.theShaderGroup != null) {
            this.theShaderGroup.createBindFramebuffers(n, n2);
         }

         this.mc.renderGlobal.createBindEntityOutlineFbs(n, n2);
      }
   }

   private FloatBuffer setFogColorBuffer(float f, float f2, float f3, float f4) {
      if (Config.isShaderPackLoaded()) {
         Shaders.setFogColor(f, f2, f3);
      }

      ((Buffer)this.fogColorBuffer).clear();
      this.fogColorBuffer.put(f).put(f2).put(f3).put(f4);
      ((Buffer)this.fogColorBuffer).flip();
      return this.fogColorBuffer;
   }

   private void updateTorchFlicker() {
      this.torchFlickerDX = (float)((double)this.torchFlickerDX + (Math.random() - Math.random()) * Math.random() * Math.random());
      this.torchFlickerDX = (float)((double)this.torchFlickerDX * 0.9);
      this.torchFlickerX = this.torchFlickerX + (this.torchFlickerDX - this.torchFlickerX) * 1.0F;
      this.lightmapUpdateNeeded = true;
   }

   public void renderHand(float f, int n, boolean flag, boolean flag2, boolean flag3) {
      if (!this.debugView) {
         GlStateManager.matrixMode(5889);
         GlStateManager.loadIdentity();
         float f2 = 0.07F;
         if (this.mc.gameSettings.anaglyph) {
            GlStateManager.translate((float)(-(n * 2 - 1)) * f2, 0.0F, 0.0F);
         }

         if (Config.isShaderPackLoaded()) {
            Shaders.applyHandDepth();
         }

         EventPerspective event = PerspectiveListener.onPerspective((float)this.mc.displayWidth / (float)this.mc.displayHeight, true);
         Project.gluPerspective(this.getFOVModifier(f, false), event.getAspectRatio(), 0.05F, this.farPlaneDistance * 2.0F);
         GlStateManager.matrixMode(5888);
         GlStateManager.loadIdentity();
         if (this.mc.gameSettings.anaglyph) {
            GlStateManager.translate((float)(n * 2 - 1) * 0.1F, 0.0F, 0.0F);
         }

         boolean flag4 = false;
         if (flag) {
            GlStateManager.pushMatrix();
            this.hurtCameraEffect(f);
            if (this.mc.gameSettings.viewBobbing) {
               this.setupViewBobbing(f);
            }

            flag4 = this.mc.getRenderViewEntity() instanceof EntityLivingBase && ((EntityLivingBase)this.mc.getRenderViewEntity()).isPlayerSleeping();
            if (this.mc.gameSettings.thirdPersonView == 0 && !flag4 && !this.mc.gameSettings.hideGUI && !this.mc.playerController.isSpectator()) {
               this.enableLightmap();
               if (Config.isShaderPackLoaded()) {
                  ShadersRender.renderItemFP(this.itemRenderer, f, flag3);
               } else {
                  this.itemRenderer.renderItemInFirstPerson(f);
               }

               this.disableLightmap();
            }

            GlStateManager.popMatrix();
         }

         if (!flag2) {
            return;
         }

         this.disableLightmap();
         if (this.mc.gameSettings.thirdPersonView == 0 && !flag4) {
            this.itemRenderer.renderOverlays(f);
            this.hurtCameraEffect(f);
         }

         if (this.mc.gameSettings.viewBobbing) {
            this.setupViewBobbing(f);
         }
      }
   }

   public boolean isShaderActive() {
      return OpenGlHelper.shadersSupported && this.theShaderGroup != null;
   }

   public void onResourceManagerReload(IResourceManager resourceManager2) {
      if (this.theShaderGroup != null) {
         this.theShaderGroup.deleteShaderGroup();
      }

      this.theShaderGroup = null;
      if (this.shaderIndex != shaderCount) {
         this.loadShader(shaderResourceLocations[this.shaderIndex]);
      } else {
         this.loadEntityShader(this.mc.getRenderViewEntity());
      }
   }

   public void renderWorld(float f, long l, long l2) {
      this.updateLightmap(f);
      if (this.mc.getRenderViewEntity() == null) {
         EntityPlayerSP thePlayer = this.mc.thePlayer;
         this.mc.setRenderViewEntity((Entity)thePlayer);
      }

      this.getMouseOver(f, false);
      this.prevRenderYaw = this.mc.getRenderViewEntity().prevRotationYaw;
      this.prevRenderPitch = this.mc.getRenderViewEntity().prevRotationPitch;
      this.renderYaw = this.mc.getRenderViewEntity().rotationYaw;
      this.renderPitch = this.mc.getRenderViewEntity().rotationPitch;
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      renderViewEntity.rotationYaw = Augustus.getInstance().getYawPitchHelper().realYaw;
      renderViewEntity.rotationPitch = Augustus.getInstance().getYawPitchHelper().realPitch;
      renderViewEntity.prevRotationYaw = Augustus.getInstance().getYawPitchHelper().realLastYaw;
      renderViewEntity.prevRotationPitch = Augustus.getInstance().getYawPitchHelper().realLastPitch;
      double posX = renderViewEntity.posX;
      double posY = renderViewEntity.posY;
      double posZ = renderViewEntity.posZ;
      double prevPosX = renderViewEntity.prevPosX;
      double prevPosY = renderViewEntity.prevPosY;
      double prevPosZ = renderViewEntity.prevPosZ;
      double lastTickPosX = renderViewEntity.lastTickPosX;
      double lastTickPosY = renderViewEntity.lastTickPosY;
      double lastTickPosZ = renderViewEntity.lastTickPosZ;
      FreeCam freeCam = Augustus.getInstance().getModuleManager().freeCam;
      if (freeCam.isToggled()) {
         freeCam.realPosX = posX;
         freeCam.realPosY = posY;
         freeCam.realPosZ = posZ;
         freeCam.realLastTickPosX = lastTickPosX;
         freeCam.realLastTickPosY = lastTickPosY;
         freeCam.realLastTickPosZ = lastTickPosZ;
         renderViewEntity.posX = freeCam.cameraPos.xCoord;
         renderViewEntity.posY = freeCam.cameraPos.yCoord;
         renderViewEntity.posZ = freeCam.cameraPos.zCoord;
         renderViewEntity.lastTickPosX = freeCam.prevCameraPos.xCoord;
         renderViewEntity.lastTickPosY = freeCam.prevCameraPos.yCoord;
         renderViewEntity.lastTickPosZ = freeCam.prevCameraPos.zCoord;
         renderViewEntity.prevPosX = freeCam.prevCameraPos.xCoord;
         renderViewEntity.prevPosY = freeCam.prevCameraPos.yCoord;
         renderViewEntity.prevPosZ = freeCam.prevCameraPos.zCoord;
      }

      if (Config.isShaderPackLoaded()) {
         Shaders.beginRender(this.mc, f, l);
      }

      GlStateManager.enableDepth();
      GlStateManager.enableAlpha();
      GlStateManager.alphaFunc(516, 0.1F);
      this.mc.mcProfiler.startSection("center");
      if (this.mc.gameSettings.anaglyph) {
         anaglyphField = 0;
         GlStateManager.colorMask(false, true, true, false);
         this.renderWorldPass(0, f, l, l2);
         anaglyphField = 1;
         GlStateManager.colorMask(true, false, false, false);
         this.renderWorldPass(1, f, l, l2);
         GlStateManager.colorMask(true, true, true, false);
      } else {
         this.renderWorldPass(2, f, l, l2);
      }

      if (freeCam.isToggled()) {
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

      this.mc.mcProfiler.endSection();
   }

   private void renderCloudsCheck(RenderGlobal renderGlobal, float f, int n) {
      if (this.mc.gameSettings.renderDistanceChunks >= 4 && !Config.isCloudsOff() && Shaders.shouldRenderClouds(this.mc.gameSettings)) {
         this.mc.mcProfiler.endStartSection("clouds");
         GlStateManager.matrixMode(5889);
         GlStateManager.loadIdentity();
         EventPerspective event = PerspectiveListener.onPerspective((float)this.mc.displayWidth / (float)this.mc.displayHeight, false);
         Project.gluPerspective(this.getFOVModifier(f, true), event.getAspectRatio(), 0.05F, this.clipDistance * 4.0F);
         GlStateManager.matrixMode(5888);
         GlStateManager.pushMatrix();
         this.setupFog(0, f);
         renderGlobal.renderClouds(f, n);
         GlStateManager.disableFog();
         GlStateManager.popMatrix();
         GlStateManager.matrixMode(5889);
         GlStateManager.loadIdentity();
         Project.gluPerspective(this.getFOVModifier(f, true), event.getAspectRatio(), 0.05F, this.clipDistance);
         GlStateManager.matrixMode(5888);
      }
   }

   private void hurtCameraEffect(float f) {
      if (this.mc.getRenderViewEntity() instanceof EntityLivingBase) {
         EntityLivingBase renderViewEntity = (EntityLivingBase)this.mc.getRenderViewEntity();
         float f2 = (float)renderViewEntity.hurtTime - f;
         if (renderViewEntity.getCurrentHealth() <= 0.0F && !ClientManagers.moduleManager.customHurtCam.shouldRemoveHurtCamAtDeath()) {
            float f3 = (float)renderViewEntity.deathTime + f;
            GlStateManager.rotate(40.0F - 8000.0F / (f3 + 200.0F), 0.0F, 0.0F, 1.0F);
         }

         if (f2 < 0.0F) {
            return;
         }

         f2 /= (float)renderViewEntity.maxHurtTime;
         f2 = MathHelper.sin(f2 * f2 * f2 * f2 * 3.1415927F);
         float attackedAtYaw = renderViewEntity.attackedAtYaw;
         GlStateManager.rotate(-attackedAtYaw, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-f2 * ClientManagers.moduleManager.customHurtCam.getHurtCamStrength(), 0.0F, 0.0F, 1.0F);
         GlStateManager.rotate(attackedAtYaw, 0.0F, 1.0F, 0.0F);
      }
   }

   private void updateMainMenu(GuiMainMenu guiMainMenu) {
      try {
         String str = null;
         Calendar instance = Calendar.getInstance();
         instance.setTime(new Date());
         int n = instance.get(5);
         int n2 = instance.get(2) + 1;
         if (n == 8 && n2 == 4) {
            str = "Happy birthday, OptiFine!";
         }

         if (n == 14 && n2 == 8) {
            str = "Happy birthday, sp614x!";
         }

         if (str == null) {
            return;
         }

         Field[] declaredFields = GuiMainMenu.class.getDeclaredFields();

         for (int i = 0; i < declaredFields.length; i++) {
            if (declaredFields[i].getType() == String.class) {
               declaredFields[i].setAccessible(true);
               declaredFields[i].set(guiMainMenu, str);
               break;
            }
         }
      } catch (Throwable e) {
      }
   }

   private void setupViewBobbing(float f) {
      if (this.mc.getRenderViewEntity() instanceof EntityPlayer) {
         EntityPlayer renderViewEntity = (EntityPlayer)this.mc.getRenderViewEntity();
         float f2 = renderViewEntity.distanceWalkedModified - renderViewEntity.prevDistanceWalkedModified;
         float f3 = -(renderViewEntity.distanceWalkedModified + f2 * f);
         float f4 = renderViewEntity.prevCameraYaw + (renderViewEntity.cameraYaw - renderViewEntity.prevCameraYaw) * f;
         float f5 = renderViewEntity.prevCameraPitch + (renderViewEntity.cameraPitch - renderViewEntity.prevCameraPitch) * f;
         GlStateManager.translate(MathHelper.sin(f3 * 3.1415927F) * f4 * 0.5F, -Math.abs(MathHelper.cos(f3 * 3.1415927F) * f4), 0.0F);
         GlStateManager.rotate(MathHelper.sin(f3 * 3.1415927F) * f4 * 3.0F, 0.0F, 0.0F, 1.0F);
         GlStateManager.rotate(Math.abs(MathHelper.cos(f3 * 3.1415927F - 0.2F) * f4) * 5.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(f5, 1.0F, 0.0F, 0.0F);
      }
   }

   public void enableLightmap() {
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.matrixMode(5890);
      GlStateManager.loadIdentity();
      float f = 0.00390625F;
      GlStateManager.scale(f, f, f);
      GlStateManager.translate(8.0F, 8.0F, 8.0F);
      GlStateManager.matrixMode(5888);
      this.mc.getTextureManager().bindTexture(this.locationLightMap);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexParameteri(3553, 10242, 33071);
      GL11.glTexParameteri(3553, 10243, 33071);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.enableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      if (Config.isShaderPackLoaded()) {
         Shaders.enableLightmap();
      }
   }

   private void waitForServerThread() {
      this.serverWaitTimeCurrent = 0;
      if (!Config.isSmoothWorld() || !Config.isSingleProcessor()) {
         this.lastServerTime = 0L;
         this.lastServerTicks = 0;
      } else if (this.mc.isIntegratedServerRunning()) {
         IntegratedServer integratedServer = this.mc.getIntegratedServer();
         if (integratedServer != null) {
            if (!this.mc.isGamePaused() && !(this.mc.currentScreen instanceof GuiDownloadTerrain)) {
               if (this.serverWaitTime > 0) {
                  Lagometer.timerServer.start();
                  Config.sleep((long)this.serverWaitTime);
                  Lagometer.timerServer.end();
                  this.serverWaitTimeCurrent = this.serverWaitTime;
               }

               long l = System.nanoTime() / 1000000L;
               if (this.lastServerTime != 0L && this.lastServerTicks != 0) {
                  long l2 = l - this.lastServerTime;
                  if (l2 < 0L) {
                     this.lastServerTime = l;
                     l2 = 0L;
                  }

                  if (l2 >= 50L) {
                     this.lastServerTime = l;
                     int tickCounter = integratedServer.getTickCounter();
                     int n = tickCounter - this.lastServerTicks;
                     if (n < 0) {
                        this.lastServerTicks = tickCounter;
                        n = 0;
                     }

                     if (n < 1 && this.serverWaitTime < 100) {
                        this.serverWaitTime += 2;
                     }

                     if (n > 1 && this.serverWaitTime > 0) {
                        this.serverWaitTime--;
                     }

                     this.lastServerTicks = tickCounter;
                  }
               } else {
                  this.lastServerTime = l;
                  this.lastServerTicks = integratedServer.getTickCounter();
                  this.avgServerTickDiff = 1.0F;
                  this.avgServerTimeDiff = 50.0F;
               }
            } else {
               if (this.mc.currentScreen instanceof GuiDownloadTerrain) {
                  Config.sleep(20L);
               }

               this.lastServerTime = 0L;
               this.lastServerTicks = 0;
            }
         }
      }
   }

   public void switchUseShader() {
      this.useShader = !this.useShader;
   }

   private void checkLoadVisibleChunks(Entity entity, float f, ICamera camera, boolean flag) {
      int n = 201435902;
      if (this.loadVisibleChunks) {
         this.loadVisibleChunks = false;
         this.loadAllVisibleChunks(entity, (double)f, camera, flag);
         this.mc.ingameGUI.getChatGUI().deleteChatLine(n);
      }

      if (Keyboard.isKeyDown(61) && Keyboard.isKeyDown(38)) {
         if (this.mc.currentScreen != null) {
            return;
         }

         this.loadVisibleChunks = true;
         ChatComponentText chatComponentText = new ChatComponentText(I18n.format("of.message.loadingVisibleChunks", new Object[0]));
         this.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(chatComponentText, n);
      }
   }

   private void renderWorldDirections(float f) {
      if (this.mc.gameSettings.showDebugInfo && !this.mc.gameSettings.hideGUI && !this.mc.thePlayer.hasReducedDebug() && !this.mc.gameSettings.reducedDebugInfo) {
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GL11.glLineWidth(1.0F);
         GlStateManager.disableTexture2D();
         GlStateManager.depthMask(false);
         GlStateManager.pushMatrix();
         GlStateManager.matrixMode(5888);
         GlStateManager.loadIdentity();
         this.orientCamera(f);
         GlStateManager.translate(0.0F, renderViewEntity.getEyeHeight(), 0.0F);
         RenderGlobal.drawOutlinedBoundingBox(0.0, 0.0, 0.0, 0.005, 1.0E-4, 1.0E-4, 255, 0, 0, 255);
         RenderGlobal.drawOutlinedBoundingBox(0.0, 0.0, 0.0, 1.0E-4, 1.0E-4, 0.005, 0, 0, 255, 255);
         RenderGlobal.drawOutlinedBoundingBox(0.0, 0.0, 0.0, 1.0E-4, 0.0033, 1.0E-4, 0, 255, 0, 255);
         GlStateManager.popMatrix();
         GlStateManager.depthMask(true);
         GlStateManager.enableTexture2D();
         GlStateManager.disableBlend();
      }
   }

   public void disableLightmap() {
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      if (Config.isShaderPackLoaded()) {
         Shaders.disableLightmap();
      }
   }

   public void loadEntityShader(Entity entity) {
      if (OpenGlHelper.shadersSupported) {
         if (this.theShaderGroup != null) {
            this.theShaderGroup.deleteShaderGroup();
         }

         this.theShaderGroup = null;
         if (entity instanceof EntityCreeper) {
            this.loadShader(new ResourceLocation("shaders/post/creeper.json"));
         } else if (entity instanceof EntitySpider) {
            this.loadShader(new ResourceLocation("shaders/post/spider.json"));
         } else if (entity instanceof EntityEnderman) {
            this.loadShader(new ResourceLocation("shaders/post/invert.json"));
         }
      }
   }

   private void renderHand(float f, int n) {
      this.renderHand(f, n, true, true, false);
   }

   private void orientCamera(float f) {
      Entity renderViewEntity = this.mc.getRenderViewEntity();
      EventOrientCamera event = OrientCameraListener.onOrientCamera(renderViewEntity.getEyeHeight(), renderViewEntity.rotationYaw, renderViewEntity.rotationPitch);
      float eyeHeight = event.getEyeHeight();
      double d = renderViewEntity.prevPosX + (renderViewEntity.posX - renderViewEntity.prevPosX) * (double)f;
      double d2 = renderViewEntity.prevPosY + (renderViewEntity.posY - renderViewEntity.prevPosY) * (double)f + (double)eyeHeight;
      double d3 = renderViewEntity.prevPosZ + (renderViewEntity.posZ - renderViewEntity.prevPosZ) * (double)f;
      if (renderViewEntity instanceof EntityLivingBase && ((EntityLivingBase)renderViewEntity).isPlayerSleeping()) {
         eyeHeight = (float)((double)eyeHeight + 1.0);
         GlStateManager.translate(0.0F, 0.3F, 0.0F);
         if (!this.mc.gameSettings.debugCamEnable) {
            BlockPos pos = new BlockPos(renderViewEntity);
            IBlockState blockState = this.mc.theWorld.getBlockState(pos);
            if (blockState.getBlock() == Blocks.bed) {
               GlStateManager.rotate((float)(((EnumFacing)blockState.getValue(BlockBed.FACING)).getHorizontalIndex() * 90), 0.0F, 1.0F, 0.0F);
            }

            GlStateManager.rotate(renderViewEntity.prevRotationYaw + (renderViewEntity.rotationYaw - renderViewEntity.prevRotationYaw) * f + 180.0F, 0.0F, -1.0F, 0.0F);
            GlStateManager.rotate(renderViewEntity.prevRotationPitch + (renderViewEntity.rotationPitch - renderViewEntity.prevRotationPitch) * f, -1.0F, 0.0F, 0.0F);
         }
      } else if (this.mc.gameSettings.thirdPersonView > 0) {
         double d4 = (double)(this.thirdPersonDistanceTemp + (this.thirdPersonDistance - this.thirdPersonDistanceTemp) * f);
         if (this.mc.gameSettings.debugCamEnable) {
            GlStateManager.translate(0.0F, 0.0F, (float)(-d4));
         } else {
            float yaw = event.getYaw();
            float pitch = event.getPitch();
            if (this.mc.gameSettings.thirdPersonView == 2) {
               pitch += 180.0F;
            }

            double d5 = (double)(-MathHelper.sin(yaw / 180.0F * 3.1415927F) * MathHelper.cos(pitch / 180.0F * 3.1415927F)) * d4;
            double d6 = (double)(MathHelper.cos(yaw / 180.0F * 3.1415927F) * MathHelper.cos(pitch / 180.0F * 3.1415927F)) * d4;
            double d7 = (double)(-MathHelper.sin(pitch / 180.0F * 3.1415927F)) * d4;
            if (!((Module)ClientManagers.moduleManager.cameraNoClip).isToggled()) {
               for (int i = 0; i < 8; i++) {
                  float f2 = (float)((i & 1) * 2 - 1);
                  float f3 = (float)((i >> 1 & 1) * 2 - 1);
                  float f4 = (float)((i >> 2 & 1) * 2 - 1);
                  f2 *= 0.1F;
                  f3 *= 0.1F;
                  f4 *= 0.1F;
                  MovingObjectPosition hit = this.mc
                     .theWorld
                     .rayTraceBlocks(
                        d + (double)f2,
                        d2 + (double)f3,
                        d3 + (double)f4,
                        d - d5 + (double)f2 + (double)f4,
                        d2 - d7 + (double)f3,
                        d3 - d6 + (double)f4
                     );
                  if (hit != null) {
                     double d8 = hit.hitVec.distanceTo(d, d2, d3);
                     if (d8 < d4) {
                        d4 = d8;
                     }
                  }
               }
            }

            if (this.mc.gameSettings.thirdPersonView == 2) {
               GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            }

            GlStateManager.rotate(event.getPitch() - pitch, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(event.getYaw() - yaw, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(0.0F, 0.0F, (float)(-d4));
            GlStateManager.rotate(yaw - event.getYaw(), 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(pitch - event.getPitch(), 1.0F, 0.0F, 0.0F);
         }
      } else {
         GlStateManager.translate(0.0F, 0.0F, -0.1F);
      }

      if (!this.mc.gameSettings.debugCamEnable) {
         if (event.isOverrideRotation()) {
            GlStateManager.rotate(event.getPitch(), 1.0F, 0.0F, 0.0F);
            if (renderViewEntity instanceof EntityAnimal) {
               EntityAnimal entityAnimal = (EntityAnimal)renderViewEntity;
               GlStateManager.rotate(event.getYaw() + 180.0F, 0.0F, 1.0F, 0.0F);
            } else {
               GlStateManager.rotate(event.getYaw() + 180.0F, 0.0F, 1.0F, 0.0F);
            }
         } else {
            GlStateManager.rotate(renderViewEntity.prevRotationPitch + (renderViewEntity.rotationPitch - renderViewEntity.prevRotationPitch) * f, 1.0F, 0.0F, 0.0F);
            if (renderViewEntity instanceof EntityAnimal) {
               EntityAnimal entityAnimal2 = (EntityAnimal)renderViewEntity;
               GlStateManager.rotate(entityAnimal2.prevRotationYawHead + (entityAnimal2.rotationYawHead - entityAnimal2.prevRotationYawHead) * f + 180.0F, 0.0F, 1.0F, 0.0F);
            } else {
               GlStateManager.rotate(renderViewEntity.prevRotationYaw + (renderViewEntity.rotationYaw - renderViewEntity.prevRotationYaw) * f + 180.0F, 0.0F, 1.0F, 0.0F);
            }
         }
      }

      GlStateManager.translate(-event.getOffset().xCoord, -event.getOffset().yCoord - (double)eyeHeight, -event.getOffset().zCoord);
      d = renderViewEntity.prevPosX + (renderViewEntity.posX - renderViewEntity.prevPosX) * (double)f;
      d2 = renderViewEntity.prevPosY + (renderViewEntity.posY - renderViewEntity.prevPosY) * (double)f + (double)eyeHeight;
      d3 = renderViewEntity.prevPosZ + (renderViewEntity.posZ - renderViewEntity.prevPosZ) * (double)f;
      this.cloudFog = this.mc.renderGlobal.hasCloudFog(d, d2, d3, f);
   }

   private void frameFinish() {
      if (this.mc.theWorld != null && Config.isShowGlErrors() && TimedEvent.isActive("CheckGlErrorFrameFinish", 10000L)) {
         int n = GlStateManager.glGetError();
         if (n != 0 && GlErrors.isEnabled(n)) {
            String glErrorString = Config.getGlErrorString(n);
            ChatComponentText chatComponentText = new ChatComponentText(I18n.format("of.message.openglError", new Object[]{n, glErrorString}));
            this.mc.ingameGUI.getChatGUI().printChatMessage(chatComponentText);
         }
      }
   }

   private float getFOVModifier(float f, boolean flag) {
      if (this.debugView) {
         return 90.0F;
      } else {
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         float f2 = 70.0F;
         if (flag) {
            f2 = this.mc.gameSettings.fovSetting;
            FovModifier fovModifier = ClientManagers.moduleManager.fovModifier;
            if (fovModifier.isToggled()) {
               f2 = (float)fovModifier.defaultFov.getValue();
               boolean flag2 = this.mc.thePlayer.isPotionActive(Potion.moveSpeed) && MoveUtil.isMoving();
               boolean isSprinting = this.mc.thePlayer.isSprinting();
               float f3 = f2;
               if (isSprinting || flag2) {
                  f3 = flag2 ? (float)fovModifier.speedFov.getValue() : (float)fovModifier.sprintFov.getValue();
               }

               if (isSprinting != fovModifier.wasSprinting || flag2 != fovModifier.wasSpeedActive) {
                  fovModifier.fovAnimation.start(Math.max(1, (int)fovModifier.animationTime.getValue()), AnimationUtil.parseEasingType(fovModifier.animationModes.getSelectedValue()), f3 > fovModifier.previousFov ? 1 : 0, false);
               }

               f2 = (float)((double)f3 + (double)(fovModifier.previousFov - f3) * fovModifier.fovAnimation.getEasedProgress());
               if (isSprinting != fovModifier.wasSprinting || flag2 != fovModifier.wasSpeedActive) {
                  fovModifier.previousFov = fovModifier.targetFov;
                  fovModifier.targetFov = f3;
               }

               fovModifier.wasSprinting = isSprinting;
               fovModifier.wasSpeedActive = flag2;
            }

            if (Config.isDynamicFov() && (!ClientManagers.moduleManager.fovModifier.isToggled() || !ClientManagers.moduleManager.fovModifier.ignoreDynamicFov.getValue())) {
               f2 *= this.fovModifierHandPrev + (this.fovModifierHand - this.fovModifierHandPrev) * f;
            }
         }

         boolean flag3 = false;
         if (this.mc.currentScreen == null) {
            flag3 = GameSettings.isKeyDown(this.mc.gameSettings.ofKeyBindZoom);
         }

         boolean flag4 = flag3;
         if (ClientManagers.moduleManager.customZoom.isToggled()) {
            CustomZoom customZoom = ClientManagers.moduleManager.customZoom;
            if (flag3 != this.wasZooming) {
               AnimationUtil zoomAnimation = customZoom.getZoomAnimation();
               int value = (int)customZoom.animationTime.getValue();
               customZoom.getZoomAnimation();
               zoomAnimation.start(value, AnimationUtil.parseEasingType(customZoom.animationModes.getSelectedValue()), flag3 ? 1 : 0, true);
            }

            if (!flag3 && customZoom.getZoomAnimation().getLinearProgress() < 1.0 && Config.zoomMode && !customZoom.animationModes.getSelectedValue().equals("None")) {
               flag3 = true;
            }
         }

         this.wasZooming = flag4;
         if (flag3) {
            if (!Config.zoomMode) {
               Config.zoomMode = true;
               Config.zoomSmoothCamera = this.mc.gameSettings.smoothCamera;
               this.mc.gameSettings.smoothCamera = true;
               this.mc.renderGlobal.displayListEntitiesDirty = true;
            }

            if (Config.zoomMode) {
               EventZoom event = ZoomListener.postZoom(4.0F);
               f2 /= event.getFovMultiplier();
            }
         } else if (Config.zoomMode) {
            Config.zoomMode = false;
            this.mc.gameSettings.smoothCamera = false;
            this.mouseFilterXAxis = new MouseFilter();
            this.mouseFilterYAxis = new MouseFilter();
            this.mc.renderGlobal.displayListEntitiesDirty = true;
         }

         if (renderViewEntity instanceof EntityLivingBase && ((EntityLivingBase)renderViewEntity).getCurrentHealth() <= 0.0F) {
            float f5 = (float)((EntityLivingBase)renderViewEntity).deathTime + f;
            f2 /= (1.0F - 500.0F / (f5 + 500.0F)) * 2.0F + 1.0F;
         }

         if (ActiveRenderInfo.getBlockAtEntityViewpoint(this.mc.theWorld, renderViewEntity, f).getMaterial() == Material.water) {
            f2 = f2 * 60.0F / 70.0F;
         }

         return f2;
      }
   }

   private void loadAllVisibleChunks(Entity entity, double d, ICamera camera, boolean flag) {
      int ofChunkUpdates = this.mc.gameSettings.ofChunkUpdates;
      boolean ofLazyChunkLoading = this.mc.gameSettings.ofLazyChunkLoading;

      try {
         this.mc.gameSettings.ofChunkUpdates = 1000;
         this.mc.gameSettings.ofLazyChunkLoading = false;
         RenderGlobal renderGlobal = Config.getRenderGlobal();
         int countLoadedChunks = renderGlobal.getCountLoadedChunks();
         System.currentTimeMillis();
         Config.dbg("Loading visible chunks");
         long l = System.currentTimeMillis() + 5000L;
         int n = 0;
         boolean flag2 = false;

         do {
            flag2 = false;

            for (int i = 0; i < 100; i++) {
               renderGlobal.displayListEntitiesDirty = true;
               renderGlobal.setupTerrain(entity, d, camera, this.frameCount++, flag);
               if (!renderGlobal.hasNoChunkUpdates()) {
                  flag2 = true;
               }

               n += renderGlobal.getCountChunksToUpdate();

               while (!renderGlobal.hasNoChunkUpdates()) {
                  renderGlobal.updateChunks(System.nanoTime() + 1000000000L);
               }

               n -= renderGlobal.getCountChunksToUpdate();
               if (!flag2) {
                  break;
               }
            }

            if (renderGlobal.getCountLoadedChunks() != countLoadedChunks) {
               flag2 = true;
               countLoadedChunks = renderGlobal.getCountLoadedChunks();
            }

            if (System.currentTimeMillis() > l) {
               Config.log("Chunks loaded: " + n);
               l = System.currentTimeMillis() + 5000L;
            }
         } while (flag2);

         Config.log("Chunks loaded: " + n);
         Config.log("Finished loading visible chunks");
         RenderChunk.renderChunksUpdated = 0;
      } finally {
         this.mc.gameSettings.ofChunkUpdates = ofChunkUpdates;
         this.mc.gameSettings.ofLazyChunkLoading = ofLazyChunkLoading;
      }
   }

   public void renderRainSnow(float f) {
      float rainStrength = this.mc.theWorld.getRainStrength(f);
      if (rainStrength > 0.0F) {
         if (Config.isRainOff()) {
            return;
         }

         this.enableLightmap();
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         WorldClient theWorld = this.mc.theWorld;
         int n = MathHelper.floor_double(renderViewEntity.posX);
         int n2 = MathHelper.floor_double(renderViewEntity.posY);
         int n3 = MathHelper.floor_double(renderViewEntity.posZ);
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         GlStateManager.disableCull();
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.alphaFunc(516, 0.1F);
         double d = renderViewEntity.lastTickPosX + (renderViewEntity.posX - renderViewEntity.lastTickPosX) * (double)f;
         double d2 = renderViewEntity.lastTickPosY + (renderViewEntity.posY - renderViewEntity.lastTickPosY) * (double)f;
         double d3 = renderViewEntity.lastTickPosZ + (renderViewEntity.posZ - renderViewEntity.lastTickPosZ) * (double)f;
         int n4 = MathHelper.floor_double(d2);
         byte b = 5;
         if (Config.isRainFancy()) {
            b = 10;
         }

         byte b2 = -1;
         float f2 = (float)this.rendererUpdateCount + f;
         worldRenderer.setTranslation(-d, -d2, -d3);
         if (Config.isRainFancy()) {
            b = 10;
         }

         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

         for (int i = n3 - b; i <= n3 + b; i++) {
            for (int j = n - b; j <= n + b; j++) {
               int n5 = (i - n3 + 16) * 32 + j - n + 16;
               double d4 = (double)this.rainXCoords[n5] * 0.5;
               double d5 = (double)this.rainYCoords[n5] * 0.5;
               pos.set(j, 0, i);
               BiomeGenBase biomeGenForCoords = theWorld.getBiomeGenForCoords(pos);
               if (biomeGenForCoords.canSpawnLightningBolt()
                  || biomeGenForCoords.getEnableSnow()
                  || ClientManagers.moduleManager.ambiance.isToggled() && ClientManagers.moduleManager.ambiance.rain.getValue() > 0.0) {
                  int precipitationHeightY = theWorld.getPrecipitationHeightY(pos.getX(), pos.getZ());
                  int n6 = n2 - b;
                  int n7 = n2 + b;
                  if (n6 < precipitationHeightY) {
                     n6 = precipitationHeightY;
                  }

                  if (n7 < precipitationHeightY) {
                     n7 = precipitationHeightY;
                  }

                  int n8 = precipitationHeightY;
                  if (precipitationHeightY < n4) {
                     n8 = n4;
                  }

                  if (n6 != n7) {
                     this.random.setSeed((long)(j * j * 3121 + j * 45238971 ^ i * i * 418711 + i * 13761));
                     pos.set(j, n6, i);
                     float floatTemperature = biomeGenForCoords.getFloatTemperature(pos);
                     float temperatureAtHeight = theWorld.getWorldChunkManager().getTemperatureAtHeight(floatTemperature, precipitationHeightY);
                     if (ClientManagers.moduleManager.ambiance.isToggled() && ClientManagers.moduleManager.ambiance.snow.getValue()) {
                        temperatureAtHeight = 0.1F;
                     }

                     if (temperatureAtHeight >= 0.15F) {
                        if (b2 != 0) {
                           if (b2 >= 0) {
                              instance.draw();
                           }

                           b2 = 0;
                           this.mc.getTextureManager().bindTexture(locationRainPng);
                           worldRenderer.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
                        }

                        double d6 = (
                              (double)(this.rendererUpdateCount + j * j * 3121 + j * 45238971 + i * i * 418711 + i * 13761 & 31) + (double)f
                           )
                           / 32.0
                           * (3.0 + this.random.nextDouble());
                        double d7 = (double)((float)j + 0.5F) - renderViewEntity.posX;
                        double d8 = (double)((float)i + 0.5F) - renderViewEntity.posZ;
                        float f3 = MathHelper.sqrt_double(d7 * d7 + d8 * d8) / (float)b;
                        float f4 = ((1.0F - f3 * f3) * 0.5F + 0.5F) * rainStrength;
                        pos.set(j, n8, i);
                        int combinedLight = theWorld.getCombinedLight(pos.getX(), pos.getY(), pos.getZ(), 0);
                        int n9 = combinedLight >> 16 & 65535;
                        int n10 = combinedLight & 65535;
                        worldRenderer.pos((double)j - d4 + 0.5, (double)n6, (double)i - d5 + 0.5)
                           .tex(0.0, (double)n6 * 0.25 + d6)
                           .color(1.0F, 1.0F, 1.0F, f4)
                           .lightmap(n9, n10)
                           .endVertex();
                        worldRenderer.pos((double)j + d4 + 0.5, (double)n6, (double)i + d5 + 0.5)
                           .tex(1.0, (double)n6 * 0.25 + d6)
                           .color(1.0F, 1.0F, 1.0F, f4)
                           .lightmap(n9, n10)
                           .endVertex();
                        worldRenderer.pos((double)j + d4 + 0.5, (double)n7, (double)i + d5 + 0.5)
                           .tex(1.0, (double)n7 * 0.25 + d6)
                           .color(1.0F, 1.0F, 1.0F, f4)
                           .lightmap(n9, n10)
                           .endVertex();
                        worldRenderer.pos((double)j - d4 + 0.5, (double)n7, (double)i - d5 + 0.5)
                           .tex(0.0, (double)n7 * 0.25 + d6)
                           .color(1.0F, 1.0F, 1.0F, f4)
                           .lightmap(n9, n10)
                           .endVertex();
                     } else {
                        if (b2 != 1) {
                           if (b2 >= 0) {
                              instance.draw();
                           }

                           b2 = 1;
                           this.mc.getTextureManager().bindTexture(locationSnowPng);
                           worldRenderer.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
                        }

                        double d9 = (double)(((float)(this.rendererUpdateCount & 511) + f) / 512.0F);
                        double d10 = this.random.nextDouble() + (double)f2 * 0.01 * (double)((float)this.random.nextGaussian());
                        double d11 = this.random.nextDouble() + (double)(f2 * (float)this.random.nextGaussian()) * 0.001;
                        double d12 = (double)((float)j + 0.5F) - renderViewEntity.posX;
                        double d13 = (double)((float)i + 0.5F) - renderViewEntity.posZ;
                        float f5 = MathHelper.sqrt_double(d12 * d12 + d13 * d13) / (float)b;
                        float f6 = ((1.0F - f5 * f5) * 0.3F + 0.5F) * rainStrength;
                        pos.set(j, n8, i);
                        int n11 = (theWorld.getCombinedLight(pos.getX(), pos.getY(), pos.getZ(), 0) * 3 + 15728880) / 4;
                        int n12 = n11 >> 16 & 65535;
                        int n13 = n11 & 65535;
                        worldRenderer.pos((double)j - d4 + 0.5, (double)n6, (double)i - d5 + 0.5)
                           .tex(0.0 + d10, (double)n6 * 0.25 + d9 + d11)
                           .color(1.0F, 1.0F, 1.0F, f6)
                           .lightmap(n12, n13)
                           .endVertex();
                        worldRenderer.pos((double)j + d4 + 0.5, (double)n6, (double)i + d5 + 0.5)
                           .tex(1.0 + d10, (double)n6 * 0.25 + d9 + d11)
                           .color(1.0F, 1.0F, 1.0F, f6)
                           .lightmap(n12, n13)
                           .endVertex();
                        worldRenderer.pos((double)j + d4 + 0.5, (double)n7, (double)i + d5 + 0.5)
                           .tex(1.0 + d10, (double)n7 * 0.25 + d9 + d11)
                           .color(1.0F, 1.0F, 1.0F, f6)
                           .lightmap(n12, n13)
                           .endVertex();
                        worldRenderer.pos((double)j - d4 + 0.5, (double)n7, (double)i - d5 + 0.5)
                           .tex(0.0 + d10, (double)n7 * 0.25 + d9 + d11)
                           .color(1.0F, 1.0F, 1.0F, f6)
                           .lightmap(n12, n13)
                           .endVertex();
                     }
                  }
               }
            }
         }

         if (b2 >= 0) {
            instance.draw();
         }

         worldRenderer.setTranslation(0.0, 0.0, 0.0);
         GlStateManager.enableCull();
         GlStateManager.disableBlend();
         GlStateManager.alphaFunc(516, 0.1F);
         this.disableLightmap();
      }
   }

   public void setupOverlayRendering(ScaledResolution sr) {
      GlStateManager.clear(256);
      GlStateManager.matrixMode(5889);
      GlStateManager.loadIdentity();
      GlStateManager.ortho(0.0, sr.getScaledWidth_double(), sr.getScaledHeight_double(), 0.0, 1000.0, 3000.0);
      GlStateManager.matrixMode(5888);
      GlStateManager.loadIdentity();
      GlStateManager.translate(0.0F, 0.0F, -2000.0F);
   }

   public void updateCameraAndRender(float f, long l, long l2) {
      boolean flag;
      Config.renderPartialTicks = f;
         this.frameInit();
         flag = Display.isActive();
if (!flag && this.mc.gameSettings.pauseOnLostFocus && (!this.mc.gameSettings.touchscreen || !MouseInputUtil.isButtonDown(1))) {
if (!ClientManagers.moduleManager.autoPlay.shouldSuppressEscapeMenu()) {
if (Minecraft.getSystemTime() - this.prevFrameTime > 500L) {
                  this.mc.displayInGameMenu();
               }
} else {
this.prevFrameTime = Minecraft.getSystemTime();
}
} else {
this.prevFrameTime = Minecraft.getSystemTime();
}

      this.mc.mcProfiler.startSection("mouse");
      if (flag && Minecraft.isRunningOnMac && this.mc.inGameHasFocus && !Mouse.isInsideWindow()) {
         Mouse.setGrabbed(false);
         Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
         Mouse.setGrabbed(true);
      }

      if (!(!((this.mc.inGameHasFocus || this.mc.currentScreen == null) && !ClientManagers.moduleManager.invManager.shouldStopMouseMovement()) && (!ClientManagers.moduleManager.guiMove.isToggled()
               || !ClientManagers.moduleManager.guiMove.canRotateInGui()
               || (!MouseInputUtil.isButtonDown(1) || !MouseInputUtil.isButtonDown(0))
                  && ClientManagers.moduleManager.guiMove.isRotateOnlyWhileClicking()))) {
if (flag && this.mc.thePlayer != null) {
            this.mc.mouseHelper.mouseXYChange();
            if (!ClientManagers.moduleManager.autoPlay.isToggled() || !ClientManagers.moduleManager.autoPlay.removeEscape.getValue()) {
               if (!Mouse.isGrabbed()) {
                  Mouse.setGrabbed(true);
               }

               if ((this.mc.currentScreen != null || !this.mc.inGameHasFocus)
                  && ClientManagers.moduleManager.guiMove.isToggled()
                  && ClientManagers.moduleManager.guiMove.canRotateInGui()
                  && (
                     MouseInputUtil.isButtonDown(1) && MouseInputUtil.isButtonDown(0)
                        || !ClientManagers.moduleManager.guiMove.isRotateOnlyWhileClicking()
                  )) {
                  Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
                  Mouse.setGrabbed(false);
               }
            }

            float f2 = this.mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
            float f3 = f2 * f2 * f2 * 8.0F;
            EventMouseMove event = MouseMoveListener.onMouseMove(this.mc.mouseHelper.deltaX, this.mc.mouseHelper.deltaY, f, StateTypes.PRE);
            float f4 = (float)event.getDeltaX() * f3;
            float f5 = (float)event.getDeltaY() * f3;
            byte b = 1;
            if (this.mc.gameSettings.invertMouse && !event.isIgnoreInvertMouse()) {
               b = -1;
            }

            if (!this.mc.gameSettings.smoothCamera || event.isBypassSmoothCamera() || ClientManagers.moduleManager.customZoom.isToggled() && ClientManagers.moduleManager.customZoom.fastZoom.getValue()) {
               this.smoothCamYaw = 0.0F;
               this.smoothCamPitch = 0.0F;
               this.mc.thePlayer.setAngles(f4, f5 * (float)b);
            } else {
               this.smoothCamYaw += f4;
               this.smoothCamPitch += f5;
               float f6 = f - this.smoothCamPartialTicks;
               this.smoothCamPartialTicks = f;
               f4 = this.smoothCamFilterX * f6;
               f5 = this.smoothCamFilterY * f6;
               this.mc.thePlayer.setAngles(f4, f5 * (float)b);
            }

            MouseMoveListener.onMouseMove(this.mc.mouseHelper.deltaX, this.mc.mouseHelper.deltaY, f, StateTypes.POST);
         }
}

      this.mc.mcProfiler.endSection();
      if (!this.mc.skipRenderWorld) {
         anaglyphEnable = this.mc.gameSettings.anaglyph;
         ScaledResolution sr = new ScaledResolution(this.mc);
         int scaledWidth = sr.getScaledWidth();
         int scaledHeight = sr.getScaledHeight();
         int n = Mouse.getX() * scaledWidth / this.mc.displayWidth;
         int n2 = scaledHeight - Mouse.getY() * scaledHeight / this.mc.displayHeight - 1;
         int limitFramerate = this.mc.gameSettings.limitFramerate;
         if (this.mc.theWorld != null) {
            this.mc.mcProfiler.startSection("level");
            int n3 = Math.min(Minecraft.getDebugFPS(), limitFramerate);
            n3 = Math.max(n3, 60);
            long l3 = System.nanoTime() - l;
            long l4 = Math.max((long)(1000000000 / n3 / 4) - l3, 0L);
            this.renderWorld(f, System.nanoTime() + l4, l2);
            if (OpenGlHelper.shadersSupported) {
               this.mc.renderGlobal.renderEntityOutlineFramebuffer();
               if (this.theShaderGroup != null && this.useShader) {
                  GlStateManager.matrixMode(5890);
                  GlStateManager.pushMatrix();
                  GlStateManager.loadIdentity();
                  this.theShaderGroup.loadShaderGroup(f);
                  GlStateManager.popMatrix();
               }

               this.mc.getFramebuffer().bindFramebuffer(true);
            }

            this.renderEndNanoTime = System.nanoTime();
            this.mc.mcProfiler.endStartSection("gui");
            if (!this.mc.gameSettings.hideGUI || this.mc.currentScreen != null) {
               GlStateManager.alphaFunc(516, 0.1F);
               this.mc.ingameGUI.renderGameOverlay(f);
               if (this.mc.gameSettings.ofShowFps && !this.mc.gameSettings.showDebugInfo) {
                  Config.drawFps();
               }

               if (this.mc.gameSettings.showDebugInfo) {
                  Lagometer.showLagometer(sr);
               }
            }

            this.mc.mcProfiler.endSection();
         } else {
            GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
            GlStateManager.matrixMode(5889);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.loadIdentity();
            this.setupOverlayRendering(sr);
            this.renderEndNanoTime = System.nanoTime();
            TileEntityRendererDispatcher.instance.renderEngine = this.mc.getTextureManager();
            TileEntityRendererDispatcher.instance.fontRenderer = this.mc.fontRendererObj;
         }

         if (this.mc.currentScreen != null) {
            GlStateManager.clear(256);

            try {
               BlurShadowManager.enableGuiLayer();
               this.mc.currentScreen.drawScreen(n, n2, f);
               if (Augustus.getInstance().getModuleManager().notifications.isToggled()) {
                  Augustus.getInstance().getModuleManager().notifications.renderNotifications(true);
               }

               BlurShadowManager.disableGuiLayer();
            } catch (Throwable e) {
               CrashReport crashReport = CrashReport.makeCrashReport(e, "Rendering screen");
               CrashReportCategory crashReportCategory = crashReport.makeCategory("Screen render details");
               crashReportCategory.addCrashSectionCallable("Screen name", new EntityRendererScreenNameCallable(this));
               crashReportCategory.addCrashSectionCallable("Mouse location", new EntityRenderer.ValueCallable(this, n, n2));
               crashReportCategory.addCrashSectionCallable("Screen size", new EntityRenderer.ValueCallable2(this, sr));
               throw new ReportedException(crashReport);
            }
         } else {
            this.mc.audioPlayer.stop();
         }

         if (this.mc.getRenderViewEntity() != null) {
            this.mc.getRenderViewEntity().prevRotationYaw = this.prevRenderYaw;
            this.mc.getRenderViewEntity().prevRotationPitch = this.prevRenderPitch;
            this.mc.getRenderViewEntity().rotationYaw = this.renderYaw;
            this.mc.getRenderViewEntity().rotationPitch = this.renderPitch;
         }
      }

      this.frameFinish();
      this.waitForServerThread();
      MemoryMonitor.update();
      Lagometer.updateLagometer();
      if (this.mc.gameSettings.ofProfiler) {
         this.mc.gameSettings.showDebugProfilerChart = true;
      }
   }

   private void frameInit() {
      GlErrors.frameStart();
      if (!this.initialized) {
         TextureUtils.registerResourceListener();
         if (Config.getBitsOs() == 64 && Config.getBitsJre() == 32) {
            Config.setNotify64BitJava(true);
         }

         this.initialized = true;
      }

      Config.checkDisplayMode();
      WorldClient theWorld = this.mc.theWorld;
      if (theWorld != null) {
         if (Config.getNewRelease() != null) {
            String str2 = "HD_U".replace("HD_U", "HD Ultra").replace("L", "Light") + " " + Config.getNewRelease();
            ChatComponentText chatComponentText = new ChatComponentText(I18n.format("of.message.newVersion", new Object[]{"§n" + str2 + "§r"}));
            chatComponentText.setChatStyle(new ChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://optifine.net/downloads")));
            this.mc.ingameGUI.getChatGUI().printChatMessage(chatComponentText);
            Config.setNewRelease((String)null);
         }

         if (Config.isNotify64BitJava()) {
            Config.setNotify64BitJava(false);
            ChatComponentText chatComponentText2 = new ChatComponentText(I18n.format("of.message.java64Bit", new Object[0]));
            this.mc.ingameGUI.getChatGUI().printChatMessage(chatComponentText2);
         }
      }

      if (this.mc.currentScreen instanceof GuiMainMenu) {
         this.updateMainMenu((GuiMainMenu)this.mc.currentScreen);
      }

      if (this.updatedWorld != theWorld) {
         RandomEntities.worldChanged(this.updatedWorld, theWorld);
         Config.updateThreadPriorities();
         this.lastServerTime = 0L;
         this.lastServerTicks = 0;
         this.updatedWorld = theWorld;
      }

      if (!this.setFxaaShader(Shaders.configAntialiasingLevel)) {
         Shaders.configAntialiasingLevel = 0;
      }

      if (this.mc.currentScreen != null && this.mc.currentScreen.getClass() == GuiChat.class) {
         this.mc.displayGuiScreen((GuiScreen)new GuiChatOF((GuiChat)this.mc.currentScreen));
      }
   }

   public void stopUseShader() {
      if (this.theShaderGroup != null) {
         this.theShaderGroup.deleteShaderGroup();
      }

      this.theShaderGroup = null;
      this.shaderIndex = shaderCount;
   }

   private boolean isDrawBlockOutline() {
      if (!this.drawBlockOutline) {
         return false;
      } else {
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         boolean flag = renderViewEntity instanceof EntityPlayer && !this.mc.gameSettings.hideGUI;
         if (flag
            && !((EntityPlayer)renderViewEntity).capabilities.allowEdit
            && (!ClientManagers.moduleManager.blockOverlay.isToggled() || ClientManagers.moduleManager.blockOverlay.isToggled() && !ClientManagers.moduleManager.blockOverlay.inAdventureMode.getValue())) {
            ItemStack currentEquippedItem = ((EntityPlayer)renderViewEntity).getCurrentEquippedItem();
            if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
               BlockPos blockPos = this.mc.objectMouseOver.getBlockPos();
               IBlockState blockState = this.mc.theWorld.getBlockState(blockPos);
               Block block = blockState.getBlock();
               if (this.mc.playerController.getCurrentGameType() == WorldSettings.GameType.SPECTATOR) {
                  flag = blockState.getBlock().hasTileEntity() && this.mc.theWorld.getTileEntity(blockPos) instanceof IInventory;
               } else {
                  flag = currentEquippedItem != null && (currentEquippedItem.canDestroy(block) || currentEquippedItem.canPlaceOn(block));
               }
            }
         }

         return flag;
      }
   }

   public boolean setFxaaShader(int n) {
      if (!OpenGlHelper.isFramebufferEnabled()) {
         return false;
      } else if (this.theShaderGroup != null && this.theShaderGroup != this.fxaaShaders[2] && this.theShaderGroup != this.fxaaShaders[4]) {
         return true;
      } else if (n != 2 && n != 4) {
         if (this.theShaderGroup == null) {
            return true;
         } else {
            this.theShaderGroup.deleteShaderGroup();
            this.theShaderGroup = null;
            return true;
         }
      } else if (this.theShaderGroup != null && this.theShaderGroup == this.fxaaShaders[n]) {
         return true;
      } else if (this.mc.theWorld == null) {
         return true;
      } else {
         this.loadShader(new ResourceLocation("shaders/post/fxaa_of_" + n + "x.json"));
         this.fxaaShaders[n] = this.theShaderGroup;
         return this.useShader;
      }
   }

   private void loadShader(ResourceLocation location) {
      if (OpenGlHelper.isFramebufferEnabled()) {
         try {
            this.theShaderGroup = new ShaderGroup(this.mc.getTextureManager(), this.resourceManager, this.mc.getFramebuffer(), location);
            this.theShaderGroup.createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
            this.useShader = true;
         } catch (IOException e) {
            logger.warn("Failed to load shader: " + location, e);
            this.shaderIndex = shaderCount;
            this.useShader = false;
         } catch (JsonSyntaxException e2) {
            logger.warn("Failed to load shader: " + location, e2);
            this.shaderIndex = shaderCount;
            this.useShader = false;
         }
      }
   }

   public void updateRenderer() {
      if (OpenGlHelper.shadersSupported && ShaderLinkHelper.getStaticShaderLinkHelper() == null) {
         ShaderLinkHelper.setNewStaticShaderLinkHelper();
      }

      this.updateFovModifierHand();
      this.updateTorchFlicker();
      this.fogColor2 = this.fogColor1;
      this.thirdPersonDistanceTemp = this.thirdPersonDistance;
      if (this.mc.gameSettings.smoothCamera) {
         float f = this.mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
         float f2 = f * f * f * 8.0F;
         this.smoothCamFilterX = this.mouseFilterXAxis.smooth(this.smoothCamYaw, 0.05F * f2);
         this.smoothCamFilterY = this.mouseFilterYAxis.smooth(this.smoothCamPitch, 0.05F * f2);
         this.smoothCamPartialTicks = 0.0F;
         this.smoothCamYaw = 0.0F;
         this.smoothCamPitch = 0.0F;
      } else {
         this.smoothCamFilterX = 0.0F;
         this.smoothCamFilterY = 0.0F;
         this.mouseFilterXAxis.reset();
         this.mouseFilterYAxis.reset();
      }

      if (this.mc.getRenderViewEntity() == null) {
         EntityPlayerSP thePlayer = this.mc.thePlayer;
         this.mc.setRenderViewEntity((Entity)thePlayer);
      }

      Entity renderViewEntity = this.mc.getRenderViewEntity();
      double posX = renderViewEntity.posX;
      double d = renderViewEntity.posY + (double)renderViewEntity.getEyeHeight();
      double posZ = renderViewEntity.posZ;
      float lightBrightness = this.mc.theWorld.getLightBrightness(MathHelper.floor_double(posX), MathHelper.floor_double(d), MathHelper.floor_double(posZ));
      float f3 = (float)this.mc.gameSettings.renderDistanceChunks / 16.0F;
      f3 = MathHelper.clamp_float(f3, 0.0F, 1.0F);
      float f4 = lightBrightness * (1.0F - f3) + f3;
      this.fogColor1 = this.fogColor1 + (f4 - this.fogColor1) * 0.1F;
      this.rendererUpdateCount++;
      this.itemRenderer.updateEquippedItem();
      this.addRainParticles();
      this.bossColorModifierPrev = this.bossColorModifier;
      if (BossStatus.hasColorModifier) {
         this.bossColorModifier += 0.05F;
         if (this.bossColorModifier > 1.0F) {
            this.bossColorModifier = 1.0F;
         }

         BossStatus.hasColorModifier = false;
      } else if (this.bossColorModifier > 0.0F) {
         this.bossColorModifier -= 0.0125F;
      }
   }

   private void addRainParticles() {
      float rainStrength = this.mc.theWorld.getRainStrength(1.0F);
      if (!Config.isRainFancy()) {
         rainStrength /= 2.0F;
      }

      if (rainStrength != 0.0F && Config.isRainSplash()) {
         this.random.setSeed((long)this.rendererUpdateCount * 312987231L);
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         WorldClient theWorld = this.mc.theWorld;
         BlockPos pos = new BlockPos(renderViewEntity);
         byte b = 10;
         double d = 0.0;
         double d2 = 0.0;
         double d3 = 0.0;
         int n = 0;
         int n2 = (int)(100.0F * rainStrength * rainStrength);
         if (this.mc.gameSettings.particleSetting == 1) {
            n2 >>= 1;
         } else if (this.mc.gameSettings.particleSetting == 2) {
            n2 = 0;
         }

         for (int i = 0; i < n2; i++) {
            BlockPos precipitationHeight = theWorld.getPrecipitationHeight(
               pos.add(this.random.nextInt(b) - this.random.nextInt(b), 0, this.random.nextInt(b) - this.random.nextInt(b))
            );
            BiomeGenBase biomeGenForCoords = theWorld.getBiomeGenForCoords(precipitationHeight);
            BlockPos pos2 = precipitationHeight.down();
            Block block = theWorld.getBlockState(pos2).getBlock();
            if (precipitationHeight.getY() <= pos.getY() + b
               && precipitationHeight.getY() >= pos.getY() - b
               && (biomeGenForCoords.canSpawnLightningBolt() && !ClientManagers.moduleManager.ambiance.isToggled() || ClientManagers.moduleManager.ambiance.rain.getValue() > 0.0)
               && (biomeGenForCoords.getFloatTemperature(precipitationHeight) >= 0.15F && !ClientManagers.moduleManager.ambiance.isToggled() || !ClientManagers.moduleManager.ambiance.snow.getValue())) {
               double d4 = this.random.nextDouble();
               double d5 = this.random.nextDouble();
               if (block.getMaterial() == Material.lava) {
                  this.mc
                     .theWorld
                     .spawnParticle(
                        EnumParticleTypes.SMOKE_NORMAL,
                        (double)precipitationHeight.getX() + d4,
                        (double)((float)precipitationHeight.getY() + 0.1F) - block.getBlockBoundsMinY(),
                        (double)precipitationHeight.getZ() + d5,
                        0.0,
                        0.0,
                        0.0,
                        new int[0]
                     );
               } else if (block.getMaterial() != Material.air) {
                  block.setBlockBoundsBasedOnState(theWorld, pos2.getX(), pos2.getY(), pos2.getZ());
                  if (this.random.nextInt(++n) == 0) {
                     d = (double)pos2.getX() + d4;
                     d2 = (double)((float)pos2.getY() + 0.1F) + block.getBlockBoundsMaxY() - 1.0;
                     d3 = (double)pos2.getZ() + d5;
                  }

                  this.mc
                     .theWorld
                     .spawnParticle(
                        EnumParticleTypes.WATER_DROP,
                        (double)pos2.getX() + d4,
                        (double)((float)pos2.getY() + 0.1F) + block.getBlockBoundsMaxY(),
                        (double)pos2.getZ() + d5,
                        0.0,
                        0.0,
                        0.0,
                        new int[0]
                     );
               }
            }
         }

         if (n > 0 && this.random.nextInt(3) < this.rainSoundCounter++) {
            this.rainSoundCounter = 0;
            if (d2 > (double)(pos.getY() + 1) && theWorld.getPrecipitationHeightY(pos.getX(), pos.getZ()) > MathHelper.floor_float((float)pos.getY())) {
               this.mc.theWorld.playSound(d, d2, d3, "ambient.weather.rain", 0.1F, 0.5F, false);
            } else {
               this.mc.theWorld.playSound(d, d2, d3, "ambient.weather.rain", 0.2F, 1.0F, false);
            }
         }
      }
   }

public static class ValueCallable implements Callable {
      public final int mouseX;
      public final int mouseY;
      public final EntityRenderer entityRenderer;

      public String getMouseLocation() throws Exception {
         return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", this.mouseX, this.mouseY, Mouse.getX(), Mouse.getY());
      }

      @Override
      public Object call() throws Exception {
         return this.getMouseLocation();
      }

      public ValueCallable(EntityRenderer entityRenderer2, int n, int n2) {
         this.entityRenderer = entityRenderer2;
         this.mouseX = n;
         this.mouseY = n2;
      }
   }

   public static class ValueCallable2 implements Callable {
      public final ScaledResolution scaledResolution;
      public final EntityRenderer entityRenderer;

      public ValueCallable2(EntityRenderer entityRenderer2, ScaledResolution sr) {
         this.entityRenderer = entityRenderer2;
         this.scaledResolution = sr;
      }

      public String getScreenSize() throws Exception {
         return String.format(
            "Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d",
            this.scaledResolution.getScaledWidth(),
            this.scaledResolution.getScaledHeight(),
            EntityRenderer.accessMc(this.entityRenderer).displayWidth,
            EntityRenderer.accessMc(this.entityRenderer).displayHeight,
            this.scaledResolution.getScaleFactor()
         );
      }

      @Override
      public Object call() throws Exception {
         return this.getScreenSize();
      }
   }
}
