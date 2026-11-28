package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CommandSummon extends CommandBase {

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, EntityList.getEntityNameList())
         : (strings.length > 1 && strings.length <= 4 ? getTabCompletionCoordinate(strings, 1, pos) : null);
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 1) {
         throw new WrongUsageException("commands.summon.usage", new Object[0]);
      } else {
         String str = strings[0];
         BlockPos position = commandSender.getPosition();
         Vec3 positionVector = commandSender.getPositionVector();
         double xCoord = positionVector.xCoord;
         double yCoord = positionVector.yCoord;
         double zCoord = positionVector.zCoord;
         if (strings.length >= 4) {
            xCoord = parseDouble(xCoord, strings[1], true);
            yCoord = parseDouble(yCoord, strings[2], false);
            zCoord = parseDouble(zCoord, strings[3], true);
            position = new BlockPos(xCoord, yCoord, zCoord);
         }

         World entityWorld = commandSender.getEntityWorld();
         if (!entityWorld.isBlockLoaded(position)) {
            throw new CommandException("commands.summon.outOfWorld", new Object[0]);
         } else {
            if ("LightningBolt".equals(str)) {
               entityWorld.addWeatherEffect(new EntityLightningBolt(entityWorld, xCoord, yCoord, zCoord));
               notifyOperators(commandSender, this, "commands.summon.success", new Object[0]);
            } else {
               NBTTagCompound tag = new NBTTagCompound();
               boolean flag = false;
               if (strings.length >= 5) {
                  IChatComponent component = getChatComponentFromNthArg(commandSender, strings, 4);

                  try {
                     tag = JsonToNBT.getTagFromJson(component.getUnformattedText());
                     flag = true;
                  } catch (NBTException e) {
                     throw new CommandException("commands.summon.tagError", new Object[]{e.getMessage()});
                  }
               }

               tag.setString("id", str);

               Entity entity;
               try {
                  entity = EntityList.createEntityFromNBT(tag, entityWorld);
               } catch (RuntimeException e2) {
                  throw new CommandException("commands.summon.failed", new Object[0]);
               }

               if (entity == null) {
                  throw new CommandException("commands.summon.failed", new Object[0]);
               }

               entity.setLocationAndAngles(xCoord, yCoord, zCoord, entity.rotationYaw, entity.rotationPitch);
               if (!flag && entity instanceof EntityLiving) {
                  ((EntityLiving)entity).onInitialSpawn(entityWorld.getDifficultyForLocation(new BlockPos(entity)), (IEntityLivingData)null);
               }

               entityWorld.spawnEntityInWorld(entity);
               Entity entity2 = entity;

               for (NBTTagCompound i = tag; entity2 != null && i.hasKey("Riding", 10); i = i.getCompoundTag("Riding")) {
                  Entity entity3 = EntityList.createEntityFromNBT(i.getCompoundTag("Riding"), entityWorld);
                  if (entity3 != null) {
                     entity3.setLocationAndAngles(xCoord, yCoord, zCoord, entity3.rotationYaw, entity3.rotationPitch);
                     entityWorld.spawnEntityInWorld(entity3);
                     entity2.mountEntity(entity3);
                  }

                  entity2 = entity3;
               }

               notifyOperators(commandSender, this, "commands.summon.success", new Object[0]);
            }
         }
      }
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.summon.usage";
   }

   @Override
   public String getCommandName() {
      return "summon";
   }
}
