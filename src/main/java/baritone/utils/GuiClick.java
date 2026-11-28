package baritone.utils;

import baritone.Baritone;
import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.GoalBlock;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.Helper;
import baritone.api.command.IBaritoneChatControl;
import java.awt.Color;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Collections;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class GuiClick extends GuiScreen {
   private final FloatBuffer MODELVIEW = BufferUtils.createFloatBuffer(16);
   private final FloatBuffer PROJECTION = BufferUtils.createFloatBuffer(16);
   private final IntBuffer VIEWPORT = BufferUtils.createIntBuffer(16);
   private final FloatBuffer TO_WORLD_BUFFER = BufferUtils.createFloatBuffer(3);
   private BlockPos clickStart;
   private BlockPos currentMouseOver;

   public void onRender() {
      GlStateManager.getFloat(2982, (FloatBuffer)((Buffer)this.MODELVIEW).clear());
      GlStateManager.getFloat(2983, (FloatBuffer)((Buffer)this.PROJECTION).clear());
      GL11.glGetInteger(2978, (IntBuffer)((Buffer)this.VIEWPORT).clear());
      if (this.currentMouseOver != null) {
         PathRenderer.drawManySelectionBoxes(this.mc.getRenderViewEntity(), Collections.singletonList(this.currentMouseOver), Color.CYAN);
         if (this.clickStart != null && !this.clickStart.equals(this.currentMouseOver)) {
            IRenderer.startLines(Color.RED, (Float)Baritone.settings().pathRenderLineWidthPixels.value, true);
            BetterBlockPos betterBlockPos = new BetterBlockPos(this.currentMouseOver);
            BetterBlockPos betterBlockPos2 = new BetterBlockPos(this.clickStart);
            IRenderer.emitAABB(
               new AxisAlignedBB(
                  (double)Math.min(betterBlockPos.x2, betterBlockPos2.x2),
                  (double)Math.min(betterBlockPos.y2, betterBlockPos2.y2),
                  (double)Math.min(betterBlockPos.z2, betterBlockPos2.z2),
                  (double)(Math.max(betterBlockPos.x2, betterBlockPos2.x2) + 1),
                  (double)(Math.max(betterBlockPos.y2, betterBlockPos2.y2) + 1),
                  (double)(Math.max(betterBlockPos.z2, betterBlockPos2.z2) + 1)
               )
            );
            IRenderer.endLines(true);
         }
      }
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      if (this.currentMouseOver != null) {
         if (state == 0) {
            if (this.clickStart != null && !this.clickStart.equals(this.currentMouseOver)) {
               BaritoneAPI.getProvider().getPrimaryBaritone().getSelectionManager().removeAllSelections();
               BaritoneAPI.getProvider().getPrimaryBaritone().getSelectionManager().addSelection(BetterBlockPos.from(this.clickStart), BetterBlockPos.from(this.currentMouseOver));
               ChatComponentText chatComponentText = new ChatComponentText("Selection made! For usage: " + (String)Baritone.settings().prefix.value + "help sel");
               chatComponentText.getChatStyle()
                  .setColor(EnumChatFormatting.WHITE)
                  .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, IBaritoneChatControl.FORCE_COMMAND_PREFIX + "help sel"));
               Helper.HELPER.logDirect(new IChatComponent[]{chatComponentText});
               this.clickStart = null;
            } else {
               BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalBlock(this.currentMouseOver));
            }
         } else if (state == 1) {
            BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalBlock(this.currentMouseOver.up()));
         }
      }

      this.clickStart = null;
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
      this.clickStart = this.currentMouseOver;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      int x = Mouse.getX();
      int y = Mouse.getY();
      Vec3 vec = this.toWorld((double)x, (double)y, 0.0);
      Vec3 vec2 = this.toWorld((double)x, (double)y, 1.0);
      if (vec != null && vec2 != null) {
         Vec3 vec3 = new Vec3(this.mc.getRenderManager().viewerPosX, this.mc.getRenderManager().viewerPosY, this.mc.getRenderManager().viewerPosZ);
         Vec3 vec4 = vec.add(vec3);
         Vec3 vec5 = vec2.add(vec3);
         MovingObjectPosition hit = this.mc
            .theWorld
            .rayTraceBlocks(vec4.xCoord, vec4.yCoord, vec4.zCoord, vec5.xCoord, vec5.yCoord, vec5.zCoord, false, false, true);
         if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            this.currentMouseOver = hit.getBlockPos();
         }
      }
   }

   @Override
   public boolean doesGuiPauseGame() {
      return false;
   }

   private Vec3 toWorld(double d, double d2, double d3) {
      return GLU.gluUnProject((float)d, (float)d2, (float)d3, this.MODELVIEW, this.PROJECTION, this.VIEWPORT, (FloatBuffer)((Buffer)this.TO_WORLD_BUFFER).clear()) ? new Vec3((double)this.TO_WORLD_BUFFER.get(0), (double)this.TO_WORLD_BUFFER.get(1), (double)this.TO_WORLD_BUFFER.get(2)) : null;
   }
}
