package client.util;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class AdvancedListener87EntryAdapter extends Listener87Entry {
   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      Object entry = iCommandSender instanceof EntityPlayer ? ((EntityPlayer)iCommandSender).worldObj : VanillaRunnable.getVanillaRunnable().resolveWorldServer(0);
      iCommandSender.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("commands.seed.success", new Object[]{((World)entry).getTimestamp()}));
   }

   @Override
   public String getText() {
      return "seed";
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.seed.usage";
   }

   @Override
   public boolean matchesICommandSender(ICommandSender iCommandSender) {
      return VanillaRunnable.getVanillaRunnable().isActive10() || super.matchesICommandSender(iCommandSender);
   }
}
