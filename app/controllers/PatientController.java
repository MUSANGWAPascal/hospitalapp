package controllers;

import models.Doctor;
import models.Patient;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.patients.*;
import views.html.patients.create;


import java.util.List;

public class PatientController extends Controller {

    static Form<Patient> patientForm = Form.form(Patient.class);

    public static Result listPatients1() {
        List<Patient> patients = Patient.find.orderBy("fullName asc").findList();
        return ok(views.html.patients.list.render(patients));
    }
    public static Result listPatients() {
        List<Patient> patients = Patient.find
                .orderBy("fullName asc")
                .fetch("assignedDoctor") // This is important for eager fetching
                .findList();

        return ok(views.html.patients.list.render(patients));
    }



    public static Result create() {
        List<Doctor> doctors = Doctor.find.all();
        return ok(create.render(patientForm, doctors));
    }

    public static Result save() {
        Form<Patient> boundForm = patientForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            List<Doctor> doctors = Doctor.find.all();
            return badRequest(create.render(boundForm, doctors));
        }
        boundForm.get().save();
        return redirect(routes.PatientController.listPatients());
    }

    public static Result edit(Long id) {
        Patient patient = Patient.find.byId(id);
        if (patient == null) return notFound("Patient not found");
        List<Doctor> doctors = Doctor.find.all();
        return ok(edit.render(patientForm.fill(patient), patient.id, doctors));
    }

    public static Result update(Long id) {
        Form<Patient> boundForm = patientForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            List<Doctor> doctors = Doctor.find.all();
            return badRequest(edit.render(boundForm, id, doctors));
        }
        Patient updated = boundForm.get();
        Patient existing = Patient.find.byId(id);
        if (existing == null) return notFound("Patient not found");

        existing.fullName = updated.fullName;
        existing.gender = updated.gender;
        existing.address = updated.address;
        existing.phone = updated.phone;
        existing.dob = updated.dob;
        existing.bloodGroup = updated.bloodGroup;
        existing.assignedDoctor = updated.assignedDoctor;

        existing.update();
        return redirect(routes.PatientController.listPatients());
    }

    public static Result delete(Long id) {
        Patient.find.byId(id).delete();
        return redirect(routes.PatientController.listPatients());
    }
}
