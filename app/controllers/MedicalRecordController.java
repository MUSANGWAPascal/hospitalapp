package controllers;

import models.Doctor;
import models.MedicalRecord;
import models.Patient;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.medicalrecords.*;

import java.util.List;

public class MedicalRecordController extends Controller {

    static Form<MedicalRecord> medicalRecordForm = Form.form(MedicalRecord.class);

    public static Result list() {
        List<MedicalRecord> records = MedicalRecord.find.all();
        return ok(index.render(records));
    }

    public static Result create() {
        return ok(create.render(medicalRecordForm, Patient.find.all(), Doctor.find.all()));
    }

    public static Result save() {
        Form<MedicalRecord> boundForm = medicalRecordForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(create.render(boundForm, Patient.find.all(), Doctor.find.all()));
        }
        boundForm.get().save();
        return redirect(routes.MedicalRecordController.list());
    }

    public static Result edit(Long id) {
        MedicalRecord record = MedicalRecord.find.byId(id);
        if (record == null) return notFound("Medical Record not found");
        return ok(edit.render(medicalRecordForm.fill(record), id, Patient.find.all(), Doctor.find.all()));
    }

    public static Result update(Long id) {
        Form<MedicalRecord> boundForm = medicalRecordForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(edit.render(boundForm, id, Patient.find.all(), Doctor.find.all()));
        }
        MedicalRecord updated = boundForm.get();
        updated.recordId = id;
        updated.update();
        return redirect(routes.MedicalRecordController.list());
    }

    public static Result delete(Long id) {
        MedicalRecord.find.byId(id).delete();
        return redirect(routes.MedicalRecordController.list());
    }
}
