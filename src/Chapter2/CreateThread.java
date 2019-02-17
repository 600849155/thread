package Chapter2;

public class CreateThread {
    public static void main(String[] args) {
        Rabbit t1 = new Rabbit();
        t1.start();
    }
}

class Rabbit extends Thread {
    @Override
    public void run() {
        System.out.println("i am t1");
    }
}
