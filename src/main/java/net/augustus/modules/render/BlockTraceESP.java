package net.augustus.modules.render;

import com.google.common.collect.Sets;
import java.awt.Color;
import java.util.Set;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.events.EventRenderBlock;
import net.augustus.modules.render.blocktrace.BlockTraceNode;
import net.augustus.settings.ColorSetting;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.modules.render.blocktrace.BlockTracePathFinder;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.RenderBlockListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.render.blocktrace.TraceBlockPos;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class BlockTraceESP extends Module implements RenderListener, TickListener, RenderBlockListener, LoadWorldListener {
   public final StringValue block;
   public final DoubleValue customID;
   public final ColorSetting blockColor;
   public final DoubleValue blockLineWidth;
   public final DoubleValue blockDistance;
   public final BlurShadowSettings block2;
   public final ColorSetting pathColor;
   public final BlurShadowSettings path;
   public final DoubleValue pathSearchRange;
   private BlockTraceNode blockTraceNode;
   private final java.util.ArrayList pathBlockPositions;
   private static final Set TRACKED_BLOCK_POSITIONS = Sets.newConcurrentHashSet();
   private Block currentTargetBlock;

   public BlockTraceESP() {
       super("BlockTraceESP", "BlockSpurESP", new Color(50, 168, 82), Categorys.RENDER);
      this.block = new StringValue(this, "Block", "Bed", new String[]{"Bed", "Cake", "Custom"});
      this.customID = new DoubleValue(this, "CustomID", 26.0, 0.0, 400.0, 0).visibleWhen(() -> this.isCustomBlockIdVisible());
      this.blockColor = new ColorSetting(this, "BlockColor", new Color(21, 121, 230, 65));
      this.blockLineWidth = new DoubleValue(this, "BlockLineWidth", 6.0, 0.0, 15.0, 0);
      this.blockDistance = new DoubleValue(this, "BlockDistance", 50.0, 1.0, 300.0, 0);
      this.block2 = (new BlurShadowSettings(this, "Block")).disableBlurByDefault().withCustomShadowColor(Color.black);
      this.pathColor = new ColorSetting(this, "PathColor", new Color(21, 121, 230, 65));
      this.path = (new BlurShadowSettings(this, "Path")).disableBlurByDefault().withCustomShadowColor(Color.black);
      this.pathSearchRange = new DoubleValue(this, "PathSearchRange", 10.0, 1.0, 10.0, 0);
      this.blockTraceNode = null;
      this.pathBlockPositions = new java.util.ArrayList();
      this.currentTargetBlock = null;
   }

   @Override
   public void onEnable() {
      TRACKED_BLOCK_POSITIONS.clear();
      mc.renderGlobal.loadRenderers();
   }

   @Override
   public void onDisable() {
      this.pathBlockPositions.clear();
      TRACKED_BLOCK_POSITIONS.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(30);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onEarlyTick() {
      this.currentTargetBlock = Block.getBlockById(this.getTargetBlockId());
      TRACKED_BLOCK_POSITIONS.removeIf(obj -> {
         if (obj != null && this.currentTargetBlock != null) {
            IBlockState blockState2 = mc.theWorld.getBlockState2(((BlockPos)obj).getX(), ((BlockPos)obj).getY(), ((BlockPos)obj).getZ());
            return blockState2 != null && !blockState2.getBlock().equals(this.currentTargetBlock);
         } else {
            return true;
         }
      });
      this.findNearestTargetAndPath();
   }

   @Override
   public void onRenderBlock(EventRenderBlock event) {
      if (event.getBlockState() != null) {
         if (event.getBlockState().getBlock().equals(Block.getBlockById(this.getTargetBlockId()))) {
            BlockPos immutableBlockPos = event.getImmutableBlockPos();
            if (immutableBlockPos == null) {
               return;
            }

            TRACKED_BLOCK_POSITIONS.add(immutableBlockPos);
         }
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      TRACKED_BLOCK_POSITIONS.clear();
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (mc.thePlayer != null && mc.theWorld != null) {
         RenderUtil.begin3DRender();
         GlStateManager.alphaFunc(516, 0.0F);
         boolean isBlurActive = this.block2.isBlurActive();
         BlurShadowManager.enableOneShotPass(isBlurActive, this.block2.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderTrackedBlocksPass(shaderRenderPass));
         RenderUtil.end3DRender();
         if (this.blockTraceNode != null) {
            RenderUtil.begin3DRender();
            isBlurActive = this.path.isBlurActive();
            BlurShadowManager.enableOneShotPass(isBlurActive, this.path.isShadowActive());
            BlurShadowManager.submitRenderTask(false, shaderRenderPass2 -> this.renderPathBlocksPass(shaderRenderPass2));
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            RenderUtil.end3DRender();
         }
      }
   }

   private BlockPos findNearestTargetAndPath() {
      int n = MathHelper.clamp_int((int)this.pathSearchRange.getValue(), 0, 10);
      BlockPos pos = null;
      double d = 100000.0;

      for (int i = -n; i <= n; i++) {
         for (int j = -n; j <= n; j++) {
            for (int k = -n; k <= n; k++) {
               BlockPos pos2 = new BlockPos(mc.thePlayer.posX + (double)i, mc.thePlayer.posY + (double)j, mc.thePlayer.posZ + (double)k);
               if (mc.theWorld.getBlockState(pos2).getBlock().equals(Block.getBlockById(this.getTargetBlockId()))) {
                  double d2 = Math.sqrt(mc.thePlayer.getDistanceSqToCenter(pos2));
                  if (d2 < d) {
                     pos = pos2;
                     d = d2;
                  }
               }
            }
         }
      }

      if (pos == null) {
         this.blockTraceNode = null;
         return null;
      } else {
         BlockTraceNode blockTraceNode2 = BlockTracePathFinder.findPath(new TraceBlockPos(pos), new TraceBlockPos(new BlockPos(mc.thePlayer.getPositionEyes(1.0F))), pos.getY());
         if (blockTraceNode2 != null) {
            this.blockTraceNode = blockTraceNode2;
         }

         if (this.blockTraceNode == null) {
            return pos;
         } else {
            BlockTraceNode blockTraceNode3 = this.blockTraceNode;
            this.pathBlockPositions.clear();

            for (int m = 0; blockTraceNode3 != null && blockTraceNode3.getBlockTraceNode() != null; m++) {
               BlockPos blockPos = blockTraceNode3.getTraceBlockPos().getBlockPos();
               if (mc.theWorld.getBlockState(blockPos).getBlock().isFullBlock() && !this.pathBlockPositions.contains(blockPos)) {
                  this.pathBlockPositions.add(blockTraceNode3.getTraceBlockPos().getBlockPos());
               }

               blockTraceNode3 = blockTraceNode3.getBlockTraceNode();
               if (m > 300) {
                  break;
               }
            }

            return this.pathBlockPositions.isEmpty() ? pos : (BlockPos)this.pathBlockPositions.get(this.pathBlockPositions.size() - 1);
         }
      }
   }

   private int getTargetBlockId() {
      switch (this.block.getSelectedValue()) {
         case "Bed":
            return 26;
         case "Cake":
            return 92;
         default:
            return (int)this.customID.getValue();
      }
   }

   private void renderPathBlocksPass(ShaderRenderPass shaderRenderPass) {

      for (BlockPos pos : (Iterable<BlockPos>)(this.pathBlockPositions)) {
         if (pos != null) {
            int colorInt = this.pathColor.getColorInt((byte)0);
            double d = (double)pos.getX() - mc.getRenderManager().getRenderPosX();
            double d2 = (double)pos.getY() - mc.getRenderManager().getRenderPosY();
            double d3 = (double)pos.getZ() - mc.getRenderManager().getRenderPosZ();
            if (shaderRenderPass == ShaderRenderPass.SHADOW) {
               colorInt = this.path.resolvePassColor(shaderRenderPass, colorInt);
               RenderUtil.setGlColorWithAlpha(colorInt, 1.0F);
            } else {
               RenderUtil.setGlColor(colorInt);
            }

            RenderUtil.drawFilledBox(d, d2, d3, d + 1.0, d2 + 1.0, d3 + 1.0);
         }
      }
   }

   private void renderTrackedBlocksPass(ShaderRenderPass shaderRenderPass) {
      int n = 0;

      for (BlockPos pos : (Iterable<BlockPos>)(TRACKED_BLOCK_POSITIONS)) {
         if (pos != null) {
            float f = (float)(this.blockLineWidth.getValue() / 2.0);
            int n2 = n++;
            int colorInt = this.blockColor.getColorInt(n2);
            if (mc.thePlayer.getDistance((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()) > 1.0) {
               double d = 1.0 - mc.thePlayer.getDistance((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()) / 20.0;
               if (d < 0.3) {
                  d = 0.3;
               }

               f = (float)((double)f * d);
            }

            RenderUtil.drawBlockEspBox(pos, this.block2.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, colorInt), n), 1.0F, f);
         }
      }
   }

   private boolean isCustomBlockIdVisible() {
      return this.block.getSelectedValue().equals("Custom");
   }

}
