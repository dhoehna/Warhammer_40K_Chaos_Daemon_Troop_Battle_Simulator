/*
 Darren Hoehna
 CS& 141
 Instructor Richardson
 Final Project Assingment
 03/16/12
 */

/*
 This program simulates a battle between two squads from Chaos Daemon army
 from Warhammer 40K\
 */
/*
 There are 30 functions and one meathod.
 makeToHitTable():  Constructs the to Hit table to.  This program returns the refrence
 to the two dimensional array.
 makeToWoundTable():  Constructs the to Wound table.  This program returns a refrence
 to the two dimensional array.

 getTeamName(console, "first"/"Second"): This asks the user what troops will compose the squad and
 returns the name of the squad as a string.
											
 console: This object is passed so the method does not have to construct a new scanner.
 "First"/"Second": This String is passed which tells the user which squad he is choosing for.
				
 getAttribute(team(One/Two): This function constructs an array that holds all the 
 attributes for each squad.  This function returns an array.
								
 team(One/Two): The team name is taken so the computer knows which text file
 to look in so the computer can read the correct attributes.

 getSquadTotal(console, "One"/"Two", team(One/Two)Attribute): This function
 asks the user how many troops will compose each squad.
 console: The scanner object is passed to the function so the function does not 
 have to construct a scanner.
 "One"/"Two": This String is passed so the output will show which team the
 user is choosing for
 team(One/Two)Attribute: This array is passed so the method can look up the
 wound count.
												
 battle(squadOneCount, squadTwoCount, squadOneLength, squadTwoLength, 
 teamOneAttribute, teamTwoAttribute, toHitTable, toWoundTable, r, 
 teamOneName, teamTwoName):This program does the battle between the 
 two squads.
 squad(One/Two)Count: the array that holds the information about which troops
 are alive or dead.
 squad(One/Two)Length: The length of the squad (This is so I don't have to deal
 with aray handeling as much).
 team(One/Two)Attribute: The array that holds the information about the 
 attributes of each squad.
 toHitTable: This is the double array that holds the information for
 what number to roll over to make a hit
 toWoundTable: This is the doble aray that holds the number that the troop
 has to roll over to wound.
 r: This is the random object that is passed for the dice roll.
 This is passed so the method does not have to construct another random object.
 This simulates rollin ga D6.
 team(One/Two)Name: This is here because the plauge bearers have two special rules.
 Plaugebearers have poisned weapons so they always wound o na 4+.
 Also they have feel no pain and thus have a 4+ save if they fialed
 their intial save.
 So the general syntax is if (Team is a plaugebearer) {
 } else {Do regular stuff}
											
								
 deadCount(squad(One/Two)): This program finds where the first live troop is in the squad array.
 Once the program comes accross an array element that is not zero
 the element number is returned.
										
 squad(One/Two): This is the array that holds the information about
 which troops are alive.

 toHit(toHitTable, team(One/Two)Attribute[0], team(One/Two)Attribute[0],
 squad(One/Two)Length, start(One/Two)Dead, r, team(One/Two)Attribute[6]):
 This function simulates the dice roll for each attack and returns
 the number of hits that have been made.
 toHitTable:This is the table that the troops have to roll over to hit.
 team(One/Two)Attribute[0]: This is the weapon skill attribute for
 the team in the function signature.
 squad(One/Two)Length: This number plus the attack attribute gets us
 how many attacks(dice to roll) to make.
 start(One/Two)Dead: This is where the first troop is alive in the array.
 This number tells the computer where to take off a wound.
 r: This is the random object and was passed as a parameter so the 
 function does not need to construct a random object.  This
 simulates the rolling of a D6.
 team(One/Two)Attribute[0]: This is the attack attribute for each squad.
			

 toWound(toWoundTable, team(one/Two)Attribute[2], team(One/Two)Attribute[3], (one/Two)ToHit, 
 r, team(One/Two)Name):
 This function takes the number of hits and rolls them under the "to Wound table" and returns
 the number of wounds made.
 toWoundTable: This double array holds the information for troops
 to roll over to make a wound.
 team(One/Two)Attribute[2]: This is the strength of the troops.  The strength
 of the attacking team and th etoughness of the defending team is placed
 on the to Wound Table to see what number to roll over ot make awound.
 team(One/Two)Attribute[3]This is the youghness of the troops.  The strength
 of the attacking team and th etoughness of the defending team is placed
 on the to Wound Table to see what number to roll over ot make awound.
 (one/two)ToHit: This is the number of hits returned by the function toHit.  This is
 the number of rolls to make to see if a troop has been wounded.
 r: This is the random object that rolls a D6.  This is passed so the
 function does not have to construct a new scanner.
 team(One/Two)Name: This is to see if the team is made up of plauge bearers because
 plauge bearers have a poisned weapon and always wound on a 4+.
		
 toSave((one/two)ToWound, team(One/Two)Attribute[8], start(One/Two)Dead, 
 squad(One/Two)Count, r, team(One/Two)Name): This function rolls a D6 for each
 wound made and returns the number of troops dead (Or who failed their save).
 (one/two)ToWound: This is the number of troops who failed their saving throw.
 team(One/Two)Attribute[8] this is the number to roll over to save a wound.
 start(One/Two)Dead: This is where the first troop is alive so the function knows
 where to start taking off wounds.
 squad(One/Two)Count: This is the array that holds the squad.  if a wound is made then 
 the number is the element for which the wound is made goes down by one.
 r: This is the random object that was made in main.  This object rolls a D6.
 team(One/Two)Name: This is to see if the team is made up of plauge bearers because
 plauge bearers have a poisned weapon and always wound on a 4+.
		
 report((one/two)Dead, squad(one/two)Length, ("one"/"two"): This function sees if the squad
 is alive by adding up all the elements of an array.  If the sum == 0 then the team is dead.
		
		

 isTeamAlive(squadOneCount): Finds out if a squad is still alive by adding up all the elements
 in the squadCount.  if the sum is greater than zero the squad is still alive.
			
 squad(One/Two)Count: the array that hilds the troops dead or alive information.
 */
