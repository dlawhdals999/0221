package board;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.temporal.JulianFields;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;
import javax.swing.table.DefaultTableModel;

import chatting.MultiChatClient;
import login.LoginMain;
import login.MemberInfoVO;
import login.SignUpMain;
import quiz.EnteringDAO;
import quiz.EnteringMain;
import quiz.QuizDAO;
import quiz.QuizMain;
import quiz.QuizVO;
import test.MainPanel;

public class BoardMain extends JFrame implements ActionListener{
	
	static MemberInfoVO mvo = new MemberInfoVO();
	
//	마우스에서 클릭했을때 정보를 얻어오기위해 사용되는 변수
	int row, col;
	int choice;
	String passwordCheck;
	
	static JButton solveButton = new JButton("문제풀기");
	static JButton inputButton = new JButton("문제입력");
	static JButton updateButton = new JButton("문제수정");
	static JButton deleteButton = new JButton("문제삭제");
	static JButton chatButton = new JButton("채팅창");
	static JButton loginButton = new JButton("로그인");
	static JButton joinButton = new JButton("회원가입");
	static JButton resetButton = new JButton("새로고침");
	static JButton profileButton = new JButton("");
	String[] columnNames = { "번호", "문제", "작성일" };
	DefaultTableModel model = new DefaultTableModel(columnNames,0);
	
	ArrayList<QuizVO> quizvo = new ArrayList<QuizVO>();
	
	public BoardMain() {
		
		setBounds(100, 100, 450, 300);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainPanel MainPanel = new MainPanel(new ImageIcon(".\\src\\images\\main9.png").getImage());
		getContentPane().add(MainPanel);
		setLayout(null);
		setResizable(false);
		
		
		
		
//		문제풀기
		solveButton.setForeground(new Color(15248986));
		solveButton.setFont(new Font("굴림", Font.BOLD, 15));
		solveButton.setBorder(null);
		solveButton.setBackground(new Color(9803));
		solveButton.setBounds(500, 438, 72, 44);
		MainPanel.add(solveButton);
		solveButton.addActionListener(this);
		
//		문제입력
		inputButton.setForeground(new Color(15248986));
		inputButton.setFont(new Font("굴림", Font.BOLD, 15));
		inputButton.setBorder(null);
		inputButton.setBackground(new Color(9803));
		inputButton.setBounds(588, 438, 72, 44);
		MainPanel.add(inputButton);
		inputButton.addActionListener(this);
		
//		문제수정
		updateButton.setForeground(new Color(15248986));
		updateButton.setFont(new Font("굴림", Font.BOLD, 15));
		updateButton.setBorder(null);
		updateButton.setBackground(new Color(9803));
		updateButton.setBounds(672, 438, 72, 44);
		MainPanel.add(updateButton);
		updateButton.addActionListener(this);
		
//		문제삭제
		deleteButton.setForeground(new Color(232, 174, 90));
		deleteButton.setFont(new Font("굴림", Font.BOLD, 15));
		deleteButton.setBorder(null);
		deleteButton.setBackground(new Color(0, 38, 75));
		deleteButton.setBounds(756, 438, 72, 44);
		MainPanel.add(deleteButton);
		deleteButton.addActionListener(this);
		
//		채팅창 버튼
		chatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		chatButton.setBorder(null);
		chatButton.setBounds(463, 537, 146, 88);
		chatButton.setBackground(new Color(15248986));
		chatButton.setForeground(new Color(9803));
		chatButton.setFont(new Font("굴림", Font.BOLD, 30));
		
		MainPanel.add(chatButton);
		chatButton.addActionListener(this);
		
//		로그인버튼
		loginButton.setForeground(new Color(0, 38, 75));
		loginButton.setFont(new Font("굴림", Font.BOLD, 30));
		loginButton.setBorder(null);
		loginButton.setBackground(new Color(232, 174, 90));
		loginButton.setBounds(629, 537, 146, 88);
		MainPanel.add(loginButton);
		loginButton.addActionListener(this);
		
		
//		회원가입
		joinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		joinButton.setBorder(null);
		joinButton.setBounds(798, 537, 146, 88);
		joinButton.setBackground(new Color(15248986));
		joinButton.setForeground(new Color(9803));
		joinButton.setFont(new Font("굴림", Font.BOLD, 30));
		MainPanel.add(joinButton);
		joinButton.addActionListener(this);
		
//		새로고침 버튼
		resetButton.setBounds(798, 50, 47, 38);
		resetButton.setBorderPainted(false);
		resetButton.setContentAreaFilled(false);
		resetButton.setFocusPainted(false);
		MainPanel.add(resetButton);
		resetButton.addActionListener(this);

//		프로필 버튼
		profileButton.setBounds(878, 38, 64, 61);
		profileButton.setBorderPainted(false);
		profileButton.setContentAreaFilled(false);
		profileButton.setFocusPainted(false);
		MainPanel.add(profileButton);
		profileButton.addActionListener(this);
		
//		버튼 클릭 되는지 확인용
//		refreshBtn.addActionListener(this);
//		profileBtn.addActionListener(this);
		
		
		chatButton.setEnabled(false);
		inputButton.setEnabled(false);
		updateButton.setEnabled(false);
		deleteButton.setEnabled(false);
		
		setSize(MainPanel.getDim());
		setPreferredSize(MainPanel.getDim()); 
		setVisible(true);
		
		pack();
		
		JTable table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		sc.setBounds(150,132,684,283);
		MainPanel.add(sc);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
//		quizvo 객체배열로 가져오기
		quizvo = QuizDAO.setquiz();
		
//		quizvo 테이블에 올리기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd(E) H:mm:ss");
		String[] rowData = new String[3];
		for (QuizVO data : quizvo) {
			rowData[0] = data.getIdx() + "";
			rowData[1] = data.getQuiz();
			rowData[2] = sdf.format(data.getWriteDate());
			model.addRow(rowData);
		}
		
		
		
//		 테이블에 마우스클릭하면 인덱스 가져오기
		table.addMouseListener(new MouseAdapter() {
			
//			마우스로 클릭했을때 정보를얻어온다.
			@Override
			public void mouseClicked(MouseEvent e) {
				
				row = table.getSelectedRow();
				col = table.getSelectedColumn();
//				System.out.println(row + "행 " + col + "열");
				choice = Integer.parseInt((String) table.getValueAt(row, 0));
//				System.out.println(choice);
				
			}
			
		});

	}
		
