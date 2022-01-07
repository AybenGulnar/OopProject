package project;


public abstract class Application {
	private String applicationNo;
	private Customer applicant;
	private String applicationStatus = "Waiting";
	private boolean applicationResult = false;

	public Application(String applicationNo, Customer applicant) {
		super();
		this.applicationNo = applicationNo;
		this.applicant = applicant;
	}
	
	public Application(String applicationNo, Customer applicant, String applicationStatus) {
		super();
		this.applicationNo = applicationNo;
		this.applicant = applicant;
		this.applicationStatus = applicationStatus;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public Customer getApplicant() {
		return applicant;
	}

	public void setApplicant(Customer applicant) {
		this.applicant = applicant;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public boolean getApplicationResult() {
		return applicationResult;
	}

	public void setApplicationResult(boolean applicationResult) {
		this.applicationResult = applicationResult;
	}

	public abstract void applicationAcceptance();

	public abstract void applicationRefuse();

}
