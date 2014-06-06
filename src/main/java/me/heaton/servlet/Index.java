package me.heaton.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@WebServlet("/index")
public class Index extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String imgPath = "/tessdir/eurotext.png";
		request.setAttribute("imgPath", getServletContext().getContextPath() + imgPath);
		String fileName = getServletContext().getRealPath(imgPath);
		File imageFile = new File(fileName);
		String result = ocr(imageFile);
		request.setAttribute("result", result);
		
		request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
	}

	private String ocr(File file) {
		Tesseract instance = Tesseract.getInstance();
		try {
			return instance.doOCR(file);
		} catch (TesseractException e) {
			return e.getMessage();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
