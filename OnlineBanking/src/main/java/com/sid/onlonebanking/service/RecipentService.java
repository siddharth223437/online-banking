package com.sid.onlonebanking.service;

import java.util.List;

import com.sid.onlonebanking.vo.AccountResponse;
import com.sid.onlonebanking.vo.PrimaryTransaction;
import com.sid.onlonebanking.vo.RecipentVO;

public interface RecipentService {
	
	public RecipentVO addByEmail(String email, String name, String userName);
	public List<RecipentVO> findRecipientWithUsername(String username);
	public boolean checkRecipientEmail(String email);
	public AccountResponse<PrimaryTransaction> transferToSomeone(String from, String toEmail, String amount,String username);

}
