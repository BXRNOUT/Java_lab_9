import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MovingTextApplet extends JPanel implements ActionListener
{
    private String text = "с новым годом!";
    private int x = 0;
    private int y = 50;
    private int dx = 1; // скорость по оси X
    private int dy = 1; // скорость по оси Y
    private boolean touchRightX = true;
    private final Font[] fonts = {
            new Font("Arial", Font.PLAIN, 30),
            new Font("Courier New", Font.PLAIN, 30),
            new Font("Times New Roman", Font.PLAIN, 30),
            new Font("Verdana", Font.PLAIN, 30)
    };
    private final Random random = new Random();
    private Font currentFont;

    public MovingTextApplet()
    {
        currentFont = fonts[random.nextInt(fonts.length)];
        Timer timer = new Timer(3, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        graphics.setFont(currentFont);
        graphics.drawString(text, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (x < 800)
            x += dx;
        if (y < 800)
            y += dy;
        if ((x <= 0 || x > getWidth() - getFontMetrics(currentFont).stringWidth(text) - 5) && !touchRightX)
        {
            dx = -dx;
            changeCase();
            changeFont();
            touchRightX = true;
        }
        if (y <= 30 || y > getHeight() - 5)
        {
            dy = -dy;
            changeCase();
            changeFont();
            touchRightX = false;
        }
        repaint();
    }

    private void changeCase()
    {
        StringBuilder newText = new StringBuilder();
        for (char c : text.toCharArray())
        {
            if (random.nextBoolean())
            {
                newText.append(Character.toUpperCase(c));
            }
            else
            {
                newText.append(Character.toLowerCase(c));
            }
        }
        text = newText.toString();
    }

    private void changeFont()
    {
        currentFont = fonts[random.nextInt(fonts.length)];
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Движущийся текст");
        MovingTextApplet panel = new MovingTextApplet();
        frame.add(panel);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}