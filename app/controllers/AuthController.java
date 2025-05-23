package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class AuthController extends Controller {

    static Form<User> loginForm = Form.form(User.class);

    public static Result login() {
        return ok(login.render(loginForm));
    }

    public static Result authenticate() {
        Form<User> filledForm = loginForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(login.render(filledForm));
        }

        User user = User.authenticate(filledForm.get().username, filledForm.get().password);
        if (user == null) {
            flash("error", "Invalid login credentials");
            return redirect(routes.AuthController.login());
        }

        session("userId", user.id.toString());
        session("username", user.username);
        session("role", user.role);
        // Redirect based on role

        return redirect(routes.Application.dashboard());
    }

    public static Result logout() {
        session().clear();
        return redirect(routes.AuthController.login());
    }
}
