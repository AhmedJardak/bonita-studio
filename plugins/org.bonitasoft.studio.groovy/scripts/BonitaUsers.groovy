import org.bonitasoft.engine.api.APIAccessor;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.ContactData;

public class BonitaUsers {

	/**
	*@param apiAccessor, the current APIAccessor
	*@param loggedUserId, the user id represented by a long.
	*@return the logged User
	**/
	public static User getUser(APIAccessor apiAccessor,long loggedUserId){
		return apiAccessor.getIdentityAPI().getUser(loggedUserId);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param taskAssigneeId, the task assignee id represented by a long.
	*@return the task assignee User
	**/
	public static User getTaskAssignee(APIAccessor apiAccessor,long taskAssigneeId){
		return apiAccessor.getIdentityAPI().getUser(taskAssigneeId);
	}	
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param taskAssigneeId, the task assignee id represented by a long.
	*@return the task assignee ContactData
	**/
	public static ContactData getTaskAssigneeContactInfo(APIAccessor apiAccessor,long taskAssigneeId){
		return apiAccessor.getIdentityAPI().getUserContactData(taskAssigneeId, false);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param parentProcessInstanceId, the parent process instance id represented by a long.
	*@return the process instance initiator manager User
	**/
	public static User getProcessInstanceInitiatorManager(APIAccessor apiAccessor,long parentProcessInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(parentProcessInstanceId).getStartedBy();
 		long managerId = apiAccessor.getIdentityAPI().getUser(initiatorId).getManagerUserId();
 		return apiAccessor.getIdentityAPI().getUser(managerId);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param parentProcessInstanceId, the parent process instance id represented by a long.
	*@return the process instance initiator manager personal ContactData
	**/
	public static ContactData getProcessInstanceInitiatorManagerPersonalContactInfo(APIAccessor apiAccessor,long parentProcessInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(parentProcessInstanceId).getStartedBy();
 		long managerId = apiAccessor.getIdentityAPI().getUser(initiatorId).getManagerUserId();
 		return apiAccessor.getIdentityAPI().getUserContactData(managerId, true);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param parentProcessInstanceId, the parent process instance id represented by a long.
	*@return the process instance initiator manager professional ContactData
	**/
	public static ContactData getProcessInstanceInitiatorManagerProfessionalContactInfo(APIAccessor apiAccessor,long parentProcessInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(parentProcessInstanceId).getStartedBy();
 		long managerId = apiAccessor.getIdentityAPI().getUser(initiatorId).getManagerUserId();
 		return apiAccessor.getIdentityAPI().getUserContactData(managerId, false);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param parentProcessInstanceId, the parent process instance id represented by a long.
	*@return the process instance initiator User
	**/
	public static User getProcessInstanceInitiator(APIAccessor apiAccessor,long parentProcessInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(parentProcessInstanceId).getStartedBy();
 		return apiAccessor.getIdentityAPI().getUser(initiatorId);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param parentProcessInstanceId, the parent process instance id represented by a long.
	*@return the process instance initiator personal ContactData
	**/
	public static ContactData getProcessInstanceInitiatorPersonalContactInfo(APIAccessor apiAccessor,long parentProcessInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(parentProcessInstanceId).getStartedBy();
 		return apiAccessor.getIdentityAPI().getUserContactData(initiatorId, true);
	}
	
	/**
	*@param apiAccessor, the current APIAccessor
	*@param parentProcessInstanceId, the parent process instance id represented by a long.
	*@return the process instance initiator professional ContactData
	**/
	public static ContactData getProcessInstanceInitiatorProfessionalContactInfo(APIAccessor apiAccessor,long parentProcessInstanceId){
		long initiatorId = apiAccessor.getProcessAPI().getProcessInstance(parentProcessInstanceId).getStartedBy();
 		return apiAccessor.getIdentityAPI().getUserContactData(initiatorId, false);
	}
	
}