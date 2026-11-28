package net.augustus.modules.render;

import com.google.common.collect.Maps;
import java.awt.Color;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.modules.render.helper.BedDefenseLayers;
import net.augustus.events.EventRenderBlock;
import net.augustus.settings.ColorSetting;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.events.listeners.ChunkUnloadListener;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.events.EventBlockChange;
import net.augustus.render.ProjectionUtil;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.RenderBlockListener;
import net.augustus.events.listeners.BlockChangeListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkCoordIntPair;

public class BedPlates extends Module implements RenderListener, RenderBlockListener, TickListener, LoadWorldListener, ChunkUnloadListener, BlockChangeListener {
   private final DoubleValue displayDistance;
   private final DoubleValue scanRadius;
   private final DoubleValue scale;
   private final BooleanValue showDistance;
   private final ColorSetting backgroundColor;
   private final BlurShadowSettings blurShadowSettings;
   private static final Map TRACKED_BED_POSITIONS = Maps.newConcurrentMap();
   private static final Map BEDS_BY_CHUNK = Maps.newConcurrentMap();
   private static final long SCAN_INTERVAL_MS = 500L;
   private long lastScanTimeMs;
   private final Map cachedBedDefenses;
   private final Map projectedBedCoords;

   public BedPlates() {
       super("BedPlates", "BettVerteidigung", new Color(255, 85, 85), Categorys.RENDER);
      this.displayDistance = new DoubleValue(this, "DisplayDistance", 40.0, 5.0, 100.0, 0);
      this.scanRadius = new DoubleValue(this, "ScanRadius", 3.0, 1.0, 5.0, 0);
      this.scale = new DoubleValue(this, "Scale", 1.0, 0.5, 3.0, 2);
      this.showDistance = new BooleanValue(this, "ShowDistance", true);
      this.backgroundColor = new ColorSetting(this, "Background", new Color(0, 0, 0, 140));
      this.blurShadowSettings = new BlurShadowSettings(this);
      this.lastScanTimeMs = 0L;
      this.cachedBedDefenses = new LinkedHashMap();
      this.projectedBedCoords = new LinkedHashMap();
   }

   @Override
   public void onEnable() {
      this.clearTrackedBeds();
      mc.renderGlobal.loadRenderers();
   }

   @Override
   public void onDisable() {
      this.clearTrackedBeds();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(30);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(17);
      this.toggleEventSubscription(64);
      this.toggleEventSubscription(55);
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.clearTrackedBeds();
   }

   @Override
   public void onRenderBlock(EventRenderBlock event) {
      IBlockState blockState = event.getBlockState();
      if (blockState != null && blockState.getBlock() == Blocks.bed) {
         if (blockState.getValue(BlockBed.PART) == BlockBed.EnumPartType.FOOT) {
            BlockPos immutableBlockPos = event.getImmutableBlockPos();
            if (immutableBlockPos != null && !TRACKED_BED_POSITIONS.containsKey(immutableBlockPos)) {
               PacketThreadUtil.runOnMainThreadImmediate(() -> this.trackBedPosition(immutableBlockPos));
            }
         }
      }
   }

   @Override
   public void onBlockChange(EventBlockChange event) {
      BlockPos blockPos = event.getBlockPos();
      IBlockState blockState = event.getBlockState();
      if (TRACKED_BED_POSITIONS.containsKey(blockPos)) {
         if (blockState == null || blockState.getBlock() != Blocks.bed || blockState.getValue(BlockBed.PART) != BlockBed.EnumPartType.FOOT) {
            PacketThreadUtil.runOnMainThreadImmediate(() -> {
               this.untrackBedPosition(blockPos);
            });
         }
      } else if (blockState != null && blockState.getBlock() == Blocks.bed && blockState.getValue(BlockBed.PART) == BlockBed.EnumPartType.FOOT) {
         PacketThreadUtil.runOnMainThreadImmediate(() -> this.trackBedPosition(blockPos));
      }
   }

   @Override
   public void onChunkUnload(long chunkKey) {
      HashSet set = (HashSet)BEDS_BY_CHUNK.get(chunkKey);
      if (set != null) {
         for (BlockPos pos : (Iterable<BlockPos>)(set)) {
            TRACKED_BED_POSITIONS.remove(pos);
            this.cachedBedDefenses.remove(pos);
         }

         BEDS_BY_CHUNK.remove(chunkKey);
      }
   }

   private void trackBedPosition(BlockPos pos) {
      TRACKED_BED_POSITIONS.put(pos, Boolean.TRUE);
      long l = ChunkCoordIntPair.chunkXZ2Int(pos.getX() >> 4, pos.getZ() >> 4);
      ((Set)BEDS_BY_CHUNK.computeIfAbsent(l, obj -> new HashSet<>())).add(pos);
   }

