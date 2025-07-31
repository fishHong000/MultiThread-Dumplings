import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;
import java.util.*;

public class A1083364_Q1 {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        System.out.print("請輸入同時光顧的顧客人數: ");
        int customer = input.nextInt();
        
        dumpling pork = new dumpling("Pork");
        dumpling beef = new dumpling("Beef");
        dumpling vegetable = new dumpling("Vegetable");
    }
}

class dumpling implements Runnable {
    
    static int customer = 1;
    static int porkNum = 5000;
    static int beefNum = 3000;
    static int vegetableNum = 1000;
    int tDumpling;
    Thread t;
    
    dumpling(String name) {
        tDumpling = 0;
        t = new Thread(this, name);
        t.start();
    }
    
    @Override
    public void run(){
        while (buyDumplings(this)) {
            
            // 暫停3秒
            // sleep(3000);
        }
        System.out.println("剩下" + porkNum + "顆豬肉水餃");
        System.out.println("剩下" + beefNum + "顆牛肉水餃");
        System.out.println("剩下" + vegetableNum + "顆蔬菜水餃");
        System.out.println("因為都剩不到10顆水餃, 這樣無法賣, 所以算是賣完了!!");
    }
    
    synchronized private static boolean buyDumplings(dumpling type) {
        
        int getType = ThreadLocalRandom.current().nextInt(0, 3);
        
        // choose pork
        if (getType == 0 && type.porkNum >= 10) {
            int buyNum = 0;
            if (type.porkNum >= 50) {
                buyNum = ThreadLocalRandom.current().nextInt(10, 51);
                type.porkNum -= buyNum;
                System.out.println("顧客" + type.customer + "點了" + buyNum + "顆豬肉水餃");
                type.customer += 1;
                return true;
            }
            else if (type.porkNum < 50) {
                buyNum = ThreadLocalRandom.current().nextInt(10, type.porkNum);
                type.porkNum -= buyNum;
                System.out.println("顧客" + type.customer + "點了" + buyNum + "顆豬肉水餃");
                type.customer += 1;
                return true;
            }
        }
        
        // choose beef
        else if (getType == 1 && type.beefNum >= 10) {
            int buyNum = 0;
            if (type.beefNum >= 50) {
                buyNum = ThreadLocalRandom.current().nextInt(10, 51);
                type.beefNum -= buyNum;
                System.out.println("顧客" + type.customer + "點了" + buyNum + "顆牛肉水餃");
                type.customer += 1;
                return true;
            }
            else if (type.beefNum < 50) {
                buyNum = ThreadLocalRandom.current().nextInt(10, type.beefNum);
                type.beefNum -= buyNum;
                System.out.println("顧客" + type.customer + "點了" + buyNum + "顆牛肉水餃");
                type.customer += 1;
                return true;
            }
        }
        
        // choose vagetable
        else if (getType == 2 && type.vegetableNum >= 10) {
            int buyNum = 0;
            if (type.vegetableNum >= 50) {
                buyNum = ThreadLocalRandom.current().nextInt(10, 51);
                type.vegetableNum -= buyNum;
                System.out.println("顧客" + customer + "點了" + buyNum + "顆蔬菜水餃");
                customer += 1;
                return true;
            }
            else if (type.vegetableNum < 50) {
                buyNum = ThreadLocalRandom.current().nextInt(10, type.vegetableNum);
                type.vegetableNum -= buyNum;
                System.out.println("顧客" + customer + "點了" + buyNum + "顆蔬菜水餃");
                customer += 1;
                return true;
            }
        }
        else if ((type.porkNum < 10) && (type.beefNum < 10) && (type.vegetableNum < 10)) {
            return false;
        }
        return true;
    }
}