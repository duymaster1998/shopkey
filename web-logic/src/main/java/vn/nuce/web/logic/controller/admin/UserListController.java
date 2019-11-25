package vn.nuce.web.logic.controller.admin;

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
import java.io.IOException;

@WebServlet("/admin-user-list.html")
public class UserListController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class,request);
        command.setListResult(SingletonServiceUtil.getUserServiceInstance().findAllUser());
        request.setAttribute(WebConstant.LIST_ITEMS,command.getListResult());
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user_list.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
