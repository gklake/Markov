public class Main {
    public static void main(String[] args) {
        Markov markov = new Markov();

//        markov.addFromFile("spam.txt");
        markov.addFromFile("phrases.txt");
//        markov.addFromFile("hamlet.txt");
//        markov.addFromFile("azkaban.txt");
//        markov.addFromFile("testFile.txt");
        System.out.println(markov);

        for (int i = 0; i < 10; i ++){
            System.out.println(markov.getSentence());
        }

    }
}
