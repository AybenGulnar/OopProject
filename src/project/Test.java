package project;

import java.util.*;

import javax.swing.colorchooser.AbstractColorChooserPanel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.sql.Date;
import bankmanagement.*;
import database.*;

public class Test {

	/**
	 * SimpleAuthenticator is used to do simple authentication when the SMTP server
	 * requires it.
	 * 
	 * @throws ParseException
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */

	public static void main(String[] args) throws ParseException {
		
//		long millis = System.currentTimeMillis();
//		java.sql.Date date = new java.sql.Date(millis);

//		Manager mng = new Manager("Derda-name", "derda-password", "derda-username", "derda-id", 10000,
//				"derda@gmail.com");
//		//database.General.createEmployee(mng);
//
//		IndividualCustomer IC = new IndividualCustomer("Ali Mert", "123456789", "Ankara", "alimert3215@gmail.com", 5000,
//				mng, "Ali Mert id", "id-123", date);
//		//database.General.createCustomer(IC);
//
//		CurrentAccount CA = new CurrentAccount(IC, "614579", mng);
//		//database.General.createAccount(CA);
//
//		YoungCard YC = new YoungCard("654789", IC, "756", date, CA);
//		//database.General.createCard(YC);
//
//		GoldCard GC = new GoldCard("354845", IC, "248", date, 10000, 20000, 50000);
//		//database.General.createCard(GC);

//		 java.util.Date date1 = null;
//        Scanner input2 = new Scanner(System.in);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        System.out.print("Enter check-in date (gg/aa/yy): ");
//        String cinput = input2.nextLine();
//        if(null != cinput && cinput.trim().length() > 0){
//        	date1 = format.parse(cinput);
//        }
//        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
//        System.out.println(sqlDate);
//        System.out.println(date1);
//        System.out.println(sqlDate.getYear());
//        System.out.println(sqlDate.getMonth());
//        System.out.println(date1.getYear());
//        System.out.println(date1.getMonth());
		// database.General.createCustomer(deneme);

		project.Customer activeCustomer;
		project.Employee activeEmployee;
		ArrayList<project.Account> activeCustomerAccounts;
		ArrayList<project.Loan> activeCustomerLoans;
		ArrayList<project.Card> activeCustomerCards;
		ArrayList<project.Application> activeCustomerApplications;
		ArrayList<project.Application> employeeDecidedApplications;

		String userName = "", password = "";
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to MyBank.\n");

		int option = 0;
		System.out.println("1 - Customer");
		System.out.println("2 - Administrator");
		System.out.println("3 - Join us");
		System.out.println("4 - Exit\n");
		System.out.print("--> ");

		option = input.nextInt();

		switch (option) {
		case 1:
			System.out.print("\nUsername: ");
			userName = input.next();
			System.out.print("Password: "); // asterix
			password = input.next();
			while (true) {
				if (bankmanagement.functions.checkCustomerPass(userName, password)) {
					activeCustomer = database.General.getCustomer(userName);
					System.out.println("1 - My Accounts");
					System.out.println("2 - My Loans");
					System.out.println("3 - My Cards");
					System.out.println("4 - My Applications");
					System.out.println("5 - Make an application");
					System.out.println("6 - Send Money");
					System.out.println("7 - Payment with card");
					System.out.println("8 - Change Password");
					System.out.println("9 - Previous Menu");
					System.out.println("10 - Exit");
					System.out.print("--> ");
					option = input.nextInt();

					switch (option) {
					case 1:
						while (true) {
							activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
							System.out.println("\nFor previous menu, enter 0.\n");
							for (project.Account account : activeCustomerAccounts) {

								System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
								System.out.print("Account no: " + account.getAccountNo());
								System.out.println(" Balance: $" + account.getBalance());
							}
							System.out.print("--> ");
							option = input.nextInt();
							if (option == 0) {
								break;
							}
							Account selectedAccount = activeCustomerAccounts.get(option - 1);

							for (project.AccountTransaction accountTransaction : selectedAccount
									.getAccountTransactions()) {

								if (accountTransaction instanceof project.MoneyTransfer) {
									project.MoneyTransfer moneyTransfer = (project.MoneyTransfer) accountTransaction;
									System.out.println("Money Transfer ");
									System.out.print(" Receiver: " + moneyTransfer.getReceiver().getOwner().getName());
									System.out
											.print(" Forwarder: " + moneyTransfer.getForwarder().getOwner().getName());
								} else if (accountTransaction instanceof project.Payment) {
									project.Payment payment = (project.Payment) accountTransaction;
									System.out.println("Payment ");
									System.out.print(" Paid Account: " + payment.getForwarder().getAccountNo());
								}
								System.out.print(" Amount: " + accountTransaction.getAmount());
								System.out.println(" Description: " + accountTransaction.getDescription());
							}

							System.out.println("\nTo go to the previous menu, press any key.");
							input.next();
						}

						break;
					case 2:

						while (true) {
							activeCustomerLoans = database.Customer.getLoans(activeCustomer);

							for (project.Loan loan : activeCustomerLoans) {
								System.out.print(activeCustomerLoans.indexOf(loan) + 1 + " - ");
								System.out.print("Loan no: " + loan.getLoanNo());
								System.out.print(" Interest: " + loan.getInterest());
								System.out.print(" Amount: " + loan.getAmount());
								System.out.print(" Maturity: " + loan.getMaturity());
								System.out.print(" RefundedAmount: " + loan.getRefundedAmount());
								System.out.println(" RemainingDebt: " + loan.getRemainingDebt());

							}
							System.out.print("\nTo go to the previous menu, press any key. - ");
							input.next();
							break;
						}
						break;
					case 3:
						while (true) {
							activeCustomerCards = database.Customer.getCards(activeCustomer);
							for (project.Card card : activeCustomerCards) {
								System.out.print(activeCustomerCards.indexOf(card) + 1 + " - ");
								System.out.print("Card no: " + card.getCardNo());
								System.out.println(" Card: " + card.toString());

							}
							System.out.println("\nFor previous menu, enter 0.\n");
							System.out.print("--> ");
							option = input.nextInt();
							if (option == 0) {
								break;
							}
							Card selectedCard = activeCustomerCards.get(option - 1);
							if (selectedCard instanceof project.IndividualBankCard) {
								project.IndividualBankCard bankCard = (project.IndividualBankCard) selectedCard;
								System.out.println("Balance: " + bankCard.getBalance());
								System.out.println("Card Point: " + bankCard.getCarPoint());
								System.out.println("Account No: " + bankCard.getAccount().getAccountNo());
							} else if (selectedCard instanceof project.IndividualCreditCard) {
								project.IndividualCreditCard creditCard = (project.IndividualCreditCard) selectedCard;
								System.out.println("Avaliable Limit: " + creditCard.getAvailableLimit());
								System.out.println("Card Limit: " + creditCard.getCardLimit());
								System.out.println("Card Point: " + creditCard.getCarPoint());
							} else if (selectedCard instanceof project.CorporateBankCard) {
								project.CorporateBankCard bankCard = (project.CorporateBankCard) selectedCard;
								System.out.println("Balance: " + bankCard.getBalance());
								System.out.println("Card Point: " + bankCard.getCarPoint());
								System.out.println("Account No: " + bankCard.getAccount().getAccountNo());
							} else if (selectedCard instanceof project.CorporateCreditCard) {
								project.CorporateCreditCard creditCard = (project.CorporateCreditCard) selectedCard;
								System.out.println("Available Limit: " + creditCard.getAvailableLimit());
								System.out.println("Card Limit: " + creditCard.getCardLimit());
								System.out.println("Card Point: " + creditCard.getCarPoint());
							}

							ArrayList<project.Payment> payments = selectedCard.getPayments();
							for (project.Payment payment : payments) {
								System.out.print(payments.indexOf(payment) + 1 + " - ");
								System.out.print("Description: " + payment.getDescription());
								System.out.println(" Amount: " + payment.getAmount());
							}

							System.out.print("\nTo go to the previous menu, press any key. - ");
							input.next();
						}

						break;
					case 4:
						while (true) {
							activeCustomerApplications = database.Customer.getApplications(activeCustomer);
							for (Application application : activeCustomerApplications) {
								System.out
										.println(" - " + (activeCustomerApplications.indexOf(application) + 1) + " - ");
								System.out.println("Application No: " + application.getApplicationNo());
								System.out.println("Application Status: " + application.getApplicationStatus());
								if (application instanceof project.LoanApplication) {
									project.LoanApplication loanApplication = (project.LoanApplication) application;
									// System.out.println("Loan Type: " + loanApplication.getLoan().getClass());
									System.out.println("Loan No: " + loanApplication.getLoan().getLoanNo());
									System.out.println("Interest: " + loanApplication.getLoan().getInterest());
									System.out.println("Maturity: " + loanApplication.getLoan().getMaturity());
									System.out
											.println("Remaining Debt: " + loanApplication.getLoan().getRemainingDebt());
									System.out.println(
											"Refunded Amount: " + loanApplication.getLoan().getRefundedAmount());
									// verilen kredi tÃ¼rÃ¼ne gÃ¶re bilgileri de yazdÄ±rmak gerek
									// ev kredisiyse tapu gibi bilgileri de yazdÄ±rmalÄ±yÄ±z
								} else if (application instanceof project.CardApplication) {
									project.CardApplication cardApplication = (project.CardApplication) application;
									System.out.println("Card No: " + cardApplication.getCard().getCardNo());
									// verilen kart tÃ¼rÃ¼ne gÃ¶re bilgileri de yazdÄ±rmak gerek
								} else if (application instanceof project.AccountApplication) {
									project.AccountApplication accountApplication = (project.AccountApplication) application;

								}
							}

							System.out.print("\nTo go to the previous menu, press any key. - ");
							input.next();
							break;
						}
						break;
					case 5:
						double limit;
						while (true) {
							System.out.println("Please choose the application type:");
							System.out.println("1 - Credit Card application");
							System.out.println("2 - Loan application");
							System.out.println("3 - Account application");
							System.out.println("4 - Previous Menu");
							System.out.print("--> ");
							int choose = input.nextInt();

							if (choose == 4) {
								break;
							}
							while (true) {
								if (choose == 1) {
									System.out.println("Please choose the card type:");
									if (activeCustomer instanceof project.IndividualCustomer) {
										System.out.println("1 - Silver Card (Max limit is 5000)");
										System.out.println("2 - Gold Card (Max limit is 10000)");
										System.out.println("3 - Previous Menu");
										System.out.print("--> ");
										int choose2 = input.nextInt();
										if (choose2 == 3) {
											break;
										}
										if (choose2 == 1) {
											System.out.println("Please enter card limit you want:");
											System.out.print("--> ");
											limit = input.nextDouble();
											if (limit > 5000) {
												System.out.println("The limit is too high for this card!");
											} else { // Kullanï¿½cï¿½ -5 girerse?
												bankmanagement.functions.doApplication(bankmanagement.functions
														.createCardApplication(bankmanagement.functions
																.createIndividualCreditCard(limit, activeCustomer)));
												System.out.println("Done");
											}
										} else if (choose2 == 2) {
											System.out.println("Please enter card limit you want:");
											System.out.print("--> ");
											limit = input.nextDouble();
											if (limit > 10000) {
												System.out.println("The limit is too high for this card!");
											} else {
												bankmanagement.functions.createCardApplication(bankmanagement.functions
														.createIndividualCreditCard(limit, activeCustomer));
												System.out.println("Done");
											}
										}

									} else if (activeCustomer instanceof project.CorporateCustomer) {
										System.out.println("1 - Silver Card Business(Max limit is 25000)");
										System.out.println("2 - Gold Card Business(Max limit is 50000)");
										System.out.print("--> ");
										int choose2 = input.nextInt();
										if (choose2 == 1) {
											System.out.println("Please enter card limit you want:");
											System.out.print("--> ");
											limit = input.nextDouble();
											if (limit > 25000) {
												System.out.println("The limit is too high for this card!");
											} else {
												bankmanagement.functions.createCardApplication(bankmanagement.functions
														.createCorporateCreditCard(limit, activeCustomer));
												System.out.println("Done");
											}
										} else if (choose2 == 2) {
											System.out.println("Please enter card limit you want:");
											System.out.print("--> ");
											limit = input.nextDouble();
											if (limit > 50000) {
												System.out.println("The limit is too high for this card!");
											} else {
												bankmanagement.functions.createCardApplication(bankmanagement.functions
														.createCorporateCreditCard(limit, activeCustomer));
												System.out.println("Done");
											}
										}

									}

								} else if (choose == 2) {
									System.out.println("Please choose the loan type:");
									if (activeCustomer instanceof project.IndividualCustomer) {
										System.out.println("1 - Transport Loan with Insurance");
										System.out.println("2 - Transport Loan without Insurance");
										System.out.println("3 - Employee Housing Loan");
										System.out.println("4 - Retired Housing Loan");
										System.out.println("5 - Employee Personal Finance Loan");
										System.out.println("6 - Retired Personal Finance Loan");
										System.out.println("7 - Consumer Personal Finance Loan");
										System.out.println("8 - Previous Menu");
										System.out.print("--> ");
										int choose2 = input.nextInt();
										if (choose2 == 8) {
											break;
										}
										String guarantorNo;
										Customer guarantor;
										double amount;
										int maturity;
										Account givenLoanAccount;
										if (choose2 == 1) {
											System.out.println("Please enter your guarantor: ");
											System.out.print("--> ");
											guarantorNo = input.next();
											guarantor = database.General.getCustomer(guarantorNo);
											System.out.println("Please enter amount:");
											System.out.print("--> ");
											amount = input.nextDouble();
											System.out.println("Please enter maturity:");
											System.out.print("--> ");
											maturity = input.nextInt();
											activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
											System.out.println(
													"Please select the account to which the money will be deposited: ");
											for (project.Account account : activeCustomerAccounts) {

												System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
												System.out.print("Account no: " + account.getAccountNo());
												System.out.println(" Balance: $" + account.getBalance());
											}
											int choose3 = input.nextInt();
											givenLoanAccount = activeCustomerAccounts.get(choose3 - 1);

											String licensePlate = input.next();
											int vehicleAge = input.nextInt();
// Customer loanTaker, Customer guarantor, int maturity, double amount, Date lastPayment,
//Date givenLoanDate, Account givenLoanAccount, String licensePlate, int vehicleAge, String insuranceNo
											project.Insuranced loan = new Insuranced(activeCustomer, guarantor, maturity, amount, null, null, givenLoanAccount, licensePlate, vehicleAge, licensePlate);

											System.out.println(loan.toString());
											System.out.println(loan.getAmount());
											System.out.println(loan.getRefundedAmount());

											System.out.println("Do you still want to the loan? Are you serious?");
											System.out.println("1 - Yes I want, I trust myself.");
											System.out.println("2 - WTF how can I pay this loan");
											System.out.print("--> ");
											int choose4 = input.nextInt();
											if (choose4 == 1) {
												bankmanagement.functions.doApplication(
														bankmanagement.functions.createLoanApplication(loan));
											} else if (choose4 == 2) {
												break;
											}
										} else if (choose2 == 2) {
											System.out.println("Please enter your guarantor:");
											System.out.print("--> ");
											guarantorNo = input.next();
											guarantor = database.General.getCustomer(guarantorNo);
											System.out.println("Please enter amount:");
											System.out.print("--> ");
											amount = input.nextDouble();
											System.out.println("Please enter maturity:");
											System.out.print("--> ");
											maturity = input.nextInt();
											activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
											System.out.println(
													"Please select the account to which the money will be deposited");
											for (project.Account account : activeCustomerAccounts) {

												System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
												System.out.print("Account no: " + account.getAccountNo());
												System.out.println(" Balance: $" + account.getBalance());
											}
											int choose3 = input.nextInt();
											givenLoanAccount = activeCustomerAccounts.get(choose3 - 1);

											String licensePlate = input.next();
											int vehicleAge = input.nextInt();
											long millis = System.currentTimeMillis();
 											java.sql.Date date = new java.sql.Date(millis);
//Customer loanTaker, Customer guarantor, int maturity, double amount, 
//Date lastPayment, Date givenLoanDate, Account givenLoanAccount, String licensePlate, int vehicleAge
											project.NotInsuranced loan = new NotInsuranced(activeCustomer, guarantor,
													maturity, amount, date, date, givenLoanAccount, licensePlate,
													vehicleAge);
											System.out.println("deneme");
											System.out.println(loan.toString());
											System.out.println(loan.getAmount());
											System.out.println(loan.getRefundedAmount());

											System.out.println("Do you still want to the loan? Are you serious?");
											System.out.println("1 - Yes I want, I trust myself.");
											System.out.println("2 - WTF how can I pay this loan");
											System.out.print("--> ");
											int choose4 = input.nextInt();
											if (choose4 == 1) {
												bankmanagement.functions.doApplication(
														bankmanagement.functions.createLoanApplication(loan));
											} else if (choose4 == 2) {
												break;
											}
										} else if (choose2 == 3) {
											System.out.println("Please enter your guarantor:");
											System.out.print("--> ");
											guarantorNo = input.next();
											guarantor = database.General.getCustomer(guarantorNo);
											System.out.println("Please enter amount:");
											System.out.print("--> ");
											amount = input.nextDouble();
											System.out.println("Please enter maturity:");
											System.out.print("--> ");
											maturity = input.nextInt();
											activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
											System.out.println(
													"Please select the account to which the money will be deposited");
											for (project.Account account : activeCustomerAccounts) {

												System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
												System.out.print("Account no: " + account.getAccountNo());
												System.out.println(" Balance: $" + account.getBalance());
											}
											int choose3 = input.nextInt();
											givenLoanAccount = activeCustomerAccounts.get(choose3 - 1);

											String deed = input.next();
											long millis = System.currentTimeMillis();
 											java.sql.Date date = new java.sql.Date(millis);
//Customer loanTaker, Customer guarantor, int maturity, double amount, Date lastPayment, Date givenLoanDate, Account givenLoanAccount, String deed
											project.EmployeeHousingLoan loan = new EmployeeHousingLoan(activeCustomer, guarantor, maturity, amount, date, date, givenLoanAccount, deed);

											System.out.println(loan.toString());
											System.out.println(loan.getAmount());
											System.out.println(loan.getRefundedAmount());

											System.out.println("Do you still want to the loan? Are you serious?");
											System.out.println("1 - Yes I want, I trust myself.");
											System.out.println("2 - WTF how can I pay this loan");
											System.out.print("--> ");
											int choose4 = input.nextInt();
											if (choose4 == 1) {
												bankmanagement.functions.doApplication(
														bankmanagement.functions.createLoanApplication(loan));
											} else if (choose4 == 2) {
												break;
											}
										} else if (choose2 == 4) {
											System.out.println("Please enter your guarantor:");
											System.out.print("--> ");
											guarantorNo = input.next();
											guarantor = database.General.getCustomer(guarantorNo);
											System.out.println("Please enter amount:");
											System.out.print("--> ");
											amount = input.nextDouble();
											System.out.println("Please enter maturity:");
											System.out.print("--> ");
											maturity = input.nextInt();
											activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
											System.out.println(
													"Please select the account to which the money will be deposited");
											for (project.Account account : activeCustomerAccounts) {

												System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
												System.out.print("Account no: " + account.getAccountNo());
												System.out.println(" Balance: $" + account.getBalance());
											}
											int choose3 = input.nextInt();
											givenLoanAccount = activeCustomerAccounts.get(choose3 - 1);

											String deed = input.next();
											
											long millis = System.currentTimeMillis();
 											java.sql.Date date = new java.sql.Date(millis);
 											
											project.RetiredHousingLoan loan = new RetiredHousingLoan(activeCustomer, guarantor, maturity, amount, date, date,
													givenLoanAccount, deed);

											System.out.println(loan.toString());
											System.out.println(loan.getAmount());
											System.out.println(loan.getRefundedAmount());

											System.out.println("Do you still want to the loan? Are you serious?");
											System.out.println("1 - Yes I want, I trust myself.");
											System.out.println("2 - WTF how can I pay this loan");
											System.out.print("--> ");
											int choose4 = input.nextInt();
											if (choose4 == 1) {
												bankmanagement.functions.doApplication(
														bankmanagement.functions.createLoanApplication(loan));
											} else if (choose4 == 2) {
												break;
											}
										} else if (choose2 == 5) {
											System.out.println("Please enter your guarantor:");
											System.out.print("--> ");
											guarantorNo = input.next();
											guarantor = database.General.getCustomer(guarantorNo);
											System.out.println("Please enter amount:");
											System.out.print("--> ");
											amount = input.nextDouble();
											System.out.println("Please enter maturity:");
											System.out.print("--> ");
											maturity = input.nextInt();
											activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
											System.out.println(
													"Please select the account to which the money will be deposited");
											for (project.Account account : activeCustomerAccounts) {

												System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
												System.out.print("Account no: " + account.getAccountNo());
												System.out.println(" Balance: $" + account.getBalance());
											}
											int choose3 = input.nextInt();
											givenLoanAccount = activeCustomerAccounts.get(choose3 - 1);
											long millis = System.currentTimeMillis();
 											java.sql.Date date = new java.sql.Date(millis);
											project.EmployeePersonalFinanceLoan loan = new EmployeePersonalFinanceLoan(
													activeCustomer, guarantor, maturity, amount, date, date, givenLoanAccount);

											System.out.println(loan.toString());
											System.out.println(loan.getAmount());
											System.out.println(loan.getRefundedAmount());

											System.out.println("Do you still want to the loan? Are you serious?");
											System.out.println("1 - Yes I want, I trust myself.");
											System.out.println("2 - WTF how can I pay this loan");
											System.out.print("--> ");
											int choose4 = input.nextInt();
											if (choose4 == 1) {
												bankmanagement.functions.doApplication(
														bankmanagement.functions.createLoanApplication(loan));
											} else if (choose4 == 2) {
												break;
											}
										} else if (choose2 == 6) {
											System.out.println("Please enter your guarantor:");
											System.out.print("--> ");
											guarantorNo = input.next();
											guarantor = database.General.getCustomer(guarantorNo);
											System.out.println("Please enter amount:");
											System.out.print("--> ");
											amount = input.nextDouble();
											System.out.println("Please enter maturity:");
											System.out.print("--> ");
											maturity = input.nextInt();
											activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
											System.out.println(
													"Please select the account to which the money will be deposited");
											for (project.Account account : activeCustomerAccounts) {

												System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
												System.out.print("Account no: " + account.getAccountNo());
												System.out.println(" Balance: $" + account.getBalance());
											}
											int choose3 = input.nextInt();
											givenLoanAccount = activeCustomerAccounts.get(choose3 - 1);
											long millis = System.currentTimeMillis();
 											java.sql.Date date = new java.sql.Date(millis);
											project.RetiredPersonalFinanceLoan loan = new RetiredPersonalFinanceLoan(
													activeCustomer, guarantor, maturity, amount, date, date, givenLoanAccount);

											System.out.println(loan.toString());
											System.out.println(loan.getAmount());
											System.out.println(loan.getRefundedAmount());

											System.out.println("Do you still want to the loan? Are you serious?");
											System.out.println("1 - Yes I want, I trust myself.");
											System.out.println("2 - WTF how can I pay this loan");
											System.out.print("--> ");
											int choose4 = input.nextInt();
											if (choose4 == 1) {
												bankmanagement.functions.doApplication(
														bankmanagement.functions.createLoanApplication(loan));
											} else if (choose4 == 2) {
												break;
											}
										} else if (choose2 == 7) {
											System.out.println("Please enter your guarantor:");
											System.out.print("--> ");
											guarantorNo = input.next();
											guarantor = database.General.getCustomer(guarantorNo);
											System.out.println("Please enter amount:");
											System.out.print("--> ");
											amount = input.nextDouble();
											System.out.println("Please enter maturity:");
											System.out.print("--> ");
											maturity = input.nextInt();
											activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
											System.out.println(
													"Please select the account to which the money will be deposited");
											for (project.Account account : activeCustomerAccounts) {

												System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
												System.out.print("Account no: " + account.getAccountNo());
												System.out.println(" Balance: $" + account.getBalance());
											}
											int choose3 = input.nextInt();
											givenLoanAccount = activeCustomerAccounts.get(choose3 - 1);
											long millis = System.currentTimeMillis();
 											java.sql.Date date = new java.sql.Date(millis);
											project.ConsumerPersonalFinanceLoan loan = new ConsumerPersonalFinanceLoan(
													activeCustomer, guarantor, maturity, amount, date, date, givenLoanAccount);

											System.out.println(loan.toString());
											System.out.println(loan.getAmount());
											System.out.println(loan.getRefundedAmount());

											System.out.println("Do you still want to the loan? Are you serious?");
											System.out.println("1 - Yes I want, I trust myself.");
											System.out.println("2 - WTF how can I pay this loan");
											System.out.print("--> ");
											int choose4 = input.nextInt();
											if (choose4 == 1) {
												bankmanagement.functions.doApplication(
														bankmanagement.functions.createLoanApplication(loan));
											} else if (choose4 == 2) {
												break;
											}
										}
									} else if (activeCustomer instanceof project.CorporateCustomer) {
										System.out.println("1 - Debit Current Account Loan");
										System.out.println("2 - Spot Loan");
										System.out.println("3 - Installment loan");
										System.out.println("4 - Guarantee loan");
										System.out.println("5 - Referance loan");
										System.out.println("6 - Project loan");
										System.out.print("--> ");
										choose = input.nextInt();
										String guarantorNo;
										Customer guarantor;
										double amount;
										int maturity;
										Account givenLoanAccount;

										if (choose == 1) {
											System.out.println("Please enter your guarantor:");
											System.out.print("--> ");
											guarantorNo = input.next();
											guarantor = database.General.getCustomer(guarantorNo);
											System.out.println("Please enter amount:");
											System.out.print("--> ");
											amount = input.nextDouble();
											System.out.println("Please enter maturity:");
											System.out.print("--> ");
											maturity = input.nextInt();
											activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
											System.out.println(
													"Please select the account to which the money will be deposited");
											for (project.Account account : activeCustomerAccounts) {

												System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
												System.out.print("Account no: " + account.getAccountNo());
												System.out.println(" Balance: $" + account.getBalance());
											}
											int choose2 = input.nextInt();
											givenLoanAccount = activeCustomerAccounts.get(choose2 - 1);
											long millis = System.currentTimeMillis();
 											java.sql.Date date = new java.sql.Date(millis);
											project.DebitCurrentAccountLoan loan = new DebitCurrentAccountLoan(activeCustomer, guarantor, maturity, amount, date, date,
													givenLoanAccount);

											System.out.println(loan.toString());
											System.out.println(loan.getAmount());
											System.out.println(loan.getRefundedAmount());

											System.out.println("Do you still want to the loan? Are you serious?");
											System.out.println("1 - Yes I want, I trust myself.");
											System.out.println("2 - WTF how can I pay this loan");
											System.out.print("--> ");
											int choose3 = input.nextInt();
											if (choose3 == 1) {
												bankmanagement.functions.doApplication(
														bankmanagement.functions.createLoanApplication(loan));
											} else if (choose3 == 2) {
												break;
											}
										} else if (choose == 2) {
											System.out.println("Please enter your guarantor:");
											System.out.print("--> ");
											guarantorNo = input.next();
											guarantor = database.General.getCustomer(guarantorNo);
											System.out.println("Please enter amount:");
											System.out.print("--> ");
											amount = input.nextDouble();
											System.out.println("Please enter maturity:");
											System.out.print("--> ");
											maturity = input.nextInt();
											activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
											System.out.println(
													"Please select the account to which the money will be deposited");
											for (project.Account account : activeCustomerAccounts) {

												System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
												System.out.print("Account no: " + account.getAccountNo());
												System.out.println(" Balance: $" + account.getBalance());
											}
											int choose2 = input.nextInt();
											givenLoanAccount = activeCustomerAccounts.get(choose2 - 1);
											long millis = System.currentTimeMillis();
 											java.sql.Date date = new java.sql.Date(millis);
											project.SpotLoan loan = new SpotLoan(activeCustomer,
													guarantor, maturity, amount, date, date, givenLoanAccount);

											System.out.println(loan.toString());
											System.out.println(loan.getAmount());
											System.out.println(loan.getRefundedAmount());

											System.out.println("Do you still want to the loan? Are you serious?");
											System.out.println("1 - Yes I want, I trust myself.");
											System.out.println("2 - WTF how can I pay this loan");
											System.out.print("--> ");
											int choose3 = input.nextInt();
											if (choose3 == 1) {
												bankmanagement.functions.doApplication(
														bankmanagement.functions.createLoanApplication(loan));
											} else if (choose3 == 2) {
												break;
											}
										} else if (choose == 3) {
											System.out.println("Please enter your guarantor:");
											System.out.print("--> ");
											guarantorNo = input.next();
											guarantor = database.General.getCustomer(guarantorNo);
											System.out.println("Please enter amount:");
											System.out.print("--> ");
											amount = input.nextDouble();
											System.out.println("Please enter maturity:");
											System.out.print("--> ");
											maturity = input.nextInt();
											activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
											System.out.println(
													"Please select the account to which the money will be deposited");
											for (project.Account account : activeCustomerAccounts) {

												System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
												System.out.print("Account no: " + account.getAccountNo());
												System.out.println(" Balance: $" + account.getBalance());
											}
											int choose2 = input.nextInt();
											givenLoanAccount = activeCustomerAccounts.get(choose2 - 1);
											long millis = System.currentTimeMillis();
 											java.sql.Date date = new java.sql.Date(millis);
											project.InstallmentLoan loan = new InstallmentLoan(activeCustomer,
													guarantor, maturity, amount, date, date, givenLoanAccount);

											System.out.println(loan.toString());
											System.out.println(loan.getAmount());
											System.out.println(loan.getRefundedAmount());

											System.out.println("Do you still want to the loan? Are you serious?");
											System.out.println("1 - Yes I want, I trust myself.");
											System.out.println("2 - WTF how can I pay this loan");
											System.out.print("--> ");
											int choose3 = input.nextInt();
											if (choose3 == 1) {
												bankmanagement.functions.doApplication(
														bankmanagement.functions.createLoanApplication(loan));
											} else if (choose3 == 2) {
												break;
											}
										} else if (choose == 4) {
											System.out.println("Please enter your guarantor:");
											System.out.print("--> ");
											guarantorNo = input.next();
											guarantor = database.General.getCustomer(guarantorNo);
											System.out.println("Please enter amount:");
											System.out.print("--> ");
											amount = input.nextDouble();
											System.out.println("Please enter maturity:");
											System.out.print("--> ");
											maturity = input.nextInt();
											activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
											System.out.println(
													"Please select the account to which the money will be deposited");
											for (project.Account account : activeCustomerAccounts) {

												System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
												System.out.print("Account no: " + account.getAccountNo());
												System.out.println(" Balance: $" + account.getBalance());
											}
											int choose2 = input.nextInt();
											givenLoanAccount = activeCustomerAccounts.get(choose2 - 1);
											long millis = System.currentTimeMillis();
 											java.sql.Date date = new java.sql.Date(millis);
											project.GuaranteeLoan loan = new GuaranteeLoan(activeCustomer,
													guarantor, maturity, amount, date, date, givenLoanAccount);

											System.out.println(loan.toString());
											System.out.println(loan.getAmount());
											System.out.println(loan.getRefundedAmount());

											System.out.println("Do you still want to the loan? Are you serious?");
											System.out.println("1 - Yes I want, I trust myself.");
											System.out.println("2 - WTF how can I pay this loan");
											System.out.print("--> ");
											int choose3 = input.nextInt();
											if (choose3 == 1) {
												bankmanagement.functions.doApplication(
														bankmanagement.functions.createLoanApplication(loan));
											} else if (choose3 == 2) {
												break;
											}
										} else if (choose == 5) {
											System.out.println("Please enter your guarantor:");
											System.out.print("--> ");
											guarantorNo = input.next();
											guarantor = database.General.getCustomer(guarantorNo);
											System.out.println("Please enter amount:");
											System.out.print("--> ");
											amount = input.nextDouble();
											System.out.println("Please enter maturity:");
											System.out.print("--> ");
											maturity = input.nextInt();
											activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
											System.out.println(
													"Please select the account to which the money will be deposited");
											for (project.Account account : activeCustomerAccounts) {

												System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
												System.out.print("Account no: " + account.getAccountNo());
												System.out.println(" Balance: $" + account.getBalance());
											}
											int choose2 = input.nextInt();
											givenLoanAccount = activeCustomerAccounts.get(choose2 - 1);
											long millis = System.currentTimeMillis();
 											java.sql.Date date = new java.sql.Date(millis);
											project.ReferenceLoan loan = new ReferenceLoan(activeCustomer,
													guarantor, maturity, amount, date, date, givenLoanAccount);

											System.out.println(loan.toString());
											System.out.println(loan.getAmount());
											System.out.println(loan.getRefundedAmount());

											System.out.println("Do you still want to the loan? Are you serious?");
											System.out.println("1 - Yes I want, I trust myself.");
											System.out.println("2 - WTF how can I pay this loan");
											System.out.print("--> ");
											int choose3 = input.nextInt();
											if (choose3 == 1) {
												bankmanagement.functions.doApplication(
														bankmanagement.functions.createLoanApplication(loan));
											} else if (choose3 == 2) {
												break;
											}
										} else if (choose == 6) {
											System.out.println("Please enter your guarantor:");
											System.out.print("--> ");
											guarantorNo = input.next();
											guarantor = database.General.getCustomer(guarantorNo);
											System.out.println("Please enter amount:");
											System.out.print("--> ");
											amount = input.nextDouble();
											System.out.println("Please enter maturity:");
											System.out.print("--> ");
											maturity = input.nextInt();
											activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
											System.out.println(
													"Please select the account to which the money will be deposited");
											for (project.Account account : activeCustomerAccounts) {

												System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
												System.out.print("Account no: " + account.getAccountNo());
												System.out.println(" Balance: $" + account.getBalance());
											}
											int choose2 = input.nextInt();
											givenLoanAccount = activeCustomerAccounts.get(choose2 - 1);
											long millis = System.currentTimeMillis();
 											java.sql.Date date = new java.sql.Date(millis);
											project.ProjectLoan loan = new ProjectLoan(activeCustomer,
													guarantor, maturity, amount, date, date, givenLoanAccount);

											System.out.println(loan.toString());
											System.out.println(loan.getAmount());
											System.out.println(loan.getRefundedAmount());

											System.out.println("Do you still want to the loan? Are you serious?");
											System.out.println("1 - Yes I want, I trust myself.");
											System.out.println("2 - WTF how can I pay this loan");
											System.out.print("--> ");
											int choose3 = input.nextInt();
											if (choose3 == 1) {
												bankmanagement.functions.doApplication(
														bankmanagement.functions.createLoanApplication(loan));
											} else if (choose3 == 2) {
												break;
											}
										}
									}
								} else if (choose == 3) {
									System.out.println("Please choose the account type:");
									System.out.println("1 - Current Account");
									System.out.println("2 - Time Deposit Account");
									System.out.println("3 - Previous Menu");
									System.out.print("--> ");
									int choose2 = input.nextInt();
									if (choose2 == 3) {
										break;
									}
									if (choose2 == 1) {
										Account account = bankmanagement.functions.createCurrentAccount(activeCustomer);

										bankmanagement.functions.doApplication(
												bankmanagement.functions.createAccountApplication(account));
										System.out.println("Your application has been made.");
									} else if (choose2 == 2) {
										project.TimeDepositAccount account = new TimeDepositAccount(activeCustomer,
												password, null, null, null, choose);

										bankmanagement.functions.doApplication(
												bankmanagement.functions.createAccountApplication(account));
										System.out.println("Your application has been made.");
									}
								}
							}

						}

						break;
					case 6:
						while (true) {
							project.Account forwarder;
							project.Account receiver;
							double amount;
							String receiverNo;
							String description;
							System.out.println("\nPlease choose the forwarder account: \n");
							activeCustomerAccounts = database.Customer.getAccounts(activeCustomer);
							for (project.Account account : activeCustomerAccounts) {

								System.out.print(activeCustomerAccounts.indexOf(account) + 1 + " - ");
								System.out.print("Account no: " + account.getAccountNo());
								System.out.println(" Balance: $" + account.getBalance());
							}

							System.out.println("\nFor previous menu, enter 0.\n");
							System.out.print("--> ");

							option = input.nextInt();
							if (option == 0) {
								break;
							}

							forwarder = activeCustomerAccounts.get(option - 1);

							System.out.print("\nReceiver account number: ");
							receiverNo = input.next();
							receiver = database.General.getAccount(receiverNo);

							System.out.print("\nMoney Transfer amount: ");
							amount = input.nextDouble();

							System.out.print("\nMoney Transfer description: ");
							description = input.next();

							project.MoneyTransfer moneyTransfer = bankmanagement.functions
									.createMoneyTransfer(description, forwarder, receiver, amount);

							try {
								bankmanagement.functions.doMoneyTransfer(moneyTransfer);
							} catch (BMMoneyTransferException e) {
								System.out.println(e.getaCause());
							}

							System.out.print("\nTo go to the previous menu, press any key. - ");
							input.next();
							break;
						}
						break;
					case 7:
						double amount;
						while (true) {
							String cardNo;
							String cvv;
							System.out.println("Please choose the payment");
							System.out.println("1 - Mobile Phone bill");
							System.out.println("2 - Electricity bill");
							System.out.println("3 - Internet bill");
							System.out.println("4 - Previous Menu");
							System.out.print("--> ");
							int choose = input.nextInt();
							if (choose == 4) {
								break;
							} else {
								System.out.print("Please enter the payment amount: ");
								amount = input.nextDouble();
								System.out.print("Please enter the card no: ");
								cardNo = input.next();
								System.out.print("Please enter the cvv: ");
								cvv = input.next();

								project.Card card = database.General.getCard(cardNo);
								try {
									bankmanagement.functions.doPaymentWithCard(
											bankmanagement.functions.createPayment(card, amount), cvv);
								} catch (BMPaymentException e) {
									e.getaCause();
								}
							}
						}
						break;
					case 8:
						String presentPassword;
						String newPassword;
						System.out
								.println("If you want to change your password you have to know your present password");
						System.out.println("Enter your present password: ");
						System.out.print("--> ");
						presentPassword = input.next();
						if (new String(presentPassword).equals(activeCustomer.getUserPass())) {
							System.out.println("Enter your new password");
							System.out.print("--> ");
							newPassword = input.next();
							bankmanagement.functions.changePassword(activeCustomer, newPassword);
						} else {
							System.out.println("Are you dumb? Wrong password");
							System.out.println("Please do not try again. I have work to do.");
						}
						break;
					case 9:
						main(args);
						break;
					case 10:
						System.out.println("\nHave a good day!");
						System.exit(0);
						break;
					default:
						System.out.println("Are you blind? There are just 10 options for you.");
					}
				} else {
					System.out.println("Wrong username or password.");
					main(args);
				}
			}
		case 2:
			// ADMINISTRATOR
			System.out.print("\nUsername: ");
			userName = input.next();
			System.out.print("Password: "); // asterix
			password = input.next();
			activeEmployee = database.General.getEmployee(userName);
			if (bankmanagement.functions.checkEmployeePass(userName, password)) {
				System.out.println("1 - Add Customer");
				System.out.println("2 - Add Worker");
				System.out.println("3 - Waiting applications");
				System.out.println("4 - Employee informations");
				System.out.println("5 - Change password");
				System.out.println("6 - Exit");
				System.out.print("--> ");
				option = input.nextInt();

				switch (option) {
				case 1:
					while (true) {
						String customerName;
						String adress;
						String mail;
						double income;
						System.out.println("1 - Corporate Customer");
						System.out.println("2 - Individual Customer");
						System.out.print("--> ");
						int choose = input.nextInt();
						System.out.print("Customer name: ");
						customerName = input.next();
						System.out.print("Adress: ");
						adress = input.next();
						System.out.print("Mail: ");
						mail = input.next();
						System.out.print("Income: ");
						income = input.nextDouble();
						if (choose == 1) {

							String taxNo;
							System.out.print("Tax no: ");
							taxNo = input.next();
							bankmanagement.functions.createCorporateCustomer(customerName, adress, mail, income,
									activeEmployee, taxNo);
							System.out.println("Create Customer is succesful!");
						} else if (choose == 2) {
							String individualId;
							System.out.print("Individual id: ");
							individualId = input.next();
							String occupation;
							System.out.print("occupation: ");
							occupation = input.next();
							java.util.Date birthDateUtil = null;
							System.out.print("Birth Date(dd/mm/yyyy): ");
							String dateInput = input.next();
							if (null != dateInput && dateInput.trim().length() > 0) {
								birthDateUtil = format.parse(dateInput);
							}
							java.sql.Date birthDate = new java.sql.Date(birthDateUtil.getTime());

							// String name, String adress, String mail, double income, Employee
							// chargeEmployee, String individualID, String occupation, Date birthDate
							bankmanagement.functions.createIndividualCustomer(customerName, adress, mail, income,
									activeEmployee, individualId, occupation, birthDate);
							System.out.println("\nCreate Customer is succesful!");
							break;
						}
					}
					break;
				case 2:
					String name;
					System.out.print("Worker name: ");
					name = input.next();

					String id;
					System.out.print("Worker id: ");
					id = input.next();

					String mail;
					System.out.print("Worker mail: ");
					mail = input.next();

					double salary;
					System.out.print("Worker salary: ");
					salary = input.nextDouble();

					try {
						bankmanagement.functions.createWorker(name, id, salary, mail, activeEmployee);
					} catch (BMAddWorkerException e) {
						System.out.println(e.getaCause());
					}
					System.out.println("Workerýmýz hayýrlý olsun");
					break;
				case 3:
					ArrayList<project.Application> applications = database.General.getUnfinishedApplication(activeEmployee);
					for (Application application : applications) {
						
						System.out.println(" - " + (applications.indexOf(application) + 1 )+ " - ");
						if(application instanceof project.LoanApplication) {
							System.out.println("Loan application");
						}
						else if(application instanceof project.CardApplication) {
							System.out.println("Card application");
						}
						else if(application instanceof project.AccountApplication) {
							System.out.println("Account application");
						}
						System.out.println("Application No: " + application.getApplicationNo());
						System.out.println("Application Status: " + application.getApplicationStatus());
						System.out.println("Applicant No: " + application.getApplicant().getCustomerNo());
						System.out.println("Applicant Name: " + application.getApplicant().getName());
						System.out.println("Applicant Salary: " + application.getApplicant().getIncome());
						if (application instanceof project.LoanApplication) {
							project.LoanApplication app = (LoanApplication) application;
							System.out.println("Loan No: " + app.getLoan().getLoanNo());
							System.out.println("Amount: " + app.getLoan().getAmount());
							System.out.println("Interest: " + app.getLoan().getInterest());
							System.out.println("Maturity: " + app.getLoan().getMaturity());
							// kalan bilgiler yazdýrýlacak
						} else if (application instanceof project.CardApplication) {
							project.CardApplication app = (CardApplication) application;
							if (app.getCard() instanceof project.GoldCard) {
								project.GoldCard creditCard = (project.GoldCard) app.getCard();
								System.out.println("Card No: " + creditCard.getCardNo());
								System.out.println("Card Limit: " + creditCard.getCardLimit());
							} else if (app.getCard() instanceof project.GoldCardBusiness) {
								project.GoldCardBusiness creditCard = (project.GoldCardBusiness) app.getCard();
								System.out.println("Card No: " + creditCard.getCardNo());
								System.out.println("Card Limit: " + creditCard.getCardLimit());
							} else if (app.getCard() instanceof project.SilverCardBusiness) {
								project.SilverCardBusiness creditCard = (project.SilverCardBusiness) app.getCard();
								System.out.println("Card No: " + creditCard.getCardNo());
								System.out.println("Card Limit: " + creditCard.getCardLimit());
							} else if (app.getCard() instanceof project.SilverCard) {
								project.SilverCard creditCard = (project.SilverCard) app.getCard();
								System.out.println("Card No: " + creditCard.getCardNo());
								System.out.println("Card Limit: " + creditCard.getCardLimit());
							} else if (app.getCard() instanceof project.BankCardCustomer) {
								project.BankCardCustomer creditCard = (project.BankCardCustomer) app.getCard();
								System.out.println("Card No: " + creditCard.getCardNo());
								System.out.println("Card Balance: " + creditCard.getBalance());
							} else if (app.getCard() instanceof project.YoungCard) {
								project.YoungCard creditCard = (project.YoungCard) app.getCard();
								System.out.println("Card No: " + creditCard.getCardNo());
								System.out.println("Card Balance: " + creditCard.getBalance());
							} else if (app.getCard() instanceof project.BankCardBusiness) {
								project.BankCardBusiness creditCard = (project.BankCardBusiness) app.getCard();
								System.out.println("Card No: " + creditCard.getCardNo());
								System.out.println("Card Balance: " + creditCard.getBalance());
							}

							// ifler bütün kardlar için yapýlacak

							//
							//
							//
						} else if (application instanceof project.AccountApplication) {
							project.AccountApplication app = (AccountApplication) application;
							System.out.println(app.getApplicationNo());
							System.out.println(app.getApplicant().getCustomerNo());
							System.out.println(app.getApplicant().getName());
							System.out.println(app.getAccount().getAccountNo());
							if (app.getAccount() instanceof project.TimeDepositAccount) {
								project.TimeDepositAccount tda = (TimeDepositAccount) app.getAccount();
								System.out.println(tda.getInterest());
							}
						}

					}
					System.out.println("Choose application");
					int chooseApp;
					chooseApp = input.nextInt();
					project.Application selectedApplication = applications.get(chooseApp - 1);
					System.out.println(selectedApplication.getApplicationNo());
					System.out.println("1 - Approve the application");
					System.out.println("2 - Refuse the application");
					chooseApp = input.nextInt();
					if (chooseApp == 1) {
						if (selectedApplication instanceof project.LoanApplication) {
							try {
								bankmanagement.functions.confirmLoan((project.LoanApplication) selectedApplication,
										activeEmployee);
							} catch (BMApplicationDecideException e) {
								e.getaCause();
							}

						} else if (selectedApplication instanceof project.CardApplication) {
							try {
								bankmanagement.functions.confirmCard((project.CardApplication) selectedApplication,
										activeEmployee);
							} catch (BMApplicationDecideException e) {
								e.getaCause();
							}
						} else if (selectedApplication instanceof project.AccountApplication) {
							try {
								bankmanagement.functions.confirmAccount(
										(project.AccountApplication) selectedApplication, activeEmployee);
							} catch (BMApplicationDecideException e) {
								e.getaCause();
							}
						}
					} else if (chooseApp == 2) {
						if (selectedApplication instanceof project.LoanApplication) {
							try {
								bankmanagement.functions.refuseLoan((project.LoanApplication) selectedApplication,
										activeEmployee);
							} catch (BMApplicationDecideException e) {
								e.getaCause();
							}

						} else if (selectedApplication instanceof project.CardApplication) {
							try {
								bankmanagement.functions.refuseCard((project.CardApplication) selectedApplication,
										activeEmployee);
							} catch (BMApplicationDecideException e) {
								e.getaCause();
							}
						} else if (selectedApplication instanceof project.AccountApplication) {
							try {
								bankmanagement.functions.refuseAccount((project.AccountApplication) selectedApplication,
										activeEmployee);
							} catch (BMApplicationDecideException e) {
								e.getaCause();
							}
						}
					}
					System.out.println("Process successful");

					break;
				case 4:
					System.out.println("\nEmployee informations: \n");
					System.out.println("Name: " + activeEmployee.getName());
					System.out.println("ID: " + activeEmployee.getId());
					System.out.println("Bonus: " + activeEmployee.getBonus());
					System.out.println("Mail: " + activeEmployee.getMail());
					System.out.println("Salary: " + activeEmployee.getSalary());
					// bla bla bla bla
					break;
				case 5:
					String presentPassword;
					String newPassword;
					System.out.println("If you want to change your password you have to know your present password.");
					System.out.println("Enter your present password: ");
					System.out.print("--> ");
					presentPassword = input.next();
					if (new String(presentPassword).equals(activeEmployee.getUserPassword())) {
						System.out.println("Congratulations! You can changed your password!");
						System.out.println("Enter your new password");
						System.out.print("--> ");
						newPassword = input.next();
						bankmanagement.functions.changeEmployeePassword(activeEmployee, newPassword);
					} else {
						System.out.println("Are you dumb? Wrong password");
						System.out.println("Please do not try again. I have work to do.");
					}
					break;
				case 6:
					System.out.println("6 - Exit");
					break;
				default:
					break;
				}

			} else {
				System.out.println("Wrong username or password.");
				main(args);
			}
			break;
		case 3:
			System.out.println("1 - Individual Customer");
			System.out.println("2 - Corporate Customer");
			int choose = input.nextInt();
			System.out.print("Name: ");
			String name = input.next();
			System.out.print("User Password:");
			String userPass = input.next();
			System.out.print("Adress: ");
			String adress = input.next();
			System.out.print("Mail: ");
			String mail = input.next();
			System.out.print("Income: ");
			double income = input.nextDouble();
			if (choose == 1) {
				System.out.print("Individual ID: ");
				String individualID = input.next();
				System.out.print("Occupation: ");
				String occupation = input.next();
				java.util.Date birthDateUtil = null;
				System.out.print("Birth Date(dd/mm/yyyy): ");
				String dateInput = input.next();
				if (null != dateInput && dateInput.trim().length() > 0) {
					birthDateUtil = format.parse(dateInput);
				}
				java.sql.Date birthDate = new java.sql.Date(birthDateUtil.getTime());
				Customer customer = bankmanagement.functions.createIndividualCustomer(name, userPass, adress, mail, income, individualID,
						occupation, birthDate);
				System.out.println("Welcome to the club. Lets party start!");
				System.out.println("You can enter the system with user name: " + customer.getUserName());

			} else if (choose == 2) {
				System.out.println("Tax No: ");
				String taxNo = input.next();
				Customer customer = bankmanagement.functions.createCorporateCustomer(name, userPass, adress, mail, income, taxNo);
				System.out.println("Welcome to the club. Lets party start!");
				System.out.println("You can enter the system with user name: " + customer.getUserName());
			}
			break;
		case 4:
			System.out.println("\nHave a good day!");
			System.exit(0);
			break;
		default:
			System.out.println("Are you blind? There are just 4 options for you.");
			main(args);
			// break;

		}
	}
}
