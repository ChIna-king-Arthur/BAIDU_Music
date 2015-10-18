package baiduMu;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Text_ID;
	private JTextArea textout;
	private JTextField textFieldHigh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mu frame = new mu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mu() {
		setTitle("baiduMU-ByKing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblid = new JLabel("\u65E0\u635F\u97F3\u4E50\u7684ID\u53F7\u6216\u94FE\u63A5\uFF1A");
		lblid.setFont(new Font("宋体", Font.PLAIN, 13));
		lblid.setBounds(0, 0, 170, 42);
		contentPane.add(lblid);
		
		Text_ID = new JTextField();
		Text_ID.setBounds(146, 11, 300, 21);
		contentPane.add(Text_ID);
		Text_ID.setColumns(10);
		
		JButton btnOutUrl = new JButton("\u751F\u6210\u8FDE\u63A5");
		btnOutUrl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id=Text_ID.getText();
				String out=null;
				if (id.substring(0, 5).equals("http:")) {
					String id2=id.substring(id.indexOf("song")+5, id.indexOf("download")-1);
					out=getWebCon("http://music.baidu.com/data/music/fmlink?songIds="+id2+"&type=flac",id2);
					//System.out.println(id2);
				} else {
					 out=getWebCon("http://music.baidu.com/data/music/fmlink?songIds="+id+"&type=flac",id);
				}
				
				textout.setText(out);
			}
		});
		btnOutUrl.setFont(new Font("宋体", Font.PLAIN, 16));
		btnOutUrl.setBounds(456, 4, 102, 33);
		contentPane.add(btnOutUrl);
		
		textout = new JTextArea();
		textout.setBounds(10, 92, 548, 212);
		contentPane.add(textout);
		textout.setLineWrap(true);//激活自动换行功能 
		textout.setWrapStyleWord(true);//激活断行不断字功能 
		
		JLabel lblNewLabel = new JLabel("\u8D85\u9AD8\u54C1\u8D28\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel.setBounds(61, 56, 75, 30);
		contentPane.add(lblNewLabel);
		
		textFieldHigh = new JTextField();
		textFieldHigh.setBounds(146, 61, 300, 21);
		contentPane.add(textFieldHigh);
		textFieldHigh.setColumns(10);
		
		JButton buttonHigh = new JButton("\u751F\u6210\u8FDE\u63A5");
		buttonHigh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonHigh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id=textFieldHigh.getText();
				String out=null;
				if (id.substring(0, 5).equals("http:")) {
					String id2=id.substring(id.indexOf("song")+5, id.indexOf("download")-1);
					out=getHigh("http://music.baidu.com/data/music/fmlink?songIds="+id2+"&type=mp3&rate=320",id2);
					//System.out.println(id2);
				} else {
					 out=getHigh("http://music.baidu.com/data/music/fmlink?songIds="+id+"&type=mp3&rate=320",id);
				}
				textout.setText(out);
			}
		});
		buttonHigh.setFont(new Font("宋体", Font.PLAIN, 16));
		buttonHigh.setBounds(456, 53, 102, 35);
		contentPane.add(buttonHigh);
		
	
		
	
		
	}
	public  String getWebCon(String domain,String id) {
		String uf=null;
		// System.out.println("开始读取内容...("+domain+")");
		StringBuffer sb = new StringBuffer();
		try {
			java.net.URL url = new java.net.URL(domain);
			BufferedReader in = new BufferedReader(new InputStreamReader(url
					.openStream()));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			String uu=sb.toString();
			String u1=uu.substring(uu.indexOf("showLink")+11, uu.indexOf("format")-3);
			String u2=u1.replaceAll("\\\\", "");
			String u3=u2.replaceAll("yinyueshiting", "musicdata");
			String u4=u3.substring(u3.indexOf(id), u3.indexOf(".flac"));
			uf=u3.replaceAll(u4, id);
			//System.out.println(sb.toString());
			in.close();
		} catch (Exception e) { // Report any errors that arise
			sb.append(e.toString());
			System.err.println(e);
			System.err
					.println("Usage:   java   HttpClient   <URL>   [<filename>]");
		}
		return uf;
	}
	public  String getHigh(String domain,String id) {
		String uf=null;
		// System.out.println("开始读取内容...("+domain+")");
		StringBuffer sb = new StringBuffer();
		try {
			java.net.URL url = new java.net.URL(domain);
			BufferedReader in = new BufferedReader(new InputStreamReader(url
					.openStream()));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			String uu=sb.toString();
			String u1=uu.substring(uu.indexOf("showLink")+11, uu.indexOf("format")-3);
			String u2=u1.replaceAll("\\\\", "");
			String u3=u2.replaceAll("yinyueshiting", "musicdata");
			String u4=u3.substring(u3.indexOf(id), u3.indexOf(".mp3"));
			uf=u3.replaceAll(u4, id);
			//System.out.println(sb.toString());
			in.close();
		} catch (Exception e) { // Report any errors that arise
			sb.append(e.toString());
			System.err.println(e);
			System.err
					.println("Usage:   java   HttpClient   <URL>   [<filename>]");
		}
		return uf;
	}
	
}
