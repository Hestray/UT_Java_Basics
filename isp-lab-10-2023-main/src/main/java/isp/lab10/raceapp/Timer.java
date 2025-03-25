package isp.lab10.raceapp;

public class Timer extends Thread {
    public long counter;
    private boolean counting;

    public Timer() {
        counter   = 0;
        this.counting  = true;
    }

    public void run() {
        try {
            while (this.counting) {
                counter++;
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println("whomst'd've'ly'yaint'nt'ed'ies's'y'es hast dared to wake me up?");
        }
    }

    public void stopCounting() {
        this.counting = false;
    }
}
