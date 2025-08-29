import java.io.*;
import java.util.*;

public class Scheduler
{
    private List<Process> allProcesses = new ArrayList<>();
    private ReadyQueue readyQueue = new ReadyQueue();
    int contextSwitches = 0;
    int clock = 0;
    private int timeQuantum;
    private int contextSwitchTime = 0;

    public Scheduler(String filename, int timeQuantum) throws IOException
    {
        this.timeQuantum = timeQuantum;
        loadProcesses(filename);
    }

    public void loadProcesses(String filename) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        br.readLine(); // No header.
        while((line = br.readLine()) != null)
        {
            String[] parts = line.split(",");
            int pid = Integer.parseInt(parts[0].trim());
            int arrival = Integer.parseInt(parts[1].trim());
            int burst = Integer.parseInt(parts[2].trim());
            allProcesses.add(new Process(pid, arrival, burst));
        }

        br.close();
    }

    public void run()
    {
        List<Process> completed = new ArrayList<>();
        int idleTime = 0;

        while(completed.size() < allProcesses.size())
        {
            for(Process p : allProcesses)
            {
                if(p.arrivalTime == clock)
                {
                    readyQueue.enqueue(p);
                }
            }

            if(!readyQueue.isEmpty())
            {
                Process current = readyQueue.dequeue();
                if(current.startTime == -1) current.startTime = clock;

                int executeTime = Math.min(timeQuantum, current.remainingTime);
                for(int i =0; i < executeTime; i++)
                {
                    clock++;
                    for(Process p : allProcesses)
                    {
                        if(p.arrivalTime == clock)
                        {
                            readyQueue.enqueue(p);
                        }
                    }
                }

                current.remainingTime -= executeTime;
                contextSwitches++;

                if(current.remainingTime == 0 && !completed.contains(current))
                {
                    current.completionTime = clock;
                    completed.add(current);
                }
                else
                {
                    readyQueue.enqueue(current);
                }
            }
            else
            {
                // Idle CPU.
                clock++;
                idleTime++;
            }
        }

        printResults(completed, idleTime);
    }

    private void printResults(List<Process> completed, int idleTime)
    {
        System.out.println("PID | Arrival | Burst | Start | Completion | Turnaround | Waiting");
        for(Process p : completed)
        {
            System.out.printf("%3d | %7d | %5d | %5d | %10d | %10d | %7d\n",
                    p.pid, p.arrivalTime, p.burstTime, p.startTime, p.completionTime,
                    p.getTurnaroundTime(), p.getWaitingTime());
        }

        double avgTurnaround = completed.stream().mapToInt(Process::getTurnaroundTime).average().orElse(0);
        double avgWaiting = completed.stream().mapToInt(Process::getWaitingTime).average().orElse(0);
        double throughput = (double) completed.size() / clock;
        double cpuUtil = 1 - ((double) (contextSwitchTime * contextSwitches) / clock);

        System.out.println("\n--- Performance Metrics ---");
        System.out.printf("CPU Utilization: %.2f%%\n", cpuUtil * 100);
        System.out.printf("Throughput: %.2f processes/unit time\n", throughput);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaround);
        System.out.printf("Average Waiting Time: %.2f\n", avgWaiting);
        System.out.printf("Context Switches: %d\n", contextSwitches);
    }
}
