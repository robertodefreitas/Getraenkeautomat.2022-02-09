package my.machine.camunda;

import my.machine.Getraenkeautomat;
import my.machine.dto.GetraenkUndWechselgeld;
import my.machine.dto.Getraenkewunsch;
import my.machine.kasse.Muenze;
import my.machine.kasse.Muenztyp;
import my.machine.waren.Getraenk;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaFormData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class KaufenAusfuehren implements JavaDelegate {

  private final static Logger LOGGER = Logger.getLogger("KAUFEN-AUSFUEHREN");
  Getraenkeautomat ga = GA.starten();

  public void execute(DelegateExecution execution) throws Exception {
    // https://forum.camunda.org/t/using-form-fields-enum-values-in-an-external-form/4001/4
    //CamundaFormData formData = execution.getBpmnModelElementInstance().getExtensionElements().getElementsQuery().filterByType(CamundaFormData.class).singleResult();
    String getraenk = execution.getVariable("getraenk").toString();
    Getraenkewunsch getraenkewunsch;
    List<Muenze> einzahlung = new ArrayList<>();

    for (Muenztyp muenzetyp: Muenztyp.values()) {
        Integer muenzAnzahl = execution.getVariable(muenzetyp.name()).hashCode();
        while (muenzAnzahl > 0){
            einzahlung.add(new Muenze(muenzetyp));
            muenzAnzahl = muenzAnzahl - 1;
        }
    }

    switch(getraenk){
      case "wasser":
        getraenkewunsch = new Getraenkewunsch(1);
        LOGGER.info("Gewaehltes Getraenk: " + getraenk);
        break;
      case "cola":
        getraenkewunsch = new Getraenkewunsch(2);
        LOGGER.info("Gewaehltes Getraenk: " + getraenk);
        break;
      default:
        getraenkewunsch = new Getraenkewunsch(1);
        LOGGER.info("Gewaehltes Getraenk: (default) " + getraenk);
        break;
    }

    GetraenkUndWechselgeld getraenkUndWechselgeld = ga.kaufen(getraenkewunsch,einzahlung);

    execution.setVariable("wechselgeld", Muenztyp.umwandelnMuenzen2Cents(getraenkUndWechselgeld.getWechselgeld()));

  }

//  private TaskService taskService;
//
//  public List<Task> getAllTasks() {
//    return taskService.createTaskQuery().taskAssignee("bernd").list();
//  }

}
