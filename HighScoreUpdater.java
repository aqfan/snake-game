import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HighScoreUpdater {
    int score;
    
    private Map<String, Integer> easy = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
    private Map<String, Integer> medium = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
    private Map<String, Integer> hard = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
    
    public static class FormatException extends Exception {
        private static final long serialVersionUID = 390458644716076032L;

        public FormatException(String msg) {
            super(msg);
        }
    }
    
    public HighScoreUpdater() throws IOException, FormatException {
        updateData();
    }
    
    public void write(String username, int score, String difficulty) throws IOException, FormatException {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter("highscores.txt", true));
            if (insertData(username, score, difficulty)){
            output.append(username + "," + score + "," + difficulty);
            output.newLine();
            output.close();
            }
        } catch (IOException e) {
            System.out.printf("ERROR writing score to file: %s\n", e);
        }
        updateData();
    }
    
    public void updateData() throws IOException, FormatException {
        String str = "";
        BufferedReader in = new BufferedReader(new FileReader("highscores.txt"));
        try {
            while ((str = in.readLine()) != null) {
                if (str.trim().length() > 0) {
                    List<String> parsed = Arrays.asList(str.trim().split(","));
                    if (parsed.size() != 3) {
                        throw new HighScoreUpdater.FormatException("invalid file format");
                    } else {
                        String key = parsed.get(0).trim();
                        int value = Integer.parseInt(parsed.get(1).trim());
                        String level = parsed.get(2).trim();
                        insertData(key, value, level);
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.printf("ERROR reading score file: %s\n", e);
        }
    }
    public boolean insertData(String username, int score, String difficulty){
        if (difficulty.equals("Easy")){
            if(easy.containsKey(username)){
                if(easy.get(username) < score){
                    easy.put(username, score);
                    return true;
                }
            } else {
                easy.put(username, score);
                return true;
            }
        } else if (difficulty.equals("Medium")) {
            if(medium.containsKey(username)){
                if(medium.get(username) < score){
                    medium.put(username, score);
                    return true;
                }
            } else {
                medium.put(username, score);
                return true;
            }
        } else if (difficulty.equals("Hard")) {
            if(hard.containsKey(username)){
                if(hard.get(username) < score){
                    hard.put(username, score);
                    return true;
                }
            } else {
                hard.put(username, score);
                return true;
            }
        }
        return false;
    }
    
    public Map<String, Integer> getEasy(){
        return easy;
    }
    
    public Map<String, Integer> getMedium(){
        return medium;
    }
    
    public Map<String, Integer> getHard(){
        return hard;
    }
}
