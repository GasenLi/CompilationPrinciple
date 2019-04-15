import java.io.*;

public class Statistics {
    int num_chars = 0, num_lines = 0;


    public void collection() throws IOException {
        File sourceFile = new File("E:\\workSpace\\CompilationPrinciple\\LexicalAnalysisAutoGenerator\\src\\main\\java\\data.txt");
        Reader fileReader = new FileReader(sourceFile);

        char[] data = new char[1024];
        while (fileReader.read(data)!=-1){
            for(char c : data){
                if(c != 0){
                    num_chars++;

                    if(c == '\n'){
                        num_lines++;
                    }
                }else {
                    break;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Statistics statistics = new Statistics();
        statistics.collection();

        System.out.printf("This file has %5d chars, %5d lines", statistics.num_chars, statistics.num_lines);
    }

}
