package kowale;
import kowale.database.EasyVENT;
import kowale.event.Event;
import kowale.userInterface.*;
import java.util.Vector;


public class Main {
    public static void main(String[] args) {

        // String[][] data = {
        //     { "Meczyk jakiś", "Bydgoszcz", "35.19.2022 25:72", "120" },
        //     { "Ludzie biegający w kółko", "Warszafka", "48.17.2023 29:81", "200" }
        // };
        // String[] columnNames = {"Name", "Location", "Date and time", "Tickets sold"};
        int number = 10;   // dowolna liczba sektorów
        String[][] sectors = new String[number][3];
        for (int i=0; i<number; ++i) {
            String iStr = String.valueOf(i+1);
            String[] sector = {iStr, "0", "0", "0"};
            sectors[i] = sector;
        }

        new InputSectorDataFrame(sectors);

    }



        // Create EasyVENT object
        // EasyVENT easyVENT = new EasyVENT();
//        // Create user who is an organiser
//        NewUserData user1 = new NewUserData("seanMurphy", "murphylaw123", 'O');
//
//        // put his details into database = Register user
//        boolean registered_successfully = easyVENT.create_new_user(user1);
//
//        if(registered_successfully){ // if user has been registered successfully
//
//            // Create user with different password than the one assigned to this login
//            NewUserData user2 = new NewUserData("seanMurphy", "murphylaw321", 'O');
//
//            boolean result1 = easyVENT.log_in(user2);
//
//            if(!result1){
//                System.out.println("Login of user: "+user2.login+ " failed.\nRetrying...");
//            }
//
//            // Create user with the same password and login
//            NewUserData user3 = new NewUserData("seanMurphy", "murphylaw123", 'O');
//
//            boolean result3 = easyVENT.log_in(user3);
//
//            if(!result3){
//                System.out.println("Login of user: "+user3.login+ " failed.");
//            }
//            else{
//                System.out.println("Login of user: "+user3.login+ " was successful. The rest of user's data: ");
//                System.out.println(user3.toString());
//            }
//
//        }
//        else{
//            System.out.println("Problem occurred during user creation");
//        }
//
//

    }

