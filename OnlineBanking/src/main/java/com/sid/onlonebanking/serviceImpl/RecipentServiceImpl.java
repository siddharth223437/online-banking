package com.sid.onlonebanking.serviceImpl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sid.onlonebanking.repository.PrimaryTransactionRepository;
import com.sid.onlonebanking.repository.RecpientRepository;
import com.sid.onlonebanking.repository.SavingsTransactionsRepository;
import com.sid.onlonebanking.repository.UserRepository;
import com.sid.onlonebanking.service.RecipentService;
import com.sid.onlonebanking.vo.AccountResponse;
import com.sid.onlonebanking.vo.PrimaryAccount;
import com.sid.onlonebanking.vo.PrimaryTransaction;
import com.sid.onlonebanking.vo.RecipentVO;
import com.sid.onlonebanking.vo.SavingsAccount;
import com.sid.onlonebanking.vo.SavingsTransaction;
import com.sid.onlonebanking.vo.Users;

@Service
public class RecipentServiceImpl implements RecipentService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RecpientRepository recpientRepository;
	
	@Autowired
	private PrimaryTransactionRepository primaryTransacRepository;
	
	@Autowired
	private SavingsTransactionsRepository savingsTransactionRepo;

	@Override
	public RecipentVO addByEmail(String email, String name, String username) {
		Users user = userRepository.findByUsername(username);
		if(!user.getEmail().equalsIgnoreCase(email)) {
			RecipentVO recpient = new RecipentVO();
			recpient.setRecipentEmail(email);
			recpient.setRecipentName(name);
			recpient.setUsername(username);
			recpientRepository.save(recpient);
			return recpient;
		}
		return null;
	}

	@Override
	public List<RecipentVO> findRecipientWithUsername(String username) {
		Users user = userRepository.findByUsername(username);
		if(user != null) {
			List<RecipentVO> recipList = recpientRepository.findByUsername(username);
			return recipList;
		}
		return null;
	}

	@Override
	public boolean checkRecipientEmail(String email) {
		RecipentVO resip = recpientRepository.findByrecipentEmail(email);
		if(resip != null) {
			return false;
		}
		return true;
	}

	@Override
	public AccountResponse<PrimaryTransaction> transferToSomeone(String from, String toEmail, String amount, String username) {
		AccountResponse<PrimaryTransaction> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(username);
		if(from.equals("Primary")) {
			PrimaryAccount pa = user.getPrimaryAccount();
			double amt = Double.parseDouble(amount);
			pa.setAccountBalance(pa.getAccountBalance() - amt);
			if(pa.getAccountBalance() < 0) {
				resp.setStatus(false);
				resp.setMessage("Not enough funds to transfer");
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
			pt.setType(from);
			pt.setDescription("Money trasfered to"+" "+toEmail);
			pt.setStatus("Transaction Completed");
			pt.setPrimaryAccount(pa);
			pt.setUsername(username);
			primaryTransacRepository.save(pt);
			
			// to deposit into email address
			Users findUserByEmail = userRepository.findByEmail(toEmail);
			PrimaryAccount pa1 = findUserByEmail.getPrimaryAccount();
			pa1.setAccountBalance(pa1.getAccountBalance() + amt);
			String str1 = new DecimalFormat("##.###").format(pa1.getAccountBalance());
			pa1.setAccountBalance(Double.parseDouble(str1));
			findUserByEmail.setPrimaryAccount(pa1);
			userRepository.save(findUserByEmail);
			PrimaryTransaction pt1 = new PrimaryTransaction();
			pt1.setAvailableBalance(findUserByEmail.getPrimaryAccount().getAccountBalance());
			pt1.setDate(date);
			pt1.setAmount(amount);
			pt1.setType(user.getfName());
			pt1.setDescription("Money Recieved from"+" "+user.getEmail());
			pt1.setStatus("Transaction Completed");
			pt1.setPrimaryAccount(pa1);
			pt1.setUsername(findUserByEmail.getUsername());
			primaryTransacRepository.save(pt1);
			
			resp.setMessage("Transfe Completed");
			resp.setResponseObject(pt1);
			resp.setStatus(true);
			return resp;
		}
		if(from.equals("Savings")) {
			SavingsAccount sa = user.getSavingsAccount();
			double amt = Double.parseDouble(amount);
			sa.setAccountBalance(sa.getAccountBalance() - amt);
			if(sa.getAccountBalance() < 0) {
				resp.setStatus(false);
				resp.setMessage("Not enough funds to transfer");
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
			st.setType(from);
			st.setDescription("Money trasfered to"+" "+toEmail);
			st.setStatus("Transaction Completed");
			st.setSavingsAccount(sa);
			st.setUsername(username);
			savingsTransactionRepo.save(st);
			
			// to deposit into email address
			Users findUserByEmail = userRepository.findByEmail(toEmail);
			SavingsAccount sa1 = findUserByEmail.getSavingsAccount();
			sa1.setAccountBalance(sa1.getAccountBalance() + amt);
			String str1 = new DecimalFormat("##.###").format(sa1.getAccountBalance());
			sa1.setAccountBalance(Double.parseDouble(str1));
			findUserByEmail.setSavingsAccount(sa1);
			userRepository.save(findUserByEmail);
			SavingsTransaction st1 = new SavingsTransaction();
			st1.setAvailableBalance(findUserByEmail.getSavingsAccount().getAccountBalance());
			st1.setDate(date);
			st1.setAmount(amount);
			st1.setType(user.getfName());
		    st1.setDescription("Money Recieved from"+" "+user.getEmail());
			st1.setStatus("Transaction Completed");
			st1.setSavingsAccount(sa1);
			st1.setUsername(findUserByEmail.getUsername());
			savingsTransactionRepo.save(st1);
			
			resp.setMessage("Transfe Completed");
			//resp.setResponseObject(st1);
			resp.setStatus(true);
			return resp;
		
		}
		return resp;
	}

}
