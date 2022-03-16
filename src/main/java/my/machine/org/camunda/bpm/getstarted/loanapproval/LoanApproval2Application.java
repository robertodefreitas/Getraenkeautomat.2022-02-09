package my.machine.org.camunda.bpm.getstarted.loanapproval;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;

/**
 * camunda-server | 09-Mar-2022 16:11:30.600 SEVERE [Catalina-utility-2] org.apache.catalina.core.StandardContext.startInternal Error during ServletContainerInitializer processing
 * camunda-server |        javax.servlet.ServletException: ENGINE-07013 An application must not contain more than one class annotated with @ProcessApplication.
 * camunda-server |  Application '/loan-approval-0.1.0-SNAPSHOT' contains the following @ProcessApplication classes:
 * camunda-server |   org.camunda.bpm.getstarted.my.machine.org.camunda.bpm.getstarted.loanapproval.LoanApprovalApplication
 * camunda-server |   org.camunda.bpm.getstarted.my.machine.org.camunda.bpm.getstarted.loanapproval.LoanApproval2Application
 */
//@ProcessApplication("Loan Approval Zwo App")
public class LoanApproval2Application extends ServletProcessApplication {
    // empty implementation
}