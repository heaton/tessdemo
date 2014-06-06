package me.heaton.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

@WebServlet("/upload")
public class Upload extends HttpServlet{

	private static final long serialVersionUID = 4220745755155487595L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }

        JSONObject resp = new JSONObject();
        resp.put("result", "success");
        ServletFileUpload upload = getRequestHandler();

        try {
        	JSONObject data = new JSONObject();
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item : items) {
				if(item.isFormField()) {
					data.put(item.getFieldName(), item.getString());
				}else if(!item.isFormField()) {
					String value = item.getName() + "," + item.getSize() + ","
							+ item.getContentType();
					data.put(item.getFieldName(), value);
				}
			}
			resp.put("data", data);
		} catch (FileUploadException e) {
			throw new IOException(e);
		}
        response.setContentType("application/json");
        response.getWriter().write(resp.toString());
        response.getWriter().close();
	}

	private ServletFileUpload getRequestHandler() {
		DiskFileItemFactory factory = new DiskFileItemFactory();

        File repository = getTempdir();
        factory.setRepository(repository);
        
        ServletFileUpload upload = new ServletFileUpload(factory);
		return upload;
	}

	private File getTempdir() {
		ServletContext servletContext = getServletContext();
        return (File) servletContext.getAttribute("javax.servlet.context.tempdir");
	}

}
