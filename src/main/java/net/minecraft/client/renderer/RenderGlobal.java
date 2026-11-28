package net.minecraft.client.renderer;

import java.util.concurrent.Callable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonSyntaxException;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.augustus.modules.Module;
import net.augustus.modules.render.ItemESP;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.RenderEntitiesListener;
import net.optifine.SmartAnimations;
import net.augustus.settings.options.EspRenderModeOption;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.IRenderChunkFactory;
import net.minecraft.client.renderer.chunk.ListChunkFactory;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.chunk.VboChunkFactory;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.RenderItemFrame;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemRecord;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Matrix4f;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vector3d;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderServer;
import net.optifine.CustomColors;
import net.optifine.CustomSky;
import net.optifine.DynamicLights;
import net.optifine.Lagometer;
import net.optifine.RandomEntities;
import net.optifine.config.Config;
import net.optifine.model.BlockModelUtils;
import net.optifine.render.ChunkVisibility;
import net.optifine.render.CloudRenderer;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;
import net.optifine.shaders.ShadowUtils;
import net.optifine.shaders.gui.GuiShaderOptions;
import net.optifine.util.RenderChunkUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class RenderGlobal implements IWorldAccess, IResourceManagerReloadListener {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final ResourceLocation locationMoonPhasesPng = new ResourceLocation("textures/environment/moon_phases.png");
   private static final ResourceLocation locationSunPng = new ResourceLocation("textures/environment/sun.png");
   private static final ResourceLocation locationCloudsPng = new ResourceLocation("textures/environment/clouds.png");
   private static final ResourceLocation locationEndSkyPng = new ResourceLocation("textures/environment/end_sky.png");
   private static final ResourceLocation locationForcefieldPng = new ResourceLocation("textures/misc/forcefield.png");
   public final Minecraft mc;
   private final TextureManager renderEngine;
   public final RenderManager renderManager;
   private WorldClient theWorld;
   private ObjectLinkedOpenHashSet chunksToUpdate = new ObjectLinkedOpenHashSet();
   private List renderInfos = Lists.newArrayListWithCapacity(69696);
   private final ObjectLinkedOpenHashSet setTileEntities = new ObjectLinkedOpenHashSet();
   public ViewFrustum viewFrustum;
   private int starGLCallList = -1;
   private int glSkyList = -1;
   private int glSkyList2 = -1;
   private VertexFormat vertexBufferFormat;
   private VertexBuffer starVBO;
   private VertexBuffer skyVBO;
   private VertexBuffer sky2VBO;
   private int cloudTickCounter;
   public final Map damagedBlocks = Maps.newHashMap();
   private final Map mapSoundPositions = Maps.newHashMap();
   private final TextureAtlasSprite[] destroyBlockIcons = new TextureAtlasSprite[10];
   private Framebuffer entityOutlineFramebuffer;
   private ShaderGroup entityOutlineShader;
   private double frustumUpdatePosX = 5.0E-324;
   private double frustumUpdatePosY = 5.0E-324;
   private double frustumUpdatePosZ = 5.0E-324;
   private int frustumUpdatePosChunkX = -2147483648;
   private int frustumUpdatePosChunkY = -2147483648;
   private int frustumUpdatePosChunkZ = -2147483648;
   private double lastViewEntityX = 5.0E-324;
   private double lastViewEntityY = 5.0E-324;
   private double lastViewEntityZ = 5.0E-324;
   private double lastViewEntityPitch = 5.0E-324;
   private double lastViewEntityYaw = 5.0E-324;
   private final ChunkRenderDispatcher renderDispatcher = new ChunkRenderDispatcher();
   private ChunkRenderContainer renderContainer;
   private int renderDistanceChunks = -1;
   private int renderEntitiesStartupCounter = 2;
   private int countEntitiesTotal;
   public int countEntitiesRendered;
   private int countEntitiesHidden;
   private boolean debugFixTerrainFrustum = false;
   private ClippingHelper debugFixedClippingHelper;
   private final Vector4f[] debugTerrainMatrix = new Vector4f[8];
   private final Vector3d debugTerrainFrustumPosition = new Vector3d();
   private boolean vboEnabled = false;
   public IRenderChunkFactory renderChunkFactory;
   private double prevRenderSortX;
   private double prevRenderSortY;
   private double prevRenderSortZ;
   public boolean displayListEntitiesDirty = true;
   private CloudRenderer cloudRenderer;
   public Entity renderedEntity;
   public Set chunksToResortTransparency = new LinkedHashSet();
   public Set chunksToUpdateForced = new LinkedHashSet();
   private Deque visibilityDeque = new ArrayDeque();
   private List renderInfosEntities = new ArrayList(1024);
   private List renderInfosTileEntities = new ArrayList(1024);
   private List renderInfosNormal = new ArrayList(1024);
   private List renderInfosEntitiesNormal = new ArrayList(1024);
   private List renderInfosTileEntitiesNormal = new ArrayList(1024);
   private List renderInfosShadow = new ArrayList(1024);
   private List renderInfosEntitiesShadow = new ArrayList(1024);
   private List renderInfosTileEntitiesShadow = new ArrayList(1024);
   private int renderDistance = 0;
   private int renderDistanceSq = 0;
   private static final Set SET_ALL_FACINGS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(EnumFacing.VALUES)));
   private int countTileEntitiesRendered;
   private IChunkProvider worldChunkProvider = null;
   private LongHashMap worldChunkProviderMap = null;
   private int countLoadedChunksPrev = 0;
   private RenderEnv renderEnv = new RenderEnv(Blocks.air.getDefaultState(), new BlockPos(0, 0, 0));
   public boolean renderOverlayDamaged = false;
   public boolean renderOverlayEyes = false;
   private boolean firstWorldLoad = false;
   private static int renderEntitiesCounter = 0;

   public int getCountActiveRenderers() {
      return this.renderInfos.size();
   }

   public void deleteAllDisplayLists() {
   }

   private void cleanupDamagedBlocks(Iterator it) {
      while (it.hasNext()) {
         int n = ((DestroyBlockProgress)it.next()).getCreationCloudUpdateTick();
         if (this.cloudTickCounter - n > 400) {
            it.remove();
         }
      }
   }

   public void renderEntityOutlineFramebuffer() {
      if (this.isRenderEntityOutlines()) {
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 1, 1, 1);
         this.entityOutlineFramebuffer.framebufferRenderExt(this.mc.displayWidth, this.mc.displayHeight, false);
         GlStateManager.disableBlend();
      }
   }

   public void sendBlockBreakProgress(int n, BlockPos pos, int n2) {
      if (n2 >= 0 && n2 < 10) {
         DestroyBlockProgress destroyBlockProgress = (DestroyBlockProgress)this.damagedBlocks.get(n);
         if (destroyBlockProgress == null || destroyBlockProgress.getPosition().getX() != pos.getX() || destroyBlockProgress.getPosition().getY() != pos.getY() || destroyBlockProgress.getPosition().getZ() != pos.getZ()) {
            destroyBlockProgress = new DestroyBlockProgress(n, pos);
            this.damagedBlocks.put(n, destroyBlockProgress);
         }

         destroyBlockProgress.setPartialBlockDamage(n2);
         destroyBlockProgress.setCloudUpdateTick(this.cloudTickCounter);
      } else {
         this.damagedBlocks.remove(n);
      }
   }

   public RenderChunk getRenderChunk(BlockPos pos) {
      return this.viewFrustum.getRenderChunk(pos);
   }

   public void spawnParticle(int n, boolean flag, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
      try {
         this.spawnEntityFX(n, flag, d, d2, d3, d4, d5, d6, ints);
      } catch (Throwable e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Exception while adding particle");
         CrashReportCategory crashReportCategory = crashReport.makeCategory("Particle being added");
         crashReportCategory.addCrashSection("ID", n);
         if (ints != null) {
            crashReportCategory.addCrashSection("Parameters", ints);
         }

         crashReportCategory.addCrashSectionCallable("Position", new RenderGlobal.ValueCallable(this, d, d2, d3));
         throw new ReportedException(crashReport);
      }
   }

   public void renderEntities(Entity entity, ICamera camera, float f) {
      byte b = 0;
      if (this.renderEntitiesStartupCounter > 0) {
         if (b > 0) {
            return;
         }

         this.renderEntitiesStartupCounter--;
      } else {
         double d = entity.prevPosX + (entity.posX - entity.prevPosX) * (double)f;
         double d2 = entity.prevPosY + (entity.posY - entity.prevPosY) * (double)f;
         double d3 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)f;
         this.theWorld.theProfiler.startSection("prepare");
         TileEntityRendererDispatcher.instance
            .cacheActiveRenderInfo(this.theWorld, this.mc.getTextureManager(), this.mc.fontRendererObj, this.mc.getRenderViewEntity(), f);
         this.renderManager
            .cacheActiveRenderInfo(this.theWorld, this.mc.fontRendererObj, this.mc.getRenderViewEntity(), this.mc.pointedEntity, this.mc.gameSettings, f);
         renderEntitiesCounter++;
         if (b == 0) {
            this.countEntitiesTotal = 0;
            this.countEntitiesRendered = 0;
            this.countEntitiesHidden = 0;
            this.countTileEntitiesRendered = 0;
         }

         Entity renderViewEntity = this.mc.getRenderViewEntity();
         double d4 = renderViewEntity.lastTickPosX + (renderViewEntity.posX - renderViewEntity.lastTickPosX) * (double)f;
         double d5 = renderViewEntity.lastTickPosY + (renderViewEntity.posY - renderViewEntity.lastTickPosY) * (double)f;
         double d6 = renderViewEntity.lastTickPosZ + (renderViewEntity.posZ - renderViewEntity.lastTickPosZ) * (double)f;
         TileEntityRendererDispatcher.staticPlayerX = d4;
         TileEntityRendererDispatcher.staticPlayerY = d5;
         TileEntityRendererDispatcher.staticPlayerZ = d6;
         this.renderManager.setRenderPosition(d4, d5, d6);
         this.mc.entityRenderer.enableLightmap();
         this.theWorld.theProfiler.endStartSection("global");
         List loadedEntityList = this.theWorld.getLoadedEntityList();
         if (b == 0) {
            this.countEntitiesTotal = loadedEntityList.size();
         }

         if (Config.isFogOff() && this.mc.entityRenderer.fogStandard) {
            GlStateManager.disableFog();
         }

for (int i = 0; i < this.theWorld.weatherEffects.size(); i++) {
            Entity entity2 = (Entity)this.theWorld.weatherEffects.get(i);
            this.countEntitiesRendered++;
            if (entity2.isInRangeToRender3d(d, d2, d3)) {
               this.renderManager.renderEntitySimple(entity2, f);
            }
         }

         if (this.isRenderEntityOutlines()) {
            GlStateManager.depthFunc(519);
            GlStateManager.disableFog();
            this.entityOutlineFramebuffer.framebufferClear();
            this.entityOutlineFramebuffer.bindFramebuffer(false);
            this.theWorld.theProfiler.endStartSection("entityOutlines");
            RenderHelper.disableStandardItemLighting();
            this.renderManager.setRenderOutlines(true);

            for (int j = 0; j < loadedEntityList.size(); j++) {
               Entity entity3 = (Entity)loadedEntityList.get(j);
               boolean flag2 = this.mc.getRenderViewEntity() instanceof EntityLivingBase && ((EntityLivingBase)this.mc.getRenderViewEntity()).isPlayerSleeping();
               boolean inRange = entity3.isInRangeToRender3d(d, d2, d3)
                  && (entity3.ignoreFrustumCheck || camera.isBoundingBoxInFrustum(entity3.getEntityBoundingBox()) || entity3.riddenByEntity == this.mc.thePlayer);
               boolean isEspTarget = ClientManagers.moduleManager.esp.isToggled()
                  && EspRenderModeOption.VANILLA.isSelected()
                  && entity3 instanceof EntityLivingBase
                  && ClientManagers.moduleManager.esp.isValidTargetEntity((EntityLivingBase)entity3);
               boolean isItemEspTarget = ClientManagers.moduleManager.itemESP.isToggled()
                  && ClientManagers.moduleManager.itemESP.mode.getSelectedValue().equals("Vanilla")
                  && entity3 instanceof EntityItem
                  && ItemESP.isItemWhitelisted(((EntityItem)entity3).getEntityItem().getItem());
               boolean flag3 = inRange && (isEspTarget || isItemEspTarget);

               boolean flag4 = flag3;
               if ((entity3 != this.mc.getRenderViewEntity() || this.mc.gameSettings.thirdPersonView != 0 || flag2) && flag4) {
                  this.renderManager.renderEntitySimple(entity3, f);
               }
            }

            this.renderManager.setRenderOutlines(false);
            RenderHelper.enableStandardItemLighting();
            GlStateManager.depthMask(false);
            this.entityOutlineShader.loadShaderGroup(f);
            GlStateManager.enableLighting();
            GlStateManager.depthMask(true);
            this.mc.getFramebuffer().bindFramebuffer(false);
            GlStateManager.enableFog();
            GlStateManager.enableBlend();
            GlStateManager.enableColorMaterial();
            GlStateManager.depthFunc(515);
            GlStateManager.enableDepth();
            GlStateManager.enableAlpha();
         }

         this.theWorld.theProfiler.endStartSection("entities");
         boolean isShaderPackLoaded = Config.isShaderPackLoaded();
         if (isShaderPackLoaded) {
            Shaders.beginEntities();
         }

         RenderItemFrame.updateItemRenderDistance();
         boolean fancyGraphics = this.mc.gameSettings.fancyGraphics;
         this.mc.gameSettings.fancyGraphics = Config.isDroppedItemsFancy();
         if (Shaders.isShadowPass && !this.mc.thePlayer.isSpectator()) {
            boolean flag5 = true;
         } else {
            boolean flag6 = false;
         }

         RenderEntitiesListener.onRenderEntities(camera, d, d2, d3, f, isShaderPackLoaded);

         for (int k = 0; k < this.renderInfosEntities.size(); k++) {
            RenderGlobal.ContainerLocalRenderInformation information = (RenderGlobal.ContainerLocalRenderInformation)this.renderInfosEntities.get(k);
            ClassInheritanceMultiMap classInheritanceMultiMap = information.renderChunk.getChunk().getEntityLists()[information.renderChunk.getPosition().getY() / 16];
            if (!classInheritanceMultiMap.isEmpty()) {
               for (Entity entity5 : (Iterable<Entity>)(classInheritanceMultiMap)) {
                  boolean flag7 = this.renderManager.shouldRender(entity5, camera, d, d2, d3) || entity5.riddenByEntity == this.mc.thePlayer;
                  if (flag7) {
                     boolean flag8 = this.mc.getRenderViewEntity() instanceof EntityLivingBase
                        && ((EntityLivingBase)this.mc.getRenderViewEntity()).isPlayerSleeping();
                     if (entity5 == this.mc.getRenderViewEntity() && this.mc.gameSettings.thirdPersonView == 0 && !flag8
                        || !(entity5.posY < 0.0)
                           && !(entity5.posY >= 256.0)
                           && !this.theWorld
                              .isBlockLoaded(MathHelper.floor_double(entity5.posX), MathHelper.floor_double(entity5.posY), MathHelper.floor_double(entity5.posZ))) {
                        continue;
                     }

                     this.countEntitiesRendered++;
                     this.renderedEntity = entity5;
                     if (isShaderPackLoaded) {
                        Shaders.nextEntity(entity5);
                     }

                     this.renderManager.renderEntitySimple(entity5, f);
                     this.renderedEntity = null;
                  }

                  if (!flag7 && entity5 instanceof EntityWitherSkull) {
                     this.renderedEntity = entity5;
                     if (isShaderPackLoaded) {
                        Shaders.nextEntity(entity5);
                     }

                     this.mc.getRenderManager().renderWitherSkull(entity5, f);
                     this.renderedEntity = null;
                  }
               }
            }
         }

         this.mc.gameSettings.fancyGraphics = fancyGraphics;
         FontRenderer fontRenderer = TileEntityRendererDispatcher.instance.getFontRenderer();
         if (isShaderPackLoaded) {
            Shaders.endEntities();
            Shaders.beginBlockEntities();
         }

         this.theWorld.theProfiler.endStartSection("blockentities");
         RenderHelper.enableStandardItemLighting();

         for (int m = 0; m < this.renderInfosTileEntities.size(); m++) {
            List tileEntities = ((RenderGlobal.ContainerLocalRenderInformation)this.renderInfosTileEntities.get(m)).renderChunk.getCompiledChunk().getTileEntities();
            if (!tileEntities.isEmpty()) {
               for (TileEntity tileEntity : (Iterable<TileEntity>)(tileEntities)) {
                  if (!false) {
                     TileEntityRendererDispatcher.instance.renderTileEntity(tileEntity, f, -1);
                     this.countTileEntitiesRendered++;
                  }
               }
            }
         }

         ObjectLinkedOpenHashSet setTileEntities2 = this.setTileEntities;
         synchronized (this.setTileEntities) {
            ObjectListIterator it = this.setTileEntities.iterator();

            while (it.hasNext()) {
               Object obj = it.next();
               if (isShaderPackLoaded) {
                  Shaders.nextBlockEntity((TileEntity)obj);
               }

               TileEntityRendererDispatcher.instance.renderTileEntity((TileEntity)obj, f, -1);
            }
         }

         this.renderOverlayDamaged = true;
         this.preRenderDamagedBlocks();

         for (Object obj2 : this.damagedBlocks.values()) {
            BlockPos position = ((DestroyBlockProgress)obj2).getPosition();
            TileEntity tileEntity2 = this.theWorld.getTileEntity(position);
            if (tileEntity2 instanceof TileEntityChest) {
               TileEntityChest tileEntityChest = (TileEntityChest)tileEntity2;
               if (tileEntityChest.adjacentChestXNeg != null) {
                  position = position.offset(EnumFacing.WEST);
                  tileEntity2 = this.theWorld.getTileEntity(position);
               } else if (tileEntityChest.adjacentChestZNeg != null) {
                  position = position.offset(EnumFacing.NORTH);
                  tileEntity2 = this.theWorld.getTileEntity(position);
               }
            }

            Block block = this.theWorld.getBlockState(position).getBlock();
            if (tileEntity2 != null
               && (block instanceof BlockChest || block instanceof BlockEnderChest || block instanceof BlockSign || block instanceof BlockSkull)) {
               if (isShaderPackLoaded) {
                  Shaders.nextBlockEntity(tileEntity2);
               }

               TileEntityRendererDispatcher.instance.renderTileEntity(tileEntity2, f, ((DestroyBlockProgress)obj2).getPartialBlockDamage());
            }
         }

         this.postRenderDamagedBlocks();
         this.renderOverlayDamaged = false;
         if (isShaderPackLoaded) {
            Shaders.endBlockEntities();
         }

         renderEntitiesCounter--;
         this.mc.entityRenderer.disableLightmap();
         this.mc.mcProfiler.endSection();
      }
   }

   public void renderClouds(float f, int n) {
      if (!Config.isCloudsOff() && this.mc.theWorld.provider.isSurfaceWorld()) {
         if (Config.isShaderPackLoaded()) {
            Shaders.beginClouds();
         }

         if (Config.isCloudsFancy()) {
            this.renderCloudsFancy(f, n);
         } else {
            float f2 = 0.0F;
            GlStateManager.disableCull();
            float f3 = (float)(
               this.mc.getRenderViewEntity().lastTickPosY + (this.mc.getRenderViewEntity().posY - this.mc.getRenderViewEntity().lastTickPosY) * (double)f2
            );
            Tessellator instance = Tessellator.getInstance();
            WorldRenderer worldRenderer = instance.getWorldRenderer();
            this.renderEngine.bindTexture(locationCloudsPng);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            Vec3 cloudColour = this.theWorld.getCloudColour(f2);
            float xCoord = (float)cloudColour.xCoord;
            float yCoord = (float)cloudColour.yCoord;
            float zCoord = (float)cloudColour.zCoord;
            this.cloudRenderer.prepareToRender(false, this.cloudTickCounter, f, cloudColour);
            if (this.cloudRenderer.shouldUpdateGlList()) {
               this.cloudRenderer.startUpdateGlList();
               if (n != 2) {
                  float f4 = (xCoord * 30.0F + yCoord * 59.0F + zCoord * 11.0F) / 100.0F;
                  float f5 = (xCoord * 30.0F + yCoord * 70.0F) / 100.0F;
                  float f6 = (xCoord * 30.0F + zCoord * 70.0F) / 100.0F;
                  xCoord = f4;
                  yCoord = f5;
                  zCoord = f6;
               }

               float f7 = 4.8828125E-4F;
               double d = (double)((float)this.cloudTickCounter + f2);
               double d2 = this.mc.getRenderViewEntity().prevPosX
                  + (this.mc.getRenderViewEntity().posX - this.mc.getRenderViewEntity().prevPosX) * (double)f2
                  + d * 0.029999999329447746;
               double d3 = this.mc.getRenderViewEntity().prevPosZ + (this.mc.getRenderViewEntity().posZ - this.mc.getRenderViewEntity().prevPosZ) * (double)f2;
               int n2 = MathHelper.floor_double(d2 / 2048.0);
               int n3 = MathHelper.floor_double(d3 / 2048.0);
               d2 -= (double)(n2 * 2048);
               d3 -= (double)(n3 * 2048);
               float f8 = this.theWorld.provider.getCloudHeight() - f3 + 0.33F;
               f8 += this.mc.gameSettings.ofCloudsHeight * 128.0F;
               float f9 = (float)(d2 * 4.8828125E-4);
               float f10 = (float)(d3 * 4.8828125E-4);
               worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);

               for (int i = -256; i < 256; i += 32) {
                  for (int j = -256; j < 256; j += 32) {
                     worldRenderer.pos((double)(i + 0), (double)f8, (double)(j + 32))
                        .tex((double)((float)(i + 0) * 4.8828125E-4F + f9), (double)((float)(j + 32) * 4.8828125E-4F + f10))
                        .color(xCoord, yCoord, zCoord, 0.8F)
                        .endVertex();
                     worldRenderer.pos((double)(i + 32), (double)f8, (double)(j + 32))
                        .tex((double)((float)(i + 32) * 4.8828125E-4F + f9), (double)((float)(j + 32) * 4.8828125E-4F + f10))
                        .color(xCoord, yCoord, zCoord, 0.8F)
                        .endVertex();
                     worldRenderer.pos((double)(i + 32), (double)f8, (double)(j + 0))
                        .tex((double)((float)(i + 32) * 4.8828125E-4F + f9), (double)((float)(j + 0) * 4.8828125E-4F + f10))
                        .color(xCoord, yCoord, zCoord, 0.8F)
                        .endVertex();
                     worldRenderer.pos((double)(i + 0), (double)f8, (double)(j + 0))
                        .tex((double)((float)(i + 0) * 4.8828125E-4F + f9), (double)((float)(j + 0) * 4.8828125E-4F + f10))
                        .color(xCoord, yCoord, zCoord, 0.8F)
                        .endVertex();
                  }
               }

               instance.draw();
               this.cloudRenderer.endUpdateGlList();
            }

            this.cloudRenderer.renderGlList();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableBlend();
            GlStateManager.enableCull();
         }

         if (Config.isShaderPackLoaded()) {
            Shaders.endClouds();
         }
      }
   }

   public String getDebugInfoRenders() {
      int length = this.viewFrustum.renderChunks.length;
      int n = 0;

      for (RenderGlobal.ContainerLocalRenderInformation information : (Iterable<RenderGlobal.ContainerLocalRenderInformation>)(this.renderInfos)) {
         CompiledChunk compiledChunk = information.renderChunk.compiledChunk;
         if (compiledChunk != CompiledChunk.DUMMY && !compiledChunk.isEmpty()) {
            n++;
         }
      }

      return String.format("C: %d/%d %sD: %d, %s", n, length, this.mc.renderChunksMany ? "(s) " : "", this.renderDistanceChunks, this.renderDispatcher.getDebugInfo());
   }

   public int getCountRenderers() {
      return this.viewFrustum.renderChunks.length;
   }

   public int getCountLoadedChunks() {
      if (this.theWorld == null) {
         return 0;
      } else {
         IChunkProvider chunkProvider = this.theWorld.getChunkProvider();
         if (chunkProvider == null) {
            return 0;
         } else {
            if (chunkProvider != this.worldChunkProvider) {
               this.worldChunkProvider = chunkProvider;
               if (chunkProvider instanceof ChunkProviderClient) {
                  this.worldChunkProviderMap = ((ChunkProviderClient)chunkProvider).chunkMapping;
               } else if (chunkProvider instanceof ChunkProviderServer) {
                  this.worldChunkProviderMap = ((ChunkProviderServer)chunkProvider).id2ChunkMap;
               }
            }

            return this.worldChunkProviderMap == null ? 0 : this.worldChunkProviderMap.getNumHashElements();
         }
      }
   }

   public static void drawSelectionBoundingBox(AxisAlignedBB box) {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(3, DefaultVertexFormats.POSITION);
      worldRenderer.pos(box.minX, box.minY, box.minZ).endVertex();
      worldRenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
      worldRenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
      worldRenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
      worldRenderer.pos(box.minX, box.minY, box.minZ).endVertex();
      instance.draw();
      worldRenderer.begin(3, DefaultVertexFormats.POSITION);
      worldRenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
      worldRenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
      worldRenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
      worldRenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
      worldRenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
      instance.draw();
      worldRenderer.begin(1, DefaultVertexFormats.POSITION);
      worldRenderer.pos(box.minX, box.minY, box.minZ).endVertex();
      worldRenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
      worldRenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
      worldRenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
      worldRenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
      worldRenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
      worldRenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
      worldRenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
      instance.draw();
   }

   private void postRenderDamagedBlocks() {
      GlStateManager.disableAlpha();
      GlStateManager.doPolygonOffset(0.0F, 0.0F);
      GlStateManager.disablePolygonOffset();
      GlStateManager.enableAlpha();
      GlStateManager.depthMask(true);
      GlStateManager.popMatrix();
      if (Config.isShaderPackLoaded()) {
         ShadersRender.endBlockDamage();
      }
   }

   private static double distanceSq(double d, double d2, double d3, double d4, double d5, double d6) {
      return (d - d4) * (d - d4) + (d2 - d5) * (d2 - d5) + (d3 - d6) * (d3 - d6);
   }

   public void onEntityRemoved(Entity entity) {
      RandomEntities.entityUnloaded(entity, this.theWorld);
      if (Config.isDynamicLights()) {
         DynamicLights.entityRemoved(entity, this);
      }
   }

   private void renderBlockLayer(EnumWorldBlockLayer enumWorldBlockLayer) {
      this.mc.entityRenderer.enableLightmap();
      if (OpenGlHelper.useVbo()) {
         GL11.glEnableClientState(32884);
         OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
         GL11.glEnableClientState(32888);
         OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
         GL11.glEnableClientState(32888);
         OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
         GL11.glEnableClientState(32886);
      }

      if (Config.isShaderPackLoaded()) {
         ShadersRender.preRenderChunkLayer(enumWorldBlockLayer);
      }

      this.renderContainer.renderChunkLayer(enumWorldBlockLayer);
      if (Config.isShaderPackLoaded()) {
         ShadersRender.postRenderChunkLayer(enumWorldBlockLayer);
      }

      if (OpenGlHelper.useVbo()) {
         for (VertexFormatElement vertexFormatElement : (Iterable<VertexFormatElement>)(DefaultVertexFormats.BLOCK.getElements())) {
            VertexFormatElement.EnumUsage usage = vertexFormatElement.getUsage();
            int index = vertexFormatElement.getIndex();
            switch (usage) {
               case POSITION:
                  GL11.glDisableClientState(32884);
                  break;
               case UV:
                  OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + index);
                  GL11.glDisableClientState(32888);
                  OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                  break;
               case COLOR:
                  GL11.glDisableClientState(32886);
                  GlStateManager.resetColor();
            }
         }
      }

      this.mc.entityRenderer.disableLightmap();
   }

   private void renderCloudsFancy(float f, int n) {
      f = 0.0F;
      GlStateManager.disableCull();
      float f2 = (float)(this.mc.getRenderViewEntity().lastTickPosY + (this.mc.getRenderViewEntity().posY - this.mc.getRenderViewEntity().lastTickPosY) * (double)f);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      float f3 = 12.0F;
      float f4 = 4.0F;
      double d = (double)((float)this.cloudTickCounter + f);
      double d2 = (
            this.mc.getRenderViewEntity().prevPosX
               + (this.mc.getRenderViewEntity().posX - this.mc.getRenderViewEntity().prevPosX) * (double)f
               + d * 0.029999999329447746
         )
         / 12.0;
      double d3 = (this.mc.getRenderViewEntity().prevPosZ + (this.mc.getRenderViewEntity().posZ - this.mc.getRenderViewEntity().prevPosZ) * (double)f) / 12.0
         + 0.33000001311302185;
      float f5 = this.theWorld.provider.getCloudHeight() - f2 + 0.33F;
      f5 += this.mc.gameSettings.ofCloudsHeight * 128.0F;
      int n2 = MathHelper.floor_double(d2 / 2048.0);
      int n3 = MathHelper.floor_double(d3 / 2048.0);
      d2 -= (double)(n2 * 2048);
      d3 -= (double)(n3 * 2048);
      this.renderEngine.bindTexture(locationCloudsPng);
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      Vec3 cloudColour = this.theWorld.getCloudColour(f);
      float xCoord = (float)cloudColour.xCoord;
      float yCoord = (float)cloudColour.yCoord;
      float zCoord = (float)cloudColour.zCoord;
      this.cloudRenderer.prepareToRender(true, this.cloudTickCounter, f, cloudColour);
      if (n != 2) {
         float f6 = (xCoord * 30.0F + yCoord * 59.0F + zCoord * 11.0F) / 100.0F;
         float f7 = (xCoord * 30.0F + yCoord * 70.0F) / 100.0F;
         float f8 = (xCoord * 30.0F + zCoord * 70.0F) / 100.0F;
         xCoord = f6;
         yCoord = f7;
         zCoord = f8;
      }

      float f9 = xCoord * 0.9F;
      float f10 = yCoord * 0.9F;
      float f11 = zCoord * 0.9F;
      float f12 = xCoord * 0.7F;
      float f13 = yCoord * 0.7F;
      float f14 = zCoord * 0.7F;
      float f15 = xCoord * 0.8F;
      float f16 = yCoord * 0.8F;
      float f17 = zCoord * 0.8F;
      float f18 = 0.00390625F;
      float f19 = (float)MathHelper.floor_double(d2) * 0.00390625F;
      float f20 = (float)MathHelper.floor_double(d3) * 0.00390625F;
      float f21 = (float)(d2 - (double)MathHelper.floor_double(d2));
      float f22 = (float)(d3 - (double)MathHelper.floor_double(d3));
      float f23 = 9.765625E-4F;
      GlStateManager.scale(12.0F, 1.0F, 12.0F);

      for (int i = 0; i < 2; i++) {
         if (i == 0) {
            GlStateManager.colorMask(false, false, false, false);
         } else {
            switch (n) {
               case 0:
                  GlStateManager.colorMask(false, true, true, true);
                  break;
               case 1:
                  GlStateManager.colorMask(true, false, false, true);
                  break;
               case 2:
                  GlStateManager.colorMask(true, true, true, true);
            }
         }

         this.cloudRenderer.renderGlList();
      }

      if (this.cloudRenderer.shouldUpdateGlList()) {
         this.cloudRenderer.startUpdateGlList();

         for (int j = -3; j <= 4; j++) {
            for (int k = -3; k <= 4; k++) {
               worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
               float f24 = (float)(j * 8);
               float f25 = (float)(k * 8);
               float f26 = f24 - f21;
               float f27 = f25 - f22;
               if (f5 > -5.0F) {
                  worldRenderer.pos((double)(f26 + 0.0F), (double)(f5 + 0.0F), (double)(f27 + 8.0F))
                     .tex((double)((f24 + 0.0F) * 0.00390625F + f19), (double)((f25 + 8.0F) * 0.00390625F + f20))
                     .color(f12, f13, f14, 0.8F)
                     .normal(0.0F, -1.0F, 0.0F)
                     .endVertex();
                  worldRenderer.pos((double)(f26 + 8.0F), (double)(f5 + 0.0F), (double)(f27 + 8.0F))
                     .tex((double)((f24 + 8.0F) * 0.00390625F + f19), (double)((f25 + 8.0F) * 0.00390625F + f20))
                     .color(f12, f13, f14, 0.8F)
                     .normal(0.0F, -1.0F, 0.0F)
                     .endVertex();
                  worldRenderer.pos((double)(f26 + 8.0F), (double)(f5 + 0.0F), (double)(f27 + 0.0F))
                     .tex((double)((f24 + 8.0F) * 0.00390625F + f19), (double)((f25 + 0.0F) * 0.00390625F + f20))
                     .color(f12, f13, f14, 0.8F)
                     .normal(0.0F, -1.0F, 0.0F)
                     .endVertex();
                  worldRenderer.pos((double)(f26 + 0.0F), (double)(f5 + 0.0F), (double)(f27 + 0.0F))
                     .tex((double)((f24 + 0.0F) * 0.00390625F + f19), (double)((f25 + 0.0F) * 0.00390625F + f20))
                     .color(f12, f13, f14, 0.8F)
                     .normal(0.0F, -1.0F, 0.0F)
                     .endVertex();
               }

               if (f5 <= 5.0F) {
                  worldRenderer.pos((double)(f26 + 0.0F), (double)(f5 + 4.0F - 9.765625E-4F), (double)(f27 + 8.0F))
                     .tex((double)((f24 + 0.0F) * 0.00390625F + f19), (double)((f25 + 8.0F) * 0.00390625F + f20))
                     .color(xCoord, yCoord, zCoord, 0.8F)
                     .normal(0.0F, 1.0F, 0.0F)
                     .endVertex();
                  worldRenderer.pos((double)(f26 + 8.0F), (double)(f5 + 4.0F - 9.765625E-4F), (double)(f27 + 8.0F))
                     .tex((double)((f24 + 8.0F) * 0.00390625F + f19), (double)((f25 + 8.0F) * 0.00390625F + f20))
                     .color(xCoord, yCoord, zCoord, 0.8F)
                     .normal(0.0F, 1.0F, 0.0F)
                     .endVertex();
                  worldRenderer.pos((double)(f26 + 8.0F), (double)(f5 + 4.0F - 9.765625E-4F), (double)(f27 + 0.0F))
                     .tex((double)((f24 + 8.0F) * 0.00390625F + f19), (double)((f25 + 0.0F) * 0.00390625F + f20))
                     .color(xCoord, yCoord, zCoord, 0.8F)
                     .normal(0.0F, 1.0F, 0.0F)
                     .endVertex();
                  worldRenderer.pos((double)(f26 + 0.0F), (double)(f5 + 4.0F - 9.765625E-4F), (double)(f27 + 0.0F))
                     .tex((double)((f24 + 0.0F) * 0.00390625F + f19), (double)((f25 + 0.0F) * 0.00390625F + f20))
                     .color(xCoord, yCoord, zCoord, 0.8F)
                     .normal(0.0F, 1.0F, 0.0F)
                     .endVertex();
               }

               if (j > -1) {
                  for (int m = 0; m < 8; m++) {
                     worldRenderer.pos((double)(f26 + (float)m + 0.0F), (double)(f5 + 0.0F), (double)(f27 + 8.0F))
                        .tex((double)((f24 + (float)m + 0.5F) * 0.00390625F + f19), (double)((f25 + 8.0F) * 0.00390625F + f20))
                        .color(f9, f10, f11, 0.8F)
                        .normal(-1.0F, 0.0F, 0.0F)
                        .endVertex();
                     worldRenderer.pos((double)(f26 + (float)m + 0.0F), (double)(f5 + 4.0F), (double)(f27 + 8.0F))
                        .tex((double)((f24 + (float)m + 0.5F) * 0.00390625F + f19), (double)((f25 + 8.0F) * 0.00390625F + f20))
                        .color(f9, f10, f11, 0.8F)
                        .normal(-1.0F, 0.0F, 0.0F)
                        .endVertex();
                     worldRenderer.pos((double)(f26 + (float)m + 0.0F), (double)(f5 + 4.0F), (double)(f27 + 0.0F))
                        .tex((double)((f24 + (float)m + 0.5F) * 0.00390625F + f19), (double)((f25 + 0.0F) * 0.00390625F + f20))
                        .color(f9, f10, f11, 0.8F)
                        .normal(-1.0F, 0.0F, 0.0F)
                        .endVertex();
                     worldRenderer.pos((double)(f26 + (float)m + 0.0F), (double)(f5 + 0.0F), (double)(f27 + 0.0F))
                        .tex((double)((f24 + (float)m + 0.5F) * 0.00390625F + f19), (double)((f25 + 0.0F) * 0.00390625F + f20))
                        .color(f9, f10, f11, 0.8F)
                        .normal(-1.0F, 0.0F, 0.0F)
                        .endVertex();
                  }
               }

               if (j <= 1) {
                  for (int idx = 0; idx < 8; idx++) {
                     worldRenderer.pos((double)(f26 + (float)idx + 1.0F - 9.765625E-4F), (double)(f5 + 0.0F), (double)(f27 + 8.0F))
                        .tex((double)((f24 + (float)idx + 0.5F) * 0.00390625F + f19), (double)((f25 + 8.0F) * 0.00390625F + f20))
                        .color(f9, f10, f11, 0.8F)
                        .normal(1.0F, 0.0F, 0.0F)
                        .endVertex();
                     worldRenderer.pos((double)(f26 + (float)idx + 1.0F - 9.765625E-4F), (double)(f5 + 4.0F), (double)(f27 + 8.0F))
                        .tex((double)((f24 + (float)idx + 0.5F) * 0.00390625F + f19), (double)((f25 + 8.0F) * 0.00390625F + f20))
                        .color(f9, f10, f11, 0.8F)
                        .normal(1.0F, 0.0F, 0.0F)
                        .endVertex();
                     worldRenderer.pos((double)(f26 + (float)idx + 1.0F - 9.765625E-4F), (double)(f5 + 4.0F), (double)(f27 + 0.0F))
                        .tex((double)((f24 + (float)idx + 0.5F) * 0.00390625F + f19), (double)((f25 + 0.0F) * 0.00390625F + f20))
                        .color(f9, f10, f11, 0.8F)
                        .normal(1.0F, 0.0F, 0.0F)
                        .endVertex();
                     worldRenderer.pos((double)(f26 + (float)idx + 1.0F - 9.765625E-4F), (double)(f5 + 0.0F), (double)(f27 + 0.0F))
                        .tex((double)((f24 + (float)idx + 0.5F) * 0.00390625F + f19), (double)((f25 + 0.0F) * 0.00390625F + f20))
                        .color(f9, f10, f11, 0.8F)
                        .normal(1.0F, 0.0F, 0.0F)
                        .endVertex();
                  }
               }

               if (k > -1) {
                  for (int index = 0; index < 8; index++) {
                     worldRenderer.pos((double)(f26 + 0.0F), (double)(f5 + 4.0F), (double)(f27 + (float)index + 0.0F))
                        .tex((double)((f24 + 0.0F) * 0.00390625F + f19), (double)((f25 + (float)index + 0.5F) * 0.00390625F + f20))
                        .color(f15, f16, f17, 0.8F)
                        .normal(0.0F, 0.0F, -1.0F)
                        .endVertex();
                     worldRenderer.pos((double)(f26 + 8.0F), (double)(f5 + 4.0F), (double)(f27 + (float)index + 0.0F))
                        .tex((double)((f24 + 8.0F) * 0.00390625F + f19), (double)((f25 + (float)index + 0.5F) * 0.00390625F + f20))
                        .color(f15, f16, f17, 0.8F)
                        .normal(0.0F, 0.0F, -1.0F)
                        .endVertex();
                     worldRenderer.pos((double)(f26 + 8.0F), (double)(f5 + 0.0F), (double)(f27 + (float)index + 0.0F))
                        .tex((double)((f24 + 8.0F) * 0.00390625F + f19), (double)((f25 + (float)index + 0.5F) * 0.00390625F + f20))
                        .color(f15, f16, f17, 0.8F)
                        .normal(0.0F, 0.0F, -1.0F)
                        .endVertex();
                     worldRenderer.pos((double)(f26 + 0.0F), (double)(f5 + 0.0F), (double)(f27 + (float)index + 0.0F))
                        .tex((double)((f24 + 0.0F) * 0.00390625F + f19), (double)((f25 + (float)index + 0.5F) * 0.00390625F + f20))
                        .color(f15, f16, f17, 0.8F)
                        .normal(0.0F, 0.0F, -1.0F)
                        .endVertex();
                  }
               }

               if (k <= 1) {
                  for (int i2 = 0; i2 < 8; i2++) {
                     worldRenderer.pos((double)(f26 + 0.0F), (double)(f5 + 4.0F), (double)(f27 + (float)i2 + 1.0F - 9.765625E-4F))
                        .tex((double)((f24 + 0.0F) * 0.00390625F + f19), (double)((f25 + (float)i2 + 0.5F) * 0.00390625F + f20))
                        .color(f15, f16, f17, 0.8F)
                        .normal(0.0F, 0.0F, 1.0F)
                        .endVertex();
                     worldRenderer.pos((double)(f26 + 8.0F), (double)(f5 + 4.0F), (double)(f27 + (float)i2 + 1.0F - 9.765625E-4F))
                        .tex((double)((f24 + 8.0F) * 0.00390625F + f19), (double)((f25 + (float)i2 + 0.5F) * 0.00390625F + f20))
                        .color(f15, f16, f17, 0.8F)
                        .normal(0.0F, 0.0F, 1.0F)
                        .endVertex();
                     worldRenderer.pos((double)(f26 + 8.0F), (double)(f5 + 0.0F), (double)(f27 + (float)i2 + 1.0F - 9.765625E-4F))
                        .tex((double)((f24 + 8.0F) * 0.00390625F + f19), (double)((f25 + (float)i2 + 0.5F) * 0.00390625F + f20))
                        .color(f15, f16, f17, 0.8F)
                        .normal(0.0F, 0.0F, 1.0F)
                        .endVertex();
                     worldRenderer.pos((double)(f26 + 0.0F), (double)(f5 + 0.0F), (double)(f27 + (float)i2 + 1.0F - 9.765625E-4F))
                        .tex((double)((f24 + 0.0F) * 0.00390625F + f19), (double)((f25 + (float)i2 + 0.5F) * 0.00390625F + f20))
                        .color(f15, f16, f17, 0.8F)
                        .normal(0.0F, 0.0F, 1.0F)
                        .endVertex();
                  }
               }

               instance.draw();
            }
         }

         this.cloudRenderer.endUpdateGlList();
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableBlend();
      GlStateManager.enableCull();
   }

   public void resetClouds() {
      this.cloudRenderer.reset();
   }

   private void preRenderDamagedBlocks() {
      GlStateManager.tryBlendFuncSeparate(774, 768, 1, 0);
      GlStateManager.enableBlend();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
      GlStateManager.doPolygonOffset(-1.0F, -10.0F);
      GlStateManager.enablePolygonOffset();
      GlStateManager.alphaFunc(516, 0.1F);
      GlStateManager.enableAlpha();
      GlStateManager.pushMatrix();
      if (Config.isShaderPackLoaded()) {
         ShadersRender.beginBlockDamage();
      }
   }

   public void playSoundToNearExcept(EntityPlayer player, String str, double d, double d2, double d3, float f, float f2) {
   }

   public String getDebugInfoEntities() {
      return "E: " + this.countEntitiesRendered + "/" + this.countEntitiesTotal + ", B: " + this.countEntitiesHidden + ", I: " + (this.countEntitiesTotal - this.countEntitiesHidden - this.countEntitiesRendered) + ", " + Config.getVersionDebug();
   }

   public boolean hasNoChunkUpdates() {
      return this.chunksToUpdate.isEmpty() && this.renderDispatcher.hasNoChunkUpdates();
   }

   public WorldClient getTheWorld() {
      return this.theWorld;
   }

   private Set getVisibleFacings(BlockPos pos) {
      VisGraph visGraph = new VisGraph();
      BlockPos pos2 = new BlockPos(pos.getX() >> 4 << 4, pos.getY() >> 4 << 4, pos.getZ() >> 4 << 4);
      Chunk chunkFromBlockCoords = this.theWorld.getChunkFromBlockCoords(pos2);

      for (BlockPos.MutableBlockPos pos3 : (Iterable<BlockPos.MutableBlockPos>)(BlockPos.getAllInBoxMutable(pos2, pos2.add(15, 15, 15)))) {
         if (chunkFromBlockCoords.getBlock(pos3).isOpaqueCube()) {
            visGraph.setOpaqueCube(pos3);
         }
      }

      return visGraph.getVisibleFacings(pos);
   }

   public static void drawOutlinedBoundingBox(AxisAlignedBB box, int n, int n2, int n3, int n4) {
      drawOutlinedBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, n, n2, n3, n4);
   }

   public void drawBlockDamageTexture(Tessellator tessellator, WorldRenderer worldRenderer, Entity entity, float f) {
      double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f;
      double d2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f;
      double d3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f;
      if (!this.damagedBlocks.isEmpty()) {
         this.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
         this.preRenderDamagedBlocks();
         worldRenderer.begin(7, DefaultVertexFormats.BLOCK);
         worldRenderer.setTranslation(-d, -d2, -d3);
         worldRenderer.noColor();
         Iterator it = this.damagedBlocks.values().iterator();

         while (it.hasNext()) {
            DestroyBlockProgress destroyBlockProgress = (DestroyBlockProgress)it.next();
            BlockPos position = destroyBlockProgress.getPosition();
            double d4 = (double)position.getX() - d;
            double d5 = (double)position.getY() - d2;
            double d6 = (double)position.getZ() - d3;
            Block block = this.theWorld.getBlockState(position).getBlock();
            if (!(block instanceof BlockChest)
               && !(block instanceof BlockEnderChest)
               && !(block instanceof BlockSign)
               && !(block instanceof BlockSkull)) {
               if (d4 * d4 + d5 * d5 + d6 * d6 > 1024.0) {
                  it.remove();
               } else {
                  IBlockState blockState = this.theWorld.getBlockState(position);
                  if (blockState.getBlock().getMaterial() != Material.air) {
                     int partialBlockDamage = destroyBlockProgress.getPartialBlockDamage();
                     TextureAtlasSprite textureAtlasSprite = this.destroyBlockIcons[partialBlockDamage];
                     this.mc.getBlockRendererDispatcher().renderBlockDamage(blockState, position, textureAtlasSprite, this.theWorld);
                  }
               }
            }
         }

         tessellator.draw();
         worldRenderer.setTranslation(0.0, 0.0, 0.0);
         this.postRenderDamagedBlocks();
      }
   }

   private void spawnParticle(EnumParticleTypes enumParticleTypes, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
      this.spawnParticle(enumParticleTypes.getParticleID(), enumParticleTypes.getShouldIgnoreRange(), d, d2, d3, d4, d5, d6, ints);
   }

   public void markBlockRangeForRenderUpdate(int n, int n2, int n3, int n4, int n5, int n6) {
      this.markBlocksForUpdate(n - 1, n2 - 1, n3 - 1, n4 + 1, n5 + 1, n6 + 1);
   }

   private void clearRenderInfos() {
      if (renderEntitiesCounter > 0) {
         this.renderInfos = new ArrayList(this.renderInfos.size() + 16);
         this.renderInfosEntities = new ArrayList(this.renderInfosEntities.size() + 16);
         this.renderInfosTileEntities = new ArrayList(this.renderInfosTileEntities.size() + 16);
      } else {
         this.renderInfos.clear();
         this.renderInfosEntities.clear();
         this.renderInfosTileEntities.clear();
      }
   }

   public boolean isRenderEntityOutlines() {
      return (!ClientManagers.moduleManager.esp.isToggled() || !EspRenderModeOption.VANILLA.isSelected())
            && (!ClientManagers.moduleManager.itemESP.isToggled() || !ClientManagers.moduleManager.itemESP.mode.getSelectedValue().equals("Vanilla"))
         ? !Config.isFastRender()
            && !Config.isShaderPackLoaded()
            && !Config.isAntialiasing()
            && this.entityOutlineFramebuffer != null
            && this.entityOutlineShader != null
            && this.mc.thePlayer != null
            && this.mc.thePlayer.isSpectator()
            && this.mc.gameSettings.keyBindSpectatorOutlines.isKeyDown()
         : !Config.isFastRender()
            && !Config.isShaderPackLoaded()
            && !Config.isAntialiasing()
            && this.entityOutlineFramebuffer != null
            && this.entityOutlineShader != null
            && this.mc.thePlayer != null;
   }

   public void playRecord(String str, BlockPos pos) {
      ISound sound = (ISound)this.mapSoundPositions.get(pos);
      if (sound != null) {
         this.mc.getSoundHandler().stopSound(sound);
         this.mapSoundPositions.remove(pos);
      }

      if (str != null) {
         ItemRecord record = ItemRecord.getRecord(str);
         if (record != null) {
            this.mc.ingameGUI.setRecordPlayingMessage(record.getRecordNameLocal());
         }

         PositionedSoundRecord positionedSoundRecord = PositionedSoundRecord.create(new ResourceLocation(str), (float)pos.getX(), (float)pos.getY(), (float)pos.getZ());
         this.mapSoundPositions.put(pos, positionedSoundRecord);
         this.mc.getSoundHandler().playSound(positionedSoundRecord);
      }
   }

   public int getCountTileEntitiesRendered() {
      return this.countTileEntitiesRendered;
   }

   public void renderWorldBorder(Entity entity, float f) {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      WorldBorder worldBorder = this.theWorld.getWorldBorder();
      double d = (double)(this.mc.gameSettings.renderDistanceChunks * 16);
      if (entity.posX >= worldBorder.maxX() - d || entity.posX <= worldBorder.minX() + d || entity.posZ >= worldBorder.maxZ() - d || entity.posZ <= worldBorder.minZ() + d) {
         if (Config.isShaderPackLoaded()) {
            Shaders.pushProgram();
            Shaders.useProgram(Shaders.ProgramTexturedLit);
         }

         double d2 = 1.0 - worldBorder.getClosestDistance(entity) / d;
         d2 = Math.pow(d2, 4.0);
         double d3 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f;
         double d4 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f;
         double d5 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f;
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
         this.renderEngine.bindTexture(locationForcefieldPng);
         GlStateManager.depthMask(false);
         GlStateManager.pushMatrix();
         int id = worldBorder.getStatus().getID();
         GlStateManager.color((float)(id >> 16 & 0xFF) / 255.0F, (float)(id >> 8 & 0xFF) / 255.0F, (float)(id & 0xFF) / 255.0F, (float)d2);
         GlStateManager.doPolygonOffset(-3.0F, -3.0F);
         GlStateManager.enablePolygonOffset();
         GlStateManager.alphaFunc(516, 0.1F);
         GlStateManager.enableAlpha();
         GlStateManager.disableCull();
         float f5 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F;
         float f6 = 0.0F;
         float f7 = 0.0F;
         float f8 = 128.0F;
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
         worldRenderer.setTranslation(-d3, -d4, -d5);
         double d6 = Math.max((double)MathHelper.floor_double(d5 - d), worldBorder.minZ());
         double d7 = Math.min((double)MathHelper.ceiling_double_int(d5 + d), worldBorder.maxZ());
         if (d3 > worldBorder.maxX() - d) {
            float f9 = 0.0F;

            for (double i = d6; i < d7; f9 += 0.5F) {
               double d8 = Math.min(1.0, d7 - i);
               float f10 = (float)d8 * 0.5F;
               worldRenderer.pos(worldBorder.maxX(), 256.0, i).tex((double)(f5 + f9), (double)(f5 + 0.0F)).endVertex();
               worldRenderer.pos(worldBorder.maxX(), 256.0, i + d8).tex((double)(f5 + f10 + f9), (double)(f5 + 0.0F)).endVertex();
               worldRenderer.pos(worldBorder.maxX(), 0.0, i + d8).tex((double)(f5 + f10 + f9), (double)(f5 + 128.0F)).endVertex();
               worldRenderer.pos(worldBorder.maxX(), 0.0, i).tex((double)(f5 + f9), (double)(f5 + 128.0F)).endVertex();
               i++;
            }
         }

         if (d3 < worldBorder.minX() + d) {
            float f11 = 0.0F;

            for (double j = d6; j < d7; f11 += 0.5F) {
               double d9 = Math.min(1.0, d7 - j);
               float f12 = (float)d9 * 0.5F;
               worldRenderer.pos(worldBorder.minX(), 256.0, j).tex((double)(f5 + f11), (double)(f5 + 0.0F)).endVertex();
               worldRenderer.pos(worldBorder.minX(), 256.0, j + d9).tex((double)(f5 + f12 + f11), (double)(f5 + 0.0F)).endVertex();
               worldRenderer.pos(worldBorder.minX(), 0.0, j + d9).tex((double)(f5 + f12 + f11), (double)(f5 + 128.0F)).endVertex();
               worldRenderer.pos(worldBorder.minX(), 0.0, j).tex((double)(f5 + f11), (double)(f5 + 128.0F)).endVertex();
               j++;
            }
         }

         d6 = Math.max((double)MathHelper.floor_double(d3 - d), worldBorder.minX());
         d7 = Math.min((double)MathHelper.ceiling_double_int(d3 + d), worldBorder.maxX());
         if (d5 > worldBorder.maxZ() - d) {
            float f13 = 0.0F;

            for (double k = d6; k < d7; f13 += 0.5F) {
               double d10 = Math.min(1.0, d7 - k);
               float f14 = (float)d10 * 0.5F;
               worldRenderer.pos(k, 256.0, worldBorder.maxZ()).tex((double)(f5 + f13), (double)(f5 + 0.0F)).endVertex();
               worldRenderer.pos(k + d10, 256.0, worldBorder.maxZ()).tex((double)(f5 + f14 + f13), (double)(f5 + 0.0F)).endVertex();
               worldRenderer.pos(k + d10, 0.0, worldBorder.maxZ()).tex((double)(f5 + f14 + f13), (double)(f5 + 128.0F)).endVertex();
               worldRenderer.pos(k, 0.0, worldBorder.maxZ()).tex((double)(f5 + f13), (double)(f5 + 128.0F)).endVertex();
               k++;
            }
         }

         if (d5 < worldBorder.minZ() + d) {
            float f15 = 0.0F;

            for (double m = d6; m < d7; f15 += 0.5F) {
               double d11 = Math.min(1.0, d7 - m);
               float f16 = (float)d11 * 0.5F;
               worldRenderer.pos(m, 256.0, worldBorder.minZ()).tex((double)(f5 + f15), (double)(f5 + 0.0F)).endVertex();
               worldRenderer.pos(m + d11, 256.0, worldBorder.minZ()).tex((double)(f5 + f16 + f15), (double)(f5 + 0.0F)).endVertex();
               worldRenderer.pos(m + d11, 0.0, worldBorder.minZ()).tex((double)(f5 + f16 + f15), (double)(f5 + 128.0F)).endVertex();
               worldRenderer.pos(m, 0.0, worldBorder.minZ()).tex((double)(f5 + f15), (double)(f5 + 128.0F)).endVertex();
               m++;
            }
         }

         instance.draw();
         worldRenderer.setTranslation(0.0, 0.0, 0.0);
         GlStateManager.enableCull();
         GlStateManager.disableAlpha();
         GlStateManager.doPolygonOffset(0.0F, 0.0F);
         GlStateManager.disablePolygonOffset();
         GlStateManager.enableAlpha();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.disableBlend();
         GlStateManager.popMatrix();
         GlStateManager.depthMask(true);
         if (Config.isShaderPackLoaded()) {
            Shaders.popProgram();
         }
      }
   }

   public void updateChunks(long l) {
      l = (long)((double)l + 1.0E8);
      this.displayListEntitiesDirty = this.displayListEntitiesDirty | this.renderDispatcher.runChunkUploads(l);
      if (this.chunksToUpdateForced.size() > 0) {
         Iterator it = this.chunksToUpdateForced.iterator();

         while (it.hasNext()) {
            RenderChunk renderChunk = (RenderChunk)it.next();
            if (!this.renderDispatcher.updateChunkLater(renderChunk)) {
               break;
            }

            renderChunk.setNeedsUpdate(false);
            it.remove();
            this.chunksToUpdate.remove(renderChunk);
            this.chunksToResortTransparency.remove(renderChunk);
         }
      }

      if (this.chunksToResortTransparency.size() > 0) {
         Iterator it2 = this.chunksToResortTransparency.iterator();
         if (it2.hasNext()) {
            RenderChunk renderChunk2 = (RenderChunk)it2.next();
            if (this.renderDispatcher.updateTransparencyLater(renderChunk2)) {
               it2.remove();
            }
         }
      }

      double d = 0.0;
      int updatesPerFrame = Config.getUpdatesPerFrame();
      if (!this.chunksToUpdate.isEmpty()) {
         ObjectListIterator it3 = this.chunksToUpdate.iterator();

         while (it3.hasNext()) {
            RenderChunk renderChunk3 = (RenderChunk)it3.next();
            boolean isEmpty = renderChunk3.isEmpty();
            boolean flag;
            if (isEmpty) {
               flag = this.renderDispatcher.updateChunkNow(renderChunk3);
            } else {
               flag = this.renderDispatcher.updateChunkLater(renderChunk3);
            }

            if (!flag) {
               break;
            }

            renderChunk3.setNeedsUpdate(false);
            it3.remove();
            if (!isEmpty) {
               double d2 = 2.0 * RenderChunkUtils.getRelativeBufferSize2(renderChunk3);
               d += d2;
               if (d > (double)updatesPerFrame) {
                  break;
               }
            }
         }
      }
   }

   public void renderSky(float f, int n) {
      if (this.mc.theWorld.provider.getDimensionId() == 1) {
         this.renderSkyEnd();
      } else if (this.mc.theWorld.provider.isSurfaceWorld()) {
         GlStateManager.disableTexture2D();
         boolean isShaderPackLoaded = Config.isShaderPackLoaded();
         if (isShaderPackLoaded) {
            Shaders.disableTexture2D();
         }

         Vec3 skyColor = this.theWorld.getSkyColor(this.mc.getRenderViewEntity(), f);
         skyColor = CustomColors.getSkyColor(
            skyColor, this.mc.theWorld, this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().posY + 1.0, this.mc.getRenderViewEntity().posZ
         );
         if (isShaderPackLoaded) {
            Shaders.setSkyColor(skyColor);
         }

         float xCoord = (float)skyColor.xCoord;
         float yCoord = (float)skyColor.yCoord;
         float zCoord = (float)skyColor.zCoord;
         if (n != 2) {
            float f2 = (xCoord * 30.0F + yCoord * 59.0F + zCoord * 11.0F) / 100.0F;
            float f3 = (xCoord * 30.0F + yCoord * 70.0F) / 100.0F;
            float f4 = (xCoord * 30.0F + zCoord * 70.0F) / 100.0F;
            xCoord = f2;
            yCoord = f3;
            zCoord = f4;
         }

         GlStateManager.color(xCoord, yCoord, zCoord);
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         GlStateManager.depthMask(false);
         GlStateManager.enableFog();
         if (isShaderPackLoaded) {
            Shaders.enableFog();
         }

         GlStateManager.color(xCoord, yCoord, zCoord);
         if (isShaderPackLoaded) {
            Shaders.preSkyList();
         }

         if (Config.isSkyEnabled()) {
            if (this.vboEnabled) {
               this.skyVBO.bindBuffer();
               GL11.glEnableClientState(32884);
               GL11.glVertexPointer(3, 5126, 12, 0L);
               this.skyVBO.drawArrays(7);
               this.skyVBO.unbindBuffer();
               GL11.glDisableClientState(32884);
            } else {
               GlStateManager.callList(this.glSkyList);
            }
         }

         GlStateManager.disableFog();
         if (isShaderPackLoaded) {
            Shaders.disableFog();
         }

         GlStateManager.disableAlpha();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         RenderHelper.disableStandardItemLighting();
         float[] floats = this.theWorld.provider.calcSunriseSunsetColors(this.theWorld.getCelestialAngle(f), f);
         if (floats != null && Config.isSunMoonEnabled()) {
            GlStateManager.disableTexture2D();
            if (isShaderPackLoaded) {
               Shaders.disableTexture2D();
            }

            GlStateManager.shadeModel(7425);
            GlStateManager.pushMatrix();
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(MathHelper.sin(this.theWorld.getCelestialAngleRadians(f)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            float f5 = floats[0];
            float f6 = floats[1];
            float f7 = floats[2];
            if (n != 2) {
               float f8 = (f5 * 30.0F + f6 * 59.0F + f7 * 11.0F) / 100.0F;
               float f9 = (f5 * 30.0F + f6 * 70.0F) / 100.0F;
               float f10 = (f5 * 30.0F + f7 * 70.0F) / 100.0F;
               f5 = f8;
               f6 = f9;
               f7 = f10;
            }

            worldRenderer.begin(6, DefaultVertexFormats.POSITION_COLOR);
            worldRenderer.pos(0.0, 100.0, 0.0).color(f5, f6, f7, floats[3]).endVertex();
            boolean flag = true;

            for (int i = 0; i <= 16; i++) {
               float f11 = (float)i * 3.1415927F * 2.0F / 16.0F;
               float f12 = MathHelper.sin(f11);
               float f13 = MathHelper.cos(f11);
               worldRenderer.pos((double)(f12 * 120.0F), (double)(f13 * 120.0F), (double)(-f13 * 40.0F * floats[3]))
                  .color(floats[0], floats[1], floats[2], 0.0F)
                  .endVertex();
            }

            instance.draw();
            GlStateManager.popMatrix();
            GlStateManager.shadeModel(7424);
         }

         GlStateManager.enableTexture2D();
         if (isShaderPackLoaded) {
            Shaders.enableTexture2D();
         }

         GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
         GlStateManager.pushMatrix();
         float f14 = 1.0F - this.theWorld.getRainStrength(f);
         GlStateManager.color(1.0F, 1.0F, 1.0F, f14);
         GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
         CustomSky.renderSky(this.theWorld, this.renderEngine, f);
         if (isShaderPackLoaded) {
            Shaders.preCelestialRotate();
         }

         GlStateManager.rotate(this.theWorld.getCelestialAngle(f) * 360.0F, 1.0F, 0.0F, 0.0F);
         if (isShaderPackLoaded) {
            Shaders.postCelestialRotate();
         }

         float f15 = 30.0F;
         if (Config.isSunTexture()) {
            this.renderEngine.bindTexture(locationSunPng);
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
            worldRenderer.pos((double)(-f15), 100.0, (double)(-f15)).tex(0.0, 0.0).endVertex();
            worldRenderer.pos((double)f15, 100.0, (double)(-f15)).tex(1.0, 0.0).endVertex();
            worldRenderer.pos((double)f15, 100.0, (double)f15).tex(1.0, 1.0).endVertex();
            worldRenderer.pos((double)(-f15), 100.0, (double)f15).tex(0.0, 1.0).endVertex();
            instance.draw();
         }

         f15 = 20.0F;
         if (Config.isMoonTexture()) {
            this.renderEngine.bindTexture(locationMoonPhasesPng);
            int moonPhase = this.theWorld.getMoonPhase();
            int n2 = moonPhase % 4;
            int n3 = moonPhase / 4 % 2;
            float f16 = (float)n2 / 4.0F;
            float f17 = (float)n3 / 2.0F;
            float f18 = (float)(n2 + 1) / 4.0F;
            float f19 = (float)(n3 + 1) / 2.0F;
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
            worldRenderer.pos((double)(-f15), -100.0, (double)f15).tex((double)f18, (double)f19).endVertex();
            worldRenderer.pos((double)f15, -100.0, (double)f15).tex((double)f16, (double)f19).endVertex();
            worldRenderer.pos((double)f15, -100.0, (double)(-f15)).tex((double)f16, (double)f17).endVertex();
            worldRenderer.pos((double)(-f15), -100.0, (double)(-f15)).tex((double)f18, (double)f17).endVertex();
            instance.draw();
         }

         GlStateManager.disableTexture2D();
         if (isShaderPackLoaded) {
            Shaders.disableTexture2D();
         }

         float f20 = this.theWorld.getStarBrightness(f) * f14;
         if (f20 > 0.0F && Config.isStarsEnabled() && !CustomSky.hasSkyLayers(this.theWorld)) {
            GlStateManager.color(f20, f20, f20, f20);
            if (this.vboEnabled) {
               this.starVBO.bindBuffer();
               GL11.glEnableClientState(32884);
               GL11.glVertexPointer(3, 5126, 12, 0L);
               this.starVBO.drawArrays(7);
               this.starVBO.unbindBuffer();
               GL11.glDisableClientState(32884);
            } else {
               GlStateManager.callList(this.starGLCallList);
            }
         }

         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableBlend();
         GlStateManager.enableAlpha();
         GlStateManager.enableFog();
         if (isShaderPackLoaded) {
            Shaders.enableFog();
         }

         GlStateManager.popMatrix();
         GlStateManager.disableTexture2D();
         if (isShaderPackLoaded) {
            Shaders.disableTexture2D();
         }

         GlStateManager.color(0.0F, 0.0F, 0.0F);
         double d = this.mc.thePlayer.getPositionEyes(f).yCoord - this.theWorld.getHorizon();
         if (d < 0.0) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 12.0F, 0.0F);
            if (this.vboEnabled) {
               this.sky2VBO.bindBuffer();
               GL11.glEnableClientState(32884);
               GL11.glVertexPointer(3, 5126, 12, 0L);
               this.sky2VBO.drawArrays(7);
               this.sky2VBO.unbindBuffer();
               GL11.glDisableClientState(32884);
            } else {
               GlStateManager.callList(this.glSkyList2);
            }

            GlStateManager.popMatrix();
            float f21 = -((float)(d + 65.0));
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            worldRenderer.pos(-1.0, (double)f21, 1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(1.0, (double)f21, 1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(1.0, -1.0, 1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(-1.0, -1.0, 1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(-1.0, -1.0, -1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(1.0, -1.0, -1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(1.0, (double)f21, -1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(-1.0, (double)f21, -1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(1.0, -1.0, -1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(1.0, -1.0, 1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(1.0, (double)f21, 1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(1.0, (double)f21, -1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(-1.0, (double)f21, -1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(-1.0, (double)f21, 1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(-1.0, -1.0, 1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(-1.0, -1.0, -1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(-1.0, -1.0, -1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(-1.0, -1.0, 1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(1.0, -1.0, 1.0).color(0, 0, 0, 255).endVertex();
            worldRenderer.pos(1.0, -1.0, -1.0).color(0, 0, 0, 255).endVertex();
            instance.draw();
         }

         if (this.theWorld.provider.isSkyColored()) {
            GlStateManager.color(xCoord * 0.2F + 0.04F, yCoord * 0.2F + 0.04F, zCoord * 0.6F + 0.1F);
         } else {
            GlStateManager.color(xCoord, yCoord, zCoord);
         }

         if (this.mc.gameSettings.renderDistanceChunks <= 4) {
            GlStateManager.color(this.mc.entityRenderer.fogColorRed, this.mc.entityRenderer.fogColorGreen, this.mc.entityRenderer.fogColorBlue);
         }

         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, -((float)(d - 16.0)), 0.0F);
         if (Config.isSkyEnabled()) {
            if (this.vboEnabled) {
               this.sky2VBO.bindBuffer();
               GlStateManager.glEnableClientState(32884);
               GlStateManager.glVertexPointer(3, 5126, 12, 0);
               this.sky2VBO.drawArrays(7);
               this.sky2VBO.unbindBuffer();
               GlStateManager.glDisableClientState(32884);
            } else {
               GlStateManager.callList(this.glSkyList2);
            }
         }

         GlStateManager.popMatrix();
         GlStateManager.enableTexture2D();
         if (isShaderPackLoaded) {
            Shaders.enableTexture2D();
         }

         GlStateManager.depthMask(true);
      }
   }

   public void drawSelectionBox(EntityPlayer player, MovingObjectPosition hit, int n, float f) {
      if (n == 0 && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.color(0.0F, 0.0F, 0.0F, 0.4F);
         GL11.glLineWidth(2.0F);
         GlStateManager.disableTexture2D();
         if (Config.isShaderPackLoaded()) {
            Shaders.disableTexture2D();
         }

         GlStateManager.depthMask(false);
         float f2 = 0.002F;
         BlockPos blockPos = hit.getBlockPos();
         Block block = this.theWorld.getBlockState(blockPos).getBlock();
         if (block.getMaterial() != Material.air && this.theWorld.getWorldBorder().contains(blockPos)) {
            block.setBlockBoundsBasedOnState(this.theWorld, blockPos.getX(), blockPos.getY(), blockPos.getZ());
            double d = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)f;
            double d2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)f;
            double d3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)f;
            AxisAlignedBB selectedBoundingBox = block.getSelectedBoundingBox(this.theWorld, blockPos);
            Block.EnumOffsetType offsetType = block.getOffsetType();
            if (offsetType != Block.EnumOffsetType.NONE) {
               selectedBoundingBox = BlockModelUtils.getOffsetBoundingBox(selectedBoundingBox, offsetType, blockPos);
            }

            drawSelectionBoundingBox(selectedBoundingBox.expand(0.0020000000949949026, 0.0020000000949949026, 0.0020000000949949026).offset(-d, -d2, -d3));
         }

         GlStateManager.depthMask(true);
         GlStateManager.enableTexture2D();
         if (Config.isShaderPackLoaded()) {
            Shaders.enableTexture2D();
         }

         GlStateManager.disableBlend();
      }
   }

   public void markBlockForUpdate(BlockPos pos) {
      int x = pos.getX();
      int y = pos.getY();
      int z = pos.getZ();
      this.markBlocksForUpdate(x - 1, y - 1, z - 1, x + 1, y + 1, z + 1);
   }

   private void renderStars(WorldRenderer worldRenderer) {
      Random rand = new Random(10842L);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION);

      for (int i = 0; i < 1500; i++) {
         double d = (double)(rand.nextFloat() * 2.0F - 1.0F);
         double d2 = (double)(rand.nextFloat() * 2.0F - 1.0F);
         double d3 = (double)(rand.nextFloat() * 2.0F - 1.0F);
         double d4 = (double)(0.15F + rand.nextFloat() * 0.1F);
         double d5 = d * d + d2 * d2 + d3 * d3;
         if (d5 < 1.0 && d5 > 0.01) {
            d5 = 1.0 / Math.sqrt(d5);
            d *= d5;
            d2 *= d5;
            d3 *= d5;
            double d6 = d * 100.0;
            double d7 = d2 * 100.0;
            double d8 = d3 * 100.0;
            double d9 = Math.atan2(d, d3);
            double d10 = Math.sin(d9);
            double d11 = Math.cos(d9);
            double d12 = Math.atan2(Math.sqrt(d * d + d3 * d3), d2);
            double d13 = Math.sin(d12);
            double d14 = Math.cos(d12);
            double d15 = rand.nextDouble() * 3.141592653589793 * 2.0;
            double d16 = Math.sin(d15);
            double d17 = Math.cos(d15);

            for (int j = 0; j < 4; j++) {
               double d18 = 0.0;
               double d19 = (double)((j & 2) - 1) * d4;
               double d20 = (double)((j + 1 & 2) - 1) * d4;
               double d21 = 0.0;
               double d22 = d19 * d17 - d20 * d16;
               double d23 = d20 * d17 + d19 * d16;
               double d24 = d22 * d13 + 0.0 * d14;
               double d25 = 0.0 * d13 - d22 * d14;
               double d26 = d25 * d10 - d23 * d11;
               double d27 = d23 * d10 + d25 * d11;
               worldRenderer.pos(d6 + d26, d7 + d24, d8 + d27).endVertex();
            }
         }
      }
   }

   public void playSound(String str, double d, double d2, double d3, float f, float f2) {
   }

   public void stopChunkUpdates() {
      this.chunksToUpdate.clear();
      this.renderDispatcher.stopChunkUpdates();
   }

   public static void drawOutlinedBoundingBox(double d, double d2, double d3, double d4, double d5, double d6, int n, int n2, int n3, int n4) {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(3, DefaultVertexFormats.POSITION_COLOR);
      worldRenderer.pos(d, d2, d3).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d4, d2, d3).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d4, d2, d6).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d, d2, d6).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d, d2, d3).color(n, n2, n3, n4).endVertex();
      instance.draw();
      worldRenderer.begin(3, DefaultVertexFormats.POSITION_COLOR);
      worldRenderer.pos(d, d5, d3).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d4, d5, d3).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d4, d5, d6).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d, d5, d6).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d, d5, d3).color(n, n2, n3, n4).endVertex();
      instance.draw();
      worldRenderer.begin(1, DefaultVertexFormats.POSITION_COLOR);
      worldRenderer.pos(d, d2, d3).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d, d5, d3).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d4, d2, d3).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d4, d5, d3).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d4, d2, d6).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d4, d5, d6).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d, d2, d6).color(n, n2, n3, n4).endVertex();
      worldRenderer.pos(d, d5, d6).color(n, n2, n3, n4).endVertex();
      instance.draw();
   }

   public void setDisplayListEntitiesDirty() {
      this.displayListEntitiesDirty = true;
   }

   private RenderChunk getRenderChunkOffset(int n, int n2, RenderChunk renderChunk, EnumFacing facing, boolean flag, int n3) {
      RenderChunk renderChunkNeighbour = renderChunk.getRenderChunkNeighbour(facing);
      if (renderChunkNeighbour == null) {
         return null;
      } else if (renderChunkNeighbour.getPosition().getY() > n3) {
         return null;
      } else {
         if (flag) {
            BlockPos position = renderChunkNeighbour.getPosition();
            int n4 = n - position.getX();
            int n5 = n2 - position.getZ();
            if ((n4 * n4 + n5 * n5) > this.renderDistanceSq) {
               return null;
            }
         }

         return renderChunkNeighbour;
      }
   }

   public void makeEntityOutlineShader() {
      if (OpenGlHelper.shadersSupported) {
         if (ShaderLinkHelper.getStaticShaderLinkHelper() == null) {
            ShaderLinkHelper.setNewStaticShaderLinkHelper();
         }

         ResourceLocation location = new ResourceLocation("shaders/post/entity_outline.json");

         try {
            this.entityOutlineShader = new ShaderGroup(this.mc.getTextureManager(), this.mc.getResourceManager(), this.mc.getFramebuffer(), location);
            this.entityOutlineShader.createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
            this.entityOutlineFramebuffer = this.entityOutlineShader.getFramebufferRaw("final");
         } catch (IOException e) {
            LOGGER.warn("Failed to load shader: " + location, e);
            this.entityOutlineShader = null;
            this.entityOutlineFramebuffer = null;
         } catch (JsonSyntaxException e2) {
            LOGGER.warn("Failed to load shader: " + location, e2);
            this.entityOutlineShader = null;
            this.entityOutlineFramebuffer = null;
         }
      } else {
         this.entityOutlineShader = null;
         this.entityOutlineFramebuffer = null;
      }
   }

   private void markBlocksForUpdate(int n, int n2, int n3, int n4, int n5, int n6) {
      this.viewFrustum.markBlocksForUpdate(n, n2, n3, n4, n5, n6);
   }

   public void loadRenderers() {
      if (this.theWorld != null) {
         this.displayListEntitiesDirty = true;
         Blocks.leaves.setGraphicsLevel(Config.isTreesFancy());
         Blocks.leaves2.setGraphicsLevel(Config.isTreesFancy());
         BlockModelRenderer.updateAoLightValue();
         if (Config.isDynamicLights()) {
            DynamicLights.clear();
         }

         SmartAnimations.update();
         this.renderDistanceChunks = this.mc.gameSettings.renderDistanceChunks;
         this.renderDistance = this.renderDistanceChunks * 16;
         this.renderDistanceSq = this.renderDistance * this.renderDistance;
         boolean vboEnabled2 = this.vboEnabled;
         this.vboEnabled = OpenGlHelper.useVbo();
         if (vboEnabled2 && !this.vboEnabled) {
            this.renderContainer = new RenderList();
            this.renderChunkFactory = new ListChunkFactory();
         } else if (!vboEnabled2 && this.vboEnabled) {
            this.renderContainer = new VboRenderList();
            this.renderChunkFactory = new VboChunkFactory();
         }

         this.generateStars();
         this.generateSky();
         this.generateSky2();
         if (this.viewFrustum != null) {
            this.viewFrustum.deleteGlResources();
         }

         this.stopChunkUpdates();
         ObjectLinkedOpenHashSet setTileEntities2 = this.setTileEntities;
         synchronized (this.setTileEntities) {
            this.setTileEntities.clear();
         }

         this.viewFrustum = new ViewFrustum(this.theWorld, this.mc.gameSettings.renderDistanceChunks, this, this.renderChunkFactory);
         if (this.theWorld != null) {
            Entity renderViewEntity = this.mc.getRenderViewEntity();
            if (renderViewEntity != null) {
               this.viewFrustum.updateChunkPositions(renderViewEntity.posX, renderViewEntity.posZ);
            }
         }

         this.renderEntitiesStartupCounter = 2;
      }

      if (this.mc.thePlayer == null) {
         this.firstWorldLoad = true;
      }
   }

   public void resumeChunkUpdates() {
      if (this.renderDispatcher != null) {
         this.renderDispatcher.resumeChunkUpdates();
      }
   }

   public int renderBlockLayer(EnumWorldBlockLayer enumWorldBlockLayer, double d, int n, Entity entity) {
      RenderHelper.disableStandardItemLighting();
      if (enumWorldBlockLayer == EnumWorldBlockLayer.TRANSLUCENT && !Shaders.isShadowPass) {
         this.mc.mcProfiler.startSection("translucent_sort");
         double d2 = entity.posX - this.prevRenderSortX;
         double d3 = entity.posY - this.prevRenderSortY;
         double d4 = entity.posZ - this.prevRenderSortZ;
         if (d2 * d2 + d3 * d3 + d4 * d4 > 1.0) {
            this.prevRenderSortX = entity.posX;
            this.prevRenderSortY = entity.posY;
            this.prevRenderSortZ = entity.posZ;
            int n2 = 0;
            this.chunksToResortTransparency.clear();

            for (RenderGlobal.ContainerLocalRenderInformation information : (Iterable<RenderGlobal.ContainerLocalRenderInformation>)(this.renderInfos)) {
               if (information.renderChunk.compiledChunk.isLayerStarted(enumWorldBlockLayer) && n2++ < 15) {
                  this.chunksToResortTransparency.add(information.renderChunk);
               }
            }
         }

         this.mc.mcProfiler.endSection();
      }

      this.mc.mcProfiler.startSection("filterempty");
      int n3 = 0;
      boolean flag = enumWorldBlockLayer == EnumWorldBlockLayer.TRANSLUCENT;
      int n4 = flag ? this.renderInfos.size() - 1 : 0;
      int n5 = flag ? -1 : this.renderInfos.size();
      int n6 = flag ? -1 : 1;

      for (int i = n4; i != n5; i += n6) {
         RenderChunk renderChunk = ((RenderGlobal.ContainerLocalRenderInformation)this.renderInfos.get(i)).renderChunk;
         if (renderChunk != null && renderChunk.getCompiledChunk() != null && !renderChunk.getCompiledChunk().isLayerEmpty(enumWorldBlockLayer)) {
            n3++;
            this.renderContainer.addRenderChunk(renderChunk, enumWorldBlockLayer);
         }
      }

      if (n3 == 0) {
         this.mc.mcProfiler.endSection();
         return n3;
      } else {
         if (Config.isFogOff() && this.mc.entityRenderer.fogStandard) {
            GlStateManager.disableFog();
         }

         this.mc.mcProfiler.endStartSection("render_" + enumWorldBlockLayer);
         this.renderBlockLayer(enumWorldBlockLayer);
         this.mc.mcProfiler.endSection();
         return n3;
      }
   }

   private void updateDestroyBlockIcons() {
      TextureMap textureMapBlocks = this.mc.getTextureMapBlocks();

      for (int i = 0; i < this.destroyBlockIcons.length; i++) {
         this.destroyBlockIcons[i] = textureMapBlocks.getAtlasSprite("minecraft:blocks/destroy_stage_" + i);
      }
   }

   private void renderSkyEnd() {
      if (Config.isSkyEnabled()) {
         GlStateManager.disableFog();
         GlStateManager.disableAlpha();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         RenderHelper.disableStandardItemLighting();
         GlStateManager.depthMask(false);
         this.renderEngine.bindTexture(locationEndSkyPng);
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();

         for (int i = 0; i < 6; i++) {
            GlStateManager.pushMatrix();
            if (i == 1) {
               GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (i == 2) {
               GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (i == 3) {
               GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            }

            if (i == 4) {
               GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            }

            if (i == 5) {
               GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
            }

            worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            int n = 40;
            int n2 = 40;
            int n3 = 40;
            if (Config.isCustomColors()) {
               Vec3 vec = new Vec3((double)n / 255.0, (double)n2 / 255.0, (double)n3 / 255.0);
               vec = CustomColors.getWorldSkyColor(vec, this.theWorld, this.mc.getRenderViewEntity(), 0.0F);
               n = (int)(vec.xCoord * 255.0);
               n2 = (int)(vec.yCoord * 255.0);
               n3 = (int)(vec.zCoord * 255.0);
            }

            worldRenderer.pos(-100.0, -100.0, -100.0).tex(0.0, 0.0).color(n, n2, n3, 255).endVertex();
            worldRenderer.pos(-100.0, -100.0, 100.0).tex(0.0, 16.0).color(n, n2, n3, 255).endVertex();
            worldRenderer.pos(100.0, -100.0, 100.0).tex(16.0, 16.0).color(n, n2, n3, 255).endVertex();
            worldRenderer.pos(100.0, -100.0, -100.0).tex(16.0, 0.0).color(n, n2, n3, 255).endVertex();
            instance.draw();
            GlStateManager.popMatrix();
         }

         GlStateManager.depthMask(true);
         GlStateManager.enableTexture2D();
         GlStateManager.enableAlpha();
         GlStateManager.disableBlend();
      }
   }

   public boolean hasCloudFog(double d, double d2, double d3, float f) {
      return false;
   }

   public void notifyLightSet(int n, int n2, int n3) {
      this.markBlocksForUpdate(n - 1, n2 - 1, n3 - 1, n + 1, n2 + 1, n3 + 1);
   }

   private EntityFX spawnEntityFX(int n, boolean flag, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
      if (this.mc != null && this.mc.getRenderViewEntity() != null && this.mc.effectRenderer != null) {
         int particleSetting = this.mc.gameSettings.particleSetting;
         if (particleSetting == 1 && this.theWorld.rand.nextInt(3) == 0) {
            particleSetting = 2;
         }

         double d7 = this.mc.getRenderViewEntity().posX - d;
         double d8 = this.mc.getRenderViewEntity().posY - d2;
         double d9 = this.mc.getRenderViewEntity().posZ - d3;
         if (n == EnumParticleTypes.EXPLOSION_HUGE.getParticleID() && !Config.isAnimatedExplosion()) {
            return null;
         } else if (n == EnumParticleTypes.EXPLOSION_LARGE.getParticleID() && !Config.isAnimatedExplosion()) {
            return null;
         } else if (n == EnumParticleTypes.EXPLOSION_NORMAL.getParticleID() && !Config.isAnimatedExplosion()) {
            return null;
         } else if (n == EnumParticleTypes.SUSPENDED.getParticleID() && !Config.isWaterParticles()) {
            return null;
         } else if (n == EnumParticleTypes.SUSPENDED_DEPTH.getParticleID() && !Config.isVoidParticles()) {
            return null;
         } else if (n == EnumParticleTypes.SMOKE_NORMAL.getParticleID() && !Config.isAnimatedSmoke()) {
            return null;
         } else if (n == EnumParticleTypes.SMOKE_LARGE.getParticleID() && !Config.isAnimatedSmoke()) {
            return null;
         } else if (n == EnumParticleTypes.SPELL_MOB.getParticleID() && !Config.isPotionParticles()) {
            return null;
         } else if (n == EnumParticleTypes.SPELL_MOB_AMBIENT.getParticleID() && !Config.isPotionParticles()) {
            return null;
         } else if (n == EnumParticleTypes.SPELL.getParticleID() && !Config.isPotionParticles()) {
            return null;
         } else if (n == EnumParticleTypes.SPELL_INSTANT.getParticleID() && !Config.isPotionParticles()) {
            return null;
         } else if (n == EnumParticleTypes.SPELL_WITCH.getParticleID() && !Config.isPotionParticles()) {
            return null;
         } else if (n == EnumParticleTypes.PORTAL.getParticleID() && !Config.isPortalParticles()) {
            return null;
         } else if (n == EnumParticleTypes.FLAME.getParticleID() && !Config.isAnimatedFlame()) {
            return null;
         } else if (n == EnumParticleTypes.REDSTONE.getParticleID() && !Config.isAnimatedRedstone()) {
            return null;
         } else if (n == EnumParticleTypes.DRIP_WATER.getParticleID() && !Config.isDrippingWaterLava()) {
            return null;
         } else if (n == EnumParticleTypes.DRIP_LAVA.getParticleID() && !Config.isDrippingWaterLava()) {
            return null;
         } else if (n == EnumParticleTypes.FIREWORKS_SPARK.getParticleID() && !Config.isFireworkParticles()) {
            return null;
         } else {
            if (!flag) {
               double d10 = 256.0;
               if (n == EnumParticleTypes.CRIT.getParticleID()) {
                  d10 = 38416.0;
               }

               if (d7 * d7 + d8 * d8 + d9 * d9 > d10) {
                  return null;
               }

               if (particleSetting > 1) {
                  return null;
               }
            }

            EntityFX entityFX = this.mc.effectRenderer.spawnEffectParticle(n, d, d2, d3, d4, d5, d6, ints);
            if (n == EnumParticleTypes.WATER_BUBBLE.getParticleID()) {
               CustomColors.updateWaterFX(entityFX, this.theWorld, d, d2, d3, this.renderEnv);
            }

            if (n == EnumParticleTypes.WATER_SPLASH.getParticleID()) {
               CustomColors.updateWaterFX(entityFX, this.theWorld, d, d2, d3, this.renderEnv);
            }

            if (n == EnumParticleTypes.WATER_DROP.getParticleID()) {
               CustomColors.updateWaterFX(entityFX, this.theWorld, d, d2, d3, this.renderEnv);
            }

            if (n == EnumParticleTypes.TOWN_AURA.getParticleID()) {
               CustomColors.updateMyceliumFX(entityFX);
            }

            if (n == EnumParticleTypes.PORTAL.getParticleID()) {
               CustomColors.updatePortalFX(entityFX);
            }

            if (n == EnumParticleTypes.REDSTONE.getParticleID()) {
               CustomColors.updateReddustFX(entityFX, this.theWorld, d, d2, d3);
            }

            return entityFX;
         }
      } else {
         return null;
      }
   }

   public RenderGlobal(Minecraft mc2) {
      this.cloudRenderer = new CloudRenderer(mc2);
      this.mc = mc2;
      this.renderManager = mc2.getRenderManager();
      this.renderEngine = mc2.getTextureManager();
      this.renderEngine.bindTexture(locationForcefieldPng);
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GlStateManager.bindTexture(0);
      this.updateDestroyBlockIcons();
      this.vboEnabled = OpenGlHelper.useVbo();
      if (this.vboEnabled) {
         this.renderContainer = new VboRenderList();
         this.renderChunkFactory = new VboChunkFactory();
      } else {
         this.renderContainer = new RenderList();
         this.renderChunkFactory = new ListChunkFactory();
      }

      this.vertexBufferFormat = new VertexFormat();
      this.vertexBufferFormat.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.POSITION, 3));
      this.generateStars();
      this.generateSky();
      this.generateSky2();
   }

   public void onResourceManagerReload(IResourceManager resourceManager) {
      this.updateDestroyBlockIcons();
   }

   public void updateTileEntities(Collection items, Collection items2) {
      synchronized (this.setTileEntities) {
         this.setTileEntities.removeAll(items);
         this.setTileEntities.addAll(items2);
      }
   }

   public void onEntityAdded(Entity entity) {
      RandomEntities.entityLoaded(entity, this.theWorld);
      if (Config.isDynamicLights()) {
         DynamicLights.entityAdded(entity, this);
      }
   }

   public void broadcastSound(int n, BlockPos pos, int n2) {
      switch (n) {
         case 1013:
         case 1018:
            if (this.mc.getRenderViewEntity() != null) {
               double d = (double)pos.getX() - this.mc.getRenderViewEntity().posX;
               double d2 = (double)pos.getY() - this.mc.getRenderViewEntity().posY;
               double d3 = (double)pos.getZ() - this.mc.getRenderViewEntity().posZ;
               double d4 = Math.sqrt(d * d + d2 * d2 + d3 * d3);
               double posX = this.mc.getRenderViewEntity().posX;
               double posY = this.mc.getRenderViewEntity().posY;
               double posZ = this.mc.getRenderViewEntity().posZ;
               if (d4 > 0.0) {
                  posX += d / d4 * 2.0;
                  posY += d2 / d4 * 2.0;
                  posZ += d3 / d4 * 2.0;
               }

               if (n == 1013) {
                  this.theWorld.playSound(posX, posY, posZ, "mob.wither.spawn", 1.0F, 1.0F, false);
               } else {
                  this.theWorld.playSound(posX, posY, posZ, "mob.enderdragon.end", 5.0F, 1.0F, false);
               }
            }
      }
   }

   private void generateSky2() {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      if (this.sky2VBO != null) {
         this.sky2VBO.deleteGlBuffers();
      }

      if (this.glSkyList2 >= 0) {
         GLAllocation.deleteDisplayLists(this.glSkyList2);
         this.glSkyList2 = -1;
      }

      if (this.vboEnabled) {
         this.sky2VBO = new VertexBuffer(this.vertexBufferFormat);
         this.renderSky(worldRenderer, -16.0F, true);
         worldRenderer.finishDrawing();
         worldRenderer.reset();
         this.sky2VBO.bufferData(worldRenderer.getByteBuffer());
      } else {
         this.glSkyList2 = GLAllocation.generateDisplayLists(1);
         GL11.glNewList(this.glSkyList2, 4864);
         this.renderSky(worldRenderer, -16.0F, true);
         instance.draw();
         GL11.glEndList();
      }
   }

   private void fixTerrainFrustum(double d, double d2, double d3) {
      this.debugFixedClippingHelper = new ClippingHelperImpl();
      ((ClippingHelperImpl)this.debugFixedClippingHelper).init();
      Matrix4f matrix4f = new Matrix4f(this.debugFixedClippingHelper.modelviewMatrix);
      matrix4f.transpose();
      Matrix4f matrix4f2 = new Matrix4f(this.debugFixedClippingHelper.projectionMatrix);
      matrix4f2.transpose();
      Matrix4f matrix4f3 = new Matrix4f();
      Matrix4f.mul(matrix4f2, matrix4f, matrix4f3);
      matrix4f3.invert();
      this.debugTerrainFrustumPosition.x = d;
      this.debugTerrainFrustumPosition.y = d2;
      this.debugTerrainFrustumPosition.z = d3;
      this.debugTerrainMatrix[0] = new Vector4f(-1.0F, -1.0F, -1.0F, 1.0F);
      this.debugTerrainMatrix[1] = new Vector4f(1.0F, -1.0F, -1.0F, 1.0F);
      this.debugTerrainMatrix[2] = new Vector4f(1.0F, 1.0F, -1.0F, 1.0F);
      this.debugTerrainMatrix[3] = new Vector4f(-1.0F, 1.0F, -1.0F, 1.0F);
      this.debugTerrainMatrix[4] = new Vector4f(-1.0F, -1.0F, 1.0F, 1.0F);
      this.debugTerrainMatrix[5] = new Vector4f(1.0F, -1.0F, 1.0F, 1.0F);
      this.debugTerrainMatrix[6] = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.debugTerrainMatrix[7] = new Vector4f(-1.0F, 1.0F, 1.0F, 1.0F);

      for (int i = 0; i < 8; i++) {
         Matrix4f.transform(matrix4f3, this.debugTerrainMatrix[i], this.debugTerrainMatrix[i]);
         this.debugTerrainMatrix[i].x = this.debugTerrainMatrix[i].x / this.debugTerrainMatrix[i].w;
         this.debugTerrainMatrix[i].y = this.debugTerrainMatrix[i].y / this.debugTerrainMatrix[i].w;
         this.debugTerrainMatrix[i].z = this.debugTerrainMatrix[i].z / this.debugTerrainMatrix[i].w;
         this.debugTerrainMatrix[i].w = 1.0F;
      }
   }

   public int getCountChunksToUpdate() {
      return this.chunksToUpdate.size();
   }

   public void createBindEntityOutlineFbs(int n, int n2) {
      if (OpenGlHelper.shadersSupported && this.entityOutlineShader != null) {
         this.entityOutlineShader.createBindFramebuffers(n, n2);
      }
   }

   private boolean isPositionInRenderChunk(int n, int n2, int n3, RenderChunk renderChunk) {
      BlockPos position = renderChunk.getPosition();
      return MathHelper.abs_int(n - position.getX()) <= 16 && MathHelper.abs_int(n2 - position.getY()) <= 16 && MathHelper.abs_int(n3 - position.getZ()) <= 16;
   }

   public Vector3f getViewVector(Entity entity, double d) {
      float f = (float)((double)entity.prevRotationPitch + (double)(entity.rotationPitch - entity.prevRotationPitch) * d);
      float f2 = (float)((double)entity.prevRotationYaw + (double)(entity.rotationYaw - entity.prevRotationYaw) * d);
      if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 2) {
         f += 180.0F;
      }

      float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
      float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
      float f5 = -MathHelper.cos(-f * 0.017453292F);
      float f6 = MathHelper.sin(-f * 0.017453292F);
      return new Vector3f(f4 * f5, f6, f3 * f5);
   }

   private void renderSky(WorldRenderer worldRenderer, float f, boolean flag) {
      worldRenderer.begin(7, DefaultVertexFormats.POSITION);
      int n = (this.renderDistance / 64 + 1) * 64 + 64;

      for (int i = -n; i <= n; i += 64) {
         for (int j = -n; j <= n; j += 64) {
            float f2 = (float)i;
            float f3 = (float)(i + 64);
            if (flag) {
               f3 = (float)i;
               f2 = (float)(i + 64);
            }

            worldRenderer.pos((double)f2, (double)f, (double)j).endVertex();
            worldRenderer.pos((double)f3, (double)f, (double)j).endVertex();
            worldRenderer.pos((double)f3, (double)f, (double)(j + 64)).endVertex();
            worldRenderer.pos((double)f2, (double)f, (double)(j + 64)).endVertex();
         }
      }
   }

   public void playAuxSFX(EntityPlayer player, int n, BlockPos pos, int n2) {
      Random rand = this.theWorld.rand;
      switch (n) {
         case 1000:
            this.theWorld.playSoundAtPos(pos, "random.click", 1.0F, 1.0F, false);
            break;
         case 1001:
            this.theWorld.playSoundAtPos(pos, "random.click", 1.0F, 1.2F, false);
            break;
         case 1002:
            this.theWorld.playSoundAtPos(pos, "random.bow", 1.0F, 1.2F, false);
            break;
         case 1003:
            this.theWorld.playSoundAtPos(pos, "random.door_open", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1004:
            this.theWorld.playSoundAtPos(pos, "random.fizz", 0.5F, 2.6F + (rand.nextFloat() - rand.nextFloat()) * 0.8F, false);
            break;
         case 1005:
            if (Item.getItemById(n2) instanceof ItemRecord) {
               this.theWorld.playRecord(pos, "records." + ((ItemRecord)Item.getItemById(n2)).recordName);
            } else {
               this.theWorld.playRecord(pos, (String)null);
            }
            break;
         case 1006:
            this.theWorld.playSoundAtPos(pos, "random.door_close", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1007:
            this.theWorld.playSoundAtPos(pos, "mob.ghast.charge", 10.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1008:
            this.theWorld.playSoundAtPos(pos, "mob.ghast.fireball", 10.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1009:
            this.theWorld.playSoundAtPos(pos, "mob.ghast.fireball", 2.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1010:
            this.theWorld.playSoundAtPos(pos, "mob.zombie.wood", 2.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1011:
            this.theWorld.playSoundAtPos(pos, "mob.zombie.metal", 2.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1012:
            this.theWorld.playSoundAtPos(pos, "mob.zombie.woodbreak", 2.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1014:
            this.theWorld.playSoundAtPos(pos, "mob.wither.shoot", 2.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1015:
            this.theWorld.playSoundAtPos(pos, "mob.bat.takeoff", 0.05F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1016:
            this.theWorld.playSoundAtPos(pos, "mob.zombie.infect", 2.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1017:
            this.theWorld.playSoundAtPos(pos, "mob.zombie.unfect", 2.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1020:
            this.theWorld.playSoundAtPos(pos, "random.anvil_break", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1021:
            this.theWorld.playSoundAtPos(pos, "random.anvil_use", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1022:
            this.theWorld.playSoundAtPos(pos, "random.anvil_land", 0.3F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 2000:
            int n3 = n2 % 3 - 1;
            int n4 = n2 / 3 % 3 - 1;
            double d = (double)pos.getX() + (double)n3 * 0.6 + 0.5;
            double d2 = (double)pos.getY() + 0.5;
            double d3 = (double)pos.getZ() + (double)n4 * 0.6 + 0.5;

            for (int i = 0; i < 10; i++) {
               double d4 = rand.nextDouble() * 0.2 + 0.01;
               double d5 = d + (double)n3 * 0.01 + (rand.nextDouble() - 0.5) * (double)n4 * 0.5;
               double d6 = d2 + (rand.nextDouble() - 0.5) * 0.5;
               double d7 = d3 + (double)n4 * 0.01 + (rand.nextDouble() - 0.5) * (double)n3 * 0.5;
               double d8 = (double)n3 * d4 + rand.nextGaussian() * 0.01;
               double d9 = -0.03 + rand.nextGaussian() * 0.01;
               double d10 = (double)n4 * d4 + rand.nextGaussian() * 0.01;
               this.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d5, d6, d7, d8, d9, d10, new int[0]);
            }

            return;
         case 2001:
            Block blockById = Block.getBlockById(n2 & 4095);
            if (blockById.getMaterial() != Material.air) {
               this.mc
                  .getSoundHandler()
                  .playSound(
                     new PositionedSoundRecord(
                        new ResourceLocation(blockById.stepSound.getBreakSound()),
                        (blockById.stepSound.getVolume() + 1.0F) / 2.0F,
                        blockById.stepSound.getFrequency() * 0.8F,
                        (float)pos.getX() + 0.5F,
                        (float)pos.getY() + 0.5F,
                        (float)pos.getZ() + 0.5F
                     )
                  );
            }

            if (!((Module)ClientManagers.moduleManager.noBlockParticles).isToggled()) {
               this.mc.effectRenderer.addBlockDestroyEffects(pos, blockById.getStateFromMeta(n2 >> 12 & 0xFF));
            }
            break;
         case 2002:
            double x = (double)pos.getX();
            double y = (double)pos.getY();
            double z = (double)pos.getZ();

            for (int j = 0; j < 8; j++) {
               this.spawnParticle(
                  EnumParticleTypes.ITEM_CRACK,
                  x,
                  y,
                  z,
                  rand.nextGaussian() * 0.15,
                  rand.nextDouble() * 0.2,
                  rand.nextGaussian() * 0.15,
                  new int[]{Item.getIdFromItem(Items.potionitem), n2}
               );
            }

            int colorFromDamage = Items.potionitem.getColorFromDamage(n2);
            float f = (float)(colorFromDamage >> 16 & 0xFF) / 255.0F;
            float f2 = (float)(colorFromDamage >> 8 & 0xFF) / 255.0F;
            float f3 = (float)(colorFromDamage >> 0 & 0xFF) / 255.0F;
            EnumParticleTypes spell = EnumParticleTypes.SPELL;
            if (Items.potionitem.isEffectInstant(n2)) {
               spell = EnumParticleTypes.SPELL_INSTANT;
            }

            for (int k = 0; k < 100; k++) {
               double d11 = rand.nextDouble() * 4.0;
               double d12 = rand.nextDouble() * 3.141592653589793 * 2.0;
               double d13 = Math.cos(d12) * d11;
               double d14 = 0.01 + rand.nextDouble() * 0.5;
               double d15 = Math.sin(d12) * d11;
               EntityFX entityFX = this.spawnEntityFX(
                  spell.getParticleID(), spell.getShouldIgnoreRange(), x + d13 * 0.1, y + 0.3, z + d15 * 0.1, d13, d14, d15, new int[0]
               );
               if (entityFX != null) {
                  float f4 = 0.75F + rand.nextFloat() * 0.25F;
                  entityFX.setRBGColorF(f * f4, f2 * f4, f3 * f4);
                  entityFX.multiplyVelocity((float)d11);
               }
            }

            this.theWorld.playSoundAtPos(pos, "game.potion.smash", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 2003:
            double d16 = (double)pos.getX() + 0.5;
            double y2 = (double)pos.getY();
            double d17 = (double)pos.getZ() + 0.5;

            for (int m = 0; m < 8; m++) {
               this.spawnParticle(
                  EnumParticleTypes.ITEM_CRACK,
                  d16,
                  y2,
                  d17,
                  rand.nextGaussian() * 0.15,
                  rand.nextDouble() * 0.2,
                  rand.nextGaussian() * 0.15,
                  new int[]{Item.getIdFromItem(Items.ender_eye)}
               );
            }

            for (double idx = 0.0; idx < 6.283185307179586; idx += 0.15707963267948966) {
               this.spawnParticle(
                  EnumParticleTypes.PORTAL,
                  d16 + Math.cos(idx) * 5.0,
                  y2 - 0.4,
                  d17 + Math.sin(idx) * 5.0,
                  Math.cos(idx) * -5.0,
                  0.0,
                  Math.sin(idx) * -5.0,
                  new int[0]
               );
               this.spawnParticle(
                  EnumParticleTypes.PORTAL,
                  d16 + Math.cos(idx) * 5.0,
                  y2 - 0.4,
                  d17 + Math.sin(idx) * 5.0,
                  Math.cos(idx) * -7.0,
                  0.0,
                  Math.sin(idx) * -7.0,
                  new int[0]
               );
            }

            return;
         case 2004:
            for (int index = 0; index < 20; index++) {
               double d18 = (double)pos.getX() + 0.5 + ((double)this.theWorld.rand.nextFloat() - 0.5) * 2.0;
               double d19 = (double)pos.getY() + 0.5 + ((double)this.theWorld.rand.nextFloat() - 0.5) * 2.0;
               double d20 = (double)pos.getZ() + 0.5 + ((double)this.theWorld.rand.nextFloat() - 0.5) * 2.0;
               this.theWorld.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d18, d19, d20, 0.0, 0.0, 0.0, new int[0]);
               this.theWorld.spawnParticle(EnumParticleTypes.FLAME, d18, d19, d20, 0.0, 0.0, 0.0, new int[0]);
            }

            return;
         case 2005:
            ItemDye.spawnBonemealParticles(this.theWorld, pos, n2);
      }
   }

   private void generateStars() {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      if (this.starVBO != null) {
         this.starVBO.deleteGlBuffers();
      }

      if (this.starGLCallList >= 0) {
         GLAllocation.deleteDisplayLists(this.starGLCallList);
         this.starGLCallList = -1;
      }

      if (this.vboEnabled) {
         this.starVBO = new VertexBuffer(this.vertexBufferFormat);
         this.renderStars(worldRenderer);
         worldRenderer.finishDrawing();
         worldRenderer.reset();
         this.starVBO.bufferData(worldRenderer.getByteBuffer());
      } else {
         this.starGLCallList = GLAllocation.generateDisplayLists(1);
         GlStateManager.pushMatrix();
         GL11.glNewList(this.starGLCallList, 4864);
         this.renderStars(worldRenderer);
         instance.draw();
         GL11.glEndList();
         GlStateManager.popMatrix();
      }
   }

   private void generateSky() {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      if (this.skyVBO != null) {
         this.skyVBO.deleteGlBuffers();
      }

      if (this.glSkyList >= 0) {
         GLAllocation.deleteDisplayLists(this.glSkyList);
         this.glSkyList = -1;
      }

      if (this.vboEnabled) {
         this.skyVBO = new VertexBuffer(this.vertexBufferFormat);
         this.renderSky(worldRenderer, 16.0F, false);
         worldRenderer.finishDrawing();
         worldRenderer.reset();
         this.skyVBO.bufferData(worldRenderer.getByteBuffer());
      } else {
         this.glSkyList = GLAllocation.generateDisplayLists(1);
         GL11.glNewList(this.glSkyList, 4864);
         this.renderSky(worldRenderer, 16.0F, false);
         instance.draw();
         GL11.glEndList();
      }
   }

   public void checkFirstWorldLoad() {
      if (this.firstWorldLoad) {
         this.loadRenderers();
         this.firstWorldLoad = false;
      }
   }

   public void pauseChunkUpdates() {
      if (this.renderDispatcher != null) {
         this.renderDispatcher.pauseChunkUpdates();
      }
   }

   public void updateClouds() {
      if (Config.isShaderPackLoaded()) {
         if (Keyboard.isKeyDown(61) && Keyboard.isKeyDown(24)) {
            GuiShaderOptions guiShaderOptions = new GuiShaderOptions((GuiScreen)null, Config.getGameSettings());
            Config.getMc().displayGuiScreen((GuiScreen)guiShaderOptions);
         }

         if (Keyboard.isKeyDown(61) && Keyboard.isKeyDown(19)) {
            Shaders.uninit();
            Shaders.loadShaderPack();
         }
      }

      this.cloudTickCounter++;
      if (this.cloudTickCounter % 20 == 0) {
         this.cleanupDamagedBlocks(this.damagedBlocks.values().iterator());
      }
   }

   public int getCountEntitiesRendered() {
      return this.countEntitiesRendered;
   }

   public void setupTerrain(Entity entity, double d, ICamera camera, int n, boolean flag) {
      if (this.mc.gameSettings.renderDistanceChunks != this.renderDistanceChunks) {
         this.loadRenderers();
      }

      this.theWorld.theProfiler.startSection("camera");
      double d2 = entity.posX - this.frustumUpdatePosX;
      double d3 = entity.posY - this.frustumUpdatePosY;
      double d4 = entity.posZ - this.frustumUpdatePosZ;
      if (this.frustumUpdatePosChunkX != entity.chunkCoordX || this.frustumUpdatePosChunkY != entity.chunkCoordY || this.frustumUpdatePosChunkZ != entity.chunkCoordZ || d2 * d2 + d3 * d3 + d4 * d4 > 16.0) {
         this.frustumUpdatePosX = entity.posX;
         this.frustumUpdatePosY = entity.posY;
         this.frustumUpdatePosZ = entity.posZ;
         this.frustumUpdatePosChunkX = entity.chunkCoordX;
         this.frustumUpdatePosChunkY = entity.chunkCoordY;
         this.frustumUpdatePosChunkZ = entity.chunkCoordZ;
         this.viewFrustum.updateChunkPositions(entity.posX, entity.posZ);
      }

      if (Config.isDynamicLights()) {
         DynamicLights.update(this);
      }

      this.theWorld.theProfiler.endStartSection("renderlistcamera");
      double d5 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * d;
      double d6 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * d;
      double d7 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * d;
      this.renderContainer.initialize(d5, d6, d7);
      this.theWorld.theProfiler.endStartSection("cull");
      if (this.debugFixedClippingHelper != null) {
         Frustum frustum = new Frustum(this.debugFixedClippingHelper);
         frustum.setPosition(this.debugTerrainFrustumPosition.x, this.debugTerrainFrustumPosition.y, this.debugTerrainFrustumPosition.z);
         camera = frustum;
      }

      this.mc.mcProfiler.endStartSection("culling");
      int n2 = MathHelper.floor_double(d5);
      int n3 = MathHelper.floor_double(d6 + (double)entity.getEyeHeight());
      int n4 = MathHelper.floor_double(d7);
      RenderChunk renderChunk = this.viewFrustum.getRenderChunk(n2, n3, n4);
      this.displayListEntitiesDirty = this.displayListEntitiesDirty
         || !this.chunksToUpdate.isEmpty()
         || entity.posX != this.lastViewEntityX
         || entity.posY != this.lastViewEntityY
         || entity.posZ != this.lastViewEntityZ
         || (double)entity.rotationPitch != this.lastViewEntityPitch
         || (double)entity.rotationYaw != this.lastViewEntityYaw;
      this.lastViewEntityX = entity.posX;
      this.lastViewEntityY = entity.posY;
      this.lastViewEntityZ = entity.posZ;
      this.lastViewEntityPitch = (double)entity.rotationPitch;
      this.lastViewEntityYaw = (double)entity.rotationYaw;
      boolean flag2 = this.debugFixedClippingHelper != null;
      this.mc.mcProfiler.endStartSection("update");
      Lagometer.timerVisibility.start();
      int countLoadedChunks = this.getCountLoadedChunks();
      if (countLoadedChunks != this.countLoadedChunksPrev) {
         this.countLoadedChunksPrev = countLoadedChunks;
         this.displayListEntitiesDirty = true;
      }

      int n5 = 256;
      if (!ChunkVisibility.getCounter()) {
         this.displayListEntitiesDirty = true;
      }

      if (!flag2 && this.displayListEntitiesDirty && Config.isIntegratedServerRunning()) {
         n5 = ChunkVisibility.getMaxChunkY(this.theWorld, entity, this.renderDistanceChunks);
      }

      RenderChunk renderChunk2 = this.viewFrustum.getRenderChunk(new BlockPos(entity.posX, entity.posY, entity.posZ));
      if (Shaders.isShadowPass) {
         this.renderInfos = this.renderInfosShadow;
         this.renderInfosEntities = this.renderInfosEntitiesShadow;
         this.renderInfosTileEntities = this.renderInfosTileEntitiesShadow;
         if (!flag2 && this.displayListEntitiesDirty) {
            this.clearRenderInfos();
            if (renderChunk2 != null && renderChunk2.getPosition().getY() > n5) {
               this.renderInfosEntities.add(renderChunk2.getRenderInfo());
            }

            Iterator it = ShadowUtils.makeShadowChunkIterator(this.theWorld, d, entity, this.renderDistanceChunks, this.viewFrustum);

            while (it.hasNext()) {
               RenderChunk renderChunk3 = (RenderChunk)it.next();
               if (renderChunk3 != null && renderChunk3.getPosition().getY() <= n5) {
                  RenderGlobal.ContainerLocalRenderInformation renderInfo = renderChunk3.getRenderInfo();
                  if (!renderChunk3.compiledChunk.isEmpty() || renderChunk3.isNeedsUpdate()) {
                     this.renderInfos.add(renderInfo);
                  }

                  if (renderChunk3.getChunk().hasEntities) {
                     this.renderInfosEntities.add(renderInfo);
                  }

                  if (!renderChunk3.getCompiledChunk().getTileEntities().isEmpty()) {
                     this.renderInfosTileEntities.add(renderInfo);
                  }
               }
            }
         }
      } else {
         this.renderInfos = this.renderInfosNormal;
         this.renderInfosEntities = this.renderInfosEntitiesNormal;
         this.renderInfosTileEntities = this.renderInfosTileEntitiesNormal;
      }

      if (!flag2 && this.displayListEntitiesDirty && !Shaders.isShadowPass) {
         this.displayListEntitiesDirty = false;
         this.clearRenderInfos();
         this.visibilityDeque.clear();
         Deque visibilityDeque2 = this.visibilityDeque;
         boolean renderChunksMany = this.mc.renderChunksMany;
         if (renderChunk != null && renderChunk.getPosition().getY() <= n5) {
            boolean flag3 = false;
            RenderGlobal.ContainerLocalRenderInformation information = new RenderGlobal.ContainerLocalRenderInformation(renderChunk, (EnumFacing)null, 0);
            Set set = SET_ALL_FACINGS;
            if (set.size() == 1) {
               Vector3f viewVector = this.getViewVector(entity, d);
               set.remove(EnumFacing.getFacingFromVector(viewVector.x, viewVector.y, viewVector.z).getOpposite());
            }

            if (set.isEmpty()) {
               flag3 = true;
            }

            if (flag3 && !flag) {
               this.renderInfos.add(information);
            } else {
               if (flag && this.theWorld.getBlockState2(n2, n3, n4).getBlock().isOpaqueCube()) {
                  renderChunksMany = false;
               }

               renderChunk.setFrameIndex(n);
               visibilityDeque2.add(information);
            }
         } else {
            int n6 = n3 > 0 ? Math.min(n5, 248) : 8;
            if (renderChunk2 != null) {
               this.renderInfosEntities.add(renderChunk2.getRenderInfo());
            }

            for (int i = -this.renderDistanceChunks; i <= this.renderDistanceChunks; i++) {
               for (int j = -this.renderDistanceChunks; j <= this.renderDistanceChunks; j++) {
                  RenderChunk renderChunk4 = this.viewFrustum.getRenderChunk((i << 4) + 8, n6, (j << 4) + 8);
                  if (renderChunk4 != null && renderChunk4.isBoundingBoxInFrustum((ICamera)camera, n)) {
                     renderChunk4.setFrameIndex(n);
                     RenderGlobal.ContainerLocalRenderInformation renderInfo2 = renderChunk4.getRenderInfo();
                     RenderGlobal.ContainerLocalRenderInformation.callInitialize(renderInfo2, null, 0);
                     visibilityDeque2.add(renderInfo2);
                  }
               }
            }
         }

         this.mc.mcProfiler.startSection("iteration");
         boolean isFogOn = Config.isFogOn();

         while (!visibilityDeque2.isEmpty()) {
            RenderGlobal.ContainerLocalRenderInformation information2 = (RenderGlobal.ContainerLocalRenderInformation)visibilityDeque2.poll();
            RenderChunk renderChunk5 = information2.renderChunk;
            EnumFacing facing = information2.facing;
            CompiledChunk compiledChunk = renderChunk5.compiledChunk;
            if (!compiledChunk.isEmpty() || renderChunk5.isNeedsUpdate()) {
               this.renderInfos.add(information2);
            }

            if (renderChunk5.getChunk().hasEntities) {
               this.renderInfosEntities.add(information2);
            }

            if (!compiledChunk.getTileEntities().isEmpty()) {
               this.renderInfosTileEntities.add(information2);
            }

            for (EnumFacing facing2 : renderChunksMany ? ChunkVisibility.getFacingsNotOpposite(information2.counter) : EnumFacing.VALUES) {
               if (!renderChunksMany || facing == null || compiledChunk.isVisible(facing.getOpposite(), facing2)) {
                  RenderChunk renderChunkOffset = this.getRenderChunkOffset(n2, n4, renderChunk5, facing2, isFogOn, n5);
                  if (renderChunkOffset != null && renderChunkOffset.setFrameIndex(n) && renderChunkOffset.isBoundingBoxInFrustum((ICamera)camera, n)) {
                     int n7 = information2.counter | 1 << facing2.ordinal();
                     RenderGlobal.ContainerLocalRenderInformation renderInfo3 = renderChunkOffset.getRenderInfo();
                     RenderGlobal.ContainerLocalRenderInformation.callInitialize(renderInfo3, facing2, n7);
                     visibilityDeque2.add(renderInfo3);
                  }
               }
            }
         }

         this.mc.mcProfiler.endSection();
      }

      this.mc.mcProfiler.endStartSection("captureFrustum");
      if (this.debugFixTerrainFrustum) {
         this.fixTerrainFrustum(d5, d6, d7);
         this.debugFixTerrainFrustum = false;
      }

      Lagometer.timerVisibility.end();
      if (Shaders.isShadowPass) {
         Shaders.endSection();
      } else {
         this.mc.mcProfiler.endStartSection("rebuildNear");
         this.renderDispatcher.clearChunkUpdates();
         ObjectLinkedOpenHashSet chunksToUpdate2 = this.chunksToUpdate;
         this.chunksToUpdate = new ObjectLinkedOpenHashSet();
         Lagometer.timerChunkUpdate.start();

         for (RenderGlobal.ContainerLocalRenderInformation information3 : (Iterable<RenderGlobal.ContainerLocalRenderInformation>)(this.renderInfos)) {
            RenderChunk renderChunk6 = information3.renderChunk;
            if (renderChunk6.isNeedsUpdate() || chunksToUpdate2.contains(renderChunk6)) {
               this.displayListEntitiesDirty = true;
               BlockPos position = renderChunk6.getPosition();
               if (!(distanceSq(
                     (double)n2, (double)n3, (double)n4, (double)(position.getX() + 8), (double)(position.getY() + 8), (double)(position.getZ() + 8)
                  )
                  < 768.0)) {
                  this.chunksToUpdate.add(renderChunk6);
               } else if (!renderChunk6.isPlayerUpdate()) {
                  this.chunksToUpdateForced.add(renderChunk6);
               } else {
                  this.mc.mcProfiler.startSection("build near");
                  this.renderDispatcher.updateChunkNow(renderChunk6);
                  renderChunk6.setNeedsUpdate(false);
                  this.mc.mcProfiler.endSection();
               }
            }
         }

         Lagometer.timerChunkUpdate.end();
         this.chunksToUpdate.addAll(chunksToUpdate2);
         this.mc.mcProfiler.endSection();
      }
   }

   public void setWorldAndLoadRenderers(WorldClient world) {
      if (this.theWorld != null) {
         this.theWorld.removeWorldAccess(this);
      }

      this.frustumUpdatePosX = 5.0E-324;
      this.frustumUpdatePosY = 5.0E-324;
      this.frustumUpdatePosZ = 5.0E-324;
      this.frustumUpdatePosChunkX = -2147483648;
      this.frustumUpdatePosChunkY = -2147483648;
      this.frustumUpdatePosChunkZ = -2147483648;
      this.renderManager.set(world);
      this.theWorld = world;
      if (Config.isDynamicLights()) {
         DynamicLights.clear();
      }

      ChunkVisibility.reset();
      this.worldChunkProvider = null;
      this.worldChunkProviderMap = null;
      this.renderEnv.reset((IBlockState)null, (BlockPos)null);
      Shaders.checkWorldChanged(this.theWorld);
      if (world != null) {
         world.addWorldAccess(this);
         this.loadRenderers();
      } else {
         this.chunksToUpdate.clear();
         this.clearRenderInfos();
         if (this.viewFrustum != null) {
            this.viewFrustum.deleteGlResources();
         }

         this.viewFrustum = null;
      }
   }

public static class ValueCallable implements Callable {
      public final double xCoord;
      public final double yCoord;
      public final double zCoord;
      public final RenderGlobal renderGlobal;

      @Override
      public Object call() throws Exception {
         return this.getParticlePosition();
      }

      public String getParticlePosition() throws Exception {
         return CrashReportCategory.getCoordinateInfo(this.xCoord, this.yCoord, this.zCoord);
      }

      public ValueCallable(RenderGlobal renderGlobal2, double d, double d2, double d3) {
         this.renderGlobal = renderGlobal2;
         this.xCoord = d;
         this.yCoord = d2;
         this.zCoord = d3;
      }
   }

public static class ContainerLocalRenderInformation {
      public RenderChunk renderChunk;
      public EnumFacing facing;
      public int counter;

      public ContainerLocalRenderInformation(RenderChunk renderChunk2, EnumFacing facing2, int n) {
         this.renderChunk = renderChunk2;
         this.facing = facing2;
         this.counter = n;
      }

      public static void callInitialize(ContainerLocalRenderInformation information, EnumFacing facing2, int n) {
         information.initialize(facing2, n);
      }

      public void setDirection(byte b, EnumFacing facing2) {
         this.counter = this.counter | b | 1 << facing2.ordinal();
      }

      public boolean hasDirection(EnumFacing facing2) {
         return (this.counter & 1 << facing2.ordinal()) > 0;
      }

      private void initialize(EnumFacing facing2, int n) {
         this.facing = facing2;
         this.counter = n;
      }
   }
}
