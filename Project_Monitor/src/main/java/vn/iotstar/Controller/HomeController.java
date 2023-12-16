package vn.iotstar.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.Config.JPAConfig;
import vn.iotstar.Entity.Users;
import vn.iotstar.Service.ElectricalServiceImpl;
import vn.iotstar.Service.IElectricalService;
import vn.iotstar.Service.IUserService;
import vn.iotstar.Service.UserServiceImpl;
import vn.iotstar.utils.Constant;
import vn.iotstar.utils.Email;

@WebServlet(urlPatterns = { "/home", "/login", "/register", "/VerifyCode", "/logout" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = -2734356106757374014L;

	IElectricalService electricalService = new ElectricalServiceImpl();
	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = req.getRequestURI().toString();
		if (url.contains("register")) {
			getRegister(req, resp);
		} else if (url.contains("login")) {
			getLogin(req, resp);

		} else if (url.contains("VerifyCode")) {
			req.getRequestDispatcher("verify.jsp").forward(req, resp);
		} else if (url.contains("logout")) {
			getLogout(req, resp);
		} else {
			homePage(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if (url.contains("register")) {
			postRegister(req, resp);
		} else if (url.contains("login")) {
			postLogin(req, resp);
		} else if (url.contains("VerifyCode")) {
			postVerifyCode(req, resp);
		}
	}

//home with method get
	protected void homePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login").forward(req, resp);
	}

	protected void getRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}

//register with method post
	protected void postRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		// lấy tham số trên form
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		Email sm = new Email();
		String code = sm.getRandom();
		Users user = new Users(username, email, fullname, code);
		String alertMsg = "";
		if (userService.checkExistEmail(email)) {
			alertMsg = "Email đã tồn tại";
			req.setAttribute("user", user);
			req.setAttribute("error", alertMsg);
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		} else if (userService.checkExistUsername(username)) {
			alertMsg = "User Name đã tồn tại";
			req.setAttribute("user", user);
			req.setAttribute("error", alertMsg);
			req.getRequestDispatcher("register.jsp").forward(req, resp);

		} else {
			

			boolean test = sm.sendEmail(user);
			if (test) {
				HttpSession session = req.getSession();
				session.setAttribute("account", user);
				boolean isSuccess = userService.register(email, password, username, fullname, code);
				if (isSuccess) {
					resp.sendRedirect(req.getContextPath() + "/VerifyCode");
					// req.getRequestDispatcher("/views/web/verify.jsp").forward(req, resp);

				} else {
					alertMsg = "Lỗi hệ thống";
					req.setAttribute("error", alertMsg);
					req.getRequestDispatcher("register.jsp").forward(req, resp);
				}
			} else {
				PrintWriter out = resp.getWriter();
				out.println("Lỗi khi gửi mail !!!!");
			}

		}
	}

	protected void getLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		// check session
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			req.getRequestDispatcher("monitor.jsp").forward(req, resp);
			// req.getRequestDispatcher("/views/web/waiting.jsp").forward(req, resp);
			return;
		}
		// check cookie
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					session = req.getSession(true);
					session.setAttribute("username", cookie.getValue());
					req.getRequestDispatcher("monitor.jsp").forward(req, resp);
					// req.getRequestDispatcher("/views/web/waiting.jsp").forward(req, resp);
					return;
				}
			}
		}
		req.getRequestDispatcher("login.jsp").forward(req, resp);

	}

//login voiws method Post
	protected void postLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		boolean isRememberMe = false;
		String remember = req.getParameter("remember");

		if ("on".equals(remember)) {
			isRememberMe = true;
		}

		String alertMsg = "";

		javax.persistence.EntityManager entityManager = JPAConfig.getEntityManager();

		TypedQuery<Users> query = entityManager.createQuery(
				"SELECT u FROM Users u WHERE u.username = :username AND u.password = :password", Users.class);
		query.setParameter("username", username);
		query.setParameter("password", password);

		try {
			Users user = query.getSingleResult();
			int status = user.getStatus();
			if (status == 1) {
				HttpSession session = req.getSession(true);
				session.setAttribute("account", user);
				if (isRememberMe) {
					saveRemeberMe(resp, username);
				}

				req.getRequestDispatcher("monitor.jsp").forward(req, resp);
			} else if (status == 0){
				alertMsg = "Tài khoản chưa được kích hoạt hoặc đã bị khóa, vui lòng liên hệ Admin.";
				req.setAttribute("error", alertMsg);
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}

		} catch (NoResultException e) {
			alertMsg = "Sai tài khoản hoặc mật khẩu";
			req.setAttribute("username", username);
			req.setAttribute("error", alertMsg);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}

	private void saveRemeberMe(HttpServletResponse response, String username) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
		cookie.setMaxAge(30 * 60);
		response.addCookie(cookie);
	}

	protected void postVerifyCode(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String alertMsg = "";
		try (PrintWriter out = resp.getWriter()) {
			HttpSession session = req.getSession();
			Users user = (Users) session.getAttribute("account");
			String code = req.getParameter("authcode");
			if (code.equals(user.getCode())) {
				user.setEmail(user.getEmail());
				user.setStatus(1);

				userService.updatestatus(user.getEmail(), 1, null);

				alertMsg = "Kích hoạt tài khoản thành công!";
				req.setAttribute("message", alertMsg);
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} else {
				alertMsg = "Sai mã kích hoạt, vui lòng kiểm tra lại!";
				req.setAttribute("error", alertMsg);
				req.getRequestDispatcher("verify.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void getLogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		// check session
		HttpSession session = req.getSession();
		session.removeAttribute("account"); // remove session
		Cookie[] cookies = req.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
					cookie.setMaxAge(0); // <=> remove cookie
					resp.addCookie(cookie); // add again
					break;
				}
			}
		}

		resp.sendRedirect("./login");
	}

}
