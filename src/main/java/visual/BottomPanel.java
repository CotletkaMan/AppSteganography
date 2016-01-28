package visual;

import logic.algorithms.Algorithm;
import visual.controllers.ButtonActioner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by cotletkaman on 26.01.16.
 */
public class BottomPanel extends JPanel {
    private JButton prev;
    private JButton next;
    private JButton action;

    private ArrayList<Algorithm> algorithmLinkedList;
    private int index = 0;

    private ButtonActioner buttonActioner;

    public BottomPanel(ArrayList<Algorithm> algorithms){
        algorithmLinkedList = algorithms;
        config();
        actions();
    }

    private void config(){
        prev = new JButton("<<");
        action = new JButton(algorithmLinkedList.get(index).getNameAlgorithm());
        next = new JButton(">>");
        this.add(prev);
        this.add(action);
        this.add(next);
    }

    private void actions(){
        prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prev();
            }
        });
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });

        action.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonActioner.run();
            }
        });
    }

    public void prev(){
        index = (index - 1) % algorithmLinkedList.size();
        action.setText(algorithmLinkedList.get(index).getNameAlgorithm());
    }

    public void next(){
        index = (index + 1) % algorithmLinkedList.size();
        action.setText(algorithmLinkedList.get(index).getNameAlgorithm());
    }

    public Algorithm getSelected(){
        return algorithmLinkedList.get(index);
    }

    public void setAction(ButtonActioner action){
        this.buttonActioner = action;
    }
}
