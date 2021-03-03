import java.util.Scanner;

public class SecondaryMenu {
    Files files;
    private static final String SECONDARY_PROMPT =
            "   \nSelect any of the following: \n"+
            "   a -> Add a file\n"+
            "   b -> Delete a file\n"+
            "   c -> Search a file\n"+
            "   d -> GoBack";

    public SecondaryMenu() {
        this.files = new Files(Main.PATHNAME);
    }

    void showSecondaryMenu() {
        System.out.println(SECONDARY_PROMPT);
        try{
            Scanner scanner = new Scanner(System.in);
            char[] input = scanner.nextLine().toLowerCase().trim().toCharArray();
            char option = input[0];

            switch (option){
                case 'a' : {
                    System.out.println("   ↳ Adding a file...");
                    files.addFile(files.takeFilenameInput(false));
                    showSecondaryMenu();
                }
                case 'b' : {
                    System.out.println("   ↳ Deleting a file...");
                    files.deleteFile(files.takeFilenameInput(true));
                    showSecondaryMenu();
                }
                case 'c' : {
                    System.out.println("   ↳ Searching a file...");
                    files.searchFile(files.takeFilenameInput(true));
                    showSecondaryMenu();
                }
                case 'd' : {
                    System.out.println("    Going Back to MAIN menu");
                    new MainMenu(Main.PATHNAME).showMenu();
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

}
