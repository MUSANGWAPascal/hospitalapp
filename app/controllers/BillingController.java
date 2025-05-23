package controllers;

import models.Bill;
import models.Patient;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.billing.*;

import java.util.List;

public class BillingController extends Controller {

    static Form<Bill> billForm = Form.form(Bill.class);

    public static Result list() {
        List<Bill> bills = Bill.find.all();
        return ok(index.render(bills));
    }

    public static Result create() {
        return ok(create.render(billForm, Patient.find.all()));
    }

    public static Result save() {
        Form<Bill> boundForm = billForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(create.render(boundForm, Patient.find.all()));
        }
        boundForm.get().save();
        return redirect(routes.BillingController.list());
    }

    public static Result edit(Long id) {
        Bill bill = Bill.find.byId(id);
        if (bill == null) return notFound("Bill not found");
        return ok(edit.render(billForm.fill(bill), id, Patient.find.all()));
    }

    public static Result update(Long id) {
        Form<Bill> boundForm = billForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(edit.render(boundForm, id, Patient.find.all()));
        }
        Bill updated = boundForm.get();
        updated.id = id;
        updated.update();
        return redirect(routes.BillingController.list());
    }

    public static Result delete(Long id) {
        Bill.find.byId(id).delete();
        return redirect(routes.BillingController.list());
    }
}
