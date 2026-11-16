package cc.baritone.utils;

import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsOpenUrlType;
import client.util.ClickEventActionManager;
import client.util.MovingObjectType;
import java.awt.Color;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Collections;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class HeuristicService extends GuiScreen {
   public final FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);
   public final FloatBuffer floatBuffer2 = BufferUtils.createFloatBuffer(16);
   public final IntBuffer intBuffer = BufferUtils.createIntBuffer(16);
   public final FloatBuffer floatBuffer3 = BufferUtils.createFloatBuffer(3);
   public BlockPos blockPos;
   public BlockPos blockPos2;

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public void processAction(int count, int count2, int count3) {
      this.blockPos = this.blockPos2;
   }

   private Vec3 resolveVec3(double posX, double posY, double posZ) {
      return GLU.gluUnProject(
            (float)posX, (float)posY, (float)posZ, this.floatBuffer, this.floatBuffer2, this.intBuffer, (FloatBuffer)((Buffer)this.floatBuffer3).clear()
         )
         ? new Vec3((double)this.floatBuffer3.get(0), (double)this.floatBuffer3.get(1), (double)this.floatBuffer3.get(2))
         : null;
   }

   public void updateState() {
      GlStateManager.glStateAction2(2982, (FloatBuffer)((Buffer)this.floatBuffer).clear());
      GlStateManager.glStateAction2(2983, (FloatBuffer)((Buffer)this.floatBuffer2).clear());
      GL11.glGetInteger(2978, (IntBuffer)((Buffer)this.intBuffer).clear());
      if (this.blockPos2 != null) {
         DepthFirstSampler.applyEntity(this.mc.getRenderViewEntity(), Collections.singletonList(this.blockPos2), Color.CYAN);
         if (this.blockPos != null && !this.blockPos.equals(this.blockPos2)) {
            TessellatorCallback.applyColor(Color.RED, (Float)BaritoneManager.getSettings().pathRenderLineWidthPixels.dataRef, true);
            BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = new BetterBlockPosXSYSZSBlockPos(this.blockPos2);
            BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = new BetterBlockPosXSYSZSBlockPos(this.blockPos);
            TessellatorCallback.applyAxisAlignedBBByAxisAlignedBB(
               new AxisAlignedBB(
                  (double)Math.min(betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos2.count9),
                  (double)Math.min(betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos2.count12),
                  (double)Math.min(betterBlockPosXSYSZSBlockPos.count10, betterBlockPosXSYSZSBlockPos2.count10),
                  (double)(Math.max(betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos2.count9) + 1),
                  (double)(Math.max(betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos2.count12) + 1),
                  (double)(Math.max(betterBlockPosXSYSZSBlockPos.count10, betterBlockPosXSYSZSBlockPos2.count10) + 1)
               )
            );
            TessellatorCallback.setEnabled(true);
         }
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      if (this.blockPos2 != null) {
         if (count3 == 0) {
            if (this.blockPos != null && !this.blockPos.equals(this.blockPos2)) {
               SettingsTxtManager.getExtraListener().get().getListener().getManagedListenerArray();
               SettingsTxtManager.getExtraListener()
                  .get()
                  .getListener()
                  .resolveManagedListener(
                     BetterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(this.blockPos),
                     BetterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(this.blockPos2)
                  );
               ChatComponentText chatComponentText = new ChatComponentText("Selection made! For usage: " + (String)BaritoneManager.getSettings().prefix.dataRef + "help sel");
               chatComponentText.getStyleHasParentManager()
                  .withClientSettingsBlackType(ClientSettingsBlackType.WHITE)
                  .getStyleHasParentManager(new ClickEventActionManager(ClientSettingsOpenUrlType.RUN_COMMAND, BreadthFirstHandler.labelText + "help sel"));
               DRNHandler.dRNHandler.applyIChatComponentArray(new IChatComponent[]{chatComponentText});
               this.blockPos = null;
            } else {
               SettingsTxtManager.getExtraListener()
                  .get()
                  .getINACTIVEHandlerExtension()
                  .applySecondaryListenerBySecondaryListene(new DepthFirstResolver(this.blockPos2));
            }
         } else if (count3 == 1) {
            SettingsTxtManager.getExtraListener()
               .get()
               .getINACTIVEHandlerExtension()
               .applySecondaryListenerBySecondaryListene(new DepthFirstResolver(this.blockPos2.up()));
         }
      }

      this.blockPos = null;
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      int coordX = Mouse.getX();
      int coordY = Mouse.getY();
      Vec3 vec = this.resolveVec3((double)coordX, (double)coordY, 0.0);
      Vec3 vec2 = this.resolveVec3((double)coordX, (double)coordY, 1.0);
      if (vec != null && vec2 != null) {
         Vec3 vec3 = new Vec3(this.mc.getRenderManager().viewerPosX, this.mc.getRenderManager().viewerPosZ, this.mc.getRenderManager().viewerPosY);
         Vec3 vec32 = vec.getVec36(vec3);
         Vec3 vec33 = vec2.getVec36(vec3);
         MovingObjectPosition movingObjectPosition = this.mc
            .theWorld
            .getMovingObjectPosition4(vec32.xCoord, vec32.yCoord, vec32.zCoord, vec33.xCoord, vec33.yCoord, vec33.zCoord, false, false, true);
         if (movingObjectPosition != null && movingObjectPosition.typeOfHit == MovingObjectType.BLOCK) {
            this.blockPos2 = movingObjectPosition.getBlockPos();
         }
      }
   }
}
