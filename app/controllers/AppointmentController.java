package controllers;

import models.Appointment;
import models.Doctor;
import models.Patient;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.appointments.*;

import java.util.List;

public class AppointmentController extends Controller {

    static Form<Appointment> appointmentForm = Form.form(Appointment.class);

    public static Result list() {
        List<Appointment> appointments = Appointment.find.all();
        return ok(index.render(appointments));
    }

    public static Result create() {
        return ok(create.render(appointmentForm, Patient.find.all(), Doctor.find.all()));
    }

    public static Result save() {
        Form<Appointment> boundForm = appointmentForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(create.render(boundForm, Patient.find.all(), Doctor.find.all()));
        }
        boundForm.get().save();
        return redirect(routes.AppointmentController.list());
    }

    public static Result edit(Long id) {
        Appointment appointment = Appointment.find.byId(id);
        if (appointment == null) return notFound("Appointment not found");
        return ok(edit.render(appointmentForm.fill(appointment), id, Patient.find.all(), Doctor.find.all()));
    }

    public static Result update(Long id) {
        Form<Appointment> boundForm = appointmentForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(edit.render(boundForm, id, Patient.find.all(), Doctor.find.all()));
        }
        Appointment updated = boundForm.get();
        updated.id = id;
        updated.update();
        return redirect(routes.AppointmentController.list());
    }

    public static Result delete(Long id) {
        Appointment.find.byId(id).delete();
        return redirect(routes.AppointmentController.list());
    }
}
