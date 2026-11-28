package net.augustus.modules.render;

import com.google.common.collect.Maps;
import java.awt.Color;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.render.CubeVboBatchRenderer;
import net.augustus.events.EventRenderBlock;
import net.augustus.render.RenderPassTask;
import net.augustus.render.VboCubeEntry;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.events.listeners.ChunkUnloadListener;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.settings.options.BlockEspBlockOption;
import net.augustus.events.EventBlockChange;
import net.augustus.settings.SettingChangeListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.RenderBlockListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.events.listeners.BlockChangeListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ChunkCoordIntPair;

public class BlockESP extends Module implements RenderListener, RenderBlockListener, TickListener, LoadWorldListener, ChunkUnloadListener, BlockChangeListener {
   private final StringValue mode;
   private final BooleanValue oneColor;
   public final ColorSetting color2;
   public final DoubleValue lineWidth;
   public final BlurShadowSettings blurShadowSettings;
   private static final HashMap BLOCK_OPTIONS = new HashMap();
   private static final Map TRACKED_BLOCK_CUBES = Maps.newConcurrentMap();
   private static final Map TRACKED_BLOCK_OPTIONS = Maps.newConcurrentMap();
   private static final Map BLOCKS_BY_CHUNK = Maps.newConcurrentMap();
   private long lastColorUpdateMs;
   private CubeVboBatchRenderer cubeVboBatchRenderer;
   private boolean needsRendererReload;

   public BlockESP() {
       super("BlockESP", "BlockDurchWandSehen", new Color(171, 9, 41), Categorys.RENDER);
      this.mode = (StringValue)new StringValue(this, "Mode", "Old", new String[]{"Old", "New"}).withChangeListener(() -> this.onRenderModeChanged());
      this.oneColor = new BooleanValue(this, "OneColor", true);
      ColorSetting colorSetting = new ColorSetting(this, "Color", new Color(21, 121, 230, 65));
      BooleanValue oneColor2 = this.oneColor;
      this.color2 = (ColorSetting)colorSetting.withVisibility(() -> oneColor2.getValue());
      DoubleValue lineWidthSetting = new DoubleValue(this, "LineWidth", 6.0, 0.0, 15.0, 0);
      SettingVisibilityCondition condition2 = () -> {
         return this.mode.getSelectedValue().equals("Old");
      };
      this.lineWidth = lineWidthSetting.visibleWhen(condition2);
      this.blurShadowSettings = (new BlurShadowSettings(this)).disableBlurByDefault().withCustomShadowColor(Color.black);
      this.lastColorUpdateMs = 0L;
      this.cubeVboBatchRenderer = new CubeVboBatchRenderer(200);
      this.needsRendererReload = false;

      for (ResourceLocation location : (Iterable<ResourceLocation>)(Block.blockRegistry.getKeys())) {
         String resourcePath = location.getResourcePath();
         Block blockFromName = Block.getBlockFromName(resourcePath);
         BLOCK_OPTIONS.put(blockFromName, new BlockEspBlockOption(this, resourcePath, blockFromName == Blocks.bed, null));
      }

      BlockEspBlockOption[] blockEspBlockOptions = (BlockEspBlockOption[])BLOCK_OPTIONS.values().toArray(new BlockEspBlockOption[0]);
      Arrays.sort(blockEspBlockOptions, Comparator.comparing(BlockEspBlockOption::getName));
      new MultiOptionSetting(this, "Blocks", blockEspBlockOptions);

      for (BlockEspBlockOption blockEspBlockOption : blockEspBlockOptions) {
         BlockEspBlockOption.initOptionColorSetting(blockEspBlockOption);
      }
   }

   @Override
   public void onEnable() {
      this.clearTrackedBlocks();
      mc.renderGlobal.loadRenderers();
   }

