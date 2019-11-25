package vn.nuce.web.logic.controller.web;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DisplayImage extends HttpServlet {
    private final String imagesBase = "/fileupload";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageUrl = request.getRequestURI();
        String relativeImagePath = imageUrl.substring("/repository/".length());
        ServletOutputStream outputStream;
        outputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(imagesBase + File.separator + relativeImagePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        int ch = 0;
        while ((ch = bufferedInputStream.read()) != -1) {
            bufferedOutputStream.write(ch);
        }
        bufferedInputStream.close();
        fileInputStream.close();
        bufferedOutputStream.close();
        outputStream.close();
    }
}
