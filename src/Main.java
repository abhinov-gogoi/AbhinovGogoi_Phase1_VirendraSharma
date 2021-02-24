public class Main {
    final static String PATHNAME = "src/resources";

    public static void main(String[] args) {
        System.out.println( "\n*****************  LockedMe.com *******************"+
                            "\n***************** Abhinov Gogoi *******************"+
                            "\n\nDIRECTORY : "+PATHNAME);

        MainMenu mainMenu = new MainMenu(PATHNAME);
        mainMenu.showMenu();
    }
}
