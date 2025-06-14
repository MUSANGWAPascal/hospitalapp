package controllers;

import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.prescription.*;

import java.util.List;

public class PrescriptionController extends Controller {

    static Form<Prescription> prescriptionForm = Form.form(Prescription.class);

    public static Result list() {
        List<Prescription> prescriptions = Prescription.find.all();
        return ok(list.render(prescriptions));
    }

    public static Result create() {
        return ok(create.render(prescriptionForm, Appointment.find.all()));
    }

    public static Result save() {
        Form<Prescription> form = prescriptionForm.bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(create.render(form, Appointment.find.all()));
        }
        form.get().save();
        return redirect(routes.PrescriptionController.list());
    }

    public static Result edit(Long id) {
        Prescription prescription = Prescription.find.byId(id);
        Form<Prescription> form = prescriptionForm.fill(prescription);
        return ok(edit.render(form, id, Appointment.find.all()));
    }

    public static Result update(Long id) {
        Form<Prescription> form = prescriptionForm.bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(edit.render(form, id, Appointment.find.all()));
        }
        Prescription updated = form.get();
        updated.prescriptionId = id;
        updated.update();
        return redirect(routes.PrescriptionController.list());
    }

    public static Result delete(Long id) {
        Prescription.find.byId(id).delete();
        return redirect(routes.PrescriptionController.list());
    }
}