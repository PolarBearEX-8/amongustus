package net.minecraft.item;


import client.event.EventBoundingBox;
import client.event.EventBoundingBoxCallback;
import client.setting.ItemNoneType;
import client.util.CurveSelector;
import client.ClientGlobals;
import client.module.Module;
import client.render.EntitySkinLocation;
import client.render.RenderManager;
import client.render.RenderPlayer;
import client.render.WorldRenderer;
import client.setting.BlockSolidType;
import client.setting.ItemPhysicsNoneType;
import client.util.BuildManager;
import client.util.DefaultVertexFormats;
import client.util.DimensionStringEntry;
import client.util.IBlockState;
import client.util.ListenerAdapter27;
import client.util.PreEndHandManager;
import client.util.ServiceStringEntry;
import client.util.SlotManager;
import client.util.TargetEstimator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ItemRenderer {
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/map/map_background.png");
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/misc/underwater.png");
   public final Minecraft mc;
   public ItemStack itemStack;
   public float factor2;
   public float factor;
   public final RenderManager renderManager;
   public final RenderItem renderItem;
   public int count = -1;

   private void applyServiceStringEntry(float factor3, ServiceStringEntry serviceStringEntry) {
      this.mc.getTextureManager().applyResourceLocation3(ListenerAdapter27.resourceLocation);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      GlStateManager.color4f2(0.1F, 0.1F, 0.1F, 0.5F);
      GlStateManager.pushMatrix();
      float factor4 = serviceStringEntry.getFactor5();
      float factor5 = serviceStringEntry.getFactor2();
      float factor6 = serviceStringEntry.getFactor();
      float factor7 = serviceStringEntry.getFactor6();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer(-1.0, -1.0, -0.5).getWorldRenderer6((double)factor5, (double)factor7).updateState();
      worldRenderer.getWorldRenderer(1.0, -1.0, -0.5).getWorldRenderer6((double)factor4, (double)factor7).updateState();
      worldRenderer.getWorldRenderer(1.0, 1.0, -0.5).getWorldRenderer6((double)factor4, (double)factor6).updateState();
      worldRenderer.getWorldRenderer(-1.0, 1.0, -0.5).getWorldRenderer6((double)factor5, (double)factor6).updateState();
      tessellator.updateState();
      GlStateManager.popMatrix();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void updateState4() {
      if (ClientGlobals.moduleManager.legacyAnimations.canExecute8()) {
         GlStateManager.translate(-0.43F, 0.2F, 0.0F);
      } else {
         GlStateManager.translate(-0.5F, 0.2F, 0.0F);
      }

      GlStateManager.rotate(30.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(60.0F, 0.0F, 1.0F, 0.0F);
   }

   private void applyFloat(float factor3, float factor4) {
      if (ClientGlobals.moduleManager.legacyAnimations.canExecute6()) {
         GlStateManager.translate3f3(0.85F, 0.85F, 0.85F);
         GlStateManager.translate(-0.078F, 0.003F, 0.05F);
      }

      if (ClientGlobals.moduleManager.legacyAnimations.canExecute8()) {
         GlStateManager.translate(0.56F, -0.52F, -0.71999997F);
         GlStateManager.translate(0.0F, factor3 * -0.6F, 0.0F);
         GlStateManager.rotate(50.0F, 0.0F, 1.0F, 0.0F);
      } else {
         GlStateManager.translate(0.56F, -0.52F, -0.71999997F);
         GlStateManager.translate(0.0F, factor3 * -0.6F, 0.0F);
         GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
      }

      float factor5 = MathHelper.abs(factor4 * factor4 * (float) Math.PI);
      float factor6 = MathHelper.abs(MathHelper.sin(factor4) * (float) Math.PI);
      GlStateManager.rotate(factor5 * -20.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(factor6 * -20.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(factor6 * -80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate3f3(0.4F, 0.4F, 0.4F);
   }

   private void applyAbstractClientPlayerByAbstractClientPl(AbstractClientPlayer player, float factor3, float factor4) {
      float factor5 = -0.3F * MathHelper.abs(MathHelper.sin(factor4) * (float) Math.PI);
      float factor6 = 0.4F * MathHelper.abs(MathHelper.sin(factor4) * (float) Math.PI * 2.0F);
      float factor7 = -0.4F * MathHelper.abs(factor4 * (float) Math.PI);
      GlStateManager.translate(factor5, factor6, factor7);
      GlStateManager.translate(0.64000005F, -0.6F, -0.71999997F);
      GlStateManager.translate(0.0F, factor3 * -0.6F, 0.0F);
      GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
      float factor8 = MathHelper.abs(factor4 * factor4 * (float) Math.PI);
      GlStateManager.rotate(MathHelper.abs(MathHelper.sin(factor4) * (float) Math.PI) * 70.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(factor8 * -20.0F, 0.0F, 0.0F, 1.0F);
      this.mc.getTextureManager().applyResourceLocation3(player.getResourceLocation2());
      GlStateManager.translate(-1.0F, 3.6F, 3.5F);
      GlStateManager.rotate(120.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(200.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate3f3(1.0F, 1.0F, 1.0F);
      GlStateManager.translate(5.6F, 0.0F, 0.0F);
      EntitySkinLocation entitySkinLocation = this.renderManager.resolveEntitySkinLocation(this.mc.thePlayer);
      GlStateManager.disableAlpha();
      ((RenderPlayer)entitySkinLocation).applyAbstractClientPlayer(this.mc.thePlayer);
      GlStateManager.enableLighting();
   }

   public void updateState3() {
      this.factor = this.factor2;
      EntityPlayerSP player = this.mc.thePlayer;
      SlotManager.itemStack = this.mc.thePlayer.container.getSlot(SlotManager.indexOffset + 36).getStack();
      ItemStack itemStack2 = SlotManager.itemStack;
      boolean flag = false;
      if (this.itemStack != null && itemStack2 != null) {
         if (!this.itemStack.processAction(itemStack2)) {
            flag = true;
         }
      } else if (this.itemStack == null && itemStack2 == null) {
         flag = false;
      } else {
         flag = true;
      }

      float factor3 = 0.4F;
      float factor4 = MathHelper.clamp_float((flag ? 0.0F : 1.0F) - this.factor2, -factor3, factor3);
      this.factor2 += factor4;
      if (this.factor2 < 0.1F) {
         this.itemStack = itemStack2;
         this.count = player.inventoryPlayer.currentItem;
         if (BuildManager.canExecute()) {
            PreEndHandManager.applyItemStack(itemStack2);
         }

         this.itemStack = itemStack2;
         this.count = player.inventoryPlayer.currentItem;
      }
   }

   private void applyRenderPlayer(RenderPlayer renderPlayer) {
      GlStateManager.pushMatrix();
      GlStateManager.rotate(92.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(41.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.translate(-0.3F, -1.1F, 0.45F);
      renderPlayer.applyAbstractClientPlayer2(this.mc.thePlayer);
      GlStateManager.popMatrix();
   }

   private void applyAbstractClientPlayer(float factor3, AbstractClientPlayer player) {
      GlStateManager.rotate(-18.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(-12.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-8.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate(-0.9F, 0.2F, 0.0F);
      float factor4 = (float)this.itemStack.processAction6() - ((float)player.getEntityId2() - factor3 + 1.0F);
      float factor5 = factor4 / 20.0F;
      float factor6 = (factor5 * factor5 + factor5 * 2.0F) / 3.0F;
      if (factor6 > 1.0F) {
         factor6 = 1.0F;
      }

      if (factor6 > 0.1F) {
         float factor7 = MathHelper.abs((factor4 - 0.1F) * 1.3F);
         float factor8 = factor6 - 0.1F;
         float factor9 = factor7 * factor8;
         GlStateManager.translate(factor9 * 0.0F, factor9 * 0.01F, factor9 * 0.0F);
      }

      GlStateManager.translate(factor6 * 0.0F, factor6 * 0.0F, factor6 * 0.1F);
      GlStateManager.translate3f3(1.0F, 1.0F, 1.0F + factor6 * 0.2F);
   }

   private void applyAbstractClientPlayerByAbstractClientPl(AbstractClientPlayer player, float posX, float posY, float posZ) {
      float factor3 = -0.4F * MathHelper.abs(MathHelper.sin(posZ) * (float) Math.PI);
      float factor4 = 0.2F * MathHelper.abs(MathHelper.sin(posZ) * (float) Math.PI * 2.0F);
      float factor5 = -0.2F * MathHelper.abs(posZ * (float) Math.PI);
      GlStateManager.translate(factor3, factor4, factor5);
      float factor6 = this.computeFactor(posX);
      GlStateManager.translate(0.0F, 0.04F, -0.72F);
      GlStateManager.translate(0.0F, posY * -1.2F, 0.0F);
      GlStateManager.translate(0.0F, factor6 * -0.5F, 0.0F);
      GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(factor6 * -85.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
      this.applyAbstractClientPlayer2(player);
      float factor7 = MathHelper.abs(posZ * posZ * (float) Math.PI);
      float factor8 = MathHelper.abs(MathHelper.sin(posZ) * (float) Math.PI);
      GlStateManager.rotate(factor7 * -20.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(factor8 * -20.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(factor8 * -80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate3f3(0.38F, 0.38F, 0.38F);
      GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate(-1.0F, -1.0F, 0.0F);
      GlStateManager.translate3f3(0.015625F, 0.015625F, 0.015625F);
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation2);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      GL11.glNormal3f(0.0F, 0.0F, -1.0F);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer(-7.0, 135.0, 0.0).getWorldRenderer6(0.0, 1.0).updateState();
      worldRenderer.getWorldRenderer(135.0, 135.0, 0.0).getWorldRenderer6(1.0, 1.0).updateState();
      worldRenderer.getWorldRenderer(135.0, -7.0, 0.0).getWorldRenderer6(1.0, 0.0).updateState();
      worldRenderer.getWorldRenderer(-7.0, -7.0, 0.0).getWorldRenderer6(0.0, 0.0).updateState();
      tessellator.updateState();
      DimensionStringEntry dimensionStringEntry = Items.filledMap.resolveDimensionStringEntryByItemStack(this.itemStack, this.mc.theWorld);
      if (dimensionStringEntry != null) {
         this.mc.entityRenderer.getTexturesMapIconsManager().applyDimensionStringEntry(dimensionStringEntry, false);
      }
   }

   public void updateState() {
      this.factor2 = 0.0F;
   }

   public void applyFloatByFloat(float factor3) {
      if (!BuildManager.canExecute() || !PreEndHandManager.isEnabled5()) {
         float factor4 = 1.0F - (this.factor + (this.factor2 - this.factor) * factor3);
         EntityPlayerSP player = this.mc.thePlayer;
         float factor5 = player.computeFactorByFloat(factor3);
         float factor6 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * factor3;
         float factor7 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * factor3;
         this.applyFloatByFloat(factor6, factor7);
         this.applyAbstractClientPlayer(player);
         this.applyEntityPlayerSP(player, factor3);
         GlStateManager.disableLighting();
         GlStateManager.pushMatrix();
         if (((Module)ClientGlobals.moduleManager.customItemPos).isToggled() && ClientGlobals.moduleManager.customItemPos.settingLeftHand.isEnabled()) {
            GlStateManager.translate3d(-1.0, 1.0, 1.0);
            GlStateManager.disableAlpha();
         }

         if (this.itemStack != null) {
            EventBoundingBox event = EventBoundingBoxCallback.resolveEventBoundingBox(0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0);
            boolean flag = player.getEntityId2() > 0;
            ItemStack stack = this.mc.thePlayer.getHeldItem();
            if (stack != null
               && ((Module)ClientGlobals.moduleManager.blockAnimation).isToggled()
               && ClientGlobals.moduleManager.blockAnimation.settingFakeBlock.isEnabled()
               && (
                  !ClientGlobals.moduleManager.blockAnimation.settingOnlyWhileKillAura.isEnabled()
                     || ((Module)ClientGlobals.moduleManager.killAura).isToggled() && ClientGlobals.moduleManager.killAura.target != null
               )
               && factor5 > 0.0F
               && stack.getItem() instanceof ItemSword) {
               flag = true;
            }

            GlStateManager.translate3d2(event.getValue2(), event.getValue4(), event.getValue7());
            if (this.itemStack.getItem() instanceof ItemFilledMap) {
               this.applyAbstractClientPlayerByAbstractClientPl(player, factor6, factor4, factor5);
            } else if (flag) {
               GlStateManager.translate3d2(-event.getValue2(), -event.getValue4(), -event.getValue7());
               GlStateManager.translate3d2(-event.getValue3(), -event.getValue5(), -event.getValue());
               ItemNoneType itemNoneType = this.itemStack.processAction4();
               flowBlockD:
               switch (CurveSelector.countArray[itemNoneType.ordinal()]) {
                  case 1:
                     this.applyFloat(factor4, 0.0F);
                     break;
                  case 2:
                  case 3:
                     this.applyAbstractClientPlayer(player, factor3);
                     this.applyFloat(factor4, 0.0F);
                     break;
                  case 4:
                     if (((Module)ClientGlobals.moduleManager.blockAnimation).isToggled()) {
                        GlStateManager.translate3d2(0.0, ClientGlobals.moduleManager.blockAnimation.settingHeight.getValue(), 0.0);
                     }

                     if (((Module)ClientGlobals.moduleManager.blockAnimation).isToggled()) {
                        flowBlockC: {
                           flowBlockB: {
                              flowBlockA: {
                                 scopeBlock: {
                                    tryBlock: {
                                       branchBlock: {
                                          scanBlock: {
                                             evalBlock: {
                                                checkBlock: {
                                                   guardBlock: {
                                                      searchBlock: {
                                                         String selected = ClientGlobals.moduleManager.blockAnimation.settingBlockAnimations.getSelected();
                                                         byte byteValue = -1;
                                                         switch (selected.hashCode()) {
                                                            case -2076258303:
                                                               if (selected.equals("Jigsaw")) {
                                                                  break evalBlock;
                                                               }
                                                               break;
                                                            case -1704090549:
                                                               if (selected.equals("Wiggle")) {
                                                                  break flowBlockB;
                                                               }
                                                               break;
                                                            case -352259601:
                                                               if (selected.equals("Exhibition")) {
                                                                  break branchBlock;
                                                               }
                                                               break;
                                                            case 48570:
                                                               if (selected.equals("1.7")) {
                                                                  break flowBlockC;
                                                               }
                                                               break;
                                                            case 2583650:
                                                               if (selected.equals("Spin")) {
                                                                  byteValue = 11;
                                                               }
                                                               break;
                                                            case 71456692:
                                                               if (selected.equals("Jello")) {
                                                                  break tryBlock;
                                                               }
                                                               break;
                                                            case 79882757:
                                                               if (selected.equals("Sigma")) {
                                                                  break checkBlock;
                                                               }
                                                               break;
                                                            case 80307556:
                                                               if (selected.equals("Swong")) {
                                                                  break scopeBlock;
                                                               }
                                                               break;
                                                            case 776064579:
                                                               if (selected.equals("Minimalistic")) {
                                                                  break flowBlockA;
                                                               }
                                                               break;
                                                            case 987507365:
                                                               if (selected.equals("Forward")) {
                                                                  break scanBlock;
                                                               }
                                                               break;
                                                            case 1537133243:
                                                               if (selected.equals("Violence")) {
                                                                  break searchBlock;
                                                               }
                                                               break;
                                                            case 1964854307:
                                                               if (selected.equals("Exhibition2")) {
                                                                  break guardBlock;
                                                               }
                                                         }

                                                         switch (byteValue) {
                                                            case 0:
                                                               break flowBlockC;
                                                            case 1:
                                                               break flowBlockB;
                                                            case 2:
                                                               break flowBlockA;
                                                            case 3:
                                                               break scopeBlock;
                                                            case 4:
                                                               break tryBlock;
                                                            case 5:
                                                               break branchBlock;
                                                            case 6:
                                                               break scanBlock;
                                                            case 7:
                                                               break evalBlock;
                                                            case 8:
                                                               break;
                                                            case 9:
                                                               break checkBlock;
                                                            case 10:
                                                               break guardBlock;
                                                            case 11:
                                                               this.applyFloat(factor4, 0.0F);
                                                               this.updateState4();
                                                               GlStateManager.translate3d2(-0.2, 1.0, 0.5);
                                                               GlStateManager.rotate(90.0F, 3.0F, 1.0F, 0.0F);
                                                               GlStateManager.rotate(
                                                                  (float)Math.toDegrees((double)System.currentTimeMillis() / 200.0 % (Math.PI * 2)),
                                                                  1.0F,
                                                                  0.0F,
                                                                  1.0F
                                                               );
                                                            default:
                                                               this.applyFloat(factor4, 0.0F);
                                                               this.updateState4();
                                                               break flowBlockD;
                                                         }
                                                      }

                                                      this.applyFloat(0.0F, factor5);
                                                      this.updateState4();
                                                      GlStateManager.translate(-0.2F, 0.2F, -0.5F);
                                                      break checkBlock;
                                                   }

                                                   float factor8 = MathHelper.abs(MathHelper.sin(factor5) * (float) Math.PI);
                                                   this.applyFloat(factor4 / 2.5F, 0.0F);
                                                   GL11.glTranslatef(0.1F, 0.4F, -0.1F);
                                                   GL11.glRotated((double)(-factor8 * 20.0F), (double)(factor8 / 2.0F), 0.0, 9.0);
                                                   GL11.glRotated((double)(-factor8 * 50.0F), 0.8F, (double)(factor8 / 2.0F), 0.0);
                                                   this.updateState4();
                                                   break;
                                                }

                                                float factor9 = MathHelper.abs(factor5 * factor5 * (float) Math.PI);
                                                this.applyFloat(factor4 * 0.5F, 0.0F);
                                                GlStateManager.rotate(-factor9 * 55.0F / 2.0F, -8.0F, -0.0F, 9.0F);
                                                GlStateManager.rotate(-factor9 * 45.0F, 1.0F, factor9 / 2.0F, -0.0F);
                                                this.updateState4();
                                                GL11.glTranslated(1.2, 0.3, 0.5);
                                                GL11.glTranslatef(-1.0F, -0.2F, 0.2F);
                                                break;
                                             }

                                             this.applyFloat(factor4, factor5 + factor4);
                                             this.updateState4();
                                             GlStateManager.translate3d2(0.1, 0.0, 0.0);
                                             break;
                                          }

                                          this.applyFloat(factor4 / 3.0F, 0.0F);
                                          GlStateManager.translate3d2(0.0, 0.3, 0.0);
                                          float factor10 = MathHelper.abs(MathHelper.sin(factor5) * (float) Math.PI);
                                          GlStateManager.rotate(-factor10 * 36.0F, 2.09F, 0.0F, 0.0F);
                                          GlStateManager.rotate(-factor10 * 25.0F, 2.09F, 0.0F, 15.0F);
                                          GlStateManager.rotate(-factor10 * 16.0F, 12.09F, 3.0F, 0.0F);
                                          GlStateManager.rotate(5.0F, 0.0F, -2.0F, 12.0F);
                                          this.updateState4();
                                          break;
                                       }

                                       this.applyFloat(factor4 / 3.0F, 0.0F);
                                       float factor11 = MathHelper.abs(MathHelper.sin(factor5) * (float) Math.PI);
                                       GlStateManager.rotate(-factor11 * 36.0F, 2.09F, 0.0F, 0.0F);
                                       GlStateManager.rotate(-factor11 * 25.0F, 2.09F, 0.0F, 15.0F);
                                       GlStateManager.translate3d2(0.0, 0.3, 0.0);
                                       this.updateState4();
                                       break;
                                    }

                                    float factor12 = MathHelper.abs(MathHelper.sin(factor5) * (float) Math.PI);
                                    this.applyFloat(factor4 / 3.0F, 0.0F);
                                    GlStateManager.translate3f3(0.8F, 0.8F, 0.8F);
                                    GlStateManager.translate(0.0F, 0.1F, 0.0F);
                                    this.updateState4();
                                    GlStateManager.rotate(factor12 * 35.0F / 2.0F, 0.0F, 1.0F, 1.5F);
                                    GlStateManager.rotate(-factor12 * 190.0F / 4.0F, 1.0F, 0.5F, 0.0F);
                                    break;
                                 }

                                 this.applyFloat(factor4 / 3.0F, 0.0F);
                                 float factor13 = MathHelper.abs((float)((double)MathHelper.sin(factor5) * Math.PI));
                                 GL11.glRotatef(-factor13 * 80.0F / 5.0F, factor13 / 3.0F, -0.0F, 9.0F);
                                 GL11.glRotatef(-factor13 * 40.0F, 8.0F, factor13 / 9.0F, -0.1F);
                                 this.updateState4();
                                 break;
                              }

                              float factor14 = MathHelper.abs(MathHelper.sin(factor5) * (float) Math.PI);
                              GlStateManager.translate(0.0F, 0.0F, -factor14 / 5.0F);
                              this.applyFloat(factor4 / 3.0F, 0.0F);
                              this.updateState4();
                              break;
                           }

                           this.applyFloat(factor4 / 3.0F, 0.0F);
                           GlStateManager.rotate(-MathHelper.abs(MathHelper.sin(factor5) * (float) Math.PI) * 45.0F, 2.09F, 0.0F, 0.0F);
                           this.updateState4();
                           break;
                        }

                        this.applyFloat(factor4, factor5);
                        this.updateState4();
                     } else {
                        this.applyFloat(factor4, 0.0F);
                        this.updateState4();
                     }
                     break;
                  case 5:
                     this.applyFloat(factor4, 0.0F);
                     this.applyAbstractClientPlayer(factor3, player);
               }
            } else {
               this.applyFloat4(((Module)ClientGlobals.moduleManager.swingAnimation).isToggled() ? 0.0F : factor5);
               this.applyFloat(factor4, factor5);
            }

            GlStateManager.translate3d(event.getValue6(), event.getValue6(), event.getValue6());
            this.applyEntityLivingBaseByEntityLivingBase(player, this.itemStack, ItemPhysicsNoneType.FIRST_PERSON);
         } else if (!player.isActive12()) {
            this.applyAbstractClientPlayerByAbstractClientPl(player, factor4, factor5);
         }

         GlStateManager.popMatrix();
         GlStateManager.applyState8();
         RenderHelper.disableStandardItemLighting();
      }
   }

   private void applyFloat4(float factor3) {
      float factor4 = -0.4F * MathHelper.abs(MathHelper.sin(factor3) * (float) Math.PI);
      float factor5 = 0.2F * MathHelper.abs(MathHelper.sin(factor3) * (float) Math.PI * 2.0F);
      float factor6 = -0.2F * MathHelper.abs(factor3 * (float) Math.PI);
      GlStateManager.translate(factor4, factor5, factor6);
   }

   private float computeFactor(float factor3) {
      return -MathHelper.cos(MathHelper.clamp_float(1.0F - factor3 / 45.0F + 0.1F, 0.0F, 1.0F) * (float) Math.PI) * 0.5F + 0.5F;
   }

   private void applyRenderPlayerByRenderPlayer(RenderPlayer renderPlayer) {
      GlStateManager.pushMatrix();
      GlStateManager.rotate(54.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(64.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(-62.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.translate(0.25F, -0.85F, 0.75F);
      renderPlayer.applyAbstractClientPlayer(this.mc.thePlayer);
      GlStateManager.popMatrix();
   }

   public void updateState2() {
      this.factor2 = 0.0F;
   }

   private void applyEntityPlayerSP(EntityPlayerSP player, float factor3) {
      float factor4 = player.scaleFactor32 + (player.scaleFactor61 - player.scaleFactor32) * factor3;
      float factor5 = player.scaleFactor14 + (player.scaleFactor9 - player.scaleFactor14) * factor3;
      GlStateManager.rotate((player.rotationPitch - factor4) * 0.1F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate((player.rotationYaw - factor5) * 0.1F, 0.0F, 1.0F, 0.0F);
   }

   private void applyFloatByFloat(float factor3, float factor4) {
      GlStateManager.pushMatrix();
      GlStateManager.rotate(factor3, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(factor4, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.popMatrix();
   }

   public void applyFloat2(float factor3) {
      GlStateManager.applyState10();
      if (this.mc.thePlayer.isActive15()) {
         IBlockState blockState = this.mc.theWorld.getBlockState(new BlockPos(this.mc.thePlayer));
         new BlockPos(this.mc.thePlayer);
         EntityPlayerSP player = this.mc.thePlayer;

         for (int index = 0; index < 8; index++) {
            double offset = player.posX + (double)(((float)((index >> 0) % 2) - 0.5F) * player.width * 0.8F);
            double offset2 = player.posY + (double)(((float)((index >> 1) % 2) - 0.5F) * 0.1F);
            double offset3 = player.posZ + (double)(((float)((index >> 2) % 2) - 0.5F) * player.width * 0.8F);
            BlockPos blockPos = new BlockPos(offset, offset2 + (double)player.getHeight2(), offset3);
            IBlockState blockState2 = this.mc.theWorld.getBlockState(blockPos);
            if (blockState2.getBlock().isActive3()) {
               blockState = blockState2;
            }
         }

         if (blockState.getBlock().processAction13() != -1) {
            this.applyServiceStringEntry(factor3, this.mc.getGoldenCrystal().getCommonMapEntry().resolveServiceStringEntry(blockState));
         }
      }

      if (!this.mc.thePlayer.isActive23()) {
         if (this.mc.thePlayer.matchesMaterial(Material.coreItemEntry1)) {
            this.applyFloat(factor3);
         }

         if (this.mc.thePlayer.canExecute2()) {
            this.applyFloat3(factor3);
         }
      }

      GlStateManager.applyState14();
   }

   private void applyFloat3(float factor3) {
      if (!((Module)ClientGlobals.moduleManager.customFireHeight).isToggled() || !ClientGlobals.moduleManager.customFireHeight.settingRemoveFire.isEnabled()) {
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 0.9F);
         GlStateManager.glDepthFunc(519);
         GlStateManager.depthMask(false);
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         float factor4 = 1.0F;
         float factor5 = ((Module)ClientGlobals.moduleManager.customFireHeight).isToggled()
            ? (float)ClientGlobals.moduleManager.customFireHeight.settingFireHeight.getValue()
            : -0.3F;

         for (int index = 0; index < 2; index++) {
            GlStateManager.pushMatrix();
            ServiceStringEntry serviceStringEntry = this.mc.getListenerAdapter27().get("minecraft:blocks/fire_layer_1");
            this.mc.getTextureManager().applyResourceLocation3(ListenerAdapter27.resourceLocation);
            float factor6 = serviceStringEntry.getFactor5();
            float factor7 = serviceStringEntry.getFactor2();
            float factor8 = serviceStringEntry.getFactor();
            float factor9 = serviceStringEntry.getFactor6();
            float factor10 = (0.0F - factor4) / 2.0F;
            float factor11 = factor10 + factor4;
            float factor12 = 0.0F - factor4 / 2.0F;
            float factor13 = factor12 + factor4;
            float factor14 = -0.5F;
            GlStateManager.translate((float)(-(index * 2 - 1)) * 0.24F, factor5, 0.0F);
            GlStateManager.rotate((float)(index * 2 - 1) * 10.0F, 0.0F, 1.0F, 0.0F);
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
            worldRenderer.applyServiceStringEntryByServiceStringEnt(serviceStringEntry);
            worldRenderer.getWorldRenderer((double)factor10, (double)factor12, (double)factor14).getWorldRenderer6((double)factor7, (double)factor9).updateState();
            worldRenderer.getWorldRenderer((double)factor11, (double)factor12, (double)factor14).getWorldRenderer6((double)factor6, (double)factor9).updateState();
            worldRenderer.getWorldRenderer((double)factor11, (double)factor13, (double)factor14).getWorldRenderer6((double)factor6, (double)factor8).updateState();
            worldRenderer.getWorldRenderer((double)factor10, (double)factor13, (double)factor14).getWorldRenderer6((double)factor7, (double)factor8).updateState();
            tessellator.updateState();
            GlStateManager.popMatrix();
         }

         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableBlend();
         GlStateManager.depthMask(true);
         GlStateManager.glDepthFunc(515);
      }
   }

   private void applyAbstractClientPlayer(AbstractClientPlayer player, float factor3) {
      float factor4 = (float)player.getEntityId2() - factor3 + 1.0F;
      float factor5 = factor4 / (float)this.itemStack.processAction6();
      float factor6 = MathHelper.computeFactor(MathHelper.cos(factor4 / 4.0F * (float) Math.PI) * 0.1F);
      if (factor5 >= 0.8F) {
         factor6 = 0.0F;
      }

      GlStateManager.translate(0.0F, factor6, 0.0F);
      float factor7 = 1.0F - (float)Math.pow((double)factor5, 27.0);
      GlStateManager.translate(factor7 * 0.6F, factor7 * -0.5F, factor7 * 0.0F);
      GlStateManager.rotate(factor7 * 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(factor7 * 10.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(factor7 * 30.0F, 0.0F, 0.0F, 1.0F);
   }

   private boolean matchesBlock(Block block) {
      return block != null && block.processAction3() == BlockSolidType.TRANSLUCENT;
   }

   private void applyAbstractClientPlayer2(AbstractClientPlayer player) {
      this.mc.getTextureManager().applyResourceLocation3(player.getResourceLocation2());
      RenderPlayer renderPlayer = (RenderPlayer)this.renderManager.resolveEntitySkinLocation(this.mc.thePlayer);
      if (!player.isActive12()) {
         GlStateManager.disableAlpha();
         this.applyRenderPlayerByRenderPlayer(renderPlayer);
         this.applyRenderPlayer(renderPlayer);
         GlStateManager.enableLighting();
      }
   }

   private void applyAbstractClientPlayer(AbstractClientPlayer player) {
      int count2 = this.mc
         .theWorld
         .computeCount(
            MathHelper.ceiling_double_int(player.posX),
            MathHelper.ceiling_double_int(player.posY + (double)player.getHeight2()),
            MathHelper.ceiling_double_int(player.posZ),
            0
         );
      if (BuildManager.isActive28()) {
         count2 = TargetEstimator.computeCount(this.mc.getRenderViewEntity(), count2);
      }

      float factor3 = (float)(count2 & 65535);
      float factor4 = (float)(count2 >> 16);
      OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, factor3, factor4);
   }

   private void applyFloat(float factor3) {
      if (!BuildManager.canExecute() || PreEndHandManager.isActive7()) {
         this.mc.getTextureManager().applyResourceLocation3(resourceLocation);
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         float factor4 = this.mc.thePlayer.computeFactor(factor3);
         GlStateManager.color4f2(factor4, factor4, factor4, 0.5F);
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.pushMatrix();
         float factor5 = -this.mc.thePlayer.rotationYaw / 64.0F;
         float factor6 = this.mc.thePlayer.rotationPitch / 64.0F;
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
         worldRenderer.getWorldRenderer(-1.0, -1.0, -0.5).getWorldRenderer6((double)(4.0F + factor5), (double)(4.0F + factor6)).updateState();
         worldRenderer.getWorldRenderer(1.0, -1.0, -0.5).getWorldRenderer6((double)(0.0F + factor5), (double)(4.0F + factor6)).updateState();
         worldRenderer.getWorldRenderer(1.0, 1.0, -0.5).getWorldRenderer6((double)(0.0F + factor5), (double)(0.0F + factor6)).updateState();
         worldRenderer.getWorldRenderer(-1.0, 1.0, -0.5).getWorldRenderer6((double)(4.0F + factor5), (double)(0.0F + factor6)).updateState();
         tessellator.updateState();
         GlStateManager.popMatrix();
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableBlend();
      }
   }

   public void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, ItemStack stack, ItemPhysicsNoneType itemPhysicsNoneType) {
      if (stack != null) {
         Block block = Block.resolveBlock(stack.getItem());
         GlStateManager.pushMatrix();
         if (this.renderItem.matchesItemStack(stack)) {
            GlStateManager.translate3f3(2.0F, 2.0F, 2.0F);
            if (this.matchesBlock(block) && (!BuildManager.canExecute() || !PreEndHandManager.enabled38)) {
               GlStateManager.depthMask(false);
            }
         }

         this.renderItem.applyItemStack(stack, livingEntity, itemPhysicsNoneType);
         if (this.matchesBlock(block)) {
            GlStateManager.depthMask(true);
         }

         GlStateManager.popMatrix();
      }
   }

   public ItemRenderer(Minecraft mc2) {
      this.mc = mc2;
      this.renderManager = mc2.getRenderManager();
      this.renderItem = mc2.getRenderItem();
   }
}
