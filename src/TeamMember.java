/**
 * Created by peter on 5/16/16.
 */
public class TeamMember {
    // Data fields
    private ProjectTeam team;

    public TeamMember(ProjectTeam setTeam, int num){

        team = setTeam;
        // Alternate start of interactions so results display evenly
        WebSimulation.agenda.add(new Interaction(0, team), (team.getTeamNumber() + (2*num)));
    } // Constructor


} // End TeamMember
