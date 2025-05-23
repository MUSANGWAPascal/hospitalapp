package controllers;

import models.Appointment;
import models.Doctor;
import models.Patient;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.dashboard;

public class Application extends Controller {
    public static Result dashboard() {
        String role = session("role");

        if ("ADMIN".equals(role)) {
            return ok(views.html.adminDashboard.render("Welcome, Admin!"));
        } else if ("DOCTOR".equals(role)) {
            Long userId = Long.parseLong(session("userId"));
            Doctor doctor = Doctor.find.where().eq("user.id", userId).findUnique();

            if (doctor == null) {
                return internalServerError("No doctor profile found for this user.");
            }

            int patientCount = Patient.find.where().eq("assignedDoctor.id", doctor.id).findRowCount();
            int appointmentCount = Appointment.find.where().eq("doctor.id", doctor.id).findRowCount();

            return ok(views.html.doctorDashboard.render("Welcome Doctor", patientCount, appointmentCount));
        } else if ("STAFF".equals(role)) {
            return ok(views.html.staffDashboard.render("Welcome, Staff!"));
        } else {
            return forbidden("Unknown role");
        }
    }
}