   private void untrackBedPosition(BlockPos pos) {
      TRACKED_BED_POSITIONS.remove(pos);
      this.cachedBedDefenses.remove(pos);
      long l = ChunkCoordIntPair.chunkXZ2Int(pos.getX() >> 4, pos.getZ() >> 4);
      HashSet set = (HashSet)BEDS_BY_CHUNK.get(l);
      if (set != null) {
         set.remove(pos);
         if (set.isEmpty()) {
            BEDS_BY_CHUNK.remove(l);
         }
      }
   }

   @Override
   public void onTick() {
      if (mc.thePlayer != null && mc.theWorld != null) {
         long currentTimeMs = System.currentTimeMillis();
         if (currentTimeMs - this.lastScanTimeMs >= 500L) {
            this.lastScanTimeMs = currentTimeMs;
            double value = this.displayDistance.getValue();
            int value2 = (int)this.scanRadius.getValue();
            LinkedHashMap map = new LinkedHashMap();

            for (BlockPos pos : (Iterable<BlockPos>)(TRACKED_BED_POSITIONS.keySet())) {
               if (!(mc.thePlayer.getDistance((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5) > value)) {
                  map.put(pos, this.scanBedDefenseLayers(pos, value2));
               }
            }

            this.cachedBedDefenses.clear();
            this.cachedBedDefenses.putAll(map);
         }
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (mc.thePlayer != null && mc.theWorld != null) {
         if (!this.cachedBedDefenses.isEmpty()) {
            ScaledResolution sr = new ScaledResolution(mc);
            LinkedHashMap map = new LinkedHashMap();

            for (BlockPos pos : (Iterable<BlockPos>)(this.cachedBedDefenses.keySet())) {
               double d = (double)pos.getX() + 0.5;
               double d2 = (double)pos.getY() + 1.4;
               double d3 = (double)pos.getZ() + 0.5;
               map.put(pos, ProjectionUtil.project3DTo2D(
                  d - mc.getRenderManager().getRenderPosX(), d2 - mc.getRenderManager().getRenderPosY(), d3 - mc.getRenderManager().getRenderPosZ(), sr
               ));
            }

            this.projectedBedCoords.clear();
            this.projectedBedCoords.putAll(map);
         }
      }
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if (mc.thePlayer != null && mc.theWorld != null) {
         if (!this.cachedBedDefenses.isEmpty() && !this.projectedBedCoords.isEmpty()) {
            for (Entry entry : (Iterable<Entry>)(this.cachedBedDefenses.entrySet())) {
               BlockPos key = (BlockPos)entry.getKey();
               BedDefenseLayers value = (BedDefenseLayers)entry.getValue();
               Vec3 vec = (Vec3)this.projectedBedCoords.get(key);
               if (vec != null && !(vec.zCoord < 0.0) && !(vec.zCoord > 1.0)) {
                  double xCoord = vec.xCoord;
                  double yCoord = vec.yCoord;
                  GlStateManager.pushMatrix();
                  double value2 = this.scale.getValue();
                  GlStateManager.translate(xCoord, yCoord, 0.0);
                  GlStateManager.scale(value2, value2, 1.0);
                  GlStateManager.translate(-xCoord, -yCoord, 0.0);
                  this.renderBedPlate(xCoord, yCoord, value, key);
                  GlStateManager.popMatrix();
               }
            }

            RenderUtil.resetGlColorState();
         }
      }
   }

   private void renderBedPlate(double d, double d2, BedDefenseLayers bedDefenseLayers, BlockPos pos) {
      byte b = 4;
      byte b2 = 16;
      byte b3 = 2;
      int fontheight = mc.fontRendererObj.FONT_HEIGHT;
      String str = null;
      int n = 0;
      if (this.showDistance.getValue()) {
         double distance = mc.thePlayer.getDistance((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
         str = "§7" + String.format("%.1f", distance) + "m";
         n = mc.fontRendererObj.getStringWidth(str);
      }

      if (bedDefenseLayers.blocks.isEmpty()) {
         String str2 = "§cUnprotected";
         double stringWidth = (double)mc.fontRendererObj.getStringWidth(str2);
         double d3 = Math.max(stringWidth, (double)n) + (double)(b * 2);
         double d4 = (double)(fontheight + (str != null ? fontheight + 2 : 0) + b * 2);
         double d5 = d - d3 / 2.0;
         double d6 = d2 - d4 - 2.0;
         BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderUnprotectedBackground(d5, d6, d3, d4, shaderRenderPass));
         mc.fontRendererObj.drawStringWithShadow(str2, (float)(d - stringWidth / 2.0), (float)(d6 + (double)b), -1);
         if (str != null) {
            mc.fontRendererObj.drawStringWithShadow(str, (float)(d - (double)((float)n / 2.0F)), (float)(d6 + (double)b + (double)fontheight + 2.0), -1);
         }
      } else {
         int size = bedDefenseLayers.blocks.size();
         int n2 = size * b2 + (size - 1) * b3;
         double d7 = (double)(Math.max(n2, n) + b * 2);
         double d8 = (double)(b + (str != null ? fontheight + 3 : 0) + b2 + b);
         double d9 = d - d7 / 2.0;
         double d10 = d2 - d8 - 2.0;
         BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass2 -> this.renderProtectedBackground(d9, d10, d7, d8, shaderRenderPass2));
         double d11 = d10 + (double)b;
         if (str != null) {
            mc.fontRendererObj.drawStringWithShadow(str, (float)(d - (double)((float)n / 2.0F)), (float)d11, -1);
            d11 += (double)(fontheight + 3);
         }

         int n3 = (int)d - n2 / 2;
         RenderHelper.enableGUIStandardItemLighting();

         for (int i = 0; i < size; i++) {
            Block block = (Block)bedDefenseLayers.blocks.get(i);
            int n4 = n3 + i * (b2 + b3);
            if (Item.getItemFromBlock(block) != null) {
               RenderUtil.renderItemIntoGuiElevated(new ItemStack(block), n4, (int)d11, false);
            }
         }
      }
   }

   private BedDefenseLayers scanBedDefenseLayers(BlockPos pos, int n) {
      IBlockState blockState = mc.theWorld.getBlockState(pos);
      if (blockState.getBlock() != Blocks.bed) {
         return new BedDefenseLayers(null);
      } else {
         BlockPos pos2 = pos.offset((EnumFacing)blockState.getValue(BlockBed.FACING));
         int y = pos.getY();
         HashSet set = new HashSet();
         set.add(pos);
         set.add(pos2);
         Set<BlockPos> set2 = new HashSet<>();
         set2.add(pos);
         set2.add(pos2);
         LinkedHashSet set3 = new LinkedHashSet();

         for (int i = 1; i <= n; i++) {
            LinkedHashSet set4 = new LinkedHashSet();

            for (BlockPos pos3 : set2) {
               for (EnumFacing facing : EnumFacing.values()) {
                  if (facing != EnumFacing.DOWN || pos3.getY() > y) {
                     BlockPos pos4 = pos3.offset(facing);
                     if (set.add(pos4) && pos4.getY() >= y) {
                        Block block = mc.theWorld.getBlockState(pos4).getBlock();
                        if (block != Blocks.bed && block != Blocks.air) {
                           set3.add(block);
                           set4.add(pos4);
                        }
                     }
                  }
               }
            }

            set2 = set4;
            if (set4.isEmpty()) {
               break;
            }
         }

         java.util.ArrayList list = new java.util.ArrayList(set3);
         list.sort(Comparator.comparingDouble(obj -> getBlockSortKey((Block)obj)));
         BedDefenseLayers bedDefenseLayers = new BedDefenseLayers(null);
         bedDefenseLayers.blocks = list;
         return bedDefenseLayers;
      }
   }

   private void clearTrackedBeds() {
      TRACKED_BED_POSITIONS.clear();
      BEDS_BY_CHUNK.clear();
      this.cachedBedDefenses.clear();
      this.projectedBedCoords.clear();
      this.lastScanTimeMs = 0L;
   }

   private static float getBlockDefenseStrength(Block block) {
      if (block == Blocks.obsidian) {
         return 50.0F;
      } else if (block == Blocks.end_stone) {
         return 3.0F;
      } else if (block == Blocks.iron_block) {
         return 5.0F;
      } else if (block == Blocks.diamond_block) {
         return 5.0F;
      } else if (block == Blocks.emerald_block) {
         return 5.0F;
      } else if (block == Blocks.stained_hardened_clay || block == Blocks.hardened_clay) {
         return 1.25F;
      } else if (block == Blocks.cobblestone || block == Blocks.stone) {
         return 2.0F;
      } else if (block == Blocks.planks) {
         return 2.0F;
      } else if (block == Blocks.wool) {
         return 0.8F;
      } else if (block != Blocks.glass && block != Blocks.stained_glass) {
         try {
            return block.getBlockHardness(null, null);
         } catch (Exception e) {
            return 1.0F;
         }
      } else {
         return 0.3F;
      }
   }

   private static double getBlockSortKey(Block block) {
      return (double)(-getBlockDefenseStrength(block));
   }

   private void renderProtectedBackground(double d, double d2, double d3, double d4, ShaderRenderPass shaderRenderPass) {
      RenderUtil.drawRect(d, d2, d + d3, d2 + d4, ColorUtil.resolvePassColor(shaderRenderPass, this.backgroundColor.getColorInt((byte)0)));
   }

   private void renderUnprotectedBackground(double d, double d2, double d3, double d4, ShaderRenderPass shaderRenderPass) {
      RenderUtil.drawRect(d, d2, d + d3, d2 + d4, ColorUtil.resolvePassColor(shaderRenderPass, this.backgroundColor.getColorInt((byte)0)));
   }

}
