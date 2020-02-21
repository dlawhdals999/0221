package frame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ModifyFrame extends JFrame{

	public static void main(String[] args) {
		
		ModifyFrame window = new ModifyFrame();
				
	}

	public ModifyFrame() {
		setLocation(600, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		이미지 삽입
		MainPanel modifyPage = new MainPanel(new ImageIcon("C:\\project_CYJ\\teamproject\\workspace\\Frame\\src\\images\\ModifyPage.png").getImage());
		getContentPane().add(modifyPage, BorderLayout.SOUTH);
		
//		아이디 라벨
		JLabel idLabel = new JLabel();
		idLabel.setBounds(252, 144, 269, 37);
		modifyPage.add(idLabel);
		
//		비밀번호 라벨
		JLabel pwLabel = new JLabel();
		pwLabel.setBounds(252, 200, 269, 37);
		modifyPage.add(pwLabel);
		
//		새비밀번호 라벨
		JLabel newPwLabel = new JLabel();
		newPwLabel.setBounds(252, 260, 269, 37);
		modifyPage.add(newPwLabel);
		
//		패스워드 라벨
		JLabel PWCheckLabel = new JLabel();
		PWCheckLabel.setBounds(252, 318, 269, 37);
		modifyPage.add(PWCheckLabel);
		
//		성별 라벨
		JLabel genderLabel = new JLabel();
		genderLabel.setBounds(252, 383, 269, 37);
		modifyPage.add(genderLabel);
		
//		닉네임 라벨
		JLabel nickNameLabel = new JLabel();
		nickNameLabel.setBounds(252, 444, 269, 37);
		modifyPage.add(nickNameLabel);
		
		
		
		
//		크기조정
		setSize(new Dimension(modifyPage.getDim()));
		setPreferredSize(new Dimension(modifyPage.getDim()));
		pack();
		setVisible(true);
	}
}
