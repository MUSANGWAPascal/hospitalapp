package controllers;

import models.LabTest;
import models.LabResult;
import models.Patient;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.lab.*;

import java.util.List;

public class LabController extends Controller {

    static Form<LabTest> labTestForm = Form.form(LabTest.class);
    static Form<LabResult> labResultForm = Form.form(LabResult.class);

    // LabTest CRUD
    public static Result listTests() {
        List<LabTest> tests = LabTest.find.all();
        return ok(testsIndex.render(tests));
    }


    public static Result createTest() {
        return ok(testsCreate.render(labTestForm));
    }

    public static Result saveTest() {
        Form<LabTest> boundForm = labTestForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(testsCreate.render(boundForm));
        }
        boundForm.get().save();
        return redirect(routes.LabController.listTests());
    }

    public static Result editTest(Long id) {
        LabTest test = LabTest.find.byId(id);
        if (test == null) return notFound("Lab test not found");
        return ok(testsEdit.render(labTestForm.fill(test), id));
    }

    public static Result updateTest(Long id) {
        Form<LabTest> boundForm = labTestForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(testsEdit.render(boundForm, id));
        }
        LabTest updated = boundForm.get();
        updated.testId = id;
        updated.update();
        return redirect(routes.LabController.listTests());
    }

    public static Result deleteTest(Long id) {
        LabTest.find.byId(id).delete();
        return redirect(routes.LabController.listTests());
    }

    // LabResult CRUD

    public static Result listResults() {
        List<LabResult> results = LabResult.find.all();
        return ok(resultsIndex.render(results));
    }

    public static Result createResult1() {
        return ok(resultsCreate.render(labResultForm, LabTest.find.all(), Patient.find.all()));
    }
    // Show form for creating a new LabResult
    public static Result createResult() {
        return ok(resultsCreate.render(labResultForm, LabTest.find.all(), Patient.find.all()));
    }

    public static Result saveResult1() {
        Form<LabResult> boundForm = labResultForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(resultsCreate.render(boundForm, LabTest.find.all(), Patient.find.all()));
        }
        boundForm.get().save();
        return redirect(routes.LabController.listResults());
    }
    // Save new LabResult
    public static Result saveResult() {
        Form<LabResult> boundForm = labResultForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(resultsCreate.render(boundForm, LabTest.find.all(), Patient.find.all()));
        }
        boundForm.get().save();
        return redirect(routes.LabController.listResults());
    }
    // Show form to edit existing LabResult
    public static Result editResult(Long id) {
        LabResult result = LabResult.find.byId(id);
        if (result == null) return notFound("Lab result not found");
        return ok(resultsEdit.render(labResultForm.fill(result), id, LabTest.find.all(), Patient.find.all()));
    }

    public static Result editResult1(Long id) {
        LabResult result = LabResult.find.byId(id);
        if (result == null) return notFound("Lab result not found");
        return ok(resultsEdit.render(labResultForm.fill(result), id, LabTest.find.all(), Patient.find.all()));
    }

    public static Result updateResult1(Long id) {
        Form<LabResult> boundForm = labResultForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(resultsEdit.render(boundForm, id, LabTest.find.all(), Patient.find.all()));
        }
        LabResult updated = boundForm.get();
        updated.id = id;
        updated.update();
        return redirect(routes.LabController.listResults());
    }
    // Update existing LabResult
    public static Result updateResult(Long id) {
        Form<LabResult> boundForm = labResultForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            return badRequest(resultsEdit.render(boundForm, id, LabTest.find.all(), Patient.find.all()));
        }
        LabResult updated = boundForm.get();
        updated.id = id;
        updated.update();
        return redirect(routes.LabController.listResults());
    }


    public static Result deleteResult1(Long id) {
        LabResult.find.byId(id).delete();
        return redirect(routes.LabController.listResults());
    }
    // Delete LabResult by id
    public static Result deleteResult(Long id) {
        LabResult result = LabResult.find.byId(id);
        if (result != null) {
            result.delete();
        }
        return redirect(routes.LabController.listResults());
    }
}
