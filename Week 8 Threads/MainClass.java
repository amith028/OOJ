public class MainClass {
    
    public static class BMSCollegeThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("BMS College of Engineering");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static class CSEThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("CSE");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
	System.out.println("Name: Amith R");
	System.out.println("USN: 1BM23CS028");
        BMSCollegeThread bmsThread = new BMSCollegeThread();
        CSEThread cseThread = new CSEThread();
        bmsThread.start();
        cseThread.start();
    }
}
