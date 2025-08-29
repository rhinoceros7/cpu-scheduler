public class Process
{
    public int pid;
    public int arrivalTime;
    public int burstTime;
    public int remainingTime;
    public int startTime = -1;
    public int completionTime;

    public Process(int pid, int arrivalTime, int burstTime)
    {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }

    public int getTurnaroundTime()
    {
        return completionTime - arrivalTime;
    }

    public int getWaitingTime()
    {
        return getTurnaroundTime() - burstTime;
    }

    public String toString()
    {
        return "P" + pid + " [arrival=" + arrivalTime + ", burst=" + burstTime + "]";
    }

}
