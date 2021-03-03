import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    File pathname = new File(PATHNAME);
    static final String PATHNAME = "src/resources";
    Scanner scanner = new Scanner(System.in);

    private static final String WELCOME_PROMPT =
            "\n*****************  LockedMe.com *******************"+
                    "\n***************** Abhinov Gogoi *******************"+
                    "\n\nDIRECTORY : "+PATHNAME;

    private static final String MAIN_MENU_PROMPT =
            "\nMAIN MENU - Select any of the following: \n"+
                    "1 -> List files in directory\n"+
                    "2 -> Add, Delete or Search\n"+
                    "3 -> Exit Program";

    private static final String SECONDARY_MENU_PROMPT =
            "   \nSelect any of the following: \n"+
                    "   a -> Add a file\n"+
                    "   b -> Delete a file\n"+
                    "   c -> Search a file\n"+
                    "   d -> GoBack";

    // PRIMARY MENU
    void showMenu() {
        System.out.println(MAIN_MENU_PROMPT);
        try{
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option){
                case 1 : {
                    displayFilesList();
                    showMenu();
                }
                case 2 : {
                    showSecondaryMenu();
                }
                case 3 : {
                    System.out.println("Thank You");
                    System.exit(0);
                }
                default : {
                    showMenu();
                }
            }
        }
        catch (Exception e){
            System.out.println("Please enter 1, 2 or 3\n");
            showMenu();
        }
    }

    void showSecondaryMenu() {
        System.out.println(SECONDARY_MENU_PROMPT);
        try{
            Scanner scanner = new Scanner(System.in);
            char[] input = scanner.nextLine().toLowerCase().trim().toCharArray();
            char option = input[0];

            switch (option){
                case 'a' : {
                    System.out.println("   ↳ Adding a file...");
                    addFile(takeFilenameInput(false));
                    showSecondaryMenu();
                }
                case 'b' : {
                    System.out.println("   ↳ Deleting a file...");
                    deleteFile(takeFilenameInput(true));
                    showSecondaryMenu();
                }
                case 'c' : {
                    System.out.println("   ↳ Searching a file...");
                    searchFile(takeFilenameInput(true));
                    showSecondaryMenu();
                }
                case 'd' : {
                    System.out.println("    Going Back to MAIN menu");
                    showMenu();
                }
                default : {
                    System.out.println("   Please enter a, b, c or d");
                    showSecondaryMenu();
                }
            }
        }
        catch (Exception e){
            System.out.println("   Please enter a, b, c or d");
            showSecondaryMenu();
        }
    }

    public void displayFilesList() {
        String[] list = pathname.list();
        if (list==null)
            System.out.println("No files in the directory");
        else {
            System.out.println("The files in "+ pathname +" are :\n");
            Arrays.sort(list);
            for (String str:list) {
                System.out.println(str);
            }
        }
    }

    public static boolean fileExistsCaseSensitive(String path) {
        try {
            File file = new File(path);
            return file.exists() && file.getCanonicalFile().getName().equals(file.getName());
        } catch (IOException e) {
            return false;
        }
    }

    void addFile(String filename) throws IOException {
        File file = new File(pathname+"/"+filename);

        if (file.createNewFile())
            System.out.println("    File \""+filename+"\" added to "+ pathname +"\n");
        else if(file.exists())
            System.out.println("    File \""+filename+"\" already exists at "+ pathname +"\n");
        else
            System.out.println("Something went wrong. File NOT added\n");

    }

    void deleteFile(String filename) {
        File file = new File(pathname+"/"+filename);

        if (fileExistsCaseSensitive(pathname+"/"+filename) && file.delete()) {
            System.out.println("    File \"" + filename + "\" deleted from " + pathname + "\n");
        }
        else
            System.out.println("    Delete Operation failed. FILE NOT FOUND\n");
    }

    void searchFile(String filename) {
        File file = new File(pathname+"/"+filename);

        if(fileExistsCaseSensitive(pathname+"/"+filename))
            System.out.println("    FOUND : File \""+filename+"\" exists at "+ pathname +"\n");
        else
            System.out.println("    File NOT found (FNF)\n");
    }

    public String takeFilenameInput(boolean case_sensitivity) {
        System.out.print("    Please enter a filename : ");
        try{
            String filename;
            if (case_sensitivity)
                filename = scanner.nextLine().trim();
            else
                filename = scanner.nextLine().toLowerCase().trim();

            if (filename.equals(""))
                return takeFilenameInput(case_sensitivity);
            else
                return filename;
        }
        catch (Exception e) {
            System.out.println("    Something went wrong");
        }
        return takeFilenameInput(case_sensitivity);
    }

    // Driver/Launcher Method
    public static void main(String[] args) {
        System.out.println(WELCOME_PROMPT);
        Main mainMenu = new Main();
        mainMenu.showMenu();
    }
}
