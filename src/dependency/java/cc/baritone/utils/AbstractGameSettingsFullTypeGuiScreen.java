package cc.baritone.utils;

import client.setting.GameSettingsFullType;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public abstract class AbstractGameSettingsFullTypeGuiScreen extends GuiScreen {
   public float factor3;
   public float factor4;
   public int count2;
   public boolean enabled6;
   public boolean enabled7;
   public GameSettingsFullType gameSettingsFullType;
   public EntityPlayerSP entityPlayerSP;
   public Entity entity;
   public boolean enabled8 = false;
   public static final float factor5 = 1.0F;
   public static final float factor6 = 0.7F;

   @Override
   public void updateState4() {
      this.factor3 = this.factor4;
      this.factor4 = this.factor3 + 1.0F * (float)(1.0 + 0.7F * Math.cos((double)(this.factor3 + 45.0F) / 45.0 * Math.PI));
   }

   @Override
   public void enableRepeatEvents() {
      super.enableRepeatEvents();
      Pokechu22WorldDownloaderManager.minecraft.gameSettings.state106 = this.enabled7;
      Pokechu22WorldDownloaderManager.minecraft.gameSettings.gameSettingsFullType = this.gameSettingsFullType;
      Entity entity2 = this.entity;
      Pokechu22WorldDownloaderManager.minecraft.applyEntityByEntity(entity2);
   }

   private double computeValue(double posX, double posY, double posZ) {
      double offset = posZ;
      Vec3 vec3 = Pokechu22WorldDownloaderManager.entityPlayerSP
         .getVec3()
         .getVec312(0.0, (double)Pokechu22WorldDownloaderManager.entityPlayerSP.getHeight2(), 0.0);
      Vec3 vec = new Vec3(
         Pokechu22WorldDownloaderManager.entityPlayerSP.posX - posZ * posX,
         Pokechu22WorldDownloaderManager.entityPlayerSP.posY + (double)Pokechu22WorldDownloaderManager.entityPlayerSP.getHeight2(),
         Pokechu22WorldDownloaderManager.entityPlayerSP.posZ + posY
      );

      for (int index = 0; index < 9; index++) {
         float factor = (index & 1) != 0 ? -0.1F : 0.1F;
         float factor2 = (index & 2) != 0 ? -0.1F : 0.1F;
         float factor7 = (index & 4) != 0 ? -0.1F : 0.1F;
         if (index == 8) {
            factor = 0.0F;
            factor2 = 0.0F;
            factor7 = 0.0F;
         }

         Vec3 vec32 = vec3.getVec312((double)factor, (double)factor2, (double)factor7);
         Vec3 vec33 = vec.getVec312((double)factor, (double)factor2, (double)factor7);
         MovingObjectPosition movingObjectPosition = this.mc
            .theWorld
            .getMovingObjectPosition2(vec32.xCoord, vec32.yCoord, vec32.zCoord, vec33.xCoord, vec33.yCoord, vec33.zCoord);
         if (movingObjectPosition != null) {
            double coordinate = movingObjectPosition.hitVec.getCoordinate4(vec3);
            if (coordinate < offset && coordinate > 0.0) {
               offset = coordinate;
            }
         }
      }

      return offset - 0.25;
   }

   @Override
   public void applyIndex(int count, int count3, float factor) {
      if (this.entityPlayerSP != null) {
         float factor2 = this.factor3 + (this.factor4 - this.factor3) * factor;
         this.entityPlayerSP.prevRotationPitch = this.entityPlayerSP.rotationPitch = 0.0F;
         this.entityPlayerSP.prevRotationYaw = this.entityPlayerSP.rotationYaw = factor2;
         double offset = Math.cos((double)factor2 / 180.0 * Math.PI);
         double offset2 = Math.sin((double)(factor2 - 90.0F) / 180.0 * Math.PI);

         double offset3;
         try {
            offset3 = this.computeValue(offset, offset2, 0.5);
         } catch (NullPointerException nullPointerException) {
            return;
         }

         this.entityPlayerSP.lastTickPosY = this.entityPlayerSP.prevPosY = this.entityPlayerSP.posY = Pokechu22WorldDownloaderManager.entityPlayerSP.posY;
         this.entityPlayerSP.lastTickPosX = this.entityPlayerSP.prevPosX = this.entityPlayerSP.posX = Pokechu22WorldDownloaderManager.entityPlayerSP.posX
            - offset3 * offset;
         this.entityPlayerSP.lastTickPosZ = this.entityPlayerSP.prevPosZ = this.entityPlayerSP.posZ = Pokechu22WorldDownloaderManager.entityPlayerSP.posZ
            + offset3 * offset2;
      }

      super.applyIndex(count, count3, factor);
   }

   @Override
   public void updateState2() {
      if (!this.enabled8) {
         this.entityPlayerSP = new EntityPlayerSP(
            Pokechu22WorldDownloaderManager.minecraft,
            Pokechu22WorldDownloaderManager.worldClient,
            Pokechu22WorldDownloaderManager.entityPlayerSP.netHandlerPlayClient,
            Pokechu22WorldDownloaderManager.entityPlayerSP.getStatFileWriter()
         );
         this.entityPlayerSP
            .applyDoubleByDouble(
               Pokechu22WorldDownloaderManager.entityPlayerSP.posX,
               Pokechu22WorldDownloaderManager.entityPlayerSP.posY - Pokechu22WorldDownloaderManager.entityPlayerSP.getValue(),
               Pokechu22WorldDownloaderManager.entityPlayerSP.posZ,
               Pokechu22WorldDownloaderManager.entityPlayerSP.rotationYaw,
               0.0F
            );
         this.factor3 = this.factor4 = Pokechu22WorldDownloaderManager.entityPlayerSP.rotationYaw;
         this.enabled7 = Pokechu22WorldDownloaderManager.minecraft.gameSettings.state106;
         this.gameSettingsFullType = Pokechu22WorldDownloaderManager.minecraft.gameSettings.gameSettingsFullType;
         Pokechu22WorldDownloaderManager.minecraft.gameSettings.state106 = false;
         Pokechu22WorldDownloaderManager.minecraft.gameSettings.gameSettingsFullType = GameSettingsFullType.HIDDEN;
         this.entity = Pokechu22WorldDownloaderManager.minecraft.getRenderViewEntity();
         this.enabled8 = true;
      }

      EntityPlayerSP entityPlayerSP2 = this.entityPlayerSP;
      Pokechu22WorldDownloaderManager.minecraft.applyEntityByEntity(entityPlayerSP2);
   }
}
