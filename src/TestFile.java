import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.io.File;

public class TestFile {

    public HashMap<String,int[]> read_scores(String fileName){
        HashMap<String,int[]> scoreFile = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            reader.readLine(); // read the head line one time
            while((line = reader.readLine())!= null){
                String[] score = line.split(",");
                int[] point = new int[score.length];
                for (int i =1; i<score.length; i++){
                    point[i-1] = Integer.parseInt(score[i]);
                }
                scoreFile.put(score[0],point);
            }
            return scoreFile;
        }catch(Exception e){
            e.printStackTrace(); 
        }
        return null;
    }

    public int getTotalScore(HashMap<String,int[]> dict, String id){
        if(dict.containsKey(id)){
            int[] allScore = dict.get(id);
            int totalScore = 0;
            for(int score : allScore){
                totalScore += score;
            }
            return totalScore;
        }else{
            return -1;
        }
    }

    public HashSet<String> getEditedStudentID(String filePath){
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            HashSet<String> students = new HashSet<String>();
            String line;
            reader.readLine();
            while((line = reader.readLine()) != null){
                String[] parts = line.split(","); 
                students.add(parts[0]);
            }
            return students;
        }catch(Exception e){
            e.printStackTrace(); 
        }
        return null;
    }

    public void createNewEditedScore(String oldScore, String editScore, String newScore){
        HashMap<String, int[]> studentScore = read_scores(oldScore); //ref for hashmap from old scores.
        try(BufferedReader reader = new BufferedReader(new FileReader(editScore))){//replace the int[] in hashmap corresponding for editScore
            String line;
            int[] score;
            reader.readLine();
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if(studentScore.containsKey(parts[0])){
                    score = studentScore.get(parts[0]);
                    score[Integer.parseInt(parts[1])] = Integer.parseInt(parts[2]);
                    studentScore.put(parts[0], score);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        try {//this check for newfile file path if not yet created, create new file.
            File file = new File(newScore);
            if (file.exists()) {
                System.out.println("File already exists.");
            } else {
                if (file.createNewFile()) {
                    System.out.println("File created successfully.");
                } else {
                    System.out.println("File creation failed.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error when creating or checking the file: " + e.getMessage());
            e.printStackTrace();
        }
        
        //this write the information from edit hashmap to the new file. 
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(newScore))){ 
            writer.write("ID,Quiz1,Quiz2,Quiz3,Quiz4,Quiz5+\n");
            for (String studentID : studentScore.keySet()){
                writer.write(studentID + " - " + Arrays.toString(studentScore.get(studentID))+ "\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
            
    }
}
