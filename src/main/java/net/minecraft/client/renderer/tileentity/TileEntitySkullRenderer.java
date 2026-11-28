package net.minecraft.client.renderer.tileentity;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHumanoidHead;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TileEntitySkullRenderer extends TileEntitySpecialRenderer {
   private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");
   private static final ResourceLocation WITHER_SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
   private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");
   private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation("textures/entity/creeper/creeper.png");
   public static TileEntitySkullRenderer instance;
   public ModelSkeletonHead skeletonHead = new ModelSkeletonHead(0, 0, 64, 32);
   public ModelSkeletonHead humanoidHead = new ModelHumanoidHead();

   public void renderTileEntityAt(TileEntitySkull tileEntitySkull, double d, double d2, double d3, float f, int n) {
      tileEntitySkull.setSkinLocation(
         this.renderSkull(
            (float)d,
            (float)d2,
            (float)d3,
            EnumFacing.getFront(tileEntitySkull.getBlockMetadata() & 7),
            (float)(tileEntitySkull.getSkullRotation() * 360) / 16.0F,
            tileEntitySkull.getSkullType(),
            tileEntitySkull.getPlayerProfile(),
            n,
            tileEntitySkull.getSkinLocation()
         )
      );
   }

   @Override
   public void setRendererDispatcher(TileEntityRendererDispatcher dispatcher) {
      super.setRendererDispatcher(dispatcher);
      instance = this;
   }

   public ResourceLocation renderSkull(float f, float f2, float f3, EnumFacing facing, float f4, int n, GameProfile profile, int n2, ResourceLocation location) {
      ModelSkeletonHead skeletonHead2 = this.skeletonHead;
      ResourceLocation location2 = null;
      if (n2 >= 0) {
         this.bindTexture(DESTROY_STAGES[n2]);
         GlStateManager.matrixMode(5890);
         GlStateManager.pushMatrix();
         GlStateManager.scale(4.0F, 2.0F, 1.0F);
         GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
         GlStateManager.matrixMode(5888);
      } else {
         switch (n) {
            case 0:
            default:
               this.bindTexture(SKELETON_TEXTURES);
               break;
            case 1:
               this.bindTexture(WITHER_SKELETON_TEXTURES);
               break;
            case 2:
               this.bindTexture(ZOMBIE_TEXTURES);
               skeletonHead2 = this.humanoidHead;
               break;
            case 3:
               try {
                  skeletonHead2 = this.humanoidHead;
                  location2 = DefaultPlayerSkin.getDefaultSkinLegacy();
                  if (profile != null) {
                     Minecraft minecraft = Minecraft.getMinecraft();
                     Map map = minecraft.getSkinManager().loadSkinFromCache(profile);
                     if (map.containsKey(Type.SKIN)) {
                        if (location == null) {
                           location2 = minecraft.getSkinManager().loadSkin((MinecraftProfileTexture)map.get(Type.SKIN), Type.SKIN);
                        } else {
                           location2 = location;
                        }
                     } else {
                        location2 = DefaultPlayerSkin.getDefaultSkin(EntityPlayer.getUUID(profile));
                     }
                  }

                  this.bindTexture(location2);
               } catch (Exception e) {
               }
               break;
            case 4:
               this.bindTexture(CREEPER_TEXTURES);
         }
      }

      GlStateManager.pushMatrix();
      GlStateManager.disableCull();
      if (facing != EnumFacing.UP) {
         switch (facing) {
            case NORTH:
               GlStateManager.translate(f + 0.5F, f2 + 0.25F, f3 + 0.74F);
               break;
            case SOUTH:
               GlStateManager.translate(f + 0.5F, f2 + 0.25F, f3 + 0.26F);
               f4 = 180.0F;
               break;
            case WEST:
               GlStateManager.translate(f + 0.74F, f2 + 0.25F, f3 + 0.5F);
               f4 = 270.0F;
               break;
            case EAST:
            default:
               GlStateManager.translate(f + 0.26F, f2 + 0.25F, f3 + 0.5F);
               f4 = 90.0F;
         }
      } else {
         GlStateManager.translate(f + 0.5F, f2, f3 + 0.5F);
      }

      float f5 = 0.0625F;
      GlStateManager.enableRescaleNormal();
      GlStateManager.scale(-1.0F, -1.0F, 1.0F);
      GlStateManager.enableAlpha();
      skeletonHead2.render((Entity)null, 0.0F, 0.0F, 0.0F, f4, 0.0F, f5, true);
      GlStateManager.popMatrix();
      if (n2 >= 0) {
         GlStateManager.matrixMode(5890);
         GlStateManager.popMatrix();
         GlStateManager.matrixMode(5888);
      }

      return location2;
   }

   @Override
   public void renderTileEntityAt(TileEntity tileEntity, double d, double d2, double d3, float f, int n) {
      this.renderTileEntityAt((TileEntitySkull)tileEntity, d, d2, d3, f, n);
   }

}
