package com.uap.it1311l.passwordencryptorapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uap.it1311l.passwordencryptorapi.models.EncryptionResponse;
import com.uap.it1311l.passwordencryptorapi.webclient.EncryptionApiClient;
import com.uap.it1311l.passwordencryptorapi.webclient.EncryptionMyBatisMapper;

@Service
public class EncryptDecryptService {

	@Autowired
	EncryptionApiClient apiClient;
	
	@Autowired
	EncryptionMyBatisMapper EncryptmyBatisMapper;
	
	
	public EncryptionResponse encrypt(String password) {
		EncryptionResponse pass = apiClient.encrypt("hotchocolate", password, "AES");
		EncryptmyBatisMapper.insert(pass.getResult());
		return pass;
	}
	
	public String decrypt(String word) {
		if (EncryptmyBatisMapper.exists(word) > 0) {
			EncryptionResponse pass = apiClient.decrypt("hotchocolate", word, "AES");
			return pass.getResult();
		} else {
			return "Encrypted Password does not exist.";
		}
	}
	
	
}
