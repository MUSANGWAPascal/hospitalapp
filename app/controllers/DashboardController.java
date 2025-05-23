//package controllers;
//
//import play.mvc.*;
//import views.html.dashboard.*;
//
//@Security.Authenticated(Secured.class)
//public class DashboardController extends Controller {
//
//    public static Result adminDashboard() {
//        if (!"ADMIN".equals(session("role"))) return forbidden("Access denied");
//        return ok(admin.render());
//    }
//
//    public static Result doctorDashboard() {
//        if (!"DOCTOR".equals(session("role"))) return forbidden("Access denied");
//        return ok(doctor.render());
//    }
//
//    public static Result staffDashboard() {
//        if (!"STAFF".equals(session("role"))) return forbidden("Access denied");
//        return ok(staff.render());
//    }
//}
