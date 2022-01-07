package project;

import java.util.ArrayList;

public class Manager extends Employee implements CanAddCustomer, CanDecideOpenMoneyAccount, CanDecideLoan,
		CanDisplayAccountTransaction, CanAddWorker, CanDecideCard {

	public Manager(String name, String userPassword, String id, double salary, String mail) {
		super(name, userPassword, id, salary, mail);
	}
	
	public Manager(String name,  String id, double salary, String mail) {
		super(name, id, salary, mail);
	}

	@Override
	public void addCard(Card card) {
		decidedCard.add(card);
	}

	@Override
	public void addWworker(Worker worker) {
		addedWorker.add(worker);
	}

	@Override
	public void addDisplayAccountTransaction(AccountTransaction accountTransaction) {
		displayedAccountTransaction.add(accountTransaction);
	}

	@Override
	public void addLoan(Loan loan) {
		decidedLoan.add(loan);
	}

	@Override
	public void addMoneyAccount(Account account) {
		addMoneyAccount.add(account);
	}

	@Override
	public void addCustomer(Customer customer) {
		addedCustomer.add(customer);
	}

	@Override
	public void calculateBonus() {
		if(addedCustomer.size() >= 10 && addedWorker.size()>=5) {
			setBonus(200);
		}
		else if(addedCustomer.size() >= 5 && addedWorker.size()>=2) {
			setBonus(100);
		}
	}
}
