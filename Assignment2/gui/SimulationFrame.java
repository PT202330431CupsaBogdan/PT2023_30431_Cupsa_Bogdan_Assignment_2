package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.*;
import javax.swing.border.Border;

import model.Server;
import model.Task;


public class SimulationFrame {

    private static String Title = "Time: 0";
    private static final String ServerLabel = "Server ";
    private static final String TaskLabel = "Task ";
    private static final Color Background = Color.WHITE;
    private static final Color ServerColor = Color.LIGHT_GRAY;
    private static final Color TaskColor = Color.GRAY;
    private static final Dimension ServerDimension = new Dimension(150, 300);
    private static final Dimension TaskDimension = new Dimension(150, 300);

    private JFrame frame;
    private JPanel serverPanel;
    private JPanel taskPanel;
    private JLabel[] serverLabels;
    private JTextArea taskArea = new JTextArea();

    public SimulationFrame(int numberOfServers, List<Task> taskList) {

        frame = new JFrame(Title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout());
        
        serverPanel = new JPanel(new GridLayout(numberOfServers, numberOfServers));
        serverPanel.setPreferredSize(ServerDimension);
        serverPanel.setBackground(ServerColor);
        
        Border serverBorder = BorderFactory.createTitledBorder("Servers");
        serverPanel.setBorder(serverBorder);

        serverLabels = new JLabel[numberOfServers];

        for (int i = 0; i < numberOfServers; i++) {
            serverLabels[i] = new JLabel(ServerLabel + (i+1), SwingConstants.CENTER);
            serverLabels[i].setOpaque(true);
            serverLabels[i].setBackground(Background);
            serverPanel.add(serverLabels[i]);
        }

        taskPanel = new JPanel(new GridLayout(1, 0));
        taskPanel.setBackground(TaskColor);

        Border taskBorder = BorderFactory.createTitledBorder("Tasks");
        taskArea.setText(taskList.toString());
        taskPanel.setBorder(taskBorder);
        taskPanel.add(taskArea);

        frame.add(serverPanel, BorderLayout.WEST);
        frame.add(taskPanel, BorderLayout.EAST);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void addTask(Task task) {

        JLabel taskLabel = new JLabel(task.toString(), SwingConstants.CENTER);
        taskLabel.setOpaque(true);
        taskLabel.setBackground(Background);
        taskLabel.setPreferredSize(TaskDimension);
        taskPanel.add(taskLabel);
        frame.validate();
    }

    public void update(List<Server> servers, List<Task> taskList, int time) {
        for (int i = 0; i < servers.size(); i++) {
            Server server = servers.get(i);
    
            StringBuilder sb = new StringBuilder();
            sb.append(ServerLabel);
            sb.append(i + 1);
            sb.append(": ");
    
            Task[] tasks = server.getTasks();
            if (tasks.length > 0) {
                for(Task t : tasks)
                    sb.append(t.toString()).append("; ");
            } else {
                sb.append("idle");
            }
            serverLabels[i].setText(sb.toString());
        }
        taskArea.setText(taskList.toString());
        frame.setTitle("Time " + time);
        frame.repaint();
    }
    
    
}