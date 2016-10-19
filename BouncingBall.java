import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * @author Huyen
 *
 */
public class BouncingBall extends JFrame {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	private static final int UPDATETIME = 5;
	private DrawingCanvas canvas;
	int x = 50, y = 50;
	int size = 50;
	int xSpeed = 1, ySpeed = 2;
	Color[] ballColorArray = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.GRAY, Color.PINK, Color.BLACK };
	Color ballColor = Color.BLACK;
	
	public BouncingBall() {
		canvas = new DrawingCanvas();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setContentPane(canvas);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Bouncing Ball");
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		ActionListener updateTask = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				x += xSpeed;
				y += ySpeed;
				if (x < 0 || x > WIDTH - size) {
					xSpeed = -xSpeed;
					changeBallColor();
				}
				if (y < 0 || y > HEIGHT - size) {
					ySpeed = -ySpeed;
					changeBallColor();
				}
				repaint();
			}
		};

		Timer timer = new Timer(UPDATETIME, updateTask);
		timer.start();
	}

	/**
	 * Change the color of the ball to random color
	 */
	public void changeBallColor() {
		Random rnd = new Random();
		ballColor = ballColorArray[rnd.nextInt(ballColorArray.length)];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new BouncingBall();
			}
		});

	}

	private class DrawingCanvas extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.WHITE);
			g.setColor(ballColor);
			g.fillOval(x, y, size, size);
		}
	}
}
