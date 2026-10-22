package net;

import java.security.Key;
import net.Class_471j;
import net.Class_472z;
import net.Class_47Fi;
import net.Class_47M0;
import net.Class_47rM;
import net.Class_4f;


import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class Class_472l extends Class_472z {
   private final Class_47rM field_46;
   private static final long c = Class_4f.a(-3395585593659978603L, 4231473500107948753L, MethodHandles.lookup().lookupClass()).a(276658337890800L);
   private static final String e;
   private static final Object[] j = new Object[15];
   private static final String[] k = new String[15];

   private static RuntimeException b(Exception var0) {
      return Class_472l.<RuntimeException, RuntimeException>throwUnchecked(var0);
   }

   private static String b(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         int var10000 = var5 = 255 & var0[var4];

         try {
            if (var10000 < 192) {
               var3[var1++] = (char)var5;
               continue;
            }
         } catch (RuntimeException var7) {
            throw b(var7);
         }

         if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            byte var9 = var0[++var4];
            var6 = (char)(var6 | (char)(var9 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var13 = (char)((char)(var5 & 15) << '\f');
            byte var10 = var0[++var4];
            var13 = (char)(var13 | (char)(var10 & 63) << 6);
            var10 = var0[++var4];
            var13 = (char)(var13 | (char)(var10 & 63));
            var3[var1++] = var13;
         }
      }

      return new String(var3, 0, var1);
   }

   private static int i(long var0, long var2) {
      var0 ^= var2 << 48 | var2;
      int var4 = (int)(var0 >>> 46);

      try {
         if (k[var4] != null) {
            return var4;
         }
      } catch (RuntimeException var15) {
         throw b(var15);
      }

      Object var5 = j[var4];

      try {
         if (!(var5 instanceof String)) {
            return var4;
         }
      } catch (RuntimeException var14) {
         throw b(var14);
      }

      byte var6 = 0;

      byte var10000;
      label314: {
         label313: {
            label312: {
               label311: {
                  label310: {
                     label309: {
                        label308: {
                           label307: {
                              label306: {
                                 label305: {
                                    label304: {
                                       label303: {
                                          label302: {
                                             label301: {
                                                label300: {
                                                   label299: {
                                                      label298: {
                                                         label297: {
                                                            label296: {
                                                               label295: {
                                                                  label294: {
                                                                     label293: {
                                                                        label292: {
                                                                           label291: {
                                                                              label290: {
                                                                                 label289: {
                                                                                    label288: {
                                                                                       label287: {
                                                                                          label286: {
                                                                                             label285: {
                                                                                                label284: {
                                                                                                   label283: {
                                                                                                      label282: {
                                                                                                         label281: {
                                                                                                            label280: {
                                                                                                               label279: {
                                                                                                                  label278: {
                                                                                                                     label277: {
                                                                                                                        label276: {
                                                                                                                           label275: {
                                                                                                                              label274: {
                                                                                                                                 label273: {
                                                                                                                                    label272: {
                                                                                                                                       label271: {
                                                                                                                                          label270: {
                                                                                                                                             label269: {
                                                                                                                                                label268: {
                                                                                                                                                   label267: {
                                                                                                                                                      label266: {
                                                                                                                                                         label265: {
                                                                                                                                                            label264: {
                                                                                                                                                               label263: {
                                                                                                                                                                  label262: {
                                                                                                                                                                     label261: {
                                                                                                                                                                        label260: {
                                                                                                                                                                           label259: {
                                                                                                                                                                              label258: {
                                                                                                                                                                                 label257: {
                                                                                                                                                                                    label256: {
                                                                                                                                                                                       label255: {
                                                                                                                                                                                          label254: {
                                                                                                                                                                                             label253: {
                                                                                                                                                                                                label252: {
                                                                                                                                                                                                   try {
                                                                                                                                                                                                      switch ((int)(
                                                                                                                                                                                                         var0
                                                                                                                                                                                                               >>> 42
                                                                                                                                                                                                            & 63L
                                                                                                                                                                                                      )) {
                                                                                                                                                                                                         case 0:
                                                                                                                                                                                                            var10000 = 8;
                                                                                                                                                                                                            break label314;
                                                                                                                                                                                                         case 1:
                                                                                                                                                                                                            break label313;
                                                                                                                                                                                                         case 2:
                                                                                                                                                                                                            break label312;
                                                                                                                                                                                                         case 3:
                                                                                                                                                                                                            break label311;
                                                                                                                                                                                                         case 4:
                                                                                                                                                                                                            break label310;
                                                                                                                                                                                                         case 5:
                                                                                                                                                                                                            break label309;
                                                                                                                                                                                                         case 6:
                                                                                                                                                                                                            break label308;
                                                                                                                                                                                                         case 7:
                                                                                                                                                                                                            break label307;
                                                                                                                                                                                                         case 8:
                                                                                                                                                                                                            break label306;
                                                                                                                                                                                                         case 9:
                                                                                                                                                                                                            break label305;
                                                                                                                                                                                                         case 10:
                                                                                                                                                                                                            break label304;
                                                                                                                                                                                                         case 11:
                                                                                                                                                                                                            break label303;
                                                                                                                                                                                                         case 12:
                                                                                                                                                                                                            break label302;
                                                                                                                                                                                                         case 13:
                                                                                                                                                                                                            break label301;
                                                                                                                                                                                                         case 14:
                                                                                                                                                                                                            break label300;
                                                                                                                                                                                                         case 15:
                                                                                                                                                                                                            break label299;
                                                                                                                                                                                                         case 16:
                                                                                                                                                                                                            break label298;
                                                                                                                                                                                                         case 17:
                                                                                                                                                                                                            break label297;
                                                                                                                                                                                                         case 18:
                                                                                                                                                                                                            break label296;
                                                                                                                                                                                                         case 19:
                                                                                                                                                                                                            break label295;
                                                                                                                                                                                                         case 20:
                                                                                                                                                                                                            break label294;
                                                                                                                                                                                                         case 21:
                                                                                                                                                                                                            break label293;
                                                                                                                                                                                                         case 22:
                                                                                                                                                                                                            break label292;
                                                                                                                                                                                                         case 23:
                                                                                                                                                                                                            break label291;
                                                                                                                                                                                                         case 24:
                                                                                                                                                                                                            break label290;
                                                                                                                                                                                                         case 25:
                                                                                                                                                                                                            break label289;
                                                                                                                                                                                                         case 26:
                                                                                                                                                                                                            break label288;
                                                                                                                                                                                                         case 27:
                                                                                                                                                                                                            break label287;
                                                                                                                                                                                                         case 28:
                                                                                                                                                                                                            break label286;
                                                                                                                                                                                                         case 29:
                                                                                                                                                                                                            break label285;
                                                                                                                                                                                                         case 30:
                                                                                                                                                                                                            break label284;
                                                                                                                                                                                                         case 31:
                                                                                                                                                                                                            break label283;
                                                                                                                                                                                                         case 32:
                                                                                                                                                                                                            break label282;
                                                                                                                                                                                                         case 33:
                                                                                                                                                                                                            break label281;
                                                                                                                                                                                                         case 34:
                                                                                                                                                                                                            break label280;
                                                                                                                                                                                                         case 35:
                                                                                                                                                                                                            break label279;
                                                                                                                                                                                                         case 36:
                                                                                                                                                                                                            break label278;
                                                                                                                                                                                                         case 37:
                                                                                                                                                                                                            break label277;
                                                                                                                                                                                                         case 38:
                                                                                                                                                                                                            break label276;
                                                                                                                                                                                                         case 39:
                                                                                                                                                                                                            break label275;
                                                                                                                                                                                                         case 40:
                                                                                                                                                                                                            break label274;
                                                                                                                                                                                                         case 41:
                                                                                                                                                                                                            break label273;
                                                                                                                                                                                                         case 42:
                                                                                                                                                                                                            break label272;
                                                                                                                                                                                                         case 43:
                                                                                                                                                                                                            break label271;
                                                                                                                                                                                                         case 44:
                                                                                                                                                                                                            break label270;
                                                                                                                                                                                                         case 45:
                                                                                                                                                                                                            break label269;
                                                                                                                                                                                                         case 46:
                                                                                                                                                                                                            break label268;
                                                                                                                                                                                                         case 47:
                                                                                                                                                                                                            break label267;
                                                                                                                                                                                                         case 48:
                                                                                                                                                                                                            break label266;
                                                                                                                                                                                                         case 49:
                                                                                                                                                                                                            break label265;
                                                                                                                                                                                                         case 50:
                                                                                                                                                                                                            break label264;
                                                                                                                                                                                                         case 51:
                                                                                                                                                                                                            break label263;
                                                                                                                                                                                                         case 52:
                                                                                                                                                                                                            break label262;
                                                                                                                                                                                                         case 53:
                                                                                                                                                                                                            break label261;
                                                                                                                                                                                                         case 54:
                                                                                                                                                                                                            break label260;
                                                                                                                                                                                                         case 55:
                                                                                                                                                                                                            break label259;
                                                                                                                                                                                                         case 56:
                                                                                                                                                                                                            break label258;
                                                                                                                                                                                                         case 57:
                                                                                                                                                                                                            break label257;
                                                                                                                                                                                                         case 58:
                                                                                                                                                                                                            break label256;
                                                                                                                                                                                                         case 59:
                                                                                                                                                                                                            break label255;
                                                                                                                                                                                                         case 60:
                                                                                                                                                                                                            break label254;
                                                                                                                                                                                                         case 61:
                                                                                                                                                                                                            break label253;
                                                                                                                                                                                                         case 62:
                                                                                                                                                                                                            break label252;
                                                                                                                                                                                                      }
                                                                                                                                                                                                   } catch (RuntimeException var13) {
                                                                                                                                                                                                      throw b(
                                                                                                                                                                                                         var13
                                                                                                                                                                                                      );
                                                                                                                                                                                                   }

                                                                                                                                                                                                   var10000 = 24;
                                                                                                                                                                                                   break label314;
                                                                                                                                                                                                }

                                                                                                                                                                                                var10000 = 49;
                                                                                                                                                                                                break label314;
                                                                                                                                                                                             }

                                                                                                                                                                                             var10000 = 21;
                                                                                                                                                                                             break label314;
                                                                                                                                                                                          }

                                                                                                                                                                                          var10000 = 52;
                                                                                                                                                                                          break label314;
                                                                                                                                                                                       }

                                                                                                                                                                                       var10000 = 50;
                                                                                                                                                                                       break label314;
                                                                                                                                                                                    }

                                                                                                                                                                                    var10000 = 63;
                                                                                                                                                                                    break label314;
                                                                                                                                                                                 }

                                                                                                                                                                                 var10000 = 14;
                                                                                                                                                                                 break label314;
                                                                                                                                                                              }

                                                                                                                                                                              var10000 = 18;
                                                                                                                                                                              break label314;
                                                                                                                                                                           }

                                                                                                                                                                           var10000 = 7;
                                                                                                                                                                           break label314;
                                                                                                                                                                        }

                                                                                                                                                                        var10000 = 44;
                                                                                                                                                                        break label314;
                                                                                                                                                                     }

                                                                                                                                                                     var10000 = 19;
                                                                                                                                                                     break label314;
                                                                                                                                                                  }

                                                                                                                                                                  var10000 = 6;
                                                                                                                                                                  break label314;
                                                                                                                                                               }

                                                                                                                                                               var10000 = 30;
                                                                                                                                                               break label314;
                                                                                                                                                            }

                                                                                                                                                            var10000 = 33;
                                                                                                                                                            break label314;
                                                                                                                                                         }

                                                                                                                                                         var10000 = 3;
                                                                                                                                                         break label314;
                                                                                                                                                      }

                                                                                                                                                      var10000 = 2;
                                                                                                                                                      break label314;
                                                                                                                                                   }

                                                                                                                                                   var10000 = 11;
                                                                                                                                                   break label314;
                                                                                                                                                }

                                                                                                                                                var10000 = 0;
                                                                                                                                                break label314;
                                                                                                                                             }

                                                                                                                                             var10000 = 16;
                                                                                                                                             break label314;
                                                                                                                                          }

                                                                                                                                          var10000 = 40;
                                                                                                                                          break label314;
                                                                                                                                       }

                                                                                                                                       var10000 = 41;
                                                                                                                                       break label314;
                                                                                                                                    }

                                                                                                                                    var10000 = 17;
                                                                                                                                    break label314;
                                                                                                                                 }

                                                                                                                                 var10000 = 32;
                                                                                                                                 break label314;
                                                                                                                              }

                                                                                                                              var10000 = 36;
                                                                                                                              break label314;
                                                                                                                           }

                                                                                                                           var10000 = 55;
                                                                                                                           break label314;
                                                                                                                        }

                                                                                                                        var10000 = 25;
                                                                                                                        break label314;
                                                                                                                     }

                                                                                                                     var10000 = 23;
                                                                                                                     break label314;
                                                                                                                  }

                                                                                                                  var10000 = 22;
                                                                                                                  break label314;
                                                                                                               }

                                                                                                               var10000 = 28;
                                                                                                               break label314;
                                                                                                            }

                                                                                                            var10000 = 61;
                                                                                                            break label314;
                                                                                                         }

                                                                                                         var10000 = 43;
                                                                                                         break label314;
                                                                                                      }

                                                                                                      var10000 = 46;
                                                                                                      break label314;
                                                                                                   }

                                                                                                   var10000 = 31;
                                                                                                   break label314;
                                                                                                }

                                                                                                var10000 = 56;
                                                                                                break label314;
                                                                                             }

                                                                                             var10000 = 45;
                                                                                             break label314;
                                                                                          }

                                                                                          var10000 = 9;
                                                                                          break label314;
                                                                                       }

                                                                                       var10000 = 47;
                                                                                       break label314;
                                                                                    }

                                                                                    var10000 = 4;
                                                                                    break label314;
                                                                                 }

                                                                                 var10000 = 59;
                                                                                 break label314;
                                                                              }

                                                                              var10000 = 53;
                                                                              break label314;
                                                                           }

                                                                           var10000 = 20;
                                                                           break label314;
                                                                        }

                                                                        var10000 = 1;
                                                                        break label314;
                                                                     }

                                                                     var10000 = 29;
                                                                     break label314;
                                                                  }

                                                                  var10000 = 27;
                                                                  break label314;
                                                               }

                                                               var10000 = 26;
                                                               break label314;
                                                            }

                                                            var10000 = 58;
                                                            break label314;
                                                         }

                                                         var10000 = 60;
                                                         break label314;
                                                      }

                                                      var10000 = 5;
                                                      break label314;
                                                   }

                                                   var10000 = 37;
                                                   break label314;
                                                }

                                                var10000 = 12;
                                                break label314;
                                             }

                                             var10000 = 51;
                                             break label314;
                                          }

                                          var10000 = 48;
                                          break label314;
                                       }

                                       var10000 = 34;
                                       break label314;
                                    }

                                    var10000 = 54;
                                    break label314;
                                 }

                                 var10000 = 42;
                                 break label314;
                              }

                              var10000 = 15;
                              break label314;
                           }

                           var10000 = 38;
                           break label314;
                        }

                        var10000 = 10;
                        break label314;
                     }

                     var10000 = 62;
                     break label314;
                  }

                  var10000 = 39;
                  break label314;
               }

               var10000 = 13;
               break label314;
            }

            var10000 = 57;
            break label314;
         }

         var10000 = 35;
      }

      var6 = var10000;
      int[] var7 = new int[6];

      for (int var8 = 0; var8 < 6; var8++) {
         int var9 = 7 * (5 - var8);
         int var10 = (int)(var0 >>> var9 & 127L);
         var10 -= var6;

         try {
            if (var10 < 0) {
               var10 += 128;
            }
         } catch (RuntimeException var12) {
            throw b(var12);
         }

         var7[var8] = var10;
      }

      char[] var18 = ((String)var5).toCharArray();
      int var19 = 0;

      while (var19 < var18.length) {
         int var21 = var7[var19 % var7.length];

         try {
            if (var21 == 0) {
               break;
            }

            var18[var19] = (char)(var18[var19] ^ var21);
            var19++;
         } catch (RuntimeException var11) {
            throw b(var11);
         }
      }

      k[var4] = new String(var18);
      return var4;
   }

    private static Field e(Class clazz, String string, Class clazz2) {
        for (Field field : clazz.getDeclaredFields()) {
            try {
                try {
                    if (!field.getName().equals(string) || field.getType() != clazz2) continue;
                }
                catch (RuntimeException runtimeException) {
                    throw Class_472l.b(runtimeException);
                }
                return field;
            }
            catch (RuntimeException runtimeException) {
                throw Class_472l.b(runtimeException);
            }
        }
        return null;
    }

    private static Method e(Class clazz, String string, Class clazz2, int n, Class[] classArray) {
        block6: for (Method method : clazz.getDeclaredMethods()) {
            try {
                if (!method.getName().equals(string) || method.getReturnType() != clazz2) continue;
            }
            catch (RuntimeException runtimeException) {
                throw Class_472l.b(runtimeException);
            }
            Class<?>[] classArray2 = method.getParameterTypes();
            if (classArray2.length != n) continue;
            int n2 = 0;
            while (true) {
                try {
                    try {
                        if (n2 >= n) break;
                        if (classArray2[n2] != classArray[n2]) {
                            continue block6;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw Class_472l.b(runtimeException);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw Class_472l.b(runtimeException);
                }
                ++n2;
            }
            return method;
        }
        return null;
    }

   private static Method l(long var0, long var2) {
      int var4 = i(var0, var2);
      Object var5 = j[var4];
      if (!(var5 instanceof String)) {
         return (Method)var5;
      }

      String var6 = k[var4];
      int var7 = var6.indexOf(8);
      Class var8 = j(Long.parseLong(var6.substring(0, var7), 36), 0L);
      int var9 = var6.indexOf(8, ++var7);
      String var10 = var6.substring(var7, var9);
      int var11 = -1;
      int var12 = var9;

      do {
         var11++;
         var12++;
      } while ((var12 = var6.indexOf(8, var12)) > -1);

      int var13;
      Class[] var14 = new Class[var13 = var11 - 1];
      Class var15 = null;
      var12 = var9 + 1;

      for (int var16 = 0; var16 < var11; var16++) {
         int var17 = var6.indexOf(8, var12);
         var15 = j(Long.parseLong(var6.substring(var12, var17), 36), 0L);

         try {
            if (var16 < var13) {
               var14[var16] = var15;
            }
         } catch (RuntimeException var22) {
            throw b(var22);
         }

         var12 = var17 + 1;
      }

      Class var26 = var8;

      while (true) {
         Method var29 = e(var26, var10, var15, var13, var14);
         if (var29 != null) {
            j[var4] = var29;
            return var29;
         }

         if (var26.getName().equals("java.lang.Object")) {
            break;
         }

         if ((var26 = var26.getSuperclass()) == null) {
            var26 = j(673139839723626L, 0L);
            break;
         }
      }

      var26 = var8;

      while (true) {
         Class[] var30;
         if ((var30 = var26.getInterfaces()) != null) {
            for (int var18 = 0; var18 < var30.length; var18++) {
               Method var19 = f(var30[var18], var10, var15, var13, var14);

               try {
                  if (var19 != null) {
                     j[var4] = var19;
                     return var19;
                  }
               } catch (RuntimeException var21) {
                  throw b(var21);
               }
            }
         }

         if (var26.getName().equals("java.lang.Object")) {
            StringBuffer var31 = new StringBuffer();
            var31.append("NoSuchMethodException in ").append(var8.getName()).append(' ').append(var15.getName()).append(' ').append(var10).append('(');
            int var32 = 0;

            while (true) {
               try {
                  if (var32 >= var13) {
                     break;
                  }

                  var31.append(var14[var32].getName());
                  if (++var32 >= var13) {
                     continue;
                  }
               } catch (RuntimeException var20) {
                  throw b(var20);
               }

               var31.append(", ");
            }

            var31.append(')');
            throw new RuntimeException(var31.toString());
         }

         if ((var26 = var26.getSuperclass()) == null) {
            var26 = j(673139839723626L, 0L);
         }
      }
   }

   private static Method f(Class var0, String var1, Class var2, int var3, Class[] var4) {
      Method var5 = e(var0, var1, var2, var3, var4);

      try {
         if (var5 != null) {
            return var5;
         }
      } catch (RuntimeException var8) {
         throw b(var8);
      }

      Class[] var6 = var0.getInterfaces();
      if (var6 != null) {
         for (int var7 = 0; var7 < var6.length; var7++) {
            var5 = f(var6[var7], var1, var2, var3, var4);

            try {
               if (var5 != null) {
                  return var5;
               }
            } catch (RuntimeException var9) {
               throw b(var9);
            }
         }
      }

      return null;
   }

   private static Field f(Class var0, String var1, Class var2) {
      Field var3 = e(var0, var1, var2);

      try {
         if (var3 != null) {
            return var3;
         }
      } catch (RuntimeException var6) {
         throw b(var6);
      }

      Class[] var4 = var0.getInterfaces();
      if (var4 != null) {
         for (int var5 = 0; var5 < var4.length; var5++) {
            var3 = f(var4[var5], var1, var2);

            try {
               if (var3 != null) {
                  return var3;
               }
            } catch (RuntimeException var7) {
               throw b(var7);
            }
         }
      }

      return null;
   }

   private static Field k(long var0, long var2) {
      int var4 = i(var0, var2);
      Object var5 = j[var4];
      if (!(var5 instanceof String)) {
         return (Field)var5;
      }

      String var6 = k[var4];
      int var7 = var6.indexOf(8);
      Class var8 = j(Long.parseLong(var6.substring(0, var7), 36), 0L);
      int var9 = var6.indexOf(8, ++var7);
      String var10 = var6.substring(var7, var9);
      Class var11 = j(Long.parseLong(var6.substring(++var9), 36), 0L);
      Class var12 = var8;

      while (true) {
         Field var13 = e(var12, var10, var11);

         try {
            if (var13 != null) {
               j[var4] = var13;
               return var13;
            }
         } catch (RuntimeException var16) {
            throw b(var16);
         }

         Class[] var14 = var12.getInterfaces();
         if (var14 != null) {
            for (int var15 = 0; var15 < var14.length; var15++) {
               var13 = f(var14[var15], var10, var11);

               try {
                  if (var13 != null) {
                     j[var4] = var13;
                     return var13;
                  }
               } catch (RuntimeException var17) {
                  throw b(var17);
               }
            }
         }

         if (var12.getName().equals("java.lang.Object")) {
            StringBuffer var21 = new StringBuffer();
            var21.append("NoSuchFieldException in ").append(var8.getName()).append(' ').append(var11.getName()).append(' ').append(var10);
            throw new RuntimeException(var21.toString());
         }

         var12 = var12.getSuperclass();
         if (var12 == null) {
            var12 = j(673139839723626L, 0L);
         }
      }
   }

   private static Class j(long var0, long var2) {
      Class var5 = null;
      int var4 = i(var0, var2);
      Object var6 = j[var4];
      Object var10000 = var6;

      try {
         if (var10000 instanceof String) {
            var5 = Class.forName(k[var4].replace("net/47OQ", "net/ModuleBase").replace("net.47OQ", "net.ModuleBase").replace("net/47R3", "net/ModuleRegistry").replace("net.47R3", "net.ModuleRegistry").replace("net/40U", "net/RegistryContext").replace("net.40U", "net.RegistryContext").replace("net/47C2", "net/Teams").replace("net.47C2", "net.Teams").replace("net/47C1", "net/BowAimbot").replace("net.47C1", "net.BowAimbot").replace("net/47CG", "net/TargetArea").replace("net.47CG", "net.TargetArea").replace("net/47CY", "net/KillAura").replace("net.47CY", "net.KillAura").replace("net/47CD", "net/AntiBot").replace("net.47CD", "net.AntiBot").replace("net/47CS", "net/LagRange").replace("net.47CS", "net.LagRange").replace("net/47Ci", "net/Criticals").replace("net.47Ci", "net.Criticals").replace("launcher/46g", "launcher/ArbitraryPrecisionInteger").replace("launcher.46g", "launcher.ArbitraryPrecisionInteger").replace("net/40", "net/Class_40").replace("net.40", "net.Class_40").replace("net/41", "net/Class_41").replace("net.41", "net.Class_41").replace("net/42", "net/Class_42").replace("net.42", "net.Class_42").replace("net/43", "net/Class_43").replace("net.43", "net.Class_43").replace("net/44", "net/Class_44").replace("net.44", "net.Class_44").replace("net/45", "net/Class_45").replace("net.45", "net.Class_45").replace("net/46", "net/Class_46").replace("net.46", "net.Class_46").replace("net/47", "net/Class_47").replace("net.47", "net.Class_47").replace("net/47rM", "net/Class_47rM").replace("net.47rM", "net.Class_47rM").replace("net/49", "net/Class_49").replace("net.49", "net.Class_49").replace("net/4f", "net/Class_4f").replace("net.4f", "net.Class_4f").replace("net/472z", "net/Class_472z").replace("net.472z", "net.Class_472z").replace("net/47Fi", "net/Class_47Fi").replace("net.47Fi", "net.Class_47Fi").replace("net/471j", "net/Class_471j").replace("net.471j", "net.Class_471j").replace("net/47M0", "net/Class_47M0").replace("net.47M0", "net.Class_47M0").replace("net/472l", "net/Class_472l").replace("net.472l", "net.Class_472l"));
            j[var4] = var5;
            return var5;
         }
      } catch (Exception var8) {
         throw new RuntimeException(var8.toString());
      }

      return (Class)var6;
   }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Override
    public void method_43(List var1_1, long var2_2) {
        long v0;
        long var4_3;
        long var6_4;
        long var8_5;
        int var10_6;
        double var11_7;
        double cfr_temp_0;
        double cfr_temp_1;
        int v1;
        Class_47rM v4;
        Class_47rM v9;
        double v10;
        long v11;
        long v12;
        long v13;
        block23: {
            block25: {
                block26: {
                    block21: {
                        block24: {
                            block22: {
                                v0 = var2_2;
                                var4_3 = v0 ^ 78132077528566L;
                                var6_4 = v0 ^ 54889684730193L;
                                var8_5 = v0 ^ 125520247469773L;
                                var10_6 = Class_472l.c("\u00f6", (long)-8287158557523915911L, (long)var2_2);
                                var11_7 = Class_472l.c("\u00a5", (Object)this, (Object)var1_1, (long)var4_3, (long)-8287099815710959930L, (long)var2_2);
                                try {
                                    try {
                                        try {
                                            try {
                                                try {
                                                    cfr_temp_0 = var11_7 - 0.45;
                                                    v1 = cfr_temp_0 == 0 ? 0 : (cfr_temp_0 < 0 ? -1 : 1);
                                                    if (var10_6 != 0) break block21;
                                                    if (v1 >= 0) {
                                                        break block22;
                                                    }
                                                }
                                                catch (RuntimeException v2) {
                                                    throw Class_472l.b(v2);
                                                }
                                                Class_472l.c("\u00a5", (Object)this.field_46, (double)1.0, (long)var8_5, (long)-8286744537772285825L, (long)var2_2);
                                                if (var2_2 <= 0L) break block22;
                                            }
                                            catch (RuntimeException v3) {
                                                throw Class_472l.b(v3);
                                            }
                                            v4 = this.field_46;
                                        }
                                        catch (RuntimeException v5) {
                                            throw Class_472l.b(v5);
                                        }
                                        if (var10_6 == 0 && !(Class_472l.c("\u00a5", (Object)v4, (long)-8286685809141557410L, (long)var2_2) >= 3.0)) break block23;
                                    }
                                    catch (RuntimeException v6) {
                                        throw Class_472l.b(v6);
                                    }
                                    v4 = this.field_46;
                                }
                                catch (RuntimeException v7) {
                                    throw Class_472l.b(v7);
                                }
lbl39:
                                // 2 sources

                                Class_472l.c("\u00a5", (Object)v4, (Object)Class_47M0.NONE, (long)var6_4, (Object)((double)var11_7 + ""), (long)-8286966610273941255L, (long)var2_2);
                            }
                            try {
                                try {
                                    try {
                                        if (var2_2 < 0L) break block24;
                                        if (var10_6 == 0) break block23;
                                    }
                                    catch (RuntimeException v8) {
                                        throw Class_472l.b(v8);
                                    }
lbl49:
                                    // 2 sources

                                    v9 = this.field_46;
                                    v10 /* !! */  = -0.5;
                                    v11 = var8_5;
                                    v12 = -8286744537772285825L;
                                    v13 = var2_2;
                                    if (var2_2 < 0L) break block25;
                                }
                                catch (RuntimeException v14) {
                                    throw Class_472l.b(v14);
                                }
                                Class_472l.c("\u00a5", (Object)v9, (double)v10 /* !! */ , (long)v11, (long)v12, (long)v13);
                            }
                            catch (RuntimeException v15) {
                                throw Class_472l.b(v15);
                            }
                        }
                        try {
                            v9 = this.field_46;
                            if (var10_6 != 0) break block26;
                            cfr_temp_1 = Class_472l.c("\u00a5", (Object)v9, (long)-8286685809141557410L, (long)var2_2) - 0.0;
                            v1 = cfr_temp_1 == 0 ? 0 : (cfr_temp_1 < 0 ? -1 : 1);
                        }
                        catch (RuntimeException v16) {
                            throw Class_472l.b(v16);
                        }
                    }
                    try {
                        if (v1 >= 0) break block23;
                        v9 = this.field_46;
                    }
                    catch (RuntimeException v17) {
                        throw Class_472l.b(v17);
                    }
                }
                v10 /* !! */  = (double)(-Class_472l.c("\u00a5", (Object)this.field_46, (long)-8286685809141557410L, (long)var2_2));
                v11 = var8_5;
                v12 = -8286744537772285825L;
                v13 = var2_2;
            }
            Class_472l.c("\u00a5", (Object)v9, (double)v10 /* !! */ , (long)v11, (long)v12, (long)v13);
        }
    }

   public Class_472l(String var1, Class_471j var2, long var3) {
      super(var1, var2, (c ^ var3) ^ 51503604596438L, 100);
      this.field_46 = new Class_47rM(310, e, Class_47Fi.AUTOCLICKER);
   }

   static {
      c();
      try {
      long var0 = c ^ 83698745952750L;
      Cipher var2;
      Cipher var10000 = var2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};
      int var3 = 1;

      try {
         while (var3 < 8) {
            var10003[var3] = (byte)(var0 << var3 * 8 >>> 56);
            var3++;
         }
      } catch (RuntimeException var5) {
         throw b(var5);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      byte[] var4 = var2.doFinal("»¢\u0014\u0019È\u0004R÷\f:\u0012{\u0082Ý¥¾îP¹\u008d¯v\u0090Kû\u009dÖ4ü\u00953Ä".getBytes("ISO-8859-1"));
      String var6 = b(var4).intern();
      byte var10001 = -1;
      e = var6;
      } catch (Exception exception) {
         throw Class_472l.<RuntimeException, RuntimeException>throwUnchecked(exception);
      }
   }

   private static Object invokeRecovered(Method method, Object receiver, Object... arguments) {
      try {
         MethodHandle target = MethodHandles.lookup().unreflect(method);
         if (!java.lang.reflect.Modifier.isStatic(method.getModifiers())) {
            target = target.bindTo(receiver);
         }
         return target.invokeWithArguments(arguments);
      } catch (Throwable throwable) {
         return Class_472l.<RuntimeException, Object>throwUnchecked(throwable);
      }
   }

   @SuppressWarnings("unchecked")
   private static <T extends Throwable, R> R throwUnchecked(Throwable throwable) throws T {
      throw (T)throwable;
   }

   private static int c(String name, long encoded, long key) {
      return ((Number)invokeRecovered(l(encoded, key), null)).intValue();
   }

   private static double c(String name, Object receiver, Object argument, long argumentKey, long encoded, long key) {
      return ((Number)invokeRecovered(l(encoded, key), receiver, argument, argumentKey)).doubleValue();
   }

   private static void c(String name, Object receiver, double value, long argumentKey, long encoded, long key) {
      invokeRecovered(l(encoded, key), receiver, value, argumentKey);
   }

   private static double c(String name, Object receiver, long encoded, long key) {
      return ((Number)invokeRecovered(l(encoded, key), receiver)).doubleValue();
   }

   private static void c(String name, Object receiver, Object value, long argumentKey, Object message, long encoded, long key) {
      invokeRecovered(l(encoded, key), receiver, value, argumentKey, message);
   }

   private static Object c(Lookup var0, MutableCallSite var1, String var2, MethodType var3, Object[] var4) {
      int var5 = var4.length - 2;
      long var6 = (Long)var4[var5];
      long var9 = (Long)var4[++var5];
      MethodHandle var8 = c(var0, var1, var2, var3, var6, var9);
      var1.setTarget(MethodHandles.explicitCastArguments(var8, var3));
      try {
         return (Object)var8.asSpreader(Object[].class, var4.length).invoke(var4);
      } catch (Throwable throwable) {
         return Class_472l.<RuntimeException, Object>throwUnchecked(throwable);
      }
   }

   private static CallSite c(Lookup var0, String var1, MethodType var2) {
      MutableCallSite var3 = new MutableCallSite(var2);

      try {
         MethodHandle bootstrap = var0.findStatic(
            Class_472l.class,
            "c",
            MethodType.methodType(Object.class, Lookup.class, MutableCallSite.class, String.class, MethodType.class, Object[].class)
         );
         var3.setTarget(
            MethodHandles.explicitCastArguments(
               MethodHandles.insertArguments(bootstrap.asCollector(Object[].class, var2.parameterCount()), 0, var0, var3, var1, var2), var2
            )
         );
         return var3;
      } catch (Exception var5) {
         throw new RuntimeException("net/472l" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static void c() {
      j[0] = "~6\u00159Q'b\u001e";
      j[1] = double.class;
      k[1] = "java/lang/Double";
      j[2] = long.class;
      k[2] = "java/lang/Long";
      j[3] = void.class;
      k[3] = "java/lang/Void";
      j[4] = "j5{JFb6*";
      j[5] = "\u001a:KK\u0006,\u00042Q\u0004k6\u001c7XI\\0\u001f5";
      j[6] = int.class;
      k[6] = "java/lang/Integer";
      j[7] = "15\u0012\u0001N\u0004\u0012`";
      j[8] = "`6P\u001aD[k9AU9Cx>H\u001c";
      j[9] = "!_\u001b\u0005;C*P\nJZM![\u000e\u0010";
      j[10] = "3Vq8<\u0013uFrI;J\r\u001fvyv\u0002m\u001fv(<s";
      j[11] = "G_\u0004Sx\u001c\u0001O\u0007\"\u007fAy\u0016\u0003\u00122\r\u0019\u0016\u0003Cx|@J\u0003YzD\u0001MZSCF\u0016H]_)\u0017\u001eSX\"";
      j[12] = "=De \u001b\u001ex\u0004rq \u001b?8%}I_8\u0004)?R\\\u0007\u0001|~[\u001e?@{'Q'>\u0007)0QG>\u0007xz ";
      j[13] = "\rJA{SdH\nV*ha\u00076\u0001fU:\t\u000bY \u0006'7";
      j[14] = ">\u0001o>S4x\u0011lOTe\u0000Lo3\u00031q\u001e34\bT9\u0014h4Qlx\u00131>hif\u00118rV11OgOR;g\u0010*%\u00033|\u0015W";
   }

    /*
     * Loose catch block
     */
    private static MethodHandle c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, MethodType methodType, long l, long l2) {
        char c2 = string.charAt(0);
        MethodHandle methodHandle = null;
        Field field = null;
        Method method = null;
        try {
            block18: {
                block13: {
                    block12: {
                        block16: {
                            if (c2 == 'A') break block12;
                            if (c2 == 'P') break block12;

                        }
                        try {
                            block17: {
                                if (c2 == '\u00d0') break block12;

                            }
                            if (c2 != 's') break block13;
                        }
                        catch (Exception exception) {
                            throw Class_472l.b(exception);
                        }
                    }
                    field = Class_472l.k(l, l2);
                    Class<?> clazz = field.getDeclaringClass();
                    String string2 = field.getName();
                    Class<?> clazz2 = field.getType();
                    methodHandle = c2 == 'A' ? lookup.findGetter(clazz, string2, clazz2) : (c2 == 'P' ? lookup.findSetter(clazz, string2, clazz2) : (c2 == '\u00d0' ? lookup.findStaticGetter(clazz, string2, clazz2) : lookup.findStaticSetter(clazz, string2, clazz2)));
                    break block18;
                }
                method = Class_472l.l(l, l2);
                Class<?> clazz = method.getDeclaringClass();
                String string3 = method.getName();
                MethodType methodType2 = MethodType.methodType(method.getReturnType(), method.getParameterTypes());
                methodHandle = c2 == '\u00a5' ? lookup.findVirtual(clazz, string3, methodType2) : (c2 == '\u00f6' ? lookup.findStatic(clazz, string3, methodType2) : lookup.findSpecial(clazz, string3, methodType2, clazz));
            }
            return MethodHandles.dropArguments(methodHandle, methodType.parameterCount() - 2, Long.TYPE, Long.TYPE);
        }
        catch (Exception exception) {
            String string4;
            StringBuilder stringBuilder;
            StringBuilder stringBuilder2;
            block15: {
                block14: {
                    stringBuilder2 = new StringBuilder();
                    try {
                        stringBuilder = stringBuilder2.append(exception.getClass().getName()).append(" : ");
                        if (field == null) break block14;
                        string4 = field.toString();
                        break block15;
                    }
                    catch (Exception exception2) {
                        throw Class_472l.b(exception2);
                    }
                }
                try {
                    string4 = method != null ? method.toString() : " null ";
                }
                catch (Exception exception3) {
                    throw Class_472l.b(exception3);
                }
            }
            stringBuilder.append(string4).append(" : ").append(exception.toString());
            throw new RuntimeException(stringBuilder2.toString());
        }
    }
}
