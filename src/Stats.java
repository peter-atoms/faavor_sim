import java.util.ArrayList;

/**
 * Created by peter on 5/16/16.
 */
public class Stats {
    // Class used to keep track of interaction time statistics

    // Data fields
    private long totalInteractionTime;
    private ArrayList<Integer> interactions; // change to public?
    private int teamNumber;

    public Stats(int team){
        interactions = new ArrayList<>();
        teamNumber = team;
    } // Constructor

    public void updateInteractionStats(int time){
        // Add time to total and store for future reference
        totalInteractionTime += time;
        interactions.add(time);
    } // End updateInteractionStats

    public void display(){
        int count = 0;
        // Display team number
        System.out.println("\n\nTeam " + (teamNumber+1) + ": ");

        // Displays individual interaction times
        // 10 per line
        for(Integer data : interactions){
            count++;
            System.out.print(data + "\t\t\t");
            if (count % 10 == 0){
                System.out.println();
            } // end if
        } // End for

        int days = (int) (totalInteractionTime / (60*24));
        int hours = (int) ((totalInteractionTime % (60*24)) / 60);
        int minutes = (int) (totalInteractionTime % 60);
        System.out.println("\n\nTotal Interaction Time: " + totalInteractionTime + " minutes");
        System.out.println("Or: " + days + " days, " + hours + " hours, and " + minutes + " minutes");
    } // End display

    public long getTotalInteractionTime(){
        return totalInteractionTime;
    } // End getTotalInteractionTime

    public int getNumOfInteractions(){
        return interactions.size();
    } // End getNumOfInteractions

    public ArrayList<Integer> getInteractions() {
        return interactions;
    } // End getInteractions
} // End Stats
