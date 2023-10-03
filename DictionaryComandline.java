import java.util.Scanner;

public class DictionaryComandline {
    private DictionaryManagement dictionaryManagement;
    private Scanner scanner;

    public DictionaryComandline() {
        dictionaryManagement = new DictionaryManagement();
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to My Application!");

        boolean isRunning = true;
        while (isRunning) {
            showMenu();
            int action = getUserAction();

            switch (action) {
                case 0:
                    isRunning = false;
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    dictionaryManagement.insertFromCommandline();
                    break;
                case 2:
                    // Thực hiện hành động xóa từ
                    break;
                case 3:
                    // Thực hiện hành động cập nhật từ
                    break;
                case 4:
                    dictionaryManagement.showAllWords();
                    break;
                case 5:
                    dictionaryManagement.dictionaryLookup();
                    break;
                case 6:
                    // Thực hiện hành động tìm kiếm từ
                    break;
                case 7:
                    Hangman.run();
                    break;
                case 8:
                    dictionaryManagement.insertFromFile();
                    break;
                case 9:
                    dictionaryManagement.dictionaryExportToFile();
                    break;
                default:
                    System.out.println("Action not supported!");
                    break;
            }
        }

        scanner.close();
    }

    private void showMenu() {
        System.out.println("[0] Exit");
        System.out.println("[1] Add Word");
        System.out.println("[2] Remove Word");
        System.out.println("[3] Update Word");
        System.out.println("[4] Display All Words");
        System.out.println("[5] Lookup Word");
        System.out.println("[6] Search Words");
        System.out.println("[7] Game");
        System.out.println("[8] Import from file");
        System.out.println("[9] Export to file");

        System.out.print("Your action: ");
    }

    private int getUserAction() {
        int action;
        try {
            action = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            action = -1;
        }
        return action;
    }

    // Các phương thức khác để triển khai các hành động tương ứng với menu
}