package Dictionary_commandline;

public class Test {
    public static void main(String[] args) {
        DictionaryManagement management = new DictionaryManagement();
        DictionaryCommandline a = new DictionaryCommandline();
        a.dictionaryAdvanced();

        //management.dictionaryExportToFile("C:\\Users\\Admin\\IdeaProjects\\JavaApp\\src\\dictionaries.txt");
//        management.insertWordFromFile("D:\\Prj IntelIj\\Java_Dict_App\\src\\dictionaries.txt");
//        management.showAllWords();
//        Scanner sc = new Scanner(System.in);
//        String word = sc.next();
//        System.out.println(word);
//        //management.showSuggest(word);
//        System.out.println("\n \n");
        //management.exportToFile("D:\\Prj IntelIj\\Java_Dict_App\\src\\dictionaries.txt");

    }
}
