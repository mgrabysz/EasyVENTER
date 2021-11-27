import database.EasyVENT;
import database.NewUserData;
import userInterface.MainMenuFrame;
import userInterface.WelcomeFrame;

public class Main {
    public static void main(String[] args) {
        // Create EasyVENT object
        EasyVENT easyVENT = new EasyVENT();
        // boolean choosen = false;
        //to co tu zrobiłem w debugu działa, a podczas runu się zawiesza idk why??
//        while (!choosen)
//        {
//            if (easyVENT.menuframe.decision == 2)
//            {
//                System.out.println("Wylogowywanie");
//                choosen = true;
//            }
//            else if (easyVENT.menuframe.decision == 1)
//            {
//                if (easyVENT.user_type == "client")
//                {
//                    System.out.println("Client wants to explore events");
//                }
//                else
//                {
//                    System.out.println("Organizer wants to add new events");
//                }
//                choosen = true;
//            }
//        }

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
}
