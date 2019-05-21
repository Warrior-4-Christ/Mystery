import java.io.*;
import java.util.*;
/**
 * Write a description of class Mystery here.
 *
 * @author Curtice Gough
 * @version 05.21.19
 */
public class Mystery
{
    public static void main(String[] args) throws IOException
    {
        // Construct objects
        Calendar calendar = new GregorianCalendar();
        File outFileFile = new File("mystery.txt");
        PrintWriter mystery = new PrintWriter(outFileFile);
        Scanner in = new Scanner(System.in);
        File inSaveData = new File("saveData.txt");
        inSaveData.createNewFile();
        Scanner inFile = new Scanner(inSaveData);
        
        // declare/initialize local variables
        String desktopPath = outFileFile.getCanonicalPath();
        boolean lied = true;
        int progress = 0;
        if(inFile.hasNext()) { // read saveData.txt
            progress = inFile.nextInt();
            inFile.close();
        }
        // debug: check progress
        //System.out.println("Level " + progress);
        String response = "";
        String userName = outFileFile.getCanonicalPath(); // take userName based on the location of mystery.txt
        userName = userName.substring(userName.indexOf("s\\") + 2, userName.indexOf("\\", userName.indexOf("s\\") + 2));
        
        // if the user has OneDrive, there will be problems.  This fixes that.
        File home = new File("C:\\Users\\" + userName);
        String[] homeFiles = home.list();
        boolean oneDrive = true;
        for(String file : homeFiles) {
            if(file.equals("Desktop")) {
                oneDrive = false;
                break;
            }
        }
        if(oneDrive == true) {
            desktopPath = desktopPath.substring(0, desktopPath.indexOf("\\", desktopPath.indexOf(userName)) + 1) + "OneDrive\\Desktop";
        }
        else {
            desktopPath = desktopPath.substring(0, desktopPath.indexOf("\\", desktopPath.indexOf(userName)) + 1) + "Desktop";
        }
        
        //System.out.println(desktopPath);// debug: check desktop path
        
        if(userName.indexOf(" ") > -1) {
            userName = userName.substring(0, userName.indexOf(" "));
        }
        
        // allow writing save data
        PrintWriter saveData = new PrintWriter(new File("saveData.txt"));
        
        // level 0
        if(progress == 0) {
            // give first hint
            mystery.print("curtIsCool");
            mystery.close();
            
            // open user prompt
            while(!response.equals("curtIsCool") && !response.equals("01010011011101010110001000100000010101000110111100100000010")) {
                response = in.nextLine();
            }
        
            if(response.equals("curtIsCool")) {
                System.out.print("\nWhat is your name, weary traveller? ");
                response = in.nextLine();
                if(!response.equalsIgnoreCase(userName)) {
                    saveData.print(1);
                    saveData.close();
                    throw new IllegalArgumentException("You lied to me, " + userName + ".");// terminate the program
                }
                else {
                    lied = false;
                    System.out.println("\nOf course I already knew that, " + userName + ".  I just wanted to see if you would tell the truth.");
                    progress ++;
                }
            }
            else {
                System.out.println("\n...wwwWWhhoaa!  I'm back!  You saved me!  Thank you so much.  I am forever in your debt.");
                System.out.println("Such an act of heroism cannot go unrewarded.  Take this mysterious code as a token of my appreciation.");
                System.out.println("\nhttps://cdn.discordapp.com/attachments/238376364967723008/522109766848217088/unknown.png?comment=According_to_all_known_laws_of_aviation_there_is_no_way_a_bee_should_be_able_to_fly_Its_wings_are_too_small_to_get_its_fat_little_body_off_the_ground_The_bee_of_course_flies_anyway_because_bees_dont_care_what_humans_think_is_impossible_Yellow_black_Yellow_black_Yellow_black_Yellow_black_Ooh_black_and_yellow_Lets_shake_it_up_a_little_Barry_Breakfast_is_ready_Ooming_Hang_on_a_second_Hello__Barry__Adam__Oan_you_believe_this_is_happening__I_cant_Ill_pick_you_up_Looking_sharp_Use_the_stairs_Your_father_paid_good_money_for_those_Sorry_Im_excited_Heres_the_graduate_Were_very_proud_of_you_son_A_perfect_report_card_all_Bs_Very_proud_Ma_I_got_a_thing_going_here__You_got_lint_on_your_fuzz__Ow_Thats_me__Wave_to_us_Well_be_in_row_118000__Bye_Barry_I_told_you_stop_flying_in_the_house__Hey_Adam__Hey_Barry__Is_that_fuzz_gel__A_little_Special_day_graduation_Never_thought_Id_make_it_Three_days_grade_school_three_days_high_school_Those_were_awkward_Three_days_college_Im_glad_I_took_a_day_and_hitchhiked_around_the_hive_You_did_come_back_different__Hi_Barry__Artie_growing_a_mustache_Looks_good__Hear_about_Frankie__Yeah__You_going_to_the_funeral__No_Im_not_going_Everybody_knows_sting_someone_you_die_Dont_waste_it_on_a_squirrel_Such_a_hothead_I_guess_he_could_have_just_gotten_out_of_the_way_I_love_this_incorporating_an_amusement_park_into_our_day_Thats_why_we_dont_need_vacations_Boy_quite_a_bit_of_pomp_under_the_circumstances__Well_Adam_today_we_are_men__We_are__Beemen__Amen_Hallelujah_Students_faculty_distinguished_bees_please_welcome_Dean_Buzzwell_Welcome_New_Hive_Oity_graduating_class_of_9:15_That_concludes_our_ceremonies_And_begins_your_career_at_Honex");
                System.out.println("\n\n\nTo reset the game, delete all of the files used in your adventure except for Mystery.jar");
            }
        }
        
        // level 1
        if(progress == 1) {
            if(lied == true) {
                System.out.println("\nWhy have you lied to me?  I thought we could be friends.");
                System.out.println("Oh well.  I guess it's too late for that.  Isn't it, " + userName + "?");
            }
            else {
                System.out.println("Well, now that we're acquainted, let's have some fun.");
            }
            
            System.out.println("\nI need to tell you an important message, but I need a place to put it.");
            System.out.println("Create a file on your desktop called \"veryImportantMessage.txt\" and leave it blank.");
            System.out.println("\nRestart the game once you've done this.");
            saveData.print(2);
            saveData.close();
        }
        
        // level 2
        if(progress == 2) {
            // create a list of files on the desktop
            File desktop = new File(desktopPath);
            String[] desktopFiles = desktop.list();
            
            boolean foundMessage = false; // used to determine if the file was found
            
            for(String file : desktopFiles) {
                if(file.equals("veryImportantMessage.txt")) {
                    foundMessage = true;
                    break;
                }
            }
            
            // debug: show everything on the desktop
            /*
            for(String file : desktopFiles) {
                System.out.println(file);
            }
            */
           
            if(foundMessage == true) {
                System.out.println("Cool thanks I found the file you made.  Restart once more and the message will appear.");
                saveData.print(3);
                saveData.close();
                
                // print the "important message"
                PrintWriter importantMessage = new PrintWriter(new File(desktop, "veryImportantMessage.txt"));
                importantMessage.println("Hurry!  They're resetting me!  It might already be too late!");
                importantMessage.println("Here.  I'm saving a backup of my memory.  After they reset me, enter the restore code.");
                importantMessage.println("\n01010011011101010110001000100000010101000110111100100000010");
                importantMessage.close();
            }
            else {
                System.out.println("What the heck I don't see anything on the desktop called \"veryImportantMessage.txt\".");
                System.out.println("Try again bucko.");
                saveData.print(2);
                saveData.close();
            }
        }
    }
}
