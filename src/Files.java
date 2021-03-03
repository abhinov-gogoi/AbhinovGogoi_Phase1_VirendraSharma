import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Files {
    private final File pathname;
    Scanner scanner;


    // constructor
    public Files(String pathname) {
        this.pathname = new File(pathname);
        scanner = new Scanner(System.in);
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

    public static boolean fileExistsCaseSensitive(String path) {
        try {
            File file = new File(path);
            return file.exists() && file.getCanonicalFile().getName().equals(file.getName());
        } catch (IOException e) {
            return false;
        }
    }
}
