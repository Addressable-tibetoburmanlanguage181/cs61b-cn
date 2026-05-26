package demo;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.io.File;

public class ExampleSaveLoad {
    public static void main(String[] args) {
        String filename = "name.txt";
        File file = new File(filename);

        // 步骤 1：检查文件是否存在并读取保存的名称
        if (file.exists()) {
            In in = new In(file);
            if (in.hasNextLine()) {
                String savedName = in.readLine();
                System.out.println("你好，上次运行此程序时，你的名字是 " + savedName);
            } else {
                System.out.println("我不知道你的名字。");
            }
        } else {
            System.out.println("我不知道你的名字。");
        }

        // 步骤 2：提示用户输入名字
        System.out.print("请输入你的名字：");
        In keyboard = new In();  // 从标准输入读取
        String currentName = keyboard.readLine();

        // 步骤 3：将新名称保存到文件
        Out out = new Out(filename);
        out.println(currentName);
        out.close();
    }
}