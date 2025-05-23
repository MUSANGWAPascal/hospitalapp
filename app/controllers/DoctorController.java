package controllers;

import models.Doctor;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.doctors.*;

import java.util.List;

public class DoctorController extends Controller {

    static Form<Doctor> doctorForm = Form.form(Doctor.class);

    public static Result listDoctors() {
        List<Doctor> doctors = Doctor.find.all();
        return ok(list.render(doctors));
    }

    public static Result create() {
        return ok(create.render(doctorForm));
    }

    public static Result save() {
        Form<Doctor> boundForm = doctorForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(create.render(boundForm));
        }
        boundForm.get().save();
        return redirect(routes.DoctorController.listDoctors());
    }

    public static Result edit(Long id) {
        Doctor doctor = Doctor.find.byId(id);
        if (doctor == null) return notFound("Doctor not found");
        return ok(edit.render(doctorForm.fill(doctor), id));
    }

    public static Result update(Long id) {
        Form<Doctor> boundForm = doctorForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(edit.render(boundForm, id));
        }
        Doctor updated = boundForm.get();
        updated.id = id;
        updated.update();
        return redirect(routes.DoctorController.listDoctors());
    }

    public static Result delete(Long id) {
        Doctor.find.byId(id).delete();
        return redirect(routes.DoctorController.listDoctors());
    }
}
