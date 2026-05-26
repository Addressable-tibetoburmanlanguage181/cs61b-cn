package demo;

import edu.princeton.cs.algs4.In;

public class FileReadDemo {
    public static void main(String[] args) {
        In in = new In("./data/ngrams/very_short.csv");
        int i = 0;

        while (!in.isEmpty()) {
            i += 1;
            String nextLine = in.readLine();
            System.out.print("第 " + i + " 行是：");
            System.out.println(nextLine);
            System.out.print("按制表符分割后，第一个词是：");
            String[] splitLine = nextLine.split("\t");
            System.out.println(splitLine[0]);
        }
    }
}
