package com.sid.onlonebanking.service;

import java.util.List;

import com.sid.onlonebanking.vo.PrimaryTransaction;

public interface GenerateStatementService {
	public void createPdf(List<PrimaryTransaction> pList);

}
