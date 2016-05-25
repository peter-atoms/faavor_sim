import java.util.Random;

/**
 * Created by peter on 5/16/16.
 */
public class Interaction implements Event {
    // Data fields
    private int avgInteractionTime;
    private int time;
    private ProjectTeam relatedTeam;
    static Random generator = new Random();

    public Interaction(int intervalTime, ProjectTeam team){
        time = intervalTime;
        relatedTeam = team;

        // 15 or 5 minutes subtracted to ensure correct minimum time
        if (relatedTeam.isUsingFaavor()){
            avgInteractionTime = 41;
        } else {
            avgInteractionTime = 106;
        } // End if-else

    } // Constructor

    public int run() {
        int tempTime;

        // 1) Increment interaction counter
        // 2) Update statistics
        // 3) Schedule next interaction if < limit

        if (time != 0 && (relatedTeam.teamData.getNumOfInteractions() < WebSimulation.limit)) {
            // time of 0 indicates first event(s) of simulation
            // Only record first 200 interactions
            relatedTeam.teamData.updateInteractionStats(time);
        } // End if

        if (relatedTeam.teamData.getNumOfInteractions() < WebSimulation.limit){
            // Only add new Interaction if limit has not been reached
            // Generate interaction length using exponential distribution
            // Based off example from http://preshing.com/20111007/how-to-generate-random-timings-for-a-poisson-process/
            if (avgInteractionTime == 40) {
                tempTime = (int) (-Math.log(generator.nextDouble()) * avgInteractionTime) + 5;
            } else {
                tempTime = (int) (-Math.log(generator.nextDouble()) * avgInteractionTime) + 15;
            } // End if-else

            // Schedule completion of next interaction -> one week later (2400 minutes)
            WebSimulation.agenda.add(new Interaction(tempTime, relatedTeam), 2400);
        } // End if
        return relatedTeam.getTeamNumber();
    } // End run

} // End Interaction

