package com.czm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyFrame extends JFrame {
    JFileChooser jfc = new JFileChooser();// 文件选择器

    public MyFrame() {
        //设置窗口可见
        setVisible(true);
        //设置关闭窗口后关闭程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        setBounds(200, 200, 600, 600);
//        setLayout(new FlowLayout());
        setLayout(null);
        //创建组件(标签)
        JLabel jLabel = new JLabel("选取文件");
        JLabel jLabe2 = new JLabel("文件目录");
        //设置字体Font（字体，样式，大小）
        jLabel.setFont(new Font("楷体",Font.BOLD,20));
        // 更改前景颜色（也就是字体颜色）
        jLabel.setForeground(Color.GREEN);
        jLabel.setBounds(20, 20, 100, 20);
        jLabe2.setFont(new Font("楷体",Font.BOLD,20));
        jLabe2.setBounds(20, 50, 100, 20);
        contentPane.add(jLabe2);
        JButton jButton = new JButton("...");
        jButton.setBounds(120, 20, 50, 20);
        jButton.addActionListener(new MyActionListener());

        JButton jButton2 = new JButton("...");

        //向下拉框（下拉列表模型）
        JComboBox<String> jComboBox = new JComboBox<>();
        String items[] = new String[]{"字符数", "单词数目", "行数", "复杂的数据"};
        ComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(items);
        jComboBox.setModel(comboBoxModel);
        //设置是否可输入(默认false)输入后获取的下标为-1
        jComboBox.setEditable(true);

        jComboBox.setBounds(200, 20, 90, 20);
        contentPane.add(jComboBox);


        contentPane.add(jButton);
//        contentPane.add(jButton2);
        contentPane.add(jLabel);
        contentPane.validate();
    }

    class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            jfc.setFileSelectionMode(1);// 设定只能选择到文件夹
            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
            if (state == 1) {
                return;
            } else {
                File f = jfc.getSelectedFile();// f为选择到的目录
            }
        }
    }
    public static void main(String[] args) {
        new MyFrame();
    }


}
