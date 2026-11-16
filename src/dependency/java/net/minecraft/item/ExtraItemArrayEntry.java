package net.minecraft.item;

import client.render.WorldRenderer;
import client.util.BuildManager;
import client.util.DefaultVertexFormats;
import client.util.RotationEvaluator;
import client.util.Vec3Entry4;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;

public class ExtraItemArrayEntry {
   public Vec3Entry4[] vec3Entry4Array;
   public int count;
   public boolean enabled;

   public void updateState() {
      Vec3Entry4[] vec3Entry4Array2 = new Vec3Entry4[this.vec3Entry4Array.length];

      for (int index = 0; index < this.vec3Entry4Array.length; index++) {
         vec3Entry4Array2[index] = this.vec3Entry4Array[this.vec3Entry4Array.length - index - 1];
      }

      this.vec3Entry4Array = vec3Entry4Array2;
   }

   public void applyWorldRenderer(WorldRenderer worldRenderer, float factor) {
      Vec3 vec = this.vec3Entry4Array[1].vec3.resolveVec3(this.vec3Entry4Array[0].vec3);
      Vec3 vec2 = this.vec3Entry4Array[1].vec3.resolveVec3(this.vec3Entry4Array[2].vec3).subtract(vec).normalize();
      float posX = (float)vec2.xCoord;
      float posY = (float)vec2.yCoord;
      float posZ = (float)vec2.zCoord;
      if (this.enabled) {
         posX = -posX;
         posY = -posY;
         posZ = -posZ;
      }

      boolean flag = !worldRenderer.isEnabled3();
      if (flag) {
         if (BuildManager.canExecute()) {
            worldRenderer.updateAuxiliaryLoggerEntry(7, RotationEvaluator.auxiliaryLoggerEntry);
         } else {
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry4);
         }
      }

      for (int index = 0; index < 4; index++) {
         Vec3Entry4 vec3Entry = this.vec3Entry4Array[index];
         worldRenderer.getWorldRenderer(vec3Entry.vec3.xCoord * (double)factor, vec3Entry.vec3.yCoord * (double)factor, vec3Entry.vec3.zCoord * (double)factor)
            .getWorldRenderer6((double)vec3Entry.factor, (double)vec3Entry.factor2)
            .resolveWorldRenderer(posX, posY, posZ)
            .updateState();
      }

      if (flag) {
         Tessellator.getTessellator().updateState();
      }
   }

   public ExtraItemArrayEntry(Vec3Entry4[] vec3Entry4Array2) {
      this.vec3Entry4Array = vec3Entry4Array2;
      this.count = vec3Entry4Array2.length;
   }

   public ExtraItemArrayEntry(Vec3Entry4[] vec3Entry4Array2, int count2, int count3, int count4, int count5, float factor, float factor2) {
      this(vec3Entry4Array2);
      float factor3 = 0.0F / factor;
      float factor4 = 0.0F / factor2;
      vec3Entry4Array2[0] = vec3Entry4Array2[0].resolveVec3Entry4((float)count4 / factor - factor3, (float)count3 / factor2 + factor4);
      vec3Entry4Array2[1] = vec3Entry4Array2[1].resolveVec3Entry4((float)count2 / factor + factor3, (float)count3 / factor2 + factor4);
      vec3Entry4Array2[2] = vec3Entry4Array2[2].resolveVec3Entry4((float)count2 / factor + factor3, (float)count5 / factor2 - factor4);
      vec3Entry4Array2[3] = vec3Entry4Array2[3].resolveVec3Entry4((float)count4 / factor - factor3, (float)count5 / factor2 - factor4);
   }
}
