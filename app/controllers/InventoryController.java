package controllers;

import models.InventoryItem;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.inventory.*;

import java.util.List;

public class InventoryController extends Controller {

    static Form<InventoryItem> itemForm = Form.form(InventoryItem.class);

    public static Result list() {
        List<InventoryItem> items = InventoryItem.find.all();
        return ok(index.render(items));
    }

    public static Result create() {
        return ok(create.render(itemForm));
    }

    public static Result save() {
        Form<InventoryItem> boundForm = itemForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(create.render(boundForm));
        }
        boundForm.get().save();
        return redirect(routes.InventoryController.list());
    }

    public static Result edit(Long id) {
        InventoryItem item = InventoryItem.find.byId(id);
        if (item == null) return notFound("Item not found");
        return ok(edit.render(itemForm.fill(item), id));
    }

    public static Result update(Long id) {
        Form<InventoryItem> boundForm = itemForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(edit.render(boundForm, id));
        }
        InventoryItem updated = boundForm.get();
        updated.id = id;
        updated.update();
        return redirect(routes.InventoryController.list());
    }

    public static Result delete(Long id) {
        InventoryItem.find.byId(id).delete();
        return redirect(routes.InventoryController.list());
    }
}