import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class WarhammerBattle {

    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        Scanner console = new Scanner(System.in);
        Random r = new Random();

        // constructs the "to Hit" and "to Wound" tables
        int[][] toHitTable = makeToHitTable();
        int[][] toWoundTable = maketoWoundTable();

        // Obtains the troop choices
        String teamOneName;
        String teamTwoName;
        do {
            teamOneName = getTeamName(console, "first");
        } while (teamOneName.equals("No Input"));
        do {
            teamTwoName = getTeamName(console, "second");
        } while (teamTwoName.equals("No Input"));
        
        File teamOneFileName = new File(teamOneName);
        File teamTwoFileName = new File(teamTwoName);
        
        BufferedReader read = new BufferedReader(new FileReader(teamOneFileName));
        BufferedReader secondRead = new BufferedReader(new FileReader(teamTwoFileName));

        /* 
        JarFile jarFile = new JarFile("WarhammerBattle.jar");
        JarEntry entry = jarFile.getJarEntry(teamOneName);
        InputStreamReader input = new InputStreamReader(jarFile.getInputStream(entry));
        BufferedReader read = new BufferedReader(input);
        
        JarFile secondJarFile = new JarFile("WarhammerBattle.jar");
        JarEntry secondEntry = secondJarFile.getJarEntry(teamTwoName);
        InputStreamReader secondInput = new InputStreamReader(jarFile.getInputStream(secondEntry));
        BufferedReader secondRead = new BufferedReader(secondInput);
        */
        // stores the attributes of the troops in an array

        /*
         * [0] = ws, [1] = bs, [2] = s, [3] = t, [4] = w, [5] = i, [6] = a, [7]
         * = ld, [8] = sv
         */
        int[] teamOneAttribute = getAttribute(read);
        int[] teamTwoAttribute = getAttribute(secondRead);

        // obtains the number of troops in each squad.
        int[] squadOneCount;
        int[] squadTwoCount;
        do {
        squadOneCount = getSquadTotal(console, "one", teamOneAttribute);
        } while (squadOneCount.length <= 4 || squadOneCount.length >= 21);
        int squadOneLength = squadOneCount.length;

        do {
            squadTwoCount = getSquadTotal(console, "two", teamTwoAttribute);
        } while (squadTwoCount.length <= 4 || squadTwoCount.length >= 21);
        int squadTwoLength = squadTwoCount.length;


        battle(squadOneCount, squadTwoCount, squadOneLength, squadTwoLength,
                teamOneAttribute, teamTwoAttribute, toHitTable, toWoundTable, r,
                teamOneName, teamTwoName);
    } // end main

    public static int battle(int[] squadOneCount, int[] squadTwoCount,
            int squadOneLength, int squadTwoLength,
            int[] teamOneAttribute, int[] teamTwoAttribute,
            int[][] toHitTable, int[][] toWoundTable, Random r,
            String teamOneName, String teamTwoName) {

        boolean isOneAlive = true;
        boolean isTwoAlive = true;
        int round = 2;

        while (isOneAlive && isTwoAlive) {
            //BATTLE!!!!!!!!!!!!!
            Scanner console2 = new Scanner(System.in);

            int startOneDead = deadCount(squadOneCount);
            int startTwoDead = deadCount(squadTwoCount);

            if (teamOneAttribute[5] > teamTwoAttribute[5]) {
                int oneToHit = toHit(toHitTable, teamOneAttribute[0], teamTwoAttribute[0],
                        squadOneLength, startOneDead, r, teamOneAttribute[6]);
                int oneToWound = toWound(toWoundTable, teamOneAttribute[2],
                        teamTwoAttribute[3], oneToHit, r,
                        teamOneName);
                int twoDead = toSave(oneToWound, teamTwoAttribute[8], startTwoDead,
                        squadTwoCount, r, teamTwoName);

                startTwoDead = deadCount(squadTwoCount);
                squadTwoLength = squadTwoLength - twoDead;
                isTwoAlive = isTeamAlive(squadTwoCount);
                if (!isTwoAlive) {
                    System.out.println("Team two won after " + (round - 2) + " rounds.");
                    return 1;
                }
                report(twoDead, squadTwoLength, "two");

                int twoToHit = toHit(toHitTable, teamTwoAttribute[0], teamOneAttribute[0],
                        squadOneLength, startOneDead, r, teamTwoAttribute[6]);
                int twoToWound = toWound(toWoundTable, teamTwoAttribute[2],
                        teamOneAttribute[3], oneToHit, r,
                        teamTwoName);
                int oneDead = toSave(oneToWound, teamOneAttribute[8], startOneDead,
                        squadOneCount, r, teamOneName);


                startOneDead = deadCount(squadOneCount);

                squadOneLength = squadOneLength - oneDead;

                report(oneDead, squadOneLength, "one");
                System.out.print("Press enter to procced to round " + round);
                console2.nextLine();
                round++;

            } else if (teamOneAttribute[5] < teamTwoAttribute[5]) {

                int twoToHit = toHit(toHitTable, teamTwoAttribute[0], teamOneAttribute[0],
                        squadOneLength, startOneDead, r, teamTwoAttribute[6]);
                int twoToWound = toWound(toWoundTable, teamTwoAttribute[2],
                        teamOneAttribute[3], twoToHit, r,
                        teamTwoName);
                int oneDead = toSave(twoToWound, teamOneAttribute[8], startOneDead,
                        squadOneCount, r, teamOneName);

                startOneDead = deadCount(squadOneCount);
                squadOneLength = squadOneLength - oneDead;
                report(oneDead, squadOneLength, "one");
                isOneAlive = isTeamAlive(squadOneCount);
                if (!isOneAlive) {
                    System.out.println("Team one won after " + (round - 2) + " rounds.");
                    return 1;
                }


                int oneToHit = toHit(toHitTable, teamOneAttribute[0], teamTwoAttribute[0],
                        squadOneLength, startOneDead, r, teamOneAttribute[6]);
                int oneToWound = toWound(toWoundTable, teamOneAttribute[2],
                        teamTwoAttribute[3], oneToHit, r,
                        teamOneName);
                int twoDead = toSave(oneToWound, teamTwoAttribute[8], startTwoDead,
                        squadTwoCount, r, teamTwoName);


                startTwoDead = deadCount(squadTwoCount);
                squadTwoLength = squadTwoLength - twoDead;

                report(twoDead, squadTwoLength, "two");
                System.out.print("Press enter to procced to round " + round);
                console2.nextLine();
                round++;
            } else { //teamOneAttribute[5] == teamTwoAttribute[5]
                int oneToHit = toHit(toHitTable, teamOneAttribute[0], teamTwoAttribute[0],
                        squadOneLength, startOneDead, r, teamOneAttribute[6]);
                int oneToWound = toWound(toWoundTable, teamOneAttribute[2],
                        teamTwoAttribute[3], oneToHit, r,
                        teamOneName);

                int twoToHit = toHit(toHitTable, teamTwoAttribute[0], teamOneAttribute[0],
                        squadOneLength, startOneDead, r, teamTwoAttribute[6]);
                int twoToWound = toWound(toWoundTable, teamTwoAttribute[2],
                        teamOneAttribute[3], oneToHit, r,
                        teamTwoName);


                int oneDead = toSave(oneToWound, teamOneAttribute[8], startOneDead,
                        squadOneCount, r, teamOneName);
                int twoDead = toSave(oneToWound, teamTwoAttribute[8], startTwoDead,
                        squadTwoCount, r, teamTwoName);

                startOneDead = deadCount(squadOneCount);
                squadOneLength = squadOneLength - oneDead;

                startTwoDead = deadCount(squadTwoCount);
                squadTwoLength = squadTwoLength - twoDead;

                report(oneDead, squadOneLength, "one");
                report(twoDead, squadTwoLength, "two");
                System.out.print("Press enter to procced to round " + round);
                console2.nextLine();
                round++;
            } // end if/else for who attacks first

            // true = there are people who are alive.  false =  team is dead.
            isOneAlive = isTeamAlive(squadOneCount);
            isTwoAlive = isTeamAlive(squadTwoCount);
            // gets the sum of all the elements of the squad array.  If the sum is greater then one
            // then there is at least on troop alive

        } // end while battles until one team is dead

        if (isOneAlive) {
            System.out.println("Team one won after " + (round - 2) + " rounds.");
        } else {
            System.out.println("Team two won after " + (round - 2) + " rounds.");
        } // end if/else.  reports the winner.
        return 1;
    } // end battle.

    public static int[][] makeToHitTable() {
        // makes the to Hit Table
        int[][] toHitTable = new int[10][10];
        /*
         * toHitTable[0][0] = {4, 4, 5, 5, 5, 5, 5, 5, 5, 5}; toHitTable[1][1] =
         * {3, 4, 4, 4, 5, 5, 5, 5, 5, 5}; toHitTable[2][2] = {3, 3, 4, 4, 4, 5,
         * 5, 5, 5, 5}; toHitTable[3][3] = {3, 3, 3, 4, 4, 4, 4, 4, 5, 5};
         * toHitTable[4][4] = {3, 3, 3, 3, 4, 4, 4, 4, 4, 4}; toHitTable[5][5] =
         * {3, 3, 3, 3, 3, 4, 4, 4, 4, 4}; toHitTable[6][6] = {3, 3, 3, 3, 3, 3,
         * 4, 4, 4, 4}; toHitTable[7][7] = {3, 3, 3, 3, 3, 3, 3, 4, 4, 4};
         * toHitTable[8][8] = {3, 3, 3, 3, 3, 3, 3, 3, 4, 4}; toHitTable[9][9] =
         * {3, 3, 3, 3, 3, 3, 3, 3, 3, 4};
         */
        // makes row 0
        toHitTable[0][0] = 4;
        toHitTable[0][1] = 4;
        for (int x = 2; x <= 9; x++) {
            toHitTable[0][x] = 5;
        } // end for, sets the rest of the values to five

        // makes row 1
        toHitTable[1][0] = 3;
        toHitTable[1][1] = 4;
        toHitTable[1][2] = 4;
        toHitTable[1][3] = 4;
        for (int x = 4; x <= 9; x++) {
            toHitTable[1][x] = 5;
        } // end for, fills the rest of the table in with fives

        // makes row 2
        toHitTable[2][0] = 3;
        toHitTable[2][1] = 3;
        toHitTable[2][2] = 4;
        toHitTable[2][3] = 4;
        toHitTable[2][4] = 4;
        for (int x = 5; x <= 9; x++) {
            toHitTable[2][x] = 5;
        } // end for, fills the rest of the row with fives.

        // makes row 3
        for (int x = 0; x <= 2; x++) {
            toHitTable[3][x] = 3;
        } // end for
        for (int x = 3; x <= 7; x++) {
            toHitTable[3][x] = 4;
        } // end for
        toHitTable[3][8] = 5;
        toHitTable[3][9] = 5;

        //makes rows four - 9
        for (int x = 4; x <= 9; x++) {
            for (int y = 0; y <= x - 1; y++) {
                toHitTable[x][y] = 3;
            } // end for, fills in three's
            for (int z = x; z <= 9; z++) {
                toHitTable[x][z] = 4;
            } // end for, fills in the rest of the row with fours
        } // end for, fill sin rows 4 through nine.
        return toHitTable;
    } // end makeToHitTable

    public static int[][] maketoWoundTable() {
        // makes the to WOund table

        int[][] toWoundTable = new int[10][10];
        
        // makes row 0	
        toWoundTable[0][0] = 4;
        toWoundTable[0][1] = 5;
        toWoundTable[0][2] = 6;
        toWoundTable[0][3] = 6;
        for (int x = 4; x <= 9; x++) {
            toWoundTable[0][x] = 7;
        } // for for

        // makes row 1
        toWoundTable[1][0] = 3;
        toWoundTable[1][1] = 4;
        toWoundTable[1][2] = 5;
        toWoundTable[1][3] = 6;
        toWoundTable[1][4] = 6;
        for (int x = 5; x <= 9; x++) {
            toWoundTable[1][x] = 7;
        } // end for

        // makes rows 2 - 5
        for (int x = 2; x <= 5; x++) {
            for (int y = 0; y <= x - 1; y++) {
                toWoundTable[x][y] = 2;
            } // end for to fill with 2;s
            toWoundTable[x][x] = 3;
            toWoundTable[x][x + 1] = 4;
            toWoundTable[x][x + 2] = 5;
            toWoundTable[x][x + 3] = 6;
            toWoundTable[x][x + 4] = 6;
            for (int z = x + 5; z <= 9; z++) {
                toWoundTable[x][z] = 7;
            } // end for, fills the rest of the row with sevens.
        } // end for.  Fills up rows 2 through 5

        //makes row 6
        for (int x = 0; x <= 4; x++) {
            toWoundTable[6][x] = 2;
        } // end for, fills the first four cells of row six with 2's.
        toWoundTable[6][5] = 3;
        toWoundTable[6][6] = 4;
        toWoundTable[6][7] = 5;
        toWoundTable[6][8] = 6;
        toWoundTable[6][9] = 6;

        // makes row 7
        for (int x = 0; x <= 5; x++) {
            toWoundTable[7][x] = 2;
        } // end for, fills the first four cells of row seven with 2's
        toWoundTable[7][6] = 3;
        toWoundTable[7][7] = 4;
        toWoundTable[7][8] = 5;
        toWoundTable[7][9] = 6;

        // makes row 8
        for (int x = 0; x <= 6; x++) {
            toWoundTable[8][x] = 2;
        } // end for, fills the first seven cells with 2's
        toWoundTable[8][7] = 3;
        toWoundTable[8][8] = 4;
        toWoundTable[8][9] = 5;

        // makes row 9
        for (int x = 0; x <= 7; x++) {
            toWoundTable[9][x] = 2;
        } // end for, fills the first eight cells of row nine with 2's
        toWoundTable[9][8] = 3;
        toWoundTable[9][9] = 4;
        return toWoundTable;
    } // end makeToWoundTable

    public static String getTeamName(Scanner console, String number) {
        int troopChoice = 0;
        try {
            System.out.println("Who is in the " + number + " squad?");
            System.out.println("1 = Pink Horrors");
            System.out.println("2 = Blood Letters");
            System.out.println("3 = Daemonettes");
            System.out.println("4 = Plaguebearers");
            System.out.print("Please enter a number between one and four.");
            troopChoice = console.nextInt();
            System.out.println();

            if (troopChoice == 1) {
                String teamName = "pinkhorrors.txt";
                return teamName;
            } else if (troopChoice == 2) {
                String teamName = "bloodletters.txt";
                return teamName;
            } else if (troopChoice == 3) {
                String teamName = "daemonettes.txt";
                return teamName;
            } else if (troopChoice == 4) {
                String teamName = "plaguebearers.txt";
                return teamName;
            } else {
                System.out.println("Invalid input.");
                System.out.println("Please enter a number between one and five.");
                System.out.println();
                return "No Input";
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry.");
            System.out.println("Please enter a number between one and four.");
            System.out.println();
            return "No Input";
        } // end try/catch for Invalid Mismatch Exception.				
    } // end getTeamOneName

    public static int[] getSquadTotal(Scanner console, String number, int[] attribute) {
        int teamCount = 0;
        try {
            System.out.print("How many units will compose squad " + number + "? (5 - 20):");
            teamCount = console.nextInt(); // 5- 20
            if (teamCount <= 4 || teamCount >= 21) {
                System.out.println("Invalid squad size.");
                System.out.println();
                int[] teamTotal = new int[teamCount];
                return teamTotal;
            } // end if.  makes sure the squad is a legal size between five and twenty
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            System.out.println();
            console.next();
            int[] teamTotal = new int[3];
        } // end try/catch block for an InvalidMismatchException
        int[] teamTotal = new int[teamCount];
        for (int alive = 0; alive <= teamTotal.length - 1; alive++) {
            teamTotal[alive] = attribute[4];
        } // end for sets all the units to having one wound
        return teamTotal;
    } // end getSquadTotal

    public static int[] getAttribute(BufferedReader read)
            throws FileNotFoundException, IOException {
        int[] attribute = new int[9];
        for (int attributeFinder = 0; attributeFinder <= 8; attributeFinder++) {
            String line = read.readLine();
            Scanner inputLine = new Scanner(line);
            while (!inputLine.hasNextInt()) {
                inputLine.next();
            } // end while.  Makes the scanner consume input till it reaches the int..
            attribute[attributeFinder] = inputLine.nextInt();
        } // end for.  Loks thourhg all the lines and records the attributes in an array
        return attribute;
    } // end getAttribute.  looks up the attributes for both teams and stores
    // the attributes in an array.

    public static boolean isTeamAlive(int[] team) {
        int sum = 0;
        for (int x = 0; x <= team.length - 1; x++) {
            sum += team[x];
        } // end for.  adds up all the wounds left
        if (sum >= 1) {
            return true;
        } else { // team is dead
            return false;
        } // end if/else.  Returns true or false depending on weahter there are
        //wounds left to take or not.
    }// end isTeamAlive.  Finds out if a squad is still alive

    public static int deadCount(int[] squadCount) {
        int counter = 0;
        for (int x = 0; x <= squadCount.length - 1; x++) {
            if (squadCount[x] != 0) {
                return counter;
            } // end if.  breaks the method if a number greater then zero is found.
            counter++;
        } // end for.  traverses the array looking for a number greater then 0.
        counter++;
        return -1;
    } // ends deadCount.  Returns the position in the array where people still live.

    public static int toHit(int[][] toHitTable, int WSOne, int WSTwo,
            int squadLength, int startDead, Random r, int AValue) {
        int hit = 0;
        for (int x = 1; x <= squadLength * AValue; x++) {
            int toHitRoll = r.nextInt(6) + 1;
            if (toHitRoll >= toHitTable[WSOne][WSTwo]) {
                hit++;
            } // end if.  increases the hit counter is the troop made a hit
        } // end for,rolls a six sided die for each unit alive
        return hit;
    } // end toHit.  Gets how many times the opposing team has been hit.

    public static int toWound(int[][] toWoundTable, int SOne, int TTwo, int oneToHit,
            Random r, String teamName) {
        int wound = 0;
        if (teamName.equals("plaguebearers.txt")) {
            for (int x = 1; x <= oneToHit; x++) {
                int wounded = r.nextInt(6) + 1;
                if (wounded >= 4) {
                    wound++;
                } // end if.  Makes a wound if the roll is four or greater
            } // end for.  Rolls a D6 for each hit.
            return wound;
        } else { //the team is not made of plauge bearers.
            for (int x = 1; x <= oneToHit; x++) {
                int wounded = r.nextInt(6) + 1;
                if (wounded >= toWoundTable[SOne][TTwo]) {
                    wound++;
                } // end if.  increases the wound count if the roll wounds
            } // end for.  rols a six sided die for each hit made
            return wound;
        } // end if/else.  Plaugebearers wound on a 4 up and ingnore the toughness value.
    } // end toWound.  finds out how many hits wounded

    public static int toSave(int wounded, int save, int startDead, int[] squadCount,
            Random r, String teamName) {
        int dead = 0;
        int x = startDead;
        int rolls = 0;
        // x looks at the current living troop.
        if (teamName.equals("plaguebearers.txt")) {
            try {
                while (squadCount[x] != 0) {
                    int savingThrow = r.nextInt(6) + 1;
                    int feelNoPain = r.nextInt(6) + 1;
                    rolls++;
                    if (rolls == wounded) {
                        Arrays.sort(squadCount);
                        return dead;
                    } // breaks the function if the saving thorws we have rolled
                    // is equal to the number of wounds we needed to save.
                    if (savingThrow < save && feelNoPain < 4) { // saving throw is failed
                        // and feel no pain is failed
                        squadCount[x] = squadCount[x] - 1;
                        if (squadCount[x] == 0) {
                            x++;
                            dead++;
                        } // end if.  If the troop has zero wounds, increase x.
                    } // end if.  Takes off one wound if the saving Throw is failed
                } // end while.  Rolls a D6 as long as the current troop has more than zero wounds.
            } // end if else.  Plaugebrearers have a feel No Pain rule with saving throws.
            catch (ArrayIndexOutOfBoundsException e) {
                return dead;
            } // end try/catch.  Returns dead if there are more wounds left to save then troops.
            Arrays.sort(squadCount);
            return dead;
        } else {
            try {
                while (squadCount[x] != 0) {
                    int savingThrow = r.nextInt(6) + 1;
                    rolls++;
                    if (rolls == wounded) {
                        Arrays.sort(squadCount);
                        return dead;
                    } // breaks the function if the saving thorws we have rolled
                    // is equal to the number of wounds we needed to save.
                    if (savingThrow < save) { // saving throw is failed
                        // and feel no pain is failed
                        squadCount[x] = squadCount[x] - 1;
                        if (squadCount[x] == 0) {
                            x++;
                            dead++;
                        } // end if.  If the troop has zero wounds, increase x.
                    } // end if.  Takes off one wound if the saving Throw is failed
                } // end while.  Rolls a D6 as long as the current troop has more than zero wounds.
            } // end if else.  Plaugebrearers have a feel No Pain rule with saving throws.
            catch (ArrayIndexOutOfBoundsException e) {
                return dead;
            } // end try/catch.  Returns dead if there are more wounds left to save then troops.
        } // end toSave.  returns the number of troops killed
        return -1;
    }

    public static void report(int dead, int squad, String team) {
        System.out.println("Number of troops dead in squad " + team + ": " + dead);
        System.out.println("Number of troops remaining in squad " + team + ": " + squad);
        System.out.println();
    } // end report.  Tells the user the stats of the battle.
} // end class