	public static void main(String[] args) {
		
		BoardMain window = new BoardMain();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	
		
		
		
		switch (e.getActionCommand()) {
		case "새로고침" :		
			reset();
			break;
				
		case "문제풀기":
			QuizMain quizmain = new QuizMain();
			quizmain.setVisible(true);
			break;
		case "문제입력":
			EnteringMain entering = new EnteringMain();
			break;
		case "문제수정":
			passwordCheck = JOptionPane.showInputDialog("문제의 비밀번호를 입력해주세요");
			if(passwordCheck == null) {
			}else {
				EnteringDAO.check(choice, passwordCheck);
				
			}
			break;
		case "문제삭제":
			passwordCheck = JOptionPane.showInputDialog("문제의 비밀번호를 입력해주세요");
			if(passwordCheck == null) {
			}else {
				
				try {
					Connection conn = DBUtil.getMySQLConnection();
					String check = "SELECT quizpassword FROM quiz WHERE idx = '" + choice + "'" ;
					PreparedStatement pstmt = conn.prepareStatement(check);
					ResultSet rs = pstmt.executeQuery();
					rs.next();
					if(rs.getString("quizpassword").equals(passwordCheck)) {
						String sql = "DELETE FROM quiz WHERE idx = '" + choice + "'";
						PreparedStatement pstmt1 = conn.prepareStatement(sql);
						pstmt1.executeUpdate();
						JOptionPane.showMessageDialog(null, choice + "번 문제가 삭제되었습니다!");
					}else {
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
					}
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
			break;
			
		case "채팅창":
			MultiChatClient chat = new MultiChatClient();
			Thread thread = new Thread(chat);
			thread.start();
//			ChattingFrame chat = new ChattingFrame();
			JOptionPane.showMessageDialog(null, "채팅창");
			break;
			
		case "로그인":
			LoginMain loginMain = new LoginMain();
			loginMain.setVisible(true);
			if(mvo.getUserID() != null) {
//				로그인이 됬을때 버튼 바꾸기
				getJoinButton().setEnabled(false);
				getLoginButton().setEnabled(false);
			}
			break;
			
		case "회원가입":
			SignUpMain signUpMain = new SignUpMain();
			signUpMain.setVisible(true);
			break;
			
		case "로그아웃":
			int logout = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
			if(logout == 0) {
//				로그아웃되면 버튼 비활성화
				getInputButton().setEnabled(false);
				getUpdateButton().setEnabled(false);
				getDeleteButton().setEnabled(false);
				getChatButton().setEnabled(false);
				getJoinButton().setEnabled(true);
				getLoginButton().setText("로그인");	
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.");
//				mvo 비워주기
				mvo = new MemberInfoVO();
			}
			break;
//		프로필
		case "":
			JOptionPane.showMessageDialog(null, "프로필창(아직안함)");
		
			break;
		}
	}

	private void reset() {
		//			quizvo 객체배열로 가져오기
					quizvo = QuizDAO.setquiz();
		
					for (int i = model.getRowCount() - 1; i >=0 ; i--) {
		//				removeRow() : JTable 디자인에 사용한 DefaultTableModel 클래스 객체에서 JTable에 넣어준 row 번째 데이터를 제거한다.
						model.removeRow(i);
					}
		//			quizvo 테이블에 올리기
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd(E) H:mm:ss");
					String[] rowData = new String[3];
					
					for (QuizVO data : quizvo) {
							rowData[0] = data.getIdx() + "";
							rowData[1] = data.getQuiz();
							rowData[2] = sdf.format(data.getWriteDate());
							model.addRow(rowData);
						}
	}
	

	public static MemberInfoVO getMvo() {
		return mvo;
	}

	public static void setMvo(MemberInfoVO mvo) {
		BoardMain.mvo = mvo;
	}

	public static JButton getSolveButton() {
		return solveButton;
	}

	public static void setSolveButton(JButton solveButton) {
		BoardMain.solveButton = solveButton;
	}

	public static JButton getInputButton() {
		return inputButton;
	}

	public static void setInputButton(JButton inputButton) {
		BoardMain.inputButton = inputButton;
	}

	public static JButton getUpdateButton() {
		return updateButton;
	}

	public static void setUpdateButton(JButton updateButton) {
		BoardMain.updateButton = updateButton;
	}

	public static JButton getDeleteButton() {
		return deleteButton;
	}

	public static void setDeleteButton(JButton deleteButton) {
		BoardMain.deleteButton = deleteButton;
	}

	public static JButton getChatButton() {
		return chatButton;
	}

	public static void setChatButton(JButton chatButton) {
		BoardMain.chatButton = chatButton;
	}

	public static JButton getLoginButton() {
		return loginButton;
	}

	public static void setLoginButton(JButton loginButton) {
		BoardMain.loginButton = loginButton;
	}

	public static JButton getJoinButton() {
		return joinButton;
	}

	public static void setJoinButton(JButton joinButton) {
		BoardMain.joinButton = joinButton;
	}



}
