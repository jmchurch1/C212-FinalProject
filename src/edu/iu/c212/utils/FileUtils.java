package edu.iu.c212.utils;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;

import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    private static File file = new File("UserData/UserData.txt");

    // line format:
    // user_name|balance|item1,item2,item3
    // user name not allowed to contain pipe

    /**
     * Write user data to the file you provided above.
     *
     * @param users The total list of all users
     */
    public static void writeUserDataToFile(List<User> users) throws IOException {

    }

    /**
     * Read user data from the file you provided above. Return a list of Users
     */
    public static List<User> getUserDataFromFile() throws IOException {
        List<User> userList = new ArrayList<>();
        File input = file;
        Scanner sc = new Scanner(input);
        while (sc.hasNextLine()){
            // split the line into its three different parts
            // username, current balance, items
            String[] userParts = sc.nextLine().split("|");
            // get the userName
            String username = userParts[0];
            if (username.contains("_")){
                username = username.substring(0, username.indexOf("_")) + " " + username.substring(username.indexOf("_") + 1, username.length() - 1);
            }
            double userBalance = Double.parseDouble(userParts[1]);
            List<Item> userItems = new ArrayList<>();
            if (userParts.length == 3){
                String[] itemParts = userParts[2].split(",");
                for (String item : itemParts) {
                    for (Item p : Item.values()){
                        // I don't think this is the best way since enums are
                        // there for comparison to be more efficient
                        if (p.getReadableName().equals(item)){
                            userItems.add(p);
                            break;
                        }
                    }
                }
            }
            userList.add(new User(username, userBalance, userItems));
        }

        sc.close();
        
        return userList;
    }
}