   @Override
   public void onDisable() {
      this.clearTrackedBlocks();
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
   public void onTick() {
      if (this.needsRendererReload) {
         mc.renderGlobal.loadRenderers();
         this.needsRendererReload = false;
      }
   }

   @Override
   public void onBlockChange(EventBlockChange event) {
      BlockPos blockPos = event.getBlockPos();
      if (TRACKED_BLOCK_CUBES.containsKey(blockPos)) {
         IBlockState blockState = event.getBlockState();
         if (blockState != null && !((BlockEspBlockOption)BLOCK_OPTIONS.get(blockState.getBlock())).selected) {
            PacketThreadUtil.runOnMainThreadImmediate(() -> this.handleBlockRemoved(blockPos));
         }
      }
   }

   @Override
   public void onRenderBlock(EventRenderBlock event) {
      BlockEspBlockOption blockEspBlockOption;
      if (TRACKED_BLOCK_CUBES.size() < 2000 && event.getBlockState() != null && event.getBlockState().getBlock() != Blocks.air && (blockEspBlockOption = (BlockEspBlockOption)BLOCK_OPTIONS.get(event.getBlockState().getBlock())).selected) {
         BlockPos immutableBlockPos = event.getImmutableBlockPos();
         if (immutableBlockPos == null) {
            return;
         }

         if (!TRACKED_BLOCK_CUBES.containsKey(immutableBlockPos)) {
            Block block = event.getBlockState().getBlock();
            VboCubeEntry vboCubeEntry = new VboCubeEntry(
               (double)immutableBlockPos.getX() + block.getBlockBoundsMinX(),
               (double)immutableBlockPos.getY() + block.getBlockBoundsMinY(),
               (double)immutableBlockPos.getZ() + block.getBlockBoundsMinZ(),
               block.getBlockBoundsMaxX(),
               block.getBlockBoundsMaxY(),
               block.getBlockBoundsMaxZ()
            );
            PacketThreadUtil.runOnMainThreadImmediate(() -> this.registerTrackedBlock(immutableBlockPos, vboCubeEntry, blockEspBlockOption));
         }
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.clearTrackedBlocks();
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (mc.thePlayer != null && mc.theWorld != null) {
         RenderUtil.begin3DRender();
         boolean flag = this.mode.getSelectedValue().equals("Old");
         long currentTimeMs = System.currentTimeMillis();
         boolean flag2 = currentTimeMs - this.lastColorUpdateMs > 1L;
         if (flag2) {
            this.lastColorUpdateMs = currentTimeMs;
         }

         BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
         BlurShadowManager.submitRenderTask(true, shaderRenderPass -> this.renderTrackedBlocksPass(flag, flag2, shaderRenderPass));
         RenderUtil.end3DRender();
      }
   }

   @Override
   public void onChunkUnload(long chunkKey) {
      HashSet set = (HashSet)BLOCKS_BY_CHUNK.get(chunkKey);
      if (set != null) {
         for (BlockPos pos : (Iterable<BlockPos>)(set)) {
            this.removeTrackedBlockEntry(pos, false);
         }

         BLOCKS_BY_CHUNK.remove(chunkKey);
      }
   }

   private void clearTrackedBlocks() {
      TRACKED_BLOCK_CUBES.clear();
      BLOCKS_BY_CHUNK.clear();
      this.cubeVboBatchRenderer.clear();
   }

   private void removeTrackedBlock(BlockPos pos) {
      this.removeTrackedBlockEntry(pos, true);
   }

   private void removeTrackedBlockEntry(BlockPos pos, boolean flag) {
      if (this.mode.getSelectedValue().equals("New")) {
         VboCubeEntry vboCubeEntry = (VboCubeEntry)TRACKED_BLOCK_CUBES.get(pos);
         this.cubeVboBatchRenderer.removeCube(vboCubeEntry);
      }

      TRACKED_BLOCK_CUBES.remove(pos);
      TRACKED_BLOCK_OPTIONS.remove(pos);
      if (flag) {
         this.removeFromChunkIndex(pos);
      }
   }

   private void removeFromChunkIndex(BlockPos pos) {
      long l = ChunkCoordIntPair.chunkXZ2Int(pos.getX() >> 4, pos.getZ() >> 4);
      HashSet set = (HashSet)BLOCKS_BY_CHUNK.get(l);
      if (set != null) {
         set.remove(pos);
         if (set.isEmpty()) {
            BLOCKS_BY_CHUNK.remove(l);
         }
      }
   }

   private void addTrackedBlockEntry(BlockPos pos, VboCubeEntry vboCubeEntry, BlockEspBlockOption blockEspBlockOption) {
      TRACKED_BLOCK_CUBES.put(pos, vboCubeEntry);
      TRACKED_BLOCK_OPTIONS.put(pos, blockEspBlockOption);
      ((Set)BLOCKS_BY_CHUNK.computeIfAbsent(ChunkCoordIntPair.chunkXZ2Int(pos.getX() >> 4, pos.getZ() >> 4), obj -> new HashSet<>())).add(pos);
   }

   private void renderTrackedBlocksPass(boolean flag, boolean flag2, ShaderRenderPass shaderRenderPass) {
      GlStateManager.pushMatrix();
      GlStateManager.color(0.0F, 1.0F, 0.0F, 1.0F);
      int n = 0;
      if (flag) {
         for (BlockPos pos : (Iterable<BlockPos>)(TRACKED_BLOCK_CUBES.keySet())) {
            if (pos != null) {
               float f = (float)(this.lineWidth.getValue() / 2.0);
               int n2 = this.oneColor.getValue() ? this.color2.getColorInt(n) : ((BlockEspBlockOption)TRACKED_BLOCK_OPTIONS.get(pos)).colorSetting.getColorInt(n);
               double distance = mc.thePlayer.getDistance((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
               if (distance > 1.0) {
                  double d = 1.0 - distance / 20.0;
                  if (d < 0.3) {
                     d = 0.3;
                  }

                  f *= (float)d;
               }

               RenderUtil.drawBlockEspBox(pos, this.blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, n2), n), 1.0F, f);
               n++;
            }
         }
      } else {
         GlStateManager.translate(-mc.getRenderManager().getRenderPosX(), -mc.getRenderManager().getRenderPosY(), -mc.getRenderManager().getRenderPosZ());

         for (Entry entry : (Iterable<Entry>)(TRACKED_BLOCK_CUBES.entrySet())) {
            VboCubeEntry value = (VboCubeEntry)entry.getValue();
            BlockPos key = (BlockPos)entry.getKey();
            if (value != null) {
               if (flag2) {
                  value.setColor(this.oneColor.getValue() ? this.color2.getColorInt(n) : ((BlockEspBlockOption)TRACKED_BLOCK_OPTIONS.get(key)).colorSetting.getColorInt(n)).flushIfDirty();
               }

               n++;
            }
         }

         this.cubeVboBatchRenderer.render();
      }

      GlStateManager.popMatrix();
   }

   private void registerTrackedBlock(BlockPos pos, VboCubeEntry vboCubeEntry, BlockEspBlockOption blockEspBlockOption) {
      this.addTrackedBlockEntry(pos, vboCubeEntry, blockEspBlockOption);
      if (this.mode.getSelectedValue().equals("New")) {
         this.cubeVboBatchRenderer.addCube(vboCubeEntry);
      }
   }

   private void handleBlockRemoved(BlockPos pos) {
      this.removeTrackedBlock(pos);
   }

   private void onRenderModeChanged() {
      this.clearTrackedBlocks();
      mc.renderGlobal.loadRenderers();
   }

   public static void clearTrackedBlocksBridge(BlockESP blockESP) {
      blockESP.clearTrackedBlocks();
   }

   public static boolean setNeedsRendererReload(BlockESP blockESP, boolean flag) {
      return blockESP.needsRendererReload = flag;
   }

   public static BooleanValue getOneColorSetting(BlockESP blockESP) {
      return blockESP.oneColor;
   }

}
