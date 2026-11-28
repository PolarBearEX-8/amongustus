package net.augustus.render;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class ProjectionUtil implements MC {
   private static final FloatBuffer MODELVIEW_MATRIX = BufferUtils.createFloatBuffer(16);
   private static final IntBuffer VIEWPORT = BufferUtils.createIntBuffer(16);
   private static final FloatBuffer SCREEN_COORDS = BufferUtils.createFloatBuffer(4);
   private static final FloatBuffer PROJECTION_MATRIX = BufferUtils.createFloatBuffer(16);

   public static ProjectedBounds2D projectBoundingBox(AxisAlignedBB box, ScaledResolution sr) {
      double d = -1337.0;
      double d2 = -1337.0;
      double d3 = -1337.0;
      double d4 = -1337.0;

      for (Vec3 vec : (Iterable<Vec3>)(getRelativeBoxCorners(box))) {
         Vec3 vec2 = project3DTo2D((double)((float)vec.xCoord), (double)((float)vec.yCoord), (double)((float)vec.zCoord), sr);
         if (vec2.zCoord >= 0.0 && vec2.zCoord <= 1.0) {
            if (d == -1337.0 || d > vec2.xCoord) {
               d = vec2.xCoord;
            }

            if (d2 == -1337.0 || d2 > vec2.yCoord) {
               d2 = vec2.yCoord;
            }

            if (d3 == -1337.0 || d3 < vec2.xCoord) {
               d3 = vec2.xCoord;
            }

            if (d4 == -1337.0 || d4 < vec2.yCoord) {
               d4 = vec2.yCoord;
            }
         }
      }

      return new ProjectedBounds2D(d, d2, d3, d4);
   }

   public static void updateMatrices() {
      GL11.glGetFloat(2982, MODELVIEW_MATRIX);
      GL11.glGetFloat(2983, PROJECTION_MATRIX);
      GL11.glGetInteger(2978, VIEWPORT);
   }

   public static Vec3 project3DTo2D(double d, double d2, double d3, ScaledResolution sr) {
      updateMatrices();
      GLU.gluProject((float)d, (float)d2, (float)d3, MODELVIEW_MATRIX, PROJECTION_MATRIX, VIEWPORT, SCREEN_COORDS);
      int scaleFactor = sr.getScaleFactor();
      return new Vec3((double)(SCREEN_COORDS.get(0) / (float)scaleFactor), (double)(((float)mc.displayHeight - SCREEN_COORDS.get(1)) / (float)scaleFactor), (double)SCREEN_COORDS.get(2));
   }

   private static ArrayList getRelativeBoxCorners(AxisAlignedBB box) {
      ArrayList list = new ArrayList();
      list.add(new Vec3(box.minX - mc.getRenderManager().getRenderPosX(), box.minY - mc.getRenderManager().getRenderPosY(), box.minZ - mc.getRenderManager().getRenderPosZ()));
      list.add(new Vec3(box.maxX - mc.getRenderManager().getRenderPosX(), box.minY - mc.getRenderManager().getRenderPosY(), box.minZ - mc.getRenderManager().getRenderPosZ()));
      list.add(new Vec3(box.maxX - mc.getRenderManager().getRenderPosX(), box.minY - mc.getRenderManager().getRenderPosY(), box.maxZ - mc.getRenderManager().getRenderPosZ()));
      list.add(new Vec3(box.minX - mc.getRenderManager().getRenderPosX(), box.minY - mc.getRenderManager().getRenderPosY(), box.maxZ - mc.getRenderManager().getRenderPosZ()));
      list.add(new Vec3(box.minX - mc.getRenderManager().getRenderPosX(), box.maxY - mc.getRenderManager().getRenderPosY(), box.minZ - mc.getRenderManager().getRenderPosZ()));
      list.add(new Vec3(box.maxX - mc.getRenderManager().getRenderPosX(), box.maxY - mc.getRenderManager().getRenderPosY(), box.minZ - mc.getRenderManager().getRenderPosZ()));
      list.add(new Vec3(box.maxX - mc.getRenderManager().getRenderPosX(), box.maxY - mc.getRenderManager().getRenderPosY(), box.maxZ - mc.getRenderManager().getRenderPosZ()));
      list.add(new Vec3(box.minX - mc.getRenderManager().getRenderPosX(), box.maxY - mc.getRenderManager().getRenderPosY(), box.maxZ - mc.getRenderManager().getRenderPosZ()));
      return list;
   }
}
