package net.augustus.commands.impl;

import net.augustus.commands.Command;
import net.augustus.utils.ChatUtil;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.DataInputStream;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.util.Vec3;

public class GenRotationsCommand extends Command {

   public void helpMessage() {
      ChatUtil.sendChat("§c" + this.getCommand() + "§7 (generates new aura rotations)");
   }

   public GenRotationsCommand(long l) {
      super(".genrotations");
   }

   public void commandAction(String[] args, String rawMessage) {
      if (mc.theWorld != null) {
         ArrayList list = new ArrayList();
         ArrayList list2 = new ArrayList();
         ArrayList list3 = new ArrayList();
         FileDialog fileDialog = new FileDialog((Frame)null);
         fileDialog.setVisible(true);
         File file = new File(fileDialog.getDirectory() + fileDialog.getFile());
         HashMap map = new HashMap();
         HashMap map2 = new HashMap();
         ArrayList list4 = new ArrayList();
         ArrayList list5 = new ArrayList();
         HashMap map3 = new HashMap();
         HashMap map4 = new HashMap();

         try {
            DataInputStream in = new DataInputStream(Files.newInputStream(file.toPath()));

            while (in.available() > 0) {
               list3.add(new double[]{in.readDouble(), in.readDouble()});
               list.add(new Vec3(in.readDouble(), in.readDouble(), in.readDouble()));
               list2.add(new Vec3(in.readDouble(), in.readDouble(), in.readDouble()));
               int n = in.readInt();
               int n2 = in.readInt();
               list4.add(n2);
               ArrayList list6 = new ArrayList();

               for (int i = 0; i < n; i++) {
                  int n3 = in.readInt();
                  int n4 = in.readInt();
                  int n5 = in.readInt();
                  Vec3 vec = new Vec3(in.readDouble(), in.readDouble(), in.readDouble());
                  Vec3 vec2 = new Vec3(in.readDouble(), in.readDouble(), in.readDouble());
                  list6.add(new Object[]{n3, n4, n5, vec, vec2});
                  map2.put(n3 + "_" + n5, vec);
                  map.put(n4 + "_" + n5, vec2);
               }

               int n6 = in.readInt();
               ArrayList list7 = new ArrayList();
               ArrayList list8 = new ArrayList();

               for (int j = 0; j < n6; j++) {
                  list7.add(new Vec3(in.readDouble(), in.readDouble(), in.readDouble()));
                  list8.add(new Vec3(in.readDouble(), in.readDouble(), in.readDouble()));
               }

               map4.put(n2, list7);
               map3.put(n2, list8);
               list5.add(list6);
               in.readFloat();
               in.readFloat();
            }

            ChatUtil.sendChat("Loaded " + list3.size() + " ticks!");
            new File(file.getCanonicalPath() + "_" + System.currentTimeMillis()).createNewFile();
            Vec3 interpolatedPos = mc.thePlayer.getInterpolatedPos(1.0F);
            int ticksExisted = mc.thePlayer.ticksExisted;
            mc.thePlayer.setPosition(interpolatedPos.xCoord, interpolatedPos.yCoord, interpolatedPos.zCoord);
            mc.thePlayer.ticksExisted = ticksExisted;
         } catch (Throwable e) {
            e.printStackTrace();
            ChatUtil.sendChat("Failed to load file");
         }
      }
   }

}
