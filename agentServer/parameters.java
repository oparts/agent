public class parameters{ 
	static int expectedTime;
	static int spendableTime;
	static int samplingTime;
	static int thresholdScore = 5;
	static int currentScore = 0;
	static int numRequest = 0;
	static int numResponse = 0;
	
	parameters(int expTime, int spendTime, int sampTime, int threScore)
	{
		expectedTime = expTime;
		spendableTime = spendTime;
		samplingTime = sampTime;
		thresholdScore = threScore;
	}
	
	public static void setExpectedTime(int time)
	{
		expectedTime = time;
	}
	
	public static int getExpectedTime()
	{
		return expectedTime;
	}
	
	public static void setSpendableTime(int time)
	{
		spendableTime = time;
	}
	
	public static int getSpendableTime()
	{
		return spendableTime;
	}
	
	public static void setSamplingTime(int time)
	{
		samplingTime= time;
	}
	
	public static int getSamplingTime()
	{
		return samplingTime;
	}
	
	public static void setNumRequest(int num)
	{
		numRequest = num;
	}
	
	public static int getNumRequest()
	{
		return numRequest;
	}
	
	public static void setNumResponse(int num)
	{
		numResponse = num;
	}
	
	public static int getNumResponse()
	{
		return numResponse;
	}
	
	public static void addCurrentScore()
	{
		currentScore++;
	}
	
	public static void subCurrentScore()
	{
		currentScore--;
	}
	
	public static int getCurrentScore()
	{
		return currentScore;
	}
	
	public static void setThresholdScore(int score)
	{
		thresholdScore = score;
	}
	
	public static int getThresholdScore()
	{
		return thresholdScore;
	}
	

} 