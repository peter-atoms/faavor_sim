import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.InvalidParameterException;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by peter on 5/25/16.
 */
public class WebSimulation extends Applet {
    // Data Fields
    static PQ agenda = new PQ();
    static Company currentCompany;
    static int limit = 200; // number of interactions
    static int timeSaved;
    static boolean finished = false;
    static boolean initialized = false;
    static int numOfTeams;
    static int sizeOfTeams;
    static int numOfEmployees;
    static String companyName;
    Font f;

    public void init(){
        f = new Font("SansSerif", Font.BOLD, 24);
        setBackground(Color.black);
        setBounds(0, 0, 1280, 800);
        //setLocation();
    } // end init

    public void start(){
        setVisible(true);
        run();
    } // end start

    public void destroy(){
        setVisible(false);
        super.destroy();
        System.exit(0);
    } // end destroy

    public void paint(Graphics g){
        g.setFont(f);
        g.setColor(Color.white);

        // Label axes
        String s1 = "Without Faavor";
        String s2 = "With Faavor";

        g.drawString(s1, 320 - (g.getFontMetrics().stringWidth(s1) / 2), 680);
        g.drawString(s2, 960 - (g.getFontMetrics().stringWidth(s2) / 2), 680);

        g.setFont(new Font("SansSerif", Font.ITALIC, 10));
        g.drawString("Interaction Length (in Minutes)",
                (640 - (g.getFontMetrics().stringWidth("Interaction Length (in Minutes)") / 2)), 680);
        g.setFont(new Font("SansSerif", Font.PLAIN, 10));
        for (int k = 0; k < 700; k+=100) {
            g.drawString(""+k, (640 - (g.getFontMetrics().stringWidth(""+k) / 2)), 650-k);
        } // end for

        if(initialized) { // avoid NullPointerException
            // Team 1: w/o Faavor; Team 2: w/Faavor
            ArrayList team1Data = currentCompany.teams[0].teamData.getInteractions();
            ArrayList team2Data = currentCompany.teams[1].teamData.getInteractions();

            int team1;
            int team2;

            // Display results via graph -> Only two of the teams
            for (int i = 0; i < team1Data.size(); i++) {
                team1 = (int) team1Data.get(i);
                g.setColor(Color.yellow);
                g.fillRect((20 + 3 * i), 650 - team1, 2, team1);
            } // End for

            for (int j = 0; j < team2Data.size(); j++) {
                team2 = (int) team2Data.get(j);
                g.setColor(Color.cyan);
                g.fillRect((660 + 3 * j), 650 - team2, 2, team2);
            } // End for
        } // end if

        if (finished) {
            // Display time saved
            // Split into days, hours, minutes
            // Days calculated as calendar days
            int days = timeSaved / (60 * 24);
            int hours = (timeSaved % (60 * 24)) / 60;
            int minutes = timeSaved % 60;

            // Calculate cost savings, assuming $50/hr average
            int costSaved = ((24 * days) + hours) * 50;
            String savings = NumberFormat.getCurrencyInstance().format(costSaved);
            String message1 = "Time saved by using Faavor";
            String message2 = "Money saved by using Faavor";
            String converted = "";

            // Check if nouns should be in plural form or not
            if (days == 1) {
                converted += (days + " day, ");
            } else {
                converted += (days + " days, ");
            } // End if-else

            if (hours == 1) {
                converted += (hours + " hour, ");
            } else {
                converted += (hours + " hours, ");
            } // End if-else

            if (minutes == 1) {
                converted += (minutes + " minute");
            } else {
                converted += (minutes + " minutes");
            } // End if-else

            // Reset font
            g.setFont(new Font("SansSerif", Font.BOLD, 24));

            // Centering taken from: http://stackoverflow.com/questions/31322129/java-how-to-center-a-string-in-the-middle-of-the-frame
            int len1 = g.getFontMetrics().stringWidth(message1);
            int len2 = g.getFontMetrics().stringWidth(message2);
            g.setColor(Color.white);
            g.drawLine((640 - len1) / 2, 745, (640 + len1) / 2 , 745);
            g.drawString(message1, (640 - len1) / 2, 740);

            g.drawLine(960 - (len2 / 2), 745, 960 + (len2 / 2), 745);
            g.drawString(message2, 960 - (len2 / 2), 740);

            g.setFont(new Font("SansSerif", Font.ITALIC, 24));
            g.setColor(Color.green);
            int len3 = g.getFontMetrics().stringWidth(converted);
            int len4 = g.getFontMetrics().stringWidth(savings);

            g.drawString(converted, (640 - len3) / 2, 775);
            g.drawString(savings, 960 - (len4 / 2), 775);
        } // End if
    } // End paint

