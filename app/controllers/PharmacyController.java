package controllers;

import models.Medicine;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.pharmacy.*;

import java.util.List;

public class PharmacyController extends Controller {

    static Form<Medicine> medicineForm = Form.form(Medicine.class);

    public static Result list() {
        List<Medicine> medicines = Medicine.find.all();
        return ok(index.render(medicines));
    }

    public static Result create() {
        return ok(create.render(medicineForm));
    }

    public static Result save() {
        Form<Medicine> boundForm = medicineForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(create.render(boundForm));
        }
        boundForm.get().save();
        return redirect(routes.PharmacyController.list());
    }

    public static Result edit(Long id) {
        Medicine medicine = Medicine.find.byId(id);
        if (medicine == null) return notFound("Medicine not found");
        return ok(edit.render(medicineForm.fill(medicine), id));
    }

    public static Result update(Long id) {
        Form<Medicine> boundForm = medicineForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(edit.render(boundForm, id));
        }
        Medicine updated = boundForm.get();
        updated.id = id;
        updated.update();
        return redirect(routes.PharmacyController.list());
    }

    public static Result delete(Long id) {
        Medicine.find.byId(id).delete();
        return redirect(routes.PharmacyController.list());
    }
}
