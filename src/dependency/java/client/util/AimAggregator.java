package client.util;

import client.event.DefinedMacroIndirectListener;
import client.event.PreUpdateObserver;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AimAggregator {
   public boolean enabled = true;
   public Deque deque = new ArrayDeque();
   public Deque deque2 = new ArrayDeque();
   public Map map = new HashMap();
   public static final Pattern pattern = Pattern.compile("\\s*#\\s*(\\w+)\\s*(.*)");
   public static final Pattern pattern2 = Pattern.compile("defined\\s+(\\w+)");
   public static final Pattern pattern3 = Pattern.compile("defined\\s*\\(\\s*(\\w+)\\s*\\)");
   public static final Pattern pattern4 = Pattern.compile("(\\w+)");
   public static final String labelText = "define";
   public static final String labelText2 = "undef";
   public static final String labelText3 = "ifdef";
   public static final String labelText4 = "ifndef";
   public static final String labelText5 = "if";
   public static final String labelText6 = "else";
   public static final String labelText7 = "elif";
   public static final String labelText8 = "endif";
   public static final List list = Arrays.asList("define", "undef", "ifdef", "ifndef", "if", "else", "elif", "endif");

   private boolean matchesText2(String text) {
      String text2 = pattern2.matcher(text).replaceAll("defined_$1");
      text2 = pattern3.matcher(text2).replaceAll("defined_$1");
      int count = 0;

      boolean flag;
      do {
         flag = false;
         Matcher matcher = pattern4.matcher(text2);

         while (matcher.find()) {
            String text3 = matcher.group();
            if (text3.length() > 0) {
               char character = text3.charAt(0);
               if ((Character.isLetter(character) || character == '_') && this.map.containsKey(text3)) {
                  String text4 = (String)this.map.get(text3);
                  if (text4 == null) {
                     text4 = "1";
                  }

                  int count2 = matcher.start();
                  int count3 = matcher.end();
                  text2 = text2.substring(0, count2) + " " + text4 + " " + text2.substring(count3);
                  flag = true;
                  count++;
                  break;
               }
            }
         }
      } while (flag && count < 100);

      if (count >= 100) {
         BuildManager.applyText3("Too many iterations: " + count + ", when resolving: " + text2);
         return true;
      } else {
         try {
            DefinedMacroIndirectListener definedMacroIndirectListener = new DefinedMacroIndirectListener(this.map);
            PreUpdateObserver preUpdateObserver = new TargetSorter(definedMacroIndirectListener).resolvePreUpdateObserver(text2);
            if (preUpdateObserver.getBOOL() == AimSorter.BOOL) {
               return ((TargetObserver)preUpdateObserver).isActive();
            } else if (preUpdateObserver.getBOOL() == AimSorter.FLOAT) {
               return ((AimValidator)preUpdateObserver).getFactor() != 0.0F;
            } else {
               throw new AuxiliaryExceptionAdapter("Not a boolean or float expression: " + preUpdateObserver.getBOOL());
            }
         } catch (AuxiliaryExceptionAdapter exception) {
            BuildManager.applyText3("Invalid macro expression: " + text2);
            BuildManager.applyText3("Error: " + exception.getMessage());
            return false;
         }
      }
   }

   public static boolean matchesTextByText(String text) {
      Matcher matcher = pattern.matcher(text);
      if (!matcher.matches()) {
         return false;
      } else {
         String text2 = matcher.group(1);
         return list.contains(text2);
      }
   }

   private void applyText(String text, String text2) {
      StringTokenizer stringTokenizer = new StringTokenizer(text2, " \t");
      String text3 = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "";
      String text4 = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken("").trim() : "";
      if (text.equals("define")) {
         this.map.put(text3, text4);
      } else if (text.equals("undef")) {
         this.map.remove(text3);
      } else if (text.equals("ifdef")) {
         boolean flag = this.map.containsKey(text3);
         this.deque.add(flag);
         this.deque2.add(flag);
      } else if (text.equals("ifndef")) {
         boolean flag2 = !this.map.containsKey(text3);
         this.deque.add(flag2);
         this.deque2.add(flag2);
      } else if (text.equals("if")) {
         boolean flag3 = this.matchesText2(text2);
         this.deque.add(flag3);
         this.deque2.add(flag3);
      } else if (!this.deque.isEmpty()) {
         if (text.equals("elif")) {
            this.deque.removeLast();
            boolean flag4 = (Boolean)this.deque2.removeLast();
            if (flag4) {
               this.deque.add(false);
               this.deque2.add(flag4);
            } else {
               boolean flag5 = this.matchesText2(text2);
               this.deque.add(flag5);
               this.deque2.add(flag5);
            }
         } else if (text.equals("else")) {
            this.deque.removeLast();
            boolean flag6 = !(Boolean)this.deque2.removeLast();
            this.deque.add(flag6);
            this.deque2.add(true);
         } else if (text.equals("endif")) {
            this.deque.removeLast();
            this.deque2.removeLast();
         }
      }
   }

   public boolean matchesText(String text) {
      Matcher matcher = pattern.matcher(text);
      if (!matcher.matches()) {
         return this.enabled;
      } else {
         String text2 = matcher.group(1);
         String text3 = matcher.group(2);
         int count = text3.indexOf("//");
         if (count >= 0) {
            text3 = text3.substring(0, count);
         }

         boolean enabled2 = this.enabled;
         this.applyText(text2, text3);
         this.enabled = !this.deque.contains(Boolean.FALSE);
         return this.enabled || enabled2;
      }
   }
}
