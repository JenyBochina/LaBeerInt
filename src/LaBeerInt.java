import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

class LaBeerInt{
    public static void main(String[] args) {    	
        try(FileReader reader = new FileReader(new File("").getAbsolutePath()+"./test/small-test.in.txt");
        	FileWriter writer = new FileWriter(new File("").getAbsolutePath()+"./test/result.txt", false))
        {
            Scanner scan = new Scanner(reader);
            int numberOfCases = Integer.parseInt(scan.nextLine());
            for(int i = 0; i < numberOfCases; i++){               
                writer.write("Case #" + String.valueOf(i + 1)+":"+"\n"+resultOfOneCase(scan.nextLine()));    
            }
            scan.close();
        }
        
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }

    public static int[][] turnMatrixAndReturnNewMatrix(int[][] m){
        int[][] temp = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    public static String resultOfOneCase(String way){
        String ret = "";
    	String[] labPaths = way.split(" ");
        int goDown = 0;
        int goLeft = 0;
        int goUp = 0;
        int goRight = 0;
        int pathWay = 0;
        for(int i = 0; i < labPaths[0].length(); i++){
            if (labPaths[0].charAt(i) == 'W'){
                if(pathWay == 0)
                    goDown++;
                if(pathWay == 1)
                    goLeft++;
                if(pathWay == 2)
                    goUp++;
                if(pathWay == 3)
                    goRight++;
            }
            if (labPaths[0].charAt(i) == 'R'){
                pathWay++;
                pathWay = pathWay % 4;
            }
            else if (labPaths[0].charAt(i) == 'L'){
                pathWay -= 1;
                if(pathWay == -1){
                    pathWay = 3;
                }
            }
        }
        String exitTurn = "";

        if(pathWay == 0)
            exitTurn = "Down";
        if(pathWay == 1)
            exitTurn = "Left";
        if(pathWay == 2)
            exitTurn = "Up";
        if(pathWay == 3)
            exitTurn = "Right";

        int goDownMax = goDown;
        int goLeftMax = goLeft;
        int goUpMax = goUp;
        int goRightMax = goRight;
        int entryDiff = goLeft - goRight;
        goDown = 0;
        goLeft = 0;
        goUp = 0;
        goRight = 0;
        pathWay = (pathWay + 2) % 4;;
        for(int i = 0; i < labPaths[1].length(); i++){
            if (labPaths[1].charAt(i) == 'W'){
                if(pathWay == 0)
                    goDown++;
                if(pathWay == 1)
                    goLeft++;
                if(pathWay == 2)
                    goUp++;
                if(pathWay == 3)
                    goRight++;
            }
            if (labPaths[1].charAt(i) == 'R'){
                pathWay++;
                pathWay = pathWay % 4;
            }
            else if (labPaths[1].charAt(i) == 'L'){
                pathWay -= 1;
                if(pathWay == -1){
                    pathWay = 3;
                }
            }
        }

        if(goDown > goDownMax)
            goDownMax = goDown;
        if(goLeft > goLeftMax)
            goLeftMax = goLeft;
        if(goUp > goUpMax)
            goUpMax = goUp;
        if(goRight > goRightMax)
            goRightMax = goRight;

        int maxVertical = 0;
        int maxHorizontal = 0;
        if(goDownMax > goUpMax){
            maxVertical = goDownMax;
        }
        else{
            maxVertical = goUpMax;
        }
        if(goLeftMax > goRightMax){
            maxHorizontal = goLeftMax;
        }
        else{
            maxHorizontal = goRightMax;
        }
        int[][] labInfo = new int[(maxVertical+1)*2][((maxHorizontal+1)*3+Math.abs(entryDiff))*2];
        int xIndex = (maxHorizontal + entryDiff) * 2 + 1;
        if(xIndex < 0){
            xIndex = 1;
        }
        int yIndex = 0;
        labInfo[yIndex][xIndex] = 1;
        pathWay = 0;
        for(int i = 0; i < labPaths[0].length(); i++){
            if (labPaths[0].charAt(i) == 'W'){
                if(pathWay == 0){
                    labInfo[yIndex + 1][xIndex] = 1;
                    labInfo[yIndex + 2][xIndex] = 1;
                    yIndex += 2;
                }
                if(pathWay == 1){
                    labInfo[yIndex][xIndex - 1] = 1;
                    labInfo[yIndex][xIndex - 2] = 1;
                    xIndex -= 2;
                }
                if(pathWay == 2){
                    labInfo[yIndex - 1][xIndex] = 1;
                    labInfo[yIndex - 2][xIndex] = 1;
                    yIndex -= 2;
                }
                if(pathWay == 3){
                    labInfo[yIndex][xIndex + 1] = 1;
                    labInfo[yIndex][xIndex + 2] = 1;
                    xIndex += 2;
                }
            }
            if (labPaths[0].charAt(i) == 'R'){
                pathWay++;
                pathWay = pathWay % 4;
            }
            else if (labPaths[0].charAt(i) == 'L'){
                pathWay -= 1;
                if(pathWay == -1){
                    pathWay = 3;
                }
            }
        }
        pathWay = (pathWay + 2) % 4;;
        for(int i = 0; i < labPaths[1].length(); i++){
            if (labPaths[1].charAt(i) == 'W'){
                if(pathWay == 0){
                    labInfo[yIndex + 1][xIndex] = 1;
                    labInfo[yIndex + 2][xIndex] = 1;
                    yIndex += 2;
                }
                if(pathWay == 1){
                    labInfo[yIndex][xIndex - 1] = 1;
                    labInfo[yIndex][xIndex - 2] = 1;
                    xIndex -= 2;
                }
                if(pathWay == 2){
                    labInfo[yIndex - 1][xIndex] = 1;
                    labInfo[yIndex - 2][xIndex] = 1;
                    yIndex -= 2;
                }
                if(pathWay == 3){
                    labInfo[yIndex][xIndex + 1] = 1;
                    labInfo[yIndex][xIndex + 2] = 1;
                    xIndex += 2;
                }
            }
            if (labPaths[1].charAt(i) == 'R'){
                pathWay++;
                pathWay = pathWay % 4;
            }
            else if (labPaths[1].charAt(i) == 'L'){
                pathWay -= 1;
                if(pathWay == -1){
                    pathWay = 3;
                }
            }
        }

        int leftBoard = 0;
        int rightBoard = 0;
        int downBoard = 0;
        Boolean checkLeft = false;
        int sum = 0;
        int[][] labInfoT = turnMatrixAndReturnNewMatrix(labInfo);

        for (int i = 0; i < labInfo.length; i++) {
            for (int j = 0; j < labInfo[i].length; j++) {
                sum += labInfo[i][j];
            }
            if(sum == 0){
                downBoard = i - 1;
                sum = 0;
                break;
            }
            sum = 0;
        }

        for (int i = 0; i < labInfoT.length; i++) {
            for (int j = 0; j < labInfoT[i].length; j++) {
                sum += labInfoT[i][j];
            }
            if(sum > 0 & !checkLeft){
                leftBoard = i;
                checkLeft = true;
            }
            if(sum == 0 & checkLeft){
                rightBoard = i - 1;
                sum = 0;
                break;
            }
            sum = 0;
        }

        int[][] labInfoControl = new int[downBoard + 1][rightBoard - leftBoard + 1];

        for (int i = 0; i < labInfoControl.length; i++) {
            for (int j = 0; j < labInfoControl[i].length; j++) {
                labInfoControl[i][j] = labInfo[i][j + leftBoard];
            }
        }

        if(exitTurn == "Up"){
            labInfo = new int[labInfoControl.length + 2][labInfoControl[0].length + 4];
            for (int i = 0; i < labInfoControl.length; i++) {
                for (int j = 0; j < labInfoControl[i].length; j++) {
                    labInfo[i][j + 2] = labInfoControl[i][j];
                }
            }
        }

        if(exitTurn == "Down"){
            labInfo = new int[labInfoControl.length][labInfoControl[0].length + 4];
            for (int i = 0; i < labInfoControl.length; i++) {
                for (int j = 0; j < labInfoControl[i].length; j++) {
                    labInfo[i][j + 2] = labInfoControl[i][j];
                }
            }
        }

        if(exitTurn == "Left"){
            labInfo = new int[labInfoControl.length + 2][labInfoControl[0].length + 2];
            for (int i = 0; i < labInfoControl.length; i++) {
                for (int j = 0; j < labInfoControl[i].length; j++) {
                    labInfo[i][j] = labInfoControl[i][j];
                }
            }
        }

        if(exitTurn == "Right"){
            labInfo = new int[labInfoControl.length + 2][labInfoControl[0].length + 2];
            for (int i = 0; i < labInfoControl.length; i++) {
                for (int j = 0; j < labInfoControl[i].length; j++) {
                    labInfo[i][j + 2] = labInfoControl[i][j];
                }
            }
        }

        String[][] answer = new String[labInfo.length / 2 - 1][labInfo[0].length / 2 - 1];
        String[] ways = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


        for (int i = 2; i < labInfo.length - 2; i+=2) {
            for (int j = 2; j < labInfo[0].length - 2; j+=2) {
                answer[(i-2)/2][(j-2)/2] = ways[labInfo[i - 1][j] + labInfo[i + 1][j] * 2 + labInfo[i][j - 1] * 4 + labInfo[i][j + 1] * 8 - 1];
            }
        }

        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer[i].length; j++) {
                ret+=answer[i][j].toString();
            }
            ret+="\n";
        }
        return ret;
    }
 
}
