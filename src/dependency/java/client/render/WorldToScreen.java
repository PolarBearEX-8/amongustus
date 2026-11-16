package client.render;

import client.util.MinecraftAccessor;
import client.util.TimeHelper;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class WorldToScreen implements MinecraftAccessor {
   public static final FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(4);
   public static final IntBuffer intBuffer = BufferUtils.createIntBuffer(16);
   public static final FloatBuffer floatBuffer2 = BufferUtils.createFloatBuffer(16);
   public static final FloatBuffer floatBuffer3 = BufferUtils.createFloatBuffer(16);

   public static double[] resolveDoubleArray(AxisAlignedBB boundingBox, ScaledResolution scaledResolution) {
      double offset = -1337.0;
      double offset2 = -1337.0;
      double offset3 = -1337.0;
      double offset4 = -1337.0;

      for (Vec3 vec : (Iterable<Vec3>)(Iterable)(resolveArrayList(boundingBox))) {
         Vec3 vec2 = resolveVec3((double)((float)vec.xCoord), (double)((float)vec.yCoord), (double)((float)vec.zCoord), scaledResolution);
         if (vec2.zCoord >= 0.0 && vec2.zCoord <= 1.0) {
            if (offset == -1337.0 || offset > vec2.xCoord) {
               offset = vec2.xCoord;
            }

            if (offset2 == -1337.0 || offset2 > vec2.yCoord) {
               offset2 = vec2.yCoord;
            }

            if (offset3 == -1337.0 || offset3 < vec2.xCoord) {
               offset3 = vec2.xCoord;
            }

            if (offset4 == -1337.0 || offset4 < vec2.yCoord) {
               offset4 = vec2.yCoord;
            }
         }
      }

      return new double[]{offset, offset2, offset3, offset4};
   }

   public static Vec3 resolveVec3(double posX, double posY, double posZ, ScaledResolution scaledResolution) {
      renderElement();
      GLU.gluProject((float)posX, (float)posY, (float)posZ, floatBuffer3, floatBuffer2, intBuffer, floatBuffer);
      int scaleFactor = scaledResolution.getScaleFactor();
      return new Vec3(
         (double)(floatBuffer.get(0) / (float)scaleFactor),
         (double)(((float)MinecraftAccessor.minecraft.displayHeight - floatBuffer.get(1)) / (float)scaleFactor),
         (double)floatBuffer.get(2)
      );
   }

   static {
      int[] countArray = new int[3];
   }

   private static ArrayList resolveArrayList(AxisAlignedBB boundingBox) {
      ArrayList list = new ArrayList();
      list.add(
         new Vec3(
            boundingBox.minX - MinecraftAccessor.minecraft.getRenderManager().getRenderPosX(),
            boundingBox.minY - MinecraftAccessor.minecraft.getRenderManager().getRenderPosY(),
            boundingBox.minZ - MinecraftAccessor.minecraft.getRenderManager().getRenderPosZ()
         )
      );
      list.add(
         new Vec3(
            boundingBox.maxX - MinecraftAccessor.minecraft.getRenderManager().getRenderPosX(),
            boundingBox.minY - MinecraftAccessor.minecraft.getRenderManager().getRenderPosY(),
            boundingBox.minZ - MinecraftAccessor.minecraft.getRenderManager().getRenderPosZ()
         )
      );
      list.add(
         new Vec3(
            boundingBox.maxX - MinecraftAccessor.minecraft.getRenderManager().getRenderPosX(),
            boundingBox.minY - MinecraftAccessor.minecraft.getRenderManager().getRenderPosY(),
            boundingBox.maxZ - MinecraftAccessor.minecraft.getRenderManager().getRenderPosZ()
         )
      );
      list.add(
         new Vec3(
            boundingBox.minX - MinecraftAccessor.minecraft.getRenderManager().getRenderPosX(),
            boundingBox.minY - MinecraftAccessor.minecraft.getRenderManager().getRenderPosY(),
            boundingBox.maxZ - MinecraftAccessor.minecraft.getRenderManager().getRenderPosZ()
         )
      );
      list.add(
         new Vec3(
            boundingBox.minX - MinecraftAccessor.minecraft.getRenderManager().getRenderPosX(),
            boundingBox.maxY - MinecraftAccessor.minecraft.getRenderManager().getRenderPosY(),
            boundingBox.minZ - MinecraftAccessor.minecraft.getRenderManager().getRenderPosZ()
         )
      );
      list.add(
         new Vec3(
            boundingBox.maxX - MinecraftAccessor.minecraft.getRenderManager().getRenderPosX(),
            boundingBox.maxY - MinecraftAccessor.minecraft.getRenderManager().getRenderPosY(),
            boundingBox.minZ - MinecraftAccessor.minecraft.getRenderManager().getRenderPosZ()
         )
      );
      list.add(
         new Vec3(
            boundingBox.maxX - MinecraftAccessor.minecraft.getRenderManager().getRenderPosX(),
            boundingBox.maxY - MinecraftAccessor.minecraft.getRenderManager().getRenderPosY(),
            boundingBox.maxZ - MinecraftAccessor.minecraft.getRenderManager().getRenderPosZ()
         )
      );
      list.add(
         new Vec3(
            boundingBox.minX - MinecraftAccessor.minecraft.getRenderManager().getRenderPosX(),
            boundingBox.maxY - MinecraftAccessor.minecraft.getRenderManager().getRenderPosY(),
            boundingBox.maxZ - MinecraftAccessor.minecraft.getRenderManager().getRenderPosZ()
         )
      );
      return list;
   }

   public static void renderElement() {
      GL11.glGetFloat(2982, floatBuffer3);
      GL11.glGetFloat(2983, floatBuffer2);
      GL11.glGetInteger(2978, intBuffer);
   }
}
