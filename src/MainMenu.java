import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    Files files;

    private static final String PROMPT  =
            "\nMAIN MENU - Select any of the following: \n"+
            "1 -> List files in directory\n"+
            "2 -> Add, Delete or Search\n"+
            "3 -> Exit Program";

    public MainMenu(String pathname) {
        files = new Files(pathname);
    }

    // PRIMARY MENU
    void showMenu() {
        System.out.println(PROMPT);

        try{
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option){
                case 1 : {
                    files.displayFilesList();
                    showMenu();
                }
                case 2 : {
                    new SecondaryMenu().showSecondaryMenu();
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
}
