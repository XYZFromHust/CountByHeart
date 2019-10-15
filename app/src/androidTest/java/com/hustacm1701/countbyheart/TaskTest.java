package com.hustacm1701.countbyheart;
import com.hustacm1701.countbyheart.object.Task;

public class TaskTest {
    public static void main(String[] args) {
        int[] answers = new int[18];
        String[] tasks = new String[18];
        for (int i = 0;i<18;i++){
            Task task = new Task(i/3+1);
            tasks[i] = task.getContent();
            answers[i] = task.getAnswer();
            System.out.println(task.getContent()+" "+task.getAnswer());
        }
        for (int i = 0;i<18;i++){
            System.out.println(tasks[i]);
        }
        for (int i = 0;i<18;i++){
            System.out.println(answers[i]);
        }
    }
}
