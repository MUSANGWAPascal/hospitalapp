package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import utils.PasswordUtil;
import play.data.Form;
import views.html.usermanagement.*;


import java.util.List;

@Security.Authenticated(Secured.class)
public class UserController extends Controller {

    static Form<User> userForm = Form.form(User.class);

    private static boolean isAdmin() {
        return "ADMIN".equals(session("role"));
    }

    public static Result list() {
        if (!isAdmin()) {
            return forbidden("Access denied");
        }
        List<User> users = User.find.all();
        return ok(index.render(users));
    }

    public static Result create() {
        if (!isAdmin()) {
            return forbidden("Access denied");
        }
        return ok(create.render(userForm));
    }

    public static Result save() {
        if (!isAdmin()) {
            return forbidden("Access denied");
        }

        Form<User> boundForm = userForm.bindFromRequest();

        if (boundForm.hasErrors()) {
            return badRequest(create.render(boundForm));
        }

        User newUser = boundForm.get();

        if (newUser.password == null || newUser.password.trim().isEmpty()) {
            flash("error", "Password cannot be empty");
            return badRequest(create.render(boundForm));
        }

        newUser.save();
        return redirect(routes.UserController.list());
    }





    public static Result edit(Long id) {
        if (!isAdmin()) {
            return forbidden("Access denied");
        }
        User user = User.find.byId(id);
        if (user == null) return notFound("User not found");

        // Password field will be empty (for security), so we fill other fields manually
        Form<User> filledForm = userForm.fill(user);
        return ok(edit.render(filledForm, id));
    }

    public static Result update(Long id) {
        if (!isAdmin()) {
            return forbidden("Access denied");
        }
        Form<User> boundForm = userForm.bindFromRequest();

        if (boundForm.hasErrors()) {
            return badRequest(edit.render(boundForm, id));
        }

        User updatedUser = boundForm.get();
        User existingUser = User.find.byId(id);
        if (existingUser == null) return notFound("User not found");

        existingUser.username = updatedUser.username;
        existingUser.fullName = updatedUser.fullName;
        existingUser.role = updatedUser.role;

        // ✅ Correct password update
        if (updatedUser.password != null && !updatedUser.password.trim().isEmpty()) {
            existingUser.password = PasswordUtil.hashPassword(updatedUser.password);
        }

        existingUser.update();

        return redirect(routes.UserController.list());
    }

    public static Result delete(Long id) {
        if (!isAdmin()) {
            return forbidden("Access denied");
        }
        User.find.byId(id).delete();
        return redirect(routes.UserController.list());
    }
}
