package bomb;

import common.IntList;
import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Bomb {
    // 请勿修改此文件

    public String shufflePassword(String s) {
        String code = "" + s.hashCode();
        StdRandom.setSeed(1337);
        char[] chars = code.toCharArray();
        StdRandom.shuffle(chars);
        return String.valueOf(chars);
    }

    public IntList shufflePasswordIntList(String s) {
        String code = "" + s.hashCode();
        StdRandom.setSeed(61833);
        char[] chars = code.toCharArray();
        StdRandom.shuffle(chars);

        IntList curr = null;
        for (int i = chars.length - 1; i >= 0; i--) {
            curr = new IntList(Integer.parseInt(String.valueOf(chars[i])), curr);
        }

        return curr;
    }

    public void phase0(String password) {
        String correctPassword = shufflePassword("hello");
        if (!password.equals(correctPassword)) {
            System.out.println("第 0 阶段 BOOM！");
            return;
        }
        System.err.println("你通过了第 0 阶段，密码是 \"" + password + "\"");
    }

    public void phase1(IntList password) {
        IntList correctIntListPassword = shufflePasswordIntList("bye");
        if (!correctIntListPassword.equals(password)) {
            System.out.println("第 1 阶段 BOOM！");
            return;
        }
        System.err.print("你通过了第 1 阶段，密码是 \"");
        System.err.print(password.print());
        System.err.println("\"");
    }

    public void phase2(String password) {
        Random r = new Random(1337);
        Set<Integer> numbers = new LinkedHashSet<>();
        while (numbers.size() < 100000) {
            numbers.add(r.nextInt());
        }

        boolean correct = false;
        int i = 0;
        for (int number : numbers) {
            if (i == 1337 && Integer.parseInt(password) == number) {
                correct = true;
            }
            i++;
        }
        if (!correct) {
            System.out.println("第 2 阶段 BOOM！");
            return;
        }
        System.err.println("你通过了第 2 阶段，密码是 \"" + password + "\"");
    }

}
