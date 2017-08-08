import java.util.*;
import java.util.stream.Collector;

/**
 * Created by Adrian on 28/08/2015.
 */
public class Oblig1 {

    // OPPGAVE 1

    /*i dette tilfellet får man (2n - 1) + 1 sammenlikninger.
    n er tabellen sin lengde.*/

    public static int maks(int[] a) {

        int arrayLength = a.length;
        if (arrayLength == 0) {
            throw new NoSuchElementException("Tomt array");
        }
        int m = a[0];

        int mellom;
        for (int i = 1; i < a.length; i++) {
            mellom = a[i];
            if (mellom > m) {
                m = mellom;
            } else {
                a[i - 1] = mellom;
                a[i] = m;
            }
        }
        return a[arrayLength - 1];
    }

    /*Flest ombyttinger skjer når tabellen er sortert fra størt til minst.
    Færrest skjer når tabellen er sortert fra minst til størst.

    Gjorde forsøk der jeg hadde en løkke som genererte en ny tabell på 1000000
    tall for hver gjennomgang, og fant maks av den. Løkka gjorde dette 1000 ganger
    per metode så jeg kunne få et presist snitt. Forskjelllen var veldig liten,
    med maks() på 1.586 ms og ombyttinger(int[] a) på 1.552 ms*/


    public static int ombyttinger(int[] a) {
        int arrayLength = a.length;
        int ombyttinger = 0;

        if (arrayLength == 0) {
            throw new NoSuchElementException("Tomt array");
        }

        int m = a[0];

        if (arrayLength == 1) {
            return m;
        }

        int mellom;

        for (int i = 1; i < a.length; i++) {
            mellom = a[i];
            if (mellom > m) {
                m = mellom;
            } else {
                a[i - 1] = mellom;
                a[i] = m;
                ombyttinger++;
            }
        }
        return ombyttinger;
    }

    // END OPPGAVE 1

    // OPPGAVE 2

    /*
    Antallet sammenlikninger at mener vi er summen av x plusset sammen, det man går
    fra x = 0, til x = n. Den er av første orden.

    I forhold til våre tester får vi at (n^2)/4 gir gjennomsnittet for antall ombyttinger.
     */

    public static void sortering(int[] a) {

        int arrayLength = a.length;
        if (arrayLength <= 1) {
            return;
        }
        int ombyttinger;
        int flytter;
        int neste;

        for (int i = arrayLength - 1; i > 0; i--) {
            flytter = a[0];
            ombyttinger = 0;
            for (int j = 1; j <= i; j++) {
                neste = a[j];
                if (neste > flytter) {
                    flytter = neste;
                } else {
                    a[j - 1] = neste;
                    a[j] = flytter;
                    ombyttinger++;
                }
            }
            if (ombyttinger == 0) {
                return;
            }
        }
    }

    // END OPPGAVE 2

    // OPPGAVE 3

    public static int medlemsnummer(){
        byte com = 0;
        int count = 0;
        int[] full = {5,5,5,5,5};
        int[] ID = new int[5];
        ID[0] = 1;
        int index = 4;
        int digit = 0;
        while(!Arrays.equals(ID,full)){
            for(int i = 0;i <= ID.length-3; i++){
                for(int j = i+1; j <=ID.length-2;j++){
                    if(ID[i]==ID[j]){
                        if(com==0){
                            com++;
                            digit = ID[i];
                        }else if(ID[i] == digit){
                            com = 2;
                        }else{
                            com = 3;
                        }
                    }
                }
            }
            for(int i = 0;i < 6;i++){
                ID[index] = i;
            }

            switch (com){
                case 1:count++;
                    break;
                case 2:count+=6;
                    break;
                case 3:count+=2;
                    break;
            }
            com = 0;

            for(int i = index;i >= 0; i-- ){
                if(ID[i]<5){
                    ID[i]++;
                    for(int j = i+1;j<=index;j++){
                        ID[j] = 0;
                    }
                    break;
                }
            }
        }
        return 6480-count;
    }

