package com.sid.onlonebanking.controller;

import java.security.Principal;
import java.security.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sid.onlonebanking.repository.AccountTransactionRepository;
import com.sid.onlonebanking.repository.PrimaryAccountRepository;
import com.sid.onlonebanking.repository.PrimaryTransactionRepository;
import com.sid.onlonebanking.repository.SavingsAccountRepository;
import com.sid.onlonebanking.repository.SavingsTransactionsRepository;
import com.sid.onlonebanking.repository.UserRepository;
import com.sid.onlonebanking.serviceImpl.UserService;
import com.sid.onlonebanking.vo.AccountResponse;
import com.sid.onlonebanking.vo.AccountTransaction;
import com.sid.onlonebanking.vo.PrimaryAccount;
import com.sid.onlonebanking.vo.PrimaryTransaction;
import com.sid.onlonebanking.vo.SavingsAccount;
import com.sid.onlonebanking.vo.SavingsTransaction;
import com.sid.onlonebanking.vo.Users;



@RestController
@CrossOrigin
@RequestMapping("account")
public class AccountController {
	
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PrimaryTransactionRepository primaryTransacRepository;
	
	@Autowired
	private SavingsTransactionsRepository savingsTransactionRepo;
	
	@GetMapping("/welcome/{username}")
	public AccountResponse<Users> showHomePage(@PathVariable("username") String username, Principal principal){
		AccountResponse<Users> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(username);
		if(user!= null) {
			resp.setResponseObject(user);
			resp.setStatus(true);
			resp.setMessage("Showing primary account");
			return resp;
		}
		resp.setStatus(false);
		return resp;
	}
	
	@GetMapping("/deposit/{amount}/{type}")
	public AccountResponse<AccountTransaction> deposit(Principal principal, @PathVariable("amount") String amount,@PathVariable("type") String type){
		AccountResponse<AccountTransaction> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(principal.getName());
		if(user != null) {
			if(type.equals("Primary")) {
				PrimaryAccount pa = user.getPrimaryAccount();
				double amt = Double.parseDouble(amount);
				pa.setAccountBalance(pa.getAccountBalance() + amt);
				String str = new DecimalFormat(".##").format(pa.getAccountBalance());
				pa.setAccountBalance(Double.parseDouble(str));
				user.setPrimaryAccount(pa);
				userRepository.save(user);
				 //primaryActRepo.save(pa);
				PrimaryTransaction pt = new PrimaryTransaction();
				Date date = new Date();
				pt.setAvailableBalance(user.getPrimaryAccount().getAccountBalance());
				pt.setDate(date);
				pt.setAmount(amount);
				pt.setType(type);
				pt.setDescription("Credit to primary account");
				pt.setStatus("Transaction Completed");
				pt.setPrimaryAccount(pa);
				pt.setUsername(principal.getName());
				// primaryActRepo.save(pa);
			
				//AccountTransaction at = new AccountTransaction();
				//at.setUsername(principal.getName());
				//at.setPrimaryTransaction(pt);
				 primaryTransacRepository.save(pt);
				//at.setCreatedDate(date);
				//accountTrasnsRepo.save(at);
				//resp.setResponseObject(at);
				resp.setMessage("Deposited to Primary Account Successful");
				resp.setStatus(true);
				return resp;
			}
			if(type.equals("Savings")) {
				SavingsAccount sa = user.getSavingsAccount();
				double amt = Double.parseDouble(amount);
				sa.setAccountBalance(sa.getAccountBalance() + amt);
				String str = new DecimalFormat(".##").format(sa.getAccountBalance());
				sa.setAccountBalance(Double.parseDouble(str));
				user.setSavingsAccount(sa);
				userRepository.save(user);
				 //savingsActRepo.save(sa);
				SavingsTransaction st = new SavingsTransaction();
				Date date = new Date();
				st.setAvailableBalance(user.getSavingsAccount().getAccountBalance());
				st.setDate(date);
				st.setAmount(amount);
				st.setType(type);
				st.setDescription("Credit to Savings account");
				st.setStatus("Transaction Completed");
			     st.setSavingsAccount(sa);
				st.setUsername(principal.getName());
				//primaryActRepo.save(pa);
				//AccountTransaction at = new AccountTransaction();
				//at.setUsername(principal.getName());
				//at.setSavingsTransaction(st);
				//at.setCreatedDate(date);
				 savingsTransactionRepo.save(st);
				//accountTrasnsRepo.save(at);
				//resp.setResponseObject(at);
				resp.setMessage("Deposited to Savings Account Successful");
				resp.setStatus(true);
				return resp;
			}
		}
		return resp;
	}
	
