package my.machine.org.camunda.bpm.getstarted.loanapproval;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.persistence.entity.TaskEntity;
import org.camunda.bpm.engine.task.IdentityLink;

import java.util.Set;
import java.util.logging.Logger;

public class ProcessRequest2Delegate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");

    public void execute(DelegateExecution execution) throws Exception {

        /**
         * ### mary
         * 10-Mar-2022 13:07:07.255 INFORMATION [http-nio-8199-exec-6] org.camunda.bpm.getstarted.my.machine.org.camunda.bpm.getstarted.loanapproval.ProcessRequestDelegate.execute
         * Processing request by 'CID-20220310-1306'...
         *
         * 10-Mar-2022 13:07:07.259 INFORMATION [http-nio-8199-exec-6] org.camunda.bpm.getstarted.my.machine.org.camunda.bpm.getstarted.loanapproval.ProcessRequest2Delegate.execute
         * Processing (2) request by 'CID-20220310-1306' | getBpmnModelInstance: BPMN Model | getActivityInstanceId: 9b34d141-a06a-11ec-83b0-62f262e7f31a | getCurrentActivityId: ServiceTask_ProcessRequest2Delegate
         *
         * 10-Mar-2022 13:07:07.259 INFORMATION [http-nio-8199-exec-6] org.camunda.bpm.getstarted.my.machine.org.camunda.bpm.getstarted.loanapproval.ProcessRequest2Delegate.execute
         * Processing (2) selectExampleRequest: Option_2 | selectExampleApprove: Option_3
         *
         * ### john
         * 10-Mar-2022 13:08:01.241 INFORMATION [http-nio-8199-exec-9] org.camunda.bpm.getstarted.my.machine.org.camunda.bpm.getstarted.loanapproval.ProcessRequestDelegate.execute
         * Processing request by 'CID-20220310-1306'...
         *
         * 10-Mar-2022 13:08:01.243 INFORMATION [http-nio-8199-exec-9] org.camunda.bpm.getstarted.my.machine.org.camunda.bpm.getstarted.loanapproval.ProcessRequest2Delegate.execute
         * Processing (2) request by 'CID-20220310-1306' | getBpmnModelInstance: BPMN Model | getActivityInstanceId: bb629479-a06a-11ec-83b0-62f262e7f31a | getCurrentActivityId: ServiceTask_ProcessRequest2Delegate
         *
         * 10-Mar-2022 13:08:01.244 INFORMATION [http-nio-8199-exec-9] org.camunda.bpm.getstarted.my.machine.org.camunda.bpm.getstarted.loanapproval.ProcessRequest2Delegate.execute
         * Processing (2) selectExampleRequest: Option_2 | selectExampleApprove: Option_4
         */
        LOGGER.info(
                "Processing (2) request by '"
                        + execution.getVariable("customerId")
                        + "' | getBpmnModelInstance: "
                        + execution.getBpmnModelInstance().getModel().getModelName()
                        + " | getActivityInstanceId: "
                        + execution.getActivityInstanceId()
                        + " | getCurrentActivityId: "
                        + execution.getCurrentActivityId()
        );

        LOGGER.info(
                "Processing (2) selectExampleRequest: "
                        + execution.getVariable("selectExampleRequest")
                        + " | selectExampleApprove: "
                        + execution.getVariable("selectExampleApprove")
        );

        //DelegateTask delegateTask = new TaskEntity();
        //notify(delegateTask);
    }

    // https://forum.camunda.org/t/get-candidate-user-group-and-assignee-from-delegateexecution/13305
    //@Override
    public void notify(DelegateTask task) {

        if(task.getAssignee() != null) {
            String assignee = task.getAssignee();
            LOGGER.info("The assignee for task " + task.getId() + " is: " + assignee);
        }
        else {
            LOGGER.info("Task " + task.getId() + " is not yet assigned. Displaying all candidate users and groups...");

            Set<IdentityLink> identityLinks = task.getCandidates();
            if(identityLinks.isEmpty())
                LOGGER.info("No candidate users or groups exist for this User Task. Id: " + task.getId());
            else {
                for (IdentityLink identityLink : identityLinks) {
                    if(identityLink.getGroupId() != null)
                        LOGGER.info("Task " + task.getId() + " has an identity link for a group with id: "
                                + identityLink.getGroupId());
                    else if(identityLink.getUserId() != null)
                        LOGGER.info("Task " + task.getId() + " has an identity link for a user with id: "
                                + identityLink.getUserId());
                }
            }
        }
    }
}