    // END OPPGAVE 3

    // OPPGAVE 4

    public static int antallUlikeUsortert(int[] a) {

        if (a.length == 0) {
            return 0;
        }
        if (a.length == 1) {
            return 1;
        }
        int maks = a[0];

        for (int i = 1; i < a.length; i++) {
            if (a[i] > maks) {
                maks = a[i];
            }
        }

        int count = 0;

        for (int i = 0; i <= maks; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[j] == i) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    // END OPPGAVE 4

    // OPPGAVE 5

    public static void rotasjon(char[] a) {

        int arrayLength = a.length;

        if (arrayLength <= 1) {
            return;
        }

        char runner = a[arrayLength - 1];
        char keeper;

        for (int i = 0; i < arrayLength; i++) {
            keeper = a[i];
            a[i] = runner;
            runner = keeper;
        }
    }

    // END OPPGAVE 5

    // OPPGAVE 6

    public static void rotasjon(char[] a, int k) {
        int arrayLength = a.length;

        if (arrayLength <= 1 || k == 0 || k % arrayLength == 0) {
            return;
        }

        int flyttinger = k % arrayLength;
        if (flyttinger < 0) {
            flyttinger = arrayLength + flyttinger;
        }

        int next = flyttinger;

        char keeper;
        char runner = a[0];

        if (flyttinger % 2 != 0 && arrayLength % 2 == 0 || flyttinger % 2 == 0 && arrayLength % 2 != 0) {

            for (int i = 0; i < arrayLength; i++) {
                keeper = a[next];
                a[next] = runner;
                runner = keeper;
                next = (flyttinger + next) % arrayLength;
            }
        } else {
            char keeper2;
            char runner2 = a[1];

            for (int i = 0; i < arrayLength / 2; i++) {
                keeper = a[next];
                keeper2 = a[next + 1];
                a[next] = runner;
                a[next + 1] = runner2;
                runner = keeper;
                runner2 = keeper2;
                next = (flyttinger + next) % arrayLength;
            }
        }
    }

    // END OPPGAVE 6

    // OPPGAVE 7

    public static String flett(String s, String t) {
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        int sLength = a.length;
        int tLength = b.length;
        int length = (sLength > tLength) ? sLength : tLength;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            if (sLength - 1 >= i) {
                builder.append(a[i]);
            }
            if (tLength - 1 >= i) {
                builder.append(b[i]);
            }
        }
        return builder.toString();
    }

    public static String flett(String... s) {
        if (s.length == 0) {
            return "";
        }
        int length = s[0].length();
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i < s.length; i++) {
            if (s[i].length() > length) {
                length = s[i].length();
            }
        }

