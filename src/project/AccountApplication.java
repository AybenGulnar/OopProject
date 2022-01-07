package project;


public class AccountApplication extends Application{

	private Account account;

	public AccountApplication(String applicationNo, Customer applicant, Account account) {
		super(applicationNo, applicant);
		this.account = account;
	}
	
	public AccountApplication(String applicationNo, Customer applicant, Account account, String applicationStatus) {
		super(applicationNo, applicant, applicationStatus);
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public void applicationAcceptance() {
		setApplicationResult(true);
	}

	public void applicationRefuse() {
		setApplicationResult(false);
	}
}
