package isp.lab10.raceapp;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Race!");

        // necessary threads for semaphore and cars
        Leaderboard     leaderboard     = new Leaderboard();
        CarRace         carRace         = new CarRace(leaderboard);
        Semaphore       semaphore       = new Semaphore();
        PlaySound       playSound       = new PlaySound();
        Timer           timer           = new Timer();

        // start semaphore
        semaphore.start();
        semaphore.closeWindow();    // after thread is done, close the window

        // start race & sound
        carRace.start();
        playSound.playSound();
        timer.start();

        // when race stops, stop sound too
        carRace.join();
        playSound.stopSound();
        timer.stopCounting();

        // display leaderboard
        leaderboard.raceStopwatch(timer.counter);
        leaderboard.display();
        System.exit(0);
    }
}
