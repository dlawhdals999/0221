package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import frame.mainPanel;

public class MyPageFrame extends JFrame{
	
	public static void main(String[] args) {
		
		MyPageFrame window = new MyPageFrame();
		
	}

	public MyPageFrame() {
		mainPanel mainpanel = new mainPanel(new ImageIcon("C:\\project_CYJ\\teamproject\\workspace\\Frame\\src\\images\\mypage9.png").getImage());
		getContentPane().add(mainpanel, BorderLayout.SOUTH);
		
//		회원정보 수정 버튼
		JButton ModifyBtn = new JButton("회원정보수정");
		ModifyBtn.setBounds(396, 500, 114, 37);
		ModifyBtn.setBackground(new Color(3447003));
		ModifyBtn.setBorder(null);
		ModifyBtn.setForeground(Color.white);
		ModifyBtn.setFont(new Font("D2Coding", Font.BOLD, 15));
		
		mainpanel.add(ModifyBtn);
		
//		아이디라벨
		JLabel idLabel = new JLabel();
		idLabel.setBounds(179, 126, 225, 26);
		mainpanel.add(idLabel);
		
//		이름라벨
		JLabel nameLabel = new JLabel();
		nameLabel.setBounds(179, 169, 225, 26);
		mainpanel.add(nameLabel);
		
//		성별라벨
		JLabel genderLabel = new JLabel();
		genderLabel.setBounds(179, 214, 225, 26);
		mainpanel.add(genderLabel);
		
//		닉네임라벨
		JLabel nickNameLabel = new JLabel();
		nickNameLabel.setBounds(179, 259, 225, 26);
		mainpanel.add(nickNameLabel);
		
//		정답라벨
		JLabel answerLabel = new JLabel();
		answerLabel.setBounds(179, 336, 225, 26);
		mainpanel.add(answerLabel);
		
//		오답라벨
		JLabel wrongAnswerLabel = new JLabel();
		wrongAnswerLabel.setBounds(179, 380, 225, 26);
		mainpanel.add(wrongAnswerLabel);
		
//		정답률라벨
		JLabel answerRateLabel = new JLabel();
		answerRateLabel.setBounds(179, 423, 225, 26);
		mainpanel.add(answerRateLabel);
		
		setSize(mainpanel.getDim());
		setPreferredSize(mainpanel.getDim()); 
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
