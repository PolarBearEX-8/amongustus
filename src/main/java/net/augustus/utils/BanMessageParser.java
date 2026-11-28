package net.augustus.utils;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BanMessageParser {

   public static boolean isBanMessage(String str) {
      String str2 = "(hacking|hack|ban|client|banned|forbidden|prohibited|barred|restricted|prohibido|vetado|vedado|prohibición|censurado|禁止|受禁|被禁止|प्रतिबंधित|निषिद्ध|मना|ممنوع|محظور|ممنوعة|নিষিদ্ধ|নিষেধিত|বাধাযুক্ত|proibido|vetado|interditado|banido|censurado|Запрещено|Под запретом|Отменен|禁止|禁じられた|verboten|untersagt|gesperrt|verhindert|ausgeschlossen|gebannt|interdit|banni|proscrit|exclu|censuré|금지된|차단된|금지|제한된|yasak|engelli|bloke|men edilmiş|vietato|proibito|censurato|bandito|impedito|bị cấm|bị ngăn chặn|bị chặn|bị cấm vận|zakazany|zablokowany|wykluczony|uniemożliwiony|verboden|geblokkeerd|uitgesloten|geweerd|заборонено|заблоковано|виключено|заборонено|interzis|suspendat|blocat|interdicție|απαγορευμένο|αποκλεισμένο|αποκλείεται|απαγορευμένος|zakázáno|blokováno|vyřazeno|omezeno|ถูกห้าม|ถูกปิดกั้น|ถูกระงับ|ห้าม|dilarang|diblokir|dihentikan|terlarang|ipinagbawal|binawalan|hinarang|nasuspinde|dilarang|disekat|terhalang|larangan|tiltott|letiltott|megtiltott|korlátozott|förbjuden|blockerad|avstängd|spärrad|forbudt|blokeret|suspenderet|blokeret|kielletty|estetty|rajoitettu|estetty|forbudt|blokkert|suspendert|stengt|заборонений|заборонено|заборонено|заборонено|забаронена|забаронены|забаронены|забаронены|tilintiwa|inabran|pinigilan|ipinagbawal|suku|dilarang|dilarang|dilarang|dilarang|ممنوع|ممنوع|ممنوع|ممنوع|ممنوع|ممنوع|ممنوع|ممنوع|ممنوع|ممنوع|禁止|禁止|禁止|禁止|禁止|禁止|禁止|禁止|禁止|禁止)";
      return Pattern.compile(str2, 2).matcher(str).find();
   }

   public static long parseBanDurationMillis(String str) {
      long l = 0L;
      String[] strings = new String[]{
         "(\\d+)\\s*(day|days|dag|dagen|jour|jours|Tag|Tage|天|день|дни)",
         "(\\d+)\\s*(hour|hours|heure|heures|Stunde|Stunden|小时|час|часов)",
         "(\\d+)\\s*(minute|minutes|minuut|minuten|minute|минута|минут)"
      };

      for (String str2 : strings) {
         Matcher matcher = Pattern.compile(str2, 2).matcher(str);
         if (matcher.find()) {
            int n = Integer.parseInt(matcher.group(1));
            switch (matcher.group(2).toLowerCase()) {
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
                  l += TimeUnit.DAYS.toMillis((long)n);
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
                  l += TimeUnit.HOURS.toMillis((long)n);
                  break;
               case "minute":
               case "minutes":
               case "minuut":
               case "minuten":
               case "минута":
               case "минут":
                  l += TimeUnit.MINUTES.toMillis((long)n);
            }
         }
      }

      return l;
   }
}
