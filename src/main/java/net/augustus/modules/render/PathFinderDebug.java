package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.utils.pathfinding.PathNode;
import net.augustus.utils.pathfinding.AStarPathFinder;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.pathfinding.PathBlockPos;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class PathFinderDebug extends Module implements RenderListener, TickListener {
   private String lastStartPosKey;
   private java.util.ArrayList computedPath;
   public java.util.ArrayList highlightedBlocks;

   public PathFinderDebug() {
       super("PathFinderDebug", "WegSucherEntwicklungsHelfer", Color.WHITE, Categorys.RENDER);
      this.lastStartPosKey = "";
      this.computedPath = new java.util.ArrayList();
      this.highlightedBlocks = new java.util.ArrayList();
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(40);
   }

   public void onTick() {
      if (!Augustus.getInstance().getModuleManager().freeCam.isToggled()) {
         Vec3 interpolatedPos = mc.thePlayer.getInterpolatedPos(1.0F);
         PathBlockPos pathBlockPos = new PathBlockPos((int)Math.floor(interpolatedPos.xCoord), (int)Math.floor(interpolatedPos.yCoord), (int)Math.floor(interpolatedPos.zCoord));
         PathBlockPos pathBlockPos2 = new PathBlockPos(-170, 67, -91);
         String str = pathBlockPos.toKeyString();
         if (!this.lastStartPosKey.equals(str) && pathBlockPos.distanceTo(pathBlockPos2) < 500.0) {
            this.lastStartPosKey = str;
            boolean isToggled = Augustus.getInstance().getModuleManager().jesus.isToggled();
            boolean flag = isToggled && Augustus.getInstance().getModuleManager().jesus.mode.getSelectedValue().equalsIgnoreCase("dolphin");
            this.computedPath = new AStarPathFinder(pathBlockPos, pathBlockPos2, isToggled && !flag, flag, false).computePath(true, 50L);
         }
      }
   }

   public void onRender3D(float partialTicks) {
      RenderUtil.begin3DRender();
      GlStateManager.pushMatrix();
      GlStateManager.translate(-mc.getRenderManager().getRenderPosX(), -mc.getRenderManager().getRenderPosY(), -mc.getRenderManager().getRenderPosZ());
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glBegin(3);
      if (this.computedPath != null) {
         for (PathNode pathNode : (Iterable<PathNode>)(this.computedPath)) {
            GL11.glVertex3d((double)pathNode.getPos().x + 0.5, (double)pathNode.getPos().y + 0.01, (double)pathNode.getPos().z + 0.5);
         }
      }

      GL11.glEnd();
      GlStateManager.popMatrix();

      for (BlockPos pos : (Iterable<BlockPos>)(this.highlightedBlocks)) {
         int n = ColorUtil.packRgba(0, 0, 1, 25);
         float f2 = 0.3F;
         RenderUtil.drawBlockEspBox(pos, n, 0.3F, f2);
      }

      RenderUtil.end3DRender();
   }

}
