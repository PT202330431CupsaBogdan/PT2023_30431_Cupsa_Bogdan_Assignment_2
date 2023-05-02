package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InputFrame extends JPanel {
        private final JLabel nbClientsLabel;
        private final JLabel title;
        private final JTextField nbClients;
        private final JLabel queueLabel;
        private final JLabel maxSimulationLabel;
        private final JLabel minArrivalLabel;
        private final JLabel maxProcessingLabel;
        private final JLabel minProcessingLabel;
        private final JLabel maxArrivalLabel;
        private final JTextField nbQueues;
        private final JTextField simTime;
        private final JTextField minArrival;
        private final JTextField maxArrival;
        private final JTextField minProcessing;
        private final JTextField maxProcessing;
        private final JButton startButton;

        public InputFrame() {
            JPanel panel = new JPanel();
            nbClientsLabel = new JLabel ("Number of clients:");
            title = new JLabel ("      Queue Simulation Setup");
            nbClients = new JTextField (5);
            queueLabel = new JLabel ("Number of queues:");
            maxSimulationLabel = new JLabel ("Maximum simulation time:");
            minArrivalLabel = new JLabel ("Minimum arrival time:");
            maxProcessingLabel = new JLabel ("Maximum Processing time:");
            minProcessingLabel = new JLabel ("Minimum Processing time");
            maxArrivalLabel = new JLabel ("Maximum arrival time:");
            nbQueues = new JTextField (5);
            simTime = new JTextField (5);
            minArrival = new JTextField (5);
            maxArrival = new JTextField (5);
            minProcessing = new JTextField (5);
            maxProcessing = new JTextField (5);
            nbQueues.setText("2");
            nbClients.setText("6");
            simTime.setText("30");
            minArrival.setText("1");
            minProcessing.setText("1");
            maxProcessing.setText("6");
            maxArrival.setText("14");
            startButton = new JButton ("Start");

            nbClientsLabel.setForeground(Color.darkGray);
            maxArrivalLabel.setForeground(Color.darkGray);
            maxProcessingLabel.setForeground(Color.darkGray);
            minArrivalLabel.setForeground(Color.darkGray);
            minProcessingLabel.setForeground(Color.darkGray);
            maxArrivalLabel.setForeground(Color.darkGray);
            maxSimulationLabel.setForeground(Color.darkGray);
            queueLabel.setForeground(Color.darkGray);
            title.setForeground(Color.darkGray);

            setPreferredSize (new Dimension (390, 519));
            setLayout (null);
            setBackground(Color.CYAN);

            add(nbClientsLabel);
            add(title);
            add(nbClients);
            add(queueLabel);
            add(maxSimulationLabel);
            add(minArrivalLabel);
            add(maxProcessingLabel);
            add(minProcessingLabel);
            add(maxArrivalLabel);
            add(nbQueues);
            add(simTime);
            add(minArrival);
            add(maxArrival);
            add(minProcessing);
            add(maxProcessing);
            add(startButton);

            nbClientsLabel.setBounds (30, 40, 140, 40);
            title.setBounds (95, 10, 185, 30);
            nbClients.setBounds (215, 50, 100, 25);
            queueLabel.setBounds (30, 95, 135, 25);
            maxSimulationLabel.setBounds (30, 150, 170, 25);
            minArrivalLabel.setBounds (30, 205, 145, 30);
            maxProcessingLabel.setBounds (30, 370, 155, 30);
            minProcessingLabel.setBounds (30, 315, 145, 35);
            maxArrivalLabel.setBounds (30, 260, 145, 30);
            nbQueues.setBounds (215, 95, 100, 25);
            simTime.setBounds (215, 150, 100, 25);
            minArrival.setBounds (215, 210, 100, 25);
            maxArrival.setBounds (215, 265, 100, 25);
            minProcessing.setBounds (215, 325, 100, 25);
            maxProcessing.setBounds (215, 375, 100, 25);
            startButton.setBounds (120, 435, 150, 45);
        }

        public void viewInputFrame() {
            JFrame frame = new JFrame ("Simulation Setup");
            frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(this);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

        public ArrayList<Integer> getInput(){
            ArrayList<Integer> inputs = new ArrayList<>();
            inputs.add(Integer.parseInt(nbClients.getText()));          //nbClients
            inputs.add(Integer.parseInt(nbQueues.getText()));            //nbQueues
            inputs.add(Integer.parseInt(simTime.getText()));            //simTime
            inputs.add(Integer.parseInt(minArrival.getText()));            //minArrival
            inputs.add(Integer.parseInt(maxArrival.getText()));            //maxArrival
            inputs.add(Integer.parseInt(minProcessing.getText()));            //minProcessing
            inputs.add(Integer.parseInt(maxProcessing.getText()));            //maxProcessing
            return inputs;
        }

        public void addButtonAction(ActionListener actionListener){
            startButton.addActionListener(actionListener);
        }

        public void close(){
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
        }
}