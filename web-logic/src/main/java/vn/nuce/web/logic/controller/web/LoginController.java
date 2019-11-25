package vn.nuce.web.logic.controller.web;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import vn.nuce.core.common.utils.SessionUtil;
import vn.nuce.core.dto.CheckLoginDTO;
import vn.nuce.core.dto.RoleDTO;
import vn.nuce.core.dto.UserDTO;
import vn.nuce.core.web.common.WebConstant;
import vn.nuce.core.web.utils.FormUtil;
import vn.nuce.core.web.utils.SingletonServiceUtil;
import vn.nuce.web.logic.command.UserCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/login.html", "/registration.html"})//urlPatterns:sử dụng nhiều link
public class LoginController extends HttpServlet {
    private transient final Logger log = Logger.getLogger(this.getClass());// transient trang thai biến không được lưu trữ
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals(WebConstant.LOGIN)) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
            rd.forward(request, response);
        } else if (action.equals(WebConstant.LOGOUT)) {
            SessionUtil.getInstance().removeValue(request.getSession(), WebConstant.LOGIN_NAME);
            SessionUtil.getInstance().removeValue(request.getSession(), WebConstant.USER_ID);
            SessionUtil.getInstance().removeValue(request.getSession(), WebConstant.BALANCE);
            response.sendRedirect("/home.html");
        } else if (action.equals(WebConstant.REGISTER)) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/register.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        if (action.equals(WebConstant.LOGIN)) {
            StringBuffer jb = new StringBuffer();
            String line = null;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
            JSONObject jsonObject = new JSONObject(jb.toString());
            UserDTO pojo = new UserDTO();
            pojo.setUserName(jsonObject.getString("user"));
            pojo.setPassword(jsonObject.getString("password"));
            if (pojo != null) {
                CheckLoginDTO login = SingletonServiceUtil.getUserServiceInstance().checkLogin(pojo.getUserName(), pojo.getPassword());
                JSONObject object = new JSONObject();
                if (login.isUserExist()) {
                    if (login.getRoleName().equals(WebConstant.ROLE_ADMIN)) {
                        object.put("sta","ADMIN");
                    } else if (login.getRoleName().equals(WebConstant.ROLE_USER)) {
                        object.put("sta","USER");
                    }
                    SessionUtil.getInstance().putValue(request.getSession(), WebConstant.LOGIN_NAME, login.getUserDTO().getLastName());
                    SessionUtil.getInstance().putValue(request.getSession(), WebConstant.USER_ID,login.getUserDTO().getUserId());
                    SessionUtil.getInstance().putValue(request.getSession(), WebConstant.BALANCE,login.getUserDTO().getBalance());
                } else {
                    object.put("sta","ERROR");
                }
                PrintWriter writer = response.getWriter();
                writer.append(object.toString());
                writer.close();
                RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
                rd.forward(request, response);
            }
        } else if (action.equals(WebConstant.REGISTER)) {
            UserDTO pojo = command.getPojo();
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            byte[] bytes = firstName.getBytes(StandardCharsets.ISO_8859_1);
            firstName = new String(bytes, StandardCharsets.UTF_8);
            bytes = lastName.getBytes(StandardCharsets.ISO_8859_1);
            lastName = new String(bytes, StandardCharsets.UTF_8);
            try {
                if (pojo != null && StringUtils.isNotBlank(pojo.getPassword()) && StringUtils.isNotBlank(pojo.getUserName())
                        && StringUtils.isNotBlank(command.getRepeatPass())) {
                    if (command.getRepeatPass().equals(pojo.getPassword())) {
                        RoleDTO roleDTO = new RoleDTO();
                        roleDTO.setRoleId(command.getRoleId());
                        pojo.setFirstName(firstName);
                        pojo.setLastName(lastName);
                        pojo.setRoleDTO(roleDTO);
                        SingletonServiceUtil.getUserServiceInstance().saveUser(pojo);
                        request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, "Đăng ký thành công");
                    }
                } else {
                    request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
                    request.setAttribute(WebConstant.MESSAGE_RESPONSE, "lỗi ko đăng ký được");
                }
            } catch (Exception e) {
                request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
                request.setAttribute(WebConstant.MESSAGE_RESPONSE, "lỗi ko đăng ký được");
            }
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/register.jsp");
            rd.forward(request, response);
        }
    }
}
