package client.util;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.DataInputStream;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.util.Vec3;

public class RotationgeneratorGeneratesAuxiliaryStringEntry extends AuxiliaryStringEntry {
   public RotationgeneratorGeneratesAuxiliaryStringEntry() {
      super("rotationgenerator");
   }

   @Override
   public void updateState() {
      RayTraceUtil.applyString("§c" + this.getString() + "§7 (generates new aura rotations)");
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (MinecraftAccessor.minecraft.theWorld != null) {
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
            DataInputStream dataInputStream = new DataInputStream(Files.newInputStream(file.toPath()));

            while (dataInputStream.available() > 0) {
               list3.add(new double[]{dataInputStream.readDouble(), dataInputStream.readDouble()});
               list.add(new Vec3(dataInputStream.readDouble(), dataInputStream.readDouble(), dataInputStream.readDouble()));
               list2.add(new Vec3(dataInputStream.readDouble(), dataInputStream.readDouble(), dataInputStream.readDouble()));
               int count = dataInputStream.readInt();
               int count2 = dataInputStream.readInt();
               list4.add(count2);
               ArrayList list6 = new ArrayList();

               for (int index = 0; index < count; index++) {
                  int count3 = dataInputStream.readInt();
                  int count4 = dataInputStream.readInt();
                  int count5 = dataInputStream.readInt();
                  Vec3 vec = new Vec3(dataInputStream.readDouble(), dataInputStream.readDouble(), dataInputStream.readDouble());
                  Vec3 vec2 = new Vec3(dataInputStream.readDouble(), dataInputStream.readDouble(), dataInputStream.readDouble());
                  list6.add(new Object[]{count3, count4, count5, vec, vec2});
                  map2.put(count3 + "_" + count5, vec);
                  map.put(count4 + "_" + count5, vec2);
               }

               int count6 = dataInputStream.readInt();
               ArrayList list7 = new ArrayList();
               ArrayList list8 = new ArrayList();

               for (int index2 = 0; index2 < count6; index2++) {
                  list7.add(new Vec3(dataInputStream.readDouble(), dataInputStream.readDouble(), dataInputStream.readDouble()));
                  list8.add(new Vec3(dataInputStream.readDouble(), dataInputStream.readDouble(), dataInputStream.readDouble()));
               }

               map4.put(count2, list7);
               map3.put(count2, list8);
               list5.add(list6);
               dataInputStream.readFloat();
               dataInputStream.readFloat();
            }

            RayTraceUtil.applyString("Loaded " + list3.size() + " ticks!");
            new File(file.getCanonicalPath() + "_" + System.currentTimeMillis()).createNewFile();
            Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec32(1.0F);
            int ticksExisted = MinecraftAccessor.minecraft.thePlayer.ticksExisted;
            MinecraftAccessor.minecraft.thePlayer.processDouble2(vec3.xCoord, vec3.yCoord, vec3.zCoord);
            MinecraftAccessor.minecraft.thePlayer.ticksExisted = ticksExisted;
         } catch (Throwable throwable) {
            throwable.printStackTrace();
            RayTraceUtil.applyString("Failed to load file");
         }
      }
   }
}
