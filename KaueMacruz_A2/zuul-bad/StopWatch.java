import java.util.Timer;
import java.util.TimerTask;
/**
 * Write a description of class StopWatch here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StopWatch
{
    public static int interval;
    public static Timer timer;

     public static void start() {
        String secs = "600";
        interval = Integer.parseInt(secs);
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                setInterval();
            }
        }, delay, period);
    }
    
    private static final int setInterval() {
        
        if (interval == 1){
            timer.cancel();
            System.out.println("Oops! You have run out of time!");
            System.out.println("Please try again if you like or type 'quit' to finish the game");
            Command command = new Command("QUIT", null);;
            Game.quit(command);
            Game.printWelcome();
            start();
        }
        if(interval == 120){
            System.out.println("You have 2 minutes to finish your mission before the game starts!!"); 
        }
        return --interval;
    }
}