	@GetMapping("/transaction/primary")
	public AccountResponse<List<PrimaryTransaction>> allTransactionPrimary(Principal principle) {
		AccountResponse<List<PrimaryTransaction>> resp = new AccountResponse<>();
		List<PrimaryTransaction> ptra = primaryTransacRepository.findByusername(principle.getName());
		Collections.sort(ptra, new Comparator<PrimaryTransaction>() {

			@Override
			public int compare(PrimaryTransaction o1, PrimaryTransaction o2) {
				return o2.getDate().compareTo(o1.getDate());
			}
		});
		resp.setResponseObject(ptra);
		return resp;
	}
	
	@GetMapping("/transaction/savings")
	public AccountResponse<List<SavingsTransaction>> allTransactionSavings(Principal principle) {
		AccountResponse<List<SavingsTransaction>> resp = new AccountResponse<>();
		List<SavingsTransaction> ptra = savingsTransactionRepo.findByUsername(principle.getName());
		Collections.sort(ptra, new Comparator<SavingsTransaction>() {

			@Override
			public int compare(SavingsTransaction o1, SavingsTransaction o2) {
				return o2.getDate().compareTo(o1.getDate());
			}
		});
		resp.setResponseObject(ptra);
		return resp;
	}
	
	@GetMapping("/withdraw/{amount}/{type}")
	public AccountResponse<PrimaryTransaction> withdraw(Principal principal, @PathVariable("amount") String amount,@PathVariable("type") String type)
	{
		AccountResponse<PrimaryTransaction> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(principal.getName());
		if(user != null) {
			//primaryActRepo.save(pa);
			if(type.equals("Primary")) {
				PrimaryAccount pa = user.getPrimaryAccount();
				double amt = Double.parseDouble(amount);
				pa.setAccountBalance(pa.getAccountBalance() - amt);
				if(pa.getAccountBalance() < 0) {
					resp.setMessage("You don't have enough funds to withdraw");
					return resp;
				}
				String str = new DecimalFormat("##.###").format(pa.getAccountBalance());
				pa.setAccountBalance(Double.parseDouble(str));
				user.setPrimaryAccount(pa);
				userRepository.save(user);
				PrimaryTransaction pt = new PrimaryTransaction();
				Date date = new Date();
				pt.setAvailableBalance(user.getPrimaryAccount().getAccountBalance());
				pt.setDate(date);
				pt.setAmount("-"+amount);
				pt.setType(type);
				pt.setDescription("Debit from Primary Account");
				pt.setStatus("Transaction Completed");
				pt.setPrimaryAccount(pa);
				pt.setUsername(principal.getName());
				//primaryActRepo.save(pa);
				primaryTransacRepository.save(pt);
				resp.setResponseObject(pt);
				resp.setMessage("Money withdraw is successful");
				resp.setStatus(true);
			}
			if(type.equals("Savings")) {
				SavingsAccount sa = user.getSavingsAccount();
				double amt = Double.parseDouble(amount);
				sa.setAccountBalance(sa.getAccountBalance() - amt);
				if(sa.getAccountBalance() < 0) {
					resp.setMessage("You don't have enough funds to withdraw");
					return resp;
				}
				String str = new DecimalFormat("##.###").format(sa.getAccountBalance());
				sa.setAccountBalance(Double.parseDouble(str));
				user.setSavingsAccount(sa);
				userRepository.save(user);
				SavingsTransaction st = new SavingsTransaction();
				Date date = new Date();
				st.setAvailableBalance(user.getSavingsAccount().getAccountBalance());
				st.setDate(date);
				st.setAmount("-"+amount);
				st.setType(type);
				st.setDescription("Debit from Savings Account");
				st.setStatus("Transaction Completed");
				st.setSavingsAccount(sa);
				st.setUsername(principal.getName());
				//primaryActRepo.save(pa);
				savingsTransactionRepo.save(st);
				//resp.setResponseObject(pt);
				resp.setMessage("Money withdraw is successful");
				resp.setStatus(true);
			}
		}
		return resp;
	}
	@GetMapping("/transfer/{act1}/{act2}/{amount}")
	public AccountResponse<PrimaryTransaction> transferBetAct(Principal principal, @PathVariable("act1") String act1, @PathVariable("act2") String act2, @PathVariable("amount") String amount) {
		AccountResponse<PrimaryTransaction> resp = new AccountResponse<>();
		Users users = userRepository.findByUsername(principal.getName());
		if(users != null & act1.equalsIgnoreCase("Primary") && act2.equalsIgnoreCase("Savings")) {
			PrimaryAccount pa = users.getPrimaryAccount();
			SavingsAccount sa = users.getSavingsAccount();
			double amt = Double.parseDouble(amount);
			pa.setAccountBalance(pa.getAccountBalance() - amt);
			sa.setAccountBalance(sa.getAccountBalance() + amt);
			if(pa.getAccountBalance() < 0) {
				resp.setMessage("Not enough funds to transfer");
				return resp;
			}
			String str = new DecimalFormat("##.###").format(pa.getAccountBalance());
			pa.setAccountBalance(Double.parseDouble(str));
			String str2 = new DecimalFormat("##.###").format(sa.getAccountBalance());
			sa.setAccountBalance(Double.parseDouble(str2));
			users.setPrimaryAccount(pa);
			users.setSavingsAccount(sa);
			userRepository.save(users);
			PrimaryTransaction pt = new PrimaryTransaction();
			SavingsTransaction st = new SavingsTransaction();
			Date date = new Date();
			pt.setAvailableBalance(users.getPrimaryAccount().getAccountBalance());
			pt.setDate(date);
			pt.setAmount("-"+amount);
			pt.setType(act1);
			pt.setDescription("Transfered to Savings Account");
			pt.setStatus("Transfer Completed");
			pt.setPrimaryAccount(pa);
			pt.setUsername(principal.getName());
			primaryTransacRepository.save(pt);
			resp.setResponseObject(pt);
			resp.setMessage("Money Transfered to savings successful");
			resp.setStatus(true);
			// for savings transaction List
			st.setAvailableBalance(users.getSavingsAccount().getAccountBalance());
			st.setDate(date);
			st.setAmount(amount);
			st.setType(act2);
			st.setDescription("Recievd money from Primary Act");
			st.setStatus("Recieved Completed");
			st.setSavingsAccount(sa);
			st.setUsername(principal.getName());
			savingsTransactionRepo.save(st);
			//resp.setResponseObject(pt);
			resp.setMessage("Money receieved from Primary Act successful");
			resp.setStatus(true);
		}
		
		if(users != null & act1.equalsIgnoreCase("Savings") && act2.equalsIgnoreCase("Primary")) {
			PrimaryAccount pa = users.getPrimaryAccount();
			SavingsAccount sa = users.getSavingsAccount();
			double amt = Double.parseDouble(amount);
			pa.setAccountBalance(pa.getAccountBalance() + amt);
			sa.setAccountBalance(sa.getAccountBalance() - amt);
			if(sa.getAccountBalance() < 0) {
				resp.setMessage("Not enough funds to transfer");
				return resp;
			}
			String str = new DecimalFormat("##.###").format(pa.getAccountBalance());
			pa.setAccountBalance(Double.parseDouble(str));
			String str2 = new DecimalFormat("##.###").format(sa.getAccountBalance());
			sa.setAccountBalance(Double.parseDouble(str2));
			users.setPrimaryAccount(pa);
			users.setSavingsAccount(sa);
			userRepository.save(users);
			PrimaryTransaction pt = new PrimaryTransaction();
			SavingsTransaction st = new SavingsTransaction();
			Date date = new Date();
			pt.setAvailableBalance(users.getPrimaryAccount().getAccountBalance());
			pt.setDate(date);
			pt.setAmount(amount);
			pt.setType(act2);
			pt.setDescription("Receievd monet from Savings");
			pt.setStatus("Received Completed");
			pt.setPrimaryAccount(pa);
			pt.setUsername(principal.getName());
			primaryTransacRepository.save(pt);
			resp.setResponseObject(pt);
			resp.setMessage("Money Recieved from savings successful");
			resp.setStatus(true);
			// for savings transaction List
			st.setAvailableBalance(users.getSavingsAccount().getAccountBalance());
			st.setDate(date);
			st.setAmount("-"+amount);
			st.setType(act1);
			st.setDescription("Transfered money to Primary Act");
			st.setStatus("Transfer Completed");
			st.setSavingsAccount(sa);
			st.setUsername(principal.getName());
			savingsTransactionRepo.save(st);
			//resp.setResponseObject(pt);
			resp.setMessage("Money Transfered to Primary Act successful");
			resp.setStatus(true);
		}
		return resp;
	}
	

}
