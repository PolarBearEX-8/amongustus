package client.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class TertiaryResourceLocationArrayHandler extends ResourceLocationArrayHolder {
   public static final Map map = Maps.newHashMap();
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/entity/banner_base.png");
   public InternalFloatEntryAdapter internalFloatEntryAdapter = new InternalFloatEntryAdapter();

   public void applyTileEntityBanner(TileEntityBanner tileEntityBanner, double posX, double posY, double posZ, float factor, int count) {
      boolean flag = tileEntityBanner.getWorld() != null;
      boolean flag2 = !flag || tileEntityBanner.getBlock() == Blocks.block138;
      int count2 = flag ? tileEntityBanner.getCount() : 0;
      long value = flag ? tileEntityBanner.getWorld().getTimestamp2() : 0L;
      GlStateManager.pushMatrix();
      float factor2 = 0.6666667F;
      if (flag2) {
         GlStateManager.translate((float)posX + 0.5F, (float)posY + 0.75F * factor2, (float)posZ + 0.5F);
         GlStateManager.rotate(-((float)(count2 * 360) / 16.0F), 0.0F, 1.0F, 0.0F);
         this.internalFloatEntryAdapter.boxesManager3.enabled3 = true;
      } else {
         float factor3 = 0.0F;
         if (count2 == 2) {
            factor3 = 180.0F;
         }

         if (count2 == 4) {
            factor3 = 90.0F;
         }

         if (count2 == 5) {
            factor3 = -90.0F;
         }

         GlStateManager.translate((float)posX + 0.5F, (float)posY - 0.25F * factor2, (float)posZ + 0.5F);
         GlStateManager.rotate(-factor3, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(0.0F, -0.3125F, -0.4375F);
         this.internalFloatEntryAdapter.boxesManager3.enabled3 = false;
      }

      BlockPos blockPos = tileEntityBanner.getBlockPos();
      float factor4 = (float)(blockPos.getX() * 7 + blockPos.getY() * 9 + blockPos.getZ() * 13) + (float)value + factor;
      this.internalFloatEntryAdapter.boxesManager2.factor = (-0.0125F + 0.01F * MathHelper.cos(factor4 * (float) Math.PI * 0.02F)) * (float) Math.PI;
      GlStateManager.disableLighting();
      ResourceLocation location = this.resolveResourceLocation(tileEntityBanner);
      if (location != null) {
         this.applyResourceLocation2(location);
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(factor2, -factor2, -factor2);
         this.internalFloatEntryAdapter.updateState();
         GlStateManager.popMatrix();
      }

      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }

   @Override
   public void applyTileEntity(TileEntity tileEntity, double posX, double posY, double posZ, float factor, int count) {
      this.applyTileEntityBanner((TileEntityBanner)tileEntity, posX, posY, posZ, factor, count);
   }

   private ResourceLocation resolveResourceLocation(TileEntityBanner tileEntityBanner) {
      String text = tileEntityBanner.getText();
      if (text.isEmpty()) {
         return null;
      } else {
         DampenerConverter dampenerConverter = (DampenerConverter)map.get(text);
         if (dampenerConverter == null) {
            if (map.size() >= 256) {
               long timestamp = System.currentTimeMillis();
               Iterator iterator = map.keySet().iterator();

               while (iterator.hasNext()) {
                  String text2 = (String)iterator.next();
                  DampenerConverter dampenerConverter2 = (DampenerConverter)map.get(text2);
                  if (timestamp - dampenerConverter2.timestamp > 60000L) {
                     Minecraft.getMinecraft().getTextureManager().applyResourceLocation2(dampenerConverter2.resourceLocation);
                     iterator.remove();
                  }
               }

               if (map.size() >= 256) {
                  return null;
               }
            }

            List list = tileEntityBanner.getList();
            List listAsList = tileEntityBanner.getListAsList();
            ArrayList list2 = Lists.newArrayList();

            for (AngleAccumulator angleAccumulator : (Iterable<AngleAccumulator>)(Iterable<?>)list) {
               list2.add("textures/entity/banner/" + angleAccumulator.getLabelText() + ".png");
            }

            dampenerConverter = new DampenerConverter(null);
            dampenerConverter.resourceLocation = new ResourceLocation(text);
            Minecraft.getMinecraft().getTextureManager().matchesResourceLocation(dampenerConverter.resourceLocation, new SmoothCoordinator(resourceLocation2, list2, listAsList));
            map.put(text, dampenerConverter);
         }

         dampenerConverter.timestamp = System.currentTimeMillis();
         return dampenerConverter.resourceLocation;
      }
   }
}
