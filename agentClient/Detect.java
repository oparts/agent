import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TimerTask;

class Detect extends TimerTask {	
	parameters para;
	
	static File readFile;
    static File[] listReadFiles;
    static int fileNum;

    //CPU
	static File writeFile;
	static FileWriter fileWriter;
    static BufferedWriter bufWriter;
    static PrintWriter printWriter;
    
    static int[] request;
    static int[] response;
    static int reqCnt=1;
    static int resCnt=1;
    
    static int delayCnt=0;
    
    Detect(){
    	int i = 1;
    	
    	request = new int[50];
    	response = new int[50];
    
    	while(i < 50)
    	{
    		request[i] = 100-5*i;
    		response[i] = 5*i;
    		
    		i++;
    	}
    	
    }
    
	public void run(){		
		int totalReq = request[reqCnt];
		int totalRes = response[resCnt];
		int delay = totalReq - totalRes;
		
		delayCnt += delay;
		delayCnt /= reqCnt;
		
		if(delayCnt < 0)
			delayCnt = 0;
		
		if(totalReq > totalRes)
		{
			System.out.println("Result = bad; delay = " + delayCnt);
			para.addCurrentScore();
		}
		
		else
		{
			System.out.println("Result = Good; delay = " + delayCnt);
			para.subCurrentScore();
		}
		
		if(para.getCurrentScore() > para.getThresholdScore())
		{
			para.setFlagReportTrue();
		}
		
		reqCnt++;
		resCnt++;
	}
}