package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;

public class CommandEffect extends CommandBase {

   @Override
   public String getCommandName() {
      return "effect";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 2) {
         throw new WrongUsageException("commands.effect.usage", new Object[0]);
      } else {
         EntityLivingBase entity = (EntityLivingBase)getEntity(commandSender, strings[0], EntityLivingBase.class);
         if (strings[1].equals("clear")) {
            if (entity.getActivePotionEffects().isEmpty()) {
               throw new CommandException("commands.effect.failure.notActive.all", new Object[]{entity.getName()});
            }

            entity.clearActivePotions();
            notifyOperators(commandSender, this, "commands.effect.success.removed.all", new Object[]{entity.getName()});
         } else {
            int n;
            try {
               n = parseInt(strings[1], 1);
            } catch (NumberInvalidException e) {
               Potion potion = Potion.getPotionFromResourceLocation(strings[1]);
               if (potion == null) {
                  throw e;
               }

               n = potion.id;
            }

            int n2 = 600;
            int n3 = 30;
            int n4 = 0;
            if (n < 0 || n >= Potion.potionTypes.length || Potion.potionTypes[n] == null) {
               throw new NumberInvalidException("commands.effect.notFound", new Object[]{n});
            }

            Potion potion2 = Potion.potionTypes[n];
            if (strings.length >= 3) {
               n3 = parseInt(strings[2], 0, 1000000);
               if (potion2.isInstant()) {
                  n2 = n3;
               } else {
                  n2 = n3 * 20;
               }
            } else if (potion2.isInstant()) {
               n2 = 1;
            }

            if (strings.length >= 4) {
               n4 = parseInt(strings[3], 0, 255);
            }

            boolean flag = true;
            if (strings.length >= 5 && "true".equalsIgnoreCase(strings[4])) {
               flag = false;
            }

            if (n3 > 0) {
               PotionEffect potionEffect = new PotionEffect(n, n2, n4, false, flag);
               entity.addPotionEffect(potionEffect);
               notifyOperators(
                  commandSender,
                  this,
                  "commands.effect.success",
                  new Object[]{new ChatComponentTranslation(potionEffect.getEffectName(), new Object[0]), n, n4, entity.getName(), n3}
               );
            } else {
               if (!entity.isPotionActive(n)) {
                  throw new CommandException(
                     "commands.effect.failure.notActive", new Object[]{new ChatComponentTranslation(potion2.getName(), new Object[0]), entity.getName()}
                  );
               }

               entity.removePotionEffect(n);
               notifyOperators(
                  commandSender, this, "commands.effect.success.removed", new Object[]{new ChatComponentTranslation(potion2.getName(), new Object[0]), entity.getName()}
               );
            }
         }
      }
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.effect.usage";
   }

   public String[] getAllUsernames() {
      return MinecraftServer.getServer().getAllUsernames();
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, this.getAllUsernames())
         : (
            strings.length == 2
               ? getListOfStringsMatchingLastWord(strings, Potion.getPotionLocations())
               : (strings.length == 5 ? getListOfStringsMatchingLastWord(strings, new String[]{"true", "false"}) : null)
         );
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 0;
   }
}
