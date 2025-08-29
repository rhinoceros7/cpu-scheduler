**CSCI-330 CPU Scheduling Project**

Ryan King

4/13/25

Instructor: Susan Gass

**Overview**

This project simulates Round Robin CPU scheduling, a preemptive algorithm where each
process is assigned a fixed time quantum and cycled through a FIFO ready queue. The simulator
evaluates how varying the time quantum affects performance metrics such as:

- CPU Utilization
- Throughput
- Turnaround Time
- Waiting Time
- Context Switches

**Implementation Details**

The project is built in Java using the following classes:

- Process – stores process attributes (PID, arrival time, burst time, etc.)
- Scheduler – runs the main Round Robin simulation logic
- ReadyQueue – manages processes waiting for the CPU
- Main – parses command-line arguments and starts the simulation

Each simulation run loads a CSV file (processes.csv) and takes in a specified time quantum.


**Sample Input File**

File: processes.csv

pid,arrive,burst
1,0,
2,1,
3,0,
4,2,

**How to Run**

In IntelliJ (IDE I used):

1. Go to Run > Edit Configurations
2. Set Program arguments to: processes.csv <time_quantum>
3. Click Run on Main.java
   The image below shows how to set the program arguments.


Repeat with different time quantum values: 1, 2, 4, 6, 10

**Time Quantum: 1**

PID | Arrival | Burst | Start | Completion | Turnaround | Waiting
3 | 0 | 2 | 1 | 7 | 7 | 5
4 | 2 | 6 | 5 | 18 | 16 | 10
2 | 1 | 7 | 2 | 19 | 18 | 11
1 | 0 | 5 | 0 | 20 | 20 | 15

--- Performance Metrics ---
CPU Utilization: 100.00%
Throughput: 0.20 processes/unit time
Average Turnaround Time: 15.
Average Waiting Time: 10.
Context Switches: 20

**Time Quantum: 2**

PID | Arrival | Burst | Start | Completion | Turnaround | Waiting
3 | 0 | 2 | 2 | 4 | 4 | 2
4 | 2 | 6 | 6 | 16 | 14 | 8
1 | 0 | 5 | 0 | 17 | 17 | 12
2 | 1 | 7 | 4 | 20 | 19 | 12

--- Performance Metrics ---


CPU Utilization: 100.00%
Throughput: 0.20 processes/unit time
Average Turnaround Time: 13.
Average Waiting Time: 8.
Context Switches: 13

**Time Quantum: 4**

PID | Arrival | Burst | Start | Completion | Turnaround | Waiting
3 | 0 | 2 | 4 | 6 | 6 | 4
1 | 0 | 5 | 0 | 15 | 15 | 10
2 | 1 | 7 | 6 | 18 | 17 | 10
4 | 2 | 6 | 10 | 20 | 18 | 12

--- Performance Metrics ---
CPU Utilization: 100.00%
Throughput: 0.20 processes/unit time
Average Turnaround Time: 14.
Average Waiting Time: 9.
Context Switches: 7

**Time Quantum: 6**

PID | Arrival | Burst | Start | Completion | Turnaround | Waiting
1 | 0 | 5 | 0 | 5 | 5 | 0
3 | 0 | 2 | 5 | 7 | 7 | 5
4 | 2 | 6 | 13 | 19 | 17 | 11
2 | 1 | 7 | 7 | 20 | 19 | 12

--- Performance Metrics ---
CPU Utilization: 100.00%
Throughput: 0.20 processes/unit time
Average Turnaround Time: 12.
Average Waiting Time: 7.
Context Switches: 5

**Time Quantum: 10**

PID | Arrival | Burst | Start | Completion | Turnaround | Waiting
1 | 0 | 5 | 0 | 5 | 5 | 0
3 | 0 | 2 | 5 | 7 | 7 | 5
2 | 1 | 7 | 7 | 14 | 13 | 6
4 | 2 | 6 | 14 | 20 | 18 | 12

--- Performance Metrics ---
CPU Utilization: 100.00%
Throughput: 0.20 processes/unit time
Average Turnaround Time: 10.
Average Waiting Time: 5.
Context Switches: 4

**Analysis & Conclusion**


Smaller time quantums (1–2) increase fairness but also increase context switching overhead,
raising turnaround and waiting times.

Larger quantums (6–10) lower overhead and improve efficiency but favor longer processes,
approaching FCFS behavior.

Time Quantum 6 appears to be the best time quantum for efficiency and speed in this simulation.

Round Robin performance is heavily influenced by the size of the time quantum. There is a
trade-off between responsiveness and efficiency.


