import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class Search extends JDialog implements ActionListener {
    EmployeeDetails parent;
    JButton search, cancel;
    JTextField searchField;
    JLabel searchLabel;
    String searchType;

   
    public Search(EmployeeDetails parent, String searchType) {
        this.searchType = searchType;
        setTitle("Search by " + searchType);
        setModal(true);
        this.parent = parent;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane(searchPane());
        setContentPane(scrollPane);

        getRootPane().setDefaultButton(search);

        setSize(500, 190);
        setLocation(350, 250);
        setVisible(true);
    }
    public Container searchPane() {
        JPanel searchP = new JPanel(new GridLayout(3, 1));
        JPanel textP = new JPanel();
        JPanel buttonP = new JPanel();

        searchP.add(new JLabel("Search by " + searchType));

        textP.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        textP.add(searchLabel = new JLabel("Enter " + searchType + ":"));
        searchLabel.setFont(this.parent.font1);
        textP.add(searchField = new JTextField(20));
        searchField.setFont(this.parent.font1);
        searchField.setDocument(new JTextFieldLimit(20));

        buttonP.add(search = new JButton("Search"));
        search.addActionListener(this);
        search.requestFocus();

        buttonP.add(cancel = new JButton("Cancel"));
        cancel.addActionListener(this);

        searchP.add(textP);
        searchP.add(buttonP);

        return searchP;
    }

    
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == search) {
            
            try {
                if (searchType.equals("ID")) {
                    Double.parseDouble(searchField.getText());
                    this.parent.searchByIdField.setText(searchField.getText());
                    
                    this.parent.searchEmployeeById();
                } else {
                    this.parent.searchBySurnameField.setText(searchField.getText());
                   
                    this.parent.searchEmployeeBySurname();
                }
                dispose();
            }
            catch (NumberFormatException num) {
                
                searchField.setBackground(new Color(255, 150, 150));
                JOptionPane.showMessageDialog(null, "Wrong " + searchType + " format!");
            }
        }
       
        else if (e.getSource() == cancel)
            dispose();
    }
}