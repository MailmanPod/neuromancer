/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.DateBuilder.*;

import java.util.Date;
import java.util.logging.Level;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Quality of Service
 */
public class StartEmailJob {
    private static Scheduler sched;

    public void excecute() throws Exception{
        //Logger log = LoggerFactory.getLogger(StartEmailJob.class);
        

       System.out.println("------- Initializing ----------------------");

        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        sched = sf.getScheduler();

        System.out.println
        ("------- Initialization Complete -----------");

        // computer a time that is on the next round minute
        Date runTime = evenMinuteDate(new Date());

        System.out.println
        ("------- Scheduling Job  -------------------");

        JobDataMap map = new JobDataMap();
        map.put("nombre_persona", "El pulpito asesino");
        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(MailJob.class)
            .withIdentity("job1", "group1")
                .usingJobData(map)
            .build();
        
        // Trigger the job to run on the next round minute
        Trigger trigger = newTrigger()
            .withIdentity("trigger1", "group1")
            .startAt(runTime)
            .build();
        
        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);
        System.out.println
        (job.getKey() + " will run at: " + runTime);  

        // Start up the scheduler (nothing can actually run until the 
        // scheduler has been started)
        sched.start();

        System.out.println
        ("------- Started Scheduler -----------------");

        /*// wait long enough so that the scheduler as an opportunity to 
        // run the job!
        log.info("------- Waiting 65 seconds... -------------");
        try {
            // wait 65 seconds to show job
            Thread.sleep(65L * 1000L); 
            // executing...
        } catch (Exception e) {
        }*/

        /*// shut down the scheduler
        log.info("------- Shutting Down ---------------------");
        sched.shutdown(true);
        log.info("------- Shutdown Complete -----------------");*/
    }
    
    public void stop(){
        try {
            // shut down the scheduler
            System.out.println
            ("------- Shutting Down ---------------------");
            sched.shutdown(true);
            System.out.println
            ("------- Shutdown Complete -----------------");
        } catch (SchedulerException ex) {
            java.util.logging.Logger.getLogger(StartEmailJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            StartEmailJob nice = new StartEmailJob();
            nice.excecute();
            
            System.out.println("Pulse una tecla para finalizar");
            System.in.read();
            
            nice.stop();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
}
