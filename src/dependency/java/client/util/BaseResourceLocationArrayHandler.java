package client.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.DiamondGem;
import net.minecraft.item.ItemEntryRegistry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class BaseResourceLocationArrayHandler extends ResourceLocationArrayHolder {
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/skeleton/skeleton.png");
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/entity/zombie/zombie.png");
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/creeper/creeper.png");
   public static BaseResourceLocationArrayHandler baseResourceLocationArrayHandler;
   public DiamondGem diamondGem2 = new DiamondGem(0, 0, 64, 32);
   public DiamondGem diamondGem = new ItemEntryRegistry();

   @Override
   public void applyTileEntity(TileEntity tileEntity, double posX, double posY, double posZ, float factor, int count) {
      this.applyTileEntitySkull((TileEntitySkull)tileEntity, posX, posY, posZ, factor, count);
   }

   public void applyTileEntitySkull(TileEntitySkull tileEntitySkull, double posX, double posY, double posZ, float factor, int count) {
      EnumFacing enumFacing = EnumFacing.getEnumFacing6(tileEntitySkull.getCount() & 7);
      tileEntitySkull.setResourceLocation(
         this.resolveResourceLocation(
            (float)posX,
            (float)posY,
            (float)posZ,
            enumFacing,
            (float)(tileEntitySkull.getCount3() * 360) / 16.0F,
            tileEntitySkull.getCount2(),
            tileEntitySkull.getGameProfile(),
            count,
            tileEntitySkull.getResourceLocation()
         )
      );
   }

   public ResourceLocation resolveResourceLocation(
      float posX, float posY, float posZ, EnumFacing enumFacing, float factor, int count, GameProfile gameProfile, int count2, ResourceLocation location
   ) {
      float factor2 = factor;
      DiamondGem diamondGem3 = this.diamondGem2;
      ResourceLocation location2 = null;
      if (count2 >= 0) {
         this.applyResourceLocation2(resourceLocationArray[count2]);
         GlStateManager.matrixMode(5890);
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(4.0F, 2.0F, 1.0F);
         GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
         GlStateManager.matrixMode(5888);
      } else {
         switch (count) {
            case 0:
            default:
               this.applyResourceLocation2(resourceLocation4);
               break;
            case 1:
               this.applyResourceLocation2(resourceLocation2);
               break;
            case 2:
               this.applyResourceLocation2(resourceLocation5);
               diamondGem3 = this.diamondGem;
               break;
            case 3:
               try {
                  diamondGem3 = this.diamondGem;
                  location2 = SlimManager.getResourceLocation();
                  if (gameProfile != null) {
                     Minecraft mc = Minecraft.getMinecraft();
                     Map unchecked = mc.getSkinsManager().getUnchecked(gameProfile);
                     if (unchecked.containsKey(Type.SKIN)) {
                        if (location == null) {
                           location2 = mc.getSkinsManager().resolveResourceLocationByMinecraftProfile((MinecraftProfileTexture)unchecked.get(Type.SKIN), Type.SKIN);
                        } else {
                           location2 = location;
                        }
                     } else {
                        location2 = SlimManager.resolveResourceLocationByUUID(EntityPlayer.resolveUUID(gameProfile));
                     }
                  }

                  this.applyResourceLocation2(location2);
               } catch (Exception exception) {
               }
               break;
            case 4:
               this.applyResourceLocation2(resourceLocation3);
         }
      }

      GlStateManager.pushMatrix();
      GlStateManager.disableAlpha();
      if (enumFacing != EnumFacing.UP) {
         switch (GeometryWriter.countArray[enumFacing.ordinal()]) {
            case 1:
               GlStateManager.translate(posX + 0.5F, posY + 0.25F, posZ + 0.74F);
               break;
            case 2:
               GlStateManager.translate(posX + 0.5F, posY + 0.25F, posZ + 0.26F);
               factor2 = 180.0F;
               break;
            case 3:
               GlStateManager.translate(posX + 0.74F, posY + 0.25F, posZ + 0.5F);
               factor2 = 270.0F;
               break;
            case 4:
            default:
               GlStateManager.translate(posX + 0.26F, posY + 0.25F, posZ + 0.5F);
               factor2 = 90.0F;
         }
      } else {
         GlStateManager.translate(posX + 0.5F, posY, posZ + 0.5F);
      }

      float factor3 = 0.0625F;
      GlStateManager.disableLighting();
      GlStateManager.translate3f3(-1.0F, -1.0F, 1.0F);
      GlStateManager.applyState14();
      diamondGem3.applyEntity((Entity)null, 0.0F, 0.0F, 0.0F, factor2, 0.0F, factor3, true);
      GlStateManager.popMatrix();
      if (count2 >= 0) {
         GlStateManager.matrixMode(5890);
         GlStateManager.popMatrix();
         GlStateManager.matrixMode(5888);
      }

      return location2;
   }

   @Override
   public void setStandardMapEntry(StandardMapEntry standardMapEntry) {
      super.setStandardMapEntry(standardMapEntry);
      baseResourceLocationArrayHandler = this;
   }
}
