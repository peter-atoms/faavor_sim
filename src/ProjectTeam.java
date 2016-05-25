/**
 * Created by peter on 5/16/16.
 */
public class ProjectTeam {
    // Data Fields
    private TeamMember[] members;
    private boolean usingFaavor;
    private int teamNumber;
    public Stats teamData;

    public ProjectTeam(int size, boolean faavor, int team){
        teamNumber = team;
        members = new TeamMember[size];
        usingFaavor = faavor;
        teamData = new Stats(teamNumber);

        // Initialize TeamMembers
        for(int i = 0; i < size; i++){
            members[i] = new TeamMember(this, i);
        } // End for

    } // Constructor

    public boolean isUsingFaavor(){
        return usingFaavor;
    } // End isUsingFaavor

    public int getTeamNumber(){
        return teamNumber;
    } // End getTeamNumber
} // End ProjectTeam