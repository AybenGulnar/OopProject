package project;

import java.util.Random;

public class Worker extends Employee
		implements CanAddCustomer, CanDecideOpenMoneyAccount, CanDisplayAccountTransaction, CanDecideCard {

	private Employee chargeEmployee;

	public Worker(String name, String userPassword,  String id, double salary, String mail) {
		super(name, userPassword, id, salary, mail);
	}
	
	public Worker(String name,  String id, double salary, String mail, Employee chargeEmployee) {
		super(name, id, salary, mail);
		this.chargeEmployee = chargeEmployee;
		
	}

	public Employee getChargeEmployee() {
		return chargeEmployee;
	}

	public void setChargeEmployee(Employee chargeEmployee) {
		this.chargeEmployee = chargeEmployee;
	}

	@Override
	public void addCard(Card card) {
		decidedCard.add(card);
	}

	@Override
	public void addDisplayAccountTransaction(AccountTransaction accountTransaction) {
		displayedAccountTransaction.add(accountTransaction);
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
		if(addedCustomer.size() >= 10) {
			setBonus(100);
		}
		else if(addedCustomer.size() >=5) {
			setBonus(50);
		}
	}

}
