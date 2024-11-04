package csu.web.mypetstore.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
        import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class CodeServlet extends HttpServlet {
    private static final long seriaLVersionUID=1L;

    public void service(HttpServletRequest httpServletRequest, HttpServletResponse response) throws ServletException, IOException {
        //创建图片
        BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
        //创建画笔
        Graphics g = image.getGraphics();
        Random r = new Random();
        g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        g.fillRect(0, 0, 100, 30);//矩形边框
        for (int i = 0; i < 8; i++) {
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            g.drawLine(r.nextInt(100), r.nextInt(30), r.nextInt(100), r.nextInt(30));
        }
        //随机字符串
        String str=getNumbers();
        //发送现有验证码
        HttpSession session= httpServletRequest.getSession();
        session.setAttribute("code",str);
        g.setFont(new Font(null,Font.ITALIC,25));
        //设置字符格式
        g.drawString(str,5,20);
        //输出图片
        response.setContentType("image/jpeg");
        OutputStream out=response.getOutputStream();
        ImageIO.write(image,"jpeg",out);



    }
    public String getNumbers(){
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String result="";
        Random r=new Random();
        for(int i=0;i<4;i++){
            //随机字符
            char c=str.charAt(r.nextInt(str.length()));
            result+=c;
        }
        return result;

    }
}