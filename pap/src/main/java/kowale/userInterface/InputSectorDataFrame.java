package kowale.userInterface;

import java.util.Vector;

// Przykładowe parametry dla konstruktora:
// int number = 10;   // dowolna liczba sektorów
// String[][] sectors = new String[number][3];

// for (int i=0; i<number; ++i) {
//     String iStr = String.valueOf(i+1);
//     String[] sector = {iStr, "0", "0", "0"};
//     sectors[i] = sector;
// }




public class InputSectorDataFrame extends BasicTableFrame {
    /*
    Frame to set number of seats and ticket price of each sector
    Constructor takes parameter:
    :data: String[][]
        table of data about sectors
    */


    public InputSectorDataFrame(String[][] data) {
        super(data, new String[]{"Number of sector", "Number of seats", "Ticket price"} , "Confirm", true);
    }

}