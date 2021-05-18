package dog_shopping_proj.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog_shopping_proj.action.Action;
import dog_shopping_proj.action.ActionForward;
import dog_shopping_proj.action.NullAction;

@WebServlet(urlPatterns = {"*.do"},
			loadOnStartup = 1,
			initParams = {
					@WebInitParam(
							name="configFile",
							value = "WEB-INF/commandAction.properties")
			}
		)
public class DogFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map<String, Action> actionMap = new HashMap<>();
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() - config "+config.getInitParameter("configFile"));
		String configFile = config.getInitParameter("configFile");
		try(InputStream is = config.getServletContext().getResourceAsStream(configFile)){
			Properties props = new Properties();
			props.load(is);
			
			System.out.println("props >> " + props);
			for(Entry<Object, Object> entry : props.entrySet()) {
//				System.out.println(entry.getKey() + " : " + entry.getValue());
				Class<?> cls;
				Action action = null;
				try {
					cls = Class.forName((String)entry.getValue());
					action = (Action) cls.newInstance();
				} catch (ClassNotFoundException e) {
					action = new NullAction();
					e.printStackTrace();
				}
				actionMap.put((String)entry.getKey(), action);
			}
			
//			for(Entry<String, Action> entry : actionMap.entrySet()) {
//				System.out.println(entry.getKey() + " : " + entry.getValue());
//			}
		} catch (IOException  | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String RequestURI = request.getRequestURI();
//		String contextPath = request.getContextPath();
//		String command=RequestURI.substring(contextPath.length());   
//		System.out.println(RequestURI + " >> " + contextPath + " >> " + command);

		String command = request.getServletPath(); //3줄코드를 한번에 압축
		System.out.println(command);
		ActionForward forward = null;
		Action action = actionMap.get(command);

		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
		
	}

}