    // Methods for solicitation of company data
    public int getNumOfTeams(){
        // Returns validated number of teams as entered
        boolean validInput = false;
        String displayMessage = "Enter Number of Teams (to be doubled):";
        String input;
        int output = 1;
        JOptionPane pane = new JOptionPane();
        Frame f = new Frame();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                destroy();
            }
        });
        f.setLocationRelativeTo(null);

        while (!validInput){
            // Solicit user input
            input = pane.showInputDialog(f, displayMessage, "",JOptionPane.PLAIN_MESSAGE);

            if (input == null){ // End on close/cancel
                destroy();
            } // End if

            try{
                input = input.trim();
                output = Integer.parseInt(input);
                if (output <= 0){
                    throw new InvalidParameterException();
                } // End if
                validInput = true;
            } catch(InvalidParameterException e){
                displayMessage = "Please try again.\nEnter Number of Teams:";
            } // End try-catch
        } // End while

        if (output == 0){
            output = 1;
        } // End if

        return output;
    } // End getNumOfTeams

    public int getTeamSize(){
        // Returns validated team size
        boolean validInput = false;
        String displayMessage = "Enter Size of Teams:";
        String input;
        int output = 1;
        JOptionPane pane = new JOptionPane();
        Frame f = new Frame();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                destroy();
            }
        });
        f.setLocationRelativeTo(null);

        while (!validInput){
            // Solicit user input
            input = pane.showInputDialog(f, displayMessage, "",JOptionPane.PLAIN_MESSAGE);

            if (input == null){ // End on close/cancel
                destroy();
            } // End if

            try{
                input = input.trim();
                output = Integer.parseInt(input);
                if (output <= 0){
                    throw new InvalidParameterException();
                } // End if
                validInput = true;
            } catch(Exception e){
                displayMessage = "Please try again.\nEnter Size of Teams:";
            } // End try-catch
        } // End while

        if (output == 0){
            output = 1;
        } // End if

        return output;
    } // End getTeamSize

    public int getNumOfEmployees(){
        // Returns validated number of employees
        boolean validInput = false;
        String displayMessage = "Enter Number of Employees:";
        String input;
        int output = 1;
        JOptionPane pane = new JOptionPane();
        Frame f = new Frame();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                destroy();
            }
        });
        f.setLocationRelativeTo(null);

        while (!validInput){
            // Solicit user input
            input = pane.showInputDialog(f, displayMessage, "",JOptionPane.PLAIN_MESSAGE);

            if (input == null){ // End on close/cancel
                destroy();
            } // End if

            try{
                input = input.trim();
                output = Integer.parseInt(input);
                if (output < (numOfTeams * sizeOfTeams)){
                    // Make sure number of employees is enough to fill all the teams
                    throw new InvalidParameterException();
                } // End if
                validInput = true;
            } catch(Exception e){
                displayMessage = "Please try again. Must have at least " + (numOfTeams * sizeOfTeams) +
                        " employees.\nEnter Number of Employees:";
            } // End try-catch
        } // End while

        if (output == 0){
            output = 1;
        } // End if

        return output;
    } // End getNumOfEmployees

    public String getCompanyName(){
        String input;
        String displayMessage = "Enter Company Name:";
        JOptionPane pane = new JOptionPane();
        Frame f = new Frame();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                destroy();
            }
        });
        f.setLocationRelativeTo(null);

        // Solicit user input
        input = pane.showInputDialog(f, displayMessage, "",JOptionPane.PLAIN_MESSAGE);

        if (input == null){ // End on close/cancel
            destroy();
        } // End if

        return input.trim();
    } // End getCompanyName


    public void run() {
        // Number of interactions: 200
        // Length of simulation: ~6 months (259,200 minutes)
        // Assuming 30 days per month, 5 work days per week
        // Simulation time: 130 days
        // @8 hours/day: 1040 hours = 62,400 minutes simulation time

        // Stop creating interactions if 200 have occurred for given team

        // *** For Future Iterations: ***
        // -Adjust interaction times to account for ~8 hour workdays?
        // -Generate time between interactions?

        // Instantiate company, which in turn creates teams,
        // Which in turn create team members
        // Who each create Interactions
        numOfTeams = getNumOfTeams();
        sizeOfTeams = getTeamSize();
        numOfEmployees = getNumOfEmployees();
        companyName = getCompanyName();

        // Number of teams doubled to compare performance
        currentCompany = new Company(2*numOfTeams, companyName, numOfEmployees, sizeOfTeams);
        initialized = true;

        // Delay before displaying results
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        } // End try-catch

        int currentTeam;

        while (agenda.getCurrentTime() < 62400 && !currentCompany.finished(limit)){
            // Run for 6 months, make sure all interactions are complete
            currentTeam = agenda.remove().run();

            if (currentTeam == 1 || currentTeam == 0){
                // Only delay/display results for first two teams
                repaint();

                // Pause to allow gradual printing
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {

                } // End try-catch
            } // End if

            if (currentCompany.finished(limit)){
                // End simulation if all interactions are complete
                break;
            } // End if
        } // End while

        // Allow final numbers to be displayed
        finished = true;

        // Calculate time saved
        int withFaavor = 0;
        int withoutFaavor = 0;
        //int stopPoint =

        for (int i = 0; i < currentCompany.teams.length; i++){
            if (i % 2 == 0){ // Not using Faavor
                withoutFaavor += currentCompany.teams[i].teamData.getTotalInteractionTime();
            } else { // Using Faavor
                withFaavor += currentCompany.teams[i].teamData.getTotalInteractionTime();
            } // End if-else
        } // End for
        timeSaved = withoutFaavor - withFaavor;

        // Final display
        repaint();

    } // End run

} // class WebSimulation
