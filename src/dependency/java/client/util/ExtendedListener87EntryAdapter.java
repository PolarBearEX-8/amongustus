package client.util;

import client.setting.CombatNumberProperty;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.storage.WorldInfo;

public class ExtendedListener87EntryAdapter extends Listener87Entry {
   @Override
   public String getText() {
      return "worldborder";
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.worldborder.usage";
   }

   public WorldInfo getWorldInfo() {
      return VanillaRunnable.getVanillaRunnable().worldServerArray[0].getInternalListEntry();
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1
         ? resolveList(textArray, new String[]{"set", "center", "damage", "warning", "add", "get"})
         : (
            textArray.length == 2 && textArray[0].equals("damage")
               ? resolveList(textArray, new String[]{"buffer", "amount"})
               : (
                  textArray.length >= 2 && textArray.length <= 3 && textArray[0].equals("center")
                     ? resolveList(textArray, 1, blockPos)
                     : (textArray.length == 2 && textArray[0].equals("warning") ? resolveList(textArray, new String[]{"time", "distance"}) : null)
               )
         );
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 1) {
         throw new AimDispatcher("commands.worldborder.usage", new Object[0]);
      } else {
         WorldInfo worldInfo = this.getWorldInfo();
         if (textArray[0].equals("set")) {
            if (textArray.length != 2 && textArray.length != 3) {
               throw new AimDispatcher("commands.worldborder.set.usage", new Object[0]);
            }

            double value = worldInfo.getValue7();
            double offset = computeValue(textArray[1], 1.0, 6.0E7);
            long value2 = textArray.length > 2 ? computeTimestamp(textArray[2], 0L, 0x20c49ba5e353f7L) * 1000L : 0L;
            if (value2 > 0L) {
               worldInfo.applyDouble(value, offset, value2);
               if (value > offset) {
                  applyICommandSender(
                     iCommandSender,
                     this,
                     "commands.worldborder.setSlowly.shrink.success",
                     new Object[]{String.format("%.1f", offset), String.format("%.1f", value), Long.toString(value2 / 1000L)}
                  );
               } else {
                  applyICommandSender(
                     iCommandSender,
                     this,
                     "commands.worldborder.setSlowly.grow.success",
                     new Object[]{String.format("%.1f", offset), String.format("%.1f", value), Long.toString(value2 / 1000L)}
                  );
               }
            } else {
               worldInfo.applyDouble(offset);
               applyICommandSender(iCommandSender, this, "commands.worldborder.set.success", new Object[]{String.format("%.1f", offset), String.format("%.1f", value)});
            }
         } else if (textArray[0].equals("add")) {
            if (textArray.length != 2 && textArray.length != 3) {
               throw new AimDispatcher("commands.worldborder.add.usage", new Object[0]);
            }

            double value3 = worldInfo.getValue3();
            double offset2 = value3 + computeValue(textArray[1], -value3, 6.0E7 - value3);
            long value4 = worldInfo.getTimestamp() + (textArray.length > 2 ? computeTimestamp(textArray[2], 0L, 0x20c49ba5e353f7L) * 1000L : 0L);
            if (value4 > 0L) {
               worldInfo.applyDouble(value3, offset2, value4);
               if (value3 > offset2) {
                  applyICommandSender(
                     iCommandSender,
                     this,
                     "commands.worldborder.setSlowly.shrink.success",
                     new Object[]{String.format("%.1f", offset2), String.format("%.1f", value3), Long.toString(value4 / 1000L)}
                  );
               } else {
                  applyICommandSender(
                     iCommandSender,
                     this,
                     "commands.worldborder.setSlowly.grow.success",
                     new Object[]{String.format("%.1f", offset2), String.format("%.1f", value3), Long.toString(value4 / 1000L)}
                  );
               }
            } else {
               worldInfo.applyDouble(offset2);
               applyICommandSender(iCommandSender, this, "commands.worldborder.set.success", new Object[]{String.format("%.1f", offset2), String.format("%.1f", value3)});
            }
         } else if (textArray[0].equals("center")) {
            if (textArray.length != 3) {
               throw new AimDispatcher("commands.worldborder.center.usage", new Object[0]);
            }

            BlockPos blockPos = iCommandSender.getBlockPos();
            double offset3 = computeValue((double)blockPos.getX() + 0.5, textArray[1], true);
            double offset4 = computeValue((double)blockPos.getZ() + 0.5, textArray[2], true);
            worldInfo.applyDouble(offset3, offset4);
            applyICommandSender(iCommandSender, this, "commands.worldborder.center.success", new Object[]{offset3, offset4});
         } else if (textArray[0].equals("damage")) {
            if (textArray.length < 2) {
               throw new AimDispatcher("commands.worldborder.damage.usage", new Object[0]);
            }

            if (textArray[1].equals("buffer")) {
               if (textArray.length != 3) {
                  throw new AimDispatcher("commands.worldborder.damage.buffer.usage", new Object[0]);
               }

               double offset5 = computeValue(textArray[2], 0.0);
               double value5 = worldInfo.getValue4();
               worldInfo.applyDoubleByDouble(offset5);
               applyICommandSender(
                  iCommandSender, this, "commands.worldborder.damage.buffer.success", new Object[]{String.format("%.1f", offset5), String.format("%.1f", value5)}
               );
            } else if (textArray[1].equals("amount")) {
               if (textArray.length != 3) {
                  throw new AimDispatcher("commands.worldborder.damage.amount.usage", new Object[0]);
               }

               double offset6 = computeValue(textArray[2], 0.0);
               double value6 = worldInfo.getValue10();
               worldInfo.applyDouble2(offset6);
               applyICommandSender(
                  iCommandSender, this, "commands.worldborder.damage.amount.success", new Object[]{String.format("%.2f", offset6), String.format("%.2f", value6)}
               );
            }
         } else if (textArray[0].equals("warning")) {
            if (textArray.length < 2) {
               throw new AimDispatcher("commands.worldborder.warning.usage", new Object[0]);
            }

            int count = computeCount(textArray[2], 0);
            if (textArray[1].equals("time")) {
               if (textArray.length != 3) {
                  throw new AimDispatcher("commands.worldborder.warning.time.usage", new Object[0]);
               }

               int count2 = worldInfo.getCount3();
               worldInfo.applyIndexByIndex(count);
               applyICommandSender(iCommandSender, this, "commands.worldborder.warning.time.success", new Object[]{count, count2});
            } else if (textArray[1].equals("distance")) {
               if (textArray.length != 3) {
                  throw new AimDispatcher("commands.worldborder.warning.distance.usage", new Object[0]);
               }

               int count3 = worldInfo.getCount2();
               worldInfo.applyIndex(count);
               applyICommandSender(iCommandSender, this, "commands.worldborder.warning.distance.success", new Object[]{count, count3});
            }
         } else {
            if (!textArray[0].equals("get")) {
               throw new AimDispatcher("commands.worldborder.usage", new Object[0]);
            }

            double value7 = worldInfo.getValue3();
            iCommandSender.applyCombatNumberProperty(CombatNumberProperty.QUERY_RESULT, MathHelper.ceiling_double_int(value7 + 0.5));
            iCommandSender.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("commands.worldborder.get.success", new Object[]{String.format("%.0f", value7)}));
         }
      }
   }

   @Override
   public int getCount() {
      return 2;
   }
}
