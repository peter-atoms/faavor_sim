/**
 * Created by peter on 5/16/16.
 */
public class Company {
    // Data Fields
    private String companyName;
    ProjectTeam[] teams; // Use array for adaptability - more than 2 teams in future runs
    private int numOfEmployees;


    public Company(){
        // Default number of teams, name, and team size
        this(2, "3M", 5000, 8);
    } // Default constructor

    public Company(int numOfTeams, String name, int employees,int teamSize){
        // Set number of teams and name based on input
        teams = new ProjectTeam[numOfTeams];
        companyName = name;
        numOfEmployees = employees;

        // Initialize each project team
        // TeamMembers added in ProjectTeam constructor
        boolean usingFaavor = true;
        for (int i = 0; i < numOfTeams; i++){
            // Alternate use of Faavor
            // First team will not be using Faavor
            // Second team will (and so on)
            usingFaavor = (i%2 != 0);
            // Teams index starting at zero
            teams[i] = new ProjectTeam(teamSize, usingFaavor, i);
        } // End For

    } // Constructor

    public String getCompanyName(){
        return companyName;
    } // End getCompanyName
    public int getNumOfEmployees() {
        return numOfEmployees;
    } // End getNumOfEmployees

    public boolean finished(int limit){
        boolean output = true;

        // Check if each team has had 200 interactions
        for (ProjectTeam current : teams){
            if (current.teamData.getNumOfInteractions() < limit){
                output = false;
            } // End if
        } // End for

        return output;
    } // End notFinished

} // End Company