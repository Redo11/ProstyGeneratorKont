package pl.polskistevek.accountgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Gatherer {
    public static int number;
    public static int all;

    public static void main(String[] args) {
        //System.out.println("Welcome to Nickname and Password gatherer!");
        System.out.println("Witaj w zbieraczy Nazw i Haseł!");
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Select directory with player files [Example: User]: ");
        System.out.println("Wybierz folder z plikami graczy [Przykład: Użytkownik]: ");
        String input = scanner.nextLine();
        //System.out.println("Starting gathering passwords and nicks...");
        System.out.println("Zaczęto zbieranie haseł i nicków...");
        long time = System.currentTimeMillis();
        File path = new File(input + "/");
        File[] files = path.listFiles();
        PrintWriter zapis = null;
        PrintWriter zapis1 = null;
        PrintWriter zapis2 = null;
        try {
            zapis = new PrintWriter("hasła.txt"); //!
            zapis1 = new PrintWriter("nazwy.txt"); //!
            zapis2 = new PrintWriter("konta.txt"); //!
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        all = files.length;
        System.out.println("Znaleziono " + all + " plików. Zaczęto..."); //!
        for (File value : files) {
            if (value.isFile()) {
                String file = value.toString();
                try {
                    Scanner odczyt = new Scanner(new File(file));
                    String tekst = odczyt.nextLine();
                    odczyt.close();
                    String[] haslo = tekst.split(" ");
                    number++;
                    if (number%500 == 0){
                        System.out.println("Postęp: " + number); //!
                    }
                    try {
                        String out = haslo[3];
                        String out1 = haslo[1].replace("[", "").replace("]", "");
                        String out2 = haslo[1].replace("[", "").replace("]", "") + ":" + haslo[3];
                        zapis.println(out);
                        zapis1.println(out1);
                        zapis2.println(out2);
                    } catch (Exception e) {
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        zapis.close();
        zapis1.close();
        zapis2.close();
        long time2 = System.currentTimeMillis() - time;
        System.out.println("Liczba wygenerowanych kont: " + number); //!
        System.out.println("Zkończono w " + time2 + "ms"); //!
    }
}
