package net.augustus.modules.legit;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.util.ArrayList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.modules.legit.helper.MlgJumpOutcome;
import net.augustus.render.RenderUtil;
import net.augustus.settings.DoubleValue;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.opengl.GL11;

public class MLGHelper extends Module implements RenderListener {
   private final RangeSetting minDistance;
   private final ColorSetting textColor;
   private final DoubleValue posX;
   private final DoubleValue posY;
   private final DoubleValue scale;
   private final ArrayList walkFallHeights;
   private final ArrayList jumpFallHeights;
   private final ArrayList bothFallHeights;

   public MLGHelper() {
       super("MLGHelper", "MLGHelfer", new Color(50, 168, 141), Categorys.LEGIT);
      this.minDistance = new RangeSetting(this, "MinDistance", 10.0, 100.0, 10.0, 100.0, 0);
      this.textColor = new ColorSetting(this, "Color", new Color(255, 255, 255, 255));
      this.posX = new DoubleValue(this, "PosX", 50.0, 0.0, 100.0, 2);
      this.posY = new DoubleValue(this, "PosY", 0.0, 0.0, 100.0, 2);
      this.scale = new DoubleValue(this, "Scale", 1.0, 0.5, 4.0, 1);
      this.walkFallHeights = Lists.newArrayList(
         new Integer[]{
            16,
            19,
            22,
            24,
            27,
            29,
            34,
            36,
            38,
            40,
            42,
            49,
            51,
            53,
            55,
            58,
            60,
            62,
            65,
            67,
            69,
            72,
            74,
            77,
            79,
            82,
            85,
            87,
            90,
            93,
            95,
            98,
            101,
            104,
            106,
            109,
            112,
            115,
            118,
            119,
            121,
            127,
            130,
            133,
            135,
            136
         }
      );
      this.jumpFallHeights = Lists.newArrayList(
         new Integer[]{
            17,
            18,
            20,
            23,
            28,
            30,
            31,
            35,
            37,
            39,
            41,
            43,
            45,
            50,
            52,
            54,
            56,
            59,
            61,
            63,
            66,
            68,
            71,
            73,
            76,
            78,
            81,
            83,
            86,
            89,
            91,
            94,
            97,
            100,
            103,
            105,
            107,
            108,
            111,
            114,
            117,
            122,
            125,
            128
         }
      );
      this.bothFallHeights = Lists.newArrayList(new Integer[]{10, 11, 13, 14, 15, 22, 26, 33, 47});
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
   }

   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      MlgJumpOutcome mlgJumpOutcome = this.calculateJumpOutcome();
      if (mlgJumpOutcome != MlgJumpOutcome.NOTHING) {
         GL11.glPushMatrix();
         GL11.glScaled(this.scale.getValue(), this.scale.getValue(), 1.0);
         FontRenderer fontRendererObj = mc.fontRendererObj;
         String displayName2 = mlgJumpOutcome.getDisplayName();
         int n = (int)(
            sr.getScaledWidth_double() / this.scale.getValue() * this.posX.getValue() / 100.0
               - (double)((float)mc.fontRendererObj.getStringWidth(mlgJumpOutcome.getDisplayName()) / 2.0F)
         );
         fontRendererObj.drawString(displayName2, n, (int)(sr.getScaledHeight_double() / this.scale.getValue() * this.posY.getValue() / 100.0), this.textColor.getColorInt((byte)0));
         GL11.glPopMatrix();
      }

      RenderUtil.resetGlColorState();
   }

   public MlgJumpOutcome calculateJumpOutcome() {
      if (mc.thePlayer != null && mc.theWorld != null) {
         if (!this.isMovingVertically() && this.isHoldingMlgItem()) {
            return this.evaluateLookedAtBlock();
         }
      }

      return MlgJumpOutcome.NOTHING;
   }

   private boolean isMovingVertically() {
      return mc.thePlayer.prevPosY != mc.thePlayer.posY;
   }

   private boolean isHoldingMlgItem() {
      ItemStack currentEquippedItem = mc.thePlayer.getCurrentEquippedItem();
      return currentEquippedItem != null && (currentEquippedItem.getItem().equals(Item.getItemById(30)) || currentEquippedItem.getItem().equals(Item.getItemById(65)));
   }

   private MlgJumpOutcome evaluateLookedAtBlock() {
      MovingObjectPosition hit = this.rayTraceLandingBlock();
      if (hit != null && this.isWithinHorizontalRange(hit) && this.isAirAboveTargetBlock(hit)) {
         return this.classifyFallHeight(this.getFallHeight(hit) - 1);
      } else {
         return MlgJumpOutcome.NOTHING;
      }
   }

   private MovingObjectPosition rayTraceLandingBlock() {
      return mc.thePlayer.rayTrace(256.0, 1.0F);
   }

   private boolean isWithinHorizontalRange(MovingObjectPosition hit) {
      BlockPos position = mc.thePlayer.getPosition();
      BlockPos blockPos = hit.getBlockPos();
      int n = Math.abs(blockPos.getX() - position.getX());
      int n2 = Math.abs(blockPos.getZ() - position.getZ());
      double d = (double)n;
      if (d <= this.minDistance.getMaxValue()) {
         d = (double)n2;
         if (d <= this.minDistance.getMaxValue()) {
            return true;
         }
      }

      return false;
   }

   private boolean isAirAboveTargetBlock(MovingObjectPosition hit) {
      BlockPos pos = hit.getBlockPos().up();
      return mc.theWorld.getBlockState(pos).getBlock() instanceof BlockAir;
   }

   private int getFallHeight(MovingObjectPosition hit) {
      int y = hit.getBlockPos().getY();
      return mc.thePlayer.getPosition().getY() - y;
   }

   private MlgJumpOutcome classifyFallHeight(int n) {
      if ((double)n <= this.minDistance.getMinValue()) {
         return MlgJumpOutcome.NOTHING;
      } else if (this.walkFallHeights.contains(n)) {
         return MlgJumpOutcome.WALK;
      } else if (this.jumpFallHeights.contains(n)) {
         return MlgJumpOutcome.JUMP;
      } else {
         return this.bothFallHeights.contains(n) ? MlgJumpOutcome.BOTH : MlgJumpOutcome.IMPOSSIBLE;
      }
   }

}
