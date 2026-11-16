package client.util;

import client.render.WorldRenderer;
import client.setting.RenderBooleanProperty;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;

public class DampenerService {
   public void applyWorldRenderer(WorldRenderer worldRenderer) {
      if (worldRenderer.getCount3() > 0) {
         if (worldRenderer.getCountAsIndex() == 7 && BuildManager.isActive78()) {
            worldRenderer.renderElement2();
         }

         AuxiliaryLoggerEntry auxiliaryLoggerEntry = worldRenderer.getAuxiliaryLoggerEntry();
         int count = auxiliaryLoggerEntry.getCount2();
         ByteBuffer byteBuffer = worldRenderer.getByteBuffer();
         List list = auxiliaryLoggerEntry.getList();

         for (int index = 0; index < list.size(); index++) {
            AltLoggerEntry altLoggerEntry = (AltLoggerEntry)list.get(index);
            RenderBooleanProperty renderBooleanProperty = altLoggerEntry.getRenderBooleanProperty();
            int count2 = altLoggerEntry.getCombatValueProperty().getCount2();
            int count3 = altLoggerEntry.getCount();
            ((Buffer)byteBuffer).position(auxiliaryLoggerEntry.getIntByIndex(index));
            switch (HandlerIntArrayEntry.countArray[renderBooleanProperty.ordinal()]) {
               case 1:
                  GL11.glVertexPointer(altLoggerEntry.getCount2(), count2, count, byteBuffer);
                  GL11.glEnableClientState(32884);
                  break;
               case 2:
                  OpenGlHelper.applyIndex4(OpenGlHelper.lightmapTexUnit + count3);
                  GL11.glTexCoordPointer(altLoggerEntry.getCount2(), count2, count, byteBuffer);
                  GL11.glEnableClientState(32888);
                  OpenGlHelper.applyIndex4(OpenGlHelper.lightmapTexUnit);
                  break;
               case 3:
                  GL11.glColorPointer(altLoggerEntry.getCount2(), count2, count, byteBuffer);
                  GL11.glEnableClientState(32886);
                  break;
               case 4:
                  GL11.glNormalPointer(count2, count, byteBuffer);
                  GL11.glEnableClientState(32885);
            }
         }

         if (worldRenderer.isActive()) {
            worldRenderer.renderElement();
         } else if (BuildManager.canExecute()) {
            InternalLongArrayEntry.applyWorldRenderer(worldRenderer.getCountAsIndex(), 0, worldRenderer.getCount3(), worldRenderer);
         } else {
            GL11.glDrawArrays(worldRenderer.getCountAsIndex(), 0, worldRenderer.getCount3());
         }

         int count4 = 0;

         for (int index2 = list.size(); count4 < index2; count4++) {
            AltLoggerEntry altLoggerEntry2 = (AltLoggerEntry)list.get(count4);
            RenderBooleanProperty renderBooleanProperty2 = altLoggerEntry2.getRenderBooleanProperty();
            int count5 = altLoggerEntry2.getCount();
            switch (HandlerIntArrayEntry.countArray[renderBooleanProperty2.ordinal()]) {
               case 1:
                  GL11.glDisableClientState(32884);
                  break;
               case 2:
                  OpenGlHelper.applyIndex4(OpenGlHelper.lightmapTexUnit + count5);
                  GL11.glDisableClientState(32888);
                  OpenGlHelper.applyIndex4(OpenGlHelper.lightmapTexUnit);
                  break;
               case 3:
                  GL11.glDisableClientState(32886);
                  GlStateManager.applyState();
                  break;
               case 4:
                  GL11.glDisableClientState(32885);
            }
         }
      }

      worldRenderer.renderElement5();
   }
}
