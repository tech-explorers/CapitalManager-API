package com.capital.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capital.constants.GeneralConstants;
import com.capital.dao.UserDao;
import com.capital.entities.LoginEntity;
import com.capital.entities.UserEntity;
import com.capital.model.ResponseModel;
import com.capital.model.UserResponse;

@Service
@Transactional
public class UserService {

	@Autowired
	private ResponseModel<UserResponse> responseModel;

	@Autowired
	private UserResponse userResponse;

	@Autowired
	private UserDao dao;

	static Logger logger = Logger.getLogger(UserService.class.getName());

	public List<LoginEntity> getAllUsers() {
		logger.info("Seq Value : " + dao.seqNextVal("group_groupid"));
		return dao.getAllUsers();
	}

	public ResponseModel<UserResponse> registerUser(UserEntity userEntity) {
		responseModel.clear();
		userResponse.clear();
		String loginID = dao.seqNextVal("login_details_loginid");
		userEntity.setUserId(GeneralConstants.STRING_ID);
		userEntity.getLoginEntity().setEmailId(userEntity.getEmailId());
		userEntity.getLoginEntity().setLoginid(loginID);

		boolean result = dao.registerUser(userEntity);
		logger.info("The register result is -----" + result);
		if (result) {
			// Create Response Object
			userResponse.setFirstName(userEntity.getFirstName());
			userResponse.setMiddleName(userEntity.getMiddleName());
			userResponse.setLastName(userEntity.getLastName());

			responseModel.setResponseObject(userResponse);
			responseModel.setResponseMessage(GeneralConstants.REGISTER_SUCCESS_MSG);
		}

		else {
			responseModel.setResponseMessage(GeneralConstants.REGISTER_FAIL_MSG);
		}

		return responseModel;
	}

}
