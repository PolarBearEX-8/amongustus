package augustus.modules.misc;

import client.Client;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.render.PathRenderer;
import client.render.RenderUtils;
import client.util.PathNode;
import client.util.RandomUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.BlockPlaceData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class PathFinderDebug extends Module implements RenderListener, TickListener {
   public String labelText4 = "";
   public ArrayList arrayList = new ArrayList();
   public ArrayList arrayList2 = new ArrayList();

   public PathFinderDebug() {
      super("PathFinderDebug", "WegSucherEntwicklungsHelfer", Color.WHITE, Category.RENDER);
   }

   @Override
   public void onPreDisable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onEnable() {
      this.registerEvent(32);
      this.registerEvent(40);
   }

   @Override
   public void onRunTick() {
      if (!((Module)Client.getClient().getModuleManager().freeCam).isToggled()) {
         Vec3 vec3 = minecraft.thePlayer.getVec32(1.0F);
         BlockPlaceData blockPlaceData = new BlockPlaceData((int)Math.floor(vec3.xCoord), (int)Math.floor(vec3.yCoord), (int)Math.floor(vec3.zCoord));
         BlockPlaceData blockPlaceData2 = new BlockPlaceData(-170, 67, -91);
         String string = blockPlaceData.getString();
         if (!this.labelText4.equals(string) && blockPlaceData.getCoordinate(blockPlaceData2) < 500.0) {
            this.labelText4 = string;
            boolean toggled = ((Module)Client.getClient().getModuleManager().jesus).isToggled();
            boolean flag = toggled && Client.getClient().getModuleManager().jesus.settingMode.getSelected().equalsIgnoreCase("dolphin");
            PathRenderer pathRenderer = new PathRenderer(blockPlaceData, blockPlaceData2, toggled && !flag, flag, false);
            this.arrayList = pathRenderer.getArrayList2(true, 50L);
         }
      }
   }

   @Override
   public void onRender3D(float factor) {
      RenderUtils.updateState();
      GL11.glTranslated(-minecraft.getRenderManager().getRenderPosX(), -minecraft.getRenderManager().getRenderPosY(), -minecraft.getRenderManager().getRenderPosZ());
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glBegin(3);
      if (this.arrayList != null) {
         for (PathNode pathNode : (Iterable<PathNode>)(Object)(this.arrayList)) {
            GL11.glVertex3d(
               (double)pathNode.getBlockPlaceData().indexOffset1 + 0.5,
               (double)pathNode.getBlockPlaceData().indexOffset2 + 0.01,
               (double)pathNode.getBlockPlaceData().indexOffset + 0.5
            );
         }
      }

      GL11.glEnd();
      GL11.glPopMatrix();
      Iterator iterator = this.arrayList2.iterator();

      while (iterator.hasNext()) {
         RenderUtils.applyBlockPos((BlockPos)iterator.next(), RandomUtil.getInt12(0, 0, 1, 25), 0.3F, 0.3F);
      }

      RenderUtils.prepareScissor();
   }
}
