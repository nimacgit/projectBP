package bp96.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class SorterFrame extends JFrame {
    private  JButton sortButton = new JButton("sort");
    public SorterFrame(int n)
    {

        super();
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        for(int i = 0; i < n;i++)
        {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(), BoxLayout.LINE_AXIS);

        }

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == sortButton)
        {
            List<Integer> numbers = new ArrayList<Integer>();
            for(int i = 0; i < textFields.size(); i++)

        }
    }
}
