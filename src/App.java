import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class App {
       public static void main(String[] args) {
        // Test the methods

        TestFile testFile = new TestFile();

        // Task 1: Test the 'read_scores' method
        HashMap<String, int[]> allScores = testFile.read_scores("G:\\A2. TIME-Peerat\\ISE\\Year 2\\ADV compog\\Lab 9\\Lab 9\\src\\student_scores.csv");
        System.out.println("All Scores:");
        for (String studentID : allScores.keySet()) {
            System.out.println(studentID + " - " + Arrays.toString(allScores.get(studentID)));
        }
 
        // Task 2: Test the 'getTotalScore' method
        String studentIDToLookup = "6632462421";
        int totalScore = testFile.getTotalScore(allScores, studentIDToLookup);
        if (totalScore != -1) {
            System.out.println("Total Score for " + studentIDToLookup + ": " + totalScore);
        } else {
            System.out.println("Student not found.");
        }

        // Task 3: Test the 'getEditedStudentID' method
        HashSet<String> editedStudentIDs = testFile.getEditedStudentID("G:\\A2. TIME-Peerat\\ISE\\Year 2\\ADV compog\\Lab 9\\all_student_scores\\edited_scores.csv");
        System.out.println("Edited Student IDs: " + editedStudentIDs);

        // Task 4: Test the 'createNewEditedScore' method
        testFile.createNewEditedScore("G:\\A2. TIME-Peerat\\ISE\\Year 2\\ADV compog\\Lab 9\\Lab 9\\src\\student_scores.csv", "G:\\A2. TIME-Peerat\\ISE\\Year 2\\ADV compog\\Lab 9\\all_student_scores\\edited_scores.csv", "G:\\A2. TIME-Peerat\\ISE\\Year 2\\ADV compog\\Lab 9\\Lab 9\\src\\new_scores.csv");
        System.out.println("New scores have been written to 'new_scores.csv'."); 
    }
}
