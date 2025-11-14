package comp2450.UI;

import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class CreateCalendarDisplay {

    final private Scanner sc;

    public CreateCalendarDisplay() {
        sc = new Scanner(System.in);
    }

    public LocalDateTime createCalendar() {

        try {
            System.out.println(FigletFont.convertOneLine("Date Creator!"));
        } catch (IOException ignored) { }

        System.out.println("Please enter the date: ");

        LocalDateTime date = null;

        do {
            System.out.println("\nEnter: YEAR MONTH DAY HOUR MINUTE SECOND");
            System.out.println("Example: 2025 11 14 15 30 00");

            String line = sc.nextLine().trim();

            String[] parts = line.split("\\s+");


            if (parts.length != 6) {
                System.out.println("Please enter exactly 6 numbers separated by spaces.");
                date = null;
            }else{
                try {
                       int year   = Integer.parseInt(parts[0]);
                       int month  = Integer.parseInt(parts[1]);
                       int day    = Integer.parseInt(parts[2]);
                       int hour   = Integer.parseInt(parts[3]);
                       int minute = Integer.parseInt(parts[4]);
                       int second = Integer.parseInt(parts[5]);
                       date = LocalDateTime.of(year, month, day, hour, minute, second);
                }

                catch (NumberFormatException nfe) {
                    System.out.println("All values must be STRICTLY whole numbers.");
                    date = null;
                }
                catch (DateTimeException dte) {
                    System.out.println("Invalid Entry: " + dte.getMessage());
                    date = null;
                }
            }

        } while (date == null);

        return date;
    }


}
//public LocalDateTime createCalendar(){
//
//    LocalDateTime cal = null;
//    try {
//        System.out.println(FigletFont.convertOneLine("Date Creator!"));
//    } catch (IOException ignored) { }
//
//    do{
//
//        try{
//
//            System.out.println("Enter year, month, day, hour (0-23), minute (0-59), and second (0-59) separated by spaces:");
//            System.out.println("Note: Use 24-hour format for hours. Example: 2025 10 12 14 30 00");
//
//            int year = sc.nextInt();
//            int month = sc.nextInt();
//            int day = sc.nextInt();
//
//            int hour = sc.nextInt();
//            int minute = sc.nextInt();
//            int second = sc.nextInt();
//            sc.nextLine();
//            cal = LocalDateTime.of(year, month, day, hour, minute, second);
//
//        }
//        catch(InputMismatchException ime){
//            System.out.println("Note: Use 24-hour format for hours: please enter only numbers");
//            sc.nextLine();
//            cal = null;
//        }
//        catch(DateTimeException dte){
//            System.out.println("Invalid entry: "+dte.getMessage());
//            sc.nextLine();
//            cal = null;
//        }
//    }while(cal == null);
//    return cal;
//}
