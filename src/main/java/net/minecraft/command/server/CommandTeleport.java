package net.minecraft.command.server;

import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class CommandTeleport extends CommandBase {

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 0;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.tp.usage";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length != 1 && strings.length != 2 ? null : getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames());
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 1) {
         throw new WrongUsageException("commands.tp.usage", new Object[0]);
      } else {
         byte b = 0;
         Object obj;
         if (strings.length != 2 && strings.length != 4 && strings.length != 6) {
            obj = getCommandSenderAsPlayer(commandSender);
         } else {
            obj = getEntity(commandSender, strings[0]);
            b = 1;
         }

         if (strings.length != 1 && strings.length != 2) {
            if (strings.length < b + 3) {
               throw new WrongUsageException("commands.tp.usage", new Object[0]);
            }

            if (((Entity)obj).entityWorld != null) {
               int n = b + 1;
               CommandBase.CoordinateArg coordinateArg = parseCoordinate(((Entity)obj).posX, strings[b], true);
               CommandBase.CoordinateArg coordinateArg2 = parseCoordinate(((Entity)obj).posY, strings[n++], 0, 0, false);
               CommandBase.CoordinateArg coordinateArg3 = parseCoordinate(((Entity)obj).posZ, strings[n++], true);
               CommandBase.CoordinateArg coordinateArg4 = parseCoordinate((double)((Entity)obj).rotationYaw, strings.length > n ? strings[n++] : "~", false);
               CommandBase.CoordinateArg coordinateArg5 = parseCoordinate((double)((Entity)obj).rotationPitch, strings.length > n ? strings[n] : "~", false);
               if (obj instanceof EntityPlayerMP) {
                  EnumSet enumSet = EnumSet.noneOf(S08PacketPlayerPosLook.EnumFlags.class);
                  if (coordinateArg.isRelative()) {
                     enumSet.add(S08PacketPlayerPosLook.EnumFlags.X);
                  }

                  if (coordinateArg2.isRelative()) {
                     enumSet.add(S08PacketPlayerPosLook.EnumFlags.Y);
                  }

                  if (coordinateArg3.isRelative()) {
                     enumSet.add(S08PacketPlayerPosLook.EnumFlags.Z);
                  }

                  if (coordinateArg5.isRelative()) {
                     enumSet.add(S08PacketPlayerPosLook.EnumFlags.X_ROT);
                  }

                  if (coordinateArg4.isRelative()) {
                     enumSet.add(S08PacketPlayerPosLook.EnumFlags.Y_ROT);
                  }

                  float amount = (float)coordinateArg4.getAmount();
                  if (!coordinateArg4.isRelative()) {
                     amount = MathHelper.wrapAngleTo180_float(amount);
                  }

                  float amount2 = (float)coordinateArg5.getAmount();
                  if (!coordinateArg5.isRelative()) {
                     amount2 = MathHelper.wrapAngleTo180_float(amount2);
                  }

                  if (amount2 > 90.0F || amount2 < -90.0F) {
                     amount2 = MathHelper.wrapAngleTo180_float(180.0F - amount2);
                     amount = MathHelper.wrapAngleTo180_float(amount + 180.0F);
                  }

                  ((Entity)obj).mountEntity((Entity)null);
                  ((EntityPlayerMP)obj)
                     .playerNetServerHandler
                     .setPlayerLocation(coordinateArg.getAmount(), coordinateArg2.getAmount(), coordinateArg3.getAmount(), amount, amount2, enumSet);
                  ((Entity)obj).setRotationYawHead(amount);
               } else {
                  float f = (float)MathHelper.wrapAngleTo180_double(coordinateArg4.getResult());
                  float f2 = (float)MathHelper.wrapAngleTo180_double(coordinateArg5.getResult());
                  if (f2 > 90.0F || f2 < -90.0F) {
                     f2 = MathHelper.wrapAngleTo180_float(180.0F - f2);
                     f = MathHelper.wrapAngleTo180_float(f + 180.0F);
                  }

                  ((Entity)obj).setLocationAndAngles(coordinateArg.getResult(), coordinateArg2.getResult(), coordinateArg3.getResult(), f, f2);
                  ((Entity)obj).setRotationYawHead(f);
               }

               notifyOperators(
                  commandSender,
                  this,
                  "commands.tp.success.coordinates",
                  new Object[]{((Entity)obj).getName(), coordinateArg.getResult(), coordinateArg2.getResult(), coordinateArg3.getResult()}
               );
            }
         } else {
            Entity entity = getEntity(commandSender, strings[strings.length - 1]);
            if (entity.entityWorld != ((Entity)obj).entityWorld) {
               throw new CommandException("commands.tp.notSameDimension", new Object[0]);
            }

            ((Entity)obj).mountEntity((Entity)null);
            if (obj instanceof EntityPlayerMP) {
               ((EntityPlayerMP)obj).playerNetServerHandler.setPlayerLocation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
            } else {
               ((Entity)obj).setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
            }

            notifyOperators(commandSender, this, "commands.tp.success", new Object[]{((Entity)obj).getName(), entity.getName()});
         }
      }
   }

   @Override
   public String getCommandName() {
      return "tp";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }
}
