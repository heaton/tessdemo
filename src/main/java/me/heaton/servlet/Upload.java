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

import me.heaton.helper.FileItemList;
import me.heaton.helper.UploadHandler;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

@WebServlet("/upload")
public class Upload extends HttpServlet{

	private static final long serialVersionUID = 4220745755155487595L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        checkMultipartForm(request);

		FileItemList reqs = parseRequest(request);

		UploadHandler handler = new UploadHandler(getServletContext());
		JSONObject resp = handler.operate(reqs);
		writeToResponse(response, resp);
	}

	private void checkMultipartForm(HttpServletRequest request) {
		if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }
	}

	private FileItemList parseRequest(HttpServletRequest request) throws IOException{
		ServletFileUpload upload = getRequestHandler();
		try {
			List<FileItem> items = upload.parseRequest(request);
			return new FileItemList(items);
		} catch (FileUploadException e) {
			throw new IOException(e);
		}
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

	private void writeToResponse(HttpServletResponse response, JSONObject resp)
			throws IOException {
		response.setContentType("application/json");
		response.getWriter().write(resp.toString());
		response.getWriter().close();
	}

}
