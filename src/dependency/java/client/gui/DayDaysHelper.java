package client.gui;

import client.util.TimeHelper;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayDaysHelper {
   public static long computeTimestamp(String text) {
      long value = 0L;
      String[] textArray = new String[]{
         "(\\d+)\\s*(day|days|dag|dagen|jour|jours|Tag|Tage|天|день|дни)",
         "(\\d+)\\s*(hour|hours|heure|heures|Stunde|Stunden|小时|час|часов)",
         "(\\d+)\\s*(minute|minutes|minuut|minuten|minute|минута|минут)"
      };
      int length = textArray.length;

      for (int index = 0; index < length; index++) {
         Matcher matcher = Pattern.compile(textArray[index], 2).matcher(text);
         if (matcher.find()) {
            int count = Integer.parseInt(matcher.group(1));
            String text2 = matcher.group(2).toLowerCase();
            switch (text2) {
               case "day":
               case "days":
               case "dag":
               case "dagen":
               case "jour":
               case "jours":
               case "tag":
               case "tage":
               case "天":
               case "день":
               case "дни":
                  value += TimeUnit.DAYS.toMillis((long)count);
                  break;
               case "hour":
               case "hours":
               case "heure":
               case "heures":
               case "stunde":
               case "stunden":
               case "小时":
               case "час":
               case "часов":
                  value += TimeUnit.HOURS.toMillis((long)count);
                  break;
               case "minute":
               case "minutes":
               case "minuut":
               case "minuten":
               case "минута":
               case "минут":
                  value += TimeUnit.MINUTES.toMillis((long)count);
            }
         }
      }

      return value;
   }

   public static boolean matchesText(String text) {
      return Pattern.compile(
            "(hacking|hack|ban|client|banned|forbidden|prohibited|barred|restricted|prohibido|vetado|vedado|prohibición|censurado|禁止|受禁|被禁止|प्रतिबंधित|निषिद्ध|मना|ممنوع|محظور|ممنوعة|নিষিদ্ধ|নিষেধিত|বাধাযুক্ত|proibido|vetado|interditado|banido|censurado|Запрещено|Под запретом|Отменен|禁止|禁じられた|verboten|untersagt|gesperrt|verhindert|ausgeschlossen|gebannt|interdit|banni|proscrit|exclu|censuré|금지된|차단된|금지|제한된|yasak|engelli|bloke|men edilmiş|vietato|proibito|censurato|bandito|impedito|bị cấm|bị ngăn chặn|bị chặn|bị cấm vận|zakazany|zablokowany|wykluczony|uniemożliwiony|verboden|geblokkeerd|uitgesloten|geweerd|заборонено|заблоковано|виключено|заборонено|interzis|suspendat|blocat|interdicție|απαγορευμένο|αποκλεισμένο|αποκλείεται|απαγορευμένος|zakázáno|blokováno|vyřazeno|omezeno|ถูกห้าม|ถูกปิดกั้น|ถูกระงับ|ห้าม|dilarang|diblokir|dihentikan|terlarang|ipinagbawal|binawalan|hinarang|nasuspinde|dilarang|disekat|terhalang|larangan|tiltott|letiltott|megtiltott|korlátozott|förbjuden|blockerad|avstängd|spärrad|forbudt|blokeret|suspenderet|blokeret|kielletty|estetty|rajoitettu|estetty|forbudt|blokkert|suspendert|stengt|заборонений|заборонено|заборонено|заборонено|забаронена|забаронены|забаронены|забаронены|tilintiwa|inabran|pinigilan|ipinagbawal|suku|dilarang|dilarang|dilarang|dilarang|ممنوع|ممنوع|ممنوع|ممنوع|ممنوع|ممنوع|ممنوع|ممنوع|ممنوع|ممنوع|禁止|禁止|禁止|禁止|禁止|禁止|禁止|禁止|禁止|禁止)",
            2
         )
         .matcher(text)
         .find();
   }
}
