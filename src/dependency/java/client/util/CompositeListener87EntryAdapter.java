package client.util;

import client.setting.NetHandlerPlayClientXType;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class CompositeListener87EntryAdapter extends Listener87Entry {
   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 1) {
         throw new AimDispatcher("commands.tp.usage", new Object[0]);
      } else {
         byte byteValue = 0;
         Object entry;
         if (textArray.length != 2 && textArray.length != 4 && textArray.length != 6) {
            entry = resolveEntityPlayerAdapter(iCommandSender);
         } else {
            entry = resolveEntity(iCommandSender, textArray[0]);
            byteValue = 1;
         }

         if (textArray.length != 1 && textArray.length != 2) {
            if (textArray.length < byteValue + 3) {
               throw new AimDispatcher("commands.tp.usage", new Object[0]);
            }

            if (((Entity)entry).worldObj != null) {
               int count = byteValue + 1;
               Controller17 controller = resolveController17(((Entity)entry).posX, textArray[byteValue], true);
               Controller17 controller2 = resolveController17(((Entity)entry).posY, textArray[count++], 0, 0, false);
               Controller17 controller3 = resolveController17(((Entity)entry).posZ, textArray[count++], true);
               Controller17 controller4 = resolveController17((double)((Entity)entry).rotationYaw, textArray.length > count ? textArray[count++] : "~", false);
               Controller17 controller5 = resolveController17((double)((Entity)entry).rotationPitch, textArray.length > count ? textArray[count] : "~", false);
               if (entry instanceof EntityPlayerAdapter) {
                  EnumSet enumSet = EnumSet.noneOf(NetHandlerPlayClientXType.class);
                  if (controller.isEnabled()) {
                     enumSet.add(NetHandlerPlayClientXType.X);
                  }

                  if (controller2.isEnabled()) {
                     enumSet.add(NetHandlerPlayClientXType.Y);
                  }

                  if (controller3.isEnabled()) {
                     enumSet.add(NetHandlerPlayClientXType.Z);
                  }

                  if (controller5.isEnabled()) {
                     enumSet.add(NetHandlerPlayClientXType.X_ROT);
                  }

                  if (controller4.isEnabled()) {
                     enumSet.add(NetHandlerPlayClientXType.Y_ROT);
                  }

                  float value = (float)controller4.getValue2();
                  if (!controller4.isEnabled()) {
                     value = MathHelper.sqrt_float(value);
                  }

                  float value2 = (float)controller5.getValue2();
                  if (!controller5.isEnabled()) {
                     value2 = MathHelper.sqrt_float(value2);
                  }

                  if (value2 > 90.0F || value2 < -90.0F) {
                     value2 = MathHelper.sqrt_float(180.0F - value2);
                     value = MathHelper.sqrt_float(value + 180.0F);
                  }

                  ((Entity)entry).setEntity8((Entity)null);
                  ((EntityPlayerAdapter)entry).auxiliaryListenerAdapter4.applySet(controller.getValue2(), controller2.getValue2(), controller3.getValue2(), value, value2, enumSet);
                  ((Entity)entry).setScaleFactor12(value);
               } else {
                  float doubleItem = (float)MathHelper.getDouble4(controller4.getValue());
                  float doubleItem2 = (float)MathHelper.getDouble4(controller5.getValue());
                  if (doubleItem2 > 90.0F || doubleItem2 < -90.0F) {
                     doubleItem2 = MathHelper.sqrt_float(180.0F - doubleItem2);
                     doubleItem = MathHelper.sqrt_float(doubleItem + 180.0F);
                  }

                  ((Entity)entry).applyDoubleByDouble(controller.getValue(), controller2.getValue(), controller3.getValue(), doubleItem, doubleItem2);
                  ((Entity)entry).setScaleFactor12(doubleItem);
               }

               applyICommandSender(
                  iCommandSender, this, "commands.tp.success.coordinates", new Object[]{((Entity)entry).getText(), controller.getValue(), controller2.getValue(), controller3.getValue()}
               );
            }
         } else {
            Entity entity = resolveEntity(iCommandSender, textArray[textArray.length - 1]);
            if (entity.worldObj != ((Entity)entry).worldObj) {
               throw new InternalExceptionAdapter("commands.tp.notSameDimension", new Object[0]);
            }

            ((Entity)entry).setEntity8((Entity)null);
            if (entry instanceof EntityPlayerAdapter) {
               ((EntityPlayerAdapter)entry).auxiliaryListenerAdapter4.applyDouble(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
            } else {
               ((Entity)entry).applyDoubleByDouble(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
            }

            applyICommandSender(iCommandSender, this, "commands.tp.success", new Object[]{((Entity)entry).getText(), entity.getText()});
         }
      }
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 0;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.tp.usage";
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public String getText() {
      return "tp";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length != 1 && textArray.length != 2 ? null : resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray());
   }
}
