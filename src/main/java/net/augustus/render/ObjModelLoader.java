package net.augustus.render;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class ObjModelLoader {
   private static Map facesByMaterial = new HashMap();
   public static List vertices = new ArrayList();
   public static List faces = new ArrayList();
   private static int modelDisplayList;
   public static List texCoords = new ArrayList();
   public static List normals = new ArrayList();
   private static int textureId;
   public static Map materials = new HashMap();

   public static void renderModel() {

      for (Entry entry : (Iterable<Entry>)(facesByMaterial.entrySet())) {
         ObjMaterial objMaterial = (ObjMaterial)materials.get(entry.getKey());
         if (objMaterial != null) {
            GlStateManager.color(objMaterial.diffuseColor.x, objMaterial.diffuseColor.y, objMaterial.diffuseColor.z);
         }
      }

      for (ObjMaterial objMaterial2 : (Iterable<ObjMaterial>)(materials.values())) {
         GlStateManager.bindTexture(textureId);
         GlStateManager.callList(objMaterial2.displayListId);
      }

      GlStateManager.resetColor();
   }

   public static void compileDisplayLists() {

      for (Entry entry : (Iterable<Entry>)(facesByMaterial.entrySet())) {
         ObjMaterial objMaterial = (ObjMaterial)materials.get(entry.getKey());
         if (objMaterial.displayListId >= 0) {
            GLAllocation.deleteDisplayLists(objMaterial.displayListId);
         }

         int n = GL11.glGenLists(1);
         objMaterial.displayListId = n;
         GL11.glNewList(n, 4864);
         GL11.glColor3f(objMaterial.diffuseColor.x, objMaterial.diffuseColor.y, objMaterial.diffuseColor.z);

         for (ObjModelFace objModelFace : (Iterable<ObjModelFace>)((List)entry.getValue())) {
            GL11.glBegin(7);

            for (int i = 0; i < objModelFace.vertexIndices.length; i++) {
               int n2 = objModelFace.vertexIndices[i] - 1;
               int n3 = objModelFace.texCoordIndices[i] - 1;
               int n4 = objModelFace.normalIndices[i] - 1;
               if (n3 >= 0) {
                  Vector2f vector2f = (Vector2f)texCoords.get(n3);
                  GL11.glTexCoord2f(vector2f.x, vector2f.y);
               }

               if (n4 >= 0) {
                  Vector3f vector3f = (Vector3f)normals.get(n4);
                  GL11.glNormal3f(vector3f.x, vector3f.y, vector3f.z);
               }

               Vector3f vector3f2 = (Vector3f)vertices.get(n2);
               GL11.glVertex3f(vector3f2.x, vector3f2.y, vector3f2.z);
            }

            GL11.glEnd();
         }

         GL11.glEndList();
      }
   }

   public static void loadObjModel(String str, String str2) {

      try {
         loadMaterialLibrary(str2);
         InputStream resourceAsStream = ObjModelLoader.class.getResourceAsStream(String.format("/assets/augustus/custommodels/%s", str));
         if (resourceAsStream == null) {
            throw new IOException("Datei nicht gefunden: " + str2);
         }

         BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
         String str3 = null;

         String str4;
         while ((str4 = reader.readLine()) != null) {
            if (str4.startsWith("v ")) {
               String[] parts = str4.split("\\s+");
               Vector3f vector3f = new Vector3f(Float.parseFloat(parts[1]), Float.parseFloat(parts[2]), Float.parseFloat(parts[3]));
               vertices.add(vector3f);
            } else if (str4.startsWith("vt ")) {
               String[] strings2 = str4.split("\\s+");
               Vector2f vector2f = new Vector2f(Float.parseFloat(strings2[1]), Float.parseFloat(strings2[2]));
               texCoords.add(vector2f);
            } else if (str4.startsWith("vn ")) {
               String[] strings3 = str4.split("\\s+");
               Vector3f vector3f2 = new Vector3f(Float.parseFloat(strings3[1]), Float.parseFloat(strings3[2]), Float.parseFloat(strings3[3]));
               normals.add(vector3f2);
            } else if (str4.startsWith("usemtl ")) {
               str3 = str4.substring(7).trim();
            } else if (str4.startsWith("f ")) {
               String[] strings4 = str4.split("\\s+");
               ArrayList list = new ArrayList();
               ArrayList list2 = new ArrayList();
               ArrayList list3 = new ArrayList();

               for (int i = 1; i < strings4.length; i++) {
                  String[] strings5 = strings4[i].split("/");
                  list.add(Integer.parseInt(strings5[0]));
                  if (strings5.length > 1 && !strings5[1].isEmpty()) {
                     list2.add(Integer.parseInt(strings5[1]));
                  }

                  if (strings5.length > 2) {
                     list3.add(Integer.parseInt(strings5[2]));
                  }
               }

               ObjModelFace objModelFace = new ObjModelFace(
                  list.stream().mapToInt(obj -> (Integer)obj).toArray(),
                  list2.stream().mapToInt(obj2 -> (Integer)obj2).toArray(),
                  list3.stream().mapToInt(obj3 -> (Integer)obj3).toArray(),
                  str3
               );
               faces.add(objModelFace);
               ((List)facesByMaterial.computeIfAbsent(str3, obj4 -> new ArrayList<>())).add(objModelFace);
            }
         }

         reader.close();
         textureId = GL11.glGenTextures();
         GL11.glBindTexture(3553, textureId);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }


   public static void loadMaterialLibrary(String str) throws IOException {
      InputStream resourceAsStream = ObjModelLoader.class.getResourceAsStream(String.format("/assets/augustus/custommodels/%s", str));
      if (resourceAsStream == null) {
         throw new IOException("Datei nicht gefunden: " + str);
      } else {
         BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
         ObjMaterial objMaterial = null;

         String str2;
         while ((str2 = reader.readLine()) != null) {
            if (str2.startsWith("newmtl ")) {
               if (objMaterial != null) {
                  materials.put(objMaterial.name, objMaterial);
               }

               objMaterial = new ObjMaterial();
               objMaterial.name = str2.substring(7).trim();
            } else if (str2.startsWith("Ka ")) {
               String[] parts = str2.split("\\s+");
               Vector3f vector3f = new Vector3f(Float.parseFloat(parts[1]), Float.parseFloat(parts[2]), Float.parseFloat(parts[3]));
               if (objMaterial != null) {
                  objMaterial.ambientColor = vector3f;
               }
            } else if (str2.startsWith("Kd ")) {
               String[] strings2 = str2.split("\\s+");
               Vector3f vector3f2 = new Vector3f(Float.parseFloat(strings2[1]), Float.parseFloat(strings2[2]), Float.parseFloat(strings2[3]));
               if (objMaterial != null) {
                  objMaterial.diffuseColor = vector3f2;
               }
            } else if (str2.startsWith("Ks ")) {
               String[] strings3 = str2.split("\\s+");
               Vector3f vector3f3 = new Vector3f(Float.parseFloat(strings3[1]), Float.parseFloat(strings3[2]), Float.parseFloat(strings3[3]));
               if (objMaterial != null) {
                  objMaterial.specularColor = vector3f3;
               }
            } else if (str2.startsWith("Ke ")) {
               String[] strings4 = str2.split("\\s+");
               Vector3f vector3f4 = new Vector3f(Float.parseFloat(strings4[1]), Float.parseFloat(strings4[2]), Float.parseFloat(strings4[3]));
               if (objMaterial != null) {
                  objMaterial.emissiveColor = vector3f4;
               }
            } else if (str2.startsWith("Ns ")) {
               float f = Float.parseFloat(str2.split("\\s+")[1]);
               if (objMaterial != null) {
                  objMaterial.specularExponent = f;
               }
            } else if (str2.startsWith("Ni ")) {
               float f2 = Float.parseFloat(str2.split("\\s+")[1]);
               if (objMaterial != null) {
                  objMaterial.opticalDensity = f2;
               }
            } else if (str2.startsWith("d ")) {
               float f3 = Float.parseFloat(str2.split("\\s+")[1]);
               if (objMaterial != null) {
                  objMaterial.dissolve = f3;
               }
            } else if (str2.startsWith("illum ")) {
               int n = Integer.parseInt(str2.split("\\s+")[1]);
               if (objMaterial != null) {
                  objMaterial.illuminationModel = n;
               }
            }
         }

         if (objMaterial != null) {
            materials.put(objMaterial.name, objMaterial);
         }

         reader.close();
      }
   }



}
