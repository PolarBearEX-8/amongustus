package client.render;

import client.util.VectorMatcher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class ObjModelMesh {
   public static List list = new ArrayList();
   public static int count;
   public static List list2 = new ArrayList();
   public static Map map = new HashMap();
   public static Map map2 = new HashMap();
   public static List list3 = new ArrayList();
   public static int count2;
   public static List list4 = new ArrayList();
   private static String[] stringArray;

   private static int intValue(Integer count3) {
      return count3;
   }

   public static void renderMesh() {

      for (Object entryObj : map2.entrySet()) {
         Entry entry = (Entry)entryObj;
         Vector3fRecord vector3fRecord = (Vector3fRecord)map.get(entry.getKey());
         if (vector3fRecord.count >= 0) {
            VectorMatcher.glDeleteLists(vector3fRecord.count);
         }

         int count3 = GL11.glGenLists(1);
         vector3fRecord.count = count3;
         GL11.glNewList(count3, 4864);
         GL11.glColor3f(vector3fRecord.vector3f.x, vector3fRecord.vector3f.y, vector3fRecord.vector3f.z);

         for (Object recordNodeImplObj : (List)entry.getValue()) {
            RecordNodeImpl recordNodeImpl = (RecordNodeImpl)recordNodeImplObj;
            GL11.glBegin(7);

            for (int index = 0; index < recordNodeImpl.countArray.length; index++) {
               int count4 = recordNodeImpl.countArray[index] - 1;
               int count5 = recordNodeImpl.countArray2[index] - 1;
               int count6 = recordNodeImpl.countArray3[index] - 1;
               if (count5 >= 0) {
                  Vector2f vector2f = (Vector2f)list3.get(count5);
                  GL11.glTexCoord2f(vector2f.x, vector2f.y);
               }

               if (count6 >= 0) {
                  Vector3f vector3f = (Vector3f)list.get(count6);
                  GL11.glNormal3f(vector3f.x, vector3f.y, vector3f.z);
               }

               Vector3f vector3f2 = (Vector3f)list4.get(count4);
               GL11.glVertex3f(vector3f2.x, vector3f2.y, vector3f2.z);
            }

            GL11.glEnd();
         }

         GL11.glEndList();
      }
   }

   public static void loadObjModel(String text, String text2) {

      try {
         applyText(text2);
         InputStream resourceAsStream = ObjModelMesh.class.getResourceAsStream(String.format("/assets/augustus/custommodels/%s", text));
         if (resourceAsStream == null) {
            throw new IOException("Datei nicht gefunden: " + text2);
         }

         BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
         String text3 = null;

         String text4;
         while ((text4 = reader.readLine()) != null) {
            if (text4.startsWith("v ")) {
               String[] textArray = text4.split("\\s+");
               Vector3f vector3f = new Vector3f(Float.parseFloat(textArray[1]), Float.parseFloat(textArray[2]), Float.parseFloat(textArray[3]));
               list4.add(vector3f);
            } else if (text4.startsWith("vt ")) {
               String[] textArray2 = text4.split("\\s+");
               Vector2f vector2f = new Vector2f(Float.parseFloat(textArray2[1]), Float.parseFloat(textArray2[2]));
               list3.add(vector2f);
            } else if (text4.startsWith("vn ")) {
               String[] textArray3 = text4.split("\\s+");
               Vector3f vector3f2 = new Vector3f(Float.parseFloat(textArray3[1]), Float.parseFloat(textArray3[2]), Float.parseFloat(textArray3[3]));
               list.add(vector3f2);
            } else if (text4.startsWith("usemtl ")) {
               text3 = text4.substring(7).trim();
            } else if (text4.startsWith("f ")) {
               String[] textArray4 = text4.split("\\s+");
               ArrayList list5 = new ArrayList();
               ArrayList list6 = new ArrayList();
               ArrayList list7 = new ArrayList();

               for (int index = 1; index < textArray4.length; index++) {
                  String[] textArray5 = textArray4[index].split("/");
                  list5.add(Integer.parseInt(textArray5[0]));
                  if (textArray5.length > 1 && !textArray5[1].isEmpty()) {
                     list6.add(Integer.parseInt(textArray5[1]));
                  }

                  if (textArray5.length > 2) {
                     list7.add(Integer.parseInt(textArray5[2]));
                  }
               }

               RecordNodeImpl recordNodeImpl = new RecordNodeImpl(
                  list5.stream().mapToInt((item -> ObjModelMesh.intValue((Integer)item))).toArray(),
                  list6.stream().mapToInt((item -> ObjModelMesh.intValueByInteger((Integer)item))).toArray(),
                  list7.stream().mapToInt((item -> ObjModelMesh.intValue2((Integer)item))).toArray(),
                  text3
               );
               list2.add(recordNodeImpl);
               ((List)map2.computeIfAbsent(text3, item -> ObjModelMesh.resolveList((String)item))).add(recordNodeImpl);
            }
         }

         reader.close();
         count = GL11.glGenTextures();
         GL11.glBindTexture(3553, count);
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   public static void applyText(String text) throws IOException {
      InputStream resourceAsStream = ObjModelMesh.class.getResourceAsStream(String.format("/assets/augustus/custommodels/%s", text));
      if (resourceAsStream == null) {
         throw new IOException("Datei nicht gefunden: " + text);
      } else {
         BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
         Vector3fRecord vector3fRecord = null;

         String text2;
         while ((text2 = reader.readLine()) != null) {
            if (text2.startsWith("newmtl ")) {
               if (vector3fRecord != null) {
                  map.put(vector3fRecord.labelText, vector3fRecord);
               }

               vector3fRecord = new Vector3fRecord();
               vector3fRecord.labelText = text2.substring(7).trim();
            } else if (text2.startsWith("Ka ")) {
               String[] textArray = text2.split("\\s+");
               Vector3f vector3f = new Vector3f(Float.parseFloat(textArray[1]), Float.parseFloat(textArray[2]), Float.parseFloat(textArray[3]));
               if (vector3fRecord != null) {
                  vector3fRecord.vector3f2 = vector3f;
               }
            } else if (text2.startsWith("Kd ")) {
               String[] textArray2 = text2.split("\\s+");
               Vector3f vector3f2 = new Vector3f(Float.parseFloat(textArray2[1]), Float.parseFloat(textArray2[2]), Float.parseFloat(textArray2[3]));
               if (vector3fRecord != null) {
                  vector3fRecord.vector3f = vector3f2;
               }
            } else if (text2.startsWith("Ks ")) {
               String[] textArray3 = text2.split("\\s+");
               Vector3f vector3f3 = new Vector3f(Float.parseFloat(textArray3[1]), Float.parseFloat(textArray3[2]), Float.parseFloat(textArray3[3]));
               if (vector3fRecord != null) {
                  vector3fRecord.vector3f3 = vector3f3;
               }
            } else if (text2.startsWith("Ke ")) {
               String[] textArray4 = text2.split("\\s+");
               Vector3f vector3f4 = new Vector3f(Float.parseFloat(textArray4[1]), Float.parseFloat(textArray4[2]), Float.parseFloat(textArray4[3]));
               if (vector3fRecord != null) {
                  vector3fRecord.vector3f4 = vector3f4;
               }
            } else if (text2.startsWith("Ns ")) {
               float factor = Float.parseFloat(text2.split("\\s+")[1]);
               if (vector3fRecord != null) {
                  vector3fRecord.factor = factor;
               }
            } else if (text2.startsWith("Ni ")) {
               float factor2 = Float.parseFloat(text2.split("\\s+")[1]);
               if (vector3fRecord != null) {
                  vector3fRecord.factor2 = factor2;
               }
            } else if (text2.startsWith("d ")) {
               float factor3 = Float.parseFloat(text2.split("\\s+")[1]);
               if (vector3fRecord != null) {
                  vector3fRecord.factor3 = factor3;
               }
            } else if (text2.startsWith("illum ")) {
               int count3 = Integer.parseInt(text2.split("\\s+")[1]);
               if (vector3fRecord != null) {
                  vector3fRecord.count2 = count3;
               }
            }
         }

         if (vector3fRecord != null) {
            map.put(vector3fRecord.labelText, vector3fRecord);
         }

         reader.close();
      }
   }

   private static List resolveList(String text) {
      return new ArrayList<>();
   }

   private static int intValueByInteger(Integer count3) {
      return count3;
   }

   private static int intValue2(Integer count3) {
      return count3;
   }

   public static void renderElement() {

      for (Object entryObj : map2.entrySet()) {
         Entry entry = (Entry)entryObj;
         Vector3fRecord vector3fRecord = (Vector3fRecord)map.get(entry.getKey());
         if (vector3fRecord != null) {
            GlStateManager.translate3f2(vector3fRecord.vector3f.x, vector3fRecord.vector3f.y, vector3fRecord.vector3f.z);
         }
      }

      for (Object vector3fRecord2Obj : map.values()) {
         Vector3fRecord vector3fRecord2 = (Vector3fRecord)vector3fRecord2Obj;
         GlStateManager.setActiveTexture(count);
         GlStateManager.applyIndexByIndex(vector3fRecord2.count);
      }

      GlStateManager.applyState();
   }

   public static String[] getStringArray() {
      return null;
   }
}
