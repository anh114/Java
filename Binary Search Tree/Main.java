package Exercise1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static BTree t = new BTree();
    public static void main(String[] args) {
        try {
            System.out.println("Length of input: ");
            int n = scanner.nextInt();
            System.out.println("Values: ");
            int[] arr = new int[n];
            for(int i = 0; i< n; i++){
                int num = scanner.nextInt();
                if(num > 0 && num < 100){
                    arr[i] = num;
                }
            }

            //ArrayList and check duplicate numbers
            ArrayList<Integer> ans = new ArrayList<>();
            for(int num: arr){
                //Check duplicated
                if(!ans.contains(num)){
                    ans.add(num);
                }
            }

            //insert all value of ans in Btree
            for(int num: ans){
                t.insert(new Node(num));
            }

        menu();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void menu (){
        System.out.println("1. Display Binary Tree");
        System.out.println("2. Find Nodes > x");
        System.out.println("0. Quit: ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                System.out.println("1. Pre-order");
                System.out.println("2. In-order");
                int method = scanner.nextInt();
                t.display(method);
                break;
            case 2:
                System.out.println("X: ");
                int x = scanner.nextInt();
                t.find(x);
                System.out.println();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Choose 0 to 2");
        }
        menu();
    }


}
