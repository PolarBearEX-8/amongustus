package net.minecraft.client.renderer;

import augustus.modes.render.EspRenderMode;
import augustus.modules.render.ItemESP;
import client.ClientGlobals;
import client.event.ContextListener;
import client.event.EventLiquidCollisionCallback;
import client.event.MultiplayerChunkCacheListener;
import client.event.PostUpdateObserver;
import client.event.PreMotionConsumer;
import client.module.Module;
import client.render.EntityEspRenderer;
import client.render.RenderGlobalEntry;
import client.render.RenderManager;
import client.render.WorldRenderer;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.BlockNoneType;
import client.setting.BlockSolidType;
import client.setting.CombatValueProperty;
import client.setting.RenderBooleanProperty;
import client.util.AlertNotificationSound;
import client.util.AltLoggerEntry;
import client.util.AndFSManager;
import client.util.AuxiliaryLoggerEntry;
import client.util.BaseIBlockStateEntry;
import client.util.BezierAggregator;
import client.util.BitSetEntry;
import client.util.BitSetHolder;
import client.util.BuildManager;
import client.util.CommonDoubleEntry;
import client.util.CurveRegistry;
import client.util.CurveSerializer;
import client.util.CustomFloatArrayEntry;
import client.util.DefaultVertexFormats;
import client.util.DonTKnowHowToSearchForAbstractSet;
import client.util.DoubleEntryAdapter;
import client.util.EnumFacingArrayEntry;
import client.util.ExtraDoubleEntry;
import client.util.IBlockState;
import client.util.InternalFloatEntry;
import client.util.InternalMinecraftEntry;
import client.util.InterpolationEvaluator;
import client.util.ListenerAdapter21;
import client.util.ListenerAdapter27;
import client.util.ListenerController;
import client.util.LoadingManager;
import client.util.MBSManager;
import client.util.McpatcherSkyWorldManager;
import client.util.MovingObjectType;
import client.util.NormalManager;
import client.util.OptifineRandomManager;
import client.util.PassManager;
import client.util.PreEndHandManager;
import client.util.RayTraceSerializer;
import client.util.RotationBridge;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.ServiceStringEntry;
import client.util.SharedFloatEntry;
import client.util.SmoothRanker;
import client.util.SpecialDoubleEntry;
import client.util.SplineDirector;
import client.util.StandardMapEntry;
import client.util.SupplementalResourceLocationEntryAdapter;
import client.util.TargetEstimator;
import client.util.TertiaryLoggerHolder;
import client.util.VClipCollisionFilter;
import client.util.VectorMatcher;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonSyntaxException;
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
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.AuxiliaryBlockPosEntry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockTrappedChest;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityWitherskull;
import net.minecraft.entity.TertiaryEntityAdapter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.AuxiliaryItemArrayEntry12Entry;
import net.minecraft.item.DiamondChestplate;
import net.minecraft.item.DiamondEssence;
import net.minecraft.item.IronShard;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemRecordWait;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class RenderGlobal implements IResourceManagerReloadListener, ContextListener {
   public static final Logger logger = LogManager.getLogger();
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/environment/moon_phases.png");
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/environment/sun.png");
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/environment/clouds.png");
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/environment/end_sky.png");
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/misc/forcefield.png");
   public final Minecraft mc;
   public final TextureManager textureManager;
   public final RenderManager renderManager;
   public WorldClient worldClient;
   public Set set2 = Sets.newLinkedHashSet();
   public List list8 = Lists.newArrayListWithCapacity(69696);
   public final Set set5 = Sets.newHashSet();
   public RenderGlobalEntry renderGlobalEntry;
   public int count2 = -1;
   public int count16 = -1;
   public int count11 = -1;
   public AuxiliaryLoggerEntry auxiliaryLoggerEntry;
   public DiamondChestplate diamondChestplate;
   public DiamondChestplate diamondChestplate2;
   public DiamondChestplate diamondChestplate3;
   public int count15;
   public final Map map = Maps.newHashMap();
   public final Map map2 = Maps.newHashMap();
   public final ServiceStringEntry[] serviceStringEntryArray = new ServiceStringEntry[10];
   public Framebuffer framebuffer;
   public PassManager passManager;
   public double value10 = Double.MIN_VALUE;
   public double value11 = Double.MIN_VALUE;
   public double value2 = Double.MIN_VALUE;
   public int count6 = Integer.MIN_VALUE;
   public int count13 = Integer.MIN_VALUE;
   public int count = Integer.MIN_VALUE;
   public double value = Double.MIN_VALUE;
   public double value6 = Double.MIN_VALUE;
   public double value9 = Double.MIN_VALUE;
   public double value4 = Double.MIN_VALUE;
   public double value5 = Double.MIN_VALUE;
   public final TertiaryLoggerHolder tertiaryLoggerHolder = new TertiaryLoggerHolder();
   public ExtraDoubleEntry extraDoubleEntry;
   public int count10 = -1;
   public int count12 = 2;
   public int count5;
   public int indexOffset;
   public int count4;
   public boolean enabled2 = false;
   public CustomFloatArrayEntry customFloatArrayEntry;
   public final Vector4f[] vector4fArray = new Vector4f[8];
   public final CommonDoubleEntry commonDoubleEntry = new CommonDoubleEntry();
   public boolean enabled3 = false;
   public PostUpdateObserver postUpdateObserver;
   public double value8;
   public double value3;
   public double value7;
   public boolean enabled6 = true;
   public InternalMinecraftEntry internalMinecraftEntry;
   public Entity entity;
   public Set set4 = new LinkedHashSet();
   public Set set = new LinkedHashSet();
   public Deque deque = new ArrayDeque();
   public List list4 = new ArrayList(1024);
   public List list9 = new ArrayList(1024);
   public List list6 = new ArrayList(1024);
   public List list = new ArrayList(1024);
   public List list7 = new ArrayList(1024);
   public List list3 = new ArrayList(1024);
   public List list5 = new ArrayList(1024);
   public List list2 = new ArrayList(1024);
   public int count9 = 0;
   public int count3 = 0;
   public static final Set set3 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(EnumFacing.VALUES)));
   public int count8;
   public IChunkProvider iChunkProvider = null;
   public InternalFloatEntry internalFloatEntry = null;
   public int count7 = 0;
   public BaseIBlockStateEntry baseIBlockStateEntry = new BaseIBlockStateEntry(Blocks.air.getDefaultState(), new BlockPos(0, 0, 0));
   public boolean enabled = false;
   public boolean enabled4 = false;
   public boolean enabled5 = false;
   public static int count14 = 0;

   private static double computeValue(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      return (posX - posX4) * (posX - posX4) + (posX2 - posY) * (posX2 - posY) + (posX3 - posZ) * (posX3 - posZ);
   }

   private void applyWorldRenderer(WorldRenderer worldRenderer, float factor, boolean flag) {
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry1);
      int count17 = (this.count9 / 64 + 1) * 64 + 64;

      for (int index = -count17; index <= count17; index += 64) {
         for (int index2 = -count17; index2 <= count17; index2 += 64) {
            float factor2 = (float)index;
            float factor3 = (float)(index + 64);
            if (flag) {
               factor3 = (float)index;
               factor2 = (float)(index + 64);
            }

            worldRenderer.getWorldRenderer((double)factor2, (double)factor, (double)index2).updateState();
            worldRenderer.getWorldRenderer((double)factor3, (double)factor, (double)index2).updateState();
            worldRenderer.getWorldRenderer((double)factor3, (double)factor, (double)(index2 + 64)).updateState();
            worldRenderer.getWorldRenderer((double)factor2, (double)factor, (double)(index2 + 64)).updateState();
         }
      }
   }

   public void updateState16() {
      this.set2.clear();
      this.tertiaryLoggerHolder.updateState();
   }

   private void applyDouble(double posX, double posY, double posZ) {
      this.customFloatArrayEntry = new DiamondEssence();
      ((DiamondEssence)this.customFloatArrayEntry).updateState();
      SmoothRanker smoothRanker = new SmoothRanker(this.customFloatArrayEntry.factorArray2);
      smoothRanker.transpose();
      SmoothRanker smoothRanker2 = new SmoothRanker(this.customFloatArrayEntry.factorArray);
      smoothRanker2.transpose();
      SmoothRanker smoothRanker3 = new SmoothRanker();
      SmoothRanker.mul(smoothRanker2, smoothRanker, smoothRanker3);
      smoothRanker3.invert();
      this.commonDoubleEntry.value3 = posX;
      this.commonDoubleEntry.value2 = posY;
      this.commonDoubleEntry.value = posZ;
      this.vector4fArray[0] = new Vector4f(-1.0F, -1.0F, -1.0F, 1.0F);
      this.vector4fArray[1] = new Vector4f(1.0F, -1.0F, -1.0F, 1.0F);
      this.vector4fArray[2] = new Vector4f(1.0F, 1.0F, -1.0F, 1.0F);
      this.vector4fArray[3] = new Vector4f(-1.0F, 1.0F, -1.0F, 1.0F);
      this.vector4fArray[4] = new Vector4f(-1.0F, -1.0F, 1.0F, 1.0F);
      this.vector4fArray[5] = new Vector4f(1.0F, -1.0F, 1.0F, 1.0F);
      this.vector4fArray[6] = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.vector4fArray[7] = new Vector4f(-1.0F, 1.0F, 1.0F, 1.0F);

      for (int index = 0; index < 8; index++) {
         SmoothRanker.transform(smoothRanker3, this.vector4fArray[index], this.vector4fArray[index]);
         this.vector4fArray[index].x = this.vector4fArray[index].x / this.vector4fArray[index].w;
         this.vector4fArray[index].y = this.vector4fArray[index].y / this.vector4fArray[index].w;
         this.vector4fArray[index].z = this.vector4fArray[index].z / this.vector4fArray[index].w;
         this.vector4fArray[index].w = 1.0F;
      }
   }

   public int getCount() {
      return this.renderGlobalEntry.renderChunkArray.length;
   }

   private TertiaryEntityAdapter resolveTertiaryEntityAdapter(
      int count17, boolean flag, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      if (this.mc != null && this.mc.getRenderViewEntity() != null && this.mc.effectRenderer != null) {
         int indexOffset2 = this.mc.gameSettings.indexOffset134;
         if (indexOffset2 == 1 && this.worldClient.random.nextInt(3) == 0) {
            indexOffset2 = 2;
         }

         double offset = this.mc.getRenderViewEntity().posX - posX;
         double offset2 = this.mc.getRenderViewEntity().posY - posX2;
         double offset3 = this.mc.getRenderViewEntity().posZ - posX3;
         if (count17 == AttackEffectsExplosionNormalType.EXPLOSION_HUGE.getIndex() && !BuildManager.isActive21()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.EXPLOSION_LARGE.getIndex() && !BuildManager.isActive21()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.EXPLOSION_NORMAL.getIndex() && !BuildManager.isActive21()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.SUSPENDED.getIndex() && !BuildManager.isActive76()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.SUSPENDED_DEPTH.getIndex() && !BuildManager.isActive61()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.SMOKE_NORMAL.getIndex() && !BuildManager.isActive66()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.SMOKE_LARGE.getIndex() && !BuildManager.isActive66()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.SPELL_MOB.getIndex() && !BuildManager.isActive40()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.SPELL_MOB_AMBIENT.getIndex() && !BuildManager.isActive40()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.SPELL.getIndex() && !BuildManager.isActive40()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.SPELL_INSTANT.getIndex() && !BuildManager.isActive40()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.SPELL_WITCH.getIndex() && !BuildManager.isActive40()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.PORTAL.getIndex() && !BuildManager.isActive42()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.FLAME.getIndex() && !BuildManager.isActive17()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.REDSTONE.getIndex() && !BuildManager.isActive14()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.DRIP_WATER.getIndex() && !BuildManager.isActive41()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.DRIP_LAVA.getIndex() && !BuildManager.isActive41()) {
            return null;
         } else if (count17 == AttackEffectsExplosionNormalType.FIREWORKS_SPARK.getIndex() && !BuildManager.isActive()) {
            return null;
         } else {
            if (!flag) {
               double offset4 = 256.0;
               if (count17 == AttackEffectsExplosionNormalType.CRIT.getIndex()) {
                  offset4 = 38416.0;
               }

               if (offset * offset + offset2 * offset2 + offset3 * offset3 > offset4) {
                  return null;
               }

               if (indexOffset2 > 1) {
                  return null;
               }
            }

            TertiaryEntityAdapter tertiaryEntityAdapter = this.mc.effectRenderer.resolveTertiaryEntityAdapter(count17, posX, posX2, posX3, posX4, posY, posZ, countArray);
            if (count17 == AttackEffectsExplosionNormalType.WATER_BUBBLE.getIndex()) {
               LoadingManager.applyTertiaryEntityAdapter(tertiaryEntityAdapter, this.worldClient, posX, posX2, posX3, this.baseIBlockStateEntry);
            }

            if (count17 == AttackEffectsExplosionNormalType.WATER_SPLASH.getIndex()) {
               LoadingManager.applyTertiaryEntityAdapter(tertiaryEntityAdapter, this.worldClient, posX, posX2, posX3, this.baseIBlockStateEntry);
            }

            if (count17 == AttackEffectsExplosionNormalType.WATER_DROP.getIndex()) {
               LoadingManager.applyTertiaryEntityAdapter(tertiaryEntityAdapter, this.worldClient, posX, posX2, posX3, this.baseIBlockStateEntry);
            }

            if (count17 == AttackEffectsExplosionNormalType.TOWN_AURA.getIndex()) {
               LoadingManager.applyTertiaryEntityAdapter(tertiaryEntityAdapter);
            }

            if (count17 == AttackEffectsExplosionNormalType.PORTAL.getIndex()) {
               LoadingManager.applyTertiaryEntityAdapterByTertiaryEntityAd(tertiaryEntityAdapter);
            }

            if (count17 == AttackEffectsExplosionNormalType.REDSTONE.getIndex()) {
               LoadingManager.applyTertiaryEntityAdapter(tertiaryEntityAdapter, this.worldClient, posX, posX2, posX3);
            }

            return tertiaryEntityAdapter;
         }
      } else {
         return null;
      }
   }

   public void updateState() {
      if (this.worldClient != null) {
         this.enabled6 = true;
         Blocks.decayableManager2.setEnabled(BuildManager.isActive67());
         Blocks.decayableManager.setEnabled(BuildManager.isActive67());
         SharedFloatEntry.updateState();
         if (BuildManager.isActive28()) {
            TargetEstimator.updateState();
         }

         BitSetHolder.updateState();
         this.count10 = this.mc.gameSettings.indexOffset16;
         this.count9 = this.count10 * 16;
         this.count3 = this.count9 * this.count9;
         boolean enabled7 = this.enabled3;
         this.enabled3 = OpenGlHelper.isActive();
         if (enabled7 && !this.enabled3) {
            this.extraDoubleEntry = new DoubleEntryAdapter();
            this.postUpdateObserver = new ListenerController();
         } else if (!enabled7 && this.enabled3) {
            this.extraDoubleEntry = new SpecialDoubleEntry();
            this.postUpdateObserver = new CurveSerializer();
         }

         this.updateState9();
         this.updateState7();
         this.updateState18();
         if (this.renderGlobalEntry != null) {
            this.renderGlobalEntry.renderElement();
         }

         this.updateState16();
         synchronized (this.set5) {
            this.set5.clear();
         }

         this.renderGlobalEntry = new RenderGlobalEntry(this.worldClient, this.mc.gameSettings.indexOffset16, this, this.postUpdateObserver);
         if (this.worldClient != null) {
            Entity renderViewEntity = this.mc.getRenderViewEntity();
            if (renderViewEntity != null) {
               this.renderGlobalEntry.applyDouble(renderViewEntity.posX, renderViewEntity.posZ);
            }
         }

         this.count12 = 2;
      }

      if (this.mc.thePlayer == null) {
         this.enabled5 = true;
      }
   }

   public void applyFloat(float factor, int count17) {
      if (!BuildManager.isActive18() && this.mc.theWorld.secondaryFloatArrayEntry.isActive2()) {
         if (BuildManager.canExecute()) {
            PreEndHandManager.updateState9();
         }

         if (BuildManager.isActive9()) {
            this.applyFloatByFloat(factor, count17);
         } else {
            float factor2 = 0.0F;
            GlStateManager.disableAlpha();
            float factor3 = (float)(
               this.mc.getRenderViewEntity().lastTickPosY + (this.mc.getRenderViewEntity().posY - this.mc.getRenderViewEntity().lastTickPosY) * (double)factor2
            );
            Tessellator tessellator = Tessellator.getTessellator();
            WorldRenderer worldRenderer = tessellator.getWorldRenderer();
            this.textureManager.applyResourceLocation3(resourceLocation5);
            GlStateManager.disableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            Vec3 vec = this.worldClient.resolveVecByFloat(factor2);
            float posX = (float)vec.xCoord;
            float posY = (float)vec.yCoord;
            float posZ = (float)vec.zCoord;
            this.internalMinecraftEntry.applyVec3(false, this.count15, factor, vec);
            if (this.internalMinecraftEntry.isActive()) {
               this.internalMinecraftEntry.glNewList();
               if (count17 != 2) {
                  float factor4 = (posX * 30.0F + posY * 59.0F + posZ * 11.0F) / 100.0F;
                  posY = (posX * 30.0F + posY * 70.0F) / 100.0F;
                  posZ = (posX * 30.0F + posZ * 70.0F) / 100.0F;
                  posX = factor4;
               }

               double offset = (double)((float)this.count15 + factor2);
               double offset2 = this.mc.getRenderViewEntity().prevPosX
                  + (this.mc.getRenderViewEntity().posX - this.mc.getRenderViewEntity().prevPosX) * (double)factor2
                  + offset * 0.03F;
               double offset3 = this.mc.getRenderViewEntity().prevPosZ
                  + (this.mc.getRenderViewEntity().posZ - this.mc.getRenderViewEntity().prevPosZ) * (double)factor2;
               int count18 = MathHelper.ceiling_double_int(offset2 / 2048.0);
               int count19 = MathHelper.ceiling_double_int(offset3 / 2048.0);
               double offset4 = offset2 - (double)(count18 * 2048);
               double offset5 = offset3 - (double)(count19 * 2048);
               float factor5 = this.worldClient.secondaryFloatArrayEntry.getFactor() - factor3 + 0.33F + this.mc.gameSettings.scaleFactor60 * 128.0F;
               float factor6 = (float)(offset4 * 4.8828125E-4);
               float factor7 = (float)(offset5 * 4.8828125E-4);
               worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);

               for (int shortValue = -256; shortValue < 256; shortValue += 32) {
                  for (int shortValue2 = -256; shortValue2 < 256; shortValue2 += 32) {
                     worldRenderer.getWorldRenderer((double)(shortValue + 0), (double)factor5, (double)(shortValue2 + 32))
                        .getWorldRenderer6((double)((float)(shortValue + 0) * 4.8828125E-4F + factor6), (double)((float)(shortValue2 + 32) * 4.8828125E-4F + factor7))
                        .resolveWorldRenderer(posX, posY, posZ, 0.8F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(shortValue + 32), (double)factor5, (double)(shortValue2 + 32))
                        .getWorldRenderer6((double)((float)(shortValue + 32) * 4.8828125E-4F + factor6), (double)((float)(shortValue2 + 32) * 4.8828125E-4F + factor7))
                        .resolveWorldRenderer(posX, posY, posZ, 0.8F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(shortValue + 32), (double)factor5, (double)(shortValue2 + 0))
                        .getWorldRenderer6((double)((float)(shortValue + 32) * 4.8828125E-4F + factor6), (double)((float)(shortValue2 + 0) * 4.8828125E-4F + factor7))
                        .resolveWorldRenderer(posX, posY, posZ, 0.8F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(shortValue + 0), (double)factor5, (double)(shortValue2 + 0))
                        .getWorldRenderer6((double)((float)(shortValue + 0) * 4.8828125E-4F + factor6), (double)((float)(shortValue2 + 0) * 4.8828125E-4F + factor7))
                        .resolveWorldRenderer(posX, posY, posZ, 0.8F)
                        .updateState();
                  }
               }

               tessellator.updateState();
               this.internalMinecraftEntry.updateState3();
            }

            this.internalMinecraftEntry.updateState();
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableBlend();
            GlStateManager.enableLighting();
         }

         if (BuildManager.canExecute()) {
            PreEndHandManager.updateState33();
         }
      }
   }

   private void applyAttackEffectsExplosionNorm(
      AttackEffectsExplosionNormalType attackEffectsExplosionNormalType, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray
   ) {
      this.applyIndexArray(attackEffectsExplosionNormalType.getIndex(), attackEffectsExplosionNormalType.isEnabled(), posX, posX2, posX3, posX4, posY, posZ, countArray);
   }

   public void applyIndex(int count17, int count18) {
      if (OpenGlHelper.enabled4 && this.passManager != null) {
         this.passManager.applyIndex(count17, count18);
      }
   }

   @Override
   public void applyEntityPlayerByEntityPlayer(EntityPlayer player, String text, double posX, double posY, double posZ, float factor, float factor2) {
   }

   public void updateState3() {
      if (this.isActive()) {
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 1, 1, 1);
         this.framebuffer.applyIndex(this.mc.displayWidth, this.mc.displayHeight, false);
         GlStateManager.enableBlend();
      }
   }

   public boolean checkDouble(double posX, double posY, double posZ, float factor) {
      return false;
   }

   private void applyBlockSolidType(BlockSolidType blockSolidType) {
      this.mc.entityRenderer.updateState7();
      if (OpenGlHelper.isActive()) {
         GL11.glEnableClientState(32884);
         OpenGlHelper.applyIndex4(OpenGlHelper.lightmapTexUnit);
         GL11.glEnableClientState(32888);
         OpenGlHelper.applyIndex4(OpenGlHelper.defaultTexUnit);
         GL11.glEnableClientState(32888);
         OpenGlHelper.applyIndex4(OpenGlHelper.lightmapTexUnit);
         GL11.glEnableClientState(32886);
      }

      if (BuildManager.canExecute()) {
         RotationBridge.applyBlockSolidType(blockSolidType);
      }

      this.extraDoubleEntry.applyBlockSolidType(blockSolidType);
      if (BuildManager.canExecute()) {
         RotationBridge.applyBlockSolidTypeByBlockSolidType(blockSolidType);
      }

      if (OpenGlHelper.isActive()) {
         for (AltLoggerEntry altLoggerEntry : (Iterable<AltLoggerEntry>)(Iterable)DefaultVertexFormats.auxiliaryLoggerEntry5.getList()) {
            RenderBooleanProperty renderBooleanProperty = altLoggerEntry.getRenderBooleanProperty();
            int count17 = altLoggerEntry.getCount();
            switch (InterpolationEvaluator.countArray[renderBooleanProperty.ordinal()]) {
               case 1:
                  GL11.glDisableClientState(32884);
                  break;
               case 2:
                  OpenGlHelper.applyIndex4(OpenGlHelper.lightmapTexUnit + count17);
                  GL11.glDisableClientState(32888);
                  OpenGlHelper.applyIndex4(OpenGlHelper.lightmapTexUnit);
                  break;
               case 3:
                  GL11.glDisableClientState(32886);
                  GlStateManager.applyState();
            }
         }
      }

      this.mc.entityRenderer.updateState5();
   }

   public int computeCount(BlockSolidType blockSolidType, double offset, int count17, Entity entity2) {
      RenderHelper.disableStandardItemLighting();
      if (blockSolidType == BlockSolidType.TRANSLUCENT && !PreEndHandManager.enabled12) {
         this.mc.uNKNOWNManager.applyTextByText("translucent_sort");
         double offset2 = entity2.posX - this.value8;
         double offset3 = entity2.posY - this.value3;
         double offset4 = entity2.posZ - this.value7;
         if (offset2 * offset2 + offset3 * offset3 + offset4 * offset4 > 1.0) {
            this.value8 = entity2.posX;
            this.value3 = entity2.posY;
            this.value7 = entity2.posZ;
            int count18 = 0;
            this.set4.clear();

            for (AuxiliaryItemArrayEntry12Entry auxiliaryItemArrayEntry12Entry : (Iterable<AuxiliaryItemArrayEntry12Entry>)(Iterable)this.list8) {
               if (auxiliaryItemArrayEntry12Entry.renderChunk.ironShard.ordinalByBlockSolidType(blockSolidType) && count18++ < 15) {
                  this.set4.add(auxiliaryItemArrayEntry12Entry.renderChunk);
               }
            }
         }

         this.mc.uNKNOWNManager.updateState();
      }

      this.mc.uNKNOWNManager.applyTextByText("filterempty");
      int count19 = 0;
      boolean flag = blockSolidType == BlockSolidType.TRANSLUCENT;
      int count20 = flag ? this.list8.size() - 1 : 0;
      int count21 = flag ? -1 : this.list8.size();

      for (int index = flag ? -1 : 1; count20 != count21; count20 += index) {
         RenderChunk renderChunk = ((AuxiliaryItemArrayEntry12Entry)this.list8.get(count20)).renderChunk;
         if (renderChunk != null && renderChunk.getIronShard() != null && !renderChunk.getIronShard().ordinal3(blockSolidType)) {
            count19++;
            this.extraDoubleEntry.applyRenderChunk(renderChunk, blockSolidType);
         }
      }

      if (count19 == 0) {
         this.mc.uNKNOWNManager.updateState();
         return count19;
      } else {
         if (BuildManager.isActive7() && this.mc.entityRenderer.enabled10) {
            GlStateManager.applyState20();
         }

         this.mc.uNKNOWNManager.applyText("render_" + blockSolidType);
         this.applyBlockSolidType(blockSolidType);
         this.mc.uNKNOWNManager.updateState();
         return count19;
      }
   }

   public static void applyDouble(double posX, double posX2, double posX3, double posX4, double posY, double posZ, int count17, int count18, int count19, int count20) {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry3);
      worldRenderer.getWorldRenderer(posX, posX2, posX3).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX4, posX2, posX3).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX4, posX2, posZ).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX, posX2, posZ).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX, posX2, posX3).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      tessellator.updateState();
      worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry3);
      worldRenderer.getWorldRenderer(posX, posY, posX3).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX4, posY, posX3).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX4, posY, posZ).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX, posY, posZ).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX, posY, posX3).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      tessellator.updateState();
      worldRenderer.updateAuxiliaryLoggerEntry(1, DefaultVertexFormats.auxiliaryLoggerEntry3);
      worldRenderer.getWorldRenderer(posX, posX2, posX3).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX, posY, posX3).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX4, posX2, posX3).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX4, posY, posX3).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX4, posX2, posZ).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX4, posY, posZ).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX, posX2, posZ).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      worldRenderer.getWorldRenderer(posX, posY, posZ).resolveWorldRenderer(count17, count18, count19, count20).updateState();
      tessellator.updateState();
   }

   @Override
   public void applyEntityByEntity(Entity entity2) {
      OptifineRandomManager.applyEntityByEntity(entity2, this.worldClient);
      if (BuildManager.isActive28()) {
         TargetEstimator.applyEntity2(entity2, this);
      }
   }

   private void updateState2() {
      if (count14 > 0) {
         this.list8 = new ArrayList(this.list8.size() + 16);
         this.list4 = new ArrayList(this.list4.size() + 16);
         this.list9 = new ArrayList(this.list9.size() + 16);
      } else {
         this.list8.clear();
         this.list4.clear();
         this.list9.clear();
      }
   }

   public int size() {
      return this.list8.size();
   }

   private boolean matchesRenderChunk(int count17, int count18, int count19, RenderChunk renderChunk) {
      BlockPos blockPos = renderChunk.getBlockPos();
      return MathHelper.computeCount(count17 - blockPos.getX()) <= 16
         && MathHelper.computeCount(count18 - blockPos.getY()) <= 16
         && MathHelper.computeCount(count19 - blockPos.getZ()) <= 16;
   }

   public boolean isActive() {
      return (!((Module)ClientGlobals.moduleManager.esp).isToggled() || !EspRenderMode.VANILLA.isSelected())
            && (!((Module)ClientGlobals.moduleManager.itemESP).isToggled() || !ClientGlobals.moduleManager.itemESP.settingMode.getSelected().equals("Vanilla"))
         ? !BuildManager.canExecute3()
            && !BuildManager.canExecute()
            && !BuildManager.canExecute4()
            && this.framebuffer != null
            && this.passManager != null
            && this.mc.thePlayer != null
            && this.mc.thePlayer.isActive23()
            && this.mc.gameSettings.keyBindSpectatorOutlines.isKeyDown()
         : !BuildManager.canExecute3()
            && !BuildManager.canExecute()
            && !BuildManager.canExecute4()
            && this.framebuffer != null
            && this.passManager != null
            && this.mc.thePlayer != null;
   }

   @Override
   public void applyIndex(int count17, int count18, int count19, int count20, int count21, int count22) {
      this.applyIndexByIndex(count17 - 1, count18 - 1, count19 - 1, count20 + 1, count21 + 1, count22 + 1);
   }

   public void applyFloat2(float factor, int count17) {
      if (this.mc.theWorld.secondaryFloatArrayEntry.getCountAsIndex() == 1) {
         this.updateState13();
      } else if (this.mc.theWorld.secondaryFloatArrayEntry.isActive2()) {
         GlStateManager.enableTexture2D();
         boolean flag = BuildManager.canExecute();
         if (flag) {
            PreEndHandManager.updateState();
         }

         Vec3 vec = LoadingManager.resolveVec3(
            this.worldClient.resolveVec3(this.mc.getRenderViewEntity(), factor),
            this.mc.theWorld,
            this.mc.getRenderViewEntity().posX,
            this.mc.getRenderViewEntity().posY + 1.0,
            this.mc.getRenderViewEntity().posZ
         );
         if (flag) {
            PreEndHandManager.applyVec3(vec);
         }

         float posX = (float)vec.xCoord;
         float posY = (float)vec.yCoord;
         float posZ = (float)vec.zCoord;
         if (count17 != 2) {
            float factor2 = (posX * 30.0F + posY * 59.0F + posZ * 11.0F) / 100.0F;
            posY = (posX * 30.0F + posY * 70.0F) / 100.0F;
            posZ = (posX * 30.0F + posZ * 70.0F) / 100.0F;
            posX = factor2;
         }

         GlStateManager.translate3f2(posX, posY, posZ);
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         GlStateManager.depthMask(false);
         GlStateManager.applyState19();
         if (flag) {
            PreEndHandManager.updateState62();
         }

         GlStateManager.translate3f2(posX, posY, posZ);
         if (flag) {
            PreEndHandManager.updateState43();
         }

         if (BuildManager.isActive32()) {
            if (this.enabled3) {
               this.diamondChestplate2.updateState2();
               GL11.glEnableClientState(32884);
               GL11.glVertexPointer(3, 5126, 12, 0L);
               this.diamondChestplate2.applyIndex(7);
               this.diamondChestplate2.updateState();
               GL11.glDisableClientState(32884);
            } else {
               GlStateManager.applyIndexByIndex(this.count16);
            }
         }

         GlStateManager.applyState20();
         if (flag) {
            PreEndHandManager.updateState50();
         }

         GlStateManager.applyState10();
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         RenderHelper.disableStandardItemLighting();
         float[] factorArray = this.worldClient.secondaryFloatArrayEntry.resolveFloatArray(this.worldClient.computeFactor(factor), factor);
         if (factorArray != null && BuildManager.isActive59()) {
            GlStateManager.enableTexture2D();
            if (flag) {
               PreEndHandManager.updateState();
            }

            GlStateManager.glShadeModel(7425);
            GlStateManager.pushMatrix();
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(MathHelper.abs(this.worldClient.computeFactor2(factor)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            float factor3 = factorArray[0];
            float factor4 = factorArray[1];
            float factor5 = factorArray[2];
            if (count17 != 2) {
               float factor6 = (factor3 * 30.0F + factor4 * 59.0F + factor5 * 11.0F) / 100.0F;
               factor4 = (factor3 * 30.0F + factor4 * 70.0F) / 100.0F;
               factor5 = (factor3 * 30.0F + factor5 * 70.0F) / 100.0F;
               factor3 = factor6;
            }

            worldRenderer.updateAuxiliaryLoggerEntry(6, DefaultVertexFormats.auxiliaryLoggerEntry3);
            worldRenderer.getWorldRenderer(0.0, 100.0, 0.0).resolveWorldRenderer(factor3, factor4, factor5, factorArray[3]).updateState();

            for (int index = 0; index <= 16; index++) {
               float factor7 = (float)index * (float) Math.PI * 2.0F / 16.0F;
               float factor8 = MathHelper.abs(factor7);
               float factor9 = MathHelper.cos(factor7);
               worldRenderer.getWorldRenderer((double)(factor8 * 120.0F), (double)(factor9 * 120.0F), (double)(-factor9 * 40.0F * factorArray[3]))
                  .resolveWorldRenderer(factorArray[0], factorArray[1], factorArray[2], 0.0F)
                  .updateState();
            }

            tessellator.updateState();
            GlStateManager.popMatrix();
            GlStateManager.glShadeModel(7424);
         }

         GlStateManager.disableTexture2D();
         if (flag) {
            PreEndHandManager.updateState2();
         }

         GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
         GlStateManager.pushMatrix();
         float factor10 = 1.0F - this.worldClient.getFloat6(factor);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, factor10);
         GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
         McpatcherSkyWorldManager.applyWorld(this.worldClient, this.textureManager, factor);
         if (flag) {
            PreEndHandManager.updateState17();
         }

         GlStateManager.rotate(this.worldClient.computeFactor(factor) * 360.0F, 1.0F, 0.0F, 0.0F);
         if (flag) {
            PreEndHandManager.updateState30();
         }

         float factor11 = 30.0F;
         if (BuildManager.isActive13()) {
            this.textureManager.applyResourceLocation3(resourceLocation2);
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
            worldRenderer.getWorldRenderer((double)(-factor11), 100.0, (double)(-factor11)).getWorldRenderer6(0.0, 0.0).updateState();
            worldRenderer.getWorldRenderer((double)factor11, 100.0, (double)(-factor11)).getWorldRenderer6(1.0, 0.0).updateState();
            worldRenderer.getWorldRenderer((double)factor11, 100.0, (double)factor11).getWorldRenderer6(1.0, 1.0).updateState();
            worldRenderer.getWorldRenderer((double)(-factor11), 100.0, (double)factor11).getWorldRenderer6(0.0, 1.0).updateState();
            tessellator.updateState();
         }

         float factor12 = 20.0F;
         if (BuildManager.isActive77()) {
            this.textureManager.applyResourceLocation3(resourceLocation3);
            int count18 = this.worldClient.getCount2();
            int count19 = count18 % 4;
            int count20 = count18 / 4 % 2;
            float factor13 = (float)count19 / 4.0F;
            float factor14 = (float)count20 / 2.0F;
            float factor15 = (float)(count19 + 1) / 4.0F;
            float factor16 = (float)(count20 + 1) / 2.0F;
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
            worldRenderer.getWorldRenderer((double)(-factor12), -100.0, (double)factor12).getWorldRenderer6((double)factor15, (double)factor16).updateState();
            worldRenderer.getWorldRenderer((double)factor12, -100.0, (double)factor12).getWorldRenderer6((double)factor13, (double)factor16).updateState();
            worldRenderer.getWorldRenderer((double)factor12, -100.0, (double)(-factor12)).getWorldRenderer6((double)factor13, (double)factor14).updateState();
            worldRenderer.getWorldRenderer((double)(-factor12), -100.0, (double)(-factor12)).getWorldRenderer6((double)factor15, (double)factor14).updateState();
            tessellator.updateState();
         }

         GlStateManager.enableTexture2D();
         if (flag) {
            PreEndHandManager.updateState();
         }

         float factor17 = this.worldClient.computeFactorByFloat(factor) * factor10;
         if (factor17 > 0.0F && BuildManager.isActive43() && !McpatcherSkyWorldManager.matchesWorldByWorld(this.worldClient)) {
            GlStateManager.color4f2(factor17, factor17, factor17, factor17);
            if (this.enabled3) {
               this.diamondChestplate.updateState2();
               GL11.glEnableClientState(32884);
               GL11.glVertexPointer(3, 5126, 12, 0L);
               this.diamondChestplate.applyIndex(7);
               this.diamondChestplate.updateState();
               GL11.glDisableClientState(32884);
            } else {
               GlStateManager.applyIndexByIndex(this.count2);
            }
         }

         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableBlend();
         GlStateManager.applyState14();
         GlStateManager.applyState19();
         if (flag) {
            PreEndHandManager.updateState62();
         }

         GlStateManager.popMatrix();
         GlStateManager.enableTexture2D();
         if (flag) {
            PreEndHandManager.updateState();
         }

         GlStateManager.translate3f2(0.0F, 0.0F, 0.0F);
         double offset = this.mc.thePlayer.getVec35(factor).yCoord - this.worldClient.getDouble();
         if (offset < 0.0) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 12.0F, 0.0F);
            if (this.enabled3) {
               this.diamondChestplate3.updateState2();
               GL11.glEnableClientState(32884);
               GL11.glVertexPointer(3, 5126, 12, 0L);
               this.diamondChestplate3.applyIndex(7);
               this.diamondChestplate3.updateState();
               GL11.glDisableClientState(32884);
            } else {
               GlStateManager.applyIndexByIndex(this.count11);
            }

            GlStateManager.popMatrix();
            float factor18 = -((float)(offset + 65.0));
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry3);
            worldRenderer.getWorldRenderer(-1.0, (double)factor18, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(1.0, (double)factor18, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(1.0, -1.0, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(-1.0, -1.0, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(-1.0, -1.0, -1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(1.0, -1.0, -1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(1.0, (double)factor18, -1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(-1.0, (double)factor18, -1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(1.0, -1.0, -1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(1.0, -1.0, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(1.0, (double)factor18, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(1.0, (double)factor18, -1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(-1.0, (double)factor18, -1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(-1.0, (double)factor18, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(-1.0, -1.0, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(-1.0, -1.0, -1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(-1.0, -1.0, -1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(-1.0, -1.0, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(1.0, -1.0, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer(1.0, -1.0, -1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            tessellator.updateState();
         }

         if (this.worldClient.secondaryFloatArrayEntry.isActive()) {
            GlStateManager.translate3f2(posX * 0.2F + 0.04F, posY * 0.2F + 0.04F, posZ * 0.6F + 0.1F);
         } else {
            GlStateManager.translate3f2(posX, posY, posZ);
         }

         if (this.mc.gameSettings.indexOffset16 <= 4) {
            GlStateManager.translate3f2(this.mc.entityRenderer.factor9, this.mc.entityRenderer.factor15, this.mc.entityRenderer.factor2);
         }

         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, -((float)(offset - 16.0)), 0.0F);
         if (BuildManager.isActive32()) {
            if (this.enabled3) {
               this.diamondChestplate3.updateState2();
               GlStateManager.enableClientState(32884);
               GlStateManager.glStateAction3(3, 5126, 12, 0);
               this.diamondChestplate3.applyIndex(7);
               this.diamondChestplate3.updateState();
               GlStateManager.disableClientState(32884);
            } else {
               GlStateManager.applyIndexByIndex(this.count11);
            }
         }

         GlStateManager.popMatrix();
         GlStateManager.disableTexture2D();
         if (flag) {
            PreEndHandManager.updateState2();
         }

         GlStateManager.depthMask(true);
      }
   }

   public void updateState14() {
      this.enabled6 = true;
   }

   public String getText() {
      return "E: "
         + this.indexOffset
         + "/"
         + this.count5
         + ", B: "
         + this.count4
         + ", I: "
         + (this.count5 - this.count4 - this.indexOffset)
         + ", "
         + BuildManager.getTextAsText();
   }

   private void applyIndexByIndex(int count17, int count18, int count19, int count20, int count21, int count22) {
      this.renderGlobalEntry.applyIndex(count17, count18, count19, count20, count21, count22);
   }

   private void updateState18() {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      if (this.diamondChestplate3 != null) {
         this.diamondChestplate3.updateState3();
      }

      if (this.count11 >= 0) {
         VectorMatcher.glDeleteLists(this.count11);
         this.count11 = -1;
      }

      if (this.enabled3) {
         this.diamondChestplate3 = new DiamondChestplate(this.auxiliaryLoggerEntry);
         this.applyWorldRenderer(worldRenderer, -16.0F, true);
         worldRenderer.renderElement6();
         worldRenderer.renderElement5();
         this.diamondChestplate3.applyByteBuffer(worldRenderer.getByteBuffer());
      } else {
         this.count11 = VectorMatcher.computeCount(1);
         GL11.glNewList(this.count11, 4864);
         this.applyWorldRenderer(worldRenderer, -16.0F, true);
         tessellator.updateState();
         GL11.glEndList();
      }
   }

   @Override
   public void applyBlockPosByBlockPos(int count17, BlockPos blockPos, int count18) {
      switch (count17) {
         case 1013:
         case 1018:
            if (this.mc.getRenderViewEntity() != null) {
               double offset = (double)blockPos.getX() - this.mc.getRenderViewEntity().posX;
               double offset2 = (double)blockPos.getY() - this.mc.getRenderViewEntity().posY;
               double offset3 = (double)blockPos.getZ() - this.mc.getRenderViewEntity().posZ;
               double offset4 = Math.sqrt(offset * offset + offset2 * offset2 + offset3 * offset3);
               double posX = this.mc.getRenderViewEntity().posX;
               double posY = this.mc.getRenderViewEntity().posY;
               double posZ = this.mc.getRenderViewEntity().posZ;
               if (offset4 > 0.0) {
                  posX += offset / offset4 * 2.0;
                  posY += offset2 / offset4 * 2.0;
                  posZ += offset3 / offset4 * 2.0;
               }

               if (count17 == 1013) {
                  this.worldClient.applyText(posX, posY, posZ, "mob.wither.spawn", 1.0F, 1.0F, false);
               } else {
                  this.worldClient.applyText(posX, posY, posZ, "mob.enderdragon.end", 5.0F, 1.0F, false);
               }
            }
      }
   }

   public void applyEntityByEntity(Entity entity2, float factor) {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      WorldInfo internalListEntry = this.worldClient.getInternalListEntry();
      double offset = (double)(this.mc.gameSettings.indexOffset16 * 16);
      if (entity2.posX >= internalListEntry.getValue2() - offset
         || entity2.posX <= internalListEntry.getValue9() + offset
         || entity2.posZ >= internalListEntry.getValueAsDouble() - offset
         || entity2.posZ <= internalListEntry.getValue() + offset) {
         if (BuildManager.canExecute()) {
            PreEndHandManager.updateState67();
            PreEndHandManager.applyNoneManager2(PreEndHandManager.noneManager30);
         }

         double offset2 = Math.pow(1.0 - internalListEntry.computeValueByEntity(entity2) / offset, 4.0);
         double offset3 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * (double)factor;
         double offset4 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * (double)factor;
         double offset5 = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * (double)factor;
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
         this.textureManager.applyResourceLocation3(resourceLocation);
         GlStateManager.depthMask(false);
         GlStateManager.pushMatrix();
         int count17 = internalListEntry.getRenderToggleProperty().getCount();
         float factor2 = (float)(count17 >> 16 & 0xFF) / 255.0F;
         float factor3 = (float)(count17 >> 8 & 0xFF) / 255.0F;
         float factor4 = (float)(count17 & 0xFF) / 255.0F;
         GlStateManager.color4f2(factor2, factor3, factor4, (float)offset2);
         GlStateManager.applyFloat(-3.0F, -3.0F);
         GlStateManager.applyState4();
         GlStateManager.alphaFunc(516, 0.1F);
         GlStateManager.applyState14();
         GlStateManager.disableAlpha();
         float factor5 = (float)(Minecraft.getTimestamp() % 3000L) / 3000.0F;
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
         worldRenderer.applyDouble(-offset3, -offset4, -offset5);
         double offset6 = Math.max((double)MathHelper.ceiling_double_int(offset5 - offset), internalListEntry.getValue());
         double offset7 = Math.min((double)MathHelper.truncateDoubleToInt(offset5 + offset), internalListEntry.getValueAsDouble());
         if (offset3 > internalListEntry.getValue2() - offset) {
            float factor6 = 0.0F;

            for (double offset8 = offset6; offset8 < offset7; factor6 += 0.5F) {
               double offset9 = Math.min(1.0, offset7 - offset8);
               float factor7 = (float)offset9 * 0.5F;
               worldRenderer.getWorldRenderer(internalListEntry.getValue2(), 256.0, offset8).getWorldRenderer6((double)(factor5 + factor6), (double)(factor5 + 0.0F)).updateState();
               worldRenderer.getWorldRenderer(internalListEntry.getValue2(), 256.0, offset8 + offset9)
                  .getWorldRenderer6((double)(factor5 + factor7 + factor6), (double)(factor5 + 0.0F))
                  .updateState();
               worldRenderer.getWorldRenderer(internalListEntry.getValue2(), 0.0, offset8 + offset9)
                  .getWorldRenderer6((double)(factor5 + factor7 + factor6), (double)(factor5 + 128.0F))
                  .updateState();
               worldRenderer.getWorldRenderer(internalListEntry.getValue2(), 0.0, offset8).getWorldRenderer6((double)(factor5 + factor6), (double)(factor5 + 128.0F)).updateState();
               offset8++;
            }
         }

         if (offset3 < internalListEntry.getValue9() + offset) {
            float factor8 = 0.0F;

            for (double offset10 = offset6; offset10 < offset7; factor8 += 0.5F) {
               double offset11 = Math.min(1.0, offset7 - offset10);
               float factor9 = (float)offset11 * 0.5F;
               worldRenderer.getWorldRenderer(internalListEntry.getValue9(), 256.0, offset10).getWorldRenderer6((double)(factor5 + factor8), (double)(factor5 + 0.0F)).updateState();
               worldRenderer.getWorldRenderer(internalListEntry.getValue9(), 256.0, offset10 + offset11)
                  .getWorldRenderer6((double)(factor5 + factor9 + factor8), (double)(factor5 + 0.0F))
                  .updateState();
               worldRenderer.getWorldRenderer(internalListEntry.getValue9(), 0.0, offset10 + offset11)
                  .getWorldRenderer6((double)(factor5 + factor9 + factor8), (double)(factor5 + 128.0F))
                  .updateState();
               worldRenderer.getWorldRenderer(internalListEntry.getValue9(), 0.0, offset10).getWorldRenderer6((double)(factor5 + factor8), (double)(factor5 + 128.0F)).updateState();
               offset10++;
            }
         }

         double offset12 = Math.max((double)MathHelper.ceiling_double_int(offset3 - offset), internalListEntry.getValue9());
         double offset13 = Math.min((double)MathHelper.truncateDoubleToInt(offset3 + offset), internalListEntry.getValue2());
         if (offset5 > internalListEntry.getValueAsDouble() - offset) {
            float factor10 = 0.0F;

            for (double offset14 = offset12; offset14 < offset13; factor10 += 0.5F) {
               double offset15 = Math.min(1.0, offset13 - offset14);
               float factor11 = (float)offset15 * 0.5F;
               worldRenderer.getWorldRenderer(offset14, 256.0, internalListEntry.getValueAsDouble()).getWorldRenderer6((double)(factor5 + factor10), (double)(factor5 + 0.0F)).updateState();
               worldRenderer.getWorldRenderer(offset14 + offset15, 256.0, internalListEntry.getValueAsDouble())
                  .getWorldRenderer6((double)(factor5 + factor11 + factor10), (double)(factor5 + 0.0F))
                  .updateState();
               worldRenderer.getWorldRenderer(offset14 + offset15, 0.0, internalListEntry.getValueAsDouble())
                  .getWorldRenderer6((double)(factor5 + factor11 + factor10), (double)(factor5 + 128.0F))
                  .updateState();
               worldRenderer.getWorldRenderer(offset14, 0.0, internalListEntry.getValueAsDouble()).getWorldRenderer6((double)(factor5 + factor10), (double)(factor5 + 128.0F)).updateState();
               offset14++;
            }
         }

         if (offset5 < internalListEntry.getValue() + offset) {
            float factor12 = 0.0F;

            for (double offset16 = offset12; offset16 < offset13; factor12 += 0.5F) {
               double offset17 = Math.min(1.0, offset13 - offset16);
               float factor13 = (float)offset17 * 0.5F;
               worldRenderer.getWorldRenderer(offset16, 256.0, internalListEntry.getValue()).getWorldRenderer6((double)(factor5 + factor12), (double)(factor5 + 0.0F)).updateState();
               worldRenderer.getWorldRenderer(offset16 + offset17, 256.0, internalListEntry.getValue())
                  .getWorldRenderer6((double)(factor5 + factor13 + factor12), (double)(factor5 + 0.0F))
                  .updateState();
               worldRenderer.getWorldRenderer(offset16 + offset17, 0.0, internalListEntry.getValue())
                  .getWorldRenderer6((double)(factor5 + factor13 + factor12), (double)(factor5 + 128.0F))
                  .updateState();
               worldRenderer.getWorldRenderer(offset16, 0.0, internalListEntry.getValue()).getWorldRenderer6((double)(factor5 + factor12), (double)(factor5 + 128.0F)).updateState();
               offset16++;
            }
         }

         tessellator.updateState();
         worldRenderer.applyDouble(0.0, 0.0, 0.0);
         GlStateManager.enableLighting();
         GlStateManager.applyState10();
         GlStateManager.applyFloat(0.0F, 0.0F);
         GlStateManager.enableAlpha();
         GlStateManager.applyState14();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.enableBlend();
         GlStateManager.popMatrix();
         GlStateManager.depthMask(true);
         if (BuildManager.canExecute()) {
            PreEndHandManager.updateState38();
         }
      }
   }

   public Vector3f resolveVector3f(Entity entity2, double offset) {
      float factor = (float)((double)entity2.prevRotationPitch + (double)(entity2.rotationPitch - entity2.prevRotationPitch) * offset);
      float factor2 = (float)((double)entity2.prevRotationYaw + (double)(entity2.rotationYaw - entity2.prevRotationYaw) * offset);
      if (Minecraft.getMinecraft().gameSettings.indexOffset64 == 2) {
         factor += 180.0F;
      }

      float factor3 = MathHelper.cos(-factor2 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float factor4 = MathHelper.abs(-factor2 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float factor5 = -MathHelper.cos(-factor * (float) (Math.PI / 180.0));
      float factor6 = MathHelper.abs(-factor * (float) (Math.PI / 180.0));
      return new Vector3f(factor4 * factor5, factor6, factor3 * factor5);
   }

   public static void applyAxisAlignedBB(AxisAlignedBB boundingBox, int count17, int count18, int count19, int count20) {
      applyDouble(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ, count17, count18, count19, count20);
   }

   public int getCount8() {
      return this.count8;
   }

   public void applyCollection(Collection collection, Collection collection2) {
      synchronized (this.set5) {
         this.set5.removeAll(collection);
         this.set5.addAll(collection2);
      }
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) {
      this.updateState5();
   }

   @Override
   public void applyText(String text, double posX, double posY, double posZ, float factor, float factor2) {
   }

   @Override
   public void applyEntity3(Entity entity2) {
      OptifineRandomManager.applyEntity3(entity2, this.worldClient);
      if (BuildManager.isActive28()) {
         TargetEstimator.applyEntity(entity2, this);
      }
   }

   public void updateState19() {
      if (this.enabled5) {
         this.updateState();
         this.enabled5 = false;
      }
   }

   public void applyEntityPlayerByEntityPlayer(EntityPlayer player, MovingObjectPosition hitResult, int count17, float factor) {
      if (count17 == 0 && hitResult.typeOfHit == MovingObjectType.BLOCK) {
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.color4f2(0.0F, 0.0F, 0.0F, 0.4F);
         GL11.glLineWidth(2.0F);
         GlStateManager.enableTexture2D();
         if (BuildManager.canExecute()) {
            PreEndHandManager.updateState();
         }

         GlStateManager.depthMask(false);
         BlockPos blockPos = hitResult.getBlockPos();
         Block block = this.worldClient.getBlockState(blockPos).getBlock();
         if (block.getMaterial() != Material.coreItemEntry && this.worldClient.getInternalListEntry().checkBlockPos(blockPos)) {
            block.processAction(this.worldClient, blockPos.getX(), blockPos.getY(), blockPos.getZ());
            double offset = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)factor;
            double offset2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)factor;
            double offset3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)factor;
            AxisAlignedBB boundingBox = block.processAction((World)this.worldClient, blockPos);
            BlockNoneType blockNoneType = block.processAction9();
            if (blockNoneType != BlockNoneType.NONE) {
               boundingBox = NormalManager.resolveAxisAlignedBB(boundingBox, blockNoneType, blockPos);
            }

            applyAxisAlignedBB2(boundingBox.offset(0.002F, 0.002F, 0.002F).expand(-offset, -offset2, -offset3));
         }

         GlStateManager.depthMask(true);
         GlStateManager.disableTexture2D();
         if (BuildManager.canExecute()) {
            PreEndHandManager.updateState2();
         }

         GlStateManager.enableBlend();
      }
   }

   private void applyWorldRenderer(WorldRenderer worldRenderer) {
      Random random = new Random(10842L);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry1);

      for (int index = 0; index < 1500; index++) {
         double offset = (double)(random.nextFloat() * 2.0F - 1.0F);
         double offset2 = (double)(random.nextFloat() * 2.0F - 1.0F);
         double offset3 = (double)(random.nextFloat() * 2.0F - 1.0F);
         double offset4 = (double)(0.15F + random.nextFloat() * 0.1F);
         double offset5 = offset * offset + offset2 * offset2 + offset3 * offset3;
         if (offset5 < 1.0 && offset5 > 0.01) {
            double offset6 = 1.0 / Math.sqrt(offset5);
            double offset7 = offset * offset6;
            double offset8 = offset2 * offset6;
            double offset9 = offset3 * offset6;
            double offset10 = offset7 * 100.0;
            double offset11 = offset8 * 100.0;
            double offset12 = offset9 * 100.0;
            double offset13 = Math.atan2(offset7, offset9);
            double offset14 = Math.sin(offset13);
            double offset15 = Math.cos(offset13);
            double offset16 = Math.atan2(Math.sqrt(offset7 * offset7 + offset9 * offset9), offset8);
            double offset17 = Math.sin(offset16);
            double offset18 = Math.cos(offset16);
            double offset19 = random.nextDouble() * Math.PI * 2.0;
            double offset20 = Math.sin(offset19);
            double offset21 = Math.cos(offset19);

            for (int index2 = 0; index2 < 4; index2++) {
               double offset22 = (double)((index2 & 2) - 1) * offset4;
               double offset23 = (double)((index2 + 1 & 2) - 1) * offset4;
               double offset24 = offset22 * offset21 - offset23 * offset20;
               double offset25 = offset23 * offset21 + offset22 * offset20;
               double offset26 = offset24 * offset17 + 0.0 * offset18;
               double offset27 = 0.0 * offset17 - offset24 * offset18;
               double offset28 = offset27 * offset14 - offset25 * offset15;
               double offset29 = offset25 * offset14 + offset27 * offset15;
               worldRenderer.getWorldRenderer(offset10 + offset28, offset11 + offset26, offset12 + offset29).updateState();
            }
         }
      }
   }

   public void updateState15() {
   }

   @Override
   public void applyBlockPos(int count17, BlockPos blockPos, int count18) {
      if (count18 >= 0 && count18 < 10) {
         AuxiliaryBlockPosEntry auxiliaryBlockPosEntry = (AuxiliaryBlockPosEntry)this.map.get(count17);
         if (auxiliaryBlockPosEntry == null || auxiliaryBlockPosEntry.getBlockPos().getX() != blockPos.getX() || auxiliaryBlockPosEntry.getBlockPos().getY() != blockPos.getY() || auxiliaryBlockPosEntry.getBlockPos().getZ() != blockPos.getZ()) {
            auxiliaryBlockPosEntry = new AuxiliaryBlockPosEntry(count17, blockPos);
            this.map.put(count17, auxiliaryBlockPosEntry);
         }

         auxiliaryBlockPosEntry.applyIndex(count18);
         auxiliaryBlockPosEntry.setCount2(this.count15);
      } else {
         this.map.remove(count17);
      }
   }

   public void applyWorldClientByWorldClient(WorldClient world) {
      if (this.worldClient != null) {
         this.worldClient.remove(this);
      }

      this.value10 = Double.MIN_VALUE;
      this.value11 = Double.MIN_VALUE;
      this.value2 = Double.MIN_VALUE;
      this.count6 = Integer.MIN_VALUE;
      this.count13 = Integer.MIN_VALUE;
      this.count = Integer.MIN_VALUE;
      this.renderManager.setWorld(world);
      this.worldClient = world;
      if (BuildManager.isActive28()) {
         TargetEstimator.updateState();
      }

      EnumFacingArrayEntry.updateState();
      this.iChunkProvider = null;
      this.internalFloatEntry = null;
      this.baseIBlockStateEntry.applyIBlockState((IBlockState)null, (BlockPos)null);
      PreEndHandManager.applyWorldByWorld(this.worldClient);
      if (world != null) {
         world.add(this);
         this.updateState();
      } else {
         this.set2.clear();
         this.updateState2();
         if (this.renderGlobalEntry != null) {
            this.renderGlobalEntry.renderElement();
         }

         this.renderGlobalEntry = null;
      }
   }

   public int sizeAsIndex() {
      return this.set2.size();
   }

   public void applyEntity(Entity entity2, PreMotionConsumer preMotionConsumer, float factor) {
      if (this.count12 > 0) {
         this.count12--;
      } else {
         double offset = entity2.prevPosX + (entity2.posX - entity2.prevPosX) * (double)factor;
         double offset2 = entity2.prevPosY + (entity2.posY - entity2.prevPosY) * (double)factor;
         double offset3 = entity2.prevPosZ + (entity2.posZ - entity2.prevPosZ) * (double)factor;
         this.worldClient.uNKNOWNManager.applyTextByText("prepare");
         StandardMapEntry.standardMapEntry
            .applyWorld(this.worldClient, this.mc.getTextureManager(), this.mc.fontRendererObj, this.mc.getRenderViewEntity(), factor);
         this.renderManager
            .applyWorldByWorld(this.worldClient, this.mc.fontRendererObj, this.mc.getRenderViewEntity(), this.mc.pointedEntity, this.mc.gameSettings, factor);
         count14++;
         this.count5 = 0;
         this.indexOffset = 0;
         this.count4 = 0;
         this.count8 = 0;
         Entity renderViewEntity = this.mc.getRenderViewEntity();
         double offset4 = renderViewEntity.lastTickPosX + (renderViewEntity.posX - renderViewEntity.lastTickPosX) * (double)factor;
         double offset5 = renderViewEntity.lastTickPosY + (renderViewEntity.posY - renderViewEntity.lastTickPosY) * (double)factor;
         double offset6 = renderViewEntity.lastTickPosZ + (renderViewEntity.posZ - renderViewEntity.lastTickPosZ) * (double)factor;
         StandardMapEntry.value5 = offset4;
         StandardMapEntry.value3 = offset5;
         StandardMapEntry.value4 = offset6;
         this.renderManager.setRenderPosition(offset4, offset5, offset6);
         this.mc.entityRenderer.updateState7();
         this.worldClient.uNKNOWNManager.applyText("global");
         List loadedEntityList = this.worldClient.getLoadedEntityList();
         this.count5 = loadedEntityList.size();
         if (BuildManager.isActive7() && this.mc.entityRenderer.enabled10) {
            GlStateManager.applyState20();
         }

         for (int index = 0; index < this.worldClient.list4.size(); index++) {
            Entity entity3 = (Entity)this.worldClient.list4.get(index);
            this.indexOffset++;
            if (entity3.checkDouble2(offset, offset2, offset3)) {
               this.renderManager.renderEntitySimple(entity3, factor);
            }
         }

         if (this.isActive()) {
            GlStateManager.glDepthFunc(519);
            GlStateManager.applyState20();
            this.framebuffer.updateState();
            this.framebuffer.setState(false);
            this.worldClient.uNKNOWNManager.applyText("entityOutlines");
            RenderHelper.disableStandardItemLighting();
            this.renderManager.setState(true);

            for (int index2 = 0; index2 < loadedEntityList.size(); index2++) {
               boolean flag;
               Entity entity4;
               boolean flag2;
               guardBlock: {
                  searchBlock: {
                     entity4 = (Entity)loadedEntityList.get(index2);
                     flag = this.mc.getRenderViewEntity() instanceof EntityLivingBase && ((EntityLivingBase)this.mc.getRenderViewEntity()).isState5();
                     if (entity4.checkDouble2(offset, offset2, offset3)
                        && (entity4.enabled3 || preMotionConsumer.checkAxisAlignedBB(entity4.getBoundingBox2()) || entity4.entity == this.mc.thePlayer)) {
                        if (((Module)ClientGlobals.moduleManager.esp).isToggled() && EspRenderMode.VANILLA.isSelected() && entity4 instanceof EntityLivingBase) {
                           EntityLivingBase livingEntity = (EntityLivingBase)entity4;
                           if (ClientGlobals.moduleManager.esp.checkEntityLivingBase(livingEntity)) {
                              break searchBlock;
                           }
                        }

                        if (((Module)ClientGlobals.moduleManager.itemESP).isToggled()
                           && ClientGlobals.moduleManager.itemESP.settingMode.getSelected().equals("Vanilla")
                           && entity4 instanceof EntityItem
                           && ItemESP.checkItem(((EntityItem)entity4).getItemStack().getItem())) {
                           break searchBlock;
                        }
                     }

                     flag2 = false;
                     break guardBlock;
                  }

                  flag2 = true;
               }

               boolean flag3 = flag2;
               if ((entity4 != this.mc.getRenderViewEntity() || this.mc.gameSettings.indexOffset64 != 0 || flag) && flag3) {
                  this.renderManager.renderEntitySimple(entity4, factor);
               }
            }

            this.renderManager.setState(false);
            RenderHelper.enableStandardItemLighting();
            GlStateManager.depthMask(false);
            this.passManager.applyFloat(factor);
            GlStateManager.applyState13();
            GlStateManager.depthMask(true);
            this.mc.getFramebuffer().setState(false);
            GlStateManager.applyState19();
            GlStateManager.disableBlend();
            GlStateManager.applyState6();
            GlStateManager.glDepthFunc(515);
            GlStateManager.disableDepth();
            GlStateManager.applyState14();
         }

         this.worldClient.uNKNOWNManager.applyText("entities");
         boolean flag4 = BuildManager.canExecute();
         if (flag4) {
            PreEndHandManager.updateState52();
         }

         SupplementalResourceLocationEntryAdapter.updateState();
         boolean state = this.mc.gameSettings.state5;
         this.mc.gameSettings.state5 = BuildManager.isActive19();
         if (PreEndHandManager.enabled12 && !this.mc.thePlayer.isActive23()) {
         } else {
         }

         EventLiquidCollisionCallback.applyPreMotionConsumer(preMotionConsumer, offset, offset2, offset3, factor, flag4);

         for (int index3 = 0; index3 < this.list4.size(); index3++) {
            AuxiliaryItemArrayEntry12Entry auxiliaryItemArrayEntry12Entry = (AuxiliaryItemArrayEntry12Entry)this.list4.get(index3);
            DonTKnowHowToSearchForAbstractSet donTKnowHowToSearchForAbstractSet = auxiliaryItemArrayEntry12Entry.renderChunk.getAlertNotificationSound().getDonTKnowHowToSearchForAbstractSetArray()[auxiliaryItemArrayEntry12Entry.renderChunk
                  .getBlockPos()
                  .getY()
               / 16];
            if (!donTKnowHowToSearchForAbstractSet.isEmpty()) {
               for (Entity entity5 : (Iterable<Entity>)(Iterable)donTKnowHowToSearchForAbstractSet) {
                  boolean flag5 = this.renderManager.matchesEntityByEntity(entity5, preMotionConsumer, offset, offset2, offset3) || entity5.entity == this.mc.thePlayer;
                  if (flag5) {
                     boolean flag6 = this.mc.getRenderViewEntity() instanceof EntityLivingBase && ((EntityLivingBase)this.mc.getRenderViewEntity()).isState5();
                     if (entity5 == this.mc.getRenderViewEntity() && this.mc.gameSettings.indexOffset64 == 0 && !flag6
                        || !(entity5.posY < 0.0)
                           && !(entity5.posY >= 256.0)
                           && !this.worldClient
                              .checkIndex(
                                 MathHelper.ceiling_double_int(entity5.posX),
                                 MathHelper.ceiling_double_int(entity5.posY),
                                 MathHelper.ceiling_double_int(entity5.posZ)
                              )) {
                        continue;
                     }

                     this.indexOffset++;
                     this.entity = entity5;
                     if (flag4) {
                        PreEndHandManager.applyEntity7(entity5);
                     }

                     this.renderManager.renderEntitySimple(entity5, factor);
                     this.entity = null;
                  }

                  if (!flag5 && entity5 instanceof EntityWitherskull) {
                     this.entity = entity5;
                     if (flag4) {
                        PreEndHandManager.applyEntity7(entity5);
                     }

                     this.mc.getRenderManager().applyEntityByEntity(entity5, factor);
                     this.entity = null;
                  }
               }
            }
         }

         this.mc.gameSettings.state5 = state;
         StandardMapEntry.standardMapEntry.getFontRendererAsFontRenderer();
         if (flag4) {
            PreEndHandManager.updateState31();
            PreEndHandManager.updateState69();
         }

         this.worldClient.uNKNOWNManager.applyText("blockentities");
         RenderHelper.enableStandardItemLighting();

         for (int index4 = 0; index4 < this.list9.size(); index4++) {
            List list10 = ((AuxiliaryItemArrayEntry12Entry)this.list9.get(index4)).renderChunk.getIronShard().getList();
            if (!list10.isEmpty()) {
               for (TileEntity tileEntity : (Iterable<TileEntity>)(Iterable)list10) {
                  StandardMapEntry.standardMapEntry.applyTileEntity(tileEntity, factor, -1);
                  this.count8++;
               }
            }
         }

         synchronized (this.set5) {
            for (Object element : this.set5) {
               if (flag4) {
                  PreEndHandManager.applyTileEntityByTileEntity((TileEntity)element);
               }

               StandardMapEntry.standardMapEntry.applyTileEntity((TileEntity)element, factor, -1);
            }
         }

         this.enabled = true;
         this.updateState4();

         for (Object element2 : this.map.values()) {
            BlockPos blockPos = ((AuxiliaryBlockPosEntry)element2).getBlockPos();
            TileEntity tileEntity2 = this.worldClient.resolveTileEntity(blockPos);
            if (tileEntity2 instanceof TileEntityChest) {
               TileEntityChest tileEntityChest = (TileEntityChest)tileEntity2;
               if (tileEntityChest.tileEntityChest != null) {
                  blockPos = blockPos.offset(EnumFacing.WEST);
                  tileEntity2 = this.worldClient.resolveTileEntity(blockPos);
               } else if (tileEntityChest.tileEntityChest2 != null) {
                  blockPos = blockPos.offset(EnumFacing.NORTH);
                  tileEntity2 = this.worldClient.resolveTileEntity(blockPos);
               }
            }

            Block block = this.worldClient.getBlockState(blockPos).getBlock();
            if (tileEntity2 != null
               && (
                  block instanceof BlockTrappedChest
                     || block instanceof BlockEnderChest
                     || block instanceof VClipCollisionFilter
                     || block instanceof BlockSkull
               )) {
               if (flag4) {
                  PreEndHandManager.applyTileEntityByTileEntity(tileEntity2);
               }

               StandardMapEntry.standardMapEntry.applyTileEntity(tileEntity2, factor, ((AuxiliaryBlockPosEntry)element2).getCount());
            }
         }

         this.updateState12();
         this.enabled = false;
         if (flag4) {
            PreEndHandManager.updateState22();
         }

         count14--;
         this.mc.entityRenderer.updateState5();
         this.mc.uNKNOWNManager.updateState();
      }
   }

   public static void applyAxisAlignedBB2(AxisAlignedBB boundingBox) {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry1);
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.minY, boundingBox.minZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.minY, boundingBox.minZ).updateState();
      tessellator.updateState();
      worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry1);
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).updateState();
      tessellator.updateState();
      worldRenderer.updateAuxiliaryLoggerEntry(1, DefaultVertexFormats.auxiliaryLoggerEntry1);
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.minY, boundingBox.minZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).updateState();
      worldRenderer.getWorldRenderer(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).updateState();
      tessellator.updateState();
   }

   @Override
   public void applyIndex(int count17, int count18, int count19) {
      this.applyIndexByIndex(count17 - 1, count18 - 1, count19 - 1, count17 + 1, count18 + 1, count19 + 1);
   }

   @Override
   public void applyEntityPlayerByEntityPlayer(EntityPlayer player, int count17, BlockPos blockPos, int count18) {
      Random random = this.worldClient.random;
      switch (count17) {
         case 1000:
            this.worldClient.applyBlockPos(blockPos, "random.click", 1.0F, 1.0F, false);
            break;
         case 1001:
            this.worldClient.applyBlockPos(blockPos, "random.click", 1.0F, 1.2F, false);
            break;
         case 1002:
            this.worldClient.applyBlockPos(blockPos, "random.bow", 1.0F, 1.2F, false);
            break;
         case 1003:
            this.worldClient.applyBlockPos(blockPos, "random.door_open", 1.0F, this.worldClient.random.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1004:
            this.worldClient.applyBlockPos(blockPos, "random.fizz", 0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F, false);
            break;
         case 1005:
            if (Item.processAction(count18) instanceof ItemRecordWait) {
               this.worldClient.applyBlockPos(blockPos, "records." + ((ItemRecordWait)Item.processAction(count18)).labelText);
            } else {
               this.worldClient.applyBlockPos(blockPos, (String)null);
            }
            break;
         case 1006:
            this.worldClient.applyBlockPos(blockPos, "random.door_close", 1.0F, this.worldClient.random.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1007:
            this.worldClient.applyBlockPos(blockPos, "mob.ghast.charge", 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1008:
            this.worldClient.applyBlockPos(blockPos, "mob.ghast.fireball", 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1009:
            this.worldClient.applyBlockPos(blockPos, "mob.ghast.fireball", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1010:
            this.worldClient.applyBlockPos(blockPos, "mob.zombie.wood", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1011:
            this.worldClient.applyBlockPos(blockPos, "mob.zombie.metal", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1012:
            this.worldClient.applyBlockPos(blockPos, "mob.zombie.woodbreak", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1014:
            this.worldClient.applyBlockPos(blockPos, "mob.wither.shoot", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1015:
            this.worldClient.applyBlockPos(blockPos, "mob.bat.takeoff", 0.05F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1016:
            this.worldClient.applyBlockPos(blockPos, "mob.zombie.infect", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1017:
            this.worldClient.applyBlockPos(blockPos, "mob.zombie.unfect", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
            break;
         case 1020:
            this.worldClient.applyBlockPos(blockPos, "random.anvil_break", 1.0F, this.worldClient.random.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1021:
            this.worldClient.applyBlockPos(blockPos, "random.anvil_use", 1.0F, this.worldClient.random.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 1022:
            this.worldClient.applyBlockPos(blockPos, "random.anvil_land", 0.3F, this.worldClient.random.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 2000:
            int count19 = count18 % 3 - 1;
            int count20 = count18 / 3 % 3 - 1;
            double offset = (double)blockPos.getX() + (double)count19 * 0.6 + 0.5;
            double offset2 = (double)blockPos.getY() + 0.5;
            double offset3 = (double)blockPos.getZ() + (double)count20 * 0.6 + 0.5;

            for (int index = 0; index < 10; index++) {
               double offset4 = random.nextDouble() * 0.2 + 0.01;
               double offset5 = offset + (double)count19 * 0.01 + (random.nextDouble() - 0.5) * (double)count20 * 0.5;
               double offset6 = offset2 + (random.nextDouble() - 0.5) * 0.5;
               double offset7 = offset3 + (double)count20 * 0.01 + (random.nextDouble() - 0.5) * (double)count19 * 0.5;
               double offset8 = (double)count19 * offset4 + random.nextGaussian() * 0.01;
               double offset9 = -0.03 + random.nextGaussian() * 0.01;
               double offset10 = (double)count20 * offset4 + random.nextGaussian() * 0.01;
               this.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_NORMAL, offset5, offset6, offset7, offset8, offset9, offset10, new int[0]);
            }

            return;
         case 2001:
            Block block = Block.processAction3(count18 & 4095);
            if (block.getMaterial() != Material.coreItemEntry) {
               this.mc
                  .getMcSoundHandler()
                  .applyISound2(
                     new PositionedSoundRecord(
                        new ResourceLocation(block.stepManager20.getText2()),
                        (block.stepManager20.getFactor2() + 1.0F) / 2.0F,
                        block.stepManager20.getFactor() * 0.8F,
                        (float)blockPos.getX() + 0.5F,
                        (float)blockPos.getY() + 0.5F,
                        (float)blockPos.getZ() + 0.5F
                     )
                  );
            }

            if (!((Module)ClientGlobals.moduleManager.noBlockParticles).isToggled()) {
               this.mc.effectRenderer.applyBlockPos(blockPos, block.processAction(count18 >> 12 & 0xFF));
            }
            break;
         case 2002:
            double coordX = (double)blockPos.getX();
            double coordY = (double)blockPos.getY();
            double coordZ = (double)blockPos.getZ();

            for (int index2 = 0; index2 < 8; index2++) {
               this.applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.ITEM_CRACK,
                  coordX,
                  coordY,
                  coordZ,
                  random.nextGaussian() * 0.15,
                  random.nextDouble() * 0.2,
                  random.nextGaussian() * 0.15,
                  new int[]{Item.getIndex(Items.potion), count18}
               );
            }

            int count21 = Items.potion.computeCount(count18);
            float factor = (float)(count21 >> 16 & 0xFF) / 255.0F;
            float factor2 = (float)(count21 >> 8 & 0xFF) / 255.0F;
            float factor3 = (float)(count21 >> 0 & 0xFF) / 255.0F;
            AttackEffectsExplosionNormalType attackEffectsExplosionNormalType = AttackEffectsExplosionNormalType.SPELL;
            if (Items.potion.checkIndex(count18)) {
               attackEffectsExplosionNormalType = AttackEffectsExplosionNormalType.SPELL_INSTANT;
            }

            for (int index3 = 0; index3 < 100; index3++) {
               double offset11 = random.nextDouble() * 4.0;
               double offset12 = random.nextDouble() * Math.PI * 2.0;
               double offset13 = Math.cos(offset12) * offset11;
               double offset14 = 0.01 + random.nextDouble() * 0.5;
               double offset15 = Math.sin(offset12) * offset11;
               TertiaryEntityAdapter tertiaryEntityAdapter = this.resolveTertiaryEntityAdapter(
                  attackEffectsExplosionNormalType.getIndex(), attackEffectsExplosionNormalType.isEnabled(), coordX + offset13 * 0.1, coordY + 0.3, coordZ + offset15 * 0.1, offset13, offset14, offset15, new int[0]
               );
               if (tertiaryEntityAdapter != null) {
                  float factor4 = 0.75F + random.nextFloat() * 0.25F;
                  tertiaryEntityAdapter.applyFloatByFloat(factor * factor4, factor2 * factor4, factor3 * factor4);
                  tertiaryEntityAdapter.resolveTertiaryEntityAdapter((float)offset11);
               }
            }

            this.worldClient.applyBlockPos(blockPos, "game.potion.smash", 1.0F, this.worldClient.random.nextFloat() * 0.1F + 0.9F, false);
            break;
         case 2003:
            double offset16 = (double)blockPos.getX() + 0.5;
            double coordY2 = (double)blockPos.getY();
            double offset17 = (double)blockPos.getZ() + 0.5;

            for (int index4 = 0; index4 < 8; index4++) {
               this.applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.ITEM_CRACK,
                  offset16,
                  coordY2,
                  offset17,
                  random.nextGaussian() * 0.15,
                  random.nextDouble() * 0.2,
                  random.nextGaussian() * 0.15,
                  new int[]{Item.getIndex(Items.enderEye)}
               );
            }

            for (double offset18 = 0.0; offset18 < Math.PI * 2; offset18 += Math.PI / 20) {
               this.applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.PORTAL,
                  offset16 + Math.cos(offset18) * 5.0,
                  coordY2 - 0.4,
                  offset17 + Math.sin(offset18) * 5.0,
                  Math.cos(offset18) * -5.0,
                  0.0,
                  Math.sin(offset18) * -5.0,
                  new int[0]
               );
               this.applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.PORTAL,
                  offset16 + Math.cos(offset18) * 5.0,
                  coordY2 - 0.4,
                  offset17 + Math.sin(offset18) * 5.0,
                  Math.cos(offset18) * -7.0,
                  0.0,
                  Math.sin(offset18) * -7.0,
                  new int[0]
               );
            }

            return;
         case 2004:
            for (int index5 = 0; index5 < 20; index5++) {
               double offset19 = (double)blockPos.getX() + 0.5 + ((double)this.worldClient.random.nextFloat() - 0.5) * 2.0;
               double offset20 = (double)blockPos.getY() + 0.5 + ((double)this.worldClient.random.nextFloat() - 0.5) * 2.0;
               double offset21 = (double)blockPos.getZ() + 0.5 + ((double)this.worldClient.random.nextFloat() - 0.5) * 2.0;
               this.worldClient.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_NORMAL, offset19, offset20, offset21, 0.0, 0.0, 0.0, new int[0]);
               this.worldClient.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.FLAME, offset19, offset20, offset21, 0.0, 0.0, 0.0, new int[0]);
            }

            return;
         case 2005:
            ItemDye.applyWorld(this.worldClient, blockPos, count18);
      }
   }

   public String getTextAsText() {
      int length = this.renderGlobalEntry.renderChunkArray.length;
      int count17 = 0;
      Iterator iterator = this.list8.iterator();

      while (iterator.hasNext()) {
         IronShard ironShard = ((AuxiliaryItemArrayEntry12Entry)iterator.next()).renderChunk.ironShard;
         if (ironShard != IronShard.ironShard && !ironShard.isEnabled()) {
            count17++;
         }
      }

      return String.format("C: %d/%d %sD: %d, %s", count17, length, this.mc.enabled ? "(s) " : "", this.count10, this.tertiaryLoggerHolder.getText());
   }

   public void applyEntityByEntity(Entity entity2, double offset, PreMotionConsumer preMotionConsumer, int count17, boolean flag) {
      Object entry = preMotionConsumer;
      if (this.mc.gameSettings.indexOffset16 != this.count10) {
         this.updateState();
      }

      this.worldClient.uNKNOWNManager.applyTextByText("camera");
      double offset2 = entity2.posX - this.value10;
      double offset3 = entity2.posY - this.value11;
      double offset4 = entity2.posZ - this.value2;
      if (this.count6 != entity2.count7 || this.count13 != entity2.count2 || this.count != entity2.count8 || offset2 * offset2 + offset3 * offset3 + offset4 * offset4 > 16.0) {
         this.value10 = entity2.posX;
         this.value11 = entity2.posY;
         this.value2 = entity2.posZ;
         this.count6 = entity2.count7;
         this.count13 = entity2.count2;
         this.count = entity2.count8;
         this.renderGlobalEntry.applyDouble(entity2.posX, entity2.posZ);
      }

      if (BuildManager.isActive28()) {
         TargetEstimator.applyRenderGlobal3(this);
      }

      this.worldClient.uNKNOWNManager.applyText("renderlistcamera");
      double offset5 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * offset;
      double offset6 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * offset;
      double offset7 = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * offset;
      this.extraDoubleEntry.applyDouble(offset5, offset6, offset7);
      this.worldClient.uNKNOWNManager.applyText("cull");
      if (this.customFloatArrayEntry != null) {
         EntityEspRenderer entityEspRenderer = new EntityEspRenderer(this.customFloatArrayEntry);
         entityEspRenderer.processDouble(this.commonDoubleEntry.value3, this.commonDoubleEntry.value2, this.commonDoubleEntry.value);
         entry = entityEspRenderer;
      }

      this.mc.uNKNOWNManager.applyText("culling");
      int count18 = MathHelper.ceiling_double_int(offset5);
      int count19 = MathHelper.ceiling_double_int(offset6 + (double)entity2.getHeight2());
      int count20 = MathHelper.ceiling_double_int(offset7);
      RenderChunk renderChunk = this.renderGlobalEntry.resolveRenderChunk(count18, count19, count20);
      this.enabled6 = this.enabled6
         || !this.set2.isEmpty()
         || entity2.posX != this.value
         || entity2.posY != this.value6
         || entity2.posZ != this.value9
         || (double)entity2.rotationPitch != this.value4
         || (double)entity2.rotationYaw != this.value5;
      this.value = entity2.posX;
      this.value6 = entity2.posY;
      this.value9 = entity2.posZ;
      this.value4 = (double)entity2.rotationPitch;
      this.value5 = (double)entity2.rotationYaw;
      boolean flag2 = this.customFloatArrayEntry != null;
      this.mc.uNKNOWNManager.applyText("update");
      MBSManager.nativeLongEntry2.nanoTime();
      int countAsIndex = this.getCountAsIndex();
      if (countAsIndex != this.count7) {
         this.count7 = countAsIndex;
         this.enabled6 = true;
      }

      int count21 = 256;
      if (!EnumFacingArrayEntry.isActive()) {
         this.enabled6 = true;
      }

      if (!flag2 && this.enabled6 && BuildManager.isActive74()) {
         count21 = EnumFacingArrayEntry.computeCount(this.worldClient, entity2, this.count10);
      }

      RenderChunk renderChunk2 = this.renderGlobalEntry.resolveRenderChunk(new BlockPos(entity2.posX, entity2.posY, entity2.posZ));
      if (PreEndHandManager.enabled12) {
         this.list8 = this.list3;
         this.list4 = this.list5;
         this.list9 = this.list2;
         if (!flag2 && this.enabled6) {
            this.updateState2();
            if (renderChunk2 != null && renderChunk2.getBlockPos().getY() > count21) {
               this.list4.add(renderChunk2.getAuxiliaryItemArrayEntry12Entry());
            }

            Iterator iterator = RayTraceSerializer.resolveIterator(this.worldClient, offset, entity2, this.count10, this.renderGlobalEntry);

            while (iterator.hasNext()) {
               RenderChunk renderChunk3 = (RenderChunk)iterator.next();
               if (renderChunk3 != null && renderChunk3.getBlockPos().getY() <= count21) {
                  AuxiliaryItemArrayEntry12Entry auxiliaryItemArrayEntry12Entry = renderChunk3.getAuxiliaryItemArrayEntry12Entry();
                  if (!renderChunk3.ironShard.isEnabled() || renderChunk3.isEnabled()) {
                     this.list8.add(auxiliaryItemArrayEntry12Entry);
                  }

                  if (renderChunk3.getAlertNotificationSound().enabled2) {
                     this.list4.add(auxiliaryItemArrayEntry12Entry);
                  }

                  if (!renderChunk3.getIronShard().getList().isEmpty()) {
                     this.list9.add(auxiliaryItemArrayEntry12Entry);
                  }
               }
            }
         }
      } else {
         this.list8 = this.list6;
         this.list4 = this.list;
         this.list9 = this.list7;
      }

      if (!flag2 && this.enabled6 && !PreEndHandManager.enabled12) {
         this.enabled6 = false;
         this.updateState2();
         this.deque.clear();
         Deque deque2 = this.deque;
         boolean enabled7 = this.mc.enabled;
         if (renderChunk != null && renderChunk.getBlockPos().getY() <= count21) {
            boolean flag3 = false;
            AuxiliaryItemArrayEntry12Entry auxiliaryItemArrayEntry12Entry2 = new AuxiliaryItemArrayEntry12Entry(renderChunk, (EnumFacing)null, 0);
            Set set6 = set3;
            if (set6.size() == 1) {
               Vector3f vector3f = this.resolveVector3f(entity2, offset);
               EnumFacing enumFacing = EnumFacing.resolveEnumFacing(vector3f.x, vector3f.y, vector3f.z).getEnumFacing5();
               set6.remove(enumFacing);
            }

            if (set6.isEmpty()) {
               flag3 = true;
            }

            if (flag3 && !flag) {
               this.list8.add(auxiliaryItemArrayEntry12Entry2);
            } else {
               if (flag && this.worldClient.getIBlockState(count18, count19, count20).getBlock().processAction15()) {
                  enabled7 = false;
               }

               renderChunk.checkIndex(count17);
               deque2.add(auxiliaryItemArrayEntry12Entry2);
            }
         } else {
            int count22 = count19 > 0 ? Math.min(count21, 248) : 8;
            if (renderChunk2 != null) {
               this.list4.add(renderChunk2.getAuxiliaryItemArrayEntry12Entry());
            }

            for (int index = -this.count10; index <= this.count10; index++) {
               for (int index2 = -this.count10; index2 <= this.count10; index2++) {
                  RenderChunk renderChunk4 = this.renderGlobalEntry.resolveRenderChunk(new BlockPos((index << 4) + 8, count22, (index2 << 4) + 8));
                  if (renderChunk4 != null && renderChunk4.matchesPreMotionConsumer((PreMotionConsumer)entry, count17)) {
                     renderChunk4.checkIndex(count17);
                     AuxiliaryItemArrayEntry12Entry auxiliaryItemArrayEntry12Entry3 = renderChunk4.getAuxiliaryItemArrayEntry12Entry();
                     AuxiliaryItemArrayEntry12Entry.applyAuxiliaryItemArrayEntry12E(auxiliaryItemArrayEntry12Entry3, null, 0);
                     deque2.add(auxiliaryItemArrayEntry12Entry3);
                  }
               }
            }
         }

         this.mc.uNKNOWNManager.applyTextByText("iteration");
         boolean active = BuildManager.isActive63();

         while (!deque2.isEmpty()) {
            AuxiliaryItemArrayEntry12Entry auxiliaryItemArrayEntry12Entry4 = (AuxiliaryItemArrayEntry12Entry)deque2.poll();
            RenderChunk renderChunk5 = auxiliaryItemArrayEntry12Entry4.renderChunk;
            EnumFacing enumFacing2 = auxiliaryItemArrayEntry12Entry4.enumFacing;
            IronShard ironShard = renderChunk5.ironShard;
            if (!ironShard.isEnabled() || renderChunk5.isEnabled()) {
               this.list8.add(auxiliaryItemArrayEntry12Entry4);
            }

            if (renderChunk5.getAlertNotificationSound().enabled2) {
               this.list4.add(auxiliaryItemArrayEntry12Entry4);
            }

            if (!ironShard.getList().isEmpty()) {
               this.list9.add(auxiliaryItemArrayEntry12Entry4);
            }

            for (EnumFacing enumFacing3 : enabled7 ? EnumFacingArrayEntry.resolveEnumFacingArray(auxiliaryItemArrayEntry12Entry4.count) : EnumFacing.VALUES) {
               if (!enabled7 || enumFacing2 == null || ironShard.matchesEnumFacing(enumFacing2.getEnumFacing5(), enumFacing3)) {
                  RenderChunk renderChunk6 = this.resolveRenderChunk(count18, count20, renderChunk5, enumFacing3, active, count21);
                  if (renderChunk6 != null && renderChunk6.checkIndex(count17) && renderChunk6.matchesPreMotionConsumer((PreMotionConsumer)entry, count17)) {
                     int count23 = auxiliaryItemArrayEntry12Entry4.count | 1 << enumFacing3.ordinal();
                     AuxiliaryItemArrayEntry12Entry auxiliaryItemArrayEntry12Entry5 = renderChunk6.getAuxiliaryItemArrayEntry12Entry();
                     AuxiliaryItemArrayEntry12Entry.applyAuxiliaryItemArrayEntry12E(auxiliaryItemArrayEntry12Entry5, enumFacing3, count23);
                     deque2.add(auxiliaryItemArrayEntry12Entry5);
                  }
               }
            }
         }

         this.mc.uNKNOWNManager.updateState();
      }

      this.mc.uNKNOWNManager.applyText("captureFrustum");
      if (this.enabled2) {
         this.applyDouble(offset5, offset6, offset7);
         this.enabled2 = false;
      }

      MBSManager.nativeLongEntry2.updateState();
      if (PreEndHandManager.enabled12) {
         PreEndHandManager.updateState59();
      } else {
         this.mc.uNKNOWNManager.applyText("rebuildNear");
         this.tertiaryLoggerHolder.updateState2();
         Set set7 = this.set2;
         this.set2 = Sets.newLinkedHashSet();
         MBSManager.nativeLongEntry7.nanoTime();
         Iterator iterator2 = this.list8.iterator();

         while (iterator2.hasNext()) {
            RenderChunk renderChunk7 = ((AuxiliaryItemArrayEntry12Entry)iterator2.next()).renderChunk;
            if (renderChunk7.isEnabled() || set7.contains(renderChunk7)) {
               this.enabled6 = true;
               BlockPos blockPos = renderChunk7.getBlockPos();
               if (!(
                  computeValue((double)count18, (double)count19, (double)count20, (double)(blockPos.getX() + 8), (double)(blockPos.getY() + 8), (double)(blockPos.getZ() + 8))
                     < 768.0
               )) {
                  this.set2.add(renderChunk7);
               } else if (!renderChunk7.isEnabled5()) {
                  this.set.add(renderChunk7);
               } else {
                  this.mc.uNKNOWNManager.applyTextByText("build near");
                  this.tertiaryLoggerHolder.matchesRenderChunkByRenderChunk(renderChunk7);
                  renderChunk7.setEnabled(false);
                  this.mc.uNKNOWNManager.updateState();
               }
            }
         }

         MBSManager.nativeLongEntry7.updateState();
         this.set2.addAll(set7);
         this.mc.uNKNOWNManager.updateState();
      }
   }

   public void applyTessellatorByTessellator(Tessellator tessellator, WorldRenderer worldRenderer, Entity entity2, float factor) {
      double offset = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * (double)factor;
      double offset2 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * (double)factor;
      double offset3 = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * (double)factor;
      if (!this.map.isEmpty()) {
         this.textureManager.applyResourceLocation3(ListenerAdapter27.resourceLocation);
         this.updateState4();
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry5);
         worldRenderer.applyDouble(-offset, -offset2, -offset3);
         worldRenderer.renderElement3();
         Iterator iterator = this.map.values().iterator();

         while (iterator.hasNext()) {
            AuxiliaryBlockPosEntry auxiliaryBlockPosEntry = (AuxiliaryBlockPosEntry)iterator.next();
            BlockPos blockPos = auxiliaryBlockPosEntry.getBlockPos();
            double offset4 = (double)blockPos.getX() - offset;
            double offset5 = (double)blockPos.getY() - offset2;
            double offset6 = (double)blockPos.getZ() - offset3;
            Block block = this.worldClient.getBlockState(blockPos).getBlock();
            if (!(block instanceof BlockTrappedChest)
               && !(block instanceof BlockEnderChest)
               && !(block instanceof VClipCollisionFilter)
               && !(block instanceof BlockSkull)) {
               if (offset4 * offset4 + offset5 * offset5 + offset6 * offset6 > 1024.0) {
                  iterator.remove();
               } else {
                  IBlockState blockState = this.worldClient.getBlockState(blockPos);
                  if (blockState.getBlock().getMaterial() != Material.coreItemEntry) {
                     int count17 = auxiliaryBlockPosEntry.getCount();
                     ServiceStringEntry serviceStringEntry = this.serviceStringEntryArray[count17];
                     this.mc.getGoldenCrystal().applyIBlockState(blockState, blockPos, serviceStringEntry, this.worldClient);
                  }
               }
            }
         }

         tessellator.updateState();
         worldRenderer.applyDouble(0.0, 0.0, 0.0);
         this.updateState12();
      }
   }

   public boolean isActiveAsFlag() {
      return this.set2.isEmpty() && this.tertiaryLoggerHolder.isActive();
   }

   private void applyIterator(Iterator iterator) {
      while (iterator.hasNext()) {
         int count17 = ((AuxiliaryBlockPosEntry)iterator.next()).getCount2();
         if (this.count15 - count17 > 400) {
            iterator.remove();
         }
      }
   }

   @Override
   public void applyText(String text, BlockPos blockPos) {
      ISound iSound = (ISound)this.map2.get(blockPos);
      if (iSound != null) {
         this.mc.getMcSoundHandler().applyISound(iSound);
         this.map2.remove(blockPos);
      }

      if (text != null) {
         ItemRecordWait itemRecordWait = ItemRecordWait.get(text);
         if (itemRecordWait != null) {
            this.mc.ingameGUI.applyText(itemRecordWait.getText());
         }

         PositionedSoundRecord positionedSoundRecord = PositionedSoundRecord.createRecordAtPosition(
            new ResourceLocation(text), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ()
         );
         this.map2.put(blockPos, positionedSoundRecord);
         this.mc.getMcSoundHandler().applyISound2(positionedSoundRecord);
      }
   }

   public int getCountAsIndex() {
      if (this.worldClient == null) {
         return 0;
      } else {
         IChunkProvider commonListener = this.worldClient.getCommonListener();
         if (commonListener == null) {
            return 0;
         } else {
            if (commonListener != this.iChunkProvider) {
               this.iChunkProvider = commonListener;
               if (commonListener instanceof MultiplayerChunkCacheListener) {
                  this.internalFloatEntry = ((MultiplayerChunkCacheListener)commonListener).internalFloatEntry;
               } else if (commonListener instanceof ListenerAdapter21) {
                  this.internalFloatEntry = ((ListenerAdapter21)commonListener).internalFloatEntry;
               }
            }

            return this.internalFloatEntry == null ? 0 : this.internalFloatEntry.getCount2();
         }
      }
   }

   public void updateState17() {
      if (this.tertiaryLoggerHolder != null) {
         this.tertiaryLoggerHolder.updateState3();
      }
   }

   private void applyFloatByFloat(float factor, int count17) {
      float factor2 = 0.0F;
      GlStateManager.disableAlpha();
      float factor3 = (float)(
         this.mc.getRenderViewEntity().lastTickPosY + (this.mc.getRenderViewEntity().posY - this.mc.getRenderViewEntity().lastTickPosY) * (double)factor2
      );
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      double offset = (double)((float)this.count15 + factor2);
      double offset2 = (
            this.mc.getRenderViewEntity().prevPosX
               + (this.mc.getRenderViewEntity().posX - this.mc.getRenderViewEntity().prevPosX) * (double)factor2
               + offset * 0.03F
         )
         / 12.0;
      double offset3 = (this.mc.getRenderViewEntity().prevPosZ + (this.mc.getRenderViewEntity().posZ - this.mc.getRenderViewEntity().prevPosZ) * (double)factor2)
            / 12.0
         + 0.33F;
      float factor4 = this.worldClient.secondaryFloatArrayEntry.getFactor() - factor3 + 0.33F + this.mc.gameSettings.scaleFactor60 * 128.0F;
      int count18 = MathHelper.ceiling_double_int(offset2 / 2048.0);
      int count19 = MathHelper.ceiling_double_int(offset3 / 2048.0);
      double offset4 = offset2 - (double)(count18 * 2048);
      double offset5 = offset3 - (double)(count19 * 2048);
      this.textureManager.applyResourceLocation3(resourceLocation5);
      GlStateManager.disableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      Vec3 vec = this.worldClient.resolveVecByFloat(factor2);
      float posX = (float)vec.xCoord;
      float posY = (float)vec.yCoord;
      float posZ = (float)vec.zCoord;
      this.internalMinecraftEntry.applyVec3(true, this.count15, factor2, vec);
      if (count17 != 2) {
         float factor5 = (posX * 30.0F + posY * 59.0F + posZ * 11.0F) / 100.0F;
         float factor6 = (posX * 30.0F + posY * 70.0F) / 100.0F;
         float factor7 = (posX * 30.0F + posZ * 70.0F) / 100.0F;
         posX = factor5;
         posY = factor6;
         posZ = factor7;
      }

      float factor8 = posX * 0.9F;
      float factor9 = posY * 0.9F;
      float factor10 = posZ * 0.9F;
      float factor11 = posX * 0.7F;
      float factor12 = posY * 0.7F;
      float factor13 = posZ * 0.7F;
      float factor14 = posX * 0.8F;
      float factor15 = posY * 0.8F;
      float factor16 = posZ * 0.8F;
      float factor17 = (float)MathHelper.ceiling_double_int(offset4) * 0.00390625F;
      float factor18 = (float)MathHelper.ceiling_double_int(offset5) * 0.00390625F;
      float factor19 = (float)(offset4 - (double)MathHelper.ceiling_double_int(offset4));
      float factor20 = (float)(offset5 - (double)MathHelper.ceiling_double_int(offset5));
      GlStateManager.translate3f3(12.0F, 1.0F, 12.0F);

      for (int index = 0; index < 2; index++) {
         if (index == 0) {
            GlStateManager.applyFlag(false, false, false, false);
         } else {
            switch (count17) {
               case 0:
                  GlStateManager.applyFlag(false, true, true, true);
                  break;
               case 1:
                  GlStateManager.applyFlag(true, false, false, true);
                  break;
               case 2:
                  GlStateManager.applyFlag(true, true, true, true);
            }
         }

         this.internalMinecraftEntry.updateState();
      }

      if (this.internalMinecraftEntry.isActive()) {
         this.internalMinecraftEntry.glNewList();

         for (int index2 = -3; index2 <= 4; index2++) {
            for (int index3 = -3; index3 <= 4; index3++) {
               worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry2);
               float factor21 = (float)(index2 * 8);
               float factor22 = (float)(index3 * 8);
               float factor23 = factor21 - factor19;
               float factor24 = factor22 - factor20;
               if (factor4 > -5.0F) {
                  worldRenderer.getWorldRenderer((double)(factor23 + 0.0F), (double)(factor4 + 0.0F), (double)(factor24 + 8.0F))
                     .getWorldRenderer6((double)((factor21 + 0.0F) * 0.00390625F + factor17), (double)((factor22 + 8.0F) * 0.00390625F + factor18))
                     .resolveWorldRenderer(factor11, factor12, factor13, 0.8F)
                     .resolveWorldRenderer(0.0F, -1.0F, 0.0F)
                     .updateState();
                  worldRenderer.getWorldRenderer((double)(factor23 + 8.0F), (double)(factor4 + 0.0F), (double)(factor24 + 8.0F))
                     .getWorldRenderer6((double)((factor21 + 8.0F) * 0.00390625F + factor17), (double)((factor22 + 8.0F) * 0.00390625F + factor18))
                     .resolveWorldRenderer(factor11, factor12, factor13, 0.8F)
                     .resolveWorldRenderer(0.0F, -1.0F, 0.0F)
                     .updateState();
                  worldRenderer.getWorldRenderer((double)(factor23 + 8.0F), (double)(factor4 + 0.0F), (double)(factor24 + 0.0F))
                     .getWorldRenderer6((double)((factor21 + 8.0F) * 0.00390625F + factor17), (double)((factor22 + 0.0F) * 0.00390625F + factor18))
                     .resolveWorldRenderer(factor11, factor12, factor13, 0.8F)
                     .resolveWorldRenderer(0.0F, -1.0F, 0.0F)
                     .updateState();
                  worldRenderer.getWorldRenderer((double)(factor23 + 0.0F), (double)(factor4 + 0.0F), (double)(factor24 + 0.0F))
                     .getWorldRenderer6((double)((factor21 + 0.0F) * 0.00390625F + factor17), (double)((factor22 + 0.0F) * 0.00390625F + factor18))
                     .resolveWorldRenderer(factor11, factor12, factor13, 0.8F)
                     .resolveWorldRenderer(0.0F, -1.0F, 0.0F)
                     .updateState();
               }

               if (factor4 <= 5.0F) {
                  worldRenderer.getWorldRenderer((double)(factor23 + 0.0F), (double)(factor4 + 4.0F - 9.765625E-4F), (double)(factor24 + 8.0F))
                     .getWorldRenderer6((double)((factor21 + 0.0F) * 0.00390625F + factor17), (double)((factor22 + 8.0F) * 0.00390625F + factor18))
                     .resolveWorldRenderer(posX, posY, posZ, 0.8F)
                     .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
                     .updateState();
                  worldRenderer.getWorldRenderer((double)(factor23 + 8.0F), (double)(factor4 + 4.0F - 9.765625E-4F), (double)(factor24 + 8.0F))
                     .getWorldRenderer6((double)((factor21 + 8.0F) * 0.00390625F + factor17), (double)((factor22 + 8.0F) * 0.00390625F + factor18))
                     .resolveWorldRenderer(posX, posY, posZ, 0.8F)
                     .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
                     .updateState();
                  worldRenderer.getWorldRenderer((double)(factor23 + 8.0F), (double)(factor4 + 4.0F - 9.765625E-4F), (double)(factor24 + 0.0F))
                     .getWorldRenderer6((double)((factor21 + 8.0F) * 0.00390625F + factor17), (double)((factor22 + 0.0F) * 0.00390625F + factor18))
                     .resolveWorldRenderer(posX, posY, posZ, 0.8F)
                     .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
                     .updateState();
                  worldRenderer.getWorldRenderer((double)(factor23 + 0.0F), (double)(factor4 + 4.0F - 9.765625E-4F), (double)(factor24 + 0.0F))
                     .getWorldRenderer6((double)((factor21 + 0.0F) * 0.00390625F + factor17), (double)((factor22 + 0.0F) * 0.00390625F + factor18))
                     .resolveWorldRenderer(posX, posY, posZ, 0.8F)
                     .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
                     .updateState();
               }

               if (index2 > -1) {
                  for (int index4 = 0; index4 < 8; index4++) {
                     worldRenderer.getWorldRenderer((double)(factor23 + (float)index4 + 0.0F), (double)(factor4 + 0.0F), (double)(factor24 + 8.0F))
                        .getWorldRenderer6((double)((factor21 + (float)index4 + 0.5F) * 0.00390625F + factor17), (double)((factor22 + 8.0F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor8, factor9, factor10, 0.8F)
                        .resolveWorldRenderer(-1.0F, 0.0F, 0.0F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(factor23 + (float)index4 + 0.0F), (double)(factor4 + 4.0F), (double)(factor24 + 8.0F))
                        .getWorldRenderer6((double)((factor21 + (float)index4 + 0.5F) * 0.00390625F + factor17), (double)((factor22 + 8.0F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor8, factor9, factor10, 0.8F)
                        .resolveWorldRenderer(-1.0F, 0.0F, 0.0F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(factor23 + (float)index4 + 0.0F), (double)(factor4 + 4.0F), (double)(factor24 + 0.0F))
                        .getWorldRenderer6((double)((factor21 + (float)index4 + 0.5F) * 0.00390625F + factor17), (double)((factor22 + 0.0F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor8, factor9, factor10, 0.8F)
                        .resolveWorldRenderer(-1.0F, 0.0F, 0.0F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(factor23 + (float)index4 + 0.0F), (double)(factor4 + 0.0F), (double)(factor24 + 0.0F))
                        .getWorldRenderer6((double)((factor21 + (float)index4 + 0.5F) * 0.00390625F + factor17), (double)((factor22 + 0.0F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor8, factor9, factor10, 0.8F)
                        .resolveWorldRenderer(-1.0F, 0.0F, 0.0F)
                        .updateState();
                  }
               }

               if (index2 <= 1) {
                  for (int index5 = 0; index5 < 8; index5++) {
                     worldRenderer.getWorldRenderer((double)(factor23 + (float)index5 + 1.0F - 9.765625E-4F), (double)(factor4 + 0.0F), (double)(factor24 + 8.0F))
                        .getWorldRenderer6((double)((factor21 + (float)index5 + 0.5F) * 0.00390625F + factor17), (double)((factor22 + 8.0F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor8, factor9, factor10, 0.8F)
                        .resolveWorldRenderer(1.0F, 0.0F, 0.0F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(factor23 + (float)index5 + 1.0F - 9.765625E-4F), (double)(factor4 + 4.0F), (double)(factor24 + 8.0F))
                        .getWorldRenderer6((double)((factor21 + (float)index5 + 0.5F) * 0.00390625F + factor17), (double)((factor22 + 8.0F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor8, factor9, factor10, 0.8F)
                        .resolveWorldRenderer(1.0F, 0.0F, 0.0F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(factor23 + (float)index5 + 1.0F - 9.765625E-4F), (double)(factor4 + 4.0F), (double)(factor24 + 0.0F))
                        .getWorldRenderer6((double)((factor21 + (float)index5 + 0.5F) * 0.00390625F + factor17), (double)((factor22 + 0.0F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor8, factor9, factor10, 0.8F)
                        .resolveWorldRenderer(1.0F, 0.0F, 0.0F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(factor23 + (float)index5 + 1.0F - 9.765625E-4F), (double)(factor4 + 0.0F), (double)(factor24 + 0.0F))
                        .getWorldRenderer6((double)((factor21 + (float)index5 + 0.5F) * 0.00390625F + factor17), (double)((factor22 + 0.0F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor8, factor9, factor10, 0.8F)
                        .resolveWorldRenderer(1.0F, 0.0F, 0.0F)
                        .updateState();
                  }
               }

               if (index3 > -1) {
                  for (int index6 = 0; index6 < 8; index6++) {
                     worldRenderer.getWorldRenderer((double)(factor23 + 0.0F), (double)(factor4 + 4.0F), (double)(factor24 + (float)index6 + 0.0F))
                        .getWorldRenderer6((double)((factor21 + 0.0F) * 0.00390625F + factor17), (double)((factor22 + (float)index6 + 0.5F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor14, factor15, factor16, 0.8F)
                        .resolveWorldRenderer(0.0F, 0.0F, -1.0F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(factor23 + 8.0F), (double)(factor4 + 4.0F), (double)(factor24 + (float)index6 + 0.0F))
                        .getWorldRenderer6((double)((factor21 + 8.0F) * 0.00390625F + factor17), (double)((factor22 + (float)index6 + 0.5F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor14, factor15, factor16, 0.8F)
                        .resolveWorldRenderer(0.0F, 0.0F, -1.0F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(factor23 + 8.0F), (double)(factor4 + 0.0F), (double)(factor24 + (float)index6 + 0.0F))
                        .getWorldRenderer6((double)((factor21 + 8.0F) * 0.00390625F + factor17), (double)((factor22 + (float)index6 + 0.5F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor14, factor15, factor16, 0.8F)
                        .resolveWorldRenderer(0.0F, 0.0F, -1.0F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(factor23 + 0.0F), (double)(factor4 + 0.0F), (double)(factor24 + (float)index6 + 0.0F))
                        .getWorldRenderer6((double)((factor21 + 0.0F) * 0.00390625F + factor17), (double)((factor22 + (float)index6 + 0.5F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor14, factor15, factor16, 0.8F)
                        .resolveWorldRenderer(0.0F, 0.0F, -1.0F)
                        .updateState();
                  }
               }

               if (index3 <= 1) {
                  for (int index7 = 0; index7 < 8; index7++) {
                     worldRenderer.getWorldRenderer((double)(factor23 + 0.0F), (double)(factor4 + 4.0F), (double)(factor24 + (float)index7 + 1.0F - 9.765625E-4F))
                        .getWorldRenderer6((double)((factor21 + 0.0F) * 0.00390625F + factor17), (double)((factor22 + (float)index7 + 0.5F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor14, factor15, factor16, 0.8F)
                        .resolveWorldRenderer(0.0F, 0.0F, 1.0F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(factor23 + 8.0F), (double)(factor4 + 4.0F), (double)(factor24 + (float)index7 + 1.0F - 9.765625E-4F))
                        .getWorldRenderer6((double)((factor21 + 8.0F) * 0.00390625F + factor17), (double)((factor22 + (float)index7 + 0.5F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor14, factor15, factor16, 0.8F)
                        .resolveWorldRenderer(0.0F, 0.0F, 1.0F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(factor23 + 8.0F), (double)(factor4 + 0.0F), (double)(factor24 + (float)index7 + 1.0F - 9.765625E-4F))
                        .getWorldRenderer6((double)((factor21 + 8.0F) * 0.00390625F + factor17), (double)((factor22 + (float)index7 + 0.5F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor14, factor15, factor16, 0.8F)
                        .resolveWorldRenderer(0.0F, 0.0F, 1.0F)
                        .updateState();
                     worldRenderer.getWorldRenderer((double)(factor23 + 0.0F), (double)(factor4 + 0.0F), (double)(factor24 + (float)index7 + 1.0F - 9.765625E-4F))
                        .getWorldRenderer6((double)((factor21 + 0.0F) * 0.00390625F + factor17), (double)((factor22 + (float)index7 + 0.5F) * 0.00390625F + factor18))
                        .resolveWorldRenderer(factor14, factor15, factor16, 0.8F)
                        .resolveWorldRenderer(0.0F, 0.0F, 1.0F)
                        .updateState();
                  }
               }

               tessellator.updateState();
            }
         }

         this.internalMinecraftEntry.updateState3();
      }

      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.enableBlend();
      GlStateManager.enableLighting();
   }

   private RenderChunk resolveRenderChunk(int count17, int count18, RenderChunk renderChunk, EnumFacing enumFacing, boolean flag, int count19) {
      RenderChunk renderChunk2 = renderChunk.resolveRenderChunk(enumFacing);
      if (renderChunk2 == null) {
         return null;
      } else if (renderChunk2.getBlockPos().getY() > count19) {
         return null;
      } else {
         if (flag) {
            BlockPos blockPos = renderChunk2.getBlockPos();
            int count20 = count17 - blockPos.getX();
            int count21 = count18 - blockPos.getZ();
            if (count20 * count20 + count21 * count21 > this.count3) {
               return null;
            }
         }

         return renderChunk2;
      }
   }

   public int getIndexOffset() {
      return this.indexOffset;
   }

   public void applyTime(long value12) {
      long value13 = (long)((double)value12 + 1.0E8);
      this.enabled6 = this.enabled6 | this.tertiaryLoggerHolder.checkTime(value13);
      if (this.set.size() > 0) {
         Iterator iterator = this.set.iterator();

         while (iterator.hasNext()) {
            RenderChunk renderChunk = (RenderChunk)iterator.next();
            if (!this.tertiaryLoggerHolder.matchesRenderChunk2(renderChunk)) {
               break;
            }

            renderChunk.setEnabled(false);
            iterator.remove();
            this.set2.remove(renderChunk);
            this.set4.remove(renderChunk);
         }
      }

      if (this.set4.size() > 0) {
         Iterator iterator2 = this.set4.iterator();
         while (iterator2.hasNext()) {
            RenderChunk renderChunk2 = (RenderChunk)iterator2.next();
            if (this.tertiaryLoggerHolder.matchesRenderChunk(renderChunk2)) {
               iterator2.remove();
            }
         }
      }

      double offset = 0.0;
      int count17 = BuildManager.getCount6();
      if (!this.set2.isEmpty()) {
         Iterator iterator3 = this.set2.iterator();

         while (iterator3.hasNext()) {
            RenderChunk renderChunk3 = (RenderChunk)iterator3.next();
            boolean active = renderChunk3.isActive();
            boolean flag;
            if (active) {
               flag = this.tertiaryLoggerHolder.matchesRenderChunkByRenderChunk(renderChunk3);
            } else {
               flag = this.tertiaryLoggerHolder.matchesRenderChunk2(renderChunk3);
            }

            if (!flag) {
               break;
            }

            renderChunk3.setEnabled(false);
            iterator3.remove();
            if (!active) {
               double offset2 = 2.0 * CurveRegistry.computeValue(renderChunk3);
               offset += offset2;
               if (offset > (double)count17) {
                  break;
               }
            }
         }
      }
   }

   private Set resolveSet(BlockPos blockPos) {
      BitSetEntry bitSetEntry = new BitSetEntry();
      BlockPos blockPos2 = new BlockPos(blockPos.getX() >> 4 << 4, blockPos.getY() >> 4 << 4, blockPos.getZ() >> 4 << 4);
      AlertNotificationSound loggerEntry = this.worldClient.getLoggerEntry(blockPos2);

      for (AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter : (Iterable<AuxiliaryBlockPosAdapter>)(Iterable)BlockPos.resolveIterableByBlockPos(blockPos2, blockPos2.add(15, 15, 15))) {
         if (loggerEntry.resolveBlock(auxiliaryBlockPosAdapter).processAction15()) {
            bitSetEntry.applyBlockPos(auxiliaryBlockPosAdapter);
         }
      }

      return bitSetEntry.resolveSet(blockPos);
   }

   public WorldClient getWorldClient() {
      return this.worldClient;
   }

   public RenderChunk resolveRenderChunk(BlockPos blockPos) {
      return this.renderGlobalEntry.resolveRenderChunk(blockPos);
   }

   @Override
   public void applyBlockPos(BlockPos blockPos) {
      int coordX = blockPos.getX();
      int coordY = blockPos.getY();
      int coordZ = blockPos.getZ();
      this.applyIndexByIndex(coordX - 1, coordY - 1, coordZ - 1, coordX + 1, coordY + 1, coordZ + 1);
   }

   @Override
   public void applyIndexArray(int count17, boolean flag, double posX, double posX2, double posX3, double posX4, double posY, double posZ, int[] countArray) {
      try {
         this.resolveTertiaryEntityAdapter(count17, flag, posX, posX2, posX3, posX4, posY, posZ, countArray);
      } catch (Throwable throwable) {
         CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Exception while adding particle");
         CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Particle being added");
         crashReportCategory.add("ID", count17);
         if (countArray != null) {
            crashReportCategory.add("Parameters", countArray);
         }

         crashReportCategory.applyText("Position", new BezierAggregator(this, posX, posX2, posX3));
         throw new SecondaryRuntimeExceptionAdapter(crashReport);
      }
   }

   private void updateState13() {
      if (BuildManager.isActive32()) {
         GlStateManager.applyState20();
         GlStateManager.applyState10();
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         RenderHelper.disableStandardItemLighting();
         GlStateManager.depthMask(false);
         this.textureManager.applyResourceLocation3(resourceLocation4);
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();

         for (int index = 0; index < 6; index++) {
            GlStateManager.pushMatrix();
            if (index == 1) {
               GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (index == 2) {
               GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (index == 3) {
               GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            }

            if (index == 4) {
               GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            }

            if (index == 5) {
               GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
            }

            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
            int count17 = 40;
            int count18 = 40;
            int count19 = 40;
            if (BuildManager.isActive33()) {
               Vec3 vec = LoadingManager.resolveVec4(
                  new Vec3((double)count17 / 255.0, (double)count18 / 255.0, (double)count19 / 255.0), this.worldClient, this.mc.getRenderViewEntity(), 0.0F
               );
               count17 = (int)(vec.xCoord * 255.0);
               count18 = (int)(vec.yCoord * 255.0);
               count19 = (int)(vec.zCoord * 255.0);
            }

            worldRenderer.getWorldRenderer(-100.0, -100.0, -100.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(count17, count18, count19, 255).updateState();
            worldRenderer.getWorldRenderer(-100.0, -100.0, 100.0).getWorldRenderer6(0.0, 16.0).resolveWorldRenderer(count17, count18, count19, 255).updateState();
            worldRenderer.getWorldRenderer(100.0, -100.0, 100.0).getWorldRenderer6(16.0, 16.0).resolveWorldRenderer(count17, count18, count19, 255).updateState();
            worldRenderer.getWorldRenderer(100.0, -100.0, -100.0).getWorldRenderer6(16.0, 0.0).resolveWorldRenderer(count17, count18, count19, 255).updateState();
            tessellator.updateState();
            GlStateManager.popMatrix();
         }

         GlStateManager.depthMask(true);
         GlStateManager.disableTexture2D();
         GlStateManager.applyState14();
         GlStateManager.enableBlend();
      }
   }

   public void updateState8() {
      if (this.tertiaryLoggerHolder != null) {
         this.tertiaryLoggerHolder.updateState4();
      }
   }

   private void updateState9() {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      if (this.diamondChestplate != null) {
         this.diamondChestplate.updateState3();
      }

      if (this.count2 >= 0) {
         VectorMatcher.glDeleteLists(this.count2);
         this.count2 = -1;
      }

      if (this.enabled3) {
         this.diamondChestplate = new DiamondChestplate(this.auxiliaryLoggerEntry);
         this.applyWorldRenderer(worldRenderer);
         worldRenderer.renderElement6();
         worldRenderer.renderElement5();
         this.diamondChestplate.applyByteBuffer(worldRenderer.getByteBuffer());
      } else {
         this.count2 = VectorMatcher.computeCount(1);
         GlStateManager.pushMatrix();
         GL11.glNewList(this.count2, 4864);
         this.applyWorldRenderer(worldRenderer);
         tessellator.updateState();
         GL11.glEndList();
         GlStateManager.popMatrix();
      }
   }

   public void updateState10() {
      if (BuildManager.canExecute()) {
         if (Keyboard.isKeyDown(61) && Keyboard.isKeyDown(24)) {
            SplineDirector splineDirector = new SplineDirector((GuiScreen)null, BuildManager.getGameSettings());
            BuildManager.getMc().displayGuiScreen(splineDirector);
         }

         if (Keyboard.isKeyDown(61) && Keyboard.isKeyDown(19)) {
            PreEndHandManager.updateState77();
            PreEndHandManager.updateState63();
         }
      }

      this.count15++;
      if (this.count15 % 20 == 0) {
         this.applyIterator(this.map.values().iterator());
      }
   }

   private void updateState7() {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      if (this.diamondChestplate2 != null) {
         this.diamondChestplate2.updateState3();
      }

      if (this.count16 >= 0) {
         VectorMatcher.glDeleteLists(this.count16);
         this.count16 = -1;
      }

      if (this.enabled3) {
         this.diamondChestplate2 = new DiamondChestplate(this.auxiliaryLoggerEntry);
         this.applyWorldRenderer(worldRenderer, 16.0F, false);
         worldRenderer.renderElement6();
         worldRenderer.renderElement5();
         this.diamondChestplate2.applyByteBuffer(worldRenderer.getByteBuffer());
      } else {
         this.count16 = VectorMatcher.computeCount(1);
         GL11.glNewList(this.count16, 4864);
         this.applyWorldRenderer(worldRenderer, 16.0F, false);
         tessellator.updateState();
         GL11.glEndList();
      }
   }

   public void updateState11() {
      this.internalMinecraftEntry.updateState2();
   }

   public void updateState6() {
      if (OpenGlHelper.enabled4) {
         if (AndFSManager.getAndFSManager() == null) {
            AndFSManager.updateState();
         }

         ResourceLocation location = new ResourceLocation("shaders/post/entity_outline.json");

         try {
            this.passManager = new PassManager(this.mc.getTextureManager(), this.mc.getIResourceManager(), this.mc.getFramebuffer(), location);
            this.passManager.applyIndex(this.mc.displayWidth, this.mc.displayHeight);
            this.framebuffer = this.passManager.get("final");
         } catch (IOException ioException) {
            logger.warn("Failed to load shader: " + location, ioException);
            this.passManager = null;
            this.framebuffer = null;
         } catch (JsonSyntaxException jsonSyntaxException) {
            logger.warn("Failed to load shader: " + location, jsonSyntaxException);
            this.passManager = null;
            this.framebuffer = null;
         }
      } else {
         this.passManager = null;
         this.framebuffer = null;
      }
   }

   private void updateState5() {
      ListenerAdapter27 listenerAdapter = this.mc.getListenerAdapter27();

      for (int index = 0; index < this.serviceStringEntryArray.length; index++) {
         this.serviceStringEntryArray[index] = listenerAdapter.get("minecraft:blocks/destroy_stage_" + index);
      }
   }

   private void updateState12() {
      GlStateManager.applyState10();
      GlStateManager.applyFloat(0.0F, 0.0F);
      GlStateManager.enableAlpha();
      GlStateManager.applyState14();
      GlStateManager.depthMask(true);
      GlStateManager.popMatrix();
      if (BuildManager.canExecute()) {
         RotationBridge.updateState12();
      }
   }

   private void updateState4() {
      GlStateManager.tryBlendFuncSeparate(774, 768, 1, 0);
      GlStateManager.disableBlend();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 0.5F);
      GlStateManager.applyFloat(-1.0F, -10.0F);
      GlStateManager.applyState4();
      GlStateManager.alphaFunc(516, 0.1F);
      GlStateManager.applyState14();
      GlStateManager.pushMatrix();
      if (BuildManager.canExecute()) {
         RotationBridge.updateState14();
      }
   }

   public RenderGlobal(Minecraft mc2) {
      this.internalMinecraftEntry = new InternalMinecraftEntry(mc2);
      this.mc = mc2;
      this.renderManager = mc2.getRenderManager();
      this.textureManager = mc2.getTextureManager();
      this.textureManager.applyResourceLocation3(resourceLocation);
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GlStateManager.setActiveTexture(0);
      this.updateState5();
      this.enabled3 = OpenGlHelper.isActive();
      if (this.enabled3) {
         this.extraDoubleEntry = new SpecialDoubleEntry();
         this.postUpdateObserver = new CurveSerializer();
      } else {
         this.extraDoubleEntry = new DoubleEntryAdapter();
         this.postUpdateObserver = new ListenerController();
      }

      this.auxiliaryLoggerEntry = new AuxiliaryLoggerEntry();
      this.auxiliaryLoggerEntry.resolveAuxiliaryLoggerEntry(new AltLoggerEntry(0, CombatValueProperty.FLOAT, RenderBooleanProperty.POSITION, 3));
      this.updateState9();
      this.updateState7();
      this.updateState18();
   }
}
