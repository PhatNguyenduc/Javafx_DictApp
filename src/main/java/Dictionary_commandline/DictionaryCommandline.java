package Dictionary_commandline;

import java.util.Scanner;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement;
    private Scanner scanner;

    public DictionaryCommandline() {
        dictionaryManagement = new DictionaryManagement();
        scanner = new Scanner(System.in);
    }
    public DictionaryCommandline(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    public void dictionaryAdvanced() {
        System.out.println("Welcome to My Application!");

        while (true) {
                System.out.println("[0] Exit");
                System.out.println("[1] Add");
                System.out.println("[2] Remove");
                System.out.println("[3] Update");
                System.out.println("[4] Display");
                System.out.println("[5] Lookup");
                System.out.println("[6] Search");
                System.out.println("[7] Game");
                System.out.println("[8] Import from file");
                System.out.println("[9] Export to file");
                System.out.print("Your action: ");

                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 0:
                        System.out.println("Exiting the application.");
                        return;
                    case 1:
                        // Thêm từ
                        System.out.print("Enter word: ");
                        String wordTarget = scanner.nextLine();
                        System.out.print("Enter definition: ");
                        String wordExplain = scanner.nextLine();
                        dictionaryManagement.insertWordByUser(wordTarget, wordExplain);
                        break;
                    case 2:
                        // Xóa từ
                        System.out.print("Enter word to delete: ");
                        String wordToDelete = scanner.nextLine();
                        dictionaryManagement.deleteWord(wordToDelete);
                        break;
                    case 3:
                        // Cập nhật từ
                        System.out.print("Enter word to edit: ");
                        String wordToEdit = scanner.nextLine();
                        System.out.print("Enter new definition: ");
                        String newWordExplain = scanner.nextLine().trim();
                        dictionaryManagement.updateWord(wordToEdit, newWordExplain);
                        break;
                    case 4:
                        // Hiển thị danh sách từ
                        dictionaryManagement.showAllWords();
                        break;
                    case 5:
                        //Lookup từ
                        System.out.print("Enter word to lookup: ");
                        String wordToLookup = scanner.nextLine().toLowerCase().trim();
                        if (dictionaryManagement.searchWord(wordToLookup)) {
                            System.out.println("Meaning: " + dictionaryManagement.getWordMeaning(wordToLookup));
                        } else {
                            System.out.println("Word not found.");
                        }
                        break;
                    case 6:
                        // Tìm kiếm từ với prefix
                        System.out.print("Enter a prefix to suggest words: ");
                        String suggestPrefix = scanner.nextLine().toLowerCase().trim();
                        dictionaryManagement.showSuggest(suggestPrefix);
                        break;
                    case 7:
                        // Chơi trò chơi
                        dictionaryManagement.playGame();

                        break;
                    case 8:
                        // Import từ tệp
                        System.out.println("Importing...");

                        dictionaryManagement.insertWordFromFile("src\\main\\java\\dictionaries.txt");
                        break;
                    case 9:
                        // Export ra tệp
                        System.out.println("Exporting...");

                        dictionaryManagement.exportToFile("src\\main\\java\\dictionaries.txt");

                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
        }


    }