        char[] a;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < s.length; j++) {
                a = s[j].toCharArray();
                if (i < a.length) {
                    builder.append(a[i]);
                }
            }
        }
        return builder.toString();
    }

    // END OPPGAVE 7

    // OPPGAVE 8

    public static int[] indeks(int[] a) {
        int[] indeks = new int[]{0, 1, 2};

        int temp;

        if (a[0] > a[2]) {
            temp = indeks[0];
            indeks[0] = indeks[2];
            indeks[2] = temp;
        }
        if (a[indeks[0]] > a[indeks[1]]) {
            temp = indeks[0];
            indeks[0] = indeks[1];
            indeks[1] = temp;
        }
        if (a[indeks[1]] > a[indeks[2]]) {
            temp = indeks[1];
            indeks[1] = indeks[2];
            indeks[2] = temp;
        }

        return indeks;
    }

    public static int[] tredjeMin(int[] a) {
        if (a.length < 3) {
            throw new NoSuchElementException("Array er for kort");
        }
        int[] b = indeks(new int[]{a[0], a[1], a[2]});
        int min = b[0], nextmin = b[1], lastmin = b[2];

        for (int i = 3; i < a.length; i++) {
            if (a[i] < a[min]) {
                lastmin = nextmin;
                nextmin = min;
                min = i;
                continue;
            } else if (a[i] < a[nextmin]) {
                lastmin = nextmin;
                nextmin = i;
                continue;
            } else if (a[i] < a[lastmin]) {
                lastmin = i;
            }
        }

        return new int[]{min, nextmin, lastmin};
    }

    // END OPPGAVE 8

    // OPPGAVE 9

    public static int[] kMinst(int[] a, int k) {
        if(k < 1) {
            throw new NoSuchElementException("");
        } if(k > a.length) {
            throw new IllegalArgumentException("");
        }
        int[] returnArray = new int[k];

        for(int i = 0; i < k; i++) {
            returnArray[i] = a[i];
        }
        kvikksortering(returnArray);

        int index;

        for(int i = k; i < a.length; i++) {
            index = - (binSearch(returnArray, a[i])+1);
            if(index < returnArray.length && index >= 0) {
                for(int j = k-1; j >= index;) {
                    if(j == index) {
                        returnArray[j] = a[i];
                        break;
                    }
                    returnArray[j] = returnArray[--j];
                }
            }
        }
        return returnArray;
    }

    // END OPPGAVE 9

    // OPPGAVE 10

    /*public static boolean inneholdt(String a, String b) {
        char[] alphabetA = new char[]{'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Æ', 'Ø', 'Å'};

        int[] amountsA = new int[29];
        int[] amountsB = new int[29];

        char[] temperary = a.toCharArray();

        for(int i = 0; i < a.length())





















        return false;
    }*/

    public static boolean inneholdt(String a, String b) {

        if(a.equals("")) {
            return true;
        }
        if(b.length() < a.length() || b.equals("") && !a.equals("")) {
            return false;
        }
        Map<Character, Integer> aMap = fillMap(a);
        Map<Character, Integer> bMap = fillMap(b);

        if(bMap.size() < aMap.size()) {
            return false;
        }
        for(Map.Entry key : aMap.entrySet()) {
            if(bMap.get(key.getKey()) < aMap.get(key.getKey())) {
                return false;
            }
        }
        return true;
    }

    // END OPPGAVE 10

    // HJELPEMETODER

    private static int binSearch(int[] a, int verdi)
    {
        int v = 0, h = a.length - 1;
        while (v < h)
        {
            int m = (v + h)/2;

            if (verdi > a[m]) v = m + 1;
            else  h = m;
        }
        if (h < v || verdi < a[v]) return -(v + 1);
        else if (verdi == a[v]) return v;
        else  return -(v + 2);
    }

    private static Map fillMap(String a) {
        Map<Character, Integer> aMap = new HashMap<>();
        a.chars().forEach(c -> {
            int i = aMap.getOrDefault((char)c, 0);
            aMap.put(((char)c), ++i);
        });
        return aMap;
    }

    public static int parter(int[] a, int v, int h, int skilleverdi)
    {
        while (v <= h && a[v] < skilleverdi) v++;
        while (v <= h && a[h] >= skilleverdi) h--;

        while (true) {
            if (v < h) bytt(a,v++,h--);
            else  return v;
            while (a[v] < skilleverdi) v++;
            while (a[h] >= skilleverdi) h--;
        }
    }

    public static void kvikksortering(int[] a) {
        kvikksortering1(a, 0, a.length - 1);
    }

    private static void kvikksortering1(int[] a, int v, int h) {
        if (v >= h) return;
        int k = sParter(a, v, h, (v + h)/2);
        kvikksortering1(a, v, k - 1);
        kvikksortering1(a, k + 1, h);
    }

    public static int sParter(int[] a, int v, int h, int indeks) {
        bytt(a, indeks, h);
        int k = parter(a, v, h - 1, a[h]);
        bytt(a, k, h);
        return k;
    }

    private static void bytt(int[] a, int f, int t) {
        int b = a[f];
        a[f] = a[t];
        a[t] = b;
    }
}